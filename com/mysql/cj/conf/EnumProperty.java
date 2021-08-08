/*
 * Decompiled with CFR 0.150.
 */
package com.mysql.cj.conf;

import com.mysql.cj.conf.AbstractRuntimeProperty;
import com.mysql.cj.conf.PropertyDefinition;

public class EnumProperty<T extends Enum<T>>
extends AbstractRuntimeProperty<T> {
    private static final long serialVersionUID = -60853080911910124L;

    protected EnumProperty(PropertyDefinition<T> propertyDefinition) {
        super(propertyDefinition);
    }
}

