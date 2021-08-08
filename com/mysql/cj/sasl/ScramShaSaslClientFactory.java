/*
 * Decompiled with CFR 0.150.
 */
package com.mysql.cj.sasl;

import com.mysql.cj.sasl.ScramSha1SaslClient;
import com.mysql.cj.sasl.ScramSha256SaslClient;
import com.mysql.cj.util.StringUtils;
import java.io.IOException;
import java.util.Map;
import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.NameCallback;
import javax.security.auth.callback.PasswordCallback;
import javax.security.auth.callback.UnsupportedCallbackException;
import javax.security.sasl.SaslClient;
import javax.security.sasl.SaslClientFactory;
import javax.security.sasl.SaslException;

public class ScramShaSaslClientFactory
implements SaslClientFactory {
    private static final String[] SUPPORTED_MECHANISMS = new String[]{"MYSQLCJ-SCRAM-SHA-1", "MYSQLCJ-SCRAM-SHA-256"};

    @Override
    public SaslClient createSaslClient(String[] mechanisms, String authorizationId, String protocol, String serverName, Map<String, ?> props, CallbackHandler cbh) throws SaslException {
        for (String mech : mechanisms) {
            if (mech.equals("MYSQLCJ-SCRAM-SHA-1")) {
                return new ScramSha1SaslClient(authorizationId, this.getUsername(mech, authorizationId, cbh), this.getPassword(mech, cbh));
            }
            if (!mech.equals("MYSQLCJ-SCRAM-SHA-256")) continue;
            return new ScramSha256SaslClient(authorizationId, this.getUsername(mech, authorizationId, cbh), this.getPassword(mech, cbh));
        }
        return null;
    }

    @Override
    public String[] getMechanismNames(Map<String, ?> props) {
        return (String[])SUPPORTED_MECHANISMS.clone();
    }

    private String getUsername(String prefix, String authorizationId, CallbackHandler cbh) throws SaslException {
        if (cbh == null) {
            throw new SaslException("Callback handler required to get username.");
        }
        try {
            String prompt = prefix + " authentication id:";
            NameCallback ncb = StringUtils.isNullOrEmpty(authorizationId) ? new NameCallback(prompt) : new NameCallback(prompt, authorizationId);
            cbh.handle(new Callback[]{ncb});
            String userName = ncb.getName();
            return userName;
        }
        catch (IOException | UnsupportedCallbackException e) {
            throw new SaslException("Cannot get username", e);
        }
    }

    private String getPassword(String prefix, CallbackHandler cbh) throws SaslException {
        if (cbh == null) {
            throw new SaslException("Callback handler required to get password.");
        }
        try {
            String prompt = prefix + " password:";
            PasswordCallback pcb = new PasswordCallback(prompt, false);
            cbh.handle(new Callback[]{pcb});
            String password = new String(pcb.getPassword());
            pcb.clearPassword();
            return password;
        }
        catch (IOException | UnsupportedCallbackException e) {
            throw new SaslException("Cannot get password", e);
        }
    }
}

