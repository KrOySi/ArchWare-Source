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
import net.minecraft.init.Blocks;
import net.minecraft.util.math.AxisAlignedBB;

public class BlockOverlay
extends Module {
    public BlockOverlay() {
        super("BlockOverlay", Category.RENDER);
    }

    @EventTarget
    public void onRender(EventRender3D event) {
        if (this.mc.world.getBlockState(this.mc.objectMouseOver.getBlockPos()).getBlock() != Blocks.AIR && this.mc.world.getBlockState(this.mc.objectMouseOver.getBlockPos()).getBlock().isFullBlock(this.mc.world.getBlockState(this.mc.objectMouseOver.getBlockPos()))) {
            double[] cords = new double[]{(double)this.mc.objectMouseOver.getBlockPos().getX() - this.mc.getRenderManager().renderPosX, (double)this.mc.objectMouseOver.getBlockPos().getY() - this.mc.getRenderManager().renderPosY, (double)this.mc.objectMouseOver.getBlockPos().getZ() - this.mc.getRenderManager().renderPosZ};
            RenderUtil.drawOutline(new AxisAlignedBB(cords[0], cords[1], cords[2], cords[0] + 1.0, cords[1] + 1.0, cords[2] + 1.0), 1.7f, Color.WHITE);
        }
    }
}

