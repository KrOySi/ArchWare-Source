/*
 * Decompiled with CFR 0.150.
 */
package com.mysql.cj.protocol.x;

import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class ContinuousOutputStream
extends FilterOutputStream {
    protected ContinuousOutputStream(OutputStream out) {
        super(out);
    }

    @Override
    public void close() throws IOException {
        this.flush();
    }
}

