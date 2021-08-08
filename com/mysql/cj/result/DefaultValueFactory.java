/*
 * Decompiled with CFR 0.150.
 */
package com.mysql.cj.result;

import com.mysql.cj.Messages;
import com.mysql.cj.conf.PropertyKey;
import com.mysql.cj.conf.PropertySet;
import com.mysql.cj.exceptions.DataConversionException;
import com.mysql.cj.protocol.InternalDate;
import com.mysql.cj.protocol.InternalTime;
import com.mysql.cj.protocol.InternalTimestamp;
import com.mysql.cj.result.ValueFactory;
import java.math.BigDecimal;
import java.math.BigInteger;

public abstract class DefaultValueFactory<T>
implements ValueFactory<T> {
    protected boolean jdbcCompliantTruncationForReads = true;
    protected PropertySet pset = null;

    public DefaultValueFactory(PropertySet pset) {
        this.pset = pset;
        this.jdbcCompliantTruncationForReads = this.pset.getBooleanProperty(PropertyKey.jdbcCompliantTruncation).getInitialValue();
    }

    @Override
    public void setPropertySet(PropertySet pset) {
        this.pset = pset;
    }

    protected T unsupported(String sourceType) {
        throw new DataConversionException(Messages.getString("ResultSet.UnsupportedConversion", new Object[]{sourceType, this.getTargetTypeName()}));
    }

    @Override
    public T createFromDate(InternalDate idate) {
        return this.unsupported("DATE");
    }

    @Override
    public T createFromTime(InternalTime it) {
        return this.unsupported("TIME");
    }

    @Override
    public T createFromTimestamp(InternalTimestamp its) {
        return this.unsupported("TIMESTAMP");
    }

    @Override
    public T createFromDatetime(InternalTimestamp its) {
        return this.unsupported("DATETIME");
    }

    @Override
    public T createFromLong(long l) {
        return this.unsupported("LONG");
    }

    @Override
    public T createFromBigInteger(BigInteger i) {
        return this.unsupported("BIGINT");
    }

    @Override
    public T createFromDouble(double d) {
        return this.unsupported("DOUBLE");
    }

    @Override
    public T createFromBigDecimal(BigDecimal d) {
        return this.unsupported("DECIMAL");
    }

    @Override
    public T createFromBit(byte[] bytes, int offset, int length) {
        return this.unsupported("BIT");
    }

    @Override
    public T createFromYear(long l) {
        return this.unsupported("YEAR");
    }

    @Override
    public T createFromNull() {
        return null;
    }
}

