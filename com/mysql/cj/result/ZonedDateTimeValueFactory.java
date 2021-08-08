/*
 * Decompiled with CFR 0.150.
 */
package com.mysql.cj.result;

import com.mysql.cj.Messages;
import com.mysql.cj.conf.PropertyKey;
import com.mysql.cj.conf.PropertySet;
import com.mysql.cj.exceptions.DataConversionException;
import com.mysql.cj.exceptions.DataReadException;
import com.mysql.cj.protocol.InternalDate;
import com.mysql.cj.protocol.InternalTime;
import com.mysql.cj.protocol.InternalTimestamp;
import com.mysql.cj.protocol.a.MysqlTextValueDecoder;
import com.mysql.cj.result.AbstractDateTimeValueFactory;
import com.mysql.cj.result.Field;
import com.mysql.cj.util.StringUtils;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeParseException;
import java.util.TimeZone;

public class ZonedDateTimeValueFactory
extends AbstractDateTimeValueFactory<ZonedDateTime> {
    private TimeZone defaultTimeZone;
    private TimeZone connectionTimeZone;

    public ZonedDateTimeValueFactory(PropertySet pset, TimeZone defaultTimeZone, TimeZone connectionTimeZone) {
        super(pset);
        this.defaultTimeZone = defaultTimeZone;
        this.connectionTimeZone = connectionTimeZone;
    }

    @Override
    public ZonedDateTime localCreateFromDate(InternalDate idate) {
        if (idate.getYear() == 0 && idate.getMonth() == 0 && idate.getDay() == 0) {
            throw new DataReadException(Messages.getString("ResultSet.InvalidZeroDate"));
        }
        return LocalDateTime.of(idate.getYear(), idate.getMonth(), idate.getDay(), 0, 0, 0, 0).atZone(this.defaultTimeZone.toZoneId());
    }

    @Override
    public ZonedDateTime localCreateFromTime(InternalTime it) {
        if (it.getHours() < 0 || it.getHours() >= 24) {
            throw new DataReadException(Messages.getString("ResultSet.InvalidTimeValue", new Object[]{it.toString()}));
        }
        return LocalDateTime.of(1970, 1, 1, it.getHours(), it.getMinutes(), it.getSeconds(), it.getNanos()).atZone(this.defaultTimeZone.toZoneId());
    }

    @Override
    public ZonedDateTime localCreateFromTimestamp(InternalTimestamp its) {
        if (its.getYear() == 0 && its.getMonth() == 0 && its.getDay() == 0) {
            throw new DataReadException(Messages.getString("ResultSet.InvalidZeroDate"));
        }
        return LocalDateTime.of(its.getYear(), its.getMonth(), its.getDay(), its.getHours(), its.getMinutes(), its.getSeconds(), its.getNanos()).atZone((this.pset.getBooleanProperty(PropertyKey.preserveInstants).getValue() != false ? this.connectionTimeZone : this.defaultTimeZone).toZoneId());
    }

    @Override
    public ZonedDateTime localCreateFromDatetime(InternalTimestamp its) {
        if (its.getYear() == 0 && its.getMonth() == 0 && its.getDay() == 0) {
            throw new DataReadException(Messages.getString("ResultSet.InvalidZeroDate"));
        }
        return LocalDateTime.of(its.getYear(), its.getMonth(), its.getDay(), its.getHours(), its.getMinutes(), its.getSeconds(), its.getNanos()).atZone((this.pset.getBooleanProperty(PropertyKey.preserveInstants).getValue() != false ? this.connectionTimeZone : this.defaultTimeZone).toZoneId());
    }

    @Override
    public ZonedDateTime createFromBytes(byte[] bytes, int offset, int length, Field f) {
        if (length == 0 && this.pset.getBooleanProperty(PropertyKey.emptyStringsConvertToZero).getValue().booleanValue()) {
            return (ZonedDateTime)this.createFromLong(0L);
        }
        String s = StringUtils.toString(bytes, offset, length, f.getEncoding());
        byte[] newBytes = s.getBytes();
        if (MysqlTextValueDecoder.isDate(s)) {
            return (ZonedDateTime)this.createFromDate(MysqlTextValueDecoder.getDate(newBytes, 0, newBytes.length));
        }
        if (MysqlTextValueDecoder.isTime(s)) {
            return (ZonedDateTime)this.createFromTime(MysqlTextValueDecoder.getTime(newBytes, 0, newBytes.length, f.getDecimals()));
        }
        if (MysqlTextValueDecoder.isTimestamp(s)) {
            return (ZonedDateTime)this.createFromTimestamp(MysqlTextValueDecoder.getTimestamp(newBytes, 0, newBytes.length, f.getDecimals()));
        }
        try {
            return ZonedDateTime.parse(s.replace(" ", "T"));
        }
        catch (DateTimeParseException e) {
            throw new DataConversionException(Messages.getString("ResultSet.UnableToConvertString", new Object[]{s, this.getTargetTypeName()}));
        }
    }

    @Override
    public String getTargetTypeName() {
        return ZonedDateTime.class.getName();
    }
}

