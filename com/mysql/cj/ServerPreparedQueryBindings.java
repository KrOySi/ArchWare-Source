/*
 * Decompiled with CFR 0.150.
 */
package com.mysql.cj;

import com.mysql.cj.AbstractQueryBindings;
import com.mysql.cj.Messages;
import com.mysql.cj.MysqlType;
import com.mysql.cj.ServerPreparedQueryBindValue;
import com.mysql.cj.Session;
import com.mysql.cj.conf.PropertyKey;
import com.mysql.cj.exceptions.ExceptionFactory;
import com.mysql.cj.exceptions.WrongArgumentException;
import com.mysql.cj.util.StringUtils;
import com.mysql.cj.util.TimeUtil;
import java.io.InputStream;
import java.io.Reader;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Blob;
import java.sql.Clob;
import java.sql.Date;
import java.sql.NClob;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Calendar;
import java.util.concurrent.atomic.AtomicBoolean;

public class ServerPreparedQueryBindings
extends AbstractQueryBindings<ServerPreparedQueryBindValue> {
    private AtomicBoolean sendTypesToServer = new AtomicBoolean(false);
    private boolean longParameterSwitchDetected = false;

    public ServerPreparedQueryBindings(int parameterCount, Session sess) {
        super(parameterCount, sess);
    }

    @Override
    protected void initBindValues(int parameterCount) {
        this.bindValues = new ServerPreparedQueryBindValue[parameterCount];
        for (int i = 0; i < parameterCount; ++i) {
            ((ServerPreparedQueryBindValue[])this.bindValues)[i] = new ServerPreparedQueryBindValue(this.session.getServerSession().getDefaultTimeZone(), this.session.getServerSession().getSessionTimeZone(), this.session.getPropertySet());
        }
    }

    @Override
    public ServerPreparedQueryBindings clone() {
        ServerPreparedQueryBindings newBindings = new ServerPreparedQueryBindings(((ServerPreparedQueryBindValue[])this.bindValues).length, this.session);
        ServerPreparedQueryBindValue[] bvs = new ServerPreparedQueryBindValue[((ServerPreparedQueryBindValue[])this.bindValues).length];
        for (int i = 0; i < ((ServerPreparedQueryBindValue[])this.bindValues).length; ++i) {
            bvs[i] = ((ServerPreparedQueryBindValue[])this.bindValues)[i].clone();
        }
        newBindings.bindValues = bvs;
        newBindings.sendTypesToServer = this.sendTypesToServer;
        newBindings.longParameterSwitchDetected = this.longParameterSwitchDetected;
        newBindings.isLoadDataQuery = this.isLoadDataQuery;
        return newBindings;
    }

    public ServerPreparedQueryBindValue getBinding(int parameterIndex, boolean forLongData) {
        if (((ServerPreparedQueryBindValue[])this.bindValues)[parameterIndex] != null && ((ServerPreparedQueryBindValue[])this.bindValues)[parameterIndex].isStream && !forLongData) {
            this.longParameterSwitchDetected = true;
        }
        return ((ServerPreparedQueryBindValue[])this.bindValues)[parameterIndex];
    }

    @Override
    public void checkParameterSet(int columnIndex) {
        if (!((ServerPreparedQueryBindValue[])this.bindValues)[columnIndex].isSet()) {
            throw ExceptionFactory.createException(WrongArgumentException.class, Messages.getString("ServerPreparedStatement.13") + (columnIndex + 1) + Messages.getString("ServerPreparedStatement.14"));
        }
    }

    public AtomicBoolean getSendTypesToServer() {
        return this.sendTypesToServer;
    }

    public boolean isLongParameterSwitchDetected() {
        return this.longParameterSwitchDetected;
    }

    public void setLongParameterSwitchDetected(boolean longParameterSwitchDetected) {
        this.longParameterSwitchDetected = longParameterSwitchDetected;
    }

    @Override
    public void setAsciiStream(int parameterIndex, InputStream x) {
        this.setAsciiStream(parameterIndex, x, -1);
    }

    @Override
    public void setAsciiStream(int parameterIndex, InputStream x, int length) {
        if (x == null) {
            this.setNull(parameterIndex);
        } else {
            ServerPreparedQueryBindValue binding = this.getBinding(parameterIndex, true);
            this.sendTypesToServer.compareAndSet(false, binding.resetToType(252, this.numberOfExecutions));
            binding.value = x;
            binding.isStream = true;
            binding.streamLength = (Boolean)this.useStreamLengthsInPrepStmts.getValue() != false ? (long)length : -1L;
        }
    }

    @Override
    public void setAsciiStream(int parameterIndex, InputStream x, long length) {
        this.setAsciiStream(parameterIndex, x, (int)length);
        ((ServerPreparedQueryBindValue[])this.bindValues)[parameterIndex].setMysqlType(MysqlType.TEXT);
    }

    @Override
    public void setBigDecimal(int parameterIndex, BigDecimal x) {
        if (x == null) {
            this.setNull(parameterIndex);
        } else {
            ServerPreparedQueryBindValue binding = this.getBinding(parameterIndex, false);
            this.sendTypesToServer.compareAndSet(false, binding.resetToType(246, this.numberOfExecutions));
            binding.value = StringUtils.fixDecimalExponent(x.toPlainString());
            binding.parameterType = MysqlType.DECIMAL;
        }
    }

    @Override
    public void setBigInteger(int parameterIndex, BigInteger x) {
        this.setValue(parameterIndex, x.toString(), MysqlType.BIGINT_UNSIGNED);
    }

    @Override
    public void setBinaryStream(int parameterIndex, InputStream x) {
        this.setBinaryStream(parameterIndex, x, -1);
    }

    @Override
    public void setBinaryStream(int parameterIndex, InputStream x, int length) {
        if (x == null) {
            this.setNull(parameterIndex);
        } else {
            ServerPreparedQueryBindValue binding = this.getBinding(parameterIndex, true);
            this.sendTypesToServer.compareAndSet(false, binding.resetToType(252, this.numberOfExecutions));
            binding.value = x;
            binding.isStream = true;
            binding.streamLength = (Boolean)this.useStreamLengthsInPrepStmts.getValue() != false ? (long)length : -1L;
            binding.parameterType = MysqlType.BLOB;
        }
    }

    @Override
    public void setBinaryStream(int parameterIndex, InputStream x, long length) {
        this.setBinaryStream(parameterIndex, x, (int)length);
    }

    @Override
    public void setBlob(int parameterIndex, InputStream inputStream) {
        this.setBinaryStream(parameterIndex, inputStream);
    }

    @Override
    public void setBlob(int parameterIndex, InputStream inputStream, long length) {
        this.setBinaryStream(parameterIndex, inputStream, (int)length);
    }

    @Override
    public void setBlob(int parameterIndex, Blob x) {
        if (x == null) {
            this.setNull(parameterIndex);
        } else {
            try {
                ServerPreparedQueryBindValue binding = this.getBinding(parameterIndex, true);
                this.sendTypesToServer.compareAndSet(false, binding.resetToType(252, this.numberOfExecutions));
                binding.value = x;
                binding.isStream = true;
                binding.streamLength = (Boolean)this.useStreamLengthsInPrepStmts.getValue() != false ? x.length() : -1L;
                binding.parameterType = MysqlType.BLOB;
            }
            catch (Throwable t) {
                throw ExceptionFactory.createException(t.getMessage(), t);
            }
        }
    }

    @Override
    public void setBoolean(int parameterIndex, boolean x) {
        ServerPreparedQueryBindValue binding = this.getBinding(parameterIndex, false);
        this.sendTypesToServer.compareAndSet(false, binding.resetToType(1, this.numberOfExecutions));
        binding.value = x ? 1L : 0L;
        binding.parameterType = MysqlType.BOOLEAN;
    }

    @Override
    public void setByte(int parameterIndex, byte x) {
        ServerPreparedQueryBindValue binding = this.getBinding(parameterIndex, false);
        this.sendTypesToServer.compareAndSet(false, binding.resetToType(1, this.numberOfExecutions));
        binding.value = (long)x;
        binding.parameterType = MysqlType.TINYINT;
    }

    @Override
    public void setBytes(int parameterIndex, byte[] x) {
        if (x == null) {
            this.setNull(parameterIndex);
        } else {
            ServerPreparedQueryBindValue binding = this.getBinding(parameterIndex, false);
            this.sendTypesToServer.compareAndSet(false, binding.resetToType(253, this.numberOfExecutions));
            binding.value = x;
            binding.parameterType = MysqlType.BINARY;
        }
    }

    @Override
    public void setBytes(int parameterIndex, byte[] x, boolean checkForIntroducer, boolean escapeForMBChars) {
        this.setBytes(parameterIndex, x);
    }

    @Override
    public void setBytesNoEscape(int parameterIndex, byte[] parameterAsBytes) {
        this.setBytes(parameterIndex, parameterAsBytes);
    }

    @Override
    public void setBytesNoEscapeNoQuotes(int parameterIndex, byte[] parameterAsBytes) {
        this.setBytes(parameterIndex, parameterAsBytes);
    }

    @Override
    public void setCharacterStream(int parameterIndex, Reader reader) {
        this.setCharacterStream(parameterIndex, reader, -1);
    }

    @Override
    public void setCharacterStream(int parameterIndex, Reader reader, int length) {
        if (reader == null) {
            this.setNull(parameterIndex);
        } else {
            ServerPreparedQueryBindValue binding = this.getBinding(parameterIndex, true);
            this.sendTypesToServer.compareAndSet(false, binding.resetToType(252, this.numberOfExecutions));
            binding.value = reader;
            binding.isStream = true;
            binding.streamLength = (Boolean)this.useStreamLengthsInPrepStmts.getValue() != false ? (long)length : -1L;
            binding.parameterType = MysqlType.TEXT;
        }
    }

    @Override
    public void setCharacterStream(int parameterIndex, Reader reader, long length) {
        this.setCharacterStream(parameterIndex, reader, (int)length);
    }

    @Override
    public void setClob(int parameterIndex, Reader reader) {
        this.setCharacterStream(parameterIndex, reader);
    }

    @Override
    public void setClob(int parameterIndex, Reader reader, long length) {
        this.setCharacterStream(parameterIndex, reader, length);
    }

    @Override
    public void setClob(int parameterIndex, Clob x) {
        if (x == null) {
            this.setNull(parameterIndex);
        } else {
            try {
                ServerPreparedQueryBindValue binding = this.getBinding(parameterIndex, true);
                this.sendTypesToServer.compareAndSet(false, binding.resetToType(252, this.numberOfExecutions));
                binding.value = x.getCharacterStream();
                binding.isStream = true;
                binding.streamLength = (Boolean)this.useStreamLengthsInPrepStmts.getValue() != false ? x.length() : -1L;
                binding.parameterType = MysqlType.TEXT;
            }
            catch (Throwable t) {
                throw ExceptionFactory.createException(t.getMessage(), t);
            }
        }
    }

    @Override
    public void setDate(int parameterIndex, Date x) {
        this.setDate(parameterIndex, x, null);
    }

    @Override
    public void setDate(int parameterIndex, Date x, Calendar cal) {
        if (x == null) {
            this.setNull(parameterIndex);
        } else {
            ServerPreparedQueryBindValue binding = this.getBinding(parameterIndex, false);
            this.sendTypesToServer.compareAndSet(false, binding.resetToType(10, this.numberOfExecutions));
            binding.value = x;
            binding.calendar = cal == null ? null : (Calendar)cal.clone();
            binding.parameterType = MysqlType.DATE;
        }
    }

    @Override
    public void setDouble(int parameterIndex, double x) {
        if (!this.session.getPropertySet().getBooleanProperty(PropertyKey.allowNanAndInf).getValue().booleanValue() && (x == Double.POSITIVE_INFINITY || x == Double.NEGATIVE_INFINITY || Double.isNaN(x))) {
            throw ExceptionFactory.createException(WrongArgumentException.class, Messages.getString("PreparedStatement.64", new Object[]{x}), this.session.getExceptionInterceptor());
        }
        ServerPreparedQueryBindValue binding = this.getBinding(parameterIndex, false);
        this.sendTypesToServer.compareAndSet(false, binding.resetToType(5, this.numberOfExecutions));
        binding.value = x;
        binding.parameterType = MysqlType.DOUBLE;
    }

    @Override
    public void setFloat(int parameterIndex, float x) {
        ServerPreparedQueryBindValue binding = this.getBinding(parameterIndex, false);
        this.sendTypesToServer.compareAndSet(false, binding.resetToType(4, this.numberOfExecutions));
        binding.value = Float.valueOf(x);
        binding.parameterType = MysqlType.FLOAT;
    }

    @Override
    public void setInt(int parameterIndex, int x) {
        ServerPreparedQueryBindValue binding = this.getBinding(parameterIndex, false);
        this.sendTypesToServer.compareAndSet(false, binding.resetToType(3, this.numberOfExecutions));
        binding.value = (long)x;
        binding.parameterType = MysqlType.INT;
    }

    @Override
    public void setLocalDate(int parameterIndex, LocalDate x, MysqlType targetMysqlType) {
        ServerPreparedQueryBindValue binding = this.getBinding(parameterIndex, false);
        this.sendTypesToServer.compareAndSet(false, binding.resetToType(10, this.numberOfExecutions));
        binding.parameterType = targetMysqlType;
        binding.value = x;
    }

    @Override
    public void setLocalTime(int parameterIndex, LocalTime x, MysqlType targetMysqlType) {
        ServerPreparedQueryBindValue binding = this.getBinding(parameterIndex, false);
        if (targetMysqlType == MysqlType.DATE) {
            this.sendTypesToServer.compareAndSet(false, binding.resetToType(10, this.numberOfExecutions));
            binding.parameterType = targetMysqlType;
            binding.value = DEFAULT_DATE;
            return;
        }
        if (!this.session.getServerSession().getCapabilities().serverSupportsFracSecs() || !((Boolean)this.sendFractionalSeconds.getValue()).booleanValue()) {
            if (x.getNano() > 0) {
                x = x.withNano(0);
            }
        } else {
            int fractLen = 6;
            if (this.columnDefinition != null && parameterIndex <= this.columnDefinition.getFields().length && parameterIndex >= 0) {
                fractLen = this.columnDefinition.getFields()[parameterIndex].getDecimals();
            }
            x = TimeUtil.adjustNanosPrecision(x, fractLen, !this.session.getServerSession().isServerTruncatesFracSecs());
        }
        if (targetMysqlType == MysqlType.TIME) {
            this.sendTypesToServer.compareAndSet(false, binding.resetToType(11, this.numberOfExecutions));
        } else {
            this.sendTypesToServer.compareAndSet(false, binding.resetToType(12, this.numberOfExecutions));
        }
        binding.parameterType = targetMysqlType;
        binding.value = x;
    }

    @Override
    public void setLocalDateTime(int parameterIndex, LocalDateTime x, MysqlType targetMysqlType) {
        ServerPreparedQueryBindValue binding = this.getBinding(parameterIndex, false);
        if (targetMysqlType == MysqlType.DATE) {
            x = LocalDateTime.of(x.toLocalDate(), DEFAULT_TIME);
            this.sendTypesToServer.compareAndSet(false, binding.resetToType(10, this.numberOfExecutions));
        } else {
            int fractLen = 6;
            if (this.columnDefinition != null && parameterIndex <= this.columnDefinition.getFields().length && parameterIndex >= 0) {
                fractLen = this.columnDefinition.getFields()[parameterIndex].getDecimals();
            }
            if (!this.session.getServerSession().getCapabilities().serverSupportsFracSecs() || !((Boolean)this.sendFractionalSeconds.getValue()).booleanValue()) {
                if (x.getNano() > 0) {
                    x = x.withNano(0);
                }
            } else {
                x = TimeUtil.adjustNanosPrecision(x, fractLen, !this.session.getServerSession().isServerTruncatesFracSecs());
            }
            if (targetMysqlType == MysqlType.TIME) {
                this.sendTypesToServer.compareAndSet(false, binding.resetToType(11, this.numberOfExecutions));
            } else {
                this.sendTypesToServer.compareAndSet(false, binding.resetToType(12, this.numberOfExecutions));
            }
        }
        binding.parameterType = targetMysqlType;
        binding.value = x;
    }

    @Override
    public void setLong(int parameterIndex, long x) {
        ServerPreparedQueryBindValue binding = this.getBinding(parameterIndex, false);
        this.sendTypesToServer.compareAndSet(false, binding.resetToType(8, this.numberOfExecutions));
        binding.value = x;
        binding.parameterType = MysqlType.BIGINT;
    }

    @Override
    public void setNCharacterStream(int parameterIndex, Reader value) {
        this.setNCharacterStream(parameterIndex, value, -1L);
    }

    @Override
    public void setNCharacterStream(int parameterIndex, Reader reader, long length) {
        if (!this.charEncoding.equalsIgnoreCase("UTF-8") && !this.charEncoding.equalsIgnoreCase("utf8")) {
            throw ExceptionFactory.createException(Messages.getString("ServerPreparedStatement.28"), this.session.getExceptionInterceptor());
        }
        if (reader == null) {
            this.setNull(parameterIndex);
        } else {
            ServerPreparedQueryBindValue binding = this.getBinding(parameterIndex, true);
            this.sendTypesToServer.compareAndSet(false, binding.resetToType(252, this.numberOfExecutions));
            binding.value = reader;
            binding.isStream = true;
            binding.streamLength = (Boolean)this.useStreamLengthsInPrepStmts.getValue() != false ? length : -1L;
            binding.parameterType = MysqlType.TEXT;
        }
    }

    @Override
    public void setNClob(int parameterIndex, Reader reader) {
        this.setNCharacterStream(parameterIndex, reader);
    }

    @Override
    public void setNClob(int parameterIndex, Reader reader, long length) {
        if (!this.charEncoding.equalsIgnoreCase("UTF-8") && !this.charEncoding.equalsIgnoreCase("utf8")) {
            throw ExceptionFactory.createException(Messages.getString("ServerPreparedStatement.29"), this.session.getExceptionInterceptor());
        }
        this.setNCharacterStream(parameterIndex, reader, length);
    }

    @Override
    public void setNClob(int parameterIndex, NClob value) {
        try {
            this.setNClob(parameterIndex, value.getCharacterStream(), (Boolean)this.useStreamLengthsInPrepStmts.getValue() != false ? value.length() : -1L);
        }
        catch (Throwable t) {
            throw ExceptionFactory.createException(t.getMessage(), t, this.session.getExceptionInterceptor());
        }
    }

    @Override
    public void setNString(int parameterIndex, String x) {
        if (!this.charEncoding.equalsIgnoreCase("UTF-8") && !this.charEncoding.equalsIgnoreCase("utf8")) {
            throw ExceptionFactory.createException(Messages.getString("ServerPreparedStatement.30"), this.session.getExceptionInterceptor());
        }
        this.setString(parameterIndex, x);
    }

    @Override
    public void setNull(int parameterIndex) {
        ServerPreparedQueryBindValue binding = this.getBinding(parameterIndex, false);
        this.sendTypesToServer.compareAndSet(false, binding.resetToType(6, this.numberOfExecutions));
        binding.setNull(true);
    }

    @Override
    public void setShort(int parameterIndex, short x) {
        ServerPreparedQueryBindValue binding = this.getBinding(parameterIndex, false);
        this.sendTypesToServer.compareAndSet(false, binding.resetToType(2, this.numberOfExecutions));
        binding.value = (long)x;
        binding.parameterType = MysqlType.SMALLINT;
    }

    @Override
    public void setString(int parameterIndex, String x) {
        if (x == null) {
            this.setNull(parameterIndex);
        } else {
            ServerPreparedQueryBindValue binding = this.getBinding(parameterIndex, false);
            this.sendTypesToServer.compareAndSet(false, binding.resetToType(253, this.numberOfExecutions));
            binding.value = x;
            binding.charEncoding = this.charEncoding;
            binding.parameterType = MysqlType.VARCHAR;
        }
    }

    @Override
    public void setTime(int parameterIndex, Time x, Calendar cal) {
        if (x == null) {
            this.setNull(parameterIndex);
        } else {
            if (!(this.session.getServerSession().getCapabilities().serverSupportsFracSecs() && ((Boolean)this.sendFractionalSeconds.getValue()).booleanValue() && ((Boolean)this.sendFractionalSecondsForTime.getValue()).booleanValue())) {
                x = TimeUtil.truncateFractionalSeconds(x);
            }
            ServerPreparedQueryBindValue binding = this.getBinding(parameterIndex, false);
            this.sendTypesToServer.compareAndSet(false, binding.resetToType(11, this.numberOfExecutions));
            binding.value = x;
            binding.calendar = cal == null ? null : (Calendar)cal.clone();
            binding.parameterType = MysqlType.TIME;
        }
    }

    @Override
    public void setTime(int parameterIndex, Time x) {
        this.setTime(parameterIndex, x, null);
    }

    @Override
    public void bindTimestamp(int parameterIndex, Timestamp x, Calendar targetCalendar, int fractionalLength, MysqlType targetMysqlType) {
        ServerPreparedQueryBindValue binding = this.getBinding(parameterIndex, false);
        this.sendTypesToServer.compareAndSet(false, binding.resetToType(targetMysqlType == MysqlType.TIMESTAMP ? 7 : 12, this.numberOfExecutions));
        if (fractionalLength < 0) {
            fractionalLength = 6;
        }
        x = TimeUtil.adjustNanosPrecision(x, fractionalLength, !this.session.getServerSession().isServerTruncatesFracSecs());
        binding.value = x;
        binding.calendar = targetCalendar == null ? null : (Calendar)targetCalendar.clone();
        binding.parameterType = targetMysqlType;
    }

    @Override
    public void setDuration(int parameterIndex, Duration x, MysqlType targetMysqlType) {
        ServerPreparedQueryBindValue binding = this.getBinding(parameterIndex, false);
        if (!this.session.getServerSession().getCapabilities().serverSupportsFracSecs() || !((Boolean)this.sendFractionalSeconds.getValue()).booleanValue()) {
            if (x.getNano() > 0) {
                x = x.isNegative() ? x.plusSeconds(1L).withNanos(0) : x.withNanos(0);
            }
        } else {
            int fractLen = 6;
            if (this.columnDefinition != null && parameterIndex <= this.columnDefinition.getFields().length && parameterIndex >= 0) {
                fractLen = this.columnDefinition.getFields()[parameterIndex].getDecimals();
            }
            x = TimeUtil.adjustNanosPrecision(x, fractLen, !this.session.getServerSession().isServerTruncatesFracSecs());
        }
        if (targetMysqlType == MysqlType.TIME) {
            this.sendTypesToServer.compareAndSet(false, binding.resetToType(11, this.numberOfExecutions));
        } else {
            this.sendTypesToServer.compareAndSet(false, binding.resetToType(12, this.numberOfExecutions));
        }
        binding.parameterType = targetMysqlType;
        binding.value = x;
    }
}

