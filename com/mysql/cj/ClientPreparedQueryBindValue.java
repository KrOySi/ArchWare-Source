/*
 * Decompiled with CFR 0.150.
 */
package com.mysql.cj;

import com.mysql.cj.BindValue;
import com.mysql.cj.MysqlType;
import java.io.InputStream;

public class ClientPreparedQueryBindValue
implements BindValue {
    protected boolean isNull;
    protected boolean isStream = false;
    protected MysqlType parameterType = MysqlType.NULL;
    public Object value;
    public Object origValue;
    protected long streamLength;
    protected boolean isSet = false;

    public ClientPreparedQueryBindValue() {
    }

    @Override
    public ClientPreparedQueryBindValue clone() {
        return new ClientPreparedQueryBindValue(this);
    }

    protected ClientPreparedQueryBindValue(ClientPreparedQueryBindValue copyMe) {
        this.isNull = copyMe.isNull;
        this.isStream = copyMe.isStream;
        this.parameterType = copyMe.parameterType;
        if (copyMe.value != null && copyMe.value instanceof byte[]) {
            this.value = new byte[((byte[])copyMe.value).length];
            System.arraycopy(copyMe.value, 0, this.value, 0, ((byte[])copyMe.value).length);
        } else {
            this.value = copyMe.value;
        }
        this.streamLength = copyMe.streamLength;
        this.isSet = copyMe.isSet;
    }

    @Override
    public void reset() {
        this.isNull = false;
        this.isStream = false;
        this.parameterType = MysqlType.NULL;
        this.value = null;
        this.origValue = null;
        this.streamLength = 0L;
        this.isSet = false;
    }

    @Override
    public boolean isNull() {
        return this.isNull;
    }

    @Override
    public void setNull(boolean isNull) {
        this.isNull = isNull;
        if (isNull) {
            this.parameterType = MysqlType.NULL;
        }
        this.isSet = true;
    }

    @Override
    public boolean isStream() {
        return this.isStream;
    }

    @Override
    public void setIsStream(boolean isStream) {
        this.isStream = isStream;
    }

    @Override
    public MysqlType getMysqlType() {
        return this.parameterType;
    }

    @Override
    public void setMysqlType(MysqlType type) {
        this.parameterType = type;
    }

    @Override
    public byte[] getByteValue() {
        if (this.value instanceof byte[]) {
            return (byte[])this.value;
        }
        return null;
    }

    @Override
    public void setByteValue(byte[] parameterValue) {
        this.isNull = false;
        this.isStream = false;
        this.value = parameterValue;
        this.streamLength = 0L;
        this.isSet = true;
    }

    @Override
    public void setOrigByteValue(byte[] origParamValue) {
        this.origValue = origParamValue;
    }

    @Override
    public byte[] getOrigByteValue() {
        return (byte[])this.origValue;
    }

    @Override
    public InputStream getStreamValue() {
        if (this.value instanceof InputStream) {
            return (InputStream)this.value;
        }
        return null;
    }

    @Override
    public void setStreamValue(InputStream parameterStream, long streamLength) {
        this.value = parameterStream;
        this.streamLength = streamLength;
        this.isSet = true;
    }

    @Override
    public long getStreamLength() {
        return this.streamLength;
    }

    @Override
    public boolean isSet() {
        return this.isSet;
    }
}

