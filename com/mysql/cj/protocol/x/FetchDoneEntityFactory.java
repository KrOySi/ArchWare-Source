/*
 * Decompiled with CFR 0.150.
 */
package com.mysql.cj.protocol.x;

import com.mysql.cj.protocol.ProtocolEntityFactory;
import com.mysql.cj.protocol.x.FetchDoneEntity;
import com.mysql.cj.protocol.x.XMessage;

public class FetchDoneEntityFactory
implements ProtocolEntityFactory<FetchDoneEntity, XMessage> {
    @Override
    public FetchDoneEntity createFromMessage(XMessage message) {
        return new FetchDoneEntity();
    }
}

