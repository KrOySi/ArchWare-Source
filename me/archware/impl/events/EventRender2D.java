/*
 * Decompiled with CFR 0.150.
 */
package me.archware.impl.events;

import me.archware.base.event.Event;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.ScaledResolution;

public class EventRender2D
extends Event {
    private float partialTicks;

    public EventRender2D(float partialTicks) {
        this.partialTicks = partialTicks;
    }

    public float getPartialTicks() {
        return this.partialTicks;
    }

    public FontRenderer getFR() {
        return this.mc.fontRendererObj;
    }

    public int getStringWidth(String str) {
        return this.getFR().getStringWidth(str);
    }

    public ScaledResolution getSR() {
        return new ScaledResolution(this.mc);
    }
}

