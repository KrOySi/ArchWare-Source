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
 *  com.google.protobuf.InvalidProtocolBufferException
 *  com.google.protobuf.Message
 *  com.google.protobuf.MessageLite
 *  com.google.protobuf.MessageOrBuilder
 *  com.google.protobuf.Parser
 *  com.google.protobuf.RepeatedFieldBuilderV3
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
import com.google.protobuf.RepeatedFieldBuilderV3;
import com.google.protobuf.UnknownFieldSet;
import com.mysql.cj.x.protobuf.Mysqlx;
import com.mysql.cj.x.protobuf.MysqlxDatatypes;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class MysqlxSql {
    private static final Descriptors.Descriptor internal_static_Mysqlx_Sql_StmtExecute_descriptor;
    private static final GeneratedMessageV3.FieldAccessorTable internal_static_Mysqlx_Sql_StmtExecute_fieldAccessorTable;
    private static final Descriptors.Descriptor internal_static_Mysqlx_Sql_StmtExecuteOk_descriptor;
    private static final GeneratedMessageV3.FieldAccessorTable internal_static_Mysqlx_Sql_StmtExecuteOk_fieldAccessorTable;
    private static Descriptors.FileDescriptor descriptor;

    private MysqlxSql() {
    }

    public static void registerAllExtensions(ExtensionRegistryLite registry) {
    }

    public static void registerAllExtensions(ExtensionRegistry registry) {
        MysqlxSql.registerAllExtensions((ExtensionRegistryLite)registry);
    }

    public static Descriptors.FileDescriptor getDescriptor() {
        return descriptor;
    }

    static {
        String[] descriptorData = new String[]{"\n\u0010mysqlx_sql.proto\u0012\nMysqlx.Sql\u001a\fmysqlx.proto\u001a\u0016mysqlx_datatypes.proto\"\u007f\n\u000bStmtExecute\u0012\u0016\n\tnamespace\u0018\u0003 \u0001(\t:\u0003sql\u0012\f\n\u0004stmt\u0018\u0001 \u0002(\f\u0012#\n\u0004args\u0018\u0002 \u0003(\u000b2\u0015.Mysqlx.Datatypes.Any\u0012\u001f\n\u0010compact_metadata\u0018\u0004 \u0001(\b:\u0005false:\u0004\u0088\u00ea0\f\"\u0015\n\rStmtExecuteOk:\u0004\u0090\u00ea0\u0011B\u0019\n\u0017com.mysql.cj.x.protobuf"};
        descriptor = Descriptors.FileDescriptor.internalBuildGeneratedFileFrom((String[])descriptorData, (Descriptors.FileDescriptor[])new Descriptors.FileDescriptor[]{Mysqlx.getDescriptor(), MysqlxDatatypes.getDescriptor()});
        internal_static_Mysqlx_Sql_StmtExecute_descriptor = (Descriptors.Descriptor)MysqlxSql.getDescriptor().getMessageTypes().get(0);
        internal_static_Mysqlx_Sql_StmtExecute_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(internal_static_Mysqlx_Sql_StmtExecute_descriptor, new String[]{"Namespace", "Stmt", "Args", "CompactMetadata"});
        internal_static_Mysqlx_Sql_StmtExecuteOk_descriptor = (Descriptors.Descriptor)MysqlxSql.getDescriptor().getMessageTypes().get(1);
        internal_static_Mysqlx_Sql_StmtExecuteOk_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(internal_static_Mysqlx_Sql_StmtExecuteOk_descriptor, new String[0]);
        ExtensionRegistry registry = ExtensionRegistry.newInstance();
        registry.add(Mysqlx.clientMessageId);
        registry.add(Mysqlx.serverMessageId);
        Descriptors.FileDescriptor.internalUpdateFileDescriptor((Descriptors.FileDescriptor)descriptor, (ExtensionRegistry)registry);
        Mysqlx.getDescriptor();
        MysqlxDatatypes.getDescriptor();
    }

    public static final class StmtExecuteOk
    extends GeneratedMessageV3
    implements StmtExecuteOkOrBuilder {
        private static final long serialVersionUID = 0L;
        private byte memoizedIsInitialized = (byte)-1;
        private static final StmtExecuteOk DEFAULT_INSTANCE = new StmtExecuteOk();
        @Deprecated
        public static final Parser<StmtExecuteOk> PARSER = new AbstractParser<StmtExecuteOk>(){

            public StmtExecuteOk parsePartialFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                return new StmtExecuteOk(input, extensionRegistry);
            }
        };

        private StmtExecuteOk(GeneratedMessageV3.Builder<?> builder) {
            super(builder);
        }

        private StmtExecuteOk() {
        }

        protected Object newInstance(GeneratedMessageV3.UnusedPrivateParameter unused) {
            return new StmtExecuteOk();
        }

        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        private StmtExecuteOk(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
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
            return internal_static_Mysqlx_Sql_StmtExecuteOk_descriptor;
        }

        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return internal_static_Mysqlx_Sql_StmtExecuteOk_fieldAccessorTable.ensureFieldAccessorsInitialized(StmtExecuteOk.class, Builder.class);
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
            if (!(obj instanceof StmtExecuteOk)) {
                return super.equals(obj);
            }
            StmtExecuteOk other = (StmtExecuteOk)obj;
            return this.unknownFields.equals((Object)other.unknownFields);
        }

        public int hashCode() {
            if (this.memoizedHashCode != 0) {
                return this.memoizedHashCode;
            }
            int hash = 41;
            hash = 19 * hash + StmtExecuteOk.getDescriptor().hashCode();
            this.memoizedHashCode = hash = 29 * hash + this.unknownFields.hashCode();
            return hash;
        }

        public static StmtExecuteOk parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
            return (StmtExecuteOk)PARSER.parseFrom(data);
        }

        public static StmtExecuteOk parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (StmtExecuteOk)PARSER.parseFrom(data, extensionRegistry);
        }

        public static StmtExecuteOk parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return (StmtExecuteOk)PARSER.parseFrom(data);
        }

        public static StmtExecuteOk parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (StmtExecuteOk)PARSER.parseFrom(data, extensionRegistry);
        }

        public static StmtExecuteOk parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return (StmtExecuteOk)PARSER.parseFrom(data);
        }

        public static StmtExecuteOk parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (StmtExecuteOk)PARSER.parseFrom(data, extensionRegistry);
        }

        public static StmtExecuteOk parseFrom(InputStream input) throws IOException {
            return (StmtExecuteOk)GeneratedMessageV3.parseWithIOException(PARSER, (InputStream)input);
        }

        public static StmtExecuteOk parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (StmtExecuteOk)GeneratedMessageV3.parseWithIOException(PARSER, (InputStream)input, (ExtensionRegistryLite)extensionRegistry);
        }

        public static StmtExecuteOk parseDelimitedFrom(InputStream input) throws IOException {
            return (StmtExecuteOk)GeneratedMessageV3.parseDelimitedWithIOException(PARSER, (InputStream)input);
        }

        public static StmtExecuteOk parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (StmtExecuteOk)GeneratedMessageV3.parseDelimitedWithIOException(PARSER, (InputStream)input, (ExtensionRegistryLite)extensionRegistry);
        }

        public static StmtExecuteOk parseFrom(CodedInputStream input) throws IOException {
            return (StmtExecuteOk)GeneratedMessageV3.parseWithIOException(PARSER, (CodedInputStream)input);
        }

        public static StmtExecuteOk parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (StmtExecuteOk)GeneratedMessageV3.parseWithIOException(PARSER, (CodedInputStream)input, (ExtensionRegistryLite)extensionRegistry);
        }

        public Builder newBuilderForType() {
            return StmtExecuteOk.newBuilder();
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(StmtExecuteOk prototype) {
            return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
        }

        public Builder toBuilder() {
            return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
        }

        protected Builder newBuilderForType(GeneratedMessageV3.BuilderParent parent) {
            Builder builder = new Builder(parent);
            return builder;
        }

        public static StmtExecuteOk getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<StmtExecuteOk> parser() {
            return PARSER;
        }

        public Parser<StmtExecuteOk> getParserForType() {
            return PARSER;
        }

        public StmtExecuteOk getDefaultInstanceForType() {
            return DEFAULT_INSTANCE;
        }

        public static final class Builder
        extends GeneratedMessageV3.Builder<Builder>
        implements StmtExecuteOkOrBuilder {
            public static final Descriptors.Descriptor getDescriptor() {
                return internal_static_Mysqlx_Sql_StmtExecuteOk_descriptor;
            }

            protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                return internal_static_Mysqlx_Sql_StmtExecuteOk_fieldAccessorTable.ensureFieldAccessorsInitialized(StmtExecuteOk.class, Builder.class);
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
                return internal_static_Mysqlx_Sql_StmtExecuteOk_descriptor;
            }

            public StmtExecuteOk getDefaultInstanceForType() {
                return StmtExecuteOk.getDefaultInstance();
            }

            public StmtExecuteOk build() {
                StmtExecuteOk result = this.buildPartial();
                if (!result.isInitialized()) {
                    throw Builder.newUninitializedMessageException((Message)result);
                }
                return result;
            }

            public StmtExecuteOk buildPartial() {
                StmtExecuteOk result = new StmtExecuteOk(this);
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
                if (other instanceof StmtExecuteOk) {
                    return this.mergeFrom((StmtExecuteOk)other);
                }
                super.mergeFrom(other);
                return this;
            }

            public Builder mergeFrom(StmtExecuteOk other) {
                if (other == StmtExecuteOk.getDefaultInstance()) {
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
                StmtExecuteOk parsedMessage = null;
                try {
                    parsedMessage = (StmtExecuteOk)PARSER.parsePartialFrom(input, extensionRegistry);
                }
                catch (InvalidProtocolBufferException e) {
                    parsedMessage = (StmtExecuteOk)e.getUnfinishedMessage();
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

    public static interface StmtExecuteOkOrBuilder
    extends MessageOrBuilder {
    }

    public static final class StmtExecute
    extends GeneratedMessageV3
    implements StmtExecuteOrBuilder {
        private static final long serialVersionUID = 0L;
        private int bitField0_;
        public static final int NAMESPACE_FIELD_NUMBER = 3;
        private volatile Object namespace_;
        public static final int STMT_FIELD_NUMBER = 1;
        private ByteString stmt_;
        public static final int ARGS_FIELD_NUMBER = 2;
        private List<MysqlxDatatypes.Any> args_;
        public static final int COMPACT_METADATA_FIELD_NUMBER = 4;
        private boolean compactMetadata_;
        private byte memoizedIsInitialized = (byte)-1;
        private static final StmtExecute DEFAULT_INSTANCE = new StmtExecute();
        @Deprecated
        public static final Parser<StmtExecute> PARSER = new AbstractParser<StmtExecute>(){

            public StmtExecute parsePartialFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                return new StmtExecute(input, extensionRegistry);
            }
        };

        private StmtExecute(GeneratedMessageV3.Builder<?> builder) {
            super(builder);
        }

        private StmtExecute() {
            this.namespace_ = "sql";
            this.stmt_ = ByteString.EMPTY;
            this.args_ = Collections.emptyList();
        }

        protected Object newInstance(GeneratedMessageV3.UnusedPrivateParameter unused) {
            return new StmtExecute();
        }

        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        private StmtExecute(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            this();
            if (extensionRegistry == null) {
                throw new NullPointerException();
            }
            int mutable_bitField0_ = 0;
            UnknownFieldSet.Builder unknownFields = UnknownFieldSet.newBuilder();
            try {
                boolean done = false;
                block13: while (!done) {
                    int tag = input.readTag();
                    switch (tag) {
                        case 0: {
                            done = true;
                            continue block13;
                        }
                        case 10: {
                            this.bitField0_ |= 2;
                            this.stmt_ = input.readBytes();
                            continue block13;
                        }
                        case 18: {
                            if ((mutable_bitField0_ & 4) == 0) {
                                this.args_ = new ArrayList<MysqlxDatatypes.Any>();
                                mutable_bitField0_ |= 4;
                            }
                            this.args_.add((MysqlxDatatypes.Any)input.readMessage(MysqlxDatatypes.Any.PARSER, extensionRegistry));
                            continue block13;
                        }
                        case 26: {
                            ByteString bs = input.readBytes();
                            this.bitField0_ |= 1;
                            this.namespace_ = bs;
                            continue block13;
                        }
                        case 32: {
                            this.bitField0_ |= 4;
                            this.compactMetadata_ = input.readBool();
                            continue block13;
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
                if ((mutable_bitField0_ & 4) != 0) {
                    this.args_ = Collections.unmodifiableList(this.args_);
                }
                this.unknownFields = unknownFields.build();
                this.makeExtensionsImmutable();
            }
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return internal_static_Mysqlx_Sql_StmtExecute_descriptor;
        }

        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return internal_static_Mysqlx_Sql_StmtExecute_fieldAccessorTable.ensureFieldAccessorsInitialized(StmtExecute.class, Builder.class);
        }

        @Override
        public boolean hasNamespace() {
            return (this.bitField0_ & 1) != 0;
        }

        @Override
        public String getNamespace() {
            Object ref = this.namespace_;
            if (ref instanceof String) {
                return (String)ref;
            }
            ByteString bs = (ByteString)ref;
            String s = bs.toStringUtf8();
            if (bs.isValidUtf8()) {
                this.namespace_ = s;
            }
            return s;
        }

        @Override
        public ByteString getNamespaceBytes() {
            Object ref = this.namespace_;
            if (ref instanceof String) {
                ByteString b = ByteString.copyFromUtf8((String)((String)ref));
                this.namespace_ = b;
                return b;
            }
            return (ByteString)ref;
        }

        @Override
        public boolean hasStmt() {
            return (this.bitField0_ & 2) != 0;
        }

        @Override
        public ByteString getStmt() {
            return this.stmt_;
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
            return (this.bitField0_ & 4) != 0;
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
            if (!this.hasStmt()) {
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
            if ((this.bitField0_ & 2) != 0) {
                output.writeBytes(1, this.stmt_);
            }
            for (int i = 0; i < this.args_.size(); ++i) {
                output.writeMessage(2, (MessageLite)this.args_.get(i));
            }
            if ((this.bitField0_ & 1) != 0) {
                GeneratedMessageV3.writeString((CodedOutputStream)output, (int)3, (Object)this.namespace_);
            }
            if ((this.bitField0_ & 4) != 0) {
                output.writeBool(4, this.compactMetadata_);
            }
            this.unknownFields.writeTo(output);
        }

        public int getSerializedSize() {
            int size = this.memoizedSize;
            if (size != -1) {
                return size;
            }
            size = 0;
            if ((this.bitField0_ & 2) != 0) {
                size += CodedOutputStream.computeBytesSize((int)1, (ByteString)this.stmt_);
            }
            for (int i = 0; i < this.args_.size(); ++i) {
                size += CodedOutputStream.computeMessageSize((int)2, (MessageLite)((MessageLite)this.args_.get(i)));
            }
            if ((this.bitField0_ & 1) != 0) {
                size += GeneratedMessageV3.computeStringSize((int)3, (Object)this.namespace_);
            }
            if ((this.bitField0_ & 4) != 0) {
                size += CodedOutputStream.computeBoolSize((int)4, (boolean)this.compactMetadata_);
            }
            this.memoizedSize = size += this.unknownFields.getSerializedSize();
            return size;
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof StmtExecute)) {
                return super.equals(obj);
            }
            StmtExecute other = (StmtExecute)obj;
            if (this.hasNamespace() != other.hasNamespace()) {
                return false;
            }
            if (this.hasNamespace() && !this.getNamespace().equals(other.getNamespace())) {
                return false;
            }
            if (this.hasStmt() != other.hasStmt()) {
                return false;
            }
            if (this.hasStmt() && !this.getStmt().equals((Object)other.getStmt())) {
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
            hash = 19 * hash + StmtExecute.getDescriptor().hashCode();
            if (this.hasNamespace()) {
                hash = 37 * hash + 3;
                hash = 53 * hash + this.getNamespace().hashCode();
            }
            if (this.hasStmt()) {
                hash = 37 * hash + 1;
                hash = 53 * hash + this.getStmt().hashCode();
            }
            if (this.getArgsCount() > 0) {
                hash = 37 * hash + 2;
                hash = 53 * hash + this.getArgsList().hashCode();
            }
            if (this.hasCompactMetadata()) {
                hash = 37 * hash + 4;
                hash = 53 * hash + Internal.hashBoolean((boolean)this.getCompactMetadata());
            }
            this.memoizedHashCode = hash = 29 * hash + this.unknownFields.hashCode();
            return hash;
        }

        public static StmtExecute parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
            return (StmtExecute)PARSER.parseFrom(data);
        }

        public static StmtExecute parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (StmtExecute)PARSER.parseFrom(data, extensionRegistry);
        }

        public static StmtExecute parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return (StmtExecute)PARSER.parseFrom(data);
        }

        public static StmtExecute parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (StmtExecute)PARSER.parseFrom(data, extensionRegistry);
        }

        public static StmtExecute parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return (StmtExecute)PARSER.parseFrom(data);
        }

        public static StmtExecute parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (StmtExecute)PARSER.parseFrom(data, extensionRegistry);
        }

        public static StmtExecute parseFrom(InputStream input) throws IOException {
            return (StmtExecute)GeneratedMessageV3.parseWithIOException(PARSER, (InputStream)input);
        }

        public static StmtExecute parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (StmtExecute)GeneratedMessageV3.parseWithIOException(PARSER, (InputStream)input, (ExtensionRegistryLite)extensionRegistry);
        }

        public static StmtExecute parseDelimitedFrom(InputStream input) throws IOException {
            return (StmtExecute)GeneratedMessageV3.parseDelimitedWithIOException(PARSER, (InputStream)input);
        }

        public static StmtExecute parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (StmtExecute)GeneratedMessageV3.parseDelimitedWithIOException(PARSER, (InputStream)input, (ExtensionRegistryLite)extensionRegistry);
        }

        public static StmtExecute parseFrom(CodedInputStream input) throws IOException {
            return (StmtExecute)GeneratedMessageV3.parseWithIOException(PARSER, (CodedInputStream)input);
        }

        public static StmtExecute parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (StmtExecute)GeneratedMessageV3.parseWithIOException(PARSER, (CodedInputStream)input, (ExtensionRegistryLite)extensionRegistry);
        }

        public Builder newBuilderForType() {
            return StmtExecute.newBuilder();
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(StmtExecute prototype) {
            return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
        }

        public Builder toBuilder() {
            return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
        }

        protected Builder newBuilderForType(GeneratedMessageV3.BuilderParent parent) {
            Builder builder = new Builder(parent);
            return builder;
        }

        public static StmtExecute getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<StmtExecute> parser() {
            return PARSER;
        }

        public Parser<StmtExecute> getParserForType() {
            return PARSER;
        }

        public StmtExecute getDefaultInstanceForType() {
            return DEFAULT_INSTANCE;
        }

        public static final class Builder
        extends GeneratedMessageV3.Builder<Builder>
        implements StmtExecuteOrBuilder {
            private int bitField0_;
            private Object namespace_ = "sql";
            private ByteString stmt_ = ByteString.EMPTY;
            private List<MysqlxDatatypes.Any> args_ = Collections.emptyList();
            private RepeatedFieldBuilderV3<MysqlxDatatypes.Any, MysqlxDatatypes.Any.Builder, MysqlxDatatypes.AnyOrBuilder> argsBuilder_;
            private boolean compactMetadata_;

            public static final Descriptors.Descriptor getDescriptor() {
                return internal_static_Mysqlx_Sql_StmtExecute_descriptor;
            }

            protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                return internal_static_Mysqlx_Sql_StmtExecute_fieldAccessorTable.ensureFieldAccessorsInitialized(StmtExecute.class, Builder.class);
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
                this.namespace_ = "sql";
                this.bitField0_ &= 0xFFFFFFFE;
                this.stmt_ = ByteString.EMPTY;
                this.bitField0_ &= 0xFFFFFFFD;
                if (this.argsBuilder_ == null) {
                    this.args_ = Collections.emptyList();
                    this.bitField0_ &= 0xFFFFFFFB;
                } else {
                    this.argsBuilder_.clear();
                }
                this.compactMetadata_ = false;
                this.bitField0_ &= 0xFFFFFFF7;
                return this;
            }

            public Descriptors.Descriptor getDescriptorForType() {
                return internal_static_Mysqlx_Sql_StmtExecute_descriptor;
            }

            public StmtExecute getDefaultInstanceForType() {
                return StmtExecute.getDefaultInstance();
            }

            public StmtExecute build() {
                StmtExecute result = this.buildPartial();
                if (!result.isInitialized()) {
                    throw Builder.newUninitializedMessageException((Message)result);
                }
                return result;
            }

            public StmtExecute buildPartial() {
                StmtExecute result = new StmtExecute(this);
                int from_bitField0_ = this.bitField0_;
                int to_bitField0_ = 0;
                if ((from_bitField0_ & 1) != 0) {
                    to_bitField0_ |= 1;
                }
                result.namespace_ = this.namespace_;
                if ((from_bitField0_ & 2) != 0) {
                    to_bitField0_ |= 2;
                }
                result.stmt_ = this.stmt_;
                if (this.argsBuilder_ == null) {
                    if ((this.bitField0_ & 4) != 0) {
                        this.args_ = Collections.unmodifiableList(this.args_);
                        this.bitField0_ &= 0xFFFFFFFB;
                    }
                    result.args_ = this.args_;
                } else {
                    result.args_ = this.argsBuilder_.build();
                }
                if ((from_bitField0_ & 8) != 0) {
                    result.compactMetadata_ = this.compactMetadata_;
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
                if (other instanceof StmtExecute) {
                    return this.mergeFrom((StmtExecute)other);
                }
                super.mergeFrom(other);
                return this;
            }

            public Builder mergeFrom(StmtExecute other) {
                if (other == StmtExecute.getDefaultInstance()) {
                    return this;
                }
                if (other.hasNamespace()) {
                    this.bitField0_ |= 1;
                    this.namespace_ = other.namespace_;
                    this.onChanged();
                }
                if (other.hasStmt()) {
                    this.setStmt(other.getStmt());
                }
                if (this.argsBuilder_ == null) {
                    if (!other.args_.isEmpty()) {
                        if (this.args_.isEmpty()) {
                            this.args_ = other.args_;
                            this.bitField0_ &= 0xFFFFFFFB;
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
                        this.bitField0_ &= 0xFFFFFFFB;
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
                if (!this.hasStmt()) {
                    return false;
                }
                for (int i = 0; i < this.getArgsCount(); ++i) {
                    if (this.getArgs(i).isInitialized()) continue;
                    return false;
                }
                return true;
            }

            public Builder mergeFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                StmtExecute parsedMessage = null;
                try {
                    parsedMessage = (StmtExecute)PARSER.parsePartialFrom(input, extensionRegistry);
                }
                catch (InvalidProtocolBufferException e) {
                    parsedMessage = (StmtExecute)e.getUnfinishedMessage();
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
            public boolean hasNamespace() {
                return (this.bitField0_ & 1) != 0;
            }

            @Override
            public String getNamespace() {
                Object ref = this.namespace_;
                if (!(ref instanceof String)) {
                    ByteString bs = (ByteString)ref;
                    String s = bs.toStringUtf8();
                    if (bs.isValidUtf8()) {
                        this.namespace_ = s;
                    }
                    return s;
                }
                return (String)ref;
            }

            @Override
            public ByteString getNamespaceBytes() {
                Object ref = this.namespace_;
                if (ref instanceof String) {
                    ByteString b = ByteString.copyFromUtf8((String)((String)ref));
                    this.namespace_ = b;
                    return b;
                }
                return (ByteString)ref;
            }

            public Builder setNamespace(String value) {
                if (value == null) {
                    throw new NullPointerException();
                }
                this.bitField0_ |= 1;
                this.namespace_ = value;
                this.onChanged();
                return this;
            }

            public Builder clearNamespace() {
                this.bitField0_ &= 0xFFFFFFFE;
                this.namespace_ = StmtExecute.getDefaultInstance().getNamespace();
                this.onChanged();
                return this;
            }

            public Builder setNamespaceBytes(ByteString value) {
                if (value == null) {
                    throw new NullPointerException();
                }
                this.bitField0_ |= 1;
                this.namespace_ = value;
                this.onChanged();
                return this;
            }

            @Override
            public boolean hasStmt() {
                return (this.bitField0_ & 2) != 0;
            }

            @Override
            public ByteString getStmt() {
                return this.stmt_;
            }

            public Builder setStmt(ByteString value) {
                if (value == null) {
                    throw new NullPointerException();
                }
                this.bitField0_ |= 2;
                this.stmt_ = value;
                this.onChanged();
                return this;
            }

            public Builder clearStmt() {
                this.bitField0_ &= 0xFFFFFFFD;
                this.stmt_ = StmtExecute.getDefaultInstance().getStmt();
                this.onChanged();
                return this;
            }

            private void ensureArgsIsMutable() {
                if ((this.bitField0_ & 4) == 0) {
                    this.args_ = new ArrayList<MysqlxDatatypes.Any>(this.args_);
                    this.bitField0_ |= 4;
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
                    this.bitField0_ &= 0xFFFFFFFB;
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
                    this.argsBuilder_ = new RepeatedFieldBuilderV3(this.args_, (this.bitField0_ & 4) != 0, (AbstractMessage.BuilderParent)this.getParentForChildren(), this.isClean());
                    this.args_ = null;
                }
                return this.argsBuilder_;
            }

            @Override
            public boolean hasCompactMetadata() {
                return (this.bitField0_ & 8) != 0;
            }

            @Override
            public boolean getCompactMetadata() {
                return this.compactMetadata_;
            }

            public Builder setCompactMetadata(boolean value) {
                this.bitField0_ |= 8;
                this.compactMetadata_ = value;
                this.onChanged();
                return this;
            }

            public Builder clearCompactMetadata() {
                this.bitField0_ &= 0xFFFFFFF7;
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

    public static interface StmtExecuteOrBuilder
    extends MessageOrBuilder {
        public boolean hasNamespace();

        public String getNamespace();

        public ByteString getNamespaceBytes();

        public boolean hasStmt();

        public ByteString getStmt();

        public List<MysqlxDatatypes.Any> getArgsList();

        public MysqlxDatatypes.Any getArgs(int var1);

        public int getArgsCount();

        public List<? extends MysqlxDatatypes.AnyOrBuilder> getArgsOrBuilderList();

        public MysqlxDatatypes.AnyOrBuilder getArgsOrBuilder(int var1);

        public boolean hasCompactMetadata();

        public boolean getCompactMetadata();
    }
}

