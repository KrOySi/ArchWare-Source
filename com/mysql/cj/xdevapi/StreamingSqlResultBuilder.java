/*
 * Decompiled with CFR 0.150.
 */
package com.mysql.cj.xdevapi;

import com.mysql.cj.MysqlxSession;
import com.mysql.cj.conf.PropertySet;
import com.mysql.cj.exceptions.CJCommunicationsException;
import com.mysql.cj.protocol.ColumnDefinition;
import com.mysql.cj.protocol.ProtocolEntity;
import com.mysql.cj.protocol.ResultBuilder;
import com.mysql.cj.protocol.x.Notice;
import com.mysql.cj.protocol.x.StatementExecuteOk;
import com.mysql.cj.protocol.x.StatementExecuteOkBuilder;
import com.mysql.cj.protocol.x.XProtocol;
import com.mysql.cj.protocol.x.XProtocolRowInputStream;
import com.mysql.cj.result.Field;
import com.mysql.cj.xdevapi.SqlMultiResult;
import com.mysql.cj.xdevapi.SqlResult;
import com.mysql.cj.xdevapi.SqlResultBuilder;
import com.mysql.cj.xdevapi.SqlSingleResult;
import java.util.ArrayList;
import java.util.List;
import java.util.TimeZone;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Supplier;

public class StreamingSqlResultBuilder
implements ResultBuilder<SqlResult> {
    TimeZone defaultTimeZone;
    PropertySet pset;
    XProtocol protocol;
    StatementExecuteOkBuilder statementExecuteOkBuilder = new StatementExecuteOkBuilder();
    boolean isRowResult = false;
    ProtocolEntity lastEntity = null;
    List<SqlSingleResult> resultSets = new ArrayList<SqlSingleResult>();
    private SqlResult result;

    public StreamingSqlResultBuilder(MysqlxSession sess) {
        this.defaultTimeZone = sess.getServerSession().getDefaultTimeZone();
        this.pset = sess.getPropertySet();
        this.protocol = sess.getProtocol();
    }

    @Override
    public boolean addProtocolEntity(ProtocolEntity entity) {
        if (entity instanceof Notice) {
            this.statementExecuteOkBuilder.addProtocolEntity(entity);
        } else {
            this.lastEntity = entity;
        }
        AtomicBoolean readLastResult = new AtomicBoolean(false);
        Supplier<ProtocolEntity> okReader = () -> {
            if (readLastResult.get()) {
                throw new CJCommunicationsException("Invalid state attempting to read ok packet");
            }
            if (this.protocol.hasMoreResults()) {
                StatementExecuteOk res = this.statementExecuteOkBuilder.build();
                this.statementExecuteOkBuilder = new StatementExecuteOkBuilder();
                return res;
            }
            readLastResult.set(true);
            return this.protocol.readQueryResult(this.statementExecuteOkBuilder);
        };
        Supplier<SqlResult> resultStream = () -> {
            if (readLastResult.get()) {
                return null;
            }
            if (this.lastEntity != null && this.lastEntity instanceof Field || this.protocol.isSqlResultPending()) {
                ColumnDefinition cd;
                if (this.lastEntity != null && this.lastEntity instanceof Field) {
                    cd = this.protocol.readMetadata((Field)this.lastEntity, n -> this.statementExecuteOkBuilder.addProtocolEntity((ProtocolEntity)n));
                    this.lastEntity = null;
                } else {
                    cd = this.protocol.readMetadata();
                }
                return new SqlSingleResult(cd, this.protocol.getServerSession().getDefaultTimeZone(), new XProtocolRowInputStream(cd, this.protocol, n -> this.statementExecuteOkBuilder.addProtocolEntity((ProtocolEntity)n)), okReader, this.pset);
            }
            readLastResult.set(true);
            SqlResultBuilder rb = new SqlResultBuilder(this.defaultTimeZone, this.pset);
            rb.addProtocolEntity(entity);
            return this.protocol.readQueryResult(rb);
        };
        this.result = new SqlMultiResult(resultStream);
        return true;
    }

    @Override
    public SqlResult build() {
        return this.result;
    }
}

