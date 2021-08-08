/*
 * Decompiled with CFR 0.150.
 */
package com.mysql.cj.xdevapi;

import com.mysql.cj.Messages;
import com.mysql.cj.conf.ConnectionUrl;
import com.mysql.cj.conf.HostInfo;
import com.mysql.cj.conf.PropertyDefinitions;
import com.mysql.cj.conf.PropertyKey;
import com.mysql.cj.exceptions.CJCommunicationsException;
import com.mysql.cj.exceptions.ExceptionFactory;
import com.mysql.cj.exceptions.InvalidConnectionAttributeException;
import com.mysql.cj.xdevapi.Session;
import com.mysql.cj.xdevapi.SessionImpl;
import java.util.List;
import java.util.Properties;

public class SessionFactory {
    protected ConnectionUrl parseUrl(String url) {
        ConnectionUrl connUrl = ConnectionUrl.getConnectionUrlInstance(url, null);
        if (connUrl == null || connUrl.getType() != ConnectionUrl.Type.XDEVAPI_SESSION && connUrl.getType() != ConnectionUrl.Type.XDEVAPI_DNS_SRV_SESSION) {
            throw ExceptionFactory.createException(InvalidConnectionAttributeException.class, "Initialization via URL failed for \"" + url + "\"");
        }
        return connUrl;
    }

    protected Session getSession(ConnectionUrl connUrl) {
        CJCommunicationsException latestException = null;
        List<HostInfo> hostsList = connUrl.getHostsList();
        for (HostInfo hi : hostsList) {
            try {
                return new SessionImpl(hi);
            }
            catch (CJCommunicationsException e) {
                if (e.getCause() == null) {
                    throw e;
                }
                latestException = e;
            }
        }
        if (latestException != null) {
            throw ExceptionFactory.createException(CJCommunicationsException.class, Messages.getString("Session.Create.Failover.0"), latestException);
        }
        return null;
    }

    public Session getSession(String url) {
        return this.getSession(this.parseUrl(url));
    }

    public Session getSession(Properties properties) {
        if (properties.containsKey(PropertyKey.xdevapiDnsSrv.getKeyName()) && ((Boolean)PropertyDefinitions.getPropertyDefinition(PropertyKey.xdevapiDnsSrv).parseObject(properties.getProperty(PropertyKey.xdevapiDnsSrv.getKeyName()), null)).booleanValue()) {
            ConnectionUrl connUrl = ConnectionUrl.getConnectionUrlInstance(ConnectionUrl.Type.XDEVAPI_DNS_SRV_SESSION.getScheme(), properties);
            return this.getSession(connUrl);
        }
        ConnectionUrl connUrl = ConnectionUrl.getConnectionUrlInstance(ConnectionUrl.Type.XDEVAPI_SESSION.getScheme(), properties);
        return new SessionImpl(connUrl.getMainHost());
    }
}

