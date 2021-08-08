/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  com.google.protobuf.GeneratedMessageV3
 *  com.google.protobuf.InvalidProtocolBufferException
 *  com.google.protobuf.Message
 *  com.google.protobuf.Parser
 */
package com.mysql.cj.protocol.x;

import com.google.protobuf.GeneratedMessageV3;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Message;
import com.google.protobuf.Parser;
import com.mysql.cj.exceptions.CJCommunicationsException;
import com.mysql.cj.exceptions.WrongArgumentException;
import com.mysql.cj.protocol.FullReadInputStream;
import com.mysql.cj.protocol.MessageListener;
import com.mysql.cj.protocol.MessageReader;
import com.mysql.cj.protocol.Protocol;
import com.mysql.cj.protocol.x.MessageConstants;
import com.mysql.cj.protocol.x.Notice;
import com.mysql.cj.protocol.x.XMessage;
import com.mysql.cj.protocol.x.XMessageHeader;
import com.mysql.cj.protocol.x.XProtocolError;
import com.mysql.cj.x.protobuf.Mysqlx;
import com.mysql.cj.x.protobuf.MysqlxNotice;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Optional;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

public class SyncMessageReader
implements MessageReader<XMessageHeader, XMessage> {
    private FullReadInputStream inputStream;
    LinkedList<XMessageHeader> headersQueue = new LinkedList();
    LinkedList<GeneratedMessageV3> messagesQueue = new LinkedList();
    BlockingQueue<MessageListener<XMessage>> messageListenerQueue = new LinkedBlockingQueue<MessageListener<XMessage>>();
    Object dispatchingThreadMonitor = new Object();
    Object waitingSyncOperationMonitor = new Object();
    Thread dispatchingThread = null;
    private Protocol.ProtocolEventHandler protocolEventHandler = null;

    public SyncMessageReader(FullReadInputStream inputStream, Protocol.ProtocolEventHandler protocolEventHandler) {
        this.inputStream = inputStream;
        this.protocolEventHandler = protocolEventHandler;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public XMessageHeader readHeader() throws IOException {
        Object object = this.waitingSyncOperationMonitor;
        synchronized (object) {
            XMessageHeader header = this.headersQueue.peek();
            if (header == null) {
                header = this.readHeaderLocal();
            }
            if (header.getMessageType() == 1) {
                throw new XProtocolError(this.readMessageLocal(Mysqlx.Error.class, true));
            }
            return header;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public int getNextNonNoticeMessageType() throws IOException {
        Object object = this.waitingSyncOperationMonitor;
        synchronized (object) {
            XMessageHeader header;
            if (!this.headersQueue.isEmpty()) {
                for (XMessageHeader hdr : this.headersQueue) {
                    if (hdr.getMessageType() == 11) continue;
                    return hdr.getMessageType();
                }
            }
            do {
                if ((header = this.readHeaderLocal()).getMessageType() == 1) {
                    Mysqlx.Error msg = this.readMessageLocal(Mysqlx.Error.class, false);
                    this.messagesQueue.addLast(msg);
                    throw new XProtocolError(msg);
                }
                if (header.getMessageType() != 11) continue;
                this.messagesQueue.addLast(this.readMessageLocal(MysqlxNotice.Frame.class, false));
            } while (header.getMessageType() == 11);
            return header.getMessageType();
        }
    }

    private XMessageHeader readHeaderLocal() throws IOException {
        XMessageHeader header;
        try {
            byte[] buf = new byte[5];
            this.inputStream.readFully(buf);
            header = new XMessageHeader(buf);
            this.headersQueue.add(header);
        }
        catch (IOException ex) {
            throw new CJCommunicationsException("Cannot read packet header", ex);
        }
        return header;
    }

    private <T extends GeneratedMessageV3> T readMessageLocal(Class<T> messageClass, boolean fromQueue) {
        XMessageHeader header;
        if (fromQueue) {
            header = this.headersQueue.poll();
            GeneratedMessageV3 msg = this.messagesQueue.poll();
            if (msg != null) {
                return (T)msg;
            }
        } else {
            header = this.headersQueue.getLast();
        }
        Parser<? extends GeneratedMessageV3> parser = MessageConstants.MESSAGE_CLASS_TO_PARSER.get(messageClass);
        byte[] packet = new byte[header.getMessageSize()];
        try {
            this.inputStream.readFully(packet);
        }
        catch (IOException ex) {
            throw new CJCommunicationsException("Cannot read packet payload", ex);
        }
        try {
            Notice.XWarning w;
            int code;
            GeneratedMessageV3 msg = (GeneratedMessageV3)parser.parseFrom(packet);
            if (msg instanceof MysqlxNotice.Frame && ((MysqlxNotice.Frame)msg).getType() == 1 && ((MysqlxNotice.Frame)msg).getScope() == MysqlxNotice.Frame.Scope.GLOBAL && ((code = (int)(w = new Notice.XWarning((MysqlxNotice.Frame)msg)).getCode()) == 1053 || code == 1810 || code == 3169)) {
                CJCommunicationsException ex = new CJCommunicationsException(w.getMessage());
                ex.setVendorCode(code);
                if (this.protocolEventHandler != null) {
                    this.protocolEventHandler.invokeListeners(code == 1053 ? Protocol.ProtocolEventListener.EventType.SERVER_SHUTDOWN : Protocol.ProtocolEventListener.EventType.SERVER_CLOSED_SESSION, ex);
                }
                throw ex;
            }
            return (T)msg;
        }
        catch (InvalidProtocolBufferException ex) {
            throw new WrongArgumentException(ex);
        }
    }

    @Override
    public XMessage readMessage(Optional<XMessage> reuse, XMessageHeader hdr) throws IOException {
        return this.readMessage((Optional)reuse, hdr.getMessageType());
    }

    @Override
    public XMessage readMessage(Optional<XMessage> reuse, int expectedType) throws IOException {
        Object object = this.waitingSyncOperationMonitor;
        synchronized (object) {
            try {
                XMessageHeader hdr;
                Class<? extends GeneratedMessageV3> expectedClass = MessageConstants.getMessageClassForType(expectedType);
                ArrayList<Notice> notices = null;
                while ((hdr = this.readHeader()).getMessageType() == 11 && expectedType != 11) {
                    if (notices == null) {
                        notices = new ArrayList<Notice>();
                    }
                    notices.add(Notice.getInstance(new XMessage((Message)this.readMessageLocal(MessageConstants.getMessageClassForType(11), true))));
                }
                Class<? extends GeneratedMessageV3> messageClass = MessageConstants.getMessageClassForType(hdr.getMessageType());
                if (expectedClass != messageClass) {
                    throw new WrongArgumentException("Unexpected message class. Expected '" + expectedClass.getSimpleName() + "' but actually received '" + messageClass.getSimpleName() + "'");
                }
                return new XMessage((Message)this.readMessageLocal(messageClass, true)).addNotices(notices);
            }
            catch (IOException e) {
                throw new XProtocolError(e.getMessage(), e);
            }
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void pushMessageListener(MessageListener<XMessage> listener) {
        try {
            this.messageListenerQueue.put(listener);
        }
        catch (InterruptedException e) {
            throw new CJCommunicationsException("Cannot queue message listener.", e);
        }
        Object object = this.dispatchingThreadMonitor;
        synchronized (object) {
            if (this.dispatchingThread == null) {
                ListenersDispatcher ld = new ListenersDispatcher();
                this.dispatchingThread = new Thread((Runnable)ld, "Message listeners dispatching thread");
                this.dispatchingThread.start();
                int millis = 5000;
                while (!ld.started) {
                    try {
                        Thread.sleep(10L);
                    }
                    catch (InterruptedException e) {
                        throw new XProtocolError(e.getMessage(), e);
                    }
                    if ((millis -= 10) > 0) continue;
                    throw new XProtocolError("Timeout for starting ListenersDispatcher exceeded.");
                }
            }
        }
    }

    private class ListenersDispatcher
    implements Runnable {
        private static final long POLL_TIMEOUT = 100L;
        boolean started = false;

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public void run() {
            Object object = SyncMessageReader.this.waitingSyncOperationMonitor;
            synchronized (object) {
                this.started = true;
                block10: while (true) {
                    try {
                        while (true) {
                            MessageListener<XMessage> l;
                            if ((l = SyncMessageReader.this.messageListenerQueue.poll(100L, TimeUnit.MILLISECONDS)) == null) {
                                Object object2 = SyncMessageReader.this.dispatchingThreadMonitor;
                                synchronized (object2) {
                                    if (SyncMessageReader.this.messageListenerQueue.peek() == null) {
                                        SyncMessageReader.this.dispatchingThread = null;
                                        break block10;
                                    }
                                    continue block10;
                                }
                            }
                            try {
                                XMessageHeader hdr;
                                XMessage msg = null;
                                while (!l.processMessage(msg = SyncMessageReader.this.readMessage((Optional<XMessage>)null, hdr = SyncMessageReader.this.readHeader()))) {
                                }
                                continue block10;
                            }
                            catch (Throwable t) {
                                l.error(t);
                                continue;
                            }
                            break;
                        }
                    }
                    catch (InterruptedException e) {
                        throw new CJCommunicationsException("Read operation interrupted.", e);
                    }
                }
            }
        }
    }
}

