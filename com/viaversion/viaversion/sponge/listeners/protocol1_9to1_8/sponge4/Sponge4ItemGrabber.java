/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  org.spongepowered.api.entity.living.player.Player
 *  org.spongepowered.api.item.inventory.ItemStack
 */
package com.viaversion.viaversion.sponge.listeners.protocol1_9to1_8.sponge4;

import com.viaversion.viaversion.sponge.listeners.protocol1_9to1_8.ItemGrabber;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.item.inventory.ItemStack;

public class Sponge4ItemGrabber
implements ItemGrabber {
    @Override
    public ItemStack getItem(Player player) {
        return player.getItemInHand().orElse(null);
    }
}

