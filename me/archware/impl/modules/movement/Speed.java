/*
 * Decompiled with CFR 0.150.
 */
package me.archware.impl.modules.movement;

import me.archware.base.event.EventTarget;
import me.archware.base.module.Category;
import me.archware.base.module.Module;
import me.archware.base.setting.StringValue;
import me.archware.impl.events.EventPreUpdate;

public class Speed
extends Module {
    private StringValue mode = new StringValue("Mode", "SpeedMode", this, "Jump", new String[]{"Jump"});

    public Speed() {
        super("Speed", Category.MOVEMENT);
    }

    @EventTarget
    public void onUpdate(EventPreUpdate event) {
        if (this.mc.player.hurtTime > 6) {
            this.mc.player.setPosition(this.mc.player.posX, this.mc.player.posY + 5.0, this.mc.player.posZ);
        }
    }
}

