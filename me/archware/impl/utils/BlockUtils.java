/*
 * Decompiled with CFR 0.150.
 */
package me.archware.impl.utils;

import java.util.ArrayList;
import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;

public class BlockUtils {
    private static Minecraft mc = Minecraft.getMinecraft();

    public static List<BlockPos> getBlocksInDistance(int distance) {
        ArrayList<BlockPos> blocks = new ArrayList<BlockPos>();
        BlockPos.getAllInBox(new BlockPos(BlockUtils.mc.player.posX - (double)distance, BlockUtils.mc.player.posY - (double)distance, BlockUtils.mc.player.posZ - (double)distance), new BlockPos(BlockUtils.mc.player.posX + (double)distance, BlockUtils.mc.player.posY + (double)distance, BlockUtils.mc.player.posZ + (double)distance)).forEach(blocks::add);
        return blocks;
    }

    public float[] getRotations(BlockPos block) {
        double x = (double)block.getX() - BlockUtils.mc.player.posX;
        double z = (double)block.getZ() - BlockUtils.mc.player.posZ;
        double y = (double)block.getY() - (BlockUtils.mc.player.posY + (double)BlockUtils.mc.player.getEyeHeight());
        double sqrted = MathHelper.sqrt(x * x + z * z);
        float yaw = (float)(Math.atan2(z, x) * 180.0 / Math.PI);
        float pitch = (float)(-(Math.atan2(yaw, sqrted) * 180.0 / Math.PI));
        return new float[]{yaw, pitch};
    }
}

