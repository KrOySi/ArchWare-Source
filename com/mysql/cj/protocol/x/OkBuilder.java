/*
 * Decompiled with CFR 0.150.
 */
package com.mysql.cj.protocol.x;

import com.mysql.cj.exceptions.ExceptionFactory;
import com.mysql.cj.exceptions.WrongArgumentException;
import com.mysql.cj.protocol.ProtocolEntity;
import com.mysql.cj.protocol.ResultBuilder;
import com.mysql.cj.protocol.x.Notice;
import com.mysql.cj.protocol.x.Ok;

public class OkBuilder
implements ResultBuilder<Ok> {
    @Override
    public boolean addProtocolEntity(ProtocolEntity entity) {
        if (entity instanceof Notice) {
            return false;
        }
        if (entity instanceof Ok) {
            return true;
        }
        throw ExceptionFactory.createException(WrongArgumentException.class, "Unexpected protocol entity " + entity);
    }

    @Override
    public Ok build() {
        return new Ok();
    }
}

