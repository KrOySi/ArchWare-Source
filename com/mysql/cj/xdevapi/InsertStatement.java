/*
 * Decompiled with CFR 0.150.
 */
package com.mysql.cj.xdevapi;

import com.mysql.cj.xdevapi.InsertResult;
import com.mysql.cj.xdevapi.Statement;
import java.util.Arrays;
import java.util.List;

public interface InsertStatement
extends Statement<InsertStatement, InsertResult> {
    public InsertStatement values(List<Object> var1);

    default public InsertStatement values(Object ... values) {
        return this.values(Arrays.asList(values));
    }
}

