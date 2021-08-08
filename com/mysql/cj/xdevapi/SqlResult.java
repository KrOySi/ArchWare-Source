/*
 * Decompiled with CFR 0.150.
 */
package com.mysql.cj.xdevapi;

import com.mysql.cj.xdevapi.InsertResult;
import com.mysql.cj.xdevapi.Result;
import com.mysql.cj.xdevapi.RowResult;
import com.mysql.cj.xdevapi.XDevAPIError;

public interface SqlResult
extends Result,
InsertResult,
RowResult {
    default public boolean nextResult() {
        return false;
    }

    @Override
    default public Long getAutoIncrementValue() {
        throw new XDevAPIError("Method getAutoIncrementValue() is allowed only for insert statements.");
    }
}

