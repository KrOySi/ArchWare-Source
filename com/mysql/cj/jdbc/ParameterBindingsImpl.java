/*
 * Decompiled with CFR 0.150.
 */
package com.mysql.cj.jdbc;

import com.mysql.cj.BindValue;
import com.mysql.cj.CharsetMapping;
import com.mysql.cj.PreparedQuery;
import com.mysql.cj.QueryBindings;
import com.mysql.cj.Session;
import com.mysql.cj.conf.PropertyKey;
import com.mysql.cj.conf.PropertySet;
import com.mysql.cj.exceptions.ExceptionInterceptor;
import com.mysql.cj.jdbc.ParameterBindings;
import com.mysql.cj.jdbc.exceptions.SQLError;
import com.mysql.cj.jdbc.result.ResultSetFactory;
import com.mysql.cj.jdbc.result.ResultSetImpl;
import com.mysql.cj.protocol.a.result.ByteArrayRow;
import com.mysql.cj.protocol.a.result.ResultsetRowsStatic;
import com.mysql.cj.result.DefaultColumnDefinition;
import com.mysql.cj.result.Field;
import com.mysql.cj.util.StringUtils;
import java.io.InputStream;
import java.io.Reader;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.URL;
import java.sql.Array;
import java.sql.Blob;
import java.sql.Clob;
import java.sql.Date;
import java.sql.Ref;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class ParameterBindingsImpl
implements ParameterBindings {
    private QueryBindings<?> queryBindings;
    private List<Object> batchedArgs;
    private PropertySet propertySet;
    private ExceptionInterceptor exceptionInterceptor;
    private ResultSetImpl bindingsAsRs;
    private BindValue[] bindValues;

    ParameterBindingsImpl(PreparedQuery<?> query, Session session, ResultSetFactory resultSetFactory) throws SQLException {
        this.queryBindings = query.getQueryBindings();
        this.batchedArgs = query.getBatchedArgs();
        this.propertySet = session.getPropertySet();
        this.exceptionInterceptor = session.getExceptionInterceptor();
        ArrayList<ByteArrayRow> rows = new ArrayList<ByteArrayRow>();
        int paramCount = query.getParameterCount();
        this.bindValues = new BindValue[paramCount];
        for (int i = 0; i < paramCount; ++i) {
            this.bindValues[i] = this.queryBindings.getBindValues()[i].clone();
        }
        byte[][] rowData = new byte[paramCount][];
        Field[] typeMetadata = new Field[paramCount];
        for (int i = 0; i < paramCount; ++i) {
            Field parameterMetadata;
            int batchCommandIndex = query.getBatchCommandIndex();
            rowData[i] = batchCommandIndex == -1 ? this.getBytesRepresentation(i) : this.getBytesRepresentationForBatch(i, batchCommandIndex);
            int charsetIndex = 0;
            switch (this.queryBindings.getBindValues()[i].getMysqlType()) {
                case BINARY: 
                case BLOB: 
                case GEOMETRY: 
                case LONGBLOB: 
                case MEDIUMBLOB: 
                case TINYBLOB: 
                case UNKNOWN: 
                case VARBINARY: {
                    charsetIndex = 63;
                    break;
                }
                default: {
                    try {
                        charsetIndex = CharsetMapping.getCollationIndexForJavaEncoding(this.propertySet.getStringProperty(PropertyKey.characterEncoding).getValue(), session.getServerSession().getServerVersion());
                        break;
                    }
                    catch (RuntimeException ex) {
                        throw SQLError.createSQLException(ex.toString(), "S1009", ex, null);
                    }
                }
            }
            typeMetadata[i] = parameterMetadata = new Field(null, "parameter_" + (i + 1), charsetIndex, this.propertySet.getStringProperty(PropertyKey.characterEncoding).getValue(), this.queryBindings.getBindValues()[i].getMysqlType(), rowData[i].length);
        }
        rows.add(new ByteArrayRow(rowData, this.exceptionInterceptor));
        this.bindingsAsRs = resultSetFactory.createFromResultsetRows(1007, 1004, new ResultsetRowsStatic(rows, new DefaultColumnDefinition(typeMetadata)));
        this.bindingsAsRs.next();
    }

    private byte[] getBytesRepresentation(int parameterIndex) {
        return this.queryBindings.getBytesRepresentation(parameterIndex);
    }

    private byte[] getBytesRepresentationForBatch(int parameterIndex, int commandIndex) {
        Object batchedArg = this.batchedArgs.get(commandIndex);
        if (batchedArg instanceof String) {
            return StringUtils.getBytes((String)batchedArg, this.propertySet.getStringProperty(PropertyKey.characterEncoding).getValue());
        }
        return ((QueryBindings)batchedArg).getBytesRepresentation(parameterIndex);
    }

    @Override
    public Array getArray(int parameterIndex) throws SQLException {
        return this.bindingsAsRs.getArray(parameterIndex);
    }

    @Override
    public InputStream getAsciiStream(int parameterIndex) throws SQLException {
        return this.bindingsAsRs.getAsciiStream(parameterIndex);
    }

    @Override
    public BigDecimal getBigDecimal(int parameterIndex) throws SQLException {
        return this.bindingsAsRs.getBigDecimal(parameterIndex);
    }

    @Override
    public InputStream getBinaryStream(int parameterIndex) throws SQLException {
        return this.bindingsAsRs.getBinaryStream(parameterIndex);
    }

    @Override
    public Blob getBlob(int parameterIndex) throws SQLException {
        return this.bindingsAsRs.getBlob(parameterIndex);
    }

    @Override
    public boolean getBoolean(int parameterIndex) throws SQLException {
        return this.bindingsAsRs.getBoolean(parameterIndex);
    }

    @Override
    public byte getByte(int parameterIndex) throws SQLException {
        return this.bindingsAsRs.getByte(parameterIndex);
    }

    @Override
    public byte[] getBytes(int parameterIndex) throws SQLException {
        return this.bindingsAsRs.getBytes(parameterIndex);
    }

    @Override
    public Reader getCharacterStream(int parameterIndex) throws SQLException {
        return this.bindingsAsRs.getCharacterStream(parameterIndex);
    }

    @Override
    public Clob getClob(int parameterIndex) throws SQLException {
        return this.bindingsAsRs.getClob(parameterIndex);
    }

    @Override
    public Date getDate(int parameterIndex) throws SQLException {
        return this.bindingsAsRs.getDate(parameterIndex);
    }

    @Override
    public double getDouble(int parameterIndex) throws SQLException {
        return this.bindingsAsRs.getDouble(parameterIndex);
    }

    @Override
    public float getFloat(int parameterIndex) throws SQLException {
        return this.bindingsAsRs.getFloat(parameterIndex);
    }

    @Override
    public int getInt(int parameterIndex) throws SQLException {
        return this.bindingsAsRs.getInt(parameterIndex);
    }

    @Override
    public BigInteger getBigInteger(int parameterIndex) throws SQLException {
        return this.bindingsAsRs.getBigInteger(parameterIndex);
    }

    @Override
    public long getLong(int parameterIndex) throws SQLException {
        return this.bindingsAsRs.getLong(parameterIndex);
    }

    @Override
    public Reader getNCharacterStream(int parameterIndex) throws SQLException {
        return this.bindingsAsRs.getCharacterStream(parameterIndex);
    }

    @Override
    public Reader getNClob(int parameterIndex) throws SQLException {
        return this.bindingsAsRs.getCharacterStream(parameterIndex);
    }

    @Override
    public Object getObject(int parameterIndex) throws SQLException {
        if (this.bindValues[parameterIndex - 1].isNull()) {
            return null;
        }
        switch (this.queryBindings.getBindValues()[parameterIndex - 1].getMysqlType()) {
            case TINYINT: 
            case TINYINT_UNSIGNED: {
                return this.getByte(parameterIndex);
            }
            case SMALLINT: 
            case SMALLINT_UNSIGNED: {
                return this.getShort(parameterIndex);
            }
            case INT: 
            case INT_UNSIGNED: {
                return this.getInt(parameterIndex);
            }
            case BIGINT: {
                return this.getLong(parameterIndex);
            }
            case BIGINT_UNSIGNED: {
                return this.getBigInteger(parameterIndex);
            }
            case FLOAT: 
            case FLOAT_UNSIGNED: {
                return Float.valueOf(this.getFloat(parameterIndex));
            }
            case DOUBLE: 
            case DOUBLE_UNSIGNED: {
                return this.getDouble(parameterIndex);
            }
        }
        return this.bindingsAsRs.getObject(parameterIndex);
    }

    @Override
    public Ref getRef(int parameterIndex) throws SQLException {
        return this.bindingsAsRs.getRef(parameterIndex);
    }

    @Override
    public short getShort(int parameterIndex) throws SQLException {
        return this.bindingsAsRs.getShort(parameterIndex);
    }

    @Override
    public String getString(int parameterIndex) throws SQLException {
        return this.bindingsAsRs.getString(parameterIndex);
    }

    @Override
    public Time getTime(int parameterIndex) throws SQLException {
        return this.bindingsAsRs.getTime(parameterIndex);
    }

    @Override
    public Timestamp getTimestamp(int parameterIndex) throws SQLException {
        return this.bindingsAsRs.getTimestamp(parameterIndex);
    }

    @Override
    public URL getURL(int parameterIndex) throws SQLException {
        return this.bindingsAsRs.getURL(parameterIndex);
    }

    @Override
    public boolean isNull(int parameterIndex) throws SQLException {
        return this.queryBindings.isNull(parameterIndex - 1);
    }
}

