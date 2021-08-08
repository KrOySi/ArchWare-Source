/*
 * Decompiled with CFR 0.150.
 */
package com.mysql.cj.protocol.x;

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;

public class ConfinedInputStream
extends FilterInputStream {
    private int limit = 0;
    private int consumed = 0;
    private boolean closed = false;

    protected ConfinedInputStream(InputStream in) {
        this(in, 0);
    }

    protected ConfinedInputStream(InputStream in, int lim) {
        super(in);
        this.limit = lim;
        this.consumed = 0;
    }

    @Override
    public int available() throws IOException {
        this.ensureOpen();
        return this.limit - this.consumed;
    }

    @Override
    public void close() throws IOException {
        if (!this.closed) {
            this.dumpLeftovers();
            this.closed = true;
        }
    }

    @Override
    public int read() throws IOException {
        this.ensureOpen();
        int read = super.read();
        if (read >= 0) {
            ++this.consumed;
        }
        return read;
    }

    @Override
    public int read(byte[] b) throws IOException {
        this.ensureOpen();
        return this.read(b, 0, b.length);
    }

    @Override
    public int read(byte[] b, int off, int len) throws IOException {
        this.ensureOpen();
        if (this.consumed >= this.limit) {
            return -1;
        }
        int toRead = Math.min(len, this.available());
        int read = super.read(b, off, toRead);
        if (read > 0) {
            this.consumed += read;
        }
        return read;
    }

    public int resetLimit(int len) {
        int remaining = 0;
        try {
            remaining = this.available();
        }
        catch (IOException iOException) {
            // empty catch block
        }
        this.limit = len;
        this.consumed = 0;
        return remaining;
    }

    protected long dumpLeftovers() throws IOException {
        long skipped = this.skip(this.available());
        this.consumed = (int)((long)this.consumed + skipped);
        return skipped;
    }

    private void ensureOpen() throws IOException {
        if (this.closed) {
            throw new IOException("Stream closed");
        }
    }
}

