/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  javafx.animation.Interpolator
 *  org.lwjgl.opengl.GL11
 */
package me.archware.impl.modules.render;

import java.awt.Color;
import java.awt.Font;
import javafx.animation.Interpolator;
import me.archware.base.clickgui.util.FontUtil;
import me.archware.base.event.EventTarget;
import me.archware.base.module.Category;
import me.archware.base.module.Module;
import me.archware.impl.events.EventRender2D;
import me.archware.impl.modules.combat.KillAura;
import me.archware.impl.utils.RenderUtil;
import me.archware.impl.utils.TimeUtil;
import me.archware.impl.utils.font.CustomFontRenderer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import org.lwjgl.opengl.GL11;

public class TargetHUD
extends Module {
    private final CustomFontRenderer font = new CustomFontRenderer(new Font("Arial", 0, 10), true, true);
    private final TimeUtil timer = new TimeUtil();
    private float healthWidth = 20.0f;

    public TargetHUD() {
        super("TargetHUD", Category.RENDER);
    }

    @EventTarget
    public void onRender2D(EventRender2D event) {
        if (KillAura.target instanceof EntityPlayer) {
            if (String.valueOf(this.healthWidth).equals("NaN")) {
                this.healthWidth = 60.0f;
            }
            EntityPlayer target = (EntityPlayer)KillAura.target;
            ScaledResolution scaledResolution = new ScaledResolution(this.mc);
            RenderUtil.drawRoundedRect(scaledResolution.getScaledWidth() / 2 + 40, scaledResolution.getScaledHeight() / 2 + 60, 120.0, 50.0, 4.0, new Color(13, 13, 13).hashCode());
            GL11.glPushMatrix();
            GlStateManager.color(1.0f, 1.0f, 1.0f);
            GuiInventory.drawEntityOnScreen(scaledResolution.getScaledWidth() / 2 + 55, target.isSneaking() ? scaledResolution.getScaledHeight() / 2 + 102 : scaledResolution.getScaledHeight() / 2 + 105, 21, -16.0f, 0.0f, target);
            GL11.glPopMatrix();
            FontUtil.drawString(target.getName(), scaledResolution.getScaledWidth() / 2 + 70, scaledResolution.getScaledHeight() / 2 + 67, -1);
            this.font.drawString("Distance: " + (int)this.mc.player.getDistanceToEntity(target), scaledResolution.getScaledWidth() / 2 + 70, scaledResolution.getScaledHeight() / 2 + 77, -1);
            this.font.drawString("MS: " + this.mc.getConnection().getPlayerInfo(target.getUniqueID()).getResponseTime(), scaledResolution.getScaledWidth() / 2 + 70, scaledResolution.getScaledHeight() / 2 + 83, -1);
            this.healthWidth = (float)Interpolator.EASE_OUT.interpolate((double)this.healthWidth, (double)(target.getHealth() / target.getMaxHealth() * 60.0f), 12.0 / (double)Minecraft.getDebugFPS());
            RenderUtil.drawRect(scaledResolution.getScaledWidth() / 2 + 70, (double)(scaledResolution.getScaledHeight() / 2 + 90), 60.0, 2.0, Color.GRAY.hashCode());
            RenderUtil.drawRect(scaledResolution.getScaledWidth() / 2 + 70, (double)(scaledResolution.getScaledHeight() / 2 + 90), (double)this.healthWidth, 2.0, Color.GREEN.hashCode());
            int gapples = 0;
            if (Item.getIdFromItem(target.inventory.getCurrentItem().getItem()) == 322) {
                gapples = target.inventory.getCurrentItem().stackSize;
            } else if (Item.getIdFromItem(target.getHeldItemOffhand().getItem()) == 322) {
                gapples = target.getHeldItemOffhand().stackSize;
            }
            GL11.glPushMatrix();
            GL11.glTranslatef((float)(scaledResolution.getScaledWidth() / 2 + 68), (float)(scaledResolution.getScaledHeight() / 2 + 93), (float)0.0f);
            GL11.glScaled((double)0.85, (double)0.85, (double)0.0);
            this.mc.getRenderItem().renderItemIntoGUI(new ItemStack(Items.GOLDEN_APPLE), 0, 0);
            GL11.glScaled((double)0.83, (double)0.83, (double)0.0);
            this.mc.fontRendererObj.drawStringWithShadow(String.valueOf(gapples), 7.5f, 10.0f, -1);
            GL11.glPopMatrix();
        }
    }
}

