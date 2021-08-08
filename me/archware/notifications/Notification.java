/*
 * Decompiled with CFR 0.150.
 */
package me.archware.notifications;

import me.archware.notifications.NotificationType;

public class Notification {
    private String description;
    private NotificationType notificationType;
    private double posY;
    private double posX;
    private double lastTickPosY;
    private double lastTickPosX;
    private double width;
    private boolean passed;
    private long startTime;

    public Notification(String description, NotificationType notificationType) {
        this.description = description;
        this.notificationType = notificationType;
        this.startTime = System.currentTimeMillis();
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPosY() {
        return this.posY;
    }

    public void setPosY(double posY) {
        this.posY = posY;
    }

    public void setPassed(boolean passed) {
        this.passed = passed;
    }

    public boolean isPassed() {
        return this.passed;
    }

    public double getLastTickPosY() {
        return this.lastTickPosY;
    }

    public void setLastTickPosY(double lastTickPosY) {
        this.lastTickPosY = lastTickPosY;
    }

    public double getWidth() {
        return this.width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double getPosX() {
        return this.posX;
    }

    public void setPosX(double posX) {
        this.posX = posX;
    }

    public double getLastTickPosX() {
        return this.lastTickPosX;
    }

    public void setLastTickPosX(double lastTickPosX) {
        this.lastTickPosX = lastTickPosX;
    }

    public NotificationType getNotificationType() {
        return this.notificationType;
    }

    public long getStartTime() {
        return this.startTime;
    }
}

