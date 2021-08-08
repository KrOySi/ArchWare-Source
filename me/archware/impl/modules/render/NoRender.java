/*
 * Decompiled with CFR 0.150.
 */
package me.archware.impl.modules.render;

import me.archware.base.module.Category;
import me.archware.base.module.Module;
import me.archware.base.setting.BooleanValue;

public class NoRender
extends Module {
    private BooleanValue pumpkin = new BooleanValue("Pumpkin", "NoRenderPumpkin", this, true);
    private BooleanValue fire = new BooleanValue("Fire", "NoRenderFire", this, true);
    private BooleanValue portal = new BooleanValue("Portal", "NoRenderPortal", this, true);

    public NoRender() {
        super("NoRender", Category.RENDER);
    }
}

