/*
 * Decompiled with CFR 0.150.
 */
package me.archware.impl.modules.render;

import me.archware.base.module.Category;
import me.archware.base.module.Module;
import me.archware.base.setting.BooleanValue;
import me.archware.base.setting.NumericValue;
import me.archware.base.setting.StringValue;

public class ClickGUI
extends Module {
    private StringValue design = new StringValue("Design", "GUIDesign", this, "New", new String[]{"JellyLike", "New"});
    private BooleanValue sound = new BooleanValue("Sound", "Sound", this, false);
    private NumericValue red = new NumericValue("Red", "GuiRed", (Module)this, 255.0f, 0.0f, 255.0f);
    private NumericValue green = new NumericValue("Green", "GuiGreen", (Module)this, 255.0f, 0.0f, 255.0f);
    private NumericValue blue = new NumericValue("Blue", "GuiBlue", (Module)this, 255.0f, 0.0f, 255.0f);
    private final StringValue mode = new StringValue("Mode", "ClickGUIMode", this, "ArchWare", new String[]{"ArchWare", "Old"});

    public ClickGUI() {
        super("ClickGUI", Category.RENDER, 54);
    }

    @Override
    public void onEnable() {
        this.mc.displayGuiScreen(new me.archware.base.clickgui.ClickGUI());
        this.toggle();
        super.onEnable();
    }
}

