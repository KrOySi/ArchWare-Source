/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  com.google.protobuf.AbstractMessage
 *  com.google.protobuf.AbstractMessage$BuilderParent
 *  com.google.protobuf.AbstractParser
 *  com.google.protobuf.ByteString
 *  com.google.protobuf.CodedInputStream
 *  com.google.protobuf.CodedOutputStream
 *  com.google.protobuf.Descriptors$Descriptor
 *  com.google.protobuf.Descriptors$EnumDescriptor
 *  com.google.protobuf.Descriptors$EnumValueDescriptor
 *  com.google.protobuf.Descriptors$FieldDescriptor
 *  com.google.protobuf.Descriptors$FileDescriptor
 *  com.google.protobuf.Descriptors$OneofDescriptor
 *  com.google.protobuf.ExtensionRegistry
 *  com.google.protobuf.ExtensionRegistryLite
 *  com.google.protobuf.GeneratedMessageV3
 *  com.google.protobuf.GeneratedMessageV3$Builder
 *  com.google.protobuf.GeneratedMessageV3$BuilderParent
 *  com.google.protobuf.GeneratedMessageV3$FieldAccessorTable
 *  com.google.protobuf.GeneratedMessageV3$UnusedPrivateParameter
 *  com.google.protobuf.Internal
 *  com.google.protobuf.Internal$EnumLiteMap
 *  com.google.protobuf.InvalidProtocolBufferException
 *  com.google.protobuf.Message
 *  com.google.protobuf.MessageLite
 *  com.google.protobuf.MessageOrBuilder
 *  com.google.protobuf.Parser
 *  com.google.protobuf.ProtocolMessageEnum
 *  com.google.protobuf.SingleFieldBuilderV3
 *  com.google.protobuf.UnknownFieldSet
 *  com.google.protobuf.UnknownFieldSet$Builder
 */
package com.mysql.cj.x.protobuf;

import com.google.protobuf.AbstractMessage;
import com.google.protobuf.AbstractParser;
import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.Descriptors;
import com.google.protobuf.ExtensionRegistry;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageV3;
import com.google.protobuf.Internal;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Message;
import com.google.protobuf.MessageLite;
import com.google.protobuf.MessageOrBuilder;
import com.google.protobuf.Parser;
import com.google.protobuf.ProtocolMessageEnum;
import com.google.protobuf.SingleFieldBuilderV3;
import com.google.protobuf.UnknownFieldSet;
import com.mysql.cj.x.protobuf.Mysqlx;
import com.mysql.cj.x.protobuf.MysqlxPrepare;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

public final class MysqlxCursor {
    private static final Descriptors.Descriptor internal_static_Mysqlx_Cursor_Open_descriptor;
    private static final GeneratedMessageV3.FieldAccessorTable internal_static_Mysqlx_Cursor_Open_fieldAccessorTable;
    private static final Descriptors.Descriptor internal_static_Mysqlx_Cursor_Open_OneOfMessage_descriptor;
    private static final GeneratedMessageV3.FieldAccessorTable internal_static_Mysqlx_Cursor_Open_OneOfMessage_fieldAccessorTable;
    private static final Descriptors.Descriptor internal_static_Mysqlx_Cursor_Fetch_descriptor;
    private static final GeneratedMessageV3.FieldAccessorTable internal_static_Mysqlx_Cursor_Fetch_fieldAccessorTable;
    private static final Descriptors.Descriptor internal_static_Mysqlx_Cursor_Close_descriptor;
    private static final GeneratedMessageV3.FieldAccessorTable internal_static_Mysqlx_Cursor_Close_fieldAccessorTable;
    private static Descriptors.FileDescriptor descriptor;

    private MysqlxCursor() {
    }

    public static void registerAllExtensions(ExtensionRegistryLite registry) {
    }

    public static void registerAllExtensions(ExtensionRegistry registry) {
        MysqlxCursor.registerAllExtensions((ExtensionRegistryLite)registry);
    }

    public static Descriptors.FileDescriptor getDescriptor() {
        return descriptor;
    }

    static {
        String[] descriptorData = new String[]{"\n\u0013mysqlx_cursor.proto\u0012\rMysqlx.Cursor\u001a\fmysqlx.proto\u001a\u0014mysqlx_prepare.proto\"\u00f8\u0001\n\u0004Open\u0012\u0011\n\tcursor_id\u0018\u0001 \u0002(\r\u0012.\n\u0004stmt\u0018\u0004 \u0002(\u000b2 .Mysqlx.Cursor.Open.OneOfMessage\u0012\u0012\n\nfetch_rows\u0018\u0005 \u0001(\u0004\u001a\u0092\u0001\n\fOneOfMessage\u00123\n\u0004type\u0018\u0001 \u0002(\u000e2%.Mysqlx.Cursor.Open.OneOfMessage.Type\u00120\n\u000fprepare_execute\u0018\u0002 \u0001(\u000b2\u0017.Mysqlx.Prepare.Execute\"\u001b\n\u0004Type\u0012\u0013\n\u000fPREPARE_EXECUTE\u0010\u0000:\u0004\u0088\u00ea0+\"4\n\u0005Fetch\u0012\u0011\n\tcursor_id\u0018\u0001 \u0002(\r\u0012\u0012\n\nfetch_rows\u0018\u0005 \u0001(\u0004:\u0004\u0088\u00ea0-\" \n\u0005Close\u0012\u0011\n\tcursor_id\u0018\u0001 \u0002(\r:\u0004\u0088\u00ea0,B\u0019\n\u0017com.mysql.cj.x.protobuf"};
        descriptor = Descriptors.FileDescriptor.internalBuildGeneratedFileFrom((String[])descriptorData, (Descriptors.FileDescriptor[])new Descriptors.FileDescriptor[]{Mysqlx.getDescriptor(), MysqlxPrepare.getDescriptor()});
        internal_static_Mysqlx_Cursor_Open_descriptor = (Descriptors.Descriptor)MysqlxCursor.getDescriptor().getMessageTypes().get(0);
        internal_static_Mysqlx_Cursor_Open_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(internal_static_Mysqlx_Cursor_Open_descriptor, new String[]{"CursorId", "Stmt", "FetchRows"});
        internal_static_Mysqlx_Cursor_Open_OneOfMessage_descriptor = (Descriptors.Descriptor)internal_static_Mysqlx_Cursor_Open_descriptor.getNestedTypes().get(0);
        internal_static_Mysqlx_Cursor_Open_OneOfMessage_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(internal_static_Mysqlx_Cursor_Open_OneOfMessage_descriptor, new String[]{"Type", "PrepareExecute"});
        internal_static_Mysqlx_Cursor_Fetch_descriptor = (Descriptors.Descriptor)MysqlxCursor.getDescriptor().getMessageTypes().get(1);
        internal_static_Mysqlx_Cursor_Fetch_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(internal_static_Mysqlx_Cursor_Fetch_descriptor, new String[]{"CursorId", "FetchRows"});
        internal_static_Mysqlx_Cursor_Close_descriptor = (Descriptors.Descriptor)MysqlxCursor.getDescriptor().getMessageTypes().get(2);
        internal_static_Mysqlx_Cursor_Close_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(internal_static_Mysqlx_Cursor_Close_descriptor, new String[]{"CursorId"});
        ExtensionRegistry registry = ExtensionRegistry.newInstance();
        registry.add(Mysqlx.clientMessageId);
        Descriptors.FileDescriptor.internalUpdateFileDescriptor((Descriptors.FileDescriptor)descriptor, (ExtensionRegistry)registry);
        Mysqlx.getDescriptor();
        MysqlxPrepare.getDescriptor();
    }

    public static final class Close
    extends GeneratedMessageV3
    implements CloseOrBuilder {
        private static final long serialVersionUID = 0L;
        private int bitField0_;
        public static final int CURSOR_ID_FIELD_NUMBER = 1;
        private int cursorId_;
        private byte memoizedIsInitialized = (byte)-1;
        private static final Close DEFAULT_INSTANCE = new Close();
        @Deprecated
        public static final Parser<Close> PARSER = new AbstractParser<Close>(){

            public Close parsePartialFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                return new Close(input, extensionRegistry);
            }
        };

        private Close(GeneratedMessageV3.Builder<?> builder) {
            super(builder);
        }

        private Close() {
        }

        protected Object newInstance(GeneratedMessageV3.UnusedPrivateParameter unused) {
            return new Close();
        }

        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        private Close(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            this();
            if (extensionRegistry == null) {
                throw new NullPointerException();
            }
            boolean mutable_bitField0_ = false;
            UnknownFieldSet.Builder unknownFields = UnknownFieldSet.newBuilder();
            try {
                boolean done = false;
                block10: while (!done) {
                    int tag = input.readTag();
                    switch (tag) {
                        case 0: {
                            done = true;
                            continue block10;
                        }
                        case 8: {
                            this.bitField0_ |= 1;
                            this.cursorId_ = input.readUInt32();
                            continue block10;
                        }
                    }
                    if (this.parseUnknownField(input, unknownFields, extensionRegistry, tag)) continue;
                    done = true;
                }
            }
            catch (InvalidProtocolBufferException e) {
                throw e.setUnfinishedMessage((MessageLite)this);
            }
            catch (IOException e) {
                throw new InvalidProtocolBufferException(e).setUnfinishedMessage((MessageLite)this);
            }
            finally {
                this.unknownFields = unknownFields.build();
                this.makeExtensionsImmutable();
            }
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return internal_static_Mysqlx_Cursor_Close_descriptor;
        }

        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return internal_static_Mysqlx_Cursor_Close_fieldAccessorTable.ensureFieldAccessorsInitialized(Close.class, Builder.class);
        }

        @Override
        public boolean hasCursorId() {
            return (this.bitField0_ & 1) != 0;
        }

        @Override
        public int getCursorId() {
            return this.cursorId_;
        }

        public final boolean isInitialized() {
            byte isInitialized = this.memoizedIsInitialized;
            if (isInitialized == 1) {
                return true;
            }
            if (isInitialized == 0) {
                return false;
            }
            if (!this.hasCursorId()) {
                this.memoizedIsInitialized = 0;
                return false;
            }
            this.memoizedIsInitialized = 1;
            return true;
        }

        public void writeTo(CodedOutputStream output) throws IOException {
            if ((this.bitField0_ & 1) != 0) {
                output.writeUInt32(1, this.cursorId_);
            }
            this.unknownFields.writeTo(output);
        }

        public int getSerializedSize() {
            int size = this.memoizedSize;
            if (size != -1) {
                return size;
            }
            size = 0;
            if ((this.bitField0_ & 1) != 0) {
                size += CodedOutputStream.computeUInt32Size((int)1, (int)this.cursorId_);
            }
            this.memoizedSize = size += this.unknownFields.getSerializedSize();
            return size;
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof Close)) {
                return super.equals(obj);
            }
            Close other = (Close)obj;
            if (this.hasCursorId() != other.hasCursorId()) {
                return false;
            }
            if (this.hasCursorId() && this.getCursorId() != other.getCursorId()) {
                return false;
            }
            return this.unknownFields.equals((Object)other.unknownFields);
        }

        public int hashCode() {
            if (this.memoizedHashCode != 0) {
                return this.memoizedHashCode;
            }
            int hash = 41;
            hash = 19 * hash + Close.getDescriptor().hashCode();
            if (this.hasCursorId()) {
                hash = 37 * hash + 1;
                hash = 53 * hash + this.getCursorId();
            }
            this.memoizedHashCode = hash = 29 * hash + this.unknownFields.hashCode();
            return hash;
        }

        public static Close parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
            return (Close)PARSER.parseFrom(data);
        }

        public static Close parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (Close)PARSER.parseFrom(data, extensionRegistry);
        }

        public static Close parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return (Close)PARSER.parseFrom(data);
        }

        public static Close parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (Close)PARSER.parseFrom(data, extensionRegistry);
        }

        public static Close parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return (Close)PARSER.parseFrom(data);
        }

        public static Close parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (Close)PARSER.parseFrom(data, extensionRegistry);
        }

        public static Close parseFrom(InputStream input) throws IOException {
            return (Close)GeneratedMessageV3.parseWithIOException(PARSER, (InputStream)input);
        }

        public static Close parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (Close)GeneratedMessageV3.parseWithIOException(PARSER, (InputStream)input, (ExtensionRegistryLite)extensionRegistry);
        }

        public static Close parseDelimitedFrom(InputStream input) throws IOException {
            return (Close)GeneratedMessageV3.parseDelimitedWithIOException(PARSER, (InputStream)input);
        }

        public static Close parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (Close)GeneratedMessageV3.parseDelimitedWithIOException(PARSER, (InputStream)input, (ExtensionRegistryLite)extensionRegistry);
        }

        public static Close parseFrom(CodedInputStream input) throws IOException {
            return (Close)GeneratedMessageV3.parseWithIOException(PARSER, (CodedInputStream)input);
        }

        public static Close parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (Close)GeneratedMessageV3.parseWithIOException(PARSER, (CodedInputStream)input, (ExtensionRegistryLite)extensionRegistry);
        }

        public Builder newBuilderForType() {
            return Close.newBuilder();
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(Close prototype) {
            return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
        }

        public Builder toBuilder() {
            return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
        }

        protected Builder newBuilderForType(GeneratedMessageV3.BuilderParent parent) {
            Builder builder = new Builder(parent);
            return builder;
        }

        public static Close getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<Close> parser() {
            return PARSER;
        }

        public Parser<Close> getParserForType() {
            return PARSER;
        }

        public Close getDefaultInstanceForType() {
            return DEFAULT_INSTANCE;
        }

        public static final class Builder
        extends GeneratedMessageV3.Builder<Builder>
        implements CloseOrBuilder {
            private int bitField0_;
            private int cursorId_;

            public static final Descriptors.Descriptor getDescriptor() {
                return internal_static_Mysqlx_Cursor_Close_descriptor;
            }

            protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                return internal_static_Mysqlx_Cursor_Close_fieldAccessorTable.ensureFieldAccessorsInitialized(Close.class, Builder.class);
            }

            private Builder() {
                this.maybeForceBuilderInitialization();
            }

            private Builder(GeneratedMessageV3.BuilderParent parent) {
                super(parent);
                this.maybeForceBuilderInitialization();
            }

            private void maybeForceBuilderInitialization() {
                if (alwaysUseFieldBuilders) {
                    // empty if block
                }
            }

            public Builder clear() {
                super.clear();
                this.cursorId_ = 0;
                this.bitField0_ &= 0xFFFFFFFE;
                return this;
            }

            public Descriptors.Descriptor getDescriptorForType() {
                return internal_static_Mysqlx_Cursor_Close_descriptor;
            }

            public Close getDefaultInstanceForType() {
                return Close.getDefaultInstance();
            }

            public Close build() {
                Close result = this.buildPartial();
                if (!result.isInitialized()) {
                    throw Builder.newUninitializedMessageException((Message)result);
                }
                return result;
            }

            public Close buildPartial() {
                Close result = new Close(this);
                int from_bitField0_ = this.bitField0_;
                int to_bitField0_ = 0;
                if ((from_bitField0_ & 1) != 0) {
                    result.cursorId_ = this.cursorId_;
                    to_bitField0_ |= 1;
                }
                result.bitField0_ = to_bitField0_;
                this.onBuilt();
                return result;
            }

            public Builder clone() {
                return (Builder)super.clone();
            }

            public Builder setField(Descriptors.FieldDescriptor field, Object value) {
                return (Builder)super.setField(field, value);
            }

            public Builder clearField(Descriptors.FieldDescriptor field) {
                return (Builder)super.clearField(field);
            }

            public Builder clearOneof(Descriptors.OneofDescriptor oneof) {
                return (Builder)super.clearOneof(oneof);
            }

            public Builder setRepeatedField(Descriptors.FieldDescriptor field, int index, Object value) {
                return (Builder)super.setRepeatedField(field, index, value);
            }

            public Builder addRepeatedField(Descriptors.FieldDescriptor field, Object value) {
                return (Builder)super.addRepeatedField(field, value);
            }

            public Builder mergeFrom(Message other) {
                if (other instanceof Close) {
                    return this.mergeFrom((Close)other);
                }
                super.mergeFrom(other);
                return this;
            }

            public Builder mergeFrom(Close other) {
                if (other == Close.getDefaultInstance()) {
                    return this;
                }
                if (other.hasCursorId()) {
                    this.setCursorId(other.getCursorId());
                }
                this.mergeUnknownFields(other.unknownFields);
                this.onChanged();
                return this;
            }

            public final boolean isInitialized() {
                return this.hasCursorId();
            }

            public Builder mergeFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                Close parsedMessage = null;
                try {
                    parsedMessage = (Close)PARSER.parsePartialFrom(input, extensionRegistry);
                }
                catch (InvalidProtocolBufferException e) {
                    parsedMessage = (Close)e.getUnfinishedMessage();
                    throw e.unwrapIOException();
                }
                finally {
                    if (parsedMessage != null) {
                        this.mergeFrom(parsedMessage);
                    }
                }
                return this;
            }

            @Override
            public boolean hasCursorId() {
                return (this.bitField0_ & 1) != 0;
            }

            @Override
            public int getCursorId() {
                return this.cursorId_;
            }

            public Builder setCursorId(int value) {
                this.bitField0_ |= 1;
                this.cursorId_ = value;
                this.onChanged();
                return this;
            }

            public Builder clearCursorId() {
                this.bitField0_ &= 0xFFFFFFFE;
                this.cursorId_ = 0;
                this.onChanged();
                return this;
            }

            public final Builder setUnknownFields(UnknownFieldSet unknownFields) {
                return (Builder)super.setUnknownFields(unknownFields);
            }

            public final Builder mergeUnknownFields(UnknownFieldSet unknownFields) {
                return (Builder)super.mergeUnknownFields(unknownFields);
            }
        }
    }

    public static interface CloseOrBuilder
    extends MessageOrBuilder {
        public boolean hasCursorId();

        public int getCursorId();
    }

    public static final class Fetch
    extends GeneratedMessageV3
    implements FetchOrBuilder {
        private static final long serialVersionUID = 0L;
        private int bitField0_;
        public static final int CURSOR_ID_FIELD_NUMBER = 1;
        private int cursorId_;
        public static final int FETCH_ROWS_FIELD_NUMBER = 5;
        private long fetchRows_;
        private byte memoizedIsInitialized = (byte)-1;
        private static final Fetch DEFAULT_INSTANCE = new Fetch();
        @Deprecated
        public static final Parser<Fetch> PARSER = new AbstractParser<Fetch>(){

            public Fetch parsePartialFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                return new Fetch(input, extensionRegistry);
            }
        };

        private Fetch(GeneratedMessageV3.Builder<?> builder) {
            super(builder);
        }

        private Fetch() {
        }

        protected Object newInstance(GeneratedMessageV3.UnusedPrivateParameter unused) {
            return new Fetch();
        }

        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        private Fetch(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            this();
            if (extensionRegistry == null) {
                throw new NullPointerException();
            }
            boolean mutable_bitField0_ = false;
            UnknownFieldSet.Builder unknownFields = UnknownFieldSet.newBuilder();
            try {
                boolean done = false;
                block11: while (!done) {
                    int tag = input.readTag();
                    switch (tag) {
                        case 0: {
                            done = true;
                            continue block11;
                        }
                        case 8: {
                            this.bitField0_ |= 1;
                            this.cursorId_ = input.readUInt32();
                            continue block11;
                        }
                        case 40: {
                            this.bitField0_ |= 2;
                            this.fetchRows_ = input.readUInt64();
                            continue block11;
                        }
                    }
                    if (this.parseUnknownField(input, unknownFields, extensionRegistry, tag)) continue;
                    done = true;
                }
            }
            catch (InvalidProtocolBufferException e) {
                throw e.setUnfinishedMessage((MessageLite)this);
            }
            catch (IOException e) {
                throw new InvalidProtocolBufferException(e).setUnfinishedMessage((MessageLite)this);
            }
            finally {
                this.unknownFields = unknownFields.build();
                this.makeExtensionsImmutable();
            }
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return internal_static_Mysqlx_Cursor_Fetch_descriptor;
        }

        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return internal_static_Mysqlx_Cursor_Fetch_fieldAccessorTable.ensureFieldAccessorsInitialized(Fetch.class, Builder.class);
        }

        @Override
        public boolean hasCursorId() {
            return (this.bitField0_ & 1) != 0;
        }

        @Override
        public int getCursorId() {
            return this.cursorId_;
        }

        @Override
        public boolean hasFetchRows() {
            return (this.bitField0_ & 2) != 0;
        }

        @Override
        public long getFetchRows() {
            return this.fetchRows_;
        }

        public final boolean isInitialized() {
            byte isInitialized = this.memoizedIsInitialized;
            if (isInitialized == 1) {
                return true;
            }
            if (isInitialized == 0) {
                return false;
            }
            if (!this.hasCursorId()) {
                this.memoizedIsInitialized = 0;
                return false;
            }
            this.memoizedIsInitialized = 1;
            return true;
        }

        public void writeTo(CodedOutputStream output) throws IOException {
            if ((this.bitField0_ & 1) != 0) {
                output.writeUInt32(1, this.cursorId_);
            }
            if ((this.bitField0_ & 2) != 0) {
                output.writeUInt64(5, this.fetchRows_);
            }
            this.unknownFields.writeTo(output);
        }

        public int getSerializedSize() {
            int size = this.memoizedSize;
            if (size != -1) {
                return size;
            }
            size = 0;
            if ((this.bitField0_ & 1) != 0) {
                size += CodedOutputStream.computeUInt32Size((int)1, (int)this.cursorId_);
            }
            if ((this.bitField0_ & 2) != 0) {
                size += CodedOutputStream.computeUInt64Size((int)5, (long)this.fetchRows_);
            }
            this.memoizedSize = size += this.unknownFields.getSerializedSize();
            return size;
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof Fetch)) {
                return super.equals(obj);
            }
            Fetch other = (Fetch)obj;
            if (this.hasCursorId() != other.hasCursorId()) {
                return false;
            }
            if (this.hasCursorId() && this.getCursorId() != other.getCursorId()) {
                return false;
            }
            if (this.hasFetchRows() != other.hasFetchRows()) {
                return false;
            }
            if (this.hasFetchRows() && this.getFetchRows() != other.getFetchRows()) {
                return false;
            }
            return this.unknownFields.equals((Object)other.unknownFields);
        }

        public int hashCode() {
            if (this.memoizedHashCode != 0) {
                return this.memoizedHashCode;
            }
            int hash = 41;
            hash = 19 * hash + Fetch.getDescriptor().hashCode();
            if (this.hasCursorId()) {
                hash = 37 * hash + 1;
                hash = 53 * hash + this.getCursorId();
            }
            if (this.hasFetchRows()) {
                hash = 37 * hash + 5;
                hash = 53 * hash + Internal.hashLong((long)this.getFetchRows());
            }
            this.memoizedHashCode = hash = 29 * hash + this.unknownFields.hashCode();
            return hash;
        }

        public static Fetch parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
            return (Fetch)PARSER.parseFrom(data);
        }

        public static Fetch parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (Fetch)PARSER.parseFrom(data, extensionRegistry);
        }

        public static Fetch parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return (Fetch)PARSER.parseFrom(data);
        }

        public static Fetch parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (Fetch)PARSER.parseFrom(data, extensionRegistry);
        }

        public static Fetch parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return (Fetch)PARSER.parseFrom(data);
        }

        public static Fetch parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (Fetch)PARSER.parseFrom(data, extensionRegistry);
        }

        public static Fetch parseFrom(InputStream input) throws IOException {
            return (Fetch)GeneratedMessageV3.parseWithIOException(PARSER, (InputStream)input);
        }

        public static Fetch parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (Fetch)GeneratedMessageV3.parseWithIOException(PARSER, (InputStream)input, (ExtensionRegistryLite)extensionRegistry);
        }

        public static Fetch parseDelimitedFrom(InputStream input) throws IOException {
            return (Fetch)GeneratedMessageV3.parseDelimitedWithIOException(PARSER, (InputStream)input);
        }

        public static Fetch parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (Fetch)GeneratedMessageV3.parseDelimitedWithIOException(PARSER, (InputStream)input, (ExtensionRegistryLite)extensionRegistry);
        }

        public static Fetch parseFrom(CodedInputStream input) throws IOException {
            return (Fetch)GeneratedMessageV3.parseWithIOException(PARSER, (CodedInputStream)input);
        }

        public static Fetch parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (Fetch)GeneratedMessageV3.parseWithIOException(PARSER, (CodedInputStream)input, (ExtensionRegistryLite)extensionRegistry);
        }

        public Builder newBuilderForType() {
            return Fetch.newBuilder();
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(Fetch prototype) {
            return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
        }

        public Builder toBuilder() {
            return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
        }

        protected Builder newBuilderForType(GeneratedMessageV3.BuilderParent parent) {
            Builder builder = new Builder(parent);
            return builder;
        }

        public static Fetch getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<Fetch> parser() {
            return PARSER;
        }

        public Parser<Fetch> getParserForType() {
            return PARSER;
        }

        public Fetch getDefaultInstanceForType() {
            return DEFAULT_INSTANCE;
        }

        public static final class Builder
        extends GeneratedMessageV3.Builder<Builder>
        implements FetchOrBuilder {
            private int bitField0_;
            private int cursorId_;
            private long fetchRows_;

            public static final Descriptors.Descriptor getDescriptor() {
                return internal_static_Mysqlx_Cursor_Fetch_descriptor;
            }

            protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                return internal_static_Mysqlx_Cursor_Fetch_fieldAccessorTable.ensureFieldAccessorsInitialized(Fetch.class, Builder.class);
            }

            private Builder() {
                this.maybeForceBuilderInitialization();
            }

            private Builder(GeneratedMessageV3.BuilderParent parent) {
                super(parent);
                this.maybeForceBuilderInitialization();
            }

            private void maybeForceBuilderInitialization() {
                if (alwaysUseFieldBuilders) {
                    // empty if block
                }
            }

            public Builder clear() {
                super.clear();
                this.cursorId_ = 0;
                this.bitField0_ &= 0xFFFFFFFE;
                this.fetchRows_ = 0L;
                this.bitField0_ &= 0xFFFFFFFD;
                return this;
            }

            public Descriptors.Descriptor getDescriptorForType() {
                return internal_static_Mysqlx_Cursor_Fetch_descriptor;
            }

            public Fetch getDefaultInstanceForType() {
                return Fetch.getDefaultInstance();
            }

            public Fetch build() {
                Fetch result = this.buildPartial();
                if (!result.isInitialized()) {
                    throw Builder.newUninitializedMessageException((Message)result);
                }
                return result;
            }

            public Fetch buildPartial() {
                Fetch result = new Fetch(this);
                int from_bitField0_ = this.bitField0_;
                int to_bitField0_ = 0;
                if ((from_bitField0_ & 1) != 0) {
                    result.cursorId_ = this.cursorId_;
                    to_bitField0_ |= 1;
                }
                if ((from_bitField0_ & 2) != 0) {
                    result.fetchRows_ = this.fetchRows_;
                    to_bitField0_ |= 2;
                }
                result.bitField0_ = to_bitField0_;
                this.onBuilt();
                return result;
            }

            public Builder clone() {
                return (Builder)super.clone();
            }

            public Builder setField(Descriptors.FieldDescriptor field, Object value) {
                return (Builder)super.setField(field, value);
            }

            public Builder clearField(Descriptors.FieldDescriptor field) {
                return (Builder)super.clearField(field);
            }

            public Builder clearOneof(Descriptors.OneofDescriptor oneof) {
                return (Builder)super.clearOneof(oneof);
            }

            public Builder setRepeatedField(Descriptors.FieldDescriptor field, int index, Object value) {
                return (Builder)super.setRepeatedField(field, index, value);
            }

            public Builder addRepeatedField(Descriptors.FieldDescriptor field, Object value) {
                return (Builder)super.addRepeatedField(field, value);
            }

            public Builder mergeFrom(Message other) {
                if (other instanceof Fetch) {
                    return this.mergeFrom((Fetch)other);
                }
                super.mergeFrom(other);
                return this;
            }

            public Builder mergeFrom(Fetch other) {
                if (other == Fetch.getDefaultInstance()) {
                    return this;
                }
                if (other.hasCursorId()) {
                    this.setCursorId(other.getCursorId());
                }
                if (other.hasFetchRows()) {
                    this.setFetchRows(other.getFetchRows());
                }
                this.mergeUnknownFields(other.unknownFields);
                this.onChanged();
                return this;
            }

            public final boolean isInitialized() {
                return this.hasCursorId();
            }

            public Builder mergeFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                Fetch parsedMessage = null;
                try {
                    parsedMessage = (Fetch)PARSER.parsePartialFrom(input, extensionRegistry);
                }
                catch (InvalidProtocolBufferException e) {
                    parsedMessage = (Fetch)e.getUnfinishedMessage();
                    throw e.unwrapIOException();
                }
                finally {
                    if (parsedMessage != null) {
                        this.mergeFrom(parsedMessage);
                    }
                }
                return this;
            }

            @Override
            public boolean hasCursorId() {
                return (this.bitField0_ & 1) != 0;
            }

            @Override
            public int getCursorId() {
                return this.cursorId_;
            }

            public Builder setCursorId(int value) {
                this.bitField0_ |= 1;
                this.cursorId_ = value;
                this.onChanged();
                return this;
            }

            public Builder clearCursorId() {
                this.bitField0_ &= 0xFFFFFFFE;
                this.cursorId_ = 0;
                this.onChanged();
                return this;
            }

            @Override
            public boolean hasFetchRows() {
                return (this.bitField0_ & 2) != 0;
            }

            @Override
            public long getFetchRows() {
                return this.fetchRows_;
            }

            public Builder setFetchRows(long value) {
                this.bitField0_ |= 2;
                this.fetchRows_ = value;
                this.onChanged();
                return this;
            }

            public Builder clearFetchRows() {
                this.bitField0_ &= 0xFFFFFFFD;
                this.fetchRows_ = 0L;
                this.onChanged();
                return this;
            }

            public final Builder setUnknownFields(UnknownFieldSet unknownFields) {
                return (Builder)super.setUnknownFields(unknownFields);
            }

            public final Builder mergeUnknownFields(UnknownFieldSet unknownFields) {
                return (Builder)super.mergeUnknownFields(unknownFields);
            }
        }
    }

    public static interface FetchOrBuilder
    extends MessageOrBuilder {
        public boolean hasCursorId();

        public int getCursorId();

        public boolean hasFetchRows();

        public long getFetchRows();
    }

    public static final class Open
    extends GeneratedMessageV3
    implements OpenOrBuilder {
        private static final long serialVersionUID = 0L;
        private int bitField0_;
        public static final int CURSOR_ID_FIELD_NUMBER = 1;
        private int cursorId_;
        public static final int STMT_FIELD_NUMBER = 4;
        private OneOfMessage stmt_;
        public static final int FETCH_ROWS_FIELD_NUMBER = 5;
        private long fetchRows_;
        private byte memoizedIsInitialized = (byte)-1;
        private static final Open DEFAULT_INSTANCE = new Open();
        @Deprecated
        public static final Parser<Open> PARSER = new AbstractParser<Open>(){

            public Open parsePartialFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                return new Open(input, extensionRegistry);
            }
        };

        private Open(GeneratedMessageV3.Builder<?> builder) {
            super(builder);
        }

        private Open() {
        }

        protected Object newInstance(GeneratedMessageV3.UnusedPrivateParameter unused) {
            return new Open();
        }

        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        private Open(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            this();
            if (extensionRegistry == null) {
                throw new NullPointerException();
            }
            boolean mutable_bitField0_ = false;
            UnknownFieldSet.Builder unknownFields = UnknownFieldSet.newBuilder();
            try {
                boolean done = false;
                block12: while (!done) {
                    int tag = input.readTag();
                    switch (tag) {
                        case 0: {
                            done = true;
                            continue block12;
                        }
                        case 8: {
                            this.bitField0_ |= 1;
                            this.cursorId_ = input.readUInt32();
                            continue block12;
                        }
                        case 34: {
                            OneOfMessage.Builder subBuilder = null;
                            if ((this.bitField0_ & 2) != 0) {
                                subBuilder = this.stmt_.toBuilder();
                            }
                            this.stmt_ = (OneOfMessage)input.readMessage(OneOfMessage.PARSER, extensionRegistry);
                            if (subBuilder != null) {
                                subBuilder.mergeFrom(this.stmt_);
                                this.stmt_ = subBuilder.buildPartial();
                            }
                            this.bitField0_ |= 2;
                            continue block12;
                        }
                        case 40: {
                            this.bitField0_ |= 4;
                            this.fetchRows_ = input.readUInt64();
                            continue block12;
                        }
                    }
                    if (this.parseUnknownField(input, unknownFields, extensionRegistry, tag)) continue;
                    done = true;
                }
            }
            catch (InvalidProtocolBufferException e) {
                throw e.setUnfinishedMessage((MessageLite)this);
            }
            catch (IOException e) {
                throw new InvalidProtocolBufferException(e).setUnfinishedMessage((MessageLite)this);
            }
            finally {
                this.unknownFields = unknownFields.build();
                this.makeExtensionsImmutable();
            }
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return internal_static_Mysqlx_Cursor_Open_descriptor;
        }

        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return internal_static_Mysqlx_Cursor_Open_fieldAccessorTable.ensureFieldAccessorsInitialized(Open.class, Builder.class);
        }

        @Override
        public boolean hasCursorId() {
            return (this.bitField0_ & 1) != 0;
        }

        @Override
        public int getCursorId() {
            return this.cursorId_;
        }

        @Override
        public boolean hasStmt() {
            return (this.bitField0_ & 2) != 0;
        }

        @Override
        public OneOfMessage getStmt() {
            return this.stmt_ == null ? OneOfMessage.getDefaultInstance() : this.stmt_;
        }

        @Override
        public OneOfMessageOrBuilder getStmtOrBuilder() {
            return this.stmt_ == null ? OneOfMessage.getDefaultInstance() : this.stmt_;
        }

        @Override
        public boolean hasFetchRows() {
            return (this.bitField0_ & 4) != 0;
        }

        @Override
        public long getFetchRows() {
            return this.fetchRows_;
        }

        public final boolean isInitialized() {
            byte isInitialized = this.memoizedIsInitialized;
            if (isInitialized == 1) {
                return true;
            }
            if (isInitialized == 0) {
                return false;
            }
            if (!this.hasCursorId()) {
                this.memoizedIsInitialized = 0;
                return false;
            }
            if (!this.hasStmt()) {
                this.memoizedIsInitialized = 0;
                return false;
            }
            if (!this.getStmt().isInitialized()) {
                this.memoizedIsInitialized = 0;
                return false;
            }
            this.memoizedIsInitialized = 1;
            return true;
        }

        public void writeTo(CodedOutputStream output) throws IOException {
            if ((this.bitField0_ & 1) != 0) {
                output.writeUInt32(1, this.cursorId_);
            }
            if ((this.bitField0_ & 2) != 0) {
                output.writeMessage(4, (MessageLite)this.getStmt());
            }
            if ((this.bitField0_ & 4) != 0) {
                output.writeUInt64(5, this.fetchRows_);
            }
            this.unknownFields.writeTo(output);
        }

        public int getSerializedSize() {
            int size = this.memoizedSize;
            if (size != -1) {
                return size;
            }
            size = 0;
            if ((this.bitField0_ & 1) != 0) {
                size += CodedOutputStream.computeUInt32Size((int)1, (int)this.cursorId_);
            }
            if ((this.bitField0_ & 2) != 0) {
                size += CodedOutputStream.computeMessageSize((int)4, (MessageLite)this.getStmt());
            }
            if ((this.bitField0_ & 4) != 0) {
                size += CodedOutputStream.computeUInt64Size((int)5, (long)this.fetchRows_);
            }
            this.memoizedSize = size += this.unknownFields.getSerializedSize();
            return size;
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof Open)) {
                return super.equals(obj);
            }
            Open other = (Open)obj;
            if (this.hasCursorId() != other.hasCursorId()) {
                return false;
            }
            if (this.hasCursorId() && this.getCursorId() != other.getCursorId()) {
                return false;
            }
            if (this.hasStmt() != other.hasStmt()) {
                return false;
            }
            if (this.hasStmt() && !this.getStmt().equals(other.getStmt())) {
                return false;
            }
            if (this.hasFetchRows() != other.hasFetchRows()) {
                return false;
            }
            if (this.hasFetchRows() && this.getFetchRows() != other.getFetchRows()) {
                return false;
            }
            return this.unknownFields.equals((Object)other.unknownFields);
        }

        public int hashCode() {
            if (this.memoizedHashCode != 0) {
                return this.memoizedHashCode;
            }
            int hash = 41;
            hash = 19 * hash + Open.getDescriptor().hashCode();
            if (this.hasCursorId()) {
                hash = 37 * hash + 1;
                hash = 53 * hash + this.getCursorId();
            }
            if (this.hasStmt()) {
                hash = 37 * hash + 4;
                hash = 53 * hash + this.getStmt().hashCode();
            }
            if (this.hasFetchRows()) {
                hash = 37 * hash + 5;
                hash = 53 * hash + Internal.hashLong((long)this.getFetchRows());
            }
            this.memoizedHashCode = hash = 29 * hash + this.unknownFields.hashCode();
            return hash;
        }

        public static Open parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
            return (Open)PARSER.parseFrom(data);
        }

        public static Open parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (Open)PARSER.parseFrom(data, extensionRegistry);
        }

        public static Open parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return (Open)PARSER.parseFrom(data);
        }

        public static Open parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (Open)PARSER.parseFrom(data, extensionRegistry);
        }

        public static Open parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return (Open)PARSER.parseFrom(data);
        }

        public static Open parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (Open)PARSER.parseFrom(data, extensionRegistry);
        }

        public static Open parseFrom(InputStream input) throws IOException {
            return (Open)GeneratedMessageV3.parseWithIOException(PARSER, (InputStream)input);
        }

        public static Open parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (Open)GeneratedMessageV3.parseWithIOException(PARSER, (InputStream)input, (ExtensionRegistryLite)extensionRegistry);
        }

        public static Open parseDelimitedFrom(InputStream input) throws IOException {
            return (Open)GeneratedMessageV3.parseDelimitedWithIOException(PARSER, (InputStream)input);
        }

        public static Open parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (Open)GeneratedMessageV3.parseDelimitedWithIOException(PARSER, (InputStream)input, (ExtensionRegistryLite)extensionRegistry);
        }

        public static Open parseFrom(CodedInputStream input) throws IOException {
            return (Open)GeneratedMessageV3.parseWithIOException(PARSER, (CodedInputStream)input);
        }

        public static Open parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (Open)GeneratedMessageV3.parseWithIOException(PARSER, (CodedInputStream)input, (ExtensionRegistryLite)extensionRegistry);
        }

        public Builder newBuilderForType() {
            return Open.newBuilder();
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(Open prototype) {
            return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
        }

        public Builder toBuilder() {
            return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
        }

        protected Builder newBuilderForType(GeneratedMessageV3.BuilderParent parent) {
            Builder builder = new Builder(parent);
            return builder;
        }

        public static Open getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<Open> parser() {
            return PARSER;
        }

        public Parser<Open> getParserForType() {
            return PARSER;
        }

        public Open getDefaultInstanceForType() {
            return DEFAULT_INSTANCE;
        }

        public static final class Builder
        extends GeneratedMessageV3.Builder<Builder>
        implements OpenOrBuilder {
            private int bitField0_;
            private int cursorId_;
            private OneOfMessage stmt_;
            private SingleFieldBuilderV3<OneOfMessage, OneOfMessage.Builder, OneOfMessageOrBuilder> stmtBuilder_;
            private long fetchRows_;

            public static final Descriptors.Descriptor getDescriptor() {
                return internal_static_Mysqlx_Cursor_Open_descriptor;
            }

            protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                return internal_static_Mysqlx_Cursor_Open_fieldAccessorTable.ensureFieldAccessorsInitialized(Open.class, Builder.class);
            }

            private Builder() {
                this.maybeForceBuilderInitialization();
            }

            private Builder(GeneratedMessageV3.BuilderParent parent) {
                super(parent);
                this.maybeForceBuilderInitialization();
            }

            private void maybeForceBuilderInitialization() {
                if (alwaysUseFieldBuilders) {
                    this.getStmtFieldBuilder();
                }
            }

            public Builder clear() {
                super.clear();
                this.cursorId_ = 0;
                this.bitField0_ &= 0xFFFFFFFE;
                if (this.stmtBuilder_ == null) {
                    this.stmt_ = null;
                } else {
                    this.stmtBuilder_.clear();
                }
                this.bitField0_ &= 0xFFFFFFFD;
                this.fetchRows_ = 0L;
                this.bitField0_ &= 0xFFFFFFFB;
                return this;
            }

            public Descriptors.Descriptor getDescriptorForType() {
                return internal_static_Mysqlx_Cursor_Open_descriptor;
            }

            public Open getDefaultInstanceForType() {
                return Open.getDefaultInstance();
            }

            public Open build() {
                Open result = this.buildPartial();
                if (!result.isInitialized()) {
                    throw Builder.newUninitializedMessageException((Message)result);
                }
                return result;
            }

            public Open buildPartial() {
                Open result = new Open(this);
                int from_bitField0_ = this.bitField0_;
                int to_bitField0_ = 0;
                if ((from_bitField0_ & 1) != 0) {
                    result.cursorId_ = this.cursorId_;
                    to_bitField0_ |= 1;
                }
                if ((from_bitField0_ & 2) != 0) {
                    if (this.stmtBuilder_ == null) {
                        result.stmt_ = this.stmt_;
                    } else {
                        result.stmt_ = (OneOfMessage)this.stmtBuilder_.build();
                    }
                    to_bitField0_ |= 2;
                }
                if ((from_bitField0_ & 4) != 0) {
                    result.fetchRows_ = this.fetchRows_;
                    to_bitField0_ |= 4;
                }
                result.bitField0_ = to_bitField0_;
                this.onBuilt();
                return result;
            }

            public Builder clone() {
                return (Builder)super.clone();
            }

            public Builder setField(Descriptors.FieldDescriptor field, Object value) {
                return (Builder)super.setField(field, value);
            }

            public Builder clearField(Descriptors.FieldDescriptor field) {
                return (Builder)super.clearField(field);
            }

            public Builder clearOneof(Descriptors.OneofDescriptor oneof) {
                return (Builder)super.clearOneof(oneof);
            }

            public Builder setRepeatedField(Descriptors.FieldDescriptor field, int index, Object value) {
                return (Builder)super.setRepeatedField(field, index, value);
            }

            public Builder addRepeatedField(Descriptors.FieldDescriptor field, Object value) {
                return (Builder)super.addRepeatedField(field, value);
            }

            public Builder mergeFrom(Message other) {
                if (other instanceof Open) {
                    return this.mergeFrom((Open)other);
                }
                super.mergeFrom(other);
                return this;
            }

            public Builder mergeFrom(Open other) {
                if (other == Open.getDefaultInstance()) {
                    return this;
                }
                if (other.hasCursorId()) {
                    this.setCursorId(other.getCursorId());
                }
                if (other.hasStmt()) {
                    this.mergeStmt(other.getStmt());
                }
                if (other.hasFetchRows()) {
                    this.setFetchRows(other.getFetchRows());
                }
                this.mergeUnknownFields(other.unknownFields);
                this.onChanged();
                return this;
            }

            public final boolean isInitialized() {
                if (!this.hasCursorId()) {
                    return false;
                }
                if (!this.hasStmt()) {
                    return false;
                }
                return this.getStmt().isInitialized();
            }

            public Builder mergeFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                Open parsedMessage = null;
                try {
                    parsedMessage = (Open)PARSER.parsePartialFrom(input, extensionRegistry);
                }
                catch (InvalidProtocolBufferException e) {
                    parsedMessage = (Open)e.getUnfinishedMessage();
                    throw e.unwrapIOException();
                }
                finally {
                    if (parsedMessage != null) {
                        this.mergeFrom(parsedMessage);
                    }
                }
                return this;
            }

            @Override
            public boolean hasCursorId() {
                return (this.bitField0_ & 1) != 0;
            }

            @Override
            public int getCursorId() {
                return this.cursorId_;
            }

            public Builder setCursorId(int value) {
                this.bitField0_ |= 1;
                this.cursorId_ = value;
                this.onChanged();
                return this;
            }

            public Builder clearCursorId() {
                this.bitField0_ &= 0xFFFFFFFE;
                this.cursorId_ = 0;
                this.onChanged();
                return this;
            }

            @Override
            public boolean hasStmt() {
                return (this.bitField0_ & 2) != 0;
            }

            @Override
            public OneOfMessage getStmt() {
                if (this.stmtBuilder_ == null) {
                    return this.stmt_ == null ? OneOfMessage.getDefaultInstance() : this.stmt_;
                }
                return (OneOfMessage)this.stmtBuilder_.getMessage();
            }

            public Builder setStmt(OneOfMessage value) {
                if (this.stmtBuilder_ == null) {
                    if (value == null) {
                        throw new NullPointerException();
                    }
                    this.stmt_ = value;
                    this.onChanged();
                } else {
                    this.stmtBuilder_.setMessage((AbstractMessage)value);
                }
                this.bitField0_ |= 2;
                return this;
            }

            public Builder setStmt(OneOfMessage.Builder builderForValue) {
                if (this.stmtBuilder_ == null) {
                    this.stmt_ = builderForValue.build();
                    this.onChanged();
                } else {
                    this.stmtBuilder_.setMessage((AbstractMessage)builderForValue.build());
                }
                this.bitField0_ |= 2;
                return this;
            }

            public Builder mergeStmt(OneOfMessage value) {
                if (this.stmtBuilder_ == null) {
                    this.stmt_ = (this.bitField0_ & 2) != 0 && this.stmt_ != null && this.stmt_ != OneOfMessage.getDefaultInstance() ? OneOfMessage.newBuilder(this.stmt_).mergeFrom(value).buildPartial() : value;
                    this.onChanged();
                } else {
                    this.stmtBuilder_.mergeFrom((AbstractMessage)value);
                }
                this.bitField0_ |= 2;
                return this;
            }

            public Builder clearStmt() {
                if (this.stmtBuilder_ == null) {
                    this.stmt_ = null;
                    this.onChanged();
                } else {
                    this.stmtBuilder_.clear();
                }
                this.bitField0_ &= 0xFFFFFFFD;
                return this;
            }

            public OneOfMessage.Builder getStmtBuilder() {
                this.bitField0_ |= 2;
                this.onChanged();
                return (OneOfMessage.Builder)this.getStmtFieldBuilder().getBuilder();
            }

            @Override
            public OneOfMessageOrBuilder getStmtOrBuilder() {
                if (this.stmtBuilder_ != null) {
                    return (OneOfMessageOrBuilder)this.stmtBuilder_.getMessageOrBuilder();
                }
                return this.stmt_ == null ? OneOfMessage.getDefaultInstance() : this.stmt_;
            }

            private SingleFieldBuilderV3<OneOfMessage, OneOfMessage.Builder, OneOfMessageOrBuilder> getStmtFieldBuilder() {
                if (this.stmtBuilder_ == null) {
                    this.stmtBuilder_ = new SingleFieldBuilderV3((AbstractMessage)this.getStmt(), (AbstractMessage.BuilderParent)this.getParentForChildren(), this.isClean());
                    this.stmt_ = null;
                }
                return this.stmtBuilder_;
            }

            @Override
            public boolean hasFetchRows() {
                return (this.bitField0_ & 4) != 0;
            }

            @Override
            public long getFetchRows() {
                return this.fetchRows_;
            }

            public Builder setFetchRows(long value) {
                this.bitField0_ |= 4;
                this.fetchRows_ = value;
                this.onChanged();
                return this;
            }

            public Builder clearFetchRows() {
                this.bitField0_ &= 0xFFFFFFFB;
                this.fetchRows_ = 0L;
                this.onChanged();
                return this;
            }

            public final Builder setUnknownFields(UnknownFieldSet unknownFields) {
                return (Builder)super.setUnknownFields(unknownFields);
            }

            public final Builder mergeUnknownFields(UnknownFieldSet unknownFields) {
                return (Builder)super.mergeUnknownFields(unknownFields);
            }
        }

        public static final class OneOfMessage
        extends GeneratedMessageV3
        implements OneOfMessageOrBuilder {
            private static final long serialVersionUID = 0L;
            private int bitField0_;
            public static final int TYPE_FIELD_NUMBER = 1;
            private int type_;
            public static final int PREPARE_EXECUTE_FIELD_NUMBER = 2;
            private MysqlxPrepare.Execute prepareExecute_;
            private byte memoizedIsInitialized = (byte)-1;
            private static final OneOfMessage DEFAULT_INSTANCE = new OneOfMessage();
            @Deprecated
            public static final Parser<OneOfMessage> PARSER = new AbstractParser<OneOfMessage>(){

                public OneOfMessage parsePartialFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                    return new OneOfMessage(input, extensionRegistry);
                }
            };

            private OneOfMessage(GeneratedMessageV3.Builder<?> builder) {
                super(builder);
            }

            private OneOfMessage() {
                this.type_ = 0;
            }

            protected Object newInstance(GeneratedMessageV3.UnusedPrivateParameter unused) {
                return new OneOfMessage();
            }

            public final UnknownFieldSet getUnknownFields() {
                return this.unknownFields;
            }

            private OneOfMessage(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                this();
                if (extensionRegistry == null) {
                    throw new NullPointerException();
                }
                boolean mutable_bitField0_ = false;
                UnknownFieldSet.Builder unknownFields = UnknownFieldSet.newBuilder();
                try {
                    boolean done = false;
                    block11: while (!done) {
                        int tag = input.readTag();
                        switch (tag) {
                            case 0: {
                                done = true;
                                continue block11;
                            }
                            case 8: {
                                int rawValue = input.readEnum();
                                Type value = Type.valueOf(rawValue);
                                if (value == null) {
                                    unknownFields.mergeVarintField(1, rawValue);
                                    continue block11;
                                }
                                this.bitField0_ |= 1;
                                this.type_ = rawValue;
                                continue block11;
                            }
                            case 18: {
                                MysqlxPrepare.Execute.Builder subBuilder = null;
                                if ((this.bitField0_ & 2) != 0) {
                                    subBuilder = this.prepareExecute_.toBuilder();
                                }
                                this.prepareExecute_ = (MysqlxPrepare.Execute)input.readMessage(MysqlxPrepare.Execute.PARSER, extensionRegistry);
                                if (subBuilder != null) {
                                    subBuilder.mergeFrom(this.prepareExecute_);
                                    this.prepareExecute_ = subBuilder.buildPartial();
                                }
                                this.bitField0_ |= 2;
                                continue block11;
                            }
                        }
                        if (this.parseUnknownField(input, unknownFields, extensionRegistry, tag)) continue;
                        done = true;
                    }
                }
                catch (InvalidProtocolBufferException e) {
                    throw e.setUnfinishedMessage((MessageLite)this);
                }
                catch (IOException e) {
                    throw new InvalidProtocolBufferException(e).setUnfinishedMessage((MessageLite)this);
                }
                finally {
                    this.unknownFields = unknownFields.build();
                    this.makeExtensionsImmutable();
                }
            }

            public static final Descriptors.Descriptor getDescriptor() {
                return internal_static_Mysqlx_Cursor_Open_OneOfMessage_descriptor;
            }

            protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                return internal_static_Mysqlx_Cursor_Open_OneOfMessage_fieldAccessorTable.ensureFieldAccessorsInitialized(OneOfMessage.class, Builder.class);
            }

            @Override
            public boolean hasType() {
                return (this.bitField0_ & 1) != 0;
            }

            @Override
            public Type getType() {
                Type result = Type.valueOf(this.type_);
                return result == null ? Type.PREPARE_EXECUTE : result;
            }

            @Override
            public boolean hasPrepareExecute() {
                return (this.bitField0_ & 2) != 0;
            }

            @Override
            public MysqlxPrepare.Execute getPrepareExecute() {
                return this.prepareExecute_ == null ? MysqlxPrepare.Execute.getDefaultInstance() : this.prepareExecute_;
            }

            @Override
            public MysqlxPrepare.ExecuteOrBuilder getPrepareExecuteOrBuilder() {
                return this.prepareExecute_ == null ? MysqlxPrepare.Execute.getDefaultInstance() : this.prepareExecute_;
            }

            public final boolean isInitialized() {
                byte isInitialized = this.memoizedIsInitialized;
                if (isInitialized == 1) {
                    return true;
                }
                if (isInitialized == 0) {
                    return false;
                }
                if (!this.hasType()) {
                    this.memoizedIsInitialized = 0;
                    return false;
                }
                if (this.hasPrepareExecute() && !this.getPrepareExecute().isInitialized()) {
                    this.memoizedIsInitialized = 0;
                    return false;
                }
                this.memoizedIsInitialized = 1;
                return true;
            }

            public void writeTo(CodedOutputStream output) throws IOException {
                if ((this.bitField0_ & 1) != 0) {
                    output.writeEnum(1, this.type_);
                }
                if ((this.bitField0_ & 2) != 0) {
                    output.writeMessage(2, (MessageLite)this.getPrepareExecute());
                }
                this.unknownFields.writeTo(output);
            }

            public int getSerializedSize() {
                int size = this.memoizedSize;
                if (size != -1) {
                    return size;
                }
                size = 0;
                if ((this.bitField0_ & 1) != 0) {
                    size += CodedOutputStream.computeEnumSize((int)1, (int)this.type_);
                }
                if ((this.bitField0_ & 2) != 0) {
                    size += CodedOutputStream.computeMessageSize((int)2, (MessageLite)this.getPrepareExecute());
                }
                this.memoizedSize = size += this.unknownFields.getSerializedSize();
                return size;
            }

            public boolean equals(Object obj) {
                if (obj == this) {
                    return true;
                }
                if (!(obj instanceof OneOfMessage)) {
                    return super.equals(obj);
                }
                OneOfMessage other = (OneOfMessage)obj;
                if (this.hasType() != other.hasType()) {
                    return false;
                }
                if (this.hasType() && this.type_ != other.type_) {
                    return false;
                }
                if (this.hasPrepareExecute() != other.hasPrepareExecute()) {
                    return false;
                }
                if (this.hasPrepareExecute() && !this.getPrepareExecute().equals(other.getPrepareExecute())) {
                    return false;
                }
                return this.unknownFields.equals((Object)other.unknownFields);
            }

            public int hashCode() {
                if (this.memoizedHashCode != 0) {
                    return this.memoizedHashCode;
                }
                int hash = 41;
                hash = 19 * hash + OneOfMessage.getDescriptor().hashCode();
                if (this.hasType()) {
                    hash = 37 * hash + 1;
                    hash = 53 * hash + this.type_;
                }
                if (this.hasPrepareExecute()) {
                    hash = 37 * hash + 2;
                    hash = 53 * hash + this.getPrepareExecute().hashCode();
                }
                this.memoizedHashCode = hash = 29 * hash + this.unknownFields.hashCode();
                return hash;
            }

            public static OneOfMessage parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
                return (OneOfMessage)PARSER.parseFrom(data);
            }

            public static OneOfMessage parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                return (OneOfMessage)PARSER.parseFrom(data, extensionRegistry);
            }

            public static OneOfMessage parseFrom(ByteString data) throws InvalidProtocolBufferException {
                return (OneOfMessage)PARSER.parseFrom(data);
            }

            public static OneOfMessage parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                return (OneOfMessage)PARSER.parseFrom(data, extensionRegistry);
            }

            public static OneOfMessage parseFrom(byte[] data) throws InvalidProtocolBufferException {
                return (OneOfMessage)PARSER.parseFrom(data);
            }

            public static OneOfMessage parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                return (OneOfMessage)PARSER.parseFrom(data, extensionRegistry);
            }

            public static OneOfMessage parseFrom(InputStream input) throws IOException {
                return (OneOfMessage)GeneratedMessageV3.parseWithIOException(PARSER, (InputStream)input);
            }

            public static OneOfMessage parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                return (OneOfMessage)GeneratedMessageV3.parseWithIOException(PARSER, (InputStream)input, (ExtensionRegistryLite)extensionRegistry);
            }

            public static OneOfMessage parseDelimitedFrom(InputStream input) throws IOException {
                return (OneOfMessage)GeneratedMessageV3.parseDelimitedWithIOException(PARSER, (InputStream)input);
            }

            public static OneOfMessage parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                return (OneOfMessage)GeneratedMessageV3.parseDelimitedWithIOException(PARSER, (InputStream)input, (ExtensionRegistryLite)extensionRegistry);
            }

            public static OneOfMessage parseFrom(CodedInputStream input) throws IOException {
                return (OneOfMessage)GeneratedMessageV3.parseWithIOException(PARSER, (CodedInputStream)input);
            }

            public static OneOfMessage parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                return (OneOfMessage)GeneratedMessageV3.parseWithIOException(PARSER, (CodedInputStream)input, (ExtensionRegistryLite)extensionRegistry);
            }

            public Builder newBuilderForType() {
                return OneOfMessage.newBuilder();
            }

            public static Builder newBuilder() {
                return DEFAULT_INSTANCE.toBuilder();
            }

            public static Builder newBuilder(OneOfMessage prototype) {
                return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
            }

            public Builder toBuilder() {
                return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
            }

            protected Builder newBuilderForType(GeneratedMessageV3.BuilderParent parent) {
                Builder builder = new Builder(parent);
                return builder;
            }

            public static OneOfMessage getDefaultInstance() {
                return DEFAULT_INSTANCE;
            }

            public static Parser<OneOfMessage> parser() {
                return PARSER;
            }

            public Parser<OneOfMessage> getParserForType() {
                return PARSER;
            }

            public OneOfMessage getDefaultInstanceForType() {
                return DEFAULT_INSTANCE;
            }

            public static final class Builder
            extends GeneratedMessageV3.Builder<Builder>
            implements OneOfMessageOrBuilder {
                private int bitField0_;
                private int type_ = 0;
                private MysqlxPrepare.Execute prepareExecute_;
                private SingleFieldBuilderV3<MysqlxPrepare.Execute, MysqlxPrepare.Execute.Builder, MysqlxPrepare.ExecuteOrBuilder> prepareExecuteBuilder_;

                public static final Descriptors.Descriptor getDescriptor() {
                    return internal_static_Mysqlx_Cursor_Open_OneOfMessage_descriptor;
                }

                protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                    return internal_static_Mysqlx_Cursor_Open_OneOfMessage_fieldAccessorTable.ensureFieldAccessorsInitialized(OneOfMessage.class, Builder.class);
                }

                private Builder() {
                    this.maybeForceBuilderInitialization();
                }

                private Builder(GeneratedMessageV3.BuilderParent parent) {
                    super(parent);
                    this.maybeForceBuilderInitialization();
                }

                private void maybeForceBuilderInitialization() {
                    if (alwaysUseFieldBuilders) {
                        this.getPrepareExecuteFieldBuilder();
                    }
                }

                public Builder clear() {
                    super.clear();
                    this.type_ = 0;
                    this.bitField0_ &= 0xFFFFFFFE;
                    if (this.prepareExecuteBuilder_ == null) {
                        this.prepareExecute_ = null;
                    } else {
                        this.prepareExecuteBuilder_.clear();
                    }
                    this.bitField0_ &= 0xFFFFFFFD;
                    return this;
                }

                public Descriptors.Descriptor getDescriptorForType() {
                    return internal_static_Mysqlx_Cursor_Open_OneOfMessage_descriptor;
                }

                public OneOfMessage getDefaultInstanceForType() {
                    return OneOfMessage.getDefaultInstance();
                }

                public OneOfMessage build() {
                    OneOfMessage result = this.buildPartial();
                    if (!result.isInitialized()) {
                        throw Builder.newUninitializedMessageException((Message)result);
                    }
                    return result;
                }

                public OneOfMessage buildPartial() {
                    OneOfMessage result = new OneOfMessage(this);
                    int from_bitField0_ = this.bitField0_;
                    int to_bitField0_ = 0;
                    if ((from_bitField0_ & 1) != 0) {
                        to_bitField0_ |= 1;
                    }
                    result.type_ = this.type_;
                    if ((from_bitField0_ & 2) != 0) {
                        if (this.prepareExecuteBuilder_ == null) {
                            result.prepareExecute_ = this.prepareExecute_;
                        } else {
                            result.prepareExecute_ = (MysqlxPrepare.Execute)this.prepareExecuteBuilder_.build();
                        }
                        to_bitField0_ |= 2;
                    }
                    result.bitField0_ = to_bitField0_;
                    this.onBuilt();
                    return result;
                }

                public Builder clone() {
                    return (Builder)super.clone();
                }

                public Builder setField(Descriptors.FieldDescriptor field, Object value) {
                    return (Builder)super.setField(field, value);
                }

                public Builder clearField(Descriptors.FieldDescriptor field) {
                    return (Builder)super.clearField(field);
                }

                public Builder clearOneof(Descriptors.OneofDescriptor oneof) {
                    return (Builder)super.clearOneof(oneof);
                }

                public Builder setRepeatedField(Descriptors.FieldDescriptor field, int index, Object value) {
                    return (Builder)super.setRepeatedField(field, index, value);
                }

                public Builder addRepeatedField(Descriptors.FieldDescriptor field, Object value) {
                    return (Builder)super.addRepeatedField(field, value);
                }

                public Builder mergeFrom(Message other) {
                    if (other instanceof OneOfMessage) {
                        return this.mergeFrom((OneOfMessage)other);
                    }
                    super.mergeFrom(other);
                    return this;
                }

                public Builder mergeFrom(OneOfMessage other) {
                    if (other == OneOfMessage.getDefaultInstance()) {
                        return this;
                    }
                    if (other.hasType()) {
                        this.setType(other.getType());
                    }
                    if (other.hasPrepareExecute()) {
                        this.mergePrepareExecute(other.getPrepareExecute());
                    }
                    this.mergeUnknownFields(other.unknownFields);
                    this.onChanged();
                    return this;
                }

                public final boolean isInitialized() {
                    if (!this.hasType()) {
                        return false;
                    }
                    return !this.hasPrepareExecute() || this.getPrepareExecute().isInitialized();
                }

                public Builder mergeFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                    OneOfMessage parsedMessage = null;
                    try {
                        parsedMessage = (OneOfMessage)PARSER.parsePartialFrom(input, extensionRegistry);
                    }
                    catch (InvalidProtocolBufferException e) {
                        parsedMessage = (OneOfMessage)e.getUnfinishedMessage();
                        throw e.unwrapIOException();
                    }
                    finally {
                        if (parsedMessage != null) {
                            this.mergeFrom(parsedMessage);
                        }
                    }
                    return this;
                }

                @Override
                public boolean hasType() {
                    return (this.bitField0_ & 1) != 0;
                }

                @Override
                public Type getType() {
                    Type result = Type.valueOf(this.type_);
                    return result == null ? Type.PREPARE_EXECUTE : result;
                }

                public Builder setType(Type value) {
                    if (value == null) {
                        throw new NullPointerException();
                    }
                    this.bitField0_ |= 1;
                    this.type_ = value.getNumber();
                    this.onChanged();
                    return this;
                }

                public Builder clearType() {
                    this.bitField0_ &= 0xFFFFFFFE;
                    this.type_ = 0;
                    this.onChanged();
                    return this;
                }

                @Override
                public boolean hasPrepareExecute() {
                    return (this.bitField0_ & 2) != 0;
                }

                @Override
                public MysqlxPrepare.Execute getPrepareExecute() {
                    if (this.prepareExecuteBuilder_ == null) {
                        return this.prepareExecute_ == null ? MysqlxPrepare.Execute.getDefaultInstance() : this.prepareExecute_;
                    }
                    return (MysqlxPrepare.Execute)this.prepareExecuteBuilder_.getMessage();
                }

                public Builder setPrepareExecute(MysqlxPrepare.Execute value) {
                    if (this.prepareExecuteBuilder_ == null) {
                        if (value == null) {
                            throw new NullPointerException();
                        }
                        this.prepareExecute_ = value;
                        this.onChanged();
                    } else {
                        this.prepareExecuteBuilder_.setMessage((AbstractMessage)value);
                    }
                    this.bitField0_ |= 2;
                    return this;
                }

                public Builder setPrepareExecute(MysqlxPrepare.Execute.Builder builderForValue) {
                    if (this.prepareExecuteBuilder_ == null) {
                        this.prepareExecute_ = builderForValue.build();
                        this.onChanged();
                    } else {
                        this.prepareExecuteBuilder_.setMessage((AbstractMessage)builderForValue.build());
                    }
                    this.bitField0_ |= 2;
                    return this;
                }

                public Builder mergePrepareExecute(MysqlxPrepare.Execute value) {
                    if (this.prepareExecuteBuilder_ == null) {
                        this.prepareExecute_ = (this.bitField0_ & 2) != 0 && this.prepareExecute_ != null && this.prepareExecute_ != MysqlxPrepare.Execute.getDefaultInstance() ? MysqlxPrepare.Execute.newBuilder(this.prepareExecute_).mergeFrom(value).buildPartial() : value;
                        this.onChanged();
                    } else {
                        this.prepareExecuteBuilder_.mergeFrom((AbstractMessage)value);
                    }
                    this.bitField0_ |= 2;
                    return this;
                }

                public Builder clearPrepareExecute() {
                    if (this.prepareExecuteBuilder_ == null) {
                        this.prepareExecute_ = null;
                        this.onChanged();
                    } else {
                        this.prepareExecuteBuilder_.clear();
                    }
                    this.bitField0_ &= 0xFFFFFFFD;
                    return this;
                }

                public MysqlxPrepare.Execute.Builder getPrepareExecuteBuilder() {
                    this.bitField0_ |= 2;
                    this.onChanged();
                    return (MysqlxPrepare.Execute.Builder)this.getPrepareExecuteFieldBuilder().getBuilder();
                }

                @Override
                public MysqlxPrepare.ExecuteOrBuilder getPrepareExecuteOrBuilder() {
                    if (this.prepareExecuteBuilder_ != null) {
                        return (MysqlxPrepare.ExecuteOrBuilder)this.prepareExecuteBuilder_.getMessageOrBuilder();
                    }
                    return this.prepareExecute_ == null ? MysqlxPrepare.Execute.getDefaultInstance() : this.prepareExecute_;
                }

                private SingleFieldBuilderV3<MysqlxPrepare.Execute, MysqlxPrepare.Execute.Builder, MysqlxPrepare.ExecuteOrBuilder> getPrepareExecuteFieldBuilder() {
                    if (this.prepareExecuteBuilder_ == null) {
                        this.prepareExecuteBuilder_ = new SingleFieldBuilderV3((AbstractMessage)this.getPrepareExecute(), (AbstractMessage.BuilderParent)this.getParentForChildren(), this.isClean());
                        this.prepareExecute_ = null;
                    }
                    return this.prepareExecuteBuilder_;
                }

                public final Builder setUnknownFields(UnknownFieldSet unknownFields) {
                    return (Builder)super.setUnknownFields(unknownFields);
                }

                public final Builder mergeUnknownFields(UnknownFieldSet unknownFields) {
                    return (Builder)super.mergeUnknownFields(unknownFields);
                }
            }

            public static enum Type implements ProtocolMessageEnum
            {
                PREPARE_EXECUTE(0);

                public static final int PREPARE_EXECUTE_VALUE = 0;
                private static final Internal.EnumLiteMap<Type> internalValueMap;
                private static final Type[] VALUES;
                private final int value;

                public final int getNumber() {
                    return this.value;
                }

                @Deprecated
                public static Type valueOf(int value) {
                    return Type.forNumber(value);
                }

                public static Type forNumber(int value) {
                    switch (value) {
                        case 0: {
                            return PREPARE_EXECUTE;
                        }
                    }
                    return null;
                }

                public static Internal.EnumLiteMap<Type> internalGetValueMap() {
                    return internalValueMap;
                }

                public final Descriptors.EnumValueDescriptor getValueDescriptor() {
                    return (Descriptors.EnumValueDescriptor)Type.getDescriptor().getValues().get(this.ordinal());
                }

                public final Descriptors.EnumDescriptor getDescriptorForType() {
                    return Type.getDescriptor();
                }

                public static final Descriptors.EnumDescriptor getDescriptor() {
                    return (Descriptors.EnumDescriptor)OneOfMessage.getDescriptor().getEnumTypes().get(0);
                }

                public static Type valueOf(Descriptors.EnumValueDescriptor desc) {
                    if (desc.getType() != Type.getDescriptor()) {
                        throw new IllegalArgumentException("EnumValueDescriptor is not for this type.");
                    }
                    return VALUES[desc.getIndex()];
                }

                private Type(int value) {
                    this.value = value;
                }

                static {
                    internalValueMap = new Internal.EnumLiteMap<Type>(){

                        public Type findValueByNumber(int number) {
                            return Type.forNumber(number);
                        }
                    };
                    VALUES = Type.values();
                }
            }
        }

        public static interface OneOfMessageOrBuilder
        extends MessageOrBuilder {
            public boolean hasType();

            public OneOfMessage.Type getType();

            public boolean hasPrepareExecute();

            public MysqlxPrepare.Execute getPrepareExecute();

            public MysqlxPrepare.ExecuteOrBuilder getPrepareExecuteOrBuilder();
        }
    }

    public static interface OpenOrBuilder
    extends MessageOrBuilder {
        public boolean hasCursorId();

        public int getCursorId();

        public boolean hasStmt();

        public Open.OneOfMessage getStmt();

        public Open.OneOfMessageOrBuilder getStmtOrBuilder();

        public boolean hasFetchRows();

        public long getFetchRows();
    }
}

