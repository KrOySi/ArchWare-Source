/*
 * Decompiled with CFR 0.150.
 */
package me.archware.impl.modules.misc;

import me.archware.base.event.EventTarget;
import me.archware.base.module.Category;
import me.archware.base.module.Module;
import me.archware.impl.events.EventPreUpdate;

public class SelfDamage
extends Module {
    private int jumps = 0;

    public SelfDamage() {
        super("SelfDamage", Category.MISC);
    }

    @Override
    public void onEnable() {
        this.jumps = 0;
        super.onEnable();
    }

    @EventTarget
    public void onUpdate(EventPreUpdate event) {
        if (this.jumps < 3) {
            this.mc.timer.field_194147_b = 4.0f;
            event.setOnGround(false);
        }
        if (this.mc.player.onGround) {
            if (this.jumps < 3) {
                this.mc.player.jump();
                ++this.jumps;
            } else {
                this.toggle();
            }
        }
    }
}

