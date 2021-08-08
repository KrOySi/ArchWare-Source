/*
 * Decompiled with CFR 0.150.
 */
package me.archware.impl.modules.misc;

import me.archware.base.event.EventTarget;
import me.archware.base.module.Category;
import me.archware.base.module.Module;
import me.archware.impl.events.EventPreUpdate;

public class GroundSpoof
extends Module {
    public GroundSpoof() {
        super("GroundSpoof", Category.MISC);
    }

    @EventTarget
    public void onUpdate(EventPreUpdate event) {
        if (event.isOnGround()) {
            event.setOnGround(false);
        }
    }

    @Override
    public void onDisable() {
        super.onDisable();
    }
}

