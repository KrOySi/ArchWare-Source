/*
 * Decompiled with CFR 0.150.
 */
package com.mysql.cj.result;

import com.mysql.cj.conf.PropertySet;
import com.mysql.cj.protocol.InternalDate;
import com.mysql.cj.protocol.InternalTime;
import com.mysql.cj.protocol.InternalTimestamp;
import com.mysql.cj.result.Field;
import java.math.BigDecimal;
import java.math.BigInteger;

public interface ValueFactory<T> {
    public void setPropertySet(PropertySet var1);

    public T createFromDate(InternalDate var1);

    public T createFromTime(InternalTime var1);

    public T createFromTimestamp(InternalTimestamp var1);

    public T createFromDatetime(InternalTimestamp var1);

    public T createFromLong(long var1);

    public T createFromBigInteger(BigInteger var1);

    public T createFromDouble(double var1);

    public T createFromBigDecimal(BigDecimal var1);

    public T createFromBytes(byte[] var1, int var2, int var3, Field var4);

    public T createFromBit(byte[] var1, int var2, int var3);

    public T createFromYear(long var1);

    public T createFromNull();

    public String getTargetTypeName();
}

