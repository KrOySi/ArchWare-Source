/*
 * Decompiled with CFR 0.150.
 */
package com.mysql.cj;

import com.mysql.cj.protocol.Message;
import java.util.List;

public interface MessageBuilder<M extends Message> {
    public M buildSqlStatement(String var1);

    public M buildSqlStatement(String var1, List<Object> var2);

    public M buildClose();
}

