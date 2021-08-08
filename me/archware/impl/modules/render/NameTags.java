/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  org.lwjgl.opengl.GL11
 */
package me.archware.impl.modules.render;

import java.awt.Color;
import java.util.Map;
import me.archware.base.event.EventTarget;
import me.archware.base.module.Category;
import me.archware.base.module.Module;
import me.archware.impl.events.EventRender3D;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;
import org.lwjgl.opengl.GL11;

public class NameTags
extends Module {
    public NameTags() {
        super("NameTags", Category.RENDER);
    }

    @EventTarget
    public void onRender(EventRender3D event) {
        for (Entity e : this.mc.world.loadedEntityList) {
            if (!(e instanceof EntityPlayer) || e == this.mc.player) continue;
            double x = e.lastTickPosX + (e.posX - e.lastTickPosX) * (double)event.getPartialTicks() - this.mc.getRenderManager().renderPosX;
            double y = e.lastTickPosY + (e.posY - e.lastTickPosY) * (double)event.getPartialTicks() - this.mc.getRenderManager().renderPosY;
            double z = e.lastTickPosZ + (e.posZ - e.lastTickPosZ) * (double)event.getPartialTicks() - this.mc.getRenderManager().renderPosZ;
            GL11.glPushMatrix();
            GL11.glDisable((int)2929);
            GL11.glDisable((int)3553);
            GL11.glNormal3f((float)0.0f, (float)1.0f, (float)0.0f);
            GlStateManager.disableLighting();
            GlStateManager.enableBlend();
            float size = Math.min(Math.max(1.2f * (this.mc.player.getDistanceToEntity(e) * 0.15f), 1.25f), 6.0f) * 0.02f;
            GL11.glTranslatef((float)((float)x), (float)((float)y + e.height + 0.4f), (float)((float)z));
            GlStateManager.glNormal3f(0.0f, 1.0f, 0.0f);
            GlStateManager.rotate(-this.mc.getRenderManager().playerViewY, 0.0f, 1.0f, 0.0f);
            GlStateManager.rotate(this.mc.getRenderManager().playerViewX, 1.0f, 0.0f, 0.0f);
            GL11.glScalef((float)(-size), (float)(-size), (float)size);
            int health = (int)(((EntityPlayer)e).getHealth() / ((EntityPlayer)e).getMaxHealth() * 100.0f);
            Gui.drawRect(-this.mc.fontRendererObj.getStringWidth(e.getName() + " " + health + "%") / 2 - 2, -2.0, this.mc.fontRendererObj.getStringWidth(e.getName() + " " + health + "%") / 2 + 2, 9.0, new Color(17, 17, 17).hashCode());
            this.mc.fontRendererObj.drawString(e.getName() + " " + (Object)((Object)TextFormatting.GREEN) + health + "%", -this.mc.fontRendererObj.getStringWidth(e.getName() + " " + health + "%") / 2, 0, -1);
            GL11.glEnable((int)3553);
            int posX = -this.mc.fontRendererObj.getStringWidth(e.getName() + " " + health + "%") / 2 - 8;
            if (Item.getIdFromItem(((EntityPlayer)e).inventory.getCurrentItem().getItem()) != 0) {
                this.mc.getRenderItem().zLevel = -100.0f;
                this.mc.getRenderItem().renderItemIntoGUI(new ItemStack(((EntityPlayer)e).inventory.getCurrentItem().getItem()), posX - 2, -20);
                this.mc.getRenderItem().zLevel = 0.0f;
                int posY = -30;
                Map<Enchantment, Integer> enchantments = EnchantmentHelper.getEnchantments(((EntityPlayer)e).inventory.getCurrentItem());
                for (Enchantment enchantment : enchantments.keySet()) {
                    int level = EnchantmentHelper.getEnchantmentLevel(enchantment, ((EntityPlayer)e).inventory.getCurrentItem());
                    this.mc.fontRendererObj.drawCenteredStringWithShadow(String.valueOf(enchantment.getName().substring(12).charAt(0)).toUpperCase() + level, posX + 6, posY, -1);
                    posY -= 12;
                }
                posX += 15;
            }
            for (ItemStack item : e.getArmorInventoryList()) {
                this.mc.getRenderItem().zLevel = -100.0f;
                this.mc.getRenderItem().renderItemIntoGUI(new ItemStack(item.getItem()), posX, -20);
                this.mc.getRenderItem().zLevel = 0.0f;
                int posY = -30;
                Map<Enchantment, Integer> enchantments = EnchantmentHelper.getEnchantments(item);
                for (Enchantment enchantment : enchantments.keySet()) {
                    int level = EnchantmentHelper.getEnchantmentLevel(enchantment, item);
                    this.mc.fontRendererObj.drawCenteredStringWithShadow(String.valueOf(enchantment.getName().substring(12).charAt(0)).toUpperCase() + level, posX + 9, posY, -1);
                    posY -= 12;
                }
                posX += 17;
            }
            int gapples = 0;
            if (Item.getIdFromItem(((EntityPlayer)e).inventory.getCurrentItem().getItem()) == 322) {
                gapples = ((EntityPlayer)e).inventory.getCurrentItem().stackSize;
            } else if (Item.getIdFromItem(((EntityPlayer)e).getHeldItemOffhand().getItem()) == 322) {
                gapples = ((EntityPlayer)e).getHeldItemOffhand().stackSize;
            }
            if (gapples > 0) {
                this.mc.getRenderItem().zLevel = -100.0f;
                this.mc.getRenderItem().renderItemIntoGUI(new ItemStack(Items.GOLDEN_APPLE), posX, -20);
                this.mc.getRenderItem().zLevel = 0.0f;
                this.mc.fontRendererObj.drawCenteredStringWithShadow(String.valueOf(gapples), posX + 9, -30.0, -1);
            }
            GL11.glEnable((int)2929);
            GL11.glPopMatrix();
        }
    }
}

