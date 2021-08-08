/*
 * Decompiled with CFR 0.150.
 */
package me.archware.impl.modules.render;

import java.awt.Color;
import me.archware.base.event.EventTarget;
import me.archware.base.module.Category;
import me.archware.base.module.Module;
import me.archware.impl.events.EventRender2D;
import me.archware.impl.utils.RenderUtil;
import net.minecraft.client.gui.ScaledResolution;

public class CoolFeatureBtw
extends Module {
    public CoolFeatureBtw() {
        super("CoolFeatureBtw", Category.RENDER);
    }

    @EventTarget
    public void onRender(EventRender2D event) {
        ScaledResolution sr = new ScaledResolution(this.mc);
        RenderUtil.drawRect(0.0, (double)(sr.getScaledHeight() / 2) - 0.5, (double)sr.getScaledWidth(), 0.5, Color.BLACK.hashCode());
        RenderUtil.drawRect((double)(sr.getScaledWidth() / 2) - 0.5, 0.0, 0.5, (double)sr.getScaledHeight(), Color.BLACK.hashCode());
    }
}

