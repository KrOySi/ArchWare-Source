/*
 * Decompiled with CFR 0.150.
 */
package com.mysql.cj.protocol;

import com.mysql.cj.MessageBuilder;
import com.mysql.cj.Messages;
import com.mysql.cj.Session;
import com.mysql.cj.TransactionEventHandler;
import com.mysql.cj.conf.PropertyKey;
import com.mysql.cj.conf.PropertySet;
import com.mysql.cj.exceptions.ExceptionInterceptor;
import com.mysql.cj.log.Log;
import com.mysql.cj.protocol.AuthenticationProvider;
import com.mysql.cj.protocol.Message;
import com.mysql.cj.protocol.PacketReceivedTimeHolder;
import com.mysql.cj.protocol.PacketSentTimeHolder;
import com.mysql.cj.protocol.Protocol;
import com.mysql.cj.protocol.SocketConnection;
import com.mysql.cj.util.TimeUtil;
import java.lang.ref.WeakReference;
import java.util.LinkedList;
import java.util.concurrent.CopyOnWriteArrayList;

public abstract class AbstractProtocol<M extends Message>
implements Protocol<M>,
Protocol.ProtocolEventHandler {
    protected Session session;
    protected SocketConnection socketConnection;
    protected PropertySet propertySet;
    protected TransactionEventHandler transactionManager;
    protected transient Log log;
    protected ExceptionInterceptor exceptionInterceptor;
    protected AuthenticationProvider<M> authProvider;
    protected MessageBuilder<M> messageBuilder;
    private PacketSentTimeHolder packetSentTimeHolder = new PacketSentTimeHolder(){};
    private PacketReceivedTimeHolder packetReceivedTimeHolder = new PacketReceivedTimeHolder(){};
    protected LinkedList<StringBuilder> packetDebugRingBuffer = null;
    protected boolean useNanosForElapsedTime;
    protected String queryTimingUnits;
    private CopyOnWriteArrayList<WeakReference<Protocol.ProtocolEventListener>> listeners = new CopyOnWriteArrayList();

    @Override
    public void init(Session sess, SocketConnection phConnection, PropertySet propSet, TransactionEventHandler trManager) {
        this.session = sess;
        this.propertySet = propSet;
        this.socketConnection = phConnection;
        this.exceptionInterceptor = this.socketConnection.getExceptionInterceptor();
        this.transactionManager = trManager;
        this.useNanosForElapsedTime = this.propertySet.getBooleanProperty(PropertyKey.useNanosForElapsedTime).getValue() != false && TimeUtil.nanoTimeAvailable();
        this.queryTimingUnits = this.useNanosForElapsedTime ? Messages.getString("Nanoseconds") : Messages.getString("Milliseconds");
    }

    @Override
    public SocketConnection getSocketConnection() {
        return this.socketConnection;
    }

    @Override
    public AuthenticationProvider<M> getAuthenticationProvider() {
        return this.authProvider;
    }

    @Override
    public ExceptionInterceptor getExceptionInterceptor() {
        return this.exceptionInterceptor;
    }

    @Override
    public PacketSentTimeHolder getPacketSentTimeHolder() {
        return this.packetSentTimeHolder;
    }

    @Override
    public void setPacketSentTimeHolder(PacketSentTimeHolder packetSentTimeHolder) {
        this.packetSentTimeHolder = packetSentTimeHolder;
    }

    @Override
    public PacketReceivedTimeHolder getPacketReceivedTimeHolder() {
        return this.packetReceivedTimeHolder;
    }

    @Override
    public void setPacketReceivedTimeHolder(PacketReceivedTimeHolder packetReceivedTimeHolder) {
        this.packetReceivedTimeHolder = packetReceivedTimeHolder;
    }

    @Override
    public PropertySet getPropertySet() {
        return this.propertySet;
    }

    @Override
    public void setPropertySet(PropertySet propertySet) {
        this.propertySet = propertySet;
    }

    @Override
    public MessageBuilder<M> getMessageBuilder() {
        return this.messageBuilder;
    }

    @Override
    public void reset() {
    }

    @Override
    public String getQueryTimingUnits() {
        return this.queryTimingUnits;
    }

    @Override
    public void addListener(Protocol.ProtocolEventListener l) {
        this.listeners.addIfAbsent(new WeakReference<Protocol.ProtocolEventListener>(l));
    }

    @Override
    public void removeListener(Protocol.ProtocolEventListener listener) {
        for (WeakReference<Protocol.ProtocolEventListener> wr : this.listeners) {
            Protocol.ProtocolEventListener l = (Protocol.ProtocolEventListener)wr.get();
            if (l != listener) continue;
            this.listeners.remove(wr);
            break;
        }
    }

    @Override
    public void invokeListeners(Protocol.ProtocolEventListener.EventType type, Throwable reason) {
        for (WeakReference<Protocol.ProtocolEventListener> wr : this.listeners) {
            Protocol.ProtocolEventListener l = (Protocol.ProtocolEventListener)wr.get();
            if (l != null) {
                l.handleEvent(type, this, reason);
                continue;
            }
            this.listeners.remove(wr);
        }
    }
}

