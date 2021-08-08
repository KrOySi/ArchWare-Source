/*
 * Decompiled with CFR 0.150.
 */
package com.mysql.cj.protocol;

public interface Message {
    public byte[] getByteBuffer();

    public int getPosition();
}

