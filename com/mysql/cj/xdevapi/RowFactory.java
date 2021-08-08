/*
 * Decompiled with CFR 0.150.
 */
package com.mysql.cj.xdevapi;

import com.mysql.cj.conf.PropertySet;
import com.mysql.cj.protocol.ColumnDefinition;
import com.mysql.cj.protocol.ProtocolEntity;
import com.mysql.cj.protocol.ProtocolEntityFactory;
import com.mysql.cj.protocol.x.XMessage;
import com.mysql.cj.xdevapi.Row;
import com.mysql.cj.xdevapi.RowImpl;
import java.util.TimeZone;

public class RowFactory
implements ProtocolEntityFactory<Row, XMessage> {
    private ColumnDefinition metadata;
    private TimeZone defaultTimeZone;
    private PropertySet pset;

    public RowFactory(ColumnDefinition metadata, TimeZone defaultTimeZone, PropertySet pset) {
        this.metadata = metadata;
        this.defaultTimeZone = defaultTimeZone;
        this.pset = pset;
    }

    @Override
    public Row createFromProtocolEntity(ProtocolEntity internalRow) {
        return new RowImpl((com.mysql.cj.result.Row)internalRow, this.metadata, this.defaultTimeZone, this.pset);
    }
}

