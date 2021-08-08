/*
 * Decompiled with CFR 0.150.
 */
package com.mysql.cj.xdevapi;

import com.mysql.cj.xdevapi.Schema;
import com.mysql.cj.xdevapi.Session;

public interface DatabaseObject {
    public Session getSession();

    public Schema getSchema();

    public String getName();

    public DbObjectStatus existsInDatabase();

    public static enum DbObjectStatus {
        EXISTS,
        NOT_EXISTS,
        UNKNOWN;

    }

    public static enum DbObjectType {
        COLLECTION,
        TABLE,
        VIEW,
        COLLECTION_VIEW;

    }
}

