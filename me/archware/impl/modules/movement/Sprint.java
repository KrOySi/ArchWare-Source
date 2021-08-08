/*
 * Decompiled with CFR 0.150.
 */
package me.archware.impl.modules.movement;

import me.archware.base.event.EventTarget;
import me.archware.base.module.Category;
import me.archware.base.module.Module;
import me.archware.impl.events.EventUpdate;

public class Sprint
extends Module {
    public Sprint() {
        super("Sprint", Category.MOVEMENT);
    }

    @EventTarget
    public void onUpdate(EventUpdate eventUpdate) {
        this.mc.player.setSprinting(!this.mc.player.isCollidedHorizontally && this.mc.gameSettings.keyBindForward.isKeyDown());
    }
}

