/*
 * Decompiled with CFR 0.150.
 */
package com.mysql.cj.result;

import com.mysql.cj.Constants;
import com.mysql.cj.Messages;
import com.mysql.cj.conf.PropertyKey;
import com.mysql.cj.conf.PropertySet;
import com.mysql.cj.exceptions.DataConversionException;
import com.mysql.cj.protocol.a.MysqlTextValueDecoder;
import com.mysql.cj.result.DefaultValueFactory;
import com.mysql.cj.result.Field;
import com.mysql.cj.util.DataTypeUtil;
import com.mysql.cj.util.StringUtils;
import java.math.BigDecimal;
import java.math.BigInteger;

public class BooleanValueFactory
extends DefaultValueFactory<Boolean> {
    public BooleanValueFactory(PropertySet pset) {
        super(pset);
    }

    @Override
    public Boolean createFromLong(long l) {
        return l == -1L || l > 0L;
    }

    @Override
    public Boolean createFromBigInteger(BigInteger i) {
        return i.compareTo(Constants.BIG_INTEGER_ZERO) > 0 || i.compareTo(Constants.BIG_INTEGER_NEGATIVE_ONE) == 0;
    }

    @Override
    public Boolean createFromDouble(double d) {
        return d > 0.0 || d == -1.0;
    }

    @Override
    public Boolean createFromBigDecimal(BigDecimal d) {
        return d.compareTo(Constants.BIG_DECIMAL_ZERO) > 0 || d.compareTo(Constants.BIG_DECIMAL_NEGATIVE_ONE) == 0;
    }

    @Override
    public Boolean createFromBit(byte[] bytes, int offset, int length) {
        return this.createFromLong(DataTypeUtil.bitToLong(bytes, offset, length));
    }

    @Override
    public Boolean createFromYear(long l) {
        return this.createFromLong(l);
    }

    @Override
    public String getTargetTypeName() {
        return Boolean.class.getName();
    }

    @Override
    public Boolean createFromBytes(byte[] bytes, int offset, int length, Field f) {
        if (length == 0 && this.pset.getBooleanProperty(PropertyKey.emptyStringsConvertToZero).getValue().booleanValue()) {
            return this.createFromLong(0L);
        }
        String s = StringUtils.toString(bytes, offset, length, f.getEncoding());
        byte[] newBytes = s.getBytes();
        if (s.equalsIgnoreCase("Y") || s.equalsIgnoreCase("true")) {
            return this.createFromLong(1L);
        }
        if (s.equalsIgnoreCase("N") || s.equalsIgnoreCase("false")) {
            return this.createFromLong(0L);
        }
        if (s.contains("e") || s.contains("E") || s.matches("-?\\d*\\.\\d*")) {
            return this.createFromDouble(MysqlTextValueDecoder.getDouble(newBytes, 0, newBytes.length));
        }
        if (s.matches("-?\\d+")) {
            if (s.charAt(0) == '-' || length <= 19 && newBytes[0] >= 48 && newBytes[0] <= 56) {
                return this.createFromLong(MysqlTextValueDecoder.getLong(newBytes, 0, newBytes.length));
            }
            return this.createFromBigInteger(MysqlTextValueDecoder.getBigInteger(newBytes, 0, newBytes.length));
        }
        throw new DataConversionException(Messages.getString("ResultSet.UnableToInterpretString", new Object[]{s}));
    }
}

