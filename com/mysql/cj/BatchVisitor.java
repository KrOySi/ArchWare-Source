/*
 * Decompiled with CFR 0.150.
 */
package com.mysql.cj;

public interface BatchVisitor {
    public BatchVisitor increment();

    public BatchVisitor decrement();

    public BatchVisitor append(byte[] var1);

    public BatchVisitor merge(byte[] var1, byte[] var2);

    public BatchVisitor mergeWithLast(byte[] var1);
}

