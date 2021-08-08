/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  javafx.animation.Interpolator
 */
package me.archware.notifications;

import java.awt.Color;
import java.awt.Font;
import javafx.animation.Interpolator;
import me.archware.base.clickgui.util.FontUtil;
import me.archware.impl.utils.RenderUtil;
import me.archware.impl.utils.font.CustomFontRenderer;
import me.archware.notifications.Notification;
import me.archware.notifications.NotificationManager;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;

public class NotificationRenderer {
    private static final Minecraft mc = Minecraft.getMinecraft();
    private static final CustomFontRenderer font = new CustomFontRenderer(new Font("Arial", 0, 15), true, true);

    public static void render(float partialTicks, ScaledResolution scaledResolution) {
        for (int i = 0; i < NotificationManager.getQueue().size(); ++i) {
            Color color;
            Notification notification = NotificationManager.getQueue().get(i);
            switch (notification.getNotificationType()) {
                case WARNING: {
                    color = Color.ORANGE;
                    break;
                }
                case ERROR: {
                    color = Color.RED;
                    break;
                }
                default: {
                    color = Color.GREEN;
                }
            }
            notification.setPosY(Interpolator.EASE_OUT.interpolate(notification.getPosY(), (double)(i == 0 ? 22 : 22 + i * 22), 10.0 / (double)Minecraft.getDebugFPS()));
            RenderUtil.drawRect((double)(scaledResolution.getScaledWidth() - FontUtil.getStringWidth(notification.getDescription()) - 6) - notification.getPosX(), (double)new ScaledResolution(mc).getScaledHeight() - notification.getPosY(), (double)FontUtil.getStringWidth(notification.getDescription()), 10.0, new Color(13, 13, 13).hashCode());
            RenderUtil.drawRect((double)(scaledResolution.getScaledWidth() - FontUtil.getStringWidth(notification.getDescription()) - 6) - notification.getPosX(), (double)new ScaledResolution(mc).getScaledHeight() - notification.getPosY() + 9.0, notification.getWidth(), 1.0, RenderUtil.fade(color, 1, 1).hashCode());
            font.drawString(notification.getDescription(), (double)(scaledResolution.getScaledWidth() - FontUtil.getStringWidth(notification.getDescription())) - 2.5 - notification.getPosX(), (double)new ScaledResolution(mc).getScaledHeight() - notification.getPosY() + 3.0, -1);
            if (notification.getWidth() <= (double)FontUtil.getStringWidth(notification.getDescription())) {
                notification.setWidth(notification.getWidth() + 12.0 / (double)Minecraft.getDebugFPS());
                continue;
            }
            notification.setPosX((float)Interpolator.EASE_OUT.interpolate(notification.getPosX(), (double)(-FontUtil.getStringWidth(notification.getDescription()) - 6), 10.0 / (double)Minecraft.getDebugFPS()));
            if (!(notification.getPosX() < (double)(-FontUtil.getStringWidth(notification.getDescription())))) continue;
            NotificationManager.getQueue().remove(notification);
        }
    }
}

