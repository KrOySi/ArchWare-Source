/*
 * Decompiled with CFR 0.150.
 */
package com.mysql.cj;

import com.mysql.cj.AbstractPreparedQuery;
import com.mysql.cj.BindValue;
import com.mysql.cj.ClientPreparedQueryBindValue;
import com.mysql.cj.ClientPreparedQueryBindings;
import com.mysql.cj.NativeSession;

public class ClientPreparedQuery
extends AbstractPreparedQuery<ClientPreparedQueryBindings> {
    public ClientPreparedQuery(NativeSession sess) {
        super(sess);
    }

    @Override
    protected long[] computeMaxParameterSetSizeAndBatchSize(int numBatchedArgs) {
        long sizeOfEntireBatch = 0L;
        long maxSizeOfParameterSet = 0L;
        for (int i = 0; i < numBatchedArgs; ++i) {
            ClientPreparedQueryBindings qBindings = (ClientPreparedQueryBindings)this.batchedArgs.get(i);
            BindValue[] bindValues = qBindings.getBindValues();
            long sizeOfParameterSet = 0L;
            for (int j = 0; j < bindValues.length; ++j) {
                if (!bindValues[j].isNull()) {
                    if (bindValues[j].isStream()) {
                        long streamLength = bindValues[j].getStreamLength();
                        if (streamLength != -1L) {
                            sizeOfParameterSet += streamLength * 2L;
                            continue;
                        }
                        int paramLength = ((ClientPreparedQueryBindValue[])qBindings.getBindValues())[j].getByteValue().length;
                        sizeOfParameterSet += (long)paramLength;
                        continue;
                    }
                    sizeOfParameterSet += (long)((ClientPreparedQueryBindValue[])qBindings.getBindValues())[j].getByteValue().length;
                    continue;
                }
                sizeOfParameterSet += 4L;
            }
            sizeOfParameterSet = this.parseInfo.getValuesClause() != null ? (sizeOfParameterSet += (long)(this.parseInfo.getValuesClause().length() + 1)) : (sizeOfParameterSet += (long)(this.originalSql.length() + 1));
            sizeOfEntireBatch += sizeOfParameterSet;
            if (sizeOfParameterSet <= maxSizeOfParameterSet) continue;
            maxSizeOfParameterSet = sizeOfParameterSet;
        }
        return new long[]{maxSizeOfParameterSet, sizeOfEntireBatch};
    }
}

