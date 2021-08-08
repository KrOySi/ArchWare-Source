/*
 * Decompiled with CFR 0.150.
 */
package com.mysql.cj.conf;

import com.mysql.cj.conf.PropertyKey;
import com.mysql.cj.conf.RuntimeProperty;
import com.mysql.cj.exceptions.ExceptionInterceptor;

public interface PropertyDefinition<T> {
    public boolean hasValueConstraints();

    public boolean isRangeBased();

    public PropertyKey getPropertyKey();

    public String getName();

    public String getCcAlias();

    public boolean hasCcAlias();

    public T getDefaultValue();

    public boolean isRuntimeModifiable();

    public String getDescription();

    public String getSinceVersion();

    public String getCategory();

    public int getOrder();

    public String[] getAllowableValues();

    public int getLowerBound();

    public int getUpperBound();

    public T parseObject(String var1, ExceptionInterceptor var2);

    public RuntimeProperty<T> createRuntimeProperty();
}

