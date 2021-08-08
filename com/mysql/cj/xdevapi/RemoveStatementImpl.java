/*
 * Decompiled with CFR 0.150.
 */
package com.mysql.cj.xdevapi;

import com.mysql.cj.Messages;
import com.mysql.cj.MysqlxSession;
import com.mysql.cj.protocol.x.XMessage;
import com.mysql.cj.protocol.x.XMessageBuilder;
import com.mysql.cj.xdevapi.DocFilterParams;
import com.mysql.cj.xdevapi.FilterableStatement;
import com.mysql.cj.xdevapi.RemoveStatement;
import com.mysql.cj.xdevapi.Result;
import com.mysql.cj.xdevapi.UpdateResultBuilder;
import com.mysql.cj.xdevapi.XDevAPIError;
import java.util.concurrent.CompletableFuture;

public class RemoveStatementImpl
extends FilterableStatement<RemoveStatement, Result>
implements RemoveStatement {
    RemoveStatementImpl(MysqlxSession mysqlxSession, String schema, String collection, String criteria) {
        super(new DocFilterParams(schema, collection, false));
        this.mysqlxSession = mysqlxSession;
        if (criteria == null || criteria.trim().length() == 0) {
            throw new XDevAPIError(Messages.getString("RemoveStatement.0", new String[]{"criteria"}));
        }
        this.filterParams.setCriteria(criteria);
    }

    @Override
    @Deprecated
    public RemoveStatement orderBy(String ... sortFields) {
        return (RemoveStatement)super.orderBy(sortFields);
    }

    @Override
    public Result executeStatement() {
        return (Result)this.mysqlxSession.query(this.getMessageBuilder().buildDelete(this.filterParams), new UpdateResultBuilder());
    }

    @Override
    protected XMessage getPrepareStatementXMessage() {
        return this.getMessageBuilder().buildPrepareDelete(this.preparedStatementId, this.filterParams);
    }

    @Override
    protected Result executePreparedStatement() {
        return (Result)this.mysqlxSession.query(this.getMessageBuilder().buildPrepareExecute(this.preparedStatementId, this.filterParams), new UpdateResultBuilder());
    }

    @Override
    public CompletableFuture<Result> executeAsync() {
        return this.mysqlxSession.queryAsync(((XMessageBuilder)this.mysqlxSession.getMessageBuilder()).buildDelete(this.filterParams), new UpdateResultBuilder());
    }

    @Override
    @Deprecated
    public RemoveStatement where(String searchCondition) {
        return (RemoveStatement)super.where(searchCondition);
    }
}

