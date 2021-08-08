/*
 * Decompiled with CFR 0.150.
 */
package com.mysql.cj.result;

import com.mysql.cj.conf.PropertySet;
import com.mysql.cj.protocol.InternalDate;
import com.mysql.cj.protocol.InternalTime;
import com.mysql.cj.protocol.InternalTimestamp;
import com.mysql.cj.result.AbstractDateTimeValueFactory;
import java.time.Duration;

public class DurationValueFactory
extends AbstractDateTimeValueFactory<Duration> {
    public DurationValueFactory(PropertySet pset) {
        super(pset);
    }

    @Override
    Duration localCreateFromDate(InternalDate idate) {
        return (Duration)this.unsupported("DATE");
    }

    @Override
    public Duration localCreateFromTime(InternalTime it) {
        String ptn = (it.getHours() < 0 ? "-PT" : "PT") + (it.getHours() < 0 ? -it.getHours() : it.getHours()) + "H" + it.getMinutes() + "M" + it.getSeconds() + "." + it.getNanos() + "S";
        return Duration.parse(ptn);
    }

    @Override
    public Duration localCreateFromTimestamp(InternalTimestamp its) {
        return (Duration)this.unsupported("TIMESTAMP");
    }

    @Override
    public Duration localCreateFromDatetime(InternalTimestamp its) {
        return (Duration)this.unsupported("DATETIME");
    }

    @Override
    public String getTargetTypeName() {
        return Duration.class.getName();
    }
}

