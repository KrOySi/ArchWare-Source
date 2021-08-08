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
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import org.lwjgl.opengl.GL11;

public class Skeleton
extends Module {
    public Skeleton() {
        super("Skeleton", Category.RENDER);
    }

    @EventTarget
    public void onRender(EventRender3D event) {
        GL11.glPushMatrix();
        for (Entity e : this.mc.world.loadedEntityList) {
            if (!(e instanceof EntityPlayer)) continue;
        }
        GL11.glPopMatrix();
    }
}

