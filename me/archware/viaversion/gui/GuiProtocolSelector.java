/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  com.mojang.realmsclient.gui.ChatFormatting
 *  org.lwjgl.opengl.GL11
 */
package me.archware.viaversion.gui;

import com.mojang.realmsclient.gui.ChatFormatting;
import java.io.IOException;
import me.archware.viaversion.ViaMCP;
import me.archware.viaversion.protocols.ProtocolCollection;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiSlot;
import org.lwjgl.opengl.GL11;

public class GuiProtocolSelector
extends GuiScreen {
    private GuiScreen parent;
    public SlotList list;

    public GuiProtocolSelector(GuiScreen parent) {
        this.parent = parent;
    }

    @Override
    public void initGui() {
        super.initGui();
        this.buttonList.add(new GuiButton(1, this.width / 2 - 100, this.height - 27, 200, 20, "Back"));
        this.list = new SlotList(this.mc, this.width, this.height, 32, this.height - 32, 10){

            @Override
            protected void func_192637_a(int p_192637_1_, int p_192637_2_, int p_192637_3_, int p_192637_4_, int p_192637_5_, int p_192637_6_, float p_192637_7_) {
                System.out.println("skid");
            }
        };
    }

    @Override
    protected void actionPerformed(GuiButton p_actionPerformed_1_) throws IOException {
        this.list.actionPerformed(p_actionPerformed_1_);
        if (p_actionPerformed_1_.id == 1) {
            this.mc.displayGuiScreen(this.parent);
        }
    }

    @Override
    public void handleMouseInput() throws IOException {
        this.list.handleMouseInput();
        super.handleMouseInput();
    }

    @Override
    public void drawScreen(int p_drawScreen_1_, int p_drawScreen_2_, float p_drawScreen_3_) {
        this.list.drawScreen(p_drawScreen_1_, p_drawScreen_2_, p_drawScreen_3_);
        GL11.glPushMatrix();
        GL11.glScalef((float)2.0f, (float)2.0f, (float)2.0f);
        this.drawCenteredString(this.fontRendererObj, (Object)ChatFormatting.BOLD + "ViaMCP Reborn", this.width / 4, 6, 0xFFFFFF);
        GL11.glPopMatrix();
        this.drawString(this.fontRendererObj, "Maintained by Hideri", 3, 3, -1);
        this.drawString(this.fontRendererObj, "Discord: Hideri#9003", 3, 13, -1);
        this.drawString(this.fontRendererObj, "Credits", 3, this.height - 30, -1);
        this.drawString(this.fontRendererObj, "ViaForge: https://github.com/FlorianMichael/ViaForge", 3, this.height - 20, -1);
        this.drawString(this.fontRendererObj, "Original ViaMCP: https://github.com/LaVache-FR/ViaMCP", 3, this.height - 10, -1);
        super.drawScreen(p_drawScreen_1_, p_drawScreen_2_, p_drawScreen_3_);
    }

    abstract class SlotList
    extends GuiSlot {
        public SlotList(Minecraft p_i1052_1_, int p_i1052_2_, int p_i1052_3_, int p_i1052_4_, int p_i1052_5_, int p_i1052_6_) {
            super(p_i1052_1_, p_i1052_2_, p_i1052_3_, p_i1052_4_, p_i1052_5_, p_i1052_6_);
        }

        @Override
        protected int getSize() {
            return ProtocolCollection.values().length;
        }

        @Override
        protected void elementClicked(int i, boolean b, int i1, int i2) {
            ViaMCP.getInstance().setVersion(ProtocolCollection.values()[i].getVersion().getVersion());
        }

        @Override
        protected boolean isSelected(int i) {
            return false;
        }

        @Override
        protected void drawBackground() {
            GuiProtocolSelector.this.drawDefaultBackground();
        }
    }
}

