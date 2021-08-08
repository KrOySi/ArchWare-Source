/*
 * Decompiled with CFR 0.150.
 */
package com.mysql.cj.xdevapi;

import com.mysql.cj.xdevapi.Result;
import com.mysql.cj.xdevapi.Statement;

public interface RemoveStatement
extends Statement<RemoveStatement, Result> {
    @Deprecated
    public RemoveStatement orderBy(String ... var1);

    public RemoveStatement sort(String ... var1);

    public RemoveStatement limit(long var1);
}

