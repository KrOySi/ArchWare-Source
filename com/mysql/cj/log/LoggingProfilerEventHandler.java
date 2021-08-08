/*
 * Decompiled with CFR 0.150.
 */
package com.mysql.cj.log;

import com.mysql.cj.Constants;
import com.mysql.cj.Query;
import com.mysql.cj.Session;
import com.mysql.cj.log.Log;
import com.mysql.cj.log.ProfilerEvent;
import com.mysql.cj.log.ProfilerEventHandler;
import com.mysql.cj.log.ProfilerEventImpl;
import com.mysql.cj.protocol.Resultset;

public class LoggingProfilerEventHandler
implements ProfilerEventHandler {
    private Log logger;

    @Override
    public void consumeEvent(ProfilerEvent evt) {
        switch (evt.getEventType()) {
            case 0: {
                this.logger.logWarn(evt);
                break;
            }
            default: {
                this.logger.logInfo(evt);
            }
        }
    }

    @Override
    public void destroy() {
        this.logger = null;
    }

    @Override
    public void init(Log log) {
        this.logger = log;
    }

    @Override
    public void processEvent(byte eventType, Session session, Query query, Resultset resultSet, long eventDuration, Throwable eventCreationPoint, String message) {
        this.consumeEvent(new ProfilerEventImpl(eventType, session == null ? "" : session.getHostInfo().getHost(), session == null ? "" : session.getHostInfo().getDatabase(), session == null ? -1L : session.getThreadId(), query == null ? -1 : query.getId(), resultSet == null ? -1 : resultSet.getResultId(), eventDuration, session == null ? Constants.MILLIS_I18N : session.getQueryTimingUnits(), eventCreationPoint, message));
    }
}

