/*
 * Decompiled with CFR 0.150.
 */
package com.mysql.cj.xdevapi;

import com.mysql.cj.MysqlxSession;
import com.mysql.cj.protocol.x.XMessage;
import com.mysql.cj.protocol.x.XMessageBuilder;
import com.mysql.cj.xdevapi.FilterableStatement;
import com.mysql.cj.xdevapi.Result;
import com.mysql.cj.xdevapi.TableFilterParams;
import com.mysql.cj.xdevapi.UpdateParams;
import com.mysql.cj.xdevapi.UpdateResultBuilder;
import com.mysql.cj.xdevapi.UpdateStatement;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

public class UpdateStatementImpl
extends FilterableStatement<UpdateStatement, Result>
implements UpdateStatement {
    private UpdateParams updateParams = new UpdateParams();

    UpdateStatementImpl(MysqlxSession mysqlxSession, String schema, String table) {
        super(new TableFilterParams(schema, table, false));
        this.mysqlxSession = mysqlxSession;
    }

    @Override
    protected Result executeStatement() {
        return (Result)this.mysqlxSession.query(this.getMessageBuilder().buildRowUpdate(this.filterParams, this.updateParams), new UpdateResultBuilder());
    }

    @Override
    protected XMessage getPrepareStatementXMessage() {
        return this.getMessageBuilder().buildPrepareRowUpdate(this.preparedStatementId, this.filterParams, this.updateParams);
    }

    @Override
    protected Result executePreparedStatement() {
        return (Result)this.mysqlxSession.query(this.getMessageBuilder().buildPrepareExecute(this.preparedStatementId, this.filterParams), new UpdateResultBuilder());
    }

    @Override
    public CompletableFuture<Result> executeAsync() {
        return this.mysqlxSession.queryAsync(((XMessageBuilder)this.mysqlxSession.getMessageBuilder()).buildRowUpdate(this.filterParams, this.updateParams), new UpdateResultBuilder());
    }

    @Override
    public UpdateStatement set(Map<String, Object> fieldsAndValues) {
        this.resetPrepareState();
        this.updateParams.setUpdates(fieldsAndValues);
        return this;
    }

    @Override
    public UpdateStatement set(String field, Object value) {
        this.resetPrepareState();
        this.updateParams.addUpdate(field, value);
        return this;
    }
}

