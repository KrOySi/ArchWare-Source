/*
 * Decompiled with CFR 0.150.
 */
package me.archware.impl.modules.movement;

import me.archware.base.event.EventTarget;
import me.archware.base.module.Category;
import me.archware.base.module.Module;
import me.archware.impl.events.EventPreUpdate;

public class Step
extends Module {
    public Step() {
        super("Step", Category.MOVEMENT);
    }

    @EventTarget
    public void onUpdate(EventPreUpdate event) {
        event.setOnGround(true);
        if (this.mc.player.isCollidedHorizontally && this.mc.player.motionY <= 0.2) {
            this.mc.player.onGround = true;
            this.mc.player.jump();
        }
    }
}

