/*
 * Decompiled with CFR 0.150.
 */
package com.mysql.cj.protocol;

public interface PacketReceivedTimeHolder {
    default public long getLastPacketReceivedTime() {
        return 0L;
    }
}

