/*
 * Decompiled with CFR 0.150.
 */
package me.archware.impl.modules.render;

import java.awt.Color;
import me.archware.base.event.EventTarget;
import me.archware.base.module.Category;
import me.archware.base.module.Module;
import me.archware.impl.events.EventRender3D;
import me.archware.impl.utils.RenderUtil;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.util.math.AxisAlignedBB;

public class ChestESP
extends Module {
    public ChestESP() {
        super("ChestESP", Category.RENDER);
    }

    @EventTarget
    public void onRender(EventRender3D event) {
        for (TileEntity e : this.mc.world.loadedTileEntityList) {
            if (!(e instanceof TileEntityChest)) continue;
            double x = (double)e.getPos().getX() - this.mc.getRenderManager().renderPosX;
            double y = (double)e.getPos().getY() - this.mc.getRenderManager().renderPosY;
            double z = (double)e.getPos().getZ() - this.mc.getRenderManager().renderPosZ;
            RenderUtil.drawOutline(new AxisAlignedBB(x, y, z, x + 1.0, y + 1.0, z + 1.0), 1.6f, Color.ORANGE);
        }
    }
}

