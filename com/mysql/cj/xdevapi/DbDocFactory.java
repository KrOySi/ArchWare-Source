/*
 * Decompiled with CFR 0.150.
 */
package com.mysql.cj.xdevapi;

import com.mysql.cj.conf.PropertySet;
import com.mysql.cj.protocol.ProtocolEntity;
import com.mysql.cj.protocol.ProtocolEntityFactory;
import com.mysql.cj.protocol.x.XMessage;
import com.mysql.cj.result.Row;
import com.mysql.cj.xdevapi.DbDoc;
import com.mysql.cj.xdevapi.DbDocValueFactory;

public class DbDocFactory
implements ProtocolEntityFactory<DbDoc, XMessage> {
    private PropertySet pset;

    public DbDocFactory(PropertySet pset) {
        this.pset = pset;
    }

    @Override
    public DbDoc createFromProtocolEntity(ProtocolEntity internalRow) {
        return ((Row)internalRow).getValue(0, new DbDocValueFactory(this.pset));
    }
}

