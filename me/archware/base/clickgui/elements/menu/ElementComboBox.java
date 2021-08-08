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

public class ElementComboBox
extends Element {
    public ElementComboBox(ModuleButton iparent, Setting iset) {
        this.parent = iparent;
        this.set = iset;
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        Color temp = ColorUtil.getClickGUIColor();
        int color = new Color(temp.getRed(), temp.getGreen(), temp.getBlue(), 150).getRGB();
        Gui.drawRect(this.x, this.y, this.x + this.width, this.y + this.height, -15066598);
        FontUtil.drawStringWithShadow(this.setstrg, (int)this.x + 1, this.y + 15.0 - 10.0, -1);
        int clr1 = color;
        int clr2 = temp.getRGB();
        Gui.drawRect(this.x, this.y + 14.0, this.x + this.width, this.y + 15.0, 0x77000000);
        if (this.comboextended) {
            Gui.drawRect(this.x, this.y + 15.0, this.x + this.width, this.y + this.height, -1441656302);
            double ay = this.y + 15.0;
            for (String sld : this.set.getValues()) {
                String elementtitle = sld.substring(0, 1).toUpperCase() + sld.substring(1, sld.length());
                FontUtil.drawString(elementtitle, (int)this.x + 2, ay + 1.0, -1);
                if (sld.equalsIgnoreCase(this.set.getValueString())) {
                    Gui.drawRect(this.x, ay, this.x + 1.5, ay + (double)FontUtil.getFontHeight() + 2.0, clr1);
                }
                if ((double)mouseX >= this.x && (double)mouseX <= this.x + this.width && (double)mouseY >= ay && (double)mouseY < ay + (double)FontUtil.getFontHeight() + 2.0) {
                    Gui.drawRect(this.x + this.width - 1.2, ay, this.x + this.width, ay + (double)FontUtil.getFontHeight() + 2.0, clr2);
                }
                ay += (double)(FontUtil.getFontHeight() + 2);
            }
        }
    }

    @Override
    public boolean mouseClicked(int mouseX, int mouseY, int mouseButton) {
        if (mouseButton == 0) {
            if (this.isButtonHovered(mouseX, mouseY)) {
                this.comboextended = !this.comboextended;
                return true;
            }
            if (!this.comboextended) {
                return false;
            }
            double ay = this.y + 15.0;
            for (String slcd : this.set.getValues()) {
                if ((double)mouseX >= this.x && (double)mouseX <= this.x + this.width && (double)mouseY >= ay && (double)mouseY <= ay + (double)FontUtil.getFontHeight() + 2.0) {
                    if (this.clickgui != null && this.clickgui.setmgr != null) {
                        this.set.setValueString(slcd);
                    }
                    return true;
                }
                ay += (double)(FontUtil.getFontHeight() + 2);
            }
        }
        return super.mouseClicked(mouseX, mouseY, mouseButton);
    }

    public boolean isButtonHovered(int mouseX, int mouseY) {
        return (double)mouseX >= this.x && (double)mouseX <= this.x + this.width && (double)mouseY >= this.y && (double)mouseY <= this.y + 15.0;
    }
}

