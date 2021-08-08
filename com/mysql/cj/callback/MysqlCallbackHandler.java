/*
 * Decompiled with CFR 0.150.
 */
package com.mysql.cj.callback;

import com.mysql.cj.callback.MysqlCallback;

@FunctionalInterface
public interface MysqlCallbackHandler {
    public void handle(MysqlCallback var1);
}

