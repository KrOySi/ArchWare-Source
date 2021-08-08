/*
 * Decompiled with CFR 0.150.
 */
package com.mysql.cj.protocol;

import com.mysql.cj.ServerVersion;

public interface ServerCapabilities {
    public int getCapabilityFlags();

    public void setCapabilityFlags(int var1);

    public ServerVersion getServerVersion();

    public void setServerVersion(ServerVersion var1);

    public boolean serverSupportsFracSecs();
}

