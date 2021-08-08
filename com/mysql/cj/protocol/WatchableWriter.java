/*
 * Decompiled with CFR 0.150.
 */
package com.mysql.cj.protocol;

import com.mysql.cj.protocol.WriterWatcher;
import java.io.CharArrayWriter;

public class WatchableWriter
extends CharArrayWriter {
    private WriterWatcher watcher;

    @Override
    public void close() {
        super.close();
        if (this.watcher != null) {
            this.watcher.writerClosed(this);
        }
    }

    public void setWatcher(WriterWatcher watcher) {
        this.watcher = watcher;
    }
}

