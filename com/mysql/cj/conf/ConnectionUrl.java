/*
 * Decompiled with CFR 0.150.
 */
package com.mysql.cj.conf;

import com.mysql.cj.Messages;
import com.mysql.cj.conf.ConnectionPropertiesTransform;
import com.mysql.cj.conf.ConnectionUrlParser;
import com.mysql.cj.conf.DatabaseUrlContainer;
import com.mysql.cj.conf.HostInfo;
import com.mysql.cj.conf.HostsListView;
import com.mysql.cj.conf.PropertyDefinitions;
import com.mysql.cj.conf.PropertyKey;
import com.mysql.cj.exceptions.CJException;
import com.mysql.cj.exceptions.ExceptionFactory;
import com.mysql.cj.exceptions.InvalidConnectionAttributeException;
import com.mysql.cj.exceptions.UnsupportedConnectionStringException;
import com.mysql.cj.exceptions.WrongArgumentException;
import com.mysql.cj.util.DnsSrv;
import com.mysql.cj.util.LRUCache;
import com.mysql.cj.util.StringUtils;
import com.mysql.cj.util.Util;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.TreeMap;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.stream.Collectors;
import javax.naming.NamingException;

public abstract class ConnectionUrl
implements DatabaseUrlContainer {
    public static final String DEFAULT_HOST = "localhost";
    public static final int DEFAULT_PORT = 3306;
    private static final LRUCache<String, ConnectionUrl> connectionUrlCache = new LRUCache(100);
    private static final ReadWriteLock rwLock = new ReentrantReadWriteLock();
    protected Type type;
    protected String originalConnStr;
    protected String originalDatabase;
    protected List<HostInfo> hosts = new ArrayList<HostInfo>();
    protected Map<String, String> properties = new HashMap<String, String>();
    ConnectionPropertiesTransform propertiesTransformer;

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static ConnectionUrl getConnectionUrlInstance(String connString, Properties info) {
        if (connString == null) {
            throw ExceptionFactory.createException(WrongArgumentException.class, Messages.getString("ConnectionString.0"));
        }
        String connStringCacheKey = ConnectionUrl.buildConnectionStringCacheKey(connString, info);
        rwLock.readLock().lock();
        ConnectionUrl connectionUrl = (ConnectionUrl)connectionUrlCache.get(connStringCacheKey);
        if (connectionUrl == null) {
            rwLock.readLock().unlock();
            rwLock.writeLock().lock();
            try {
                connectionUrl = (ConnectionUrl)connectionUrlCache.get(connStringCacheKey);
                if (connectionUrl == null) {
                    ConnectionUrlParser connStrParser = ConnectionUrlParser.parseConnectionString(connString);
                    connectionUrl = Type.getConnectionUrlInstance(connStrParser, info);
                    connectionUrlCache.put(connStringCacheKey, connectionUrl);
                }
                rwLock.readLock().lock();
            }
            finally {
                rwLock.writeLock().unlock();
            }
        }
        rwLock.readLock().unlock();
        return connectionUrl;
    }

    private static String buildConnectionStringCacheKey(String connString, Properties info) {
        StringBuilder sbKey = new StringBuilder(connString);
        sbKey.append("\u00a7");
        sbKey.append(info == null ? null : info.stringPropertyNames().stream().map(k -> k + "=" + info.getProperty((String)k)).collect(Collectors.joining(", ", "{", "}")));
        return sbKey.toString();
    }

    public static boolean acceptsUrl(String connString) {
        return ConnectionUrlParser.isConnectionStringSupported(connString);
    }

    protected ConnectionUrl() {
    }

    public ConnectionUrl(String origUrl) {
        this.originalConnStr = origUrl;
    }

    protected ConnectionUrl(ConnectionUrlParser connStrParser, Properties info) {
        this.originalConnStr = connStrParser.getDatabaseUrl();
        this.originalDatabase = connStrParser.getPath() == null ? "" : connStrParser.getPath();
        this.collectProperties(connStrParser, info);
        this.collectHostsInfo(connStrParser);
    }

    protected void collectProperties(ConnectionUrlParser connStrParser, Properties info) {
        connStrParser.getProperties().entrySet().stream().forEach(e -> this.properties.put(PropertyKey.normalizeCase((String)e.getKey()), (String)e.getValue()));
        if (info != null) {
            info.stringPropertyNames().stream().forEach(k -> this.properties.put(PropertyKey.normalizeCase(k), info.getProperty((String)k)));
        }
        this.setupPropertiesTransformer();
        this.expandPropertiesFromConfigFiles(this.properties);
        this.injectPerTypeProperties(this.properties);
    }

    protected void setupPropertiesTransformer() {
        String propertiesTransformClassName = this.properties.get(PropertyKey.propertiesTransform.getKeyName());
        if (!StringUtils.isNullOrEmpty(propertiesTransformClassName)) {
            try {
                this.propertiesTransformer = (ConnectionPropertiesTransform)Class.forName(propertiesTransformClassName).newInstance();
            }
            catch (CJException | ClassNotFoundException | IllegalAccessException | InstantiationException e) {
                throw ExceptionFactory.createException(InvalidConnectionAttributeException.class, Messages.getString("ConnectionString.9", new Object[]{propertiesTransformClassName, e.toString()}), e);
            }
        }
    }

    protected void expandPropertiesFromConfigFiles(Map<String, String> props) {
        String configFiles = props.get(PropertyKey.useConfigs.getKeyName());
        if (!StringUtils.isNullOrEmpty(configFiles)) {
            Properties configProps = ConnectionUrl.getPropertiesFromConfigFiles(configFiles);
            configProps.stringPropertyNames().stream().map(PropertyKey::normalizeCase).filter(k -> !props.containsKey(k)).forEach(k -> props.put((String)k, configProps.getProperty((String)k)));
        }
    }

    public static Properties getPropertiesFromConfigFiles(String configFiles) {
        Properties configProps = new Properties();
        for (String configFile : configFiles.split(",")) {
            try (InputStream configAsStream = ConnectionUrl.class.getResourceAsStream("/com/mysql/cj/configurations/" + configFile + ".properties");){
                if (configAsStream == null) {
                    throw ExceptionFactory.createException(InvalidConnectionAttributeException.class, Messages.getString("ConnectionString.10", new Object[]{configFile}));
                }
                configProps.load(configAsStream);
            }
            catch (IOException e) {
                throw ExceptionFactory.createException(InvalidConnectionAttributeException.class, Messages.getString("ConnectionString.11", new Object[]{configFile}), e);
            }
        }
        return configProps;
    }

    protected void injectPerTypeProperties(Map<String, String> props) {
    }

    protected void replaceLegacyPropertyValues(Map<String, String> props) {
        String zeroDateTimeBehavior = props.get(PropertyKey.zeroDateTimeBehavior.getKeyName());
        if (zeroDateTimeBehavior != null && zeroDateTimeBehavior.equalsIgnoreCase("convertToNull")) {
            props.put(PropertyKey.zeroDateTimeBehavior.getKeyName(), "CONVERT_TO_NULL");
        }
    }

    protected void collectHostsInfo(ConnectionUrlParser connStrParser) {
        connStrParser.getHosts().stream().map(this::fixHostInfo).forEach(this.hosts::add);
    }

    protected HostInfo fixHostInfo(HostInfo hi) {
        HashMap<String, String> hostProps = new HashMap<String, String>();
        hostProps.putAll(this.properties);
        hi.getHostProperties().entrySet().stream().forEach(e -> {
            String cfr_ignored_0 = (String)hostProps.put(PropertyKey.normalizeCase((String)e.getKey()), (String)e.getValue());
        });
        if (!hostProps.containsKey(PropertyKey.DBNAME.getKeyName())) {
            hostProps.put(PropertyKey.DBNAME.getKeyName(), this.getDatabase());
        }
        this.preprocessPerTypeHostProperties(hostProps);
        String host = (String)hostProps.remove(PropertyKey.HOST.getKeyName());
        if (!StringUtils.isNullOrEmpty(hi.getHost())) {
            host = hi.getHost();
        } else if (StringUtils.isNullOrEmpty(host)) {
            host = this.getDefaultHost();
        }
        String portAsString = (String)hostProps.remove(PropertyKey.PORT.getKeyName());
        int port = hi.getPort();
        if (port == -1 && !StringUtils.isNullOrEmpty(portAsString)) {
            try {
                port = Integer.valueOf(portAsString);
            }
            catch (NumberFormatException e2) {
                throw ExceptionFactory.createException(WrongArgumentException.class, Messages.getString("ConnectionString.7", new Object[]{hostProps.get(PropertyKey.PORT.getKeyName())}), e2);
            }
        }
        if (port == -1) {
            port = this.getDefaultPort();
        }
        String user = (String)hostProps.remove(PropertyKey.USER.getKeyName());
        if (!StringUtils.isNullOrEmpty(hi.getUser())) {
            user = hi.getUser();
        } else if (StringUtils.isNullOrEmpty(user)) {
            user = this.getDefaultUser();
        }
        String password = (String)hostProps.remove(PropertyKey.PASSWORD.getKeyName());
        if (!StringUtils.isNullOrEmpty(hi.getPassword())) {
            password = hi.getPassword();
        } else if (StringUtils.isNullOrEmpty(password)) {
            password = this.getDefaultPassword();
        }
        this.expandPropertiesFromConfigFiles(hostProps);
        this.fixProtocolDependencies(hostProps);
        this.replaceLegacyPropertyValues(hostProps);
        return this.buildHostInfo(host, port, user, password, hostProps);
    }

    protected void preprocessPerTypeHostProperties(Map<String, String> hostProps) {
    }

    public String getDefaultHost() {
        return DEFAULT_HOST;
    }

    public int getDefaultPort() {
        return 3306;
    }

    public String getDefaultUser() {
        return this.properties.get(PropertyKey.USER.getKeyName());
    }

    public String getDefaultPassword() {
        return this.properties.get(PropertyKey.PASSWORD.getKeyName());
    }

    protected void fixProtocolDependencies(Map<String, String> hostProps) {
        String protocol = hostProps.get(PropertyKey.PROTOCOL.getKeyName());
        if (!StringUtils.isNullOrEmpty(protocol) && protocol.equalsIgnoreCase("PIPE") && !hostProps.containsKey(PropertyKey.socketFactory.getKeyName())) {
            hostProps.put(PropertyKey.socketFactory.getKeyName(), "com.mysql.cj.protocol.NamedPipeSocketFactory");
        }
    }

    public Type getType() {
        return this.type;
    }

    @Override
    public String getDatabaseUrl() {
        return this.originalConnStr;
    }

    public String getDatabase() {
        return this.properties.containsKey(PropertyKey.DBNAME.getKeyName()) ? this.properties.get(PropertyKey.DBNAME.getKeyName()) : this.originalDatabase;
    }

    public int hostsCount() {
        return this.hosts.size();
    }

    public HostInfo getMainHost() {
        return this.hosts.isEmpty() ? null : this.hosts.get(0);
    }

    public List<HostInfo> getHostsList() {
        return this.getHostsList(HostsListView.ALL);
    }

    public List<HostInfo> getHostsList(HostsListView view) {
        return Collections.unmodifiableList(this.hosts);
    }

    public HostInfo getHostOrSpawnIsolated(String hostPortPair) {
        return this.getHostOrSpawnIsolated(hostPortPair, this.hosts);
    }

    public HostInfo getHostOrSpawnIsolated(String hostPortPair, List<HostInfo> hostsList) {
        for (HostInfo hi : hostsList) {
            if (!hostPortPair.equals(hi.getHostPortPair())) continue;
            return hi;
        }
        ConnectionUrlParser.Pair<String, Integer> hostAndPort = ConnectionUrlParser.parseHostPortPair(hostPortPair);
        String host = (String)hostAndPort.left;
        Integer port = (Integer)hostAndPort.right;
        String user = this.getDefaultUser();
        String password = this.getDefaultPassword();
        return this.buildHostInfo(host, port, user, password, this.properties);
    }

    protected HostInfo buildHostInfo(String host, int port, String user, String password, Map<String, String> hostProps) {
        if (this.propertiesTransformer != null) {
            Properties props = new Properties();
            props.putAll(hostProps);
            props.setProperty(PropertyKey.HOST.getKeyName(), host);
            props.setProperty(PropertyKey.PORT.getKeyName(), String.valueOf(port));
            if (user != null) {
                props.setProperty(PropertyKey.USER.getKeyName(), user);
            }
            if (password != null) {
                props.setProperty(PropertyKey.PASSWORD.getKeyName(), password);
            }
            Properties transformedProps = this.propertiesTransformer.transformProperties(props);
            host = transformedProps.getProperty(PropertyKey.HOST.getKeyName());
            try {
                port = Integer.parseInt(transformedProps.getProperty(PropertyKey.PORT.getKeyName()));
            }
            catch (NumberFormatException e) {
                throw ExceptionFactory.createException(WrongArgumentException.class, Messages.getString("ConnectionString.8", new Object[]{PropertyKey.PORT.getKeyName(), transformedProps.getProperty(PropertyKey.PORT.getKeyName())}), e);
            }
            user = transformedProps.getProperty(PropertyKey.USER.getKeyName());
            password = transformedProps.getProperty(PropertyKey.PASSWORD.getKeyName());
            TreeMap<String, String> transformedHostProps = new TreeMap<String, String>(String.CASE_INSENSITIVE_ORDER);
            transformedProps.stringPropertyNames().stream().forEach(k -> transformedHostProps.put((String)k, transformedProps.getProperty((String)k)));
            transformedHostProps.remove(PropertyKey.HOST.getKeyName());
            transformedHostProps.remove(PropertyKey.PORT.getKeyName());
            transformedHostProps.remove(PropertyKey.USER.getKeyName());
            transformedHostProps.remove(PropertyKey.PASSWORD.getKeyName());
            hostProps = transformedHostProps;
        }
        return new HostInfo(this, host, port, user, password, hostProps);
    }

    public Map<String, String> getOriginalProperties() {
        return Collections.unmodifiableMap(this.properties);
    }

    public Properties getConnectionArgumentsAsProperties() {
        Properties props = new Properties();
        if (this.properties != null) {
            props.putAll(this.properties);
        }
        return this.propertiesTransformer != null ? this.propertiesTransformer.transformProperties(props) : props;
    }

    public List<HostInfo> getHostsListFromDnsSrv(HostInfo srvHost) {
        String srvServiceName = srvHost.getHost();
        List<DnsSrv.SrvRecord> srvRecords = null;
        try {
            srvRecords = DnsSrv.lookupSrvRecords(srvServiceName);
        }
        catch (NamingException e) {
            throw ExceptionFactory.createException(Messages.getString("ConnectionString.26", new Object[]{srvServiceName}), e);
        }
        if (srvRecords == null || srvRecords.size() == 0) {
            throw ExceptionFactory.createException(Messages.getString("ConnectionString.26", new Object[]{srvServiceName}));
        }
        return Collections.unmodifiableList(this.srvRecordsToHostsList(srvRecords, srvHost));
    }

    private List<HostInfo> srvRecordsToHostsList(List<DnsSrv.SrvRecord> srvRecords, HostInfo baseHostInfo) {
        return srvRecords.stream().map(s -> this.buildHostInfo(s.getTarget(), s.getPort(), baseHostInfo.getUser(), baseHostInfo.getPassword(), baseHostInfo.getHostProperties())).collect(Collectors.toList());
    }

    public String toString() {
        StringBuilder asStr = new StringBuilder(super.toString());
        asStr.append(String.format(" :: {type: \"%s\", hosts: %s, database: \"%s\", properties: %s, propertiesTransformer: %s}", new Object[]{this.type, this.hosts, this.originalDatabase, this.properties, this.propertiesTransformer}));
        return asStr.toString();
    }

    public static enum Type {
        FAILOVER_DNS_SRV_CONNECTION("jdbc:mysql+srv:", HostsCardinality.ONE_OR_MORE, "com.mysql.cj.conf.url.FailoverDnsSrvConnectionUrl"),
        LOADBALANCE_DNS_SRV_CONNECTION("jdbc:mysql+srv:loadbalance:", HostsCardinality.ONE_OR_MORE, "com.mysql.cj.conf.url.LoadBalanceDnsSrvConnectionUrl"),
        REPLICATION_DNS_SRV_CONNECTION("jdbc:mysql+srv:replication:", HostsCardinality.ONE_OR_MORE, "com.mysql.cj.conf.url.ReplicationDnsSrvConnectionUrl"),
        XDEVAPI_DNS_SRV_SESSION("mysqlx+srv:", HostsCardinality.ONE_OR_MORE, "com.mysql.cj.conf.url.XDevApiDnsSrvConnectionUrl"),
        SINGLE_CONNECTION("jdbc:mysql:", HostsCardinality.SINGLE, "com.mysql.cj.conf.url.SingleConnectionUrl", PropertyKey.dnsSrv, FAILOVER_DNS_SRV_CONNECTION),
        FAILOVER_CONNECTION("jdbc:mysql:", HostsCardinality.MULTIPLE, "com.mysql.cj.conf.url.FailoverConnectionUrl", PropertyKey.dnsSrv, FAILOVER_DNS_SRV_CONNECTION),
        LOADBALANCE_CONNECTION("jdbc:mysql:loadbalance:", HostsCardinality.ONE_OR_MORE, "com.mysql.cj.conf.url.LoadBalanceConnectionUrl", PropertyKey.dnsSrv, LOADBALANCE_DNS_SRV_CONNECTION),
        REPLICATION_CONNECTION("jdbc:mysql:replication:", HostsCardinality.ONE_OR_MORE, "com.mysql.cj.conf.url.ReplicationConnectionUrl", PropertyKey.dnsSrv, REPLICATION_DNS_SRV_CONNECTION),
        XDEVAPI_SESSION("mysqlx:", HostsCardinality.ONE_OR_MORE, "com.mysql.cj.conf.url.XDevApiConnectionUrl", PropertyKey.xdevapiDnsSrv, XDEVAPI_DNS_SRV_SESSION);

        private String scheme;
        private HostsCardinality cardinality;
        private String implementingClass;
        private PropertyKey dnsSrvPropertyKey;
        private Type alternateDnsSrvType;

        private Type(String scheme, HostsCardinality cardinality, String implementingClass) {
            this(scheme, cardinality, implementingClass, null, null);
        }

        private Type(String scheme, HostsCardinality cardinality, String implementingClass, PropertyKey dnsSrvPropertyKey, Type alternateDnsSrvType) {
            this.scheme = scheme;
            this.cardinality = cardinality;
            this.implementingClass = implementingClass;
            this.dnsSrvPropertyKey = dnsSrvPropertyKey;
            this.alternateDnsSrvType = alternateDnsSrvType;
        }

        public String getScheme() {
            return this.scheme;
        }

        public HostsCardinality getCardinality() {
            return this.cardinality;
        }

        public String getImplementingClass() {
            return this.implementingClass;
        }

        public PropertyKey getDnsSrvPropertyKey() {
            return this.dnsSrvPropertyKey;
        }

        public Type getAlternateDnsSrvType() {
            return this.alternateDnsSrvType;
        }

        public static Type fromValue(String scheme, int n) {
            for (Type t : Type.values()) {
                if (!t.getScheme().equalsIgnoreCase(scheme) || n >= 0 && !t.getCardinality().assertSize(n)) continue;
                return t;
            }
            if (n < 0) {
                throw ExceptionFactory.createException(UnsupportedConnectionStringException.class, Messages.getString("ConnectionString.5", new Object[]{scheme}));
            }
            throw ExceptionFactory.createException(UnsupportedConnectionStringException.class, Messages.getString("ConnectionString.6", new Object[]{scheme, n}));
        }

        public static ConnectionUrl getConnectionUrlInstance(ConnectionUrlParser parser, Properties info) {
            int hostsCount = parser.getHosts().size();
            Type type = Type.fromValue(parser.getScheme(), hostsCount);
            PropertyKey dnsSrvPropKey = type.getDnsSrvPropertyKey();
            if (dnsSrvPropKey != null && type.getAlternateDnsSrvType() != null) {
                if (info != null && info.containsKey(dnsSrvPropKey.getKeyName())) {
                    if (((Boolean)PropertyDefinitions.getPropertyDefinition(dnsSrvPropKey).parseObject(info.getProperty(dnsSrvPropKey.getKeyName()), null)).booleanValue()) {
                        type = Type.fromValue(type.getAlternateDnsSrvType().getScheme(), hostsCount);
                    }
                } else {
                    Map<String, String> parsedProperties = parser.getProperties();
                    if (parsedProperties.containsKey(dnsSrvPropKey.getKeyName()) && ((Boolean)PropertyDefinitions.getPropertyDefinition(dnsSrvPropKey).parseObject(parsedProperties.get(dnsSrvPropKey.getKeyName()), null)).booleanValue()) {
                        type = Type.fromValue(type.getAlternateDnsSrvType().getScheme(), hostsCount);
                    }
                }
            }
            return type.getImplementingInstance(parser, info);
        }

        public static boolean isSupported(String scheme) {
            for (Type t : Type.values()) {
                if (!t.getScheme().equalsIgnoreCase(scheme)) continue;
                return true;
            }
            return false;
        }

        private ConnectionUrl getImplementingInstance(ConnectionUrlParser parser, Properties info) {
            return (ConnectionUrl)Util.getInstance(this.getImplementingClass(), new Class[]{ConnectionUrlParser.class, Properties.class}, new Object[]{parser, info}, null);
        }
    }

    public static enum HostsCardinality {
        SINGLE{

            @Override
            public boolean assertSize(int n) {
                return n == 1;
            }
        }
        ,
        MULTIPLE{

            @Override
            public boolean assertSize(int n) {
                return n > 1;
            }
        }
        ,
        ONE_OR_MORE{

            @Override
            public boolean assertSize(int n) {
                return n >= 1;
            }
        };


        public abstract boolean assertSize(int var1);
    }
}

