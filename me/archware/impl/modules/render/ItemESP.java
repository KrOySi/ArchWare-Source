/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  org.lwjgl.opengl.GL11
 */
package me.archware.impl.modules.render;

import java.awt.Color;
import me.archware.base.event.EventTarget;
import me.archware.base.module.Category;
import me.archware.base.module.Module;
import me.archware.base.setting.BooleanValue;
import me.archware.base.setting.StringValue;
import me.archware.impl.events.EventRender3D;
import me.archware.impl.utils.RenderUtil;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.util.math.AxisAlignedBB;
import org.lwjgl.opengl.GL11;

public class ItemESP
extends Module {
    private final StringValue mode = new StringValue("Mode", "ItemESPMode", this, "Box", new String[]{"Box", "Corners"});
    private final BooleanValue tag = new BooleanValue("Tag", "ItemESPTag", this, true);

    public ItemESP() {
        super("ItemESP", Category.RENDER);
    }

    @EventTarget
    public void onRender(EventRender3D event) {
        this.setSuffix(this.mode.getValueString());
        for (Entity e : this.mc.world.loadedEntityList) {
            if (!(e instanceof EntityItem)) continue;
            double x = e.lastTickPosX + (e.posX - e.lastTickPosX) * (double)event.getPartialTicks() - this.mc.getRenderManager().renderPosX - 0.1;
            double y = e.lastTickPosY + (e.posY - e.lastTickPosY) * (double)event.getPartialTicks() - this.mc.getRenderManager().renderPosY;
            double z = e.lastTickPosZ + (e.posZ - e.lastTickPosZ) * (double)event.getPartialTicks() - this.mc.getRenderManager().renderPosZ - 0.15;
            if (this.tag.getValueBoolean()) {
                GL11.glPushMatrix();
                GL11.glDisable((int)2929);
                GL11.glDisable((int)3553);
                GL11.glNormal3f((float)0.0f, (float)1.0f, (float)0.0f);
                GlStateManager.disableLighting();
                GlStateManager.enableBlend();
                float size = Math.min(Math.max(1.2f * (this.mc.player.getDistanceToEntity(e) * 0.15f), 1.25f), 6.0f) * 0.014f;
                GL11.glTranslatef((float)((float)x), (float)((float)y + e.height + 0.5f), (float)((float)z));
                GlStateManager.glNormal3f(0.0f, 1.0f, 0.0f);
                GlStateManager.rotate(-this.mc.getRenderManager().playerViewY, 0.0f, 1.0f, 0.0f);
                GlStateManager.rotate(this.mc.getRenderManager().playerViewX, 1.0f, 0.0f, 0.0f);
                GL11.glScalef((float)(-size), (float)(-size), (float)size);
                Gui.drawRect(-this.mc.fontRendererObj.getStringWidth(((EntityItem)e).getEntityItem().getDisplayName()) / 2 - 2, -2.0, this.mc.fontRendererObj.getStringWidth(((EntityItem)e).getEntityItem().getDisplayName()) / 2 + 2, 9.0, Integer.MIN_VALUE);
                this.mc.fontRendererObj.drawString(((EntityItem)e).getEntityItem().getDisplayName(), -this.mc.fontRendererObj.getStringWidth(((EntityItem)e).getEntityItem().getDisplayName()) / 2, 0, -1);
                GL11.glEnable((int)3553);
                GL11.glEnable((int)2929);
                GL11.glPopMatrix();
            }
            switch (this.mode.getValueString()) {
                case "Box": {
                    RenderUtil.drawOutline(new AxisAlignedBB(x, y, z, x + 0.5, y + 0.5, z + 0.5), 1.6f, RenderUtil.rainbow(2500));
                    break;
                }
                case "Corners": {
                    GL11.glPushMatrix();
                    GL11.glDisable((int)2929);
                    GL11.glDisable((int)3553);
                    GL11.glTranslated((double)(x + 0.1), (double)y, (double)z);
                    GL11.glNormal3f((float)0.0f, (float)1.0f, (float)0.0f);
                    GL11.glRotatef((float)(-this.mc.getRenderManager().playerViewY), (float)0.0f, (float)1.0f, (float)0.0f);
                    GL11.glRotatef((float)this.mc.getRenderManager().playerViewX, (float)1.0f, (float)0.0f, (float)0.0f);
                    GL11.glLineWidth((float)3.0f);
                    GL11.glColor3f((float)0.0f, (float)0.0f, (float)0.0f);
                    GL11.glBegin((int)3);
                    GL11.glVertex3d((double)e.width, (double)0.1, (double)0.0);
                    GL11.glVertex3d((double)e.width, (double)-0.1, (double)0.0);
                    GL11.glVertex3d((double)0.15, (double)-0.1, (double)0.0);
                    GL11.glEnd();
                    GL11.glBegin((int)3);
                    GL11.glVertex3d((double)(-e.width), (double)0.1, (double)0.0);
                    GL11.glVertex3d((double)(-e.width), (double)-0.1, (double)0.0);
                    GL11.glVertex3d((double)-0.15, (double)-0.1, (double)0.0);
                    GL11.glEnd();
                    GL11.glBegin((int)3);
                    GL11.glVertex3d((double)0.15, (double)((double)e.height + 0.1), (double)0.0);
                    GL11.glVertex3d((double)e.width, (double)((double)e.height + 0.1), (double)0.0);
                    GL11.glVertex3d((double)e.width, (double)((double)e.height - 0.1), (double)0.0);
                    GL11.glEnd();
                    GL11.glBegin((int)3);
                    GL11.glVertex3d((double)-0.15, (double)((double)e.height + 0.1), (double)0.0);
                    GL11.glVertex3d((double)(-e.width), (double)((double)e.height + 0.1), (double)0.0);
                    GL11.glVertex3d((double)(-e.width), (double)((double)e.height - 0.1), (double)0.0);
                    GL11.glEnd();
                    GL11.glLineWidth((float)1.0f);
                    GL11.glColor3f((float)Color.CYAN.darker().getRed(), (float)Color.CYAN.darker().getGreen(), (float)Color.CYAN.darker().getBlue());
                    GL11.glBegin((int)3);
                    GL11.glVertex3d((double)e.width, (double)0.1, (double)0.0);
                    GL11.glVertex3d((double)e.width, (double)-0.1, (double)0.0);
                    GL11.glVertex3d((double)0.15, (double)-0.1, (double)0.0);
                    GL11.glEnd();
                    GL11.glBegin((int)3);
                    GL11.glVertex3d((double)(-e.width), (double)0.1, (double)0.0);
                    GL11.glVertex3d((double)(-e.width), (double)-0.1, (double)0.0);
                    GL11.glVertex3d((double)-0.15, (double)-0.1, (double)0.0);
                    GL11.glEnd();
                    GL11.glBegin((int)3);
                    GL11.glVertex3d((double)0.15, (double)((double)e.height + 0.1), (double)0.0);
                    GL11.glVertex3d((double)e.width, (double)((double)e.height + 0.1), (double)0.0);
                    GL11.glVertex3d((double)e.width, (double)((double)e.height - 0.1), (double)0.0);
                    GL11.glEnd();
                    GL11.glBegin((int)3);
                    GL11.glVertex3d((double)-0.15, (double)((double)e.height + 0.1), (double)0.0);
                    GL11.glVertex3d((double)(-e.width), (double)((double)e.height + 0.1), (double)0.0);
                    GL11.glVertex3d((double)(-e.width), (double)((double)e.height - 0.1), (double)0.0);
                    GL11.glEnd();
                    GL11.glColor3f((float)1.0f, (float)1.0f, (float)1.0f);
                    GL11.glDisable((int)2960);
                    GL11.glEnable((int)2929);
                    GL11.glEnable((int)3553);
                    GL11.glPopMatrix();
                }
            }
        }
    }
}

