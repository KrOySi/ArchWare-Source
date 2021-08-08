/*
 * Decompiled with CFR 0.150.
 */
package me.archware.impl.modules.misc;

import me.archware.base.event.EventTarget;
import me.archware.base.module.Category;
import me.archware.base.module.Module;
import me.archware.base.setting.NumericValue;
import me.archware.impl.events.EventPreUpdate;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.inventory.ClickType;

public class SlotClicker
extends Module {
    private NumericValue slot = new NumericValue("Slot", "SlotClicker", (Module)this, 0.0f, 0.0f, 37.0f);

    public SlotClicker() {
        super("SlotClicker", Category.MISC);
    }

    @EventTarget
    public void onPreUpdate(EventPreUpdate event) {
        if (this.mc.currentScreen instanceof GuiContainer) {
            this.mc.playerController.windowClick(this.mc.player.openContainer.windowId, (int)this.slot.getValueNumeric(), 0, ClickType.PICKUP, this.mc.player);
        }
    }
}

