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
import me.archware.impl.events.EventRender3D;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityArmorStand;
import org.lwjgl.opengl.GL11;

public class Tracers
extends Module {
    public Tracers() {
        super("Tracers", Category.RENDER);
    }

    @EventTarget
    public void onRender(EventRender3D event) {
        GlStateManager.disableFog();
        for (Entity e : this.mc.world.loadedEntityList) {
            if (!(e instanceof EntityLivingBase) || e == this.mc.player || e instanceof EntityArmorStand) continue;
            double x = e.lastTickPosX + (e.posX - e.lastTickPosX) * (double)event.getPartialTicks() - this.mc.getRenderManager().renderPosX;
            double y = e.lastTickPosY + (e.posY - e.lastTickPosY) * (double)event.getPartialTicks() - this.mc.getRenderManager().renderPosY;
            double z = e.lastTickPosZ + (e.posZ - e.lastTickPosZ) * (double)event.getPartialTicks() - this.mc.getRenderManager().renderPosZ;
            GL11.glPushMatrix();
            GL11.glDisable((int)2929);
            GL11.glDisable((int)3553);
            GL11.glColor4f((float)255.0f, (float)255.0f, (float)255.0f, (float)255.0f);
            GL11.glBegin((int)2);
            GL11.glVertex3d((double)x, (double)(y + (double)e.height), (double)z);
            GL11.glVertex3d((double)x, (double)y, (double)z);
            GL11.glVertex3d((double)0.0, (double)0.0, (double)0.0);
            GL11.glEnd();
            GL11.glEnable((int)3553);
            GL11.glEnable((int)2929);
            GL11.glPopMatrix();
        }
    }
}

