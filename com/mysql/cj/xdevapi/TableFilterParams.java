/*
 * Decompiled with CFR 0.150.
 */
package com.mysql.cj.xdevapi;

import com.mysql.cj.xdevapi.AbstractFilterParams;
import com.mysql.cj.xdevapi.ExprParser;
import java.util.Arrays;
import java.util.stream.Collectors;

public class TableFilterParams
extends AbstractFilterParams {
    public TableFilterParams(String schemaName, String collectionName) {
        this(schemaName, collectionName, true);
    }

    public TableFilterParams(String schemaName, String collectionName, boolean supportsOffset) {
        super(schemaName, collectionName, supportsOffset, true);
    }

    @Override
    public void setFields(String ... projection) {
        this.projection = projection;
        this.fields = new ExprParser(Arrays.stream(projection).collect(Collectors.joining(", ")), true).parseTableSelectProjection();
    }
}

