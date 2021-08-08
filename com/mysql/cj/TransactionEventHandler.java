/*
 * Decompiled with CFR 0.150.
 */
package com.mysql.cj;

public interface TransactionEventHandler {
    public void transactionBegun();

    public void transactionCompleted();
}

