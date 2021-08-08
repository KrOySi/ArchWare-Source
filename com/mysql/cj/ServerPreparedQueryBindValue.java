/*
 * Decompiled with CFR 0.150.
 */
package com.mysql.cj;

import com.mysql.cj.AbstractQueryBindings;
import com.mysql.cj.BindValue;
import com.mysql.cj.ClientPreparedQueryBindValue;
import com.mysql.cj.Messages;
import com.mysql.cj.conf.PropertyKey;
import com.mysql.cj.conf.PropertySet;
import com.mysql.cj.conf.RuntimeProperty;
import com.mysql.cj.exceptions.CJException;
import com.mysql.cj.exceptions.ExceptionFactory;
import com.mysql.cj.exceptions.ExceptionInterceptor;
import com.mysql.cj.protocol.a.NativeConstants;
import com.mysql.cj.protocol.a.NativePacketPayload;
import com.mysql.cj.util.StringUtils;
import com.mysql.cj.util.TimeUtil;
import java.sql.Date;
import java.sql.Timestamp;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;
import java.util.Calendar;
import java.util.Locale;
import java.util.TimeZone;

public class ServerPreparedQueryBindValue
extends ClientPreparedQueryBindValue
implements BindValue {
    public long boundBeforeExecutionNum = 0L;
    public int bufferType;
    public Calendar calendar;
    PropertySet pset;
    private TimeZone defaultTimeZone;
    private TimeZone connectionTimeZone;
    private RuntimeProperty<Boolean> cacheDefaultTimeZone = null;
    protected String charEncoding = null;

    public ServerPreparedQueryBindValue(TimeZone defaultTimeZone, TimeZone connectionTimeZone, PropertySet pset) {
        this.pset = pset;
        this.defaultTimeZone = defaultTimeZone;
        this.connectionTimeZone = connectionTimeZone;
        this.cacheDefaultTimeZone = pset.getBooleanProperty(PropertyKey.cacheDefaultTimeZone);
    }

    @Override
    public ServerPreparedQueryBindValue clone() {
        return new ServerPreparedQueryBindValue(this);
    }

    private ServerPreparedQueryBindValue(ServerPreparedQueryBindValue copyMe) {
        super(copyMe);
        this.pset = copyMe.pset;
        this.defaultTimeZone = copyMe.defaultTimeZone;
        this.connectionTimeZone = copyMe.connectionTimeZone;
        this.cacheDefaultTimeZone = copyMe.cacheDefaultTimeZone;
        this.bufferType = copyMe.bufferType;
        this.calendar = copyMe.calendar;
        this.charEncoding = copyMe.charEncoding;
    }

    @Override
    public void reset() {
        super.reset();
        this.calendar = null;
        this.charEncoding = null;
    }

    public boolean resetToType(int bufType, long numberOfExecutions) {
        boolean sendTypesToServer = false;
        this.reset();
        if ((bufType != 6 || this.bufferType == 0) && this.bufferType != bufType) {
            sendTypesToServer = true;
            this.bufferType = bufType;
        }
        this.isSet = true;
        this.boundBeforeExecutionNum = numberOfExecutions;
        return sendTypesToServer;
    }

    public String toString() {
        return this.toString(false);
    }

    public String toString(boolean quoteIfNeeded) {
        if (this.isStream) {
            return "' STREAM DATA '";
        }
        if (this.isNull) {
            return "NULL";
        }
        DateTimeFormatter timeFmtWithOptMicros = new DateTimeFormatterBuilder().appendPattern("HH:mm:ss").appendFraction(ChronoField.NANO_OF_SECOND, 0, 6, true).toFormatter();
        DateTimeFormatter datetimeFmtWithOptMicros = new DateTimeFormatterBuilder().appendPattern("yyyy-MM-dd HH:mm:ss").appendFraction(ChronoField.NANO_OF_SECOND, 0, 6, true).toFormatter();
        switch (this.bufferType) {
            case 1: 
            case 2: 
            case 3: 
            case 8: {
                return String.valueOf((Long)this.value);
            }
            case 4: {
                return String.valueOf(((Float)this.value).floatValue());
            }
            case 5: {
                return String.valueOf((Double)this.value);
            }
            case 11: {
                String s = this.value instanceof LocalDateTime ? ((LocalDateTime)this.value).format(timeFmtWithOptMicros) : (this.value instanceof LocalTime ? ((LocalTime)this.value).format(timeFmtWithOptMicros) : (this.value instanceof Duration ? TimeUtil.getDurationString((Duration)this.value) : String.valueOf(this.value)));
                return "'" + s + "'";
            }
            case 10: {
                String s = this.value instanceof LocalDate ? ((LocalDate)this.value).format(TimeUtil.DATE_FORMATTER) : (this.value instanceof LocalTime ? ((LocalTime)this.value).atDate(LocalDate.of(1970, 1, 1)).format(TimeUtil.DATE_FORMATTER) : (this.value instanceof LocalDateTime ? ((LocalDateTime)this.value).format(TimeUtil.DATE_FORMATTER) : String.valueOf(this.value)));
                return "'" + s + "'";
            }
            case 7: 
            case 12: {
                String s = this.value instanceof LocalDate ? ((LocalDate)this.value).format(datetimeFmtWithOptMicros) : (this.value instanceof LocalTime ? ((LocalTime)this.value).atDate(LocalDate.of(1970, 1, 1)).format(timeFmtWithOptMicros) : (this.value instanceof LocalDateTime ? ((LocalDateTime)this.value).format(datetimeFmtWithOptMicros) : String.valueOf(this.value)));
                return "'" + s + "'";
            }
            case 15: 
            case 253: 
            case 254: {
                if (quoteIfNeeded) {
                    return "'" + String.valueOf(this.value) + "'";
                }
                return String.valueOf(this.value);
            }
        }
        if (this.value instanceof byte[]) {
            return "byte data";
        }
        if (quoteIfNeeded) {
            return "'" + String.valueOf(this.value) + "'";
        }
        return String.valueOf(this.value);
    }

    public long getBoundLength() {
        if (this.isNull) {
            return 0L;
        }
        if (this.isStream) {
            return this.streamLength;
        }
        switch (this.bufferType) {
            case 1: {
                return 1L;
            }
            case 2: {
                return 2L;
            }
            case 3: {
                return 4L;
            }
            case 8: {
                return 8L;
            }
            case 4: {
                return 4L;
            }
            case 5: {
                return 8L;
            }
            case 11: {
                return 9L;
            }
            case 10: {
                return 7L;
            }
            case 7: 
            case 12: {
                return 11L;
            }
            case 0: 
            case 15: 
            case 246: 
            case 253: 
            case 254: {
                if (this.value instanceof byte[]) {
                    return ((byte[])this.value).length;
                }
                return ((String)this.value).length();
            }
        }
        return 0L;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void storeBinding(NativePacketPayload intoPacket, boolean isLoadDataQuery, String characterEncoding, ExceptionInterceptor interceptor) {
        ServerPreparedQueryBindValue serverPreparedQueryBindValue = this;
        synchronized (serverPreparedQueryBindValue) {
            try {
                switch (this.bufferType) {
                    case 1: {
                        intoPacket.writeInteger(NativeConstants.IntegerDataType.INT1, (Long)this.value);
                        return;
                    }
                    case 2: {
                        intoPacket.writeInteger(NativeConstants.IntegerDataType.INT2, (Long)this.value);
                        return;
                    }
                    case 3: {
                        intoPacket.writeInteger(NativeConstants.IntegerDataType.INT4, (Long)this.value);
                        return;
                    }
                    case 8: {
                        intoPacket.writeInteger(NativeConstants.IntegerDataType.INT8, (Long)this.value);
                        return;
                    }
                    case 4: {
                        intoPacket.writeInteger(NativeConstants.IntegerDataType.INT4, Float.floatToIntBits(((Float)this.value).floatValue()));
                        return;
                    }
                    case 5: {
                        intoPacket.writeInteger(NativeConstants.IntegerDataType.INT8, Double.doubleToLongBits((Double)this.value));
                        return;
                    }
                    case 11: {
                        this.storeTime(intoPacket);
                        return;
                    }
                    case 10: {
                        this.storeDate(intoPacket);
                        return;
                    }
                    case 7: 
                    case 12: {
                        this.storeDateTime(intoPacket, this.bufferType);
                        return;
                    }
                    case 0: 
                    case 15: 
                    case 246: 
                    case 253: 
                    case 254: {
                        if (this.value instanceof byte[]) {
                            intoPacket.writeBytes(NativeConstants.StringSelfDataType.STRING_LENENC, (byte[])this.value);
                        } else if (!isLoadDataQuery) {
                            intoPacket.writeBytes(NativeConstants.StringSelfDataType.STRING_LENENC, StringUtils.getBytes((String)this.value, characterEncoding));
                        } else {
                            intoPacket.writeBytes(NativeConstants.StringSelfDataType.STRING_LENENC, StringUtils.getBytes((String)this.value));
                        }
                        return;
                    }
                }
            }
            catch (CJException uEE) {
                throw ExceptionFactory.createException(Messages.getString("ServerPreparedStatement.22") + characterEncoding + "'", uEE, interceptor);
            }
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private void storeDate(NativePacketPayload intoPacket) {
        ServerPreparedQueryBindValue serverPreparedQueryBindValue = this;
        synchronized (serverPreparedQueryBindValue) {
            int day;
            int month;
            int year;
            if (this.value instanceof LocalDate) {
                year = ((LocalDate)this.value).getYear();
                month = ((LocalDate)this.value).getMonthValue();
                day = ((LocalDate)this.value).getDayOfMonth();
            } else if (this.value instanceof LocalTime) {
                year = AbstractQueryBindings.DEFAULT_DATE.getYear();
                month = AbstractQueryBindings.DEFAULT_DATE.getMonthValue();
                day = AbstractQueryBindings.DEFAULT_DATE.getDayOfMonth();
            } else if (this.value instanceof LocalDateTime) {
                year = ((LocalDateTime)this.value).getYear();
                month = ((LocalDateTime)this.value).getMonthValue();
                day = ((LocalDateTime)this.value).getDayOfMonth();
            } else {
                if (this.calendar == null) {
                    this.calendar = Calendar.getInstance(this.cacheDefaultTimeZone.getValue() != false ? this.defaultTimeZone : TimeZone.getDefault(), Locale.US);
                }
                this.calendar.setTime((java.util.Date)this.value);
                this.calendar.set(11, 0);
                this.calendar.set(12, 0);
                this.calendar.set(13, 0);
                year = this.calendar.get(1);
                month = this.calendar.get(2) + 1;
                day = this.calendar.get(5);
            }
            intoPacket.ensureCapacity(5);
            intoPacket.writeInteger(NativeConstants.IntegerDataType.INT1, 4L);
            intoPacket.writeInteger(NativeConstants.IntegerDataType.INT2, year);
            intoPacket.writeInteger(NativeConstants.IntegerDataType.INT1, month);
            intoPacket.writeInteger(NativeConstants.IntegerDataType.INT1, day);
        }
    }

    private void storeTime(NativePacketPayload intoPacket) {
        int microseconds;
        int seconds;
        int minutes;
        int hours;
        int neg = 0;
        int days = 0;
        if (this.value instanceof LocalDateTime) {
            hours = ((LocalDateTime)this.value).getHour();
            minutes = ((LocalDateTime)this.value).getMinute();
            seconds = ((LocalDateTime)this.value).getSecond();
            microseconds = ((LocalDateTime)this.value).getNano() / 1000;
        } else if (this.value instanceof LocalTime) {
            hours = ((LocalTime)this.value).getHour();
            minutes = ((LocalTime)this.value).getMinute();
            seconds = ((LocalTime)this.value).getSecond();
            microseconds = ((LocalTime)this.value).getNano() / 1000;
        } else if (this.value instanceof Duration) {
            neg = ((Duration)this.value).isNegative() ? 1 : 0;
            long fullSeconds = ((Duration)this.value).abs().getSeconds();
            seconds = (int)(fullSeconds % 60L);
            long fullMinutes = fullSeconds / 60L;
            minutes = (int)(fullMinutes % 60L);
            long fullHours = fullMinutes / 60L;
            hours = (int)(fullHours % 24L);
            days = (int)(fullHours / 24L);
            microseconds = ((Duration)this.value).abs().getNano() / 1000;
        } else {
            if (this.calendar == null) {
                this.calendar = Calendar.getInstance(this.defaultTimeZone, Locale.US);
            }
            this.calendar.setTime((java.util.Date)this.value);
            hours = this.calendar.get(11);
            minutes = this.calendar.get(12);
            seconds = this.calendar.get(13);
            microseconds = this.calendar.get(14) * 1000;
        }
        intoPacket.ensureCapacity(microseconds > 0 ? 13 : 9);
        intoPacket.writeInteger(NativeConstants.IntegerDataType.INT1, microseconds > 0 ? 12L : 8L);
        intoPacket.writeInteger(NativeConstants.IntegerDataType.INT1, neg);
        intoPacket.writeInteger(NativeConstants.IntegerDataType.INT4, days);
        intoPacket.writeInteger(NativeConstants.IntegerDataType.INT1, hours);
        intoPacket.writeInteger(NativeConstants.IntegerDataType.INT1, minutes);
        intoPacket.writeInteger(NativeConstants.IntegerDataType.INT1, seconds);
        if (microseconds > 0) {
            intoPacket.writeInteger(NativeConstants.IntegerDataType.INT4, microseconds);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private void storeDateTime(NativePacketPayload intoPacket, int mysqlType) {
        ServerPreparedQueryBindValue serverPreparedQueryBindValue = this;
        synchronized (serverPreparedQueryBindValue) {
            int year = 0;
            int month = 0;
            int day = 0;
            int hours = 0;
            int minutes = 0;
            int seconds = 0;
            int microseconds = 0;
            if (this.value instanceof LocalDate) {
                year = ((LocalDate)this.value).getYear();
                month = ((LocalDate)this.value).getMonthValue();
                day = ((LocalDate)this.value).getDayOfMonth();
            } else if (this.value instanceof LocalTime) {
                year = AbstractQueryBindings.DEFAULT_DATE.getYear();
                month = AbstractQueryBindings.DEFAULT_DATE.getMonthValue();
                day = AbstractQueryBindings.DEFAULT_DATE.getDayOfMonth();
                hours = ((LocalTime)this.value).getHour();
                minutes = ((LocalTime)this.value).getMinute();
                seconds = ((LocalTime)this.value).getSecond();
                microseconds = ((LocalTime)this.value).getNano() / 1000;
            } else if (this.value instanceof LocalDateTime) {
                year = ((LocalDateTime)this.value).getYear();
                month = ((LocalDateTime)this.value).getMonthValue();
                day = ((LocalDateTime)this.value).getDayOfMonth();
                hours = ((LocalDateTime)this.value).getHour();
                minutes = ((LocalDateTime)this.value).getMinute();
                seconds = ((LocalDateTime)this.value).getSecond();
                microseconds = ((LocalDateTime)this.value).getNano() / 1000;
            } else {
                if (this.calendar == null) {
                    this.calendar = Calendar.getInstance(mysqlType == 7 && this.pset.getBooleanProperty(PropertyKey.preserveInstants).getValue() != false ? this.connectionTimeZone : this.defaultTimeZone, Locale.US);
                }
                this.calendar.setTime((java.util.Date)this.value);
                if (this.value instanceof Date) {
                    this.calendar.set(11, 0);
                    this.calendar.set(12, 0);
                    this.calendar.set(13, 0);
                }
                year = this.calendar.get(1);
                month = this.calendar.get(2) + 1;
                day = this.calendar.get(5);
                hours = this.calendar.get(11);
                minutes = this.calendar.get(12);
                seconds = this.calendar.get(13);
                microseconds = this.value instanceof Timestamp ? ((Timestamp)this.value).getNanos() / 1000 : this.calendar.get(14) * 1000;
            }
            intoPacket.ensureCapacity(microseconds > 0 ? 12 : 8);
            intoPacket.writeInteger(NativeConstants.IntegerDataType.INT1, microseconds > 0 ? 11L : 7L);
            intoPacket.writeInteger(NativeConstants.IntegerDataType.INT2, year);
            intoPacket.writeInteger(NativeConstants.IntegerDataType.INT1, month);
            intoPacket.writeInteger(NativeConstants.IntegerDataType.INT1, day);
            intoPacket.writeInteger(NativeConstants.IntegerDataType.INT1, hours);
            intoPacket.writeInteger(NativeConstants.IntegerDataType.INT1, minutes);
            intoPacket.writeInteger(NativeConstants.IntegerDataType.INT1, seconds);
            if (microseconds > 0) {
                intoPacket.writeInteger(NativeConstants.IntegerDataType.INT4, microseconds);
            }
        }
    }

    @Override
    public byte[] getByteValue() {
        if (!this.isStream) {
            return this.charEncoding != null ? StringUtils.getBytes(this.toString(), this.charEncoding) : this.toString().getBytes();
        }
        return null;
    }
}

