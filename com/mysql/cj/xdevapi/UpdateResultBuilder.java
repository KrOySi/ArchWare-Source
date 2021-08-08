/*
 * Decompiled with CFR 0.150.
 */
package com.mysql.cj.xdevapi;

import com.mysql.cj.exceptions.ExceptionFactory;
import com.mysql.cj.exceptions.WrongArgumentException;
import com.mysql.cj.protocol.ProtocolEntity;
import com.mysql.cj.protocol.ResultBuilder;
import com.mysql.cj.protocol.x.Notice;
import com.mysql.cj.protocol.x.Ok;
import com.mysql.cj.protocol.x.StatementExecuteOk;
import com.mysql.cj.protocol.x.StatementExecuteOkBuilder;
import com.mysql.cj.xdevapi.Result;
import com.mysql.cj.xdevapi.UpdateResult;

public class UpdateResultBuilder<T extends Result>
implements ResultBuilder<T> {
    protected StatementExecuteOkBuilder statementExecuteOkBuilder = new StatementExecuteOkBuilder();

    @Override
    public boolean addProtocolEntity(ProtocolEntity entity) {
        if (entity instanceof Notice) {
            this.statementExecuteOkBuilder.addProtocolEntity(entity);
            return false;
        }
        if (entity instanceof StatementExecuteOk) {
            return true;
        }
        if (entity instanceof Ok) {
            return true;
        }
        throw ExceptionFactory.createException(WrongArgumentException.class, "Unexpected protocol entity " + entity);
    }

    @Override
    public T build() {
        return (T)new UpdateResult(this.statementExecuteOkBuilder.build());
    }
}

