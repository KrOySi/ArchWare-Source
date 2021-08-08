/*
 * Decompiled with CFR 0.150.
 */
package com.mysql.cj.log;

import com.mysql.cj.Query;
import com.mysql.cj.Session;
import com.mysql.cj.log.Log;
import com.mysql.cj.log.ProfilerEvent;
import com.mysql.cj.protocol.Resultset;

public interface ProfilerEventHandler {
    public void init(Log var1);

    public void destroy();

    public void consumeEvent(ProfilerEvent var1);

    public void processEvent(byte var1, Session var2, Query var3, Resultset var4, long var5, Throwable var7, String var8);
}

