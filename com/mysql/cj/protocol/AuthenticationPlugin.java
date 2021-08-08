/*
 * Decompiled with CFR 0.150.
 */
package com.mysql.cj.protocol;

import com.mysql.cj.callback.MysqlCallbackHandler;
import com.mysql.cj.protocol.Message;
import com.mysql.cj.protocol.Protocol;
import java.util.List;

public interface AuthenticationPlugin<M extends Message> {
    default public void init(Protocol<M> protocol) {
    }

    default public void init(Protocol<M> protocol, MysqlCallbackHandler callbackHandler) {
        this.init(protocol);
    }

    default public void reset() {
    }

    default public void destroy() {
    }

    public String getProtocolPluginName();

    public boolean requiresConfidentiality();

    public boolean isReusable();

    public void setAuthenticationParameters(String var1, String var2);

    default public void setSourceOfAuthData(String sourceOfAuthData) {
    }

    public boolean nextAuthenticationStep(M var1, List<M> var2);
}

