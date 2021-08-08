/*
 * Decompiled with CFR 0.150.
 */
package com.mysql.cj.xdevapi;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.stream.IntStream;

public interface Statement<STMT_T, RES_T> {
    public RES_T execute();

    public CompletableFuture<RES_T> executeAsync();

    default public STMT_T clearBindings() {
        throw new UnsupportedOperationException("This statement doesn't support bound parameters");
    }

    default public STMT_T bind(String argName, Object value) {
        throw new UnsupportedOperationException("This statement doesn't support bound parameters");
    }

    default public STMT_T bind(Map<String, Object> values) {
        this.clearBindings();
        values.entrySet().forEach(e -> this.bind((String)e.getKey(), e.getValue()));
        return (STMT_T)this;
    }

    default public STMT_T bind(List<Object> values) {
        this.clearBindings();
        IntStream.range(0, values.size()).forEach(i -> this.bind(String.valueOf(i), values.get(i)));
        return (STMT_T)this;
    }

    default public STMT_T bind(Object ... values) {
        return this.bind(Arrays.asList(values));
    }

    public static enum LockContention {
        DEFAULT,
        NOWAIT,
        SKIP_LOCKED;

    }
}

