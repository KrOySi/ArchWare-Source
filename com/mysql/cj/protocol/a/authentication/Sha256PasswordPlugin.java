/*
 * Decompiled with CFR 0.150.
 */
package com.mysql.cj.protocol.a.authentication;

import com.mysql.cj.Messages;
import com.mysql.cj.callback.MysqlCallbackHandler;
import com.mysql.cj.callback.UsernameCallback;
import com.mysql.cj.conf.PropertyKey;
import com.mysql.cj.conf.PropertySet;
import com.mysql.cj.conf.RuntimeProperty;
import com.mysql.cj.exceptions.CJException;
import com.mysql.cj.exceptions.ExceptionFactory;
import com.mysql.cj.exceptions.ExceptionInterceptor;
import com.mysql.cj.exceptions.UnableToConnectException;
import com.mysql.cj.exceptions.WrongArgumentException;
import com.mysql.cj.protocol.AuthenticationPlugin;
import com.mysql.cj.protocol.ExportControlled;
import com.mysql.cj.protocol.Protocol;
import com.mysql.cj.protocol.Security;
import com.mysql.cj.protocol.a.NativeConstants;
import com.mysql.cj.protocol.a.NativePacketPayload;
import com.mysql.cj.util.StringUtils;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

public class Sha256PasswordPlugin
implements AuthenticationPlugin<NativePacketPayload> {
    public static String PLUGIN_NAME = "sha256_password";
    protected Protocol<NativePacketPayload> protocol;
    protected MysqlCallbackHandler usernameCallbackHandler;
    protected String password = null;
    protected String seed = null;
    protected boolean publicKeyRequested = false;
    protected String publicKeyString = null;
    protected RuntimeProperty<String> serverRSAPublicKeyFile = null;

    @Override
    public void init(Protocol<NativePacketPayload> prot, MysqlCallbackHandler mch) {
        this.protocol = prot;
        this.usernameCallbackHandler = mch;
        this.serverRSAPublicKeyFile = this.protocol.getPropertySet().getStringProperty(PropertyKey.serverRSAPublicKeyFile);
        String pkURL = this.serverRSAPublicKeyFile.getValue();
        if (pkURL != null) {
            this.publicKeyString = Sha256PasswordPlugin.readRSAKey(pkURL, this.protocol.getPropertySet(), this.protocol.getExceptionInterceptor());
        }
    }

    @Override
    public void destroy() {
        this.password = null;
        this.seed = null;
        this.publicKeyRequested = false;
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
        return true;
    }

    @Override
    public void setAuthenticationParameters(String user, String password) {
        this.password = password;
        if (user == null) {
            this.usernameCallbackHandler.handle(new UsernameCallback(System.getProperty("user.name")));
        }
    }

    @Override
    public boolean nextAuthenticationStep(NativePacketPayload fromServer, List<NativePacketPayload> toServer) {
        toServer.clear();
        if (this.password == null || this.password.length() == 0 || fromServer == null) {
            NativePacketPayload bresp = new NativePacketPayload(new byte[]{0});
            toServer.add(bresp);
        } else {
            try {
                if (this.protocol.getSocketConnection().isSSLEstablished()) {
                    NativePacketPayload bresp = new NativePacketPayload(StringUtils.getBytes(this.password, this.protocol.getPasswordCharacterEncoding()));
                    bresp.setPosition(bresp.getPayloadLength());
                    bresp.writeInteger(NativeConstants.IntegerDataType.INT1, 0L);
                    bresp.setPosition(0);
                    toServer.add(bresp);
                } else if (this.serverRSAPublicKeyFile.getValue() != null) {
                    this.seed = fromServer.readString(NativeConstants.StringSelfDataType.STRING_TERM, null);
                    NativePacketPayload bresp = new NativePacketPayload(this.encryptPassword());
                    toServer.add(bresp);
                } else {
                    if (!this.protocol.getPropertySet().getBooleanProperty(PropertyKey.allowPublicKeyRetrieval).getValue().booleanValue()) {
                        throw ExceptionFactory.createException(UnableToConnectException.class, Messages.getString("Sha256PasswordPlugin.2"), this.protocol.getExceptionInterceptor());
                    }
                    if (this.publicKeyRequested && fromServer.getPayloadLength() > 20) {
                        this.publicKeyString = fromServer.readString(NativeConstants.StringSelfDataType.STRING_TERM, null);
                        NativePacketPayload bresp = new NativePacketPayload(this.encryptPassword());
                        toServer.add(bresp);
                        this.publicKeyRequested = false;
                    } else {
                        this.seed = fromServer.readString(NativeConstants.StringSelfDataType.STRING_TERM, null);
                        NativePacketPayload bresp = new NativePacketPayload(new byte[]{1});
                        toServer.add(bresp);
                        this.publicKeyRequested = true;
                    }
                }
            }
            catch (CJException e) {
                throw ExceptionFactory.createException(e.getMessage(), e, this.protocol.getExceptionInterceptor());
            }
        }
        return true;
    }

    protected byte[] encryptPassword() {
        return this.encryptPassword("RSA/ECB/OAEPWithSHA-1AndMGF1Padding");
    }

    protected byte[] encryptPassword(String transformation) {
        byte[] arrby;
        byte[] input = null;
        if (this.password != null) {
            arrby = StringUtils.getBytesNullTerminated(this.password, this.protocol.getPasswordCharacterEncoding());
        } else {
            byte[] arrby2 = new byte[1];
            arrby = arrby2;
            arrby2[0] = 0;
        }
        input = arrby;
        byte[] mysqlScrambleBuff = new byte[input.length];
        Security.xorString(input, mysqlScrambleBuff, this.seed.getBytes(), input.length);
        return ExportControlled.encryptWithRSAPublicKey(mysqlScrambleBuff, ExportControlled.decodeRSAPublicKey(this.publicKeyString), transformation);
    }

    protected static String readRSAKey(String pkPath, PropertySet propertySet, ExceptionInterceptor exceptionInterceptor) {
        String res = null;
        byte[] fileBuf = new byte[2048];
        BufferedInputStream fileIn = null;
        try {
            File f = new File(pkPath);
            String canonicalPath = f.getCanonicalPath();
            fileIn = new BufferedInputStream(new FileInputStream(canonicalPath));
            int bytesRead = 0;
            StringBuilder sb = new StringBuilder();
            while ((bytesRead = fileIn.read(fileBuf)) != -1) {
                sb.append(StringUtils.toAsciiString(fileBuf, 0, bytesRead));
            }
            res = sb.toString();
        }
        catch (IOException ioEx) {
            Object[] arrobject;
            if (propertySet.getBooleanProperty(PropertyKey.paranoid).getValue().booleanValue()) {
                Object[] arrobject2 = new Object[1];
                arrobject = arrobject2;
                arrobject2[0] = "";
            } else {
                Object[] arrobject3 = new Object[1];
                arrobject = arrobject3;
                arrobject3[0] = "'" + pkPath + "'";
            }
            throw ExceptionFactory.createException(WrongArgumentException.class, Messages.getString("Sha256PasswordPlugin.0", arrobject), exceptionInterceptor);
        }
        finally {
            if (fileIn != null) {
                try {
                    fileIn.close();
                }
                catch (IOException e) {
                    throw ExceptionFactory.createException(Messages.getString("Sha256PasswordPlugin.1"), e, exceptionInterceptor);
                }
            }
        }
        return res;
    }
}

