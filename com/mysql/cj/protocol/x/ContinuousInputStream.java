/*
 * Decompiled with CFR 0.150.
 */
package com.mysql.cj.protocol.x;

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.Queue;

public class ContinuousInputStream
extends FilterInputStream {
    private Queue<InputStream> inputStreams = new LinkedList<InputStream>();
    private boolean closed = false;

    protected ContinuousInputStream(InputStream in) {
        super(in);
    }

    @Override
    public int available() throws IOException {
        this.ensureOpen();
        int available = super.available();
        if (available == 0 && this.nextInLine()) {
            return this.available();
        }
        return available;
    }

    @Override
    public void close() throws IOException {
        if (!this.closed) {
            this.closed = true;
            super.close();
            for (InputStream is : this.inputStreams) {
                is.close();
            }
        }
    }

    @Override
    public int read() throws IOException {
        this.ensureOpen();
        int read = super.read();
        if (read >= 0) {
            return read;
        }
        if (this.nextInLine()) {
            return this.read();
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
        int toRead = Math.min(len, this.available());
        int read = super.read(b, off, toRead);
        if (read > 0) {
            return read;
        }
        if (this.nextInLine()) {
            return this.read(b, off, len);
        }
        return read;
    }

    protected boolean addInputStream(InputStream newIn) {
        return this.inputStreams.offer(newIn);
    }

    private boolean nextInLine() throws IOException {
        InputStream nextInputStream = this.inputStreams.poll();
        if (nextInputStream != null) {
            super.close();
            this.in = nextInputStream;
            return true;
        }
        return false;
    }

    private void ensureOpen() throws IOException {
        if (this.closed) {
            throw new IOException("Stream closed");
        }
    }
}

