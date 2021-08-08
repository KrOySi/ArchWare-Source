/*
 * Decompiled with CFR 0.150.
 */
package com.mysql.cj.xdevapi;

import com.mysql.cj.MysqlxSession;
import com.mysql.cj.exceptions.FeatureNotAvailableException;
import com.mysql.cj.xdevapi.SqlResult;
import com.mysql.cj.xdevapi.SqlResultBuilder;
import com.mysql.cj.xdevapi.SqlStatement;
import com.mysql.cj.xdevapi.StreamingSqlResultBuilder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

public class SqlStatementImpl
implements SqlStatement {
    private MysqlxSession mysqlxSession;
    private String sql;
    private List<Object> args = new ArrayList<Object>();

    public SqlStatementImpl(MysqlxSession mysqlxSession, String sql) {
        this.mysqlxSession = mysqlxSession;
        this.sql = sql;
    }

    @Override
    public SqlResult execute() {
        return this.mysqlxSession.query(this.mysqlxSession.getMessageBuilder().buildSqlStatement(this.sql, this.args), new StreamingSqlResultBuilder(this.mysqlxSession));
    }

    @Override
    public CompletableFuture<SqlResult> executeAsync() {
        return this.mysqlxSession.queryAsync(this.mysqlxSession.getMessageBuilder().buildSqlStatement(this.sql, this.args), new SqlResultBuilder(this.mysqlxSession));
    }

    @Override
    public SqlStatement clearBindings() {
        this.args.clear();
        return this;
    }

    @Override
    public SqlStatement bind(List<Object> values) {
        this.args.addAll(values);
        return this;
    }

    @Override
    public SqlStatement bind(Map<String, Object> values) {
        throw new FeatureNotAvailableException("Cannot bind named parameters for SQL statements");
    }
}

