/*
 * Decompiled with CFR 0.150.
 */
package me.archware.notifications;

import java.util.ArrayList;
import java.util.List;
import me.archware.notifications.Notification;

public class NotificationManager {
    private static List<Notification> queue = new ArrayList<Notification>();

    public static void addNotification(Notification notification) {
        queue.add(notification);
    }

    public static void removeNotification(Notification notification) {
        queue.remove(notification);
    }

    public static List<Notification> getQueue() {
        return queue;
    }
}

