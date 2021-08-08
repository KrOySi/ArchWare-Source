/*
 * Decompiled with CFR 0.150.
 */
package me.archware.impl.modules.misc;

import me.archware.base.event.EventTarget;
import me.archware.base.module.Category;
import me.archware.base.module.Module;
import me.archware.base.setting.NumericValue;
import me.archware.impl.events.EventUpdate;
import me.archware.impl.utils.TimeUtil;

public class Timer
extends Module {
    private final TimeUtil timeUtil = new TimeUtil();
    private NumericValue timer = new NumericValue("Timer", "Timer", (Module)this, 0.3f, 0.01f, 2.0f);

    public Timer() {
        super("Timer", Category.MISC);
    }

    @EventTarget
    public void onUpdate(EventUpdate event) {
        this.mc.timer.field_194147_b = this.timer.getValueNumeric();
    }
}

