/*
 * Decompiled with CFR 0.150.
 */
package me.archware.antiautistprotection.authentication;

import java.awt.Color;
import java.awt.Font;
import java.io.IOException;
import java.sql.SQLException;
import me.archware.ArchWare;
import me.archware.antiautistprotection.authentication.SQLConnector;
import me.archware.antiautistprotection.authentication.components.GuiButton;
import me.archware.antiautistprotection.authentication.components.GuiTextField;
import me.archware.impl.utils.font.CustomFontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;

public class GuiAuthenticate
extends GuiScreen {
    private final CustomFontRenderer font = new CustomFontRenderer(new Font("Arial", 0, 17), true, true);
    protected static String status = "Waiting...";
    private final CustomFontRenderer font2 = new CustomFontRenderer(new Font("Arial", 0, 13), true, true);
    private GuiTextField login;
    private GuiTextField password;
    private GuiButton authenticate;

    @Override
    public void initGui() {
        ScaledResolution sr = new ScaledResolution(this.mc);
        this.login = new GuiTextField(1337, "Login", sr.getScaledWidth() / 2 - 59, sr.getScaledHeight() / 2 - 40, 120.0, 14.0);
        this.password = new GuiTextField(1337, "Password", sr.getScaledWidth() / 2 - 59, sr.getScaledHeight() / 2 - 20, 120.0, 14.0);
        this.authenticate = new GuiButton(1337, "Authenticate", sr.getScaledWidth() / 2 - 59, sr.getScaledHeight() / 2, 120.0, 14.0){

            @Override
            public void onClicked() {
                if (GuiAuthenticate.this.login.getTextField().isEmpty()) {
                    status = "Login field cannot be empty.";
                    return;
                }
                if (GuiAuthenticate.this.password.getTextField().isEmpty()) {
                    status = "Password field cannot be empty.";
                    return;
                }
                status = "Authenticating...";
                ArchWare.login = GuiAuthenticate.this.login.getTextField();
                try {
                    new SQLConnector().authenticate(ArchWare.login, GuiAuthenticate.this.password.getTextField());
                }
                catch (SQLException sQLException) {
                    // empty catch block
                }
            }
        };
        super.initGui();
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        ScaledResolution sr = new ScaledResolution(this.mc);
        Gui.drawRect(0.0, 0.0, sr.getScaledWidth(), sr.getScaledWidth(), new Color(16, 16, 16).hashCode());
        this.font.drawCenteredStringWithShadow(status, sr.getScaledWidth() / 2 + 2, 25.0f, -1);
        this.font2.drawCenteredStringWithShadow("ArchWare. Build: Development build: 130721", sr.getScaledWidth() / 2, sr.getScaledHeight() - 12, -1);
        this.login.draw(mouseX, mouseY, partialTicks);
        this.password.draw(mouseX, mouseY, partialTicks);
        this.authenticate.draw(mouseX, mouseY, partialTicks);
        super.drawScreen(mouseX, mouseY, partialTicks);
    }

    @Override
    protected void keyTyped(char typedChar, int keyCode) throws IOException {
        if (typedChar << 3 != 0) {
            this.login.keyTyped(typedChar, keyCode);
            this.password.keyTyped(typedChar, keyCode);
        }
        super.keyTyped(typedChar, keyCode);
    }

    @Override
    protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
        this.login.mouseClicked(mouseX, mouseY, mouseButton);
        this.password.mouseClicked(mouseX, mouseY, mouseButton);
        this.authenticate.mouseClicked(mouseX, mouseY, mouseButton);
        super.mouseClicked(mouseX, mouseY, mouseButton);
    }
}

