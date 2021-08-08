/*
 * Decompiled with CFR 0.150.
 */
package com.mysql.cj.result;

import com.mysql.cj.Messages;
import com.mysql.cj.conf.PropertyDefinitions;
import com.mysql.cj.conf.PropertyKey;
import com.mysql.cj.conf.PropertySet;
import com.mysql.cj.exceptions.DataConversionException;
import com.mysql.cj.protocol.InternalDate;
import com.mysql.cj.protocol.InternalTime;
import com.mysql.cj.protocol.InternalTimestamp;
import com.mysql.cj.protocol.a.MysqlTextValueDecoder;
import com.mysql.cj.result.DefaultValueFactory;
import com.mysql.cj.result.Field;
import com.mysql.cj.util.StringUtils;

public abstract class AbstractDateTimeValueFactory<T>
extends DefaultValueFactory<T> {
    public AbstractDateTimeValueFactory(PropertySet pset) {
        super(pset);
    }

    abstract T localCreateFromDate(InternalDate var1);

    abstract T localCreateFromTime(InternalTime var1);

    abstract T localCreateFromTimestamp(InternalTimestamp var1);

    abstract T localCreateFromDatetime(InternalTimestamp var1);

    @Override
    public T createFromDate(InternalDate idate) {
        if (idate.isZero()) {
            switch ((PropertyDefinitions.ZeroDatetimeBehavior)((Object)this.pset.getEnumProperty(PropertyKey.zeroDateTimeBehavior).getValue())) {
                case CONVERT_TO_NULL: {
                    return null;
                }
                case ROUND: {
                    return this.localCreateFromDate(new InternalDate(1, 1, 1));
                }
            }
        }
        return this.localCreateFromDate(idate);
    }

    @Override
    public T createFromTime(InternalTime it) {
        return this.localCreateFromTime(it);
    }

    @Override
    public T createFromTimestamp(InternalTimestamp its) {
        if (its.isZero()) {
            switch ((PropertyDefinitions.ZeroDatetimeBehavior)((Object)this.pset.getEnumProperty(PropertyKey.zeroDateTimeBehavior).getValue())) {
                case CONVERT_TO_NULL: {
                    return null;
                }
                case ROUND: {
                    return this.localCreateFromTimestamp(new InternalTimestamp(1, 1, 1, 0, 0, 0, 0, 0));
                }
            }
        }
        return this.localCreateFromTimestamp(its);
    }

    @Override
    public T createFromDatetime(InternalTimestamp its) {
        if (its.isZero()) {
            switch ((PropertyDefinitions.ZeroDatetimeBehavior)((Object)this.pset.getEnumProperty(PropertyKey.zeroDateTimeBehavior).getValue())) {
                case CONVERT_TO_NULL: {
                    return null;
                }
                case ROUND: {
                    return this.localCreateFromDatetime(new InternalTimestamp(1, 1, 1, 0, 0, 0, 0, 0));
                }
            }
        }
        return this.localCreateFromDatetime(its);
    }

    @Override
    public T createFromYear(long year) {
        if (this.pset.getBooleanProperty(PropertyKey.yearIsDateType).getValue().booleanValue()) {
            if (year < 100L) {
                if (year <= 69L) {
                    year += 100L;
                }
                year += 1900L;
            }
            return this.createFromDate(new InternalDate((int)year, 1, 1));
        }
        return this.createFromLong(year);
    }

    @Override
    public T createFromBytes(byte[] bytes, int offset, int length, Field f) {
        if (length == 0 && this.pset.getBooleanProperty(PropertyKey.emptyStringsConvertToZero).getValue().booleanValue()) {
            return this.createFromLong(0L);
        }
        String s = StringUtils.toString(bytes, offset, length, f.getEncoding());
        byte[] newBytes = s.getBytes();
        if (MysqlTextValueDecoder.isDate(s)) {
            return this.createFromDate(MysqlTextValueDecoder.getDate(newBytes, 0, newBytes.length));
        }
        if (MysqlTextValueDecoder.isTime(s)) {
            return this.createFromTime(MysqlTextValueDecoder.getTime(newBytes, 0, newBytes.length, f.getDecimals()));
        }
        if (MysqlTextValueDecoder.isTimestamp(s)) {
            return this.createFromTimestamp(MysqlTextValueDecoder.getTimestamp(newBytes, 0, newBytes.length, f.getDecimals()));
        }
        throw new DataConversionException(Messages.getString("ResultSet.UnableToConvertString", new Object[]{s, this.getTargetTypeName()}));
    }
}

