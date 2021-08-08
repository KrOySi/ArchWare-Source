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

public class LoadBalanceDnsSrvConnectionUrl
extends ConnectionUrl {
    private static final String DEFAULT_HOST = "";
    private static final int DEFAULT_PORT = -1;

    public LoadBalanceDnsSrvConnectionUrl(ConnectionUrlParser connStrParser, Properties info) {
        super(connStrParser, info);
        this.type = ConnectionUrl.Type.LOADBALANCE_DNS_SRV_CONNECTION;
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
        if (hostProps.containsKey(PropertyKey.dnsSrv.getKeyName()) && !BooleanPropertyDefinition.booleanFrom(PropertyKey.dnsSrv.getKeyName(), hostProps.get(PropertyKey.dnsSrv.getKeyName()), null).booleanValue()) {
            throw ExceptionFactory.createException(InvalidConnectionAttributeException.class, Messages.getString("ConnectionString.23", new Object[]{PropertyKey.dnsSrv.getKeyName()}));
        }
        if (hostProps.containsKey(PropertyKey.PROTOCOL.getKeyName()) && hostProps.get(PropertyKey.PROTOCOL.getKeyName()).equalsIgnoreCase("PIPE")) {
            throw ExceptionFactory.createException(InvalidConnectionAttributeException.class, Messages.getString("ConnectionString.24"));
        }
        if (hostProps.containsKey(PropertyKey.loadBalanceConnectionGroup.getKeyName())) {
            throw ExceptionFactory.createException(InvalidConnectionAttributeException.class, Messages.getString("ConnectionString.25", new Object[]{PropertyKey.loadBalanceConnectionGroup.getKeyName()}));
        }
    }

    @Override
    protected void injectPerTypeProperties(Map<String, String> props) {
        if (props.containsKey(PropertyKey.loadBalanceAutoCommitStatementThreshold.getKeyName())) {
            try {
                int autoCommitSwapThreshold = Integer.parseInt(props.get(PropertyKey.loadBalanceAutoCommitStatementThreshold.getKeyName()));
                if (autoCommitSwapThreshold > 0) {
                    String queryInterceptors = props.get(PropertyKey.queryInterceptors.getKeyName());
                    String lbi = "com.mysql.cj.jdbc.ha.LoadBalancedAutoCommitInterceptor";
                    if (StringUtils.isNullOrEmpty(queryInterceptors)) {
                        props.put(PropertyKey.queryInterceptors.getKeyName(), lbi);
                    } else {
                        props.put(PropertyKey.queryInterceptors.getKeyName(), queryInterceptors + "," + lbi);
                    }
                }
            }
            catch (Throwable throwable) {
                // empty catch block
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
    public List<HostInfo> getHostsList(HostsListView view) {
        return this.getHostsListFromDnsSrv(this.getMainHost());
    }
}

