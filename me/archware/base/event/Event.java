/*
 * Decompiled with CFR 0.150.
 */
package me.archware.base.event;

import java.lang.reflect.InvocationTargetException;
import me.archware.ArchWare;
import me.archware.base.event.ArrayHelper;
import me.archware.base.event.Data;
import net.minecraft.client.Minecraft;

public abstract class Event {
    public Minecraft mc = Minecraft.getMinecraft();
    private boolean cancelled;

    public Event call() {
        this.cancelled = false;
        Event.call(this);
        return this;
    }

    public void cancel() {
        this.cancelled = true;
    }

    public boolean isCancelled() {
        return this.cancelled;
    }

    public void setCancelled(boolean cancelled) {
        this.cancelled = cancelled;
    }

    private static final void call(Event event) {
        ArrayHelper<Data> dataList = ArchWare.eventManager.get(event.getClass());
        if (dataList != null) {
            for (Data data : dataList) {
                try {
                    data.target.invoke(data.source, event);
                }
                catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
                catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static enum State {
        PRE("PRE", 0),
        POST("POST", 1);


        private State(String string2, int number) {
        }
    }
}

