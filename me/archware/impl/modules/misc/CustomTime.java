/*
 * Decompiled with CFR 0.150.
 */
package me.archware.impl.modules.misc;

import me.archware.base.event.EventTarget;
import me.archware.base.module.Category;
import me.archware.base.module.Module;
import me.archware.base.setting.BooleanValue;
import me.archware.base.setting.NumericValue;
import me.archware.impl.events.EventPreUpdate;

public class CustomTime
extends Module {
    private final NumericValue time = new NumericValue("Time", "CustomTime", (Module)this, 12000.0f, 1000.0f, 16000.0f);
    private final BooleanValue cycle = new BooleanValue("Cycle", "CustomTimeCycle", this, false);
    private final NumericValue cycleSpeed = new NumericValue("Cycle speed", "CustomTimeCycleSpeed", (Module)this, 55.0f, 5.0f, 125.0f);

    public CustomTime() {
        super("CustomTime", Category.RENDER);
    }

    @EventTarget
    public void onTick(EventPreUpdate event) {
        if (!this.cycle.getValueBoolean()) {
            this.mc.world.setWorldTime((long)this.time.getValueNumeric());
        } else {
            this.mc.world.setWorldTime(this.mc.world.getWorldTime() >= 20000L ? 50L : this.mc.world.getWorldTime() + (long)this.cycleSpeed.getValueNumeric());
        }
    }
}

