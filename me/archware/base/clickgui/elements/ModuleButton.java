/*
 * Decompiled with CFR 0.150.
 */
package me.archware.base.clickgui.elements;

import java.awt.Color;
import java.io.IOException;
import java.util.ArrayList;
import me.archware.ArchWare;
import me.archware.base.clickgui.Panel;
import me.archware.base.clickgui.elements.Element;
import me.archware.base.clickgui.elements.menu.ElementCheckBox;
import me.archware.base.clickgui.elements.menu.ElementComboBox;
import me.archware.base.clickgui.elements.menu.ElementSlider;
import me.archware.base.clickgui.util.ColorUtil;
import me.archware.base.clickgui.util.FontUtil;
import me.archware.base.module.Module;
import me.archware.base.setting.Setting;
import me.archware.base.setting.SettingType;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;

public class ModuleButton {
    public Module mod;
    public ArrayList<Element> menuelements;
    public Panel parent;
    public double x;
    public double y;
    public double width;
    public double height;
    public boolean extended = false;
    public boolean listening = false;

    public ModuleButton(Module imod, Panel pl) {
        this.mod = imod;
        this.height = Minecraft.getMinecraft().fontRendererObj.FONT_HEIGHT + 2;
        this.parent = pl;
        this.menuelements = new ArrayList();
        if (ArchWare.settingManager.getSettingsByMod(imod) != null) {
            for (Setting s : ArchWare.settingManager.getSettingsByMod(imod)) {
                if (s.getSettingType() == SettingType.Boolean) {
                    this.menuelements.add(new ElementCheckBox(this, s));
                    continue;
                }
                if (s.getSettingType() == SettingType.Numeric) {
                    this.menuelements.add(new ElementSlider(this, s));
                    continue;
                }
                if (s.getSettingType() != SettingType.String) continue;
                this.menuelements.add(new ElementComboBox(this, s));
            }
        }
    }

    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        Color temp = ColorUtil.getClickGUIColor();
        int color = new Color(temp.getRed(), temp.getGreen(), temp.getBlue(), 150).getRGB();
        int textcolor = -5263441;
        if (this.mod.isToggled()) {
            Gui.drawRect(this.x - 2.0, this.y, this.x + this.width + 2.0, this.y + this.height + 1.0, color);
            textcolor = -1052689;
        }
        if (this.isHovered(mouseX, mouseY)) {
            Gui.drawRect(this.x - 2.0, this.y, this.x + this.width + 2.0, this.y + this.height + 1.0, 0x55111111);
        }
        FontUtil.drawStringWithShadow(this.mod.getName(), this.x + 1.0, this.y + 1.0 + this.height / 2.0 - 3.0, textcolor);
    }

    public boolean mouseClicked(int mouseX, int mouseY, int mouseButton) {
        if (!this.isHovered(mouseX, mouseY)) {
            return false;
        }
        if (mouseButton == 0) {
            this.mod.toggle();
        } else if (mouseButton == 1 && this.menuelements != null && this.menuelements.size() > 0) {
            boolean b;
            this.extended = b = !this.extended;
        }
        return true;
    }

    public boolean keyTyped(char typedChar, int keyCode) throws IOException {
        return false;
    }

    public boolean isHovered(int mouseX, int mouseY) {
        return (double)mouseX >= this.x && (double)mouseX <= this.x + this.width && (double)mouseY >= this.y && (double)mouseY <= this.y + this.height;
    }
}

