/*
 * Decompiled with CFR 0.150.
 */
package com.mysql.cj.protocol;

import com.mysql.cj.protocol.WatchableStream;

public interface OutputStreamWatcher {
    public void streamClosed(WatchableStream var1);
}

