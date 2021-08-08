/*
 * Decompiled with CFR 0.150.
 */
package me.archware.base.newgui.element.elements;

import java.awt.Color;
import me.archware.ArchWare;
import me.archware.base.clickgui.util.FontUtil;
import me.archware.base.module.Category;
import me.archware.base.newgui.element.AbstractElement;
import me.archware.impl.utils.RenderUtil;

public class ElementTab
extends AbstractElement {
    private Category parent;
    private double x;
    private double y = 30.0;

    public ElementTab(Category parent, double x) {
        this.parent = parent;
        this.x = x;
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        RenderUtil.drawRect(this.x, this.y, 75.0, 18.0, new Color((int)ArchWare.settingManager.getSettingById("GuiRed").getValueNumeric(), (int)ArchWare.settingManager.getSettingById("GuiGreen").getValueNumeric(), (int)ArchWare.settingManager.getSettingById("GuiBlue").getValueNumeric()).darker().hashCode());
        FontUtil.drawCenteredStringWithShadow(this.parent.name().charAt(0) + this.parent.name().substring(1).toLowerCase(), this.x + 35.5, this.y + 6.0, -1);
        RenderUtil.drawRect(this.x, this.y + 18.0, 75.0, (double)(8 * ArchWare.moduleManager.getModulesByCategory(this.parent).size()), new Color(0, 0, 0, 95).hashCode());
    }
}

