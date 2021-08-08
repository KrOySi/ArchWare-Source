/*
 * Decompiled with CFR 0.150.
 */
package com.mysql.cj.protocol.x;

import com.mysql.cj.protocol.ProtocolEntityFactory;
import com.mysql.cj.protocol.x.StatementExecuteOk;
import com.mysql.cj.protocol.x.XMessage;

public class StatementExecuteOkFactory
implements ProtocolEntityFactory<StatementExecuteOk, XMessage> {
    @Override
    public StatementExecuteOk createFromMessage(XMessage message) {
        return new StatementExecuteOk();
    }
}

