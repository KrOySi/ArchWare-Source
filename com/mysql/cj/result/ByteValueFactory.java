/*
 * Decompiled with CFR 0.150.
 */
package com.mysql.cj.result;

import com.mysql.cj.Constants;
import com.mysql.cj.Messages;
import com.mysql.cj.conf.PropertyKey;
import com.mysql.cj.conf.PropertySet;
import com.mysql.cj.exceptions.NumberOutOfRange;
import com.mysql.cj.result.DefaultValueFactory;
import com.mysql.cj.result.Field;
import com.mysql.cj.util.DataTypeUtil;
import com.mysql.cj.util.StringUtils;
import java.math.BigDecimal;
import java.math.BigInteger;

public class ByteValueFactory
extends DefaultValueFactory<Byte> {
    public ByteValueFactory(PropertySet pset) {
        super(pset);
    }

    @Override
    public Byte createFromBigInteger(BigInteger i) {
        if (this.jdbcCompliantTruncationForReads && (i.compareTo(Constants.BIG_INTEGER_MIN_BYTE_VALUE) < 0 || i.compareTo(Constants.BIG_INTEGER_MAX_BYTE_VALUE) > 0)) {
            throw new NumberOutOfRange(Messages.getString("ResultSet.NumberOutOfRange", new Object[]{i, this.getTargetTypeName()}));
        }
        return (byte)i.intValue();
    }

    @Override
    public Byte createFromLong(long l) {
        if (this.jdbcCompliantTruncationForReads && (l < -128L || l > 127L)) {
            throw new NumberOutOfRange(Messages.getString("ResultSet.NumberOutOfRange", new Object[]{Long.valueOf(l).toString(), this.getTargetTypeName()}));
        }
        return (byte)l;
    }

    @Override
    public Byte createFromBigDecimal(BigDecimal d) {
        if (this.jdbcCompliantTruncationForReads && (d.compareTo(Constants.BIG_DECIMAL_MIN_BYTE_VALUE) < 0 || d.compareTo(Constants.BIG_DECIMAL_MAX_BYTE_VALUE) > 0)) {
            throw new NumberOutOfRange(Messages.getString("ResultSet.NumberOutOfRange", new Object[]{d, this.getTargetTypeName()}));
        }
        return (byte)d.longValue();
    }

    @Override
    public Byte createFromDouble(double d) {
        if (this.jdbcCompliantTruncationForReads && (d < -128.0 || d > 127.0)) {
            throw new NumberOutOfRange(Messages.getString("ResultSet.NumberOutOfRange", new Object[]{d, this.getTargetTypeName()}));
        }
        return (byte)d;
    }

    @Override
    public Byte createFromBit(byte[] bytes, int offset, int length) {
        long l = DataTypeUtil.bitToLong(bytes, offset, length);
        if (this.jdbcCompliantTruncationForReads && l >> 8 != 0L) {
            throw new NumberOutOfRange(Messages.getString("ResultSet.NumberOutOfRange", new Object[]{Long.valueOf(l).toString(), this.getTargetTypeName()}));
        }
        return (byte)l;
    }

    @Override
    public Byte createFromYear(long l) {
        return this.createFromLong(l);
    }

    @Override
    public String getTargetTypeName() {
        return Byte.class.getName();
    }

    @Override
    public Byte createFromBytes(byte[] bytes, int offset, int length, Field f) {
        if (length == 0 && this.pset.getBooleanProperty(PropertyKey.emptyStringsConvertToZero).getValue().booleanValue()) {
            return (byte)0;
        }
        String s = StringUtils.toString(bytes, offset, length, f.getEncoding());
        byte[] newBytes = s.getBytes();
        if (this.jdbcCompliantTruncationForReads && newBytes.length != 1) {
            throw new NumberOutOfRange(Messages.getString("ResultSet.NumberOutOfRange", new Object[]{s, this.getTargetTypeName()}));
        }
        return newBytes[0];
    }
}

