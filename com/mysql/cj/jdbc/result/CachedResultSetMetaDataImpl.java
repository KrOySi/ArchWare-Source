/*
 * Decompiled with CFR 0.150.
 */
package com.mysql.cj.jdbc.result;

import com.mysql.cj.jdbc.result.CachedResultSetMetaData;
import com.mysql.cj.result.DefaultColumnDefinition;
import java.sql.ResultSetMetaData;

public class CachedResultSetMetaDataImpl
extends DefaultColumnDefinition
implements CachedResultSetMetaData {
    ResultSetMetaData metadata;

    @Override
    public ResultSetMetaData getMetadata() {
        return this.metadata;
    }

    @Override
    public void setMetadata(ResultSetMetaData metadata) {
        this.metadata = metadata;
    }
}

