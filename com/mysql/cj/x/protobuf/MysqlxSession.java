/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
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
 *  com.google.protobuf.UnknownFieldSet
 *  com.google.protobuf.UnknownFieldSet$Builder
 */
package com.mysql.cj.x.protobuf;

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
import com.google.protobuf.UnknownFieldSet;
import com.mysql.cj.x.protobuf.Mysqlx;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

public final class MysqlxSession {
    private static final Descriptors.Descriptor internal_static_Mysqlx_Session_AuthenticateStart_descriptor;
    private static final GeneratedMessageV3.FieldAccessorTable internal_static_Mysqlx_Session_AuthenticateStart_fieldAccessorTable;
    private static final Descriptors.Descriptor internal_static_Mysqlx_Session_AuthenticateContinue_descriptor;
    private static final GeneratedMessageV3.FieldAccessorTable internal_static_Mysqlx_Session_AuthenticateContinue_fieldAccessorTable;
    private static final Descriptors.Descriptor internal_static_Mysqlx_Session_AuthenticateOk_descriptor;
    private static final GeneratedMessageV3.FieldAccessorTable internal_static_Mysqlx_Session_AuthenticateOk_fieldAccessorTable;
    private static final Descriptors.Descriptor internal_static_Mysqlx_Session_Reset_descriptor;
    private static final GeneratedMessageV3.FieldAccessorTable internal_static_Mysqlx_Session_Reset_fieldAccessorTable;
    private static final Descriptors.Descriptor internal_static_Mysqlx_Session_Close_descriptor;
    private static final GeneratedMessageV3.FieldAccessorTable internal_static_Mysqlx_Session_Close_fieldAccessorTable;
    private static Descriptors.FileDescriptor descriptor;

    private MysqlxSession() {
    }

    public static void registerAllExtensions(ExtensionRegistryLite registry) {
    }

    public static void registerAllExtensions(ExtensionRegistry registry) {
        MysqlxSession.registerAllExtensions((ExtensionRegistryLite)registry);
    }

    public static Descriptors.FileDescriptor getDescriptor() {
        return descriptor;
    }

    static {
        String[] descriptorData = new String[]{"\n\u0014mysqlx_session.proto\u0012\u000eMysqlx.Session\u001a\fmysqlx.proto\"Y\n\u0011AuthenticateStart\u0012\u0011\n\tmech_name\u0018\u0001 \u0002(\t\u0012\u0011\n\tauth_data\u0018\u0002 \u0001(\f\u0012\u0018\n\u0010initial_response\u0018\u0003 \u0001(\f:\u0004\u0088\u00ea0\u0004\"3\n\u0014AuthenticateContinue\u0012\u0011\n\tauth_data\u0018\u0001 \u0002(\f:\b\u0090\u00ea0\u0003\u0088\u00ea0\u0005\")\n\u000eAuthenticateOk\u0012\u0011\n\tauth_data\u0018\u0001 \u0001(\f:\u0004\u0090\u00ea0\u0004\"'\n\u0005Reset\u0012\u0018\n\tkeep_open\u0018\u0001 \u0001(\b:\u0005false:\u0004\u0088\u00ea0\u0006\"\r\n\u0005Close:\u0004\u0088\u00ea0\u0007B\u0019\n\u0017com.mysql.cj.x.protobuf"};
        descriptor = Descriptors.FileDescriptor.internalBuildGeneratedFileFrom((String[])descriptorData, (Descriptors.FileDescriptor[])new Descriptors.FileDescriptor[]{Mysqlx.getDescriptor()});
        internal_static_Mysqlx_Session_AuthenticateStart_descriptor = (Descriptors.Descriptor)MysqlxSession.getDescriptor().getMessageTypes().get(0);
        internal_static_Mysqlx_Session_AuthenticateStart_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(internal_static_Mysqlx_Session_AuthenticateStart_descriptor, new String[]{"MechName", "AuthData", "InitialResponse"});
        internal_static_Mysqlx_Session_AuthenticateContinue_descriptor = (Descriptors.Descriptor)MysqlxSession.getDescriptor().getMessageTypes().get(1);
        internal_static_Mysqlx_Session_AuthenticateContinue_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(internal_static_Mysqlx_Session_AuthenticateContinue_descriptor, new String[]{"AuthData"});
        internal_static_Mysqlx_Session_AuthenticateOk_descriptor = (Descriptors.Descriptor)MysqlxSession.getDescriptor().getMessageTypes().get(2);
        internal_static_Mysqlx_Session_AuthenticateOk_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(internal_static_Mysqlx_Session_AuthenticateOk_descriptor, new String[]{"AuthData"});
        internal_static_Mysqlx_Session_Reset_descriptor = (Descriptors.Descriptor)MysqlxSession.getDescriptor().getMessageTypes().get(3);
        internal_static_Mysqlx_Session_Reset_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(internal_static_Mysqlx_Session_Reset_descriptor, new String[]{"KeepOpen"});
        internal_static_Mysqlx_Session_Close_descriptor = (Descriptors.Descriptor)MysqlxSession.getDescriptor().getMessageTypes().get(4);
        internal_static_Mysqlx_Session_Close_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(internal_static_Mysqlx_Session_Close_descriptor, new String[0]);
        ExtensionRegistry registry = ExtensionRegistry.newInstance();
        registry.add(Mysqlx.clientMessageId);
        registry.add(Mysqlx.serverMessageId);
        Descriptors.FileDescriptor.internalUpdateFileDescriptor((Descriptors.FileDescriptor)descriptor, (ExtensionRegistry)registry);
        Mysqlx.getDescriptor();
    }

    public static final class Close
    extends GeneratedMessageV3
    implements CloseOrBuilder {
        private static final long serialVersionUID = 0L;
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
            return internal_static_Mysqlx_Session_Close_descriptor;
        }

        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return internal_static_Mysqlx_Session_Close_fieldAccessorTable.ensureFieldAccessorsInitialized(Close.class, Builder.class);
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
            if (!(obj instanceof Close)) {
                return super.equals(obj);
            }
            Close other = (Close)obj;
            return this.unknownFields.equals((Object)other.unknownFields);
        }

        public int hashCode() {
            if (this.memoizedHashCode != 0) {
                return this.memoizedHashCode;
            }
            int hash = 41;
            hash = 19 * hash + Close.getDescriptor().hashCode();
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
            public static final Descriptors.Descriptor getDescriptor() {
                return internal_static_Mysqlx_Session_Close_descriptor;
            }

            protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                return internal_static_Mysqlx_Session_Close_fieldAccessorTable.ensureFieldAccessorsInitialized(Close.class, Builder.class);
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
                return internal_static_Mysqlx_Session_Close_descriptor;
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
                this.mergeUnknownFields(other.unknownFields);
                this.onChanged();
                return this;
            }

            public final boolean isInitialized() {
                return true;
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
    }

    public static final class Reset
    extends GeneratedMessageV3
    implements ResetOrBuilder {
        private static final long serialVersionUID = 0L;
        private int bitField0_;
        public static final int KEEP_OPEN_FIELD_NUMBER = 1;
        private boolean keepOpen_;
        private byte memoizedIsInitialized = (byte)-1;
        private static final Reset DEFAULT_INSTANCE = new Reset();
        @Deprecated
        public static final Parser<Reset> PARSER = new AbstractParser<Reset>(){

            public Reset parsePartialFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                return new Reset(input, extensionRegistry);
            }
        };

        private Reset(GeneratedMessageV3.Builder<?> builder) {
            super(builder);
        }

        private Reset() {
        }

        protected Object newInstance(GeneratedMessageV3.UnusedPrivateParameter unused) {
            return new Reset();
        }

        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        private Reset(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
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
                            this.keepOpen_ = input.readBool();
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
            return internal_static_Mysqlx_Session_Reset_descriptor;
        }

        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return internal_static_Mysqlx_Session_Reset_fieldAccessorTable.ensureFieldAccessorsInitialized(Reset.class, Builder.class);
        }

        @Override
        public boolean hasKeepOpen() {
            return (this.bitField0_ & 1) != 0;
        }

        @Override
        public boolean getKeepOpen() {
            return this.keepOpen_;
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
            if ((this.bitField0_ & 1) != 0) {
                output.writeBool(1, this.keepOpen_);
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
                size += CodedOutputStream.computeBoolSize((int)1, (boolean)this.keepOpen_);
            }
            this.memoizedSize = size += this.unknownFields.getSerializedSize();
            return size;
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof Reset)) {
                return super.equals(obj);
            }
            Reset other = (Reset)obj;
            if (this.hasKeepOpen() != other.hasKeepOpen()) {
                return false;
            }
            if (this.hasKeepOpen() && this.getKeepOpen() != other.getKeepOpen()) {
                return false;
            }
            return this.unknownFields.equals((Object)other.unknownFields);
        }

        public int hashCode() {
            if (this.memoizedHashCode != 0) {
                return this.memoizedHashCode;
            }
            int hash = 41;
            hash = 19 * hash + Reset.getDescriptor().hashCode();
            if (this.hasKeepOpen()) {
                hash = 37 * hash + 1;
                hash = 53 * hash + Internal.hashBoolean((boolean)this.getKeepOpen());
            }
            this.memoizedHashCode = hash = 29 * hash + this.unknownFields.hashCode();
            return hash;
        }

        public static Reset parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
            return (Reset)PARSER.parseFrom(data);
        }

        public static Reset parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (Reset)PARSER.parseFrom(data, extensionRegistry);
        }

        public static Reset parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return (Reset)PARSER.parseFrom(data);
        }

        public static Reset parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (Reset)PARSER.parseFrom(data, extensionRegistry);
        }

        public static Reset parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return (Reset)PARSER.parseFrom(data);
        }

        public static Reset parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (Reset)PARSER.parseFrom(data, extensionRegistry);
        }

        public static Reset parseFrom(InputStream input) throws IOException {
            return (Reset)GeneratedMessageV3.parseWithIOException(PARSER, (InputStream)input);
        }

        public static Reset parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (Reset)GeneratedMessageV3.parseWithIOException(PARSER, (InputStream)input, (ExtensionRegistryLite)extensionRegistry);
        }

        public static Reset parseDelimitedFrom(InputStream input) throws IOException {
            return (Reset)GeneratedMessageV3.parseDelimitedWithIOException(PARSER, (InputStream)input);
        }

        public static Reset parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (Reset)GeneratedMessageV3.parseDelimitedWithIOException(PARSER, (InputStream)input, (ExtensionRegistryLite)extensionRegistry);
        }

        public static Reset parseFrom(CodedInputStream input) throws IOException {
            return (Reset)GeneratedMessageV3.parseWithIOException(PARSER, (CodedInputStream)input);
        }

        public static Reset parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (Reset)GeneratedMessageV3.parseWithIOException(PARSER, (CodedInputStream)input, (ExtensionRegistryLite)extensionRegistry);
        }

        public Builder newBuilderForType() {
            return Reset.newBuilder();
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(Reset prototype) {
            return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
        }

        public Builder toBuilder() {
            return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
        }

        protected Builder newBuilderForType(GeneratedMessageV3.BuilderParent parent) {
            Builder builder = new Builder(parent);
            return builder;
        }

        public static Reset getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<Reset> parser() {
            return PARSER;
        }

        public Parser<Reset> getParserForType() {
            return PARSER;
        }

        public Reset getDefaultInstanceForType() {
            return DEFAULT_INSTANCE;
        }

        public static final class Builder
        extends GeneratedMessageV3.Builder<Builder>
        implements ResetOrBuilder {
            private int bitField0_;
            private boolean keepOpen_;

            public static final Descriptors.Descriptor getDescriptor() {
                return internal_static_Mysqlx_Session_Reset_descriptor;
            }

            protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                return internal_static_Mysqlx_Session_Reset_fieldAccessorTable.ensureFieldAccessorsInitialized(Reset.class, Builder.class);
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
                this.keepOpen_ = false;
                this.bitField0_ &= 0xFFFFFFFE;
                return this;
            }

            public Descriptors.Descriptor getDescriptorForType() {
                return internal_static_Mysqlx_Session_Reset_descriptor;
            }

            public Reset getDefaultInstanceForType() {
                return Reset.getDefaultInstance();
            }

            public Reset build() {
                Reset result = this.buildPartial();
                if (!result.isInitialized()) {
                    throw Builder.newUninitializedMessageException((Message)result);
                }
                return result;
            }

            public Reset buildPartial() {
                Reset result = new Reset(this);
                int from_bitField0_ = this.bitField0_;
                int to_bitField0_ = 0;
                if ((from_bitField0_ & 1) != 0) {
                    result.keepOpen_ = this.keepOpen_;
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
                if (other instanceof Reset) {
                    return this.mergeFrom((Reset)other);
                }
                super.mergeFrom(other);
                return this;
            }

            public Builder mergeFrom(Reset other) {
                if (other == Reset.getDefaultInstance()) {
                    return this;
                }
                if (other.hasKeepOpen()) {
                    this.setKeepOpen(other.getKeepOpen());
                }
                this.mergeUnknownFields(other.unknownFields);
                this.onChanged();
                return this;
            }

            public final boolean isInitialized() {
                return true;
            }

            public Builder mergeFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                Reset parsedMessage = null;
                try {
                    parsedMessage = (Reset)PARSER.parsePartialFrom(input, extensionRegistry);
                }
                catch (InvalidProtocolBufferException e) {
                    parsedMessage = (Reset)e.getUnfinishedMessage();
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
            public boolean hasKeepOpen() {
                return (this.bitField0_ & 1) != 0;
            }

            @Override
            public boolean getKeepOpen() {
                return this.keepOpen_;
            }

            public Builder setKeepOpen(boolean value) {
                this.bitField0_ |= 1;
                this.keepOpen_ = value;
                this.onChanged();
                return this;
            }

            public Builder clearKeepOpen() {
                this.bitField0_ &= 0xFFFFFFFE;
                this.keepOpen_ = false;
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

    public static interface ResetOrBuilder
    extends MessageOrBuilder {
        public boolean hasKeepOpen();

        public boolean getKeepOpen();
    }

    public static final class AuthenticateOk
    extends GeneratedMessageV3
    implements AuthenticateOkOrBuilder {
        private static final long serialVersionUID = 0L;
        private int bitField0_;
        public static final int AUTH_DATA_FIELD_NUMBER = 1;
        private ByteString authData_;
        private byte memoizedIsInitialized = (byte)-1;
        private static final AuthenticateOk DEFAULT_INSTANCE = new AuthenticateOk();
        @Deprecated
        public static final Parser<AuthenticateOk> PARSER = new AbstractParser<AuthenticateOk>(){

            public AuthenticateOk parsePartialFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                return new AuthenticateOk(input, extensionRegistry);
            }
        };

        private AuthenticateOk(GeneratedMessageV3.Builder<?> builder) {
            super(builder);
        }

        private AuthenticateOk() {
            this.authData_ = ByteString.EMPTY;
        }

        protected Object newInstance(GeneratedMessageV3.UnusedPrivateParameter unused) {
            return new AuthenticateOk();
        }

        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        private AuthenticateOk(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
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
                            this.bitField0_ |= 1;
                            this.authData_ = input.readBytes();
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
            return internal_static_Mysqlx_Session_AuthenticateOk_descriptor;
        }

        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return internal_static_Mysqlx_Session_AuthenticateOk_fieldAccessorTable.ensureFieldAccessorsInitialized(AuthenticateOk.class, Builder.class);
        }

        @Override
        public boolean hasAuthData() {
            return (this.bitField0_ & 1) != 0;
        }

        @Override
        public ByteString getAuthData() {
            return this.authData_;
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
            if ((this.bitField0_ & 1) != 0) {
                output.writeBytes(1, this.authData_);
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
                size += CodedOutputStream.computeBytesSize((int)1, (ByteString)this.authData_);
            }
            this.memoizedSize = size += this.unknownFields.getSerializedSize();
            return size;
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof AuthenticateOk)) {
                return super.equals(obj);
            }
            AuthenticateOk other = (AuthenticateOk)obj;
            if (this.hasAuthData() != other.hasAuthData()) {
                return false;
            }
            if (this.hasAuthData() && !this.getAuthData().equals((Object)other.getAuthData())) {
                return false;
            }
            return this.unknownFields.equals((Object)other.unknownFields);
        }

        public int hashCode() {
            if (this.memoizedHashCode != 0) {
                return this.memoizedHashCode;
            }
            int hash = 41;
            hash = 19 * hash + AuthenticateOk.getDescriptor().hashCode();
            if (this.hasAuthData()) {
                hash = 37 * hash + 1;
                hash = 53 * hash + this.getAuthData().hashCode();
            }
            this.memoizedHashCode = hash = 29 * hash + this.unknownFields.hashCode();
            return hash;
        }

        public static AuthenticateOk parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
            return (AuthenticateOk)PARSER.parseFrom(data);
        }

        public static AuthenticateOk parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (AuthenticateOk)PARSER.parseFrom(data, extensionRegistry);
        }

        public static AuthenticateOk parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return (AuthenticateOk)PARSER.parseFrom(data);
        }

        public static AuthenticateOk parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (AuthenticateOk)PARSER.parseFrom(data, extensionRegistry);
        }

        public static AuthenticateOk parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return (AuthenticateOk)PARSER.parseFrom(data);
        }

        public static AuthenticateOk parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (AuthenticateOk)PARSER.parseFrom(data, extensionRegistry);
        }

        public static AuthenticateOk parseFrom(InputStream input) throws IOException {
            return (AuthenticateOk)GeneratedMessageV3.parseWithIOException(PARSER, (InputStream)input);
        }

        public static AuthenticateOk parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (AuthenticateOk)GeneratedMessageV3.parseWithIOException(PARSER, (InputStream)input, (ExtensionRegistryLite)extensionRegistry);
        }

        public static AuthenticateOk parseDelimitedFrom(InputStream input) throws IOException {
            return (AuthenticateOk)GeneratedMessageV3.parseDelimitedWithIOException(PARSER, (InputStream)input);
        }

        public static AuthenticateOk parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (AuthenticateOk)GeneratedMessageV3.parseDelimitedWithIOException(PARSER, (InputStream)input, (ExtensionRegistryLite)extensionRegistry);
        }

        public static AuthenticateOk parseFrom(CodedInputStream input) throws IOException {
            return (AuthenticateOk)GeneratedMessageV3.parseWithIOException(PARSER, (CodedInputStream)input);
        }

        public static AuthenticateOk parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (AuthenticateOk)GeneratedMessageV3.parseWithIOException(PARSER, (CodedInputStream)input, (ExtensionRegistryLite)extensionRegistry);
        }

        public Builder newBuilderForType() {
            return AuthenticateOk.newBuilder();
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(AuthenticateOk prototype) {
            return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
        }

        public Builder toBuilder() {
            return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
        }

        protected Builder newBuilderForType(GeneratedMessageV3.BuilderParent parent) {
            Builder builder = new Builder(parent);
            return builder;
        }

        public static AuthenticateOk getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<AuthenticateOk> parser() {
            return PARSER;
        }

        public Parser<AuthenticateOk> getParserForType() {
            return PARSER;
        }

        public AuthenticateOk getDefaultInstanceForType() {
            return DEFAULT_INSTANCE;
        }

        public static final class Builder
        extends GeneratedMessageV3.Builder<Builder>
        implements AuthenticateOkOrBuilder {
            private int bitField0_;
            private ByteString authData_ = ByteString.EMPTY;

            public static final Descriptors.Descriptor getDescriptor() {
                return internal_static_Mysqlx_Session_AuthenticateOk_descriptor;
            }

            protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                return internal_static_Mysqlx_Session_AuthenticateOk_fieldAccessorTable.ensureFieldAccessorsInitialized(AuthenticateOk.class, Builder.class);
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
                this.authData_ = ByteString.EMPTY;
                this.bitField0_ &= 0xFFFFFFFE;
                return this;
            }

            public Descriptors.Descriptor getDescriptorForType() {
                return internal_static_Mysqlx_Session_AuthenticateOk_descriptor;
            }

            public AuthenticateOk getDefaultInstanceForType() {
                return AuthenticateOk.getDefaultInstance();
            }

            public AuthenticateOk build() {
                AuthenticateOk result = this.buildPartial();
                if (!result.isInitialized()) {
                    throw Builder.newUninitializedMessageException((Message)result);
                }
                return result;
            }

            public AuthenticateOk buildPartial() {
                AuthenticateOk result = new AuthenticateOk(this);
                int from_bitField0_ = this.bitField0_;
                int to_bitField0_ = 0;
                if ((from_bitField0_ & 1) != 0) {
                    to_bitField0_ |= 1;
                }
                result.authData_ = this.authData_;
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
                if (other instanceof AuthenticateOk) {
                    return this.mergeFrom((AuthenticateOk)other);
                }
                super.mergeFrom(other);
                return this;
            }

            public Builder mergeFrom(AuthenticateOk other) {
                if (other == AuthenticateOk.getDefaultInstance()) {
                    return this;
                }
                if (other.hasAuthData()) {
                    this.setAuthData(other.getAuthData());
                }
                this.mergeUnknownFields(other.unknownFields);
                this.onChanged();
                return this;
            }

            public final boolean isInitialized() {
                return true;
            }

            public Builder mergeFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                AuthenticateOk parsedMessage = null;
                try {
                    parsedMessage = (AuthenticateOk)PARSER.parsePartialFrom(input, extensionRegistry);
                }
                catch (InvalidProtocolBufferException e) {
                    parsedMessage = (AuthenticateOk)e.getUnfinishedMessage();
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
            public boolean hasAuthData() {
                return (this.bitField0_ & 1) != 0;
            }

            @Override
            public ByteString getAuthData() {
                return this.authData_;
            }

            public Builder setAuthData(ByteString value) {
                if (value == null) {
                    throw new NullPointerException();
                }
                this.bitField0_ |= 1;
                this.authData_ = value;
                this.onChanged();
                return this;
            }

            public Builder clearAuthData() {
                this.bitField0_ &= 0xFFFFFFFE;
                this.authData_ = AuthenticateOk.getDefaultInstance().getAuthData();
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

    public static interface AuthenticateOkOrBuilder
    extends MessageOrBuilder {
        public boolean hasAuthData();

        public ByteString getAuthData();
    }

    public static final class AuthenticateContinue
    extends GeneratedMessageV3
    implements AuthenticateContinueOrBuilder {
        private static final long serialVersionUID = 0L;
        private int bitField0_;
        public static final int AUTH_DATA_FIELD_NUMBER = 1;
        private ByteString authData_;
        private byte memoizedIsInitialized = (byte)-1;
        private static final AuthenticateContinue DEFAULT_INSTANCE = new AuthenticateContinue();
        @Deprecated
        public static final Parser<AuthenticateContinue> PARSER = new AbstractParser<AuthenticateContinue>(){

            public AuthenticateContinue parsePartialFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                return new AuthenticateContinue(input, extensionRegistry);
            }
        };

        private AuthenticateContinue(GeneratedMessageV3.Builder<?> builder) {
            super(builder);
        }

        private AuthenticateContinue() {
            this.authData_ = ByteString.EMPTY;
        }

        protected Object newInstance(GeneratedMessageV3.UnusedPrivateParameter unused) {
            return new AuthenticateContinue();
        }

        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        private AuthenticateContinue(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
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
                            this.bitField0_ |= 1;
                            this.authData_ = input.readBytes();
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
            return internal_static_Mysqlx_Session_AuthenticateContinue_descriptor;
        }

        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return internal_static_Mysqlx_Session_AuthenticateContinue_fieldAccessorTable.ensureFieldAccessorsInitialized(AuthenticateContinue.class, Builder.class);
        }

        @Override
        public boolean hasAuthData() {
            return (this.bitField0_ & 1) != 0;
        }

        @Override
        public ByteString getAuthData() {
            return this.authData_;
        }

        public final boolean isInitialized() {
            byte isInitialized = this.memoizedIsInitialized;
            if (isInitialized == 1) {
                return true;
            }
            if (isInitialized == 0) {
                return false;
            }
            if (!this.hasAuthData()) {
                this.memoizedIsInitialized = 0;
                return false;
            }
            this.memoizedIsInitialized = 1;
            return true;
        }

        public void writeTo(CodedOutputStream output) throws IOException {
            if ((this.bitField0_ & 1) != 0) {
                output.writeBytes(1, this.authData_);
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
                size += CodedOutputStream.computeBytesSize((int)1, (ByteString)this.authData_);
            }
            this.memoizedSize = size += this.unknownFields.getSerializedSize();
            return size;
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof AuthenticateContinue)) {
                return super.equals(obj);
            }
            AuthenticateContinue other = (AuthenticateContinue)obj;
            if (this.hasAuthData() != other.hasAuthData()) {
                return false;
            }
            if (this.hasAuthData() && !this.getAuthData().equals((Object)other.getAuthData())) {
                return false;
            }
            return this.unknownFields.equals((Object)other.unknownFields);
        }

        public int hashCode() {
            if (this.memoizedHashCode != 0) {
                return this.memoizedHashCode;
            }
            int hash = 41;
            hash = 19 * hash + AuthenticateContinue.getDescriptor().hashCode();
            if (this.hasAuthData()) {
                hash = 37 * hash + 1;
                hash = 53 * hash + this.getAuthData().hashCode();
            }
            this.memoizedHashCode = hash = 29 * hash + this.unknownFields.hashCode();
            return hash;
        }

        public static AuthenticateContinue parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
            return (AuthenticateContinue)PARSER.parseFrom(data);
        }

        public static AuthenticateContinue parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (AuthenticateContinue)PARSER.parseFrom(data, extensionRegistry);
        }

        public static AuthenticateContinue parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return (AuthenticateContinue)PARSER.parseFrom(data);
        }

        public static AuthenticateContinue parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (AuthenticateContinue)PARSER.parseFrom(data, extensionRegistry);
        }

        public static AuthenticateContinue parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return (AuthenticateContinue)PARSER.parseFrom(data);
        }

        public static AuthenticateContinue parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (AuthenticateContinue)PARSER.parseFrom(data, extensionRegistry);
        }

        public static AuthenticateContinue parseFrom(InputStream input) throws IOException {
            return (AuthenticateContinue)GeneratedMessageV3.parseWithIOException(PARSER, (InputStream)input);
        }

        public static AuthenticateContinue parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (AuthenticateContinue)GeneratedMessageV3.parseWithIOException(PARSER, (InputStream)input, (ExtensionRegistryLite)extensionRegistry);
        }

        public static AuthenticateContinue parseDelimitedFrom(InputStream input) throws IOException {
            return (AuthenticateContinue)GeneratedMessageV3.parseDelimitedWithIOException(PARSER, (InputStream)input);
        }

        public static AuthenticateContinue parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (AuthenticateContinue)GeneratedMessageV3.parseDelimitedWithIOException(PARSER, (InputStream)input, (ExtensionRegistryLite)extensionRegistry);
        }

        public static AuthenticateContinue parseFrom(CodedInputStream input) throws IOException {
            return (AuthenticateContinue)GeneratedMessageV3.parseWithIOException(PARSER, (CodedInputStream)input);
        }

        public static AuthenticateContinue parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (AuthenticateContinue)GeneratedMessageV3.parseWithIOException(PARSER, (CodedInputStream)input, (ExtensionRegistryLite)extensionRegistry);
        }

        public Builder newBuilderForType() {
            return AuthenticateContinue.newBuilder();
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(AuthenticateContinue prototype) {
            return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
        }

        public Builder toBuilder() {
            return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
        }

        protected Builder newBuilderForType(GeneratedMessageV3.BuilderParent parent) {
            Builder builder = new Builder(parent);
            return builder;
        }

        public static AuthenticateContinue getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<AuthenticateContinue> parser() {
            return PARSER;
        }

        public Parser<AuthenticateContinue> getParserForType() {
            return PARSER;
        }

        public AuthenticateContinue getDefaultInstanceForType() {
            return DEFAULT_INSTANCE;
        }

        public static final class Builder
        extends GeneratedMessageV3.Builder<Builder>
        implements AuthenticateContinueOrBuilder {
            private int bitField0_;
            private ByteString authData_ = ByteString.EMPTY;

            public static final Descriptors.Descriptor getDescriptor() {
                return internal_static_Mysqlx_Session_AuthenticateContinue_descriptor;
            }

            protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                return internal_static_Mysqlx_Session_AuthenticateContinue_fieldAccessorTable.ensureFieldAccessorsInitialized(AuthenticateContinue.class, Builder.class);
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
                this.authData_ = ByteString.EMPTY;
                this.bitField0_ &= 0xFFFFFFFE;
                return this;
            }

            public Descriptors.Descriptor getDescriptorForType() {
                return internal_static_Mysqlx_Session_AuthenticateContinue_descriptor;
            }

            public AuthenticateContinue getDefaultInstanceForType() {
                return AuthenticateContinue.getDefaultInstance();
            }

            public AuthenticateContinue build() {
                AuthenticateContinue result = this.buildPartial();
                if (!result.isInitialized()) {
                    throw Builder.newUninitializedMessageException((Message)result);
                }
                return result;
            }

            public AuthenticateContinue buildPartial() {
                AuthenticateContinue result = new AuthenticateContinue(this);
                int from_bitField0_ = this.bitField0_;
                int to_bitField0_ = 0;
                if ((from_bitField0_ & 1) != 0) {
                    to_bitField0_ |= 1;
                }
                result.authData_ = this.authData_;
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
                if (other instanceof AuthenticateContinue) {
                    return this.mergeFrom((AuthenticateContinue)other);
                }
                super.mergeFrom(other);
                return this;
            }

            public Builder mergeFrom(AuthenticateContinue other) {
                if (other == AuthenticateContinue.getDefaultInstance()) {
                    return this;
                }
                if (other.hasAuthData()) {
                    this.setAuthData(other.getAuthData());
                }
                this.mergeUnknownFields(other.unknownFields);
                this.onChanged();
                return this;
            }

            public final boolean isInitialized() {
                return this.hasAuthData();
            }

            public Builder mergeFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                AuthenticateContinue parsedMessage = null;
                try {
                    parsedMessage = (AuthenticateContinue)PARSER.parsePartialFrom(input, extensionRegistry);
                }
                catch (InvalidProtocolBufferException e) {
                    parsedMessage = (AuthenticateContinue)e.getUnfinishedMessage();
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
            public boolean hasAuthData() {
                return (this.bitField0_ & 1) != 0;
            }

            @Override
            public ByteString getAuthData() {
                return this.authData_;
            }

            public Builder setAuthData(ByteString value) {
                if (value == null) {
                    throw new NullPointerException();
                }
                this.bitField0_ |= 1;
                this.authData_ = value;
                this.onChanged();
                return this;
            }

            public Builder clearAuthData() {
                this.bitField0_ &= 0xFFFFFFFE;
                this.authData_ = AuthenticateContinue.getDefaultInstance().getAuthData();
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

    public static interface AuthenticateContinueOrBuilder
    extends MessageOrBuilder {
        public boolean hasAuthData();

        public ByteString getAuthData();
    }

    public static final class AuthenticateStart
    extends GeneratedMessageV3
    implements AuthenticateStartOrBuilder {
        private static final long serialVersionUID = 0L;
        private int bitField0_;
        public static final int MECH_NAME_FIELD_NUMBER = 1;
        private volatile Object mechName_;
        public static final int AUTH_DATA_FIELD_NUMBER = 2;
        private ByteString authData_;
        public static final int INITIAL_RESPONSE_FIELD_NUMBER = 3;
        private ByteString initialResponse_;
        private byte memoizedIsInitialized = (byte)-1;
        private static final AuthenticateStart DEFAULT_INSTANCE = new AuthenticateStart();
        @Deprecated
        public static final Parser<AuthenticateStart> PARSER = new AbstractParser<AuthenticateStart>(){

            public AuthenticateStart parsePartialFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                return new AuthenticateStart(input, extensionRegistry);
            }
        };

        private AuthenticateStart(GeneratedMessageV3.Builder<?> builder) {
            super(builder);
        }

        private AuthenticateStart() {
            this.mechName_ = "";
            this.authData_ = ByteString.EMPTY;
            this.initialResponse_ = ByteString.EMPTY;
        }

        protected Object newInstance(GeneratedMessageV3.UnusedPrivateParameter unused) {
            return new AuthenticateStart();
        }

        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        private AuthenticateStart(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
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
                        case 10: {
                            ByteString bs = input.readBytes();
                            this.bitField0_ |= 1;
                            this.mechName_ = bs;
                            continue block12;
                        }
                        case 18: {
                            this.bitField0_ |= 2;
                            this.authData_ = input.readBytes();
                            continue block12;
                        }
                        case 26: {
                            this.bitField0_ |= 4;
                            this.initialResponse_ = input.readBytes();
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
            return internal_static_Mysqlx_Session_AuthenticateStart_descriptor;
        }

        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return internal_static_Mysqlx_Session_AuthenticateStart_fieldAccessorTable.ensureFieldAccessorsInitialized(AuthenticateStart.class, Builder.class);
        }

        @Override
        public boolean hasMechName() {
            return (this.bitField0_ & 1) != 0;
        }

        @Override
        public String getMechName() {
            Object ref = this.mechName_;
            if (ref instanceof String) {
                return (String)ref;
            }
            ByteString bs = (ByteString)ref;
            String s = bs.toStringUtf8();
            if (bs.isValidUtf8()) {
                this.mechName_ = s;
            }
            return s;
        }

        @Override
        public ByteString getMechNameBytes() {
            Object ref = this.mechName_;
            if (ref instanceof String) {
                ByteString b = ByteString.copyFromUtf8((String)((String)ref));
                this.mechName_ = b;
                return b;
            }
            return (ByteString)ref;
        }

        @Override
        public boolean hasAuthData() {
            return (this.bitField0_ & 2) != 0;
        }

        @Override
        public ByteString getAuthData() {
            return this.authData_;
        }

        @Override
        public boolean hasInitialResponse() {
            return (this.bitField0_ & 4) != 0;
        }

        @Override
        public ByteString getInitialResponse() {
            return this.initialResponse_;
        }

        public final boolean isInitialized() {
            byte isInitialized = this.memoizedIsInitialized;
            if (isInitialized == 1) {
                return true;
            }
            if (isInitialized == 0) {
                return false;
            }
            if (!this.hasMechName()) {
                this.memoizedIsInitialized = 0;
                return false;
            }
            this.memoizedIsInitialized = 1;
            return true;
        }

        public void writeTo(CodedOutputStream output) throws IOException {
            if ((this.bitField0_ & 1) != 0) {
                GeneratedMessageV3.writeString((CodedOutputStream)output, (int)1, (Object)this.mechName_);
            }
            if ((this.bitField0_ & 2) != 0) {
                output.writeBytes(2, this.authData_);
            }
            if ((this.bitField0_ & 4) != 0) {
                output.writeBytes(3, this.initialResponse_);
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
                size += GeneratedMessageV3.computeStringSize((int)1, (Object)this.mechName_);
            }
            if ((this.bitField0_ & 2) != 0) {
                size += CodedOutputStream.computeBytesSize((int)2, (ByteString)this.authData_);
            }
            if ((this.bitField0_ & 4) != 0) {
                size += CodedOutputStream.computeBytesSize((int)3, (ByteString)this.initialResponse_);
            }
            this.memoizedSize = size += this.unknownFields.getSerializedSize();
            return size;
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof AuthenticateStart)) {
                return super.equals(obj);
            }
            AuthenticateStart other = (AuthenticateStart)obj;
            if (this.hasMechName() != other.hasMechName()) {
                return false;
            }
            if (this.hasMechName() && !this.getMechName().equals(other.getMechName())) {
                return false;
            }
            if (this.hasAuthData() != other.hasAuthData()) {
                return false;
            }
            if (this.hasAuthData() && !this.getAuthData().equals((Object)other.getAuthData())) {
                return false;
            }
            if (this.hasInitialResponse() != other.hasInitialResponse()) {
                return false;
            }
            if (this.hasInitialResponse() && !this.getInitialResponse().equals((Object)other.getInitialResponse())) {
                return false;
            }
            return this.unknownFields.equals((Object)other.unknownFields);
        }

        public int hashCode() {
            if (this.memoizedHashCode != 0) {
                return this.memoizedHashCode;
            }
            int hash = 41;
            hash = 19 * hash + AuthenticateStart.getDescriptor().hashCode();
            if (this.hasMechName()) {
                hash = 37 * hash + 1;
                hash = 53 * hash + this.getMechName().hashCode();
            }
            if (this.hasAuthData()) {
                hash = 37 * hash + 2;
                hash = 53 * hash + this.getAuthData().hashCode();
            }
            if (this.hasInitialResponse()) {
                hash = 37 * hash + 3;
                hash = 53 * hash + this.getInitialResponse().hashCode();
            }
            this.memoizedHashCode = hash = 29 * hash + this.unknownFields.hashCode();
            return hash;
        }

        public static AuthenticateStart parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
            return (AuthenticateStart)PARSER.parseFrom(data);
        }

        public static AuthenticateStart parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (AuthenticateStart)PARSER.parseFrom(data, extensionRegistry);
        }

        public static AuthenticateStart parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return (AuthenticateStart)PARSER.parseFrom(data);
        }

        public static AuthenticateStart parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (AuthenticateStart)PARSER.parseFrom(data, extensionRegistry);
        }

        public static AuthenticateStart parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return (AuthenticateStart)PARSER.parseFrom(data);
        }

        public static AuthenticateStart parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (AuthenticateStart)PARSER.parseFrom(data, extensionRegistry);
        }

        public static AuthenticateStart parseFrom(InputStream input) throws IOException {
            return (AuthenticateStart)GeneratedMessageV3.parseWithIOException(PARSER, (InputStream)input);
        }

        public static AuthenticateStart parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (AuthenticateStart)GeneratedMessageV3.parseWithIOException(PARSER, (InputStream)input, (ExtensionRegistryLite)extensionRegistry);
        }

        public static AuthenticateStart parseDelimitedFrom(InputStream input) throws IOException {
            return (AuthenticateStart)GeneratedMessageV3.parseDelimitedWithIOException(PARSER, (InputStream)input);
        }

        public static AuthenticateStart parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (AuthenticateStart)GeneratedMessageV3.parseDelimitedWithIOException(PARSER, (InputStream)input, (ExtensionRegistryLite)extensionRegistry);
        }

        public static AuthenticateStart parseFrom(CodedInputStream input) throws IOException {
            return (AuthenticateStart)GeneratedMessageV3.parseWithIOException(PARSER, (CodedInputStream)input);
        }

        public static AuthenticateStart parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (AuthenticateStart)GeneratedMessageV3.parseWithIOException(PARSER, (CodedInputStream)input, (ExtensionRegistryLite)extensionRegistry);
        }

        public Builder newBuilderForType() {
            return AuthenticateStart.newBuilder();
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(AuthenticateStart prototype) {
            return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
        }

        public Builder toBuilder() {
            return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
        }

        protected Builder newBuilderForType(GeneratedMessageV3.BuilderParent parent) {
            Builder builder = new Builder(parent);
            return builder;
        }

        public static AuthenticateStart getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<AuthenticateStart> parser() {
            return PARSER;
        }

        public Parser<AuthenticateStart> getParserForType() {
            return PARSER;
        }

        public AuthenticateStart getDefaultInstanceForType() {
            return DEFAULT_INSTANCE;
        }

        public static final class Builder
        extends GeneratedMessageV3.Builder<Builder>
        implements AuthenticateStartOrBuilder {
            private int bitField0_;
            private Object mechName_ = "";
            private ByteString authData_ = ByteString.EMPTY;
            private ByteString initialResponse_ = ByteString.EMPTY;

            public static final Descriptors.Descriptor getDescriptor() {
                return internal_static_Mysqlx_Session_AuthenticateStart_descriptor;
            }

            protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                return internal_static_Mysqlx_Session_AuthenticateStart_fieldAccessorTable.ensureFieldAccessorsInitialized(AuthenticateStart.class, Builder.class);
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
                this.mechName_ = "";
                this.bitField0_ &= 0xFFFFFFFE;
                this.authData_ = ByteString.EMPTY;
                this.bitField0_ &= 0xFFFFFFFD;
                this.initialResponse_ = ByteString.EMPTY;
                this.bitField0_ &= 0xFFFFFFFB;
                return this;
            }

            public Descriptors.Descriptor getDescriptorForType() {
                return internal_static_Mysqlx_Session_AuthenticateStart_descriptor;
            }

            public AuthenticateStart getDefaultInstanceForType() {
                return AuthenticateStart.getDefaultInstance();
            }

            public AuthenticateStart build() {
                AuthenticateStart result = this.buildPartial();
                if (!result.isInitialized()) {
                    throw Builder.newUninitializedMessageException((Message)result);
                }
                return result;
            }

            public AuthenticateStart buildPartial() {
                AuthenticateStart result = new AuthenticateStart(this);
                int from_bitField0_ = this.bitField0_;
                int to_bitField0_ = 0;
                if ((from_bitField0_ & 1) != 0) {
                    to_bitField0_ |= 1;
                }
                result.mechName_ = this.mechName_;
                if ((from_bitField0_ & 2) != 0) {
                    to_bitField0_ |= 2;
                }
                result.authData_ = this.authData_;
                if ((from_bitField0_ & 4) != 0) {
                    to_bitField0_ |= 4;
                }
                result.initialResponse_ = this.initialResponse_;
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
                if (other instanceof AuthenticateStart) {
                    return this.mergeFrom((AuthenticateStart)other);
                }
                super.mergeFrom(other);
                return this;
            }

            public Builder mergeFrom(AuthenticateStart other) {
                if (other == AuthenticateStart.getDefaultInstance()) {
                    return this;
                }
                if (other.hasMechName()) {
                    this.bitField0_ |= 1;
                    this.mechName_ = other.mechName_;
                    this.onChanged();
                }
                if (other.hasAuthData()) {
                    this.setAuthData(other.getAuthData());
                }
                if (other.hasInitialResponse()) {
                    this.setInitialResponse(other.getInitialResponse());
                }
                this.mergeUnknownFields(other.unknownFields);
                this.onChanged();
                return this;
            }

            public final boolean isInitialized() {
                return this.hasMechName();
            }

            public Builder mergeFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                AuthenticateStart parsedMessage = null;
                try {
                    parsedMessage = (AuthenticateStart)PARSER.parsePartialFrom(input, extensionRegistry);
                }
                catch (InvalidProtocolBufferException e) {
                    parsedMessage = (AuthenticateStart)e.getUnfinishedMessage();
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
            public boolean hasMechName() {
                return (this.bitField0_ & 1) != 0;
            }

            @Override
            public String getMechName() {
                Object ref = this.mechName_;
                if (!(ref instanceof String)) {
                    ByteString bs = (ByteString)ref;
                    String s = bs.toStringUtf8();
                    if (bs.isValidUtf8()) {
                        this.mechName_ = s;
                    }
                    return s;
                }
                return (String)ref;
            }

            @Override
            public ByteString getMechNameBytes() {
                Object ref = this.mechName_;
                if (ref instanceof String) {
                    ByteString b = ByteString.copyFromUtf8((String)((String)ref));
                    this.mechName_ = b;
                    return b;
                }
                return (ByteString)ref;
            }

            public Builder setMechName(String value) {
                if (value == null) {
                    throw new NullPointerException();
                }
                this.bitField0_ |= 1;
                this.mechName_ = value;
                this.onChanged();
                return this;
            }

            public Builder clearMechName() {
                this.bitField0_ &= 0xFFFFFFFE;
                this.mechName_ = AuthenticateStart.getDefaultInstance().getMechName();
                this.onChanged();
                return this;
            }

            public Builder setMechNameBytes(ByteString value) {
                if (value == null) {
                    throw new NullPointerException();
                }
                this.bitField0_ |= 1;
                this.mechName_ = value;
                this.onChanged();
                return this;
            }

            @Override
            public boolean hasAuthData() {
                return (this.bitField0_ & 2) != 0;
            }

            @Override
            public ByteString getAuthData() {
                return this.authData_;
            }

            public Builder setAuthData(ByteString value) {
                if (value == null) {
                    throw new NullPointerException();
                }
                this.bitField0_ |= 2;
                this.authData_ = value;
                this.onChanged();
                return this;
            }

            public Builder clearAuthData() {
                this.bitField0_ &= 0xFFFFFFFD;
                this.authData_ = AuthenticateStart.getDefaultInstance().getAuthData();
                this.onChanged();
                return this;
            }

            @Override
            public boolean hasInitialResponse() {
                return (this.bitField0_ & 4) != 0;
            }

            @Override
            public ByteString getInitialResponse() {
                return this.initialResponse_;
            }

            public Builder setInitialResponse(ByteString value) {
                if (value == null) {
                    throw new NullPointerException();
                }
                this.bitField0_ |= 4;
                this.initialResponse_ = value;
                this.onChanged();
                return this;
            }

            public Builder clearInitialResponse() {
                this.bitField0_ &= 0xFFFFFFFB;
                this.initialResponse_ = AuthenticateStart.getDefaultInstance().getInitialResponse();
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

    public static interface AuthenticateStartOrBuilder
    extends MessageOrBuilder {
        public boolean hasMechName();

        public String getMechName();

        public ByteString getMechNameBytes();

        public boolean hasAuthData();

        public ByteString getAuthData();

        public boolean hasInitialResponse();

        public ByteString getInitialResponse();
    }
}

