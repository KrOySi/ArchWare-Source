/*
 * Decompiled with CFR 0.150.
 */
package com.mysql.cj.protocol;

import com.mysql.cj.exceptions.CJOperationNotSupportedException;
import com.mysql.cj.exceptions.ExceptionFactory;
import com.mysql.cj.protocol.Message;

public interface MessageListener<M extends Message> {
    default public boolean processMessage(M message) {
        throw ExceptionFactory.createException(CJOperationNotSupportedException.class, "Not allowed");
    }

    public void error(Throwable var1);
}

