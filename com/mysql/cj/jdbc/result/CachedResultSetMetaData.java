/*
 * Decompiled with CFR 0.150.
 */
package com.mysql.cj.jdbc.result;

import com.mysql.cj.protocol.ColumnDefinition;
import java.sql.ResultSetMetaData;

public interface CachedResultSetMetaData
extends ColumnDefinition {
    public ResultSetMetaData getMetadata();

    public void setMetadata(ResultSetMetaData var1);
}

