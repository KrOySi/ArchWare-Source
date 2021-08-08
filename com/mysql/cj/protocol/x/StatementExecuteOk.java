/*
 * Decompiled with CFR 0.150.
 */
package com.mysql.cj.protocol.x;

import com.mysql.cj.protocol.ProtocolEntity;
import com.mysql.cj.protocol.Warning;
import com.mysql.cj.xdevapi.Result;
import com.mysql.cj.xdevapi.WarningImpl;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

public class StatementExecuteOk
implements ProtocolEntity,
Result {
    private long rowsAffected = 0L;
    private Long lastInsertId = null;
    private List<String> generatedIds;
    private List<Warning> warnings;

    public StatementExecuteOk() {
        this.generatedIds = Collections.emptyList();
        this.warnings = new ArrayList<Warning>();
    }

    public StatementExecuteOk(long rowsAffected, Long lastInsertId, List<String> generatedIds, List<Warning> warnings) {
        this.rowsAffected = rowsAffected;
        this.lastInsertId = lastInsertId;
        this.generatedIds = Collections.unmodifiableList(generatedIds);
        this.warnings = warnings;
    }

    @Override
    public long getAffectedItemsCount() {
        return this.rowsAffected;
    }

    public Long getLastInsertId() {
        return this.lastInsertId;
    }

    public List<String> getGeneratedIds() {
        return this.generatedIds;
    }

    @Override
    public int getWarningsCount() {
        return this.warnings.size();
    }

    @Override
    public Iterator<com.mysql.cj.xdevapi.Warning> getWarnings() {
        return this.warnings.stream().map(w -> new WarningImpl((Warning)w)).collect(Collectors.toList()).iterator();
    }
}

