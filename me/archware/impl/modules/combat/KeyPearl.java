/*
 * Decompiled with CFR 0.150.
 */
package me.archware.impl.modules.combat;

import me.archware.base.event.EventTarget;
import me.archware.base.module.Category;
import me.archware.base.module.Module;
import me.archware.impl.events.EventMiddleClick;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.network.play.client.CPacketHeldItemChange;
import net.minecraft.util.EnumHand;

public class KeyPearl
extends Module {
    public KeyPearl() {
        super("KeyPearl", Category.COMBAT);
    }

    @EventTarget
    public void onMiddleClick(EventMiddleClick event) {
        for (int i = 0; i < 9; ++i) {
            ItemStack slot = this.mc.player.inventory.getStackInSlot(i);
            if (Item.getIdFromItem(slot.getItem()) != 368) continue;
            this.mc.getConnection().sendPacket(new CPacketHeldItemChange(this.mc.player.inventory.getSlotFor(slot)));
            this.mc.playerController.processRightClick(this.mc.player, this.mc.world, EnumHand.MAIN_HAND);
            this.mc.getConnection().sendPacket(new CPacketHeldItemChange(this.mc.player.inventory.currentItem));
        }
    }
}

