/*
 * Decompiled with CFR 0.150.
 */
package com.mysql.cj.protocol;

import com.mysql.cj.MessageBuilder;
import com.mysql.cj.QueryResult;
import com.mysql.cj.Session;
import com.mysql.cj.TransactionEventHandler;
import com.mysql.cj.conf.PropertySet;
import com.mysql.cj.exceptions.ExceptionInterceptor;
import com.mysql.cj.protocol.AuthenticationProvider;
import com.mysql.cj.protocol.ColumnDefinition;
import com.mysql.cj.protocol.Message;
import com.mysql.cj.protocol.PacketReceivedTimeHolder;
import com.mysql.cj.protocol.PacketSentTimeHolder;
import com.mysql.cj.protocol.ProtocolEntity;
import com.mysql.cj.protocol.ProtocolEntityFactory;
import com.mysql.cj.protocol.ResultBuilder;
import com.mysql.cj.protocol.Resultset;
import com.mysql.cj.protocol.ServerCapabilities;
import com.mysql.cj.protocol.ServerSession;
import com.mysql.cj.protocol.SocketConnection;
import java.io.IOException;
import java.io.InputStream;

public interface Protocol<M extends Message> {
    public void init(Session var1, SocketConnection var2, PropertySet var3, TransactionEventHandler var4);

    public PropertySet getPropertySet();

    public void setPropertySet(PropertySet var1);

    public MessageBuilder<M> getMessageBuilder();

    public ServerCapabilities readServerCapabilities();

    public ServerSession getServerSession();

    public SocketConnection getSocketConnection();

    public AuthenticationProvider<M> getAuthenticationProvider();

    public ExceptionInterceptor getExceptionInterceptor();

    public PacketSentTimeHolder getPacketSentTimeHolder();

    public void setPacketSentTimeHolder(PacketSentTimeHolder var1);

    public PacketReceivedTimeHolder getPacketReceivedTimeHolder();

    public void setPacketReceivedTimeHolder(PacketReceivedTimeHolder var1);

    public void connect(String var1, String var2, String var3);

    public void negotiateSSLConnection();

    public void beforeHandshake();

    public void afterHandshake();

    public void changeDatabase(String var1);

    public void changeUser(String var1, String var2, String var3);

    public String getPasswordCharacterEncoding();

    public boolean versionMeetsMinimum(int var1, int var2, int var3);

    public M readMessage(M var1);

    public M checkErrorMessage();

    public void send(Message var1, int var2);

    public ColumnDefinition readMetadata();

    public M sendCommand(Message var1, boolean var2, int var3);

    public <T extends ProtocolEntity> T read(Class<T> var1, ProtocolEntityFactory<T, M> var2) throws IOException;

    public <T extends ProtocolEntity> T read(Class<Resultset> var1, int var2, boolean var3, M var4, boolean var5, ColumnDefinition var6, ProtocolEntityFactory<T, M> var7) throws IOException;

    public void setLocalInfileInputStream(InputStream var1);

    public InputStream getLocalInfileInputStream();

    public String getQueryComment();

    public void setQueryComment(String var1);

    public <T extends QueryResult> T readQueryResult(ResultBuilder<T> var1);

    public void close() throws IOException;

    public void configureTimeZone();

    public void initServerSession();

    public void reset();

    public String getQueryTimingUnits();

    public static interface ProtocolEventHandler {
        public void addListener(ProtocolEventListener var1);

        public void removeListener(ProtocolEventListener var1);

        public void invokeListeners(ProtocolEventListener.EventType var1, Throwable var2);
    }

    public static interface ProtocolEventListener {
        public void handleEvent(EventType var1, Object var2, Throwable var3);

        public static enum EventType {
            SERVER_SHUTDOWN,
            SERVER_CLOSED_SESSION;

        }
    }
}

