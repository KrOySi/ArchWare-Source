/*
 * Decompiled with CFR 0.150.
 */
package me.archware.impl.events;

import me.archware.base.event.Event;

public class EventSlowdown
extends Event {
    public float moveStrafing;
    public float moveForvard;

    public EventSlowdown(float moveStrafing, float moveForvard) {
        this.moveStrafing = moveStrafing;
        this.moveForvard = moveForvard;
    }

    public float getMoveStrafing() {
        return this.moveStrafing;
    }

    public void setMoveStrafing(float moveStrafing) {
        this.moveStrafing = moveStrafing;
    }

    public float getMoveForvard() {
        return this.moveForvard;
    }

    public void setMoveForvard(float moveForvard) {
        this.moveForvard = moveForvard;
    }
}

