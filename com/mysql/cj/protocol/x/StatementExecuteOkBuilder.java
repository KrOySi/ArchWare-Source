/*
 * Decompiled with CFR 0.150.
 */
package com.mysql.cj.protocol.x;

import com.mysql.cj.exceptions.ExceptionFactory;
import com.mysql.cj.exceptions.WrongArgumentException;
import com.mysql.cj.protocol.ProtocolEntity;
import com.mysql.cj.protocol.ResultBuilder;
import com.mysql.cj.protocol.Warning;
import com.mysql.cj.protocol.x.FetchDoneEntity;
import com.mysql.cj.protocol.x.Notice;
import com.mysql.cj.protocol.x.StatementExecuteOk;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class StatementExecuteOkBuilder
implements ResultBuilder<StatementExecuteOk> {
    private long rowsAffected = 0L;
    private Long lastInsertId = null;
    private List<String> generatedIds = Collections.emptyList();
    private List<Warning> warnings = new ArrayList<Warning>();

    @Override
    public boolean addProtocolEntity(ProtocolEntity entity) {
        if (entity instanceof Notice) {
            this.addNotice((Notice)entity);
            return false;
        }
        if (entity instanceof FetchDoneEntity) {
            return false;
        }
        if (entity instanceof StatementExecuteOk) {
            return true;
        }
        throw ExceptionFactory.createException(WrongArgumentException.class, "Unexpected protocol entity " + entity);
    }

    @Override
    public StatementExecuteOk build() {
        return new StatementExecuteOk(this.rowsAffected, this.lastInsertId, this.generatedIds, this.warnings);
    }

    private void addNotice(Notice notice) {
        if (notice instanceof Notice.XWarning) {
            this.warnings.add((Notice.XWarning)notice);
        } else if (notice instanceof Notice.XSessionStateChanged) {
            switch (((Notice.XSessionStateChanged)notice).getParamType()) {
                case 3: {
                    this.lastInsertId = ((Notice.XSessionStateChanged)notice).getValue().getVUnsignedInt();
                    break;
                }
                case 4: {
                    this.rowsAffected = ((Notice.XSessionStateChanged)notice).getValue().getVUnsignedInt();
                    break;
                }
                case 12: {
                    this.generatedIds = ((Notice.XSessionStateChanged)notice).getValueList().stream().map(v -> v.getVOctets().getValue().toStringUtf8()).collect(Collectors.toList());
                    break;
                }
            }
        }
    }
}

