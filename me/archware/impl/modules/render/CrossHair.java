/*
 * Decompiled with CFR 0.150.
 */
package me.archware.impl.modules.render;

import java.awt.Color;
import me.archware.ArchWare;
import me.archware.base.event.EventTarget;
import me.archware.base.module.Category;
import me.archware.base.module.Module;
import me.archware.base.setting.BooleanValue;
import me.archware.base.setting.NumericValue;
import me.archware.impl.events.EventRender2D;
import me.archware.impl.modules.render.CoolFeatureBtw;
import me.archware.impl.utils.RenderUtil;

public class CrossHair
extends Module {
    private NumericValue width = new NumericValue("Width", "CrossHairWidth", (Module)this, 2.0f, 1.5f, 20.0f);
    private NumericValue height = new NumericValue("Height", "CrossHairHeight", (Module)this, 2.0f, 0.5f, 20.0f);
    private NumericValue distance = new NumericValue("Distance", "CrossHairDistance", (Module)this, 3.0f, 2.5f, 12.0f);
    private BooleanValue dot = new BooleanValue("Dot", "CrossHairDot", this, true);
    private BooleanValue outline = new BooleanValue("Outline", "CrossHairOutline", this, true);
    private NumericValue red = new NumericValue("Red", "CrossHairRed", (Module)this, 255.0f, 0.0f, 255.0f);
    private NumericValue green = new NumericValue("Green", "CrossHairGreen", (Module)this, 0.0f, 0.0f, 255.0f);
    private NumericValue blue = new NumericValue("Blue", "CrossHairBlue", (Module)this, 0.0f, 0.0f, 255.0f);

    public CrossHair() {
        super("CrossHair", Category.RENDER);
    }

    @EventTarget
    public void onRender(EventRender2D event) {
        int color;
        if (ArchWare.moduleManager.getModuleByClass(CoolFeatureBtw.class).isToggled()) {
            return;
        }
        double x = event.getSR().getScaledWidth() / 2;
        double y = event.getSR().getScaledHeight() / 2;
        int n = color = this.mc.objectMouseOver.entityHit == null ? new Color((int)this.red.getValueNumeric(), (int)this.green.getValueNumeric(), (int)this.blue.getValueNumeric()).hashCode() : new Color((int)this.red.getValueNumeric(), (int)this.green.getValueNumeric(), (int)this.blue.getValueNumeric()).darker().hashCode();
        if (this.dot.getValueBoolean()) {
            if (this.outline.getValueBoolean()) {
                RenderUtil.drawRect(x - 2.3, y - 1.7, 3.3, 2.8, Color.BLACK.hashCode());
            }
            RenderUtil.drawRect(x - 1.3, y - 0.7, 1.3, 0.8, color);
        }
        if (this.outline.getValueBoolean()) {
            RenderUtil.drawRect(x - (double)this.distance.getValueNumeric() - 2.0, y - 1.7, (double)(-this.width.getValueNumeric() - 2.0f), (double)(this.height.getValueNumeric() + 2.0f), Color.BLACK.hashCode());
        }
        RenderUtil.drawRect(x - (double)this.distance.getValueNumeric() - 3.0, y - 0.7, (double)(-this.width.getValueNumeric()), (double)this.height.getValueNumeric(), color);
        if (this.outline.getValueBoolean()) {
            RenderUtil.drawRect(x - 2.0, y - (double)this.distance.getValueNumeric() - 1.0, (double)(this.height.getValueNumeric() + 2.0f), (double)(-this.width.getValueNumeric() - 2.0f), Color.BLACK.hashCode());
        }
        RenderUtil.drawRect(x - 1.0, y - (double)this.distance.getValueNumeric() - 2.0, (double)this.height.getValueNumeric(), (double)(-this.width.getValueNumeric()), color);
        if (this.outline.getValueBoolean()) {
            RenderUtil.drawRect(x + (double)this.distance.getValueNumeric() + 1.0, y - 1.7, (double)(this.width.getValueNumeric() + 2.0f), (double)(this.height.getValueNumeric() + 2.0f), Color.BLACK.hashCode());
        }
        RenderUtil.drawRect(x + (double)this.distance.getValueNumeric() + 2.0, y - 0.7, (double)this.width.getValueNumeric(), (double)this.height.getValueNumeric(), color);
        if (this.outline.getValueBoolean()) {
            RenderUtil.drawRect(x - 2.0, y + (double)this.distance.getValueNumeric(), (double)(this.height.getValueNumeric() + 2.0f), (double)(this.width.getValueNumeric() + 2.0f), Color.BLACK.hashCode());
        }
        RenderUtil.drawRect(x - 1.0, y + (double)this.distance.getValueNumeric() + 1.0, (double)this.height.getValueNumeric(), (double)this.width.getValueNumeric(), color);
    }
}

