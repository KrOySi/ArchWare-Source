/*
 * Decompiled with CFR 0.150.
 */
package me.archware.impl.modules.misc;

import me.archware.base.event.EventTarget;
import me.archware.base.module.Category;
import me.archware.base.module.Module;
import me.archware.impl.events.EventUpdate;
import me.archware.impl.utils.TimeUtil;

public class AntiAFK
extends Module {
    private TimeUtil timer = new TimeUtil();

    public AntiAFK() {
        super("AntiAFK", Category.MISC);
    }

    @EventTarget
    public void onUpdate(EventUpdate event) {
        if (this.timer.hasReached(65000.0)) {
            this.mc.player.sendChatMessage("/stats");
            this.timer.reset();
        }
    }
}

