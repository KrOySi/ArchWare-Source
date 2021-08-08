/*
 * Decompiled with CFR 0.150.
 */
package me.archware.impl.modules.combat;

import me.archware.base.event.EventTarget;
import me.archware.base.module.Category;
import me.archware.base.module.Module;
import me.archware.base.setting.NumericValue;
import me.archware.impl.events.EventUpdate;
import net.minecraft.item.ItemAppleGold;

public class AutoGapple
extends Module {
    private boolean isActive;
    NumericValue health = new NumericValue("Health", "AutoGappleHealth", (Module)this, 15.0f, 5.0f, 19.0f);

    public AutoGapple() {
        super("AutoGapple", Category.COMBAT);
    }

    @EventTarget
    public void onUpdate(EventUpdate event) {
        if (this.mc.player.getHealth() <= this.health.getValueNumeric()) {
            if (!this.mc.player.isHandActive() && this.mc.player.getHeldItemOffhand().getItem() instanceof ItemAppleGold) {
                this.isActive = true;
                this.mc.gameSettings.keyBindUseItem.pressed = true;
            }
        } else if (this.isActive) {
            this.mc.gameSettings.keyBindUseItem.pressed = false;
            this.isActive = false;
        }
    }
}

