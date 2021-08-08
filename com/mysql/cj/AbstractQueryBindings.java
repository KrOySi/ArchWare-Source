/*
 * Decompiled with CFR 0.150.
 */
package com.mysql.cj;

import com.mysql.cj.BindValue;
import com.mysql.cj.Messages;
import com.mysql.cj.MysqlType;
import com.mysql.cj.QueryBindings;
import com.mysql.cj.Session;
import com.mysql.cj.conf.PropertyKey;
import com.mysql.cj.conf.RuntimeProperty;
import com.mysql.cj.exceptions.ExceptionFactory;
import com.mysql.cj.exceptions.WrongArgumentException;
import com.mysql.cj.protocol.ColumnDefinition;
import com.mysql.cj.protocol.a.NativeConstants;
import com.mysql.cj.protocol.a.NativePacketPayload;
import com.mysql.cj.util.StringUtils;
import com.mysql.cj.util.TimeUtil;
import com.mysql.cj.util.Util;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Blob;
import java.sql.Clob;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.time.OffsetTime;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public abstract class AbstractQueryBindings<T extends BindValue>
implements QueryBindings<T> {
    protected static final byte[] HEX_DIGITS = new byte[]{48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 65, 66, 67, 68, 69, 70};
    protected static final LocalDate DEFAULT_DATE = LocalDate.of(1970, 1, 1);
    protected static final LocalTime DEFAULT_TIME = LocalTime.of(0, 0);
    protected Session session;
    protected T[] bindValues;
    protected String charEncoding;
    protected int numberOfExecutions = 0;
    protected RuntimeProperty<Boolean> useStreamLengthsInPrepStmts;
    protected RuntimeProperty<Boolean> preserveInstants;
    protected RuntimeProperty<Boolean> sendFractionalSeconds;
    protected RuntimeProperty<Boolean> sendFractionalSecondsForTime;
    private RuntimeProperty<Boolean> treatUtilDateAsTimestamp;
    protected boolean isLoadDataQuery = false;
    protected ColumnDefinition columnDefinition;
    static Map<Class<?>, MysqlType> DEFAULT_MYSQL_TYPES = new HashMap();
    private byte[] streamConvertBuf = null;

    public AbstractQueryBindings(int parameterCount, Session sess) {
        this.session = sess;
        this.charEncoding = this.session.getPropertySet().getStringProperty(PropertyKey.characterEncoding).getValue();
        this.preserveInstants = this.session.getPropertySet().getBooleanProperty(PropertyKey.preserveInstants);
        this.sendFractionalSeconds = this.session.getPropertySet().getBooleanProperty(PropertyKey.sendFractionalSeconds);
        this.sendFractionalSecondsForTime = this.session.getPropertySet().getBooleanProperty(PropertyKey.sendFractionalSecondsForTime);
        this.treatUtilDateAsTimestamp = this.session.getPropertySet().getBooleanProperty(PropertyKey.treatUtilDateAsTimestamp);
        this.useStreamLengthsInPrepStmts = this.session.getPropertySet().getBooleanProperty(PropertyKey.useStreamLengthsInPrepStmts);
        this.initBindValues(parameterCount);
    }

    protected abstract void initBindValues(int var1);

    @Override
    public abstract AbstractQueryBindings<T> clone();

    @Override
    public void setColumnDefinition(ColumnDefinition colDef) {
        this.columnDefinition = colDef;
    }

    @Override
    public boolean isLoadDataQuery() {
        return this.isLoadDataQuery;
    }

    @Override
    public void setLoadDataQuery(boolean isLoadDataQuery) {
        this.isLoadDataQuery = isLoadDataQuery;
    }

    @Override
    public T[] getBindValues() {
        return this.bindValues;
    }

    @Override
    public void setBindValues(T[] bindValues) {
        this.bindValues = bindValues;
    }

    @Override
    public boolean clearBindValues() {
        boolean hadLongData = false;
        if (this.bindValues != null) {
            for (int i = 0; i < this.bindValues.length; ++i) {
                if (this.bindValues[i] != null && this.bindValues[i].isStream()) {
                    hadLongData = true;
                }
                this.bindValues[i].reset();
            }
        }
        return hadLongData;
    }

    @Override
    public abstract void checkParameterSet(int var1);

    @Override
    public void checkAllParametersSet() {
        for (int i = 0; i < this.bindValues.length; ++i) {
            this.checkParameterSet(i);
        }
    }

    @Override
    public int getNumberOfExecutions() {
        return this.numberOfExecutions;
    }

    @Override
    public void setNumberOfExecutions(int numberOfExecutions) {
        this.numberOfExecutions = numberOfExecutions;
    }

    @Override
    public final synchronized void setValue(int paramIndex, byte[] val, MysqlType type) {
        this.bindValues[paramIndex].setByteValue(val);
        this.bindValues[paramIndex].setMysqlType(type);
    }

    public final synchronized void setOrigValue(int paramIndex, byte[] val) {
        this.bindValues[paramIndex].setOrigByteValue(val);
    }

    @Override
    public synchronized byte[] getOrigBytes(int parameterIndex) {
        return this.bindValues[parameterIndex].getOrigByteValue();
    }

    @Override
    public final synchronized void setValue(int paramIndex, String val, MysqlType type) {
        byte[] parameterAsBytes = StringUtils.getBytes(val, this.charEncoding);
        this.setValue(paramIndex, parameterAsBytes, type);
    }

    public final void hexEscapeBlock(byte[] buf, NativePacketPayload packet, int size) {
        for (int i = 0; i < size; ++i) {
            byte b = buf[i];
            int lowBits = (b & 0xFF) / 16;
            int highBits = (b & 0xFF) % 16;
            packet.writeInteger(NativeConstants.IntegerDataType.INT1, HEX_DIGITS[lowBits]);
            packet.writeInteger(NativeConstants.IntegerDataType.INT1, HEX_DIGITS[highBits]);
        }
    }

    @Override
    public void setTimestamp(int parameterIndex, Timestamp x, MysqlType targetMysqlType) {
        int fractLen = -1;
        if (!this.session.getServerSession().getCapabilities().serverSupportsFracSecs() || !this.sendFractionalSeconds.getValue().booleanValue()) {
            fractLen = 0;
        } else if (this.columnDefinition != null && parameterIndex <= this.columnDefinition.getFields().length && parameterIndex >= 0) {
            fractLen = this.columnDefinition.getFields()[parameterIndex].getDecimals();
        }
        this.setTimestamp(parameterIndex, x, null, fractLen, targetMysqlType);
    }

    @Override
    public void setTimestamp(int parameterIndex, Timestamp x, Calendar cal, MysqlType targetMysqlType) {
        int fractLen = -1;
        if (!this.session.getServerSession().getCapabilities().serverSupportsFracSecs() || !this.sendFractionalSeconds.getValue().booleanValue()) {
            fractLen = 0;
        } else if (this.columnDefinition != null && parameterIndex <= this.columnDefinition.getFields().length && parameterIndex >= 0 && this.columnDefinition.getFields()[parameterIndex].getDecimals() > 0) {
            fractLen = this.columnDefinition.getFields()[parameterIndex].getDecimals();
        }
        this.setTimestamp(parameterIndex, x, cal, fractLen, targetMysqlType);
    }

    @Override
    public void setTimestamp(int parameterIndex, Timestamp x, Calendar targetCalendar, int fractionalLength, MysqlType targetMysqlType) {
        if (x == null) {
            this.setNull(parameterIndex);
            return;
        }
        if (!this.session.getServerSession().getCapabilities().serverSupportsFracSecs() || !this.sendFractionalSeconds.getValue().booleanValue()) {
            x = TimeUtil.truncateFractionalSeconds(x);
        }
        this.bindTimestamp(parameterIndex, x, targetCalendar, fractionalLength, targetMysqlType);
    }

    @Override
    public void setObject(int parameterIndex, Object parameterObj) {
        Optional<MysqlType> mysqlType;
        if (parameterObj == null) {
            this.setNull(parameterIndex);
            return;
        }
        MysqlType defaultMysqlType = DEFAULT_MYSQL_TYPES.get(parameterObj.getClass());
        if (defaultMysqlType == null && (mysqlType = DEFAULT_MYSQL_TYPES.entrySet().stream().filter(m -> ((Class)m.getKey()).isAssignableFrom(parameterObj.getClass())).map(m -> (MysqlType)m.getValue()).findFirst()).isPresent()) {
            defaultMysqlType = mysqlType.get();
        }
        if (defaultMysqlType != null) {
            this.setObject(parameterIndex, parameterObj, defaultMysqlType);
        } else {
            this.setSerializableObject(parameterIndex, parameterObj);
        }
    }

    @Override
    public void setObject(int parameterIndex, Object parameterObj, MysqlType targetMysqlType) {
        this.setObject(parameterIndex, parameterObj, targetMysqlType, parameterObj instanceof BigDecimal ? ((BigDecimal)parameterObj).scale() : 0);
    }

    @Override
    public void setObject(int parameterIndex, Object parameterObj, MysqlType targetMysqlType, int scaleOrLength) {
        block163: {
            if (parameterObj == null) {
                this.setNull(parameterIndex);
                return;
            }
            try {
                if (parameterObj instanceof LocalDate) {
                    switch (targetMysqlType) {
                        case DATE: {
                            this.setLocalDate(parameterIndex, (LocalDate)parameterObj, targetMysqlType);
                            break block163;
                        }
                        case DATETIME: 
                        case TIMESTAMP: {
                            this.setLocalDateTime(parameterIndex, LocalDateTime.of((LocalDate)parameterObj, DEFAULT_TIME), targetMysqlType);
                            break block163;
                        }
                        case YEAR: {
                            this.setInt(parameterIndex, ((LocalDate)parameterObj).getYear());
                            break block163;
                        }
                        case CHAR: 
                        case VARCHAR: 
                        case TINYTEXT: 
                        case TEXT: 
                        case MEDIUMTEXT: 
                        case LONGTEXT: {
                            this.setString(parameterIndex, parameterObj.toString());
                            break block163;
                        }
                        default: {
                            throw ExceptionFactory.createException(WrongArgumentException.class, Messages.getString("PreparedStatement.67", new Object[]{parameterObj.getClass().getName(), targetMysqlType.toString()}), this.session.getExceptionInterceptor());
                        }
                    }
                }
                if (parameterObj instanceof LocalTime) {
                    switch (targetMysqlType) {
                        case TIME: {
                            this.setLocalTime(parameterIndex, (LocalTime)parameterObj, targetMysqlType);
                            break block163;
                        }
                        case CHAR: 
                        case VARCHAR: 
                        case TINYTEXT: 
                        case TEXT: 
                        case MEDIUMTEXT: 
                        case LONGTEXT: {
                            this.setString(parameterIndex, ((LocalTime)parameterObj).format(this.sendFractionalSeconds.getValue() != false && ((LocalTime)parameterObj).getNano() > 0 ? TimeUtil.TIME_FORMATTER_WITH_NANOS_NO_OFFSET : TimeUtil.TIME_FORMATTER_NO_FRACT_NO_OFFSET));
                            break block163;
                        }
                        default: {
                            throw ExceptionFactory.createException(WrongArgumentException.class, Messages.getString("PreparedStatement.67", new Object[]{parameterObj.getClass().getName(), targetMysqlType.toString()}), this.session.getExceptionInterceptor());
                        }
                    }
                }
                if (parameterObj instanceof LocalDateTime) {
                    switch (targetMysqlType) {
                        case DATE: 
                        case DATETIME: 
                        case TIMESTAMP: 
                        case TIME: {
                            this.setLocalDateTime(parameterIndex, (LocalDateTime)parameterObj, targetMysqlType);
                            break block163;
                        }
                        case YEAR: {
                            this.setInt(parameterIndex, ((LocalDateTime)parameterObj).getYear());
                            break block163;
                        }
                        case CHAR: 
                        case VARCHAR: 
                        case TINYTEXT: 
                        case TEXT: 
                        case MEDIUMTEXT: 
                        case LONGTEXT: {
                            this.setString(parameterIndex, ((LocalDateTime)parameterObj).format(this.sendFractionalSeconds.getValue() != false && ((LocalDateTime)parameterObj).getNano() > 0 ? TimeUtil.DATETIME_FORMATTER_WITH_NANOS_NO_OFFSET : TimeUtil.DATETIME_FORMATTER_NO_FRACT_NO_OFFSET));
                            break block163;
                        }
                        default: {
                            throw ExceptionFactory.createException(WrongArgumentException.class, Messages.getString("PreparedStatement.67", new Object[]{parameterObj.getClass().getName(), targetMysqlType.toString()}), this.session.getExceptionInterceptor());
                        }
                    }
                }
                if (parameterObj instanceof OffsetTime) {
                    switch (targetMysqlType) {
                        case TIME: {
                            this.setLocalTime(parameterIndex, ((OffsetTime)parameterObj).withOffsetSameInstant(ZoneOffset.ofTotalSeconds(this.session.getServerSession().getDefaultTimeZone().getRawOffset() / 1000)).toLocalTime(), targetMysqlType);
                            break block163;
                        }
                        case CHAR: 
                        case VARCHAR: 
                        case TINYTEXT: 
                        case TEXT: 
                        case MEDIUMTEXT: 
                        case LONGTEXT: {
                            this.setString(parameterIndex, ((OffsetTime)parameterObj).format(this.sendFractionalSeconds.getValue() != false && ((OffsetTime)parameterObj).getNano() > 0 ? TimeUtil.TIME_FORMATTER_WITH_NANOS_WITH_OFFSET : TimeUtil.TIME_FORMATTER_NO_FRACT_WITH_OFFSET));
                            break block163;
                        }
                        default: {
                            throw ExceptionFactory.createException(WrongArgumentException.class, Messages.getString("PreparedStatement.67", new Object[]{parameterObj.getClass().getName(), targetMysqlType.toString()}), this.session.getExceptionInterceptor());
                        }
                    }
                }
                if (parameterObj instanceof OffsetDateTime) {
                    switch (targetMysqlType) {
                        case DATE: {
                            this.setLocalDate(parameterIndex, ((OffsetDateTime)parameterObj).atZoneSameInstant(this.session.getServerSession().getDefaultTimeZone().toZoneId()).toLocalDate(), targetMysqlType);
                            break block163;
                        }
                        case DATETIME: 
                        case TIMESTAMP: {
                            Timestamp ts = Timestamp.valueOf(((OffsetDateTime)parameterObj).atZoneSameInstant(this.session.getServerSession().getDefaultTimeZone().toZoneId()).toLocalDateTime());
                            int fractLen = -1;
                            if (!this.session.getServerSession().getCapabilities().serverSupportsFracSecs() || !this.sendFractionalSeconds.getValue().booleanValue()) {
                                fractLen = 0;
                            } else if (this.columnDefinition != null && parameterIndex <= this.columnDefinition.getFields().length && parameterIndex >= 0 && this.columnDefinition.getFields()[parameterIndex].getDecimals() > 0) {
                                fractLen = this.columnDefinition.getFields()[parameterIndex].getDecimals();
                            }
                            if (fractLen == 0) {
                                ts = TimeUtil.truncateFractionalSeconds(ts);
                            }
                            this.bindTimestamp(parameterIndex, ts, null, fractLen, targetMysqlType);
                            break block163;
                        }
                        case TIME: {
                            this.setLocalTime(parameterIndex, ((OffsetDateTime)parameterObj).atZoneSameInstant(this.session.getServerSession().getDefaultTimeZone().toZoneId()).toLocalTime(), targetMysqlType);
                            break block163;
                        }
                        case YEAR: {
                            this.setInt(parameterIndex, ((OffsetDateTime)parameterObj).atZoneSameInstant(this.session.getServerSession().getDefaultTimeZone().toZoneId()).getYear());
                            break block163;
                        }
                        case CHAR: 
                        case VARCHAR: 
                        case TINYTEXT: 
                        case TEXT: 
                        case MEDIUMTEXT: 
                        case LONGTEXT: {
                            this.setString(parameterIndex, ((OffsetDateTime)parameterObj).format(this.sendFractionalSeconds.getValue() != false && ((OffsetDateTime)parameterObj).getNano() > 0 ? TimeUtil.DATETIME_FORMATTER_WITH_NANOS_WITH_OFFSET : TimeUtil.DATETIME_FORMATTER_NO_FRACT_WITH_OFFSET));
                            break block163;
                        }
                        default: {
                            throw ExceptionFactory.createException(WrongArgumentException.class, Messages.getString("PreparedStatement.67", new Object[]{parameterObj.getClass().getName(), targetMysqlType.toString()}), this.session.getExceptionInterceptor());
                        }
                    }
                }
                if (parameterObj instanceof ZonedDateTime) {
                    switch (targetMysqlType) {
                        case DATE: {
                            this.setLocalDate(parameterIndex, ((ZonedDateTime)parameterObj).withZoneSameInstant(this.session.getServerSession().getDefaultTimeZone().toZoneId()).toLocalDate(), targetMysqlType);
                            break block163;
                        }
                        case DATETIME: 
                        case TIMESTAMP: {
                            Timestamp ts = Timestamp.valueOf(((ZonedDateTime)parameterObj).withZoneSameInstant(this.session.getServerSession().getDefaultTimeZone().toZoneId()).toLocalDateTime());
                            int fractLen = -1;
                            if (!this.session.getServerSession().getCapabilities().serverSupportsFracSecs() || !this.sendFractionalSeconds.getValue().booleanValue()) {
                                fractLen = 0;
                            } else if (this.columnDefinition != null && parameterIndex <= this.columnDefinition.getFields().length && parameterIndex >= 0 && this.columnDefinition.getFields()[parameterIndex].getDecimals() > 0) {
                                fractLen = this.columnDefinition.getFields()[parameterIndex].getDecimals();
                            }
                            if (fractLen == 0) {
                                ts = TimeUtil.truncateFractionalSeconds(ts);
                            }
                            this.bindTimestamp(parameterIndex, ts, null, fractLen, targetMysqlType);
                            break block163;
                        }
                        case TIME: {
                            this.setLocalTime(parameterIndex, ((ZonedDateTime)parameterObj).withZoneSameInstant(this.session.getServerSession().getDefaultTimeZone().toZoneId()).toLocalTime(), targetMysqlType);
                            break block163;
                        }
                        case YEAR: {
                            this.setInt(parameterIndex, ((ZonedDateTime)parameterObj).withZoneSameInstant(this.session.getServerSession().getDefaultTimeZone().toZoneId()).getYear());
                            break block163;
                        }
                        case CHAR: 
                        case VARCHAR: 
                        case TINYTEXT: 
                        case TEXT: 
                        case MEDIUMTEXT: 
                        case LONGTEXT: {
                            this.setString(parameterIndex, ((ZonedDateTime)parameterObj).format(this.sendFractionalSeconds.getValue() != false && ((ZonedDateTime)parameterObj).getNano() > 0 ? TimeUtil.DATETIME_FORMATTER_WITH_NANOS_WITH_OFFSET : TimeUtil.DATETIME_FORMATTER_NO_FRACT_WITH_OFFSET));
                            break block163;
                        }
                        default: {
                            throw ExceptionFactory.createException(WrongArgumentException.class, Messages.getString("PreparedStatement.67", new Object[]{parameterObj.getClass().getName(), targetMysqlType.toString()}), this.session.getExceptionInterceptor());
                        }
                    }
                }
                if (parameterObj instanceof Duration) {
                    switch (targetMysqlType) {
                        case TIME: {
                            this.setDuration(parameterIndex, (Duration)parameterObj, targetMysqlType);
                            break block163;
                        }
                        case CHAR: 
                        case VARCHAR: 
                        case TINYTEXT: 
                        case TEXT: 
                        case MEDIUMTEXT: 
                        case LONGTEXT: {
                            this.setString(parameterIndex, TimeUtil.getDurationString((Duration)parameterObj));
                            break block163;
                        }
                        default: {
                            throw ExceptionFactory.createException(WrongArgumentException.class, Messages.getString("PreparedStatement.67", new Object[]{parameterObj.getClass().getName(), targetMysqlType.toString()}), this.session.getExceptionInterceptor());
                        }
                    }
                }
                if (parameterObj instanceof Date) {
                    switch (targetMysqlType) {
                        case DATE: {
                            this.setDate(parameterIndex, (Date)parameterObj);
                            break block163;
                        }
                        case DATETIME: 
                        case TIMESTAMP: {
                            this.setTimestamp(parameterIndex, new Timestamp(((java.util.Date)parameterObj).getTime()), targetMysqlType);
                            break block163;
                        }
                        case YEAR: {
                            Calendar cal = Calendar.getInstance();
                            cal.setTime((java.util.Date)parameterObj);
                            this.setInt(parameterIndex, cal.get(1));
                            break block163;
                        }
                        case CHAR: 
                        case VARCHAR: 
                        case TINYTEXT: 
                        case TEXT: 
                        case MEDIUMTEXT: 
                        case LONGTEXT: {
                            this.setString(parameterIndex, parameterObj.toString());
                            break block163;
                        }
                        default: {
                            throw ExceptionFactory.createException(WrongArgumentException.class, Messages.getString("PreparedStatement.67", new Object[]{parameterObj.getClass().getName(), targetMysqlType.toString()}), this.session.getExceptionInterceptor());
                        }
                    }
                }
                if (parameterObj instanceof Timestamp) {
                    switch (targetMysqlType) {
                        case DATE: {
                            this.setDate(parameterIndex, new Date(((java.util.Date)parameterObj).getTime()));
                            break block163;
                        }
                        case DATETIME: 
                        case TIMESTAMP: {
                            this.setTimestamp(parameterIndex, (Timestamp)parameterObj, targetMysqlType);
                            break block163;
                        }
                        case YEAR: {
                            Calendar cal = Calendar.getInstance();
                            cal.setTime((java.util.Date)parameterObj);
                            this.setInt(parameterIndex, cal.get(1));
                            break block163;
                        }
                        case TIME: {
                            this.setLocalTime(parameterIndex, ((Timestamp)parameterObj).toLocalDateTime().toLocalTime(), targetMysqlType);
                            break block163;
                        }
                        case CHAR: 
                        case VARCHAR: 
                        case TINYTEXT: 
                        case TEXT: 
                        case MEDIUMTEXT: 
                        case LONGTEXT: {
                            int dotPos;
                            String val = parameterObj.toString();
                            if (!(((Timestamp)parameterObj).getNanos() != 0 && this.sendFractionalSeconds.getValue().booleanValue() || (dotPos = val.indexOf(".")) <= 0)) {
                                val = val.substring(0, dotPos);
                            }
                            this.setString(parameterIndex, val);
                            break block163;
                        }
                        default: {
                            throw ExceptionFactory.createException(WrongArgumentException.class, Messages.getString("PreparedStatement.67", new Object[]{parameterObj.getClass().getName(), targetMysqlType.toString()}), this.session.getExceptionInterceptor());
                        }
                    }
                }
                if (parameterObj instanceof Time) {
                    switch (targetMysqlType) {
                        case DATE: {
                            this.setDate(parameterIndex, new Date(((java.util.Date)parameterObj).getTime()));
                            break block163;
                        }
                        case DATETIME: 
                        case TIMESTAMP: {
                            Timestamp ts = new Timestamp(((Time)parameterObj).getTime());
                            int fractLen = -1;
                            if (!(this.session.getServerSession().getCapabilities().serverSupportsFracSecs() && this.sendFractionalSecondsForTime.getValue().booleanValue() && this.sendFractionalSeconds.getValue().booleanValue())) {
                                fractLen = 0;
                            } else if (this.columnDefinition != null && parameterIndex <= this.columnDefinition.getFields().length && parameterIndex >= 0 && this.columnDefinition.getFields()[parameterIndex].getDecimals() > 0) {
                                fractLen = this.columnDefinition.getFields()[parameterIndex].getDecimals();
                            }
                            if (fractLen == 0) {
                                ts = TimeUtil.truncateFractionalSeconds(ts);
                            }
                            this.bindTimestamp(parameterIndex, ts, null, fractLen, MysqlType.DATETIME);
                            break block163;
                        }
                        case YEAR: {
                            Calendar cal = Calendar.getInstance();
                            cal.setTime((java.util.Date)parameterObj);
                            this.setInt(parameterIndex, cal.get(1));
                            break block163;
                        }
                        case TIME: {
                            this.setTime(parameterIndex, (Time)parameterObj);
                            break block163;
                        }
                        case CHAR: 
                        case VARCHAR: 
                        case TINYTEXT: 
                        case TEXT: 
                        case MEDIUMTEXT: 
                        case LONGTEXT: {
                            this.setString(parameterIndex, TimeUtil.getSimpleDateFormat(this.session.getServerSession().getCapabilities().serverSupportsFracSecs() && this.sendFractionalSeconds.getValue() != false && this.sendFractionalSecondsForTime.getValue() != false && TimeUtil.hasFractionalSeconds((Time)parameterObj) != false ? "HH:mm:ss.SSS" : "HH:mm:ss", null).format(parameterObj));
                            break block163;
                        }
                        default: {
                            throw ExceptionFactory.createException(WrongArgumentException.class, Messages.getString("PreparedStatement.67", new Object[]{parameterObj.getClass().getName(), targetMysqlType.toString()}), this.session.getExceptionInterceptor());
                        }
                    }
                }
                if (parameterObj instanceof java.util.Date) {
                    if (!this.treatUtilDateAsTimestamp.getValue().booleanValue()) {
                        this.setSerializableObject(parameterIndex, parameterObj);
                        return;
                    }
                    switch (targetMysqlType) {
                        case DATE: {
                            this.setDate(parameterIndex, new Date(((java.util.Date)parameterObj).getTime()));
                            break block163;
                        }
                        case DATETIME: 
                        case TIMESTAMP: {
                            this.setTimestamp(parameterIndex, new Timestamp(((java.util.Date)parameterObj).getTime()), targetMysqlType);
                            break block163;
                        }
                        case YEAR: {
                            Calendar cal = Calendar.getInstance();
                            cal.setTime((java.util.Date)parameterObj);
                            this.setInt(parameterIndex, cal.get(1));
                            break block163;
                        }
                        case TIME: {
                            LocalTime lt = ((java.util.Date)parameterObj).toInstant().atZone(this.session.getServerSession().getDefaultTimeZone().toZoneId()).toLocalTime();
                            this.setLocalTime(parameterIndex, lt, targetMysqlType);
                            break block163;
                        }
                        case CHAR: 
                        case VARCHAR: 
                        case TINYTEXT: 
                        case TEXT: 
                        case MEDIUMTEXT: 
                        case LONGTEXT: {
                            this.setString(parameterIndex, TimeUtil.getSimpleDateFormat(this.session.getServerSession().getCapabilities().serverSupportsFracSecs() && this.sendFractionalSeconds.getValue() != false && ((java.util.Date)parameterObj).toInstant().getNano() > 0 ? "yyyy-MM-dd HH:mm:ss.SSS" : "yyyy-MM-dd HH:mm:ss", null).format(parameterObj));
                            break block163;
                        }
                        default: {
                            throw ExceptionFactory.createException(WrongArgumentException.class, Messages.getString("PreparedStatement.67", new Object[]{parameterObj.getClass().getName(), targetMysqlType.toString()}), this.session.getExceptionInterceptor());
                        }
                    }
                }
                if (parameterObj instanceof Calendar) {
                    switch (targetMysqlType) {
                        case DATE: {
                            this.setDate(parameterIndex, new Date(((Calendar)parameterObj).getTimeInMillis()));
                            break block163;
                        }
                        case DATETIME: 
                        case TIMESTAMP: {
                            this.setTimestamp(parameterIndex, new Timestamp(((Calendar)parameterObj).getTimeInMillis()), targetMysqlType);
                            break block163;
                        }
                        case YEAR: {
                            this.setInt(parameterIndex, ((Calendar)parameterObj).get(1));
                            break block163;
                        }
                        case TIME: {
                            LocalTime lt = ((Calendar)parameterObj).toInstant().atZone(this.session.getServerSession().getDefaultTimeZone().toZoneId()).toLocalTime();
                            this.setLocalTime(parameterIndex, lt, targetMysqlType);
                            break block163;
                        }
                        case CHAR: 
                        case VARCHAR: 
                        case TINYTEXT: 
                        case TEXT: 
                        case MEDIUMTEXT: 
                        case LONGTEXT: {
                            ZonedDateTime zdt = ZonedDateTime.ofInstant(((Calendar)parameterObj).toInstant(), ((Calendar)parameterObj).getTimeZone().toZoneId()).withZoneSameInstant(this.session.getServerSession().getDefaultTimeZone().toZoneId());
                            this.setString(parameterIndex, zdt.format(zdt.getNano() > 0 && this.session.getServerSession().getCapabilities().serverSupportsFracSecs() && this.sendFractionalSeconds.getValue() != false ? TimeUtil.DATETIME_FORMATTER_WITH_MILLIS_NO_OFFSET : TimeUtil.DATETIME_FORMATTER_NO_FRACT_NO_OFFSET));
                            break block163;
                        }
                        default: {
                            throw ExceptionFactory.createException(WrongArgumentException.class, Messages.getString("PreparedStatement.67", new Object[]{parameterObj.getClass().getName(), targetMysqlType.toString()}), this.session.getExceptionInterceptor());
                        }
                    }
                }
                if (parameterObj instanceof String) {
                    switch (targetMysqlType) {
                        case BOOLEAN: {
                            if ("true".equalsIgnoreCase((String)parameterObj) || "Y".equalsIgnoreCase((String)parameterObj)) {
                                this.setBoolean(parameterIndex, true);
                                break block163;
                            }
                            if ("false".equalsIgnoreCase((String)parameterObj) || "N".equalsIgnoreCase((String)parameterObj)) {
                                this.setBoolean(parameterIndex, false);
                                break block163;
                            }
                            if (((String)parameterObj).matches("-?\\d+\\.?\\d*")) {
                                this.setBoolean(parameterIndex, !((String)parameterObj).matches("-?[0]+[.]*[0]*"));
                                break block163;
                            }
                            throw ExceptionFactory.createException(WrongArgumentException.class, Messages.getString("PreparedStatement.66", new Object[]{parameterObj}), this.session.getExceptionInterceptor());
                        }
                        case BIT: {
                            if ("1".equals(parameterObj) || "0".equals(parameterObj)) {
                                this.setInt(parameterIndex, Integer.valueOf((String)parameterObj));
                            } else {
                                boolean parameterAsBoolean = "true".equalsIgnoreCase((String)parameterObj);
                                this.setInt(parameterIndex, parameterAsBoolean ? 1 : 0);
                            }
                            break block163;
                        }
                        case TINYINT: 
                        case TINYINT_UNSIGNED: 
                        case SMALLINT: 
                        case SMALLINT_UNSIGNED: 
                        case MEDIUMINT: 
                        case MEDIUMINT_UNSIGNED: 
                        case INT: 
                        case INT_UNSIGNED: {
                            this.setInt(parameterIndex, Integer.valueOf((String)parameterObj));
                            break block163;
                        }
                        case BIGINT: {
                            this.setLong(parameterIndex, Long.valueOf((String)parameterObj));
                            break block163;
                        }
                        case BIGINT_UNSIGNED: {
                            this.setLong(parameterIndex, new BigInteger((String)parameterObj).longValue());
                            break block163;
                        }
                        case FLOAT: 
                        case FLOAT_UNSIGNED: {
                            this.setFloat(parameterIndex, Float.valueOf((String)parameterObj).floatValue());
                            break block163;
                        }
                        case DOUBLE: 
                        case DOUBLE_UNSIGNED: {
                            this.setDouble(parameterIndex, Double.valueOf((String)parameterObj));
                            break block163;
                        }
                        case DECIMAL: 
                        case DECIMAL_UNSIGNED: {
                            BigDecimal parameterAsNum = new BigDecimal((String)parameterObj);
                            BigDecimal scaledBigDecimal = null;
                            try {
                                scaledBigDecimal = parameterAsNum.setScale(scaleOrLength);
                            }
                            catch (ArithmeticException ex) {
                                try {
                                    scaledBigDecimal = parameterAsNum.setScale(scaleOrLength, 4);
                                }
                                catch (ArithmeticException arEx) {
                                    throw ExceptionFactory.createException(WrongArgumentException.class, Messages.getString("PreparedStatement.65", new Object[]{scaleOrLength, parameterAsNum}), this.session.getExceptionInterceptor());
                                }
                            }
                            this.setBigDecimal(parameterIndex, scaledBigDecimal);
                            break block163;
                        }
                        case CHAR: 
                        case VARCHAR: 
                        case TINYTEXT: 
                        case TEXT: 
                        case MEDIUMTEXT: 
                        case LONGTEXT: 
                        case ENUM: 
                        case SET: 
                        case JSON: {
                            this.setString(parameterIndex, parameterObj.toString());
                            break block163;
                        }
                        case BINARY: 
                        case GEOMETRY: 
                        case VARBINARY: 
                        case TINYBLOB: 
                        case BLOB: 
                        case MEDIUMBLOB: 
                        case LONGBLOB: {
                            this.setBytes(parameterIndex, StringUtils.getBytes(parameterObj.toString(), this.charEncoding));
                            break block163;
                        }
                        case DATE: 
                        case DATETIME: 
                        case TIMESTAMP: 
                        case YEAR: 
                        case TIME: {
                            this.setObject(parameterIndex, TimeUtil.parseToDateTimeObject((String)parameterObj, targetMysqlType), targetMysqlType);
                            break block163;
                        }
                        case UNKNOWN: {
                            this.setSerializableObject(parameterIndex, parameterObj);
                            break block163;
                        }
                        default: {
                            throw ExceptionFactory.createException(WrongArgumentException.class, Messages.getString("PreparedStatement.67", new Object[]{parameterObj.getClass().getName(), targetMysqlType.toString()}), this.session.getExceptionInterceptor());
                        }
                    }
                }
                if (parameterObj instanceof InputStream) {
                    this.setBinaryStream(parameterIndex, (InputStream)parameterObj, -1);
                    break block163;
                }
                if (parameterObj instanceof Boolean) {
                    switch (targetMysqlType) {
                        case BOOLEAN: {
                            this.setBoolean(parameterIndex, (Boolean)parameterObj);
                            break block163;
                        }
                        case YEAR: 
                        case BIT: 
                        case TINYINT: 
                        case TINYINT_UNSIGNED: 
                        case SMALLINT: 
                        case SMALLINT_UNSIGNED: 
                        case MEDIUMINT: 
                        case MEDIUMINT_UNSIGNED: 
                        case INT: 
                        case INT_UNSIGNED: {
                            this.setInt(parameterIndex, (Boolean)parameterObj != false ? 1 : 0);
                            break block163;
                        }
                        case BIGINT: 
                        case BIGINT_UNSIGNED: {
                            this.setLong(parameterIndex, (Boolean)parameterObj != false ? 1L : 0L);
                            break block163;
                        }
                        case FLOAT: 
                        case FLOAT_UNSIGNED: {
                            this.setFloat(parameterIndex, (Boolean)parameterObj != false ? 1.0f : 0.0f);
                            break block163;
                        }
                        case DOUBLE: 
                        case DOUBLE_UNSIGNED: {
                            this.setDouble(parameterIndex, (Boolean)parameterObj != false ? 1.0 : 0.0);
                            break block163;
                        }
                        case DECIMAL: 
                        case DECIMAL_UNSIGNED: {
                            this.setBigDecimal(parameterIndex, new BigDecimal((Boolean)parameterObj != false ? 1.0 : 0.0));
                            break block163;
                        }
                        case CHAR: 
                        case VARCHAR: 
                        case TINYTEXT: 
                        case TEXT: 
                        case MEDIUMTEXT: 
                        case LONGTEXT: {
                            this.setString(parameterIndex, parameterObj.toString());
                            break block163;
                        }
                        default: {
                            throw ExceptionFactory.createException(WrongArgumentException.class, Messages.getString("PreparedStatement.67", new Object[]{parameterObj.getClass().getName(), targetMysqlType.toString()}), this.session.getExceptionInterceptor());
                        }
                    }
                }
                if (parameterObj instanceof Number) {
                    Number parameterAsNum = (Number)parameterObj;
                    switch (targetMysqlType) {
                        case BOOLEAN: {
                            this.setBoolean(parameterIndex, parameterAsNum.intValue() != 0);
                            break;
                        }
                        case YEAR: 
                        case BIT: 
                        case TINYINT: 
                        case TINYINT_UNSIGNED: 
                        case SMALLINT: 
                        case SMALLINT_UNSIGNED: 
                        case MEDIUMINT: 
                        case MEDIUMINT_UNSIGNED: 
                        case INT: 
                        case INT_UNSIGNED: {
                            this.setInt(parameterIndex, parameterAsNum.intValue());
                            break;
                        }
                        case BIGINT: 
                        case BIGINT_UNSIGNED: {
                            this.setLong(parameterIndex, parameterAsNum.longValue());
                            break;
                        }
                        case FLOAT: 
                        case FLOAT_UNSIGNED: {
                            this.setFloat(parameterIndex, parameterAsNum.floatValue());
                            break;
                        }
                        case DOUBLE: 
                        case DOUBLE_UNSIGNED: {
                            this.setDouble(parameterIndex, parameterAsNum.doubleValue());
                            break;
                        }
                        case DECIMAL: 
                        case DECIMAL_UNSIGNED: {
                            if (parameterAsNum instanceof BigDecimal) {
                                BigDecimal scaledBigDecimal = null;
                                try {
                                    scaledBigDecimal = ((BigDecimal)parameterAsNum).setScale(scaleOrLength);
                                }
                                catch (ArithmeticException ex) {
                                    try {
                                        scaledBigDecimal = ((BigDecimal)parameterAsNum).setScale(scaleOrLength, 4);
                                    }
                                    catch (ArithmeticException arEx) {
                                        throw ExceptionFactory.createException(WrongArgumentException.class, Messages.getString("PreparedStatement.65", new Object[]{scaleOrLength, parameterAsNum}), this.session.getExceptionInterceptor());
                                    }
                                }
                                this.setBigDecimal(parameterIndex, scaledBigDecimal);
                                break;
                            }
                            if (parameterAsNum instanceof BigInteger) {
                                this.setBigDecimal(parameterIndex, new BigDecimal((BigInteger)parameterAsNum, scaleOrLength));
                                break;
                            }
                            this.setBigDecimal(parameterIndex, new BigDecimal(parameterAsNum.doubleValue()));
                            break;
                        }
                        case CHAR: 
                        case VARCHAR: 
                        case TINYTEXT: 
                        case TEXT: 
                        case MEDIUMTEXT: 
                        case LONGTEXT: 
                        case ENUM: 
                        case SET: 
                        case JSON: {
                            if (parameterObj instanceof BigDecimal) {
                                this.setString(parameterIndex, StringUtils.fixDecimalExponent(((BigDecimal)parameterObj).toPlainString()));
                                break;
                            }
                            this.setString(parameterIndex, parameterObj.toString());
                            break;
                        }
                        case BINARY: 
                        case GEOMETRY: 
                        case VARBINARY: 
                        case TINYBLOB: 
                        case BLOB: 
                        case MEDIUMBLOB: 
                        case LONGBLOB: {
                            this.setBytes(parameterIndex, StringUtils.getBytes(parameterObj.toString(), this.charEncoding));
                            break;
                        }
                        default: {
                            throw ExceptionFactory.createException(WrongArgumentException.class, Messages.getString("PreparedStatement.67", new Object[]{parameterObj.getClass().getName(), targetMysqlType.toString()}), this.session.getExceptionInterceptor());
                        }
                    }
                    break block163;
                }
                switch (targetMysqlType) {
                    case BOOLEAN: {
                        throw ExceptionFactory.createException(WrongArgumentException.class, Messages.getString("PreparedStatement.66", new Object[]{parameterObj.getClass().getName()}), this.session.getExceptionInterceptor());
                    }
                    case CHAR: 
                    case VARCHAR: 
                    case TINYTEXT: 
                    case TEXT: 
                    case MEDIUMTEXT: 
                    case LONGTEXT: 
                    case ENUM: 
                    case SET: 
                    case JSON: {
                        if (parameterObj instanceof BigDecimal) {
                            this.setString(parameterIndex, StringUtils.fixDecimalExponent(((BigDecimal)parameterObj).toPlainString()));
                            break;
                        }
                        if (parameterObj instanceof Clob) {
                            this.setClob(parameterIndex, (Clob)parameterObj);
                            break;
                        }
                        this.setString(parameterIndex, parameterObj.toString());
                        break;
                    }
                    case BINARY: 
                    case GEOMETRY: 
                    case VARBINARY: 
                    case TINYBLOB: 
                    case BLOB: 
                    case MEDIUMBLOB: 
                    case LONGBLOB: {
                        if (parameterObj instanceof byte[]) {
                            this.setBytes(parameterIndex, (byte[])parameterObj);
                            break;
                        }
                        if (parameterObj instanceof Blob) {
                            this.setBlob(parameterIndex, (Blob)parameterObj);
                            break;
                        }
                        this.setBytes(parameterIndex, StringUtils.getBytes(parameterObj.toString(), this.charEncoding));
                        break;
                    }
                    case UNKNOWN: {
                        this.setSerializableObject(parameterIndex, parameterObj);
                        break;
                    }
                    default: {
                        throw ExceptionFactory.createException(WrongArgumentException.class, Messages.getString("PreparedStatement.67", new Object[]{parameterObj.getClass().getName(), targetMysqlType.toString()}), this.session.getExceptionInterceptor());
                    }
                }
            }
            catch (Exception ex) {
                throw ExceptionFactory.createException(Messages.getString("PreparedStatement.17") + parameterObj.getClass().toString() + Messages.getString("PreparedStatement.18") + ex.getClass().getName() + Messages.getString("PreparedStatement.19") + ex.getMessage(), ex, this.session.getExceptionInterceptor());
            }
        }
    }

    protected final void setSerializableObject(int parameterIndex, Object parameterObj) {
        try {
            ByteArrayOutputStream bytesOut = new ByteArrayOutputStream();
            ObjectOutputStream objectOut = new ObjectOutputStream(bytesOut);
            objectOut.writeObject(parameterObj);
            objectOut.flush();
            objectOut.close();
            bytesOut.flush();
            bytesOut.close();
            byte[] buf = bytesOut.toByteArray();
            ByteArrayInputStream bytesIn = new ByteArrayInputStream(buf);
            this.setBinaryStream(parameterIndex, (InputStream)bytesIn, buf.length);
            this.bindValues[parameterIndex].setMysqlType(MysqlType.BINARY);
        }
        catch (Exception ex) {
            throw ExceptionFactory.createException(WrongArgumentException.class, Messages.getString("PreparedStatement.54") + ex.getClass().getName(), ex, this.session.getExceptionInterceptor());
        }
    }

    @Override
    public boolean isNull(int parameterIndex) {
        return this.bindValues[parameterIndex].isNull();
    }

    @Override
    public byte[] getBytesRepresentation(int parameterIndex) {
        if (this.bindValues[parameterIndex].isStream()) {
            return this.streamToBytes(parameterIndex, this.session.getPropertySet().getBooleanProperty(PropertyKey.useStreamLengthsInPrepStmts).getValue());
        }
        byte[] parameterVal = this.bindValues[parameterIndex].getByteValue();
        if (parameterVal == null) {
            return null;
        }
        return StringUtils.unquoteBytes(parameterVal);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private final byte[] streamToBytes(int parameterIndex, boolean useLength) {
        InputStream in = this.bindValues[parameterIndex].getStreamValue();
        in.mark(Integer.MAX_VALUE);
        try {
            if (this.streamConvertBuf == null) {
                this.streamConvertBuf = new byte[4096];
            }
            if (this.bindValues[parameterIndex].getStreamLength() == -1L) {
                useLength = false;
            }
            ByteArrayOutputStream bytesOut = new ByteArrayOutputStream();
            int bc = useLength ? Util.readBlock(in, this.streamConvertBuf, (int)this.bindValues[parameterIndex].getStreamLength(), null) : Util.readBlock(in, this.streamConvertBuf, null);
            int lengthLeftToRead = (int)this.bindValues[parameterIndex].getStreamLength() - bc;
            while (bc > 0) {
                bytesOut.write(this.streamConvertBuf, 0, bc);
                if (useLength) {
                    bc = Util.readBlock(in, this.streamConvertBuf, lengthLeftToRead, null);
                    if (bc <= 0) continue;
                    lengthLeftToRead -= bc;
                    continue;
                }
                bc = Util.readBlock(in, this.streamConvertBuf, null);
            }
            byte[] arrby = bytesOut.toByteArray();
            return arrby;
        }
        finally {
            try {
                in.reset();
            }
            catch (IOException iOException) {}
            if (this.session.getPropertySet().getBooleanProperty(PropertyKey.autoClosePStmtStreams).getValue().booleanValue()) {
                try {
                    in.close();
                }
                catch (IOException iOException) {}
                in = null;
            }
        }
    }

    static {
        DEFAULT_MYSQL_TYPES.put(String.class, MysqlType.VARCHAR);
        DEFAULT_MYSQL_TYPES.put(Date.class, MysqlType.DATE);
        DEFAULT_MYSQL_TYPES.put(Time.class, MysqlType.TIME);
        DEFAULT_MYSQL_TYPES.put(Timestamp.class, MysqlType.TIMESTAMP);
        DEFAULT_MYSQL_TYPES.put(Byte.class, MysqlType.INT);
        DEFAULT_MYSQL_TYPES.put(BigDecimal.class, MysqlType.DECIMAL);
        DEFAULT_MYSQL_TYPES.put(Short.class, MysqlType.SMALLINT);
        DEFAULT_MYSQL_TYPES.put(Integer.class, MysqlType.INT);
        DEFAULT_MYSQL_TYPES.put(Long.class, MysqlType.BIGINT);
        DEFAULT_MYSQL_TYPES.put(Float.class, MysqlType.FLOAT);
        DEFAULT_MYSQL_TYPES.put(Double.class, MysqlType.DOUBLE);
        DEFAULT_MYSQL_TYPES.put(byte[].class, MysqlType.BINARY);
        DEFAULT_MYSQL_TYPES.put(Boolean.class, MysqlType.BOOLEAN);
        DEFAULT_MYSQL_TYPES.put(LocalDate.class, MysqlType.DATE);
        DEFAULT_MYSQL_TYPES.put(LocalTime.class, MysqlType.TIME);
        DEFAULT_MYSQL_TYPES.put(LocalDateTime.class, MysqlType.DATETIME);
        DEFAULT_MYSQL_TYPES.put(OffsetTime.class, MysqlType.TIME);
        DEFAULT_MYSQL_TYPES.put(OffsetDateTime.class, MysqlType.TIMESTAMP);
        DEFAULT_MYSQL_TYPES.put(ZonedDateTime.class, MysqlType.TIMESTAMP);
        DEFAULT_MYSQL_TYPES.put(Duration.class, MysqlType.TIME);
        DEFAULT_MYSQL_TYPES.put(Blob.class, MysqlType.BLOB);
        DEFAULT_MYSQL_TYPES.put(Clob.class, MysqlType.TEXT);
        DEFAULT_MYSQL_TYPES.put(BigInteger.class, MysqlType.BIGINT);
        DEFAULT_MYSQL_TYPES.put(java.util.Date.class, MysqlType.TIMESTAMP);
        DEFAULT_MYSQL_TYPES.put(Calendar.class, MysqlType.TIMESTAMP);
        DEFAULT_MYSQL_TYPES.put(InputStream.class, MysqlType.BLOB);
    }
}

