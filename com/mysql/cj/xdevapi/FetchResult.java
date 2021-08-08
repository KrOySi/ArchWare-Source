/*
 * Decompiled with CFR 0.150.
 */
package com.mysql.cj.xdevapi;

import java.util.Iterator;
import java.util.List;

public interface FetchResult<T>
extends Iterator<T>,
Iterable<T> {
    default public boolean hasData() {
        return true;
    }

    default public T fetchOne() {
        if (this.hasNext()) {
            return (T)this.next();
        }
        return null;
    }

    @Override
    default public Iterator<T> iterator() {
        return this.fetchAll().iterator();
    }

    public long count();

    public List<T> fetchAll();
}

