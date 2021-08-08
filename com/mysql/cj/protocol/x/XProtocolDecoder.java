/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  com.google.protobuf.CodedInputStream
 */
package com.mysql.cj.protocol.x;

import com.google.protobuf.CodedInputStream;
import com.mysql.cj.exceptions.AssertionFailedException;
import com.mysql.cj.exceptions.DataReadException;
import com.mysql.cj.protocol.InternalDate;
import com.mysql.cj.protocol.InternalTime;
import com.mysql.cj.protocol.InternalTimestamp;
import com.mysql.cj.protocol.ValueDecoder;
import com.mysql.cj.result.Field;
import com.mysql.cj.result.ValueFactory;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;

public class XProtocolDecoder
implements ValueDecoder {
    public static XProtocolDecoder instance = new XProtocolDecoder();

    @Override
    public <T> T decodeDate(byte[] bytes, int offset, int length, ValueFactory<T> vf) {
        return this.decodeTimestamp(bytes, offset, length, 0, vf);
    }

    @Override
    public <T> T decodeTime(byte[] bytes, int offset, int length, int scale, ValueFactory<T> vf) {
        try {
            CodedInputStream inputStream = CodedInputStream.newInstance((byte[])bytes, (int)offset, (int)length);
            boolean negative = inputStream.readRawByte() > 0;
            int hours = 0;
            int minutes = 0;
            int seconds = 0;
            int nanos = 0;
            if (!inputStream.isAtEnd()) {
                hours = (int)inputStream.readInt64();
                if (!inputStream.isAtEnd()) {
                    minutes = (int)inputStream.readInt64();
                    if (!inputStream.isAtEnd()) {
                        seconds = (int)inputStream.readInt64();
                        if (!inputStream.isAtEnd()) {
                            nanos = 1000 * (int)inputStream.readInt64();
                        }
                    }
                }
            }
            return vf.createFromTime(new InternalTime(negative ? -1 * hours : hours, minutes, seconds, nanos, scale));
        }
        catch (IOException e) {
            throw new DataReadException(e);
        }
    }

    @Override
    public <T> T decodeTimestamp(byte[] bytes, int offset, int length, int scale, ValueFactory<T> vf) {
        try {
            CodedInputStream inputStream = CodedInputStream.newInstance((byte[])bytes, (int)offset, (int)length);
            int year = (int)inputStream.readUInt64();
            int month = (int)inputStream.readUInt64();
            int day = (int)inputStream.readUInt64();
            if (inputStream.getBytesUntilLimit() > 0) {
                int hours = 0;
                int minutes = 0;
                int seconds = 0;
                int nanos = 0;
                if (!inputStream.isAtEnd()) {
                    hours = (int)inputStream.readInt64();
                    if (!inputStream.isAtEnd()) {
                        minutes = (int)inputStream.readInt64();
                        if (!inputStream.isAtEnd()) {
                            seconds = (int)inputStream.readInt64();
                            if (!inputStream.isAtEnd()) {
                                nanos = 1000 * (int)inputStream.readInt64();
                            }
                        }
                    }
                }
                return vf.createFromTimestamp(new InternalTimestamp(year, month, day, hours, minutes, seconds, nanos, scale));
            }
            return vf.createFromDate(new InternalDate(year, month, day));
        }
        catch (IOException e) {
            throw new DataReadException(e);
        }
    }

    @Override
    public <T> T decodeDatetime(byte[] bytes, int offset, int length, int scale, ValueFactory<T> vf) {
        try {
            CodedInputStream inputStream = CodedInputStream.newInstance((byte[])bytes, (int)offset, (int)length);
            int year = (int)inputStream.readUInt64();
            int month = (int)inputStream.readUInt64();
            int day = (int)inputStream.readUInt64();
            if (inputStream.getBytesUntilLimit() > 0) {
                int hours = 0;
                int minutes = 0;
                int seconds = 0;
                int nanos = 0;
                if (!inputStream.isAtEnd()) {
                    hours = (int)inputStream.readInt64();
                    if (!inputStream.isAtEnd()) {
                        minutes = (int)inputStream.readInt64();
                        if (!inputStream.isAtEnd()) {
                            seconds = (int)inputStream.readInt64();
                            if (!inputStream.isAtEnd()) {
                                nanos = 1000 * (int)inputStream.readInt64();
                            }
                        }
                    }
                }
                return vf.createFromDatetime(new InternalTimestamp(year, month, day, hours, minutes, seconds, nanos, scale));
            }
            return vf.createFromDate(new InternalDate(year, month, day));
        }
        catch (IOException e) {
            throw new DataReadException(e);
        }
    }

    @Override
    public <T> T decodeInt1(byte[] bytes, int offset, int length, ValueFactory<T> vf) {
        return null;
    }

    @Override
    public <T> T decodeUInt1(byte[] bytes, int offset, int length, ValueFactory<T> vf) {
        return null;
    }

    @Override
    public <T> T decodeInt2(byte[] bytes, int offset, int length, ValueFactory<T> vf) {
        return null;
    }

    @Override
    public <T> T decodeUInt2(byte[] bytes, int offset, int length, ValueFactory<T> vf) {
        return null;
    }

    @Override
    public <T> T decodeInt4(byte[] bytes, int offset, int length, ValueFactory<T> vf) {
        return null;
    }

    @Override
    public <T> T decodeUInt4(byte[] bytes, int offset, int length, ValueFactory<T> vf) {
        return null;
    }

    @Override
    public <T> T decodeInt8(byte[] bytes, int offset, int length, ValueFactory<T> vf) {
        try {
            return vf.createFromLong(CodedInputStream.newInstance((byte[])bytes, (int)offset, (int)length).readSInt64());
        }
        catch (IOException e) {
            throw new DataReadException(e);
        }
    }

    @Override
    public <T> T decodeUInt8(byte[] bytes, int offset, int length, ValueFactory<T> vf) {
        try {
            BigInteger v = new BigInteger(ByteBuffer.allocate(9).put((byte)0).putLong(CodedInputStream.newInstance((byte[])bytes, (int)offset, (int)length).readUInt64()).array());
            return vf.createFromBigInteger(v);
        }
        catch (IOException e) {
            throw new DataReadException(e);
        }
    }

    @Override
    public <T> T decodeFloat(byte[] bytes, int offset, int length, ValueFactory<T> vf) {
        try {
            return vf.createFromDouble(CodedInputStream.newInstance((byte[])bytes, (int)offset, (int)length).readFloat());
        }
        catch (IOException e) {
            throw new DataReadException(e);
        }
    }

    @Override
    public <T> T decodeDouble(byte[] bytes, int offset, int length, ValueFactory<T> vf) {
        try {
            return vf.createFromDouble(CodedInputStream.newInstance((byte[])bytes, (int)offset, (int)length).readDouble());
        }
        catch (IOException e) {
            throw new DataReadException(e);
        }
    }

    @Override
    public <T> T decodeDecimal(byte[] bytes, int offset, int length, ValueFactory<T> vf) {
        try {
            CodedInputStream inputStream = CodedInputStream.newInstance((byte[])bytes, (int)offset, (int)length);
            byte scale = inputStream.readRawByte();
            CharBuffer unscaledString = CharBuffer.allocate(2 * inputStream.getBytesUntilLimit());
            unscaledString.position(1);
            byte sign = 0;
            while (true) {
                int b;
                if ((b = 0xFF & inputStream.readRawByte()) >> 4 > 9) {
                    sign = (byte)(b >> 4);
                    break;
                }
                unscaledString.append((char)((b >> 4) + 48));
                if ((b & 0xF) > 9) {
                    sign = (byte)(b & 0xF);
                    break;
                }
                unscaledString.append((char)((b & 0xF) + 48));
            }
            if (inputStream.getBytesUntilLimit() > 0) {
                throw AssertionFailedException.shouldNotHappen("Did not read all bytes while decoding decimal. Bytes left: " + inputStream.getBytesUntilLimit());
            }
            switch (sign) {
                case 10: 
                case 12: 
                case 14: 
                case 15: {
                    unscaledString.put(0, '+');
                    break;
                }
                case 11: 
                case 13: {
                    unscaledString.put(0, '-');
                }
            }
            int characters = unscaledString.position();
            unscaledString.clear();
            BigInteger unscaled = new BigInteger(unscaledString.subSequence(0, characters).toString());
            return vf.createFromBigDecimal(new BigDecimal(unscaled, scale));
        }
        catch (IOException e) {
            throw new DataReadException(e);
        }
    }

    @Override
    public <T> T decodeByteArray(byte[] bytes, int offset, int length, Field f, ValueFactory<T> vf) {
        try {
            CodedInputStream inputStream = CodedInputStream.newInstance((byte[])bytes, (int)offset, (int)length);
            int size = inputStream.getBytesUntilLimit();
            return vf.createFromBytes(inputStream.readRawBytes(--size), 0, size, f);
        }
        catch (IOException e) {
            throw new DataReadException(e);
        }
    }

    @Override
    public <T> T decodeBit(byte[] bytes, int offset, int length, ValueFactory<T> vf) {
        try {
            byte[] buf = ByteBuffer.allocate(9).put((byte)0).putLong(CodedInputStream.newInstance((byte[])bytes, (int)offset, (int)length).readUInt64()).array();
            return vf.createFromBit(buf, 0, 9);
        }
        catch (IOException e) {
            throw new DataReadException(e);
        }
    }

    @Override
    public <T> T decodeSet(byte[] bytes, int offset, int length, Field f, ValueFactory<T> vf) {
        try {
            CodedInputStream inputStream = CodedInputStream.newInstance((byte[])bytes, (int)offset, (int)length);
            StringBuilder vals = new StringBuilder();
            while (inputStream.getBytesUntilLimit() > 0) {
                if (vals.length() > 0) {
                    vals.append(",");
                }
                long valLen = inputStream.readUInt64();
                vals.append(new String(inputStream.readRawBytes((int)valLen)));
            }
            byte[] buf = vals.toString().getBytes();
            return vf.createFromBytes(buf, 0, buf.length, f);
        }
        catch (IOException e) {
            throw new DataReadException(e);
        }
    }

    @Override
    public <T> T decodeYear(byte[] bytes, int offset, int length, ValueFactory<T> vf) {
        return null;
    }
}

