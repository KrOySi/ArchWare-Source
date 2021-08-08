/*
 * Decompiled with CFR 0.150.
 */
package com.mysql.cj.protocol;

import com.mysql.cj.conf.PropertySet;
import com.mysql.cj.protocol.ServerSession;
import com.mysql.cj.protocol.SocketConnection;
import com.mysql.cj.protocol.SocketMetadata;
import java.io.Closeable;
import java.io.IOException;

public interface SocketFactory
extends SocketMetadata {
    public <T extends Closeable> T connect(String var1, int var2, PropertySet var3, int var4) throws IOException;

    default public void beforeHandshake() throws IOException {
    }

    public <T extends Closeable> T performTlsHandshake(SocketConnection var1, ServerSession var2) throws IOException;

    default public void afterHandshake() throws IOException {
    }
}

