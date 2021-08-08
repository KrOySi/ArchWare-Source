/*
 * Decompiled with CFR 0.150.
 */
package me.archware.impl.modules.render;

import java.util.ArrayList;
import java.util.List;
import me.archware.base.event.EventTarget;
import me.archware.base.module.Category;
import me.archware.base.module.Module;
import me.archware.impl.events.EventRender3D;
import me.archware.impl.events.EventUpdate;
import me.archware.notifications.Notification;
import me.archware.notifications.NotificationManager;
import me.archware.notifications.NotificationType;
import net.minecraft.util.text.TextFormatting;

public class DeathCoordinates
extends Module {
    public static List<double[]> cords = new ArrayList<double[]>();

    public DeathCoordinates() {
        super("DeathCoordinates", Category.RENDER);
    }

    @EventTarget
    public void onUpdate(EventUpdate event) {
        if (this.mc.player.isDead) {
            if (!cords.contains(new double[]{this.mc.player.posX, this.mc.player.posY, this.mc.player.posZ})) {
                cords.add(new double[]{this.mc.player.posX, this.mc.player.posY, this.mc.player.posZ});
                NotificationManager.addNotification(new Notification((Object)((Object)TextFormatting.GREEN) + "Dead coordinates successfully saved: " + (Object)((Object)TextFormatting.YELLOW) + "(" + this.mc.player.posX + ":" + this.mc.player.posY + ":" + this.mc.player.posZ + ")", NotificationType.OK));
            }
        }
    }

    @EventTarget
    public void onRender(EventRender3D event) {
    }
}

