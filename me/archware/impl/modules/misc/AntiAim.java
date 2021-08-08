/*
 * Decompiled with CFR 0.150.
 */
package me.archware.impl.modules.misc;

import java.util.concurrent.ThreadLocalRandom;
import me.archware.base.event.EventTarget;
import me.archware.base.module.Category;
import me.archware.base.module.Module;
import me.archware.base.setting.NumericValue;
import me.archware.base.setting.StringValue;
import me.archware.impl.events.EventPreUpdate;
import me.archware.impl.modules.combat.KillAura;
import me.archware.impl.utils.TimeUtil;
import net.minecraft.network.play.client.CPacketPlayer;

public class AntiAim
extends Module {
    private float yaw;
    private float swing;
    private float swingAmount;
    StringValue mode = new StringValue("Mode", "AntiAimMode", this, "Spin", new String[]{"Spin", "CSGO", "LolXd"});
    NumericValue speed = new NumericValue("Speed", "AntiAimSpeed", (Module)this, 100.0f, 40.0f, 150.0f);
    private TimeUtil timer = new TimeUtil();

    public AntiAim() {
        super("AntiAim", Category.MISC);
    }

    @EventTarget
    public void onUpdate(EventPreUpdate event) {
        this.setSuffix(this.mode.getValueString());
        if (this.yaw >= 360.0f) {
            this.yaw = 0.0f;
        }
        this.setSuffix(this.mode.getValueString());
        switch (this.mode.getValueString()) {
            case "CSGO": {
                this.mc.player.rotationYawHead = this.yaw = this.mc.player.rotationYaw + (float)(Math.random() * 34.5 - 15.0);
                this.mc.player.renderYawOffset = this.yaw;
                this.mc.player.rotationPitchHead = 90.0f;
                if (KillAura.target != null) break;
                this.mc.getConnection().sendPacket(new CPacketPlayer.Rotation(this.yaw, 90.0f, this.mc.player.onGround));
                break;
            }
            case "Spin": {
                this.yaw += this.speed.getValueNumeric();
                this.mc.player.rotationYawHead = this.yaw;
                this.mc.player.renderYawOffset = this.yaw;
                if (KillAura.target != null) break;
                this.mc.getConnection().sendPacket(new CPacketPlayer.Rotation(this.yaw, 90.0f, this.mc.player.onGround));
                break;
            }
            case "LolXd": {
                if (this.timer.hasReached(140.0)) {
                    this.yaw += 60.0f + (float)Math.random() * 3.0f;
                    this.swing = (float)ThreadLocalRandom.current().nextDouble(1.0, 3.0);
                    this.swingAmount = (float)ThreadLocalRandom.current().nextDouble(1.0, 3.0);
                    this.timer.reset();
                }
                this.mc.player.rotationYawHead = this.yaw;
                this.mc.player.renderYawOffset = this.yaw;
                this.mc.player.rotationPitchHead = 90.0f;
                this.mc.player.limbSwingAmount = this.swingAmount;
                this.mc.player.limbSwing = this.swingAmount;
                if (KillAura.target != null) break;
                event.setYaw(this.yaw);
                event.setPitch(90.0f);
            }
        }
    }
}

