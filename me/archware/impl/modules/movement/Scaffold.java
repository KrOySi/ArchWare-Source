/*
 * Decompiled with CFR 0.150.
 */
package me.archware.impl.modules.movement;

import me.archware.base.event.EventTarget;
import me.archware.base.module.Category;
import me.archware.base.module.Module;
import me.archware.impl.events.EventPreUpdate;
import me.archware.impl.utils.TimeUtil;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;

public class Scaffold
extends Module {
    private final TimeUtil timer = new TimeUtil();

    public Scaffold() {
        super("Scaffold", Category.MOVEMENT);
    }

    @EventTarget
    public void onPreUpdate(EventPreUpdate event) {
        if (this.mc.player.onGround && this.mc.world.getBlockState(new BlockPos(this.mc.player.posX, this.mc.player.posY - 0.1, this.mc.player.posZ)).getBlock() == Blocks.AIR && this.mc.player.inventory.getCurrentItem().getItem() instanceof ItemBlock && this.timer.hasReached(300.0)) {
            float[] rotations = this.getBlockRotations(new BlockPos(this.mc.player.posX, this.mc.player.posY - 8.0, this.mc.player.posZ - 1.0));
            event.setYaw(rotations[0]);
            event.setPitch(rotations[1]);
            this.mc.player.rotationYawHead = rotations[0];
            this.mc.player.renderYawOffset = rotations[0];
            this.mc.gameSettings.keyBindUseItem.pressed = true;
            this.mc.player.swingArm(EnumHand.MAIN_HAND);
            this.mc.gameSettings.keyBindUseItem.pressed = false;
            this.timer.reset();
        }
    }

    private float[] getBlockRotations(BlockPos pos) {
        double x = (double)pos.getX() - this.mc.player.posX;
        double y = (double)pos.getY() - this.mc.player.posY;
        double z = (double)pos.getZ() - this.mc.player.posZ;
        double distance = MathHelper.sqrt(x * x + z * z);
        return new float[]{(float)(Math.atan2(z, x) * 180.0 / Math.PI) - 90.0f, (float)(-(Math.atan2(y, distance) * 180.0 / Math.PI)), (float)(-(Math.atan2(y, distance) * 180.0 / Math.PI))};
    }
}

