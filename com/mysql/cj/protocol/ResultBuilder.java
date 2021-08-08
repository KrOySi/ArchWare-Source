/*
 * Decompiled with CFR 0.150.
 */
package com.mysql.cj.protocol;

import com.mysql.cj.protocol.ProtocolEntity;

public interface ResultBuilder<T> {
    public boolean addProtocolEntity(ProtocolEntity var1);

    public T build();
}

