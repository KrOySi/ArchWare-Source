/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  com.google.protobuf.AbstractMessageLite$Builder
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
 *  com.google.protobuf.UnknownFieldSet
 *  com.google.protobuf.UnknownFieldSet$Builder
 */
package com.mysql.cj.x.protobuf;

import com.google.protobuf.AbstractMessageLite;
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
import com.google.protobuf.UnknownFieldSet;
import com.mysql.cj.x.protobuf.Mysqlx;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class MysqlxResultset {
    private static final Descriptors.Descriptor internal_static_Mysqlx_Resultset_FetchDoneMoreOutParams_descriptor;
    private static final GeneratedMessageV3.FieldAccessorTable internal_static_Mysqlx_Resultset_FetchDoneMoreOutParams_fieldAccessorTable;
    private static final Descriptors.Descriptor internal_static_Mysqlx_Resultset_FetchDoneMoreResultsets_descriptor;
    private static final GeneratedMessageV3.FieldAccessorTable internal_static_Mysqlx_Resultset_FetchDoneMoreResultsets_fieldAccessorTable;
    private static final Descriptors.Descriptor internal_static_Mysqlx_Resultset_FetchDone_descriptor;
    private static final GeneratedMessageV3.FieldAccessorTable internal_static_Mysqlx_Resultset_FetchDone_fieldAccessorTable;
    private static final Descriptors.Descriptor internal_static_Mysqlx_Resultset_FetchSuspended_descriptor;
    private static final GeneratedMessageV3.FieldAccessorTable internal_static_Mysqlx_Resultset_FetchSuspended_fieldAccessorTable;
    private static final Descriptors.Descriptor internal_static_Mysqlx_Resultset_ColumnMetaData_descriptor;
    private static final GeneratedMessageV3.FieldAccessorTable internal_static_Mysqlx_Resultset_ColumnMetaData_fieldAccessorTable;
    private static final Descriptors.Descriptor internal_static_Mysqlx_Resultset_Row_descriptor;
    private static final GeneratedMessageV3.FieldAccessorTable internal_static_Mysqlx_Resultset_Row_fieldAccessorTable;
    private static Descriptors.FileDescriptor descriptor;

    private MysqlxResultset() {
    }

    public static void registerAllExtensions(ExtensionRegistryLite registry) {
    }

    public static void registerAllExtensions(ExtensionRegistry registry) {
        MysqlxResultset.registerAllExtensions((ExtensionRegistryLite)registry);
    }

    public static Descriptors.FileDescriptor getDescriptor() {
        return descriptor;
    }

    static {
        String[] descriptorData = new String[]{"\n\u0016mysqlx_resultset.proto\u0012\u0010Mysqlx.Resultset\u001a\fmysqlx.proto\"\u001e\n\u0016FetchDoneMoreOutParams:\u0004\u0090\u00ea0\u0012\"\u001f\n\u0017FetchDoneMoreResultsets:\u0004\u0090\u00ea0\u0010\"\u0011\n\tFetchDone:\u0004\u0090\u00ea0\u000e\"\u0016\n\u000eFetchSuspended:\u0004\u0090\u00ea0\u000f\"\u00a5\u0003\n\u000eColumnMetaData\u00128\n\u0004type\u0018\u0001 \u0002(\u000e2*.Mysqlx.Resultset.ColumnMetaData.FieldType\u0012\f\n\u0004name\u0018\u0002 \u0001(\f\u0012\u0015\n\roriginal_name\u0018\u0003 \u0001(\f\u0012\r\n\u0005table\u0018\u0004 \u0001(\f\u0012\u0016\n\u000eoriginal_table\u0018\u0005 \u0001(\f\u0012\u000e\n\u0006schema\u0018\u0006 \u0001(\f\u0012\u000f\n\u0007catalog\u0018\u0007 \u0001(\f\u0012\u0011\n\tcollation\u0018\b \u0001(\u0004\u0012\u0019\n\u0011fractional_digits\u0018\t \u0001(\r\u0012\u000e\n\u0006length\u0018\n \u0001(\r\u0012\r\n\u0005flags\u0018\u000b \u0001(\r\u0012\u0014\n\fcontent_type\u0018\f \u0001(\r\"\u0082\u0001\n\tFieldType\u0012\b\n\u0004SINT\u0010\u0001\u0012\b\n\u0004UINT\u0010\u0002\u0012\n\n\u0006DOUBLE\u0010\u0005\u0012\t\n\u0005FLOAT\u0010\u0006\u0012\t\n\u0005BYTES\u0010\u0007\u0012\b\n\u0004TIME\u0010\n\u0012\f\n\bDATETIME\u0010\f\u0012\u0007\n\u0003SET\u0010\u000f\u0012\b\n\u0004ENUM\u0010\u0010\u0012\u0007\n\u0003BIT\u0010\u0011\u0012\u000b\n\u0007DECIMAL\u0010\u0012:\u0004\u0090\u00ea0\f\"\u001a\n\u0003Row\u0012\r\n\u0005field\u0018\u0001 \u0003(\f:\u0004\u0090\u00ea0\r*4\n\u0011ContentType_BYTES\u0012\f\n\bGEOMETRY\u0010\u0001\u0012\b\n\u0004JSON\u0010\u0002\u0012\u0007\n\u0003XML\u0010\u0003*.\n\u0014ContentType_DATETIME\u0012\b\n\u0004DATE\u0010\u0001\u0012\f\n\bDATETIME\u0010\u0002B\u0019\n\u0017com.mysql.cj.x.protobuf"};
        descriptor = Descriptors.FileDescriptor.internalBuildGeneratedFileFrom((String[])descriptorData, (Descriptors.FileDescriptor[])new Descriptors.FileDescriptor[]{Mysqlx.getDescriptor()});
        internal_static_Mysqlx_Resultset_FetchDoneMoreOutParams_descriptor = (Descriptors.Descriptor)MysqlxResultset.getDescriptor().getMessageTypes().get(0);
        internal_static_Mysqlx_Resultset_FetchDoneMoreOutParams_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(internal_static_Mysqlx_Resultset_FetchDoneMoreOutParams_descriptor, new String[0]);
        internal_static_Mysqlx_Resultset_FetchDoneMoreResultsets_descriptor = (Descriptors.Descriptor)MysqlxResultset.getDescriptor().getMessageTypes().get(1);
        internal_static_Mysqlx_Resultset_FetchDoneMoreResultsets_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(internal_static_Mysqlx_Resultset_FetchDoneMoreResultsets_descriptor, new String[0]);
        internal_static_Mysqlx_Resultset_FetchDone_descriptor = (Descriptors.Descriptor)MysqlxResultset.getDescriptor().getMessageTypes().get(2);
        internal_static_Mysqlx_Resultset_FetchDone_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(internal_static_Mysqlx_Resultset_FetchDone_descriptor, new String[0]);
        internal_static_Mysqlx_Resultset_FetchSuspended_descriptor = (Descriptors.Descriptor)MysqlxResultset.getDescriptor().getMessageTypes().get(3);
        internal_static_Mysqlx_Resultset_FetchSuspended_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(internal_static_Mysqlx_Resultset_FetchSuspended_descriptor, new String[0]);
        internal_static_Mysqlx_Resultset_ColumnMetaData_descriptor = (Descriptors.Descriptor)MysqlxResultset.getDescriptor().getMessageTypes().get(4);
        internal_static_Mysqlx_Resultset_ColumnMetaData_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(internal_static_Mysqlx_Resultset_ColumnMetaData_descriptor, new String[]{"Type", "Name", "OriginalName", "Table", "OriginalTable", "Schema", "Catalog", "Collation", "FractionalDigits", "Length", "Flags", "ContentType"});
        internal_static_Mysqlx_Resultset_Row_descriptor = (Descriptors.Descriptor)MysqlxResultset.getDescriptor().getMessageTypes().get(5);
        internal_static_Mysqlx_Resultset_Row_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(internal_static_Mysqlx_Resultset_Row_descriptor, new String[]{"Field"});
        ExtensionRegistry registry = ExtensionRegistry.newInstance();
        registry.add(Mysqlx.serverMessageId);
        Descriptors.FileDescriptor.internalUpdateFileDescriptor((Descriptors.FileDescriptor)descriptor, (ExtensionRegistry)registry);
        Mysqlx.getDescriptor();
    }

    public static final class Row
    extends GeneratedMessageV3
    implements RowOrBuilder {
        private static final long serialVersionUID = 0L;
        public static final int FIELD_FIELD_NUMBER = 1;
        private List<ByteString> field_;
        private byte memoizedIsInitialized = (byte)-1;
        private static final Row DEFAULT_INSTANCE = new Row();
        @Deprecated
        public static final Parser<Row> PARSER = new AbstractParser<Row>(){

            public Row parsePartialFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                return new Row(input, extensionRegistry);
            }
        };

        private Row(GeneratedMessageV3.Builder<?> builder) {
            super(builder);
        }

        private Row() {
            this.field_ = Collections.emptyList();
        }

        protected Object newInstance(GeneratedMessageV3.UnusedPrivateParameter unused) {
            return new Row();
        }

        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        private Row(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
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
                        case 10: {
                            if (!(mutable_bitField0_ & true)) {
                                this.field_ = new ArrayList<ByteString>();
                                mutable_bitField0_ |= true;
                            }
                            this.field_.add(input.readBytes());
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
                if (mutable_bitField0_ & true) {
                    this.field_ = Collections.unmodifiableList(this.field_);
                }
                this.unknownFields = unknownFields.build();
                this.makeExtensionsImmutable();
            }
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return internal_static_Mysqlx_Resultset_Row_descriptor;
        }

        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return internal_static_Mysqlx_Resultset_Row_fieldAccessorTable.ensureFieldAccessorsInitialized(Row.class, Builder.class);
        }

        @Override
        public List<ByteString> getFieldList() {
            return this.field_;
        }

        @Override
        public int getFieldCount() {
            return this.field_.size();
        }

        @Override
        public ByteString getField(int index) {
            return this.field_.get(index);
        }

        public final boolean isInitialized() {
            byte isInitialized = this.memoizedIsInitialized;
            if (isInitialized == 1) {
                return true;
            }
            if (isInitialized == 0) {
                return false;
            }
            this.memoizedIsInitialized = 1;
            return true;
        }

        public void writeTo(CodedOutputStream output) throws IOException {
            for (int i = 0; i < this.field_.size(); ++i) {
                output.writeBytes(1, this.field_.get(i));
            }
            this.unknownFields.writeTo(output);
        }

        public int getSerializedSize() {
            int size = this.memoizedSize;
            if (size != -1) {
                return size;
            }
            size = 0;
            int dataSize = 0;
            for (int i = 0; i < this.field_.size(); ++i) {
                dataSize += CodedOutputStream.computeBytesSizeNoTag((ByteString)this.field_.get(i));
            }
            size += dataSize;
            size += 1 * this.getFieldList().size();
            this.memoizedSize = size += this.unknownFields.getSerializedSize();
            return size;
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof Row)) {
                return super.equals(obj);
            }
            Row other = (Row)obj;
            if (!this.getFieldList().equals(other.getFieldList())) {
                return false;
            }
            return this.unknownFields.equals((Object)other.unknownFields);
        }

        public int hashCode() {
            if (this.memoizedHashCode != 0) {
                return this.memoizedHashCode;
            }
            int hash = 41;
            hash = 19 * hash + Row.getDescriptor().hashCode();
            if (this.getFieldCount() > 0) {
                hash = 37 * hash + 1;
                hash = 53 * hash + this.getFieldList().hashCode();
            }
            this.memoizedHashCode = hash = 29 * hash + this.unknownFields.hashCode();
            return hash;
        }

        public static Row parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
            return (Row)PARSER.parseFrom(data);
        }

        public static Row parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (Row)PARSER.parseFrom(data, extensionRegistry);
        }

        public static Row parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return (Row)PARSER.parseFrom(data);
        }

        public static Row parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (Row)PARSER.parseFrom(data, extensionRegistry);
        }

        public static Row parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return (Row)PARSER.parseFrom(data);
        }

        public static Row parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (Row)PARSER.parseFrom(data, extensionRegistry);
        }

        public static Row parseFrom(InputStream input) throws IOException {
            return (Row)GeneratedMessageV3.parseWithIOException(PARSER, (InputStream)input);
        }

        public static Row parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (Row)GeneratedMessageV3.parseWithIOException(PARSER, (InputStream)input, (ExtensionRegistryLite)extensionRegistry);
        }

        public static Row parseDelimitedFrom(InputStream input) throws IOException {
            return (Row)GeneratedMessageV3.parseDelimitedWithIOException(PARSER, (InputStream)input);
        }

        public static Row parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (Row)GeneratedMessageV3.parseDelimitedWithIOException(PARSER, (InputStream)input, (ExtensionRegistryLite)extensionRegistry);
        }

        public static Row parseFrom(CodedInputStream input) throws IOException {
            return (Row)GeneratedMessageV3.parseWithIOException(PARSER, (CodedInputStream)input);
        }

        public static Row parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (Row)GeneratedMessageV3.parseWithIOException(PARSER, (CodedInputStream)input, (ExtensionRegistryLite)extensionRegistry);
        }

        public Builder newBuilderForType() {
            return Row.newBuilder();
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(Row prototype) {
            return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
        }

        public Builder toBuilder() {
            return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
        }

        protected Builder newBuilderForType(GeneratedMessageV3.BuilderParent parent) {
            Builder builder = new Builder(parent);
            return builder;
        }

        public static Row getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<Row> parser() {
            return PARSER;
        }

        public Parser<Row> getParserForType() {
            return PARSER;
        }

        public Row getDefaultInstanceForType() {
            return DEFAULT_INSTANCE;
        }

        public static final class Builder
        extends GeneratedMessageV3.Builder<Builder>
        implements RowOrBuilder {
            private int bitField0_;
            private List<ByteString> field_ = Collections.emptyList();

            public static final Descriptors.Descriptor getDescriptor() {
                return internal_static_Mysqlx_Resultset_Row_descriptor;
            }

            protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                return internal_static_Mysqlx_Resultset_Row_fieldAccessorTable.ensureFieldAccessorsInitialized(Row.class, Builder.class);
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
                this.field_ = Collections.emptyList();
                this.bitField0_ &= 0xFFFFFFFE;
                return this;
            }

            public Descriptors.Descriptor getDescriptorForType() {
                return internal_static_Mysqlx_Resultset_Row_descriptor;
            }

            public Row getDefaultInstanceForType() {
                return Row.getDefaultInstance();
            }

            public Row build() {
                Row result = this.buildPartial();
                if (!result.isInitialized()) {
                    throw Builder.newUninitializedMessageException((Message)result);
                }
                return result;
            }

            public Row buildPartial() {
                Row result = new Row(this);
                int from_bitField0_ = this.bitField0_;
                if ((this.bitField0_ & 1) != 0) {
                    this.field_ = Collections.unmodifiableList(this.field_);
                    this.bitField0_ &= 0xFFFFFFFE;
                }
                result.field_ = this.field_;
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
                if (other instanceof Row) {
                    return this.mergeFrom((Row)other);
                }
                super.mergeFrom(other);
                return this;
            }

            public Builder mergeFrom(Row other) {
                if (other == Row.getDefaultInstance()) {
                    return this;
                }
                if (!other.field_.isEmpty()) {
                    if (this.field_.isEmpty()) {
                        this.field_ = other.field_;
                        this.bitField0_ &= 0xFFFFFFFE;
                    } else {
                        this.ensureFieldIsMutable();
                        this.field_.addAll(other.field_);
                    }
                    this.onChanged();
                }
                this.mergeUnknownFields(other.unknownFields);
                this.onChanged();
                return this;
            }

            public final boolean isInitialized() {
                return true;
            }

            public Builder mergeFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                Row parsedMessage = null;
                try {
                    parsedMessage = (Row)PARSER.parsePartialFrom(input, extensionRegistry);
                }
                catch (InvalidProtocolBufferException e) {
                    parsedMessage = (Row)e.getUnfinishedMessage();
                    throw e.unwrapIOException();
                }
                finally {
                    if (parsedMessage != null) {
                        this.mergeFrom(parsedMessage);
                    }
                }
                return this;
            }

            private void ensureFieldIsMutable() {
                if ((this.bitField0_ & 1) == 0) {
                    this.field_ = new ArrayList<ByteString>(this.field_);
                    this.bitField0_ |= 1;
                }
            }

            @Override
            public List<ByteString> getFieldList() {
                return (this.bitField0_ & 1) != 0 ? Collections.unmodifiableList(this.field_) : this.field_;
            }

            @Override
            public int getFieldCount() {
                return this.field_.size();
            }

            @Override
            public ByteString getField(int index) {
                return this.field_.get(index);
            }

            public Builder setField(int index, ByteString value) {
                if (value == null) {
                    throw new NullPointerException();
                }
                this.ensureFieldIsMutable();
                this.field_.set(index, value);
                this.onChanged();
                return this;
            }

            public Builder addField(ByteString value) {
                if (value == null) {
                    throw new NullPointerException();
                }
                this.ensureFieldIsMutable();
                this.field_.add(value);
                this.onChanged();
                return this;
            }

            public Builder addAllField(Iterable<? extends ByteString> values) {
                this.ensureFieldIsMutable();
                AbstractMessageLite.Builder.addAll(values, this.field_);
                this.onChanged();
                return this;
            }

            public Builder clearField() {
                this.field_ = Collections.emptyList();
                this.bitField0_ &= 0xFFFFFFFE;
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

    public static interface RowOrBuilder
    extends MessageOrBuilder {
        public List<ByteString> getFieldList();

        public int getFieldCount();

        public ByteString getField(int var1);
    }

    public static final class ColumnMetaData
    extends GeneratedMessageV3
    implements ColumnMetaDataOrBuilder {
        private static final long serialVersionUID = 0L;
        private int bitField0_;
        public static final int TYPE_FIELD_NUMBER = 1;
        private int type_;
        public static final int NAME_FIELD_NUMBER = 2;
        private ByteString name_;
        public static final int ORIGINAL_NAME_FIELD_NUMBER = 3;
        private ByteString originalName_;
        public static final int TABLE_FIELD_NUMBER = 4;
        private ByteString table_;
        public static final int ORIGINAL_TABLE_FIELD_NUMBER = 5;
        private ByteString originalTable_;
        public static final int SCHEMA_FIELD_NUMBER = 6;
        private ByteString schema_;
        public static final int CATALOG_FIELD_NUMBER = 7;
        private ByteString catalog_;
        public static final int COLLATION_FIELD_NUMBER = 8;
        private long collation_;
        public static final int FRACTIONAL_DIGITS_FIELD_NUMBER = 9;
        private int fractionalDigits_;
        public static final int LENGTH_FIELD_NUMBER = 10;
        private int length_;
        public static final int FLAGS_FIELD_NUMBER = 11;
        private int flags_;
        public static final int CONTENT_TYPE_FIELD_NUMBER = 12;
        private int contentType_;
        private byte memoizedIsInitialized = (byte)-1;
        private static final ColumnMetaData DEFAULT_INSTANCE = new ColumnMetaData();
        @Deprecated
        public static final Parser<ColumnMetaData> PARSER = new AbstractParser<ColumnMetaData>(){

            public ColumnMetaData parsePartialFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                return new ColumnMetaData(input, extensionRegistry);
            }
        };

        private ColumnMetaData(GeneratedMessageV3.Builder<?> builder) {
            super(builder);
        }

        private ColumnMetaData() {
            this.type_ = 1;
            this.name_ = ByteString.EMPTY;
            this.originalName_ = ByteString.EMPTY;
            this.table_ = ByteString.EMPTY;
            this.originalTable_ = ByteString.EMPTY;
            this.schema_ = ByteString.EMPTY;
            this.catalog_ = ByteString.EMPTY;
        }

        protected Object newInstance(GeneratedMessageV3.UnusedPrivateParameter unused) {
            return new ColumnMetaData();
        }

        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        private ColumnMetaData(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            this();
            if (extensionRegistry == null) {
                throw new NullPointerException();
            }
            boolean mutable_bitField0_ = false;
            UnknownFieldSet.Builder unknownFields = UnknownFieldSet.newBuilder();
            try {
                boolean done = false;
                block21: while (!done) {
                    int tag = input.readTag();
                    switch (tag) {
                        case 0: {
                            done = true;
                            continue block21;
                        }
                        case 8: {
                            int rawValue = input.readEnum();
                            FieldType value = FieldType.valueOf(rawValue);
                            if (value == null) {
                                unknownFields.mergeVarintField(1, rawValue);
                                continue block21;
                            }
                            this.bitField0_ |= 1;
                            this.type_ = rawValue;
                            continue block21;
                        }
                        case 18: {
                            this.bitField0_ |= 2;
                            this.name_ = input.readBytes();
                            continue block21;
                        }
                        case 26: {
                            this.bitField0_ |= 4;
                            this.originalName_ = input.readBytes();
                            continue block21;
                        }
                        case 34: {
                            this.bitField0_ |= 8;
                            this.table_ = input.readBytes();
                            continue block21;
                        }
                        case 42: {
                            this.bitField0_ |= 0x10;
                            this.originalTable_ = input.readBytes();
                            continue block21;
                        }
                        case 50: {
                            this.bitField0_ |= 0x20;
                            this.schema_ = input.readBytes();
                            continue block21;
                        }
                        case 58: {
                            this.bitField0_ |= 0x40;
                            this.catalog_ = input.readBytes();
                            continue block21;
                        }
                        case 64: {
                            this.bitField0_ |= 0x80;
                            this.collation_ = input.readUInt64();
                            continue block21;
                        }
                        case 72: {
                            this.bitField0_ |= 0x100;
                            this.fractionalDigits_ = input.readUInt32();
                            continue block21;
                        }
                        case 80: {
                            this.bitField0_ |= 0x200;
                            this.length_ = input.readUInt32();
                            continue block21;
                        }
                        case 88: {
                            this.bitField0_ |= 0x400;
                            this.flags_ = input.readUInt32();
                            continue block21;
                        }
                        case 96: {
                            this.bitField0_ |= 0x800;
                            this.contentType_ = input.readUInt32();
                            continue block21;
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
            return internal_static_Mysqlx_Resultset_ColumnMetaData_descriptor;
        }

        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return internal_static_Mysqlx_Resultset_ColumnMetaData_fieldAccessorTable.ensureFieldAccessorsInitialized(ColumnMetaData.class, Builder.class);
        }

        @Override
        public boolean hasType() {
            return (this.bitField0_ & 1) != 0;
        }

        @Override
        public FieldType getType() {
            FieldType result = FieldType.valueOf(this.type_);
            return result == null ? FieldType.SINT : result;
        }

        @Override
        public boolean hasName() {
            return (this.bitField0_ & 2) != 0;
        }

        @Override
        public ByteString getName() {
            return this.name_;
        }

        @Override
        public boolean hasOriginalName() {
            return (this.bitField0_ & 4) != 0;
        }

        @Override
        public ByteString getOriginalName() {
            return this.originalName_;
        }

        @Override
        public boolean hasTable() {
            return (this.bitField0_ & 8) != 0;
        }

        @Override
        public ByteString getTable() {
            return this.table_;
        }

        @Override
        public boolean hasOriginalTable() {
            return (this.bitField0_ & 0x10) != 0;
        }

        @Override
        public ByteString getOriginalTable() {
            return this.originalTable_;
        }

        @Override
        public boolean hasSchema() {
            return (this.bitField0_ & 0x20) != 0;
        }

        @Override
        public ByteString getSchema() {
            return this.schema_;
        }

        @Override
        public boolean hasCatalog() {
            return (this.bitField0_ & 0x40) != 0;
        }

        @Override
        public ByteString getCatalog() {
            return this.catalog_;
        }

        @Override
        public boolean hasCollation() {
            return (this.bitField0_ & 0x80) != 0;
        }

        @Override
        public long getCollation() {
            return this.collation_;
        }

        @Override
        public boolean hasFractionalDigits() {
            return (this.bitField0_ & 0x100) != 0;
        }

        @Override
        public int getFractionalDigits() {
            return this.fractionalDigits_;
        }

        @Override
        public boolean hasLength() {
            return (this.bitField0_ & 0x200) != 0;
        }

        @Override
        public int getLength() {
            return this.length_;
        }

        @Override
        public boolean hasFlags() {
            return (this.bitField0_ & 0x400) != 0;
        }

        @Override
        public int getFlags() {
            return this.flags_;
        }

        @Override
        public boolean hasContentType() {
            return (this.bitField0_ & 0x800) != 0;
        }

        @Override
        public int getContentType() {
            return this.contentType_;
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
            this.memoizedIsInitialized = 1;
            return true;
        }

        public void writeTo(CodedOutputStream output) throws IOException {
            if ((this.bitField0_ & 1) != 0) {
                output.writeEnum(1, this.type_);
            }
            if ((this.bitField0_ & 2) != 0) {
                output.writeBytes(2, this.name_);
            }
            if ((this.bitField0_ & 4) != 0) {
                output.writeBytes(3, this.originalName_);
            }
            if ((this.bitField0_ & 8) != 0) {
                output.writeBytes(4, this.table_);
            }
            if ((this.bitField0_ & 0x10) != 0) {
                output.writeBytes(5, this.originalTable_);
            }
            if ((this.bitField0_ & 0x20) != 0) {
                output.writeBytes(6, this.schema_);
            }
            if ((this.bitField0_ & 0x40) != 0) {
                output.writeBytes(7, this.catalog_);
            }
            if ((this.bitField0_ & 0x80) != 0) {
                output.writeUInt64(8, this.collation_);
            }
            if ((this.bitField0_ & 0x100) != 0) {
                output.writeUInt32(9, this.fractionalDigits_);
            }
            if ((this.bitField0_ & 0x200) != 0) {
                output.writeUInt32(10, this.length_);
            }
            if ((this.bitField0_ & 0x400) != 0) {
                output.writeUInt32(11, this.flags_);
            }
            if ((this.bitField0_ & 0x800) != 0) {
                output.writeUInt32(12, this.contentType_);
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
                size += CodedOutputStream.computeBytesSize((int)2, (ByteString)this.name_);
            }
            if ((this.bitField0_ & 4) != 0) {
                size += CodedOutputStream.computeBytesSize((int)3, (ByteString)this.originalName_);
            }
            if ((this.bitField0_ & 8) != 0) {
                size += CodedOutputStream.computeBytesSize((int)4, (ByteString)this.table_);
            }
            if ((this.bitField0_ & 0x10) != 0) {
                size += CodedOutputStream.computeBytesSize((int)5, (ByteString)this.originalTable_);
            }
            if ((this.bitField0_ & 0x20) != 0) {
                size += CodedOutputStream.computeBytesSize((int)6, (ByteString)this.schema_);
            }
            if ((this.bitField0_ & 0x40) != 0) {
                size += CodedOutputStream.computeBytesSize((int)7, (ByteString)this.catalog_);
            }
            if ((this.bitField0_ & 0x80) != 0) {
                size += CodedOutputStream.computeUInt64Size((int)8, (long)this.collation_);
            }
            if ((this.bitField0_ & 0x100) != 0) {
                size += CodedOutputStream.computeUInt32Size((int)9, (int)this.fractionalDigits_);
            }
            if ((this.bitField0_ & 0x200) != 0) {
                size += CodedOutputStream.computeUInt32Size((int)10, (int)this.length_);
            }
            if ((this.bitField0_ & 0x400) != 0) {
                size += CodedOutputStream.computeUInt32Size((int)11, (int)this.flags_);
            }
            if ((this.bitField0_ & 0x800) != 0) {
                size += CodedOutputStream.computeUInt32Size((int)12, (int)this.contentType_);
            }
            this.memoizedSize = size += this.unknownFields.getSerializedSize();
            return size;
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof ColumnMetaData)) {
                return super.equals(obj);
            }
            ColumnMetaData other = (ColumnMetaData)obj;
            if (this.hasType() != other.hasType()) {
                return false;
            }
            if (this.hasType() && this.type_ != other.type_) {
                return false;
            }
            if (this.hasName() != other.hasName()) {
                return false;
            }
            if (this.hasName() && !this.getName().equals((Object)other.getName())) {
                return false;
            }
            if (this.hasOriginalName() != other.hasOriginalName()) {
                return false;
            }
            if (this.hasOriginalName() && !this.getOriginalName().equals((Object)other.getOriginalName())) {
                return false;
            }
            if (this.hasTable() != other.hasTable()) {
                return false;
            }
            if (this.hasTable() && !this.getTable().equals((Object)other.getTable())) {
                return false;
            }
            if (this.hasOriginalTable() != other.hasOriginalTable()) {
                return false;
            }
            if (this.hasOriginalTable() && !this.getOriginalTable().equals((Object)other.getOriginalTable())) {
                return false;
            }
            if (this.hasSchema() != other.hasSchema()) {
                return false;
            }
            if (this.hasSchema() && !this.getSchema().equals((Object)other.getSchema())) {
                return false;
            }
            if (this.hasCatalog() != other.hasCatalog()) {
                return false;
            }
            if (this.hasCatalog() && !this.getCatalog().equals((Object)other.getCatalog())) {
                return false;
            }
            if (this.hasCollation() != other.hasCollation()) {
                return false;
            }
            if (this.hasCollation() && this.getCollation() != other.getCollation()) {
                return false;
            }
            if (this.hasFractionalDigits() != other.hasFractionalDigits()) {
                return false;
            }
            if (this.hasFractionalDigits() && this.getFractionalDigits() != other.getFractionalDigits()) {
                return false;
            }
            if (this.hasLength() != other.hasLength()) {
                return false;
            }
            if (this.hasLength() && this.getLength() != other.getLength()) {
                return false;
            }
            if (this.hasFlags() != other.hasFlags()) {
                return false;
            }
            if (this.hasFlags() && this.getFlags() != other.getFlags()) {
                return false;
            }
            if (this.hasContentType() != other.hasContentType()) {
                return false;
            }
            if (this.hasContentType() && this.getContentType() != other.getContentType()) {
                return false;
            }
            return this.unknownFields.equals((Object)other.unknownFields);
        }

        public int hashCode() {
            if (this.memoizedHashCode != 0) {
                return this.memoizedHashCode;
            }
            int hash = 41;
            hash = 19 * hash + ColumnMetaData.getDescriptor().hashCode();
            if (this.hasType()) {
                hash = 37 * hash + 1;
                hash = 53 * hash + this.type_;
            }
            if (this.hasName()) {
                hash = 37 * hash + 2;
                hash = 53 * hash + this.getName().hashCode();
            }
            if (this.hasOriginalName()) {
                hash = 37 * hash + 3;
                hash = 53 * hash + this.getOriginalName().hashCode();
            }
            if (this.hasTable()) {
                hash = 37 * hash + 4;
                hash = 53 * hash + this.getTable().hashCode();
            }
            if (this.hasOriginalTable()) {
                hash = 37 * hash + 5;
                hash = 53 * hash + this.getOriginalTable().hashCode();
            }
            if (this.hasSchema()) {
                hash = 37 * hash + 6;
                hash = 53 * hash + this.getSchema().hashCode();
            }
            if (this.hasCatalog()) {
                hash = 37 * hash + 7;
                hash = 53 * hash + this.getCatalog().hashCode();
            }
            if (this.hasCollation()) {
                hash = 37 * hash + 8;
                hash = 53 * hash + Internal.hashLong((long)this.getCollation());
            }
            if (this.hasFractionalDigits()) {
                hash = 37 * hash + 9;
                hash = 53 * hash + this.getFractionalDigits();
            }
            if (this.hasLength()) {
                hash = 37 * hash + 10;
                hash = 53 * hash + this.getLength();
            }
            if (this.hasFlags()) {
                hash = 37 * hash + 11;
                hash = 53 * hash + this.getFlags();
            }
            if (this.hasContentType()) {
                hash = 37 * hash + 12;
                hash = 53 * hash + this.getContentType();
            }
            this.memoizedHashCode = hash = 29 * hash + this.unknownFields.hashCode();
            return hash;
        }

        public static ColumnMetaData parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
            return (ColumnMetaData)PARSER.parseFrom(data);
        }

        public static ColumnMetaData parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (ColumnMetaData)PARSER.parseFrom(data, extensionRegistry);
        }

        public static ColumnMetaData parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return (ColumnMetaData)PARSER.parseFrom(data);
        }

        public static ColumnMetaData parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (ColumnMetaData)PARSER.parseFrom(data, extensionRegistry);
        }

        public static ColumnMetaData parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return (ColumnMetaData)PARSER.parseFrom(data);
        }

        public static ColumnMetaData parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (ColumnMetaData)PARSER.parseFrom(data, extensionRegistry);
        }

        public static ColumnMetaData parseFrom(InputStream input) throws IOException {
            return (ColumnMetaData)GeneratedMessageV3.parseWithIOException(PARSER, (InputStream)input);
        }

        public static ColumnMetaData parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (ColumnMetaData)GeneratedMessageV3.parseWithIOException(PARSER, (InputStream)input, (ExtensionRegistryLite)extensionRegistry);
        }

        public static ColumnMetaData parseDelimitedFrom(InputStream input) throws IOException {
            return (ColumnMetaData)GeneratedMessageV3.parseDelimitedWithIOException(PARSER, (InputStream)input);
        }

        public static ColumnMetaData parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (ColumnMetaData)GeneratedMessageV3.parseDelimitedWithIOException(PARSER, (InputStream)input, (ExtensionRegistryLite)extensionRegistry);
        }

        public static ColumnMetaData parseFrom(CodedInputStream input) throws IOException {
            return (ColumnMetaData)GeneratedMessageV3.parseWithIOException(PARSER, (CodedInputStream)input);
        }

        public static ColumnMetaData parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (ColumnMetaData)GeneratedMessageV3.parseWithIOException(PARSER, (CodedInputStream)input, (ExtensionRegistryLite)extensionRegistry);
        }

        public Builder newBuilderForType() {
            return ColumnMetaData.newBuilder();
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(ColumnMetaData prototype) {
            return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
        }

        public Builder toBuilder() {
            return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
        }

        protected Builder newBuilderForType(GeneratedMessageV3.BuilderParent parent) {
            Builder builder = new Builder(parent);
            return builder;
        }

        public static ColumnMetaData getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<ColumnMetaData> parser() {
            return PARSER;
        }

        public Parser<ColumnMetaData> getParserForType() {
            return PARSER;
        }

        public ColumnMetaData getDefaultInstanceForType() {
            return DEFAULT_INSTANCE;
        }

        public static final class Builder
        extends GeneratedMessageV3.Builder<Builder>
        implements ColumnMetaDataOrBuilder {
            private int bitField0_;
            private int type_ = 1;
            private ByteString name_ = ByteString.EMPTY;
            private ByteString originalName_ = ByteString.EMPTY;
            private ByteString table_ = ByteString.EMPTY;
            private ByteString originalTable_ = ByteString.EMPTY;
            private ByteString schema_ = ByteString.EMPTY;
            private ByteString catalog_ = ByteString.EMPTY;
            private long collation_;
            private int fractionalDigits_;
            private int length_;
            private int flags_;
            private int contentType_;

            public static final Descriptors.Descriptor getDescriptor() {
                return internal_static_Mysqlx_Resultset_ColumnMetaData_descriptor;
            }

            protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                return internal_static_Mysqlx_Resultset_ColumnMetaData_fieldAccessorTable.ensureFieldAccessorsInitialized(ColumnMetaData.class, Builder.class);
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
                this.type_ = 1;
                this.bitField0_ &= 0xFFFFFFFE;
                this.name_ = ByteString.EMPTY;
                this.bitField0_ &= 0xFFFFFFFD;
                this.originalName_ = ByteString.EMPTY;
                this.bitField0_ &= 0xFFFFFFFB;
                this.table_ = ByteString.EMPTY;
                this.bitField0_ &= 0xFFFFFFF7;
                this.originalTable_ = ByteString.EMPTY;
                this.bitField0_ &= 0xFFFFFFEF;
                this.schema_ = ByteString.EMPTY;
                this.bitField0_ &= 0xFFFFFFDF;
                this.catalog_ = ByteString.EMPTY;
                this.bitField0_ &= 0xFFFFFFBF;
                this.collation_ = 0L;
                this.bitField0_ &= 0xFFFFFF7F;
                this.fractionalDigits_ = 0;
                this.bitField0_ &= 0xFFFFFEFF;
                this.length_ = 0;
                this.bitField0_ &= 0xFFFFFDFF;
                this.flags_ = 0;
                this.bitField0_ &= 0xFFFFFBFF;
                this.contentType_ = 0;
                this.bitField0_ &= 0xFFFFF7FF;
                return this;
            }

            public Descriptors.Descriptor getDescriptorForType() {
                return internal_static_Mysqlx_Resultset_ColumnMetaData_descriptor;
            }

            public ColumnMetaData getDefaultInstanceForType() {
                return ColumnMetaData.getDefaultInstance();
            }

            public ColumnMetaData build() {
                ColumnMetaData result = this.buildPartial();
                if (!result.isInitialized()) {
                    throw Builder.newUninitializedMessageException((Message)result);
                }
                return result;
            }

            public ColumnMetaData buildPartial() {
                ColumnMetaData result = new ColumnMetaData(this);
                int from_bitField0_ = this.bitField0_;
                int to_bitField0_ = 0;
                if ((from_bitField0_ & 1) != 0) {
                    to_bitField0_ |= 1;
                }
                result.type_ = this.type_;
                if ((from_bitField0_ & 2) != 0) {
                    to_bitField0_ |= 2;
                }
                result.name_ = this.name_;
                if ((from_bitField0_ & 4) != 0) {
                    to_bitField0_ |= 4;
                }
                result.originalName_ = this.originalName_;
                if ((from_bitField0_ & 8) != 0) {
                    to_bitField0_ |= 8;
                }
                result.table_ = this.table_;
                if ((from_bitField0_ & 0x10) != 0) {
                    to_bitField0_ |= 0x10;
                }
                result.originalTable_ = this.originalTable_;
                if ((from_bitField0_ & 0x20) != 0) {
                    to_bitField0_ |= 0x20;
                }
                result.schema_ = this.schema_;
                if ((from_bitField0_ & 0x40) != 0) {
                    to_bitField0_ |= 0x40;
                }
                result.catalog_ = this.catalog_;
                if ((from_bitField0_ & 0x80) != 0) {
                    result.collation_ = this.collation_;
                    to_bitField0_ |= 0x80;
                }
                if ((from_bitField0_ & 0x100) != 0) {
                    result.fractionalDigits_ = this.fractionalDigits_;
                    to_bitField0_ |= 0x100;
                }
                if ((from_bitField0_ & 0x200) != 0) {
                    result.length_ = this.length_;
                    to_bitField0_ |= 0x200;
                }
                if ((from_bitField0_ & 0x400) != 0) {
                    result.flags_ = this.flags_;
                    to_bitField0_ |= 0x400;
                }
                if ((from_bitField0_ & 0x800) != 0) {
                    result.contentType_ = this.contentType_;
                    to_bitField0_ |= 0x800;
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
                if (other instanceof ColumnMetaData) {
                    return this.mergeFrom((ColumnMetaData)other);
                }
                super.mergeFrom(other);
                return this;
            }

            public Builder mergeFrom(ColumnMetaData other) {
                if (other == ColumnMetaData.getDefaultInstance()) {
                    return this;
                }
                if (other.hasType()) {
                    this.setType(other.getType());
                }
                if (other.hasName()) {
                    this.setName(other.getName());
                }
                if (other.hasOriginalName()) {
                    this.setOriginalName(other.getOriginalName());
                }
                if (other.hasTable()) {
                    this.setTable(other.getTable());
                }
                if (other.hasOriginalTable()) {
                    this.setOriginalTable(other.getOriginalTable());
                }
                if (other.hasSchema()) {
                    this.setSchema(other.getSchema());
                }
                if (other.hasCatalog()) {
                    this.setCatalog(other.getCatalog());
                }
                if (other.hasCollation()) {
                    this.setCollation(other.getCollation());
                }
                if (other.hasFractionalDigits()) {
                    this.setFractionalDigits(other.getFractionalDigits());
                }
                if (other.hasLength()) {
                    this.setLength(other.getLength());
                }
                if (other.hasFlags()) {
                    this.setFlags(other.getFlags());
                }
                if (other.hasContentType()) {
                    this.setContentType(other.getContentType());
                }
                this.mergeUnknownFields(other.unknownFields);
                this.onChanged();
                return this;
            }

            public final boolean isInitialized() {
                return this.hasType();
            }

            public Builder mergeFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                ColumnMetaData parsedMessage = null;
                try {
                    parsedMessage = (ColumnMetaData)PARSER.parsePartialFrom(input, extensionRegistry);
                }
                catch (InvalidProtocolBufferException e) {
                    parsedMessage = (ColumnMetaData)e.getUnfinishedMessage();
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
            public FieldType getType() {
                FieldType result = FieldType.valueOf(this.type_);
                return result == null ? FieldType.SINT : result;
            }

            public Builder setType(FieldType value) {
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
                this.type_ = 1;
                this.onChanged();
                return this;
            }

            @Override
            public boolean hasName() {
                return (this.bitField0_ & 2) != 0;
            }

            @Override
            public ByteString getName() {
                return this.name_;
            }

            public Builder setName(ByteString value) {
                if (value == null) {
                    throw new NullPointerException();
                }
                this.bitField0_ |= 2;
                this.name_ = value;
                this.onChanged();
                return this;
            }

            public Builder clearName() {
                this.bitField0_ &= 0xFFFFFFFD;
                this.name_ = ColumnMetaData.getDefaultInstance().getName();
                this.onChanged();
                return this;
            }

            @Override
            public boolean hasOriginalName() {
                return (this.bitField0_ & 4) != 0;
            }

            @Override
            public ByteString getOriginalName() {
                return this.originalName_;
            }

            public Builder setOriginalName(ByteString value) {
                if (value == null) {
                    throw new NullPointerException();
                }
                this.bitField0_ |= 4;
                this.originalName_ = value;
                this.onChanged();
                return this;
            }

            public Builder clearOriginalName() {
                this.bitField0_ &= 0xFFFFFFFB;
                this.originalName_ = ColumnMetaData.getDefaultInstance().getOriginalName();
                this.onChanged();
                return this;
            }

            @Override
            public boolean hasTable() {
                return (this.bitField0_ & 8) != 0;
            }

            @Override
            public ByteString getTable() {
                return this.table_;
            }

            public Builder setTable(ByteString value) {
                if (value == null) {
                    throw new NullPointerException();
                }
                this.bitField0_ |= 8;
                this.table_ = value;
                this.onChanged();
                return this;
            }

            public Builder clearTable() {
                this.bitField0_ &= 0xFFFFFFF7;
                this.table_ = ColumnMetaData.getDefaultInstance().getTable();
                this.onChanged();
                return this;
            }

            @Override
            public boolean hasOriginalTable() {
                return (this.bitField0_ & 0x10) != 0;
            }

            @Override
            public ByteString getOriginalTable() {
                return this.originalTable_;
            }

            public Builder setOriginalTable(ByteString value) {
                if (value == null) {
                    throw new NullPointerException();
                }
                this.bitField0_ |= 0x10;
                this.originalTable_ = value;
                this.onChanged();
                return this;
            }

            public Builder clearOriginalTable() {
                this.bitField0_ &= 0xFFFFFFEF;
                this.originalTable_ = ColumnMetaData.getDefaultInstance().getOriginalTable();
                this.onChanged();
                return this;
            }

            @Override
            public boolean hasSchema() {
                return (this.bitField0_ & 0x20) != 0;
            }

            @Override
            public ByteString getSchema() {
                return this.schema_;
            }

            public Builder setSchema(ByteString value) {
                if (value == null) {
                    throw new NullPointerException();
                }
                this.bitField0_ |= 0x20;
                this.schema_ = value;
                this.onChanged();
                return this;
            }

            public Builder clearSchema() {
                this.bitField0_ &= 0xFFFFFFDF;
                this.schema_ = ColumnMetaData.getDefaultInstance().getSchema();
                this.onChanged();
                return this;
            }

            @Override
            public boolean hasCatalog() {
                return (this.bitField0_ & 0x40) != 0;
            }

            @Override
            public ByteString getCatalog() {
                return this.catalog_;
            }

            public Builder setCatalog(ByteString value) {
                if (value == null) {
                    throw new NullPointerException();
                }
                this.bitField0_ |= 0x40;
                this.catalog_ = value;
                this.onChanged();
                return this;
            }

            public Builder clearCatalog() {
                this.bitField0_ &= 0xFFFFFFBF;
                this.catalog_ = ColumnMetaData.getDefaultInstance().getCatalog();
                this.onChanged();
                return this;
            }

            @Override
            public boolean hasCollation() {
                return (this.bitField0_ & 0x80) != 0;
            }

            @Override
            public long getCollation() {
                return this.collation_;
            }

            public Builder setCollation(long value) {
                this.bitField0_ |= 0x80;
                this.collation_ = value;
                this.onChanged();
                return this;
            }

            public Builder clearCollation() {
                this.bitField0_ &= 0xFFFFFF7F;
                this.collation_ = 0L;
                this.onChanged();
                return this;
            }

            @Override
            public boolean hasFractionalDigits() {
                return (this.bitField0_ & 0x100) != 0;
            }

            @Override
            public int getFractionalDigits() {
                return this.fractionalDigits_;
            }

            public Builder setFractionalDigits(int value) {
                this.bitField0_ |= 0x100;
                this.fractionalDigits_ = value;
                this.onChanged();
                return this;
            }

            public Builder clearFractionalDigits() {
                this.bitField0_ &= 0xFFFFFEFF;
                this.fractionalDigits_ = 0;
                this.onChanged();
                return this;
            }

            @Override
            public boolean hasLength() {
                return (this.bitField0_ & 0x200) != 0;
            }

            @Override
            public int getLength() {
                return this.length_;
            }

            public Builder setLength(int value) {
                this.bitField0_ |= 0x200;
                this.length_ = value;
                this.onChanged();
                return this;
            }

            public Builder clearLength() {
                this.bitField0_ &= 0xFFFFFDFF;
                this.length_ = 0;
                this.onChanged();
                return this;
            }

            @Override
            public boolean hasFlags() {
                return (this.bitField0_ & 0x400) != 0;
            }

            @Override
            public int getFlags() {
                return this.flags_;
            }

            public Builder setFlags(int value) {
                this.bitField0_ |= 0x400;
                this.flags_ = value;
                this.onChanged();
                return this;
            }

            public Builder clearFlags() {
                this.bitField0_ &= 0xFFFFFBFF;
                this.flags_ = 0;
                this.onChanged();
                return this;
            }

            @Override
            public boolean hasContentType() {
                return (this.bitField0_ & 0x800) != 0;
            }

            @Override
            public int getContentType() {
                return this.contentType_;
            }

            public Builder setContentType(int value) {
                this.bitField0_ |= 0x800;
                this.contentType_ = value;
                this.onChanged();
                return this;
            }

            public Builder clearContentType() {
                this.bitField0_ &= 0xFFFFF7FF;
                this.contentType_ = 0;
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

        public static enum FieldType implements ProtocolMessageEnum
        {
            SINT(1),
            UINT(2),
            DOUBLE(5),
            FLOAT(6),
            BYTES(7),
            TIME(10),
            DATETIME(12),
            SET(15),
            ENUM(16),
            BIT(17),
            DECIMAL(18);

            public static final int SINT_VALUE = 1;
            public static final int UINT_VALUE = 2;
            public static final int DOUBLE_VALUE = 5;
            public static final int FLOAT_VALUE = 6;
            public static final int BYTES_VALUE = 7;
            public static final int TIME_VALUE = 10;
            public static final int DATETIME_VALUE = 12;
            public static final int SET_VALUE = 15;
            public static final int ENUM_VALUE = 16;
            public static final int BIT_VALUE = 17;
            public static final int DECIMAL_VALUE = 18;
            private static final Internal.EnumLiteMap<FieldType> internalValueMap;
            private static final FieldType[] VALUES;
            private final int value;

            public final int getNumber() {
                return this.value;
            }

            @Deprecated
            public static FieldType valueOf(int value) {
                return FieldType.forNumber(value);
            }

            public static FieldType forNumber(int value) {
                switch (value) {
                    case 1: {
                        return SINT;
                    }
                    case 2: {
                        return UINT;
                    }
                    case 5: {
                        return DOUBLE;
                    }
                    case 6: {
                        return FLOAT;
                    }
                    case 7: {
                        return BYTES;
                    }
                    case 10: {
                        return TIME;
                    }
                    case 12: {
                        return DATETIME;
                    }
                    case 15: {
                        return SET;
                    }
                    case 16: {
                        return ENUM;
                    }
                    case 17: {
                        return BIT;
                    }
                    case 18: {
                        return DECIMAL;
                    }
                }
                return null;
            }

            public static Internal.EnumLiteMap<FieldType> internalGetValueMap() {
                return internalValueMap;
            }

            public final Descriptors.EnumValueDescriptor getValueDescriptor() {
                return (Descriptors.EnumValueDescriptor)FieldType.getDescriptor().getValues().get(this.ordinal());
            }

            public final Descriptors.EnumDescriptor getDescriptorForType() {
                return FieldType.getDescriptor();
            }

            public static final Descriptors.EnumDescriptor getDescriptor() {
                return (Descriptors.EnumDescriptor)ColumnMetaData.getDescriptor().getEnumTypes().get(0);
            }

            public static FieldType valueOf(Descriptors.EnumValueDescriptor desc) {
                if (desc.getType() != FieldType.getDescriptor()) {
                    throw new IllegalArgumentException("EnumValueDescriptor is not for this type.");
                }
                return VALUES[desc.getIndex()];
            }

            private FieldType(int value) {
                this.value = value;
            }

            static {
                internalValueMap = new Internal.EnumLiteMap<FieldType>(){

                    public FieldType findValueByNumber(int number) {
                        return FieldType.forNumber(number);
                    }
                };
                VALUES = FieldType.values();
            }
        }
    }

    public static interface ColumnMetaDataOrBuilder
    extends MessageOrBuilder {
        public boolean hasType();

        public ColumnMetaData.FieldType getType();

        public boolean hasName();

        public ByteString getName();

        public boolean hasOriginalName();

        public ByteString getOriginalName();

        public boolean hasTable();

        public ByteString getTable();

        public boolean hasOriginalTable();

        public ByteString getOriginalTable();

        public boolean hasSchema();

        public ByteString getSchema();

        public boolean hasCatalog();

        public ByteString getCatalog();

        public boolean hasCollation();

        public long getCollation();

        public boolean hasFractionalDigits();

        public int getFractionalDigits();

        public boolean hasLength();

        public int getLength();

        public boolean hasFlags();

        public int getFlags();

        public boolean hasContentType();

        public int getContentType();
    }

    public static final class FetchSuspended
    extends GeneratedMessageV3
    implements FetchSuspendedOrBuilder {
        private static final long serialVersionUID = 0L;
        private byte memoizedIsInitialized = (byte)-1;
        private static final FetchSuspended DEFAULT_INSTANCE = new FetchSuspended();
        @Deprecated
        public static final Parser<FetchSuspended> PARSER = new AbstractParser<FetchSuspended>(){

            public FetchSuspended parsePartialFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                return new FetchSuspended(input, extensionRegistry);
            }
        };

        private FetchSuspended(GeneratedMessageV3.Builder<?> builder) {
            super(builder);
        }

        private FetchSuspended() {
        }

        protected Object newInstance(GeneratedMessageV3.UnusedPrivateParameter unused) {
            return new FetchSuspended();
        }

        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        private FetchSuspended(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            this();
            if (extensionRegistry == null) {
                throw new NullPointerException();
            }
            UnknownFieldSet.Builder unknownFields = UnknownFieldSet.newBuilder();
            try {
                boolean done = false;
                block9: while (!done) {
                    int tag = input.readTag();
                    switch (tag) {
                        case 0: {
                            done = true;
                            continue block9;
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
            return internal_static_Mysqlx_Resultset_FetchSuspended_descriptor;
        }

        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return internal_static_Mysqlx_Resultset_FetchSuspended_fieldAccessorTable.ensureFieldAccessorsInitialized(FetchSuspended.class, Builder.class);
        }

        public final boolean isInitialized() {
            byte isInitialized = this.memoizedIsInitialized;
            if (isInitialized == 1) {
                return true;
            }
            if (isInitialized == 0) {
                return false;
            }
            this.memoizedIsInitialized = 1;
            return true;
        }

        public void writeTo(CodedOutputStream output) throws IOException {
            this.unknownFields.writeTo(output);
        }

        public int getSerializedSize() {
            int size = this.memoizedSize;
            if (size != -1) {
                return size;
            }
            size = 0;
            this.memoizedSize = size += this.unknownFields.getSerializedSize();
            return size;
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof FetchSuspended)) {
                return super.equals(obj);
            }
            FetchSuspended other = (FetchSuspended)obj;
            return this.unknownFields.equals((Object)other.unknownFields);
        }

        public int hashCode() {
            if (this.memoizedHashCode != 0) {
                return this.memoizedHashCode;
            }
            int hash = 41;
            hash = 19 * hash + FetchSuspended.getDescriptor().hashCode();
            this.memoizedHashCode = hash = 29 * hash + this.unknownFields.hashCode();
            return hash;
        }

        public static FetchSuspended parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
            return (FetchSuspended)PARSER.parseFrom(data);
        }

        public static FetchSuspended parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (FetchSuspended)PARSER.parseFrom(data, extensionRegistry);
        }

        public static FetchSuspended parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return (FetchSuspended)PARSER.parseFrom(data);
        }

        public static FetchSuspended parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (FetchSuspended)PARSER.parseFrom(data, extensionRegistry);
        }

        public static FetchSuspended parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return (FetchSuspended)PARSER.parseFrom(data);
        }

        public static FetchSuspended parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (FetchSuspended)PARSER.parseFrom(data, extensionRegistry);
        }

        public static FetchSuspended parseFrom(InputStream input) throws IOException {
            return (FetchSuspended)GeneratedMessageV3.parseWithIOException(PARSER, (InputStream)input);
        }

        public static FetchSuspended parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (FetchSuspended)GeneratedMessageV3.parseWithIOException(PARSER, (InputStream)input, (ExtensionRegistryLite)extensionRegistry);
        }

        public static FetchSuspended parseDelimitedFrom(InputStream input) throws IOException {
            return (FetchSuspended)GeneratedMessageV3.parseDelimitedWithIOException(PARSER, (InputStream)input);
        }

        public static FetchSuspended parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (FetchSuspended)GeneratedMessageV3.parseDelimitedWithIOException(PARSER, (InputStream)input, (ExtensionRegistryLite)extensionRegistry);
        }

        public static FetchSuspended parseFrom(CodedInputStream input) throws IOException {
            return (FetchSuspended)GeneratedMessageV3.parseWithIOException(PARSER, (CodedInputStream)input);
        }

        public static FetchSuspended parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (FetchSuspended)GeneratedMessageV3.parseWithIOException(PARSER, (CodedInputStream)input, (ExtensionRegistryLite)extensionRegistry);
        }

        public Builder newBuilderForType() {
            return FetchSuspended.newBuilder();
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(FetchSuspended prototype) {
            return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
        }

        public Builder toBuilder() {
            return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
        }

        protected Builder newBuilderForType(GeneratedMessageV3.BuilderParent parent) {
            Builder builder = new Builder(parent);
            return builder;
        }

        public static FetchSuspended getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<FetchSuspended> parser() {
            return PARSER;
        }

        public Parser<FetchSuspended> getParserForType() {
            return PARSER;
        }

        public FetchSuspended getDefaultInstanceForType() {
            return DEFAULT_INSTANCE;
        }

        public static final class Builder
        extends GeneratedMessageV3.Builder<Builder>
        implements FetchSuspendedOrBuilder {
            public static final Descriptors.Descriptor getDescriptor() {
                return internal_static_Mysqlx_Resultset_FetchSuspended_descriptor;
            }

            protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                return internal_static_Mysqlx_Resultset_FetchSuspended_fieldAccessorTable.ensureFieldAccessorsInitialized(FetchSuspended.class, Builder.class);
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
                return this;
            }

            public Descriptors.Descriptor getDescriptorForType() {
                return internal_static_Mysqlx_Resultset_FetchSuspended_descriptor;
            }

            public FetchSuspended getDefaultInstanceForType() {
                return FetchSuspended.getDefaultInstance();
            }

            public FetchSuspended build() {
                FetchSuspended result = this.buildPartial();
                if (!result.isInitialized()) {
                    throw Builder.newUninitializedMessageException((Message)result);
                }
                return result;
            }

            public FetchSuspended buildPartial() {
                FetchSuspended result = new FetchSuspended(this);
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
                if (other instanceof FetchSuspended) {
                    return this.mergeFrom((FetchSuspended)other);
                }
                super.mergeFrom(other);
                return this;
            }

            public Builder mergeFrom(FetchSuspended other) {
                if (other == FetchSuspended.getDefaultInstance()) {
                    return this;
                }
                this.mergeUnknownFields(other.unknownFields);
                this.onChanged();
                return this;
            }

            public final boolean isInitialized() {
                return true;
            }

            public Builder mergeFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                FetchSuspended parsedMessage = null;
                try {
                    parsedMessage = (FetchSuspended)PARSER.parsePartialFrom(input, extensionRegistry);
                }
                catch (InvalidProtocolBufferException e) {
                    parsedMessage = (FetchSuspended)e.getUnfinishedMessage();
                    throw e.unwrapIOException();
                }
                finally {
                    if (parsedMessage != null) {
                        this.mergeFrom(parsedMessage);
                    }
                }
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

    public static interface FetchSuspendedOrBuilder
    extends MessageOrBuilder {
    }

    public static final class FetchDone
    extends GeneratedMessageV3
    implements FetchDoneOrBuilder {
        private static final long serialVersionUID = 0L;
        private byte memoizedIsInitialized = (byte)-1;
        private static final FetchDone DEFAULT_INSTANCE = new FetchDone();
        @Deprecated
        public static final Parser<FetchDone> PARSER = new AbstractParser<FetchDone>(){

            public FetchDone parsePartialFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                return new FetchDone(input, extensionRegistry);
            }
        };

        private FetchDone(GeneratedMessageV3.Builder<?> builder) {
            super(builder);
        }

        private FetchDone() {
        }

        protected Object newInstance(GeneratedMessageV3.UnusedPrivateParameter unused) {
            return new FetchDone();
        }

        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        private FetchDone(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            this();
            if (extensionRegistry == null) {
                throw new NullPointerException();
            }
            UnknownFieldSet.Builder unknownFields = UnknownFieldSet.newBuilder();
            try {
                boolean done = false;
                block9: while (!done) {
                    int tag = input.readTag();
                    switch (tag) {
                        case 0: {
                            done = true;
                            continue block9;
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
            return internal_static_Mysqlx_Resultset_FetchDone_descriptor;
        }

        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return internal_static_Mysqlx_Resultset_FetchDone_fieldAccessorTable.ensureFieldAccessorsInitialized(FetchDone.class, Builder.class);
        }

        public final boolean isInitialized() {
            byte isInitialized = this.memoizedIsInitialized;
            if (isInitialized == 1) {
                return true;
            }
            if (isInitialized == 0) {
                return false;
            }
            this.memoizedIsInitialized = 1;
            return true;
        }

        public void writeTo(CodedOutputStream output) throws IOException {
            this.unknownFields.writeTo(output);
        }

        public int getSerializedSize() {
            int size = this.memoizedSize;
            if (size != -1) {
                return size;
            }
            size = 0;
            this.memoizedSize = size += this.unknownFields.getSerializedSize();
            return size;
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof FetchDone)) {
                return super.equals(obj);
            }
            FetchDone other = (FetchDone)obj;
            return this.unknownFields.equals((Object)other.unknownFields);
        }

        public int hashCode() {
            if (this.memoizedHashCode != 0) {
                return this.memoizedHashCode;
            }
            int hash = 41;
            hash = 19 * hash + FetchDone.getDescriptor().hashCode();
            this.memoizedHashCode = hash = 29 * hash + this.unknownFields.hashCode();
            return hash;
        }

        public static FetchDone parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
            return (FetchDone)PARSER.parseFrom(data);
        }

        public static FetchDone parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (FetchDone)PARSER.parseFrom(data, extensionRegistry);
        }

        public static FetchDone parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return (FetchDone)PARSER.parseFrom(data);
        }

        public static FetchDone parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (FetchDone)PARSER.parseFrom(data, extensionRegistry);
        }

        public static FetchDone parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return (FetchDone)PARSER.parseFrom(data);
        }

        public static FetchDone parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (FetchDone)PARSER.parseFrom(data, extensionRegistry);
        }

        public static FetchDone parseFrom(InputStream input) throws IOException {
            return (FetchDone)GeneratedMessageV3.parseWithIOException(PARSER, (InputStream)input);
        }

        public static FetchDone parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (FetchDone)GeneratedMessageV3.parseWithIOException(PARSER, (InputStream)input, (ExtensionRegistryLite)extensionRegistry);
        }

        public static FetchDone parseDelimitedFrom(InputStream input) throws IOException {
            return (FetchDone)GeneratedMessageV3.parseDelimitedWithIOException(PARSER, (InputStream)input);
        }

        public static FetchDone parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (FetchDone)GeneratedMessageV3.parseDelimitedWithIOException(PARSER, (InputStream)input, (ExtensionRegistryLite)extensionRegistry);
        }

        public static FetchDone parseFrom(CodedInputStream input) throws IOException {
            return (FetchDone)GeneratedMessageV3.parseWithIOException(PARSER, (CodedInputStream)input);
        }

        public static FetchDone parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (FetchDone)GeneratedMessageV3.parseWithIOException(PARSER, (CodedInputStream)input, (ExtensionRegistryLite)extensionRegistry);
        }

        public Builder newBuilderForType() {
            return FetchDone.newBuilder();
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(FetchDone prototype) {
            return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
        }

        public Builder toBuilder() {
            return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
        }

        protected Builder newBuilderForType(GeneratedMessageV3.BuilderParent parent) {
            Builder builder = new Builder(parent);
            return builder;
        }

        public static FetchDone getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<FetchDone> parser() {
            return PARSER;
        }

        public Parser<FetchDone> getParserForType() {
            return PARSER;
        }

        public FetchDone getDefaultInstanceForType() {
            return DEFAULT_INSTANCE;
        }

        public static final class Builder
        extends GeneratedMessageV3.Builder<Builder>
        implements FetchDoneOrBuilder {
            public static final Descriptors.Descriptor getDescriptor() {
                return internal_static_Mysqlx_Resultset_FetchDone_descriptor;
            }

            protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                return internal_static_Mysqlx_Resultset_FetchDone_fieldAccessorTable.ensureFieldAccessorsInitialized(FetchDone.class, Builder.class);
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
                return this;
            }

            public Descriptors.Descriptor getDescriptorForType() {
                return internal_static_Mysqlx_Resultset_FetchDone_descriptor;
            }

            public FetchDone getDefaultInstanceForType() {
                return FetchDone.getDefaultInstance();
            }

            public FetchDone build() {
                FetchDone result = this.buildPartial();
                if (!result.isInitialized()) {
                    throw Builder.newUninitializedMessageException((Message)result);
                }
                return result;
            }

            public FetchDone buildPartial() {
                FetchDone result = new FetchDone(this);
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
                if (other instanceof FetchDone) {
                    return this.mergeFrom((FetchDone)other);
                }
                super.mergeFrom(other);
                return this;
            }

            public Builder mergeFrom(FetchDone other) {
                if (other == FetchDone.getDefaultInstance()) {
                    return this;
                }
                this.mergeUnknownFields(other.unknownFields);
                this.onChanged();
                return this;
            }

            public final boolean isInitialized() {
                return true;
            }

            public Builder mergeFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                FetchDone parsedMessage = null;
                try {
                    parsedMessage = (FetchDone)PARSER.parsePartialFrom(input, extensionRegistry);
                }
                catch (InvalidProtocolBufferException e) {
                    parsedMessage = (FetchDone)e.getUnfinishedMessage();
                    throw e.unwrapIOException();
                }
                finally {
                    if (parsedMessage != null) {
                        this.mergeFrom(parsedMessage);
                    }
                }
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

    public static interface FetchDoneOrBuilder
    extends MessageOrBuilder {
    }

    public static final class FetchDoneMoreResultsets
    extends GeneratedMessageV3
    implements FetchDoneMoreResultsetsOrBuilder {
        private static final long serialVersionUID = 0L;
        private byte memoizedIsInitialized = (byte)-1;
        private static final FetchDoneMoreResultsets DEFAULT_INSTANCE = new FetchDoneMoreResultsets();
        @Deprecated
        public static final Parser<FetchDoneMoreResultsets> PARSER = new AbstractParser<FetchDoneMoreResultsets>(){

            public FetchDoneMoreResultsets parsePartialFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                return new FetchDoneMoreResultsets(input, extensionRegistry);
            }
        };

        private FetchDoneMoreResultsets(GeneratedMessageV3.Builder<?> builder) {
            super(builder);
        }

        private FetchDoneMoreResultsets() {
        }

        protected Object newInstance(GeneratedMessageV3.UnusedPrivateParameter unused) {
            return new FetchDoneMoreResultsets();
        }

        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        private FetchDoneMoreResultsets(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            this();
            if (extensionRegistry == null) {
                throw new NullPointerException();
            }
            UnknownFieldSet.Builder unknownFields = UnknownFieldSet.newBuilder();
            try {
                boolean done = false;
                block9: while (!done) {
                    int tag = input.readTag();
                    switch (tag) {
                        case 0: {
                            done = true;
                            continue block9;
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
            return internal_static_Mysqlx_Resultset_FetchDoneMoreResultsets_descriptor;
        }

        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return internal_static_Mysqlx_Resultset_FetchDoneMoreResultsets_fieldAccessorTable.ensureFieldAccessorsInitialized(FetchDoneMoreResultsets.class, Builder.class);
        }

        public final boolean isInitialized() {
            byte isInitialized = this.memoizedIsInitialized;
            if (isInitialized == 1) {
                return true;
            }
            if (isInitialized == 0) {
                return false;
            }
            this.memoizedIsInitialized = 1;
            return true;
        }

        public void writeTo(CodedOutputStream output) throws IOException {
            this.unknownFields.writeTo(output);
        }

        public int getSerializedSize() {
            int size = this.memoizedSize;
            if (size != -1) {
                return size;
            }
            size = 0;
            this.memoizedSize = size += this.unknownFields.getSerializedSize();
            return size;
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof FetchDoneMoreResultsets)) {
                return super.equals(obj);
            }
            FetchDoneMoreResultsets other = (FetchDoneMoreResultsets)obj;
            return this.unknownFields.equals((Object)other.unknownFields);
        }

        public int hashCode() {
            if (this.memoizedHashCode != 0) {
                return this.memoizedHashCode;
            }
            int hash = 41;
            hash = 19 * hash + FetchDoneMoreResultsets.getDescriptor().hashCode();
            this.memoizedHashCode = hash = 29 * hash + this.unknownFields.hashCode();
            return hash;
        }

        public static FetchDoneMoreResultsets parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
            return (FetchDoneMoreResultsets)PARSER.parseFrom(data);
        }

        public static FetchDoneMoreResultsets parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (FetchDoneMoreResultsets)PARSER.parseFrom(data, extensionRegistry);
        }

        public static FetchDoneMoreResultsets parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return (FetchDoneMoreResultsets)PARSER.parseFrom(data);
        }

        public static FetchDoneMoreResultsets parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (FetchDoneMoreResultsets)PARSER.parseFrom(data, extensionRegistry);
        }

        public static FetchDoneMoreResultsets parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return (FetchDoneMoreResultsets)PARSER.parseFrom(data);
        }

        public static FetchDoneMoreResultsets parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (FetchDoneMoreResultsets)PARSER.parseFrom(data, extensionRegistry);
        }

        public static FetchDoneMoreResultsets parseFrom(InputStream input) throws IOException {
            return (FetchDoneMoreResultsets)GeneratedMessageV3.parseWithIOException(PARSER, (InputStream)input);
        }

        public static FetchDoneMoreResultsets parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (FetchDoneMoreResultsets)GeneratedMessageV3.parseWithIOException(PARSER, (InputStream)input, (ExtensionRegistryLite)extensionRegistry);
        }

        public static FetchDoneMoreResultsets parseDelimitedFrom(InputStream input) throws IOException {
            return (FetchDoneMoreResultsets)GeneratedMessageV3.parseDelimitedWithIOException(PARSER, (InputStream)input);
        }

        public static FetchDoneMoreResultsets parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (FetchDoneMoreResultsets)GeneratedMessageV3.parseDelimitedWithIOException(PARSER, (InputStream)input, (ExtensionRegistryLite)extensionRegistry);
        }

        public static FetchDoneMoreResultsets parseFrom(CodedInputStream input) throws IOException {
            return (FetchDoneMoreResultsets)GeneratedMessageV3.parseWithIOException(PARSER, (CodedInputStream)input);
        }

        public static FetchDoneMoreResultsets parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (FetchDoneMoreResultsets)GeneratedMessageV3.parseWithIOException(PARSER, (CodedInputStream)input, (ExtensionRegistryLite)extensionRegistry);
        }

        public Builder newBuilderForType() {
            return FetchDoneMoreResultsets.newBuilder();
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(FetchDoneMoreResultsets prototype) {
            return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
        }

        public Builder toBuilder() {
            return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
        }

        protected Builder newBuilderForType(GeneratedMessageV3.BuilderParent parent) {
            Builder builder = new Builder(parent);
            return builder;
        }

        public static FetchDoneMoreResultsets getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<FetchDoneMoreResultsets> parser() {
            return PARSER;
        }

        public Parser<FetchDoneMoreResultsets> getParserForType() {
            return PARSER;
        }

        public FetchDoneMoreResultsets getDefaultInstanceForType() {
            return DEFAULT_INSTANCE;
        }

        public static final class Builder
        extends GeneratedMessageV3.Builder<Builder>
        implements FetchDoneMoreResultsetsOrBuilder {
            public static final Descriptors.Descriptor getDescriptor() {
                return internal_static_Mysqlx_Resultset_FetchDoneMoreResultsets_descriptor;
            }

            protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                return internal_static_Mysqlx_Resultset_FetchDoneMoreResultsets_fieldAccessorTable.ensureFieldAccessorsInitialized(FetchDoneMoreResultsets.class, Builder.class);
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
                return this;
            }

            public Descriptors.Descriptor getDescriptorForType() {
                return internal_static_Mysqlx_Resultset_FetchDoneMoreResultsets_descriptor;
            }

            public FetchDoneMoreResultsets getDefaultInstanceForType() {
                return FetchDoneMoreResultsets.getDefaultInstance();
            }

            public FetchDoneMoreResultsets build() {
                FetchDoneMoreResultsets result = this.buildPartial();
                if (!result.isInitialized()) {
                    throw Builder.newUninitializedMessageException((Message)result);
                }
                return result;
            }

            public FetchDoneMoreResultsets buildPartial() {
                FetchDoneMoreResultsets result = new FetchDoneMoreResultsets(this);
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
                if (other instanceof FetchDoneMoreResultsets) {
                    return this.mergeFrom((FetchDoneMoreResultsets)other);
                }
                super.mergeFrom(other);
                return this;
            }

            public Builder mergeFrom(FetchDoneMoreResultsets other) {
                if (other == FetchDoneMoreResultsets.getDefaultInstance()) {
                    return this;
                }
                this.mergeUnknownFields(other.unknownFields);
                this.onChanged();
                return this;
            }

            public final boolean isInitialized() {
                return true;
            }

            public Builder mergeFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                FetchDoneMoreResultsets parsedMessage = null;
                try {
                    parsedMessage = (FetchDoneMoreResultsets)PARSER.parsePartialFrom(input, extensionRegistry);
                }
                catch (InvalidProtocolBufferException e) {
                    parsedMessage = (FetchDoneMoreResultsets)e.getUnfinishedMessage();
                    throw e.unwrapIOException();
                }
                finally {
                    if (parsedMessage != null) {
                        this.mergeFrom(parsedMessage);
                    }
                }
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

    public static interface FetchDoneMoreResultsetsOrBuilder
    extends MessageOrBuilder {
    }

    public static final class FetchDoneMoreOutParams
    extends GeneratedMessageV3
    implements FetchDoneMoreOutParamsOrBuilder {
        private static final long serialVersionUID = 0L;
        private byte memoizedIsInitialized = (byte)-1;
        private static final FetchDoneMoreOutParams DEFAULT_INSTANCE = new FetchDoneMoreOutParams();
        @Deprecated
        public static final Parser<FetchDoneMoreOutParams> PARSER = new AbstractParser<FetchDoneMoreOutParams>(){

            public FetchDoneMoreOutParams parsePartialFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                return new FetchDoneMoreOutParams(input, extensionRegistry);
            }
        };

        private FetchDoneMoreOutParams(GeneratedMessageV3.Builder<?> builder) {
            super(builder);
        }

        private FetchDoneMoreOutParams() {
        }

        protected Object newInstance(GeneratedMessageV3.UnusedPrivateParameter unused) {
            return new FetchDoneMoreOutParams();
        }

        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        private FetchDoneMoreOutParams(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            this();
            if (extensionRegistry == null) {
                throw new NullPointerException();
            }
            UnknownFieldSet.Builder unknownFields = UnknownFieldSet.newBuilder();
            try {
                boolean done = false;
                block9: while (!done) {
                    int tag = input.readTag();
                    switch (tag) {
                        case 0: {
                            done = true;
                            continue block9;
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
            return internal_static_Mysqlx_Resultset_FetchDoneMoreOutParams_descriptor;
        }

        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return internal_static_Mysqlx_Resultset_FetchDoneMoreOutParams_fieldAccessorTable.ensureFieldAccessorsInitialized(FetchDoneMoreOutParams.class, Builder.class);
        }

        public final boolean isInitialized() {
            byte isInitialized = this.memoizedIsInitialized;
            if (isInitialized == 1) {
                return true;
            }
            if (isInitialized == 0) {
                return false;
            }
            this.memoizedIsInitialized = 1;
            return true;
        }

        public void writeTo(CodedOutputStream output) throws IOException {
            this.unknownFields.writeTo(output);
        }

        public int getSerializedSize() {
            int size = this.memoizedSize;
            if (size != -1) {
                return size;
            }
            size = 0;
            this.memoizedSize = size += this.unknownFields.getSerializedSize();
            return size;
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof FetchDoneMoreOutParams)) {
                return super.equals(obj);
            }
            FetchDoneMoreOutParams other = (FetchDoneMoreOutParams)obj;
            return this.unknownFields.equals((Object)other.unknownFields);
        }

        public int hashCode() {
            if (this.memoizedHashCode != 0) {
                return this.memoizedHashCode;
            }
            int hash = 41;
            hash = 19 * hash + FetchDoneMoreOutParams.getDescriptor().hashCode();
            this.memoizedHashCode = hash = 29 * hash + this.unknownFields.hashCode();
            return hash;
        }

        public static FetchDoneMoreOutParams parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
            return (FetchDoneMoreOutParams)PARSER.parseFrom(data);
        }

        public static FetchDoneMoreOutParams parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (FetchDoneMoreOutParams)PARSER.parseFrom(data, extensionRegistry);
        }

        public static FetchDoneMoreOutParams parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return (FetchDoneMoreOutParams)PARSER.parseFrom(data);
        }

        public static FetchDoneMoreOutParams parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (FetchDoneMoreOutParams)PARSER.parseFrom(data, extensionRegistry);
        }

        public static FetchDoneMoreOutParams parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return (FetchDoneMoreOutParams)PARSER.parseFrom(data);
        }

        public static FetchDoneMoreOutParams parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (FetchDoneMoreOutParams)PARSER.parseFrom(data, extensionRegistry);
        }

        public static FetchDoneMoreOutParams parseFrom(InputStream input) throws IOException {
            return (FetchDoneMoreOutParams)GeneratedMessageV3.parseWithIOException(PARSER, (InputStream)input);
        }

        public static FetchDoneMoreOutParams parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (FetchDoneMoreOutParams)GeneratedMessageV3.parseWithIOException(PARSER, (InputStream)input, (ExtensionRegistryLite)extensionRegistry);
        }

        public static FetchDoneMoreOutParams parseDelimitedFrom(InputStream input) throws IOException {
            return (FetchDoneMoreOutParams)GeneratedMessageV3.parseDelimitedWithIOException(PARSER, (InputStream)input);
        }

        public static FetchDoneMoreOutParams parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (FetchDoneMoreOutParams)GeneratedMessageV3.parseDelimitedWithIOException(PARSER, (InputStream)input, (ExtensionRegistryLite)extensionRegistry);
        }

        public static FetchDoneMoreOutParams parseFrom(CodedInputStream input) throws IOException {
            return (FetchDoneMoreOutParams)GeneratedMessageV3.parseWithIOException(PARSER, (CodedInputStream)input);
        }

        public static FetchDoneMoreOutParams parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (FetchDoneMoreOutParams)GeneratedMessageV3.parseWithIOException(PARSER, (CodedInputStream)input, (ExtensionRegistryLite)extensionRegistry);
        }

        public Builder newBuilderForType() {
            return FetchDoneMoreOutParams.newBuilder();
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(FetchDoneMoreOutParams prototype) {
            return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
        }

        public Builder toBuilder() {
            return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
        }

        protected Builder newBuilderForType(GeneratedMessageV3.BuilderParent parent) {
            Builder builder = new Builder(parent);
            return builder;
        }

        public static FetchDoneMoreOutParams getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<FetchDoneMoreOutParams> parser() {
            return PARSER;
        }

        public Parser<FetchDoneMoreOutParams> getParserForType() {
            return PARSER;
        }

        public FetchDoneMoreOutParams getDefaultInstanceForType() {
            return DEFAULT_INSTANCE;
        }

        public static final class Builder
        extends GeneratedMessageV3.Builder<Builder>
        implements FetchDoneMoreOutParamsOrBuilder {
            public static final Descriptors.Descriptor getDescriptor() {
                return internal_static_Mysqlx_Resultset_FetchDoneMoreOutParams_descriptor;
            }

            protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                return internal_static_Mysqlx_Resultset_FetchDoneMoreOutParams_fieldAccessorTable.ensureFieldAccessorsInitialized(FetchDoneMoreOutParams.class, Builder.class);
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
                return this;
            }

            public Descriptors.Descriptor getDescriptorForType() {
                return internal_static_Mysqlx_Resultset_FetchDoneMoreOutParams_descriptor;
            }

            public FetchDoneMoreOutParams getDefaultInstanceForType() {
                return FetchDoneMoreOutParams.getDefaultInstance();
            }

            public FetchDoneMoreOutParams build() {
                FetchDoneMoreOutParams result = this.buildPartial();
                if (!result.isInitialized()) {
                    throw Builder.newUninitializedMessageException((Message)result);
                }
                return result;
            }

            public FetchDoneMoreOutParams buildPartial() {
                FetchDoneMoreOutParams result = new FetchDoneMoreOutParams(this);
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
                if (other instanceof FetchDoneMoreOutParams) {
                    return this.mergeFrom((FetchDoneMoreOutParams)other);
                }
                super.mergeFrom(other);
                return this;
            }

            public Builder mergeFrom(FetchDoneMoreOutParams other) {
                if (other == FetchDoneMoreOutParams.getDefaultInstance()) {
                    return this;
                }
                this.mergeUnknownFields(other.unknownFields);
                this.onChanged();
                return this;
            }

            public final boolean isInitialized() {
                return true;
            }

            public Builder mergeFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                FetchDoneMoreOutParams parsedMessage = null;
                try {
                    parsedMessage = (FetchDoneMoreOutParams)PARSER.parsePartialFrom(input, extensionRegistry);
                }
                catch (InvalidProtocolBufferException e) {
                    parsedMessage = (FetchDoneMoreOutParams)e.getUnfinishedMessage();
                    throw e.unwrapIOException();
                }
                finally {
                    if (parsedMessage != null) {
                        this.mergeFrom(parsedMessage);
                    }
                }
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

    public static interface FetchDoneMoreOutParamsOrBuilder
    extends MessageOrBuilder {
    }

    public static enum ContentType_DATETIME implements ProtocolMessageEnum
    {
        DATE(1),
        DATETIME(2);

        public static final int DATE_VALUE = 1;
        public static final int DATETIME_VALUE = 2;
        private static final Internal.EnumLiteMap<ContentType_DATETIME> internalValueMap;
        private static final ContentType_DATETIME[] VALUES;
        private final int value;

        public final int getNumber() {
            return this.value;
        }

        @Deprecated
        public static ContentType_DATETIME valueOf(int value) {
            return ContentType_DATETIME.forNumber(value);
        }

        public static ContentType_DATETIME forNumber(int value) {
            switch (value) {
                case 1: {
                    return DATE;
                }
                case 2: {
                    return DATETIME;
                }
            }
            return null;
        }

        public static Internal.EnumLiteMap<ContentType_DATETIME> internalGetValueMap() {
            return internalValueMap;
        }

        public final Descriptors.EnumValueDescriptor getValueDescriptor() {
            return (Descriptors.EnumValueDescriptor)ContentType_DATETIME.getDescriptor().getValues().get(this.ordinal());
        }

        public final Descriptors.EnumDescriptor getDescriptorForType() {
            return ContentType_DATETIME.getDescriptor();
        }

        public static final Descriptors.EnumDescriptor getDescriptor() {
            return (Descriptors.EnumDescriptor)MysqlxResultset.getDescriptor().getEnumTypes().get(1);
        }

        public static ContentType_DATETIME valueOf(Descriptors.EnumValueDescriptor desc) {
            if (desc.getType() != ContentType_DATETIME.getDescriptor()) {
                throw new IllegalArgumentException("EnumValueDescriptor is not for this type.");
            }
            return VALUES[desc.getIndex()];
        }

        private ContentType_DATETIME(int value) {
            this.value = value;
        }

        static {
            internalValueMap = new Internal.EnumLiteMap<ContentType_DATETIME>(){

                public ContentType_DATETIME findValueByNumber(int number) {
                    return ContentType_DATETIME.forNumber(number);
                }
            };
            VALUES = ContentType_DATETIME.values();
        }
    }

    public static enum ContentType_BYTES implements ProtocolMessageEnum
    {
        GEOMETRY(1),
        JSON(2),
        XML(3);

        public static final int GEOMETRY_VALUE = 1;
        public static final int JSON_VALUE = 2;
        public static final int XML_VALUE = 3;
        private static final Internal.EnumLiteMap<ContentType_BYTES> internalValueMap;
        private static final ContentType_BYTES[] VALUES;
        private final int value;

        public final int getNumber() {
            return this.value;
        }

        @Deprecated
        public static ContentType_BYTES valueOf(int value) {
            return ContentType_BYTES.forNumber(value);
        }

        public static ContentType_BYTES forNumber(int value) {
            switch (value) {
                case 1: {
                    return GEOMETRY;
                }
                case 2: {
                    return JSON;
                }
                case 3: {
                    return XML;
                }
            }
            return null;
        }

        public static Internal.EnumLiteMap<ContentType_BYTES> internalGetValueMap() {
            return internalValueMap;
        }

        public final Descriptors.EnumValueDescriptor getValueDescriptor() {
            return (Descriptors.EnumValueDescriptor)ContentType_BYTES.getDescriptor().getValues().get(this.ordinal());
        }

        public final Descriptors.EnumDescriptor getDescriptorForType() {
            return ContentType_BYTES.getDescriptor();
        }

        public static final Descriptors.EnumDescriptor getDescriptor() {
            return (Descriptors.EnumDescriptor)MysqlxResultset.getDescriptor().getEnumTypes().get(0);
        }

        public static ContentType_BYTES valueOf(Descriptors.EnumValueDescriptor desc) {
            if (desc.getType() != ContentType_BYTES.getDescriptor()) {
                throw new IllegalArgumentException("EnumValueDescriptor is not for this type.");
            }
            return VALUES[desc.getIndex()];
        }

        private ContentType_BYTES(int value) {
            this.value = value;
        }

        static {
            internalValueMap = new Internal.EnumLiteMap<ContentType_BYTES>(){

                public ContentType_BYTES findValueByNumber(int number) {
                    return ContentType_BYTES.forNumber(number);
                }
            };
            VALUES = ContentType_BYTES.values();
        }
    }
}

