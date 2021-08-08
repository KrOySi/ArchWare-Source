/*
 * Decompiled with CFR 0.150.
 */
package me.archware.base.clickgui;

import java.awt.Color;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import me.archware.ArchWare;
import me.archware.base.clickgui.Panel;
import me.archware.base.clickgui.elements.Element;
import me.archware.base.clickgui.elements.ModuleButton;
import me.archware.base.clickgui.elements.menu.ElementSlider;
import me.archware.base.clickgui.util.ColorUtil;
import me.archware.base.clickgui.util.FontUtil;
import me.archware.base.module.Category;
import me.archware.base.module.Module;
import me.archware.impl.managers.FileManager;
import me.archware.impl.managers.SettingsManager;
import me.archware.impl.utils.Particle;
import me.archware.impl.utils.RenderUtil;
import me.archware.impl.utils.TimeUtil;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;

public class ClickGUI
extends GuiScreen {
    private TimeUtil timeUtil = new TimeUtil();
    public static ArrayList<Panel> panels;
    public static ArrayList<Panel> rpanels;
    private ModuleButton mb = null;
    public SettingsManager setmgr;
    private int opacity;
    private List<Particle> particles = new ArrayList<Particle>();

    public ClickGUI() {
        this.setmgr = ArchWare.settingManager;
        FontUtil.setupFontUtils();
        panels = new ArrayList();
        double pwidth = 80.0;
        double pheight = 15.0;
        double px = 10.0;
        double py = 10.0;
        double pyplus = pheight + 10.0;
        for (final Category c : Category.values()) {
            String title = Character.toUpperCase(c.name().toLowerCase().charAt(0)) + c.name().toLowerCase().substring(1);
            panels.add(new Panel(title, px, py, pwidth, pheight, false, this){

                @Override
                public void setup() {
                    ArrayList<Module> modules = new ArrayList<Module>(ArchWare.moduleManager.modules);
                    modules.sort((m1, m2) -> FontUtil.getStringWidth(m2.getName()) - FontUtil.getStringWidth(m1.getName()));
                    for (Module m : modules) {
                        if (!m.getCategory().equals((Object)c)) continue;
                        this.Elements.add(new ModuleButton(m, this));
                    }
                }
            });
            py += pyplus;
        }
        rpanels = new ArrayList();
        for (Panel p : panels) {
            rpanels.add(p);
        }
        Collections.reverse(rpanels);
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        int i;
        ScaledResolution sr = new ScaledResolution(this.mc);
        if (this.particles.size() < 75) {
            for (i = 0; i < 75; ++i) {
                this.particles.add(new Particle((float)(Math.random() * (double)sr.getScaledWidth()), (float)(Math.random() * (double)sr.getScaledHeight())));
            }
        }
        if (this.timeUtil.hasReached(25.0)) {
            if (this.opacity < 255) {
                this.opacity += 15;
            }
            for (Particle particle : this.particles) {
                particle.setPosY(particle.getPosY() + 0.5f);
            }
            this.timeUtil.reset();
        }
        for (i = 0; i < this.particles.size(); ++i) {
            ScaledResolution scaledResolution = new ScaledResolution(this.mc);
            if (this.particles.get(i).getPosY() >= (float)scaledResolution.getScaledHeight()) {
                this.particles.remove(i);
                this.particles.add(new Particle((float)(Math.random() * (double)new ScaledResolution(this.mc).getScaledWidth()), 0.0f));
            }
            RenderUtil.drawRect(this.particles.get(i).getPosX(), (double)this.particles.get(i).getPosY(), 1.0, 1.0, new Color(255, 255, 255, this.opacity).hashCode());
        }
        for (Panel p : panels) {
            p.drawScreen(mouseX, mouseY, partialTicks);
        }
        this.mb = null;
        block4: for (Panel p : panels) {
            if (p == null || !p.visible || !p.extended || p.Elements == null || p.Elements.size() <= 0) continue;
            for (ModuleButton e : p.Elements) {
                if (!e.listening) continue;
                this.mb = e;
                break block4;
            }
        }
        for (Panel panel : panels) {
            if (!panel.extended || !panel.visible || panel.Elements == null) continue;
            for (ModuleButton b : panel.Elements) {
                if (!b.extended || b.menuelements == null || b.menuelements.isEmpty()) continue;
                double off = 0.0;
                Color temp = ColorUtil.getClickGUIColor().darker();
                int outlineColor = new Color(temp.getRed(), temp.getGreen(), temp.getBlue(), 170).getRGB();
                for (Element e : b.menuelements) {
                    e.offset = off;
                    e.update();
                    e.drawScreen(mouseX, mouseY, partialTicks);
                    off += e.height;
                }
            }
        }
        super.drawScreen(mouseX, mouseY, partialTicks);
    }

    @Override
    public void mouseClicked(int mouseX, int mouseY, int mouseButton) {
        if (this.mb != null) {
            return;
        }
        for (Panel panel : rpanels) {
            if (!panel.extended || !panel.visible || panel.Elements == null) continue;
            for (ModuleButton b : panel.Elements) {
                if (!b.extended) continue;
                for (Element e : b.menuelements) {
                    if (!e.mouseClicked(mouseX, mouseY, mouseButton)) continue;
                    return;
                }
            }
        }
        for (Panel p : rpanels) {
            if (!p.mouseClicked(mouseX, mouseY, mouseButton)) continue;
            return;
        }
        try {
            super.mouseClicked(mouseX, mouseY, mouseButton);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void mouseReleased(int mouseX, int mouseY, int state) {
        if (this.mb != null) {
            return;
        }
        for (Panel panel : rpanels) {
            if (!panel.extended || !panel.visible || panel.Elements == null) continue;
            for (ModuleButton b : panel.Elements) {
                if (!b.extended) continue;
                for (Element e : b.menuelements) {
                    e.mouseReleased(mouseX, mouseY, state);
                }
            }
        }
        for (Panel p : rpanels) {
            p.mouseReleased(mouseX, mouseY, state);
        }
        super.mouseReleased(mouseX, mouseY, state);
    }

    @Override
    protected void keyTyped(char typedChar, int keyCode) {
        for (Panel p : rpanels) {
            if (p == null || !p.visible || !p.extended || p.Elements == null || p.Elements.size() <= 0) continue;
            for (ModuleButton e : p.Elements) {
                try {
                    if (!e.keyTyped(typedChar, keyCode)) continue;
                    return;
                }
                catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }
        try {
            super.keyTyped(typedChar, keyCode);
        }
        catch (IOException e2) {
            e2.printStackTrace();
        }
    }

    @Override
    public void onGuiClosed() {
        for (Panel panel : rpanels) {
            if (panel.extended && panel.visible && panel.Elements != null) {
                for (ModuleButton b : panel.Elements) {
                    if (!b.extended) continue;
                    for (Element e : b.menuelements) {
                        if (!(e instanceof ElementSlider)) continue;
                        ((ElementSlider)e).dragging = false;
                    }
                }
            }
            try {
                FileManager.update();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
        this.opacity = 0;
        this.particles.clear();
    }

    public void closeAllSettings() {
        for (Panel p : rpanels) {
            if (p == null || !p.visible || !p.extended || p.Elements == null || p.Elements.size() <= 0) continue;
            for (ModuleButton moduleButton : p.Elements) {
            }
        }
    }
}

