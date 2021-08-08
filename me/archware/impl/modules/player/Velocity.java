/*
 * Decompiled with CFR 0.150.
 */
package me.archware.impl.modules.player;

import me.archware.base.event.EventTarget;
import me.archware.base.module.Category;
import me.archware.base.module.Module;
import me.archware.base.setting.StringValue;
import me.archware.impl.events.EventPacketReceive;
import me.archware.impl.events.EventUpdate;
import net.minecraft.network.play.server.SPacketEntityVelocity;

public class Velocity
extends Module {
    private StringValue mode = new StringValue("Mode", "VelocityMode", this, "Cancel", new String[]{"Vanilla", "Matrix"});

    public Velocity() {
        super("Velocity", Category.PLAYER);
    }

    @EventTarget
    public void onUpdate(EventUpdate event) {
        this.setSuffix(this.mode.getValueString());
        if (this.mode.getValueString().equalsIgnoreCase("Matrix") && this.mc.player.hurtTime > 8) {
            this.mc.player.onGround = true;
        }
    }

    @EventTarget
    public void onReceivePacket(EventPacketReceive event) {
        if (this.mode.getValueString().equalsIgnoreCase("Vanilla") && event.getPacket() instanceof SPacketEntityVelocity) {
            event.setCancelled(true);
        }
    }
}

