/*
 * Decompiled with CFR 0.150.
 */
package com.mysql.cj.protocol;

import com.mysql.cj.protocol.OutputStreamWatcher;

public interface WatchableStream {
    public void setWatcher(OutputStreamWatcher var1);

    public int size();

    public byte[] toByteArray();

    public void write(byte[] var1, int var2, int var3);
}

