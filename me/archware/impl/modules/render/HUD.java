/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  javafx.animation.Interpolator
 */
package me.archware.impl.modules.render;

import java.awt.Color;
import java.awt.Font;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import javafx.animation.Interpolator;
import me.archware.ArchWare;
import me.archware.base.clickgui.util.FontUtil;
import me.archware.base.event.EventTarget;
import me.archware.base.module.Category;
import me.archware.base.module.Module;
import me.archware.base.setting.BooleanValue;
import me.archware.base.setting.NumericValue;
import me.archware.base.setting.StringValue;
import me.archware.impl.events.EventRender2D;
import me.archware.impl.modules.render.tabgui.Tab;
import me.archware.impl.modules.render.tabgui.TabManager;
import me.archware.impl.utils.RenderUtil;
import me.archware.impl.utils.font.CustomFontRenderer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.util.text.TextFormatting;

public class HUD
extends Module {
    private StringValue color = new StringValue("Color", "HUDColor", this, "Static", new String[]{"Static", "Rainbow", "Pulsive", "Category"});
    private StringValue position = new StringValue("Position", "HUDPosition", this, "Right", new String[]{"Right", "Left"});
    private StringValue watermark = new StringValue("Watermark type", "HUDWatermark", this, "Simple", new String[]{"Simple", "Arch"});
    private BooleanValue time = new BooleanValue("Time", "HUDTime", this, true);
    private BooleanValue fps = new BooleanValue("FPS", "HUDFPS", this, true);
    private BooleanValue ms = new BooleanValue("Ping", "HUDPing", this, false);
    private BooleanValue tabgui = new BooleanValue("TabGUI", "HUDTabGUI", this, false);
    private BooleanValue font = new BooleanValue("Font", "HUDFont", this, true);
    private BooleanValue outline = new BooleanValue("Outline", "HUDOutline", this, false);
    private BooleanValue line = new BooleanValue("Line", "HUDLine", this, false);
    private NumericValue speed = new NumericValue("Speed", "HUDSpeed", (Module)this, 15000.0f, 2000.0f, 20000.0f);
    private NumericValue offset = new NumericValue("Offset", "Offset", (Module)this, 150.0f, 5.0f, 250.0f);
    private NumericValue red = new NumericValue("Red", "HUDRed", (Module)this, 255.0f, 0.0f, 255.0f);
    private NumericValue green = new NumericValue("Green", "HUDGreen", (Module)this, 255.0f, 0.0f, 255.0f);
    private NumericValue blue = new NumericValue("Blue", "HUDBlue", (Module)this, 255.0f, 0.0f, 255.0f);
    private NumericValue offsetX = new NumericValue("Offset X", "HUDOffsetX", (Module)this, 0.0f, 0.0f, 35.0f);
    private NumericValue offsetY = new NumericValue("Offset Y", "HUDOffsetY", (Module)this, 0.0f, 0.0f, 35.0f);
    private BooleanValue coolFeature = new BooleanValue("Cool feature btw", "HudCoolFeature", this, false);
    private final BooleanValue sort = new BooleanValue("Sort", "HUDSort", this, true);
    private final CustomFontRenderer fontRenderer = new CustomFontRenderer(new Font("Arial", 0, 14), true, true);
    private float cooledAttackStrength = 0.0f;
    private float posX = 6.0f;
    private float posY = 70.0f;
    private float posX2 = 106.0f;
    private float posY2 = 100.0f;

    public HUD() {
        super("HUD", Category.RENDER);
    }

    @EventTarget
    public void onRender(EventRender2D event) {
        int c;
        ScaledResolution sr = new ScaledResolution(this.mc);
        FontUtil.drawStringWithShadow("ArchWare | Build: " + (Object)((Object)TextFormatting.GRAY) + "Development build: 130721" + (Object)((Object)TextFormatting.WHITE) + " - " + ArchWare.login, sr.getScaledWidth() - FontUtil.getStringWidth("ArchWare | Build: Development build: 130721 - " + ArchWare.login) - 1, sr.getScaledHeight() - 8, -1);
        String name = String.valueOf("ArchWare".charAt(0)) + (Object)((Object)TextFormatting.WHITE) + "ArchWare".substring(1) + " " + (this.time.getValueBoolean() ? "[" + (Object)((Object)TextFormatting.GRAY) + new SimpleDateFormat("HH:mm:ss").format(Calendar.getInstance().getTime()) + (Object)((Object)TextFormatting.WHITE) + "] " : "") + (this.fps.getValueBoolean() ? "[FPS: " + (Object)((Object)TextFormatting.GRAY) + Minecraft.getDebugFPS() + (Object)((Object)TextFormatting.WHITE) + "] " : "") + (this.ms.getValueBoolean() ? "[MS: " + (Object)((Object)TextFormatting.GRAY) + (this.mc.isSingleplayer() ? "0" : Long.valueOf(this.mc.getCurrentServerData().pingToServer)) + (Object)((Object)TextFormatting.WHITE) + "]" : "");
        switch (this.color.getValueString()) {
            case "Rainbow": {
                c = RenderUtil.getRainbow((int)this.offset.getValueNumeric(), (int)this.speed.getValueNumeric()).hashCode();
                break;
            }
            case "Pulsive": {
                c = RenderUtil.fade(new Color((int)this.red.getValueNumeric(), (int)this.green.getValueNumeric(), (int)this.blue.getValueNumeric()), 1, 1).hashCode();
                break;
            }
            default: {
                c = new Color((int)this.red.getValueNumeric(), (int)this.green.getValueNumeric(), (int)this.blue.getValueNumeric()).hashCode();
            }
        }
        switch (this.watermark.getValueString()) {
            case "Simple": {
                if (this.font.getValueBoolean()) {
                    FontUtil.drawStringWithShadow(name, 1.0, 3.0, c);
                    break;
                }
                this.mc.fontRendererObj.drawStringWithShadow(name, 2.0f, 2.0f, c);
                break;
            }
            case "Arch": {
                RenderUtil.drawRect(6.0, 4.0, (double)(this.fontRenderer.getStringWidth("ArchWare".toLowerCase() + " | fps: " + Minecraft.getDebugFPS() + " | time: " + new SimpleDateFormat("HH:mm:ss").format(Calendar.getInstance().getTime())) + 4), 10.0, Integer.MIN_VALUE);
                RenderUtil.drawRect(6.0, 4.0, (double)(this.fontRenderer.getStringWidth("ArchWare".toLowerCase() + " | fps: " + Minecraft.getDebugFPS() + " | time: " + new SimpleDateFormat("HH:mm:ss").format(Calendar.getInstance().getTime())) + 4), 1.0, RenderUtil.getRainbow(1250, 5000).hashCode());
                this.fontRenderer.drawString(String.valueOf("ArchWare".charAt(0)).toLowerCase() + "ArchWare".substring(1).toLowerCase() + " | fps: " + Minecraft.getDebugFPS() + " | time: " + new SimpleDateFormat("HH:mm:ss").format(Calendar.getInstance().getTime()), 8.0f, 8.0f, -1);
            }
        }
        if (this.tabgui.getValueBoolean()) {
            RenderUtil.drawRect(2.0, 15.0, 50.0, (double)(12 * Category.values().length), new Color(0, 0, 0, 110).hashCode());
            for (Tab tab : TabManager.getTabs()) {
                tab.draw();
            }
        }
        ArrayList<Module> modules = new ArrayList<Module>(ArchWare.moduleManager.modules);
        if (this.sort.getValueBoolean()) {
            if (this.font.getValueBoolean()) {
                modules.sort((m1, m2) -> FontUtil.getStringWidth(m2.getName() + (m2.getSuffix() == null ? "" : " " + m2.getSuffix())) - FontUtil.getStringWidth(m1.getName() + (m1.getSuffix() == null ? "" : " " + m1.getSuffix())));
            } else {
                modules.sort((m1, m2) -> this.mc.fontRendererObj.getStringWidth(m2.getName() + (m2.getSuffix() == null ? "" : " " + m2.getSuffix())) - this.mc.fontRendererObj.getStringWidth(m1.getName() + (m1.getSuffix() == null ? "" : " " + m1.getSuffix())));
            }
        }
        if (this.coolFeature.getValueBoolean()) {
            if (String.valueOf(this.cooledAttackStrength).equals("NaN")) {
                this.cooledAttackStrength = this.mc.player.getCooledAttackStrength(0.0f);
            }
            this.cooledAttackStrength = (float)Interpolator.LINEAR.interpolate((double)this.cooledAttackStrength, (double)(this.mc.player.getCooledAttackStrength(0.0f) / 1.0f * 50.0f), 10.0 / (double)Minecraft.getDebugFPS());
            RenderUtil.drawRect(6.0, (double)(sr.getScaledHeight() / 2 - 80), 100.0, 30.0, Integer.MIN_VALUE);
            RenderUtil.drawRect(6.0, (double)(sr.getScaledHeight() / 2 - 80), 100.0, 1.0, RenderUtil.getRainbow(1250, 5000).hashCode());
            this.fontRenderer.drawString("cooldown: ", 10.0f, sr.getScaledHeight() / 2 - 70, -1);
            RenderUtil.drawRect(15 + this.fontRenderer.getStringWidth("cooldown:"), (double)(sr.getScaledHeight() / 2 - 69), 50.0, 1.5, new Color(30, 30, 30, 70).hashCode());
            RenderUtil.drawRect(15 + this.fontRenderer.getStringWidth("cooldown:"), (double)(sr.getScaledHeight() / 2 - 69), (double)this.cooledAttackStrength, 1.5, -1);
            this.fontRenderer.drawString("tick: ", 10.0f, sr.getScaledHeight() / 2 - 60, -1);
            RenderUtil.drawRect(15 + this.fontRenderer.getStringWidth("cooldown:"), (double)(sr.getScaledHeight() / 2 - 59), 50.0, 1.5, new Color(30, 30, 30, 70).hashCode());
            RenderUtil.drawRect(15 + this.fontRenderer.getStringWidth("cooldown:"), (double)(sr.getScaledHeight() / 2 - 59), 50.0, 1.5, -1);
        }
        int posY = 0;
        int posX = 0;
        switch (this.position.getValueString()) {
            case "Right": {
                posY = (int)this.offsetY.getValueNumeric() - 1;
                if (this.font.getValueBoolean()) {
                    if (this.line.getValueBoolean()) {
                        posX = new ScaledResolution(this.mc).getScaledWidth() - (int)this.offsetX.getValueNumeric();
                        break;
                    }
                    posX = new ScaledResolution(this.mc).getScaledWidth() + 1 - (int)this.offsetX.getValueNumeric();
                    break;
                }
                posX = new ScaledResolution(this.mc).getScaledWidth() + 3 - (int)this.offsetX.getValueNumeric();
                break;
            }
            case "Left": {
                posY = this.tabgui.getValueBoolean() ? 95 : 15;
                posX = 1;
            }
        }
        posY += this.mc.player.getActivePotionEffects().isEmpty() ? 0 : (this.position.getValueString().equalsIgnoreCase("Right") ? 25 : 0);
        for (Module m : modules) {
            if (String.valueOf(m.getPosX()).equals("NaN")) {
                m.setPosX(sr.getScaledWidth());
            }
            if (m.isToggled()) {
                m.setInRenderState(true);
            } else if ((int)m.getPosX() == -posX) {
                m.setInRenderState(false);
            }
            if (!m.isToggled()) continue;
            m.setPosX(posX);
            switch (this.color.getValueString()) {
                case "Static": {
                    c = new Color((int)this.red.getValueNumeric(), (int)this.green.getValueNumeric(), (int)this.blue.getValueNumeric()).hashCode();
                    break;
                }
                case "Rainbow": {
                    c = RenderUtil.getRainbow(-((int)this.offset.getValueNumeric()) * posY, (int)this.speed.getValueNumeric()).hashCode();
                    break;
                }
                case "Pulsive": {
                    c = RenderUtil.fade(new Color((int)this.red.getValueNumeric(), (int)this.green.getValueNumeric(), (int)this.blue.getValueNumeric()), posY, 125).hashCode();
                    break;
                }
                case "Category": {
                    c = m.getCategory().color();
                }
            }
            if (this.outline.getValueBoolean()) {
                switch (this.position.getValueString()) {
                    case "Left": {
                        RenderUtil.drawRect(m.getPosX() - 1.0f, (double)posY, this.font.getValueBoolean() ? (double)(FontUtil.getStringWidth(m.getName() + (m.getSuffix() == null ? "" : (Object)((Object)TextFormatting.GRAY) + " " + m.getSuffix())) + 5) : (double)(this.mc.fontRendererObj.getStringWidth(m.getName() + (m.getSuffix() == null ? "" : (Object)((Object)TextFormatting.GRAY) + " " + m.getSuffix())) + 3), 10.0, new Color(0, 0, 0, 125).hashCode());
                        break;
                    }
                    case "Right": {
                        RenderUtil.drawRect(m.getPosX(), (double)posY + 0.5, this.font.getValueBoolean() ? (double)(-FontUtil.getStringWidth(m.getName() + (m.getSuffix() == null ? "" : (Object)((Object)TextFormatting.GRAY) + " " + m.getSuffix())) - 4) : (double)(-this.mc.fontRendererObj.getStringWidth(m.getName() + (m.getSuffix() == null ? "" : (Object)((Object)TextFormatting.GRAY) + " " + m.getSuffix())) - 6), 10.0, new Color(0, 0, 0, 125).hashCode());
                    }
                }
            }
            if (this.line.getValueBoolean()) {
                switch (this.position.getValueString()) {
                    case "Left": {
                        RenderUtil.drawRect(posX - 1, (double)posY, 1.0, 10.0, c);
                        break;
                    }
                    case "Right": {
                        RenderUtil.drawRect(posX - (this.font.getValueBoolean() ? 1 : 4), (double)posY + 0.5, 1.0, 10.0, c);
                    }
                }
            }
            if (this.font.getValueBoolean()) {
                FontUtil.drawStringWithShadow(m.getName() + (m.getSuffix() == null ? "" : (Object)((Object)TextFormatting.GRAY) + " " + m.getSuffix()), (double)(m.getPosX() - (float)(m.getPosX() == 1.0f ? 0 : FontUtil.getStringWidth(m.getName() + (m.getSuffix() == null ? "" : " " + m.getSuffix())) + 3)) + (this.line.getValueBoolean() ? 0.0 : 0.5), posY + 3, c);
            } else {
                this.mc.fontRendererObj.drawStringWithShadow(m.getName() + (m.getSuffix() == null ? "" : (Object)((Object)TextFormatting.GRAY) + " " + m.getSuffix()), m.getPosX() - (float)(m.getPosX() == 1.0f ? 0 : this.mc.fontRendererObj.getStringWidth(m.getName() + (m.getSuffix() == null ? "" : " " + m.getSuffix())) + 3) + (this.position.getValueString().equals("Left") ? 1.0f : (this.line.getValueBoolean() ? -1.5f : -1.0f)), posY + 1, c);
            }
            posY += 10;
        }
    }

    @Override
    public void onKey(int key) {
        if (this.tabgui.getValueBoolean()) {
            try {
                for (Tab tab : TabManager.getTabs()) {
                    switch (key) {
                        case 208: {
                            if (tab.isExtended()) {
                                if (ArchWare.moduleManager.getModulesByCategory(tab.getParent()).indexOf(tab.getSelectedMod()) == ArchWare.moduleManager.getModulesByCategory(tab.getParent()).size() - 1) break;
                                tab.setSelectedMod(ArchWare.moduleManager.getModulesByCategory(tab.getParent()).get(ArchWare.moduleManager.getModulesByCategory(tab.getParent()).indexOf(tab.getSelectedMod()) + 1));
                                break;
                            }
                            if (TabManager.getTabs().indexOf(tab) == TabManager.getTabs().size() - 1 || !tab.isSelected()) break;
                            tab.setSelected(false);
                            TabManager.getTabs().get(TabManager.getTabs().indexOf(tab) + 1).setSelected(true);
                            return;
                        }
                        case 200: {
                            if (tab.isExtended()) {
                                if (ArchWare.moduleManager.getModulesByCategory(tab.getParent()).indexOf(tab.getSelectedMod()) == 0) break;
                                tab.setSelectedMod(ArchWare.moduleManager.getModulesByCategory(tab.getParent()).get(ArchWare.moduleManager.getModulesByCategory(tab.getParent()).indexOf(tab.getSelectedMod()) - 1));
                                break;
                            }
                            if (TabManager.getTabs().indexOf(tab) == 0 || !tab.isSelected()) break;
                            tab.setSelected(false);
                            TabManager.getTabs().get(TabManager.getTabs().indexOf(tab) - 1).setSelected(true);
                            return;
                        }
                        case 205: {
                            if (!TabManager.getTabs().stream().filter(Tab::isSelected).findFirst().get().equals(tab)) break;
                            if (tab.isExtended()) {
                                tab.getSelectedMod().toggle();
                                break;
                            }
                            if (TabManager.getTabs().stream().filter(Tab::isExtended).count() >= 1L) break;
                            tab.setExtended(true);
                            return;
                        }
                        case 203: {
                            if (!tab.isExtended()) break;
                            tab.setExtended(false);
                            return;
                        }
                    }
                }
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
        super.onKey(key);
    }
}

