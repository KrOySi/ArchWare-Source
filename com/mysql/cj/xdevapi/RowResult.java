/*
 * Decompiled with CFR 0.150.
 */
package com.mysql.cj.xdevapi;

import com.mysql.cj.xdevapi.Column;
import com.mysql.cj.xdevapi.FetchResult;
import com.mysql.cj.xdevapi.Result;
import com.mysql.cj.xdevapi.Row;
import java.util.List;

public interface RowResult
extends FetchResult<Row>,
Result {
    public int getColumnCount();

    public List<Column> getColumns();

    public List<String> getColumnNames();
}

