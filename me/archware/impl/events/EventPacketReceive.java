/*
 * Decompiled with CFR 0.150.
 */
package me.archware.impl.events;

import me.archware.base.event.Event;
import net.minecraft.network.Packet;

public class EventPacketReceive
extends Event {
    Packet packet;

    public EventPacketReceive(Packet packet) {
        this.packet = packet;
    }

    public Packet getPacket() {
        return this.packet;
    }

    public void setPacket(Packet packet) {
        this.packet = packet;
    }
}

