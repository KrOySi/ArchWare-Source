/*
 * Decompiled with CFR 0.150.
 */
package com.mysql.cj.protocol;

import com.mysql.cj.protocol.WatchableWriter;

public interface WriterWatcher {
    public void writerClosed(WatchableWriter var1);
}

