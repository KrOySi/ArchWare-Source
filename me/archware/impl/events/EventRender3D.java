/*
 * Decompiled with CFR 0.150.
 */
package me.archware.impl.events;

import me.archware.base.event.Event;

public class EventRender3D
extends Event {
    private float partialTicks;

    public EventRender3D(float partialTicks) {
        this.partialTicks = partialTicks;
    }

    public float getPartialTicks() {
        return this.partialTicks;
    }
}

