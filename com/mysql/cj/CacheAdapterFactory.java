/*
 * Decompiled with CFR 0.150.
 */
package com.mysql.cj;

import com.mysql.cj.CacheAdapter;

public interface CacheAdapterFactory<K, V> {
    public CacheAdapter<K, V> getInstance(Object var1, String var2, int var3, int var4);
}

