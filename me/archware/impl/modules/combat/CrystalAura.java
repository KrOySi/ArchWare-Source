/*
 * Decompiled with CFR 0.150.
 */
package me.archware.impl.modules.combat;

import me.archware.base.event.EventTarget;
import me.archware.base.module.Category;
import me.archware.base.module.Module;
import me.archware.base.setting.NumericValue;
import me.archware.impl.events.EventPreUpdate;
import me.archware.impl.modules.combat.KillAura;
import me.archware.impl.utils.RotationUtils;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityEnderCrystal;
import net.minecraft.network.play.client.CPacketAnimation;
import net.minecraft.network.play.client.CPacketUseEntity;
import net.minecraft.util.EnumHand;

public class CrystalAura
extends Module {
    NumericValue range = new NumericValue("Range", "CrystalAuraRange", (Module)this, 3.0f, 0.0f, 6.1f);

    public CrystalAura() {
        super("CrystalAura", Category.COMBAT);
    }

    @EventTarget
    public void onUpdate(EventPreUpdate event) {
        for (Entity e : this.mc.world.loadedEntityList) {
            if (!(e instanceof EntityEnderCrystal) || KillAura.target != null || !(this.mc.player.getDistanceToEntity(e) <= this.range.getValueNumeric())) continue;
            event.setPitch(RotationUtils.getRotation(e)[1]);
            event.setYaw(RotationUtils.getRotation(e)[0]);
            this.mc.player.rotationYawHead = event.getYaw();
            this.mc.player.renderYawOffset = event.getYaw();
            this.mc.player.rotationPitchHead = event.getPitch();
            this.mc.getConnection().sendPacket(new CPacketUseEntity(e));
            this.mc.getConnection().sendPacket(new CPacketAnimation(EnumHand.MAIN_HAND));
        }
    }
}

