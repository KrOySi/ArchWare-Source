/*
 * Decompiled with CFR 0.150.
 */
package me.archware.impl.modules.combat;

import me.archware.base.event.EventTarget;
import me.archware.base.module.Category;
import me.archware.base.module.Module;
import me.archware.base.setting.NumericValue;
import me.archware.impl.events.EventUpdate;
import me.archware.impl.utils.TimeUtil;
import net.minecraft.inventory.ClickType;
import net.minecraft.inventory.Slot;
import net.minecraft.item.Item;
import net.minecraft.network.play.client.CPacketEntityAction;

public class AutoTotem
extends Module {
    NumericValue health = new NumericValue("Health", "AutoTotemHealth", (Module)this, 4.0f, 2.0f, 15.0f);
    private TimeUtil timer = new TimeUtil();

    public AutoTotem() {
        super("AutoTotem", Category.COMBAT);
    }

    @EventTarget
    public void onUpdate(EventUpdate event) {
        if (this.mc.player.getHealth() <= this.health.getValueNumeric() && Item.getIdFromItem(this.mc.player.inventoryContainer.getSlot(45).getStack().getItem()) != 449) {
            this.mc.getConnection().sendPacket(new CPacketEntityAction(this.mc.player, CPacketEntityAction.Action.OPEN_INVENTORY));
            for (Slot slot : this.mc.player.inventoryContainer.inventorySlots) {
                if (Item.getIdFromItem(slot.getStack().getItem()) != 449) continue;
                this.mc.playerController.windowClick(this.mc.player.inventoryContainer.windowId, slot.slotNumber, 0, ClickType.PICKUP, this.mc.player);
                if (!this.timer.hasReached((long)(Math.random() * 300.0))) continue;
                this.mc.playerController.windowClick(this.mc.player.inventoryContainer.windowId, 45, 0, ClickType.PICKUP, this.mc.player);
                this.timer.reset();
            }
        }
    }
}

