/*
 * Decompiled with CFR 0.150.
 */
package com.mysql.cj;

import com.mysql.cj.CacheAdapter;
import com.mysql.cj.CacheAdapterFactory;
import com.mysql.cj.CharsetMapping;
import com.mysql.cj.CoreSession;
import com.mysql.cj.Messages;
import com.mysql.cj.Query;
import com.mysql.cj.Session;
import com.mysql.cj.TransactionEventHandler;
import com.mysql.cj.conf.HostInfo;
import com.mysql.cj.conf.PropertyKey;
import com.mysql.cj.conf.PropertySet;
import com.mysql.cj.conf.RuntimeProperty;
import com.mysql.cj.exceptions.CJCommunicationsException;
import com.mysql.cj.exceptions.CJException;
import com.mysql.cj.exceptions.ConnectionIsClosedException;
import com.mysql.cj.exceptions.ExceptionFactory;
import com.mysql.cj.exceptions.ExceptionInterceptor;
import com.mysql.cj.exceptions.ExceptionInterceptorChain;
import com.mysql.cj.exceptions.OperationCancelledException;
import com.mysql.cj.exceptions.WrongArgumentException;
import com.mysql.cj.interceptors.QueryInterceptor;
import com.mysql.cj.log.Log;
import com.mysql.cj.protocol.ColumnDefinition;
import com.mysql.cj.protocol.NetworkResources;
import com.mysql.cj.protocol.ProtocolEntityFactory;
import com.mysql.cj.protocol.Resultset;
import com.mysql.cj.protocol.SocketFactory;
import com.mysql.cj.protocol.a.NativeMessageBuilder;
import com.mysql.cj.protocol.a.NativePacketPayload;
import com.mysql.cj.protocol.a.NativeProtocol;
import com.mysql.cj.protocol.a.NativeServerSession;
import com.mysql.cj.protocol.a.NativeSocketConnection;
import com.mysql.cj.protocol.a.ResultsetFactory;
import com.mysql.cj.result.Field;
import com.mysql.cj.result.IntegerValueFactory;
import com.mysql.cj.result.LongValueFactory;
import com.mysql.cj.result.Row;
import com.mysql.cj.result.StringValueFactory;
import com.mysql.cj.util.StringUtils;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.lang.ref.WeakReference;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;
import java.nio.charset.UnsupportedCharsetException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;
import java.util.Timer;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.function.Supplier;

public class NativeSession
extends CoreSession
implements Serializable {
    private static final long serialVersionUID = 5323638898749073419L;
    private CacheAdapter<String, Map<String, String>> serverConfigCache;
    private static final Map<String, Map<Integer, String>> customIndexToCharsetMapByUrl = new HashMap<String, Map<Integer, String>>();
    private static final Map<String, Map<String, Integer>> customCharsetToMblenMapByUrl = new HashMap<String, Map<String, Integer>>();
    private boolean requiresEscapingEncoder;
    private long lastQueryFinishedTime = 0L;
    private boolean needsPing = false;
    private NativeMessageBuilder commandBuilder = new NativeMessageBuilder();
    private boolean isClosed = true;
    private Throwable forceClosedReason;
    private CopyOnWriteArrayList<WeakReference<Session.SessionEventListener>> listeners = new CopyOnWriteArrayList();
    private transient Timer cancelTimer;
    private static final String SERVER_VERSION_STRING_VAR_NAME = "server_version_string";

    public NativeSession(HostInfo hostInfo, PropertySet propSet) {
        super(hostInfo, propSet);
    }

    public void connect(HostInfo hi, String user, String password, String database, int loginTimeout, TransactionEventHandler transactionManager) throws IOException {
        this.hostInfo = hi;
        this.setSessionMaxRows(-1);
        NativeSocketConnection socketConnection = new NativeSocketConnection();
        socketConnection.connect(this.hostInfo.getHost(), this.hostInfo.getPort(), this.propertySet, this.getExceptionInterceptor(), this.log, loginTimeout);
        if (this.protocol == null) {
            this.protocol = NativeProtocol.getInstance(this, socketConnection, this.propertySet, this.log, transactionManager);
        } else {
            this.protocol.init(this, socketConnection, this.propertySet, transactionManager);
        }
        this.protocol.connect(user, password, database);
        this.protocol.getServerSession().setErrorMessageEncoding(this.protocol.getAuthenticationProvider().getEncodingForHandshake());
        this.isClosed = false;
    }

    public NativeProtocol getProtocol() {
        return (NativeProtocol)this.protocol;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void quit() {
        if (this.protocol != null) {
            try {
                ((NativeProtocol)this.protocol).quit();
            }
            catch (Exception exception) {
                // empty catch block
            }
        }
        NativeSession nativeSession = this;
        synchronized (nativeSession) {
            if (this.cancelTimer != null) {
                this.cancelTimer.cancel();
                this.cancelTimer = null;
            }
        }
        this.isClosed = true;
        super.quit();
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void forceClose() {
        if (this.protocol != null) {
            try {
                this.protocol.getSocketConnection().forceClose();
                ((NativeProtocol)this.protocol).releaseResources();
            }
            catch (Throwable throwable) {
                // empty catch block
            }
        }
        NativeSession nativeSession = this;
        synchronized (nativeSession) {
            if (this.cancelTimer != null) {
                this.cancelTimer.cancel();
                this.cancelTimer = null;
            }
        }
        this.isClosed = true;
        super.forceClose();
    }

    public void enableMultiQueries() {
        this.sendCommand(this.commandBuilder.buildComSetOption(((NativeProtocol)this.protocol).getSharedSendPacket(), 0), false, 0);
        ((NativeServerSession)this.getServerSession()).preserveOldTransactionState();
    }

    public void disableMultiQueries() {
        this.sendCommand(this.commandBuilder.buildComSetOption(((NativeProtocol)this.protocol).getSharedSendPacket(), 1), false, 0);
        ((NativeServerSession)this.getServerSession()).preserveOldTransactionState();
    }

    @Override
    public boolean isSetNeededForAutoCommitMode(boolean autoCommitFlag) {
        return ((NativeServerSession)this.protocol.getServerSession()).isSetNeededForAutoCommitMode(autoCommitFlag, false);
    }

    public int getSessionMaxRows() {
        return this.sessionMaxRows;
    }

    public void setSessionMaxRows(int sessionMaxRows) {
        this.sessionMaxRows = sessionMaxRows;
    }

    public void setQueryInterceptors(List<QueryInterceptor> queryInterceptors) {
        ((NativeProtocol)this.protocol).setQueryInterceptors(queryInterceptors);
    }

    public boolean isServerLocal(Session sess) {
        SocketFactory factory = this.protocol.getSocketConnection().getSocketFactory();
        return factory.isLocallyConnected(sess);
    }

    public void shutdownServer() {
        if (this.versionMeetsMinimum(5, 7, 9)) {
            this.sendCommand(this.commandBuilder.buildComQuery(this.getSharedSendPacket(), "SHUTDOWN"), false, 0);
        } else {
            this.sendCommand(this.commandBuilder.buildComShutdown(this.getSharedSendPacket()), false, 0);
        }
    }

    public void setSocketTimeout(int milliseconds) {
        this.getPropertySet().getProperty(PropertyKey.socketTimeout).setValue(milliseconds);
        ((NativeProtocol)this.protocol).setSocketTimeout(milliseconds);
    }

    public int getSocketTimeout() {
        RuntimeProperty sto = this.getPropertySet().getProperty(PropertyKey.socketTimeout);
        return (Integer)sto.getValue();
    }

    public void checkForCharsetMismatch() {
        ((NativeProtocol)this.protocol).checkForCharsetMismatch();
    }

    public NativePacketPayload getSharedSendPacket() {
        return ((NativeProtocol)this.protocol).getSharedSendPacket();
    }

    public void dumpPacketRingBuffer() {
        ((NativeProtocol)this.protocol).dumpPacketRingBuffer();
    }

    public <T extends Resultset> T invokeQueryInterceptorsPre(Supplier<String> sql, Query interceptedQuery, boolean forceExecute) {
        return ((NativeProtocol)this.protocol).invokeQueryInterceptorsPre(sql, interceptedQuery, forceExecute);
    }

    public <T extends Resultset> T invokeQueryInterceptorsPost(Supplier<String> sql, Query interceptedQuery, T originalResultSet, boolean forceExecute) {
        return ((NativeProtocol)this.protocol).invokeQueryInterceptorsPost(sql, interceptedQuery, originalResultSet, forceExecute);
    }

    public boolean shouldIntercept() {
        return ((NativeProtocol)this.protocol).getQueryInterceptors() != null;
    }

    public long getCurrentTimeNanosOrMillis() {
        return ((NativeProtocol)this.protocol).getCurrentTimeNanosOrMillis();
    }

    public final NativePacketPayload sendCommand(NativePacketPayload queryPacket, boolean skipCheck, int timeoutMillis) {
        return (NativePacketPayload)this.protocol.sendCommand(queryPacket, skipCheck, timeoutMillis);
    }

    public long getSlowQueryThreshold() {
        return ((NativeProtocol)this.protocol).getSlowQueryThreshold();
    }

    public boolean hadWarnings() {
        return ((NativeProtocol)this.protocol).hadWarnings();
    }

    public void clearInputStream() {
        ((NativeProtocol)this.protocol).clearInputStream();
    }

    public NetworkResources getNetworkResources() {
        return this.protocol.getSocketConnection().getNetworkResources();
    }

    @Override
    public boolean isSSLEstablished() {
        return this.protocol.getSocketConnection().isSSLEstablished();
    }

    public int getCommandCount() {
        return ((NativeProtocol)this.protocol).getCommandCount();
    }

    @Override
    public SocketAddress getRemoteSocketAddress() {
        try {
            return this.protocol.getSocketConnection().getMysqlSocket().getRemoteSocketAddress();
        }
        catch (IOException e) {
            throw new CJCommunicationsException(e);
        }
    }

    public InputStream getLocalInfileInputStream() {
        return this.protocol.getLocalInfileInputStream();
    }

    public void setLocalInfileInputStream(InputStream stream) {
        this.protocol.setLocalInfileInputStream(stream);
    }

    private void configureCharsetProperties() {
        if (this.characterEncoding.getValue() != null) {
            try {
                String testString = "abc";
                StringUtils.getBytes(testString, (String)this.characterEncoding.getValue());
            }
            catch (WrongArgumentException waEx) {
                String oldEncoding = (String)this.characterEncoding.getValue();
                this.characterEncoding.setValue(CharsetMapping.getJavaEncodingForMysqlCharset(oldEncoding));
                if (this.characterEncoding.getValue() == null) {
                    throw ExceptionFactory.createException(WrongArgumentException.class, Messages.getString("Connection.5", new Object[]{oldEncoding}), this.getExceptionInterceptor());
                }
                String testString = "abc";
                StringUtils.getBytes(testString, (String)this.characterEncoding.getValue());
            }
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public boolean configureClientCharacterSet(boolean dontCheckServerMatch) {
        boolean characterSetAlreadyConfigured;
        block38: {
            String realJavaEncoding = (String)this.characterEncoding.getValue();
            RuntimeProperty<String> characterSetResults = this.getPropertySet().getProperty(PropertyKey.characterSetResults);
            characterSetAlreadyConfigured = false;
            try {
                String mysqlCharsetName;
                characterSetAlreadyConfigured = true;
                this.configureCharsetProperties();
                realJavaEncoding = (String)this.characterEncoding.getValue();
                String connectionCollationSuffix = "";
                String connectionCollationCharset = null;
                String connectionCollation = this.getPropertySet().getStringProperty(PropertyKey.connectionCollation).getStringValue();
                if (connectionCollation != null) {
                    for (int i = 1; i < CharsetMapping.COLLATION_INDEX_TO_COLLATION_NAME.length; ++i) {
                        if (!CharsetMapping.COLLATION_INDEX_TO_COLLATION_NAME[i].equals(connectionCollation)) continue;
                        connectionCollationSuffix = " COLLATE " + CharsetMapping.COLLATION_INDEX_TO_COLLATION_NAME[i];
                        connectionCollationCharset = CharsetMapping.COLLATION_INDEX_TO_CHARSET[i].charsetName;
                        realJavaEncoding = CharsetMapping.getJavaEncodingForCollationIndex(i);
                    }
                }
                try {
                    String serverEncodingToSet = CharsetMapping.getJavaEncodingForCollationIndex(this.protocol.getServerSession().getServerDefaultCollationIndex());
                    if (serverEncodingToSet == null || serverEncodingToSet.length() == 0) {
                        if (realJavaEncoding != null) {
                            this.characterEncoding.setValue(realJavaEncoding);
                        } else {
                            throw ExceptionFactory.createException(Messages.getString("Connection.6", new Object[]{this.protocol.getServerSession().getServerDefaultCollationIndex()}), this.getExceptionInterceptor());
                        }
                    }
                    if ("ISO8859_1".equalsIgnoreCase(serverEncodingToSet)) {
                        serverEncodingToSet = "Cp1252";
                    }
                    if ("UnicodeBig".equalsIgnoreCase(serverEncodingToSet) || "UTF-16".equalsIgnoreCase(serverEncodingToSet) || "UTF-16LE".equalsIgnoreCase(serverEncodingToSet) || "UTF-32".equalsIgnoreCase(serverEncodingToSet)) {
                        serverEncodingToSet = "UTF-8";
                    }
                    this.characterEncoding.setValue(serverEncodingToSet);
                }
                catch (ArrayIndexOutOfBoundsException outOfBoundsEx) {
                    if (realJavaEncoding != null) {
                        this.characterEncoding.setValue(realJavaEncoding);
                    }
                    throw ExceptionFactory.createException(Messages.getString("Connection.6", new Object[]{this.protocol.getServerSession().getServerDefaultCollationIndex()}), this.getExceptionInterceptor());
                }
                if (this.characterEncoding.getValue() == null) {
                    this.characterEncoding.setValue("ISO8859_1");
                }
                if (realJavaEncoding != null) {
                    if (realJavaEncoding.equalsIgnoreCase("UTF-8") || realJavaEncoding.equalsIgnoreCase("UTF8")) {
                        String utf8CharsetName;
                        String string = utf8CharsetName = connectionCollationSuffix.length() > 0 ? connectionCollationCharset : "utf8mb4";
                        if (dontCheckServerMatch || !this.protocol.getServerSession().characterSetNamesMatches("utf8") || !this.protocol.getServerSession().characterSetNamesMatches("utf8mb4") || connectionCollationSuffix.length() > 0 && !connectionCollation.equalsIgnoreCase(this.protocol.getServerSession().getServerVariable("collation_server"))) {
                            this.sendCommand(this.commandBuilder.buildComQuery(null, "SET NAMES " + utf8CharsetName + connectionCollationSuffix), false, 0);
                            this.protocol.getServerSession().getServerVariables().put("character_set_client", utf8CharsetName);
                            this.protocol.getServerSession().getServerVariables().put("character_set_connection", utf8CharsetName);
                        }
                        this.characterEncoding.setValue(realJavaEncoding);
                    } else {
                        String string = mysqlCharsetName = connectionCollationSuffix.length() > 0 ? connectionCollationCharset : CharsetMapping.getMysqlCharsetForJavaEncoding(realJavaEncoding.toUpperCase(Locale.ENGLISH), this.getServerSession().getServerVersion());
                        if (mysqlCharsetName != null && (dontCheckServerMatch || !this.protocol.getServerSession().characterSetNamesMatches(mysqlCharsetName))) {
                            this.sendCommand(this.commandBuilder.buildComQuery(null, "SET NAMES " + mysqlCharsetName + connectionCollationSuffix), false, 0);
                            this.protocol.getServerSession().getServerVariables().put("character_set_client", mysqlCharsetName);
                            this.protocol.getServerSession().getServerVariables().put("character_set_connection", mysqlCharsetName);
                        }
                        this.characterEncoding.setValue(realJavaEncoding);
                    }
                } else if (this.characterEncoding.getValue() != null) {
                    mysqlCharsetName = connectionCollationSuffix.length() > 0 ? connectionCollationCharset : this.getServerSession().getServerDefaultCharset();
                    boolean ucs2 = false;
                    if ("ucs2".equalsIgnoreCase(mysqlCharsetName) || "utf16".equalsIgnoreCase(mysqlCharsetName) || "utf16le".equalsIgnoreCase(mysqlCharsetName) || "utf32".equalsIgnoreCase(mysqlCharsetName)) {
                        mysqlCharsetName = "utf8";
                        ucs2 = true;
                        if (characterSetResults.getValue() == null) {
                            characterSetResults.setValue("UTF-8");
                        }
                    }
                    if (dontCheckServerMatch || !this.protocol.getServerSession().characterSetNamesMatches(mysqlCharsetName) || ucs2) {
                        this.sendCommand(this.commandBuilder.buildComQuery(null, "SET NAMES " + mysqlCharsetName + connectionCollationSuffix), false, 0);
                        this.protocol.getServerSession().getServerVariables().put("character_set_client", mysqlCharsetName);
                        this.protocol.getServerSession().getServerVariables().put("character_set_connection", mysqlCharsetName);
                    }
                    realJavaEncoding = (String)this.characterEncoding.getValue();
                }
                String onServer = this.protocol.getServerSession().getServerVariable("character_set_results");
                if (characterSetResults.getValue() == null) {
                    if (onServer != null && onServer.length() > 0 && !"NULL".equalsIgnoreCase(onServer)) {
                        this.sendCommand(this.commandBuilder.buildComQuery(null, "SET character_set_results = NULL"), false, 0);
                        this.protocol.getServerSession().getServerVariables().put("local.character_set_results", null);
                    } else {
                        this.protocol.getServerSession().getServerVariables().put("local.character_set_results", onServer);
                    }
                } else {
                    String charsetResults = (String)characterSetResults.getValue();
                    String mysqlEncodingName = null;
                    mysqlEncodingName = "UTF-8".equalsIgnoreCase(charsetResults) || "UTF8".equalsIgnoreCase(charsetResults) ? "utf8" : ("null".equalsIgnoreCase(charsetResults) ? "NULL" : CharsetMapping.getMysqlCharsetForJavaEncoding(charsetResults.toUpperCase(Locale.ENGLISH), this.getServerSession().getServerVersion()));
                    if (mysqlEncodingName == null) {
                        throw ExceptionFactory.createException(WrongArgumentException.class, Messages.getString("Connection.7", new Object[]{charsetResults}), this.getExceptionInterceptor());
                    }
                    if (!mysqlEncodingName.equalsIgnoreCase(this.protocol.getServerSession().getServerVariable("character_set_results"))) {
                        StringBuilder setBuf = new StringBuilder("SET character_set_results = ".length() + mysqlEncodingName.length());
                        setBuf.append("SET character_set_results = ").append(mysqlEncodingName);
                        this.sendCommand(this.commandBuilder.buildComQuery(null, setBuf.toString()), false, 0);
                        this.protocol.getServerSession().getServerVariables().put("local.character_set_results", mysqlEncodingName);
                        this.protocol.getServerSession().setErrorMessageEncoding(charsetResults);
                    } else {
                        this.protocol.getServerSession().getServerVariables().put("local.character_set_results", onServer);
                    }
                }
            }
            finally {
                this.characterEncoding.setValue(realJavaEncoding);
            }
            try {
                CharsetEncoder enc = Charset.forName((String)this.characterEncoding.getValue()).newEncoder();
                CharBuffer cbuf = CharBuffer.allocate(1);
                ByteBuffer bbuf = ByteBuffer.allocate(1);
                cbuf.put("\u00a5");
                cbuf.position(0);
                enc.encode(cbuf, bbuf, true);
                if (bbuf.get(0) == 92) {
                    this.requiresEscapingEncoder = true;
                } else {
                    cbuf.clear();
                    bbuf.clear();
                    cbuf.put("\u20a9");
                    cbuf.position(0);
                    enc.encode(cbuf, bbuf, true);
                    if (bbuf.get(0) == 92) {
                        this.requiresEscapingEncoder = true;
                    }
                }
            }
            catch (UnsupportedCharsetException ucex) {
                byte[] bbuf = StringUtils.getBytes("\u00a5", (String)this.characterEncoding.getValue());
                if (bbuf[0] == 92) {
                    this.requiresEscapingEncoder = true;
                }
                bbuf = StringUtils.getBytes("\u20a9", (String)this.characterEncoding.getValue());
                if (bbuf[0] != 92) break block38;
                this.requiresEscapingEncoder = true;
            }
        }
        return characterSetAlreadyConfigured;
    }

    public boolean getRequiresEscapingEncoder() {
        return this.requiresEscapingEncoder;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private void createConfigCacheIfNeeded(Object syncMutex) {
        Object object = syncMutex;
        synchronized (object) {
            if (this.serverConfigCache != null) {
                return;
            }
            try {
                Class<?> factoryClass = Class.forName(this.getPropertySet().getStringProperty(PropertyKey.serverConfigCacheFactory).getStringValue());
                CacheAdapterFactory cacheFactory = (CacheAdapterFactory)factoryClass.newInstance();
                this.serverConfigCache = cacheFactory.getInstance(syncMutex, this.hostInfo.getDatabaseUrl(), Integer.MAX_VALUE, Integer.MAX_VALUE);
                ExceptionInterceptor evictOnCommsError = new ExceptionInterceptor(){

                    @Override
                    public ExceptionInterceptor init(Properties config, Log log1) {
                        return this;
                    }

                    @Override
                    public void destroy() {
                    }

                    @Override
                    public Exception interceptException(Exception sqlEx) {
                        if (sqlEx instanceof SQLException && ((SQLException)sqlEx).getSQLState() != null && ((SQLException)sqlEx).getSQLState().startsWith("08")) {
                            NativeSession.this.serverConfigCache.invalidate(NativeSession.this.hostInfo.getDatabaseUrl());
                        }
                        return null;
                    }
                };
                if (this.exceptionInterceptor == null) {
                    this.exceptionInterceptor = evictOnCommsError;
                } else {
                    ((ExceptionInterceptorChain)this.exceptionInterceptor).addRingZero(evictOnCommsError);
                }
            }
            catch (ClassNotFoundException e) {
                throw ExceptionFactory.createException(Messages.getString("Connection.CantFindCacheFactory", new Object[]{this.getPropertySet().getStringProperty(PropertyKey.parseInfoCacheFactory).getValue(), PropertyKey.parseInfoCacheFactory}), e, this.getExceptionInterceptor());
            }
            catch (CJException | IllegalAccessException | InstantiationException e) {
                throw ExceptionFactory.createException(Messages.getString("Connection.CantLoadCacheFactory", new Object[]{this.getPropertySet().getStringProperty(PropertyKey.parseInfoCacheFactory).getValue(), PropertyKey.parseInfoCacheFactory}), e, this.getExceptionInterceptor());
            }
        }
    }

    public void loadServerVariables(Object syncMutex, String version) {
        if (((Boolean)this.cacheServerConfiguration.getValue()).booleanValue()) {
            this.createConfigCacheIfNeeded(syncMutex);
            Map<String, String> cachedVariableMap = this.serverConfigCache.get(this.hostInfo.getDatabaseUrl());
            if (cachedVariableMap != null) {
                String cachedServerVersion = cachedVariableMap.get(SERVER_VERSION_STRING_VAR_NAME);
                if (cachedServerVersion != null && this.getServerSession().getServerVersion() != null && cachedServerVersion.equals(this.getServerSession().getServerVersion().toString())) {
                    this.protocol.getServerSession().setServerVariables(cachedVariableMap);
                    return;
                }
                this.serverConfigCache.invalidate(this.hostInfo.getDatabaseUrl());
            }
        }
        try {
            if (version != null && version.indexOf(42) != -1) {
                StringBuilder buf = new StringBuilder(version.length() + 10);
                for (int i = 0; i < version.length(); ++i) {
                    char c = version.charAt(i);
                    buf.append(c == '*' ? "[star]" : Character.valueOf(c));
                }
                version = buf.toString();
            }
            String versionComment = this.propertySet.getBooleanProperty(PropertyKey.paranoid).getValue() != false || version == null ? "" : "/* " + version + " */";
            this.protocol.getServerSession().setServerVariables(new HashMap<String, String>());
            if (this.versionMeetsMinimum(5, 1, 0)) {
                StringBuilder queryBuf = new StringBuilder(versionComment).append("SELECT");
                queryBuf.append("  @@session.auto_increment_increment AS auto_increment_increment");
                queryBuf.append(", @@character_set_client AS character_set_client");
                queryBuf.append(", @@character_set_connection AS character_set_connection");
                queryBuf.append(", @@character_set_results AS character_set_results");
                queryBuf.append(", @@character_set_server AS character_set_server");
                queryBuf.append(", @@collation_server AS collation_server");
                queryBuf.append(", @@collation_connection AS collation_connection");
                queryBuf.append(", @@init_connect AS init_connect");
                queryBuf.append(", @@interactive_timeout AS interactive_timeout");
                if (!this.versionMeetsMinimum(5, 5, 0)) {
                    queryBuf.append(", @@language AS language");
                }
                queryBuf.append(", @@license AS license");
                queryBuf.append(", @@lower_case_table_names AS lower_case_table_names");
                queryBuf.append(", @@max_allowed_packet AS max_allowed_packet");
                queryBuf.append(", @@net_write_timeout AS net_write_timeout");
                queryBuf.append(", @@performance_schema AS performance_schema");
                if (!this.versionMeetsMinimum(8, 0, 3)) {
                    queryBuf.append(", @@query_cache_size AS query_cache_size");
                    queryBuf.append(", @@query_cache_type AS query_cache_type");
                }
                queryBuf.append(", @@sql_mode AS sql_mode");
                queryBuf.append(", @@system_time_zone AS system_time_zone");
                queryBuf.append(", @@time_zone AS time_zone");
                if (this.versionMeetsMinimum(8, 0, 3) || this.versionMeetsMinimum(5, 7, 20) && !this.versionMeetsMinimum(8, 0, 0)) {
                    queryBuf.append(", @@transaction_isolation AS transaction_isolation");
                } else {
                    queryBuf.append(", @@tx_isolation AS transaction_isolation");
                }
                queryBuf.append(", @@wait_timeout AS wait_timeout");
                NativePacketPayload resultPacket = this.sendCommand(this.commandBuilder.buildComQuery(null, queryBuf.toString()), false, 0);
                Resultset rs = ((NativeProtocol)this.protocol).readAllResults(-1, false, resultPacket, false, null, new ResultsetFactory(Resultset.Type.FORWARD_ONLY, null));
                Field[] f = rs.getColumnDefinition().getFields();
                if (f.length > 0) {
                    StringValueFactory vf = new StringValueFactory(this.propertySet);
                    Row r = (Row)rs.getRows().next();
                    if (r != null) {
                        for (int i = 0; i < f.length; ++i) {
                            this.protocol.getServerSession().getServerVariables().put(f[i].getColumnLabel(), r.getValue(i, vf));
                        }
                    }
                }
            } else {
                Row r;
                NativePacketPayload resultPacket = this.sendCommand(this.commandBuilder.buildComQuery(null, versionComment + "SHOW VARIABLES"), false, 0);
                Resultset rs = ((NativeProtocol)this.protocol).readAllResults(-1, false, resultPacket, false, null, new ResultsetFactory(Resultset.Type.FORWARD_ONLY, null));
                StringValueFactory vf = new StringValueFactory(this.propertySet);
                while ((r = (Row)rs.getRows().next()) != null) {
                    this.protocol.getServerSession().getServerVariables().put(r.getValue(0, vf), r.getValue(1, vf));
                }
            }
        }
        catch (IOException e) {
            throw ExceptionFactory.createException(e.getMessage(), e);
        }
        if (((Boolean)this.cacheServerConfiguration.getValue()).booleanValue()) {
            this.protocol.getServerSession().getServerVariables().put(SERVER_VERSION_STRING_VAR_NAME, this.getServerSession().getServerVersion().toString());
            this.serverConfigCache.put(this.hostInfo.getDatabaseUrl(), this.protocol.getServerSession().getServerVariables());
        }
    }

    public void setSessionVariables() {
        String sessionVariables = this.getPropertySet().getStringProperty(PropertyKey.sessionVariables).getValue();
        if (sessionVariables != null) {
            ArrayList<String> variablesToSet = new ArrayList<String>();
            for (String part : StringUtils.split(sessionVariables, ",", "\"'(", "\"')", "\"'", true)) {
                variablesToSet.addAll(StringUtils.split(part, ";", "\"'(", "\"')", "\"'", true));
            }
            if (!variablesToSet.isEmpty()) {
                StringBuilder query = new StringBuilder("SET ");
                String separator = "";
                for (String variableToSet : variablesToSet) {
                    if (variableToSet.length() <= 0) continue;
                    query.append(separator);
                    if (!variableToSet.startsWith("@")) {
                        query.append("SESSION ");
                    }
                    query.append(variableToSet);
                    separator = ",";
                }
                this.sendCommand(this.commandBuilder.buildComQuery(null, query.toString()), false, 0);
            }
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void buildCollationMapping() {
        Map<Integer, String> customCharset = null;
        Map<String, Integer> customMblen = null;
        String databaseURL = this.hostInfo.getDatabaseUrl();
        if (((Boolean)this.cacheServerConfiguration.getValue()).booleanValue()) {
            Map<String, Map<Integer, String>> map = customIndexToCharsetMapByUrl;
            synchronized (map) {
                customCharset = customIndexToCharsetMapByUrl.get(databaseURL);
                customMblen = customCharsetToMblenMapByUrl.get(databaseURL);
            }
        }
        if (customCharset == null && this.getPropertySet().getBooleanProperty(PropertyKey.detectCustomCollations).getValue().booleanValue()) {
            Resultset rs;
            NativePacketPayload resultPacket;
            customCharset = new HashMap<Integer, String>();
            customMblen = new HashMap<String, Integer>();
            IntegerValueFactory ivf = new IntegerValueFactory(this.getPropertySet());
            try {
                Row r;
                resultPacket = this.sendCommand(this.commandBuilder.buildComQuery(null, "SHOW COLLATION"), false, 0);
                rs = ((NativeProtocol)this.protocol).readAllResults(-1, false, resultPacket, false, null, new ResultsetFactory(Resultset.Type.FORWARD_ONLY, null));
                StringValueFactory svf = new StringValueFactory(this.propertySet);
                while ((r = (Row)rs.getRows().next()) != null) {
                    int collationIndex = ((Number)r.getValue(2, ivf)).intValue();
                    String charsetName = r.getValue(1, svf);
                    if (collationIndex >= 2048 || !charsetName.equals(CharsetMapping.getMysqlCharsetNameForCollationIndex(collationIndex))) {
                        customCharset.put(collationIndex, charsetName);
                    }
                    if (CharsetMapping.CHARSET_NAME_TO_CHARSET.containsKey(charsetName)) continue;
                    customMblen.put(charsetName, null);
                }
            }
            catch (IOException e) {
                throw ExceptionFactory.createException(e.getMessage(), e, this.exceptionInterceptor);
            }
            if (customMblen.size() > 0) {
                try {
                    Row r;
                    resultPacket = this.sendCommand(this.commandBuilder.buildComQuery(null, "SHOW CHARACTER SET"), false, 0);
                    rs = ((NativeProtocol)this.protocol).readAllResults(-1, false, resultPacket, false, null, new ResultsetFactory(Resultset.Type.FORWARD_ONLY, null));
                    int charsetColumn = rs.getColumnDefinition().getColumnNameToIndex().get("Charset");
                    int maxlenColumn = rs.getColumnDefinition().getColumnNameToIndex().get("Maxlen");
                    StringValueFactory svf = new StringValueFactory(this.propertySet);
                    while ((r = (Row)rs.getRows().next()) != null) {
                        String charsetName = r.getValue(charsetColumn, svf);
                        if (!customMblen.containsKey(charsetName)) continue;
                        customMblen.put(charsetName, r.getValue(maxlenColumn, ivf));
                    }
                }
                catch (IOException e) {
                    throw ExceptionFactory.createException(e.getMessage(), e, this.exceptionInterceptor);
                }
            }
            if (((Boolean)this.cacheServerConfiguration.getValue()).booleanValue()) {
                Map<String, Map<Integer, String>> e = customIndexToCharsetMapByUrl;
                synchronized (e) {
                    customIndexToCharsetMapByUrl.put(databaseURL, customCharset);
                    customCharsetToMblenMapByUrl.put(databaseURL, customMblen);
                }
            }
        }
        if (customCharset != null) {
            ((NativeServerSession)this.protocol.getServerSession()).indexToCustomMysqlCharset = Collections.unmodifiableMap(customCharset);
        }
        if (customMblen != null) {
            ((NativeServerSession)this.protocol.getServerSession()).mysqlCharsetToCustomMblen = Collections.unmodifiableMap(customMblen);
        }
        if (this.protocol.getServerSession().getServerDefaultCollationIndex() == 0) {
            String collationServer = this.protocol.getServerSession().getServerVariable("collation_server");
            if (collationServer != null) {
                for (int i = 1; i < CharsetMapping.COLLATION_INDEX_TO_COLLATION_NAME.length; ++i) {
                    if (!CharsetMapping.COLLATION_INDEX_TO_COLLATION_NAME[i].equals(collationServer)) continue;
                    this.protocol.getServerSession().setServerDefaultCollationIndex(i);
                    break;
                }
            } else {
                this.protocol.getServerSession().setServerDefaultCollationIndex(45);
            }
        }
    }

    @Override
    public String getProcessHost() {
        try {
            long threadId = this.getThreadId();
            String processHost = this.findProcessHost(threadId);
            if (processHost == null) {
                this.log.logWarn(String.format("Connection id %d not found in \"SHOW PROCESSLIST\", assuming 32-bit overflow, using SELECT CONNECTION_ID() instead", threadId));
                NativePacketPayload resultPacket = this.sendCommand(this.commandBuilder.buildComQuery(null, "SELECT CONNECTION_ID()"), false, 0);
                Resultset rs = ((NativeProtocol)this.protocol).readAllResults(-1, false, resultPacket, false, null, new ResultsetFactory(Resultset.Type.FORWARD_ONLY, null));
                LongValueFactory lvf = new LongValueFactory(this.getPropertySet());
                Row r = (Row)rs.getRows().next();
                if (r != null) {
                    threadId = r.getValue(0, lvf);
                    processHost = this.findProcessHost(threadId);
                } else {
                    this.log.logError("No rows returned for statement \"SELECT CONNECTION_ID()\", local connection check will most likely be incorrect");
                }
            }
            if (processHost == null) {
                this.log.logWarn(String.format("Cannot find process listing for connection %d in SHOW PROCESSLIST output, unable to determine if locally connected", threadId));
            }
            return processHost;
        }
        catch (IOException e) {
            throw ExceptionFactory.createException(e.getMessage(), e);
        }
    }

    private String findProcessHost(long threadId) {
        try {
            Row r;
            String processHost = null;
            String ps = this.protocol.getServerSession().getServerVariable("performance_schema");
            NativePacketPayload resultPacket = this.versionMeetsMinimum(5, 6, 0) && ps != null && ("1".contentEquals(ps) || "ON".contentEquals(ps)) ? this.sendCommand(this.commandBuilder.buildComQuery(null, "select PROCESSLIST_ID, PROCESSLIST_USER, PROCESSLIST_HOST from performance_schema.threads where PROCESSLIST_ID=" + threadId), false, 0) : this.sendCommand(this.commandBuilder.buildComQuery(null, "SHOW PROCESSLIST"), false, 0);
            Resultset rs = ((NativeProtocol)this.protocol).readAllResults(-1, false, resultPacket, false, null, new ResultsetFactory(Resultset.Type.FORWARD_ONLY, null));
            LongValueFactory lvf = new LongValueFactory(this.getPropertySet());
            StringValueFactory svf = new StringValueFactory(this.propertySet);
            while ((r = (Row)rs.getRows().next()) != null) {
                long id = r.getValue(0, lvf);
                if (threadId != id) continue;
                processHost = r.getValue(2, svf);
                break;
            }
            return processHost;
        }
        catch (IOException e) {
            throw ExceptionFactory.createException(e.getMessage(), e);
        }
    }

    public String queryServerVariable(String varName) {
        try {
            String s;
            NativePacketPayload resultPacket = this.sendCommand(this.commandBuilder.buildComQuery(null, "SELECT " + varName), false, 0);
            Resultset rs = ((NativeProtocol)this.protocol).readAllResults(-1, false, resultPacket, false, null, new ResultsetFactory(Resultset.Type.FORWARD_ONLY, null));
            StringValueFactory svf = new StringValueFactory(this.propertySet);
            Row r = (Row)rs.getRows().next();
            if (r != null && (s = r.getValue(0, svf)) != null) {
                return s;
            }
            return null;
        }
        catch (IOException e) {
            throw ExceptionFactory.createException(e.getMessage(), e);
        }
    }

    public <T extends Resultset> T execSQL(Query callingQuery, String query, int maxRows, NativePacketPayload packet, boolean streamResults, ProtocolEntityFactory<T, NativePacketPayload> resultSetFactory, ColumnDefinition cachedMetadata, boolean isBatch) {
        long queryStartTime = (Boolean)this.gatherPerfMetrics.getValue() != false ? System.currentTimeMillis() : 0L;
        int endOfQueryPacketPosition = packet != null ? packet.getPosition() : 0;
        this.lastQueryFinishedTime = 0L;
        if (((Boolean)this.autoReconnect.getValue()).booleanValue() && (this.getServerSession().isAutoCommit() || ((Boolean)this.autoReconnectForPools.getValue()).booleanValue()) && this.needsPing && !isBatch) {
            try {
                this.ping(false, 0);
                this.needsPing = false;
            }
            catch (Exception Ex) {
                this.invokeReconnectListeners();
            }
        }
        try {
            T Ex = packet == null ? ((NativeProtocol)this.protocol).sendQueryString(callingQuery, query, (String)this.characterEncoding.getValue(), maxRows, streamResults, cachedMetadata, resultSetFactory) : ((NativeProtocol)this.protocol).sendQueryPacket(callingQuery, packet, maxRows, streamResults, cachedMetadata, resultSetFactory);
            return Ex;
        }
        catch (CJException sqlE) {
            if (this.getPropertySet().getBooleanProperty(PropertyKey.dumpQueriesOnException).getValue().booleanValue()) {
                String extractedSql = NativePacketPayload.extractSqlFromPacket(query, packet, endOfQueryPacketPosition, this.getPropertySet().getIntegerProperty(PropertyKey.maxQuerySizeToLog).getValue());
                StringBuilder messageBuf = new StringBuilder(extractedSql.length() + 32);
                messageBuf.append("\n\nQuery being executed when exception was thrown:\n");
                messageBuf.append(extractedSql);
                messageBuf.append("\n\n");
                sqlE.appendMessage(messageBuf.toString());
            }
            if (((Boolean)this.autoReconnect.getValue()).booleanValue()) {
                if (sqlE instanceof CJCommunicationsException) {
                    this.protocol.getSocketConnection().forceClose();
                }
                this.needsPing = true;
            } else if (sqlE instanceof CJCommunicationsException) {
                this.invokeCleanupListeners(sqlE);
            }
            throw sqlE;
        }
        catch (Throwable ex) {
            if (((Boolean)this.autoReconnect.getValue()).booleanValue()) {
                if (ex instanceof IOException) {
                    this.protocol.getSocketConnection().forceClose();
                } else if (ex instanceof IOException) {
                    this.invokeCleanupListeners(ex);
                }
                this.needsPing = true;
            }
            throw ExceptionFactory.createException(ex.getMessage(), ex, this.exceptionInterceptor);
        }
        finally {
            if (((Boolean)this.maintainTimeStats.getValue()).booleanValue()) {
                this.lastQueryFinishedTime = System.currentTimeMillis();
            }
            if (((Boolean)this.gatherPerfMetrics.getValue()).booleanValue()) {
                ((NativeProtocol)this.protocol).getMetricsHolder().registerQueryExecutionTime(System.currentTimeMillis() - queryStartTime);
            }
        }
    }

    public long getIdleFor() {
        return this.lastQueryFinishedTime == 0L ? 0L : System.currentTimeMillis() - this.lastQueryFinishedTime;
    }

    public boolean isNeedsPing() {
        return this.needsPing;
    }

    public void setNeedsPing(boolean needsPing) {
        this.needsPing = needsPing;
    }

    public void ping(boolean checkForClosedConnection, int timeoutMillis) {
        if (checkForClosedConnection) {
            this.checkClosed();
        }
        long pingMillisLifetime = this.getPropertySet().getIntegerProperty(PropertyKey.selfDestructOnPingSecondsLifetime).getValue().intValue();
        int pingMaxOperations = this.getPropertySet().getIntegerProperty(PropertyKey.selfDestructOnPingMaxOperations).getValue();
        if (pingMillisLifetime > 0L && System.currentTimeMillis() - this.connectionCreationTimeMillis > pingMillisLifetime || pingMaxOperations > 0 && pingMaxOperations <= this.getCommandCount()) {
            this.invokeNormalCloseListeners();
            throw ExceptionFactory.createException(Messages.getString("Connection.exceededConnectionLifetime"), "08S01", 0, false, null, this.exceptionInterceptor);
        }
        this.sendCommand(this.commandBuilder.buildComPing(null), false, timeoutMillis);
    }

    public long getConnectionCreationTimeMillis() {
        return this.connectionCreationTimeMillis;
    }

    public void setConnectionCreationTimeMillis(long connectionCreationTimeMillis) {
        this.connectionCreationTimeMillis = connectionCreationTimeMillis;
    }

    @Override
    public boolean isClosed() {
        return this.isClosed;
    }

    public void checkClosed() {
        if (this.isClosed) {
            if (this.forceClosedReason != null && this.forceClosedReason.getClass().equals(OperationCancelledException.class)) {
                throw (OperationCancelledException)this.forceClosedReason;
            }
            throw ExceptionFactory.createException(ConnectionIsClosedException.class, Messages.getString("Connection.2"), this.forceClosedReason, this.getExceptionInterceptor());
        }
    }

    public Throwable getForceClosedReason() {
        return this.forceClosedReason;
    }

    public void setForceClosedReason(Throwable forceClosedReason) {
        this.forceClosedReason = forceClosedReason;
    }

    @Override
    public void addListener(Session.SessionEventListener l) {
        this.listeners.addIfAbsent(new WeakReference<Session.SessionEventListener>(l));
    }

    @Override
    public void removeListener(Session.SessionEventListener listener) {
        for (WeakReference<Session.SessionEventListener> wr : this.listeners) {
            Session.SessionEventListener l = (Session.SessionEventListener)wr.get();
            if (l != listener) continue;
            this.listeners.remove(wr);
            break;
        }
    }

    protected void invokeNormalCloseListeners() {
        for (WeakReference<Session.SessionEventListener> wr : this.listeners) {
            Session.SessionEventListener l = (Session.SessionEventListener)wr.get();
            if (l != null) {
                l.handleNormalClose();
                continue;
            }
            this.listeners.remove(wr);
        }
    }

    protected void invokeReconnectListeners() {
        for (WeakReference<Session.SessionEventListener> wr : this.listeners) {
            Session.SessionEventListener l = (Session.SessionEventListener)wr.get();
            if (l != null) {
                l.handleReconnect();
                continue;
            }
            this.listeners.remove(wr);
        }
    }

    public void invokeCleanupListeners(Throwable whyCleanedUp) {
        for (WeakReference<Session.SessionEventListener> wr : this.listeners) {
            Session.SessionEventListener l = (Session.SessionEventListener)wr.get();
            if (l != null) {
                l.handleCleanup(whyCleanedUp);
                continue;
            }
            this.listeners.remove(wr);
        }
    }

    @Override
    public String getIdentifierQuoteString() {
        return this.protocol != null && this.protocol.getServerSession().useAnsiQuotedIdentifiers() ? "\"" : "`";
    }

    public synchronized Timer getCancelTimer() {
        if (this.cancelTimer == null) {
            this.cancelTimer = new Timer("MySQL Statement Cancellation Timer", Boolean.TRUE);
        }
        return this.cancelTimer;
    }
}

