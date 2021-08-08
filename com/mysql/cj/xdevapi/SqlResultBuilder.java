/*
 * Decompiled with CFR 0.150.
 */
package com.mysql.cj.xdevapi;

import com.mysql.cj.MysqlxSession;
import com.mysql.cj.conf.PropertySet;
import com.mysql.cj.protocol.ColumnDefinition;
import com.mysql.cj.protocol.ProtocolEntity;
import com.mysql.cj.protocol.ResultBuilder;
import com.mysql.cj.protocol.x.FetchDoneEntity;
import com.mysql.cj.protocol.x.FetchDoneMoreResults;
import com.mysql.cj.protocol.x.Notice;
import com.mysql.cj.protocol.x.StatementExecuteOk;
import com.mysql.cj.protocol.x.StatementExecuteOkBuilder;
import com.mysql.cj.result.BufferedRowList;
import com.mysql.cj.result.DefaultColumnDefinition;
import com.mysql.cj.result.Field;
import com.mysql.cj.result.Row;
import com.mysql.cj.xdevapi.SqlMultiResult;
import com.mysql.cj.xdevapi.SqlResult;
import com.mysql.cj.xdevapi.SqlSingleResult;
import com.mysql.cj.xdevapi.SqlUpdateResult;
import java.util.ArrayList;
import java.util.List;
import java.util.TimeZone;

public class SqlResultBuilder
implements ResultBuilder<SqlResult> {
    private ArrayList<Field> fields = new ArrayList();
    private ColumnDefinition metadata;
    private List<Row> rows = new ArrayList<Row>();
    TimeZone defaultTimeZone;
    PropertySet pset;
    boolean isRowResult = false;
    List<SqlSingleResult> resultSets = new ArrayList<SqlSingleResult>();
    private ProtocolEntity prevEntity = null;
    private StatementExecuteOkBuilder statementExecuteOkBuilder = new StatementExecuteOkBuilder();

    public SqlResultBuilder(TimeZone defaultTimeZone, PropertySet pset) {
        this.defaultTimeZone = defaultTimeZone;
        this.pset = pset;
    }

    public SqlResultBuilder(MysqlxSession sess) {
        this.defaultTimeZone = sess.getServerSession().getDefaultTimeZone();
        this.pset = sess.getPropertySet();
    }

    @Override
    public boolean addProtocolEntity(ProtocolEntity entity) {
        if (entity instanceof Field) {
            this.fields.add((Field)entity);
            if (!this.isRowResult) {
                this.isRowResult = true;
            }
            this.prevEntity = entity;
            return false;
        }
        if (entity instanceof Notice) {
            this.statementExecuteOkBuilder.addProtocolEntity(entity);
            return false;
        }
        if (this.isRowResult && this.metadata == null) {
            this.metadata = new DefaultColumnDefinition(this.fields.toArray(new Field[0]));
        }
        if (entity instanceof Row) {
            this.rows.add(((Row)entity).setMetadata(this.metadata));
        } else if (entity instanceof FetchDoneMoreResults) {
            this.resultSets.add(new SqlSingleResult(this.metadata, this.defaultTimeZone, new BufferedRowList(this.rows), () -> this.statementExecuteOkBuilder.build(), this.pset));
            this.fields = new ArrayList();
            this.metadata = null;
            this.rows = new ArrayList<Row>();
            this.statementExecuteOkBuilder = new StatementExecuteOkBuilder();
        } else if (entity instanceof FetchDoneEntity) {
            if (!(this.prevEntity instanceof FetchDoneMoreResults)) {
                this.resultSets.add(new SqlSingleResult(this.metadata, this.defaultTimeZone, new BufferedRowList(this.rows), () -> this.statementExecuteOkBuilder.build(), this.pset));
            }
        } else if (entity instanceof StatementExecuteOk) {
            return true;
        }
        this.prevEntity = entity;
        return false;
    }

    @Override
    public SqlResult build() {
        return this.isRowResult ? new SqlMultiResult(() -> this.resultSets.size() > 0 ? (SqlResult)this.resultSets.remove(0) : null) : new SqlUpdateResult(this.statementExecuteOkBuilder.build());
    }
}

