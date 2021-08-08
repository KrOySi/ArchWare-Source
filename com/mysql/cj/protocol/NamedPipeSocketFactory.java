/*
 * Decompiled with CFR 0.150.
 */
package com.mysql.cj.protocol;

import com.mysql.cj.Messages;
import com.mysql.cj.Session;
import com.mysql.cj.conf.PropertyKey;
import com.mysql.cj.conf.PropertySet;
import com.mysql.cj.conf.RuntimeProperty;
import com.mysql.cj.protocol.ServerSession;
import com.mysql.cj.protocol.SocketConnection;
import com.mysql.cj.protocol.SocketFactory;
import java.io.Closeable;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import java.net.Socket;
import java.net.SocketException;
import java.util.concurrent.TimeUnit;

public class NamedPipeSocketFactory
implements SocketFactory {
    private static final int DEFAULT_TIMEOUT = 100;
    private Socket namedPipeSocket;

    @Override
    public <T extends Closeable> T performTlsHandshake(SocketConnection socketConnection, ServerSession serverSession) throws IOException {
        return (T)this.namedPipeSocket;
    }

    @Override
    public <T extends Closeable> T connect(String host, int portNumber, PropertySet props, int loginTimeout) throws IOException {
        String namedPipePath = null;
        RuntimeProperty<String> path = props.getStringProperty(PropertyKey.PATH);
        if (path != null) {
            namedPipePath = path.getValue();
        }
        if (namedPipePath == null) {
            namedPipePath = "\\\\.\\pipe\\MySQL";
        } else if (namedPipePath.length() == 0) {
            throw new SocketException(Messages.getString("NamedPipeSocketFactory.2") + PropertyKey.PATH.getCcAlias() + Messages.getString("NamedPipeSocketFactory.3"));
        }
        int connectTimeout = props.getIntegerProperty(PropertyKey.connectTimeout.getKeyName()).getValue();
        int timeout = connectTimeout > 0 && loginTimeout > 0 ? Math.min(connectTimeout, loginTimeout) : connectTimeout + loginTimeout;
        this.namedPipeSocket = new NamedPipeSocket(namedPipePath, timeout);
        return (T)this.namedPipeSocket;
    }

    @Override
    public boolean isLocallyConnected(Session sess) {
        return true;
    }

    class RandomAccessFileOutputStream
    extends OutputStream {
        RandomAccessFile raFile;

        RandomAccessFileOutputStream(RandomAccessFile file) {
            this.raFile = file;
        }

        @Override
        public void close() throws IOException {
            this.raFile.close();
        }

        @Override
        public void write(byte[] b) throws IOException {
            this.raFile.write(b);
        }

        @Override
        public void write(byte[] b, int off, int len) throws IOException {
            this.raFile.write(b, off, len);
        }

        @Override
        public void write(int b) throws IOException {
        }
    }

    class RandomAccessFileInputStream
    extends InputStream {
        RandomAccessFile raFile;

        RandomAccessFileInputStream(RandomAccessFile file) {
            this.raFile = file;
        }

        @Override
        public int available() throws IOException {
            return -1;
        }

        @Override
        public void close() throws IOException {
            this.raFile.close();
        }

        @Override
        public int read() throws IOException {
            return this.raFile.read();
        }

        @Override
        public int read(byte[] b) throws IOException {
            return this.raFile.read(b);
        }

        @Override
        public int read(byte[] b, int off, int len) throws IOException {
            return this.raFile.read(b, off, len);
        }
    }

    class NamedPipeSocket
    extends Socket {
        private boolean isClosed = false;
        private RandomAccessFile namedPipeFile;

        NamedPipeSocket(String filePath, int timeout) throws IOException {
            if (filePath == null || filePath.length() == 0) {
                throw new IOException(Messages.getString("NamedPipeSocketFactory.4"));
            }
            int timeoutCountdown = timeout == 0 ? 100 : timeout;
            long startTime = System.currentTimeMillis();
            while (true) {
                try {
                    this.namedPipeFile = new RandomAccessFile(filePath, "rw");
                    break;
                }
                catch (FileNotFoundException e) {
                    if (timeout == 0) {
                        throw new IOException("Named pipe busy error (ERROR_PIPE_BUSY).\nConsider setting a value for 'connectTimeout' or DriverManager.setLoginTimeout(int) to repeatedly try opening the named pipe before failing.", e);
                    }
                    if (System.currentTimeMillis() - startTime > (long)timeoutCountdown) {
                        throw e;
                    }
                    try {
                        TimeUnit.MILLISECONDS.sleep(10L);
                    }
                    catch (InterruptedException e2) {
                        throw new IOException(e2);
                    }
                }
            }
        }

        @Override
        public synchronized void close() throws IOException {
            this.namedPipeFile.close();
            this.isClosed = true;
        }

        @Override
        public InputStream getInputStream() throws IOException {
            return new RandomAccessFileInputStream(this.namedPipeFile);
        }

        @Override
        public OutputStream getOutputStream() throws IOException {
            return new RandomAccessFileOutputStream(this.namedPipeFile);
        }

        @Override
        public boolean isClosed() {
            return this.isClosed;
        }

        @Override
        public void shutdownInput() throws IOException {
        }
    }
}

