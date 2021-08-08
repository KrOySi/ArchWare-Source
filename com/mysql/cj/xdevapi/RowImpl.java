/*
 * Decompiled with CFR 0.150.
 */
package com.mysql.cj.xdevapi;

import com.mysql.cj.conf.PropertySet;
import com.mysql.cj.exceptions.DataReadException;
import com.mysql.cj.protocol.ColumnDefinition;
import com.mysql.cj.result.BigDecimalValueFactory;
import com.mysql.cj.result.BooleanValueFactory;
import com.mysql.cj.result.ByteValueFactory;
import com.mysql.cj.result.DoubleValueFactory;
import com.mysql.cj.result.IntegerValueFactory;
import com.mysql.cj.result.LongValueFactory;
import com.mysql.cj.result.SqlDateValueFactory;
import com.mysql.cj.result.SqlTimeValueFactory;
import com.mysql.cj.result.SqlTimestampValueFactory;
import com.mysql.cj.result.StringValueFactory;
import com.mysql.cj.xdevapi.DbDoc;
import com.mysql.cj.xdevapi.DbDocValueFactory;
import com.mysql.cj.xdevapi.Row;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.TimeZone;

public class RowImpl
implements Row {
    private com.mysql.cj.result.Row row;
    private ColumnDefinition metadata;
    private TimeZone defaultTimeZone;
    private PropertySet pset;

    public RowImpl(com.mysql.cj.result.Row row, ColumnDefinition metadata, TimeZone defaultTimeZone, PropertySet pset) {
        this.row = row;
        this.metadata = metadata;
        this.defaultTimeZone = defaultTimeZone;
        this.pset = pset;
    }

    private int fieldNameToIndex(String fieldName) {
        int idx = this.metadata.findColumn(fieldName, true, 0);
        if (idx == -1) {
            throw new DataReadException("Invalid column");
        }
        return idx;
    }

    @Override
    public BigDecimal getBigDecimal(String fieldName) {
        return this.getBigDecimal(this.fieldNameToIndex(fieldName));
    }

    @Override
    public BigDecimal getBigDecimal(int pos) {
        return this.row.getValue(pos, new BigDecimalValueFactory(this.pset));
    }

    @Override
    public boolean getBoolean(String fieldName) {
        return this.getBoolean(this.fieldNameToIndex(fieldName));
    }

    @Override
    public boolean getBoolean(int pos) {
        Boolean res = this.row.getValue(pos, new BooleanValueFactory(this.pset));
        return res == null ? false : res;
    }

    @Override
    public byte getByte(String fieldName) {
        return this.getByte(this.fieldNameToIndex(fieldName));
    }

    @Override
    public byte getByte(int pos) {
        Byte res = this.row.getValue(pos, new ByteValueFactory(this.pset));
        return res == null ? (byte)0 : res;
    }

    @Override
    public Date getDate(String fieldName) {
        return this.getDate(this.fieldNameToIndex(fieldName));
    }

    @Override
    public Date getDate(int pos) {
        return this.row.getValue(pos, new SqlDateValueFactory(this.pset, null, this.defaultTimeZone));
    }

    @Override
    public DbDoc getDbDoc(String fieldName) {
        return this.getDbDoc(this.fieldNameToIndex(fieldName));
    }

    @Override
    public DbDoc getDbDoc(int pos) {
        return this.row.getValue(pos, new DbDocValueFactory(this.pset));
    }

    @Override
    public double getDouble(String fieldName) {
        return this.getDouble(this.fieldNameToIndex(fieldName));
    }

    @Override
    public double getDouble(int pos) {
        Double res = this.row.getValue(pos, new DoubleValueFactory(this.pset));
        return res == null ? 0.0 : res;
    }

    @Override
    public int getInt(String fieldName) {
        return this.getInt(this.fieldNameToIndex(fieldName));
    }

    @Override
    public int getInt(int pos) {
        Integer res = this.row.getValue(pos, new IntegerValueFactory(this.pset));
        return res == null ? 0 : res;
    }

    @Override
    public long getLong(String fieldName) {
        return this.getLong(this.fieldNameToIndex(fieldName));
    }

    @Override
    public long getLong(int pos) {
        Long res = this.row.getValue(pos, new LongValueFactory(this.pset));
        return res == null ? 0L : res;
    }

    @Override
    public String getString(String fieldName) {
        return this.getString(this.fieldNameToIndex(fieldName));
    }

    @Override
    public String getString(int pos) {
        return this.row.getValue(pos, new StringValueFactory(this.pset));
    }

    @Override
    public Time getTime(String fieldName) {
        return this.getTime(this.fieldNameToIndex(fieldName));
    }

    @Override
    public Time getTime(int pos) {
        return this.row.getValue(pos, new SqlTimeValueFactory(this.pset, null, this.defaultTimeZone));
    }

    @Override
    public Timestamp getTimestamp(String fieldName) {
        return this.getTimestamp(this.fieldNameToIndex(fieldName));
    }

    @Override
    public Timestamp getTimestamp(int pos) {
        return this.row.getValue(pos, new SqlTimestampValueFactory(this.pset, null, this.defaultTimeZone, this.defaultTimeZone));
    }
}

