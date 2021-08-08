/*
 * Decompiled with CFR 0.150.
 */
package com.mysql.cj.result;

import com.mysql.cj.Constants;
import com.mysql.cj.Messages;
import com.mysql.cj.conf.PropertySet;
import com.mysql.cj.exceptions.NumberOutOfRange;
import com.mysql.cj.result.AbstractNumericValueFactory;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.nio.ByteBuffer;

public class FloatValueFactory
extends AbstractNumericValueFactory<Float> {
    public FloatValueFactory(PropertySet pset) {
        super(pset);
    }

    @Override
    public Float createFromBigInteger(BigInteger i) {
        if (this.jdbcCompliantTruncationForReads && (new BigDecimal(i).compareTo(Constants.BIG_DECIMAL_MAX_NEGATIVE_FLOAT_VALUE) < 0 || new BigDecimal(i).compareTo(Constants.BIG_DECIMAL_MAX_FLOAT_VALUE) > 0)) {
            throw new NumberOutOfRange(Messages.getString("ResultSet.NumberOutOfRange", new Object[]{i, this.getTargetTypeName()}));
        }
        return Float.valueOf((float)i.doubleValue());
    }

    @Override
    public Float createFromLong(long l) {
        if (this.jdbcCompliantTruncationForReads && ((float)l < -3.4028235E38f || (float)l > Float.MAX_VALUE)) {
            throw new NumberOutOfRange(Messages.getString("ResultSet.NumberOutOfRange", new Object[]{l, this.getTargetTypeName()}));
        }
        return Float.valueOf(l);
    }

    @Override
    public Float createFromBigDecimal(BigDecimal d) {
        if (this.jdbcCompliantTruncationForReads && (d.compareTo(Constants.BIG_DECIMAL_MAX_NEGATIVE_FLOAT_VALUE) < 0 || d.compareTo(Constants.BIG_DECIMAL_MAX_FLOAT_VALUE) > 0)) {
            throw new NumberOutOfRange(Messages.getString("ResultSet.NumberOutOfRange", new Object[]{d, this.getTargetTypeName()}));
        }
        return Float.valueOf((float)d.doubleValue());
    }

    @Override
    public Float createFromDouble(double d) {
        if (this.jdbcCompliantTruncationForReads && (d < -3.4028234663852886E38 || d > 3.4028234663852886E38)) {
            throw new NumberOutOfRange(Messages.getString("ResultSet.NumberOutOfRange", new Object[]{d, this.getTargetTypeName()}));
        }
        return Float.valueOf((float)d);
    }

    @Override
    public Float createFromBit(byte[] bytes, int offset, int length) {
        return Float.valueOf(new BigInteger(ByteBuffer.allocate(length + 1).put((byte)0).put(bytes, offset, length).array()).floatValue());
    }

    @Override
    public String getTargetTypeName() {
        return Float.class.getName();
    }
}

