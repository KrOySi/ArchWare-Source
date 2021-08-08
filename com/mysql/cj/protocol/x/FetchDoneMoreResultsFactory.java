/*
 * Decompiled with CFR 0.150.
 */
package com.mysql.cj.protocol.x;

import com.mysql.cj.protocol.ProtocolEntityFactory;
import com.mysql.cj.protocol.x.FetchDoneMoreResults;
import com.mysql.cj.protocol.x.XMessage;

public class FetchDoneMoreResultsFactory
implements ProtocolEntityFactory<FetchDoneMoreResults, XMessage> {
    @Override
    public FetchDoneMoreResults createFromMessage(XMessage message) {
        return new FetchDoneMoreResults();
    }
}

