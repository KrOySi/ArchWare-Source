/*
 * Decompiled with CFR 0.150.
 */
package me.archware.impl.modules.player;

import me.archware.base.event.EventTarget;
import me.archware.base.module.Category;
import me.archware.base.module.Module;
import me.archware.base.setting.BooleanValue;
import me.archware.impl.events.EventPacketSend;
import net.minecraft.entity.item.EntityArmorStand;
import net.minecraft.init.Blocks;
import net.minecraft.network.play.client.CPacketPlayerTryUseItemOnBlock;
import net.minecraft.network.play.client.CPacketUseEntity;

public class NoInteract
extends Module {
    private BooleanValue craft = new BooleanValue("Crafting table", "NoInteractCraftingTable", this, true);
    private BooleanValue furnace = new BooleanValue("Furnace", "NoInteractFurnace", this, true);
    private final BooleanValue armorStand = new BooleanValue("Armor Stand", "NoInteractArmorStand", this, true);

    public NoInteract() {
        super("NoInteract", Category.PLAYER);
    }

    @EventTarget
    public void onPacket(EventPacketSend event) {
        if (event.getPacket() instanceof CPacketPlayerTryUseItemOnBlock) {
            if (this.mc.world.getBlockState(this.mc.objectMouseOver.getBlockPos()).getBlock() == Blocks.CRAFTING_TABLE && this.craft.getValueBoolean()) {
                event.setCancelled(true);
            }
            if (this.mc.world.getBlockState(this.mc.objectMouseOver.getBlockPos()).getBlock() == Blocks.FURNACE && this.furnace.getValueBoolean()) {
                event.setCancelled(true);
            }
        } else if (event.getPacket() instanceof CPacketUseEntity && ((CPacketUseEntity)event.getPacket()).getEntityFromWorld(this.mc.world) instanceof EntityArmorStand && !this.armorStand.getValueBoolean()) {
            event.setCancelled(true);
        }
    }
}

