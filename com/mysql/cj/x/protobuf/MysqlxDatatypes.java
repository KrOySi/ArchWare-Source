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
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class MysqlxDatatypes {
    private static final Descriptors.Descriptor internal_static_Mysqlx_Datatypes_Scalar_descriptor;
    private static final GeneratedMessageV3.FieldAccessorTable internal_static_Mysqlx_Datatypes_Scalar_fieldAccessorTable;
    private static final Descriptors.Descriptor internal_static_Mysqlx_Datatypes_Scalar_String_descriptor;
    private static final GeneratedMessageV3.FieldAccessorTable internal_static_Mysqlx_Datatypes_Scalar_String_fieldAccessorTable;
    private static final Descriptors.Descriptor internal_static_Mysqlx_Datatypes_Scalar_Octets_descriptor;
    private static final GeneratedMessageV3.FieldAccessorTable internal_static_Mysqlx_Datatypes_Scalar_Octets_fieldAccessorTable;
    private static final Descriptors.Descriptor internal_static_Mysqlx_Datatypes_Object_descriptor;
    private static final GeneratedMessageV3.FieldAccessorTable internal_static_Mysqlx_Datatypes_Object_fieldAccessorTable;
    private static final Descriptors.Descriptor internal_static_Mysqlx_Datatypes_Object_ObjectField_descriptor;
    private static final GeneratedMessageV3.FieldAccessorTable internal_static_Mysqlx_Datatypes_Object_ObjectField_fieldAccessorTable;
    private static final Descriptors.Descriptor internal_static_Mysqlx_Datatypes_Array_descriptor;
    private static final GeneratedMessageV3.FieldAccessorTable internal_static_Mysqlx_Datatypes_Array_fieldAccessorTable;
    private static final Descriptors.Descriptor internal_static_Mysqlx_Datatypes_Any_descriptor;
    private static final GeneratedMessageV3.FieldAccessorTable internal_static_Mysqlx_Datatypes_Any_fieldAccessorTable;
    private static Descriptors.FileDescriptor descriptor;

    private MysqlxDatatypes() {
    }

    public static void registerAllExtensions(ExtensionRegistryLite registry) {
    }

    public static void registerAllExtensions(ExtensionRegistry registry) {
        MysqlxDatatypes.registerAllExtensions((ExtensionRegistryLite)registry);
    }

    public static Descriptors.FileDescriptor getDescriptor() {
        return descriptor;
    }

    static {
        String[] descriptorData = new String[]{"\n\u0016mysqlx_datatypes.proto\u0012\u0010Mysqlx.Datatypes\"\u00c6\u0003\n\u0006Scalar\u0012+\n\u0004type\u0018\u0001 \u0002(\u000e2\u001d.Mysqlx.Datatypes.Scalar.Type\u0012\u0014\n\fv_signed_int\u0018\u0002 \u0001(\u0012\u0012\u0016\n\u000ev_unsigned_int\u0018\u0003 \u0001(\u0004\u00121\n\bv_octets\u0018\u0005 \u0001(\u000b2\u001f.Mysqlx.Datatypes.Scalar.Octets\u0012\u0010\n\bv_double\u0018\u0006 \u0001(\u0001\u0012\u000f\n\u0007v_float\u0018\u0007 \u0001(\u0002\u0012\u000e\n\u0006v_bool\u0018\b \u0001(\b\u00121\n\bv_string\u0018\t \u0001(\u000b2\u001f.Mysqlx.Datatypes.Scalar.String\u001a*\n\u0006String\u0012\r\n\u0005value\u0018\u0001 \u0002(\f\u0012\u0011\n\tcollation\u0018\u0002 \u0001(\u0004\u001a-\n\u0006Octets\u0012\r\n\u0005value\u0018\u0001 \u0002(\f\u0012\u0014\n\fcontent_type\u0018\u0002 \u0001(\r\"m\n\u0004Type\u0012\n\n\u0006V_SINT\u0010\u0001\u0012\n\n\u0006V_UINT\u0010\u0002\u0012\n\n\u0006V_NULL\u0010\u0003\u0012\f\n\bV_OCTETS\u0010\u0004\u0012\f\n\bV_DOUBLE\u0010\u0005\u0012\u000b\n\u0007V_FLOAT\u0010\u0006\u0012\n\n\u0006V_BOOL\u0010\u0007\u0012\f\n\bV_STRING\u0010\b\"}\n\u0006Object\u00121\n\u0003fld\u0018\u0001 \u0003(\u000b2$.Mysqlx.Datatypes.Object.ObjectField\u001a@\n\u000bObjectField\u0012\u000b\n\u0003key\u0018\u0001 \u0002(\t\u0012$\n\u0005value\u0018\u0002 \u0002(\u000b2\u0015.Mysqlx.Datatypes.Any\"-\n\u0005Array\u0012$\n\u0005value\u0018\u0001 \u0003(\u000b2\u0015.Mysqlx.Datatypes.Any\"\u00d3\u0001\n\u0003Any\u0012(\n\u0004type\u0018\u0001 \u0002(\u000e2\u001a.Mysqlx.Datatypes.Any.Type\u0012(\n\u0006scalar\u0018\u0002 \u0001(\u000b2\u0018.Mysqlx.Datatypes.Scalar\u0012%\n\u0003obj\u0018\u0003 \u0001(\u000b2\u0018.Mysqlx.Datatypes.Object\u0012&\n\u0005array\u0018\u0004 \u0001(\u000b2\u0017.Mysqlx.Datatypes.Array\")\n\u0004Type\u0012\n\n\u0006SCALAR\u0010\u0001\u0012\n\n\u0006OBJECT\u0010\u0002\u0012\t\n\u0005ARRAY\u0010\u0003B\u0019\n\u0017com.mysql.cj.x.protobuf"};
        descriptor = Descriptors.FileDescriptor.internalBuildGeneratedFileFrom((String[])descriptorData, (Descriptors.FileDescriptor[])new Descriptors.FileDescriptor[0]);
        internal_static_Mysqlx_Datatypes_Scalar_descriptor = (Descriptors.Descriptor)MysqlxDatatypes.getDescriptor().getMessageTypes().get(0);
        internal_static_Mysqlx_Datatypes_Scalar_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(internal_static_Mysqlx_Datatypes_Scalar_descriptor, new String[]{"Type", "VSignedInt", "VUnsignedInt", "VOctets", "VDouble", "VFloat", "VBool", "VString"});
        internal_static_Mysqlx_Datatypes_Scalar_String_descriptor = (Descriptors.Descriptor)internal_static_Mysqlx_Datatypes_Scalar_descriptor.getNestedTypes().get(0);
        internal_static_Mysqlx_Datatypes_Scalar_String_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(internal_static_Mysqlx_Datatypes_Scalar_String_descriptor, new String[]{"Value", "Collation"});
        internal_static_Mysqlx_Datatypes_Scalar_Octets_descriptor = (Descriptors.Descriptor)internal_static_Mysqlx_Datatypes_Scalar_descriptor.getNestedTypes().get(1);
        internal_static_Mysqlx_Datatypes_Scalar_Octets_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(internal_static_Mysqlx_Datatypes_Scalar_Octets_descriptor, new String[]{"Value", "ContentType"});
        internal_static_Mysqlx_Datatypes_Object_descriptor = (Descriptors.Descriptor)MysqlxDatatypes.getDescriptor().getMessageTypes().get(1);
        internal_static_Mysqlx_Datatypes_Object_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(internal_static_Mysqlx_Datatypes_Object_descriptor, new String[]{"Fld"});
        internal_static_Mysqlx_Datatypes_Object_ObjectField_descriptor = (Descriptors.Descriptor)internal_static_Mysqlx_Datatypes_Object_descriptor.getNestedTypes().get(0);
        internal_static_Mysqlx_Datatypes_Object_ObjectField_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(internal_static_Mysqlx_Datatypes_Object_ObjectField_descriptor, new String[]{"Key", "Value"});
        internal_static_Mysqlx_Datatypes_Array_descriptor = (Descriptors.Descriptor)MysqlxDatatypes.getDescriptor().getMessageTypes().get(2);
        internal_static_Mysqlx_Datatypes_Array_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(internal_static_Mysqlx_Datatypes_Array_descriptor, new String[]{"Value"});
        internal_static_Mysqlx_Datatypes_Any_descriptor = (Descriptors.Descriptor)MysqlxDatatypes.getDescriptor().getMessageTypes().get(3);
        internal_static_Mysqlx_Datatypes_Any_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(internal_static_Mysqlx_Datatypes_Any_descriptor, new String[]{"Type", "Scalar", "Obj", "Array"});
    }

    public static final class Any
    extends GeneratedMessageV3
    implements AnyOrBuilder {
        private static final long serialVersionUID = 0L;
        private int bitField0_;
        public static final int TYPE_FIELD_NUMBER = 1;
        private int type_;
        public static final int SCALAR_FIELD_NUMBER = 2;
        private Scalar scalar_;
        public static final int OBJ_FIELD_NUMBER = 3;
        private Object obj_;
        public static final int ARRAY_FIELD_NUMBER = 4;
        private Array array_;
        private byte memoizedIsInitialized = (byte)-1;
        private static final Any DEFAULT_INSTANCE = new Any();
        @Deprecated
        public static final Parser<Any> PARSER = new AbstractParser<Any>(){

            public Any parsePartialFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                return new Any(input, extensionRegistry);
            }
        };

        private Any(GeneratedMessageV3.Builder<?> builder) {
            super(builder);
        }

        private Any() {
            this.type_ = 1;
        }

        protected java.lang.Object newInstance(GeneratedMessageV3.UnusedPrivateParameter unused) {
            return new Any();
        }

        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        private Any(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            this();
            if (extensionRegistry == null) {
                throw new NullPointerException();
            }
            boolean mutable_bitField0_ = false;
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
                        case 8: {
                            int rawValue = input.readEnum();
                            Type value = Type.valueOf(rawValue);
                            if (value == null) {
                                unknownFields.mergeVarintField(1, rawValue);
                                continue block13;
                            }
                            this.bitField0_ |= 1;
                            this.type_ = rawValue;
                            continue block13;
                        }
                        case 18: {
                            Scalar.Builder subBuilder = null;
                            if ((this.bitField0_ & 2) != 0) {
                                subBuilder = this.scalar_.toBuilder();
                            }
                            this.scalar_ = (Scalar)input.readMessage(Scalar.PARSER, extensionRegistry);
                            if (subBuilder != null) {
                                subBuilder.mergeFrom(this.scalar_);
                                this.scalar_ = subBuilder.buildPartial();
                            }
                            this.bitField0_ |= 2;
                            continue block13;
                        }
                        case 26: {
                            Object.Builder subBuilder = null;
                            if ((this.bitField0_ & 4) != 0) {
                                subBuilder = this.obj_.toBuilder();
                            }
                            this.obj_ = (Object)input.readMessage(Object.PARSER, extensionRegistry);
                            if (subBuilder != null) {
                                subBuilder.mergeFrom(this.obj_);
                                this.obj_ = subBuilder.buildPartial();
                            }
                            this.bitField0_ |= 4;
                            continue block13;
                        }
                        case 34: {
                            Array.Builder subBuilder = null;
                            if ((this.bitField0_ & 8) != 0) {
                                subBuilder = this.array_.toBuilder();
                            }
                            this.array_ = (Array)input.readMessage(Array.PARSER, extensionRegistry);
                            if (subBuilder != null) {
                                subBuilder.mergeFrom(this.array_);
                                this.array_ = subBuilder.buildPartial();
                            }
                            this.bitField0_ |= 8;
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
                this.unknownFields = unknownFields.build();
                this.makeExtensionsImmutable();
            }
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return internal_static_Mysqlx_Datatypes_Any_descriptor;
        }

        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return internal_static_Mysqlx_Datatypes_Any_fieldAccessorTable.ensureFieldAccessorsInitialized(Any.class, Builder.class);
        }

        @Override
        public boolean hasType() {
            return (this.bitField0_ & 1) != 0;
        }

        @Override
        public Type getType() {
            Type result = Type.valueOf(this.type_);
            return result == null ? Type.SCALAR : result;
        }

        @Override
        public boolean hasScalar() {
            return (this.bitField0_ & 2) != 0;
        }

        @Override
        public Scalar getScalar() {
            return this.scalar_ == null ? Scalar.getDefaultInstance() : this.scalar_;
        }

        @Override
        public ScalarOrBuilder getScalarOrBuilder() {
            return this.scalar_ == null ? Scalar.getDefaultInstance() : this.scalar_;
        }

        @Override
        public boolean hasObj() {
            return (this.bitField0_ & 4) != 0;
        }

        @Override
        public Object getObj() {
            return this.obj_ == null ? Object.getDefaultInstance() : this.obj_;
        }

        @Override
        public ObjectOrBuilder getObjOrBuilder() {
            return this.obj_ == null ? Object.getDefaultInstance() : this.obj_;
        }

        @Override
        public boolean hasArray() {
            return (this.bitField0_ & 8) != 0;
        }

        @Override
        public Array getArray() {
            return this.array_ == null ? Array.getDefaultInstance() : this.array_;
        }

        @Override
        public ArrayOrBuilder getArrayOrBuilder() {
            return this.array_ == null ? Array.getDefaultInstance() : this.array_;
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
            if (this.hasScalar() && !this.getScalar().isInitialized()) {
                this.memoizedIsInitialized = 0;
                return false;
            }
            if (this.hasObj() && !this.getObj().isInitialized()) {
                this.memoizedIsInitialized = 0;
                return false;
            }
            if (this.hasArray() && !this.getArray().isInitialized()) {
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
                output.writeMessage(2, (MessageLite)this.getScalar());
            }
            if ((this.bitField0_ & 4) != 0) {
                output.writeMessage(3, (MessageLite)this.getObj());
            }
            if ((this.bitField0_ & 8) != 0) {
                output.writeMessage(4, (MessageLite)this.getArray());
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
                size += CodedOutputStream.computeMessageSize((int)2, (MessageLite)this.getScalar());
            }
            if ((this.bitField0_ & 4) != 0) {
                size += CodedOutputStream.computeMessageSize((int)3, (MessageLite)this.getObj());
            }
            if ((this.bitField0_ & 8) != 0) {
                size += CodedOutputStream.computeMessageSize((int)4, (MessageLite)this.getArray());
            }
            this.memoizedSize = size += this.unknownFields.getSerializedSize();
            return size;
        }

        public boolean equals(java.lang.Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof Any)) {
                return super.equals(obj);
            }
            Any other = (Any)obj;
            if (this.hasType() != other.hasType()) {
                return false;
            }
            if (this.hasType() && this.type_ != other.type_) {
                return false;
            }
            if (this.hasScalar() != other.hasScalar()) {
                return false;
            }
            if (this.hasScalar() && !this.getScalar().equals(other.getScalar())) {
                return false;
            }
            if (this.hasObj() != other.hasObj()) {
                return false;
            }
            if (this.hasObj() && !this.getObj().equals(other.getObj())) {
                return false;
            }
            if (this.hasArray() != other.hasArray()) {
                return false;
            }
            if (this.hasArray() && !this.getArray().equals(other.getArray())) {
                return false;
            }
            return this.unknownFields.equals((java.lang.Object)other.unknownFields);
        }

        public int hashCode() {
            if (this.memoizedHashCode != 0) {
                return this.memoizedHashCode;
            }
            int hash = 41;
            hash = 19 * hash + Any.getDescriptor().hashCode();
            if (this.hasType()) {
                hash = 37 * hash + 1;
                hash = 53 * hash + this.type_;
            }
            if (this.hasScalar()) {
                hash = 37 * hash + 2;
                hash = 53 * hash + this.getScalar().hashCode();
            }
            if (this.hasObj()) {
                hash = 37 * hash + 3;
                hash = 53 * hash + this.getObj().hashCode();
            }
            if (this.hasArray()) {
                hash = 37 * hash + 4;
                hash = 53 * hash + this.getArray().hashCode();
            }
            this.memoizedHashCode = hash = 29 * hash + this.unknownFields.hashCode();
            return hash;
        }

        public static Any parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
            return (Any)PARSER.parseFrom(data);
        }

        public static Any parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (Any)PARSER.parseFrom(data, extensionRegistry);
        }

        public static Any parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return (Any)PARSER.parseFrom(data);
        }

        public static Any parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (Any)PARSER.parseFrom(data, extensionRegistry);
        }

        public static Any parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return (Any)PARSER.parseFrom(data);
        }

        public static Any parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (Any)PARSER.parseFrom(data, extensionRegistry);
        }

        public static Any parseFrom(InputStream input) throws IOException {
            return (Any)GeneratedMessageV3.parseWithIOException(PARSER, (InputStream)input);
        }

        public static Any parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (Any)GeneratedMessageV3.parseWithIOException(PARSER, (InputStream)input, (ExtensionRegistryLite)extensionRegistry);
        }

        public static Any parseDelimitedFrom(InputStream input) throws IOException {
            return (Any)GeneratedMessageV3.parseDelimitedWithIOException(PARSER, (InputStream)input);
        }

        public static Any parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (Any)GeneratedMessageV3.parseDelimitedWithIOException(PARSER, (InputStream)input, (ExtensionRegistryLite)extensionRegistry);
        }

        public static Any parseFrom(CodedInputStream input) throws IOException {
            return (Any)GeneratedMessageV3.parseWithIOException(PARSER, (CodedInputStream)input);
        }

        public static Any parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (Any)GeneratedMessageV3.parseWithIOException(PARSER, (CodedInputStream)input, (ExtensionRegistryLite)extensionRegistry);
        }

        public Builder newBuilderForType() {
            return Any.newBuilder();
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(Any prototype) {
            return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
        }

        public Builder toBuilder() {
            return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
        }

        protected Builder newBuilderForType(GeneratedMessageV3.BuilderParent parent) {
            Builder builder = new Builder(parent);
            return builder;
        }

        public static Any getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<Any> parser() {
            return PARSER;
        }

        public Parser<Any> getParserForType() {
            return PARSER;
        }

        public Any getDefaultInstanceForType() {
            return DEFAULT_INSTANCE;
        }

        public static final class Builder
        extends GeneratedMessageV3.Builder<Builder>
        implements AnyOrBuilder {
            private int bitField0_;
            private int type_ = 1;
            private Scalar scalar_;
            private SingleFieldBuilderV3<Scalar, Scalar.Builder, ScalarOrBuilder> scalarBuilder_;
            private Object obj_;
            private SingleFieldBuilderV3<Object, Object.Builder, ObjectOrBuilder> objBuilder_;
            private Array array_;
            private SingleFieldBuilderV3<Array, Array.Builder, ArrayOrBuilder> arrayBuilder_;

            public static final Descriptors.Descriptor getDescriptor() {
                return internal_static_Mysqlx_Datatypes_Any_descriptor;
            }

            protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                return internal_static_Mysqlx_Datatypes_Any_fieldAccessorTable.ensureFieldAccessorsInitialized(Any.class, Builder.class);
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
                    this.getScalarFieldBuilder();
                    this.getObjFieldBuilder();
                    this.getArrayFieldBuilder();
                }
            }

            public Builder clear() {
                super.clear();
                this.type_ = 1;
                this.bitField0_ &= 0xFFFFFFFE;
                if (this.scalarBuilder_ == null) {
                    this.scalar_ = null;
                } else {
                    this.scalarBuilder_.clear();
                }
                this.bitField0_ &= 0xFFFFFFFD;
                if (this.objBuilder_ == null) {
                    this.obj_ = null;
                } else {
                    this.objBuilder_.clear();
                }
                this.bitField0_ &= 0xFFFFFFFB;
                if (this.arrayBuilder_ == null) {
                    this.array_ = null;
                } else {
                    this.arrayBuilder_.clear();
                }
                this.bitField0_ &= 0xFFFFFFF7;
                return this;
            }

            public Descriptors.Descriptor getDescriptorForType() {
                return internal_static_Mysqlx_Datatypes_Any_descriptor;
            }

            public Any getDefaultInstanceForType() {
                return Any.getDefaultInstance();
            }

            public Any build() {
                Any result = this.buildPartial();
                if (!result.isInitialized()) {
                    throw Builder.newUninitializedMessageException((Message)result);
                }
                return result;
            }

            public Any buildPartial() {
                Any result = new Any(this);
                int from_bitField0_ = this.bitField0_;
                int to_bitField0_ = 0;
                if ((from_bitField0_ & 1) != 0) {
                    to_bitField0_ |= 1;
                }
                result.type_ = this.type_;
                if ((from_bitField0_ & 2) != 0) {
                    if (this.scalarBuilder_ == null) {
                        result.scalar_ = this.scalar_;
                    } else {
                        result.scalar_ = (Scalar)this.scalarBuilder_.build();
                    }
                    to_bitField0_ |= 2;
                }
                if ((from_bitField0_ & 4) != 0) {
                    if (this.objBuilder_ == null) {
                        result.obj_ = this.obj_;
                    } else {
                        result.obj_ = (Object)this.objBuilder_.build();
                    }
                    to_bitField0_ |= 4;
                }
                if ((from_bitField0_ & 8) != 0) {
                    if (this.arrayBuilder_ == null) {
                        result.array_ = this.array_;
                    } else {
                        result.array_ = (Array)this.arrayBuilder_.build();
                    }
                    to_bitField0_ |= 8;
                }
                result.bitField0_ = to_bitField0_;
                this.onBuilt();
                return result;
            }

            public Builder clone() {
                return (Builder)super.clone();
            }

            public Builder setField(Descriptors.FieldDescriptor field, java.lang.Object value) {
                return (Builder)super.setField(field, value);
            }

            public Builder clearField(Descriptors.FieldDescriptor field) {
                return (Builder)super.clearField(field);
            }

            public Builder clearOneof(Descriptors.OneofDescriptor oneof) {
                return (Builder)super.clearOneof(oneof);
            }

            public Builder setRepeatedField(Descriptors.FieldDescriptor field, int index, java.lang.Object value) {
                return (Builder)super.setRepeatedField(field, index, value);
            }

            public Builder addRepeatedField(Descriptors.FieldDescriptor field, java.lang.Object value) {
                return (Builder)super.addRepeatedField(field, value);
            }

            public Builder mergeFrom(Message other) {
                if (other instanceof Any) {
                    return this.mergeFrom((Any)other);
                }
                super.mergeFrom(other);
                return this;
            }

            public Builder mergeFrom(Any other) {
                if (other == Any.getDefaultInstance()) {
                    return this;
                }
                if (other.hasType()) {
                    this.setType(other.getType());
                }
                if (other.hasScalar()) {
                    this.mergeScalar(other.getScalar());
                }
                if (other.hasObj()) {
                    this.mergeObj(other.getObj());
                }
                if (other.hasArray()) {
                    this.mergeArray(other.getArray());
                }
                this.mergeUnknownFields(other.unknownFields);
                this.onChanged();
                return this;
            }

            public final boolean isInitialized() {
                if (!this.hasType()) {
                    return false;
                }
                if (this.hasScalar() && !this.getScalar().isInitialized()) {
                    return false;
                }
                if (this.hasObj() && !this.getObj().isInitialized()) {
                    return false;
                }
                return !this.hasArray() || this.getArray().isInitialized();
            }

            public Builder mergeFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                Any parsedMessage = null;
                try {
                    parsedMessage = (Any)PARSER.parsePartialFrom(input, extensionRegistry);
                }
                catch (InvalidProtocolBufferException e) {
                    parsedMessage = (Any)e.getUnfinishedMessage();
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
                return result == null ? Type.SCALAR : result;
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
                this.type_ = 1;
                this.onChanged();
                return this;
            }

            @Override
            public boolean hasScalar() {
                return (this.bitField0_ & 2) != 0;
            }

            @Override
            public Scalar getScalar() {
                if (this.scalarBuilder_ == null) {
                    return this.scalar_ == null ? Scalar.getDefaultInstance() : this.scalar_;
                }
                return (Scalar)this.scalarBuilder_.getMessage();
            }

            public Builder setScalar(Scalar value) {
                if (this.scalarBuilder_ == null) {
                    if (value == null) {
                        throw new NullPointerException();
                    }
                    this.scalar_ = value;
                    this.onChanged();
                } else {
                    this.scalarBuilder_.setMessage((AbstractMessage)value);
                }
                this.bitField0_ |= 2;
                return this;
            }

            public Builder setScalar(Scalar.Builder builderForValue) {
                if (this.scalarBuilder_ == null) {
                    this.scalar_ = builderForValue.build();
                    this.onChanged();
                } else {
                    this.scalarBuilder_.setMessage((AbstractMessage)builderForValue.build());
                }
                this.bitField0_ |= 2;
                return this;
            }

            public Builder mergeScalar(Scalar value) {
                if (this.scalarBuilder_ == null) {
                    this.scalar_ = (this.bitField0_ & 2) != 0 && this.scalar_ != null && this.scalar_ != Scalar.getDefaultInstance() ? Scalar.newBuilder(this.scalar_).mergeFrom(value).buildPartial() : value;
                    this.onChanged();
                } else {
                    this.scalarBuilder_.mergeFrom((AbstractMessage)value);
                }
                this.bitField0_ |= 2;
                return this;
            }

            public Builder clearScalar() {
                if (this.scalarBuilder_ == null) {
                    this.scalar_ = null;
                    this.onChanged();
                } else {
                    this.scalarBuilder_.clear();
                }
                this.bitField0_ &= 0xFFFFFFFD;
                return this;
            }

            public Scalar.Builder getScalarBuilder() {
                this.bitField0_ |= 2;
                this.onChanged();
                return (Scalar.Builder)this.getScalarFieldBuilder().getBuilder();
            }

            @Override
            public ScalarOrBuilder getScalarOrBuilder() {
                if (this.scalarBuilder_ != null) {
                    return (ScalarOrBuilder)this.scalarBuilder_.getMessageOrBuilder();
                }
                return this.scalar_ == null ? Scalar.getDefaultInstance() : this.scalar_;
            }

            private SingleFieldBuilderV3<Scalar, Scalar.Builder, ScalarOrBuilder> getScalarFieldBuilder() {
                if (this.scalarBuilder_ == null) {
                    this.scalarBuilder_ = new SingleFieldBuilderV3((AbstractMessage)this.getScalar(), (AbstractMessage.BuilderParent)this.getParentForChildren(), this.isClean());
                    this.scalar_ = null;
                }
                return this.scalarBuilder_;
            }

            @Override
            public boolean hasObj() {
                return (this.bitField0_ & 4) != 0;
            }

            @Override
            public Object getObj() {
                if (this.objBuilder_ == null) {
                    return this.obj_ == null ? Object.getDefaultInstance() : this.obj_;
                }
                return (Object)this.objBuilder_.getMessage();
            }

            public Builder setObj(Object value) {
                if (this.objBuilder_ == null) {
                    if (value == null) {
                        throw new NullPointerException();
                    }
                    this.obj_ = value;
                    this.onChanged();
                } else {
                    this.objBuilder_.setMessage((AbstractMessage)value);
                }
                this.bitField0_ |= 4;
                return this;
            }

            public Builder setObj(Object.Builder builderForValue) {
                if (this.objBuilder_ == null) {
                    this.obj_ = builderForValue.build();
                    this.onChanged();
                } else {
                    this.objBuilder_.setMessage((AbstractMessage)builderForValue.build());
                }
                this.bitField0_ |= 4;
                return this;
            }

            public Builder mergeObj(Object value) {
                if (this.objBuilder_ == null) {
                    this.obj_ = (this.bitField0_ & 4) != 0 && this.obj_ != null && this.obj_ != Object.getDefaultInstance() ? Object.newBuilder(this.obj_).mergeFrom(value).buildPartial() : value;
                    this.onChanged();
                } else {
                    this.objBuilder_.mergeFrom((AbstractMessage)value);
                }
                this.bitField0_ |= 4;
                return this;
            }

            public Builder clearObj() {
                if (this.objBuilder_ == null) {
                    this.obj_ = null;
                    this.onChanged();
                } else {
                    this.objBuilder_.clear();
                }
                this.bitField0_ &= 0xFFFFFFFB;
                return this;
            }

            public Object.Builder getObjBuilder() {
                this.bitField0_ |= 4;
                this.onChanged();
                return (Object.Builder)this.getObjFieldBuilder().getBuilder();
            }

            @Override
            public ObjectOrBuilder getObjOrBuilder() {
                if (this.objBuilder_ != null) {
                    return (ObjectOrBuilder)this.objBuilder_.getMessageOrBuilder();
                }
                return this.obj_ == null ? Object.getDefaultInstance() : this.obj_;
            }

            private SingleFieldBuilderV3<Object, Object.Builder, ObjectOrBuilder> getObjFieldBuilder() {
                if (this.objBuilder_ == null) {
                    this.objBuilder_ = new SingleFieldBuilderV3((AbstractMessage)this.getObj(), (AbstractMessage.BuilderParent)this.getParentForChildren(), this.isClean());
                    this.obj_ = null;
                }
                return this.objBuilder_;
            }

            @Override
            public boolean hasArray() {
                return (this.bitField0_ & 8) != 0;
            }

            @Override
            public Array getArray() {
                if (this.arrayBuilder_ == null) {
                    return this.array_ == null ? Array.getDefaultInstance() : this.array_;
                }
                return (Array)this.arrayBuilder_.getMessage();
            }

            public Builder setArray(Array value) {
                if (this.arrayBuilder_ == null) {
                    if (value == null) {
                        throw new NullPointerException();
                    }
                    this.array_ = value;
                    this.onChanged();
                } else {
                    this.arrayBuilder_.setMessage((AbstractMessage)value);
                }
                this.bitField0_ |= 8;
                return this;
            }

            public Builder setArray(Array.Builder builderForValue) {
                if (this.arrayBuilder_ == null) {
                    this.array_ = builderForValue.build();
                    this.onChanged();
                } else {
                    this.arrayBuilder_.setMessage((AbstractMessage)builderForValue.build());
                }
                this.bitField0_ |= 8;
                return this;
            }

            public Builder mergeArray(Array value) {
                if (this.arrayBuilder_ == null) {
                    this.array_ = (this.bitField0_ & 8) != 0 && this.array_ != null && this.array_ != Array.getDefaultInstance() ? Array.newBuilder(this.array_).mergeFrom(value).buildPartial() : value;
                    this.onChanged();
                } else {
                    this.arrayBuilder_.mergeFrom((AbstractMessage)value);
                }
                this.bitField0_ |= 8;
                return this;
            }

            public Builder clearArray() {
                if (this.arrayBuilder_ == null) {
                    this.array_ = null;
                    this.onChanged();
                } else {
                    this.arrayBuilder_.clear();
                }
                this.bitField0_ &= 0xFFFFFFF7;
                return this;
            }

            public Array.Builder getArrayBuilder() {
                this.bitField0_ |= 8;
                this.onChanged();
                return (Array.Builder)this.getArrayFieldBuilder().getBuilder();
            }

            @Override
            public ArrayOrBuilder getArrayOrBuilder() {
                if (this.arrayBuilder_ != null) {
                    return (ArrayOrBuilder)this.arrayBuilder_.getMessageOrBuilder();
                }
                return this.array_ == null ? Array.getDefaultInstance() : this.array_;
            }

            private SingleFieldBuilderV3<Array, Array.Builder, ArrayOrBuilder> getArrayFieldBuilder() {
                if (this.arrayBuilder_ == null) {
                    this.arrayBuilder_ = new SingleFieldBuilderV3((AbstractMessage)this.getArray(), (AbstractMessage.BuilderParent)this.getParentForChildren(), this.isClean());
                    this.array_ = null;
                }
                return this.arrayBuilder_;
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
            SCALAR(1),
            OBJECT(2),
            ARRAY(3);

            public static final int SCALAR_VALUE = 1;
            public static final int OBJECT_VALUE = 2;
            public static final int ARRAY_VALUE = 3;
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
                    case 1: {
                        return SCALAR;
                    }
                    case 2: {
                        return OBJECT;
                    }
                    case 3: {
                        return ARRAY;
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
                return (Descriptors.EnumDescriptor)Any.getDescriptor().getEnumTypes().get(0);
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

    public static interface AnyOrBuilder
    extends MessageOrBuilder {
        public boolean hasType();

        public Any.Type getType();

        public boolean hasScalar();

        public Scalar getScalar();

        public ScalarOrBuilder getScalarOrBuilder();

        public boolean hasObj();

        public Object getObj();

        public ObjectOrBuilder getObjOrBuilder();

        public boolean hasArray();

        public Array getArray();

        public ArrayOrBuilder getArrayOrBuilder();
    }

    public static final class Array
    extends GeneratedMessageV3
    implements ArrayOrBuilder {
        private static final long serialVersionUID = 0L;
        public static final int VALUE_FIELD_NUMBER = 1;
        private List<Any> value_;
        private byte memoizedIsInitialized = (byte)-1;
        private static final Array DEFAULT_INSTANCE = new Array();
        @Deprecated
        public static final Parser<Array> PARSER = new AbstractParser<Array>(){

            public Array parsePartialFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                return new Array(input, extensionRegistry);
            }
        };

        private Array(GeneratedMessageV3.Builder<?> builder) {
            super(builder);
        }

        private Array() {
            this.value_ = Collections.emptyList();
        }

        protected java.lang.Object newInstance(GeneratedMessageV3.UnusedPrivateParameter unused) {
            return new Array();
        }

        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        private Array(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
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
                                this.value_ = new ArrayList<Any>();
                                mutable_bitField0_ |= true;
                            }
                            this.value_.add((Any)input.readMessage(Any.PARSER, extensionRegistry));
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
                    this.value_ = Collections.unmodifiableList(this.value_);
                }
                this.unknownFields = unknownFields.build();
                this.makeExtensionsImmutable();
            }
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return internal_static_Mysqlx_Datatypes_Array_descriptor;
        }

        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return internal_static_Mysqlx_Datatypes_Array_fieldAccessorTable.ensureFieldAccessorsInitialized(Array.class, Builder.class);
        }

        @Override
        public List<Any> getValueList() {
            return this.value_;
        }

        @Override
        public List<? extends AnyOrBuilder> getValueOrBuilderList() {
            return this.value_;
        }

        @Override
        public int getValueCount() {
            return this.value_.size();
        }

        @Override
        public Any getValue(int index) {
            return this.value_.get(index);
        }

        @Override
        public AnyOrBuilder getValueOrBuilder(int index) {
            return this.value_.get(index);
        }

        public final boolean isInitialized() {
            byte isInitialized = this.memoizedIsInitialized;
            if (isInitialized == 1) {
                return true;
            }
            if (isInitialized == 0) {
                return false;
            }
            for (int i = 0; i < this.getValueCount(); ++i) {
                if (this.getValue(i).isInitialized()) continue;
                this.memoizedIsInitialized = 0;
                return false;
            }
            this.memoizedIsInitialized = 1;
            return true;
        }

        public void writeTo(CodedOutputStream output) throws IOException {
            for (int i = 0; i < this.value_.size(); ++i) {
                output.writeMessage(1, (MessageLite)this.value_.get(i));
            }
            this.unknownFields.writeTo(output);
        }

        public int getSerializedSize() {
            int size = this.memoizedSize;
            if (size != -1) {
                return size;
            }
            size = 0;
            for (int i = 0; i < this.value_.size(); ++i) {
                size += CodedOutputStream.computeMessageSize((int)1, (MessageLite)((MessageLite)this.value_.get(i)));
            }
            this.memoizedSize = size += this.unknownFields.getSerializedSize();
            return size;
        }

        public boolean equals(java.lang.Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof Array)) {
                return super.equals(obj);
            }
            Array other = (Array)obj;
            if (!this.getValueList().equals(other.getValueList())) {
                return false;
            }
            return this.unknownFields.equals((java.lang.Object)other.unknownFields);
        }

        public int hashCode() {
            if (this.memoizedHashCode != 0) {
                return this.memoizedHashCode;
            }
            int hash = 41;
            hash = 19 * hash + Array.getDescriptor().hashCode();
            if (this.getValueCount() > 0) {
                hash = 37 * hash + 1;
                hash = 53 * hash + this.getValueList().hashCode();
            }
            this.memoizedHashCode = hash = 29 * hash + this.unknownFields.hashCode();
            return hash;
        }

        public static Array parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
            return (Array)PARSER.parseFrom(data);
        }

        public static Array parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (Array)PARSER.parseFrom(data, extensionRegistry);
        }

        public static Array parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return (Array)PARSER.parseFrom(data);
        }

        public static Array parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (Array)PARSER.parseFrom(data, extensionRegistry);
        }

        public static Array parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return (Array)PARSER.parseFrom(data);
        }

        public static Array parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (Array)PARSER.parseFrom(data, extensionRegistry);
        }

        public static Array parseFrom(InputStream input) throws IOException {
            return (Array)GeneratedMessageV3.parseWithIOException(PARSER, (InputStream)input);
        }

        public static Array parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (Array)GeneratedMessageV3.parseWithIOException(PARSER, (InputStream)input, (ExtensionRegistryLite)extensionRegistry);
        }

        public static Array parseDelimitedFrom(InputStream input) throws IOException {
            return (Array)GeneratedMessageV3.parseDelimitedWithIOException(PARSER, (InputStream)input);
        }

        public static Array parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (Array)GeneratedMessageV3.parseDelimitedWithIOException(PARSER, (InputStream)input, (ExtensionRegistryLite)extensionRegistry);
        }

        public static Array parseFrom(CodedInputStream input) throws IOException {
            return (Array)GeneratedMessageV3.parseWithIOException(PARSER, (CodedInputStream)input);
        }

        public static Array parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (Array)GeneratedMessageV3.parseWithIOException(PARSER, (CodedInputStream)input, (ExtensionRegistryLite)extensionRegistry);
        }

        public Builder newBuilderForType() {
            return Array.newBuilder();
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(Array prototype) {
            return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
        }

        public Builder toBuilder() {
            return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
        }

        protected Builder newBuilderForType(GeneratedMessageV3.BuilderParent parent) {
            Builder builder = new Builder(parent);
            return builder;
        }

        public static Array getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<Array> parser() {
            return PARSER;
        }

        public Parser<Array> getParserForType() {
            return PARSER;
        }

        public Array getDefaultInstanceForType() {
            return DEFAULT_INSTANCE;
        }

        public static final class Builder
        extends GeneratedMessageV3.Builder<Builder>
        implements ArrayOrBuilder {
            private int bitField0_;
            private List<Any> value_ = Collections.emptyList();
            private RepeatedFieldBuilderV3<Any, Any.Builder, AnyOrBuilder> valueBuilder_;

            public static final Descriptors.Descriptor getDescriptor() {
                return internal_static_Mysqlx_Datatypes_Array_descriptor;
            }

            protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                return internal_static_Mysqlx_Datatypes_Array_fieldAccessorTable.ensureFieldAccessorsInitialized(Array.class, Builder.class);
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
                    this.getValueFieldBuilder();
                }
            }

            public Builder clear() {
                super.clear();
                if (this.valueBuilder_ == null) {
                    this.value_ = Collections.emptyList();
                    this.bitField0_ &= 0xFFFFFFFE;
                } else {
                    this.valueBuilder_.clear();
                }
                return this;
            }

            public Descriptors.Descriptor getDescriptorForType() {
                return internal_static_Mysqlx_Datatypes_Array_descriptor;
            }

            public Array getDefaultInstanceForType() {
                return Array.getDefaultInstance();
            }

            public Array build() {
                Array result = this.buildPartial();
                if (!result.isInitialized()) {
                    throw Builder.newUninitializedMessageException((Message)result);
                }
                return result;
            }

            public Array buildPartial() {
                Array result = new Array(this);
                int from_bitField0_ = this.bitField0_;
                if (this.valueBuilder_ == null) {
                    if ((this.bitField0_ & 1) != 0) {
                        this.value_ = Collections.unmodifiableList(this.value_);
                        this.bitField0_ &= 0xFFFFFFFE;
                    }
                    result.value_ = this.value_;
                } else {
                    result.value_ = this.valueBuilder_.build();
                }
                this.onBuilt();
                return result;
            }

            public Builder clone() {
                return (Builder)super.clone();
            }

            public Builder setField(Descriptors.FieldDescriptor field, java.lang.Object value) {
                return (Builder)super.setField(field, value);
            }

            public Builder clearField(Descriptors.FieldDescriptor field) {
                return (Builder)super.clearField(field);
            }

            public Builder clearOneof(Descriptors.OneofDescriptor oneof) {
                return (Builder)super.clearOneof(oneof);
            }

            public Builder setRepeatedField(Descriptors.FieldDescriptor field, int index, java.lang.Object value) {
                return (Builder)super.setRepeatedField(field, index, value);
            }

            public Builder addRepeatedField(Descriptors.FieldDescriptor field, java.lang.Object value) {
                return (Builder)super.addRepeatedField(field, value);
            }

            public Builder mergeFrom(Message other) {
                if (other instanceof Array) {
                    return this.mergeFrom((Array)other);
                }
                super.mergeFrom(other);
                return this;
            }

            public Builder mergeFrom(Array other) {
                if (other == Array.getDefaultInstance()) {
                    return this;
                }
                if (this.valueBuilder_ == null) {
                    if (!other.value_.isEmpty()) {
                        if (this.value_.isEmpty()) {
                            this.value_ = other.value_;
                            this.bitField0_ &= 0xFFFFFFFE;
                        } else {
                            this.ensureValueIsMutable();
                            this.value_.addAll(other.value_);
                        }
                        this.onChanged();
                    }
                } else if (!other.value_.isEmpty()) {
                    if (this.valueBuilder_.isEmpty()) {
                        this.valueBuilder_.dispose();
                        this.valueBuilder_ = null;
                        this.value_ = other.value_;
                        this.bitField0_ &= 0xFFFFFFFE;
                        this.valueBuilder_ = alwaysUseFieldBuilders ? this.getValueFieldBuilder() : null;
                    } else {
                        this.valueBuilder_.addAllMessages((Iterable)other.value_);
                    }
                }
                this.mergeUnknownFields(other.unknownFields);
                this.onChanged();
                return this;
            }

            public final boolean isInitialized() {
                for (int i = 0; i < this.getValueCount(); ++i) {
                    if (this.getValue(i).isInitialized()) continue;
                    return false;
                }
                return true;
            }

            public Builder mergeFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                Array parsedMessage = null;
                try {
                    parsedMessage = (Array)PARSER.parsePartialFrom(input, extensionRegistry);
                }
                catch (InvalidProtocolBufferException e) {
                    parsedMessage = (Array)e.getUnfinishedMessage();
                    throw e.unwrapIOException();
                }
                finally {
                    if (parsedMessage != null) {
                        this.mergeFrom(parsedMessage);
                    }
                }
                return this;
            }

            private void ensureValueIsMutable() {
                if ((this.bitField0_ & 1) == 0) {
                    this.value_ = new ArrayList<Any>(this.value_);
                    this.bitField0_ |= 1;
                }
            }

            @Override
            public List<Any> getValueList() {
                if (this.valueBuilder_ == null) {
                    return Collections.unmodifiableList(this.value_);
                }
                return this.valueBuilder_.getMessageList();
            }

            @Override
            public int getValueCount() {
                if (this.valueBuilder_ == null) {
                    return this.value_.size();
                }
                return this.valueBuilder_.getCount();
            }

            @Override
            public Any getValue(int index) {
                if (this.valueBuilder_ == null) {
                    return this.value_.get(index);
                }
                return (Any)this.valueBuilder_.getMessage(index);
            }

            public Builder setValue(int index, Any value) {
                if (this.valueBuilder_ == null) {
                    if (value == null) {
                        throw new NullPointerException();
                    }
                    this.ensureValueIsMutable();
                    this.value_.set(index, value);
                    this.onChanged();
                } else {
                    this.valueBuilder_.setMessage(index, (AbstractMessage)value);
                }
                return this;
            }

            public Builder setValue(int index, Any.Builder builderForValue) {
                if (this.valueBuilder_ == null) {
                    this.ensureValueIsMutable();
                    this.value_.set(index, builderForValue.build());
                    this.onChanged();
                } else {
                    this.valueBuilder_.setMessage(index, (AbstractMessage)builderForValue.build());
                }
                return this;
            }

            public Builder addValue(Any value) {
                if (this.valueBuilder_ == null) {
                    if (value == null) {
                        throw new NullPointerException();
                    }
                    this.ensureValueIsMutable();
                    this.value_.add(value);
                    this.onChanged();
                } else {
                    this.valueBuilder_.addMessage((AbstractMessage)value);
                }
                return this;
            }

            public Builder addValue(int index, Any value) {
                if (this.valueBuilder_ == null) {
                    if (value == null) {
                        throw new NullPointerException();
                    }
                    this.ensureValueIsMutable();
                    this.value_.add(index, value);
                    this.onChanged();
                } else {
                    this.valueBuilder_.addMessage(index, (AbstractMessage)value);
                }
                return this;
            }

            public Builder addValue(Any.Builder builderForValue) {
                if (this.valueBuilder_ == null) {
                    this.ensureValueIsMutable();
                    this.value_.add(builderForValue.build());
                    this.onChanged();
                } else {
                    this.valueBuilder_.addMessage((AbstractMessage)builderForValue.build());
                }
                return this;
            }

            public Builder addValue(int index, Any.Builder builderForValue) {
                if (this.valueBuilder_ == null) {
                    this.ensureValueIsMutable();
                    this.value_.add(index, builderForValue.build());
                    this.onChanged();
                } else {
                    this.valueBuilder_.addMessage(index, (AbstractMessage)builderForValue.build());
                }
                return this;
            }

            public Builder addAllValue(Iterable<? extends Any> values) {
                if (this.valueBuilder_ == null) {
                    this.ensureValueIsMutable();
                    AbstractMessageLite.Builder.addAll(values, this.value_);
                    this.onChanged();
                } else {
                    this.valueBuilder_.addAllMessages(values);
                }
                return this;
            }

            public Builder clearValue() {
                if (this.valueBuilder_ == null) {
                    this.value_ = Collections.emptyList();
                    this.bitField0_ &= 0xFFFFFFFE;
                    this.onChanged();
                } else {
                    this.valueBuilder_.clear();
                }
                return this;
            }

            public Builder removeValue(int index) {
                if (this.valueBuilder_ == null) {
                    this.ensureValueIsMutable();
                    this.value_.remove(index);
                    this.onChanged();
                } else {
                    this.valueBuilder_.remove(index);
                }
                return this;
            }

            public Any.Builder getValueBuilder(int index) {
                return (Any.Builder)this.getValueFieldBuilder().getBuilder(index);
            }

            @Override
            public AnyOrBuilder getValueOrBuilder(int index) {
                if (this.valueBuilder_ == null) {
                    return this.value_.get(index);
                }
                return (AnyOrBuilder)this.valueBuilder_.getMessageOrBuilder(index);
            }

            @Override
            public List<? extends AnyOrBuilder> getValueOrBuilderList() {
                if (this.valueBuilder_ != null) {
                    return this.valueBuilder_.getMessageOrBuilderList();
                }
                return Collections.unmodifiableList(this.value_);
            }

            public Any.Builder addValueBuilder() {
                return (Any.Builder)this.getValueFieldBuilder().addBuilder((AbstractMessage)Any.getDefaultInstance());
            }

            public Any.Builder addValueBuilder(int index) {
                return (Any.Builder)this.getValueFieldBuilder().addBuilder(index, (AbstractMessage)Any.getDefaultInstance());
            }

            public List<Any.Builder> getValueBuilderList() {
                return this.getValueFieldBuilder().getBuilderList();
            }

            private RepeatedFieldBuilderV3<Any, Any.Builder, AnyOrBuilder> getValueFieldBuilder() {
                if (this.valueBuilder_ == null) {
                    this.valueBuilder_ = new RepeatedFieldBuilderV3(this.value_, (this.bitField0_ & 1) != 0, (AbstractMessage.BuilderParent)this.getParentForChildren(), this.isClean());
                    this.value_ = null;
                }
                return this.valueBuilder_;
            }

            public final Builder setUnknownFields(UnknownFieldSet unknownFields) {
                return (Builder)super.setUnknownFields(unknownFields);
            }

            public final Builder mergeUnknownFields(UnknownFieldSet unknownFields) {
                return (Builder)super.mergeUnknownFields(unknownFields);
            }
        }
    }

    public static interface ArrayOrBuilder
    extends MessageOrBuilder {
        public List<Any> getValueList();

        public Any getValue(int var1);

        public int getValueCount();

        public List<? extends AnyOrBuilder> getValueOrBuilderList();

        public AnyOrBuilder getValueOrBuilder(int var1);
    }

    public static final class Object
    extends GeneratedMessageV3
    implements ObjectOrBuilder {
        private static final long serialVersionUID = 0L;
        public static final int FLD_FIELD_NUMBER = 1;
        private List<ObjectField> fld_;
        private byte memoizedIsInitialized = (byte)-1;
        private static final Object DEFAULT_INSTANCE = new Object();
        @Deprecated
        public static final Parser<Object> PARSER = new AbstractParser<Object>(){

            public Object parsePartialFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                return new Object(input, extensionRegistry);
            }
        };

        private Object(GeneratedMessageV3.Builder<?> builder) {
            super(builder);
        }

        private Object() {
            this.fld_ = Collections.emptyList();
        }

        protected java.lang.Object newInstance(GeneratedMessageV3.UnusedPrivateParameter unused) {
            return new Object();
        }

        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        private Object(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
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
                                this.fld_ = new ArrayList<ObjectField>();
                                mutable_bitField0_ |= true;
                            }
                            this.fld_.add((ObjectField)input.readMessage(ObjectField.PARSER, extensionRegistry));
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
                    this.fld_ = Collections.unmodifiableList(this.fld_);
                }
                this.unknownFields = unknownFields.build();
                this.makeExtensionsImmutable();
            }
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return internal_static_Mysqlx_Datatypes_Object_descriptor;
        }

        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return internal_static_Mysqlx_Datatypes_Object_fieldAccessorTable.ensureFieldAccessorsInitialized(Object.class, Builder.class);
        }

        @Override
        public List<ObjectField> getFldList() {
            return this.fld_;
        }

        @Override
        public List<? extends ObjectFieldOrBuilder> getFldOrBuilderList() {
            return this.fld_;
        }

        @Override
        public int getFldCount() {
            return this.fld_.size();
        }

        @Override
        public ObjectField getFld(int index) {
            return this.fld_.get(index);
        }

        @Override
        public ObjectFieldOrBuilder getFldOrBuilder(int index) {
            return this.fld_.get(index);
        }

        public final boolean isInitialized() {
            byte isInitialized = this.memoizedIsInitialized;
            if (isInitialized == 1) {
                return true;
            }
            if (isInitialized == 0) {
                return false;
            }
            for (int i = 0; i < this.getFldCount(); ++i) {
                if (this.getFld(i).isInitialized()) continue;
                this.memoizedIsInitialized = 0;
                return false;
            }
            this.memoizedIsInitialized = 1;
            return true;
        }

        public void writeTo(CodedOutputStream output) throws IOException {
            for (int i = 0; i < this.fld_.size(); ++i) {
                output.writeMessage(1, (MessageLite)this.fld_.get(i));
            }
            this.unknownFields.writeTo(output);
        }

        public int getSerializedSize() {
            int size = this.memoizedSize;
            if (size != -1) {
                return size;
            }
            size = 0;
            for (int i = 0; i < this.fld_.size(); ++i) {
                size += CodedOutputStream.computeMessageSize((int)1, (MessageLite)((MessageLite)this.fld_.get(i)));
            }
            this.memoizedSize = size += this.unknownFields.getSerializedSize();
            return size;
        }

        public boolean equals(java.lang.Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof Object)) {
                return super.equals(obj);
            }
            Object other = (Object)obj;
            if (!this.getFldList().equals(other.getFldList())) {
                return false;
            }
            return this.unknownFields.equals((java.lang.Object)other.unknownFields);
        }

        public int hashCode() {
            if (this.memoizedHashCode != 0) {
                return this.memoizedHashCode;
            }
            int hash = 41;
            hash = 19 * hash + Object.getDescriptor().hashCode();
            if (this.getFldCount() > 0) {
                hash = 37 * hash + 1;
                hash = 53 * hash + this.getFldList().hashCode();
            }
            this.memoizedHashCode = hash = 29 * hash + this.unknownFields.hashCode();
            return hash;
        }

        public static Object parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
            return (Object)PARSER.parseFrom(data);
        }

        public static Object parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (Object)PARSER.parseFrom(data, extensionRegistry);
        }

        public static Object parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return (Object)PARSER.parseFrom(data);
        }

        public static Object parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (Object)PARSER.parseFrom(data, extensionRegistry);
        }

        public static Object parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return (Object)PARSER.parseFrom(data);
        }

        public static Object parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (Object)PARSER.parseFrom(data, extensionRegistry);
        }

        public static Object parseFrom(InputStream input) throws IOException {
            return (Object)GeneratedMessageV3.parseWithIOException(PARSER, (InputStream)input);
        }

        public static Object parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (Object)GeneratedMessageV3.parseWithIOException(PARSER, (InputStream)input, (ExtensionRegistryLite)extensionRegistry);
        }

        public static Object parseDelimitedFrom(InputStream input) throws IOException {
            return (Object)GeneratedMessageV3.parseDelimitedWithIOException(PARSER, (InputStream)input);
        }

        public static Object parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (Object)GeneratedMessageV3.parseDelimitedWithIOException(PARSER, (InputStream)input, (ExtensionRegistryLite)extensionRegistry);
        }

        public static Object parseFrom(CodedInputStream input) throws IOException {
            return (Object)GeneratedMessageV3.parseWithIOException(PARSER, (CodedInputStream)input);
        }

        public static Object parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (Object)GeneratedMessageV3.parseWithIOException(PARSER, (CodedInputStream)input, (ExtensionRegistryLite)extensionRegistry);
        }

        public Builder newBuilderForType() {
            return Object.newBuilder();
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(Object prototype) {
            return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
        }

        public Builder toBuilder() {
            return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
        }

        protected Builder newBuilderForType(GeneratedMessageV3.BuilderParent parent) {
            Builder builder = new Builder(parent);
            return builder;
        }

        public static Object getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<Object> parser() {
            return PARSER;
        }

        public Parser<Object> getParserForType() {
            return PARSER;
        }

        public Object getDefaultInstanceForType() {
            return DEFAULT_INSTANCE;
        }

        public static final class Builder
        extends GeneratedMessageV3.Builder<Builder>
        implements ObjectOrBuilder {
            private int bitField0_;
            private List<ObjectField> fld_ = Collections.emptyList();
            private RepeatedFieldBuilderV3<ObjectField, ObjectField.Builder, ObjectFieldOrBuilder> fldBuilder_;

            public static final Descriptors.Descriptor getDescriptor() {
                return internal_static_Mysqlx_Datatypes_Object_descriptor;
            }

            protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                return internal_static_Mysqlx_Datatypes_Object_fieldAccessorTable.ensureFieldAccessorsInitialized(Object.class, Builder.class);
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
                    this.getFldFieldBuilder();
                }
            }

            public Builder clear() {
                super.clear();
                if (this.fldBuilder_ == null) {
                    this.fld_ = Collections.emptyList();
                    this.bitField0_ &= 0xFFFFFFFE;
                } else {
                    this.fldBuilder_.clear();
                }
                return this;
            }

            public Descriptors.Descriptor getDescriptorForType() {
                return internal_static_Mysqlx_Datatypes_Object_descriptor;
            }

            public Object getDefaultInstanceForType() {
                return Object.getDefaultInstance();
            }

            public Object build() {
                Object result = this.buildPartial();
                if (!result.isInitialized()) {
                    throw Builder.newUninitializedMessageException((Message)result);
                }
                return result;
            }

            public Object buildPartial() {
                Object result = new Object(this);
                int from_bitField0_ = this.bitField0_;
                if (this.fldBuilder_ == null) {
                    if ((this.bitField0_ & 1) != 0) {
                        this.fld_ = Collections.unmodifiableList(this.fld_);
                        this.bitField0_ &= 0xFFFFFFFE;
                    }
                    result.fld_ = this.fld_;
                } else {
                    result.fld_ = this.fldBuilder_.build();
                }
                this.onBuilt();
                return result;
            }

            public Builder clone() {
                return (Builder)super.clone();
            }

            public Builder setField(Descriptors.FieldDescriptor field, java.lang.Object value) {
                return (Builder)super.setField(field, value);
            }

            public Builder clearField(Descriptors.FieldDescriptor field) {
                return (Builder)super.clearField(field);
            }

            public Builder clearOneof(Descriptors.OneofDescriptor oneof) {
                return (Builder)super.clearOneof(oneof);
            }

            public Builder setRepeatedField(Descriptors.FieldDescriptor field, int index, java.lang.Object value) {
                return (Builder)super.setRepeatedField(field, index, value);
            }

            public Builder addRepeatedField(Descriptors.FieldDescriptor field, java.lang.Object value) {
                return (Builder)super.addRepeatedField(field, value);
            }

            public Builder mergeFrom(Message other) {
                if (other instanceof Object) {
                    return this.mergeFrom((Object)other);
                }
                super.mergeFrom(other);
                return this;
            }

            public Builder mergeFrom(Object other) {
                if (other == Object.getDefaultInstance()) {
                    return this;
                }
                if (this.fldBuilder_ == null) {
                    if (!other.fld_.isEmpty()) {
                        if (this.fld_.isEmpty()) {
                            this.fld_ = other.fld_;
                            this.bitField0_ &= 0xFFFFFFFE;
                        } else {
                            this.ensureFldIsMutable();
                            this.fld_.addAll(other.fld_);
                        }
                        this.onChanged();
                    }
                } else if (!other.fld_.isEmpty()) {
                    if (this.fldBuilder_.isEmpty()) {
                        this.fldBuilder_.dispose();
                        this.fldBuilder_ = null;
                        this.fld_ = other.fld_;
                        this.bitField0_ &= 0xFFFFFFFE;
                        this.fldBuilder_ = alwaysUseFieldBuilders ? this.getFldFieldBuilder() : null;
                    } else {
                        this.fldBuilder_.addAllMessages((Iterable)other.fld_);
                    }
                }
                this.mergeUnknownFields(other.unknownFields);
                this.onChanged();
                return this;
            }

            public final boolean isInitialized() {
                for (int i = 0; i < this.getFldCount(); ++i) {
                    if (this.getFld(i).isInitialized()) continue;
                    return false;
                }
                return true;
            }

            public Builder mergeFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                Object parsedMessage = null;
                try {
                    parsedMessage = (Object)PARSER.parsePartialFrom(input, extensionRegistry);
                }
                catch (InvalidProtocolBufferException e) {
                    parsedMessage = (Object)e.getUnfinishedMessage();
                    throw e.unwrapIOException();
                }
                finally {
                    if (parsedMessage != null) {
                        this.mergeFrom(parsedMessage);
                    }
                }
                return this;
            }

            private void ensureFldIsMutable() {
                if ((this.bitField0_ & 1) == 0) {
                    this.fld_ = new ArrayList<ObjectField>(this.fld_);
                    this.bitField0_ |= 1;
                }
            }

            @Override
            public List<ObjectField> getFldList() {
                if (this.fldBuilder_ == null) {
                    return Collections.unmodifiableList(this.fld_);
                }
                return this.fldBuilder_.getMessageList();
            }

            @Override
            public int getFldCount() {
                if (this.fldBuilder_ == null) {
                    return this.fld_.size();
                }
                return this.fldBuilder_.getCount();
            }

            @Override
            public ObjectField getFld(int index) {
                if (this.fldBuilder_ == null) {
                    return this.fld_.get(index);
                }
                return (ObjectField)this.fldBuilder_.getMessage(index);
            }

            public Builder setFld(int index, ObjectField value) {
                if (this.fldBuilder_ == null) {
                    if (value == null) {
                        throw new NullPointerException();
                    }
                    this.ensureFldIsMutable();
                    this.fld_.set(index, value);
                    this.onChanged();
                } else {
                    this.fldBuilder_.setMessage(index, (AbstractMessage)value);
                }
                return this;
            }

            public Builder setFld(int index, ObjectField.Builder builderForValue) {
                if (this.fldBuilder_ == null) {
                    this.ensureFldIsMutable();
                    this.fld_.set(index, builderForValue.build());
                    this.onChanged();
                } else {
                    this.fldBuilder_.setMessage(index, (AbstractMessage)builderForValue.build());
                }
                return this;
            }

            public Builder addFld(ObjectField value) {
                if (this.fldBuilder_ == null) {
                    if (value == null) {
                        throw new NullPointerException();
                    }
                    this.ensureFldIsMutable();
                    this.fld_.add(value);
                    this.onChanged();
                } else {
                    this.fldBuilder_.addMessage((AbstractMessage)value);
                }
                return this;
            }

            public Builder addFld(int index, ObjectField value) {
                if (this.fldBuilder_ == null) {
                    if (value == null) {
                        throw new NullPointerException();
                    }
                    this.ensureFldIsMutable();
                    this.fld_.add(index, value);
                    this.onChanged();
                } else {
                    this.fldBuilder_.addMessage(index, (AbstractMessage)value);
                }
                return this;
            }

            public Builder addFld(ObjectField.Builder builderForValue) {
                if (this.fldBuilder_ == null) {
                    this.ensureFldIsMutable();
                    this.fld_.add(builderForValue.build());
                    this.onChanged();
                } else {
                    this.fldBuilder_.addMessage((AbstractMessage)builderForValue.build());
                }
                return this;
            }

            public Builder addFld(int index, ObjectField.Builder builderForValue) {
                if (this.fldBuilder_ == null) {
                    this.ensureFldIsMutable();
                    this.fld_.add(index, builderForValue.build());
                    this.onChanged();
                } else {
                    this.fldBuilder_.addMessage(index, (AbstractMessage)builderForValue.build());
                }
                return this;
            }

            public Builder addAllFld(Iterable<? extends ObjectField> values) {
                if (this.fldBuilder_ == null) {
                    this.ensureFldIsMutable();
                    AbstractMessageLite.Builder.addAll(values, this.fld_);
                    this.onChanged();
                } else {
                    this.fldBuilder_.addAllMessages(values);
                }
                return this;
            }

            public Builder clearFld() {
                if (this.fldBuilder_ == null) {
                    this.fld_ = Collections.emptyList();
                    this.bitField0_ &= 0xFFFFFFFE;
                    this.onChanged();
                } else {
                    this.fldBuilder_.clear();
                }
                return this;
            }

            public Builder removeFld(int index) {
                if (this.fldBuilder_ == null) {
                    this.ensureFldIsMutable();
                    this.fld_.remove(index);
                    this.onChanged();
                } else {
                    this.fldBuilder_.remove(index);
                }
                return this;
            }

            public ObjectField.Builder getFldBuilder(int index) {
                return (ObjectField.Builder)this.getFldFieldBuilder().getBuilder(index);
            }

            @Override
            public ObjectFieldOrBuilder getFldOrBuilder(int index) {
                if (this.fldBuilder_ == null) {
                    return this.fld_.get(index);
                }
                return (ObjectFieldOrBuilder)this.fldBuilder_.getMessageOrBuilder(index);
            }

            @Override
            public List<? extends ObjectFieldOrBuilder> getFldOrBuilderList() {
                if (this.fldBuilder_ != null) {
                    return this.fldBuilder_.getMessageOrBuilderList();
                }
                return Collections.unmodifiableList(this.fld_);
            }

            public ObjectField.Builder addFldBuilder() {
                return (ObjectField.Builder)this.getFldFieldBuilder().addBuilder((AbstractMessage)ObjectField.getDefaultInstance());
            }

            public ObjectField.Builder addFldBuilder(int index) {
                return (ObjectField.Builder)this.getFldFieldBuilder().addBuilder(index, (AbstractMessage)ObjectField.getDefaultInstance());
            }

            public List<ObjectField.Builder> getFldBuilderList() {
                return this.getFldFieldBuilder().getBuilderList();
            }

            private RepeatedFieldBuilderV3<ObjectField, ObjectField.Builder, ObjectFieldOrBuilder> getFldFieldBuilder() {
                if (this.fldBuilder_ == null) {
                    this.fldBuilder_ = new RepeatedFieldBuilderV3(this.fld_, (this.bitField0_ & 1) != 0, (AbstractMessage.BuilderParent)this.getParentForChildren(), this.isClean());
                    this.fld_ = null;
                }
                return this.fldBuilder_;
            }

            public final Builder setUnknownFields(UnknownFieldSet unknownFields) {
                return (Builder)super.setUnknownFields(unknownFields);
            }

            public final Builder mergeUnknownFields(UnknownFieldSet unknownFields) {
                return (Builder)super.mergeUnknownFields(unknownFields);
            }
        }

        public static final class ObjectField
        extends GeneratedMessageV3
        implements ObjectFieldOrBuilder {
            private static final long serialVersionUID = 0L;
            private int bitField0_;
            public static final int KEY_FIELD_NUMBER = 1;
            private volatile java.lang.Object key_;
            public static final int VALUE_FIELD_NUMBER = 2;
            private Any value_;
            private byte memoizedIsInitialized = (byte)-1;
            private static final ObjectField DEFAULT_INSTANCE = new ObjectField();
            @Deprecated
            public static final Parser<ObjectField> PARSER = new AbstractParser<ObjectField>(){

                public ObjectField parsePartialFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                    return new ObjectField(input, extensionRegistry);
                }
            };

            private ObjectField(GeneratedMessageV3.Builder<?> builder) {
                super(builder);
            }

            private ObjectField() {
                this.key_ = "";
            }

            protected java.lang.Object newInstance(GeneratedMessageV3.UnusedPrivateParameter unused) {
                return new ObjectField();
            }

            public final UnknownFieldSet getUnknownFields() {
                return this.unknownFields;
            }

            private ObjectField(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
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
                            case 10: {
                                ByteString bs = input.readBytes();
                                this.bitField0_ |= 1;
                                this.key_ = bs;
                                continue block11;
                            }
                            case 18: {
                                Any.Builder subBuilder = null;
                                if ((this.bitField0_ & 2) != 0) {
                                    subBuilder = this.value_.toBuilder();
                                }
                                this.value_ = (Any)input.readMessage(Any.PARSER, extensionRegistry);
                                if (subBuilder != null) {
                                    subBuilder.mergeFrom(this.value_);
                                    this.value_ = subBuilder.buildPartial();
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
                return internal_static_Mysqlx_Datatypes_Object_ObjectField_descriptor;
            }

            protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                return internal_static_Mysqlx_Datatypes_Object_ObjectField_fieldAccessorTable.ensureFieldAccessorsInitialized(ObjectField.class, Builder.class);
            }

            @Override
            public boolean hasKey() {
                return (this.bitField0_ & 1) != 0;
            }

            @Override
            public String getKey() {
                java.lang.Object ref = this.key_;
                if (ref instanceof String) {
                    return (String)ref;
                }
                ByteString bs = (ByteString)ref;
                String s = bs.toStringUtf8();
                if (bs.isValidUtf8()) {
                    this.key_ = s;
                }
                return s;
            }

            @Override
            public ByteString getKeyBytes() {
                java.lang.Object ref = this.key_;
                if (ref instanceof String) {
                    ByteString b = ByteString.copyFromUtf8((String)((String)ref));
                    this.key_ = b;
                    return b;
                }
                return (ByteString)ref;
            }

            @Override
            public boolean hasValue() {
                return (this.bitField0_ & 2) != 0;
            }

            @Override
            public Any getValue() {
                return this.value_ == null ? Any.getDefaultInstance() : this.value_;
            }

            @Override
            public AnyOrBuilder getValueOrBuilder() {
                return this.value_ == null ? Any.getDefaultInstance() : this.value_;
            }

            public final boolean isInitialized() {
                byte isInitialized = this.memoizedIsInitialized;
                if (isInitialized == 1) {
                    return true;
                }
                if (isInitialized == 0) {
                    return false;
                }
                if (!this.hasKey()) {
                    this.memoizedIsInitialized = 0;
                    return false;
                }
                if (!this.hasValue()) {
                    this.memoizedIsInitialized = 0;
                    return false;
                }
                if (!this.getValue().isInitialized()) {
                    this.memoizedIsInitialized = 0;
                    return false;
                }
                this.memoizedIsInitialized = 1;
                return true;
            }

            public void writeTo(CodedOutputStream output) throws IOException {
                if ((this.bitField0_ & 1) != 0) {
                    GeneratedMessageV3.writeString((CodedOutputStream)output, (int)1, (java.lang.Object)this.key_);
                }
                if ((this.bitField0_ & 2) != 0) {
                    output.writeMessage(2, (MessageLite)this.getValue());
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
                    size += GeneratedMessageV3.computeStringSize((int)1, (java.lang.Object)this.key_);
                }
                if ((this.bitField0_ & 2) != 0) {
                    size += CodedOutputStream.computeMessageSize((int)2, (MessageLite)this.getValue());
                }
                this.memoizedSize = size += this.unknownFields.getSerializedSize();
                return size;
            }

            public boolean equals(java.lang.Object obj) {
                if (obj == this) {
                    return true;
                }
                if (!(obj instanceof ObjectField)) {
                    return super.equals(obj);
                }
                ObjectField other = (ObjectField)obj;
                if (this.hasKey() != other.hasKey()) {
                    return false;
                }
                if (this.hasKey() && !this.getKey().equals(other.getKey())) {
                    return false;
                }
                if (this.hasValue() != other.hasValue()) {
                    return false;
                }
                if (this.hasValue() && !this.getValue().equals(other.getValue())) {
                    return false;
                }
                return this.unknownFields.equals((java.lang.Object)other.unknownFields);
            }

            public int hashCode() {
                if (this.memoizedHashCode != 0) {
                    return this.memoizedHashCode;
                }
                int hash = 41;
                hash = 19 * hash + ObjectField.getDescriptor().hashCode();
                if (this.hasKey()) {
                    hash = 37 * hash + 1;
                    hash = 53 * hash + this.getKey().hashCode();
                }
                if (this.hasValue()) {
                    hash = 37 * hash + 2;
                    hash = 53 * hash + this.getValue().hashCode();
                }
                this.memoizedHashCode = hash = 29 * hash + this.unknownFields.hashCode();
                return hash;
            }

            public static ObjectField parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
                return (ObjectField)PARSER.parseFrom(data);
            }

            public static ObjectField parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                return (ObjectField)PARSER.parseFrom(data, extensionRegistry);
            }

            public static ObjectField parseFrom(ByteString data) throws InvalidProtocolBufferException {
                return (ObjectField)PARSER.parseFrom(data);
            }

            public static ObjectField parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                return (ObjectField)PARSER.parseFrom(data, extensionRegistry);
            }

            public static ObjectField parseFrom(byte[] data) throws InvalidProtocolBufferException {
                return (ObjectField)PARSER.parseFrom(data);
            }

            public static ObjectField parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                return (ObjectField)PARSER.parseFrom(data, extensionRegistry);
            }

            public static ObjectField parseFrom(InputStream input) throws IOException {
                return (ObjectField)GeneratedMessageV3.parseWithIOException(PARSER, (InputStream)input);
            }

            public static ObjectField parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                return (ObjectField)GeneratedMessageV3.parseWithIOException(PARSER, (InputStream)input, (ExtensionRegistryLite)extensionRegistry);
            }

            public static ObjectField parseDelimitedFrom(InputStream input) throws IOException {
                return (ObjectField)GeneratedMessageV3.parseDelimitedWithIOException(PARSER, (InputStream)input);
            }

            public static ObjectField parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                return (ObjectField)GeneratedMessageV3.parseDelimitedWithIOException(PARSER, (InputStream)input, (ExtensionRegistryLite)extensionRegistry);
            }

            public static ObjectField parseFrom(CodedInputStream input) throws IOException {
                return (ObjectField)GeneratedMessageV3.parseWithIOException(PARSER, (CodedInputStream)input);
            }

            public static ObjectField parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                return (ObjectField)GeneratedMessageV3.parseWithIOException(PARSER, (CodedInputStream)input, (ExtensionRegistryLite)extensionRegistry);
            }

            public Builder newBuilderForType() {
                return ObjectField.newBuilder();
            }

            public static Builder newBuilder() {
                return DEFAULT_INSTANCE.toBuilder();
            }

            public static Builder newBuilder(ObjectField prototype) {
                return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
            }

            public Builder toBuilder() {
                return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
            }

            protected Builder newBuilderForType(GeneratedMessageV3.BuilderParent parent) {
                Builder builder = new Builder(parent);
                return builder;
            }

            public static ObjectField getDefaultInstance() {
                return DEFAULT_INSTANCE;
            }

            public static Parser<ObjectField> parser() {
                return PARSER;
            }

            public Parser<ObjectField> getParserForType() {
                return PARSER;
            }

            public ObjectField getDefaultInstanceForType() {
                return DEFAULT_INSTANCE;
            }

            public static final class Builder
            extends GeneratedMessageV3.Builder<Builder>
            implements ObjectFieldOrBuilder {
                private int bitField0_;
                private java.lang.Object key_ = "";
                private Any value_;
                private SingleFieldBuilderV3<Any, Any.Builder, AnyOrBuilder> valueBuilder_;

                public static final Descriptors.Descriptor getDescriptor() {
                    return internal_static_Mysqlx_Datatypes_Object_ObjectField_descriptor;
                }

                protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                    return internal_static_Mysqlx_Datatypes_Object_ObjectField_fieldAccessorTable.ensureFieldAccessorsInitialized(ObjectField.class, Builder.class);
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
                        this.getValueFieldBuilder();
                    }
                }

                public Builder clear() {
                    super.clear();
                    this.key_ = "";
                    this.bitField0_ &= 0xFFFFFFFE;
                    if (this.valueBuilder_ == null) {
                        this.value_ = null;
                    } else {
                        this.valueBuilder_.clear();
                    }
                    this.bitField0_ &= 0xFFFFFFFD;
                    return this;
                }

                public Descriptors.Descriptor getDescriptorForType() {
                    return internal_static_Mysqlx_Datatypes_Object_ObjectField_descriptor;
                }

                public ObjectField getDefaultInstanceForType() {
                    return ObjectField.getDefaultInstance();
                }

                public ObjectField build() {
                    ObjectField result = this.buildPartial();
                    if (!result.isInitialized()) {
                        throw Builder.newUninitializedMessageException((Message)result);
                    }
                    return result;
                }

                public ObjectField buildPartial() {
                    ObjectField result = new ObjectField(this);
                    int from_bitField0_ = this.bitField0_;
                    int to_bitField0_ = 0;
                    if ((from_bitField0_ & 1) != 0) {
                        to_bitField0_ |= 1;
                    }
                    result.key_ = this.key_;
                    if ((from_bitField0_ & 2) != 0) {
                        if (this.valueBuilder_ == null) {
                            result.value_ = this.value_;
                        } else {
                            result.value_ = (Any)this.valueBuilder_.build();
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

                public Builder setField(Descriptors.FieldDescriptor field, java.lang.Object value) {
                    return (Builder)super.setField(field, value);
                }

                public Builder clearField(Descriptors.FieldDescriptor field) {
                    return (Builder)super.clearField(field);
                }

                public Builder clearOneof(Descriptors.OneofDescriptor oneof) {
                    return (Builder)super.clearOneof(oneof);
                }

                public Builder setRepeatedField(Descriptors.FieldDescriptor field, int index, java.lang.Object value) {
                    return (Builder)super.setRepeatedField(field, index, value);
                }

                public Builder addRepeatedField(Descriptors.FieldDescriptor field, java.lang.Object value) {
                    return (Builder)super.addRepeatedField(field, value);
                }

                public Builder mergeFrom(Message other) {
                    if (other instanceof ObjectField) {
                        return this.mergeFrom((ObjectField)other);
                    }
                    super.mergeFrom(other);
                    return this;
                }

                public Builder mergeFrom(ObjectField other) {
                    if (other == ObjectField.getDefaultInstance()) {
                        return this;
                    }
                    if (other.hasKey()) {
                        this.bitField0_ |= 1;
                        this.key_ = other.key_;
                        this.onChanged();
                    }
                    if (other.hasValue()) {
                        this.mergeValue(other.getValue());
                    }
                    this.mergeUnknownFields(other.unknownFields);
                    this.onChanged();
                    return this;
                }

                public final boolean isInitialized() {
                    if (!this.hasKey()) {
                        return false;
                    }
                    if (!this.hasValue()) {
                        return false;
                    }
                    return this.getValue().isInitialized();
                }

                public Builder mergeFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                    ObjectField parsedMessage = null;
                    try {
                        parsedMessage = (ObjectField)PARSER.parsePartialFrom(input, extensionRegistry);
                    }
                    catch (InvalidProtocolBufferException e) {
                        parsedMessage = (ObjectField)e.getUnfinishedMessage();
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
                public boolean hasKey() {
                    return (this.bitField0_ & 1) != 0;
                }

                @Override
                public String getKey() {
                    java.lang.Object ref = this.key_;
                    if (!(ref instanceof String)) {
                        ByteString bs = (ByteString)ref;
                        String s = bs.toStringUtf8();
                        if (bs.isValidUtf8()) {
                            this.key_ = s;
                        }
                        return s;
                    }
                    return (String)ref;
                }

                @Override
                public ByteString getKeyBytes() {
                    java.lang.Object ref = this.key_;
                    if (ref instanceof String) {
                        ByteString b = ByteString.copyFromUtf8((String)((String)ref));
                        this.key_ = b;
                        return b;
                    }
                    return (ByteString)ref;
                }

                public Builder setKey(String value) {
                    if (value == null) {
                        throw new NullPointerException();
                    }
                    this.bitField0_ |= 1;
                    this.key_ = value;
                    this.onChanged();
                    return this;
                }

                public Builder clearKey() {
                    this.bitField0_ &= 0xFFFFFFFE;
                    this.key_ = ObjectField.getDefaultInstance().getKey();
                    this.onChanged();
                    return this;
                }

                public Builder setKeyBytes(ByteString value) {
                    if (value == null) {
                        throw new NullPointerException();
                    }
                    this.bitField0_ |= 1;
                    this.key_ = value;
                    this.onChanged();
                    return this;
                }

                @Override
                public boolean hasValue() {
                    return (this.bitField0_ & 2) != 0;
                }

                @Override
                public Any getValue() {
                    if (this.valueBuilder_ == null) {
                        return this.value_ == null ? Any.getDefaultInstance() : this.value_;
                    }
                    return (Any)this.valueBuilder_.getMessage();
                }

                public Builder setValue(Any value) {
                    if (this.valueBuilder_ == null) {
                        if (value == null) {
                            throw new NullPointerException();
                        }
                        this.value_ = value;
                        this.onChanged();
                    } else {
                        this.valueBuilder_.setMessage((AbstractMessage)value);
                    }
                    this.bitField0_ |= 2;
                    return this;
                }

                public Builder setValue(Any.Builder builderForValue) {
                    if (this.valueBuilder_ == null) {
                        this.value_ = builderForValue.build();
                        this.onChanged();
                    } else {
                        this.valueBuilder_.setMessage((AbstractMessage)builderForValue.build());
                    }
                    this.bitField0_ |= 2;
                    return this;
                }

                public Builder mergeValue(Any value) {
                    if (this.valueBuilder_ == null) {
                        this.value_ = (this.bitField0_ & 2) != 0 && this.value_ != null && this.value_ != Any.getDefaultInstance() ? Any.newBuilder(this.value_).mergeFrom(value).buildPartial() : value;
                        this.onChanged();
                    } else {
                        this.valueBuilder_.mergeFrom((AbstractMessage)value);
                    }
                    this.bitField0_ |= 2;
                    return this;
                }

                public Builder clearValue() {
                    if (this.valueBuilder_ == null) {
                        this.value_ = null;
                        this.onChanged();
                    } else {
                        this.valueBuilder_.clear();
                    }
                    this.bitField0_ &= 0xFFFFFFFD;
                    return this;
                }

                public Any.Builder getValueBuilder() {
                    this.bitField0_ |= 2;
                    this.onChanged();
                    return (Any.Builder)this.getValueFieldBuilder().getBuilder();
                }

                @Override
                public AnyOrBuilder getValueOrBuilder() {
                    if (this.valueBuilder_ != null) {
                        return (AnyOrBuilder)this.valueBuilder_.getMessageOrBuilder();
                    }
                    return this.value_ == null ? Any.getDefaultInstance() : this.value_;
                }

                private SingleFieldBuilderV3<Any, Any.Builder, AnyOrBuilder> getValueFieldBuilder() {
                    if (this.valueBuilder_ == null) {
                        this.valueBuilder_ = new SingleFieldBuilderV3((AbstractMessage)this.getValue(), (AbstractMessage.BuilderParent)this.getParentForChildren(), this.isClean());
                        this.value_ = null;
                    }
                    return this.valueBuilder_;
                }

                public final Builder setUnknownFields(UnknownFieldSet unknownFields) {
                    return (Builder)super.setUnknownFields(unknownFields);
                }

                public final Builder mergeUnknownFields(UnknownFieldSet unknownFields) {
                    return (Builder)super.mergeUnknownFields(unknownFields);
                }
            }
        }

        public static interface ObjectFieldOrBuilder
        extends MessageOrBuilder {
            public boolean hasKey();

            public String getKey();

            public ByteString getKeyBytes();

            public boolean hasValue();

            public Any getValue();

            public AnyOrBuilder getValueOrBuilder();
        }
    }

    public static interface ObjectOrBuilder
    extends MessageOrBuilder {
        public List<Object.ObjectField> getFldList();

        public Object.ObjectField getFld(int var1);

        public int getFldCount();

        public List<? extends Object.ObjectFieldOrBuilder> getFldOrBuilderList();

        public Object.ObjectFieldOrBuilder getFldOrBuilder(int var1);
    }

    public static final class Scalar
    extends GeneratedMessageV3
    implements ScalarOrBuilder {
        private static final long serialVersionUID = 0L;
        private int bitField0_;
        public static final int TYPE_FIELD_NUMBER = 1;
        private int type_;
        public static final int V_SIGNED_INT_FIELD_NUMBER = 2;
        private long vSignedInt_;
        public static final int V_UNSIGNED_INT_FIELD_NUMBER = 3;
        private long vUnsignedInt_;
        public static final int V_OCTETS_FIELD_NUMBER = 5;
        private Octets vOctets_;
        public static final int V_DOUBLE_FIELD_NUMBER = 6;
        private double vDouble_;
        public static final int V_FLOAT_FIELD_NUMBER = 7;
        private float vFloat_;
        public static final int V_BOOL_FIELD_NUMBER = 8;
        private boolean vBool_;
        public static final int V_STRING_FIELD_NUMBER = 9;
        private String vString_;
        private byte memoizedIsInitialized = (byte)-1;
        private static final Scalar DEFAULT_INSTANCE = new Scalar();
        @Deprecated
        public static final Parser<Scalar> PARSER = new AbstractParser<Scalar>(){

            public Scalar parsePartialFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                return new Scalar(input, extensionRegistry);
            }
        };

        private Scalar(GeneratedMessageV3.Builder<?> builder) {
            super(builder);
        }

        private Scalar() {
            this.type_ = 1;
        }

        protected java.lang.Object newInstance(GeneratedMessageV3.UnusedPrivateParameter unused) {
            return new Scalar();
        }

        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        private Scalar(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            this();
            if (extensionRegistry == null) {
                throw new NullPointerException();
            }
            boolean mutable_bitField0_ = false;
            UnknownFieldSet.Builder unknownFields = UnknownFieldSet.newBuilder();
            try {
                boolean done = false;
                block17: while (!done) {
                    int tag = input.readTag();
                    switch (tag) {
                        case 0: {
                            done = true;
                            continue block17;
                        }
                        case 8: {
                            int rawValue = input.readEnum();
                            Type value = Type.valueOf(rawValue);
                            if (value == null) {
                                unknownFields.mergeVarintField(1, rawValue);
                                continue block17;
                            }
                            this.bitField0_ |= 1;
                            this.type_ = rawValue;
                            continue block17;
                        }
                        case 16: {
                            this.bitField0_ |= 2;
                            this.vSignedInt_ = input.readSInt64();
                            continue block17;
                        }
                        case 24: {
                            this.bitField0_ |= 4;
                            this.vUnsignedInt_ = input.readUInt64();
                            continue block17;
                        }
                        case 42: {
                            Octets.Builder subBuilder = null;
                            if ((this.bitField0_ & 8) != 0) {
                                subBuilder = this.vOctets_.toBuilder();
                            }
                            this.vOctets_ = (Octets)input.readMessage(Octets.PARSER, extensionRegistry);
                            if (subBuilder != null) {
                                subBuilder.mergeFrom(this.vOctets_);
                                this.vOctets_ = subBuilder.buildPartial();
                            }
                            this.bitField0_ |= 8;
                            continue block17;
                        }
                        case 49: {
                            this.bitField0_ |= 0x10;
                            this.vDouble_ = input.readDouble();
                            continue block17;
                        }
                        case 61: {
                            this.bitField0_ |= 0x20;
                            this.vFloat_ = input.readFloat();
                            continue block17;
                        }
                        case 64: {
                            this.bitField0_ |= 0x40;
                            this.vBool_ = input.readBool();
                            continue block17;
                        }
                        case 74: {
                            String.Builder subBuilder = null;
                            if ((this.bitField0_ & 0x80) != 0) {
                                subBuilder = this.vString_.toBuilder();
                            }
                            this.vString_ = (String)input.readMessage(String.PARSER, extensionRegistry);
                            if (subBuilder != null) {
                                subBuilder.mergeFrom(this.vString_);
                                this.vString_ = subBuilder.buildPartial();
                            }
                            this.bitField0_ |= 0x80;
                            continue block17;
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
            return internal_static_Mysqlx_Datatypes_Scalar_descriptor;
        }

        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return internal_static_Mysqlx_Datatypes_Scalar_fieldAccessorTable.ensureFieldAccessorsInitialized(Scalar.class, Builder.class);
        }

        @Override
        public boolean hasType() {
            return (this.bitField0_ & 1) != 0;
        }

        @Override
        public Type getType() {
            Type result = Type.valueOf(this.type_);
            return result == null ? Type.V_SINT : result;
        }

        @Override
        public boolean hasVSignedInt() {
            return (this.bitField0_ & 2) != 0;
        }

        @Override
        public long getVSignedInt() {
            return this.vSignedInt_;
        }

        @Override
        public boolean hasVUnsignedInt() {
            return (this.bitField0_ & 4) != 0;
        }

        @Override
        public long getVUnsignedInt() {
            return this.vUnsignedInt_;
        }

        @Override
        public boolean hasVOctets() {
            return (this.bitField0_ & 8) != 0;
        }

        @Override
        public Octets getVOctets() {
            return this.vOctets_ == null ? Octets.getDefaultInstance() : this.vOctets_;
        }

        @Override
        public OctetsOrBuilder getVOctetsOrBuilder() {
            return this.vOctets_ == null ? Octets.getDefaultInstance() : this.vOctets_;
        }

        @Override
        public boolean hasVDouble() {
            return (this.bitField0_ & 0x10) != 0;
        }

        @Override
        public double getVDouble() {
            return this.vDouble_;
        }

        @Override
        public boolean hasVFloat() {
            return (this.bitField0_ & 0x20) != 0;
        }

        @Override
        public float getVFloat() {
            return this.vFloat_;
        }

        @Override
        public boolean hasVBool() {
            return (this.bitField0_ & 0x40) != 0;
        }

        @Override
        public boolean getVBool() {
            return this.vBool_;
        }

        @Override
        public boolean hasVString() {
            return (this.bitField0_ & 0x80) != 0;
        }

        @Override
        public String getVString() {
            return this.vString_ == null ? String.getDefaultInstance() : this.vString_;
        }

        @Override
        public StringOrBuilder getVStringOrBuilder() {
            return this.vString_ == null ? String.getDefaultInstance() : this.vString_;
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
            if (this.hasVOctets() && !this.getVOctets().isInitialized()) {
                this.memoizedIsInitialized = 0;
                return false;
            }
            if (this.hasVString() && !this.getVString().isInitialized()) {
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
                output.writeSInt64(2, this.vSignedInt_);
            }
            if ((this.bitField0_ & 4) != 0) {
                output.writeUInt64(3, this.vUnsignedInt_);
            }
            if ((this.bitField0_ & 8) != 0) {
                output.writeMessage(5, (MessageLite)this.getVOctets());
            }
            if ((this.bitField0_ & 0x10) != 0) {
                output.writeDouble(6, this.vDouble_);
            }
            if ((this.bitField0_ & 0x20) != 0) {
                output.writeFloat(7, this.vFloat_);
            }
            if ((this.bitField0_ & 0x40) != 0) {
                output.writeBool(8, this.vBool_);
            }
            if ((this.bitField0_ & 0x80) != 0) {
                output.writeMessage(9, (MessageLite)this.getVString());
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
                size += CodedOutputStream.computeSInt64Size((int)2, (long)this.vSignedInt_);
            }
            if ((this.bitField0_ & 4) != 0) {
                size += CodedOutputStream.computeUInt64Size((int)3, (long)this.vUnsignedInt_);
            }
            if ((this.bitField0_ & 8) != 0) {
                size += CodedOutputStream.computeMessageSize((int)5, (MessageLite)this.getVOctets());
            }
            if ((this.bitField0_ & 0x10) != 0) {
                size += CodedOutputStream.computeDoubleSize((int)6, (double)this.vDouble_);
            }
            if ((this.bitField0_ & 0x20) != 0) {
                size += CodedOutputStream.computeFloatSize((int)7, (float)this.vFloat_);
            }
            if ((this.bitField0_ & 0x40) != 0) {
                size += CodedOutputStream.computeBoolSize((int)8, (boolean)this.vBool_);
            }
            if ((this.bitField0_ & 0x80) != 0) {
                size += CodedOutputStream.computeMessageSize((int)9, (MessageLite)this.getVString());
            }
            this.memoizedSize = size += this.unknownFields.getSerializedSize();
            return size;
        }

        public boolean equals(java.lang.Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof Scalar)) {
                return super.equals(obj);
            }
            Scalar other = (Scalar)obj;
            if (this.hasType() != other.hasType()) {
                return false;
            }
            if (this.hasType() && this.type_ != other.type_) {
                return false;
            }
            if (this.hasVSignedInt() != other.hasVSignedInt()) {
                return false;
            }
            if (this.hasVSignedInt() && this.getVSignedInt() != other.getVSignedInt()) {
                return false;
            }
            if (this.hasVUnsignedInt() != other.hasVUnsignedInt()) {
                return false;
            }
            if (this.hasVUnsignedInt() && this.getVUnsignedInt() != other.getVUnsignedInt()) {
                return false;
            }
            if (this.hasVOctets() != other.hasVOctets()) {
                return false;
            }
            if (this.hasVOctets() && !this.getVOctets().equals(other.getVOctets())) {
                return false;
            }
            if (this.hasVDouble() != other.hasVDouble()) {
                return false;
            }
            if (this.hasVDouble() && Double.doubleToLongBits(this.getVDouble()) != Double.doubleToLongBits(other.getVDouble())) {
                return false;
            }
            if (this.hasVFloat() != other.hasVFloat()) {
                return false;
            }
            if (this.hasVFloat() && Float.floatToIntBits(this.getVFloat()) != Float.floatToIntBits(other.getVFloat())) {
                return false;
            }
            if (this.hasVBool() != other.hasVBool()) {
                return false;
            }
            if (this.hasVBool() && this.getVBool() != other.getVBool()) {
                return false;
            }
            if (this.hasVString() != other.hasVString()) {
                return false;
            }
            if (this.hasVString() && !this.getVString().equals(other.getVString())) {
                return false;
            }
            return this.unknownFields.equals((java.lang.Object)other.unknownFields);
        }

        public int hashCode() {
            if (this.memoizedHashCode != 0) {
                return this.memoizedHashCode;
            }
            int hash = 41;
            hash = 19 * hash + Scalar.getDescriptor().hashCode();
            if (this.hasType()) {
                hash = 37 * hash + 1;
                hash = 53 * hash + this.type_;
            }
            if (this.hasVSignedInt()) {
                hash = 37 * hash + 2;
                hash = 53 * hash + Internal.hashLong((long)this.getVSignedInt());
            }
            if (this.hasVUnsignedInt()) {
                hash = 37 * hash + 3;
                hash = 53 * hash + Internal.hashLong((long)this.getVUnsignedInt());
            }
            if (this.hasVOctets()) {
                hash = 37 * hash + 5;
                hash = 53 * hash + this.getVOctets().hashCode();
            }
            if (this.hasVDouble()) {
                hash = 37 * hash + 6;
                hash = 53 * hash + Internal.hashLong((long)Double.doubleToLongBits(this.getVDouble()));
            }
            if (this.hasVFloat()) {
                hash = 37 * hash + 7;
                hash = 53 * hash + Float.floatToIntBits(this.getVFloat());
            }
            if (this.hasVBool()) {
                hash = 37 * hash + 8;
                hash = 53 * hash + Internal.hashBoolean((boolean)this.getVBool());
            }
            if (this.hasVString()) {
                hash = 37 * hash + 9;
                hash = 53 * hash + this.getVString().hashCode();
            }
            this.memoizedHashCode = hash = 29 * hash + this.unknownFields.hashCode();
            return hash;
        }

        public static Scalar parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
            return (Scalar)PARSER.parseFrom(data);
        }

        public static Scalar parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (Scalar)PARSER.parseFrom(data, extensionRegistry);
        }

        public static Scalar parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return (Scalar)PARSER.parseFrom(data);
        }

        public static Scalar parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (Scalar)PARSER.parseFrom(data, extensionRegistry);
        }

        public static Scalar parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return (Scalar)PARSER.parseFrom(data);
        }

        public static Scalar parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (Scalar)PARSER.parseFrom(data, extensionRegistry);
        }

        public static Scalar parseFrom(InputStream input) throws IOException {
            return (Scalar)GeneratedMessageV3.parseWithIOException(PARSER, (InputStream)input);
        }

        public static Scalar parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (Scalar)GeneratedMessageV3.parseWithIOException(PARSER, (InputStream)input, (ExtensionRegistryLite)extensionRegistry);
        }

        public static Scalar parseDelimitedFrom(InputStream input) throws IOException {
            return (Scalar)GeneratedMessageV3.parseDelimitedWithIOException(PARSER, (InputStream)input);
        }

        public static Scalar parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (Scalar)GeneratedMessageV3.parseDelimitedWithIOException(PARSER, (InputStream)input, (ExtensionRegistryLite)extensionRegistry);
        }

        public static Scalar parseFrom(CodedInputStream input) throws IOException {
            return (Scalar)GeneratedMessageV3.parseWithIOException(PARSER, (CodedInputStream)input);
        }

        public static Scalar parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (Scalar)GeneratedMessageV3.parseWithIOException(PARSER, (CodedInputStream)input, (ExtensionRegistryLite)extensionRegistry);
        }

        public Builder newBuilderForType() {
            return Scalar.newBuilder();
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(Scalar prototype) {
            return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
        }

        public Builder toBuilder() {
            return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
        }

        protected Builder newBuilderForType(GeneratedMessageV3.BuilderParent parent) {
            Builder builder = new Builder(parent);
            return builder;
        }

        public static Scalar getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<Scalar> parser() {
            return PARSER;
        }

        public Parser<Scalar> getParserForType() {
            return PARSER;
        }

        public Scalar getDefaultInstanceForType() {
            return DEFAULT_INSTANCE;
        }

        public static final class Builder
        extends GeneratedMessageV3.Builder<Builder>
        implements ScalarOrBuilder {
            private int bitField0_;
            private int type_ = 1;
            private long vSignedInt_;
            private long vUnsignedInt_;
            private Octets vOctets_;
            private SingleFieldBuilderV3<Octets, Octets.Builder, OctetsOrBuilder> vOctetsBuilder_;
            private double vDouble_;
            private float vFloat_;
            private boolean vBool_;
            private String vString_;
            private SingleFieldBuilderV3<String, String.Builder, StringOrBuilder> vStringBuilder_;

            public static final Descriptors.Descriptor getDescriptor() {
                return internal_static_Mysqlx_Datatypes_Scalar_descriptor;
            }

            protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                return internal_static_Mysqlx_Datatypes_Scalar_fieldAccessorTable.ensureFieldAccessorsInitialized(Scalar.class, Builder.class);
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
                    this.getVOctetsFieldBuilder();
                    this.getVStringFieldBuilder();
                }
            }

            public Builder clear() {
                super.clear();
                this.type_ = 1;
                this.bitField0_ &= 0xFFFFFFFE;
                this.vSignedInt_ = 0L;
                this.bitField0_ &= 0xFFFFFFFD;
                this.vUnsignedInt_ = 0L;
                this.bitField0_ &= 0xFFFFFFFB;
                if (this.vOctetsBuilder_ == null) {
                    this.vOctets_ = null;
                } else {
                    this.vOctetsBuilder_.clear();
                }
                this.bitField0_ &= 0xFFFFFFF7;
                this.vDouble_ = 0.0;
                this.bitField0_ &= 0xFFFFFFEF;
                this.vFloat_ = 0.0f;
                this.bitField0_ &= 0xFFFFFFDF;
                this.vBool_ = false;
                this.bitField0_ &= 0xFFFFFFBF;
                if (this.vStringBuilder_ == null) {
                    this.vString_ = null;
                } else {
                    this.vStringBuilder_.clear();
                }
                this.bitField0_ &= 0xFFFFFF7F;
                return this;
            }

            public Descriptors.Descriptor getDescriptorForType() {
                return internal_static_Mysqlx_Datatypes_Scalar_descriptor;
            }

            public Scalar getDefaultInstanceForType() {
                return Scalar.getDefaultInstance();
            }

            public Scalar build() {
                Scalar result = this.buildPartial();
                if (!result.isInitialized()) {
                    throw Builder.newUninitializedMessageException((Message)result);
                }
                return result;
            }

            public Scalar buildPartial() {
                Scalar result = new Scalar(this);
                int from_bitField0_ = this.bitField0_;
                int to_bitField0_ = 0;
                if ((from_bitField0_ & 1) != 0) {
                    to_bitField0_ |= 1;
                }
                result.type_ = this.type_;
                if ((from_bitField0_ & 2) != 0) {
                    result.vSignedInt_ = this.vSignedInt_;
                    to_bitField0_ |= 2;
                }
                if ((from_bitField0_ & 4) != 0) {
                    result.vUnsignedInt_ = this.vUnsignedInt_;
                    to_bitField0_ |= 4;
                }
                if ((from_bitField0_ & 8) != 0) {
                    if (this.vOctetsBuilder_ == null) {
                        result.vOctets_ = this.vOctets_;
                    } else {
                        result.vOctets_ = (Octets)this.vOctetsBuilder_.build();
                    }
                    to_bitField0_ |= 8;
                }
                if ((from_bitField0_ & 0x10) != 0) {
                    result.vDouble_ = this.vDouble_;
                    to_bitField0_ |= 0x10;
                }
                if ((from_bitField0_ & 0x20) != 0) {
                    result.vFloat_ = this.vFloat_;
                    to_bitField0_ |= 0x20;
                }
                if ((from_bitField0_ & 0x40) != 0) {
                    result.vBool_ = this.vBool_;
                    to_bitField0_ |= 0x40;
                }
                if ((from_bitField0_ & 0x80) != 0) {
                    if (this.vStringBuilder_ == null) {
                        result.vString_ = this.vString_;
                    } else {
                        result.vString_ = (String)this.vStringBuilder_.build();
                    }
                    to_bitField0_ |= 0x80;
                }
                result.bitField0_ = to_bitField0_;
                this.onBuilt();
                return result;
            }

            public Builder clone() {
                return (Builder)super.clone();
            }

            public Builder setField(Descriptors.FieldDescriptor field, java.lang.Object value) {
                return (Builder)super.setField(field, value);
            }

            public Builder clearField(Descriptors.FieldDescriptor field) {
                return (Builder)super.clearField(field);
            }

            public Builder clearOneof(Descriptors.OneofDescriptor oneof) {
                return (Builder)super.clearOneof(oneof);
            }

            public Builder setRepeatedField(Descriptors.FieldDescriptor field, int index, java.lang.Object value) {
                return (Builder)super.setRepeatedField(field, index, value);
            }

            public Builder addRepeatedField(Descriptors.FieldDescriptor field, java.lang.Object value) {
                return (Builder)super.addRepeatedField(field, value);
            }

            public Builder mergeFrom(Message other) {
                if (other instanceof Scalar) {
                    return this.mergeFrom((Scalar)other);
                }
                super.mergeFrom(other);
                return this;
            }

            public Builder mergeFrom(Scalar other) {
                if (other == Scalar.getDefaultInstance()) {
                    return this;
                }
                if (other.hasType()) {
                    this.setType(other.getType());
                }
                if (other.hasVSignedInt()) {
                    this.setVSignedInt(other.getVSignedInt());
                }
                if (other.hasVUnsignedInt()) {
                    this.setVUnsignedInt(other.getVUnsignedInt());
                }
                if (other.hasVOctets()) {
                    this.mergeVOctets(other.getVOctets());
                }
                if (other.hasVDouble()) {
                    this.setVDouble(other.getVDouble());
                }
                if (other.hasVFloat()) {
                    this.setVFloat(other.getVFloat());
                }
                if (other.hasVBool()) {
                    this.setVBool(other.getVBool());
                }
                if (other.hasVString()) {
                    this.mergeVString(other.getVString());
                }
                this.mergeUnknownFields(other.unknownFields);
                this.onChanged();
                return this;
            }

            public final boolean isInitialized() {
                if (!this.hasType()) {
                    return false;
                }
                if (this.hasVOctets() && !this.getVOctets().isInitialized()) {
                    return false;
                }
                return !this.hasVString() || this.getVString().isInitialized();
            }

            public Builder mergeFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                Scalar parsedMessage = null;
                try {
                    parsedMessage = (Scalar)PARSER.parsePartialFrom(input, extensionRegistry);
                }
                catch (InvalidProtocolBufferException e) {
                    parsedMessage = (Scalar)e.getUnfinishedMessage();
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
                return result == null ? Type.V_SINT : result;
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
                this.type_ = 1;
                this.onChanged();
                return this;
            }

            @Override
            public boolean hasVSignedInt() {
                return (this.bitField0_ & 2) != 0;
            }

            @Override
            public long getVSignedInt() {
                return this.vSignedInt_;
            }

            public Builder setVSignedInt(long value) {
                this.bitField0_ |= 2;
                this.vSignedInt_ = value;
                this.onChanged();
                return this;
            }

            public Builder clearVSignedInt() {
                this.bitField0_ &= 0xFFFFFFFD;
                this.vSignedInt_ = 0L;
                this.onChanged();
                return this;
            }

            @Override
            public boolean hasVUnsignedInt() {
                return (this.bitField0_ & 4) != 0;
            }

            @Override
            public long getVUnsignedInt() {
                return this.vUnsignedInt_;
            }

            public Builder setVUnsignedInt(long value) {
                this.bitField0_ |= 4;
                this.vUnsignedInt_ = value;
                this.onChanged();
                return this;
            }

            public Builder clearVUnsignedInt() {
                this.bitField0_ &= 0xFFFFFFFB;
                this.vUnsignedInt_ = 0L;
                this.onChanged();
                return this;
            }

            @Override
            public boolean hasVOctets() {
                return (this.bitField0_ & 8) != 0;
            }

            @Override
            public Octets getVOctets() {
                if (this.vOctetsBuilder_ == null) {
                    return this.vOctets_ == null ? Octets.getDefaultInstance() : this.vOctets_;
                }
                return (Octets)this.vOctetsBuilder_.getMessage();
            }

            public Builder setVOctets(Octets value) {
                if (this.vOctetsBuilder_ == null) {
                    if (value == null) {
                        throw new NullPointerException();
                    }
                    this.vOctets_ = value;
                    this.onChanged();
                } else {
                    this.vOctetsBuilder_.setMessage((AbstractMessage)value);
                }
                this.bitField0_ |= 8;
                return this;
            }

            public Builder setVOctets(Octets.Builder builderForValue) {
                if (this.vOctetsBuilder_ == null) {
                    this.vOctets_ = builderForValue.build();
                    this.onChanged();
                } else {
                    this.vOctetsBuilder_.setMessage((AbstractMessage)builderForValue.build());
                }
                this.bitField0_ |= 8;
                return this;
            }

            public Builder mergeVOctets(Octets value) {
                if (this.vOctetsBuilder_ == null) {
                    this.vOctets_ = (this.bitField0_ & 8) != 0 && this.vOctets_ != null && this.vOctets_ != Octets.getDefaultInstance() ? Octets.newBuilder(this.vOctets_).mergeFrom(value).buildPartial() : value;
                    this.onChanged();
                } else {
                    this.vOctetsBuilder_.mergeFrom((AbstractMessage)value);
                }
                this.bitField0_ |= 8;
                return this;
            }

            public Builder clearVOctets() {
                if (this.vOctetsBuilder_ == null) {
                    this.vOctets_ = null;
                    this.onChanged();
                } else {
                    this.vOctetsBuilder_.clear();
                }
                this.bitField0_ &= 0xFFFFFFF7;
                return this;
            }

            public Octets.Builder getVOctetsBuilder() {
                this.bitField0_ |= 8;
                this.onChanged();
                return (Octets.Builder)this.getVOctetsFieldBuilder().getBuilder();
            }

            @Override
            public OctetsOrBuilder getVOctetsOrBuilder() {
                if (this.vOctetsBuilder_ != null) {
                    return (OctetsOrBuilder)this.vOctetsBuilder_.getMessageOrBuilder();
                }
                return this.vOctets_ == null ? Octets.getDefaultInstance() : this.vOctets_;
            }

            private SingleFieldBuilderV3<Octets, Octets.Builder, OctetsOrBuilder> getVOctetsFieldBuilder() {
                if (this.vOctetsBuilder_ == null) {
                    this.vOctetsBuilder_ = new SingleFieldBuilderV3((AbstractMessage)this.getVOctets(), (AbstractMessage.BuilderParent)this.getParentForChildren(), this.isClean());
                    this.vOctets_ = null;
                }
                return this.vOctetsBuilder_;
            }

            @Override
            public boolean hasVDouble() {
                return (this.bitField0_ & 0x10) != 0;
            }

            @Override
            public double getVDouble() {
                return this.vDouble_;
            }

            public Builder setVDouble(double value) {
                this.bitField0_ |= 0x10;
                this.vDouble_ = value;
                this.onChanged();
                return this;
            }

            public Builder clearVDouble() {
                this.bitField0_ &= 0xFFFFFFEF;
                this.vDouble_ = 0.0;
                this.onChanged();
                return this;
            }

            @Override
            public boolean hasVFloat() {
                return (this.bitField0_ & 0x20) != 0;
            }

            @Override
            public float getVFloat() {
                return this.vFloat_;
            }

            public Builder setVFloat(float value) {
                this.bitField0_ |= 0x20;
                this.vFloat_ = value;
                this.onChanged();
                return this;
            }

            public Builder clearVFloat() {
                this.bitField0_ &= 0xFFFFFFDF;
                this.vFloat_ = 0.0f;
                this.onChanged();
                return this;
            }

            @Override
            public boolean hasVBool() {
                return (this.bitField0_ & 0x40) != 0;
            }

            @Override
            public boolean getVBool() {
                return this.vBool_;
            }

            public Builder setVBool(boolean value) {
                this.bitField0_ |= 0x40;
                this.vBool_ = value;
                this.onChanged();
                return this;
            }

            public Builder clearVBool() {
                this.bitField0_ &= 0xFFFFFFBF;
                this.vBool_ = false;
                this.onChanged();
                return this;
            }

            @Override
            public boolean hasVString() {
                return (this.bitField0_ & 0x80) != 0;
            }

            @Override
            public String getVString() {
                if (this.vStringBuilder_ == null) {
                    return this.vString_ == null ? String.getDefaultInstance() : this.vString_;
                }
                return (String)this.vStringBuilder_.getMessage();
            }

            public Builder setVString(String value) {
                if (this.vStringBuilder_ == null) {
                    if (value == null) {
                        throw new NullPointerException();
                    }
                    this.vString_ = value;
                    this.onChanged();
                } else {
                    this.vStringBuilder_.setMessage((AbstractMessage)value);
                }
                this.bitField0_ |= 0x80;
                return this;
            }

            public Builder setVString(String.Builder builderForValue) {
                if (this.vStringBuilder_ == null) {
                    this.vString_ = builderForValue.build();
                    this.onChanged();
                } else {
                    this.vStringBuilder_.setMessage((AbstractMessage)builderForValue.build());
                }
                this.bitField0_ |= 0x80;
                return this;
            }

            public Builder mergeVString(String value) {
                if (this.vStringBuilder_ == null) {
                    this.vString_ = (this.bitField0_ & 0x80) != 0 && this.vString_ != null && this.vString_ != String.getDefaultInstance() ? String.newBuilder(this.vString_).mergeFrom(value).buildPartial() : value;
                    this.onChanged();
                } else {
                    this.vStringBuilder_.mergeFrom((AbstractMessage)value);
                }
                this.bitField0_ |= 0x80;
                return this;
            }

            public Builder clearVString() {
                if (this.vStringBuilder_ == null) {
                    this.vString_ = null;
                    this.onChanged();
                } else {
                    this.vStringBuilder_.clear();
                }
                this.bitField0_ &= 0xFFFFFF7F;
                return this;
            }

            public String.Builder getVStringBuilder() {
                this.bitField0_ |= 0x80;
                this.onChanged();
                return (String.Builder)this.getVStringFieldBuilder().getBuilder();
            }

            @Override
            public StringOrBuilder getVStringOrBuilder() {
                if (this.vStringBuilder_ != null) {
                    return (StringOrBuilder)this.vStringBuilder_.getMessageOrBuilder();
                }
                return this.vString_ == null ? String.getDefaultInstance() : this.vString_;
            }

            private SingleFieldBuilderV3<String, String.Builder, StringOrBuilder> getVStringFieldBuilder() {
                if (this.vStringBuilder_ == null) {
                    this.vStringBuilder_ = new SingleFieldBuilderV3((AbstractMessage)this.getVString(), (AbstractMessage.BuilderParent)this.getParentForChildren(), this.isClean());
                    this.vString_ = null;
                }
                return this.vStringBuilder_;
            }

            public final Builder setUnknownFields(UnknownFieldSet unknownFields) {
                return (Builder)super.setUnknownFields(unknownFields);
            }

            public final Builder mergeUnknownFields(UnknownFieldSet unknownFields) {
                return (Builder)super.mergeUnknownFields(unknownFields);
            }
        }

        public static final class Octets
        extends GeneratedMessageV3
        implements OctetsOrBuilder {
            private static final long serialVersionUID = 0L;
            private int bitField0_;
            public static final int VALUE_FIELD_NUMBER = 1;
            private ByteString value_;
            public static final int CONTENT_TYPE_FIELD_NUMBER = 2;
            private int contentType_;
            private byte memoizedIsInitialized = (byte)-1;
            private static final Octets DEFAULT_INSTANCE = new Octets();
            @Deprecated
            public static final Parser<Octets> PARSER = new AbstractParser<Octets>(){

                public Octets parsePartialFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                    return new Octets(input, extensionRegistry);
                }
            };

            private Octets(GeneratedMessageV3.Builder<?> builder) {
                super(builder);
            }

            private Octets() {
                this.value_ = ByteString.EMPTY;
            }

            protected java.lang.Object newInstance(GeneratedMessageV3.UnusedPrivateParameter unused) {
                return new Octets();
            }

            public final UnknownFieldSet getUnknownFields() {
                return this.unknownFields;
            }

            private Octets(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
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
                            case 10: {
                                this.bitField0_ |= 1;
                                this.value_ = input.readBytes();
                                continue block11;
                            }
                            case 16: {
                                this.bitField0_ |= 2;
                                this.contentType_ = input.readUInt32();
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
                return internal_static_Mysqlx_Datatypes_Scalar_Octets_descriptor;
            }

            protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                return internal_static_Mysqlx_Datatypes_Scalar_Octets_fieldAccessorTable.ensureFieldAccessorsInitialized(Octets.class, Builder.class);
            }

            @Override
            public boolean hasValue() {
                return (this.bitField0_ & 1) != 0;
            }

            @Override
            public ByteString getValue() {
                return this.value_;
            }

            @Override
            public boolean hasContentType() {
                return (this.bitField0_ & 2) != 0;
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
                if (!this.hasValue()) {
                    this.memoizedIsInitialized = 0;
                    return false;
                }
                this.memoizedIsInitialized = 1;
                return true;
            }

            public void writeTo(CodedOutputStream output) throws IOException {
                if ((this.bitField0_ & 1) != 0) {
                    output.writeBytes(1, this.value_);
                }
                if ((this.bitField0_ & 2) != 0) {
                    output.writeUInt32(2, this.contentType_);
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
                    size += CodedOutputStream.computeBytesSize((int)1, (ByteString)this.value_);
                }
                if ((this.bitField0_ & 2) != 0) {
                    size += CodedOutputStream.computeUInt32Size((int)2, (int)this.contentType_);
                }
                this.memoizedSize = size += this.unknownFields.getSerializedSize();
                return size;
            }

            public boolean equals(java.lang.Object obj) {
                if (obj == this) {
                    return true;
                }
                if (!(obj instanceof Octets)) {
                    return super.equals(obj);
                }
                Octets other = (Octets)obj;
                if (this.hasValue() != other.hasValue()) {
                    return false;
                }
                if (this.hasValue() && !this.getValue().equals((java.lang.Object)other.getValue())) {
                    return false;
                }
                if (this.hasContentType() != other.hasContentType()) {
                    return false;
                }
                if (this.hasContentType() && this.getContentType() != other.getContentType()) {
                    return false;
                }
                return this.unknownFields.equals((java.lang.Object)other.unknownFields);
            }

            public int hashCode() {
                if (this.memoizedHashCode != 0) {
                    return this.memoizedHashCode;
                }
                int hash = 41;
                hash = 19 * hash + Octets.getDescriptor().hashCode();
                if (this.hasValue()) {
                    hash = 37 * hash + 1;
                    hash = 53 * hash + this.getValue().hashCode();
                }
                if (this.hasContentType()) {
                    hash = 37 * hash + 2;
                    hash = 53 * hash + this.getContentType();
                }
                this.memoizedHashCode = hash = 29 * hash + this.unknownFields.hashCode();
                return hash;
            }

            public static Octets parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
                return (Octets)PARSER.parseFrom(data);
            }

            public static Octets parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                return (Octets)PARSER.parseFrom(data, extensionRegistry);
            }

            public static Octets parseFrom(ByteString data) throws InvalidProtocolBufferException {
                return (Octets)PARSER.parseFrom(data);
            }

            public static Octets parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                return (Octets)PARSER.parseFrom(data, extensionRegistry);
            }

            public static Octets parseFrom(byte[] data) throws InvalidProtocolBufferException {
                return (Octets)PARSER.parseFrom(data);
            }

            public static Octets parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                return (Octets)PARSER.parseFrom(data, extensionRegistry);
            }

            public static Octets parseFrom(InputStream input) throws IOException {
                return (Octets)GeneratedMessageV3.parseWithIOException(PARSER, (InputStream)input);
            }

            public static Octets parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                return (Octets)GeneratedMessageV3.parseWithIOException(PARSER, (InputStream)input, (ExtensionRegistryLite)extensionRegistry);
            }

            public static Octets parseDelimitedFrom(InputStream input) throws IOException {
                return (Octets)GeneratedMessageV3.parseDelimitedWithIOException(PARSER, (InputStream)input);
            }

            public static Octets parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                return (Octets)GeneratedMessageV3.parseDelimitedWithIOException(PARSER, (InputStream)input, (ExtensionRegistryLite)extensionRegistry);
            }

            public static Octets parseFrom(CodedInputStream input) throws IOException {
                return (Octets)GeneratedMessageV3.parseWithIOException(PARSER, (CodedInputStream)input);
            }

            public static Octets parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                return (Octets)GeneratedMessageV3.parseWithIOException(PARSER, (CodedInputStream)input, (ExtensionRegistryLite)extensionRegistry);
            }

            public Builder newBuilderForType() {
                return Octets.newBuilder();
            }

            public static Builder newBuilder() {
                return DEFAULT_INSTANCE.toBuilder();
            }

            public static Builder newBuilder(Octets prototype) {
                return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
            }

            public Builder toBuilder() {
                return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
            }

            protected Builder newBuilderForType(GeneratedMessageV3.BuilderParent parent) {
                Builder builder = new Builder(parent);
                return builder;
            }

            public static Octets getDefaultInstance() {
                return DEFAULT_INSTANCE;
            }

            public static Parser<Octets> parser() {
                return PARSER;
            }

            public Parser<Octets> getParserForType() {
                return PARSER;
            }

            public Octets getDefaultInstanceForType() {
                return DEFAULT_INSTANCE;
            }

            public static final class Builder
            extends GeneratedMessageV3.Builder<Builder>
            implements OctetsOrBuilder {
                private int bitField0_;
                private ByteString value_ = ByteString.EMPTY;
                private int contentType_;

                public static final Descriptors.Descriptor getDescriptor() {
                    return internal_static_Mysqlx_Datatypes_Scalar_Octets_descriptor;
                }

                protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                    return internal_static_Mysqlx_Datatypes_Scalar_Octets_fieldAccessorTable.ensureFieldAccessorsInitialized(Octets.class, Builder.class);
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
                    this.value_ = ByteString.EMPTY;
                    this.bitField0_ &= 0xFFFFFFFE;
                    this.contentType_ = 0;
                    this.bitField0_ &= 0xFFFFFFFD;
                    return this;
                }

                public Descriptors.Descriptor getDescriptorForType() {
                    return internal_static_Mysqlx_Datatypes_Scalar_Octets_descriptor;
                }

                public Octets getDefaultInstanceForType() {
                    return Octets.getDefaultInstance();
                }

                public Octets build() {
                    Octets result = this.buildPartial();
                    if (!result.isInitialized()) {
                        throw Builder.newUninitializedMessageException((Message)result);
                    }
                    return result;
                }

                public Octets buildPartial() {
                    Octets result = new Octets(this);
                    int from_bitField0_ = this.bitField0_;
                    int to_bitField0_ = 0;
                    if ((from_bitField0_ & 1) != 0) {
                        to_bitField0_ |= 1;
                    }
                    result.value_ = this.value_;
                    if ((from_bitField0_ & 2) != 0) {
                        result.contentType_ = this.contentType_;
                        to_bitField0_ |= 2;
                    }
                    result.bitField0_ = to_bitField0_;
                    this.onBuilt();
                    return result;
                }

                public Builder clone() {
                    return (Builder)super.clone();
                }

                public Builder setField(Descriptors.FieldDescriptor field, java.lang.Object value) {
                    return (Builder)super.setField(field, value);
                }

                public Builder clearField(Descriptors.FieldDescriptor field) {
                    return (Builder)super.clearField(field);
                }

                public Builder clearOneof(Descriptors.OneofDescriptor oneof) {
                    return (Builder)super.clearOneof(oneof);
                }

                public Builder setRepeatedField(Descriptors.FieldDescriptor field, int index, java.lang.Object value) {
                    return (Builder)super.setRepeatedField(field, index, value);
                }

                public Builder addRepeatedField(Descriptors.FieldDescriptor field, java.lang.Object value) {
                    return (Builder)super.addRepeatedField(field, value);
                }

                public Builder mergeFrom(Message other) {
                    if (other instanceof Octets) {
                        return this.mergeFrom((Octets)other);
                    }
                    super.mergeFrom(other);
                    return this;
                }

                public Builder mergeFrom(Octets other) {
                    if (other == Octets.getDefaultInstance()) {
                        return this;
                    }
                    if (other.hasValue()) {
                        this.setValue(other.getValue());
                    }
                    if (other.hasContentType()) {
                        this.setContentType(other.getContentType());
                    }
                    this.mergeUnknownFields(other.unknownFields);
                    this.onChanged();
                    return this;
                }

                public final boolean isInitialized() {
                    return this.hasValue();
                }

                public Builder mergeFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                    Octets parsedMessage = null;
                    try {
                        parsedMessage = (Octets)PARSER.parsePartialFrom(input, extensionRegistry);
                    }
                    catch (InvalidProtocolBufferException e) {
                        parsedMessage = (Octets)e.getUnfinishedMessage();
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
                public boolean hasValue() {
                    return (this.bitField0_ & 1) != 0;
                }

                @Override
                public ByteString getValue() {
                    return this.value_;
                }

                public Builder setValue(ByteString value) {
                    if (value == null) {
                        throw new NullPointerException();
                    }
                    this.bitField0_ |= 1;
                    this.value_ = value;
                    this.onChanged();
                    return this;
                }

                public Builder clearValue() {
                    this.bitField0_ &= 0xFFFFFFFE;
                    this.value_ = Octets.getDefaultInstance().getValue();
                    this.onChanged();
                    return this;
                }

                @Override
                public boolean hasContentType() {
                    return (this.bitField0_ & 2) != 0;
                }

                @Override
                public int getContentType() {
                    return this.contentType_;
                }

                public Builder setContentType(int value) {
                    this.bitField0_ |= 2;
                    this.contentType_ = value;
                    this.onChanged();
                    return this;
                }

                public Builder clearContentType() {
                    this.bitField0_ &= 0xFFFFFFFD;
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
        }

        public static interface OctetsOrBuilder
        extends MessageOrBuilder {
            public boolean hasValue();

            public ByteString getValue();

            public boolean hasContentType();

            public int getContentType();
        }

        public static final class String
        extends GeneratedMessageV3
        implements StringOrBuilder {
            private static final long serialVersionUID = 0L;
            private int bitField0_;
            public static final int VALUE_FIELD_NUMBER = 1;
            private ByteString value_;
            public static final int COLLATION_FIELD_NUMBER = 2;
            private long collation_;
            private byte memoizedIsInitialized = (byte)-1;
            private static final String DEFAULT_INSTANCE = new String();
            @Deprecated
            public static final Parser<String> PARSER = new AbstractParser<String>(){

                public String parsePartialFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                    return new String(input, extensionRegistry);
                }
            };

            private String(GeneratedMessageV3.Builder<?> builder) {
                super(builder);
            }

            private String() {
                this.value_ = ByteString.EMPTY;
            }

            protected java.lang.Object newInstance(GeneratedMessageV3.UnusedPrivateParameter unused) {
                return new String();
            }

            public final UnknownFieldSet getUnknownFields() {
                return this.unknownFields;
            }

            private String(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
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
                            case 10: {
                                this.bitField0_ |= 1;
                                this.value_ = input.readBytes();
                                continue block11;
                            }
                            case 16: {
                                this.bitField0_ |= 2;
                                this.collation_ = input.readUInt64();
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
                return internal_static_Mysqlx_Datatypes_Scalar_String_descriptor;
            }

            protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                return internal_static_Mysqlx_Datatypes_Scalar_String_fieldAccessorTable.ensureFieldAccessorsInitialized(String.class, Builder.class);
            }

            @Override
            public boolean hasValue() {
                return (this.bitField0_ & 1) != 0;
            }

            @Override
            public ByteString getValue() {
                return this.value_;
            }

            @Override
            public boolean hasCollation() {
                return (this.bitField0_ & 2) != 0;
            }

            @Override
            public long getCollation() {
                return this.collation_;
            }

            public final boolean isInitialized() {
                byte isInitialized = this.memoizedIsInitialized;
                if (isInitialized == 1) {
                    return true;
                }
                if (isInitialized == 0) {
                    return false;
                }
                if (!this.hasValue()) {
                    this.memoizedIsInitialized = 0;
                    return false;
                }
                this.memoizedIsInitialized = 1;
                return true;
            }

            public void writeTo(CodedOutputStream output) throws IOException {
                if ((this.bitField0_ & 1) != 0) {
                    output.writeBytes(1, this.value_);
                }
                if ((this.bitField0_ & 2) != 0) {
                    output.writeUInt64(2, this.collation_);
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
                    size += CodedOutputStream.computeBytesSize((int)1, (ByteString)this.value_);
                }
                if ((this.bitField0_ & 2) != 0) {
                    size += CodedOutputStream.computeUInt64Size((int)2, (long)this.collation_);
                }
                this.memoizedSize = size += this.unknownFields.getSerializedSize();
                return size;
            }

            public boolean equals(java.lang.Object obj) {
                if (obj == this) {
                    return true;
                }
                if (!(obj instanceof String)) {
                    return super.equals(obj);
                }
                String other = (String)obj;
                if (this.hasValue() != other.hasValue()) {
                    return false;
                }
                if (this.hasValue() && !this.getValue().equals((java.lang.Object)other.getValue())) {
                    return false;
                }
                if (this.hasCollation() != other.hasCollation()) {
                    return false;
                }
                if (this.hasCollation() && this.getCollation() != other.getCollation()) {
                    return false;
                }
                return this.unknownFields.equals((java.lang.Object)other.unknownFields);
            }

            public int hashCode() {
                if (this.memoizedHashCode != 0) {
                    return this.memoizedHashCode;
                }
                int hash = 41;
                hash = 19 * hash + String.getDescriptor().hashCode();
                if (this.hasValue()) {
                    hash = 37 * hash + 1;
                    hash = 53 * hash + this.getValue().hashCode();
                }
                if (this.hasCollation()) {
                    hash = 37 * hash + 2;
                    hash = 53 * hash + Internal.hashLong((long)this.getCollation());
                }
                this.memoizedHashCode = hash = 29 * hash + this.unknownFields.hashCode();
                return hash;
            }

            public static String parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
                return (String)PARSER.parseFrom(data);
            }

            public static String parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                return (String)PARSER.parseFrom(data, extensionRegistry);
            }

            public static String parseFrom(ByteString data) throws InvalidProtocolBufferException {
                return (String)PARSER.parseFrom(data);
            }

            public static String parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                return (String)PARSER.parseFrom(data, extensionRegistry);
            }

            public static String parseFrom(byte[] data) throws InvalidProtocolBufferException {
                return (String)PARSER.parseFrom(data);
            }

            public static String parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                return (String)PARSER.parseFrom(data, extensionRegistry);
            }

            public static String parseFrom(InputStream input) throws IOException {
                return (String)GeneratedMessageV3.parseWithIOException(PARSER, (InputStream)input);
            }

            public static String parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                return (String)GeneratedMessageV3.parseWithIOException(PARSER, (InputStream)input, (ExtensionRegistryLite)extensionRegistry);
            }

            public static String parseDelimitedFrom(InputStream input) throws IOException {
                return (String)GeneratedMessageV3.parseDelimitedWithIOException(PARSER, (InputStream)input);
            }

            public static String parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                return (String)GeneratedMessageV3.parseDelimitedWithIOException(PARSER, (InputStream)input, (ExtensionRegistryLite)extensionRegistry);
            }

            public static String parseFrom(CodedInputStream input) throws IOException {
                return (String)GeneratedMessageV3.parseWithIOException(PARSER, (CodedInputStream)input);
            }

            public static String parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                return (String)GeneratedMessageV3.parseWithIOException(PARSER, (CodedInputStream)input, (ExtensionRegistryLite)extensionRegistry);
            }

            public Builder newBuilderForType() {
                return String.newBuilder();
            }

            public static Builder newBuilder() {
                return DEFAULT_INSTANCE.toBuilder();
            }

            public static Builder newBuilder(String prototype) {
                return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
            }

            public Builder toBuilder() {
                return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
            }

            protected Builder newBuilderForType(GeneratedMessageV3.BuilderParent parent) {
                Builder builder = new Builder(parent);
                return builder;
            }

            public static String getDefaultInstance() {
                return DEFAULT_INSTANCE;
            }

            public static Parser<String> parser() {
                return PARSER;
            }

            public Parser<String> getParserForType() {
                return PARSER;
            }

            public String getDefaultInstanceForType() {
                return DEFAULT_INSTANCE;
            }

            public static final class Builder
            extends GeneratedMessageV3.Builder<Builder>
            implements StringOrBuilder {
                private int bitField0_;
                private ByteString value_ = ByteString.EMPTY;
                private long collation_;

                public static final Descriptors.Descriptor getDescriptor() {
                    return internal_static_Mysqlx_Datatypes_Scalar_String_descriptor;
                }

                protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                    return internal_static_Mysqlx_Datatypes_Scalar_String_fieldAccessorTable.ensureFieldAccessorsInitialized(String.class, Builder.class);
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
                    this.value_ = ByteString.EMPTY;
                    this.bitField0_ &= 0xFFFFFFFE;
                    this.collation_ = 0L;
                    this.bitField0_ &= 0xFFFFFFFD;
                    return this;
                }

                public Descriptors.Descriptor getDescriptorForType() {
                    return internal_static_Mysqlx_Datatypes_Scalar_String_descriptor;
                }

                public String getDefaultInstanceForType() {
                    return String.getDefaultInstance();
                }

                public String build() {
                    String result = this.buildPartial();
                    if (!result.isInitialized()) {
                        throw Builder.newUninitializedMessageException((Message)result);
                    }
                    return result;
                }

                public String buildPartial() {
                    String result = new String(this);
                    int from_bitField0_ = this.bitField0_;
                    int to_bitField0_ = 0;
                    if ((from_bitField0_ & 1) != 0) {
                        to_bitField0_ |= 1;
                    }
                    result.value_ = this.value_;
                    if ((from_bitField0_ & 2) != 0) {
                        result.collation_ = this.collation_;
                        to_bitField0_ |= 2;
                    }
                    result.bitField0_ = to_bitField0_;
                    this.onBuilt();
                    return result;
                }

                public Builder clone() {
                    return (Builder)super.clone();
                }

                public Builder setField(Descriptors.FieldDescriptor field, java.lang.Object value) {
                    return (Builder)super.setField(field, value);
                }

                public Builder clearField(Descriptors.FieldDescriptor field) {
                    return (Builder)super.clearField(field);
                }

                public Builder clearOneof(Descriptors.OneofDescriptor oneof) {
                    return (Builder)super.clearOneof(oneof);
                }

                public Builder setRepeatedField(Descriptors.FieldDescriptor field, int index, java.lang.Object value) {
                    return (Builder)super.setRepeatedField(field, index, value);
                }

                public Builder addRepeatedField(Descriptors.FieldDescriptor field, java.lang.Object value) {
                    return (Builder)super.addRepeatedField(field, value);
                }

                public Builder mergeFrom(Message other) {
                    if (other instanceof String) {
                        return this.mergeFrom((String)other);
                    }
                    super.mergeFrom(other);
                    return this;
                }

                public Builder mergeFrom(String other) {
                    if (other == String.getDefaultInstance()) {
                        return this;
                    }
                    if (other.hasValue()) {
                        this.setValue(other.getValue());
                    }
                    if (other.hasCollation()) {
                        this.setCollation(other.getCollation());
                    }
                    this.mergeUnknownFields(other.unknownFields);
                    this.onChanged();
                    return this;
                }

                public final boolean isInitialized() {
                    return this.hasValue();
                }

                public Builder mergeFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                    String parsedMessage = null;
                    try {
                        parsedMessage = (String)PARSER.parsePartialFrom(input, extensionRegistry);
                    }
                    catch (InvalidProtocolBufferException e) {
                        parsedMessage = (String)e.getUnfinishedMessage();
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
                public boolean hasValue() {
                    return (this.bitField0_ & 1) != 0;
                }

                @Override
                public ByteString getValue() {
                    return this.value_;
                }

                public Builder setValue(ByteString value) {
                    if (value == null) {
                        throw new NullPointerException();
                    }
                    this.bitField0_ |= 1;
                    this.value_ = value;
                    this.onChanged();
                    return this;
                }

                public Builder clearValue() {
                    this.bitField0_ &= 0xFFFFFFFE;
                    this.value_ = String.getDefaultInstance().getValue();
                    this.onChanged();
                    return this;
                }

                @Override
                public boolean hasCollation() {
                    return (this.bitField0_ & 2) != 0;
                }

                @Override
                public long getCollation() {
                    return this.collation_;
                }

                public Builder setCollation(long value) {
                    this.bitField0_ |= 2;
                    this.collation_ = value;
                    this.onChanged();
                    return this;
                }

                public Builder clearCollation() {
                    this.bitField0_ &= 0xFFFFFFFD;
                    this.collation_ = 0L;
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

        public static interface StringOrBuilder
        extends MessageOrBuilder {
            public boolean hasValue();

            public ByteString getValue();

            public boolean hasCollation();

            public long getCollation();
        }

        public static enum Type implements ProtocolMessageEnum
        {
            V_SINT(1),
            V_UINT(2),
            V_NULL(3),
            V_OCTETS(4),
            V_DOUBLE(5),
            V_FLOAT(6),
            V_BOOL(7),
            V_STRING(8);

            public static final int V_SINT_VALUE = 1;
            public static final int V_UINT_VALUE = 2;
            public static final int V_NULL_VALUE = 3;
            public static final int V_OCTETS_VALUE = 4;
            public static final int V_DOUBLE_VALUE = 5;
            public static final int V_FLOAT_VALUE = 6;
            public static final int V_BOOL_VALUE = 7;
            public static final int V_STRING_VALUE = 8;
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
                    case 1: {
                        return V_SINT;
                    }
                    case 2: {
                        return V_UINT;
                    }
                    case 3: {
                        return V_NULL;
                    }
                    case 4: {
                        return V_OCTETS;
                    }
                    case 5: {
                        return V_DOUBLE;
                    }
                    case 6: {
                        return V_FLOAT;
                    }
                    case 7: {
                        return V_BOOL;
                    }
                    case 8: {
                        return V_STRING;
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
                return (Descriptors.EnumDescriptor)Scalar.getDescriptor().getEnumTypes().get(0);
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

    public static interface ScalarOrBuilder
    extends MessageOrBuilder {
        public boolean hasType();

        public Scalar.Type getType();

        public boolean hasVSignedInt();

        public long getVSignedInt();

        public boolean hasVUnsignedInt();

        public long getVUnsignedInt();

        public boolean hasVOctets();

        public Scalar.Octets getVOctets();

        public Scalar.OctetsOrBuilder getVOctetsOrBuilder();

        public boolean hasVDouble();

        public double getVDouble();

        public boolean hasVFloat();

        public float getVFloat();

        public boolean hasVBool();

        public boolean getVBool();

        public boolean hasVString();

        public Scalar.String getVString();

        public Scalar.StringOrBuilder getVStringOrBuilder();
    }
}

