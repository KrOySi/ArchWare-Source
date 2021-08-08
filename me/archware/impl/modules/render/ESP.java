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
import me.archware.base.setting.StringValue;
import me.archware.impl.events.EventRender3D;
import me.archware.impl.utils.RenderUtil;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.AxisAlignedBB;
import org.lwjgl.opengl.GL11;

public class ESP
extends Module {
    private StringValue mode = new StringValue("Mode", "ESPMode", this, "2D", new String[]{"HitBox", "2D", "Health", "Corners"});

    public ESP() {
        super("ESP", Category.RENDER);
    }

    @EventTarget
    public void onRender(EventRender3D event) {
        this.setSuffix(this.mode.getValueString());
        switch (this.mode.getValueString()) {
            case "HitBox": {
                for (Entity e : this.mc.world.loadedEntityList) {
                    if (!(e instanceof EntityLivingBase) || e == this.mc.player) continue;
                    double x = e.lastTickPosX - this.mc.getRenderManager().renderPosX - 0.3;
                    double y = e.lastTickPosY - this.mc.getRenderManager().renderPosY;
                    double z = e.lastTickPosZ - this.mc.getRenderManager().viewerPosZ - 0.23;
                    RenderUtil.drawOutline(new AxisAlignedBB(x, y, z, x + (double)e.width, y + (double)e.height, z + (double)e.width), 5.0f, Color.BLACK);
                    RenderUtil.drawOutline(new AxisAlignedBB(x, y, z, x + (double)e.width, y + (double)e.height, z + (double)e.width), 1.8f, Color.WHITE);
                }
                break;
            }
            case "2D": {
                for (Entity e : this.mc.world.loadedEntityList) {
                    if (!(e instanceof EntityPlayer) || e == this.mc.player && this.mc.gameSettings.thirdPersonView != 1) continue;
                    double x = e.lastTickPosX + (e.posX - e.lastTickPosX) * (double)event.getPartialTicks() - this.mc.getRenderManager().renderPosX;
                    double y = e.lastTickPosY + (e.posY - e.lastTickPosY) * (double)event.getPartialTicks() - this.mc.getRenderManager().renderPosY;
                    double z = e.lastTickPosZ + (e.posZ - e.lastTickPosZ) * (double)event.getPartialTicks() - this.mc.getRenderManager().renderPosZ;
                    this.mc.entityRenderer.setupCameraTransform(event.getPartialTicks(), 0);
                    GL11.glPushMatrix();
                    GL11.glDisable((int)2929);
                    GL11.glDisable((int)3553);
                    GL11.glTranslatef((float)((float)x), (float)((float)y), (float)((float)z));
                    GL11.glNormal3f((float)0.0f, (float)1.0f, (float)0.0f);
                    GL11.glRotatef((float)(-this.mc.getRenderManager().playerViewY), (float)0.0f, (float)1.0f, (float)0.0f);
                    GL11.glRotatef((float)this.mc.getRenderManager().playerViewX, (float)1.0f, (float)0.0f, (float)0.0f);
                    GL11.glLineWidth((float)3.0f);
                    GL11.glColor3f((float)0.0f, (float)0.0f, (float)0.0f);
                    GL11.glBegin((int)3);
                    GL11.glVertex3d((double)((double)e.width + 0.1), (double)-0.4, (double)0.0);
                    GL11.glVertex3d((double)((double)e.width + 0.1), (double)((double)e.height + 0.4), (double)0.0);
                    GL11.glVertex3d((double)((double)(-e.width) - 0.1), (double)((double)e.height + 0.4), (double)0.0);
                    GL11.glVertex3d((double)((double)(-e.width) - 0.1), (double)-0.4, (double)0.0);
                    GL11.glVertex3d((double)((double)e.width + 0.1), (double)-0.4, (double)0.0);
                    GL11.glEnd();
                    GL11.glLineWidth((float)0.7f);
                    GL11.glColor3f((float)255.0f, (float)255.0f, (float)255.0f);
                    GL11.glBegin((int)3);
                    GL11.glVertex3d((double)((double)e.width + 0.1), (double)-0.4, (double)0.0);
                    GL11.glVertex3d((double)((double)e.width + 0.1), (double)((double)e.height + 0.4), (double)0.0);
                    GL11.glVertex3d((double)((double)(-e.width) - 0.1), (double)((double)e.height + 0.4), (double)0.0);
                    GL11.glVertex3d((double)((double)(-e.width) - 0.1), (double)-0.4, (double)0.0);
                    GL11.glVertex3d((double)((double)e.width + 0.1), (double)-0.4, (double)0.0);
                    GL11.glEnd();
                    GL11.glColor3f((float)0.0f, (float)0.0f, (float)0.0f);
                    GL11.glLineWidth((float)3.0f);
                    GL11.glBegin((int)3);
                    GL11.glVertex3d((double)((double)e.width + 0.4), (double)-0.4, (double)0.0);
                    GL11.glVertex3d((double)((double)e.width + 0.4), (double)((double)e.height + 0.4), (double)0.0);
                    GL11.glEnd();
                    GL11.glColor3f((float)Color.GRAY.getRed(), (float)Color.GRAY.getGreen(), (float)Color.GRAY.getBlue());
                    GL11.glLineWidth((float)0.7f);
                    GL11.glBegin((int)3);
                    GL11.glVertex3d((double)((double)e.width + 0.4), (double)-0.4, (double)0.0);
                    GL11.glVertex3d((double)((double)e.width + 0.4), (double)((double)e.height + 0.4), (double)0.0);
                    GL11.glEnd();
                    GL11.glColor3f((float)0.0f, (float)255.0f, (float)0.0f);
                    GL11.glBegin((int)3);
                    GL11.glVertex3d((double)((double)e.width + 0.4), (double)-0.4, (double)0.0);
                    GL11.glVertex3d((double)((double)e.width + 0.4), (double)((double)(((EntityPlayer)e).getHealth() / ((EntityPlayer)e).getMaxHealth()) * ((double)e.height + 0.4)), (double)0.0);
                    GL11.glEnd();
                    GL11.glBegin((int)3);
                    GL11.glEnd();
                    GL11.glEnable((int)2929);
                    GL11.glEnable((int)3553);
                    GL11.glColor3f((float)255.0f, (float)255.0f, (float)255.0f);
                    GL11.glPopMatrix();
                }
                break;
            }
            case "Health": {
                for (Entity e : this.mc.world.loadedEntityList) {
                    if (!(e instanceof EntityPlayer) || e == this.mc.player) continue;
                    double x = e.lastTickPosX + (e.posX - e.lastTickPosX) * (double)event.getPartialTicks() - this.mc.getRenderManager().renderPosX;
                    double y = e.lastTickPosY + (e.posY - e.lastTickPosY) * (double)event.getPartialTicks() - this.mc.getRenderManager().renderPosY;
                    double z = e.lastTickPosZ + (e.posZ - e.lastTickPosZ) * (double)event.getPartialTicks() - this.mc.getRenderManager().renderPosZ;
                    GL11.glPushMatrix();
                    GL11.glDisable((int)2929);
                    GL11.glDisable((int)3553);
                    GL11.glTranslatef((float)((float)x), (float)((float)y), (float)((float)z));
                    GlStateManager.glNormal3f(0.0f, 1.0f, 0.0f);
                    GlStateManager.rotate(-this.mc.getRenderManager().playerViewY, 0.0f, 1.0f, 0.0f);
                    GlStateManager.rotate(this.mc.getRenderManager().playerViewX, 1.0f, 0.0f, 0.0f);
                    GL11.glLineWidth((float)4.7f);
                    GL11.glColor3f((float)0.0f, (float)0.0f, (float)0.0f);
                    GL11.glBegin((int)3);
                    GL11.glVertex3f((float)0.4f, (float)0.0f, (float)0.0f);
                    GL11.glVertex3f((float)0.4f, (float)e.height, (float)0.0f);
                    GL11.glEnd();
                    GL11.glColor3f((float)0.0f, (float)255.0f, (float)0.0f);
                    GL11.glLineWidth((float)0.8f);
                    GL11.glColor3f((float)255.0f, (float)255.0f, (float)255.0f);
                    GL11.glBegin((int)3);
                    GL11.glVertex3f((float)0.4f, (float)0.0f, (float)0.0f);
                    GL11.glVertex3f((float)0.4f, (float)e.height, (float)0.0f);
                    GL11.glColor3f((float)0.0f, (float)255.0f, (float)0.0f);
                    GL11.glEnd();
                    GL11.glBegin((int)3);
                    GL11.glVertex3f((float)0.4f, (float)0.0f, (float)0.0f);
                    GL11.glVertex3f((float)0.4f, (float)(((EntityPlayer)e).getHealth() / ((EntityPlayer)e).getMaxHealth() * e.height), (float)0.0f);
                    GL11.glEnd();
                    GL11.glEnable((int)3553);
                    GL11.glEnable((int)2929);
                    GL11.glColor3f((float)255.0f, (float)255.0f, (float)255.0f);
                    GL11.glPopMatrix();
                }
                break;
            }
            case "Corners": {
                for (Entity e : this.mc.world.loadedEntityList) {
                    if (!(e instanceof EntityPlayer) || e == this.mc.player) continue;
                    double x = e.lastTickPosX + (e.posX - e.lastTickPosX) * (double)event.getPartialTicks() - this.mc.getRenderManager().renderPosX;
                    double y = e.lastTickPosY + (e.posY - e.lastTickPosY) * (double)event.getPartialTicks() - this.mc.getRenderManager().renderPosY;
                    double z = e.lastTickPosZ + (e.posZ - e.lastTickPosZ) * (double)event.getPartialTicks() - this.mc.getRenderManager().renderPosZ;
                    GL11.glPushMatrix();
                    GL11.glDisable((int)2929);
                    GL11.glDisable((int)3553);
                    GL11.glTranslated((double)x, (double)y, (double)z);
                    GL11.glNormal3f((float)0.0f, (float)1.0f, (float)0.0f);
                    GL11.glRotatef((float)(-this.mc.getRenderManager().playerViewY), (float)0.0f, (float)1.0f, (float)0.0f);
                    GL11.glLineWidth((float)3.0f);
                    GL11.glColor3f((float)0.0f, (float)0.0f, (float)0.0f);
                    GL11.glBegin((int)3);
                    GL11.glVertex3d((double)e.width, (double)0.2, (double)0.0);
                    GL11.glVertex3d((double)e.width, (double)-0.3, (double)0.0);
                    GL11.glVertex3d((double)0.15, (double)-0.3, (double)0.0);
                    GL11.glEnd();
                    GL11.glBegin((int)3);
                    GL11.glVertex3d((double)(-e.width), (double)0.2, (double)0.0);
                    GL11.glVertex3d((double)(-e.width), (double)-0.3, (double)0.0);
                    GL11.glVertex3d((double)-0.15, (double)-0.3, (double)0.0);
                    GL11.glEnd();
                    GL11.glBegin((int)3);
                    GL11.glVertex3d((double)0.15, (double)((double)e.height + 0.3), (double)0.0);
                    GL11.glVertex3d((double)e.width, (double)((double)e.height + 0.3), (double)0.0);
                    GL11.glVertex3d((double)e.width, (double)((double)e.height - 0.3), (double)0.0);
                    GL11.glEnd();
                    GL11.glBegin((int)3);
                    GL11.glVertex3d((double)-0.15, (double)((double)e.height + 0.3), (double)0.0);
                    GL11.glVertex3d((double)(-e.width), (double)((double)e.height + 0.3), (double)0.0);
                    GL11.glVertex3d((double)(-e.width), (double)((double)e.height - 0.3), (double)0.0);
                    GL11.glEnd();
                    GL11.glLineWidth((float)1.0f);
                    GL11.glColor3f((float)Color.CYAN.darker().getRed(), (float)Color.CYAN.darker().getGreen(), (float)Color.CYAN.darker().getBlue());
                    GL11.glBegin((int)3);
                    GL11.glVertex3d((double)e.width, (double)0.2, (double)0.0);
                    GL11.glVertex3d((double)e.width, (double)-0.3, (double)0.0);
                    GL11.glVertex3d((double)0.15, (double)-0.3, (double)0.0);
                    GL11.glEnd();
                    GL11.glBegin((int)3);
                    GL11.glVertex3d((double)(-e.width), (double)0.2, (double)0.0);
                    GL11.glVertex3d((double)(-e.width), (double)-0.3, (double)0.0);
                    GL11.glVertex3d((double)-0.15, (double)-0.3, (double)0.0);
                    GL11.glEnd();
                    GL11.glBegin((int)3);
                    GL11.glVertex3d((double)0.15, (double)((double)e.height + 0.3), (double)0.0);
                    GL11.glVertex3d((double)e.width, (double)((double)e.height + 0.3), (double)0.0);
                    GL11.glVertex3d((double)e.width, (double)((double)e.height - 0.3), (double)0.0);
                    GL11.glEnd();
                    GL11.glBegin((int)3);
                    GL11.glVertex3d((double)-0.15, (double)((double)e.height + 0.3), (double)0.0);
                    GL11.glVertex3d((double)(-e.width), (double)((double)e.height + 0.3), (double)0.0);
                    GL11.glVertex3d((double)(-e.width), (double)((double)e.height - 0.3), (double)0.0);
                    GL11.glEnd();
                    GL11.glColor3f((float)0.0f, (float)0.0f, (float)0.0f);
                    GL11.glLineWidth((float)3.0f);
                    GL11.glBegin((int)3);
                    GL11.glVertex3d((double)((double)e.width + 0.2), (double)((double)e.height + 0.3), (double)0.0);
                    GL11.glVertex3d((double)((double)e.width + 0.2), (double)-0.3, (double)0.0);
                    GL11.glEnd();
                    GL11.glColor3f((float)255.0f, (float)255.0f, (float)255.0f);
                    GL11.glLineWidth((float)1.0f);
                    GL11.glBegin((int)3);
                    GL11.glVertex3d((double)((double)e.width + 0.2), (double)((double)e.height + 0.3), (double)0.0);
                    GL11.glVertex3d((double)((double)e.width + 0.2), (double)-0.3, (double)0.0);
                    GL11.glEnd();
                    GL11.glColor3f((float)0.0f, (float)255.0f, (float)0.0f);
                    GL11.glLineWidth((float)1.0f);
                    GL11.glBegin((int)3);
                    GL11.glVertex3d((double)((double)e.width + 0.2), (double)((double)(((EntityPlayer)e).getHealth() / ((EntityPlayer)e).getMaxHealth()) * ((double)e.height + 0.3)), (double)0.0);
                    GL11.glVertex3d((double)((double)e.width + 0.2), (double)-0.3, (double)0.0);
                    GL11.glEnd();
                    GL11.glColor3f((float)1.0f, (float)1.0f, (float)1.0f);
                    GL11.glDisable((int)2960);
                    GL11.glEnable((int)2929);
                    GL11.glEnable((int)3553);
                    GL11.glPopMatrix();
                }
                break;
            }
        }
    }
}

