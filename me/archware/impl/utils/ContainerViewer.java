/*
 * Decompiled with CFR 0.150.
 */
package me.archware.impl.utils;

import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;

public class ContainerViewer
extends GuiScreen {
    private EntityPlayer target;

    public ContainerViewer(EntityPlayer target) {
        this.target = target;
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        this.mc.fontRendererObj.drawStringWithShadow("a", 1.0f, 1.0f, -1);
    }
}

