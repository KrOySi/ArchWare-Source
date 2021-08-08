/*
 * Decompiled with CFR 0.150.
 */
package me.archware.impl.modules.misc;

import me.archware.base.event.EventTarget;
import me.archware.base.module.Category;
import me.archware.base.module.Module;
import me.archware.base.setting.NumericValue;
import me.archware.impl.events.EventUpdate;
import me.archware.impl.utils.SpammerUtils;
import me.archware.impl.utils.TimeUtil;

public class Spammer
extends Module {
    private NumericValue delay = new NumericValue("Delay", "SpammerDelay", (Module)this, 1000.0f, 500.0f, 10000.0f);
    private TimeUtil timer = new TimeUtil();

    public Spammer() {
        super("Spammer", Category.MISC);
    }

    @Override
    public void onEnable() {
        super.onEnable();
    }

    @EventTarget
    public void onUpdate(EventUpdate event) {
        if (this.timer.hasReached(this.delay.getValueNumeric())) {
            this.mc.player.sendChatMessage(SpammerUtils.getMessage());
            this.timer.reset();
        }
    }
}

