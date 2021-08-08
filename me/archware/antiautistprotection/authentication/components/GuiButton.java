/*
 * Decompiled with CFR 0.150.
 */
package me.archware.antiautistprotection.authentication.components;

import java.awt.Color;
import java.awt.Font;
import java.io.IOException;
import me.archware.impl.utils.RenderUtil;
import me.archware.impl.utils.font.CustomFontRenderer;

public abstract class GuiButton {
    private int id;
    private String text;
    private double posX;
    private double posY;
    private double width;
    private double height;
    private boolean isActive;
    private final CustomFontRenderer font = new CustomFontRenderer(new Font("Arial", 0, 13), true, true);

    public GuiButton(int id, String text, double posX, double posY, double width, double height) {
        this.id = id;
        this.text = text;
        this.posX = posX;
        this.posY = posY;
        this.width = width;
        this.height = height;
    }

    public void draw(int mouseX, int mouseY, float partialTicks) {
        RenderUtil.drawRect(this.posX, this.posY, this.width, this.height, this.isHovered(mouseX, mouseY, this.posX, this.posY, this.width, this.height) ? new Color(62, 62, 62).hashCode() : new Color(42, 42, 42).hashCode());
        RenderUtil.drawRect(this.posX + 0.5, this.posY + 0.5, this.width - 1.0, this.height - 1.0, new Color(12, 12, 12).hashCode());
        this.font.drawCenteredStringWithShadow(this.text, this.posX + this.height + 45.0, this.posY + this.height / 2.0 - 1.0, this.isHovered(mouseX, mouseY, this.posX, this.posY, this.width, this.height) ? -1 : Color.DARK_GRAY.hashCode());
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getText() {
        return this.text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public double getPosX() {
        return this.posX;
    }

    public void setPosX(double posX) {
        this.posX = posX;
    }

    public double getPosY() {
        return this.posY;
    }

    public void setPosY(double posY) {
        this.posY = posY;
    }

    public double getWidth() {
        return this.width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double getHeight() {
        return this.height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public abstract void onClicked();

    public boolean isHovered(int mouseX, int mouseY, double posX, double posY, double width, double height) {
        return (double)mouseX >= posX && (double)mouseX <= posX + width && (double)mouseY >= posY && (double)mouseY <= posY + height;
    }

    public void keyTyped(char typedChar, int keyCode) throws IOException {
    }

    public void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
        this.isActive = this.isHovered(mouseX, mouseY, this.posX, this.posY, this.width, this.height);
        if (this.isHovered(mouseX, mouseY, this.posX, this.posY, this.width, this.height)) {
            this.onClicked();
        }
    }
}

