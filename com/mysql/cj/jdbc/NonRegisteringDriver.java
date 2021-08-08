/*
 * Decompiled with CFR 0.150.
 */
package com.mysql.cj.jdbc;

import com.mysql.cj.Constants;
import com.mysql.cj.Messages;
import com.mysql.cj.conf.ConnectionUrl;
import com.mysql.cj.conf.HostInfo;
import com.mysql.cj.conf.PropertyKey;
import com.mysql.cj.exceptions.CJException;
import com.mysql.cj.exceptions.ExceptionFactory;
import com.mysql.cj.exceptions.UnableToConnectException;
import com.mysql.cj.exceptions.UnsupportedConnectionStringException;
import com.mysql.cj.jdbc.AbandonedConnectionCleanupThread;
import com.mysql.cj.jdbc.ConnectionImpl;
import com.mysql.cj.jdbc.JdbcPropertySetImpl;
import com.mysql.cj.jdbc.exceptions.SQLExceptionsMapping;
import com.mysql.cj.jdbc.ha.FailoverConnectionProxy;
import com.mysql.cj.jdbc.ha.LoadBalancedConnectionProxy;
import com.mysql.cj.jdbc.ha.ReplicationConnectionProxy;
import com.mysql.cj.util.StringUtils;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverPropertyInfo;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.List;
import java.util.Properties;
import java.util.logging.Logger;

public class NonRegisteringDriver
implements Driver {
    public static String getOSName() {
        return Constants.OS_NAME;
    }

    public static String getPlatform() {
        return Constants.OS_ARCH;
    }

    static int getMajorVersionInternal() {
        return StringUtils.safeIntParse("8");
    }

    static int getMinorVersionInternal() {
        return StringUtils.safeIntParse("0");
    }

    @Override
    public boolean acceptsURL(String url) throws SQLException {
        try {
            return ConnectionUrl.acceptsUrl(url);
        }
        catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException);
        }
    }

    @Override
    public Connection connect(String url, Properties info) throws SQLException {
        try {
            try {
                if (!ConnectionUrl.acceptsUrl(url)) {
                    return null;
                }
                ConnectionUrl conStr = ConnectionUrl.getConnectionUrlInstance(url, info);
                switch (conStr.getType()) {
                    case SINGLE_CONNECTION: {
                        return ConnectionImpl.getInstance(conStr.getMainHost());
                    }
                    case FAILOVER_CONNECTION: 
                    case FAILOVER_DNS_SRV_CONNECTION: {
                        return FailoverConnectionProxy.createProxyInstance(conStr);
                    }
                    case LOADBALANCE_CONNECTION: 
                    case LOADBALANCE_DNS_SRV_CONNECTION: {
                        return LoadBalancedConnectionProxy.createProxyInstance(conStr);
                    }
                    case REPLICATION_CONNECTION: 
                    case REPLICATION_DNS_SRV_CONNECTION: {
                        return ReplicationConnectionProxy.createProxyInstance(conStr);
                    }
                }
                return null;
            }
            catch (UnsupportedConnectionStringException e) {
                return null;
            }
            catch (CJException ex) {
                throw ExceptionFactory.createException(UnableToConnectException.class, Messages.getString("NonRegisteringDriver.17", new Object[]{ex.toString()}), ex);
            }
        }
        catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException);
        }
    }

    @Override
    public int getMajorVersion() {
        return NonRegisteringDriver.getMajorVersionInternal();
    }

    @Override
    public int getMinorVersion() {
        return NonRegisteringDriver.getMinorVersionInternal();
    }

    @Override
    public DriverPropertyInfo[] getPropertyInfo(String url, Properties info) throws SQLException {
        try {
            ConnectionUrl connStr;
            String host = "";
            String port = "";
            String database = "";
            String user = "";
            String password = "";
            if (!StringUtils.isNullOrEmpty(url) && (connStr = ConnectionUrl.getConnectionUrlInstance(url, info)).getType() == ConnectionUrl.Type.SINGLE_CONNECTION) {
                HostInfo hostInfo = connStr.getMainHost();
                info = hostInfo.exposeAsProperties();
            }
            if (info != null) {
                host = info.getProperty(PropertyKey.HOST.getKeyName());
                port = info.getProperty(PropertyKey.PORT.getKeyName());
                database = info.getProperty(PropertyKey.DBNAME.getKeyName());
                user = info.getProperty(PropertyKey.USER.getKeyName());
                password = info.getProperty(PropertyKey.PASSWORD.getKeyName());
            }
            DriverPropertyInfo hostProp = new DriverPropertyInfo(PropertyKey.HOST.getKeyName(), host);
            hostProp.required = true;
            hostProp.description = Messages.getString("NonRegisteringDriver.3");
            DriverPropertyInfo portProp = new DriverPropertyInfo(PropertyKey.PORT.getKeyName(), port);
            portProp.required = false;
            portProp.description = Messages.getString("NonRegisteringDriver.7");
            DriverPropertyInfo dbProp = new DriverPropertyInfo(PropertyKey.DBNAME.getKeyName(), database);
            dbProp.required = false;
            dbProp.description = Messages.getString("NonRegisteringDriver.10");
            DriverPropertyInfo userProp = new DriverPropertyInfo(PropertyKey.USER.getKeyName(), user);
            userProp.required = true;
            userProp.description = Messages.getString("NonRegisteringDriver.13");
            DriverPropertyInfo passwordProp = new DriverPropertyInfo(PropertyKey.PASSWORD.getKeyName(), password);
            passwordProp.required = true;
            passwordProp.description = Messages.getString("NonRegisteringDriver.16");
            JdbcPropertySetImpl propSet = new JdbcPropertySetImpl();
            propSet.initializeProperties(info);
            List<DriverPropertyInfo> driverPropInfo = propSet.exposeAsDriverPropertyInfo();
            DriverPropertyInfo[] dpi = new DriverPropertyInfo[5 + driverPropInfo.size()];
            dpi[0] = hostProp;
            dpi[1] = portProp;
            dpi[2] = dbProp;
            dpi[3] = userProp;
            dpi[4] = passwordProp;
            System.arraycopy(driverPropInfo.toArray(new DriverPropertyInfo[0]), 0, dpi, 5, driverPropInfo.size());
            return dpi;
        }
        catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException);
        }
    }

    @Override
    public boolean jdbcCompliant() {
        return false;
    }

    @Override
    public Logger getParentLogger() throws SQLFeatureNotSupportedException {
        throw new SQLFeatureNotSupportedException();
    }

    static {
        try {
            Class.forName(AbandonedConnectionCleanupThread.class.getName());
        }
        catch (ClassNotFoundException classNotFoundException) {
            // empty catch block
        }
    }
}

