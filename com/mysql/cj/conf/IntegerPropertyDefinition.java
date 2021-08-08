/*
 * Decompiled with CFR 0.150.
 */
package com.mysql.cj.conf;

import com.mysql.cj.conf.AbstractPropertyDefinition;
import com.mysql.cj.conf.IntegerProperty;
import com.mysql.cj.conf.PropertyKey;
import com.mysql.cj.conf.RuntimeProperty;
import com.mysql.cj.exceptions.ExceptionFactory;
import com.mysql.cj.exceptions.ExceptionInterceptor;
import com.mysql.cj.exceptions.WrongArgumentException;

public class IntegerPropertyDefinition
extends AbstractPropertyDefinition<Integer> {
    private static final long serialVersionUID = 4151893695173946081L;
    protected int multiplier = 1;

    public IntegerPropertyDefinition(PropertyKey key, int defaultValue, boolean isRuntimeModifiable, String description, String sinceVersion, String category, int orderInCategory) {
        super(key, defaultValue, isRuntimeModifiable, description, sinceVersion, category, orderInCategory);
    }

    public IntegerPropertyDefinition(PropertyKey key, int defaultValue, boolean isRuntimeModifiable, String description, String sinceVersion, String category, int orderInCategory, int lowerBound, int upperBound) {
        super(key, defaultValue, isRuntimeModifiable, description, sinceVersion, category, orderInCategory, lowerBound, upperBound);
    }

    @Override
    public boolean isRangeBased() {
        return this.getUpperBound() != this.getLowerBound();
    }

    @Override
    public Integer parseObject(String value, ExceptionInterceptor exceptionInterceptor) {
        return IntegerPropertyDefinition.integerFrom(this.getName(), value, this.multiplier, exceptionInterceptor);
    }

    @Override
    public RuntimeProperty<Integer> createRuntimeProperty() {
        return new IntegerProperty(this);
    }

    public static Integer integerFrom(String name, String value, int multiplier, ExceptionInterceptor exceptionInterceptor) {
        try {
            int intValue = (int)(Double.valueOf(value) * (double)multiplier);
            return intValue;
        }
        catch (NumberFormatException nfe) {
            throw ExceptionFactory.createException(WrongArgumentException.class, "The connection property '" + name + "' only accepts integer values. The value '" + value + "' can not be converted to an integer.", exceptionInterceptor);
        }
    }
}

