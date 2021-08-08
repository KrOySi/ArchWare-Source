/*
 * Decompiled with CFR 0.150.
 */
package me.archware.impl.modules.misc;

import me.archware.ArchWare;
import me.archware.base.event.EventTarget;
import me.archware.base.module.Category;
import me.archware.base.module.Module;
import me.archware.impl.events.EventPacketReceive;
import me.archware.notifications.Notification;
import me.archware.notifications.NotificationManager;
import me.archware.notifications.NotificationType;
import net.minecraft.network.play.server.SPacketPlayerPosLook;

public class FlagDetector
extends Module {
    private int moduleCount;

    public FlagDetector() {
        super("FlagDetector", Category.MISC);
    }

    @EventTarget
    public void onPacket(EventPacketReceive event) {
        if (event.getPacket() instanceof SPacketPlayerPosLook) {
            if (this.mc.player.ticksExisted < 4) {
                NotificationManager.addNotification(new Notification("New world detected", NotificationType.WARNING));
            } else {
                this.moduleCount = 0;
                if (ArchWare.moduleManager.getModuleByName("Flight").isToggled()) {
                    ++this.moduleCount;
                    ArchWare.moduleManager.getModuleByName("Flight").toggle();
                }
                if (ArchWare.moduleManager.getModuleByName("Speed").isToggled()) {
                    ++this.moduleCount;
                    ArchWare.moduleManager.getModuleByName("Speed").toggle();
                }
                if (ArchWare.moduleManager.getModuleByName("Jesus").isToggled()) {
                    ++this.moduleCount;
                    ArchWare.moduleManager.getModuleByName("Jesus").toggle();
                }
                if (ArchWare.moduleManager.getModuleByName("TargetStrafe").isToggled()) {
                    ++this.moduleCount;
                    ArchWare.moduleManager.getModuleByName("TargetStrafe").toggle();
                }
                NotificationManager.addNotification(new Notification(this.moduleCount + " modules will be disabled due to lagback check.", NotificationType.WARNING));
            }
        }
    }
}

