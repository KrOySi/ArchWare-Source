/*
 * Decompiled with CFR 0.150.
 */
package me.archware.impl.modules.render.tabgui;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Comparator;
import me.archware.ArchWare;
import me.archware.base.clickgui.util.FontUtil;
import me.archware.base.module.Category;
import me.archware.base.module.Module;
import me.archware.impl.utils.RenderUtil;
import me.archware.impl.utils.TimeUtil;

public class Tab {
    private Category parent;
    private float posY;
    private boolean isSelected;
    private boolean isExtended;
    private Module selectedMod;
    private TimeUtil timer = new TimeUtil();

    public Tab(float posY, Category parent) {
        this.posY = posY;
        this.parent = parent;
    }

    public void draw() {
        if (this.selectedMod == null) {
            this.setSelectedMod(ArchWare.moduleManager.modules.stream().filter(module -> module.getCategory() == this.parent).findFirst().get());
        }
        ArrayList modules = new ArrayList();
        ArchWare.moduleManager.getModulesByCategory(this.parent).forEach(module -> modules.add(module.getName()));
        RenderUtil.drawRect(2.0, (double)this.posY, 50.0, 12.0, this.isSelected ? new Color((int)ArchWare.settingManager.getSettingById("HUDRed").getValueNumeric(), (int)ArchWare.settingManager.getSettingById("HUDGreen").getValueNumeric(), (int)ArchWare.settingManager.getSettingById("HUDBlue").getValueNumeric(), 150).hashCode() : new Color(0, 0, 0, 0).hashCode());
        FontUtil.drawStringWithShadow(this.parent.name().charAt(0) + this.parent.name().substring(1).toLowerCase(), 4.0, this.posY, -1);
        if (this.isExtended) {
            int[] y = new int[]{(int)this.posY};
            ArchWare.moduleManager.getModulesByCategory(this.parent).forEach(module -> {
                RenderUtil.drawRect(55.0, (double)y[0], (double)(FontUtil.getStringWidth(modules.stream().max(Comparator.comparingInt(String::length)).get()) + 5), 12.0, new Color(0, 0, 0, 110).hashCode());
                RenderUtil.drawRect(55.0, (double)y[0], (double)(FontUtil.getStringWidth(modules.stream().max(Comparator.comparingInt(String::length)).get()) + 5), 12.0, this.selectedMod.equals(module) ? new Color((int)ArchWare.settingManager.getSettingById("HUDRed").getValueNumeric(), (int)ArchWare.settingManager.getSettingById("HUDGreen").getValueNumeric(), (int)ArchWare.settingManager.getSettingById("HUDBlue").getValueNumeric(), 150).hashCode() : new Color(0, 0, 0, 0).hashCode());
                FontUtil.drawStringWithShadow(module.getName(), 56.0, y[0], module.isToggled() ? Color.LIGHT_GRAY.hashCode() : -1);
                y[0] = y[0] + 12;
            });
        }
    }

    public float getPosY() {
        return this.posY;
    }

    public void setPosY(float posY) {
        this.posY = posY;
    }

    public boolean isSelected() {
        return this.isSelected;
    }

    public void setSelected(boolean selected) {
        this.isSelected = selected;
    }

    public boolean isExtended() {
        return this.isExtended;
    }

    public void setExtended(boolean extended) {
        this.isExtended = extended;
    }

    public TimeUtil getTimer() {
        return this.timer;
    }

    public void setSelectedMod(Module module) {
        this.selectedMod = module;
    }

    public Module getSelectedMod() {
        return this.selectedMod;
    }

    public Category getParent() {
        return this.parent;
    }
}

