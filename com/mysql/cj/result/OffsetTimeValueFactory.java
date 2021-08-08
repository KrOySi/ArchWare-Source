/*
 * Decompiled with CFR 0.150.
 */
package com.mysql.cj.result;

import com.mysql.cj.Messages;
import com.mysql.cj.WarningListener;
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
import java.time.LocalTime;
import java.time.OffsetTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeParseException;
import java.util.TimeZone;

public class OffsetTimeValueFactory
extends AbstractDateTimeValueFactory<OffsetTime> {
    private WarningListener warningListener;
    private TimeZone tz;

    public OffsetTimeValueFactory(PropertySet pset, TimeZone tz) {
        super(pset);
        this.tz = tz;
    }

    public OffsetTimeValueFactory(PropertySet pset, TimeZone tz, WarningListener warningListener) {
        this(pset, tz);
        this.warningListener = warningListener;
    }

    @Override
    OffsetTime localCreateFromDate(InternalDate idate) {
        return LocalTime.of(0, 0).atOffset(ZoneOffset.ofTotalSeconds(this.tz.getRawOffset() / 1000));
    }

    @Override
    public OffsetTime localCreateFromTime(InternalTime it) {
        if (it.getHours() < 0 || it.getHours() >= 24) {
            throw new DataReadException(Messages.getString("ResultSet.InvalidTimeValue", new Object[]{it.toString()}));
        }
        return LocalTime.of(it.getHours(), it.getMinutes(), it.getSeconds(), it.getNanos()).atOffset(ZoneOffset.ofTotalSeconds(this.tz.getRawOffset() / 1000));
    }

    @Override
    public OffsetTime localCreateFromTimestamp(InternalTimestamp its) {
        if (this.warningListener != null) {
            this.warningListener.warningEncountered(Messages.getString("ResultSet.PrecisionLostWarning", new Object[]{this.getTargetTypeName()}));
        }
        return (OffsetTime)this.createFromTime(new InternalTime(its.getHours(), its.getMinutes(), its.getSeconds(), its.getNanos(), its.getScale()));
    }

    @Override
    public OffsetTime localCreateFromDatetime(InternalTimestamp its) {
        if (this.warningListener != null) {
            this.warningListener.warningEncountered(Messages.getString("ResultSet.PrecisionLostWarning", new Object[]{this.getTargetTypeName()}));
        }
        return (OffsetTime)this.createFromTime(new InternalTime(its.getHours(), its.getMinutes(), its.getSeconds(), its.getNanos(), its.getScale()));
    }

    @Override
    public OffsetTime createFromBytes(byte[] bytes, int offset, int length, Field f) {
        if (length == 0 && this.pset.getBooleanProperty(PropertyKey.emptyStringsConvertToZero).getValue().booleanValue()) {
            return (OffsetTime)this.createFromLong(0L);
        }
        String s = StringUtils.toString(bytes, offset, length, f.getEncoding());
        byte[] newBytes = s.getBytes();
        if (MysqlTextValueDecoder.isDate(s)) {
            return (OffsetTime)this.createFromDate(MysqlTextValueDecoder.getDate(newBytes, 0, newBytes.length));
        }
        if (MysqlTextValueDecoder.isTime(s)) {
            return (OffsetTime)this.createFromTime(MysqlTextValueDecoder.getTime(newBytes, 0, newBytes.length, f.getDecimals()));
        }
        if (MysqlTextValueDecoder.isTimestamp(s)) {
            return (OffsetTime)this.createFromTimestamp(MysqlTextValueDecoder.getTimestamp(newBytes, 0, newBytes.length, f.getDecimals()));
        }
        try {
            return OffsetTime.parse(s);
        }
        catch (DateTimeParseException e) {
            throw new DataConversionException(Messages.getString("ResultSet.UnableToConvertString", new Object[]{s, this.getTargetTypeName()}));
        }
    }

    @Override
    public String getTargetTypeName() {
        return OffsetTime.class.getName();
    }
}

