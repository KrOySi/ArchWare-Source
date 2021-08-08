/*
 * Decompiled with CFR 0.150.
 */
package com.mysql.cj;

public interface DataStoreMetadata {
    public boolean schemaExists(String var1);

    public boolean tableExists(String var1, String var2);

    public long getTableRowCount(String var1, String var2);
}

