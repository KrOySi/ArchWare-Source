/*
 * Decompiled with CFR 0.150.
 */
package me.archware.base.altmanager;

import java.awt.Color;
import java.io.IOException;
import me.archware.ArchWare;
import me.archware.base.altmanager.Alt;
import me.archware.base.altmanager.AltManagerCreateAltGui;
import me.archware.base.altmanager.AltManagerDirect;
import me.archware.base.altmanager.AuthenticatorThread;
import me.archware.impl.utils.RenderUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;

public class AltManagerGUI
extends GuiScreen {
    Alt selectedAlt;
    public static String status;

    public void addButtons() {
        ScaledResolution sr = new ScaledResolution(Minecraft.getMinecraft());
        this.buttonList.add(new GuiButton(0, sr.getScaledWidth() / 2 - 78 - 80, sr.getScaledHeight() - 40, 70, 20, "Login"));
        this.buttonList.add(new GuiButton(1, sr.getScaledWidth() / 2 - 78, sr.getScaledHeight() - 40, 70, 20, "Direct Login"));
        this.buttonList.add(new GuiButton(2, sr.getScaledWidth() / 2 - 78 + 80, sr.getScaledHeight() - 40, 70, 20, "Add"));
        this.buttonList.add(new GuiButton(3, sr.getScaledWidth() / 2 - 78 + 160, sr.getScaledHeight() - 40, 70, 20, "Delete"));
    }

    @Override
    public void keyTyped(char typedChar, int keyCode) throws IOException {
        super.keyTyped(typedChar, keyCode);
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        ScaledResolution sr = new ScaledResolution(this.mc);
        this.drawDefaultBackground();
        int posY = 70;
        for (Alt alt : ArchWare.alts) {
            RenderUtil.drawRect(sr.getScaledWidth() / 2 - 110, (double)posY, 215.0, 35.0, new Color(0, 0, 0, 135).hashCode());
            this.mc.fontRendererObj.drawString(alt.name, sr.getScaledWidth() / 2 - 60, posY + 5, -1);
            this.mc.fontRendererObj.drawString(this.getPassword(alt.password), sr.getScaledWidth() / 2 - 60, posY + 15, -1);
            posY += 45;
        }
        super.drawScreen(mouseX, mouseY, partialTicks);
    }

    public String getPassword(String pass) {
        StringBuilder stringBuilder = new StringBuilder();
        if (pass.length() >= 5) {
            stringBuilder.append(pass, 0, 4);
            for (int i = 4; i < pass.length(); ++i) {
                if (i == 16) {
                    stringBuilder.append("...");
                    break;
                }
                stringBuilder.append("*");
            }
        } else {
            stringBuilder.append(pass);
        }
        return stringBuilder.toString();
    }

    @Override
    public void initGui() {
        status = "ArchWare Alt manager";
        this.selectedAlt = null;
        this.addButtons();
    }

    @Override
    public void confirmClicked(boolean result, int id) {
        super.confirmClicked(result, id);
    }

    @Override
    public void mouseClickMove(int mouseX, int mouseY, int clickedMouseButton, long timeSinceLastClick) {
    }

    @Override
    public void mouseReleased(int mouseX, int mouseY, int state) {
        super.mouseReleased(mouseX, mouseY, state);
    }

    @Override
    public void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
        super.mouseClicked(mouseX, mouseY, mouseButton);
    }

    @Override
    public void actionPerformed(GuiButton button) throws IOException {
        if (button.id == 3 && this.selectedAlt != null) {
            ArchWare.alts.remove(this.selectedAlt);
        }
        if (button.id == 2) {
            this.mc.displayGuiScreen(new AltManagerCreateAltGui());
        }
        if (button.id == 1) {
            this.mc.displayGuiScreen(new AltManagerDirect());
        }
        if (button.id == 0 && this.selectedAlt != null) {
            AuthenticatorThread authenticator = new AuthenticatorThread(this.selectedAlt.getName(), this.selectedAlt.getPassword());
            authenticator.start();
        }
    }

    public boolean isHovered(int mouseX, int mouseY, int posX, int posY, int width, int height) {
        return mouseX >= posX && mouseX <= posX + width && mouseY >= posY && mouseY <= posY + height;
    }
}

