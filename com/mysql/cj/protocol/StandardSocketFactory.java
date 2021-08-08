/*
 * Decompiled with CFR 0.150.
 */
package com.mysql.cj.protocol;

import com.mysql.cj.Messages;
import com.mysql.cj.conf.PropertyKey;
import com.mysql.cj.conf.PropertySet;
import com.mysql.cj.protocol.ExportControlled;
import com.mysql.cj.protocol.ServerSession;
import com.mysql.cj.protocol.SocketConnection;
import com.mysql.cj.protocol.SocketFactory;
import java.io.Closeable;
import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketException;

public class StandardSocketFactory
implements SocketFactory {
    protected String host = null;
    protected int port = 3306;
    protected Socket rawSocket = null;
    protected Socket sslSocket = null;
    protected int loginTimeoutCountdown = 0;
    protected long loginTimeoutCheckTimestamp = System.currentTimeMillis();
    protected int socketTimeoutBackup = 0;

    protected Socket createSocket(PropertySet props) {
        return new Socket();
    }

    private void configureSocket(Socket sock, PropertySet pset) throws SocketException, IOException {
        int trafficClass;
        int sendBufferSize;
        sock.setTcpNoDelay(pset.getBooleanProperty(PropertyKey.tcpNoDelay).getValue());
        sock.setKeepAlive(pset.getBooleanProperty(PropertyKey.tcpKeepAlive).getValue());
        int receiveBufferSize = pset.getIntegerProperty(PropertyKey.tcpRcvBuf).getValue();
        if (receiveBufferSize > 0) {
            sock.setReceiveBufferSize(receiveBufferSize);
        }
        if ((sendBufferSize = pset.getIntegerProperty(PropertyKey.tcpSndBuf).getValue().intValue()) > 0) {
            sock.setSendBufferSize(sendBufferSize);
        }
        if ((trafficClass = pset.getIntegerProperty(PropertyKey.tcpTrafficClass).getValue().intValue()) > 0) {
            sock.setTrafficClass(trafficClass);
        }
    }

    @Override
    public <T extends Closeable> T connect(String hostname, int portNumber, PropertySet pset, int loginTimeout) throws IOException {
        this.loginTimeoutCountdown = loginTimeout;
        if (pset != null) {
            this.host = hostname;
            this.port = portNumber;
            String localSocketHostname = pset.getStringProperty(PropertyKey.localSocketAddress).getValue();
            InetSocketAddress localSockAddr = null;
            if (localSocketHostname != null && localSocketHostname.length() > 0) {
                localSockAddr = new InetSocketAddress(InetAddress.getByName(localSocketHostname), 0);
            }
            int connectTimeout = pset.getIntegerProperty(PropertyKey.connectTimeout).getValue();
            if (this.host != null) {
                InetAddress[] possibleAddresses = InetAddress.getAllByName(this.host);
                if (possibleAddresses.length == 0) {
                    throw new SocketException("No addresses for host");
                }
                SocketException lastException = null;
                for (int i = 0; i < possibleAddresses.length; ++i) {
                    try {
                        this.rawSocket = this.createSocket(pset);
                        this.configureSocket(this.rawSocket, pset);
                        InetSocketAddress sockAddr = new InetSocketAddress(possibleAddresses[i], this.port);
                        if (localSockAddr != null) {
                            this.rawSocket.bind(localSockAddr);
                        }
                        this.rawSocket.connect(sockAddr, this.getRealTimeout(connectTimeout));
                        break;
                    }
                    catch (SocketException ex) {
                        lastException = ex;
                        this.resetLoginTimeCountdown();
                        this.rawSocket = null;
                        continue;
                    }
                }
                if (this.rawSocket == null && lastException != null) {
                    throw lastException;
                }
                this.resetLoginTimeCountdown();
                this.sslSocket = this.rawSocket;
                return (T)this.rawSocket;
            }
        }
        throw new SocketException("Unable to create socket");
    }

    @Override
    public void beforeHandshake() throws IOException {
        this.resetLoginTimeCountdown();
        this.socketTimeoutBackup = this.rawSocket.getSoTimeout();
        this.rawSocket.setSoTimeout(this.getRealTimeout(this.socketTimeoutBackup));
    }

    @Override
    public <T extends Closeable> T performTlsHandshake(SocketConnection socketConnection, ServerSession serverSession) throws IOException {
        this.sslSocket = ExportControlled.performTlsHandshake(this.rawSocket, socketConnection, serverSession == null ? null : serverSession.getServerVersion());
        return (T)this.sslSocket;
    }

    @Override
    public void afterHandshake() throws IOException {
        this.resetLoginTimeCountdown();
        this.rawSocket.setSoTimeout(this.socketTimeoutBackup);
    }

    protected void resetLoginTimeCountdown() throws SocketException {
        if (this.loginTimeoutCountdown > 0) {
            long now = System.currentTimeMillis();
            this.loginTimeoutCountdown = (int)((long)this.loginTimeoutCountdown - (now - this.loginTimeoutCheckTimestamp));
            if (this.loginTimeoutCountdown <= 0) {
                throw new SocketException(Messages.getString("Connection.LoginTimeout"));
            }
            this.loginTimeoutCheckTimestamp = now;
        }
    }

    protected int getRealTimeout(int expectedTimeout) {
        if (this.loginTimeoutCountdown > 0 && (expectedTimeout == 0 || expectedTimeout > this.loginTimeoutCountdown)) {
            return this.loginTimeoutCountdown;
        }
        return expectedTimeout;
    }
}

