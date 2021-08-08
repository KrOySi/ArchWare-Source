/*
 * Decompiled with CFR 0.150.
 */
package me.archware.impl.modules.player;

import me.archware.base.event.EventTarget;
import me.archware.base.module.Category;
import me.archware.base.module.Module;
import me.archware.base.setting.BooleanValue;
import me.archware.impl.events.EventSlowdown;
import me.archware.impl.events.EventUpdate;

public class NoSlow
extends Module {
    private BooleanValue web = new BooleanValue("Web", "NoSlowWeb", this, true);

    public NoSlow() {
        super("NoSlow", Category.PLAYER);
    }

    @EventTarget
    public void onUpdate(EventUpdate event) {
        if (this.mc.player.isInWeb && this.web.getValueBoolean()) {
            this.mc.player.onGround = true;
            this.mc.player.jump();
        }
    }

    @EventTarget
    public void onSlowDown(EventSlowdown eventSlowdown) {
        eventSlowdown.cancel();
    }
}

