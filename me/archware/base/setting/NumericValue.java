/*
 * Decompiled with CFR 0.150.
 */
package me.archware.base.setting;

import me.archware.base.module.Module;
import me.archware.base.setting.Setting;

public class NumericValue
extends Setting {
    public NumericValue(String name, String id, Module parent, float value, float minValue, float maxValue) {
        super(name, id, parent, value, minValue, maxValue);
    }
}

