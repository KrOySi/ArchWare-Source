/*
 * Decompiled with CFR 0.150.
 */
package com.mysql.cj.result;

import com.mysql.cj.conf.PropertySet;
import com.mysql.cj.result.DefaultValueFactory;
import com.mysql.cj.result.Field;
import java.io.ByteArrayInputStream;
import java.io.InputStream;

public class BinaryStreamValueFactory
extends DefaultValueFactory<InputStream> {
    public BinaryStreamValueFactory(PropertySet pset) {
        super(pset);
    }

    @Override
    public InputStream createFromBytes(byte[] bytes, int offset, int length, Field f) {
        return new ByteArrayInputStream(bytes, offset, length);
    }

    @Override
    public String getTargetTypeName() {
        return InputStream.class.getName();
    }
}

