/*
 * Decompiled with CFR 0.150.
 */
package com.mysql.cj.xdevapi;

import com.mysql.cj.xdevapi.InsertResult;
import com.mysql.cj.xdevapi.InsertResultImpl;
import com.mysql.cj.xdevapi.UpdateResultBuilder;

public class InsertResultBuilder
extends UpdateResultBuilder<InsertResult> {
    @Override
    public InsertResult build() {
        return new InsertResultImpl(this.statementExecuteOkBuilder.build());
    }
}

