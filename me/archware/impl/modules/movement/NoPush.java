/*
 * Decompiled with CFR 0.150.
 */
package me.archware.impl.modules.movement;

import me.archware.base.event.EventTarget;
import me.archware.base.module.Category;
import me.archware.base.module.Module;
import me.archware.impl.events.EventUpdate;

public class NoPush
extends Module {
    public NoPush() {
        super("NoPush", Category.MOVEMENT);
    }

    @EventTarget
    public void onUpdate(EventUpdate event) {
        this.mc.player.entityCollisionReduction = 1.0f;
    }
}

