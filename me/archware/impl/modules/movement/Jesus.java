/*
 * Decompiled with CFR 0.150.
 */
package me.archware.impl.modules.movement;

import me.archware.base.event.EventTarget;
import me.archware.base.module.Category;
import me.archware.base.module.Module;
import me.archware.base.setting.NumericValue;
import me.archware.base.setting.StringValue;
import me.archware.impl.events.EventPreUpdate;
import me.archware.impl.utils.MoveUtils;

public class Jesus
extends Module {
    private StringValue mode = new StringValue("Mode", "JesusMode", this, "Matrix", new String[]{"Matrix"});
    private NumericValue motion = new NumericValue("Motion", "JesusMotion", (Module)this, 0.6f, 0.2f, 1.9f);
    private NumericValue motionY = new NumericValue("MotionY", "JesusMotionY", (Module)this, 0.6f, 0.2f, 0.8f);

    public Jesus() {
        super("Jesus", Category.MOVEMENT);
    }

    @EventTarget
    public void onPreUpdate(EventPreUpdate event) {
        this.setSuffix(this.mode.getValueString());
        switch (this.mode.getValueString()) {
            case "Matrix": {
                if (!this.mc.player.isInWater()) break;
                this.mc.player.motionY = this.motionY.getValueNumeric();
                MoveUtils.setSpeed(this.motion.getValueNumeric());
            }
        }
    }
}

