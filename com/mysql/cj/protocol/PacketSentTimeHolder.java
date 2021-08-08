/*
 * Decompiled with CFR 0.150.
 */
package com.mysql.cj.protocol;

public interface PacketSentTimeHolder {
    default public long getLastPacketSentTime() {
        return 0L;
    }

    default public long getPreviousPacketSentTime() {
        return 0L;
    }
}

