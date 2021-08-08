/*
 * Decompiled with CFR 0.150.
 */
package me.archware.base.altmanager;

import java.awt.Color;
import java.io.IOException;
import me.archware.ArchWare;
import me.archware.base.altmanager.Alt;
import me.archware.base.altmanager.AltManagerGUI;
import me.archware.base.clickgui.util.FontUtil;
import me.archware.impl.managers.FileManager;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.client.gui.ScaledResolution;

public class AltManagerCreateAltGui
extends GuiScreen {
    GuiButton add;
    GuiButton back;
    GuiTextField login;
    GuiTextField password;

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        ScaledResolution sr = new ScaledResolution(this.mc);
        this.drawDefaultBackground();
        Gui.drawRect(sr.getScaledWidth() / 2 - 80, sr.getScaledHeight() / 2 - 100, sr.getScaledWidth() / 2 + 80, sr.getScaledHeight() / 2 + 100, new Color(0.0f, 0.0f, 0.0f, 0.6f).hashCode());
        FontUtil.drawCenteredStringWithShadow("Add alt", sr.getScaledWidth() / 2 - 1, sr.getScaledHeight() / 2 - 95, -1);
        this.login.drawTextBox();
        this.password.drawTextBox();
        if (!this.login.isFocused() && this.login.getText().isEmpty()) {
            FontUtil.drawStringWithShadow("Login", sr.getScaledWidth() / 2 - 55, sr.getScaledHeight() / 2 - 34, Color.DARK_GRAY.hashCode());
        }
        if (!this.password.isFocused() && this.password.getText().isEmpty()) {
            FontUtil.drawStringWithShadow("Password", sr.getScaledWidth() / 2 - 55, sr.getScaledHeight() / 2 - 4, Color.DARK_GRAY.hashCode());
        }
        super.drawScreen(mouseX, mouseY, partialTicks);
    }

    @Override
    protected void keyTyped(char typedChar, int keyCode) throws IOException {
        this.login.textboxKeyTyped(typedChar, keyCode);
        this.password.textboxKeyTyped(typedChar, keyCode);
        super.keyTyped(typedChar, keyCode);
    }

    @Override
    public void initGui() {
        ScaledResolution sr = new ScaledResolution(this.mc);
        this.add = new GuiButton(0, sr.getScaledWidth() / 2 - 60, sr.getScaledHeight() / 2 + 30, 120, 20, "Add");
        this.back = new GuiButton(1, sr.getScaledWidth() / 2 - 60, sr.getScaledHeight() / 2 + 60, 120, 20, "Back");
        this.login = new GuiTextField(2, this.mc.fontRendererObj, sr.getScaledWidth() / 2 - 60, sr.getScaledHeight() / 2 - 40, 120, 20);
        this.password = new GuiTextField(2, this.mc.fontRendererObj, sr.getScaledWidth() / 2 - 60, sr.getScaledHeight() / 2 - 10, 120, 20);
        this.buttonList.add(this.add);
        this.buttonList.add(this.back);
        super.initGui();
    }

    @Override
    protected void actionPerformed(GuiButton button) throws IOException {
        if (button.id == 0) {
            ArchWare.alts.add(new Alt(this.login.getText(), this.password.getText().isEmpty() ? "" : this.password.getText()));
            FileManager.saveAlts();
        }
        if (button.id == 1) {
            this.mc.displayGuiScreen(new AltManagerGUI());
        }
        super.actionPerformed(button);
    }

    @Override
    protected void mouseClickMove(int mouseX, int mouseY, int clickedMouseButton, long timeSinceLastClick) {
        super.mouseClickMove(mouseX, mouseY, clickedMouseButton, timeSinceLastClick);
    }

    @Override
    protected void mouseReleased(int mouseX, int mouseY, int state) {
        super.mouseReleased(mouseX, mouseY, state);
    }

    @Override
    protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
        this.login.mouseClicked(mouseX, mouseY, mouseButton);
        this.password.mouseClicked(mouseX, mouseY, mouseButton);
        super.mouseClicked(mouseX, mouseY, mouseButton);
    }

    @Override
    public void confirmClicked(boolean result, int id) {
        super.confirmClicked(result, id);
    }
}

