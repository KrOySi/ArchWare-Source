/*
 * Decompiled with CFR 0.150.
 */
package com.mysql.cj;

import com.mysql.cj.CancelQueryTask;
import com.mysql.cj.CancelQueryTaskImpl;
import com.mysql.cj.NativeSession;
import com.mysql.cj.Query;
import com.mysql.cj.conf.PropertyKey;
import com.mysql.cj.conf.RuntimeProperty;
import com.mysql.cj.exceptions.CJException;
import com.mysql.cj.exceptions.CJTimeoutException;
import com.mysql.cj.exceptions.ExceptionFactory;
import com.mysql.cj.exceptions.OperationCancelledException;
import com.mysql.cj.protocol.Message;
import com.mysql.cj.protocol.ProtocolEntityFactory;
import com.mysql.cj.protocol.Resultset;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.TimerTask;
import java.util.concurrent.atomic.AtomicBoolean;

public abstract class AbstractQuery
implements Query {
    static int statementCounter = 1;
    public NativeSession session = null;
    protected int statementId;
    protected RuntimeProperty<Integer> maxAllowedPacket;
    protected String charEncoding = null;
    protected Object cancelTimeoutMutex = new Object();
    private Query.CancelStatus cancelStatus = Query.CancelStatus.NOT_CANCELED;
    protected int timeoutInMillis = 0;
    protected List<Object> batchedArgs;
    protected Resultset.Type resultSetType = Resultset.Type.FORWARD_ONLY;
    protected int fetchSize = 0;
    protected final AtomicBoolean statementExecuting = new AtomicBoolean(false);
    protected String currentDb = null;
    protected boolean clearWarningsCalled = false;
    private long executeTime = -1L;

    public AbstractQuery(NativeSession sess) {
        ++statementCounter;
        this.session = sess;
        this.maxAllowedPacket = sess.getPropertySet().getIntegerProperty(PropertyKey.maxAllowedPacket);
        this.charEncoding = sess.getPropertySet().getStringProperty(PropertyKey.characterEncoding).getValue();
    }

    @Override
    public int getId() {
        return this.statementId;
    }

    @Override
    public void setCancelStatus(Query.CancelStatus cs) {
        this.cancelStatus = cs;
    }

    @Override
    public long getExecuteTime() {
        return this.executeTime;
    }

    @Override
    public void setExecuteTime(long executeTime) {
        this.executeTime = executeTime;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void checkCancelTimeout() {
        Object object = this.cancelTimeoutMutex;
        synchronized (object) {
            if (this.cancelStatus != Query.CancelStatus.NOT_CANCELED) {
                CJException cause = this.cancelStatus == Query.CancelStatus.CANCELED_BY_TIMEOUT ? new CJTimeoutException() : new OperationCancelledException();
                this.resetCancelledState();
                throw cause;
            }
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void resetCancelledState() {
        Object object = this.cancelTimeoutMutex;
        synchronized (object) {
            this.cancelStatus = Query.CancelStatus.NOT_CANCELED;
        }
    }

    @Override
    public <T extends Resultset, M extends Message> ProtocolEntityFactory<T, M> getResultSetFactory() {
        return null;
    }

    @Override
    public NativeSession getSession() {
        return this.session;
    }

    @Override
    public Object getCancelTimeoutMutex() {
        return this.cancelTimeoutMutex;
    }

    @Override
    public void closeQuery() {
        this.session = null;
    }

    @Override
    public void addBatch(Object batch) {
        if (this.batchedArgs == null) {
            this.batchedArgs = new ArrayList<Object>();
        }
        this.batchedArgs.add(batch);
    }

    @Override
    public List<Object> getBatchedArgs() {
        return this.batchedArgs == null ? null : Collections.unmodifiableList(this.batchedArgs);
    }

    @Override
    public void clearBatchedArgs() {
        if (this.batchedArgs != null) {
            this.batchedArgs.clear();
        }
    }

    @Override
    public int getResultFetchSize() {
        return this.fetchSize;
    }

    @Override
    public void setResultFetchSize(int fetchSize) {
        this.fetchSize = fetchSize;
    }

    @Override
    public Resultset.Type getResultType() {
        return this.resultSetType;
    }

    @Override
    public void setResultType(Resultset.Type resultSetType) {
        this.resultSetType = resultSetType;
    }

    @Override
    public int getTimeoutInMillis() {
        return this.timeoutInMillis;
    }

    @Override
    public void setTimeoutInMillis(int timeoutInMillis) {
        this.timeoutInMillis = timeoutInMillis;
    }

    @Override
    public CancelQueryTask startQueryTimer(Query stmtToCancel, int timeout) {
        if (this.session.getPropertySet().getBooleanProperty(PropertyKey.enableQueryTimeouts).getValue().booleanValue() && timeout != 0) {
            CancelQueryTaskImpl timeoutTask = new CancelQueryTaskImpl(stmtToCancel);
            this.session.getCancelTimer().schedule((TimerTask)timeoutTask, timeout);
            return timeoutTask;
        }
        return null;
    }

    @Override
    public void stopQueryTimer(CancelQueryTask timeoutTask, boolean rethrowCancelReason, boolean checkCancelTimeout) {
        if (timeoutTask != null) {
            timeoutTask.cancel();
            if (rethrowCancelReason && timeoutTask.getCaughtWhileCancelling() != null) {
                Throwable t = timeoutTask.getCaughtWhileCancelling();
                throw ExceptionFactory.createException(t.getMessage(), t);
            }
            this.session.getCancelTimer().purge();
            if (checkCancelTimeout) {
                this.checkCancelTimeout();
            }
        }
    }

    @Override
    public AtomicBoolean getStatementExecuting() {
        return this.statementExecuting;
    }

    @Override
    public String getCurrentDatabase() {
        return this.currentDb;
    }

    @Override
    public void setCurrentDatabase(String currentDb) {
        this.currentDb = currentDb;
    }

    @Override
    public boolean isClearWarningsCalled() {
        return this.clearWarningsCalled;
    }

    @Override
    public void setClearWarningsCalled(boolean clearWarningsCalled) {
        this.clearWarningsCalled = clearWarningsCalled;
    }

    @Override
    public void statementBegins() {
        this.clearWarningsCalled = false;
        this.statementExecuting.set(true);
    }
}

