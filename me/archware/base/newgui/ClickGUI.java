/*
 * Decompiled with CFR 0.150.
 */
package me.archware.base.newgui;

import java.util.ArrayList;
import java.util.List;
import me.archware.base.module.Category;
import me.archware.base.newgui.element.AbstractElement;
import me.archware.base.newgui.element.elements.ElementTab;
import net.minecraft.client.gui.GuiScreen;

public class ClickGUI
extends GuiScreen {
    private final List<AbstractElement> elements = new ArrayList<AbstractElement>();

    public ClickGUI() {
        this.elements.clear();
        double x = 50.0;
        for (Category category : Category.values()) {
            this.elements.add(new ElementTab(category, x));
            x += 100.0;
        }
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        this.elements.forEach(element -> element.drawScreen(mouseX, mouseY, partialTicks));
        super.drawScreen(mouseX, mouseY, partialTicks);
    }
}

