/*
 * Decompiled with CFR 0.150.
 */
package com.mysql.cj.protocol;

import com.mysql.cj.protocol.ProtocolEntity;
import com.mysql.cj.result.Row;

public interface ResultsetRow
extends Row,
ProtocolEntity {
    default public boolean isBinaryEncoded() {
        return false;
    }
}

