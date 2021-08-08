/*
 * Decompiled with CFR 0.150.
 */
package me.archware.impl.modules.render;

import me.archware.base.module.Category;
import me.archware.base.module.Module;
import me.archware.base.setting.BooleanValue;
import me.archware.base.setting.NumericValue;

public class Chams
extends Module {
    private NumericValue red = new NumericValue("Red", "ChamsRed", (Module)this, 255.0f, 0.0f, 255.0f);
    private NumericValue green = new NumericValue("Green", "ChamsGreen", (Module)this, 255.0f, 0.0f, 255.0f);
    private NumericValue blue = new NumericValue("Blue", "ChamsBlue", (Module)this, 255.0f, 0.0f, 255.0f);
    private NumericValue opacity = new NumericValue("Opacity", "ChamsOpacity", (Module)this, 50.0f, 0.0f, 225.0f);
    private BooleanValue rainbow = new BooleanValue("Rainbow", "ChamsRainbow", this, false);

    public Chams() {
        super("Chams", Category.RENDER);
    }
}

