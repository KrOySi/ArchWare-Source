/*
 * Decompiled with CFR 0.150.
 */
package me.archware.base.clickgui.elements;

import me.archware.base.clickgui.ClickGUI;
import me.archware.base.clickgui.elements.ModuleButton;
import me.archware.base.clickgui.util.FontUtil;
import me.archware.base.setting.Setting;
import me.archware.base.setting.SettingType;

public class Element {
    public ClickGUI clickgui;
    public ModuleButton parent;
    public Setting set;
    public double offset;
    public double x;
    public double y;
    public double width;
    public double height;
    public String setstrg;
    public boolean comboextended;

    public void setup() {
        this.clickgui = this.parent.parent.clickgui;
    }

    public void update() {
        this.x = this.parent.x + this.parent.width + 2.0;
        this.y = this.parent.y + this.offset;
        this.width = this.parent.width + 10.0;
        this.height = 15.0;
        String sname = this.set.getName();
        if (this.set.getSettingType() == SettingType.Boolean) {
            this.setstrg = sname.substring(0, 1).toUpperCase() + sname.substring(1, sname.length());
            double textx = this.x + this.width - (double)FontUtil.getStringWidth(this.setstrg);
            if (textx < this.x + 13.0) {
                this.width += this.x + 13.0 - textx + 1.0;
            }
        } else if (this.set.getSettingType() == SettingType.String) {
            this.height = this.comboextended ? (double)(this.set.getValues().length * (FontUtil.getFontHeight() + 2) + 15) : 15.0;
            this.setstrg = sname.substring(0, 1).toUpperCase() + sname.substring(1, sname.length());
            int longest = FontUtil.getStringWidth(this.setstrg);
            for (String s : this.set.getValues()) {
                int temp = FontUtil.getStringWidth(s);
                if (temp <= longest) continue;
                longest = temp;
            }
            double textx = this.x + this.width - (double)longest;
            if (textx < this.x) {
                this.width += this.x - textx + 1.0;
            }
        } else if (this.set.getSettingType() == SettingType.Numeric) {
            this.setstrg = sname.substring(0, 1).toUpperCase() + sname.substring(1, sname.length());
            String displayval = "" + (double)Math.round((double)this.set.getValueNumeric() * 100.0) / 100.0;
            String displaymax = "" + (double)Math.round((double)this.set.getMaxValue() * 100.0) / 100.0;
            double textx = this.x + this.width - (double)FontUtil.getStringWidth(this.setstrg) - (double)FontUtil.getStringWidth(displaymax) - 4.0;
            if (textx < this.x) {
                this.width += this.x - textx + 1.0;
            }
        }
    }

    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
    }

    public boolean mouseClicked(int mouseX, int mouseY, int mouseButton) {
        return this.isHovered(mouseX, mouseY);
    }

    public void mouseReleased(int mouseX, int mouseY, int state) {
    }

    public boolean isHovered(int mouseX, int mouseY) {
        return (double)mouseX >= this.x && (double)mouseX <= this.x + this.width && (double)mouseY >= this.y && (double)mouseY <= this.y + this.height;
    }
}

