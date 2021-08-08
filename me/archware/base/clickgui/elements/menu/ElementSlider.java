/*
 * Decompiled with CFR 0.150.
 */
package me.archware.base.clickgui.elements.menu;

import java.awt.Color;
import me.archware.base.clickgui.elements.Element;
import me.archware.base.clickgui.elements.ModuleButton;
import me.archware.base.clickgui.util.ColorUtil;
import me.archware.base.clickgui.util.FontUtil;
import me.archware.base.setting.Setting;
import net.minecraft.client.gui.Gui;
import net.minecraft.util.math.MathHelper;

public class ElementSlider
extends Element {
    public boolean dragging;

    public ElementSlider(ModuleButton iparent, Setting iset) {
        this.parent = iparent;
        this.set = iset;
        this.dragging = false;
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        String displayval = "" + (double)Math.round((double)this.set.getValueNumeric() * 100.0) / 100.0;
        boolean hoveredORdragged = this.isSliderHovered(mouseX, mouseY) || this.dragging;
        Color temp = ColorUtil.getClickGUIColor();
        int color = new Color(temp.getRed(), temp.getGreen(), temp.getBlue(), hoveredORdragged ? 250 : 200).getRGB();
        int color2 = new Color(temp.getRed(), temp.getGreen(), temp.getBlue(), hoveredORdragged ? 255 : 230).getRGB();
        double percentBar = (this.set.getValueNumeric() - this.set.getMinValue()) / (this.set.getMaxValue() - this.set.getMinValue());
        Gui.drawRect(this.x, this.y, this.x + this.width, this.y + this.height, -15066598);
        FontUtil.drawString(this.setstrg, (int)this.x + 1, this.y + 3.0, -1);
        FontUtil.drawString(displayval, (int)(this.x + this.width - (double)FontUtil.getStringWidth(displayval)) - 2, this.y + 3.0, -1);
        Gui.drawRect(this.x, this.y + 12.0, this.x + this.width, this.y + 13.5, -15724528);
        Gui.drawRect(this.x, this.y + 12.0, this.x + percentBar * this.width, this.y + 13.5, color);
        if (percentBar > 0.0 && percentBar < 1.0) {
            Gui.drawRect(this.x + percentBar * this.width - 1.0, this.y + 12.0, this.x + Math.min(percentBar * this.width, this.width), this.y + 13.5, color2);
        }
        if (this.dragging) {
            double diff = this.set.getMaxValue() - this.set.getMinValue();
            double val = (double)this.set.getMinValue() + MathHelper.clamp(((double)mouseX - this.x) / this.width, 0.0, 1.0) * diff;
            this.set.setValueNumeric((float)val);
        }
    }

    @Override
    public boolean mouseClicked(int mouseX, int mouseY, int mouseButton) {
        if (mouseButton == 0 && this.isSliderHovered(mouseX, mouseY)) {
            this.dragging = true;
            return true;
        }
        return super.mouseClicked(mouseX, mouseY, mouseButton);
    }

    @Override
    public void mouseReleased(int mouseX, int mouseY, int state) {
        this.dragging = false;
    }

    public boolean isSliderHovered(int mouseX, int mouseY) {
        return (double)mouseX >= this.x && (double)mouseX <= this.x + this.width && (double)mouseY >= this.y + 11.0 && (double)mouseY <= this.y + 14.0;
    }
}

