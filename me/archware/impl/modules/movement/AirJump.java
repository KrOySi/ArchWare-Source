/*
 * Decompiled with CFR 0.150.
 */
package me.archware.impl.modules.movement;

import me.archware.base.event.EventTarget;
import me.archware.base.module.Category;
import me.archware.base.module.Module;
import me.archware.impl.events.EventUpdate;

public class AirJump
extends Module {
    public AirJump() {
        super("AirJump", Category.MOVEMENT);
    }

    @EventTarget
    public void onUpdate(EventUpdate event) {
        if (this.mc.gameSettings.keyBindJump.isKeyDown()) {
            this.mc.player.onGround = true;
        }
    }
}

