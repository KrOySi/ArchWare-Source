/*
 * Decompiled with CFR 0.150.
 */
package com.viaversion.viaversion.api.type.types;

import com.viaversion.viaversion.api.type.Type;
import java.util.LinkedList;
import java.util.List;

public class Particle {
    private int id;
    private List<ParticleData> arguments = new LinkedList<ParticleData>();

    public Particle(int id) {
        this.id = id;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<ParticleData> getArguments() {
        return this.arguments;
    }

    public void setArguments(List<ParticleData> arguments) {
        this.arguments = arguments;
    }

    public static class ParticleData {
        private Type type;
        private Object value;

        public ParticleData(Type type, Object value) {
            this.type = type;
            this.value = value;
        }

        public Type getType() {
            return this.type;
        }

        public void setType(Type type) {
            this.type = type;
        }

        public Object getValue() {
            return this.value;
        }

        public <T> T get() {
            return (T)this.value;
        }

        public void setValue(Object value) {
            this.value = value;
        }
    }
}

