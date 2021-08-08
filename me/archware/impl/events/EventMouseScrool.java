/*
 * Decompiled with CFR 0.150.
 */
package me.archware.impl.events;

import me.archware.base.event.Event;

public class EventMouseScrool
extends Event {
    private float velocity;

    public EventMouseScrool(float velocity) {
        this.velocity = velocity;
    }

    public float getVelocity() {
        return this.velocity;
    }
}

