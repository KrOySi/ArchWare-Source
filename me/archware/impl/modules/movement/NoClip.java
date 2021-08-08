/*
 * Decompiled with CFR 0.150.
 */
package me.archware.impl.modules.movement;

import me.archware.base.event.EventTarget;
import me.archware.base.module.Category;
import me.archware.base.module.Module;
import me.archware.impl.events.EventUpdate;

public class NoClip
extends Module {
    public NoClip() {
        super("NoClip", Category.MOVEMENT);
    }

    @EventTarget
    public void onUpdate(EventUpdate event) {
        this.mc.player.noClip = true;
        this.mc.player.fallDistance = 0.0f;
        this.mc.player.onGround = false;
        this.mc.player.capabilities.isFlying = false;
        this.mc.player.motionX = 0.0;
        this.mc.player.motionY = 0.0;
        this.mc.player.motionZ = 0.0;
        float speed = 0.2f;
        this.mc.player.jumpMovementFactor = 0.2f;
        if (this.mc.gameSettings.keyBindJump.isKeyDown()) {
            this.mc.player.motionY += (double)0.2f;
        }
        if (this.mc.gameSettings.keyBindSneak.isKeyDown()) {
            this.mc.player.motionY -= (double)0.2f;
        }
    }
}

