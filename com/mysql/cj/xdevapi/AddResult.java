/*
 * Decompiled with CFR 0.150.
 */
package com.mysql.cj.xdevapi;

import com.mysql.cj.xdevapi.Result;
import java.util.List;

public interface AddResult
extends Result {
    public List<String> getGeneratedIds();
}

