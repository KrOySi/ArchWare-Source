/*
 * Decompiled with CFR 0.150.
 */
package me.archware.impl.modules.combat;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import me.archware.base.event.EventTarget;
import me.archware.base.module.Category;
import me.archware.base.module.Module;
import me.archware.base.setting.StringValue;
import me.archware.impl.events.EventPreUpdate;
import me.archware.notifications.Notification;
import me.archware.notifications.NotificationManager;
import me.archware.notifications.NotificationType;
import net.minecraft.client.entity.EntityOtherPlayerMP;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;

public class AntiBot
extends Module {
    StringValue mode = new StringValue("Mode", "AntiBotMode", this, "Invisible", new String[]{"Invisible", "Matrix", "Mineland"});
    private List<String> entities = new ArrayList<String>();

    public AntiBot() {
        super("AntiBot", Category.COMBAT);
    }

    @EventTarget
    public void onUpdate(EventPreUpdate event) {
        this.setSuffix(this.mode.getValueString());
        switch (this.mode.getValueString()) {
            case "Invisible": {
                for (Entity e : this.mc.world.loadedEntityList) {
                    if (e == this.mc.player || !e.isInvisible()) continue;
                    this.mc.world.removeEntity(e);
                }
                break;
            }
            case "Matrix": {
                for (Entity e : this.mc.world.loadedEntityList) {
                    if (e.ticksExisted >= 5 || !(e instanceof EntityOtherPlayerMP) || ((EntityOtherPlayerMP)e).hurtTime <= 0 || !(this.mc.player.getDistanceToEntity(e) <= 25.0f) || this.mc.getConnection().getPlayerInfo(e.getUniqueID()).getResponseTime() == 0 || ((EntityOtherPlayerMP)e).getLastDamageSource() != null) continue;
                    this.mc.world.removeEntity(e);
                    NotificationManager.addNotification(new Notification(e.getName() + " removed from your world", NotificationType.WARNING));
                }
                break;
            }
            case "Mineland": {
                for (Entity e : this.mc.world.loadedEntityList) {
                    if (e.ticksExisted >= 5 || !(this.mc.player.getDistanceToEntity(e) <= 25.0f) || !(e instanceof EntityLivingBase) || this.mc.getConnection().getPlayerInfo(e.getUniqueID()).getResponseTime() == 0) continue;
                    this.mc.world.loadedEntityList.forEach(entity -> this.entities.add(entity.getName()));
                    if (Collections.frequency(this.entities, e.getName()) < 2) continue;
                    this.mc.world.removeEntity(e);
                    NotificationManager.addNotification(new Notification(e.getName() + " removed from your world", NotificationType.WARNING));
                }
                this.entities.clear();
            }
        }
    }

    @Override
    public void onEnable() {
        super.onEnable();
    }
}

