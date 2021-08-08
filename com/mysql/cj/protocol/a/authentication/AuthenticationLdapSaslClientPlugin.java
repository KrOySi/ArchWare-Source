/*
 * Decompiled with CFR 0.150.
 */
package com.mysql.cj.protocol.a.authentication;

import com.mysql.cj.Messages;
import com.mysql.cj.conf.PropertyKey;
import com.mysql.cj.exceptions.CJException;
import com.mysql.cj.exceptions.ExceptionFactory;
import com.mysql.cj.protocol.AuthenticationPlugin;
import com.mysql.cj.protocol.Protocol;
import com.mysql.cj.protocol.a.NativeConstants;
import com.mysql.cj.protocol.a.NativePacketPayload;
import com.mysql.cj.sasl.ScramShaSaslProvider;
import com.mysql.cj.util.StringUtils;
import java.security.PrivilegedActionException;
import java.security.Security;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import javax.security.auth.Subject;
import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.NameCallback;
import javax.security.auth.callback.PasswordCallback;
import javax.security.auth.callback.UnsupportedCallbackException;
import javax.security.auth.login.AppConfigurationEntry;
import javax.security.auth.login.Configuration;
import javax.security.auth.login.LoginContext;
import javax.security.auth.login.LoginException;
import javax.security.sasl.Sasl;
import javax.security.sasl.SaslClient;
import javax.security.sasl.SaslException;

public class AuthenticationLdapSaslClientPlugin
implements AuthenticationPlugin<NativePacketPayload> {
    public static String PLUGIN_NAME = "authentication_ldap_sasl_client";
    private static final String LOGIN_CONFIG_ENTRY = "MySQLConnectorJ";
    private static final String LDAP_SERVICE_NAME = "ldap";
    private Protocol<?> protocol = null;
    private String user;
    private String password;
    private AuthenticationMechanisms authMech;
    private SaslClient saslClient;
    private Subject subject = null;
    private boolean firstPass = true;
    private CallbackHandler credentialsCallbackHandler = cbs -> {
        for (Callback cb : cbs) {
            if (NameCallback.class.isAssignableFrom(cb.getClass())) {
                ((NameCallback)cb).setName(this.user);
                continue;
            }
            if (PasswordCallback.class.isAssignableFrom(cb.getClass())) {
                char[] passwordChars = this.password == null ? new char[0] : this.password.toCharArray();
                ((PasswordCallback)cb).setPassword(passwordChars);
                continue;
            }
            throw new UnsupportedCallbackException(cb, cb.getClass().getName());
        }
    };

    @Override
    public void init(Protocol<NativePacketPayload> prot) {
        this.protocol = prot;
        Security.addProvider(new ScramShaSaslProvider());
    }

    @Override
    public void reset() {
        if (this.saslClient != null) {
            try {
                this.saslClient.dispose();
            }
            catch (SaslException saslException) {
                // empty catch block
            }
        }
        this.user = null;
        this.password = null;
        this.authMech = null;
        this.saslClient = null;
        this.subject = null;
    }

    @Override
    public void destroy() {
        this.protocol = null;
        this.reset();
    }

    @Override
    public String getProtocolPluginName() {
        return PLUGIN_NAME;
    }

    @Override
    public boolean requiresConfidentiality() {
        return false;
    }

    @Override
    public boolean isReusable() {
        return false;
    }

    @Override
    public void setAuthenticationParameters(String user, String password) {
        this.user = user;
        this.password = password;
    }

    @Override
    public boolean nextAuthenticationStep(NativePacketPayload fromServer, List<NativePacketPayload> toServer) {
        toServer.clear();
        if (this.saslClient == null) {
            String authMechId = fromServer.readString(NativeConstants.StringSelfDataType.STRING_EOF, "ASCII");
            try {
                this.authMech = AuthenticationMechanisms.fromValue(authMechId);
            }
            catch (CJException e) {
                if (this.firstPass) {
                    this.firstPass = false;
                    toServer.add(new NativePacketPayload(new byte[0]));
                    return true;
                }
                throw e;
            }
            this.firstPass = false;
            try {
                switch (this.authMech) {
                    case GSSAPI: {
                        String krb5Kdc;
                        String ldapServerHostname = this.protocol.getPropertySet().getStringProperty(PropertyKey.ldapServerHostname).getValue();
                        if (StringUtils.isNullOrEmpty(ldapServerHostname) && !StringUtils.isNullOrEmpty(krb5Kdc = System.getProperty("java.security.krb5.kdc"))) {
                            ldapServerHostname = krb5Kdc;
                            int dotIndex = krb5Kdc.indexOf(46);
                            if (dotIndex > 0) {
                                ldapServerHostname = krb5Kdc.substring(0, dotIndex).toLowerCase(Locale.ENGLISH);
                            }
                        }
                        if (StringUtils.isNullOrEmpty(ldapServerHostname)) {
                            throw ExceptionFactory.createException(Messages.getString("AuthenticationLdapSaslClientPlugin.MissingLdapServerHostname"));
                        }
                        String loginConfigFile = System.getProperty("java.security.auth.login.config");
                        Configuration loginConfig = null;
                        if (StringUtils.isNullOrEmpty(loginConfigFile)) {
                            final String localUser = this.user;
                            final boolean debug = Boolean.getBoolean("sun.security.jgss.debug");
                            loginConfig = new Configuration(){

                                @Override
                                public AppConfigurationEntry[] getAppConfigurationEntry(String name) {
                                    HashMap<String, String> options = new HashMap<String, String>();
                                    options.put("useTicketCache", "true");
                                    options.put("renewTGT", "false");
                                    options.put("principal", localUser);
                                    options.put("debug", Boolean.toString(debug));
                                    return new AppConfigurationEntry[]{new AppConfigurationEntry("com.sun.security.auth.module.Krb5LoginModule", AppConfigurationEntry.LoginModuleControlFlag.REQUIRED, options)};
                                }
                            };
                        }
                        LoginContext loginContext = new LoginContext(LOGIN_CONFIG_ENTRY, null, this.credentialsCallbackHandler, loginConfig);
                        loginContext.login();
                        this.subject = loginContext.getSubject();
                        try {
                            String localLdapServerHostname = ldapServerHostname;
                            this.saslClient = Subject.doAs(this.subject, () -> Sasl.createSaslClient(new String[]{this.authMech.getSaslServiceName()}, null, LDAP_SERVICE_NAME, localLdapServerHostname, null, null));
                            break;
                        }
                        catch (PrivilegedActionException e) {
                            throw (SaslException)e.getException();
                        }
                    }
                    case SCRAM_SHA_1: 
                    case SCRAM_SHA_256: {
                        this.saslClient = Sasl.createSaslClient(new String[]{this.authMech.getSaslServiceName()}, null, null, null, null, this.credentialsCallbackHandler);
                    }
                }
            }
            catch (LoginException | SaslException e) {
                throw ExceptionFactory.createException(Messages.getString("AuthenticationLdapSaslClientPlugin.FailCreateSaslClient", new Object[]{this.authMech.getMechName()}), e);
            }
            if (this.saslClient == null) {
                throw ExceptionFactory.createException(Messages.getString("AuthenticationLdapSaslClientPlugin.FailCreateSaslClient", new Object[]{this.authMech.getMechName()}));
            }
        }
        if (!this.saslClient.isComplete()) {
            try {
                Subject.doAs(this.subject, () -> {
                    byte[] response = this.saslClient.evaluateChallenge(fromServer.readBytes(NativeConstants.StringSelfDataType.STRING_EOF));
                    if (response != null) {
                        NativePacketPayload bresp = new NativePacketPayload(response);
                        bresp.setPosition(0);
                        toServer.add(bresp);
                    }
                    return null;
                });
            }
            catch (PrivilegedActionException e) {
                throw ExceptionFactory.createException(Messages.getString("AuthenticationLdapSaslClientPlugin.ErrProcessingAuthIter", new Object[]{this.authMech.getMechName()}), e.getException());
            }
        }
        return true;
    }

    private static enum AuthenticationMechanisms {
        SCRAM_SHA_1("SCRAM-SHA-1", "MYSQLCJ-SCRAM-SHA-1"),
        SCRAM_SHA_256("SCRAM-SHA-256", "MYSQLCJ-SCRAM-SHA-256"),
        GSSAPI("GSSAPI", "GSSAPI");

        private String mechName;
        private String saslServiceName;

        private AuthenticationMechanisms(String mechName, String serviceName) {
            this.mechName = mechName;
            this.saslServiceName = serviceName;
        }

        static AuthenticationMechanisms fromValue(String mechName) {
            for (AuthenticationMechanisms am : AuthenticationMechanisms.values()) {
                if (!am.mechName.equalsIgnoreCase(mechName)) continue;
                return am;
            }
            throw ExceptionFactory.createException(Messages.getString("AuthenticationLdapSaslClientPlugin.UnsupportedAuthMech", new String[]{mechName}));
        }

        String getMechName() {
            return this.mechName;
        }

        String getSaslServiceName() {
            return this.saslServiceName;
        }
    }
}

