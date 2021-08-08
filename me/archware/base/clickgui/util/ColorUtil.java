/*
 * Decompiled with CFR 0.150.
 */
package me.archware.base.clickgui.util;

import java.awt.Color;
import me.archware.ArchWare;

public class ColorUtil {
    public static Color getClickGUIColor() {
        return new Color((int)ArchWare.settingManager.getSettingById("GuiRed").getValueNumeric(), (int)ArchWare.settingManager.getSettingById("GuiGreen").getValueNumeric(), (int)ArchWare.settingManager.getSettingById("GuiBlue").getValueNumeric());
    }
}

