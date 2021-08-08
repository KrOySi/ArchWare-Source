/*
 * Decompiled with CFR 0.150.
 */
package me.archware.impl.modules.misc;

import me.archware.base.event.EventTarget;
import me.archware.base.module.Category;
import me.archware.base.module.Module;
import me.archware.base.setting.NumericValue;
import me.archware.impl.events.EventPacketSend;
import me.archware.impl.events.EventUpdate;
import net.minecraft.client.entity.EntityOtherPlayerMP;
import net.minecraft.network.play.client.CPacketPlayer;
import net.minecraft.world.GameType;

public class Freecam
extends Module {
    private GameType prevGameType;
    private final NumericValue speed = new NumericValue("Speed", "FreecamSpeed", (Module)this, 0.5f, 0.5f, 5.0f);
    private EntityOtherPlayerMP entity;

    public Freecam() {
        super("Freecam", Category.MISC);
    }

    @Override
    public void onEnable() {
        try {
            this.entity = new EntityOtherPlayerMP(this.mc.world, this.mc.player.getGameProfile());
            this.entity.posX = this.mc.player.posX;
            this.entity.posY = this.mc.player.posY;
            this.entity.posZ = this.mc.player.posZ;
            this.entity.rotationYaw = this.mc.player.rotationYaw;
            this.entity.rotationPitch = this.mc.player.rotationPitch;
            this.mc.world.addEntityToWorld(-1, this.entity);
            this.prevGameType = this.mc.playerController.getCurrentGameType();
            this.mc.playerController.setGameType(GameType.SPECTATOR);
        }
        catch (NullPointerException nullPointerException) {
            // empty catch block
        }
        super.onEnable();
    }

    @Override
    public void onDisable() {
        this.mc.player.posX = this.entity.posX;
        this.mc.player.posY = this.entity.posY;
        this.mc.player.posZ = this.entity.posZ;
        this.mc.player.rotationYaw = this.entity.rotationYaw;
        this.mc.player.rotationPitch = this.entity.rotationPitch;
        this.mc.world.removeEntity(this.entity);
        this.mc.playerController.setGameType(this.prevGameType);
        super.onDisable();
    }

    @EventTarget
    public void onUpdate(EventUpdate event) {
        double forward = this.mc.player.movementInput.field_192832_b;
        double strafe = this.mc.player.movementInput.moveStrafe;
        float yaw = this.mc.player.rotationYaw;
        if (forward == 0.0 && strafe == 0.0) {
            this.mc.player.motionX = 0.0;
            this.mc.player.motionZ = 0.0;
        } else {
            if (forward != 0.0) {
                if (strafe > 0.0) {
                    yaw += (float)(forward > 0.0 ? -45 : 45);
                } else if (strafe < 0.0) {
                    yaw += (float)(forward > 0.0 ? 45 : -45);
                }
                strafe = 0.0;
                if (forward > 0.0) {
                    forward = 1.0;
                } else if (forward < 0.0) {
                    forward = -1.0;
                }
            }
            this.mc.player.setPosition(this.mc.player.posX + forward * (double)this.speed.getValueNumeric() * Math.cos(Math.toRadians(yaw + 90.0f)) + strafe * (double)this.speed.getValueNumeric() * Math.sin(Math.toRadians(yaw + 90.0f)), this.mc.gameSettings.keyBindJump.isKeyDown() ? this.mc.player.posY + (double)this.speed.getValueNumeric() : (this.mc.player.isSneaking() ? this.mc.player.posY - (double)this.speed.getValueNumeric() : this.mc.player.posY), this.mc.player.posZ + (forward * (double)this.speed.getValueNumeric() * Math.sin(Math.toRadians(yaw + 90.0f)) - strafe * (double)this.speed.getValueNumeric() * Math.cos(Math.toRadians(yaw + 90.0f))));
        }
    }

    @EventTarget
    public void onPacket(EventPacketSend event) {
        if (event.getPacket() instanceof CPacketPlayer) {
            event.setCancelled(true);
        }
    }
}

