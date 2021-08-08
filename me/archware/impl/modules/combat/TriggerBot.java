/*
 * Decompiled with CFR 0.150.
 */
package me.archware.impl.modules.combat;

import me.archware.base.event.EventTarget;
import me.archware.base.module.Category;
import me.archware.base.module.Module;
import me.archware.impl.events.EventUpdate;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.network.play.client.CPacketUseEntity;
import net.minecraft.util.EnumHand;

public class TriggerBot
extends Module {
    public TriggerBot() {
        super("TriggerBot", Category.COMBAT);
    }

    @EventTarget
    public void onUpdate(EventUpdate event) {
        if (this.mc.objectMouseOver.entityHit != null && this.mc.objectMouseOver.entityHit instanceof EntityLivingBase && this.mc.player.getDistanceToEntity(this.mc.objectMouseOver.entityHit) <= 3.8f && this.mc.player.getCooledAttackStrength(0.0f) == 1.0f) {
            this.mc.getConnection().sendPacket(new CPacketUseEntity(this.mc.objectMouseOver.entityHit));
            this.mc.player.swingArm(EnumHand.MAIN_HAND);
        }
    }
}

