/*
 * Decompiled with CFR 0.150.
 */
package me.archware.impl.modules.player;

import me.archware.base.event.EventTarget;
import me.archware.base.module.Category;
import me.archware.base.module.Module;
import me.archware.base.setting.NumericValue;
import me.archware.impl.events.EventUpdate;
import me.archware.impl.utils.TimeUtil;
import net.minecraft.inventory.ClickType;
import net.minecraft.inventory.ContainerChest;
import net.minecraft.inventory.Slot;
import net.minecraft.item.Item;

public class ChestStealer
extends Module {
    private final TimeUtil timer = new TimeUtil();
    private final NumericValue delay = new NumericValue("Delay", "ChestStealerDelay", (Module)this, 50.0f, 5.0f, 1000.0f);

    public ChestStealer() {
        super("ChestStealer", Category.PLAYER);
    }

    @EventTarget
    public void onUpdate(EventUpdate event) {
        if (this.mc.player.openContainer instanceof ContainerChest) {
            ContainerChest chest = (ContainerChest)this.mc.player.openContainer;
            for (int i = 0; i < chest.getLowerChestInventory().getSizeInventory(); ++i) {
                Slot slot = (Slot)chest.inventorySlots.get(i);
                if (!this.timer.hasReached((long)this.delay.getValueNumeric()) || Item.getIdFromItem(slot.getStack().getItem()) == 0) continue;
                this.mc.playerController.windowClick(chest.windowId, slot.slotNumber, 0, ClickType.QUICK_MOVE, this.mc.player);
                this.timer.reset();
            }
        }
    }
}

