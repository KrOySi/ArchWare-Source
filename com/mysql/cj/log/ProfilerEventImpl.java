/*
 * Decompiled with CFR 0.150.
 */
package com.mysql.cj.log;

import com.mysql.cj.log.ProfilerEvent;
import com.mysql.cj.util.LogUtils;
import com.mysql.cj.util.StringUtils;
import java.util.Date;

public class ProfilerEventImpl
implements ProfilerEvent {
    private byte eventType;
    private String hostName;
    private String database;
    private long connectionId;
    private int statementId;
    private int resultSetId;
    private long eventCreationTime;
    private long eventDuration;
    private String durationUnits;
    private String eventCreationPointDesc;
    private String message;

    public ProfilerEventImpl(byte eventType, String hostName, String db, long connectionId, int statementId, int resultSetId, long eventDuration, String durationUnits, Throwable eventCreationPoint, String message) {
        this(eventType, hostName, db, connectionId, statementId, resultSetId, System.currentTimeMillis(), eventDuration, durationUnits, LogUtils.findCallingClassAndMethod(eventCreationPoint), message);
    }

    private ProfilerEventImpl(byte eventType, String hostName, String db, long connectionId, int statementId, int resultSetId, long eventCreationTime, long eventDuration, String durationUnits, String eventCreationPointDesc, String message) {
        this.eventType = eventType;
        this.hostName = hostName == null ? "" : hostName;
        this.database = db == null ? "" : db;
        this.connectionId = connectionId;
        this.statementId = statementId;
        this.resultSetId = resultSetId;
        this.eventCreationTime = eventCreationTime;
        this.eventDuration = eventDuration;
        this.durationUnits = durationUnits == null ? "" : durationUnits;
        this.eventCreationPointDesc = eventCreationPointDesc == null ? "" : eventCreationPointDesc;
        this.message = message == null ? "" : message;
    }

    @Override
    public byte getEventType() {
        return this.eventType;
    }

    @Override
    public String getHostName() {
        return this.hostName;
    }

    @Override
    public String getDatabase() {
        return this.database;
    }

    @Override
    public long getConnectionId() {
        return this.connectionId;
    }

    @Override
    public int getStatementId() {
        return this.statementId;
    }

    @Override
    public int getResultSetId() {
        return this.resultSetId;
    }

    @Override
    public long getEventCreationTime() {
        return this.eventCreationTime;
    }

    @Override
    public long getEventDuration() {
        return this.eventDuration;
    }

    @Override
    public String getDurationUnits() {
        return this.durationUnits;
    }

    @Override
    public String getEventCreationPointAsString() {
        return this.eventCreationPointDesc;
    }

    @Override
    public String getMessage() {
        return this.message;
    }

    public String toString() {
        StringBuilder buf = new StringBuilder();
        buf.append("[");
        switch (this.getEventType()) {
            case 4: {
                buf.append("EXECUTE");
                break;
            }
            case 5: {
                buf.append("FETCH");
                break;
            }
            case 1: {
                buf.append("CONSTRUCT");
                break;
            }
            case 2: {
                buf.append("PREPARE");
                break;
            }
            case 3: {
                buf.append("QUERY");
                break;
            }
            case 0: {
                buf.append("USAGE ADVISOR");
                break;
            }
            case 6: {
                buf.append("SLOW QUERY");
                break;
            }
            default: {
                buf.append("UNKNOWN");
            }
        }
        buf.append("] ");
        buf.append(this.message);
        buf.append(" [Created on: ");
        buf.append(new Date(this.eventCreationTime));
        buf.append(", duration: ");
        buf.append(this.eventDuration);
        buf.append(", connection-id: ");
        buf.append(this.connectionId);
        buf.append(", statement-id: ");
        buf.append(this.statementId);
        buf.append(", resultset-id: ");
        buf.append(this.resultSetId);
        buf.append(",");
        buf.append(this.eventCreationPointDesc);
        buf.append("]");
        return buf.toString();
    }

    public static ProfilerEvent unpack(byte[] buf) {
        int pos = 0;
        byte eventType = buf[pos++];
        byte[] host = ProfilerEventImpl.readBytes(buf, pos);
        byte[] db = ProfilerEventImpl.readBytes(buf, pos += 4 + host.length);
        long connectionId = ProfilerEventImpl.readLong(buf, pos += 4 + db.length);
        int statementId = ProfilerEventImpl.readInt(buf, pos += 8);
        int resultSetId = ProfilerEventImpl.readInt(buf, pos += 4);
        long eventCreationTime = ProfilerEventImpl.readLong(buf, pos += 4);
        long eventDuration = ProfilerEventImpl.readLong(buf, pos += 8);
        byte[] eventDurationUnits = ProfilerEventImpl.readBytes(buf, pos += 8);
        byte[] eventCreationAsBytes = ProfilerEventImpl.readBytes(buf, pos += 4 + eventDurationUnits.length);
        byte[] message = ProfilerEventImpl.readBytes(buf, pos += 4 + eventCreationAsBytes.length);
        pos += 4 + message.length;
        return new ProfilerEventImpl(eventType, StringUtils.toString(host, "ISO8859_1"), StringUtils.toString(db, "ISO8859_1"), connectionId, statementId, resultSetId, eventCreationTime, eventDuration, StringUtils.toString(eventDurationUnits, "ISO8859_1"), StringUtils.toString(eventCreationAsBytes, "ISO8859_1"), StringUtils.toString(message, "ISO8859_1"));
    }

    @Override
    public byte[] pack() {
        byte[] hostNameAsBytes = StringUtils.getBytes(this.hostName, "ISO8859_1");
        byte[] dbAsBytes = StringUtils.getBytes(this.database, "ISO8859_1");
        byte[] durationUnitsAsBytes = StringUtils.getBytes(this.durationUnits, "ISO8859_1");
        byte[] eventCreationAsBytes = StringUtils.getBytes(this.eventCreationPointDesc, "ISO8859_1");
        byte[] messageAsBytes = StringUtils.getBytes(this.message, "ISO8859_1");
        int len = 1 + (4 + hostNameAsBytes.length) + (4 + dbAsBytes.length) + 8 + 4 + 4 + 8 + 8 + (4 + durationUnitsAsBytes.length) + (4 + eventCreationAsBytes.length) + (4 + messageAsBytes.length);
        byte[] buf = new byte[len];
        int pos = 0;
        buf[pos++] = this.eventType;
        pos = ProfilerEventImpl.writeBytes(hostNameAsBytes, buf, pos);
        pos = ProfilerEventImpl.writeBytes(dbAsBytes, buf, pos);
        pos = ProfilerEventImpl.writeLong(this.connectionId, buf, pos);
        pos = ProfilerEventImpl.writeInt(this.statementId, buf, pos);
        pos = ProfilerEventImpl.writeInt(this.resultSetId, buf, pos);
        pos = ProfilerEventImpl.writeLong(this.eventCreationTime, buf, pos);
        pos = ProfilerEventImpl.writeLong(this.eventDuration, buf, pos);
        pos = ProfilerEventImpl.writeBytes(durationUnitsAsBytes, buf, pos);
        pos = ProfilerEventImpl.writeBytes(eventCreationAsBytes, buf, pos);
        pos = ProfilerEventImpl.writeBytes(messageAsBytes, buf, pos);
        return buf;
    }

    private static int writeInt(int i, byte[] buf, int pos) {
        buf[pos++] = (byte)(i & 0xFF);
        buf[pos++] = (byte)(i >>> 8);
        buf[pos++] = (byte)(i >>> 16);
        buf[pos++] = (byte)(i >>> 24);
        return pos;
    }

    private static int writeLong(long l, byte[] buf, int pos) {
        buf[pos++] = (byte)(l & 0xFFL);
        buf[pos++] = (byte)(l >>> 8);
        buf[pos++] = (byte)(l >>> 16);
        buf[pos++] = (byte)(l >>> 24);
        buf[pos++] = (byte)(l >>> 32);
        buf[pos++] = (byte)(l >>> 40);
        buf[pos++] = (byte)(l >>> 48);
        buf[pos++] = (byte)(l >>> 56);
        return pos;
    }

    private static int writeBytes(byte[] msg, byte[] buf, int pos) {
        pos = ProfilerEventImpl.writeInt(msg.length, buf, pos);
        System.arraycopy(msg, 0, buf, pos, msg.length);
        return pos + msg.length;
    }

    private static int readInt(byte[] buf, int pos) {
        return buf[pos++] & 0xFF | (buf[pos++] & 0xFF) << 8 | (buf[pos++] & 0xFF) << 16 | (buf[pos++] & 0xFF) << 24;
    }

    private static long readLong(byte[] buf, int pos) {
        return (long)(buf[pos++] & 0xFF) | (long)(buf[pos++] & 0xFF) << 8 | (long)(buf[pos++] & 0xFF) << 16 | (long)(buf[pos++] & 0xFF) << 24 | (long)(buf[pos++] & 0xFF) << 32 | (long)(buf[pos++] & 0xFF) << 40 | (long)(buf[pos++] & 0xFF) << 48 | (long)(buf[pos++] & 0xFF) << 56;
    }

    private static byte[] readBytes(byte[] buf, int pos) {
        int length = ProfilerEventImpl.readInt(buf, pos);
        byte[] msg = new byte[length];
        System.arraycopy(buf, pos + 4, msg, 0, length);
        return msg;
    }
}

