/*
 * Decompiled with CFR 0.150.
 */
package com.mysql.cj.protocol.a;

import com.mysql.cj.Constants;
import com.mysql.cj.MessageBuilder;
import com.mysql.cj.Messages;
import com.mysql.cj.MysqlType;
import com.mysql.cj.Query;
import com.mysql.cj.QueryResult;
import com.mysql.cj.ServerVersion;
import com.mysql.cj.Session;
import com.mysql.cj.TransactionEventHandler;
import com.mysql.cj.conf.PropertyKey;
import com.mysql.cj.conf.PropertySet;
import com.mysql.cj.conf.RuntimeProperty;
import com.mysql.cj.exceptions.CJCommunicationsException;
import com.mysql.cj.exceptions.CJConnectionFeatureNotAvailableException;
import com.mysql.cj.exceptions.CJException;
import com.mysql.cj.exceptions.CJOperationNotSupportedException;
import com.mysql.cj.exceptions.CJPacketTooBigException;
import com.mysql.cj.exceptions.ClosedOnExpiredPasswordException;
import com.mysql.cj.exceptions.DataTruncationException;
import com.mysql.cj.exceptions.ExceptionFactory;
import com.mysql.cj.exceptions.FeatureNotAvailableException;
import com.mysql.cj.exceptions.MysqlErrorNumbers;
import com.mysql.cj.exceptions.PasswordExpiredException;
import com.mysql.cj.exceptions.WrongArgumentException;
import com.mysql.cj.interceptors.QueryInterceptor;
import com.mysql.cj.jdbc.exceptions.MysqlDataTruncation;
import com.mysql.cj.log.BaseMetricsHolder;
import com.mysql.cj.log.Log;
import com.mysql.cj.log.ProfilerEventHandler;
import com.mysql.cj.protocol.AbstractProtocol;
import com.mysql.cj.protocol.AuthenticationProvider;
import com.mysql.cj.protocol.ColumnDefinition;
import com.mysql.cj.protocol.ExportControlled;
import com.mysql.cj.protocol.FullReadInputStream;
import com.mysql.cj.protocol.Message;
import com.mysql.cj.protocol.MessageReader;
import com.mysql.cj.protocol.MessageSender;
import com.mysql.cj.protocol.PacketReceivedTimeHolder;
import com.mysql.cj.protocol.PacketSentTimeHolder;
import com.mysql.cj.protocol.Protocol;
import com.mysql.cj.protocol.ProtocolEntity;
import com.mysql.cj.protocol.ProtocolEntityFactory;
import com.mysql.cj.protocol.ProtocolEntityReader;
import com.mysql.cj.protocol.ResultBuilder;
import com.mysql.cj.protocol.Resultset;
import com.mysql.cj.protocol.ResultsetRow;
import com.mysql.cj.protocol.ResultsetRows;
import com.mysql.cj.protocol.SocketConnection;
import com.mysql.cj.protocol.a.BinaryResultsetReader;
import com.mysql.cj.protocol.a.ColumnDefinitionReader;
import com.mysql.cj.protocol.a.CompressedInputStream;
import com.mysql.cj.protocol.a.CompressedPacketSender;
import com.mysql.cj.protocol.a.DebugBufferingPacketReader;
import com.mysql.cj.protocol.a.DebugBufferingPacketSender;
import com.mysql.cj.protocol.a.MultiPacketReader;
import com.mysql.cj.protocol.a.NativeAuthenticationProvider;
import com.mysql.cj.protocol.a.NativeCapabilities;
import com.mysql.cj.protocol.a.NativeConstants;
import com.mysql.cj.protocol.a.NativeMessageBuilder;
import com.mysql.cj.protocol.a.NativePacketHeader;
import com.mysql.cj.protocol.a.NativePacketPayload;
import com.mysql.cj.protocol.a.NativeServerSession;
import com.mysql.cj.protocol.a.ResultsetFactory;
import com.mysql.cj.protocol.a.ResultsetRowReader;
import com.mysql.cj.protocol.a.SimplePacketReader;
import com.mysql.cj.protocol.a.SimplePacketSender;
import com.mysql.cj.protocol.a.TextResultsetReader;
import com.mysql.cj.protocol.a.TimeTrackingPacketReader;
import com.mysql.cj.protocol.a.TimeTrackingPacketSender;
import com.mysql.cj.protocol.a.TracingPacketReader;
import com.mysql.cj.protocol.a.TracingPacketSender;
import com.mysql.cj.protocol.a.result.OkPacket;
import com.mysql.cj.result.Field;
import com.mysql.cj.result.IntegerValueFactory;
import com.mysql.cj.result.Row;
import com.mysql.cj.result.StringValueFactory;
import com.mysql.cj.util.LazyString;
import com.mysql.cj.util.StringUtils;
import com.mysql.cj.util.TestUtils;
import com.mysql.cj.util.TimeUtil;
import com.mysql.cj.util.Util;
import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;
import java.lang.ref.SoftReference;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.Socket;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.UnknownHostException;
import java.nio.file.InvalidPathException;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLWarning;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.TimeZone;
import java.util.function.Supplier;

public class NativeProtocol
extends AbstractProtocol<NativePacketPayload>
implements Protocol<NativePacketPayload>,
RuntimeProperty.RuntimePropertyListener {
    protected static final int INITIAL_PACKET_SIZE = 1024;
    protected static final int COMP_HEADER_LENGTH = 3;
    protected static final int MAX_QUERY_SIZE_TO_EXPLAIN = 0x100000;
    protected static final int SSL_REQUEST_LENGTH = 32;
    private static final String EXPLAINABLE_STATEMENT = "SELECT";
    private static final String[] EXPLAINABLE_STATEMENT_EXTENSION = new String[]{"INSERT", "UPDATE", "REPLACE", "DELETE"};
    protected MessageSender<NativePacketPayload> packetSender;
    protected MessageReader<NativePacketHeader, NativePacketPayload> packetReader;
    protected NativeServerSession serverSession;
    protected CompressedPacketSender compressedPacketSender;
    protected NativePacketPayload sharedSendPacket = null;
    protected NativePacketPayload reusablePacket = null;
    private SoftReference<NativePacketPayload> loadFileBufRef;
    protected byte packetSequence = 0;
    protected boolean useCompression = false;
    private RuntimeProperty<Integer> maxAllowedPacket;
    private RuntimeProperty<Boolean> useServerPrepStmts;
    private boolean autoGenerateTestcaseScript;
    private boolean logSlowQueries = false;
    private boolean useAutoSlowLog;
    private boolean profileSQL = false;
    private long slowQueryThreshold;
    private int commandCount = 0;
    protected boolean hadWarnings = false;
    private int warningCount = 0;
    protected Map<Class<? extends ProtocolEntity>, ProtocolEntityReader<? extends ProtocolEntity, ? extends Message>> PROTOCOL_ENTITY_CLASS_TO_TEXT_READER;
    protected Map<Class<? extends ProtocolEntity>, ProtocolEntityReader<? extends ProtocolEntity, ? extends Message>> PROTOCOL_ENTITY_CLASS_TO_BINARY_READER;
    protected boolean platformDbCharsetMatches = true;
    private int statementExecutionDepth = 0;
    private List<QueryInterceptor> queryInterceptors;
    private RuntimeProperty<Boolean> maintainTimeStats;
    private RuntimeProperty<Integer> maxQuerySizeToLog;
    private InputStream localInfileInputStream;
    private BaseMetricsHolder metricsHolder;
    private String queryComment = null;
    private static String jvmPlatformCharset = null;
    private NativeMessageBuilder commandBuilder = new NativeMessageBuilder();
    private ResultsetRows streamingData = null;

    public static NativeProtocol getInstance(Session session, SocketConnection socketConnection, PropertySet propertySet, Log log, TransactionEventHandler transactionManager) {
        NativeProtocol protocol = new NativeProtocol(log);
        protocol.init(session, socketConnection, propertySet, transactionManager);
        return protocol;
    }

    public NativeProtocol(Log logger) {
        this.log = logger;
        this.metricsHolder = new BaseMetricsHolder();
    }

    @Override
    public void init(Session sess, SocketConnection phConnection, PropertySet propSet, TransactionEventHandler trManager) {
        super.init(sess, phConnection, propSet, trManager);
        this.maintainTimeStats = this.propertySet.getBooleanProperty(PropertyKey.maintainTimeStats);
        this.maxQuerySizeToLog = this.propertySet.getIntegerProperty(PropertyKey.maxQuerySizeToLog);
        this.useAutoSlowLog = this.propertySet.getBooleanProperty(PropertyKey.autoSlowLog).getValue();
        this.logSlowQueries = this.propertySet.getBooleanProperty(PropertyKey.logSlowQueries).getValue();
        this.maxAllowedPacket = this.propertySet.getIntegerProperty(PropertyKey.maxAllowedPacket);
        this.profileSQL = this.propertySet.getBooleanProperty(PropertyKey.profileSQL).getValue();
        this.autoGenerateTestcaseScript = this.propertySet.getBooleanProperty(PropertyKey.autoGenerateTestcaseScript).getValue();
        this.useServerPrepStmts = this.propertySet.getBooleanProperty(PropertyKey.useServerPrepStmts);
        this.reusablePacket = new NativePacketPayload(1024);
        try {
            this.packetSender = new SimplePacketSender(this.socketConnection.getMysqlOutput());
            this.packetReader = new SimplePacketReader(this.socketConnection, this.maxAllowedPacket);
        }
        catch (IOException ioEx) {
            throw ExceptionFactory.createCommunicationsException(this.propertySet, this.serverSession, this.getPacketSentTimeHolder(), this.getPacketReceivedTimeHolder(), ioEx, this.getExceptionInterceptor());
        }
        if (this.propertySet.getBooleanProperty(PropertyKey.logSlowQueries).getValue().booleanValue()) {
            this.calculateSlowQueryThreshold();
        }
        this.authProvider = new NativeAuthenticationProvider();
        this.authProvider.init(this, this.getPropertySet(), this.socketConnection.getExceptionInterceptor());
        HashMap<Class<Resultset>, ProtocolEntityReader<ColumnDefinition, NativePacketPayload>> protocolEntityClassToTextReader = new HashMap<Class<Resultset>, ProtocolEntityReader<ColumnDefinition, NativePacketPayload>>();
        protocolEntityClassToTextReader.put(ColumnDefinition.class, new ColumnDefinitionReader(this));
        protocolEntityClassToTextReader.put(ResultsetRow.class, new ResultsetRowReader(this));
        protocolEntityClassToTextReader.put(Resultset.class, new TextResultsetReader(this));
        this.PROTOCOL_ENTITY_CLASS_TO_TEXT_READER = Collections.unmodifiableMap(protocolEntityClassToTextReader);
        HashMap<Class, ProtocolEntityReader<ColumnDefinition, NativePacketPayload>> protocolEntityClassToBinaryReader = new HashMap<Class, ProtocolEntityReader<ColumnDefinition, NativePacketPayload>>();
        protocolEntityClassToBinaryReader.put(ColumnDefinition.class, new ColumnDefinitionReader(this));
        protocolEntityClassToBinaryReader.put(Resultset.class, new BinaryResultsetReader(this));
        this.PROTOCOL_ENTITY_CLASS_TO_BINARY_READER = Collections.unmodifiableMap(protocolEntityClassToBinaryReader);
    }

    @Override
    public MessageBuilder<NativePacketPayload> getMessageBuilder() {
        throw ExceptionFactory.createException(CJOperationNotSupportedException.class, "Not supported");
    }

    public MessageSender<NativePacketPayload> getPacketSender() {
        return this.packetSender;
    }

    public MessageReader<NativePacketHeader, NativePacketPayload> getPacketReader() {
        return this.packetReader;
    }

    @Override
    public void negotiateSSLConnection() {
        if (!ExportControlled.enabled()) {
            throw new CJConnectionFeatureNotAvailableException(this.getPropertySet(), this.serverSession, this.getPacketSentTimeHolder(), null);
        }
        long clientParam = this.serverSession.getClientParam();
        NativePacketPayload packet = new NativePacketPayload(32);
        packet.writeInteger(NativeConstants.IntegerDataType.INT4, clientParam);
        packet.writeInteger(NativeConstants.IntegerDataType.INT4, 0xFFFFFFL);
        packet.writeInteger(NativeConstants.IntegerDataType.INT1, AuthenticationProvider.getCharsetForHandshake(this.authProvider.getEncodingForHandshake(), this.serverSession.getCapabilities().getServerVersion()));
        packet.writeBytes(NativeConstants.StringLengthDataType.STRING_FIXED, new byte[23]);
        this.send(packet, packet.getPosition());
        try {
            this.socketConnection.performTlsHandshake(this.serverSession);
            this.packetSender = new SimplePacketSender(this.socketConnection.getMysqlOutput());
            this.packetReader = new SimplePacketReader(this.socketConnection, this.maxAllowedPacket);
        }
        catch (FeatureNotAvailableException nae) {
            throw new CJConnectionFeatureNotAvailableException(this.getPropertySet(), this.serverSession, this.getPacketSentTimeHolder(), nae);
        }
        catch (IOException ioEx) {
            throw ExceptionFactory.createCommunicationsException(this.propertySet, this.serverSession, this.getPacketSentTimeHolder(), this.getPacketReceivedTimeHolder(), ioEx, this.getExceptionInterceptor());
        }
    }

    public void rejectProtocol(NativePacketPayload msg) {
        try {
            this.socketConnection.getMysqlSocket().close();
        }
        catch (Exception exception) {
            // empty catch block
        }
        int errno = 2000;
        NativePacketPayload buf = msg;
        buf.setPosition(1);
        errno = (int)buf.readInteger(NativeConstants.IntegerDataType.INT2);
        String serverErrorMessage = "";
        try {
            serverErrorMessage = buf.readString(NativeConstants.StringSelfDataType.STRING_TERM, "ASCII");
        }
        catch (Exception exception) {
            // empty catch block
        }
        StringBuilder errorBuf = new StringBuilder(Messages.getString("Protocol.0"));
        errorBuf.append(serverErrorMessage);
        errorBuf.append("\"");
        String xOpen = MysqlErrorNumbers.mysqlToSqlState(errno);
        throw ExceptionFactory.createException(MysqlErrorNumbers.get(xOpen) + ", " + errorBuf.toString(), xOpen, errno, false, null, this.getExceptionInterceptor());
    }

    @Override
    public void beforeHandshake() {
        this.packetReader.resetMessageSequence();
        this.serverSession = new NativeServerSession(this.propertySet);
        this.serverSession.setCapabilities(this.readServerCapabilities());
    }

    @Override
    public void afterHandshake() {
        this.checkTransactionState();
        try {
            if ((this.serverSession.getCapabilities().getCapabilityFlags() & 0x20) != 0 && this.propertySet.getBooleanProperty(PropertyKey.useCompression).getValue().booleanValue() && !(this.socketConnection.getMysqlInput().getUnderlyingStream() instanceof CompressedInputStream)) {
                this.useCompression = true;
                this.socketConnection.setMysqlInput(new FullReadInputStream(new CompressedInputStream(this.socketConnection.getMysqlInput(), this.propertySet.getBooleanProperty(PropertyKey.traceProtocol), this.log)));
                this.packetSender = this.compressedPacketSender = new CompressedPacketSender(this.socketConnection.getMysqlOutput());
            }
            this.applyPacketDecorators(this.packetSender, this.packetReader);
            this.socketConnection.getSocketFactory().afterHandshake();
        }
        catch (IOException ioEx) {
            throw ExceptionFactory.createCommunicationsException(this.propertySet, this.serverSession, this.getPacketSentTimeHolder(), this.getPacketReceivedTimeHolder(), ioEx, this.getExceptionInterceptor());
        }
        RuntimeProperty<Boolean> useInformationSchema = this.propertySet.getProperty(PropertyKey.useInformationSchema);
        if (this.versionMeetsMinimum(8, 0, 3) && !((Boolean)useInformationSchema.getValue()).booleanValue() && !useInformationSchema.isExplicitlySet()) {
            useInformationSchema.setValue(true);
        }
        this.maintainTimeStats.addListener(this);
        this.propertySet.getBooleanProperty(PropertyKey.traceProtocol).addListener(this);
        this.propertySet.getBooleanProperty(PropertyKey.enablePacketDebug).addListener(this);
    }

    @Override
    public void handlePropertyChange(RuntimeProperty<?> prop) {
        switch (prop.getPropertyDefinition().getPropertyKey()) {
            case maintainTimeStats: 
            case traceProtocol: 
            case enablePacketDebug: {
                this.applyPacketDecorators(this.packetSender.undecorateAll(), this.packetReader.undecorateAll());
                break;
            }
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void applyPacketDecorators(MessageSender<NativePacketPayload> sender, MessageReader<NativePacketHeader, NativePacketPayload> messageReader) {
        TimeTrackingPacketSender ttSender = null;
        TimeTrackingPacketReader ttReader = null;
        LinkedList<StringBuilder> debugRingBuffer = null;
        if (this.maintainTimeStats.getValue().booleanValue()) {
            sender = ttSender = new TimeTrackingPacketSender(sender);
            messageReader = ttReader = new TimeTrackingPacketReader(messageReader);
        }
        if (this.propertySet.getBooleanProperty(PropertyKey.traceProtocol).getValue().booleanValue()) {
            sender = new TracingPacketSender(sender, this.log, this.socketConnection.getHost(), this.getServerSession().getCapabilities().getThreadId());
            messageReader = new TracingPacketReader(messageReader, this.log);
        }
        if (this.getPropertySet().getBooleanProperty(PropertyKey.enablePacketDebug).getValue().booleanValue()) {
            debugRingBuffer = new LinkedList<StringBuilder>();
            sender = new DebugBufferingPacketSender(sender, debugRingBuffer, this.propertySet.getIntegerProperty(PropertyKey.packetDebugBufferSize));
            messageReader = new DebugBufferingPacketReader(messageReader, debugRingBuffer, this.propertySet.getIntegerProperty(PropertyKey.packetDebugBufferSize));
        }
        messageReader = new MultiPacketReader(messageReader);
        Object object = this.packetReader;
        synchronized (object) {
            this.packetReader = messageReader;
            this.packetDebugRingBuffer = debugRingBuffer;
            this.setPacketSentTimeHolder(ttSender != null ? ttSender : new PacketSentTimeHolder(){});
        }
        object = this.packetSender;
        synchronized (object) {
            this.packetSender = sender;
            this.setPacketReceivedTimeHolder(ttReader != null ? ttReader : new PacketReceivedTimeHolder(){});
        }
    }

    @Override
    public NativeCapabilities readServerCapabilities() {
        NativePacketPayload buf = this.readMessage((NativePacketPayload)null);
        if (buf.isErrorPacket()) {
            this.rejectProtocol(buf);
        }
        NativeCapabilities serverCapabilities = new NativeCapabilities();
        serverCapabilities.setInitialHandshakePacket(buf);
        return serverCapabilities;
    }

    @Override
    public NativeServerSession getServerSession() {
        return this.serverSession;
    }

    @Override
    public void changeDatabase(String database) {
        if (database == null || database.length() == 0) {
            return;
        }
        try {
            this.sendCommand(this.commandBuilder.buildComInitDb(this.getSharedSendPacket(), database), false, 0);
        }
        catch (CJException ex) {
            if (this.getPropertySet().getBooleanProperty(PropertyKey.createDatabaseIfNotExist).getValue().booleanValue()) {
                this.sendCommand(this.commandBuilder.buildComQuery(this.getSharedSendPacket(), "CREATE DATABASE IF NOT EXISTS " + database), false, 0);
                this.sendCommand(this.commandBuilder.buildComInitDb(this.getSharedSendPacket(), database), false, 0);
            }
            throw ExceptionFactory.createCommunicationsException(this.getPropertySet(), this.serverSession, this.getPacketSentTimeHolder(), this.getPacketReceivedTimeHolder(), ex, this.getExceptionInterceptor());
        }
    }

    @Override
    public final NativePacketPayload readMessage(NativePacketPayload reuse) {
        try {
            NativePacketHeader header = this.packetReader.readHeader();
            NativePacketPayload buf = this.packetReader.readMessage(Optional.ofNullable(reuse), header);
            this.packetSequence = header.getMessageSequence();
            return buf;
        }
        catch (IOException ioEx) {
            throw ExceptionFactory.createCommunicationsException(this.propertySet, this.serverSession, this.getPacketSentTimeHolder(), this.getPacketReceivedTimeHolder(), ioEx, this.getExceptionInterceptor());
        }
        catch (OutOfMemoryError oom) {
            throw ExceptionFactory.createException(oom.getMessage(), "HY001", 0, false, oom, this.exceptionInterceptor);
        }
    }

    @Override
    public final void send(Message packet, int packetLen) {
        try {
            if (this.maxAllowedPacket.getValue() > 0 && packetLen > this.maxAllowedPacket.getValue()) {
                throw new CJPacketTooBigException(packetLen, this.maxAllowedPacket.getValue().intValue());
            }
            this.packetSequence = (byte)(this.packetSequence + 1);
            this.packetSender.send(packet.getByteBuffer(), packetLen, this.packetSequence);
            if (packet == this.sharedSendPacket) {
                this.reclaimLargeSharedSendPacket();
            }
        }
        catch (IOException ioEx) {
            throw ExceptionFactory.createCommunicationsException(this.getPropertySet(), this.serverSession, this.getPacketSentTimeHolder(), this.getPacketReceivedTimeHolder(), ioEx, this.getExceptionInterceptor());
        }
    }

    @Override
    public final NativePacketPayload sendCommand(Message queryPacket, boolean skipCheck, int timeoutMillis) {
        NativePacketPayload interceptedPacketPayload;
        byte command = queryPacket.getByteBuffer()[0];
        ++this.commandCount;
        if (this.queryInterceptors != null && (interceptedPacketPayload = (NativePacketPayload)this.invokeQueryInterceptorsPre(queryPacket, false)) != null) {
            return interceptedPacketPayload;
        }
        this.packetReader.resetMessageSequence();
        int oldTimeout = 0;
        if (timeoutMillis != 0) {
            try {
                oldTimeout = this.socketConnection.getMysqlSocket().getSoTimeout();
                this.socketConnection.getMysqlSocket().setSoTimeout(timeoutMillis);
            }
            catch (IOException e) {
                throw ExceptionFactory.createCommunicationsException(this.propertySet, this.serverSession, this.getPacketSentTimeHolder(), this.getPacketReceivedTimeHolder(), e, this.getExceptionInterceptor());
            }
        }
        try {
            int bytesLeft;
            this.checkForOutstandingStreamingData();
            this.serverSession.setStatusFlags(0, true);
            this.hadWarnings = false;
            this.setWarningCount(0);
            if (this.useCompression && (bytesLeft = this.socketConnection.getMysqlInput().available()) > 0) {
                this.socketConnection.getMysqlInput().skip(bytesLeft);
            }
            try {
                this.clearInputStream();
                this.packetSequence = (byte)-1;
                this.send(queryPacket, queryPacket.getPosition());
            }
            catch (CJException ex) {
                throw ex;
            }
            catch (Exception ex) {
                throw ExceptionFactory.createCommunicationsException(this.propertySet, this.serverSession, this.getPacketSentTimeHolder(), this.getPacketReceivedTimeHolder(), ex, this.getExceptionInterceptor());
            }
            NativePacketPayload returnPacket = null;
            if (!skipCheck) {
                if (command == 23 || command == 26) {
                    this.packetReader.resetMessageSequence();
                }
                returnPacket = this.checkErrorMessage(command);
                if (this.queryInterceptors != null) {
                    returnPacket = this.invokeQueryInterceptorsPost(queryPacket, returnPacket, false);
                }
            }
            NativePacketPayload nativePacketPayload = returnPacket;
            return nativePacketPayload;
        }
        catch (IOException ioEx) {
            this.serverSession.preserveOldTransactionState();
            throw ExceptionFactory.createCommunicationsException(this.propertySet, this.serverSession, this.getPacketSentTimeHolder(), this.getPacketReceivedTimeHolder(), ioEx, this.getExceptionInterceptor());
        }
        catch (CJException e) {
            this.serverSession.preserveOldTransactionState();
            throw e;
        }
        finally {
            if (timeoutMillis != 0) {
                try {
                    this.socketConnection.getMysqlSocket().setSoTimeout(oldTimeout);
                }
                catch (IOException e) {
                    throw ExceptionFactory.createCommunicationsException(this.propertySet, this.serverSession, this.getPacketSentTimeHolder(), this.getPacketReceivedTimeHolder(), e, this.getExceptionInterceptor());
                }
            }
        }
    }

    public void checkTransactionState() {
        int transState = this.serverSession.getTransactionState();
        if (transState == 3) {
            this.transactionManager.transactionCompleted();
        } else if (transState == 2) {
            this.transactionManager.transactionBegun();
        }
    }

    @Override
    public NativePacketPayload checkErrorMessage() {
        return this.checkErrorMessage(-1);
    }

    private NativePacketPayload checkErrorMessage(int command) {
        NativePacketPayload resultPacket = null;
        this.serverSession.setStatusFlags(0);
        try {
            resultPacket = this.readMessage(this.reusablePacket);
        }
        catch (CJException ex) {
            throw ex;
        }
        catch (Exception fallThru) {
            throw ExceptionFactory.createCommunicationsException(this.propertySet, this.serverSession, this.getPacketSentTimeHolder(), this.getPacketReceivedTimeHolder(), fallThru, this.getExceptionInterceptor());
        }
        this.checkErrorMessage(resultPacket);
        return resultPacket;
    }

    public void checkErrorMessage(NativePacketPayload resultPacket) {
        resultPacket.setPosition(0);
        byte statusCode = (byte)resultPacket.readInteger(NativeConstants.IntegerDataType.INT1);
        if (statusCode == -1) {
            int errno = 2000;
            errno = (int)resultPacket.readInteger(NativeConstants.IntegerDataType.INT2);
            String xOpen = null;
            String serverErrorMessage = resultPacket.readString(NativeConstants.StringSelfDataType.STRING_TERM, this.serverSession.getErrorMessageEncoding());
            if (serverErrorMessage.charAt(0) == '#') {
                if (serverErrorMessage.length() > 6) {
                    xOpen = serverErrorMessage.substring(1, 6);
                    serverErrorMessage = serverErrorMessage.substring(6);
                    if (xOpen.equals("HY000")) {
                        xOpen = MysqlErrorNumbers.mysqlToSqlState(errno);
                    }
                } else {
                    xOpen = MysqlErrorNumbers.mysqlToSqlState(errno);
                }
            } else {
                xOpen = MysqlErrorNumbers.mysqlToSqlState(errno);
            }
            this.clearInputStream();
            StringBuilder errorBuf = new StringBuilder();
            String xOpenErrorMessage = MysqlErrorNumbers.get(xOpen);
            boolean useOnlyServerErrorMessages = this.propertySet.getBooleanProperty(PropertyKey.useOnlyServerErrorMessages).getValue();
            if (!useOnlyServerErrorMessages && xOpenErrorMessage != null) {
                errorBuf.append(xOpenErrorMessage);
                errorBuf.append(Messages.getString("Protocol.0"));
            }
            errorBuf.append(serverErrorMessage);
            if (!useOnlyServerErrorMessages && xOpenErrorMessage != null) {
                errorBuf.append("\"");
            }
            this.appendDeadlockStatusInformation(this.session, xOpen, errorBuf);
            if (xOpen != null) {
                if (xOpen.startsWith("22")) {
                    throw new DataTruncationException(errorBuf.toString(), 0, true, false, 0, 0, errno);
                }
                if (errno == 1820) {
                    throw ExceptionFactory.createException(PasswordExpiredException.class, errorBuf.toString(), this.getExceptionInterceptor());
                }
                if (errno == 1862) {
                    throw ExceptionFactory.createException(ClosedOnExpiredPasswordException.class, errorBuf.toString(), this.getExceptionInterceptor());
                }
                if (errno == 4031) {
                    throw ExceptionFactory.createException(CJCommunicationsException.class, errorBuf.toString(), null, this.getExceptionInterceptor());
                }
            }
            throw ExceptionFactory.createException(errorBuf.toString(), xOpen, errno, false, null, this.getExceptionInterceptor());
        }
    }

    private void reclaimLargeSharedSendPacket() {
        if (this.sharedSendPacket != null && this.sharedSendPacket.getCapacity() > 0x100000) {
            this.sharedSendPacket = new NativePacketPayload(1024);
        }
    }

    public void clearInputStream() {
        try {
            int len;
            while ((len = this.socketConnection.getMysqlInput().available()) > 0 && this.socketConnection.getMysqlInput().skip(len) > 0L) {
            }
        }
        catch (IOException ioEx) {
            throw ExceptionFactory.createCommunicationsException(this.propertySet, this.serverSession, this.getPacketSentTimeHolder(), this.getPacketReceivedTimeHolder(), ioEx, this.getExceptionInterceptor());
        }
    }

    public void reclaimLargeReusablePacket() {
        if (this.reusablePacket != null && this.reusablePacket.getCapacity() > 0x100000) {
            this.reusablePacket = new NativePacketPayload(1024);
        }
    }

    public final <T extends Resultset> T sendQueryString(Query callingQuery, String query, String characterEncoding, int maxRows, boolean streamResults, ColumnDefinition cachedMetadata, ProtocolEntityFactory<T, NativePacketPayload> resultSetFactory) throws IOException {
        String statementComment = this.queryComment;
        if (this.propertySet.getBooleanProperty(PropertyKey.includeThreadNamesAsStatementComment).getValue().booleanValue()) {
            statementComment = (statementComment != null ? statementComment + ", " : "") + "java thread: " + Thread.currentThread().getName();
        }
        int packLength = 1 + query.length() * 4 + 2;
        byte[] commentAsBytes = null;
        if (statementComment != null) {
            commentAsBytes = StringUtils.getBytes(statementComment, characterEncoding);
            packLength += commentAsBytes.length;
            packLength += 6;
        }
        NativePacketPayload sendPacket = new NativePacketPayload(packLength);
        sendPacket.setPosition(0);
        sendPacket.writeInteger(NativeConstants.IntegerDataType.INT1, 3L);
        if (commentAsBytes != null) {
            sendPacket.writeBytes(NativeConstants.StringLengthDataType.STRING_FIXED, Constants.SLASH_STAR_SPACE_AS_BYTES);
            sendPacket.writeBytes(NativeConstants.StringLengthDataType.STRING_FIXED, commentAsBytes);
            sendPacket.writeBytes(NativeConstants.StringLengthDataType.STRING_FIXED, Constants.SPACE_STAR_SLASH_SPACE_AS_BYTES);
        }
        if (!this.platformDbCharsetMatches && StringUtils.startsWithIgnoreCaseAndWs(query, "LOAD DATA")) {
            sendPacket.writeBytes(NativeConstants.StringLengthDataType.STRING_FIXED, StringUtils.getBytes(query));
        } else {
            sendPacket.writeBytes(NativeConstants.StringLengthDataType.STRING_FIXED, StringUtils.getBytes(query, characterEncoding));
        }
        return this.sendQueryPacket(callingQuery, sendPacket, maxRows, streamResults, cachedMetadata, resultSetFactory);
    }

    public final <T extends Resultset> T sendQueryPacket(Query callingQuery, NativePacketPayload queryPacket, int maxRows, boolean streamResults, ColumnDefinition cachedMetadata, ProtocolEntityFactory<T, NativePacketPayload> resultSetFactory) throws IOException {
        long queryStartTime = this.getCurrentTimeNanosOrMillis();
        ++this.statementExecutionDepth;
        byte[] queryBuf = queryPacket.getByteBuffer();
        int oldPacketPosition = queryPacket.getPosition();
        LazyString query = new LazyString(queryBuf, 1, oldPacketPosition - 1);
        try {
            T interceptedResults;
            if (this.queryInterceptors != null && (interceptedResults = this.invokeQueryInterceptorsPre(query, callingQuery, false)) != null) {
                T t = interceptedResults;
                return t;
            }
            if (this.autoGenerateTestcaseScript) {
                StringBuilder debugBuf = new StringBuilder(query.length() + 32);
                this.generateQueryCommentBlock(debugBuf);
                debugBuf.append(query);
                debugBuf.append(';');
                TestUtils.dumpTestcaseQuery(debugBuf.toString());
            }
            NativePacketPayload resultPacket = this.sendCommand(queryPacket, false, 0);
            long queryEndTime = this.getCurrentTimeNanosOrMillis();
            long queryDuration = queryEndTime - queryStartTime;
            if (callingQuery != null) {
                callingQuery.setExecuteTime(queryDuration);
            }
            boolean queryWasSlow = this.logSlowQueries && (this.useAutoSlowLog ? this.metricsHolder.checkAbonormallyLongQuery(queryDuration) : queryDuration > (long)this.propertySet.getIntegerProperty(PropertyKey.slowQueryThresholdMillis).getValue().intValue());
            long fetchBeginTime = this.profileSQL ? this.getCurrentTimeNanosOrMillis() : 0L;
            T rs = this.readAllResults(maxRows, streamResults, resultPacket, false, cachedMetadata, resultSetFactory);
            if (this.profileSQL || queryWasSlow) {
                long fetchEndTime = this.profileSQL ? this.getCurrentTimeNanosOrMillis() : 0L;
                boolean truncated = oldPacketPosition > this.maxQuerySizeToLog.getValue();
                int extractPosition = truncated ? this.maxQuerySizeToLog.getValue() + 1 : oldPacketPosition;
                String extractedQuery = StringUtils.toString(queryBuf, 1, extractPosition - 1);
                if (truncated) {
                    extractedQuery = extractedQuery + Messages.getString("Protocol.2");
                }
                ProfilerEventHandler eventSink = this.session.getProfilerEventHandler();
                if (this.logSlowQueries) {
                    if (queryWasSlow) {
                        eventSink.processEvent((byte)6, this.session, callingQuery, (Resultset)rs, queryDuration, new Throwable(), Messages.getString("Protocol.SlowQuery", new Object[]{this.useAutoSlowLog ? " 95% of all queries " : String.valueOf(this.slowQueryThreshold), this.queryTimingUnits, queryDuration, extractedQuery}));
                        if (this.propertySet.getBooleanProperty(PropertyKey.explainSlowQueries).getValue().booleanValue()) {
                            if (oldPacketPosition < 0x100000) {
                                queryPacket.setPosition(1);
                                this.explainSlowQuery(query.toString(), extractedQuery);
                            } else {
                                this.log.logWarn(Messages.getString("Protocol.3", new Object[]{0x100000}));
                            }
                        }
                    }
                    if (this.serverSession.noGoodIndexUsed()) {
                        eventSink.processEvent((byte)6, this.session, callingQuery, (Resultset)rs, queryDuration, new Throwable(), Messages.getString("Protocol.4") + extractedQuery);
                    }
                    if (this.serverSession.noIndexUsed()) {
                        eventSink.processEvent((byte)6, this.session, callingQuery, (Resultset)rs, queryDuration, new Throwable(), Messages.getString("Protocol.5") + extractedQuery);
                    }
                    if (this.serverSession.queryWasSlow()) {
                        eventSink.processEvent((byte)6, this.session, callingQuery, (Resultset)rs, queryDuration, new Throwable(), Messages.getString("Protocol.ServerSlowQuery") + extractedQuery);
                    }
                }
                if (this.profileSQL) {
                    eventSink.processEvent((byte)3, this.session, callingQuery, (Resultset)rs, queryDuration, new Throwable(), extractedQuery);
                    eventSink.processEvent((byte)5, this.session, callingQuery, (Resultset)rs, fetchEndTime - fetchBeginTime, new Throwable(), null);
                }
            }
            if (this.hadWarnings) {
                this.scanForAndThrowDataTruncation();
            }
            if (this.queryInterceptors != null) {
                rs = this.invokeQueryInterceptorsPost(query, callingQuery, rs, false);
            }
            T t = rs;
            return t;
        }
        catch (CJException sqlEx) {
            if (this.queryInterceptors != null) {
                this.invokeQueryInterceptorsPost(query, callingQuery, null, false);
            }
            if (callingQuery != null) {
                callingQuery.checkCancelTimeout();
            }
            throw sqlEx;
        }
        finally {
            --this.statementExecutionDepth;
        }
    }

    public <T extends Resultset> T invokeQueryInterceptorsPre(Supplier<String> sql, Query interceptedQuery, boolean forceExecute) {
        T previousResultSet = null;
        int s = this.queryInterceptors.size();
        for (int i = 0; i < s; ++i) {
            Object interceptedResultSet;
            boolean shouldExecute;
            QueryInterceptor interceptor = this.queryInterceptors.get(i);
            boolean executeTopLevelOnly = interceptor.executeTopLevelOnly();
            boolean bl = shouldExecute = executeTopLevelOnly && (this.statementExecutionDepth == 1 || forceExecute) || !executeTopLevelOnly;
            if (!shouldExecute || (interceptedResultSet = interceptor.preProcess(sql, interceptedQuery)) == null) continue;
            previousResultSet = interceptedResultSet;
        }
        return previousResultSet;
    }

    public <M extends Message> M invokeQueryInterceptorsPre(M queryPacket, boolean forceExecute) {
        M previousPacketPayload = null;
        int s = this.queryInterceptors.size();
        for (int i = 0; i < s; ++i) {
            QueryInterceptor interceptor = this.queryInterceptors.get(i);
            M interceptedPacketPayload = interceptor.preProcess(queryPacket);
            if (interceptedPacketPayload == null) continue;
            previousPacketPayload = interceptedPacketPayload;
        }
        return previousPacketPayload;
    }

    public <T extends Resultset> T invokeQueryInterceptorsPost(Supplier<String> sql, Query interceptedQuery, T originalResultSet, boolean forceExecute) {
        int s = this.queryInterceptors.size();
        for (int i = 0; i < s; ++i) {
            T interceptedResultSet;
            boolean shouldExecute;
            QueryInterceptor interceptor = this.queryInterceptors.get(i);
            boolean executeTopLevelOnly = interceptor.executeTopLevelOnly();
            boolean bl = shouldExecute = executeTopLevelOnly && (this.statementExecutionDepth == 1 || forceExecute) || !executeTopLevelOnly;
            if (!shouldExecute || (interceptedResultSet = interceptor.postProcess(sql, interceptedQuery, originalResultSet, this.serverSession)) == null) continue;
            originalResultSet = interceptedResultSet;
        }
        return originalResultSet;
    }

    public <M extends Message> M invokeQueryInterceptorsPost(M queryPacket, M originalResponsePacket, boolean forceExecute) {
        int s = this.queryInterceptors.size();
        for (int i = 0; i < s; ++i) {
            QueryInterceptor interceptor = this.queryInterceptors.get(i);
            M interceptedPacketPayload = interceptor.postProcess(queryPacket, originalResponsePacket);
            if (interceptedPacketPayload == null) continue;
            originalResponsePacket = interceptedPacketPayload;
        }
        return originalResponsePacket;
    }

    public long getCurrentTimeNanosOrMillis() {
        return this.useNanosForElapsedTime ? TimeUtil.getCurrentTimeNanosOrMillis() : System.currentTimeMillis();
    }

    public boolean hadWarnings() {
        return this.hadWarnings;
    }

    public void setHadWarnings(boolean hadWarnings) {
        this.hadWarnings = hadWarnings;
    }

    public void explainSlowQuery(String query, String truncatedQuery) {
        if (StringUtils.startsWithIgnoreCaseAndWs(truncatedQuery, EXPLAINABLE_STATEMENT) || this.versionMeetsMinimum(5, 6, 3) && StringUtils.startsWithIgnoreCaseAndWs(truncatedQuery, EXPLAINABLE_STATEMENT_EXTENSION) != -1) {
            try {
                NativePacketPayload resultPacket = this.sendCommand(this.commandBuilder.buildComQuery(this.getSharedSendPacket(), "EXPLAIN " + query), false, 0);
                Resultset rs = this.readAllResults(-1, false, resultPacket, false, null, new ResultsetFactory(Resultset.Type.FORWARD_ONLY, null));
                StringBuilder explainResults = new StringBuilder(Messages.getString("Protocol.6"));
                explainResults.append(truncatedQuery);
                explainResults.append(Messages.getString("Protocol.7"));
                this.appendResultSetSlashGStyle(explainResults, rs);
                this.log.logWarn(explainResults.toString());
            }
            catch (CJException sqlEx) {
                throw sqlEx;
            }
            catch (Exception ex) {
                throw ExceptionFactory.createException(ex.getMessage(), ex, this.getExceptionInterceptor());
            }
        }
    }

    public final void skipPacket() {
        try {
            int packetLength = this.packetReader.readHeader().getMessageSize();
            this.socketConnection.getMysqlInput().skipFully(packetLength);
        }
        catch (IOException ioEx) {
            throw ExceptionFactory.createCommunicationsException(this.propertySet, this.serverSession, this.getPacketSentTimeHolder(), this.getPacketReceivedTimeHolder(), ioEx, this.getExceptionInterceptor());
        }
    }

    public final void quit() {
        try {
            try {
                if (!ExportControlled.isSSLEstablished(this.socketConnection.getMysqlSocket()) && !this.socketConnection.getMysqlSocket().isClosed()) {
                    try {
                        this.socketConnection.getMysqlSocket().shutdownInput();
                    }
                    catch (UnsupportedOperationException unsupportedOperationException) {}
                }
            }
            catch (IOException iOException) {
                // empty catch block
            }
            this.packetSequence = (byte)-1;
            NativePacketPayload packet = new NativePacketPayload(1);
            this.send(this.commandBuilder.buildComQuit(packet), packet.getPosition());
        }
        finally {
            this.socketConnection.forceClose();
            this.localInfileInputStream = null;
        }
    }

    public NativePacketPayload getSharedSendPacket() {
        if (this.sharedSendPacket == null) {
            this.sharedSendPacket = new NativePacketPayload(1024);
        }
        this.sharedSendPacket.setPosition(0);
        return this.sharedSendPacket;
    }

    private void calculateSlowQueryThreshold() {
        this.slowQueryThreshold = this.propertySet.getIntegerProperty(PropertyKey.slowQueryThresholdMillis).getValue().intValue();
        if (this.propertySet.getBooleanProperty(PropertyKey.useNanosForElapsedTime).getValue().booleanValue()) {
            long nanosThreshold = this.propertySet.getLongProperty(PropertyKey.slowQueryThresholdNanos).getValue();
            this.slowQueryThreshold = nanosThreshold != 0L ? nanosThreshold : (this.slowQueryThreshold *= 1000000L);
        }
    }

    @Override
    public void changeUser(String user, String password, String database) {
        this.packetSequence = (byte)-1;
        this.packetSender = this.packetSender.undecorateAll();
        this.packetReader = this.packetReader.undecorateAll();
        this.authProvider.changeUser(this.serverSession, user, password, database);
    }

    public void checkForCharsetMismatch() {
        String characterEncoding = this.propertySet.getStringProperty(PropertyKey.characterEncoding).getValue();
        if (characterEncoding != null) {
            String encodingToCheck = jvmPlatformCharset;
            if (encodingToCheck == null) {
                encodingToCheck = Constants.PLATFORM_ENCODING;
            }
            this.platformDbCharsetMatches = encodingToCheck == null ? false : encodingToCheck.equals(characterEncoding);
        }
    }

    protected boolean useNanosForElapsedTime() {
        return this.useNanosForElapsedTime;
    }

    public long getSlowQueryThreshold() {
        return this.slowQueryThreshold;
    }

    public int getCommandCount() {
        return this.commandCount;
    }

    public void setQueryInterceptors(List<QueryInterceptor> queryInterceptors) {
        this.queryInterceptors = queryInterceptors.isEmpty() ? null : queryInterceptors;
    }

    public List<QueryInterceptor> getQueryInterceptors() {
        return this.queryInterceptors;
    }

    public void setSocketTimeout(int milliseconds) {
        try {
            Socket soc = this.socketConnection.getMysqlSocket();
            if (soc != null) {
                soc.setSoTimeout(milliseconds);
            }
        }
        catch (IOException e) {
            throw ExceptionFactory.createException(WrongArgumentException.class, Messages.getString("Protocol.8"), e, this.getExceptionInterceptor());
        }
    }

    public void releaseResources() {
        if (this.compressedPacketSender != null) {
            this.compressedPacketSender.stop();
        }
    }

    @Override
    public void connect(String user, String password, String database) {
        this.beforeHandshake();
        this.authProvider.connect(this.serverSession, user, password, database);
    }

    protected boolean isDataAvailable() {
        try {
            return this.socketConnection.getMysqlInput().available() > 0;
        }
        catch (IOException ioEx) {
            throw ExceptionFactory.createCommunicationsException(this.propertySet, this.serverSession, this.getPacketSentTimeHolder(), this.getPacketReceivedTimeHolder(), ioEx, this.getExceptionInterceptor());
        }
    }

    public NativePacketPayload getReusablePacket() {
        return this.reusablePacket;
    }

    public int getWarningCount() {
        return this.warningCount;
    }

    public void setWarningCount(int warningCount) {
        this.warningCount = warningCount;
    }

    public void dumpPacketRingBuffer() {
        LinkedList localPacketDebugRingBuffer = this.packetDebugRingBuffer;
        if (localPacketDebugRingBuffer != null) {
            StringBuilder dumpBuffer = new StringBuilder();
            dumpBuffer.append("Last " + localPacketDebugRingBuffer.size() + " packets received from server, from oldest->newest:\n");
            dumpBuffer.append("\n");
            Iterator ringBufIter = localPacketDebugRingBuffer.iterator();
            while (ringBufIter.hasNext()) {
                dumpBuffer.append((CharSequence)ringBufIter.next());
                dumpBuffer.append("\n");
            }
            this.log.logTrace(dumpBuffer.toString());
        }
    }

    public boolean doesPlatformDbCharsetMatches() {
        return this.platformDbCharsetMatches;
    }

    @Override
    public String getPasswordCharacterEncoding() {
        String encoding = this.propertySet.getStringProperty(PropertyKey.passwordCharacterEncoding).getStringValue();
        if (encoding != null) {
            return encoding;
        }
        encoding = this.propertySet.getStringProperty(PropertyKey.characterEncoding).getValue();
        if (encoding != null) {
            return encoding;
        }
        return "UTF-8";
    }

    @Override
    public boolean versionMeetsMinimum(int major, int minor, int subminor) {
        return this.serverSession.getServerVersion().meetsMinimum(new ServerVersion(major, minor, subminor));
    }

    public static MysqlType findMysqlType(PropertySet propertySet, int mysqlTypeId, short colFlag, long length, LazyString tableName, LazyString originalTableName, int collationIndex, String encoding) {
        boolean isImplicitTemporaryTable;
        boolean isUnsigned = (colFlag & 0x20) > 0;
        boolean isFromFunction = originalTableName.length() == 0;
        boolean isBinary = (colFlag & 0x80) > 0;
        boolean bl = isImplicitTemporaryTable = tableName.length() > 0 && tableName.toString().startsWith("#sql_");
        boolean isOpaqueBinary = isBinary && collationIndex == 63 && (mysqlTypeId == 254 || mysqlTypeId == 253 || mysqlTypeId == 15) ? !isImplicitTemporaryTable : "binary".equalsIgnoreCase(encoding);
        switch (mysqlTypeId) {
            case 0: 
            case 246: {
                return isUnsigned ? MysqlType.DECIMAL_UNSIGNED : MysqlType.DECIMAL;
            }
            case 1: {
                if (!isUnsigned && length == 1L && propertySet.getBooleanProperty(PropertyKey.tinyInt1isBit).getValue().booleanValue()) {
                    if (propertySet.getBooleanProperty(PropertyKey.transformedBitIsBoolean).getValue().booleanValue()) {
                        return MysqlType.BOOLEAN;
                    }
                    return MysqlType.BIT;
                }
                return isUnsigned ? MysqlType.TINYINT_UNSIGNED : MysqlType.TINYINT;
            }
            case 2: {
                return isUnsigned ? MysqlType.SMALLINT_UNSIGNED : MysqlType.SMALLINT;
            }
            case 3: {
                return isUnsigned ? MysqlType.INT_UNSIGNED : MysqlType.INT;
            }
            case 4: {
                return isUnsigned ? MysqlType.FLOAT_UNSIGNED : MysqlType.FLOAT;
            }
            case 5: {
                return isUnsigned ? MysqlType.DOUBLE_UNSIGNED : MysqlType.DOUBLE;
            }
            case 6: {
                return MysqlType.NULL;
            }
            case 7: {
                return MysqlType.TIMESTAMP;
            }
            case 8: {
                return isUnsigned ? MysqlType.BIGINT_UNSIGNED : MysqlType.BIGINT;
            }
            case 9: {
                return isUnsigned ? MysqlType.MEDIUMINT_UNSIGNED : MysqlType.MEDIUMINT;
            }
            case 10: {
                return MysqlType.DATE;
            }
            case 11: {
                return MysqlType.TIME;
            }
            case 12: {
                return MysqlType.DATETIME;
            }
            case 13: {
                return MysqlType.YEAR;
            }
            case 15: 
            case 253: {
                if (!(!isOpaqueBinary || isFromFunction && propertySet.getBooleanProperty(PropertyKey.functionsNeverReturnBlobs).getValue().booleanValue())) {
                    return MysqlType.VARBINARY;
                }
                return MysqlType.VARCHAR;
            }
            case 16: {
                return MysqlType.BIT;
            }
            case 245: {
                return MysqlType.JSON;
            }
            case 247: {
                return MysqlType.ENUM;
            }
            case 248: {
                return MysqlType.SET;
            }
            case 249: {
                if (!isBinary || collationIndex != 63 || propertySet.getBooleanProperty(PropertyKey.blobsAreStrings).getValue().booleanValue() || isFromFunction && propertySet.getBooleanProperty(PropertyKey.functionsNeverReturnBlobs).getValue().booleanValue()) {
                    return MysqlType.TINYTEXT;
                }
                return MysqlType.TINYBLOB;
            }
            case 250: {
                if (!isBinary || collationIndex != 63 || propertySet.getBooleanProperty(PropertyKey.blobsAreStrings).getValue().booleanValue() || isFromFunction && propertySet.getBooleanProperty(PropertyKey.functionsNeverReturnBlobs).getValue().booleanValue()) {
                    return MysqlType.MEDIUMTEXT;
                }
                return MysqlType.MEDIUMBLOB;
            }
            case 251: {
                if (!isBinary || collationIndex != 63 || propertySet.getBooleanProperty(PropertyKey.blobsAreStrings).getValue().booleanValue() || isFromFunction && propertySet.getBooleanProperty(PropertyKey.functionsNeverReturnBlobs).getValue().booleanValue()) {
                    return MysqlType.LONGTEXT;
                }
                return MysqlType.LONGBLOB;
            }
            case 252: {
                int newMysqlTypeId = mysqlTypeId;
                if (length <= MysqlType.TINYBLOB.getPrecision()) {
                    newMysqlTypeId = 249;
                } else {
                    if (length <= MysqlType.BLOB.getPrecision()) {
                        if (!isBinary || collationIndex != 63 || propertySet.getBooleanProperty(PropertyKey.blobsAreStrings).getValue().booleanValue() || isFromFunction && propertySet.getBooleanProperty(PropertyKey.functionsNeverReturnBlobs).getValue().booleanValue()) {
                            newMysqlTypeId = 15;
                            return MysqlType.TEXT;
                        }
                        return MysqlType.BLOB;
                    }
                    newMysqlTypeId = length <= MysqlType.MEDIUMBLOB.getPrecision() ? 250 : 251;
                }
                return NativeProtocol.findMysqlType(propertySet, newMysqlTypeId, colFlag, length, tableName, originalTableName, collationIndex, encoding);
            }
            case 254: {
                if (isOpaqueBinary && !propertySet.getBooleanProperty(PropertyKey.blobsAreStrings).getValue().booleanValue()) {
                    return MysqlType.BINARY;
                }
                return MysqlType.CHAR;
            }
            case 255: {
                return MysqlType.GEOMETRY;
            }
        }
        return MysqlType.UNKNOWN;
    }

    @Override
    public <T extends ProtocolEntity> T read(Class<T> requiredClass, ProtocolEntityFactory<T, NativePacketPayload> protocolEntityFactory) throws IOException {
        ProtocolEntityReader<? extends ProtocolEntity, ? extends Message> sr = this.PROTOCOL_ENTITY_CLASS_TO_TEXT_READER.get(requiredClass);
        if (sr == null) {
            throw ExceptionFactory.createException(FeatureNotAvailableException.class, "ProtocolEntityReader isn't available for class " + requiredClass);
        }
        return (T)sr.read(protocolEntityFactory);
    }

    @Override
    public <T extends ProtocolEntity> T read(Class<Resultset> requiredClass, int maxRows, boolean streamResults, NativePacketPayload resultPacket, boolean isBinaryEncoded, ColumnDefinition metadata, ProtocolEntityFactory<T, NativePacketPayload> protocolEntityFactory) throws IOException {
        ProtocolEntityReader<? extends ProtocolEntity, ? extends Message> sr;
        ProtocolEntityReader<? extends ProtocolEntity, ? extends Message> protocolEntityReader = sr = isBinaryEncoded ? this.PROTOCOL_ENTITY_CLASS_TO_BINARY_READER.get(requiredClass) : this.PROTOCOL_ENTITY_CLASS_TO_TEXT_READER.get(requiredClass);
        if (sr == null) {
            throw ExceptionFactory.createException(FeatureNotAvailableException.class, "ProtocolEntityReader isn't available for class " + requiredClass);
        }
        return (T)sr.read(maxRows, streamResults, resultPacket, metadata, protocolEntityFactory);
    }

    public <T extends ProtocolEntity> T readNextResultset(T currentProtocolEntity, int maxRows, boolean streamResults, boolean isBinaryEncoded, ProtocolEntityFactory<T, NativePacketPayload> resultSetFactory) throws IOException {
        T result = null;
        if (Resultset.class.isAssignableFrom(currentProtocolEntity.getClass()) && this.serverSession.useMultiResults() && this.serverSession.hasMoreResults()) {
            T currentResultSet = currentProtocolEntity;
            do {
                NativePacketPayload fieldPacket = this.checkErrorMessage();
                fieldPacket.setPosition(0);
                T newResultSet = this.read(Resultset.class, maxRows, streamResults, fieldPacket, isBinaryEncoded, (ColumnDefinition)null, resultSetFactory);
                ((Resultset)currentResultSet).setNextResultset((Resultset)newResultSet);
                currentResultSet = newResultSet;
                if (result != null) continue;
                result = currentResultSet;
            } while (streamResults && this.serverSession.hasMoreResults() && !((Resultset)currentResultSet).hasRows());
        }
        return result;
    }

    public <T extends Resultset> T readAllResults(int maxRows, boolean streamResults, NativePacketPayload resultPacket, boolean isBinaryEncoded, ColumnDefinition metadata, ProtocolEntityFactory<T, NativePacketPayload> resultSetFactory) throws IOException {
        resultPacket.setPosition(0);
        Resultset topLevelResultSet = (Resultset)this.read(Resultset.class, maxRows, streamResults, resultPacket, isBinaryEncoded, metadata, resultSetFactory);
        if (this.serverSession.hasMoreResults()) {
            Resultset currentResultSet = topLevelResultSet;
            if (streamResults) {
                currentResultSet = this.readNextResultset(currentResultSet, maxRows, true, isBinaryEncoded, resultSetFactory);
            } else {
                while (this.serverSession.hasMoreResults()) {
                    currentResultSet = this.readNextResultset(currentResultSet, maxRows, false, isBinaryEncoded, resultSetFactory);
                }
                this.clearInputStream();
            }
        }
        if (this.hadWarnings) {
            this.scanForAndThrowDataTruncation();
        }
        this.reclaimLargeReusablePacket();
        return (T)topLevelResultSet;
    }

    public final <T> T readServerStatusForResultSets(NativePacketPayload rowPacket, boolean saveOldStatus) {
        OkPacket result = null;
        if (rowPacket.isEOFPacket()) {
            rowPacket.readInteger(NativeConstants.IntegerDataType.INT1);
            this.warningCount = (int)rowPacket.readInteger(NativeConstants.IntegerDataType.INT2);
            if (this.warningCount > 0) {
                this.hadWarnings = true;
            }
            this.serverSession.setStatusFlags((int)rowPacket.readInteger(NativeConstants.IntegerDataType.INT2), saveOldStatus);
            this.checkTransactionState();
        } else {
            OkPacket ok;
            result = ok = OkPacket.parse(rowPacket, this.serverSession.getErrorMessageEncoding());
            this.serverSession.setStatusFlags(ok.getStatusFlags(), saveOldStatus);
            this.checkTransactionState();
            this.warningCount = ok.getWarningCount();
            if (this.warningCount > 0) {
                this.hadWarnings = true;
            }
        }
        return (T)result;
    }

    @Override
    public <T extends QueryResult> T readQueryResult(ResultBuilder<T> resultBuilder) {
        throw ExceptionFactory.createException(CJOperationNotSupportedException.class, "Not supported");
    }

    @Override
    public InputStream getLocalInfileInputStream() {
        return this.localInfileInputStream;
    }

    @Override
    public void setLocalInfileInputStream(InputStream stream) {
        this.localInfileInputStream = stream;
    }

    public final NativePacketPayload sendFileToServer(String fileName) {
        NativePacketPayload filePacket = this.loadFileBufRef == null ? null : this.loadFileBufRef.get();
        int bigPacketLength = Math.min(this.maxAllowedPacket.getValue() - 12, this.alignPacketSize(this.maxAllowedPacket.getValue() - 16, 4096) - 12);
        int oneMeg = 0x100000;
        int smallerPacketSizeAligned = Math.min(oneMeg - 12, this.alignPacketSize(oneMeg - 16, 4096) - 12);
        int packetLength = Math.min(smallerPacketSizeAligned, bigPacketLength);
        if (filePacket == null) {
            try {
                filePacket = new NativePacketPayload(packetLength);
                this.loadFileBufRef = new SoftReference<NativePacketPayload>(filePacket);
            }
            catch (OutOfMemoryError oom) {
                throw ExceptionFactory.createException(Messages.getString("MysqlIO.111", new Object[]{packetLength}), "HY001", 0, false, oom, this.exceptionInterceptor);
            }
        }
        filePacket.setPosition(0);
        byte[] fileBuf = new byte[packetLength];
        BufferedInputStream fileIn = null;
        try {
            fileIn = this.getFileStream(fileName);
            int bytesRead = 0;
            while ((bytesRead = fileIn.read(fileBuf)) != -1) {
                filePacket.setPosition(0);
                filePacket.writeBytes(NativeConstants.StringLengthDataType.STRING_FIXED, fileBuf, 0, bytesRead);
                this.send(filePacket, filePacket.getPosition());
            }
        }
        catch (IOException ioEx) {
            boolean isParanoid = this.propertySet.getBooleanProperty(PropertyKey.paranoid).getValue();
            StringBuilder messageBuf = new StringBuilder(Messages.getString("MysqlIO.62"));
            if (fileName != null && !isParanoid) {
                messageBuf.append("'");
                messageBuf.append(fileName);
                messageBuf.append("'");
            }
            messageBuf.append(Messages.getString("MysqlIO.63"));
            if (!isParanoid) {
                messageBuf.append(Messages.getString("MysqlIO.64"));
                messageBuf.append(Util.stackTraceToString(ioEx));
            }
            throw ExceptionFactory.createException(messageBuf.toString(), ioEx, this.exceptionInterceptor);
        }
        finally {
            if (fileIn != null) {
                try {
                    fileIn.close();
                }
                catch (Exception ex) {
                    throw ExceptionFactory.createException(Messages.getString("MysqlIO.65"), ex, this.exceptionInterceptor);
                }
                fileIn = null;
            } else {
                filePacket.setPosition(0);
                this.send(filePacket, filePacket.getPosition());
                this.checkErrorMessage();
            }
        }
        filePacket.setPosition(0);
        this.send(filePacket, filePacket.getPosition());
        return this.checkErrorMessage();
    }

    private BufferedInputStream getFileStream(String fileName) throws IOException {
        Path filePath;
        Path safePath;
        RuntimeProperty<Boolean> allowLoadLocalInfile = this.propertySet.getBooleanProperty(PropertyKey.allowLoadLocalInfile);
        RuntimeProperty<String> allowLoadLocaInfileInPath = this.propertySet.getStringProperty(PropertyKey.allowLoadLocalInfileInPath);
        RuntimeProperty<Boolean> allowUrlInLocalInfile = this.propertySet.getBooleanProperty(PropertyKey.allowUrlInLocalInfile);
        if (!allowLoadLocalInfile.getValue().booleanValue() && !allowLoadLocaInfileInPath.isExplicitlySet()) {
            throw ExceptionFactory.createException(Messages.getString("MysqlIO.LoadDataLocalNotAllowed"), this.exceptionInterceptor);
        }
        if (allowLoadLocalInfile.getValue().booleanValue()) {
            InputStream hookedStream = this.getLocalInfileInputStream();
            if (hookedStream != null) {
                return new BufferedInputStream(hookedStream);
            }
            if (allowUrlInLocalInfile.getValue().booleanValue() && fileName.indexOf(58) != -1) {
                try {
                    URL urlFromFileName = new URL(fileName);
                    return new BufferedInputStream(urlFromFileName.openStream());
                }
                catch (MalformedURLException urlFromFileName) {
                    // empty catch block
                }
            }
            return new BufferedInputStream(new FileInputStream(fileName));
        }
        String safePathValue = allowLoadLocaInfileInPath.getValue();
        if (safePathValue.length() == 0) {
            throw ExceptionFactory.createException(Messages.getString("MysqlIO.60", new Object[]{safePathValue, PropertyKey.allowLoadLocalInfileInPath.getKeyName()}), this.exceptionInterceptor);
        }
        try {
            safePath = Paths.get(safePathValue, new String[0]).toRealPath(new LinkOption[0]);
        }
        catch (IOException | InvalidPathException e) {
            throw ExceptionFactory.createException(Messages.getString("MysqlIO.60", new Object[]{safePathValue, PropertyKey.allowLoadLocalInfileInPath.getKeyName()}), e, this.exceptionInterceptor);
        }
        if (allowUrlInLocalInfile.getValue().booleanValue()) {
            try {
                URL urlFromFileName = new URL(fileName);
                if (!urlFromFileName.getProtocol().equalsIgnoreCase("file")) {
                    throw ExceptionFactory.createException(Messages.getString("MysqlIO.66", new Object[]{urlFromFileName.getProtocol()}), this.exceptionInterceptor);
                }
                try {
                    InetAddress addr = InetAddress.getByName(urlFromFileName.getHost());
                    if (!addr.isLoopbackAddress()) {
                        throw ExceptionFactory.createException(Messages.getString("MysqlIO.67", new Object[]{urlFromFileName.getHost()}), this.exceptionInterceptor);
                    }
                }
                catch (UnknownHostException e) {
                    throw ExceptionFactory.createException(Messages.getString("MysqlIO.68", new Object[]{fileName}), e, this.exceptionInterceptor);
                }
                Path filePath2 = null;
                try {
                    filePath2 = Paths.get(urlFromFileName.toURI()).toRealPath(new LinkOption[0]);
                }
                catch (InvalidPathException e) {
                    String pathString = urlFromFileName.getPath();
                    if (pathString.indexOf(58) != -1 && (pathString.startsWith("/") || pathString.startsWith("\\"))) {
                        pathString = pathString.replaceFirst("^[/\\\\]*", "");
                    }
                    filePath2 = Paths.get(pathString, new String[0]).toRealPath(new LinkOption[0]);
                }
                catch (IllegalArgumentException e) {
                    filePath2 = Paths.get(urlFromFileName.getPath(), new String[0]).toRealPath(new LinkOption[0]);
                }
                if (!filePath2.startsWith(safePath)) {
                    throw ExceptionFactory.createException(Messages.getString("MysqlIO.61", new Object[]{filePath2, safePath}), this.exceptionInterceptor);
                }
                return new BufferedInputStream(urlFromFileName.openStream());
            }
            catch (MalformedURLException | URISyntaxException urlFromFileName) {
                // empty catch block
            }
        }
        if (!(filePath = Paths.get(fileName, new String[0]).toRealPath(new LinkOption[0])).startsWith(safePath)) {
            throw ExceptionFactory.createException(Messages.getString("MysqlIO.61", new Object[]{filePath, safePath}), this.exceptionInterceptor);
        }
        return new BufferedInputStream(new FileInputStream(filePath.toFile()));
    }

    private int alignPacketSize(int a, int l) {
        return a + l - 1 & ~(l - 1);
    }

    public ResultsetRows getStreamingData() {
        return this.streamingData;
    }

    public void setStreamingData(ResultsetRows streamingData) {
        this.streamingData = streamingData;
    }

    public void checkForOutstandingStreamingData() {
        if (this.streamingData != null) {
            boolean shouldClobber = this.propertySet.getBooleanProperty(PropertyKey.clobberStreamingResults).getValue();
            if (!shouldClobber) {
                throw ExceptionFactory.createException(Messages.getString("MysqlIO.39") + this.streamingData + Messages.getString("MysqlIO.40") + Messages.getString("MysqlIO.41") + Messages.getString("MysqlIO.42"), this.exceptionInterceptor);
            }
            this.streamingData.getOwner().closeOwner(false);
            this.clearInputStream();
        }
    }

    public void unsetStreamingData(ResultsetRows streamer) {
        if (this.streamingData == null) {
            throw ExceptionFactory.createException(Messages.getString("MysqlIO.17") + streamer + Messages.getString("MysqlIO.18"), this.exceptionInterceptor);
        }
        if (streamer == this.streamingData) {
            this.streamingData = null;
        }
    }

    public void scanForAndThrowDataTruncation() {
        if (this.streamingData == null && this.propertySet.getBooleanProperty(PropertyKey.jdbcCompliantTruncation).getValue().booleanValue() && this.getWarningCount() > 0) {
            int warningCountOld = this.getWarningCount();
            this.convertShowWarningsToSQLWarnings(this.getWarningCount(), true);
            this.setWarningCount(warningCountOld);
        }
    }

    public StringBuilder generateQueryCommentBlock(StringBuilder buf) {
        buf.append("/* conn id ");
        buf.append(this.getServerSession().getCapabilities().getThreadId());
        buf.append(" clock: ");
        buf.append(System.currentTimeMillis());
        buf.append(" */ ");
        return buf;
    }

    public BaseMetricsHolder getMetricsHolder() {
        return this.metricsHolder;
    }

    @Override
    public String getQueryComment() {
        return this.queryComment;
    }

    @Override
    public void setQueryComment(String comment) {
        this.queryComment = comment;
    }

    private void appendDeadlockStatusInformation(Session sess, String xOpen, StringBuilder errorBuf) {
        if (sess.getPropertySet().getBooleanProperty(PropertyKey.includeInnodbStatusInDeadlockExceptions).getValue().booleanValue() && xOpen != null && (xOpen.startsWith("40") || xOpen.startsWith("41")) && this.getStreamingData() == null) {
            try {
                NativePacketPayload resultPacket = this.sendCommand(this.commandBuilder.buildComQuery(this.getSharedSendPacket(), "SHOW ENGINE INNODB STATUS"), false, 0);
                Resultset rs = this.readAllResults(-1, false, resultPacket, false, null, new ResultsetFactory(Resultset.Type.FORWARD_ONLY, null));
                int colIndex = 0;
                Field f = null;
                for (int i = 0; i < rs.getColumnDefinition().getFields().length; ++i) {
                    f = rs.getColumnDefinition().getFields()[i];
                    if (!"Status".equals(f.getName())) continue;
                    colIndex = i;
                    break;
                }
                StringValueFactory vf = new StringValueFactory(this.propertySet);
                Row r = (Row)rs.getRows().next();
                if (r != null) {
                    errorBuf.append("\n\n").append(r.getValue(colIndex, vf));
                } else {
                    errorBuf.append("\n\n").append(Messages.getString("MysqlIO.NoInnoDBStatusFound"));
                }
            }
            catch (CJException | IOException ex) {
                errorBuf.append("\n\n").append(Messages.getString("MysqlIO.InnoDBStatusFailed")).append("\n\n").append(Util.stackTraceToString(ex));
            }
        }
        if (sess.getPropertySet().getBooleanProperty(PropertyKey.includeThreadDumpInDeadlockExceptions).getValue().booleanValue()) {
            errorBuf.append("\n\n*** Java threads running at time of deadlock ***\n\n");
            ThreadMXBean threadMBean = ManagementFactory.getThreadMXBean();
            long[] threadIds = threadMBean.getAllThreadIds();
            ThreadInfo[] threads = threadMBean.getThreadInfo(threadIds, Integer.MAX_VALUE);
            ArrayList<ThreadInfo> activeThreads = new ArrayList<ThreadInfo>();
            for (ThreadInfo info : threads) {
                if (info == null) continue;
                activeThreads.add(info);
            }
            for (ThreadInfo threadInfo : activeThreads) {
                StackTraceElement[] stackTrace;
                errorBuf.append('\"').append(threadInfo.getThreadName()).append("\" tid=").append(threadInfo.getThreadId()).append(" ").append((Object)threadInfo.getThreadState());
                if (threadInfo.getLockName() != null) {
                    errorBuf.append(" on lock=").append(threadInfo.getLockName());
                }
                if (threadInfo.isSuspended()) {
                    errorBuf.append(" (suspended)");
                }
                if (threadInfo.isInNative()) {
                    errorBuf.append(" (running in native)");
                }
                if ((stackTrace = threadInfo.getStackTrace()).length > 0) {
                    errorBuf.append(" in ");
                    errorBuf.append(stackTrace[0].getClassName()).append(".");
                    errorBuf.append(stackTrace[0].getMethodName()).append("()");
                }
                errorBuf.append("\n");
                if (threadInfo.getLockOwnerName() != null) {
                    errorBuf.append("\t owned by ").append(threadInfo.getLockOwnerName()).append(" Id=").append(threadInfo.getLockOwnerId()).append("\n");
                }
                for (int j = 0; j < stackTrace.length; ++j) {
                    StackTraceElement ste = stackTrace[j];
                    errorBuf.append("\tat ").append(ste.toString()).append("\n");
                }
            }
        }
    }

    private StringBuilder appendResultSetSlashGStyle(StringBuilder appendTo, Resultset rs) {
        Row r;
        Field[] fields = rs.getColumnDefinition().getFields();
        int maxWidth = 0;
        for (int i = 0; i < fields.length; ++i) {
            if (fields[i].getColumnLabel().length() <= maxWidth) continue;
            maxWidth = fields[i].getColumnLabel().length();
        }
        int rowCount = 1;
        while ((r = (Row)rs.getRows().next()) != null) {
            appendTo.append("*************************** ");
            appendTo.append(rowCount++);
            appendTo.append(". row ***************************\n");
            for (int i = 0; i < fields.length; ++i) {
                int leftPad = maxWidth - fields[i].getColumnLabel().length();
                for (int j = 0; j < leftPad; ++j) {
                    appendTo.append(" ");
                }
                appendTo.append(fields[i].getColumnLabel()).append(": ");
                String stringVal = r.getValue(i, new StringValueFactory(this.propertySet));
                appendTo.append(stringVal != null ? stringVal : "NULL").append("\n");
            }
            appendTo.append("\n");
        }
        return appendTo;
    }

    public SQLWarning convertShowWarningsToSQLWarnings(int warningCountIfKnown, boolean forTruncationOnly) {
        Throwable currentWarning = null;
        try (ResultsetRows rows = null;){
            Row r;
            NativePacketPayload resultPacket = this.sendCommand(this.commandBuilder.buildComQuery(this.getSharedSendPacket(), "SHOW WARNINGS"), false, 0);
            Resultset warnRs = this.readAllResults(-1, warningCountIfKnown > 99, resultPacket, false, null, new ResultsetFactory(Resultset.Type.FORWARD_ONLY, Resultset.Concurrency.READ_ONLY));
            int codeFieldIndex = warnRs.getColumnDefinition().findColumn("Code", false, 1) - 1;
            int messageFieldIndex = warnRs.getColumnDefinition().findColumn("Message", false, 1) - 1;
            StringValueFactory svf = new StringValueFactory(this.propertySet);
            IntegerValueFactory ivf = new IntegerValueFactory(this.propertySet);
            rows = warnRs.getRows();
            while ((r = (Row)rows.next()) != null) {
                int code = r.getValue(codeFieldIndex, ivf);
                if (forTruncationOnly) {
                    if (code != 1265 && code != 1264) continue;
                    MysqlDataTruncation newTruncation = new MysqlDataTruncation(r.getValue(messageFieldIndex, svf), 0, false, false, 0, 0, code);
                    if (currentWarning == null) {
                        currentWarning = newTruncation;
                        continue;
                    }
                    ((SQLWarning)currentWarning).setNextWarning(newTruncation);
                    continue;
                }
                String message = r.getValue(messageFieldIndex, svf);
                SQLWarning newWarning = new SQLWarning(message, MysqlErrorNumbers.mysqlToSqlState(code), code);
                if (currentWarning == null) {
                    currentWarning = newWarning;
                    continue;
                }
                ((SQLWarning)currentWarning).setNextWarning(newWarning);
            }
            if (forTruncationOnly && currentWarning != null) {
                throw ExceptionFactory.createException(currentWarning.getMessage(), currentWarning);
            }
            Throwable throwable = currentWarning;
            return throwable;
        }
    }

    @Override
    public ColumnDefinition readMetadata() {
        throw ExceptionFactory.createException(CJOperationNotSupportedException.class, "Not supported");
    }

    @Override
    public void close() throws IOException {
        throw ExceptionFactory.createException(CJOperationNotSupportedException.class, "Not supported");
    }

    @Override
    public void configureTimeZone() {
        String connectionTimeZone = this.getPropertySet().getStringProperty(PropertyKey.connectionTimeZone).getValue();
        TimeZone selectedTz = null;
        if (connectionTimeZone == null || StringUtils.isEmptyOrWhitespaceOnly(connectionTimeZone) || "LOCAL".equals(connectionTimeZone)) {
            selectedTz = TimeZone.getDefault();
        } else {
            if ("SERVER".equals(connectionTimeZone)) {
                return;
            }
            selectedTz = TimeZone.getTimeZone(ZoneId.of(connectionTimeZone));
        }
        this.serverSession.setSessionTimeZone(selectedTz);
        if (this.getPropertySet().getBooleanProperty(PropertyKey.forceConnectionTimeZoneToSession).getValue().booleanValue()) {
            StringBuilder query = new StringBuilder("SET SESSION time_zone='");
            ZoneId zid = selectedTz.toZoneId().normalized();
            if (zid instanceof ZoneOffset) {
                String offsetStr = ((ZoneOffset)zid).getId().replace("Z", "+00:00");
                query.append(offsetStr);
                this.serverSession.getServerVariables().put("time_zone", offsetStr);
            } else {
                query.append(selectedTz.getID());
                this.serverSession.getServerVariables().put("time_zone", selectedTz.getID());
            }
            query.append("'");
            this.sendCommand(this.commandBuilder.buildComQuery(null, query.toString()), false, 0);
        }
    }

    @Override
    public void initServerSession() {
        this.configureTimeZone();
        if (this.session.getServerSession().getServerVariables().containsKey("max_allowed_packet")) {
            int serverMaxAllowedPacket = this.session.getServerSession().getServerVariable("max_allowed_packet", -1);
            if (!(serverMaxAllowedPacket == -1 || this.maxAllowedPacket.isExplicitlySet() && serverMaxAllowedPacket >= this.maxAllowedPacket.getValue())) {
                this.maxAllowedPacket.setValue(serverMaxAllowedPacket);
            }
            if (this.useServerPrepStmts.getValue().booleanValue()) {
                RuntimeProperty<Integer> blobSendChunkSize = this.propertySet.getProperty(PropertyKey.blobSendChunkSize);
                int preferredBlobSendChunkSize = (Integer)blobSendChunkSize.getValue();
                int packetHeaderSize = 8203;
                int allowedBlobSendChunkSize = Math.min(preferredBlobSendChunkSize, this.maxAllowedPacket.getValue()) - packetHeaderSize;
                if (allowedBlobSendChunkSize <= 0) {
                    throw ExceptionFactory.createException(Messages.getString("Connection.15", new Object[]{packetHeaderSize}), "01S00", 0, false, null, this.exceptionInterceptor);
                }
                blobSendChunkSize.setValue(allowedBlobSendChunkSize);
            }
        }
    }

    static {
        OutputStreamWriter outWriter = null;
        try {
            outWriter = new OutputStreamWriter(new ByteArrayOutputStream());
            jvmPlatformCharset = outWriter.getEncoding();
        }
        finally {
            try {
                if (outWriter != null) {
                    outWriter.close();
                }
            }
            catch (IOException iOException) {}
        }
    }
}

