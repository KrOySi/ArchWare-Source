/*
 * Decompiled with CFR 0.150.
 */
package me.archware.impl.modules.movement;

import me.archware.ArchWare;
import me.archware.base.event.EventTarget;
import me.archware.base.module.Category;
import me.archware.base.module.Module;
import me.archware.base.setting.BooleanValue;
import me.archware.base.setting.NumericValue;
import me.archware.impl.events.EventUpdate;
import me.archware.impl.modules.combat.KillAura;
import me.archware.impl.utils.MoveUtils;
import me.archware.impl.utils.RotationUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;

public class TargetStrafe
extends Module {
    private NumericValue range = new NumericValue("Range", "TargetStrafeRange", (Module)this, 3.6f, 0.1f, 7.0f);
    private NumericValue motion = new NumericValue("Motion", "TargetStrafeMotion", (Module)this, 0.2f, 0.01f, 2.0f);
    BooleanValue damageboost = new BooleanValue("Damage Boost", "TargetStrafeDamageBoost", this, false);
    private Entity target;
    double forward = 0.0;
    private NumericValue damagemotion = new NumericValue("Damage Motion", "TargetStrafeDamageBoostMotion", (Module)this, 0.2f, 0.1f, 2.0f);
    BooleanValue TGFly = new BooleanValue("Strafe boost", "TGFlyBoost", this, false);
    private NumericValue TGFlyMotion = new NumericValue("Strafe motion", "tgmotion", (Module)this, 0.1f, 0.05f, 3.0f);

    public TargetStrafe() {
        super("TargetStrafe", Category.MOVEMENT);
    }

    @EventTarget
    public void onUpdate(EventUpdate event) {
        double speed;
        if (KillAura.target != null) {
            this.target = KillAura.target;
        }
        double d = speed = ArchWare.moduleManager.getModuleByName("Flight").isToggled() && this.TGFly.getValueBoolean() ? (double)this.TGFlyMotion.getValueNumeric() : (double)this.motion.getValueNumeric();
        if (this.target != null) {
            if (this.mc.player.hurtTime >= 6 && this.damageboost.getValueBoolean()) {
                speed += (double)this.damagemotion.getValueNumeric();
            }
            if (this.getDistance(this.target) >= 0.0 && this.getDistance(this.target) < (double)this.range.getValueNumeric() + 0.1) {
                MoveUtils.setSpeed(0.0);
                this.forward = 0.0;
            } else if (this.getDistance(this.target) >= 0.0) {
                this.forward = 3.0;
            }
            double strafe = Minecraft.getMinecraft().player.movementInput.moveStrafe;
            float yaw = RotationUtils.getRotation(this.target)[0];
            if (this.forward != 0.0 || strafe != 0.0) {
                if (this.forward != 0.0) {
                    if (strafe > 0.0) {
                        yaw += (float)(this.forward > 0.0 ? -45 : 45);
                    } else if (strafe < 0.0) {
                        yaw += (float)(this.forward > 0.0 ? 45 : -45);
                    }
                    strafe = 0.0;
                    if (this.forward > 0.0) {
                        this.forward = 1.0;
                    } else if (this.forward < 0.0) {
                        this.forward = -1.0;
                    }
                }
                Minecraft.getMinecraft().player.motionX = this.forward * speed * Math.cos(Math.toRadians(yaw + 90.0f)) + strafe * speed * Math.sin(Math.toRadians(yaw + 90.0f));
                Minecraft.getMinecraft().player.motionZ = this.forward * speed * Math.sin(Math.toRadians(yaw + 90.0f)) - strafe * speed * Math.cos(Math.toRadians(yaw + 90.0f));
            }
        }
        if (KillAura.target == null) {
            this.target = null;
        }
    }

    private double getDistance(Entity target) {
        double deltaX = this.mc.player.posX - target.posX;
        double deltaZ = this.mc.player.posZ - target.posZ;
        return Math.hypot(deltaX, deltaZ);
    }
}

