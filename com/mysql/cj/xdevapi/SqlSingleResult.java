/*
 * Decompiled with CFR 0.150.
 */
package com.mysql.cj.xdevapi;

import com.mysql.cj.conf.PropertySet;
import com.mysql.cj.protocol.ColumnDefinition;
import com.mysql.cj.protocol.ProtocolEntity;
import com.mysql.cj.result.RowList;
import com.mysql.cj.xdevapi.RowResultImpl;
import com.mysql.cj.xdevapi.SqlResult;
import java.util.TimeZone;
import java.util.function.Supplier;

public class SqlSingleResult
extends RowResultImpl
implements SqlResult {
    public SqlSingleResult(ColumnDefinition metadata, TimeZone defaultTimeZone, RowList rows, Supplier<ProtocolEntity> completer, PropertySet pset) {
        super(metadata, defaultTimeZone, rows, completer, pset);
    }
}

