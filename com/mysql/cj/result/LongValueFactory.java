/*
 * Decompiled with CFR 0.150.
 */
package com.mysql.cj.result;

import com.mysql.cj.Constants;
import com.mysql.cj.Messages;
import com.mysql.cj.conf.PropertySet;
import com.mysql.cj.exceptions.NumberOutOfRange;
import com.mysql.cj.result.AbstractNumericValueFactory;
import com.mysql.cj.util.DataTypeUtil;
import java.math.BigDecimal;
import java.math.BigInteger;

public class LongValueFactory
extends AbstractNumericValueFactory<Long> {
    public LongValueFactory(PropertySet pset) {
        super(pset);
    }

    @Override
    public Long createFromBigInteger(BigInteger i) {
        if (this.jdbcCompliantTruncationForReads && (i.compareTo(Constants.BIG_INTEGER_MIN_LONG_VALUE) < 0 || i.compareTo(Constants.BIG_INTEGER_MAX_LONG_VALUE) > 0)) {
            throw new NumberOutOfRange(Messages.getString("ResultSet.NumberOutOfRange", new Object[]{i, this.getTargetTypeName()}));
        }
        return i.longValue();
    }

    @Override
    public Long createFromLong(long l) {
        if (this.jdbcCompliantTruncationForReads && (l < Long.MIN_VALUE || l > Long.MAX_VALUE)) {
            throw new NumberOutOfRange(Messages.getString("ResultSet.NumberOutOfRange", new Object[]{Long.valueOf(l).toString(), this.getTargetTypeName()}));
        }
        return l;
    }

    @Override
    public Long createFromBigDecimal(BigDecimal d) {
        if (this.jdbcCompliantTruncationForReads && (d.compareTo(Constants.BIG_DECIMAL_MIN_LONG_VALUE) < 0 || d.compareTo(Constants.BIG_DECIMAL_MAX_LONG_VALUE) > 0)) {
            throw new NumberOutOfRange(Messages.getString("ResultSet.NumberOutOfRange", new Object[]{d, this.getTargetTypeName()}));
        }
        return d.longValue();
    }

    @Override
    public Long createFromDouble(double d) {
        if (this.jdbcCompliantTruncationForReads && (d < -9.223372036854776E18 || d > 9.223372036854776E18)) {
            throw new NumberOutOfRange(Messages.getString("ResultSet.NumberOutOfRange", new Object[]{d, this.getTargetTypeName()}));
        }
        return (long)d;
    }

    @Override
    public Long createFromBit(byte[] bytes, int offset, int length) {
        return DataTypeUtil.bitToLong(bytes, offset, length);
    }

    @Override
    public String getTargetTypeName() {
        return Long.class.getName();
    }
}

