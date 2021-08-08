/*
 * Decompiled with CFR 0.150.
 */
package me.archware.impl.utils;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;

public class RotationUtils {
    private static final Minecraft mc = Minecraft.getMinecraft();

    public static float[] getRotation(Entity entity) {
        Vec3d eyesPos = new Vec3d(RotationUtils.mc.player.posX + Math.random() / 10.0, RotationUtils.mc.player.posY + (double)RotationUtils.mc.player.getEyeHeight(), RotationUtils.mc.player.posZ + Math.random() / 10.0);
        double diffX = entity.getPositionVector().xCoord - eyesPos.xCoord;
        double diffY = entity.getPositionVector().yCoord - eyesPos.yCoord;
        double diffZ = entity.getPositionVector().zCoord - eyesPos.zCoord;
        double diffXZ = Math.sqrt(diffX * diffX + diffZ * diffZ);
        float yaw = MathHelper.wrapDegrees((float)Math.toDegrees(Math.atan2(diffZ, diffX)) - 90.0f);
        float pitch = MathHelper.wrapDegrees((float)(-Math.toDegrees(Math.atan2(diffY, diffXZ))) - 10.0f);
        float f = RotationUtils.mc.gameSettings.mouseSensitivity * 0.6f + 0.2f;
        float gcd = f * f * f * 1.2f;
        yaw -= yaw % gcd;
        pitch -= pitch % gcd;
        return new float[]{yaw, pitch};
    }
}

