/*
 * Decompiled with CFR 0.150.
 */
package com.mysql.cj.protocol.a;

import com.mysql.cj.MysqlType;
import com.mysql.cj.protocol.ColumnDefinition;
import com.mysql.cj.protocol.ProtocolEntityFactory;
import com.mysql.cj.protocol.ProtocolEntityReader;
import com.mysql.cj.protocol.a.ColumnDefinitionFactory;
import com.mysql.cj.protocol.a.NativeConstants;
import com.mysql.cj.protocol.a.NativePacketPayload;
import com.mysql.cj.protocol.a.NativeProtocol;
import com.mysql.cj.result.Field;
import com.mysql.cj.util.LazyString;

public class ColumnDefinitionReader
implements ProtocolEntityReader<ColumnDefinition, NativePacketPayload> {
    private NativeProtocol protocol;

    public ColumnDefinitionReader(NativeProtocol prot) {
        this.protocol = prot;
    }

    @Override
    public ColumnDefinition read(ProtocolEntityFactory<ColumnDefinition, NativePacketPayload> sf) {
        ColumnDefinitionFactory cdf = (ColumnDefinitionFactory)sf;
        long columnCount = cdf.getColumnCount();
        ColumnDefinition cdef = cdf.getColumnDefinitionFromCache();
        if (cdef != null && !cdf.mergeColumnDefinitions()) {
            int i = 0;
            while ((long)i < columnCount) {
                this.protocol.skipPacket();
                ++i;
            }
            return cdef;
        }
        Field[] fields = null;
        boolean checkEOF = !this.protocol.getServerSession().isEOFDeprecated();
        fields = new Field[(int)columnCount];
        int i = 0;
        while ((long)i < columnCount) {
            NativePacketPayload fieldPacket = this.protocol.readMessage((NativePacketPayload)null);
            if (checkEOF && fieldPacket.isEOFPacket()) break;
            fields[i] = this.unpackField(fieldPacket, this.protocol.getServerSession().getCharacterSetMetadata());
            ++i;
        }
        return cdf.createFromFields(fields);
    }

    protected Field unpackField(NativePacketPayload packet, String characterSetMetadata) {
        int length = (int)packet.readInteger(NativeConstants.IntegerDataType.INT_LENENC);
        packet.setPosition(packet.getPosition() + length);
        length = (int)packet.readInteger(NativeConstants.IntegerDataType.INT_LENENC);
        int offset = packet.getPosition();
        LazyString databaseName = new LazyString(packet.getByteBuffer(), offset, length, characterSetMetadata);
        packet.setPosition(packet.getPosition() + length);
        length = (int)packet.readInteger(NativeConstants.IntegerDataType.INT_LENENC);
        offset = packet.getPosition();
        LazyString tableName = new LazyString(packet.getByteBuffer(), offset, length, characterSetMetadata);
        packet.setPosition(packet.getPosition() + length);
        length = (int)packet.readInteger(NativeConstants.IntegerDataType.INT_LENENC);
        offset = packet.getPosition();
        LazyString originalTableName = new LazyString(packet.getByteBuffer(), offset, length, characterSetMetadata);
        packet.setPosition(packet.getPosition() + length);
        length = (int)packet.readInteger(NativeConstants.IntegerDataType.INT_LENENC);
        offset = packet.getPosition();
        LazyString columnName = new LazyString(packet.getByteBuffer(), offset, length, characterSetMetadata);
        packet.setPosition(packet.getPosition() + length);
        length = (int)packet.readInteger(NativeConstants.IntegerDataType.INT_LENENC);
        offset = packet.getPosition();
        LazyString originalColumnName = new LazyString(packet.getByteBuffer(), offset, length, characterSetMetadata);
        packet.setPosition(packet.getPosition() + length);
        packet.readInteger(NativeConstants.IntegerDataType.INT1);
        short collationIndex = (short)packet.readInteger(NativeConstants.IntegerDataType.INT2);
        long colLength = packet.readInteger(NativeConstants.IntegerDataType.INT4);
        int colType = (int)packet.readInteger(NativeConstants.IntegerDataType.INT1);
        short colFlag = (short)packet.readInteger(this.protocol.getServerSession().hasLongColumnInfo() ? NativeConstants.IntegerDataType.INT2 : NativeConstants.IntegerDataType.INT1);
        int colDecimals = (int)packet.readInteger(NativeConstants.IntegerDataType.INT1);
        String encoding = this.protocol.getServerSession().getEncodingForIndex(collationIndex);
        MysqlType mysqlType = NativeProtocol.findMysqlType(this.protocol.getPropertySet(), colType, colFlag, colLength, tableName, originalTableName, collationIndex, encoding);
        switch (mysqlType) {
            case TINYINT: 
            case TINYINT_UNSIGNED: 
            case SMALLINT: 
            case SMALLINT_UNSIGNED: 
            case MEDIUMINT: 
            case MEDIUMINT_UNSIGNED: 
            case INT: 
            case INT_UNSIGNED: 
            case BIGINT: 
            case BIGINT_UNSIGNED: 
            case BOOLEAN: {
                colLength = mysqlType.getPrecision().intValue();
                break;
            }
            case DECIMAL: {
                --colLength;
                if (colDecimals <= 0) break;
                --colLength;
                break;
            }
            case DECIMAL_UNSIGNED: {
                if (colDecimals <= 0) break;
                --colLength;
                break;
            }
            case FLOAT: 
            case FLOAT_UNSIGNED: 
            case DOUBLE: 
            case DOUBLE_UNSIGNED: {
                if (colDecimals != 31) break;
                colDecimals = 0;
                break;
            }
        }
        return new Field(databaseName, tableName, originalTableName, columnName, originalColumnName, colLength, colType, colFlag, colDecimals, collationIndex, encoding, mysqlType);
    }
}

