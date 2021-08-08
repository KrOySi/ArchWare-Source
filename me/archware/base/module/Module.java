/*
 * Decompiled with CFR 0.150.
 */
package me.archware.base.module;

import me.archware.ArchWare;
import me.archware.base.module.Category;
import me.archware.impl.utils.TimeUtil;
import me.archware.notifications.Notification;
import me.archware.notifications.NotificationManager;
import me.archware.notifications.NotificationType;
import net.minecraft.client.Minecraft;

public class Module {
    private String name;
    private String suffix;
    private int key;
    private boolean toggled;
    private Category category;
    private boolean isShown = true;
    private TimeUtil timer;
    private float posX = 1.0f;
    private float posY = 0.0f;
    private boolean isInRenderState;
    public Minecraft mc = Minecraft.getMinecraft();

    public Module(String name, int key, boolean toggled, Category category) {
        this.name = name;
        this.key = key;
        this.toggled = toggled;
        this.category = category;
        if (toggled) {
            ArchWare.eventManager.register(this);
        }
        this.timer = new TimeUtil();
    }

    public Module(String name, Category category, int key) {
        this.name = name;
        this.category = category;
        this.key = key;
        this.timer = new TimeUtil();
    }

    public Module(String name, Category category) {
        this.name = name;
        this.key = 0;
        this.category = category;
        this.timer = new TimeUtil();
    }

    public void onEnable() {
        ArchWare.eventManager.register(this);
    }

    public void onDisable() {
        ArchWare.eventManager.unregister(this);
    }

    public void onToggle() {
    }

    public void onKey(int key) {
        if (this.key == key) {
            this.toggle();
            NotificationManager.addNotification(new Notification(this.name + " was " + (this.isToggled() ? "enabled" : "disabled"), NotificationType.OK));
        }
    }

    public void toggle() {
        this.timer.reset();
        this.onToggle();
        boolean bl = this.toggled = !this.toggled;
        if (this.toggled) {
            this.onEnable();
        } else {
            this.onDisable();
        }
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getKey() {
        return this.key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public boolean isToggled() {
        return this.toggled;
    }

    public Category getCategory() {
        return this.category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getSuffix() {
        return this.suffix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }

    public boolean isShown() {
        return this.isShown;
    }

    public void setShown(boolean shown) {
        this.isShown = shown;
    }

    public float getPosY() {
        return this.posY;
    }

    public void setPosY(float posY) {
        this.posY = posY;
    }

    public float getPosX() {
        return this.posX;
    }

    public void setPosX(float posX) {
        this.posX = posX;
    }

    public boolean isInRenderState() {
        return this.isInRenderState;
    }

    public void setInRenderState(boolean inRenderState) {
        this.isInRenderState = inRenderState;
    }

    public TimeUtil getTimer() {
        return this.timer;
    }
}

