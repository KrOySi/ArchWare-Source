/*
 * Decompiled with CFR 0.150.
 */
package com.mysql.cj.protocol.x;

import com.mysql.cj.protocol.ProtocolEntityFactory;
import com.mysql.cj.protocol.x.Notice;
import com.mysql.cj.protocol.x.XMessage;

public class NoticeFactory
implements ProtocolEntityFactory<Notice, XMessage> {
    @Override
    public Notice createFromMessage(XMessage message) {
        return Notice.getInstance(message);
    }
}

