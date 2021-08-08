/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  org.lwjgl.opengl.GL11
 */
package me.archware.impl.modules.render;

import me.archware.base.event.EventTarget;
import me.archware.base.module.Category;
import me.archware.base.module.Module;
import me.archware.impl.events.EventRender2D;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import org.lwjgl.opengl.GL11;

public class ArmorHUD
extends Module {
    public ArmorHUD() {
        super("ArmorHUD", Category.RENDER);
    }

    @EventTarget
    public void onRender(EventRender2D event) {
        ScaledResolution sr = new ScaledResolution(this.mc);
        int posY = sr.getScaledHeight() - 28;
        for (ItemStack item : this.mc.player.getArmorInventoryList()) {
            if (Item.getIdFromItem(item.getItem()) == 0) continue;
            GL11.glPushMatrix();
            GL11.glTranslatef((float)(sr.getScaledWidth() - 20), (float)posY, (float)0.0f);
            GL11.glScaled((double)1.15, (double)1.15, (double)0.0);
            this.mc.getRenderItem().renderItemIntoGUI(item, 0, 0);
            GL11.glPopMatrix();
            posY -= 16;
        }
    }
}

