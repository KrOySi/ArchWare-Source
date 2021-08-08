/*
 * Decompiled with CFR 0.150.
 */
package me.archware.base.event;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import me.archware.base.event.ArrayHelper;
import me.archware.base.event.Data;
import me.archware.base.event.Event;
import me.archware.base.event.EventTarget;
import me.archware.base.event.Priority;

public class EventManager {
    private Map<Class<? extends Event>, ArrayHelper<Data>> REGISTRY_MAP = new HashMap<Class<? extends Event>, ArrayHelper<Data>>();

    public void register(Object o) {
        for (Method method : o.getClass().getDeclaredMethods()) {
            if (this.isMethodBad(method)) continue;
            this.register(method, o);
        }
    }

    public void register(Object o, Class<? extends Event> clazz) {
        for (Method method : o.getClass().getDeclaredMethods()) {
            if (this.isMethodBad(method, clazz)) continue;
            this.register(method, o);
        }
    }

    private void register(Method method, Object o) {
        Class<?> clazz = method.getParameterTypes()[0];
        final Data methodData = new Data(o, method, method.getAnnotation(EventTarget.class).value());
        if (!methodData.target.isAccessible()) {
            methodData.target.setAccessible(true);
        }
        if (this.REGISTRY_MAP.containsKey(clazz)) {
            if (!this.REGISTRY_MAP.get(clazz).contains(methodData)) {
                this.REGISTRY_MAP.get(clazz).add(methodData);
                this.sortListValue(clazz);
            }
        } else {
            this.REGISTRY_MAP.put(clazz, new ArrayHelper<Data>(){
                {
                    this.add(methodData);
                }
            });
        }
    }

    public void unregister(Object o) {
        for (ArrayHelper<Data> flexibalArray : this.REGISTRY_MAP.values()) {
            for (Data methodData : flexibalArray) {
                if (!methodData.source.equals(o)) continue;
                flexibalArray.remove(methodData);
            }
        }
        this.cleanMap(true);
    }

    public void unregister(Object o, Class<? extends Event> clazz) {
        if (this.REGISTRY_MAP.containsKey(clazz)) {
            for (Data methodData : this.REGISTRY_MAP.get(clazz)) {
                if (!methodData.source.equals(o)) continue;
                this.REGISTRY_MAP.get(clazz).remove(methodData);
            }
            this.cleanMap(true);
        }
    }

    public void cleanMap(boolean b) {
        Iterator<Map.Entry<Class<? extends Event>, ArrayHelper<Data>>> iterator = this.REGISTRY_MAP.entrySet().iterator();
        while (iterator.hasNext()) {
            if (b && !iterator.next().getValue().isEmpty()) continue;
            iterator.remove();
        }
    }

    public void removeEnty(Class<? extends Event> clazz) {
        Iterator<Map.Entry<Class<? extends Event>, ArrayHelper<Data>>> iterator = this.REGISTRY_MAP.entrySet().iterator();
        while (iterator.hasNext()) {
            if (!iterator.next().getKey().equals(clazz)) continue;
            iterator.remove();
            break;
        }
    }

    private void sortListValue(Class<? extends Event> clazz) {
        ArrayHelper<Data> flexibleArray = new ArrayHelper<Data>();
        for (byte b : Priority.VALUE_ARRAY) {
            for (Data methodData : this.REGISTRY_MAP.get(clazz)) {
                if (methodData.priority != b) continue;
                flexibleArray.add(methodData);
            }
        }
        this.REGISTRY_MAP.put(clazz, flexibleArray);
    }

    private boolean isMethodBad(Method method) {
        return method.getParameterTypes().length != 1 || !method.isAnnotationPresent(EventTarget.class);
    }

    private boolean isMethodBad(Method method, Class<? extends Event> clazz) {
        return this.isMethodBad(method) || method.getParameterTypes()[0].equals(clazz);
    }

    public ArrayHelper<Data> get(Class<? extends Event> clazz) {
        return this.REGISTRY_MAP.get(clazz);
    }

    public void shutdown() {
        this.REGISTRY_MAP.clear();
    }
}

