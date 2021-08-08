/*
 * Decompiled with CFR 0.150.
 */
package com.mysql.cj.xdevapi;

import com.mysql.cj.MysqlxSession;
import com.mysql.cj.protocol.x.XMessage;
import com.mysql.cj.xdevapi.DocFilterParams;
import com.mysql.cj.xdevapi.DocResult;
import com.mysql.cj.xdevapi.DocResultBuilder;
import com.mysql.cj.xdevapi.Expression;
import com.mysql.cj.xdevapi.FilterParams;
import com.mysql.cj.xdevapi.FilterableStatement;
import com.mysql.cj.xdevapi.FindStatement;
import com.mysql.cj.xdevapi.PreparableStatement;
import com.mysql.cj.xdevapi.Statement;
import com.mysql.cj.xdevapi.StreamingDocResultBuilder;
import java.util.concurrent.CompletableFuture;

public class FindStatementImpl
extends FilterableStatement<FindStatement, DocResult>
implements FindStatement {
    FindStatementImpl(MysqlxSession mysqlxSession, String schema, String collection, String criteria) {
        super(new DocFilterParams(schema, collection));
        this.mysqlxSession = mysqlxSession;
        if (criteria != null && criteria.length() > 0) {
            this.filterParams.setCriteria(criteria);
        }
        if (!this.mysqlxSession.supportsPreparedStatements()) {
            this.preparedState = PreparableStatement.PreparedState.UNSUPPORTED;
        }
    }

    @Override
    protected DocResult executeStatement() {
        return this.mysqlxSession.query(this.getMessageBuilder().buildFind(this.filterParams), new StreamingDocResultBuilder(this.mysqlxSession));
    }

    @Override
    protected XMessage getPrepareStatementXMessage() {
        return this.getMessageBuilder().buildPrepareFind(this.preparedStatementId, this.filterParams);
    }

    @Override
    protected DocResult executePreparedStatement() {
        return this.mysqlxSession.query(this.getMessageBuilder().buildPrepareExecute(this.preparedStatementId, this.filterParams), new StreamingDocResultBuilder(this.mysqlxSession));
    }

    @Override
    public CompletableFuture<DocResult> executeAsync() {
        return this.mysqlxSession.queryAsync(this.getMessageBuilder().buildFind(this.filterParams), new DocResultBuilder(this.mysqlxSession));
    }

    @Override
    public FindStatement fields(String ... projection) {
        this.resetPrepareState();
        this.filterParams.setFields(projection);
        return this;
    }

    @Override
    public FindStatement fields(Expression docProjection) {
        this.resetPrepareState();
        ((DocFilterParams)this.filterParams).setFields(docProjection);
        return this;
    }

    @Override
    public FindStatement groupBy(String ... groupBy) {
        this.resetPrepareState();
        this.filterParams.setGrouping(groupBy);
        return this;
    }

    @Override
    public FindStatement having(String having) {
        this.resetPrepareState();
        this.filterParams.setGroupingCriteria(having);
        return this;
    }

    @Override
    public FindStatement lockShared() {
        return this.lockShared(Statement.LockContention.DEFAULT);
    }

    @Override
    public FindStatement lockShared(Statement.LockContention lockContention) {
        this.resetPrepareState();
        this.filterParams.setLock(FilterParams.RowLock.SHARED_LOCK);
        switch (lockContention) {
            case NOWAIT: {
                this.filterParams.setLockOption(FilterParams.RowLockOptions.NOWAIT);
                break;
            }
            case SKIP_LOCKED: {
                this.filterParams.setLockOption(FilterParams.RowLockOptions.SKIP_LOCKED);
                break;
            }
        }
        return this;
    }

    @Override
    public FindStatement lockExclusive() {
        return this.lockExclusive(Statement.LockContention.DEFAULT);
    }

    @Override
    public FindStatement lockExclusive(Statement.LockContention lockContention) {
        this.resetPrepareState();
        this.filterParams.setLock(FilterParams.RowLock.EXCLUSIVE_LOCK);
        switch (lockContention) {
            case NOWAIT: {
                this.filterParams.setLockOption(FilterParams.RowLockOptions.NOWAIT);
                break;
            }
            case SKIP_LOCKED: {
                this.filterParams.setLockOption(FilterParams.RowLockOptions.SKIP_LOCKED);
                break;
            }
        }
        return this;
    }

    @Override
    @Deprecated
    public FindStatement where(String searchCondition) {
        return (FindStatement)super.where(searchCondition);
    }
}

