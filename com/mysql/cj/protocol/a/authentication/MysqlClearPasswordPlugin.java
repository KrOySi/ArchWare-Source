/*
 * Decompiled with CFR 0.150.
 */
package com.mysql.cj.protocol.a.authentication;

import com.mysql.cj.callback.MysqlCallbackHandler;
import com.mysql.cj.callback.UsernameCallback;
import com.mysql.cj.protocol.AuthenticationPlugin;
import com.mysql.cj.protocol.Protocol;
import com.mysql.cj.protocol.a.NativeConstants;
import com.mysql.cj.protocol.a.NativePacketPayload;
import com.mysql.cj.util.StringUtils;
import java.util.List;

public class MysqlClearPasswordPlugin
implements AuthenticationPlugin<NativePacketPayload> {
    public static String PLUGIN_NAME = "mysql_clear_password";
    private Protocol<NativePacketPayload> protocol;
    private MysqlCallbackHandler usernameCallbackHandler;
    private String password = null;

    @Override
    public void init(Protocol<NativePacketPayload> prot, MysqlCallbackHandler cbh) {
        this.protocol = prot;
        this.usernameCallbackHandler = cbh;
    }

    @Override
    public void destroy() {
        this.password = null;
    }

    @Override
    public String getProtocolPluginName() {
        return PLUGIN_NAME;
    }

    @Override
    public boolean requiresConfidentiality() {
        return true;
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
        String encoding = this.protocol.versionMeetsMinimum(5, 7, 6) ? this.protocol.getPasswordCharacterEncoding() : "UTF-8";
        NativePacketPayload bresp = new NativePacketPayload(StringUtils.getBytes(this.password != null ? this.password : "", encoding));
        bresp.setPosition(bresp.getPayloadLength());
        bresp.writeInteger(NativeConstants.IntegerDataType.INT1, 0L);
        bresp.setPosition(0);
        toServer.add(bresp);
        return true;
    }
}

