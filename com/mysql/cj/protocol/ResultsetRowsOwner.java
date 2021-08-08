/*
 * Decompiled with CFR 0.150.
 */
package com.mysql.cj.protocol;

import com.mysql.cj.MysqlConnection;
import com.mysql.cj.Query;
import com.mysql.cj.Session;

public interface ResultsetRowsOwner {
    public void closeOwner(boolean var1);

    public MysqlConnection getConnection();

    public Session getSession();

    public Object getSyncMutex();

    public String getPointOfOrigin();

    public int getOwnerFetchSize();

    public Query getOwningQuery();

    public int getOwningStatementMaxRows();

    public int getOwningStatementFetchSize();

    public long getOwningStatementServerId();
}

