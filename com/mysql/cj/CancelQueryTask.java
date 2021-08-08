/*
 * Decompiled with CFR 0.150.
 */
package com.mysql.cj;

import com.mysql.cj.Query;

public interface CancelQueryTask {
    public boolean cancel();

    public Throwable getCaughtWhileCancelling();

    public void setCaughtWhileCancelling(Throwable var1);

    public Query getQueryToCancel();

    public void setQueryToCancel(Query var1);
}

