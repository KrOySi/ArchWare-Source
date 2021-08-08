/*
 * Decompiled with CFR 0.150.
 */
package com.mysql.cj;

import com.mysql.cj.DataStoreMetadata;
import com.mysql.cj.MessageBuilder;
import com.mysql.cj.QueryResult;
import com.mysql.cj.conf.HostInfo;
import com.mysql.cj.conf.PropertySet;
import com.mysql.cj.exceptions.CJOperationNotSupportedException;
import com.mysql.cj.exceptions.ExceptionFactory;
import com.mysql.cj.exceptions.ExceptionInterceptor;
import com.mysql.cj.log.Log;
import com.mysql.cj.log.ProfilerEventHandler;
import com.mysql.cj.protocol.Message;
import com.mysql.cj.protocol.ResultBuilder;
import com.mysql.cj.protocol.ServerSession;
import com.mysql.cj.result.Row;
import java.net.SocketAddress;
import java.util.concurrent.CompletableFuture;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collector;

public interface Session {
    public PropertySet getPropertySet();

    public <M extends Message> MessageBuilder<M> getMessageBuilder();

    public void changeUser(String var1, String var2, String var3);

    public ExceptionInterceptor getExceptionInterceptor();

    public void setExceptionInterceptor(ExceptionInterceptor var1);

    public void quit();

    public void forceClose();

    public boolean versionMeetsMinimum(int var1, int var2, int var3);

    public long getThreadId();

    public boolean isSetNeededForAutoCommitMode(boolean var1);

    public Log getLog();

    public ProfilerEventHandler getProfilerEventHandler();

    public HostInfo getHostInfo();

    public String getQueryTimingUnits();

    public ServerSession getServerSession();

    public boolean isSSLEstablished();

    public SocketAddress getRemoteSocketAddress();

    public String getProcessHost();

    public void addListener(SessionEventListener var1);

    public void removeListener(SessionEventListener var1);

    public boolean isClosed();

    public String getIdentifierQuoteString();

    public DataStoreMetadata getDataStoreMetadata();

    default public <M extends Message, R, RES> RES query(M message, Predicate<Row> rowFilter, Function<Row, R> rowMapper, Collector<R, ?, RES> collector) {
        throw ExceptionFactory.createException(CJOperationNotSupportedException.class, "Not supported");
    }

    default public <M extends Message, R extends QueryResult> R query(M message, ResultBuilder<R> resultBuilder) {
        throw ExceptionFactory.createException(CJOperationNotSupportedException.class, "Not supported");
    }

    default public <M extends Message, R extends QueryResult> CompletableFuture<R> queryAsync(M message, ResultBuilder<R> resultBuilder) {
        throw ExceptionFactory.createException(CJOperationNotSupportedException.class, "Not supported");
    }

    public static interface SessionEventListener {
        public void handleNormalClose();

        public void handleReconnect();

        public void handleCleanup(Throwable var1);
    }
}

