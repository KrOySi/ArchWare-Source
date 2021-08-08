/*
 * Decompiled with CFR 0.150.
 */
package com.mysql.cj.result;

import com.mysql.cj.Messages;
import com.mysql.cj.WarningListener;
import com.mysql.cj.conf.PropertySet;
import com.mysql.cj.exceptions.DataReadException;
import com.mysql.cj.exceptions.ExceptionFactory;
import com.mysql.cj.exceptions.WrongArgumentException;
import com.mysql.cj.protocol.InternalDate;
import com.mysql.cj.protocol.InternalTime;
import com.mysql.cj.protocol.InternalTimestamp;
import com.mysql.cj.result.AbstractDateTimeValueFactory;
import java.sql.Time;
import java.util.Calendar;
import java.util.Locale;
import java.util.TimeZone;

public class SqlTimeValueFactory
extends AbstractDateTimeValueFactory<Time> {
    private WarningListener warningListener;
    private Calendar cal;

    public SqlTimeValueFactory(PropertySet pset, Calendar calendar, TimeZone tz) {
        super(pset);
        if (calendar != null) {
            this.cal = (Calendar)calendar.clone();
        } else {
            this.cal = Calendar.getInstance(tz, Locale.US);
            this.cal.setLenient(false);
        }
    }

    public SqlTimeValueFactory(PropertySet pset, Calendar calendar, TimeZone tz, WarningListener warningListener) {
        this(pset, calendar, tz);
        this.warningListener = warningListener;
    }

    @Override
    Time localCreateFromDate(InternalDate idate) {
        Calendar calendar = this.cal;
        synchronized (calendar) {
            try {
                this.cal.clear();
                return new Time(this.cal.getTimeInMillis());
            }
            catch (IllegalArgumentException e) {
                throw ExceptionFactory.createException(WrongArgumentException.class, e.getMessage(), e);
            }
        }
    }

    @Override
    public Time localCreateFromTime(InternalTime it) {
        if (it.getHours() < 0 || it.getHours() >= 24) {
            throw new DataReadException(Messages.getString("ResultSet.InvalidTimeValue", new Object[]{it.toString()}));
        }
        Calendar calendar = this.cal;
        synchronized (calendar) {
            try {
                this.cal.set(1970, 0, 1, it.getHours(), it.getMinutes(), it.getSeconds());
                this.cal.set(14, 0);
                long ms = (long)(it.getNanos() / 1000000) + this.cal.getTimeInMillis();
                return new Time(ms);
            }
            catch (IllegalArgumentException e) {
                throw ExceptionFactory.createException(WrongArgumentException.class, e.getMessage(), e);
            }
        }
    }

    @Override
    public Time localCreateFromDatetime(InternalTimestamp its) {
        if (this.warningListener != null) {
            this.warningListener.warningEncountered(Messages.getString("ResultSet.PrecisionLostWarning", new Object[]{"java.sql.Time"}));
        }
        return (Time)this.createFromTime(new InternalTime(its.getHours(), its.getMinutes(), its.getSeconds(), its.getNanos(), its.getScale()));
    }

    @Override
    public Time localCreateFromTimestamp(InternalTimestamp its) {
        if (this.warningListener != null) {
            this.warningListener.warningEncountered(Messages.getString("ResultSet.PrecisionLostWarning", new Object[]{"java.sql.Time"}));
        }
        return (Time)this.createFromTime(new InternalTime(its.getHours(), its.getMinutes(), its.getSeconds(), its.getNanos(), its.getScale()));
    }

    @Override
    public String getTargetTypeName() {
        return Time.class.getName();
    }
}

