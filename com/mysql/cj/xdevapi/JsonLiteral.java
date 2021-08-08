/*
 * Decompiled with CFR 0.150.
 */
package com.mysql.cj.xdevapi;

import com.mysql.cj.xdevapi.JsonValue;

public enum JsonLiteral implements JsonValue
{
    TRUE("true"),
    FALSE("false"),
    NULL("null");

    public final String value;

    private JsonLiteral(String val) {
        this.value = val;
    }

    public String toString() {
        return this.value;
    }
}

