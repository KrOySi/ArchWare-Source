/*
 * Decompiled with CFR 0.150.
 */
package me.archware.impl.modules.misc;

import java.awt.Color;
import me.archware.ArchWare;
import me.archware.base.event.EventTarget;
import me.archware.base.module.Category;
import me.archware.base.module.Module;
import me.archware.base.setting.NumericValue;
import me.archware.impl.events.EventRender3D;
import me.archware.impl.events.EventUpdate;
import me.archware.impl.utils.BlockUtils;
import me.archware.impl.utils.RenderUtil;
import me.archware.impl.utils.TimeUtil;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextFormatting;

public class SmartWClip
extends Module {
    private final TimeUtil timeUtil = new TimeUtil();
    private final NumericValue range = new NumericValue("Range", null, (Module)this, 25.0f, 15.0f, 125.0f);
    private final NumericValue sleepTime = new NumericValue("Sleep time", null, (Module)this, 2.0f, 1.0f, 5.0f);
    private boolean inTeleportProcess;
    private BlockPos targetPosition;
    private double posY;

    public SmartWClip() {
        super("SmartWClip", Category.MISC);
    }

    @Override
    public void onEnable() {
        this.targetPosition = null;
        this.timeUtil.reset();
        this.inTeleportProcess = false;
        new Thread(() -> {
            for (BlockPos blockPos : BlockUtils.getBlocksInDistance((int)this.range.getValueNumeric())) {
                if (this.mc.world.getBlockState(blockPos).getBlock() != Blocks.WATER || !(this.mc.player.posY - (double)blockPos.getY() >= 5.0) || this.mc.world.getBlockState(new BlockPos(blockPos.getX(), blockPos.getY() + 1, blockPos.getZ())).getBlock() != Blocks.AIR && this.mc.world.getBlockState(new BlockPos(blockPos.getX(), blockPos.getY() + 1, blockPos.getZ())).getBlock().isFullBlock(this.mc.world.getBlockState(new BlockPos(blockPos.getX(), blockPos.getY() + 1, blockPos.getZ())))) continue;
                ArchWare.sendChatMessage(String.format((Object)((Object)TextFormatting.GREEN) + "Safe position is: [%s:%s:%s]", blockPos.getX(), blockPos.getY(), blockPos.getZ()));
                this.targetPosition = new BlockPos(blockPos.getX(), blockPos.getY(), blockPos.getZ());
                this.posY = this.mc.player.posY;
                return;
            }
            ArchWare.sendChatMessage((Object)((Object)TextFormatting.RED) + "Cannot find safe position :(");
            this.toggle();
        }).start();
        super.onEnable();
    }

    @EventTarget
    public void onUpdate(EventUpdate event) {
        if ((int)this.mc.player.posX == this.targetPosition.getX() && (int)this.mc.player.posZ == this.targetPosition.getZ() + 1) {
            if (!this.inTeleportProcess) {
                ArchWare.sendChatMessage((Object)((Object)TextFormatting.YELLOW) + "Teleporting you after " + (int)this.sleepTime.getValueNumeric() + "s...");
                this.timeUtil.reset();
                this.inTeleportProcess = true;
            }
            if (this.timeUtil.hasReached((long)this.sleepTime.getValueNumeric() * 1000L)) {
                this.mc.player.setPosition(this.mc.player.posX, this.targetPosition.getY(), this.mc.player.posZ);
                ArchWare.sendChatMessage((Object)((Object)TextFormatting.GREEN) + "Successfully!");
                this.toggle();
            }
        } else if (this.inTeleportProcess) {
            this.inTeleportProcess = false;
            ArchWare.sendChatMessage((Object)((Object)TextFormatting.RED) + "Cancelled.");
        }
    }

    @EventTarget
    public void onRender(EventRender3D event) {
        if (this.targetPosition != null) {
            double[] cords = new double[]{(double)this.targetPosition.getX() - this.mc.getRenderManager().renderPosX, this.posY - this.mc.getRenderManager().renderPosY, (double)this.targetPosition.getZ() - this.mc.getRenderManager().renderPosZ};
            RenderUtil.drawOutline(new AxisAlignedBB(cords[0], cords[1], cords[2], cords[0] + 1.0, cords[1] + 2.0, cords[2] + 1.0), 1.6f, Color.WHITE);
        }
    }
}

