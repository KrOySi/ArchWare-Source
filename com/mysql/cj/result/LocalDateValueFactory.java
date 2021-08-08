/*
 * Decompiled with CFR 0.150.
 */
package com.mysql.cj.result;

import com.mysql.cj.Messages;
import com.mysql.cj.WarningListener;
import com.mysql.cj.conf.PropertySet;
import com.mysql.cj.exceptions.DataReadException;
import com.mysql.cj.protocol.InternalDate;
import com.mysql.cj.protocol.InternalTime;
import com.mysql.cj.protocol.InternalTimestamp;
import com.mysql.cj.result.AbstractDateTimeValueFactory;
import java.time.LocalDate;

public class LocalDateValueFactory
extends AbstractDateTimeValueFactory<LocalDate> {
    private WarningListener warningListener;

    public LocalDateValueFactory(PropertySet pset) {
        super(pset);
    }

    public LocalDateValueFactory(PropertySet pset, WarningListener warningListener) {
        this(pset);
        this.warningListener = warningListener;
    }

    @Override
    public LocalDate localCreateFromDate(InternalDate idate) {
        if (idate.getYear() == 0 && idate.getMonth() == 0 && idate.getDay() == 0) {
            throw new DataReadException(Messages.getString("ResultSet.InvalidZeroDate"));
        }
        return LocalDate.of(idate.getYear(), idate.getMonth(), idate.getDay());
    }

    @Override
    public LocalDate localCreateFromTimestamp(InternalTimestamp its) {
        if (this.warningListener != null) {
            this.warningListener.warningEncountered(Messages.getString("ResultSet.PrecisionLostWarning", new Object[]{this.getTargetTypeName()}));
        }
        return (LocalDate)this.createFromDate(its);
    }

    @Override
    public LocalDate localCreateFromDatetime(InternalTimestamp its) {
        if (this.warningListener != null) {
            this.warningListener.warningEncountered(Messages.getString("ResultSet.PrecisionLostWarning", new Object[]{this.getTargetTypeName()}));
        }
        return (LocalDate)this.createFromDate(its);
    }

    @Override
    LocalDate localCreateFromTime(InternalTime it) {
        return LocalDate.of(1970, 1, 1);
    }

    @Override
    public String getTargetTypeName() {
        return LocalDate.class.getName();
    }
}

