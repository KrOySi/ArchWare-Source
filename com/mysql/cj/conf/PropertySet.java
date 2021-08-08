/*
 * Decompiled with CFR 0.150.
 */
package com.mysql.cj.conf;

import com.mysql.cj.conf.PropertyKey;
import com.mysql.cj.conf.RuntimeProperty;
import java.util.Properties;

public interface PropertySet {
    public void addProperty(RuntimeProperty<?> var1);

    public void removeProperty(String var1);

    public void removeProperty(PropertyKey var1);

    public <T> RuntimeProperty<T> getProperty(String var1);

    public <T> RuntimeProperty<T> getProperty(PropertyKey var1);

    public RuntimeProperty<Boolean> getBooleanProperty(String var1);

    public RuntimeProperty<Boolean> getBooleanProperty(PropertyKey var1);

    public RuntimeProperty<Integer> getIntegerProperty(String var1);

    public RuntimeProperty<Integer> getIntegerProperty(PropertyKey var1);

    public RuntimeProperty<Long> getLongProperty(String var1);

    public RuntimeProperty<Long> getLongProperty(PropertyKey var1);

    public RuntimeProperty<Integer> getMemorySizeProperty(String var1);

    public RuntimeProperty<Integer> getMemorySizeProperty(PropertyKey var1);

    public RuntimeProperty<String> getStringProperty(String var1);

    public RuntimeProperty<String> getStringProperty(PropertyKey var1);

    public <T extends Enum<T>> RuntimeProperty<T> getEnumProperty(String var1);

    public <T extends Enum<T>> RuntimeProperty<T> getEnumProperty(PropertyKey var1);

    public void initializeProperties(Properties var1);

    public void postInitialization();

    public Properties exposeAsProperties();

    public void reset();
}

