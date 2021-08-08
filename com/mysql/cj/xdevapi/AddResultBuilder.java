/*
 * Decompiled with CFR 0.150.
 */
package com.mysql.cj.xdevapi;

import com.mysql.cj.xdevapi.AddResult;
import com.mysql.cj.xdevapi.AddResultImpl;
import com.mysql.cj.xdevapi.UpdateResultBuilder;

public class AddResultBuilder
extends UpdateResultBuilder<AddResult> {
    @Override
    public AddResult build() {
        return new AddResultImpl(this.statementExecuteOkBuilder.build());
    }
}

