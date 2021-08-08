/*
 * Decompiled with CFR 0.150.
 */
package com.mysql.cj;

import com.mysql.cj.MysqlType;
import java.io.InputStream;

public interface BindValue {
    public BindValue clone();

    public void reset();

    public boolean isNull();

    public void setNull(boolean var1);

    public boolean isStream();

    public void setIsStream(boolean var1);

    public MysqlType getMysqlType();

    public void setMysqlType(MysqlType var1);

    public byte[] getByteValue();

    public void setByteValue(byte[] var1);

    public void setOrigByteValue(byte[] var1);

    public byte[] getOrigByteValue();

    public InputStream getStreamValue();

    public void setStreamValue(InputStream var1, long var2);

    public long getStreamLength();

    public boolean isSet();
}

