/*
 * Decompiled with CFR 0.150.
 */
package com.mysql.cj.conf.url;

import com.mysql.cj.Messages;
import com.mysql.cj.conf.BooleanPropertyDefinition;
import com.mysql.cj.conf.ConnectionUrl;
import com.mysql.cj.conf.ConnectionUrlParser;
import com.mysql.cj.conf.HostInfo;
import com.mysql.cj.conf.HostsListView;
import com.mysql.cj.conf.PropertyKey;
import com.mysql.cj.exceptions.ExceptionFactory;
import com.mysql.cj.exceptions.InvalidConnectionAttributeException;
import com.mysql.cj.util.StringUtils;
import java.util.List;
import java.util.Map;
import java.util.Properties;

public class XDevApiDnsSrvConnectionUrl
extends ConnectionUrl {
    private static final String DEFAULT_HOST = "";
    private static final int DEFAULT_PORT = -1;

    public XDevApiDnsSrvConnectionUrl(ConnectionUrlParser connStrParser, Properties info) {
        super(connStrParser, info);
        this.type = ConnectionUrl.Type.XDEVAPI_DNS_SRV_SESSION;
        HostInfo srvHost = super.getMainHost();
        Map<String, String> hostProps = srvHost.getHostProperties();
        if (DEFAULT_HOST.equals(srvHost.getHost())) {
            throw ExceptionFactory.createException(InvalidConnectionAttributeException.class, Messages.getString("ConnectionString.18"));
        }
        if (this.hosts.size() != 1) {
            throw ExceptionFactory.createException(InvalidConnectionAttributeException.class, Messages.getString("ConnectionString.19"));
        }
        if (srvHost.getPort() != -1) {
            throw ExceptionFactory.createException(InvalidConnectionAttributeException.class, Messages.getString("ConnectionString.22"));
        }
        if (hostProps.containsKey(PropertyKey.xdevapiDnsSrv.getKeyName()) && !BooleanPropertyDefinition.booleanFrom(PropertyKey.xdevapiDnsSrv.getKeyName(), hostProps.get(PropertyKey.xdevapiDnsSrv.getKeyName()), null).booleanValue()) {
            throw ExceptionFactory.createException(InvalidConnectionAttributeException.class, Messages.getString("ConnectionString.23", new Object[]{PropertyKey.xdevapiDnsSrv.getKeyName()}));
        }
    }

    @Override
    protected void preprocessPerTypeHostProperties(Map<String, String> hostProps) {
        if (hostProps.containsKey(PropertyKey.ADDRESS.getKeyName())) {
            String address = hostProps.get(PropertyKey.ADDRESS.getKeyName());
            ConnectionUrlParser.Pair<String, Integer> hostPortPair = ConnectionUrlParser.parseHostPortPair(address);
            String host = StringUtils.safeTrim((String)hostPortPair.left);
            Integer port = (Integer)hostPortPair.right;
            if (!StringUtils.isNullOrEmpty(host) && !hostProps.containsKey(PropertyKey.HOST.getKeyName())) {
                hostProps.put(PropertyKey.HOST.getKeyName(), host);
            }
            if (port != -1 && !hostProps.containsKey(PropertyKey.PORT.getKeyName())) {
                hostProps.put(PropertyKey.PORT.getKeyName(), port.toString());
            }
        }
    }

    @Override
    public String getDefaultHost() {
        return DEFAULT_HOST;
    }

    @Override
    public int getDefaultPort() {
        return -1;
    }

    @Override
    protected void fixProtocolDependencies(Map<String, String> hostProps) {
    }

    @Override
    public List<HostInfo> getHostsList(HostsListView view) {
        return this.getHostsListFromDnsSrv(this.getMainHost());
    }
}

