/*
 * Decompiled with CFR 0.150.
 */
package com.mysql.cj.xdevapi;

import com.mysql.cj.protocol.x.StatementExecuteOk;
import com.mysql.cj.xdevapi.InsertResult;
import com.mysql.cj.xdevapi.UpdateResult;

public class InsertResultImpl
extends UpdateResult
implements InsertResult {
    public InsertResultImpl(StatementExecuteOk ok) {
        super(ok);
    }

    @Override
    public Long getAutoIncrementValue() {
        return this.ok.getLastInsertId();
    }
}

