/*
 * Decompiled with CFR 0.150.
 */
package com.mysql.cj.protocol.x;

import com.mysql.cj.protocol.ProtocolEntityFactory;
import com.mysql.cj.protocol.x.Ok;
import com.mysql.cj.protocol.x.XMessage;

public class OkFactory
implements ProtocolEntityFactory<Ok, XMessage> {
    @Override
    public Ok createFromMessage(XMessage message) {
        return new Ok();
    }
}

