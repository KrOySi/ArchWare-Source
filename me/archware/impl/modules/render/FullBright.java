/*
 * Decompiled with CFR 0.150.
 */
package me.archware.impl.modules.render;

import me.archware.base.module.Category;
import me.archware.base.module.Module;

public class FullBright
extends Module {
    private float oldGamma;

    public FullBright() {
        super("FullBright", Category.RENDER);
    }

    @Override
    public void onDisable() {
        this.mc.gameSettings.gammaSetting = this.oldGamma;
        super.onDisable();
    }

    @Override
    public void onEnable() {
        this.oldGamma = this.mc.gameSettings.gammaSetting;
        this.mc.gameSettings.gammaSetting = 50.0f;
        super.onEnable();
    }
}

