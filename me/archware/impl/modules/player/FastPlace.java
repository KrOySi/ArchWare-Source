/*
 * Decompiled with CFR 0.150.
 */
package me.archware.impl.modules.player;

import me.archware.base.event.EventTarget;
import me.archware.base.module.Category;
import me.archware.base.module.Module;
import me.archware.impl.events.EventUpdate;
import net.minecraft.client.Minecraft;

public class FastPlace
extends Module {
    public FastPlace() {
        super("FastPlace", Category.PLAYER);
    }

    @EventTarget
    public void onUpdate(EventUpdate event) {
        if (this.mc.gameSettings.keyBindUseItem.isKeyDown()) {
            Minecraft.getMinecraft().rightClickDelayTimer = 1;
        }
    }
}

