/*
 * Decompiled with CFR 0.150.
 */
package com.mysql.cj;

import java.util.Set;

public interface CacheAdapter<K, V> {
    public V get(K var1);

    public void put(K var1, V var2);

    public void invalidate(K var1);

    public void invalidateAll(Set<K> var1);

    public void invalidateAll();
}

