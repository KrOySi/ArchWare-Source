/*
 * Decompiled with CFR 0.150.
 */
package com.mysql.cj.protocol;

import com.mysql.cj.conf.PropertyKey;
import com.mysql.cj.conf.PropertySet;
import com.mysql.cj.protocol.StandardSocketFactory;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.Socket;

public class SocksProxySocketFactory
extends StandardSocketFactory {
    @Override
    protected Socket createSocket(PropertySet props) {
        String socksProxyHost = props.getStringProperty(PropertyKey.socksProxyHost).getValue();
        int socksProxyPort = props.getIntegerProperty(PropertyKey.socksProxyPort).getValue();
        return new Socket(new Proxy(Proxy.Type.SOCKS, new InetSocketAddress(socksProxyHost, socksProxyPort)));
    }
}

