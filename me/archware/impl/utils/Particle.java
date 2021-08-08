/*
 * Decompiled with CFR 0.150.
 */
package me.archware.impl.utils;

public class Particle {
    private float posX;
    private float posY;
    private float startX;

    public Particle(float posX, float posY) {
        this.posX = posX;
        this.posY = posY;
        this.startX = posX;
    }

    public float getPosX() {
        return this.posX;
    }

    public void setPosX(float posX) {
        this.posX = posX;
    }

    public float getPosY() {
        return this.posY;
    }

    public void setPosY(float posY) {
        this.posY = posY;
    }

    public float getStartX() {
        return this.startX;
    }
}

