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

public class IntegerValueFactory
extends AbstractNumericValueFactory<Integer> {
    public IntegerValueFactory(PropertySet pset) {
        super(pset);
    }

    @Override
    public Integer createFromBigInteger(BigInteger i) {
        if (this.jdbcCompliantTruncationForReads && (i.compareTo(Constants.BIG_INTEGER_MIN_INTEGER_VALUE) < 0 || i.compareTo(Constants.BIG_INTEGER_MAX_INTEGER_VALUE) > 0)) {
            throw new NumberOutOfRange(Messages.getString("ResultSet.NumberOutOfRange", new Object[]{i, this.getTargetTypeName()}));
        }
        return i.intValue();
    }

    @Override
    public Integer createFromLong(long l) {
        if (this.jdbcCompliantTruncationForReads && (l < Integer.MIN_VALUE || l > Integer.MAX_VALUE)) {
            throw new NumberOutOfRange(Messages.getString("ResultSet.NumberOutOfRange", new Object[]{Long.valueOf(l).toString(), this.getTargetTypeName()}));
        }
        return (int)l;
    }

    @Override
    public Integer createFromBigDecimal(BigDecimal d) {
        if (this.jdbcCompliantTruncationForReads && (d.compareTo(Constants.BIG_DECIMAL_MIN_INTEGER_VALUE) < 0 || d.compareTo(Constants.BIG_DECIMAL_MAX_INTEGER_VALUE) > 0)) {
            throw new NumberOutOfRange(Messages.getString("ResultSet.NumberOutOfRange", new Object[]{d, this.getTargetTypeName()}));
        }
        return (int)d.longValue();
    }

    @Override
    public Integer createFromDouble(double d) {
        if (this.jdbcCompliantTruncationForReads && (d < -2.147483648E9 || d > 2.147483647E9)) {
            throw new NumberOutOfRange(Messages.getString("ResultSet.NumberOutOfRange", new Object[]{d, this.getTargetTypeName()}));
        }
        return (int)d;
    }

    @Override
    public Integer createFromBit(byte[] bytes, int offset, int length) {
        long l = DataTypeUtil.bitToLong(bytes, offset, length);
        if (this.jdbcCompliantTruncationForReads && l >> 32 != 0L) {
            throw new NumberOutOfRange(Messages.getString("ResultSet.NumberOutOfRange", new Object[]{Long.valueOf(l).toString(), this.getTargetTypeName()}));
        }
        return (int)l;
    }

    @Override
    public String getTargetTypeName() {
        return Integer.class.getName();
    }
}

