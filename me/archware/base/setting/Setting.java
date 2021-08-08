/*
 * Decompiled with CFR 0.150.
 */
package me.archware.base.setting;

import me.archware.ArchWare;
import me.archware.base.module.Module;
import me.archware.base.setting.SettingType;

public class Setting {
    private Module parent;
    private String name;
    private String id;
    private SettingType settingType;
    private boolean valueBoolean;
    private float valueNumeric;
    private float minValue;
    private float maxValue;
    private String valueString;
    private String[] values;

    public Setting(String name, String id, Module parent, boolean value) {
        this.parent = parent;
        this.name = name;
        this.id = id;
        this.settingType = SettingType.Boolean;
        this.valueBoolean = value;
        ArchWare.settingManager.addSetting(this);
    }

    public Setting(String name, String id, Module parent, float value, float minValue, float maxValue) {
        this.parent = parent;
        this.name = name;
        this.id = id;
        this.settingType = SettingType.Numeric;
        this.valueNumeric = value;
        this.minValue = minValue;
        this.maxValue = maxValue;
        ArchWare.settingManager.addSetting(this);
    }

    public Setting(String name, String id, Module parent, String value, String ... values) {
        this.parent = parent;
        this.name = name;
        this.id = id;
        this.settingType = SettingType.String;
        this.valueString = value;
        this.values = values;
        ArchWare.settingManager.addSetting(this);
    }

    public void setParent(Module parent) {
        this.parent = parent;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setSettingType(SettingType settingType) {
        this.settingType = settingType;
    }

    public boolean isValueBoolean() {
        return this.valueBoolean;
    }

    public void setMinValue(float minValue) {
        this.minValue = minValue;
    }

    public void setMaxValue(float maxValue) {
        this.maxValue = maxValue;
    }

    public void setValues(String[] values) {
        this.values = values;
    }

    public boolean isTypeBoolean() {
        return this.getSettingType() == SettingType.Boolean;
    }

    public boolean isTypeNumeric() {
        return this.getSettingType() == SettingType.Numeric;
    }

    public boolean isTypeString() {
        return this.getSettingType() == SettingType.String;
    }

    public void setValueBoolean(boolean valueBoolean) {
        this.valueBoolean = valueBoolean;
    }

    public boolean getValueBoolean() {
        return this.valueBoolean;
    }

    public float getValueNumeric() {
        return this.valueNumeric;
    }

    public void setValueNumeric(float valueNumeric) {
        this.valueNumeric = valueNumeric;
    }

    public String getValueString() {
        return this.valueString;
    }

    public void setValueString(String valueString) {
        this.valueString = valueString;
    }

    public Module getParent() {
        return this.parent;
    }

    public String getName() {
        return this.name;
    }

    public String getId() {
        return this.id;
    }

    public SettingType getSettingType() {
        return this.settingType;
    }

    public String[] getValues() {
        return this.values;
    }

    public float getMinValue() {
        return this.minValue;
    }

    public float getMaxValue() {
        return this.maxValue;
    }
}

