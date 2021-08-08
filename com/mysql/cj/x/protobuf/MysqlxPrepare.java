/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  com.google.protobuf.AbstractMessage
 *  com.google.protobuf.AbstractMessage$BuilderParent
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
 *  com.google.protobuf.RepeatedFieldBuilderV3
 *  com.google.protobuf.SingleFieldBuilderV3
 *  com.google.protobuf.UnknownFieldSet
 *  com.google.protobuf.UnknownFieldSet$Builder
 */
package com.mysql.cj.x.protobuf;

import com.google.protobuf.AbstractMessage;
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
import com.google.protobuf.RepeatedFieldBuilderV3;
import com.google.protobuf.SingleFieldBuilderV3;
import com.google.protobuf.UnknownFieldSet;
import com.mysql.cj.x.protobuf.Mysqlx;
import com.mysql.cj.x.protobuf.MysqlxCrud;
import com.mysql.cj.x.protobuf.MysqlxDatatypes;
import com.mysql.cj.x.protobuf.MysqlxSql;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class MysqlxPrepare {
    private static final Descriptors.Descriptor internal_static_Mysqlx_Prepare_Prepare_descriptor;
    private static final GeneratedMessageV3.FieldAccessorTable internal_static_Mysqlx_Prepare_Prepare_fieldAccessorTable;
    private static final Descriptors.Descriptor internal_static_Mysqlx_Prepare_Prepare_OneOfMessage_descriptor;
    private static final GeneratedMessageV3.FieldAccessorTable internal_static_Mysqlx_Prepare_Prepare_OneOfMessage_fieldAccessorTable;
    private static final Descriptors.Descriptor internal_static_Mysqlx_Prepare_Execute_descriptor;
    private static final GeneratedMessageV3.FieldAccessorTable internal_static_Mysqlx_Prepare_Execute_fieldAccessorTable;
    private static final Descriptors.Descriptor internal_static_Mysqlx_Prepare_Deallocate_descriptor;
    private static final GeneratedMessageV3.FieldAccessorTable internal_static_Mysqlx_Prepare_Deallocate_fieldAccessorTable;
    private static Descriptors.FileDescriptor descriptor;

    private MysqlxPrepare() {
    }

    public static void registerAllExtensions(ExtensionRegistryLite registry) {
    }

    public static void registerAllExtensions(ExtensionRegistry registry) {
        MysqlxPrepare.registerAllExtensions((ExtensionRegistryLite)registry);
    }

    public static Descriptors.FileDescriptor getDescriptor() {
        return descriptor;
    }

    static {
        String[] descriptorData = new String[]{"\n\u0014mysqlx_prepare.proto\u0012\u000eMysqlx.Prepare\u001a\fmysqlx.proto\u001a\u0010mysqlx_sql.proto\u001a\u0011mysqlx_crud.proto\u001a\u0016mysqlx_datatypes.proto\"\u009d\u0003\n\u0007Prepare\u0012\u000f\n\u0007stmt_id\u0018\u0001 \u0002(\r\u00122\n\u0004stmt\u0018\u0002 \u0002(\u000b2$.Mysqlx.Prepare.Prepare.OneOfMessage\u001a\u00c6\u0002\n\fOneOfMessage\u00127\n\u0004type\u0018\u0001 \u0002(\u000e2).Mysqlx.Prepare.Prepare.OneOfMessage.Type\u0012\u001f\n\u0004find\u0018\u0002 \u0001(\u000b2\u0011.Mysqlx.Crud.Find\u0012#\n\u0006insert\u0018\u0003 \u0001(\u000b2\u0013.Mysqlx.Crud.Insert\u0012#\n\u0006update\u0018\u0004 \u0001(\u000b2\u0013.Mysqlx.Crud.Update\u0012#\n\u0006delete\u0018\u0005 \u0001(\u000b2\u0013.Mysqlx.Crud.Delete\u0012-\n\fstmt_execute\u0018\u0006 \u0001(\u000b2\u0017.Mysqlx.Sql.StmtExecute\">\n\u0004Type\u0012\b\n\u0004FIND\u0010\u0000\u0012\n\n\u0006INSERT\u0010\u0001\u0012\n\n\u0006UPDATE\u0010\u0002\u0012\n\n\u0006DELETE\u0010\u0004\u0012\b\n\u0004STMT\u0010\u0005:\u0004\u0088\u00ea0(\"f\n\u0007Execute\u0012\u000f\n\u0007stmt_id\u0018\u0001 \u0002(\r\u0012#\n\u0004args\u0018\u0002 \u0003(\u000b2\u0015.Mysqlx.Datatypes.Any\u0012\u001f\n\u0010compact_metadata\u0018\u0003 \u0001(\b:\u0005false:\u0004\u0088\u00ea0)\"#\n\nDeallocate\u0012\u000f\n\u0007stmt_id\u0018\u0001 \u0002(\r:\u0004\u0088\u00ea0*B\u0019\n\u0017com.mysql.cj.x.protobuf"};
        descriptor = Descriptors.FileDescriptor.internalBuildGeneratedFileFrom((String[])descriptorData, (Descriptors.FileDescriptor[])new Descriptors.FileDescriptor[]{Mysqlx.getDescriptor(), MysqlxSql.getDescriptor(), MysqlxCrud.getDescriptor(), MysqlxDatatypes.getDescriptor()});
        internal_static_Mysqlx_Prepare_Prepare_descriptor = (Descriptors.Descriptor)MysqlxPrepare.getDescriptor().getMessageTypes().get(0);
        internal_static_Mysqlx_Prepare_Prepare_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(internal_static_Mysqlx_Prepare_Prepare_descriptor, new String[]{"StmtId", "Stmt"});
        internal_static_Mysqlx_Prepare_Prepare_OneOfMessage_descriptor = (Descriptors.Descriptor)internal_static_Mysqlx_Prepare_Prepare_descriptor.getNestedTypes().get(0);
        internal_static_Mysqlx_Prepare_Prepare_OneOfMessage_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(internal_static_Mysqlx_Prepare_Prepare_OneOfMessage_descriptor, new String[]{"Type", "Find", "Insert", "Update", "Delete", "StmtExecute"});
        internal_static_Mysqlx_Prepare_Execute_descriptor = (Descriptors.Descriptor)MysqlxPrepare.getDescriptor().getMessageTypes().get(1);
        internal_static_Mysqlx_Prepare_Execute_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(internal_static_Mysqlx_Prepare_Execute_descriptor, new String[]{"StmtId", "Args", "CompactMetadata"});
        internal_static_Mysqlx_Prepare_Deallocate_descriptor = (Descriptors.Descriptor)MysqlxPrepare.getDescriptor().getMessageTypes().get(2);
        internal_static_Mysqlx_Prepare_Deallocate_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(internal_static_Mysqlx_Prepare_Deallocate_descriptor, new String[]{"StmtId"});
        ExtensionRegistry registry = ExtensionRegistry.newInstance();
        registry.add(Mysqlx.clientMessageId);
        Descriptors.FileDescriptor.internalUpdateFileDescriptor((Descriptors.FileDescriptor)descriptor, (ExtensionRegistry)registry);
        Mysqlx.getDescriptor();
        MysqlxSql.getDescriptor();
        MysqlxCrud.getDescriptor();
        MysqlxDatatypes.getDescriptor();
    }

    public static final class Deallocate
    extends GeneratedMessageV3
    implements DeallocateOrBuilder {
        private static final long serialVersionUID = 0L;
        private int bitField0_;
        public static final int STMT_ID_FIELD_NUMBER = 1;
        private int stmtId_;
        private byte memoizedIsInitialized = (byte)-1;
        private static final Deallocate DEFAULT_INSTANCE = new Deallocate();
        @Deprecated
        public static final Parser<Deallocate> PARSER = new AbstractParser<Deallocate>(){

            public Deallocate parsePartialFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                return new Deallocate(input, extensionRegistry);
            }
        };

        private Deallocate(GeneratedMessageV3.Builder<?> builder) {
            super(builder);
        }

        private Deallocate() {
        }

        protected Object newInstance(GeneratedMessageV3.UnusedPrivateParameter unused) {
            return new Deallocate();
        }

        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        private Deallocate(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
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
                            this.stmtId_ = input.readUInt32();
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
            return internal_static_Mysqlx_Prepare_Deallocate_descriptor;
        }

        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return internal_static_Mysqlx_Prepare_Deallocate_fieldAccessorTable.ensureFieldAccessorsInitialized(Deallocate.class, Builder.class);
        }

        @Override
        public boolean hasStmtId() {
            return (this.bitField0_ & 1) != 0;
        }

        @Override
        public int getStmtId() {
            return this.stmtId_;
        }

        public final boolean isInitialized() {
            byte isInitialized = this.memoizedIsInitialized;
            if (isInitialized == 1) {
                return true;
            }
            if (isInitialized == 0) {
                return false;
            }
            if (!this.hasStmtId()) {
                this.memoizedIsInitialized = 0;
                return false;
            }
            this.memoizedIsInitialized = 1;
            return true;
        }

        public void writeTo(CodedOutputStream output) throws IOException {
            if ((this.bitField0_ & 1) != 0) {
                output.writeUInt32(1, this.stmtId_);
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
                size += CodedOutputStream.computeUInt32Size((int)1, (int)this.stmtId_);
            }
            this.memoizedSize = size += this.unknownFields.getSerializedSize();
            return size;
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof Deallocate)) {
                return super.equals(obj);
            }
            Deallocate other = (Deallocate)obj;
            if (this.hasStmtId() != other.hasStmtId()) {
                return false;
            }
            if (this.hasStmtId() && this.getStmtId() != other.getStmtId()) {
                return false;
            }
            return this.unknownFields.equals((Object)other.unknownFields);
        }

        public int hashCode() {
            if (this.memoizedHashCode != 0) {
                return this.memoizedHashCode;
            }
            int hash = 41;
            hash = 19 * hash + Deallocate.getDescriptor().hashCode();
            if (this.hasStmtId()) {
                hash = 37 * hash + 1;
                hash = 53 * hash + this.getStmtId();
            }
            this.memoizedHashCode = hash = 29 * hash + this.unknownFields.hashCode();
            return hash;
        }

        public static Deallocate parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
            return (Deallocate)PARSER.parseFrom(data);
        }

        public static Deallocate parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (Deallocate)PARSER.parseFrom(data, extensionRegistry);
        }

        public static Deallocate parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return (Deallocate)PARSER.parseFrom(data);
        }

        public static Deallocate parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (Deallocate)PARSER.parseFrom(data, extensionRegistry);
        }

        public static Deallocate parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return (Deallocate)PARSER.parseFrom(data);
        }

        public static Deallocate parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (Deallocate)PARSER.parseFrom(data, extensionRegistry);
        }

        public static Deallocate parseFrom(InputStream input) throws IOException {
            return (Deallocate)GeneratedMessageV3.parseWithIOException(PARSER, (InputStream)input);
        }

        public static Deallocate parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (Deallocate)GeneratedMessageV3.parseWithIOException(PARSER, (InputStream)input, (ExtensionRegistryLite)extensionRegistry);
        }

        public static Deallocate parseDelimitedFrom(InputStream input) throws IOException {
            return (Deallocate)GeneratedMessageV3.parseDelimitedWithIOException(PARSER, (InputStream)input);
        }

        public static Deallocate parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (Deallocate)GeneratedMessageV3.parseDelimitedWithIOException(PARSER, (InputStream)input, (ExtensionRegistryLite)extensionRegistry);
        }

        public static Deallocate parseFrom(CodedInputStream input) throws IOException {
            return (Deallocate)GeneratedMessageV3.parseWithIOException(PARSER, (CodedInputStream)input);
        }

        public static Deallocate parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (Deallocate)GeneratedMessageV3.parseWithIOException(PARSER, (CodedInputStream)input, (ExtensionRegistryLite)extensionRegistry);
        }

        public Builder newBuilderForType() {
            return Deallocate.newBuilder();
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(Deallocate prototype) {
            return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
        }

        public Builder toBuilder() {
            return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
        }

        protected Builder newBuilderForType(GeneratedMessageV3.BuilderParent parent) {
            Builder builder = new Builder(parent);
            return builder;
        }

        public static Deallocate getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<Deallocate> parser() {
            return PARSER;
        }

        public Parser<Deallocate> getParserForType() {
            return PARSER;
        }

        public Deallocate getDefaultInstanceForType() {
            return DEFAULT_INSTANCE;
        }

        public static final class Builder
        extends GeneratedMessageV3.Builder<Builder>
        implements DeallocateOrBuilder {
            private int bitField0_;
            private int stmtId_;

            public static final Descriptors.Descriptor getDescriptor() {
                return internal_static_Mysqlx_Prepare_Deallocate_descriptor;
            }

            protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                return internal_static_Mysqlx_Prepare_Deallocate_fieldAccessorTable.ensureFieldAccessorsInitialized(Deallocate.class, Builder.class);
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
                this.stmtId_ = 0;
                this.bitField0_ &= 0xFFFFFFFE;
                return this;
            }

            public Descriptors.Descriptor getDescriptorForType() {
                return internal_static_Mysqlx_Prepare_Deallocate_descriptor;
            }

            public Deallocate getDefaultInstanceForType() {
                return Deallocate.getDefaultInstance();
            }

            public Deallocate build() {
                Deallocate result = this.buildPartial();
                if (!result.isInitialized()) {
                    throw Builder.newUninitializedMessageException((Message)result);
                }
                return result;
            }

            public Deallocate buildPartial() {
                Deallocate result = new Deallocate(this);
                int from_bitField0_ = this.bitField0_;
                int to_bitField0_ = 0;
                if ((from_bitField0_ & 1) != 0) {
                    result.stmtId_ = this.stmtId_;
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
                if (other instanceof Deallocate) {
                    return this.mergeFrom((Deallocate)other);
                }
                super.mergeFrom(other);
                return this;
            }

            public Builder mergeFrom(Deallocate other) {
                if (other == Deallocate.getDefaultInstance()) {
                    return this;
                }
                if (other.hasStmtId()) {
                    this.setStmtId(other.getStmtId());
                }
                this.mergeUnknownFields(other.unknownFields);
                this.onChanged();
                return this;
            }

            public final boolean isInitialized() {
                return this.hasStmtId();
            }

            public Builder mergeFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                Deallocate parsedMessage = null;
                try {
                    parsedMessage = (Deallocate)PARSER.parsePartialFrom(input, extensionRegistry);
                }
                catch (InvalidProtocolBufferException e) {
                    parsedMessage = (Deallocate)e.getUnfinishedMessage();
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
            public boolean hasStmtId() {
                return (this.bitField0_ & 1) != 0;
            }

            @Override
            public int getStmtId() {
                return this.stmtId_;
            }

            public Builder setStmtId(int value) {
                this.bitField0_ |= 1;
                this.stmtId_ = value;
                this.onChanged();
                return this;
            }

            public Builder clearStmtId() {
                this.bitField0_ &= 0xFFFFFFFE;
                this.stmtId_ = 0;
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

    public static interface DeallocateOrBuilder
    extends MessageOrBuilder {
        public boolean hasStmtId();

        public int getStmtId();
    }

    public static final class Execute
    extends GeneratedMessageV3
    implements ExecuteOrBuilder {
        private static final long serialVersionUID = 0L;
        private int bitField0_;
        public static final int STMT_ID_FIELD_NUMBER = 1;
        private int stmtId_;
        public static final int ARGS_FIELD_NUMBER = 2;
        private List<MysqlxDatatypes.Any> args_;
        public static final int COMPACT_METADATA_FIELD_NUMBER = 3;
        private boolean compactMetadata_;
        private byte memoizedIsInitialized = (byte)-1;
        private static final Execute DEFAULT_INSTANCE = new Execute();
        @Deprecated
        public static final Parser<Execute> PARSER = new AbstractParser<Execute>(){

            public Execute parsePartialFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                return new Execute(input, extensionRegistry);
            }
        };

        private Execute(GeneratedMessageV3.Builder<?> builder) {
            super(builder);
        }

        private Execute() {
            this.args_ = Collections.emptyList();
        }

        protected Object newInstance(GeneratedMessageV3.UnusedPrivateParameter unused) {
            return new Execute();
        }

        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        private Execute(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            this();
            if (extensionRegistry == null) {
                throw new NullPointerException();
            }
            int mutable_bitField0_ = 0;
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
                            this.stmtId_ = input.readUInt32();
                            continue block12;
                        }
                        case 18: {
                            if ((mutable_bitField0_ & 2) == 0) {
                                this.args_ = new ArrayList<MysqlxDatatypes.Any>();
                                mutable_bitField0_ |= 2;
                            }
                            this.args_.add((MysqlxDatatypes.Any)input.readMessage(MysqlxDatatypes.Any.PARSER, extensionRegistry));
                            continue block12;
                        }
                        case 24: {
                            this.bitField0_ |= 2;
                            this.compactMetadata_ = input.readBool();
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
                if ((mutable_bitField0_ & 2) != 0) {
                    this.args_ = Collections.unmodifiableList(this.args_);
                }
                this.unknownFields = unknownFields.build();
                this.makeExtensionsImmutable();
            }
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return internal_static_Mysqlx_Prepare_Execute_descriptor;
        }

        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return internal_static_Mysqlx_Prepare_Execute_fieldAccessorTable.ensureFieldAccessorsInitialized(Execute.class, Builder.class);
        }

        @Override
        public boolean hasStmtId() {
            return (this.bitField0_ & 1) != 0;
        }

        @Override
        public int getStmtId() {
            return this.stmtId_;
        }

        @Override
        public List<MysqlxDatatypes.Any> getArgsList() {
            return this.args_;
        }

        @Override
        public List<? extends MysqlxDatatypes.AnyOrBuilder> getArgsOrBuilderList() {
            return this.args_;
        }

        @Override
        public int getArgsCount() {
            return this.args_.size();
        }

        @Override
        public MysqlxDatatypes.Any getArgs(int index) {
            return this.args_.get(index);
        }

        @Override
        public MysqlxDatatypes.AnyOrBuilder getArgsOrBuilder(int index) {
            return this.args_.get(index);
        }

        @Override
        public boolean hasCompactMetadata() {
            return (this.bitField0_ & 2) != 0;
        }

        @Override
        public boolean getCompactMetadata() {
            return this.compactMetadata_;
        }

        public final boolean isInitialized() {
            byte isInitialized = this.memoizedIsInitialized;
            if (isInitialized == 1) {
                return true;
            }
            if (isInitialized == 0) {
                return false;
            }
            if (!this.hasStmtId()) {
                this.memoizedIsInitialized = 0;
                return false;
            }
            for (int i = 0; i < this.getArgsCount(); ++i) {
                if (this.getArgs(i).isInitialized()) continue;
                this.memoizedIsInitialized = 0;
                return false;
            }
            this.memoizedIsInitialized = 1;
            return true;
        }

        public void writeTo(CodedOutputStream output) throws IOException {
            if ((this.bitField0_ & 1) != 0) {
                output.writeUInt32(1, this.stmtId_);
            }
            for (int i = 0; i < this.args_.size(); ++i) {
                output.writeMessage(2, (MessageLite)this.args_.get(i));
            }
            if ((this.bitField0_ & 2) != 0) {
                output.writeBool(3, this.compactMetadata_);
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
                size += CodedOutputStream.computeUInt32Size((int)1, (int)this.stmtId_);
            }
            for (int i = 0; i < this.args_.size(); ++i) {
                size += CodedOutputStream.computeMessageSize((int)2, (MessageLite)((MessageLite)this.args_.get(i)));
            }
            if ((this.bitField0_ & 2) != 0) {
                size += CodedOutputStream.computeBoolSize((int)3, (boolean)this.compactMetadata_);
            }
            this.memoizedSize = size += this.unknownFields.getSerializedSize();
            return size;
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof Execute)) {
                return super.equals(obj);
            }
            Execute other = (Execute)obj;
            if (this.hasStmtId() != other.hasStmtId()) {
                return false;
            }
            if (this.hasStmtId() && this.getStmtId() != other.getStmtId()) {
                return false;
            }
            if (!this.getArgsList().equals(other.getArgsList())) {
                return false;
            }
            if (this.hasCompactMetadata() != other.hasCompactMetadata()) {
                return false;
            }
            if (this.hasCompactMetadata() && this.getCompactMetadata() != other.getCompactMetadata()) {
                return false;
            }
            return this.unknownFields.equals((Object)other.unknownFields);
        }

        public int hashCode() {
            if (this.memoizedHashCode != 0) {
                return this.memoizedHashCode;
            }
            int hash = 41;
            hash = 19 * hash + Execute.getDescriptor().hashCode();
            if (this.hasStmtId()) {
                hash = 37 * hash + 1;
                hash = 53 * hash + this.getStmtId();
            }
            if (this.getArgsCount() > 0) {
                hash = 37 * hash + 2;
                hash = 53 * hash + this.getArgsList().hashCode();
            }
            if (this.hasCompactMetadata()) {
                hash = 37 * hash + 3;
                hash = 53 * hash + Internal.hashBoolean((boolean)this.getCompactMetadata());
            }
            this.memoizedHashCode = hash = 29 * hash + this.unknownFields.hashCode();
            return hash;
        }

        public static Execute parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
            return (Execute)PARSER.parseFrom(data);
        }

        public static Execute parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (Execute)PARSER.parseFrom(data, extensionRegistry);
        }

        public static Execute parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return (Execute)PARSER.parseFrom(data);
        }

        public static Execute parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (Execute)PARSER.parseFrom(data, extensionRegistry);
        }

        public static Execute parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return (Execute)PARSER.parseFrom(data);
        }

        public static Execute parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (Execute)PARSER.parseFrom(data, extensionRegistry);
        }

        public static Execute parseFrom(InputStream input) throws IOException {
            return (Execute)GeneratedMessageV3.parseWithIOException(PARSER, (InputStream)input);
        }

        public static Execute parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (Execute)GeneratedMessageV3.parseWithIOException(PARSER, (InputStream)input, (ExtensionRegistryLite)extensionRegistry);
        }

        public static Execute parseDelimitedFrom(InputStream input) throws IOException {
            return (Execute)GeneratedMessageV3.parseDelimitedWithIOException(PARSER, (InputStream)input);
        }

        public static Execute parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (Execute)GeneratedMessageV3.parseDelimitedWithIOException(PARSER, (InputStream)input, (ExtensionRegistryLite)extensionRegistry);
        }

        public static Execute parseFrom(CodedInputStream input) throws IOException {
            return (Execute)GeneratedMessageV3.parseWithIOException(PARSER, (CodedInputStream)input);
        }

        public static Execute parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (Execute)GeneratedMessageV3.parseWithIOException(PARSER, (CodedInputStream)input, (ExtensionRegistryLite)extensionRegistry);
        }

        public Builder newBuilderForType() {
            return Execute.newBuilder();
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(Execute prototype) {
            return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
        }

        public Builder toBuilder() {
            return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
        }

        protected Builder newBuilderForType(GeneratedMessageV3.BuilderParent parent) {
            Builder builder = new Builder(parent);
            return builder;
        }

        public static Execute getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<Execute> parser() {
            return PARSER;
        }

        public Parser<Execute> getParserForType() {
            return PARSER;
        }

        public Execute getDefaultInstanceForType() {
            return DEFAULT_INSTANCE;
        }

        public static final class Builder
        extends GeneratedMessageV3.Builder<Builder>
        implements ExecuteOrBuilder {
            private int bitField0_;
            private int stmtId_;
            private List<MysqlxDatatypes.Any> args_ = Collections.emptyList();
            private RepeatedFieldBuilderV3<MysqlxDatatypes.Any, MysqlxDatatypes.Any.Builder, MysqlxDatatypes.AnyOrBuilder> argsBuilder_;
            private boolean compactMetadata_;

            public static final Descriptors.Descriptor getDescriptor() {
                return internal_static_Mysqlx_Prepare_Execute_descriptor;
            }

            protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                return internal_static_Mysqlx_Prepare_Execute_fieldAccessorTable.ensureFieldAccessorsInitialized(Execute.class, Builder.class);
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
                    this.getArgsFieldBuilder();
                }
            }

            public Builder clear() {
                super.clear();
                this.stmtId_ = 0;
                this.bitField0_ &= 0xFFFFFFFE;
                if (this.argsBuilder_ == null) {
                    this.args_ = Collections.emptyList();
                    this.bitField0_ &= 0xFFFFFFFD;
                } else {
                    this.argsBuilder_.clear();
                }
                this.compactMetadata_ = false;
                this.bitField0_ &= 0xFFFFFFFB;
                return this;
            }

            public Descriptors.Descriptor getDescriptorForType() {
                return internal_static_Mysqlx_Prepare_Execute_descriptor;
            }

            public Execute getDefaultInstanceForType() {
                return Execute.getDefaultInstance();
            }

            public Execute build() {
                Execute result = this.buildPartial();
                if (!result.isInitialized()) {
                    throw Builder.newUninitializedMessageException((Message)result);
                }
                return result;
            }

            public Execute buildPartial() {
                Execute result = new Execute(this);
                int from_bitField0_ = this.bitField0_;
                int to_bitField0_ = 0;
                if ((from_bitField0_ & 1) != 0) {
                    result.stmtId_ = this.stmtId_;
                    to_bitField0_ |= 1;
                }
                if (this.argsBuilder_ == null) {
                    if ((this.bitField0_ & 2) != 0) {
                        this.args_ = Collections.unmodifiableList(this.args_);
                        this.bitField0_ &= 0xFFFFFFFD;
                    }
                    result.args_ = this.args_;
                } else {
                    result.args_ = this.argsBuilder_.build();
                }
                if ((from_bitField0_ & 4) != 0) {
                    result.compactMetadata_ = this.compactMetadata_;
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
                if (other instanceof Execute) {
                    return this.mergeFrom((Execute)other);
                }
                super.mergeFrom(other);
                return this;
            }

            public Builder mergeFrom(Execute other) {
                if (other == Execute.getDefaultInstance()) {
                    return this;
                }
                if (other.hasStmtId()) {
                    this.setStmtId(other.getStmtId());
                }
                if (this.argsBuilder_ == null) {
                    if (!other.args_.isEmpty()) {
                        if (this.args_.isEmpty()) {
                            this.args_ = other.args_;
                            this.bitField0_ &= 0xFFFFFFFD;
                        } else {
                            this.ensureArgsIsMutable();
                            this.args_.addAll(other.args_);
                        }
                        this.onChanged();
                    }
                } else if (!other.args_.isEmpty()) {
                    if (this.argsBuilder_.isEmpty()) {
                        this.argsBuilder_.dispose();
                        this.argsBuilder_ = null;
                        this.args_ = other.args_;
                        this.bitField0_ &= 0xFFFFFFFD;
                        this.argsBuilder_ = alwaysUseFieldBuilders ? this.getArgsFieldBuilder() : null;
                    } else {
                        this.argsBuilder_.addAllMessages((Iterable)other.args_);
                    }
                }
                if (other.hasCompactMetadata()) {
                    this.setCompactMetadata(other.getCompactMetadata());
                }
                this.mergeUnknownFields(other.unknownFields);
                this.onChanged();
                return this;
            }

            public final boolean isInitialized() {
                if (!this.hasStmtId()) {
                    return false;
                }
                for (int i = 0; i < this.getArgsCount(); ++i) {
                    if (this.getArgs(i).isInitialized()) continue;
                    return false;
                }
                return true;
            }

            public Builder mergeFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                Execute parsedMessage = null;
                try {
                    parsedMessage = (Execute)PARSER.parsePartialFrom(input, extensionRegistry);
                }
                catch (InvalidProtocolBufferException e) {
                    parsedMessage = (Execute)e.getUnfinishedMessage();
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
            public boolean hasStmtId() {
                return (this.bitField0_ & 1) != 0;
            }

            @Override
            public int getStmtId() {
                return this.stmtId_;
            }

            public Builder setStmtId(int value) {
                this.bitField0_ |= 1;
                this.stmtId_ = value;
                this.onChanged();
                return this;
            }

            public Builder clearStmtId() {
                this.bitField0_ &= 0xFFFFFFFE;
                this.stmtId_ = 0;
                this.onChanged();
                return this;
            }

            private void ensureArgsIsMutable() {
                if ((this.bitField0_ & 2) == 0) {
                    this.args_ = new ArrayList<MysqlxDatatypes.Any>(this.args_);
                    this.bitField0_ |= 2;
                }
            }

            @Override
            public List<MysqlxDatatypes.Any> getArgsList() {
                if (this.argsBuilder_ == null) {
                    return Collections.unmodifiableList(this.args_);
                }
                return this.argsBuilder_.getMessageList();
            }

            @Override
            public int getArgsCount() {
                if (this.argsBuilder_ == null) {
                    return this.args_.size();
                }
                return this.argsBuilder_.getCount();
            }

            @Override
            public MysqlxDatatypes.Any getArgs(int index) {
                if (this.argsBuilder_ == null) {
                    return this.args_.get(index);
                }
                return (MysqlxDatatypes.Any)this.argsBuilder_.getMessage(index);
            }

            public Builder setArgs(int index, MysqlxDatatypes.Any value) {
                if (this.argsBuilder_ == null) {
                    if (value == null) {
                        throw new NullPointerException();
                    }
                    this.ensureArgsIsMutable();
                    this.args_.set(index, value);
                    this.onChanged();
                } else {
                    this.argsBuilder_.setMessage(index, (AbstractMessage)value);
                }
                return this;
            }

            public Builder setArgs(int index, MysqlxDatatypes.Any.Builder builderForValue) {
                if (this.argsBuilder_ == null) {
                    this.ensureArgsIsMutable();
                    this.args_.set(index, builderForValue.build());
                    this.onChanged();
                } else {
                    this.argsBuilder_.setMessage(index, (AbstractMessage)builderForValue.build());
                }
                return this;
            }

            public Builder addArgs(MysqlxDatatypes.Any value) {
                if (this.argsBuilder_ == null) {
                    if (value == null) {
                        throw new NullPointerException();
                    }
                    this.ensureArgsIsMutable();
                    this.args_.add(value);
                    this.onChanged();
                } else {
                    this.argsBuilder_.addMessage((AbstractMessage)value);
                }
                return this;
            }

            public Builder addArgs(int index, MysqlxDatatypes.Any value) {
                if (this.argsBuilder_ == null) {
                    if (value == null) {
                        throw new NullPointerException();
                    }
                    this.ensureArgsIsMutable();
                    this.args_.add(index, value);
                    this.onChanged();
                } else {
                    this.argsBuilder_.addMessage(index, (AbstractMessage)value);
                }
                return this;
            }

            public Builder addArgs(MysqlxDatatypes.Any.Builder builderForValue) {
                if (this.argsBuilder_ == null) {
                    this.ensureArgsIsMutable();
                    this.args_.add(builderForValue.build());
                    this.onChanged();
                } else {
                    this.argsBuilder_.addMessage((AbstractMessage)builderForValue.build());
                }
                return this;
            }

            public Builder addArgs(int index, MysqlxDatatypes.Any.Builder builderForValue) {
                if (this.argsBuilder_ == null) {
                    this.ensureArgsIsMutable();
                    this.args_.add(index, builderForValue.build());
                    this.onChanged();
                } else {
                    this.argsBuilder_.addMessage(index, (AbstractMessage)builderForValue.build());
                }
                return this;
            }

            public Builder addAllArgs(Iterable<? extends MysqlxDatatypes.Any> values) {
                if (this.argsBuilder_ == null) {
                    this.ensureArgsIsMutable();
                    AbstractMessageLite.Builder.addAll(values, this.args_);
                    this.onChanged();
                } else {
                    this.argsBuilder_.addAllMessages(values);
                }
                return this;
            }

            public Builder clearArgs() {
                if (this.argsBuilder_ == null) {
                    this.args_ = Collections.emptyList();
                    this.bitField0_ &= 0xFFFFFFFD;
                    this.onChanged();
                } else {
                    this.argsBuilder_.clear();
                }
                return this;
            }

            public Builder removeArgs(int index) {
                if (this.argsBuilder_ == null) {
                    this.ensureArgsIsMutable();
                    this.args_.remove(index);
                    this.onChanged();
                } else {
                    this.argsBuilder_.remove(index);
                }
                return this;
            }

            public MysqlxDatatypes.Any.Builder getArgsBuilder(int index) {
                return (MysqlxDatatypes.Any.Builder)this.getArgsFieldBuilder().getBuilder(index);
            }

            @Override
            public MysqlxDatatypes.AnyOrBuilder getArgsOrBuilder(int index) {
                if (this.argsBuilder_ == null) {
                    return this.args_.get(index);
                }
                return (MysqlxDatatypes.AnyOrBuilder)this.argsBuilder_.getMessageOrBuilder(index);
            }

            @Override
            public List<? extends MysqlxDatatypes.AnyOrBuilder> getArgsOrBuilderList() {
                if (this.argsBuilder_ != null) {
                    return this.argsBuilder_.getMessageOrBuilderList();
                }
                return Collections.unmodifiableList(this.args_);
            }

            public MysqlxDatatypes.Any.Builder addArgsBuilder() {
                return (MysqlxDatatypes.Any.Builder)this.getArgsFieldBuilder().addBuilder((AbstractMessage)MysqlxDatatypes.Any.getDefaultInstance());
            }

            public MysqlxDatatypes.Any.Builder addArgsBuilder(int index) {
                return (MysqlxDatatypes.Any.Builder)this.getArgsFieldBuilder().addBuilder(index, (AbstractMessage)MysqlxDatatypes.Any.getDefaultInstance());
            }

            public List<MysqlxDatatypes.Any.Builder> getArgsBuilderList() {
                return this.getArgsFieldBuilder().getBuilderList();
            }

            private RepeatedFieldBuilderV3<MysqlxDatatypes.Any, MysqlxDatatypes.Any.Builder, MysqlxDatatypes.AnyOrBuilder> getArgsFieldBuilder() {
                if (this.argsBuilder_ == null) {
                    this.argsBuilder_ = new RepeatedFieldBuilderV3(this.args_, (this.bitField0_ & 2) != 0, (AbstractMessage.BuilderParent)this.getParentForChildren(), this.isClean());
                    this.args_ = null;
                }
                return this.argsBuilder_;
            }

            @Override
            public boolean hasCompactMetadata() {
                return (this.bitField0_ & 4) != 0;
            }

            @Override
            public boolean getCompactMetadata() {
                return this.compactMetadata_;
            }

            public Builder setCompactMetadata(boolean value) {
                this.bitField0_ |= 4;
                this.compactMetadata_ = value;
                this.onChanged();
                return this;
            }

            public Builder clearCompactMetadata() {
                this.bitField0_ &= 0xFFFFFFFB;
                this.compactMetadata_ = false;
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

    public static interface ExecuteOrBuilder
    extends MessageOrBuilder {
        public boolean hasStmtId();

        public int getStmtId();

        public List<MysqlxDatatypes.Any> getArgsList();

        public MysqlxDatatypes.Any getArgs(int var1);

        public int getArgsCount();

        public List<? extends MysqlxDatatypes.AnyOrBuilder> getArgsOrBuilderList();

        public MysqlxDatatypes.AnyOrBuilder getArgsOrBuilder(int var1);

        public boolean hasCompactMetadata();

        public boolean getCompactMetadata();
    }

    public static final class Prepare
    extends GeneratedMessageV3
    implements PrepareOrBuilder {
        private static final long serialVersionUID = 0L;
        private int bitField0_;
        public static final int STMT_ID_FIELD_NUMBER = 1;
        private int stmtId_;
        public static final int STMT_FIELD_NUMBER = 2;
        private OneOfMessage stmt_;
        private byte memoizedIsInitialized = (byte)-1;
        private static final Prepare DEFAULT_INSTANCE = new Prepare();
        @Deprecated
        public static final Parser<Prepare> PARSER = new AbstractParser<Prepare>(){

            public Prepare parsePartialFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                return new Prepare(input, extensionRegistry);
            }
        };

        private Prepare(GeneratedMessageV3.Builder<?> builder) {
            super(builder);
        }

        private Prepare() {
        }

        protected Object newInstance(GeneratedMessageV3.UnusedPrivateParameter unused) {
            return new Prepare();
        }

        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        private Prepare(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
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
                            this.stmtId_ = input.readUInt32();
                            continue block11;
                        }
                        case 18: {
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
            return internal_static_Mysqlx_Prepare_Prepare_descriptor;
        }

        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return internal_static_Mysqlx_Prepare_Prepare_fieldAccessorTable.ensureFieldAccessorsInitialized(Prepare.class, Builder.class);
        }

        @Override
        public boolean hasStmtId() {
            return (this.bitField0_ & 1) != 0;
        }

        @Override
        public int getStmtId() {
            return this.stmtId_;
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

        public final boolean isInitialized() {
            byte isInitialized = this.memoizedIsInitialized;
            if (isInitialized == 1) {
                return true;
            }
            if (isInitialized == 0) {
                return false;
            }
            if (!this.hasStmtId()) {
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
                output.writeUInt32(1, this.stmtId_);
            }
            if ((this.bitField0_ & 2) != 0) {
                output.writeMessage(2, (MessageLite)this.getStmt());
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
                size += CodedOutputStream.computeUInt32Size((int)1, (int)this.stmtId_);
            }
            if ((this.bitField0_ & 2) != 0) {
                size += CodedOutputStream.computeMessageSize((int)2, (MessageLite)this.getStmt());
            }
            this.memoizedSize = size += this.unknownFields.getSerializedSize();
            return size;
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof Prepare)) {
                return super.equals(obj);
            }
            Prepare other = (Prepare)obj;
            if (this.hasStmtId() != other.hasStmtId()) {
                return false;
            }
            if (this.hasStmtId() && this.getStmtId() != other.getStmtId()) {
                return false;
            }
            if (this.hasStmt() != other.hasStmt()) {
                return false;
            }
            if (this.hasStmt() && !this.getStmt().equals(other.getStmt())) {
                return false;
            }
            return this.unknownFields.equals((Object)other.unknownFields);
        }

        public int hashCode() {
            if (this.memoizedHashCode != 0) {
                return this.memoizedHashCode;
            }
            int hash = 41;
            hash = 19 * hash + Prepare.getDescriptor().hashCode();
            if (this.hasStmtId()) {
                hash = 37 * hash + 1;
                hash = 53 * hash + this.getStmtId();
            }
            if (this.hasStmt()) {
                hash = 37 * hash + 2;
                hash = 53 * hash + this.getStmt().hashCode();
            }
            this.memoizedHashCode = hash = 29 * hash + this.unknownFields.hashCode();
            return hash;
        }

        public static Prepare parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
            return (Prepare)PARSER.parseFrom(data);
        }

        public static Prepare parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (Prepare)PARSER.parseFrom(data, extensionRegistry);
        }

        public static Prepare parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return (Prepare)PARSER.parseFrom(data);
        }

        public static Prepare parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (Prepare)PARSER.parseFrom(data, extensionRegistry);
        }

        public static Prepare parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return (Prepare)PARSER.parseFrom(data);
        }

        public static Prepare parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (Prepare)PARSER.parseFrom(data, extensionRegistry);
        }

        public static Prepare parseFrom(InputStream input) throws IOException {
            return (Prepare)GeneratedMessageV3.parseWithIOException(PARSER, (InputStream)input);
        }

        public static Prepare parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (Prepare)GeneratedMessageV3.parseWithIOException(PARSER, (InputStream)input, (ExtensionRegistryLite)extensionRegistry);
        }

        public static Prepare parseDelimitedFrom(InputStream input) throws IOException {
            return (Prepare)GeneratedMessageV3.parseDelimitedWithIOException(PARSER, (InputStream)input);
        }

        public static Prepare parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (Prepare)GeneratedMessageV3.parseDelimitedWithIOException(PARSER, (InputStream)input, (ExtensionRegistryLite)extensionRegistry);
        }

        public static Prepare parseFrom(CodedInputStream input) throws IOException {
            return (Prepare)GeneratedMessageV3.parseWithIOException(PARSER, (CodedInputStream)input);
        }

        public static Prepare parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (Prepare)GeneratedMessageV3.parseWithIOException(PARSER, (CodedInputStream)input, (ExtensionRegistryLite)extensionRegistry);
        }

        public Builder newBuilderForType() {
            return Prepare.newBuilder();
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(Prepare prototype) {
            return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
        }

        public Builder toBuilder() {
            return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
        }

        protected Builder newBuilderForType(GeneratedMessageV3.BuilderParent parent) {
            Builder builder = new Builder(parent);
            return builder;
        }

        public static Prepare getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<Prepare> parser() {
            return PARSER;
        }

        public Parser<Prepare> getParserForType() {
            return PARSER;
        }

        public Prepare getDefaultInstanceForType() {
            return DEFAULT_INSTANCE;
        }

        public static final class Builder
        extends GeneratedMessageV3.Builder<Builder>
        implements PrepareOrBuilder {
            private int bitField0_;
            private int stmtId_;
            private OneOfMessage stmt_;
            private SingleFieldBuilderV3<OneOfMessage, OneOfMessage.Builder, OneOfMessageOrBuilder> stmtBuilder_;

            public static final Descriptors.Descriptor getDescriptor() {
                return internal_static_Mysqlx_Prepare_Prepare_descriptor;
            }

            protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                return internal_static_Mysqlx_Prepare_Prepare_fieldAccessorTable.ensureFieldAccessorsInitialized(Prepare.class, Builder.class);
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
                this.stmtId_ = 0;
                this.bitField0_ &= 0xFFFFFFFE;
                if (this.stmtBuilder_ == null) {
                    this.stmt_ = null;
                } else {
                    this.stmtBuilder_.clear();
                }
                this.bitField0_ &= 0xFFFFFFFD;
                return this;
            }

            public Descriptors.Descriptor getDescriptorForType() {
                return internal_static_Mysqlx_Prepare_Prepare_descriptor;
            }

            public Prepare getDefaultInstanceForType() {
                return Prepare.getDefaultInstance();
            }

            public Prepare build() {
                Prepare result = this.buildPartial();
                if (!result.isInitialized()) {
                    throw Builder.newUninitializedMessageException((Message)result);
                }
                return result;
            }

            public Prepare buildPartial() {
                Prepare result = new Prepare(this);
                int from_bitField0_ = this.bitField0_;
                int to_bitField0_ = 0;
                if ((from_bitField0_ & 1) != 0) {
                    result.stmtId_ = this.stmtId_;
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
                if (other instanceof Prepare) {
                    return this.mergeFrom((Prepare)other);
                }
                super.mergeFrom(other);
                return this;
            }

            public Builder mergeFrom(Prepare other) {
                if (other == Prepare.getDefaultInstance()) {
                    return this;
                }
                if (other.hasStmtId()) {
                    this.setStmtId(other.getStmtId());
                }
                if (other.hasStmt()) {
                    this.mergeStmt(other.getStmt());
                }
                this.mergeUnknownFields(other.unknownFields);
                this.onChanged();
                return this;
            }

            public final boolean isInitialized() {
                if (!this.hasStmtId()) {
                    return false;
                }
                if (!this.hasStmt()) {
                    return false;
                }
                return this.getStmt().isInitialized();
            }

            public Builder mergeFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                Prepare parsedMessage = null;
                try {
                    parsedMessage = (Prepare)PARSER.parsePartialFrom(input, extensionRegistry);
                }
                catch (InvalidProtocolBufferException e) {
                    parsedMessage = (Prepare)e.getUnfinishedMessage();
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
            public boolean hasStmtId() {
                return (this.bitField0_ & 1) != 0;
            }

            @Override
            public int getStmtId() {
                return this.stmtId_;
            }

            public Builder setStmtId(int value) {
                this.bitField0_ |= 1;
                this.stmtId_ = value;
                this.onChanged();
                return this;
            }

            public Builder clearStmtId() {
                this.bitField0_ &= 0xFFFFFFFE;
                this.stmtId_ = 0;
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
            public static final int FIND_FIELD_NUMBER = 2;
            private MysqlxCrud.Find find_;
            public static final int INSERT_FIELD_NUMBER = 3;
            private MysqlxCrud.Insert insert_;
            public static final int UPDATE_FIELD_NUMBER = 4;
            private MysqlxCrud.Update update_;
            public static final int DELETE_FIELD_NUMBER = 5;
            private MysqlxCrud.Delete delete_;
            public static final int STMT_EXECUTE_FIELD_NUMBER = 6;
            private MysqlxSql.StmtExecute stmtExecute_;
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
                    block15: while (!done) {
                        int tag = input.readTag();
                        switch (tag) {
                            case 0: {
                                done = true;
                                continue block15;
                            }
                            case 8: {
                                int rawValue = input.readEnum();
                                Type value = Type.valueOf(rawValue);
                                if (value == null) {
                                    unknownFields.mergeVarintField(1, rawValue);
                                    continue block15;
                                }
                                this.bitField0_ |= 1;
                                this.type_ = rawValue;
                                continue block15;
                            }
                            case 18: {
                                MysqlxCrud.Find.Builder subBuilder = null;
                                if ((this.bitField0_ & 2) != 0) {
                                    subBuilder = this.find_.toBuilder();
                                }
                                this.find_ = (MysqlxCrud.Find)input.readMessage(MysqlxCrud.Find.PARSER, extensionRegistry);
                                if (subBuilder != null) {
                                    subBuilder.mergeFrom(this.find_);
                                    this.find_ = subBuilder.buildPartial();
                                }
                                this.bitField0_ |= 2;
                                continue block15;
                            }
                            case 26: {
                                MysqlxCrud.Insert.Builder subBuilder = null;
                                if ((this.bitField0_ & 4) != 0) {
                                    subBuilder = this.insert_.toBuilder();
                                }
                                this.insert_ = (MysqlxCrud.Insert)input.readMessage(MysqlxCrud.Insert.PARSER, extensionRegistry);
                                if (subBuilder != null) {
                                    subBuilder.mergeFrom(this.insert_);
                                    this.insert_ = subBuilder.buildPartial();
                                }
                                this.bitField0_ |= 4;
                                continue block15;
                            }
                            case 34: {
                                MysqlxCrud.Update.Builder subBuilder = null;
                                if ((this.bitField0_ & 8) != 0) {
                                    subBuilder = this.update_.toBuilder();
                                }
                                this.update_ = (MysqlxCrud.Update)input.readMessage(MysqlxCrud.Update.PARSER, extensionRegistry);
                                if (subBuilder != null) {
                                    subBuilder.mergeFrom(this.update_);
                                    this.update_ = subBuilder.buildPartial();
                                }
                                this.bitField0_ |= 8;
                                continue block15;
                            }
                            case 42: {
                                MysqlxCrud.Delete.Builder subBuilder = null;
                                if ((this.bitField0_ & 0x10) != 0) {
                                    subBuilder = this.delete_.toBuilder();
                                }
                                this.delete_ = (MysqlxCrud.Delete)input.readMessage(MysqlxCrud.Delete.PARSER, extensionRegistry);
                                if (subBuilder != null) {
                                    subBuilder.mergeFrom(this.delete_);
                                    this.delete_ = subBuilder.buildPartial();
                                }
                                this.bitField0_ |= 0x10;
                                continue block15;
                            }
                            case 50: {
                                MysqlxSql.StmtExecute.Builder subBuilder = null;
                                if ((this.bitField0_ & 0x20) != 0) {
                                    subBuilder = this.stmtExecute_.toBuilder();
                                }
                                this.stmtExecute_ = (MysqlxSql.StmtExecute)input.readMessage(MysqlxSql.StmtExecute.PARSER, extensionRegistry);
                                if (subBuilder != null) {
                                    subBuilder.mergeFrom(this.stmtExecute_);
                                    this.stmtExecute_ = subBuilder.buildPartial();
                                }
                                this.bitField0_ |= 0x20;
                                continue block15;
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
                return internal_static_Mysqlx_Prepare_Prepare_OneOfMessage_descriptor;
            }

            protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                return internal_static_Mysqlx_Prepare_Prepare_OneOfMessage_fieldAccessorTable.ensureFieldAccessorsInitialized(OneOfMessage.class, Builder.class);
            }

            @Override
            public boolean hasType() {
                return (this.bitField0_ & 1) != 0;
            }

            @Override
            public Type getType() {
                Type result = Type.valueOf(this.type_);
                return result == null ? Type.FIND : result;
            }

            @Override
            public boolean hasFind() {
                return (this.bitField0_ & 2) != 0;
            }

            @Override
            public MysqlxCrud.Find getFind() {
                return this.find_ == null ? MysqlxCrud.Find.getDefaultInstance() : this.find_;
            }

            @Override
            public MysqlxCrud.FindOrBuilder getFindOrBuilder() {
                return this.find_ == null ? MysqlxCrud.Find.getDefaultInstance() : this.find_;
            }

            @Override
            public boolean hasInsert() {
                return (this.bitField0_ & 4) != 0;
            }

            @Override
            public MysqlxCrud.Insert getInsert() {
                return this.insert_ == null ? MysqlxCrud.Insert.getDefaultInstance() : this.insert_;
            }

            @Override
            public MysqlxCrud.InsertOrBuilder getInsertOrBuilder() {
                return this.insert_ == null ? MysqlxCrud.Insert.getDefaultInstance() : this.insert_;
            }

            @Override
            public boolean hasUpdate() {
                return (this.bitField0_ & 8) != 0;
            }

            @Override
            public MysqlxCrud.Update getUpdate() {
                return this.update_ == null ? MysqlxCrud.Update.getDefaultInstance() : this.update_;
            }

            @Override
            public MysqlxCrud.UpdateOrBuilder getUpdateOrBuilder() {
                return this.update_ == null ? MysqlxCrud.Update.getDefaultInstance() : this.update_;
            }

            @Override
            public boolean hasDelete() {
                return (this.bitField0_ & 0x10) != 0;
            }

            @Override
            public MysqlxCrud.Delete getDelete() {
                return this.delete_ == null ? MysqlxCrud.Delete.getDefaultInstance() : this.delete_;
            }

            @Override
            public MysqlxCrud.DeleteOrBuilder getDeleteOrBuilder() {
                return this.delete_ == null ? MysqlxCrud.Delete.getDefaultInstance() : this.delete_;
            }

            @Override
            public boolean hasStmtExecute() {
                return (this.bitField0_ & 0x20) != 0;
            }

            @Override
            public MysqlxSql.StmtExecute getStmtExecute() {
                return this.stmtExecute_ == null ? MysqlxSql.StmtExecute.getDefaultInstance() : this.stmtExecute_;
            }

            @Override
            public MysqlxSql.StmtExecuteOrBuilder getStmtExecuteOrBuilder() {
                return this.stmtExecute_ == null ? MysqlxSql.StmtExecute.getDefaultInstance() : this.stmtExecute_;
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
                if (this.hasFind() && !this.getFind().isInitialized()) {
                    this.memoizedIsInitialized = 0;
                    return false;
                }
                if (this.hasInsert() && !this.getInsert().isInitialized()) {
                    this.memoizedIsInitialized = 0;
                    return false;
                }
                if (this.hasUpdate() && !this.getUpdate().isInitialized()) {
                    this.memoizedIsInitialized = 0;
                    return false;
                }
                if (this.hasDelete() && !this.getDelete().isInitialized()) {
                    this.memoizedIsInitialized = 0;
                    return false;
                }
                if (this.hasStmtExecute() && !this.getStmtExecute().isInitialized()) {
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
                    output.writeMessage(2, (MessageLite)this.getFind());
                }
                if ((this.bitField0_ & 4) != 0) {
                    output.writeMessage(3, (MessageLite)this.getInsert());
                }
                if ((this.bitField0_ & 8) != 0) {
                    output.writeMessage(4, (MessageLite)this.getUpdate());
                }
                if ((this.bitField0_ & 0x10) != 0) {
                    output.writeMessage(5, (MessageLite)this.getDelete());
                }
                if ((this.bitField0_ & 0x20) != 0) {
                    output.writeMessage(6, (MessageLite)this.getStmtExecute());
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
                    size += CodedOutputStream.computeMessageSize((int)2, (MessageLite)this.getFind());
                }
                if ((this.bitField0_ & 4) != 0) {
                    size += CodedOutputStream.computeMessageSize((int)3, (MessageLite)this.getInsert());
                }
                if ((this.bitField0_ & 8) != 0) {
                    size += CodedOutputStream.computeMessageSize((int)4, (MessageLite)this.getUpdate());
                }
                if ((this.bitField0_ & 0x10) != 0) {
                    size += CodedOutputStream.computeMessageSize((int)5, (MessageLite)this.getDelete());
                }
                if ((this.bitField0_ & 0x20) != 0) {
                    size += CodedOutputStream.computeMessageSize((int)6, (MessageLite)this.getStmtExecute());
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
                if (this.hasFind() != other.hasFind()) {
                    return false;
                }
                if (this.hasFind() && !this.getFind().equals(other.getFind())) {
                    return false;
                }
                if (this.hasInsert() != other.hasInsert()) {
                    return false;
                }
                if (this.hasInsert() && !this.getInsert().equals(other.getInsert())) {
                    return false;
                }
                if (this.hasUpdate() != other.hasUpdate()) {
                    return false;
                }
                if (this.hasUpdate() && !this.getUpdate().equals(other.getUpdate())) {
                    return false;
                }
                if (this.hasDelete() != other.hasDelete()) {
                    return false;
                }
                if (this.hasDelete() && !this.getDelete().equals(other.getDelete())) {
                    return false;
                }
                if (this.hasStmtExecute() != other.hasStmtExecute()) {
                    return false;
                }
                if (this.hasStmtExecute() && !this.getStmtExecute().equals(other.getStmtExecute())) {
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
                if (this.hasFind()) {
                    hash = 37 * hash + 2;
                    hash = 53 * hash + this.getFind().hashCode();
                }
                if (this.hasInsert()) {
                    hash = 37 * hash + 3;
                    hash = 53 * hash + this.getInsert().hashCode();
                }
                if (this.hasUpdate()) {
                    hash = 37 * hash + 4;
                    hash = 53 * hash + this.getUpdate().hashCode();
                }
                if (this.hasDelete()) {
                    hash = 37 * hash + 5;
                    hash = 53 * hash + this.getDelete().hashCode();
                }
                if (this.hasStmtExecute()) {
                    hash = 37 * hash + 6;
                    hash = 53 * hash + this.getStmtExecute().hashCode();
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
                private MysqlxCrud.Find find_;
                private SingleFieldBuilderV3<MysqlxCrud.Find, MysqlxCrud.Find.Builder, MysqlxCrud.FindOrBuilder> findBuilder_;
                private MysqlxCrud.Insert insert_;
                private SingleFieldBuilderV3<MysqlxCrud.Insert, MysqlxCrud.Insert.Builder, MysqlxCrud.InsertOrBuilder> insertBuilder_;
                private MysqlxCrud.Update update_;
                private SingleFieldBuilderV3<MysqlxCrud.Update, MysqlxCrud.Update.Builder, MysqlxCrud.UpdateOrBuilder> updateBuilder_;
                private MysqlxCrud.Delete delete_;
                private SingleFieldBuilderV3<MysqlxCrud.Delete, MysqlxCrud.Delete.Builder, MysqlxCrud.DeleteOrBuilder> deleteBuilder_;
                private MysqlxSql.StmtExecute stmtExecute_;
                private SingleFieldBuilderV3<MysqlxSql.StmtExecute, MysqlxSql.StmtExecute.Builder, MysqlxSql.StmtExecuteOrBuilder> stmtExecuteBuilder_;

                public static final Descriptors.Descriptor getDescriptor() {
                    return internal_static_Mysqlx_Prepare_Prepare_OneOfMessage_descriptor;
                }

                protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                    return internal_static_Mysqlx_Prepare_Prepare_OneOfMessage_fieldAccessorTable.ensureFieldAccessorsInitialized(OneOfMessage.class, Builder.class);
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
                        this.getFindFieldBuilder();
                        this.getInsertFieldBuilder();
                        this.getUpdateFieldBuilder();
                        this.getDeleteFieldBuilder();
                        this.getStmtExecuteFieldBuilder();
                    }
                }

                public Builder clear() {
                    super.clear();
                    this.type_ = 0;
                    this.bitField0_ &= 0xFFFFFFFE;
                    if (this.findBuilder_ == null) {
                        this.find_ = null;
                    } else {
                        this.findBuilder_.clear();
                    }
                    this.bitField0_ &= 0xFFFFFFFD;
                    if (this.insertBuilder_ == null) {
                        this.insert_ = null;
                    } else {
                        this.insertBuilder_.clear();
                    }
                    this.bitField0_ &= 0xFFFFFFFB;
                    if (this.updateBuilder_ == null) {
                        this.update_ = null;
                    } else {
                        this.updateBuilder_.clear();
                    }
                    this.bitField0_ &= 0xFFFFFFF7;
                    if (this.deleteBuilder_ == null) {
                        this.delete_ = null;
                    } else {
                        this.deleteBuilder_.clear();
                    }
                    this.bitField0_ &= 0xFFFFFFEF;
                    if (this.stmtExecuteBuilder_ == null) {
                        this.stmtExecute_ = null;
                    } else {
                        this.stmtExecuteBuilder_.clear();
                    }
                    this.bitField0_ &= 0xFFFFFFDF;
                    return this;
                }

                public Descriptors.Descriptor getDescriptorForType() {
                    return internal_static_Mysqlx_Prepare_Prepare_OneOfMessage_descriptor;
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
                        if (this.findBuilder_ == null) {
                            result.find_ = this.find_;
                        } else {
                            result.find_ = (MysqlxCrud.Find)this.findBuilder_.build();
                        }
                        to_bitField0_ |= 2;
                    }
                    if ((from_bitField0_ & 4) != 0) {
                        if (this.insertBuilder_ == null) {
                            result.insert_ = this.insert_;
                        } else {
                            result.insert_ = (MysqlxCrud.Insert)this.insertBuilder_.build();
                        }
                        to_bitField0_ |= 4;
                    }
                    if ((from_bitField0_ & 8) != 0) {
                        if (this.updateBuilder_ == null) {
                            result.update_ = this.update_;
                        } else {
                            result.update_ = (MysqlxCrud.Update)this.updateBuilder_.build();
                        }
                        to_bitField0_ |= 8;
                    }
                    if ((from_bitField0_ & 0x10) != 0) {
                        if (this.deleteBuilder_ == null) {
                            result.delete_ = this.delete_;
                        } else {
                            result.delete_ = (MysqlxCrud.Delete)this.deleteBuilder_.build();
                        }
                        to_bitField0_ |= 0x10;
                    }
                    if ((from_bitField0_ & 0x20) != 0) {
                        if (this.stmtExecuteBuilder_ == null) {
                            result.stmtExecute_ = this.stmtExecute_;
                        } else {
                            result.stmtExecute_ = (MysqlxSql.StmtExecute)this.stmtExecuteBuilder_.build();
                        }
                        to_bitField0_ |= 0x20;
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
                    if (other.hasFind()) {
                        this.mergeFind(other.getFind());
                    }
                    if (other.hasInsert()) {
                        this.mergeInsert(other.getInsert());
                    }
                    if (other.hasUpdate()) {
                        this.mergeUpdate(other.getUpdate());
                    }
                    if (other.hasDelete()) {
                        this.mergeDelete(other.getDelete());
                    }
                    if (other.hasStmtExecute()) {
                        this.mergeStmtExecute(other.getStmtExecute());
                    }
                    this.mergeUnknownFields(other.unknownFields);
                    this.onChanged();
                    return this;
                }

                public final boolean isInitialized() {
                    if (!this.hasType()) {
                        return false;
                    }
                    if (this.hasFind() && !this.getFind().isInitialized()) {
                        return false;
                    }
                    if (this.hasInsert() && !this.getInsert().isInitialized()) {
                        return false;
                    }
                    if (this.hasUpdate() && !this.getUpdate().isInitialized()) {
                        return false;
                    }
                    if (this.hasDelete() && !this.getDelete().isInitialized()) {
                        return false;
                    }
                    return !this.hasStmtExecute() || this.getStmtExecute().isInitialized();
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
                    return result == null ? Type.FIND : result;
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
                public boolean hasFind() {
                    return (this.bitField0_ & 2) != 0;
                }

                @Override
                public MysqlxCrud.Find getFind() {
                    if (this.findBuilder_ == null) {
                        return this.find_ == null ? MysqlxCrud.Find.getDefaultInstance() : this.find_;
                    }
                    return (MysqlxCrud.Find)this.findBuilder_.getMessage();
                }

                public Builder setFind(MysqlxCrud.Find value) {
                    if (this.findBuilder_ == null) {
                        if (value == null) {
                            throw new NullPointerException();
                        }
                        this.find_ = value;
                        this.onChanged();
                    } else {
                        this.findBuilder_.setMessage((AbstractMessage)value);
                    }
                    this.bitField0_ |= 2;
                    return this;
                }

                public Builder setFind(MysqlxCrud.Find.Builder builderForValue) {
                    if (this.findBuilder_ == null) {
                        this.find_ = builderForValue.build();
                        this.onChanged();
                    } else {
                        this.findBuilder_.setMessage((AbstractMessage)builderForValue.build());
                    }
                    this.bitField0_ |= 2;
                    return this;
                }

                public Builder mergeFind(MysqlxCrud.Find value) {
                    if (this.findBuilder_ == null) {
                        this.find_ = (this.bitField0_ & 2) != 0 && this.find_ != null && this.find_ != MysqlxCrud.Find.getDefaultInstance() ? MysqlxCrud.Find.newBuilder(this.find_).mergeFrom(value).buildPartial() : value;
                        this.onChanged();
                    } else {
                        this.findBuilder_.mergeFrom((AbstractMessage)value);
                    }
                    this.bitField0_ |= 2;
                    return this;
                }

                public Builder clearFind() {
                    if (this.findBuilder_ == null) {
                        this.find_ = null;
                        this.onChanged();
                    } else {
                        this.findBuilder_.clear();
                    }
                    this.bitField0_ &= 0xFFFFFFFD;
                    return this;
                }

                public MysqlxCrud.Find.Builder getFindBuilder() {
                    this.bitField0_ |= 2;
                    this.onChanged();
                    return (MysqlxCrud.Find.Builder)this.getFindFieldBuilder().getBuilder();
                }

                @Override
                public MysqlxCrud.FindOrBuilder getFindOrBuilder() {
                    if (this.findBuilder_ != null) {
                        return (MysqlxCrud.FindOrBuilder)this.findBuilder_.getMessageOrBuilder();
                    }
                    return this.find_ == null ? MysqlxCrud.Find.getDefaultInstance() : this.find_;
                }

                private SingleFieldBuilderV3<MysqlxCrud.Find, MysqlxCrud.Find.Builder, MysqlxCrud.FindOrBuilder> getFindFieldBuilder() {
                    if (this.findBuilder_ == null) {
                        this.findBuilder_ = new SingleFieldBuilderV3((AbstractMessage)this.getFind(), (AbstractMessage.BuilderParent)this.getParentForChildren(), this.isClean());
                        this.find_ = null;
                    }
                    return this.findBuilder_;
                }

                @Override
                public boolean hasInsert() {
                    return (this.bitField0_ & 4) != 0;
                }

                @Override
                public MysqlxCrud.Insert getInsert() {
                    if (this.insertBuilder_ == null) {
                        return this.insert_ == null ? MysqlxCrud.Insert.getDefaultInstance() : this.insert_;
                    }
                    return (MysqlxCrud.Insert)this.insertBuilder_.getMessage();
                }

                public Builder setInsert(MysqlxCrud.Insert value) {
                    if (this.insertBuilder_ == null) {
                        if (value == null) {
                            throw new NullPointerException();
                        }
                        this.insert_ = value;
                        this.onChanged();
                    } else {
                        this.insertBuilder_.setMessage((AbstractMessage)value);
                    }
                    this.bitField0_ |= 4;
                    return this;
                }

                public Builder setInsert(MysqlxCrud.Insert.Builder builderForValue) {
                    if (this.insertBuilder_ == null) {
                        this.insert_ = builderForValue.build();
                        this.onChanged();
                    } else {
                        this.insertBuilder_.setMessage((AbstractMessage)builderForValue.build());
                    }
                    this.bitField0_ |= 4;
                    return this;
                }

                public Builder mergeInsert(MysqlxCrud.Insert value) {
                    if (this.insertBuilder_ == null) {
                        this.insert_ = (this.bitField0_ & 4) != 0 && this.insert_ != null && this.insert_ != MysqlxCrud.Insert.getDefaultInstance() ? MysqlxCrud.Insert.newBuilder(this.insert_).mergeFrom(value).buildPartial() : value;
                        this.onChanged();
                    } else {
                        this.insertBuilder_.mergeFrom((AbstractMessage)value);
                    }
                    this.bitField0_ |= 4;
                    return this;
                }

                public Builder clearInsert() {
                    if (this.insertBuilder_ == null) {
                        this.insert_ = null;
                        this.onChanged();
                    } else {
                        this.insertBuilder_.clear();
                    }
                    this.bitField0_ &= 0xFFFFFFFB;
                    return this;
                }

                public MysqlxCrud.Insert.Builder getInsertBuilder() {
                    this.bitField0_ |= 4;
                    this.onChanged();
                    return (MysqlxCrud.Insert.Builder)this.getInsertFieldBuilder().getBuilder();
                }

                @Override
                public MysqlxCrud.InsertOrBuilder getInsertOrBuilder() {
                    if (this.insertBuilder_ != null) {
                        return (MysqlxCrud.InsertOrBuilder)this.insertBuilder_.getMessageOrBuilder();
                    }
                    return this.insert_ == null ? MysqlxCrud.Insert.getDefaultInstance() : this.insert_;
                }

                private SingleFieldBuilderV3<MysqlxCrud.Insert, MysqlxCrud.Insert.Builder, MysqlxCrud.InsertOrBuilder> getInsertFieldBuilder() {
                    if (this.insertBuilder_ == null) {
                        this.insertBuilder_ = new SingleFieldBuilderV3((AbstractMessage)this.getInsert(), (AbstractMessage.BuilderParent)this.getParentForChildren(), this.isClean());
                        this.insert_ = null;
                    }
                    return this.insertBuilder_;
                }

                @Override
                public boolean hasUpdate() {
                    return (this.bitField0_ & 8) != 0;
                }

                @Override
                public MysqlxCrud.Update getUpdate() {
                    if (this.updateBuilder_ == null) {
                        return this.update_ == null ? MysqlxCrud.Update.getDefaultInstance() : this.update_;
                    }
                    return (MysqlxCrud.Update)this.updateBuilder_.getMessage();
                }

                public Builder setUpdate(MysqlxCrud.Update value) {
                    if (this.updateBuilder_ == null) {
                        if (value == null) {
                            throw new NullPointerException();
                        }
                        this.update_ = value;
                        this.onChanged();
                    } else {
                        this.updateBuilder_.setMessage((AbstractMessage)value);
                    }
                    this.bitField0_ |= 8;
                    return this;
                }

                public Builder setUpdate(MysqlxCrud.Update.Builder builderForValue) {
                    if (this.updateBuilder_ == null) {
                        this.update_ = builderForValue.build();
                        this.onChanged();
                    } else {
                        this.updateBuilder_.setMessage((AbstractMessage)builderForValue.build());
                    }
                    this.bitField0_ |= 8;
                    return this;
                }

                public Builder mergeUpdate(MysqlxCrud.Update value) {
                    if (this.updateBuilder_ == null) {
                        this.update_ = (this.bitField0_ & 8) != 0 && this.update_ != null && this.update_ != MysqlxCrud.Update.getDefaultInstance() ? MysqlxCrud.Update.newBuilder(this.update_).mergeFrom(value).buildPartial() : value;
                        this.onChanged();
                    } else {
                        this.updateBuilder_.mergeFrom((AbstractMessage)value);
                    }
                    this.bitField0_ |= 8;
                    return this;
                }

                public Builder clearUpdate() {
                    if (this.updateBuilder_ == null) {
                        this.update_ = null;
                        this.onChanged();
                    } else {
                        this.updateBuilder_.clear();
                    }
                    this.bitField0_ &= 0xFFFFFFF7;
                    return this;
                }

                public MysqlxCrud.Update.Builder getUpdateBuilder() {
                    this.bitField0_ |= 8;
                    this.onChanged();
                    return (MysqlxCrud.Update.Builder)this.getUpdateFieldBuilder().getBuilder();
                }

                @Override
                public MysqlxCrud.UpdateOrBuilder getUpdateOrBuilder() {
                    if (this.updateBuilder_ != null) {
                        return (MysqlxCrud.UpdateOrBuilder)this.updateBuilder_.getMessageOrBuilder();
                    }
                    return this.update_ == null ? MysqlxCrud.Update.getDefaultInstance() : this.update_;
                }

                private SingleFieldBuilderV3<MysqlxCrud.Update, MysqlxCrud.Update.Builder, MysqlxCrud.UpdateOrBuilder> getUpdateFieldBuilder() {
                    if (this.updateBuilder_ == null) {
                        this.updateBuilder_ = new SingleFieldBuilderV3((AbstractMessage)this.getUpdate(), (AbstractMessage.BuilderParent)this.getParentForChildren(), this.isClean());
                        this.update_ = null;
                    }
                    return this.updateBuilder_;
                }

                @Override
                public boolean hasDelete() {
                    return (this.bitField0_ & 0x10) != 0;
                }

                @Override
                public MysqlxCrud.Delete getDelete() {
                    if (this.deleteBuilder_ == null) {
                        return this.delete_ == null ? MysqlxCrud.Delete.getDefaultInstance() : this.delete_;
                    }
                    return (MysqlxCrud.Delete)this.deleteBuilder_.getMessage();
                }

                public Builder setDelete(MysqlxCrud.Delete value) {
                    if (this.deleteBuilder_ == null) {
                        if (value == null) {
                            throw new NullPointerException();
                        }
                        this.delete_ = value;
                        this.onChanged();
                    } else {
                        this.deleteBuilder_.setMessage((AbstractMessage)value);
                    }
                    this.bitField0_ |= 0x10;
                    return this;
                }

                public Builder setDelete(MysqlxCrud.Delete.Builder builderForValue) {
                    if (this.deleteBuilder_ == null) {
                        this.delete_ = builderForValue.build();
                        this.onChanged();
                    } else {
                        this.deleteBuilder_.setMessage((AbstractMessage)builderForValue.build());
                    }
                    this.bitField0_ |= 0x10;
                    return this;
                }

                public Builder mergeDelete(MysqlxCrud.Delete value) {
                    if (this.deleteBuilder_ == null) {
                        this.delete_ = (this.bitField0_ & 0x10) != 0 && this.delete_ != null && this.delete_ != MysqlxCrud.Delete.getDefaultInstance() ? MysqlxCrud.Delete.newBuilder(this.delete_).mergeFrom(value).buildPartial() : value;
                        this.onChanged();
                    } else {
                        this.deleteBuilder_.mergeFrom((AbstractMessage)value);
                    }
                    this.bitField0_ |= 0x10;
                    return this;
                }

                public Builder clearDelete() {
                    if (this.deleteBuilder_ == null) {
                        this.delete_ = null;
                        this.onChanged();
                    } else {
                        this.deleteBuilder_.clear();
                    }
                    this.bitField0_ &= 0xFFFFFFEF;
                    return this;
                }

                public MysqlxCrud.Delete.Builder getDeleteBuilder() {
                    this.bitField0_ |= 0x10;
                    this.onChanged();
                    return (MysqlxCrud.Delete.Builder)this.getDeleteFieldBuilder().getBuilder();
                }

                @Override
                public MysqlxCrud.DeleteOrBuilder getDeleteOrBuilder() {
                    if (this.deleteBuilder_ != null) {
                        return (MysqlxCrud.DeleteOrBuilder)this.deleteBuilder_.getMessageOrBuilder();
                    }
                    return this.delete_ == null ? MysqlxCrud.Delete.getDefaultInstance() : this.delete_;
                }

                private SingleFieldBuilderV3<MysqlxCrud.Delete, MysqlxCrud.Delete.Builder, MysqlxCrud.DeleteOrBuilder> getDeleteFieldBuilder() {
                    if (this.deleteBuilder_ == null) {
                        this.deleteBuilder_ = new SingleFieldBuilderV3((AbstractMessage)this.getDelete(), (AbstractMessage.BuilderParent)this.getParentForChildren(), this.isClean());
                        this.delete_ = null;
                    }
                    return this.deleteBuilder_;
                }

                @Override
                public boolean hasStmtExecute() {
                    return (this.bitField0_ & 0x20) != 0;
                }

                @Override
                public MysqlxSql.StmtExecute getStmtExecute() {
                    if (this.stmtExecuteBuilder_ == null) {
                        return this.stmtExecute_ == null ? MysqlxSql.StmtExecute.getDefaultInstance() : this.stmtExecute_;
                    }
                    return (MysqlxSql.StmtExecute)this.stmtExecuteBuilder_.getMessage();
                }

                public Builder setStmtExecute(MysqlxSql.StmtExecute value) {
                    if (this.stmtExecuteBuilder_ == null) {
                        if (value == null) {
                            throw new NullPointerException();
                        }
                        this.stmtExecute_ = value;
                        this.onChanged();
                    } else {
                        this.stmtExecuteBuilder_.setMessage((AbstractMessage)value);
                    }
                    this.bitField0_ |= 0x20;
                    return this;
                }

                public Builder setStmtExecute(MysqlxSql.StmtExecute.Builder builderForValue) {
                    if (this.stmtExecuteBuilder_ == null) {
                        this.stmtExecute_ = builderForValue.build();
                        this.onChanged();
                    } else {
                        this.stmtExecuteBuilder_.setMessage((AbstractMessage)builderForValue.build());
                    }
                    this.bitField0_ |= 0x20;
                    return this;
                }

                public Builder mergeStmtExecute(MysqlxSql.StmtExecute value) {
                    if (this.stmtExecuteBuilder_ == null) {
                        this.stmtExecute_ = (this.bitField0_ & 0x20) != 0 && this.stmtExecute_ != null && this.stmtExecute_ != MysqlxSql.StmtExecute.getDefaultInstance() ? MysqlxSql.StmtExecute.newBuilder(this.stmtExecute_).mergeFrom(value).buildPartial() : value;
                        this.onChanged();
                    } else {
                        this.stmtExecuteBuilder_.mergeFrom((AbstractMessage)value);
                    }
                    this.bitField0_ |= 0x20;
                    return this;
                }

                public Builder clearStmtExecute() {
                    if (this.stmtExecuteBuilder_ == null) {
                        this.stmtExecute_ = null;
                        this.onChanged();
                    } else {
                        this.stmtExecuteBuilder_.clear();
                    }
                    this.bitField0_ &= 0xFFFFFFDF;
                    return this;
                }

                public MysqlxSql.StmtExecute.Builder getStmtExecuteBuilder() {
                    this.bitField0_ |= 0x20;
                    this.onChanged();
                    return (MysqlxSql.StmtExecute.Builder)this.getStmtExecuteFieldBuilder().getBuilder();
                }

                @Override
                public MysqlxSql.StmtExecuteOrBuilder getStmtExecuteOrBuilder() {
                    if (this.stmtExecuteBuilder_ != null) {
                        return (MysqlxSql.StmtExecuteOrBuilder)this.stmtExecuteBuilder_.getMessageOrBuilder();
                    }
                    return this.stmtExecute_ == null ? MysqlxSql.StmtExecute.getDefaultInstance() : this.stmtExecute_;
                }

                private SingleFieldBuilderV3<MysqlxSql.StmtExecute, MysqlxSql.StmtExecute.Builder, MysqlxSql.StmtExecuteOrBuilder> getStmtExecuteFieldBuilder() {
                    if (this.stmtExecuteBuilder_ == null) {
                        this.stmtExecuteBuilder_ = new SingleFieldBuilderV3((AbstractMessage)this.getStmtExecute(), (AbstractMessage.BuilderParent)this.getParentForChildren(), this.isClean());
                        this.stmtExecute_ = null;
                    }
                    return this.stmtExecuteBuilder_;
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
                FIND(0),
                INSERT(1),
                UPDATE(2),
                DELETE(4),
                STMT(5);

                public static final int FIND_VALUE = 0;
                public static final int INSERT_VALUE = 1;
                public static final int UPDATE_VALUE = 2;
                public static final int DELETE_VALUE = 4;
                public static final int STMT_VALUE = 5;
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
                            return FIND;
                        }
                        case 1: {
                            return INSERT;
                        }
                        case 2: {
                            return UPDATE;
                        }
                        case 4: {
                            return DELETE;
                        }
                        case 5: {
                            return STMT;
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

            public boolean hasFind();

            public MysqlxCrud.Find getFind();

            public MysqlxCrud.FindOrBuilder getFindOrBuilder();

            public boolean hasInsert();

            public MysqlxCrud.Insert getInsert();

            public MysqlxCrud.InsertOrBuilder getInsertOrBuilder();

            public boolean hasUpdate();

            public MysqlxCrud.Update getUpdate();

            public MysqlxCrud.UpdateOrBuilder getUpdateOrBuilder();

            public boolean hasDelete();

            public MysqlxCrud.Delete getDelete();

            public MysqlxCrud.DeleteOrBuilder getDeleteOrBuilder();

            public boolean hasStmtExecute();

            public MysqlxSql.StmtExecute getStmtExecute();

            public MysqlxSql.StmtExecuteOrBuilder getStmtExecuteOrBuilder();
        }
    }

    public static interface PrepareOrBuilder
    extends MessageOrBuilder {
        public boolean hasStmtId();

        public int getStmtId();

        public boolean hasStmt();

        public Prepare.OneOfMessage getStmt();

        public Prepare.OneOfMessageOrBuilder getStmtOrBuilder();
    }
}

