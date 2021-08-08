/*
 * Decompiled with CFR 0.150.
 */
package com.mysql.cj.protocol.x;

import com.mysql.cj.protocol.ProtocolEntityFactory;
import com.mysql.cj.protocol.x.XMessage;
import com.mysql.cj.protocol.x.XProtocolRow;
import com.mysql.cj.x.protobuf.MysqlxResultset;

public class XProtocolRowFactory
implements ProtocolEntityFactory<XProtocolRow, XMessage> {
    @Override
    public XProtocolRow createFromMessage(XMessage message) {
        return new XProtocolRow((MysqlxResultset.Row)MysqlxResultset.Row.class.cast((Object)message.getMessage()));
    }
}

