/*
 * Decompiled with CFR 0.150.
 */
package me.archware.impl.modules.combat;

import me.archware.base.event.EventTarget;
import me.archware.base.module.Category;
import me.archware.base.module.Module;
import me.archware.base.setting.NumericValue;
import me.archware.impl.events.EventUpdate;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.util.math.AxisAlignedBB;

public class Hitbox
extends Module {
    private final NumericValue offset = new NumericValue("Offset", "HitBoxOffset", (Module)this, 1.0f, 0.2f, 10.0f);

    public Hitbox() {
        super("Hitbox", Category.COMBAT);
    }

    @EventTarget
    public void onUpdate(EventUpdate event) {
        for (Entity e : this.mc.world.loadedEntityList) {
            if (e instanceof EntityItem || e == this.mc.player) continue;
            float width = e.width;
            float height = e.height;
            e.setEntityBoundingBox(new AxisAlignedBB(e.posX - (double)width - (double)this.offset.getValueNumeric(), e.posY, e.posZ + (double)width + (double)this.offset.getValueNumeric(), e.posX + (double)width + (double)this.offset.getValueNumeric(), e.posY + (double)height + (double)this.offset.getValueNumeric(), e.posZ - (double)width - (double)this.offset.getValueNumeric()));
        }
    }
}

