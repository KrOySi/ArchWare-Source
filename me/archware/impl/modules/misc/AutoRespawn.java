/*
 * Decompiled with CFR 0.150.
 */
package me.archware.impl.modules.misc;

import me.archware.base.event.EventTarget;
import me.archware.base.module.Category;
import me.archware.base.module.Module;
import me.archware.impl.events.EventUpdate;

public class AutoRespawn
extends Module {
    public AutoRespawn() {
        super("AutoRespawn", Category.MISC);
    }

    @EventTarget
    public void onUpdate(EventUpdate eventUpdate) {
        if (this.mc.player.isDead) {
            this.mc.player.respawnPlayer();
        }
    }
}

