/*
 * Decompiled with CFR 0.150.
 */
package me.archware.impl.modules.movement;

import me.archware.base.event.EventTarget;
import me.archware.base.module.Category;
import me.archware.base.module.Module;
import me.archware.base.setting.BooleanValue;
import me.archware.base.setting.NumericValue;
import me.archware.base.setting.StringValue;
import me.archware.impl.events.EventPacketReceive;
import me.archware.impl.events.EventPreUpdate;
import me.archware.impl.modules.combat.KillAura;
import me.archware.impl.utils.MoveUtils;
import net.minecraft.network.play.client.CPacketPlayer;
import net.minecraft.network.play.server.SPacketPlayerPosLook;
import net.minecraft.util.math.MathHelper;

public class Flight
extends Module {
    private final NumericValue speed = new NumericValue("Speed", "FlySpeed", (Module)this, 1.7f, 1.5f, 5.3f);
    private final StringValue mode = new StringValue("Mode", "FlightMode", this, "Motion", new String[]{"Motion", "Mineland"});
    private final BooleanValue autojump = new BooleanValue("AutoJump", "FlightAutoJump", this, true);

    public Flight() {
        super("Flight", Category.MOVEMENT);
    }

    @EventTarget
    public void onPacket(EventPacketReceive e) {
        if (e.getPacket() instanceof SPacketPlayerPosLook) {
            this.toggle();
        }
    }

    @EventTarget
    public void onPreUpdate(EventPreUpdate event) {
        this.setSuffix(this.mode.getValueString());
        if (this.autojump.getValueBoolean() && this.mc.player.onGround) {
            this.mc.player.jump();
        }
        switch (this.mode.getValueString()) {
            case "Motion": {
                if (!(this.mc.player.fallDistance > 0.0f)) break;
                if (KillAura.target == null) {
                    MoveUtils.setSpeed(this.speed.getValueNumeric());
                }
                this.mc.player.motionY = -0.01;
                break;
            }
            case "Mineland": {
                if (!this.mc.gameSettings.keyBindForward.isKeyDown()) break;
                float f = (float)Math.toRadians(this.mc.player.rotationYaw);
                double speed = 0.15;
                double x = -((double)MathHelper.sin(f) * 0.15);
                double z = (double)MathHelper.cos(f) * 0.15;
                this.mc.getConnection().sendPacket(new CPacketPlayer.Position(this.mc.player.posX + x, this.mc.player.posY, this.mc.player.posZ + z, false));
                this.mc.getConnection().sendPacket(new CPacketPlayer.Position(this.mc.player.posX + x, this.mc.player.posY - 490.0, this.mc.player.posZ + z, true));
                this.mc.player.motionY = 0.0;
            }
        }
    }
}

