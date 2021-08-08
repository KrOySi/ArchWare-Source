/*
 * Decompiled with CFR 0.150.
 */
package com.mysql.cj.log;

public interface ProfilerEvent {
    public static final byte TYPE_USAGE = 0;
    public static final byte TYPE_OBJECT_CREATION = 1;
    public static final byte TYPE_PREPARE = 2;
    public static final byte TYPE_QUERY = 3;
    public static final byte TYPE_EXECUTE = 4;
    public static final byte TYPE_FETCH = 5;
    public static final byte TYPE_SLOW_QUERY = 6;
    public static final byte NA = -1;

    public byte getEventType();

    public String getHostName();

    public String getDatabase();

    public long getConnectionId();

    public int getStatementId();

    public int getResultSetId();

    public long getEventCreationTime();

    public long getEventDuration();

    public String getDurationUnits();

    public String getEventCreationPointAsString();

    public String getMessage();

    public byte[] pack();
}

