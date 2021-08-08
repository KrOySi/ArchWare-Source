/*
 * Decompiled with CFR 0.150.
 */
package me.archware.impl.utils;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;

public class ItemUtils {
    private static final Minecraft mc = Minecraft.getMinecraft();
    public static final int[] BLACKLISTED_ITEMS_IDS = new int[]{6, 24, 30, 31, 32, 37, 38, 39, 40, 44, 50, 53, 67, 83, 85, 259, 263, 266, 265, 264, 281, 314, 315, 316, 317, 318, 319, 325, 326, 327, 328, 329, 331, 332, 334, 344, 346, 351, 367, 383, 352, 374, 351, 295, 296, 321, 340, 384};

    public static ItemStack getBlock() {
        for (int i = 0; i < ItemUtils.mc.player.inventory.mainInventory.size(); ++i) {
            ItemStack is = ItemUtils.mc.player.inventory.mainInventory.get(i);
            if (Block.getBlockFromItem(is.getItem()) == Blocks.AIR) continue;
            return is;
        }
        return null;
    }

    public static int blockCount() {
        int blocks = 0;
        for (int i = 0; i < ItemUtils.mc.player.inventory.mainInventory.size(); ++i) {
            ItemStack is = ItemUtils.mc.player.inventory.mainInventory.get(i);
            if (Block.getBlockFromItem(is.getItem()) == Blocks.AIR) continue;
            blocks += is.stackSize;
        }
        return blocks;
    }

    public static ItemStack getSword() {
        for (int i = 0; i < ItemUtils.mc.player.inventory.mainInventory.size(); ++i) {
            ItemStack is = ItemUtils.mc.player.inventory.mainInventory.get(i);
            if (!(is.getItem() instanceof ItemSword)) continue;
            return is;
        }
        return null;
    }
}

