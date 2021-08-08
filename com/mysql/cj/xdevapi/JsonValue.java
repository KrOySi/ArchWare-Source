/*
 * Decompiled with CFR 0.150.
 */
package com.mysql.cj.xdevapi;

public interface JsonValue {
    default public String toFormattedString() {
        return this.toString();
    }
}

