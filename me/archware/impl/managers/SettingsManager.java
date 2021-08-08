/*
 * Decompiled with CFR 0.150.
 */
package me.archware.impl.managers;

import java.util.ArrayList;
import java.util.stream.Collectors;
import me.archware.base.module.Module;
import me.archware.base.setting.Setting;

public class SettingsManager {
    private ArrayList<Setting> settings = new ArrayList();

    public ArrayList<Setting> getSettings() {
        return this.settings;
    }

    public ArrayList<Setting> getSettingsByMod(Module m) {
        return this.settings.stream().filter(s -> s.getParent().equals(m)).collect(Collectors.toCollection(ArrayList::new));
    }

    public Setting getSettingById(String id) {
        for (Setting s : this.settings) {
            if (!s.getId().equalsIgnoreCase(id)) continue;
            return s;
        }
        return null;
    }

    public Setting getSettingByName(String name) {
        for (Setting s : this.settings) {
            if (!s.getName().equalsIgnoreCase(name)) continue;
            return s;
        }
        return null;
    }

    public Setting getSetting(Module mod, String name) {
        for (Setting s : this.settings) {
            if (!s.getName().replace(" ", "").equalsIgnoreCase(name) || s.getParent() != mod) continue;
            return s;
        }
        return null;
    }

    public void addSetting(Setting s) {
        this.settings.add(s);
    }
}

