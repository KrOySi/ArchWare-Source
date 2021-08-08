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
import com.mysql.cj.x.protobuf.MysqlxDatatypes;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class MysqlxExpr {
    private static final Descriptors.Descriptor internal_static_Mysqlx_Expr_Expr_descriptor;
    private static final GeneratedMessageV3.FieldAccessorTable internal_static_Mysqlx_Expr_Expr_fieldAccessorTable;
    private static final Descriptors.Descriptor internal_static_Mysqlx_Expr_Identifier_descriptor;
    private static final GeneratedMessageV3.FieldAccessorTable internal_static_Mysqlx_Expr_Identifier_fieldAccessorTable;
    private static final Descriptors.Descriptor internal_static_Mysqlx_Expr_DocumentPathItem_descriptor;
    private static final GeneratedMessageV3.FieldAccessorTable internal_static_Mysqlx_Expr_DocumentPathItem_fieldAccessorTable;
    private static final Descriptors.Descriptor internal_static_Mysqlx_Expr_ColumnIdentifier_descriptor;
    private static final GeneratedMessageV3.FieldAccessorTable internal_static_Mysqlx_Expr_ColumnIdentifier_fieldAccessorTable;
    private static final Descriptors.Descriptor internal_static_Mysqlx_Expr_FunctionCall_descriptor;
    private static final GeneratedMessageV3.FieldAccessorTable internal_static_Mysqlx_Expr_FunctionCall_fieldAccessorTable;
    private static final Descriptors.Descriptor internal_static_Mysqlx_Expr_Operator_descriptor;
    private static final GeneratedMessageV3.FieldAccessorTable internal_static_Mysqlx_Expr_Operator_fieldAccessorTable;
    private static final Descriptors.Descriptor internal_static_Mysqlx_Expr_Object_descriptor;
    private static final GeneratedMessageV3.FieldAccessorTable internal_static_Mysqlx_Expr_Object_fieldAccessorTable;
    private static final Descriptors.Descriptor internal_static_Mysqlx_Expr_Object_ObjectField_descriptor;
    private static final GeneratedMessageV3.FieldAccessorTable internal_static_Mysqlx_Expr_Object_ObjectField_fieldAccessorTable;
    private static final Descriptors.Descriptor internal_static_Mysqlx_Expr_Array_descriptor;
    private static final GeneratedMessageV3.FieldAccessorTable internal_static_Mysqlx_Expr_Array_fieldAccessorTable;
    private static Descriptors.FileDescriptor descriptor;

    private MysqlxExpr() {
    }

    public static void registerAllExtensions(ExtensionRegistryLite registry) {
    }

    public static void registerAllExtensions(ExtensionRegistry registry) {
        MysqlxExpr.registerAllExtensions((ExtensionRegistryLite)registry);
    }

    public static Descriptors.FileDescriptor getDescriptor() {
        return descriptor;
    }

    static {
        String[] descriptorData = new String[]{"\n\u0011mysqlx_expr.proto\u0012\u000bMysqlx.Expr\u001a\u0016mysqlx_datatypes.proto\"\u00c4\u0003\n\u0004Expr\u0012$\n\u0004type\u0018\u0001 \u0002(\u000e2\u0016.Mysqlx.Expr.Expr.Type\u00121\n\nidentifier\u0018\u0002 \u0001(\u000b2\u001d.Mysqlx.Expr.ColumnIdentifier\u0012\u0010\n\bvariable\u0018\u0003 \u0001(\t\u0012)\n\u0007literal\u0018\u0004 \u0001(\u000b2\u0018.Mysqlx.Datatypes.Scalar\u00120\n\rfunction_call\u0018\u0005 \u0001(\u000b2\u0019.Mysqlx.Expr.FunctionCall\u0012'\n\boperator\u0018\u0006 \u0001(\u000b2\u0015.Mysqlx.Expr.Operator\u0012\u0010\n\bposition\u0018\u0007 \u0001(\r\u0012#\n\u0006object\u0018\b \u0001(\u000b2\u0013.Mysqlx.Expr.Object\u0012!\n\u0005array\u0018\t \u0001(\u000b2\u0012.Mysqlx.Expr.Array\"q\n\u0004Type\u0012\t\n\u0005IDENT\u0010\u0001\u0012\u000b\n\u0007LITERAL\u0010\u0002\u0012\f\n\bVARIABLE\u0010\u0003\u0012\r\n\tFUNC_CALL\u0010\u0004\u0012\f\n\bOPERATOR\u0010\u0005\u0012\u000f\n\u000bPLACEHOLDER\u0010\u0006\u0012\n\n\u0006OBJECT\u0010\u0007\u0012\t\n\u0005ARRAY\u0010\b\"/\n\nIdentifier\u0012\f\n\u0004name\u0018\u0001 \u0002(\t\u0012\u0013\n\u000bschema_name\u0018\u0002 \u0001(\t\"\u00cb\u0001\n\u0010DocumentPathItem\u00120\n\u0004type\u0018\u0001 \u0002(\u000e2\".Mysqlx.Expr.DocumentPathItem.Type\u0012\r\n\u0005value\u0018\u0002 \u0001(\t\u0012\r\n\u0005index\u0018\u0003 \u0001(\r\"g\n\u0004Type\u0012\n\n\u0006MEMBER\u0010\u0001\u0012\u0013\n\u000fMEMBER_ASTERISK\u0010\u0002\u0012\u000f\n\u000bARRAY_INDEX\u0010\u0003\u0012\u0018\n\u0014ARRAY_INDEX_ASTERISK\u0010\u0004\u0012\u0013\n\u000fDOUBLE_ASTERISK\u0010\u0005\"\u007f\n\u0010ColumnIdentifier\u00124\n\rdocument_path\u0018\u0001 \u0003(\u000b2\u001d.Mysqlx.Expr.DocumentPathItem\u0012\f\n\u0004name\u0018\u0002 \u0001(\t\u0012\u0012\n\ntable_name\u0018\u0003 \u0001(\t\u0012\u0013\n\u000bschema_name\u0018\u0004 \u0001(\t\"W\n\fFunctionCall\u0012%\n\u0004name\u0018\u0001 \u0002(\u000b2\u0017.Mysqlx.Expr.Identifier\u0012 \n\u0005param\u0018\u0002 \u0003(\u000b2\u0011.Mysqlx.Expr.Expr\":\n\bOperator\u0012\f\n\u0004name\u0018\u0001 \u0002(\t\u0012 \n\u0005param\u0018\u0002 \u0003(\u000b2\u0011.Mysqlx.Expr.Expr\"t\n\u0006Object\u0012,\n\u0003fld\u0018\u0001 \u0003(\u000b2\u001f.Mysqlx.Expr.Object.ObjectField\u001a<\n\u000bObjectField\u0012\u000b\n\u0003key\u0018\u0001 \u0002(\t\u0012 \n\u0005value\u0018\u0002 \u0002(\u000b2\u0011.Mysqlx.Expr.Expr\")\n\u0005Array\u0012 \n\u0005value\u0018\u0001 \u0003(\u000b2\u0011.Mysqlx.Expr.ExprB\u0019\n\u0017com.mysql.cj.x.protobuf"};
        descriptor = Descriptors.FileDescriptor.internalBuildGeneratedFileFrom((String[])descriptorData, (Descriptors.FileDescriptor[])new Descriptors.FileDescriptor[]{MysqlxDatatypes.getDescriptor()});
        internal_static_Mysqlx_Expr_Expr_descriptor = (Descriptors.Descriptor)MysqlxExpr.getDescriptor().getMessageTypes().get(0);
        internal_static_Mysqlx_Expr_Expr_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(internal_static_Mysqlx_Expr_Expr_descriptor, new String[]{"Type", "Identifier", "Variable", "Literal", "FunctionCall", "Operator", "Position", "Object", "Array"});
        internal_static_Mysqlx_Expr_Identifier_descriptor = (Descriptors.Descriptor)MysqlxExpr.getDescriptor().getMessageTypes().get(1);
        internal_static_Mysqlx_Expr_Identifier_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(internal_static_Mysqlx_Expr_Identifier_descriptor, new String[]{"Name", "SchemaName"});
        internal_static_Mysqlx_Expr_DocumentPathItem_descriptor = (Descriptors.Descriptor)MysqlxExpr.getDescriptor().getMessageTypes().get(2);
        internal_static_Mysqlx_Expr_DocumentPathItem_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(internal_static_Mysqlx_Expr_DocumentPathItem_descriptor, new String[]{"Type", "Value", "Index"});
        internal_static_Mysqlx_Expr_ColumnIdentifier_descriptor = (Descriptors.Descriptor)MysqlxExpr.getDescriptor().getMessageTypes().get(3);
        internal_static_Mysqlx_Expr_ColumnIdentifier_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(internal_static_Mysqlx_Expr_ColumnIdentifier_descriptor, new String[]{"DocumentPath", "Name", "TableName", "SchemaName"});
        internal_static_Mysqlx_Expr_FunctionCall_descriptor = (Descriptors.Descriptor)MysqlxExpr.getDescriptor().getMessageTypes().get(4);
        internal_static_Mysqlx_Expr_FunctionCall_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(internal_static_Mysqlx_Expr_FunctionCall_descriptor, new String[]{"Name", "Param"});
        internal_static_Mysqlx_Expr_Operator_descriptor = (Descriptors.Descriptor)MysqlxExpr.getDescriptor().getMessageTypes().get(5);
        internal_static_Mysqlx_Expr_Operator_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(internal_static_Mysqlx_Expr_Operator_descriptor, new String[]{"Name", "Param"});
        internal_static_Mysqlx_Expr_Object_descriptor = (Descriptors.Descriptor)MysqlxExpr.getDescriptor().getMessageTypes().get(6);
        internal_static_Mysqlx_Expr_Object_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(internal_static_Mysqlx_Expr_Object_descriptor, new String[]{"Fld"});
        internal_static_Mysqlx_Expr_Object_ObjectField_descriptor = (Descriptors.Descriptor)internal_static_Mysqlx_Expr_Object_descriptor.getNestedTypes().get(0);
        internal_static_Mysqlx_Expr_Object_ObjectField_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(internal_static_Mysqlx_Expr_Object_ObjectField_descriptor, new String[]{"Key", "Value"});
        internal_static_Mysqlx_Expr_Array_descriptor = (Descriptors.Descriptor)MysqlxExpr.getDescriptor().getMessageTypes().get(7);
        internal_static_Mysqlx_Expr_Array_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(internal_static_Mysqlx_Expr_Array_descriptor, new String[]{"Value"});
        MysqlxDatatypes.getDescriptor();
    }

    public static final class Array
    extends GeneratedMessageV3
    implements ArrayOrBuilder {
        private static final long serialVersionUID = 0L;
        public static final int VALUE_FIELD_NUMBER = 1;
        private List<Expr> value_;
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
                                this.value_ = new ArrayList<Expr>();
                                mutable_bitField0_ |= true;
                            }
                            this.value_.add((Expr)input.readMessage(Expr.PARSER, extensionRegistry));
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
            return internal_static_Mysqlx_Expr_Array_descriptor;
        }

        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return internal_static_Mysqlx_Expr_Array_fieldAccessorTable.ensureFieldAccessorsInitialized(Array.class, Builder.class);
        }

        @Override
        public List<Expr> getValueList() {
            return this.value_;
        }

        @Override
        public List<? extends ExprOrBuilder> getValueOrBuilderList() {
            return this.value_;
        }

        @Override
        public int getValueCount() {
            return this.value_.size();
        }

        @Override
        public Expr getValue(int index) {
            return this.value_.get(index);
        }

        @Override
        public ExprOrBuilder getValueOrBuilder(int index) {
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
            private List<Expr> value_ = Collections.emptyList();
            private RepeatedFieldBuilderV3<Expr, Expr.Builder, ExprOrBuilder> valueBuilder_;

            public static final Descriptors.Descriptor getDescriptor() {
                return internal_static_Mysqlx_Expr_Array_descriptor;
            }

            protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                return internal_static_Mysqlx_Expr_Array_fieldAccessorTable.ensureFieldAccessorsInitialized(Array.class, Builder.class);
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
                return internal_static_Mysqlx_Expr_Array_descriptor;
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
                    this.value_ = new ArrayList<Expr>(this.value_);
                    this.bitField0_ |= 1;
                }
            }

            @Override
            public List<Expr> getValueList() {
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
            public Expr getValue(int index) {
                if (this.valueBuilder_ == null) {
                    return this.value_.get(index);
                }
                return (Expr)this.valueBuilder_.getMessage(index);
            }

            public Builder setValue(int index, Expr value) {
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

            public Builder setValue(int index, Expr.Builder builderForValue) {
                if (this.valueBuilder_ == null) {
                    this.ensureValueIsMutable();
                    this.value_.set(index, builderForValue.build());
                    this.onChanged();
                } else {
                    this.valueBuilder_.setMessage(index, (AbstractMessage)builderForValue.build());
                }
                return this;
            }

            public Builder addValue(Expr value) {
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

            public Builder addValue(int index, Expr value) {
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

            public Builder addValue(Expr.Builder builderForValue) {
                if (this.valueBuilder_ == null) {
                    this.ensureValueIsMutable();
                    this.value_.add(builderForValue.build());
                    this.onChanged();
                } else {
                    this.valueBuilder_.addMessage((AbstractMessage)builderForValue.build());
                }
                return this;
            }

            public Builder addValue(int index, Expr.Builder builderForValue) {
                if (this.valueBuilder_ == null) {
                    this.ensureValueIsMutable();
                    this.value_.add(index, builderForValue.build());
                    this.onChanged();
                } else {
                    this.valueBuilder_.addMessage(index, (AbstractMessage)builderForValue.build());
                }
                return this;
            }

            public Builder addAllValue(Iterable<? extends Expr> values) {
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

            public Expr.Builder getValueBuilder(int index) {
                return (Expr.Builder)this.getValueFieldBuilder().getBuilder(index);
            }

            @Override
            public ExprOrBuilder getValueOrBuilder(int index) {
                if (this.valueBuilder_ == null) {
                    return this.value_.get(index);
                }
                return (ExprOrBuilder)this.valueBuilder_.getMessageOrBuilder(index);
            }

            @Override
            public List<? extends ExprOrBuilder> getValueOrBuilderList() {
                if (this.valueBuilder_ != null) {
                    return this.valueBuilder_.getMessageOrBuilderList();
                }
                return Collections.unmodifiableList(this.value_);
            }

            public Expr.Builder addValueBuilder() {
                return (Expr.Builder)this.getValueFieldBuilder().addBuilder((AbstractMessage)Expr.getDefaultInstance());
            }

            public Expr.Builder addValueBuilder(int index) {
                return (Expr.Builder)this.getValueFieldBuilder().addBuilder(index, (AbstractMessage)Expr.getDefaultInstance());
            }

            public List<Expr.Builder> getValueBuilderList() {
                return this.getValueFieldBuilder().getBuilderList();
            }

            private RepeatedFieldBuilderV3<Expr, Expr.Builder, ExprOrBuilder> getValueFieldBuilder() {
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
        public List<Expr> getValueList();

        public Expr getValue(int var1);

        public int getValueCount();

        public List<? extends ExprOrBuilder> getValueOrBuilderList();

        public ExprOrBuilder getValueOrBuilder(int var1);
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
            return internal_static_Mysqlx_Expr_Object_descriptor;
        }

        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return internal_static_Mysqlx_Expr_Object_fieldAccessorTable.ensureFieldAccessorsInitialized(Object.class, Builder.class);
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
                return internal_static_Mysqlx_Expr_Object_descriptor;
            }

            protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                return internal_static_Mysqlx_Expr_Object_fieldAccessorTable.ensureFieldAccessorsInitialized(Object.class, Builder.class);
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
                return internal_static_Mysqlx_Expr_Object_descriptor;
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
            private Expr value_;
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
                                Expr.Builder subBuilder = null;
                                if ((this.bitField0_ & 2) != 0) {
                                    subBuilder = this.value_.toBuilder();
                                }
                                this.value_ = (Expr)input.readMessage(Expr.PARSER, extensionRegistry);
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
                return internal_static_Mysqlx_Expr_Object_ObjectField_descriptor;
            }

            protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                return internal_static_Mysqlx_Expr_Object_ObjectField_fieldAccessorTable.ensureFieldAccessorsInitialized(ObjectField.class, Builder.class);
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
            public Expr getValue() {
                return this.value_ == null ? Expr.getDefaultInstance() : this.value_;
            }

            @Override
            public ExprOrBuilder getValueOrBuilder() {
                return this.value_ == null ? Expr.getDefaultInstance() : this.value_;
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
                private Expr value_;
                private SingleFieldBuilderV3<Expr, Expr.Builder, ExprOrBuilder> valueBuilder_;

                public static final Descriptors.Descriptor getDescriptor() {
                    return internal_static_Mysqlx_Expr_Object_ObjectField_descriptor;
                }

                protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                    return internal_static_Mysqlx_Expr_Object_ObjectField_fieldAccessorTable.ensureFieldAccessorsInitialized(ObjectField.class, Builder.class);
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
                    return internal_static_Mysqlx_Expr_Object_ObjectField_descriptor;
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
                            result.value_ = (Expr)this.valueBuilder_.build();
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
                public Expr getValue() {
                    if (this.valueBuilder_ == null) {
                        return this.value_ == null ? Expr.getDefaultInstance() : this.value_;
                    }
                    return (Expr)this.valueBuilder_.getMessage();
                }

                public Builder setValue(Expr value) {
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

                public Builder setValue(Expr.Builder builderForValue) {
                    if (this.valueBuilder_ == null) {
                        this.value_ = builderForValue.build();
                        this.onChanged();
                    } else {
                        this.valueBuilder_.setMessage((AbstractMessage)builderForValue.build());
                    }
                    this.bitField0_ |= 2;
                    return this;
                }

                public Builder mergeValue(Expr value) {
                    if (this.valueBuilder_ == null) {
                        this.value_ = (this.bitField0_ & 2) != 0 && this.value_ != null && this.value_ != Expr.getDefaultInstance() ? Expr.newBuilder(this.value_).mergeFrom(value).buildPartial() : value;
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

                public Expr.Builder getValueBuilder() {
                    this.bitField0_ |= 2;
                    this.onChanged();
                    return (Expr.Builder)this.getValueFieldBuilder().getBuilder();
                }

                @Override
                public ExprOrBuilder getValueOrBuilder() {
                    if (this.valueBuilder_ != null) {
                        return (ExprOrBuilder)this.valueBuilder_.getMessageOrBuilder();
                    }
                    return this.value_ == null ? Expr.getDefaultInstance() : this.value_;
                }

                private SingleFieldBuilderV3<Expr, Expr.Builder, ExprOrBuilder> getValueFieldBuilder() {
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

            public Expr getValue();

            public ExprOrBuilder getValueOrBuilder();
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

    public static final class Operator
    extends GeneratedMessageV3
    implements OperatorOrBuilder {
        private static final long serialVersionUID = 0L;
        private int bitField0_;
        public static final int NAME_FIELD_NUMBER = 1;
        private volatile java.lang.Object name_;
        public static final int PARAM_FIELD_NUMBER = 2;
        private List<Expr> param_;
        private byte memoizedIsInitialized = (byte)-1;
        private static final Operator DEFAULT_INSTANCE = new Operator();
        @Deprecated
        public static final Parser<Operator> PARSER = new AbstractParser<Operator>(){

            public Operator parsePartialFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                return new Operator(input, extensionRegistry);
            }
        };

        private Operator(GeneratedMessageV3.Builder<?> builder) {
            super(builder);
        }

        private Operator() {
            this.name_ = "";
            this.param_ = Collections.emptyList();
        }

        protected java.lang.Object newInstance(GeneratedMessageV3.UnusedPrivateParameter unused) {
            return new Operator();
        }

        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        private Operator(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            this();
            if (extensionRegistry == null) {
                throw new NullPointerException();
            }
            int mutable_bitField0_ = 0;
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
                            this.name_ = bs;
                            continue block11;
                        }
                        case 18: {
                            if ((mutable_bitField0_ & 2) == 0) {
                                this.param_ = new ArrayList<Expr>();
                                mutable_bitField0_ |= 2;
                            }
                            this.param_.add((Expr)input.readMessage(Expr.PARSER, extensionRegistry));
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
                if ((mutable_bitField0_ & 2) != 0) {
                    this.param_ = Collections.unmodifiableList(this.param_);
                }
                this.unknownFields = unknownFields.build();
                this.makeExtensionsImmutable();
            }
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return internal_static_Mysqlx_Expr_Operator_descriptor;
        }

        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return internal_static_Mysqlx_Expr_Operator_fieldAccessorTable.ensureFieldAccessorsInitialized(Operator.class, Builder.class);
        }

        @Override
        public boolean hasName() {
            return (this.bitField0_ & 1) != 0;
        }

        @Override
        public String getName() {
            java.lang.Object ref = this.name_;
            if (ref instanceof String) {
                return (String)ref;
            }
            ByteString bs = (ByteString)ref;
            String s = bs.toStringUtf8();
            if (bs.isValidUtf8()) {
                this.name_ = s;
            }
            return s;
        }

        @Override
        public ByteString getNameBytes() {
            java.lang.Object ref = this.name_;
            if (ref instanceof String) {
                ByteString b = ByteString.copyFromUtf8((String)((String)ref));
                this.name_ = b;
                return b;
            }
            return (ByteString)ref;
        }

        @Override
        public List<Expr> getParamList() {
            return this.param_;
        }

        @Override
        public List<? extends ExprOrBuilder> getParamOrBuilderList() {
            return this.param_;
        }

        @Override
        public int getParamCount() {
            return this.param_.size();
        }

        @Override
        public Expr getParam(int index) {
            return this.param_.get(index);
        }

        @Override
        public ExprOrBuilder getParamOrBuilder(int index) {
            return this.param_.get(index);
        }

        public final boolean isInitialized() {
            byte isInitialized = this.memoizedIsInitialized;
            if (isInitialized == 1) {
                return true;
            }
            if (isInitialized == 0) {
                return false;
            }
            if (!this.hasName()) {
                this.memoizedIsInitialized = 0;
                return false;
            }
            for (int i = 0; i < this.getParamCount(); ++i) {
                if (this.getParam(i).isInitialized()) continue;
                this.memoizedIsInitialized = 0;
                return false;
            }
            this.memoizedIsInitialized = 1;
            return true;
        }

        public void writeTo(CodedOutputStream output) throws IOException {
            if ((this.bitField0_ & 1) != 0) {
                GeneratedMessageV3.writeString((CodedOutputStream)output, (int)1, (java.lang.Object)this.name_);
            }
            for (int i = 0; i < this.param_.size(); ++i) {
                output.writeMessage(2, (MessageLite)this.param_.get(i));
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
                size += GeneratedMessageV3.computeStringSize((int)1, (java.lang.Object)this.name_);
            }
            for (int i = 0; i < this.param_.size(); ++i) {
                size += CodedOutputStream.computeMessageSize((int)2, (MessageLite)((MessageLite)this.param_.get(i)));
            }
            this.memoizedSize = size += this.unknownFields.getSerializedSize();
            return size;
        }

        public boolean equals(java.lang.Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof Operator)) {
                return super.equals(obj);
            }
            Operator other = (Operator)obj;
            if (this.hasName() != other.hasName()) {
                return false;
            }
            if (this.hasName() && !this.getName().equals(other.getName())) {
                return false;
            }
            if (!this.getParamList().equals(other.getParamList())) {
                return false;
            }
            return this.unknownFields.equals((java.lang.Object)other.unknownFields);
        }

        public int hashCode() {
            if (this.memoizedHashCode != 0) {
                return this.memoizedHashCode;
            }
            int hash = 41;
            hash = 19 * hash + Operator.getDescriptor().hashCode();
            if (this.hasName()) {
                hash = 37 * hash + 1;
                hash = 53 * hash + this.getName().hashCode();
            }
            if (this.getParamCount() > 0) {
                hash = 37 * hash + 2;
                hash = 53 * hash + this.getParamList().hashCode();
            }
            this.memoizedHashCode = hash = 29 * hash + this.unknownFields.hashCode();
            return hash;
        }

        public static Operator parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
            return (Operator)PARSER.parseFrom(data);
        }

        public static Operator parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (Operator)PARSER.parseFrom(data, extensionRegistry);
        }

        public static Operator parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return (Operator)PARSER.parseFrom(data);
        }

        public static Operator parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (Operator)PARSER.parseFrom(data, extensionRegistry);
        }

        public static Operator parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return (Operator)PARSER.parseFrom(data);
        }

        public static Operator parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (Operator)PARSER.parseFrom(data, extensionRegistry);
        }

        public static Operator parseFrom(InputStream input) throws IOException {
            return (Operator)GeneratedMessageV3.parseWithIOException(PARSER, (InputStream)input);
        }

        public static Operator parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (Operator)GeneratedMessageV3.parseWithIOException(PARSER, (InputStream)input, (ExtensionRegistryLite)extensionRegistry);
        }

        public static Operator parseDelimitedFrom(InputStream input) throws IOException {
            return (Operator)GeneratedMessageV3.parseDelimitedWithIOException(PARSER, (InputStream)input);
        }

        public static Operator parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (Operator)GeneratedMessageV3.parseDelimitedWithIOException(PARSER, (InputStream)input, (ExtensionRegistryLite)extensionRegistry);
        }

        public static Operator parseFrom(CodedInputStream input) throws IOException {
            return (Operator)GeneratedMessageV3.parseWithIOException(PARSER, (CodedInputStream)input);
        }

        public static Operator parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (Operator)GeneratedMessageV3.parseWithIOException(PARSER, (CodedInputStream)input, (ExtensionRegistryLite)extensionRegistry);
        }

        public Builder newBuilderForType() {
            return Operator.newBuilder();
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(Operator prototype) {
            return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
        }

        public Builder toBuilder() {
            return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
        }

        protected Builder newBuilderForType(GeneratedMessageV3.BuilderParent parent) {
            Builder builder = new Builder(parent);
            return builder;
        }

        public static Operator getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<Operator> parser() {
            return PARSER;
        }

        public Parser<Operator> getParserForType() {
            return PARSER;
        }

        public Operator getDefaultInstanceForType() {
            return DEFAULT_INSTANCE;
        }

        public static final class Builder
        extends GeneratedMessageV3.Builder<Builder>
        implements OperatorOrBuilder {
            private int bitField0_;
            private java.lang.Object name_ = "";
            private List<Expr> param_ = Collections.emptyList();
            private RepeatedFieldBuilderV3<Expr, Expr.Builder, ExprOrBuilder> paramBuilder_;

            public static final Descriptors.Descriptor getDescriptor() {
                return internal_static_Mysqlx_Expr_Operator_descriptor;
            }

            protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                return internal_static_Mysqlx_Expr_Operator_fieldAccessorTable.ensureFieldAccessorsInitialized(Operator.class, Builder.class);
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
                    this.getParamFieldBuilder();
                }
            }

            public Builder clear() {
                super.clear();
                this.name_ = "";
                this.bitField0_ &= 0xFFFFFFFE;
                if (this.paramBuilder_ == null) {
                    this.param_ = Collections.emptyList();
                    this.bitField0_ &= 0xFFFFFFFD;
                } else {
                    this.paramBuilder_.clear();
                }
                return this;
            }

            public Descriptors.Descriptor getDescriptorForType() {
                return internal_static_Mysqlx_Expr_Operator_descriptor;
            }

            public Operator getDefaultInstanceForType() {
                return Operator.getDefaultInstance();
            }

            public Operator build() {
                Operator result = this.buildPartial();
                if (!result.isInitialized()) {
                    throw Builder.newUninitializedMessageException((Message)result);
                }
                return result;
            }

            public Operator buildPartial() {
                Operator result = new Operator(this);
                int from_bitField0_ = this.bitField0_;
                int to_bitField0_ = 0;
                if ((from_bitField0_ & 1) != 0) {
                    to_bitField0_ |= 1;
                }
                result.name_ = this.name_;
                if (this.paramBuilder_ == null) {
                    if ((this.bitField0_ & 2) != 0) {
                        this.param_ = Collections.unmodifiableList(this.param_);
                        this.bitField0_ &= 0xFFFFFFFD;
                    }
                    result.param_ = this.param_;
                } else {
                    result.param_ = this.paramBuilder_.build();
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
                if (other instanceof Operator) {
                    return this.mergeFrom((Operator)other);
                }
                super.mergeFrom(other);
                return this;
            }

            public Builder mergeFrom(Operator other) {
                if (other == Operator.getDefaultInstance()) {
                    return this;
                }
                if (other.hasName()) {
                    this.bitField0_ |= 1;
                    this.name_ = other.name_;
                    this.onChanged();
                }
                if (this.paramBuilder_ == null) {
                    if (!other.param_.isEmpty()) {
                        if (this.param_.isEmpty()) {
                            this.param_ = other.param_;
                            this.bitField0_ &= 0xFFFFFFFD;
                        } else {
                            this.ensureParamIsMutable();
                            this.param_.addAll(other.param_);
                        }
                        this.onChanged();
                    }
                } else if (!other.param_.isEmpty()) {
                    if (this.paramBuilder_.isEmpty()) {
                        this.paramBuilder_.dispose();
                        this.paramBuilder_ = null;
                        this.param_ = other.param_;
                        this.bitField0_ &= 0xFFFFFFFD;
                        this.paramBuilder_ = alwaysUseFieldBuilders ? this.getParamFieldBuilder() : null;
                    } else {
                        this.paramBuilder_.addAllMessages((Iterable)other.param_);
                    }
                }
                this.mergeUnknownFields(other.unknownFields);
                this.onChanged();
                return this;
            }

            public final boolean isInitialized() {
                if (!this.hasName()) {
                    return false;
                }
                for (int i = 0; i < this.getParamCount(); ++i) {
                    if (this.getParam(i).isInitialized()) continue;
                    return false;
                }
                return true;
            }

            public Builder mergeFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                Operator parsedMessage = null;
                try {
                    parsedMessage = (Operator)PARSER.parsePartialFrom(input, extensionRegistry);
                }
                catch (InvalidProtocolBufferException e) {
                    parsedMessage = (Operator)e.getUnfinishedMessage();
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
            public boolean hasName() {
                return (this.bitField0_ & 1) != 0;
            }

            @Override
            public String getName() {
                java.lang.Object ref = this.name_;
                if (!(ref instanceof String)) {
                    ByteString bs = (ByteString)ref;
                    String s = bs.toStringUtf8();
                    if (bs.isValidUtf8()) {
                        this.name_ = s;
                    }
                    return s;
                }
                return (String)ref;
            }

            @Override
            public ByteString getNameBytes() {
                java.lang.Object ref = this.name_;
                if (ref instanceof String) {
                    ByteString b = ByteString.copyFromUtf8((String)((String)ref));
                    this.name_ = b;
                    return b;
                }
                return (ByteString)ref;
            }

            public Builder setName(String value) {
                if (value == null) {
                    throw new NullPointerException();
                }
                this.bitField0_ |= 1;
                this.name_ = value;
                this.onChanged();
                return this;
            }

            public Builder clearName() {
                this.bitField0_ &= 0xFFFFFFFE;
                this.name_ = Operator.getDefaultInstance().getName();
                this.onChanged();
                return this;
            }

            public Builder setNameBytes(ByteString value) {
                if (value == null) {
                    throw new NullPointerException();
                }
                this.bitField0_ |= 1;
                this.name_ = value;
                this.onChanged();
                return this;
            }

            private void ensureParamIsMutable() {
                if ((this.bitField0_ & 2) == 0) {
                    this.param_ = new ArrayList<Expr>(this.param_);
                    this.bitField0_ |= 2;
                }
            }

            @Override
            public List<Expr> getParamList() {
                if (this.paramBuilder_ == null) {
                    return Collections.unmodifiableList(this.param_);
                }
                return this.paramBuilder_.getMessageList();
            }

            @Override
            public int getParamCount() {
                if (this.paramBuilder_ == null) {
                    return this.param_.size();
                }
                return this.paramBuilder_.getCount();
            }

            @Override
            public Expr getParam(int index) {
                if (this.paramBuilder_ == null) {
                    return this.param_.get(index);
                }
                return (Expr)this.paramBuilder_.getMessage(index);
            }

            public Builder setParam(int index, Expr value) {
                if (this.paramBuilder_ == null) {
                    if (value == null) {
                        throw new NullPointerException();
                    }
                    this.ensureParamIsMutable();
                    this.param_.set(index, value);
                    this.onChanged();
                } else {
                    this.paramBuilder_.setMessage(index, (AbstractMessage)value);
                }
                return this;
            }

            public Builder setParam(int index, Expr.Builder builderForValue) {
                if (this.paramBuilder_ == null) {
                    this.ensureParamIsMutable();
                    this.param_.set(index, builderForValue.build());
                    this.onChanged();
                } else {
                    this.paramBuilder_.setMessage(index, (AbstractMessage)builderForValue.build());
                }
                return this;
            }

            public Builder addParam(Expr value) {
                if (this.paramBuilder_ == null) {
                    if (value == null) {
                        throw new NullPointerException();
                    }
                    this.ensureParamIsMutable();
                    this.param_.add(value);
                    this.onChanged();
                } else {
                    this.paramBuilder_.addMessage((AbstractMessage)value);
                }
                return this;
            }

            public Builder addParam(int index, Expr value) {
                if (this.paramBuilder_ == null) {
                    if (value == null) {
                        throw new NullPointerException();
                    }
                    this.ensureParamIsMutable();
                    this.param_.add(index, value);
                    this.onChanged();
                } else {
                    this.paramBuilder_.addMessage(index, (AbstractMessage)value);
                }
                return this;
            }

            public Builder addParam(Expr.Builder builderForValue) {
                if (this.paramBuilder_ == null) {
                    this.ensureParamIsMutable();
                    this.param_.add(builderForValue.build());
                    this.onChanged();
                } else {
                    this.paramBuilder_.addMessage((AbstractMessage)builderForValue.build());
                }
                return this;
            }

            public Builder addParam(int index, Expr.Builder builderForValue) {
                if (this.paramBuilder_ == null) {
                    this.ensureParamIsMutable();
                    this.param_.add(index, builderForValue.build());
                    this.onChanged();
                } else {
                    this.paramBuilder_.addMessage(index, (AbstractMessage)builderForValue.build());
                }
                return this;
            }

            public Builder addAllParam(Iterable<? extends Expr> values) {
                if (this.paramBuilder_ == null) {
                    this.ensureParamIsMutable();
                    AbstractMessageLite.Builder.addAll(values, this.param_);
                    this.onChanged();
                } else {
                    this.paramBuilder_.addAllMessages(values);
                }
                return this;
            }

            public Builder clearParam() {
                if (this.paramBuilder_ == null) {
                    this.param_ = Collections.emptyList();
                    this.bitField0_ &= 0xFFFFFFFD;
                    this.onChanged();
                } else {
                    this.paramBuilder_.clear();
                }
                return this;
            }

            public Builder removeParam(int index) {
                if (this.paramBuilder_ == null) {
                    this.ensureParamIsMutable();
                    this.param_.remove(index);
                    this.onChanged();
                } else {
                    this.paramBuilder_.remove(index);
                }
                return this;
            }

            public Expr.Builder getParamBuilder(int index) {
                return (Expr.Builder)this.getParamFieldBuilder().getBuilder(index);
            }

            @Override
            public ExprOrBuilder getParamOrBuilder(int index) {
                if (this.paramBuilder_ == null) {
                    return this.param_.get(index);
                }
                return (ExprOrBuilder)this.paramBuilder_.getMessageOrBuilder(index);
            }

            @Override
            public List<? extends ExprOrBuilder> getParamOrBuilderList() {
                if (this.paramBuilder_ != null) {
                    return this.paramBuilder_.getMessageOrBuilderList();
                }
                return Collections.unmodifiableList(this.param_);
            }

            public Expr.Builder addParamBuilder() {
                return (Expr.Builder)this.getParamFieldBuilder().addBuilder((AbstractMessage)Expr.getDefaultInstance());
            }

            public Expr.Builder addParamBuilder(int index) {
                return (Expr.Builder)this.getParamFieldBuilder().addBuilder(index, (AbstractMessage)Expr.getDefaultInstance());
            }

            public List<Expr.Builder> getParamBuilderList() {
                return this.getParamFieldBuilder().getBuilderList();
            }

            private RepeatedFieldBuilderV3<Expr, Expr.Builder, ExprOrBuilder> getParamFieldBuilder() {
                if (this.paramBuilder_ == null) {
                    this.paramBuilder_ = new RepeatedFieldBuilderV3(this.param_, (this.bitField0_ & 2) != 0, (AbstractMessage.BuilderParent)this.getParentForChildren(), this.isClean());
                    this.param_ = null;
                }
                return this.paramBuilder_;
            }

            public final Builder setUnknownFields(UnknownFieldSet unknownFields) {
                return (Builder)super.setUnknownFields(unknownFields);
            }

            public final Builder mergeUnknownFields(UnknownFieldSet unknownFields) {
                return (Builder)super.mergeUnknownFields(unknownFields);
            }
        }
    }

    public static interface OperatorOrBuilder
    extends MessageOrBuilder {
        public boolean hasName();

        public String getName();

        public ByteString getNameBytes();

        public List<Expr> getParamList();

        public Expr getParam(int var1);

        public int getParamCount();

        public List<? extends ExprOrBuilder> getParamOrBuilderList();

        public ExprOrBuilder getParamOrBuilder(int var1);
    }

    public static final class FunctionCall
    extends GeneratedMessageV3
    implements FunctionCallOrBuilder {
        private static final long serialVersionUID = 0L;
        private int bitField0_;
        public static final int NAME_FIELD_NUMBER = 1;
        private Identifier name_;
        public static final int PARAM_FIELD_NUMBER = 2;
        private List<Expr> param_;
        private byte memoizedIsInitialized = (byte)-1;
        private static final FunctionCall DEFAULT_INSTANCE = new FunctionCall();
        @Deprecated
        public static final Parser<FunctionCall> PARSER = new AbstractParser<FunctionCall>(){

            public FunctionCall parsePartialFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                return new FunctionCall(input, extensionRegistry);
            }
        };

        private FunctionCall(GeneratedMessageV3.Builder<?> builder) {
            super(builder);
        }

        private FunctionCall() {
            this.param_ = Collections.emptyList();
        }

        protected java.lang.Object newInstance(GeneratedMessageV3.UnusedPrivateParameter unused) {
            return new FunctionCall();
        }

        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        private FunctionCall(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            this();
            if (extensionRegistry == null) {
                throw new NullPointerException();
            }
            int mutable_bitField0_ = 0;
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
                            Identifier.Builder subBuilder = null;
                            if ((this.bitField0_ & 1) != 0) {
                                subBuilder = this.name_.toBuilder();
                            }
                            this.name_ = (Identifier)input.readMessage(Identifier.PARSER, extensionRegistry);
                            if (subBuilder != null) {
                                subBuilder.mergeFrom(this.name_);
                                this.name_ = subBuilder.buildPartial();
                            }
                            this.bitField0_ |= 1;
                            continue block11;
                        }
                        case 18: {
                            if ((mutable_bitField0_ & 2) == 0) {
                                this.param_ = new ArrayList<Expr>();
                                mutable_bitField0_ |= 2;
                            }
                            this.param_.add((Expr)input.readMessage(Expr.PARSER, extensionRegistry));
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
                if ((mutable_bitField0_ & 2) != 0) {
                    this.param_ = Collections.unmodifiableList(this.param_);
                }
                this.unknownFields = unknownFields.build();
                this.makeExtensionsImmutable();
            }
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return internal_static_Mysqlx_Expr_FunctionCall_descriptor;
        }

        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return internal_static_Mysqlx_Expr_FunctionCall_fieldAccessorTable.ensureFieldAccessorsInitialized(FunctionCall.class, Builder.class);
        }

        @Override
        public boolean hasName() {
            return (this.bitField0_ & 1) != 0;
        }

        @Override
        public Identifier getName() {
            return this.name_ == null ? Identifier.getDefaultInstance() : this.name_;
        }

        @Override
        public IdentifierOrBuilder getNameOrBuilder() {
            return this.name_ == null ? Identifier.getDefaultInstance() : this.name_;
        }

        @Override
        public List<Expr> getParamList() {
            return this.param_;
        }

        @Override
        public List<? extends ExprOrBuilder> getParamOrBuilderList() {
            return this.param_;
        }

        @Override
        public int getParamCount() {
            return this.param_.size();
        }

        @Override
        public Expr getParam(int index) {
            return this.param_.get(index);
        }

        @Override
        public ExprOrBuilder getParamOrBuilder(int index) {
            return this.param_.get(index);
        }

        public final boolean isInitialized() {
            byte isInitialized = this.memoizedIsInitialized;
            if (isInitialized == 1) {
                return true;
            }
            if (isInitialized == 0) {
                return false;
            }
            if (!this.hasName()) {
                this.memoizedIsInitialized = 0;
                return false;
            }
            if (!this.getName().isInitialized()) {
                this.memoizedIsInitialized = 0;
                return false;
            }
            for (int i = 0; i < this.getParamCount(); ++i) {
                if (this.getParam(i).isInitialized()) continue;
                this.memoizedIsInitialized = 0;
                return false;
            }
            this.memoizedIsInitialized = 1;
            return true;
        }

        public void writeTo(CodedOutputStream output) throws IOException {
            if ((this.bitField0_ & 1) != 0) {
                output.writeMessage(1, (MessageLite)this.getName());
            }
            for (int i = 0; i < this.param_.size(); ++i) {
                output.writeMessage(2, (MessageLite)this.param_.get(i));
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
                size += CodedOutputStream.computeMessageSize((int)1, (MessageLite)this.getName());
            }
            for (int i = 0; i < this.param_.size(); ++i) {
                size += CodedOutputStream.computeMessageSize((int)2, (MessageLite)((MessageLite)this.param_.get(i)));
            }
            this.memoizedSize = size += this.unknownFields.getSerializedSize();
            return size;
        }

        public boolean equals(java.lang.Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof FunctionCall)) {
                return super.equals(obj);
            }
            FunctionCall other = (FunctionCall)obj;
            if (this.hasName() != other.hasName()) {
                return false;
            }
            if (this.hasName() && !this.getName().equals(other.getName())) {
                return false;
            }
            if (!this.getParamList().equals(other.getParamList())) {
                return false;
            }
            return this.unknownFields.equals((java.lang.Object)other.unknownFields);
        }

        public int hashCode() {
            if (this.memoizedHashCode != 0) {
                return this.memoizedHashCode;
            }
            int hash = 41;
            hash = 19 * hash + FunctionCall.getDescriptor().hashCode();
            if (this.hasName()) {
                hash = 37 * hash + 1;
                hash = 53 * hash + this.getName().hashCode();
            }
            if (this.getParamCount() > 0) {
                hash = 37 * hash + 2;
                hash = 53 * hash + this.getParamList().hashCode();
            }
            this.memoizedHashCode = hash = 29 * hash + this.unknownFields.hashCode();
            return hash;
        }

        public static FunctionCall parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
            return (FunctionCall)PARSER.parseFrom(data);
        }

        public static FunctionCall parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (FunctionCall)PARSER.parseFrom(data, extensionRegistry);
        }

        public static FunctionCall parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return (FunctionCall)PARSER.parseFrom(data);
        }

        public static FunctionCall parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (FunctionCall)PARSER.parseFrom(data, extensionRegistry);
        }

        public static FunctionCall parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return (FunctionCall)PARSER.parseFrom(data);
        }

        public static FunctionCall parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (FunctionCall)PARSER.parseFrom(data, extensionRegistry);
        }

        public static FunctionCall parseFrom(InputStream input) throws IOException {
            return (FunctionCall)GeneratedMessageV3.parseWithIOException(PARSER, (InputStream)input);
        }

        public static FunctionCall parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (FunctionCall)GeneratedMessageV3.parseWithIOException(PARSER, (InputStream)input, (ExtensionRegistryLite)extensionRegistry);
        }

        public static FunctionCall parseDelimitedFrom(InputStream input) throws IOException {
            return (FunctionCall)GeneratedMessageV3.parseDelimitedWithIOException(PARSER, (InputStream)input);
        }

        public static FunctionCall parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (FunctionCall)GeneratedMessageV3.parseDelimitedWithIOException(PARSER, (InputStream)input, (ExtensionRegistryLite)extensionRegistry);
        }

        public static FunctionCall parseFrom(CodedInputStream input) throws IOException {
            return (FunctionCall)GeneratedMessageV3.parseWithIOException(PARSER, (CodedInputStream)input);
        }

        public static FunctionCall parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (FunctionCall)GeneratedMessageV3.parseWithIOException(PARSER, (CodedInputStream)input, (ExtensionRegistryLite)extensionRegistry);
        }

        public Builder newBuilderForType() {
            return FunctionCall.newBuilder();
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(FunctionCall prototype) {
            return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
        }

        public Builder toBuilder() {
            return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
        }

        protected Builder newBuilderForType(GeneratedMessageV3.BuilderParent parent) {
            Builder builder = new Builder(parent);
            return builder;
        }

        public static FunctionCall getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<FunctionCall> parser() {
            return PARSER;
        }

        public Parser<FunctionCall> getParserForType() {
            return PARSER;
        }

        public FunctionCall getDefaultInstanceForType() {
            return DEFAULT_INSTANCE;
        }

        public static final class Builder
        extends GeneratedMessageV3.Builder<Builder>
        implements FunctionCallOrBuilder {
            private int bitField0_;
            private Identifier name_;
            private SingleFieldBuilderV3<Identifier, Identifier.Builder, IdentifierOrBuilder> nameBuilder_;
            private List<Expr> param_ = Collections.emptyList();
            private RepeatedFieldBuilderV3<Expr, Expr.Builder, ExprOrBuilder> paramBuilder_;

            public static final Descriptors.Descriptor getDescriptor() {
                return internal_static_Mysqlx_Expr_FunctionCall_descriptor;
            }

            protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                return internal_static_Mysqlx_Expr_FunctionCall_fieldAccessorTable.ensureFieldAccessorsInitialized(FunctionCall.class, Builder.class);
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
                    this.getNameFieldBuilder();
                    this.getParamFieldBuilder();
                }
            }

            public Builder clear() {
                super.clear();
                if (this.nameBuilder_ == null) {
                    this.name_ = null;
                } else {
                    this.nameBuilder_.clear();
                }
                this.bitField0_ &= 0xFFFFFFFE;
                if (this.paramBuilder_ == null) {
                    this.param_ = Collections.emptyList();
                    this.bitField0_ &= 0xFFFFFFFD;
                } else {
                    this.paramBuilder_.clear();
                }
                return this;
            }

            public Descriptors.Descriptor getDescriptorForType() {
                return internal_static_Mysqlx_Expr_FunctionCall_descriptor;
            }

            public FunctionCall getDefaultInstanceForType() {
                return FunctionCall.getDefaultInstance();
            }

            public FunctionCall build() {
                FunctionCall result = this.buildPartial();
                if (!result.isInitialized()) {
                    throw Builder.newUninitializedMessageException((Message)result);
                }
                return result;
            }

            public FunctionCall buildPartial() {
                FunctionCall result = new FunctionCall(this);
                int from_bitField0_ = this.bitField0_;
                int to_bitField0_ = 0;
                if ((from_bitField0_ & 1) != 0) {
                    if (this.nameBuilder_ == null) {
                        result.name_ = this.name_;
                    } else {
                        result.name_ = (Identifier)this.nameBuilder_.build();
                    }
                    to_bitField0_ |= 1;
                }
                if (this.paramBuilder_ == null) {
                    if ((this.bitField0_ & 2) != 0) {
                        this.param_ = Collections.unmodifiableList(this.param_);
                        this.bitField0_ &= 0xFFFFFFFD;
                    }
                    result.param_ = this.param_;
                } else {
                    result.param_ = this.paramBuilder_.build();
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
                if (other instanceof FunctionCall) {
                    return this.mergeFrom((FunctionCall)other);
                }
                super.mergeFrom(other);
                return this;
            }

            public Builder mergeFrom(FunctionCall other) {
                if (other == FunctionCall.getDefaultInstance()) {
                    return this;
                }
                if (other.hasName()) {
                    this.mergeName(other.getName());
                }
                if (this.paramBuilder_ == null) {
                    if (!other.param_.isEmpty()) {
                        if (this.param_.isEmpty()) {
                            this.param_ = other.param_;
                            this.bitField0_ &= 0xFFFFFFFD;
                        } else {
                            this.ensureParamIsMutable();
                            this.param_.addAll(other.param_);
                        }
                        this.onChanged();
                    }
                } else if (!other.param_.isEmpty()) {
                    if (this.paramBuilder_.isEmpty()) {
                        this.paramBuilder_.dispose();
                        this.paramBuilder_ = null;
                        this.param_ = other.param_;
                        this.bitField0_ &= 0xFFFFFFFD;
                        this.paramBuilder_ = alwaysUseFieldBuilders ? this.getParamFieldBuilder() : null;
                    } else {
                        this.paramBuilder_.addAllMessages((Iterable)other.param_);
                    }
                }
                this.mergeUnknownFields(other.unknownFields);
                this.onChanged();
                return this;
            }

            public final boolean isInitialized() {
                if (!this.hasName()) {
                    return false;
                }
                if (!this.getName().isInitialized()) {
                    return false;
                }
                for (int i = 0; i < this.getParamCount(); ++i) {
                    if (this.getParam(i).isInitialized()) continue;
                    return false;
                }
                return true;
            }

            public Builder mergeFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                FunctionCall parsedMessage = null;
                try {
                    parsedMessage = (FunctionCall)PARSER.parsePartialFrom(input, extensionRegistry);
                }
                catch (InvalidProtocolBufferException e) {
                    parsedMessage = (FunctionCall)e.getUnfinishedMessage();
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
            public boolean hasName() {
                return (this.bitField0_ & 1) != 0;
            }

            @Override
            public Identifier getName() {
                if (this.nameBuilder_ == null) {
                    return this.name_ == null ? Identifier.getDefaultInstance() : this.name_;
                }
                return (Identifier)this.nameBuilder_.getMessage();
            }

            public Builder setName(Identifier value) {
                if (this.nameBuilder_ == null) {
                    if (value == null) {
                        throw new NullPointerException();
                    }
                    this.name_ = value;
                    this.onChanged();
                } else {
                    this.nameBuilder_.setMessage((AbstractMessage)value);
                }
                this.bitField0_ |= 1;
                return this;
            }

            public Builder setName(Identifier.Builder builderForValue) {
                if (this.nameBuilder_ == null) {
                    this.name_ = builderForValue.build();
                    this.onChanged();
                } else {
                    this.nameBuilder_.setMessage((AbstractMessage)builderForValue.build());
                }
                this.bitField0_ |= 1;
                return this;
            }

            public Builder mergeName(Identifier value) {
                if (this.nameBuilder_ == null) {
                    this.name_ = (this.bitField0_ & 1) != 0 && this.name_ != null && this.name_ != Identifier.getDefaultInstance() ? Identifier.newBuilder(this.name_).mergeFrom(value).buildPartial() : value;
                    this.onChanged();
                } else {
                    this.nameBuilder_.mergeFrom((AbstractMessage)value);
                }
                this.bitField0_ |= 1;
                return this;
            }

            public Builder clearName() {
                if (this.nameBuilder_ == null) {
                    this.name_ = null;
                    this.onChanged();
                } else {
                    this.nameBuilder_.clear();
                }
                this.bitField0_ &= 0xFFFFFFFE;
                return this;
            }

            public Identifier.Builder getNameBuilder() {
                this.bitField0_ |= 1;
                this.onChanged();
                return (Identifier.Builder)this.getNameFieldBuilder().getBuilder();
            }

            @Override
            public IdentifierOrBuilder getNameOrBuilder() {
                if (this.nameBuilder_ != null) {
                    return (IdentifierOrBuilder)this.nameBuilder_.getMessageOrBuilder();
                }
                return this.name_ == null ? Identifier.getDefaultInstance() : this.name_;
            }

            private SingleFieldBuilderV3<Identifier, Identifier.Builder, IdentifierOrBuilder> getNameFieldBuilder() {
                if (this.nameBuilder_ == null) {
                    this.nameBuilder_ = new SingleFieldBuilderV3((AbstractMessage)this.getName(), (AbstractMessage.BuilderParent)this.getParentForChildren(), this.isClean());
                    this.name_ = null;
                }
                return this.nameBuilder_;
            }

            private void ensureParamIsMutable() {
                if ((this.bitField0_ & 2) == 0) {
                    this.param_ = new ArrayList<Expr>(this.param_);
                    this.bitField0_ |= 2;
                }
            }

            @Override
            public List<Expr> getParamList() {
                if (this.paramBuilder_ == null) {
                    return Collections.unmodifiableList(this.param_);
                }
                return this.paramBuilder_.getMessageList();
            }

            @Override
            public int getParamCount() {
                if (this.paramBuilder_ == null) {
                    return this.param_.size();
                }
                return this.paramBuilder_.getCount();
            }

            @Override
            public Expr getParam(int index) {
                if (this.paramBuilder_ == null) {
                    return this.param_.get(index);
                }
                return (Expr)this.paramBuilder_.getMessage(index);
            }

            public Builder setParam(int index, Expr value) {
                if (this.paramBuilder_ == null) {
                    if (value == null) {
                        throw new NullPointerException();
                    }
                    this.ensureParamIsMutable();
                    this.param_.set(index, value);
                    this.onChanged();
                } else {
                    this.paramBuilder_.setMessage(index, (AbstractMessage)value);
                }
                return this;
            }

            public Builder setParam(int index, Expr.Builder builderForValue) {
                if (this.paramBuilder_ == null) {
                    this.ensureParamIsMutable();
                    this.param_.set(index, builderForValue.build());
                    this.onChanged();
                } else {
                    this.paramBuilder_.setMessage(index, (AbstractMessage)builderForValue.build());
                }
                return this;
            }

            public Builder addParam(Expr value) {
                if (this.paramBuilder_ == null) {
                    if (value == null) {
                        throw new NullPointerException();
                    }
                    this.ensureParamIsMutable();
                    this.param_.add(value);
                    this.onChanged();
                } else {
                    this.paramBuilder_.addMessage((AbstractMessage)value);
                }
                return this;
            }

            public Builder addParam(int index, Expr value) {
                if (this.paramBuilder_ == null) {
                    if (value == null) {
                        throw new NullPointerException();
                    }
                    this.ensureParamIsMutable();
                    this.param_.add(index, value);
                    this.onChanged();
                } else {
                    this.paramBuilder_.addMessage(index, (AbstractMessage)value);
                }
                return this;
            }

            public Builder addParam(Expr.Builder builderForValue) {
                if (this.paramBuilder_ == null) {
                    this.ensureParamIsMutable();
                    this.param_.add(builderForValue.build());
                    this.onChanged();
                } else {
                    this.paramBuilder_.addMessage((AbstractMessage)builderForValue.build());
                }
                return this;
            }

            public Builder addParam(int index, Expr.Builder builderForValue) {
                if (this.paramBuilder_ == null) {
                    this.ensureParamIsMutable();
                    this.param_.add(index, builderForValue.build());
                    this.onChanged();
                } else {
                    this.paramBuilder_.addMessage(index, (AbstractMessage)builderForValue.build());
                }
                return this;
            }

            public Builder addAllParam(Iterable<? extends Expr> values) {
                if (this.paramBuilder_ == null) {
                    this.ensureParamIsMutable();
                    AbstractMessageLite.Builder.addAll(values, this.param_);
                    this.onChanged();
                } else {
                    this.paramBuilder_.addAllMessages(values);
                }
                return this;
            }

            public Builder clearParam() {
                if (this.paramBuilder_ == null) {
                    this.param_ = Collections.emptyList();
                    this.bitField0_ &= 0xFFFFFFFD;
                    this.onChanged();
                } else {
                    this.paramBuilder_.clear();
                }
                return this;
            }

            public Builder removeParam(int index) {
                if (this.paramBuilder_ == null) {
                    this.ensureParamIsMutable();
                    this.param_.remove(index);
                    this.onChanged();
                } else {
                    this.paramBuilder_.remove(index);
                }
                return this;
            }

            public Expr.Builder getParamBuilder(int index) {
                return (Expr.Builder)this.getParamFieldBuilder().getBuilder(index);
            }

            @Override
            public ExprOrBuilder getParamOrBuilder(int index) {
                if (this.paramBuilder_ == null) {
                    return this.param_.get(index);
                }
                return (ExprOrBuilder)this.paramBuilder_.getMessageOrBuilder(index);
            }

            @Override
            public List<? extends ExprOrBuilder> getParamOrBuilderList() {
                if (this.paramBuilder_ != null) {
                    return this.paramBuilder_.getMessageOrBuilderList();
                }
                return Collections.unmodifiableList(this.param_);
            }

            public Expr.Builder addParamBuilder() {
                return (Expr.Builder)this.getParamFieldBuilder().addBuilder((AbstractMessage)Expr.getDefaultInstance());
            }

            public Expr.Builder addParamBuilder(int index) {
                return (Expr.Builder)this.getParamFieldBuilder().addBuilder(index, (AbstractMessage)Expr.getDefaultInstance());
            }

            public List<Expr.Builder> getParamBuilderList() {
                return this.getParamFieldBuilder().getBuilderList();
            }

            private RepeatedFieldBuilderV3<Expr, Expr.Builder, ExprOrBuilder> getParamFieldBuilder() {
                if (this.paramBuilder_ == null) {
                    this.paramBuilder_ = new RepeatedFieldBuilderV3(this.param_, (this.bitField0_ & 2) != 0, (AbstractMessage.BuilderParent)this.getParentForChildren(), this.isClean());
                    this.param_ = null;
                }
                return this.paramBuilder_;
            }

            public final Builder setUnknownFields(UnknownFieldSet unknownFields) {
                return (Builder)super.setUnknownFields(unknownFields);
            }

            public final Builder mergeUnknownFields(UnknownFieldSet unknownFields) {
                return (Builder)super.mergeUnknownFields(unknownFields);
            }
        }
    }

    public static interface FunctionCallOrBuilder
    extends MessageOrBuilder {
        public boolean hasName();

        public Identifier getName();

        public IdentifierOrBuilder getNameOrBuilder();

        public List<Expr> getParamList();

        public Expr getParam(int var1);

        public int getParamCount();

        public List<? extends ExprOrBuilder> getParamOrBuilderList();

        public ExprOrBuilder getParamOrBuilder(int var1);
    }

    public static final class ColumnIdentifier
    extends GeneratedMessageV3
    implements ColumnIdentifierOrBuilder {
        private static final long serialVersionUID = 0L;
        private int bitField0_;
        public static final int DOCUMENT_PATH_FIELD_NUMBER = 1;
        private List<DocumentPathItem> documentPath_;
        public static final int NAME_FIELD_NUMBER = 2;
        private volatile java.lang.Object name_;
        public static final int TABLE_NAME_FIELD_NUMBER = 3;
        private volatile java.lang.Object tableName_;
        public static final int SCHEMA_NAME_FIELD_NUMBER = 4;
        private volatile java.lang.Object schemaName_;
        private byte memoizedIsInitialized = (byte)-1;
        private static final ColumnIdentifier DEFAULT_INSTANCE = new ColumnIdentifier();
        @Deprecated
        public static final Parser<ColumnIdentifier> PARSER = new AbstractParser<ColumnIdentifier>(){

            public ColumnIdentifier parsePartialFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                return new ColumnIdentifier(input, extensionRegistry);
            }
        };

        private ColumnIdentifier(GeneratedMessageV3.Builder<?> builder) {
            super(builder);
        }

        private ColumnIdentifier() {
            this.documentPath_ = Collections.emptyList();
            this.name_ = "";
            this.tableName_ = "";
            this.schemaName_ = "";
        }

        protected java.lang.Object newInstance(GeneratedMessageV3.UnusedPrivateParameter unused) {
            return new ColumnIdentifier();
        }

        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        private ColumnIdentifier(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
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
                        case 10: {
                            if (!(mutable_bitField0_ & true)) {
                                this.documentPath_ = new ArrayList<DocumentPathItem>();
                                mutable_bitField0_ |= true;
                            }
                            this.documentPath_.add((DocumentPathItem)input.readMessage(DocumentPathItem.PARSER, extensionRegistry));
                            continue block13;
                        }
                        case 18: {
                            ByteString bs = input.readBytes();
                            this.bitField0_ |= 1;
                            this.name_ = bs;
                            continue block13;
                        }
                        case 26: {
                            ByteString bs = input.readBytes();
                            this.bitField0_ |= 2;
                            this.tableName_ = bs;
                            continue block13;
                        }
                        case 34: {
                            ByteString bs = input.readBytes();
                            this.bitField0_ |= 4;
                            this.schemaName_ = bs;
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
                if (mutable_bitField0_ & true) {
                    this.documentPath_ = Collections.unmodifiableList(this.documentPath_);
                }
                this.unknownFields = unknownFields.build();
                this.makeExtensionsImmutable();
            }
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return internal_static_Mysqlx_Expr_ColumnIdentifier_descriptor;
        }

        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return internal_static_Mysqlx_Expr_ColumnIdentifier_fieldAccessorTable.ensureFieldAccessorsInitialized(ColumnIdentifier.class, Builder.class);
        }

        @Override
        public List<DocumentPathItem> getDocumentPathList() {
            return this.documentPath_;
        }

        @Override
        public List<? extends DocumentPathItemOrBuilder> getDocumentPathOrBuilderList() {
            return this.documentPath_;
        }

        @Override
        public int getDocumentPathCount() {
            return this.documentPath_.size();
        }

        @Override
        public DocumentPathItem getDocumentPath(int index) {
            return this.documentPath_.get(index);
        }

        @Override
        public DocumentPathItemOrBuilder getDocumentPathOrBuilder(int index) {
            return this.documentPath_.get(index);
        }

        @Override
        public boolean hasName() {
            return (this.bitField0_ & 1) != 0;
        }

        @Override
        public String getName() {
            java.lang.Object ref = this.name_;
            if (ref instanceof String) {
                return (String)ref;
            }
            ByteString bs = (ByteString)ref;
            String s = bs.toStringUtf8();
            if (bs.isValidUtf8()) {
                this.name_ = s;
            }
            return s;
        }

        @Override
        public ByteString getNameBytes() {
            java.lang.Object ref = this.name_;
            if (ref instanceof String) {
                ByteString b = ByteString.copyFromUtf8((String)((String)ref));
                this.name_ = b;
                return b;
            }
            return (ByteString)ref;
        }

        @Override
        public boolean hasTableName() {
            return (this.bitField0_ & 2) != 0;
        }

        @Override
        public String getTableName() {
            java.lang.Object ref = this.tableName_;
            if (ref instanceof String) {
                return (String)ref;
            }
            ByteString bs = (ByteString)ref;
            String s = bs.toStringUtf8();
            if (bs.isValidUtf8()) {
                this.tableName_ = s;
            }
            return s;
        }

        @Override
        public ByteString getTableNameBytes() {
            java.lang.Object ref = this.tableName_;
            if (ref instanceof String) {
                ByteString b = ByteString.copyFromUtf8((String)((String)ref));
                this.tableName_ = b;
                return b;
            }
            return (ByteString)ref;
        }

        @Override
        public boolean hasSchemaName() {
            return (this.bitField0_ & 4) != 0;
        }

        @Override
        public String getSchemaName() {
            java.lang.Object ref = this.schemaName_;
            if (ref instanceof String) {
                return (String)ref;
            }
            ByteString bs = (ByteString)ref;
            String s = bs.toStringUtf8();
            if (bs.isValidUtf8()) {
                this.schemaName_ = s;
            }
            return s;
        }

        @Override
        public ByteString getSchemaNameBytes() {
            java.lang.Object ref = this.schemaName_;
            if (ref instanceof String) {
                ByteString b = ByteString.copyFromUtf8((String)((String)ref));
                this.schemaName_ = b;
                return b;
            }
            return (ByteString)ref;
        }

        public final boolean isInitialized() {
            byte isInitialized = this.memoizedIsInitialized;
            if (isInitialized == 1) {
                return true;
            }
            if (isInitialized == 0) {
                return false;
            }
            for (int i = 0; i < this.getDocumentPathCount(); ++i) {
                if (this.getDocumentPath(i).isInitialized()) continue;
                this.memoizedIsInitialized = 0;
                return false;
            }
            this.memoizedIsInitialized = 1;
            return true;
        }

        public void writeTo(CodedOutputStream output) throws IOException {
            for (int i = 0; i < this.documentPath_.size(); ++i) {
                output.writeMessage(1, (MessageLite)this.documentPath_.get(i));
            }
            if ((this.bitField0_ & 1) != 0) {
                GeneratedMessageV3.writeString((CodedOutputStream)output, (int)2, (java.lang.Object)this.name_);
            }
            if ((this.bitField0_ & 2) != 0) {
                GeneratedMessageV3.writeString((CodedOutputStream)output, (int)3, (java.lang.Object)this.tableName_);
            }
            if ((this.bitField0_ & 4) != 0) {
                GeneratedMessageV3.writeString((CodedOutputStream)output, (int)4, (java.lang.Object)this.schemaName_);
            }
            this.unknownFields.writeTo(output);
        }

        public int getSerializedSize() {
            int size = this.memoizedSize;
            if (size != -1) {
                return size;
            }
            size = 0;
            for (int i = 0; i < this.documentPath_.size(); ++i) {
                size += CodedOutputStream.computeMessageSize((int)1, (MessageLite)((MessageLite)this.documentPath_.get(i)));
            }
            if ((this.bitField0_ & 1) != 0) {
                size += GeneratedMessageV3.computeStringSize((int)2, (java.lang.Object)this.name_);
            }
            if ((this.bitField0_ & 2) != 0) {
                size += GeneratedMessageV3.computeStringSize((int)3, (java.lang.Object)this.tableName_);
            }
            if ((this.bitField0_ & 4) != 0) {
                size += GeneratedMessageV3.computeStringSize((int)4, (java.lang.Object)this.schemaName_);
            }
            this.memoizedSize = size += this.unknownFields.getSerializedSize();
            return size;
        }

        public boolean equals(java.lang.Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof ColumnIdentifier)) {
                return super.equals(obj);
            }
            ColumnIdentifier other = (ColumnIdentifier)obj;
            if (!this.getDocumentPathList().equals(other.getDocumentPathList())) {
                return false;
            }
            if (this.hasName() != other.hasName()) {
                return false;
            }
            if (this.hasName() && !this.getName().equals(other.getName())) {
                return false;
            }
            if (this.hasTableName() != other.hasTableName()) {
                return false;
            }
            if (this.hasTableName() && !this.getTableName().equals(other.getTableName())) {
                return false;
            }
            if (this.hasSchemaName() != other.hasSchemaName()) {
                return false;
            }
            if (this.hasSchemaName() && !this.getSchemaName().equals(other.getSchemaName())) {
                return false;
            }
            return this.unknownFields.equals((java.lang.Object)other.unknownFields);
        }

        public int hashCode() {
            if (this.memoizedHashCode != 0) {
                return this.memoizedHashCode;
            }
            int hash = 41;
            hash = 19 * hash + ColumnIdentifier.getDescriptor().hashCode();
            if (this.getDocumentPathCount() > 0) {
                hash = 37 * hash + 1;
                hash = 53 * hash + this.getDocumentPathList().hashCode();
            }
            if (this.hasName()) {
                hash = 37 * hash + 2;
                hash = 53 * hash + this.getName().hashCode();
            }
            if (this.hasTableName()) {
                hash = 37 * hash + 3;
                hash = 53 * hash + this.getTableName().hashCode();
            }
            if (this.hasSchemaName()) {
                hash = 37 * hash + 4;
                hash = 53 * hash + this.getSchemaName().hashCode();
            }
            this.memoizedHashCode = hash = 29 * hash + this.unknownFields.hashCode();
            return hash;
        }

        public static ColumnIdentifier parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
            return (ColumnIdentifier)PARSER.parseFrom(data);
        }

        public static ColumnIdentifier parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (ColumnIdentifier)PARSER.parseFrom(data, extensionRegistry);
        }

        public static ColumnIdentifier parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return (ColumnIdentifier)PARSER.parseFrom(data);
        }

        public static ColumnIdentifier parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (ColumnIdentifier)PARSER.parseFrom(data, extensionRegistry);
        }

        public static ColumnIdentifier parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return (ColumnIdentifier)PARSER.parseFrom(data);
        }

        public static ColumnIdentifier parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (ColumnIdentifier)PARSER.parseFrom(data, extensionRegistry);
        }

        public static ColumnIdentifier parseFrom(InputStream input) throws IOException {
            return (ColumnIdentifier)GeneratedMessageV3.parseWithIOException(PARSER, (InputStream)input);
        }

        public static ColumnIdentifier parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (ColumnIdentifier)GeneratedMessageV3.parseWithIOException(PARSER, (InputStream)input, (ExtensionRegistryLite)extensionRegistry);
        }

        public static ColumnIdentifier parseDelimitedFrom(InputStream input) throws IOException {
            return (ColumnIdentifier)GeneratedMessageV3.parseDelimitedWithIOException(PARSER, (InputStream)input);
        }

        public static ColumnIdentifier parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (ColumnIdentifier)GeneratedMessageV3.parseDelimitedWithIOException(PARSER, (InputStream)input, (ExtensionRegistryLite)extensionRegistry);
        }

        public static ColumnIdentifier parseFrom(CodedInputStream input) throws IOException {
            return (ColumnIdentifier)GeneratedMessageV3.parseWithIOException(PARSER, (CodedInputStream)input);
        }

        public static ColumnIdentifier parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (ColumnIdentifier)GeneratedMessageV3.parseWithIOException(PARSER, (CodedInputStream)input, (ExtensionRegistryLite)extensionRegistry);
        }

        public Builder newBuilderForType() {
            return ColumnIdentifier.newBuilder();
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(ColumnIdentifier prototype) {
            return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
        }

        public Builder toBuilder() {
            return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
        }

        protected Builder newBuilderForType(GeneratedMessageV3.BuilderParent parent) {
            Builder builder = new Builder(parent);
            return builder;
        }

        public static ColumnIdentifier getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<ColumnIdentifier> parser() {
            return PARSER;
        }

        public Parser<ColumnIdentifier> getParserForType() {
            return PARSER;
        }

        public ColumnIdentifier getDefaultInstanceForType() {
            return DEFAULT_INSTANCE;
        }

        public static final class Builder
        extends GeneratedMessageV3.Builder<Builder>
        implements ColumnIdentifierOrBuilder {
            private int bitField0_;
            private List<DocumentPathItem> documentPath_ = Collections.emptyList();
            private RepeatedFieldBuilderV3<DocumentPathItem, DocumentPathItem.Builder, DocumentPathItemOrBuilder> documentPathBuilder_;
            private java.lang.Object name_ = "";
            private java.lang.Object tableName_ = "";
            private java.lang.Object schemaName_ = "";

            public static final Descriptors.Descriptor getDescriptor() {
                return internal_static_Mysqlx_Expr_ColumnIdentifier_descriptor;
            }

            protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                return internal_static_Mysqlx_Expr_ColumnIdentifier_fieldAccessorTable.ensureFieldAccessorsInitialized(ColumnIdentifier.class, Builder.class);
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
                    this.getDocumentPathFieldBuilder();
                }
            }

            public Builder clear() {
                super.clear();
                if (this.documentPathBuilder_ == null) {
                    this.documentPath_ = Collections.emptyList();
                    this.bitField0_ &= 0xFFFFFFFE;
                } else {
                    this.documentPathBuilder_.clear();
                }
                this.name_ = "";
                this.bitField0_ &= 0xFFFFFFFD;
                this.tableName_ = "";
                this.bitField0_ &= 0xFFFFFFFB;
                this.schemaName_ = "";
                this.bitField0_ &= 0xFFFFFFF7;
                return this;
            }

            public Descriptors.Descriptor getDescriptorForType() {
                return internal_static_Mysqlx_Expr_ColumnIdentifier_descriptor;
            }

            public ColumnIdentifier getDefaultInstanceForType() {
                return ColumnIdentifier.getDefaultInstance();
            }

            public ColumnIdentifier build() {
                ColumnIdentifier result = this.buildPartial();
                if (!result.isInitialized()) {
                    throw Builder.newUninitializedMessageException((Message)result);
                }
                return result;
            }

            public ColumnIdentifier buildPartial() {
                ColumnIdentifier result = new ColumnIdentifier(this);
                int from_bitField0_ = this.bitField0_;
                int to_bitField0_ = 0;
                if (this.documentPathBuilder_ == null) {
                    if ((this.bitField0_ & 1) != 0) {
                        this.documentPath_ = Collections.unmodifiableList(this.documentPath_);
                        this.bitField0_ &= 0xFFFFFFFE;
                    }
                    result.documentPath_ = this.documentPath_;
                } else {
                    result.documentPath_ = this.documentPathBuilder_.build();
                }
                if ((from_bitField0_ & 2) != 0) {
                    to_bitField0_ |= 1;
                }
                result.name_ = this.name_;
                if ((from_bitField0_ & 4) != 0) {
                    to_bitField0_ |= 2;
                }
                result.tableName_ = this.tableName_;
                if ((from_bitField0_ & 8) != 0) {
                    to_bitField0_ |= 4;
                }
                result.schemaName_ = this.schemaName_;
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
                if (other instanceof ColumnIdentifier) {
                    return this.mergeFrom((ColumnIdentifier)other);
                }
                super.mergeFrom(other);
                return this;
            }

            public Builder mergeFrom(ColumnIdentifier other) {
                if (other == ColumnIdentifier.getDefaultInstance()) {
                    return this;
                }
                if (this.documentPathBuilder_ == null) {
                    if (!other.documentPath_.isEmpty()) {
                        if (this.documentPath_.isEmpty()) {
                            this.documentPath_ = other.documentPath_;
                            this.bitField0_ &= 0xFFFFFFFE;
                        } else {
                            this.ensureDocumentPathIsMutable();
                            this.documentPath_.addAll(other.documentPath_);
                        }
                        this.onChanged();
                    }
                } else if (!other.documentPath_.isEmpty()) {
                    if (this.documentPathBuilder_.isEmpty()) {
                        this.documentPathBuilder_.dispose();
                        this.documentPathBuilder_ = null;
                        this.documentPath_ = other.documentPath_;
                        this.bitField0_ &= 0xFFFFFFFE;
                        this.documentPathBuilder_ = alwaysUseFieldBuilders ? this.getDocumentPathFieldBuilder() : null;
                    } else {
                        this.documentPathBuilder_.addAllMessages((Iterable)other.documentPath_);
                    }
                }
                if (other.hasName()) {
                    this.bitField0_ |= 2;
                    this.name_ = other.name_;
                    this.onChanged();
                }
                if (other.hasTableName()) {
                    this.bitField0_ |= 4;
                    this.tableName_ = other.tableName_;
                    this.onChanged();
                }
                if (other.hasSchemaName()) {
                    this.bitField0_ |= 8;
                    this.schemaName_ = other.schemaName_;
                    this.onChanged();
                }
                this.mergeUnknownFields(other.unknownFields);
                this.onChanged();
                return this;
            }

            public final boolean isInitialized() {
                for (int i = 0; i < this.getDocumentPathCount(); ++i) {
                    if (this.getDocumentPath(i).isInitialized()) continue;
                    return false;
                }
                return true;
            }

            public Builder mergeFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                ColumnIdentifier parsedMessage = null;
                try {
                    parsedMessage = (ColumnIdentifier)PARSER.parsePartialFrom(input, extensionRegistry);
                }
                catch (InvalidProtocolBufferException e) {
                    parsedMessage = (ColumnIdentifier)e.getUnfinishedMessage();
                    throw e.unwrapIOException();
                }
                finally {
                    if (parsedMessage != null) {
                        this.mergeFrom(parsedMessage);
                    }
                }
                return this;
            }

            private void ensureDocumentPathIsMutable() {
                if ((this.bitField0_ & 1) == 0) {
                    this.documentPath_ = new ArrayList<DocumentPathItem>(this.documentPath_);
                    this.bitField0_ |= 1;
                }
            }

            @Override
            public List<DocumentPathItem> getDocumentPathList() {
                if (this.documentPathBuilder_ == null) {
                    return Collections.unmodifiableList(this.documentPath_);
                }
                return this.documentPathBuilder_.getMessageList();
            }

            @Override
            public int getDocumentPathCount() {
                if (this.documentPathBuilder_ == null) {
                    return this.documentPath_.size();
                }
                return this.documentPathBuilder_.getCount();
            }

            @Override
            public DocumentPathItem getDocumentPath(int index) {
                if (this.documentPathBuilder_ == null) {
                    return this.documentPath_.get(index);
                }
                return (DocumentPathItem)this.documentPathBuilder_.getMessage(index);
            }

            public Builder setDocumentPath(int index, DocumentPathItem value) {
                if (this.documentPathBuilder_ == null) {
                    if (value == null) {
                        throw new NullPointerException();
                    }
                    this.ensureDocumentPathIsMutable();
                    this.documentPath_.set(index, value);
                    this.onChanged();
                } else {
                    this.documentPathBuilder_.setMessage(index, (AbstractMessage)value);
                }
                return this;
            }

            public Builder setDocumentPath(int index, DocumentPathItem.Builder builderForValue) {
                if (this.documentPathBuilder_ == null) {
                    this.ensureDocumentPathIsMutable();
                    this.documentPath_.set(index, builderForValue.build());
                    this.onChanged();
                } else {
                    this.documentPathBuilder_.setMessage(index, (AbstractMessage)builderForValue.build());
                }
                return this;
            }

            public Builder addDocumentPath(DocumentPathItem value) {
                if (this.documentPathBuilder_ == null) {
                    if (value == null) {
                        throw new NullPointerException();
                    }
                    this.ensureDocumentPathIsMutable();
                    this.documentPath_.add(value);
                    this.onChanged();
                } else {
                    this.documentPathBuilder_.addMessage((AbstractMessage)value);
                }
                return this;
            }

            public Builder addDocumentPath(int index, DocumentPathItem value) {
                if (this.documentPathBuilder_ == null) {
                    if (value == null) {
                        throw new NullPointerException();
                    }
                    this.ensureDocumentPathIsMutable();
                    this.documentPath_.add(index, value);
                    this.onChanged();
                } else {
                    this.documentPathBuilder_.addMessage(index, (AbstractMessage)value);
                }
                return this;
            }

            public Builder addDocumentPath(DocumentPathItem.Builder builderForValue) {
                if (this.documentPathBuilder_ == null) {
                    this.ensureDocumentPathIsMutable();
                    this.documentPath_.add(builderForValue.build());
                    this.onChanged();
                } else {
                    this.documentPathBuilder_.addMessage((AbstractMessage)builderForValue.build());
                }
                return this;
            }

            public Builder addDocumentPath(int index, DocumentPathItem.Builder builderForValue) {
                if (this.documentPathBuilder_ == null) {
                    this.ensureDocumentPathIsMutable();
                    this.documentPath_.add(index, builderForValue.build());
                    this.onChanged();
                } else {
                    this.documentPathBuilder_.addMessage(index, (AbstractMessage)builderForValue.build());
                }
                return this;
            }

            public Builder addAllDocumentPath(Iterable<? extends DocumentPathItem> values) {
                if (this.documentPathBuilder_ == null) {
                    this.ensureDocumentPathIsMutable();
                    AbstractMessageLite.Builder.addAll(values, this.documentPath_);
                    this.onChanged();
                } else {
                    this.documentPathBuilder_.addAllMessages(values);
                }
                return this;
            }

            public Builder clearDocumentPath() {
                if (this.documentPathBuilder_ == null) {
                    this.documentPath_ = Collections.emptyList();
                    this.bitField0_ &= 0xFFFFFFFE;
                    this.onChanged();
                } else {
                    this.documentPathBuilder_.clear();
                }
                return this;
            }

            public Builder removeDocumentPath(int index) {
                if (this.documentPathBuilder_ == null) {
                    this.ensureDocumentPathIsMutable();
                    this.documentPath_.remove(index);
                    this.onChanged();
                } else {
                    this.documentPathBuilder_.remove(index);
                }
                return this;
            }

            public DocumentPathItem.Builder getDocumentPathBuilder(int index) {
                return (DocumentPathItem.Builder)this.getDocumentPathFieldBuilder().getBuilder(index);
            }

            @Override
            public DocumentPathItemOrBuilder getDocumentPathOrBuilder(int index) {
                if (this.documentPathBuilder_ == null) {
                    return this.documentPath_.get(index);
                }
                return (DocumentPathItemOrBuilder)this.documentPathBuilder_.getMessageOrBuilder(index);
            }

            @Override
            public List<? extends DocumentPathItemOrBuilder> getDocumentPathOrBuilderList() {
                if (this.documentPathBuilder_ != null) {
                    return this.documentPathBuilder_.getMessageOrBuilderList();
                }
                return Collections.unmodifiableList(this.documentPath_);
            }

            public DocumentPathItem.Builder addDocumentPathBuilder() {
                return (DocumentPathItem.Builder)this.getDocumentPathFieldBuilder().addBuilder((AbstractMessage)DocumentPathItem.getDefaultInstance());
            }

            public DocumentPathItem.Builder addDocumentPathBuilder(int index) {
                return (DocumentPathItem.Builder)this.getDocumentPathFieldBuilder().addBuilder(index, (AbstractMessage)DocumentPathItem.getDefaultInstance());
            }

            public List<DocumentPathItem.Builder> getDocumentPathBuilderList() {
                return this.getDocumentPathFieldBuilder().getBuilderList();
            }

            private RepeatedFieldBuilderV3<DocumentPathItem, DocumentPathItem.Builder, DocumentPathItemOrBuilder> getDocumentPathFieldBuilder() {
                if (this.documentPathBuilder_ == null) {
                    this.documentPathBuilder_ = new RepeatedFieldBuilderV3(this.documentPath_, (this.bitField0_ & 1) != 0, (AbstractMessage.BuilderParent)this.getParentForChildren(), this.isClean());
                    this.documentPath_ = null;
                }
                return this.documentPathBuilder_;
            }

            @Override
            public boolean hasName() {
                return (this.bitField0_ & 2) != 0;
            }

            @Override
            public String getName() {
                java.lang.Object ref = this.name_;
                if (!(ref instanceof String)) {
                    ByteString bs = (ByteString)ref;
                    String s = bs.toStringUtf8();
                    if (bs.isValidUtf8()) {
                        this.name_ = s;
                    }
                    return s;
                }
                return (String)ref;
            }

            @Override
            public ByteString getNameBytes() {
                java.lang.Object ref = this.name_;
                if (ref instanceof String) {
                    ByteString b = ByteString.copyFromUtf8((String)((String)ref));
                    this.name_ = b;
                    return b;
                }
                return (ByteString)ref;
            }

            public Builder setName(String value) {
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
                this.name_ = ColumnIdentifier.getDefaultInstance().getName();
                this.onChanged();
                return this;
            }

            public Builder setNameBytes(ByteString value) {
                if (value == null) {
                    throw new NullPointerException();
                }
                this.bitField0_ |= 2;
                this.name_ = value;
                this.onChanged();
                return this;
            }

            @Override
            public boolean hasTableName() {
                return (this.bitField0_ & 4) != 0;
            }

            @Override
            public String getTableName() {
                java.lang.Object ref = this.tableName_;
                if (!(ref instanceof String)) {
                    ByteString bs = (ByteString)ref;
                    String s = bs.toStringUtf8();
                    if (bs.isValidUtf8()) {
                        this.tableName_ = s;
                    }
                    return s;
                }
                return (String)ref;
            }

            @Override
            public ByteString getTableNameBytes() {
                java.lang.Object ref = this.tableName_;
                if (ref instanceof String) {
                    ByteString b = ByteString.copyFromUtf8((String)((String)ref));
                    this.tableName_ = b;
                    return b;
                }
                return (ByteString)ref;
            }

            public Builder setTableName(String value) {
                if (value == null) {
                    throw new NullPointerException();
                }
                this.bitField0_ |= 4;
                this.tableName_ = value;
                this.onChanged();
                return this;
            }

            public Builder clearTableName() {
                this.bitField0_ &= 0xFFFFFFFB;
                this.tableName_ = ColumnIdentifier.getDefaultInstance().getTableName();
                this.onChanged();
                return this;
            }

            public Builder setTableNameBytes(ByteString value) {
                if (value == null) {
                    throw new NullPointerException();
                }
                this.bitField0_ |= 4;
                this.tableName_ = value;
                this.onChanged();
                return this;
            }

            @Override
            public boolean hasSchemaName() {
                return (this.bitField0_ & 8) != 0;
            }

            @Override
            public String getSchemaName() {
                java.lang.Object ref = this.schemaName_;
                if (!(ref instanceof String)) {
                    ByteString bs = (ByteString)ref;
                    String s = bs.toStringUtf8();
                    if (bs.isValidUtf8()) {
                        this.schemaName_ = s;
                    }
                    return s;
                }
                return (String)ref;
            }

            @Override
            public ByteString getSchemaNameBytes() {
                java.lang.Object ref = this.schemaName_;
                if (ref instanceof String) {
                    ByteString b = ByteString.copyFromUtf8((String)((String)ref));
                    this.schemaName_ = b;
                    return b;
                }
                return (ByteString)ref;
            }

            public Builder setSchemaName(String value) {
                if (value == null) {
                    throw new NullPointerException();
                }
                this.bitField0_ |= 8;
                this.schemaName_ = value;
                this.onChanged();
                return this;
            }

            public Builder clearSchemaName() {
                this.bitField0_ &= 0xFFFFFFF7;
                this.schemaName_ = ColumnIdentifier.getDefaultInstance().getSchemaName();
                this.onChanged();
                return this;
            }

            public Builder setSchemaNameBytes(ByteString value) {
                if (value == null) {
                    throw new NullPointerException();
                }
                this.bitField0_ |= 8;
                this.schemaName_ = value;
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

    public static interface ColumnIdentifierOrBuilder
    extends MessageOrBuilder {
        public List<DocumentPathItem> getDocumentPathList();

        public DocumentPathItem getDocumentPath(int var1);

        public int getDocumentPathCount();

        public List<? extends DocumentPathItemOrBuilder> getDocumentPathOrBuilderList();

        public DocumentPathItemOrBuilder getDocumentPathOrBuilder(int var1);

        public boolean hasName();

        public String getName();

        public ByteString getNameBytes();

        public boolean hasTableName();

        public String getTableName();

        public ByteString getTableNameBytes();

        public boolean hasSchemaName();

        public String getSchemaName();

        public ByteString getSchemaNameBytes();
    }

    public static final class DocumentPathItem
    extends GeneratedMessageV3
    implements DocumentPathItemOrBuilder {
        private static final long serialVersionUID = 0L;
        private int bitField0_;
        public static final int TYPE_FIELD_NUMBER = 1;
        private int type_;
        public static final int VALUE_FIELD_NUMBER = 2;
        private volatile java.lang.Object value_;
        public static final int INDEX_FIELD_NUMBER = 3;
        private int index_;
        private byte memoizedIsInitialized = (byte)-1;
        private static final DocumentPathItem DEFAULT_INSTANCE = new DocumentPathItem();
        @Deprecated
        public static final Parser<DocumentPathItem> PARSER = new AbstractParser<DocumentPathItem>(){

            public DocumentPathItem parsePartialFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                return new DocumentPathItem(input, extensionRegistry);
            }
        };

        private DocumentPathItem(GeneratedMessageV3.Builder<?> builder) {
            super(builder);
        }

        private DocumentPathItem() {
            this.type_ = 1;
            this.value_ = "";
        }

        protected java.lang.Object newInstance(GeneratedMessageV3.UnusedPrivateParameter unused) {
            return new DocumentPathItem();
        }

        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        private DocumentPathItem(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
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
                            int rawValue = input.readEnum();
                            Type value = Type.valueOf(rawValue);
                            if (value == null) {
                                unknownFields.mergeVarintField(1, rawValue);
                                continue block12;
                            }
                            this.bitField0_ |= 1;
                            this.type_ = rawValue;
                            continue block12;
                        }
                        case 18: {
                            ByteString bs = input.readBytes();
                            this.bitField0_ |= 2;
                            this.value_ = bs;
                            continue block12;
                        }
                        case 24: {
                            this.bitField0_ |= 4;
                            this.index_ = input.readUInt32();
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
            return internal_static_Mysqlx_Expr_DocumentPathItem_descriptor;
        }

        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return internal_static_Mysqlx_Expr_DocumentPathItem_fieldAccessorTable.ensureFieldAccessorsInitialized(DocumentPathItem.class, Builder.class);
        }

        @Override
        public boolean hasType() {
            return (this.bitField0_ & 1) != 0;
        }

        @Override
        public Type getType() {
            Type result = Type.valueOf(this.type_);
            return result == null ? Type.MEMBER : result;
        }

        @Override
        public boolean hasValue() {
            return (this.bitField0_ & 2) != 0;
        }

        @Override
        public String getValue() {
            java.lang.Object ref = this.value_;
            if (ref instanceof String) {
                return (String)ref;
            }
            ByteString bs = (ByteString)ref;
            String s = bs.toStringUtf8();
            if (bs.isValidUtf8()) {
                this.value_ = s;
            }
            return s;
        }

        @Override
        public ByteString getValueBytes() {
            java.lang.Object ref = this.value_;
            if (ref instanceof String) {
                ByteString b = ByteString.copyFromUtf8((String)((String)ref));
                this.value_ = b;
                return b;
            }
            return (ByteString)ref;
        }

        @Override
        public boolean hasIndex() {
            return (this.bitField0_ & 4) != 0;
        }

        @Override
        public int getIndex() {
            return this.index_;
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
                GeneratedMessageV3.writeString((CodedOutputStream)output, (int)2, (java.lang.Object)this.value_);
            }
            if ((this.bitField0_ & 4) != 0) {
                output.writeUInt32(3, this.index_);
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
                size += GeneratedMessageV3.computeStringSize((int)2, (java.lang.Object)this.value_);
            }
            if ((this.bitField0_ & 4) != 0) {
                size += CodedOutputStream.computeUInt32Size((int)3, (int)this.index_);
            }
            this.memoizedSize = size += this.unknownFields.getSerializedSize();
            return size;
        }

        public boolean equals(java.lang.Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof DocumentPathItem)) {
                return super.equals(obj);
            }
            DocumentPathItem other = (DocumentPathItem)obj;
            if (this.hasType() != other.hasType()) {
                return false;
            }
            if (this.hasType() && this.type_ != other.type_) {
                return false;
            }
            if (this.hasValue() != other.hasValue()) {
                return false;
            }
            if (this.hasValue() && !this.getValue().equals(other.getValue())) {
                return false;
            }
            if (this.hasIndex() != other.hasIndex()) {
                return false;
            }
            if (this.hasIndex() && this.getIndex() != other.getIndex()) {
                return false;
            }
            return this.unknownFields.equals((java.lang.Object)other.unknownFields);
        }

        public int hashCode() {
            if (this.memoizedHashCode != 0) {
                return this.memoizedHashCode;
            }
            int hash = 41;
            hash = 19 * hash + DocumentPathItem.getDescriptor().hashCode();
            if (this.hasType()) {
                hash = 37 * hash + 1;
                hash = 53 * hash + this.type_;
            }
            if (this.hasValue()) {
                hash = 37 * hash + 2;
                hash = 53 * hash + this.getValue().hashCode();
            }
            if (this.hasIndex()) {
                hash = 37 * hash + 3;
                hash = 53 * hash + this.getIndex();
            }
            this.memoizedHashCode = hash = 29 * hash + this.unknownFields.hashCode();
            return hash;
        }

        public static DocumentPathItem parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
            return (DocumentPathItem)PARSER.parseFrom(data);
        }

        public static DocumentPathItem parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (DocumentPathItem)PARSER.parseFrom(data, extensionRegistry);
        }

        public static DocumentPathItem parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return (DocumentPathItem)PARSER.parseFrom(data);
        }

        public static DocumentPathItem parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (DocumentPathItem)PARSER.parseFrom(data, extensionRegistry);
        }

        public static DocumentPathItem parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return (DocumentPathItem)PARSER.parseFrom(data);
        }

        public static DocumentPathItem parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (DocumentPathItem)PARSER.parseFrom(data, extensionRegistry);
        }

        public static DocumentPathItem parseFrom(InputStream input) throws IOException {
            return (DocumentPathItem)GeneratedMessageV3.parseWithIOException(PARSER, (InputStream)input);
        }

        public static DocumentPathItem parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (DocumentPathItem)GeneratedMessageV3.parseWithIOException(PARSER, (InputStream)input, (ExtensionRegistryLite)extensionRegistry);
        }

        public static DocumentPathItem parseDelimitedFrom(InputStream input) throws IOException {
            return (DocumentPathItem)GeneratedMessageV3.parseDelimitedWithIOException(PARSER, (InputStream)input);
        }

        public static DocumentPathItem parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (DocumentPathItem)GeneratedMessageV3.parseDelimitedWithIOException(PARSER, (InputStream)input, (ExtensionRegistryLite)extensionRegistry);
        }

        public static DocumentPathItem parseFrom(CodedInputStream input) throws IOException {
            return (DocumentPathItem)GeneratedMessageV3.parseWithIOException(PARSER, (CodedInputStream)input);
        }

        public static DocumentPathItem parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (DocumentPathItem)GeneratedMessageV3.parseWithIOException(PARSER, (CodedInputStream)input, (ExtensionRegistryLite)extensionRegistry);
        }

        public Builder newBuilderForType() {
            return DocumentPathItem.newBuilder();
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(DocumentPathItem prototype) {
            return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
        }

        public Builder toBuilder() {
            return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
        }

        protected Builder newBuilderForType(GeneratedMessageV3.BuilderParent parent) {
            Builder builder = new Builder(parent);
            return builder;
        }

        public static DocumentPathItem getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<DocumentPathItem> parser() {
            return PARSER;
        }

        public Parser<DocumentPathItem> getParserForType() {
            return PARSER;
        }

        public DocumentPathItem getDefaultInstanceForType() {
            return DEFAULT_INSTANCE;
        }

        public static final class Builder
        extends GeneratedMessageV3.Builder<Builder>
        implements DocumentPathItemOrBuilder {
            private int bitField0_;
            private int type_ = 1;
            private java.lang.Object value_ = "";
            private int index_;

            public static final Descriptors.Descriptor getDescriptor() {
                return internal_static_Mysqlx_Expr_DocumentPathItem_descriptor;
            }

            protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                return internal_static_Mysqlx_Expr_DocumentPathItem_fieldAccessorTable.ensureFieldAccessorsInitialized(DocumentPathItem.class, Builder.class);
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
                this.value_ = "";
                this.bitField0_ &= 0xFFFFFFFD;
                this.index_ = 0;
                this.bitField0_ &= 0xFFFFFFFB;
                return this;
            }

            public Descriptors.Descriptor getDescriptorForType() {
                return internal_static_Mysqlx_Expr_DocumentPathItem_descriptor;
            }

            public DocumentPathItem getDefaultInstanceForType() {
                return DocumentPathItem.getDefaultInstance();
            }

            public DocumentPathItem build() {
                DocumentPathItem result = this.buildPartial();
                if (!result.isInitialized()) {
                    throw Builder.newUninitializedMessageException((Message)result);
                }
                return result;
            }

            public DocumentPathItem buildPartial() {
                DocumentPathItem result = new DocumentPathItem(this);
                int from_bitField0_ = this.bitField0_;
                int to_bitField0_ = 0;
                if ((from_bitField0_ & 1) != 0) {
                    to_bitField0_ |= 1;
                }
                result.type_ = this.type_;
                if ((from_bitField0_ & 2) != 0) {
                    to_bitField0_ |= 2;
                }
                result.value_ = this.value_;
                if ((from_bitField0_ & 4) != 0) {
                    result.index_ = this.index_;
                    to_bitField0_ |= 4;
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
                if (other instanceof DocumentPathItem) {
                    return this.mergeFrom((DocumentPathItem)other);
                }
                super.mergeFrom(other);
                return this;
            }

            public Builder mergeFrom(DocumentPathItem other) {
                if (other == DocumentPathItem.getDefaultInstance()) {
                    return this;
                }
                if (other.hasType()) {
                    this.setType(other.getType());
                }
                if (other.hasValue()) {
                    this.bitField0_ |= 2;
                    this.value_ = other.value_;
                    this.onChanged();
                }
                if (other.hasIndex()) {
                    this.setIndex(other.getIndex());
                }
                this.mergeUnknownFields(other.unknownFields);
                this.onChanged();
                return this;
            }

            public final boolean isInitialized() {
                return this.hasType();
            }

            public Builder mergeFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                DocumentPathItem parsedMessage = null;
                try {
                    parsedMessage = (DocumentPathItem)PARSER.parsePartialFrom(input, extensionRegistry);
                }
                catch (InvalidProtocolBufferException e) {
                    parsedMessage = (DocumentPathItem)e.getUnfinishedMessage();
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
                return result == null ? Type.MEMBER : result;
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
            public boolean hasValue() {
                return (this.bitField0_ & 2) != 0;
            }

            @Override
            public String getValue() {
                java.lang.Object ref = this.value_;
                if (!(ref instanceof String)) {
                    ByteString bs = (ByteString)ref;
                    String s = bs.toStringUtf8();
                    if (bs.isValidUtf8()) {
                        this.value_ = s;
                    }
                    return s;
                }
                return (String)ref;
            }

            @Override
            public ByteString getValueBytes() {
                java.lang.Object ref = this.value_;
                if (ref instanceof String) {
                    ByteString b = ByteString.copyFromUtf8((String)((String)ref));
                    this.value_ = b;
                    return b;
                }
                return (ByteString)ref;
            }

            public Builder setValue(String value) {
                if (value == null) {
                    throw new NullPointerException();
                }
                this.bitField0_ |= 2;
                this.value_ = value;
                this.onChanged();
                return this;
            }

            public Builder clearValue() {
                this.bitField0_ &= 0xFFFFFFFD;
                this.value_ = DocumentPathItem.getDefaultInstance().getValue();
                this.onChanged();
                return this;
            }

            public Builder setValueBytes(ByteString value) {
                if (value == null) {
                    throw new NullPointerException();
                }
                this.bitField0_ |= 2;
                this.value_ = value;
                this.onChanged();
                return this;
            }

            @Override
            public boolean hasIndex() {
                return (this.bitField0_ & 4) != 0;
            }

            @Override
            public int getIndex() {
                return this.index_;
            }

            public Builder setIndex(int value) {
                this.bitField0_ |= 4;
                this.index_ = value;
                this.onChanged();
                return this;
            }

            public Builder clearIndex() {
                this.bitField0_ &= 0xFFFFFFFB;
                this.index_ = 0;
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

        public static enum Type implements ProtocolMessageEnum
        {
            MEMBER(1),
            MEMBER_ASTERISK(2),
            ARRAY_INDEX(3),
            ARRAY_INDEX_ASTERISK(4),
            DOUBLE_ASTERISK(5);

            public static final int MEMBER_VALUE = 1;
            public static final int MEMBER_ASTERISK_VALUE = 2;
            public static final int ARRAY_INDEX_VALUE = 3;
            public static final int ARRAY_INDEX_ASTERISK_VALUE = 4;
            public static final int DOUBLE_ASTERISK_VALUE = 5;
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
                        return MEMBER;
                    }
                    case 2: {
                        return MEMBER_ASTERISK;
                    }
                    case 3: {
                        return ARRAY_INDEX;
                    }
                    case 4: {
                        return ARRAY_INDEX_ASTERISK;
                    }
                    case 5: {
                        return DOUBLE_ASTERISK;
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
                return (Descriptors.EnumDescriptor)DocumentPathItem.getDescriptor().getEnumTypes().get(0);
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

    public static interface DocumentPathItemOrBuilder
    extends MessageOrBuilder {
        public boolean hasType();

        public DocumentPathItem.Type getType();

        public boolean hasValue();

        public String getValue();

        public ByteString getValueBytes();

        public boolean hasIndex();

        public int getIndex();
    }

    public static final class Identifier
    extends GeneratedMessageV3
    implements IdentifierOrBuilder {
        private static final long serialVersionUID = 0L;
        private int bitField0_;
        public static final int NAME_FIELD_NUMBER = 1;
        private volatile java.lang.Object name_;
        public static final int SCHEMA_NAME_FIELD_NUMBER = 2;
        private volatile java.lang.Object schemaName_;
        private byte memoizedIsInitialized = (byte)-1;
        private static final Identifier DEFAULT_INSTANCE = new Identifier();
        @Deprecated
        public static final Parser<Identifier> PARSER = new AbstractParser<Identifier>(){

            public Identifier parsePartialFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                return new Identifier(input, extensionRegistry);
            }
        };

        private Identifier(GeneratedMessageV3.Builder<?> builder) {
            super(builder);
        }

        private Identifier() {
            this.name_ = "";
            this.schemaName_ = "";
        }

        protected java.lang.Object newInstance(GeneratedMessageV3.UnusedPrivateParameter unused) {
            return new Identifier();
        }

        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        private Identifier(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
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
                            this.name_ = bs;
                            continue block11;
                        }
                        case 18: {
                            ByteString bs = input.readBytes();
                            this.bitField0_ |= 2;
                            this.schemaName_ = bs;
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
            return internal_static_Mysqlx_Expr_Identifier_descriptor;
        }

        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return internal_static_Mysqlx_Expr_Identifier_fieldAccessorTable.ensureFieldAccessorsInitialized(Identifier.class, Builder.class);
        }

        @Override
        public boolean hasName() {
            return (this.bitField0_ & 1) != 0;
        }

        @Override
        public String getName() {
            java.lang.Object ref = this.name_;
            if (ref instanceof String) {
                return (String)ref;
            }
            ByteString bs = (ByteString)ref;
            String s = bs.toStringUtf8();
            if (bs.isValidUtf8()) {
                this.name_ = s;
            }
            return s;
        }

        @Override
        public ByteString getNameBytes() {
            java.lang.Object ref = this.name_;
            if (ref instanceof String) {
                ByteString b = ByteString.copyFromUtf8((String)((String)ref));
                this.name_ = b;
                return b;
            }
            return (ByteString)ref;
        }

        @Override
        public boolean hasSchemaName() {
            return (this.bitField0_ & 2) != 0;
        }

        @Override
        public String getSchemaName() {
            java.lang.Object ref = this.schemaName_;
            if (ref instanceof String) {
                return (String)ref;
            }
            ByteString bs = (ByteString)ref;
            String s = bs.toStringUtf8();
            if (bs.isValidUtf8()) {
                this.schemaName_ = s;
            }
            return s;
        }

        @Override
        public ByteString getSchemaNameBytes() {
            java.lang.Object ref = this.schemaName_;
            if (ref instanceof String) {
                ByteString b = ByteString.copyFromUtf8((String)((String)ref));
                this.schemaName_ = b;
                return b;
            }
            return (ByteString)ref;
        }

        public final boolean isInitialized() {
            byte isInitialized = this.memoizedIsInitialized;
            if (isInitialized == 1) {
                return true;
            }
            if (isInitialized == 0) {
                return false;
            }
            if (!this.hasName()) {
                this.memoizedIsInitialized = 0;
                return false;
            }
            this.memoizedIsInitialized = 1;
            return true;
        }

        public void writeTo(CodedOutputStream output) throws IOException {
            if ((this.bitField0_ & 1) != 0) {
                GeneratedMessageV3.writeString((CodedOutputStream)output, (int)1, (java.lang.Object)this.name_);
            }
            if ((this.bitField0_ & 2) != 0) {
                GeneratedMessageV3.writeString((CodedOutputStream)output, (int)2, (java.lang.Object)this.schemaName_);
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
                size += GeneratedMessageV3.computeStringSize((int)1, (java.lang.Object)this.name_);
            }
            if ((this.bitField0_ & 2) != 0) {
                size += GeneratedMessageV3.computeStringSize((int)2, (java.lang.Object)this.schemaName_);
            }
            this.memoizedSize = size += this.unknownFields.getSerializedSize();
            return size;
        }

        public boolean equals(java.lang.Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof Identifier)) {
                return super.equals(obj);
            }
            Identifier other = (Identifier)obj;
            if (this.hasName() != other.hasName()) {
                return false;
            }
            if (this.hasName() && !this.getName().equals(other.getName())) {
                return false;
            }
            if (this.hasSchemaName() != other.hasSchemaName()) {
                return false;
            }
            if (this.hasSchemaName() && !this.getSchemaName().equals(other.getSchemaName())) {
                return false;
            }
            return this.unknownFields.equals((java.lang.Object)other.unknownFields);
        }

        public int hashCode() {
            if (this.memoizedHashCode != 0) {
                return this.memoizedHashCode;
            }
            int hash = 41;
            hash = 19 * hash + Identifier.getDescriptor().hashCode();
            if (this.hasName()) {
                hash = 37 * hash + 1;
                hash = 53 * hash + this.getName().hashCode();
            }
            if (this.hasSchemaName()) {
                hash = 37 * hash + 2;
                hash = 53 * hash + this.getSchemaName().hashCode();
            }
            this.memoizedHashCode = hash = 29 * hash + this.unknownFields.hashCode();
            return hash;
        }

        public static Identifier parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
            return (Identifier)PARSER.parseFrom(data);
        }

        public static Identifier parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (Identifier)PARSER.parseFrom(data, extensionRegistry);
        }

        public static Identifier parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return (Identifier)PARSER.parseFrom(data);
        }

        public static Identifier parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (Identifier)PARSER.parseFrom(data, extensionRegistry);
        }

        public static Identifier parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return (Identifier)PARSER.parseFrom(data);
        }

        public static Identifier parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (Identifier)PARSER.parseFrom(data, extensionRegistry);
        }

        public static Identifier parseFrom(InputStream input) throws IOException {
            return (Identifier)GeneratedMessageV3.parseWithIOException(PARSER, (InputStream)input);
        }

        public static Identifier parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (Identifier)GeneratedMessageV3.parseWithIOException(PARSER, (InputStream)input, (ExtensionRegistryLite)extensionRegistry);
        }

        public static Identifier parseDelimitedFrom(InputStream input) throws IOException {
            return (Identifier)GeneratedMessageV3.parseDelimitedWithIOException(PARSER, (InputStream)input);
        }

        public static Identifier parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (Identifier)GeneratedMessageV3.parseDelimitedWithIOException(PARSER, (InputStream)input, (ExtensionRegistryLite)extensionRegistry);
        }

        public static Identifier parseFrom(CodedInputStream input) throws IOException {
            return (Identifier)GeneratedMessageV3.parseWithIOException(PARSER, (CodedInputStream)input);
        }

        public static Identifier parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (Identifier)GeneratedMessageV3.parseWithIOException(PARSER, (CodedInputStream)input, (ExtensionRegistryLite)extensionRegistry);
        }

        public Builder newBuilderForType() {
            return Identifier.newBuilder();
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(Identifier prototype) {
            return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
        }

        public Builder toBuilder() {
            return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
        }

        protected Builder newBuilderForType(GeneratedMessageV3.BuilderParent parent) {
            Builder builder = new Builder(parent);
            return builder;
        }

        public static Identifier getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<Identifier> parser() {
            return PARSER;
        }

        public Parser<Identifier> getParserForType() {
            return PARSER;
        }

        public Identifier getDefaultInstanceForType() {
            return DEFAULT_INSTANCE;
        }

        public static final class Builder
        extends GeneratedMessageV3.Builder<Builder>
        implements IdentifierOrBuilder {
            private int bitField0_;
            private java.lang.Object name_ = "";
            private java.lang.Object schemaName_ = "";

            public static final Descriptors.Descriptor getDescriptor() {
                return internal_static_Mysqlx_Expr_Identifier_descriptor;
            }

            protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                return internal_static_Mysqlx_Expr_Identifier_fieldAccessorTable.ensureFieldAccessorsInitialized(Identifier.class, Builder.class);
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
                this.name_ = "";
                this.bitField0_ &= 0xFFFFFFFE;
                this.schemaName_ = "";
                this.bitField0_ &= 0xFFFFFFFD;
                return this;
            }

            public Descriptors.Descriptor getDescriptorForType() {
                return internal_static_Mysqlx_Expr_Identifier_descriptor;
            }

            public Identifier getDefaultInstanceForType() {
                return Identifier.getDefaultInstance();
            }

            public Identifier build() {
                Identifier result = this.buildPartial();
                if (!result.isInitialized()) {
                    throw Builder.newUninitializedMessageException((Message)result);
                }
                return result;
            }

            public Identifier buildPartial() {
                Identifier result = new Identifier(this);
                int from_bitField0_ = this.bitField0_;
                int to_bitField0_ = 0;
                if ((from_bitField0_ & 1) != 0) {
                    to_bitField0_ |= 1;
                }
                result.name_ = this.name_;
                if ((from_bitField0_ & 2) != 0) {
                    to_bitField0_ |= 2;
                }
                result.schemaName_ = this.schemaName_;
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
                if (other instanceof Identifier) {
                    return this.mergeFrom((Identifier)other);
                }
                super.mergeFrom(other);
                return this;
            }

            public Builder mergeFrom(Identifier other) {
                if (other == Identifier.getDefaultInstance()) {
                    return this;
                }
                if (other.hasName()) {
                    this.bitField0_ |= 1;
                    this.name_ = other.name_;
                    this.onChanged();
                }
                if (other.hasSchemaName()) {
                    this.bitField0_ |= 2;
                    this.schemaName_ = other.schemaName_;
                    this.onChanged();
                }
                this.mergeUnknownFields(other.unknownFields);
                this.onChanged();
                return this;
            }

            public final boolean isInitialized() {
                return this.hasName();
            }

            public Builder mergeFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                Identifier parsedMessage = null;
                try {
                    parsedMessage = (Identifier)PARSER.parsePartialFrom(input, extensionRegistry);
                }
                catch (InvalidProtocolBufferException e) {
                    parsedMessage = (Identifier)e.getUnfinishedMessage();
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
            public boolean hasName() {
                return (this.bitField0_ & 1) != 0;
            }

            @Override
            public String getName() {
                java.lang.Object ref = this.name_;
                if (!(ref instanceof String)) {
                    ByteString bs = (ByteString)ref;
                    String s = bs.toStringUtf8();
                    if (bs.isValidUtf8()) {
                        this.name_ = s;
                    }
                    return s;
                }
                return (String)ref;
            }

            @Override
            public ByteString getNameBytes() {
                java.lang.Object ref = this.name_;
                if (ref instanceof String) {
                    ByteString b = ByteString.copyFromUtf8((String)((String)ref));
                    this.name_ = b;
                    return b;
                }
                return (ByteString)ref;
            }

            public Builder setName(String value) {
                if (value == null) {
                    throw new NullPointerException();
                }
                this.bitField0_ |= 1;
                this.name_ = value;
                this.onChanged();
                return this;
            }

            public Builder clearName() {
                this.bitField0_ &= 0xFFFFFFFE;
                this.name_ = Identifier.getDefaultInstance().getName();
                this.onChanged();
                return this;
            }

            public Builder setNameBytes(ByteString value) {
                if (value == null) {
                    throw new NullPointerException();
                }
                this.bitField0_ |= 1;
                this.name_ = value;
                this.onChanged();
                return this;
            }

            @Override
            public boolean hasSchemaName() {
                return (this.bitField0_ & 2) != 0;
            }

            @Override
            public String getSchemaName() {
                java.lang.Object ref = this.schemaName_;
                if (!(ref instanceof String)) {
                    ByteString bs = (ByteString)ref;
                    String s = bs.toStringUtf8();
                    if (bs.isValidUtf8()) {
                        this.schemaName_ = s;
                    }
                    return s;
                }
                return (String)ref;
            }

            @Override
            public ByteString getSchemaNameBytes() {
                java.lang.Object ref = this.schemaName_;
                if (ref instanceof String) {
                    ByteString b = ByteString.copyFromUtf8((String)((String)ref));
                    this.schemaName_ = b;
                    return b;
                }
                return (ByteString)ref;
            }

            public Builder setSchemaName(String value) {
                if (value == null) {
                    throw new NullPointerException();
                }
                this.bitField0_ |= 2;
                this.schemaName_ = value;
                this.onChanged();
                return this;
            }

            public Builder clearSchemaName() {
                this.bitField0_ &= 0xFFFFFFFD;
                this.schemaName_ = Identifier.getDefaultInstance().getSchemaName();
                this.onChanged();
                return this;
            }

            public Builder setSchemaNameBytes(ByteString value) {
                if (value == null) {
                    throw new NullPointerException();
                }
                this.bitField0_ |= 2;
                this.schemaName_ = value;
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

    public static interface IdentifierOrBuilder
    extends MessageOrBuilder {
        public boolean hasName();

        public String getName();

        public ByteString getNameBytes();

        public boolean hasSchemaName();

        public String getSchemaName();

        public ByteString getSchemaNameBytes();
    }

    public static final class Expr
    extends GeneratedMessageV3
    implements ExprOrBuilder {
        private static final long serialVersionUID = 0L;
        private int bitField0_;
        public static final int TYPE_FIELD_NUMBER = 1;
        private int type_;
        public static final int IDENTIFIER_FIELD_NUMBER = 2;
        private ColumnIdentifier identifier_;
        public static final int VARIABLE_FIELD_NUMBER = 3;
        private volatile java.lang.Object variable_;
        public static final int LITERAL_FIELD_NUMBER = 4;
        private MysqlxDatatypes.Scalar literal_;
        public static final int FUNCTION_CALL_FIELD_NUMBER = 5;
        private FunctionCall functionCall_;
        public static final int OPERATOR_FIELD_NUMBER = 6;
        private Operator operator_;
        public static final int POSITION_FIELD_NUMBER = 7;
        private int position_;
        public static final int OBJECT_FIELD_NUMBER = 8;
        private Object object_;
        public static final int ARRAY_FIELD_NUMBER = 9;
        private Array array_;
        private byte memoizedIsInitialized = (byte)-1;
        private static final Expr DEFAULT_INSTANCE = new Expr();
        @Deprecated
        public static final Parser<Expr> PARSER = new AbstractParser<Expr>(){

            public Expr parsePartialFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                return new Expr(input, extensionRegistry);
            }
        };

        private Expr(GeneratedMessageV3.Builder<?> builder) {
            super(builder);
        }

        private Expr() {
            this.type_ = 1;
            this.variable_ = "";
        }

        protected java.lang.Object newInstance(GeneratedMessageV3.UnusedPrivateParameter unused) {
            return new Expr();
        }

        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        private Expr(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            this();
            if (extensionRegistry == null) {
                throw new NullPointerException();
            }
            boolean mutable_bitField0_ = false;
            UnknownFieldSet.Builder unknownFields = UnknownFieldSet.newBuilder();
            try {
                boolean done = false;
                block18: while (!done) {
                    int tag = input.readTag();
                    switch (tag) {
                        case 0: {
                            done = true;
                            continue block18;
                        }
                        case 8: {
                            int rawValue = input.readEnum();
                            Type value = Type.valueOf(rawValue);
                            if (value == null) {
                                unknownFields.mergeVarintField(1, rawValue);
                                continue block18;
                            }
                            this.bitField0_ |= 1;
                            this.type_ = rawValue;
                            continue block18;
                        }
                        case 18: {
                            ColumnIdentifier.Builder subBuilder = null;
                            if ((this.bitField0_ & 2) != 0) {
                                subBuilder = this.identifier_.toBuilder();
                            }
                            this.identifier_ = (ColumnIdentifier)input.readMessage(ColumnIdentifier.PARSER, extensionRegistry);
                            if (subBuilder != null) {
                                subBuilder.mergeFrom(this.identifier_);
                                this.identifier_ = subBuilder.buildPartial();
                            }
                            this.bitField0_ |= 2;
                            continue block18;
                        }
                        case 26: {
                            ByteString bs = input.readBytes();
                            this.bitField0_ |= 4;
                            this.variable_ = bs;
                            continue block18;
                        }
                        case 34: {
                            MysqlxDatatypes.Scalar.Builder subBuilder = null;
                            if ((this.bitField0_ & 8) != 0) {
                                subBuilder = this.literal_.toBuilder();
                            }
                            this.literal_ = (MysqlxDatatypes.Scalar)input.readMessage(MysqlxDatatypes.Scalar.PARSER, extensionRegistry);
                            if (subBuilder != null) {
                                subBuilder.mergeFrom(this.literal_);
                                this.literal_ = subBuilder.buildPartial();
                            }
                            this.bitField0_ |= 8;
                            continue block18;
                        }
                        case 42: {
                            FunctionCall.Builder subBuilder = null;
                            if ((this.bitField0_ & 0x10) != 0) {
                                subBuilder = this.functionCall_.toBuilder();
                            }
                            this.functionCall_ = (FunctionCall)input.readMessage(FunctionCall.PARSER, extensionRegistry);
                            if (subBuilder != null) {
                                subBuilder.mergeFrom(this.functionCall_);
                                this.functionCall_ = subBuilder.buildPartial();
                            }
                            this.bitField0_ |= 0x10;
                            continue block18;
                        }
                        case 50: {
                            Operator.Builder subBuilder = null;
                            if ((this.bitField0_ & 0x20) != 0) {
                                subBuilder = this.operator_.toBuilder();
                            }
                            this.operator_ = (Operator)input.readMessage(Operator.PARSER, extensionRegistry);
                            if (subBuilder != null) {
                                subBuilder.mergeFrom(this.operator_);
                                this.operator_ = subBuilder.buildPartial();
                            }
                            this.bitField0_ |= 0x20;
                            continue block18;
                        }
                        case 56: {
                            this.bitField0_ |= 0x40;
                            this.position_ = input.readUInt32();
                            continue block18;
                        }
                        case 66: {
                            Object.Builder subBuilder = null;
                            if ((this.bitField0_ & 0x80) != 0) {
                                subBuilder = this.object_.toBuilder();
                            }
                            this.object_ = (Object)input.readMessage(Object.PARSER, extensionRegistry);
                            if (subBuilder != null) {
                                subBuilder.mergeFrom(this.object_);
                                this.object_ = subBuilder.buildPartial();
                            }
                            this.bitField0_ |= 0x80;
                            continue block18;
                        }
                        case 74: {
                            Array.Builder subBuilder = null;
                            if ((this.bitField0_ & 0x100) != 0) {
                                subBuilder = this.array_.toBuilder();
                            }
                            this.array_ = (Array)input.readMessage(Array.PARSER, extensionRegistry);
                            if (subBuilder != null) {
                                subBuilder.mergeFrom(this.array_);
                                this.array_ = subBuilder.buildPartial();
                            }
                            this.bitField0_ |= 0x100;
                            continue block18;
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
            return internal_static_Mysqlx_Expr_Expr_descriptor;
        }

        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return internal_static_Mysqlx_Expr_Expr_fieldAccessorTable.ensureFieldAccessorsInitialized(Expr.class, Builder.class);
        }

        @Override
        public boolean hasType() {
            return (this.bitField0_ & 1) != 0;
        }

        @Override
        public Type getType() {
            Type result = Type.valueOf(this.type_);
            return result == null ? Type.IDENT : result;
        }

        @Override
        public boolean hasIdentifier() {
            return (this.bitField0_ & 2) != 0;
        }

        @Override
        public ColumnIdentifier getIdentifier() {
            return this.identifier_ == null ? ColumnIdentifier.getDefaultInstance() : this.identifier_;
        }

        @Override
        public ColumnIdentifierOrBuilder getIdentifierOrBuilder() {
            return this.identifier_ == null ? ColumnIdentifier.getDefaultInstance() : this.identifier_;
        }

        @Override
        public boolean hasVariable() {
            return (this.bitField0_ & 4) != 0;
        }

        @Override
        public String getVariable() {
            java.lang.Object ref = this.variable_;
            if (ref instanceof String) {
                return (String)ref;
            }
            ByteString bs = (ByteString)ref;
            String s = bs.toStringUtf8();
            if (bs.isValidUtf8()) {
                this.variable_ = s;
            }
            return s;
        }

        @Override
        public ByteString getVariableBytes() {
            java.lang.Object ref = this.variable_;
            if (ref instanceof String) {
                ByteString b = ByteString.copyFromUtf8((String)((String)ref));
                this.variable_ = b;
                return b;
            }
            return (ByteString)ref;
        }

        @Override
        public boolean hasLiteral() {
            return (this.bitField0_ & 8) != 0;
        }

        @Override
        public MysqlxDatatypes.Scalar getLiteral() {
            return this.literal_ == null ? MysqlxDatatypes.Scalar.getDefaultInstance() : this.literal_;
        }

        @Override
        public MysqlxDatatypes.ScalarOrBuilder getLiteralOrBuilder() {
            return this.literal_ == null ? MysqlxDatatypes.Scalar.getDefaultInstance() : this.literal_;
        }

        @Override
        public boolean hasFunctionCall() {
            return (this.bitField0_ & 0x10) != 0;
        }

        @Override
        public FunctionCall getFunctionCall() {
            return this.functionCall_ == null ? FunctionCall.getDefaultInstance() : this.functionCall_;
        }

        @Override
        public FunctionCallOrBuilder getFunctionCallOrBuilder() {
            return this.functionCall_ == null ? FunctionCall.getDefaultInstance() : this.functionCall_;
        }

        @Override
        public boolean hasOperator() {
            return (this.bitField0_ & 0x20) != 0;
        }

        @Override
        public Operator getOperator() {
            return this.operator_ == null ? Operator.getDefaultInstance() : this.operator_;
        }

        @Override
        public OperatorOrBuilder getOperatorOrBuilder() {
            return this.operator_ == null ? Operator.getDefaultInstance() : this.operator_;
        }

        @Override
        public boolean hasPosition() {
            return (this.bitField0_ & 0x40) != 0;
        }

        @Override
        public int getPosition() {
            return this.position_;
        }

        @Override
        public boolean hasObject() {
            return (this.bitField0_ & 0x80) != 0;
        }

        @Override
        public Object getObject() {
            return this.object_ == null ? Object.getDefaultInstance() : this.object_;
        }

        @Override
        public ObjectOrBuilder getObjectOrBuilder() {
            return this.object_ == null ? Object.getDefaultInstance() : this.object_;
        }

        @Override
        public boolean hasArray() {
            return (this.bitField0_ & 0x100) != 0;
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
            if (this.hasIdentifier() && !this.getIdentifier().isInitialized()) {
                this.memoizedIsInitialized = 0;
                return false;
            }
            if (this.hasLiteral() && !this.getLiteral().isInitialized()) {
                this.memoizedIsInitialized = 0;
                return false;
            }
            if (this.hasFunctionCall() && !this.getFunctionCall().isInitialized()) {
                this.memoizedIsInitialized = 0;
                return false;
            }
            if (this.hasOperator() && !this.getOperator().isInitialized()) {
                this.memoizedIsInitialized = 0;
                return false;
            }
            if (this.hasObject() && !this.getObject().isInitialized()) {
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
                output.writeMessage(2, (MessageLite)this.getIdentifier());
            }
            if ((this.bitField0_ & 4) != 0) {
                GeneratedMessageV3.writeString((CodedOutputStream)output, (int)3, (java.lang.Object)this.variable_);
            }
            if ((this.bitField0_ & 8) != 0) {
                output.writeMessage(4, (MessageLite)this.getLiteral());
            }
            if ((this.bitField0_ & 0x10) != 0) {
                output.writeMessage(5, (MessageLite)this.getFunctionCall());
            }
            if ((this.bitField0_ & 0x20) != 0) {
                output.writeMessage(6, (MessageLite)this.getOperator());
            }
            if ((this.bitField0_ & 0x40) != 0) {
                output.writeUInt32(7, this.position_);
            }
            if ((this.bitField0_ & 0x80) != 0) {
                output.writeMessage(8, (MessageLite)this.getObject());
            }
            if ((this.bitField0_ & 0x100) != 0) {
                output.writeMessage(9, (MessageLite)this.getArray());
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
                size += CodedOutputStream.computeMessageSize((int)2, (MessageLite)this.getIdentifier());
            }
            if ((this.bitField0_ & 4) != 0) {
                size += GeneratedMessageV3.computeStringSize((int)3, (java.lang.Object)this.variable_);
            }
            if ((this.bitField0_ & 8) != 0) {
                size += CodedOutputStream.computeMessageSize((int)4, (MessageLite)this.getLiteral());
            }
            if ((this.bitField0_ & 0x10) != 0) {
                size += CodedOutputStream.computeMessageSize((int)5, (MessageLite)this.getFunctionCall());
            }
            if ((this.bitField0_ & 0x20) != 0) {
                size += CodedOutputStream.computeMessageSize((int)6, (MessageLite)this.getOperator());
            }
            if ((this.bitField0_ & 0x40) != 0) {
                size += CodedOutputStream.computeUInt32Size((int)7, (int)this.position_);
            }
            if ((this.bitField0_ & 0x80) != 0) {
                size += CodedOutputStream.computeMessageSize((int)8, (MessageLite)this.getObject());
            }
            if ((this.bitField0_ & 0x100) != 0) {
                size += CodedOutputStream.computeMessageSize((int)9, (MessageLite)this.getArray());
            }
            this.memoizedSize = size += this.unknownFields.getSerializedSize();
            return size;
        }

        public boolean equals(java.lang.Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof Expr)) {
                return super.equals(obj);
            }
            Expr other = (Expr)obj;
            if (this.hasType() != other.hasType()) {
                return false;
            }
            if (this.hasType() && this.type_ != other.type_) {
                return false;
            }
            if (this.hasIdentifier() != other.hasIdentifier()) {
                return false;
            }
            if (this.hasIdentifier() && !this.getIdentifier().equals(other.getIdentifier())) {
                return false;
            }
            if (this.hasVariable() != other.hasVariable()) {
                return false;
            }
            if (this.hasVariable() && !this.getVariable().equals(other.getVariable())) {
                return false;
            }
            if (this.hasLiteral() != other.hasLiteral()) {
                return false;
            }
            if (this.hasLiteral() && !this.getLiteral().equals(other.getLiteral())) {
                return false;
            }
            if (this.hasFunctionCall() != other.hasFunctionCall()) {
                return false;
            }
            if (this.hasFunctionCall() && !this.getFunctionCall().equals(other.getFunctionCall())) {
                return false;
            }
            if (this.hasOperator() != other.hasOperator()) {
                return false;
            }
            if (this.hasOperator() && !this.getOperator().equals(other.getOperator())) {
                return false;
            }
            if (this.hasPosition() != other.hasPosition()) {
                return false;
            }
            if (this.hasPosition() && this.getPosition() != other.getPosition()) {
                return false;
            }
            if (this.hasObject() != other.hasObject()) {
                return false;
            }
            if (this.hasObject() && !this.getObject().equals(other.getObject())) {
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
            hash = 19 * hash + Expr.getDescriptor().hashCode();
            if (this.hasType()) {
                hash = 37 * hash + 1;
                hash = 53 * hash + this.type_;
            }
            if (this.hasIdentifier()) {
                hash = 37 * hash + 2;
                hash = 53 * hash + this.getIdentifier().hashCode();
            }
            if (this.hasVariable()) {
                hash = 37 * hash + 3;
                hash = 53 * hash + this.getVariable().hashCode();
            }
            if (this.hasLiteral()) {
                hash = 37 * hash + 4;
                hash = 53 * hash + this.getLiteral().hashCode();
            }
            if (this.hasFunctionCall()) {
                hash = 37 * hash + 5;
                hash = 53 * hash + this.getFunctionCall().hashCode();
            }
            if (this.hasOperator()) {
                hash = 37 * hash + 6;
                hash = 53 * hash + this.getOperator().hashCode();
            }
            if (this.hasPosition()) {
                hash = 37 * hash + 7;
                hash = 53 * hash + this.getPosition();
            }
            if (this.hasObject()) {
                hash = 37 * hash + 8;
                hash = 53 * hash + this.getObject().hashCode();
            }
            if (this.hasArray()) {
                hash = 37 * hash + 9;
                hash = 53 * hash + this.getArray().hashCode();
            }
            this.memoizedHashCode = hash = 29 * hash + this.unknownFields.hashCode();
            return hash;
        }

        public static Expr parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
            return (Expr)PARSER.parseFrom(data);
        }

        public static Expr parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (Expr)PARSER.parseFrom(data, extensionRegistry);
        }

        public static Expr parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return (Expr)PARSER.parseFrom(data);
        }

        public static Expr parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (Expr)PARSER.parseFrom(data, extensionRegistry);
        }

        public static Expr parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return (Expr)PARSER.parseFrom(data);
        }

        public static Expr parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (Expr)PARSER.parseFrom(data, extensionRegistry);
        }

        public static Expr parseFrom(InputStream input) throws IOException {
            return (Expr)GeneratedMessageV3.parseWithIOException(PARSER, (InputStream)input);
        }

        public static Expr parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (Expr)GeneratedMessageV3.parseWithIOException(PARSER, (InputStream)input, (ExtensionRegistryLite)extensionRegistry);
        }

        public static Expr parseDelimitedFrom(InputStream input) throws IOException {
            return (Expr)GeneratedMessageV3.parseDelimitedWithIOException(PARSER, (InputStream)input);
        }

        public static Expr parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (Expr)GeneratedMessageV3.parseDelimitedWithIOException(PARSER, (InputStream)input, (ExtensionRegistryLite)extensionRegistry);
        }

        public static Expr parseFrom(CodedInputStream input) throws IOException {
            return (Expr)GeneratedMessageV3.parseWithIOException(PARSER, (CodedInputStream)input);
        }

        public static Expr parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (Expr)GeneratedMessageV3.parseWithIOException(PARSER, (CodedInputStream)input, (ExtensionRegistryLite)extensionRegistry);
        }

        public Builder newBuilderForType() {
            return Expr.newBuilder();
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(Expr prototype) {
            return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
        }

        public Builder toBuilder() {
            return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
        }

        protected Builder newBuilderForType(GeneratedMessageV3.BuilderParent parent) {
            Builder builder = new Builder(parent);
            return builder;
        }

        public static Expr getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<Expr> parser() {
            return PARSER;
        }

        public Parser<Expr> getParserForType() {
            return PARSER;
        }

        public Expr getDefaultInstanceForType() {
            return DEFAULT_INSTANCE;
        }

        public static final class Builder
        extends GeneratedMessageV3.Builder<Builder>
        implements ExprOrBuilder {
            private int bitField0_;
            private int type_ = 1;
            private ColumnIdentifier identifier_;
            private SingleFieldBuilderV3<ColumnIdentifier, ColumnIdentifier.Builder, ColumnIdentifierOrBuilder> identifierBuilder_;
            private java.lang.Object variable_ = "";
            private MysqlxDatatypes.Scalar literal_;
            private SingleFieldBuilderV3<MysqlxDatatypes.Scalar, MysqlxDatatypes.Scalar.Builder, MysqlxDatatypes.ScalarOrBuilder> literalBuilder_;
            private FunctionCall functionCall_;
            private SingleFieldBuilderV3<FunctionCall, FunctionCall.Builder, FunctionCallOrBuilder> functionCallBuilder_;
            private Operator operator_;
            private SingleFieldBuilderV3<Operator, Operator.Builder, OperatorOrBuilder> operatorBuilder_;
            private int position_;
            private Object object_;
            private SingleFieldBuilderV3<Object, Object.Builder, ObjectOrBuilder> objectBuilder_;
            private Array array_;
            private SingleFieldBuilderV3<Array, Array.Builder, ArrayOrBuilder> arrayBuilder_;

            public static final Descriptors.Descriptor getDescriptor() {
                return internal_static_Mysqlx_Expr_Expr_descriptor;
            }

            protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                return internal_static_Mysqlx_Expr_Expr_fieldAccessorTable.ensureFieldAccessorsInitialized(Expr.class, Builder.class);
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
                    this.getIdentifierFieldBuilder();
                    this.getLiteralFieldBuilder();
                    this.getFunctionCallFieldBuilder();
                    this.getOperatorFieldBuilder();
                    this.getObjectFieldBuilder();
                    this.getArrayFieldBuilder();
                }
            }

            public Builder clear() {
                super.clear();
                this.type_ = 1;
                this.bitField0_ &= 0xFFFFFFFE;
                if (this.identifierBuilder_ == null) {
                    this.identifier_ = null;
                } else {
                    this.identifierBuilder_.clear();
                }
                this.bitField0_ &= 0xFFFFFFFD;
                this.variable_ = "";
                this.bitField0_ &= 0xFFFFFFFB;
                if (this.literalBuilder_ == null) {
                    this.literal_ = null;
                } else {
                    this.literalBuilder_.clear();
                }
                this.bitField0_ &= 0xFFFFFFF7;
                if (this.functionCallBuilder_ == null) {
                    this.functionCall_ = null;
                } else {
                    this.functionCallBuilder_.clear();
                }
                this.bitField0_ &= 0xFFFFFFEF;
                if (this.operatorBuilder_ == null) {
                    this.operator_ = null;
                } else {
                    this.operatorBuilder_.clear();
                }
                this.bitField0_ &= 0xFFFFFFDF;
                this.position_ = 0;
                this.bitField0_ &= 0xFFFFFFBF;
                if (this.objectBuilder_ == null) {
                    this.object_ = null;
                } else {
                    this.objectBuilder_.clear();
                }
                this.bitField0_ &= 0xFFFFFF7F;
                if (this.arrayBuilder_ == null) {
                    this.array_ = null;
                } else {
                    this.arrayBuilder_.clear();
                }
                this.bitField0_ &= 0xFFFFFEFF;
                return this;
            }

            public Descriptors.Descriptor getDescriptorForType() {
                return internal_static_Mysqlx_Expr_Expr_descriptor;
            }

            public Expr getDefaultInstanceForType() {
                return Expr.getDefaultInstance();
            }

            public Expr build() {
                Expr result = this.buildPartial();
                if (!result.isInitialized()) {
                    throw Builder.newUninitializedMessageException((Message)result);
                }
                return result;
            }

            public Expr buildPartial() {
                Expr result = new Expr(this);
                int from_bitField0_ = this.bitField0_;
                int to_bitField0_ = 0;
                if ((from_bitField0_ & 1) != 0) {
                    to_bitField0_ |= 1;
                }
                result.type_ = this.type_;
                if ((from_bitField0_ & 2) != 0) {
                    if (this.identifierBuilder_ == null) {
                        result.identifier_ = this.identifier_;
                    } else {
                        result.identifier_ = (ColumnIdentifier)this.identifierBuilder_.build();
                    }
                    to_bitField0_ |= 2;
                }
                if ((from_bitField0_ & 4) != 0) {
                    to_bitField0_ |= 4;
                }
                result.variable_ = this.variable_;
                if ((from_bitField0_ & 8) != 0) {
                    if (this.literalBuilder_ == null) {
                        result.literal_ = this.literal_;
                    } else {
                        result.literal_ = (MysqlxDatatypes.Scalar)this.literalBuilder_.build();
                    }
                    to_bitField0_ |= 8;
                }
                if ((from_bitField0_ & 0x10) != 0) {
                    if (this.functionCallBuilder_ == null) {
                        result.functionCall_ = this.functionCall_;
                    } else {
                        result.functionCall_ = (FunctionCall)this.functionCallBuilder_.build();
                    }
                    to_bitField0_ |= 0x10;
                }
                if ((from_bitField0_ & 0x20) != 0) {
                    if (this.operatorBuilder_ == null) {
                        result.operator_ = this.operator_;
                    } else {
                        result.operator_ = (Operator)this.operatorBuilder_.build();
                    }
                    to_bitField0_ |= 0x20;
                }
                if ((from_bitField0_ & 0x40) != 0) {
                    result.position_ = this.position_;
                    to_bitField0_ |= 0x40;
                }
                if ((from_bitField0_ & 0x80) != 0) {
                    if (this.objectBuilder_ == null) {
                        result.object_ = this.object_;
                    } else {
                        result.object_ = (Object)this.objectBuilder_.build();
                    }
                    to_bitField0_ |= 0x80;
                }
                if ((from_bitField0_ & 0x100) != 0) {
                    if (this.arrayBuilder_ == null) {
                        result.array_ = this.array_;
                    } else {
                        result.array_ = (Array)this.arrayBuilder_.build();
                    }
                    to_bitField0_ |= 0x100;
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
                if (other instanceof Expr) {
                    return this.mergeFrom((Expr)other);
                }
                super.mergeFrom(other);
                return this;
            }

            public Builder mergeFrom(Expr other) {
                if (other == Expr.getDefaultInstance()) {
                    return this;
                }
                if (other.hasType()) {
                    this.setType(other.getType());
                }
                if (other.hasIdentifier()) {
                    this.mergeIdentifier(other.getIdentifier());
                }
                if (other.hasVariable()) {
                    this.bitField0_ |= 4;
                    this.variable_ = other.variable_;
                    this.onChanged();
                }
                if (other.hasLiteral()) {
                    this.mergeLiteral(other.getLiteral());
                }
                if (other.hasFunctionCall()) {
                    this.mergeFunctionCall(other.getFunctionCall());
                }
                if (other.hasOperator()) {
                    this.mergeOperator(other.getOperator());
                }
                if (other.hasPosition()) {
                    this.setPosition(other.getPosition());
                }
                if (other.hasObject()) {
                    this.mergeObject(other.getObject());
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
                if (this.hasIdentifier() && !this.getIdentifier().isInitialized()) {
                    return false;
                }
                if (this.hasLiteral() && !this.getLiteral().isInitialized()) {
                    return false;
                }
                if (this.hasFunctionCall() && !this.getFunctionCall().isInitialized()) {
                    return false;
                }
                if (this.hasOperator() && !this.getOperator().isInitialized()) {
                    return false;
                }
                if (this.hasObject() && !this.getObject().isInitialized()) {
                    return false;
                }
                return !this.hasArray() || this.getArray().isInitialized();
            }

            public Builder mergeFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                Expr parsedMessage = null;
                try {
                    parsedMessage = (Expr)PARSER.parsePartialFrom(input, extensionRegistry);
                }
                catch (InvalidProtocolBufferException e) {
                    parsedMessage = (Expr)e.getUnfinishedMessage();
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
                return result == null ? Type.IDENT : result;
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
            public boolean hasIdentifier() {
                return (this.bitField0_ & 2) != 0;
            }

            @Override
            public ColumnIdentifier getIdentifier() {
                if (this.identifierBuilder_ == null) {
                    return this.identifier_ == null ? ColumnIdentifier.getDefaultInstance() : this.identifier_;
                }
                return (ColumnIdentifier)this.identifierBuilder_.getMessage();
            }

            public Builder setIdentifier(ColumnIdentifier value) {
                if (this.identifierBuilder_ == null) {
                    if (value == null) {
                        throw new NullPointerException();
                    }
                    this.identifier_ = value;
                    this.onChanged();
                } else {
                    this.identifierBuilder_.setMessage((AbstractMessage)value);
                }
                this.bitField0_ |= 2;
                return this;
            }

            public Builder setIdentifier(ColumnIdentifier.Builder builderForValue) {
                if (this.identifierBuilder_ == null) {
                    this.identifier_ = builderForValue.build();
                    this.onChanged();
                } else {
                    this.identifierBuilder_.setMessage((AbstractMessage)builderForValue.build());
                }
                this.bitField0_ |= 2;
                return this;
            }

            public Builder mergeIdentifier(ColumnIdentifier value) {
                if (this.identifierBuilder_ == null) {
                    this.identifier_ = (this.bitField0_ & 2) != 0 && this.identifier_ != null && this.identifier_ != ColumnIdentifier.getDefaultInstance() ? ColumnIdentifier.newBuilder(this.identifier_).mergeFrom(value).buildPartial() : value;
                    this.onChanged();
                } else {
                    this.identifierBuilder_.mergeFrom((AbstractMessage)value);
                }
                this.bitField0_ |= 2;
                return this;
            }

            public Builder clearIdentifier() {
                if (this.identifierBuilder_ == null) {
                    this.identifier_ = null;
                    this.onChanged();
                } else {
                    this.identifierBuilder_.clear();
                }
                this.bitField0_ &= 0xFFFFFFFD;
                return this;
            }

            public ColumnIdentifier.Builder getIdentifierBuilder() {
                this.bitField0_ |= 2;
                this.onChanged();
                return (ColumnIdentifier.Builder)this.getIdentifierFieldBuilder().getBuilder();
            }

            @Override
            public ColumnIdentifierOrBuilder getIdentifierOrBuilder() {
                if (this.identifierBuilder_ != null) {
                    return (ColumnIdentifierOrBuilder)this.identifierBuilder_.getMessageOrBuilder();
                }
                return this.identifier_ == null ? ColumnIdentifier.getDefaultInstance() : this.identifier_;
            }

            private SingleFieldBuilderV3<ColumnIdentifier, ColumnIdentifier.Builder, ColumnIdentifierOrBuilder> getIdentifierFieldBuilder() {
                if (this.identifierBuilder_ == null) {
                    this.identifierBuilder_ = new SingleFieldBuilderV3((AbstractMessage)this.getIdentifier(), (AbstractMessage.BuilderParent)this.getParentForChildren(), this.isClean());
                    this.identifier_ = null;
                }
                return this.identifierBuilder_;
            }

            @Override
            public boolean hasVariable() {
                return (this.bitField0_ & 4) != 0;
            }

            @Override
            public String getVariable() {
                java.lang.Object ref = this.variable_;
                if (!(ref instanceof String)) {
                    ByteString bs = (ByteString)ref;
                    String s = bs.toStringUtf8();
                    if (bs.isValidUtf8()) {
                        this.variable_ = s;
                    }
                    return s;
                }
                return (String)ref;
            }

            @Override
            public ByteString getVariableBytes() {
                java.lang.Object ref = this.variable_;
                if (ref instanceof String) {
                    ByteString b = ByteString.copyFromUtf8((String)((String)ref));
                    this.variable_ = b;
                    return b;
                }
                return (ByteString)ref;
            }

            public Builder setVariable(String value) {
                if (value == null) {
                    throw new NullPointerException();
                }
                this.bitField0_ |= 4;
                this.variable_ = value;
                this.onChanged();
                return this;
            }

            public Builder clearVariable() {
                this.bitField0_ &= 0xFFFFFFFB;
                this.variable_ = Expr.getDefaultInstance().getVariable();
                this.onChanged();
                return this;
            }

            public Builder setVariableBytes(ByteString value) {
                if (value == null) {
                    throw new NullPointerException();
                }
                this.bitField0_ |= 4;
                this.variable_ = value;
                this.onChanged();
                return this;
            }

            @Override
            public boolean hasLiteral() {
                return (this.bitField0_ & 8) != 0;
            }

            @Override
            public MysqlxDatatypes.Scalar getLiteral() {
                if (this.literalBuilder_ == null) {
                    return this.literal_ == null ? MysqlxDatatypes.Scalar.getDefaultInstance() : this.literal_;
                }
                return (MysqlxDatatypes.Scalar)this.literalBuilder_.getMessage();
            }

            public Builder setLiteral(MysqlxDatatypes.Scalar value) {
                if (this.literalBuilder_ == null) {
                    if (value == null) {
                        throw new NullPointerException();
                    }
                    this.literal_ = value;
                    this.onChanged();
                } else {
                    this.literalBuilder_.setMessage((AbstractMessage)value);
                }
                this.bitField0_ |= 8;
                return this;
            }

            public Builder setLiteral(MysqlxDatatypes.Scalar.Builder builderForValue) {
                if (this.literalBuilder_ == null) {
                    this.literal_ = builderForValue.build();
                    this.onChanged();
                } else {
                    this.literalBuilder_.setMessage((AbstractMessage)builderForValue.build());
                }
                this.bitField0_ |= 8;
                return this;
            }

            public Builder mergeLiteral(MysqlxDatatypes.Scalar value) {
                if (this.literalBuilder_ == null) {
                    this.literal_ = (this.bitField0_ & 8) != 0 && this.literal_ != null && this.literal_ != MysqlxDatatypes.Scalar.getDefaultInstance() ? MysqlxDatatypes.Scalar.newBuilder(this.literal_).mergeFrom(value).buildPartial() : value;
                    this.onChanged();
                } else {
                    this.literalBuilder_.mergeFrom((AbstractMessage)value);
                }
                this.bitField0_ |= 8;
                return this;
            }

            public Builder clearLiteral() {
                if (this.literalBuilder_ == null) {
                    this.literal_ = null;
                    this.onChanged();
                } else {
                    this.literalBuilder_.clear();
                }
                this.bitField0_ &= 0xFFFFFFF7;
                return this;
            }

            public MysqlxDatatypes.Scalar.Builder getLiteralBuilder() {
                this.bitField0_ |= 8;
                this.onChanged();
                return (MysqlxDatatypes.Scalar.Builder)this.getLiteralFieldBuilder().getBuilder();
            }

            @Override
            public MysqlxDatatypes.ScalarOrBuilder getLiteralOrBuilder() {
                if (this.literalBuilder_ != null) {
                    return (MysqlxDatatypes.ScalarOrBuilder)this.literalBuilder_.getMessageOrBuilder();
                }
                return this.literal_ == null ? MysqlxDatatypes.Scalar.getDefaultInstance() : this.literal_;
            }

            private SingleFieldBuilderV3<MysqlxDatatypes.Scalar, MysqlxDatatypes.Scalar.Builder, MysqlxDatatypes.ScalarOrBuilder> getLiteralFieldBuilder() {
                if (this.literalBuilder_ == null) {
                    this.literalBuilder_ = new SingleFieldBuilderV3((AbstractMessage)this.getLiteral(), (AbstractMessage.BuilderParent)this.getParentForChildren(), this.isClean());
                    this.literal_ = null;
                }
                return this.literalBuilder_;
            }

            @Override
            public boolean hasFunctionCall() {
                return (this.bitField0_ & 0x10) != 0;
            }

            @Override
            public FunctionCall getFunctionCall() {
                if (this.functionCallBuilder_ == null) {
                    return this.functionCall_ == null ? FunctionCall.getDefaultInstance() : this.functionCall_;
                }
                return (FunctionCall)this.functionCallBuilder_.getMessage();
            }

            public Builder setFunctionCall(FunctionCall value) {
                if (this.functionCallBuilder_ == null) {
                    if (value == null) {
                        throw new NullPointerException();
                    }
                    this.functionCall_ = value;
                    this.onChanged();
                } else {
                    this.functionCallBuilder_.setMessage((AbstractMessage)value);
                }
                this.bitField0_ |= 0x10;
                return this;
            }

            public Builder setFunctionCall(FunctionCall.Builder builderForValue) {
                if (this.functionCallBuilder_ == null) {
                    this.functionCall_ = builderForValue.build();
                    this.onChanged();
                } else {
                    this.functionCallBuilder_.setMessage((AbstractMessage)builderForValue.build());
                }
                this.bitField0_ |= 0x10;
                return this;
            }

            public Builder mergeFunctionCall(FunctionCall value) {
                if (this.functionCallBuilder_ == null) {
                    this.functionCall_ = (this.bitField0_ & 0x10) != 0 && this.functionCall_ != null && this.functionCall_ != FunctionCall.getDefaultInstance() ? FunctionCall.newBuilder(this.functionCall_).mergeFrom(value).buildPartial() : value;
                    this.onChanged();
                } else {
                    this.functionCallBuilder_.mergeFrom((AbstractMessage)value);
                }
                this.bitField0_ |= 0x10;
                return this;
            }

            public Builder clearFunctionCall() {
                if (this.functionCallBuilder_ == null) {
                    this.functionCall_ = null;
                    this.onChanged();
                } else {
                    this.functionCallBuilder_.clear();
                }
                this.bitField0_ &= 0xFFFFFFEF;
                return this;
            }

            public FunctionCall.Builder getFunctionCallBuilder() {
                this.bitField0_ |= 0x10;
                this.onChanged();
                return (FunctionCall.Builder)this.getFunctionCallFieldBuilder().getBuilder();
            }

            @Override
            public FunctionCallOrBuilder getFunctionCallOrBuilder() {
                if (this.functionCallBuilder_ != null) {
                    return (FunctionCallOrBuilder)this.functionCallBuilder_.getMessageOrBuilder();
                }
                return this.functionCall_ == null ? FunctionCall.getDefaultInstance() : this.functionCall_;
            }

            private SingleFieldBuilderV3<FunctionCall, FunctionCall.Builder, FunctionCallOrBuilder> getFunctionCallFieldBuilder() {
                if (this.functionCallBuilder_ == null) {
                    this.functionCallBuilder_ = new SingleFieldBuilderV3((AbstractMessage)this.getFunctionCall(), (AbstractMessage.BuilderParent)this.getParentForChildren(), this.isClean());
                    this.functionCall_ = null;
                }
                return this.functionCallBuilder_;
            }

            @Override
            public boolean hasOperator() {
                return (this.bitField0_ & 0x20) != 0;
            }

            @Override
            public Operator getOperator() {
                if (this.operatorBuilder_ == null) {
                    return this.operator_ == null ? Operator.getDefaultInstance() : this.operator_;
                }
                return (Operator)this.operatorBuilder_.getMessage();
            }

            public Builder setOperator(Operator value) {
                if (this.operatorBuilder_ == null) {
                    if (value == null) {
                        throw new NullPointerException();
                    }
                    this.operator_ = value;
                    this.onChanged();
                } else {
                    this.operatorBuilder_.setMessage((AbstractMessage)value);
                }
                this.bitField0_ |= 0x20;
                return this;
            }

            public Builder setOperator(Operator.Builder builderForValue) {
                if (this.operatorBuilder_ == null) {
                    this.operator_ = builderForValue.build();
                    this.onChanged();
                } else {
                    this.operatorBuilder_.setMessage((AbstractMessage)builderForValue.build());
                }
                this.bitField0_ |= 0x20;
                return this;
            }

            public Builder mergeOperator(Operator value) {
                if (this.operatorBuilder_ == null) {
                    this.operator_ = (this.bitField0_ & 0x20) != 0 && this.operator_ != null && this.operator_ != Operator.getDefaultInstance() ? Operator.newBuilder(this.operator_).mergeFrom(value).buildPartial() : value;
                    this.onChanged();
                } else {
                    this.operatorBuilder_.mergeFrom((AbstractMessage)value);
                }
                this.bitField0_ |= 0x20;
                return this;
            }

            public Builder clearOperator() {
                if (this.operatorBuilder_ == null) {
                    this.operator_ = null;
                    this.onChanged();
                } else {
                    this.operatorBuilder_.clear();
                }
                this.bitField0_ &= 0xFFFFFFDF;
                return this;
            }

            public Operator.Builder getOperatorBuilder() {
                this.bitField0_ |= 0x20;
                this.onChanged();
                return (Operator.Builder)this.getOperatorFieldBuilder().getBuilder();
            }

            @Override
            public OperatorOrBuilder getOperatorOrBuilder() {
                if (this.operatorBuilder_ != null) {
                    return (OperatorOrBuilder)this.operatorBuilder_.getMessageOrBuilder();
                }
                return this.operator_ == null ? Operator.getDefaultInstance() : this.operator_;
            }

            private SingleFieldBuilderV3<Operator, Operator.Builder, OperatorOrBuilder> getOperatorFieldBuilder() {
                if (this.operatorBuilder_ == null) {
                    this.operatorBuilder_ = new SingleFieldBuilderV3((AbstractMessage)this.getOperator(), (AbstractMessage.BuilderParent)this.getParentForChildren(), this.isClean());
                    this.operator_ = null;
                }
                return this.operatorBuilder_;
            }

            @Override
            public boolean hasPosition() {
                return (this.bitField0_ & 0x40) != 0;
            }

            @Override
            public int getPosition() {
                return this.position_;
            }

            public Builder setPosition(int value) {
                this.bitField0_ |= 0x40;
                this.position_ = value;
                this.onChanged();
                return this;
            }

            public Builder clearPosition() {
                this.bitField0_ &= 0xFFFFFFBF;
                this.position_ = 0;
                this.onChanged();
                return this;
            }

            @Override
            public boolean hasObject() {
                return (this.bitField0_ & 0x80) != 0;
            }

            @Override
            public Object getObject() {
                if (this.objectBuilder_ == null) {
                    return this.object_ == null ? Object.getDefaultInstance() : this.object_;
                }
                return (Object)this.objectBuilder_.getMessage();
            }

            public Builder setObject(Object value) {
                if (this.objectBuilder_ == null) {
                    if (value == null) {
                        throw new NullPointerException();
                    }
                    this.object_ = value;
                    this.onChanged();
                } else {
                    this.objectBuilder_.setMessage((AbstractMessage)value);
                }
                this.bitField0_ |= 0x80;
                return this;
            }

            public Builder setObject(Object.Builder builderForValue) {
                if (this.objectBuilder_ == null) {
                    this.object_ = builderForValue.build();
                    this.onChanged();
                } else {
                    this.objectBuilder_.setMessage((AbstractMessage)builderForValue.build());
                }
                this.bitField0_ |= 0x80;
                return this;
            }

            public Builder mergeObject(Object value) {
                if (this.objectBuilder_ == null) {
                    this.object_ = (this.bitField0_ & 0x80) != 0 && this.object_ != null && this.object_ != Object.getDefaultInstance() ? Object.newBuilder(this.object_).mergeFrom(value).buildPartial() : value;
                    this.onChanged();
                } else {
                    this.objectBuilder_.mergeFrom((AbstractMessage)value);
                }
                this.bitField0_ |= 0x80;
                return this;
            }

            public Builder clearObject() {
                if (this.objectBuilder_ == null) {
                    this.object_ = null;
                    this.onChanged();
                } else {
                    this.objectBuilder_.clear();
                }
                this.bitField0_ &= 0xFFFFFF7F;
                return this;
            }

            public Object.Builder getObjectBuilder() {
                this.bitField0_ |= 0x80;
                this.onChanged();
                return (Object.Builder)this.getObjectFieldBuilder().getBuilder();
            }

            @Override
            public ObjectOrBuilder getObjectOrBuilder() {
                if (this.objectBuilder_ != null) {
                    return (ObjectOrBuilder)this.objectBuilder_.getMessageOrBuilder();
                }
                return this.object_ == null ? Object.getDefaultInstance() : this.object_;
            }

            private SingleFieldBuilderV3<Object, Object.Builder, ObjectOrBuilder> getObjectFieldBuilder() {
                if (this.objectBuilder_ == null) {
                    this.objectBuilder_ = new SingleFieldBuilderV3((AbstractMessage)this.getObject(), (AbstractMessage.BuilderParent)this.getParentForChildren(), this.isClean());
                    this.object_ = null;
                }
                return this.objectBuilder_;
            }

            @Override
            public boolean hasArray() {
                return (this.bitField0_ & 0x100) != 0;
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
                this.bitField0_ |= 0x100;
                return this;
            }

            public Builder setArray(Array.Builder builderForValue) {
                if (this.arrayBuilder_ == null) {
                    this.array_ = builderForValue.build();
                    this.onChanged();
                } else {
                    this.arrayBuilder_.setMessage((AbstractMessage)builderForValue.build());
                }
                this.bitField0_ |= 0x100;
                return this;
            }

            public Builder mergeArray(Array value) {
                if (this.arrayBuilder_ == null) {
                    this.array_ = (this.bitField0_ & 0x100) != 0 && this.array_ != null && this.array_ != Array.getDefaultInstance() ? Array.newBuilder(this.array_).mergeFrom(value).buildPartial() : value;
                    this.onChanged();
                } else {
                    this.arrayBuilder_.mergeFrom((AbstractMessage)value);
                }
                this.bitField0_ |= 0x100;
                return this;
            }

            public Builder clearArray() {
                if (this.arrayBuilder_ == null) {
                    this.array_ = null;
                    this.onChanged();
                } else {
                    this.arrayBuilder_.clear();
                }
                this.bitField0_ &= 0xFFFFFEFF;
                return this;
            }

            public Array.Builder getArrayBuilder() {
                this.bitField0_ |= 0x100;
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
            IDENT(1),
            LITERAL(2),
            VARIABLE(3),
            FUNC_CALL(4),
            OPERATOR(5),
            PLACEHOLDER(6),
            OBJECT(7),
            ARRAY(8);

            public static final int IDENT_VALUE = 1;
            public static final int LITERAL_VALUE = 2;
            public static final int VARIABLE_VALUE = 3;
            public static final int FUNC_CALL_VALUE = 4;
            public static final int OPERATOR_VALUE = 5;
            public static final int PLACEHOLDER_VALUE = 6;
            public static final int OBJECT_VALUE = 7;
            public static final int ARRAY_VALUE = 8;
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
                        return IDENT;
                    }
                    case 2: {
                        return LITERAL;
                    }
                    case 3: {
                        return VARIABLE;
                    }
                    case 4: {
                        return FUNC_CALL;
                    }
                    case 5: {
                        return OPERATOR;
                    }
                    case 6: {
                        return PLACEHOLDER;
                    }
                    case 7: {
                        return OBJECT;
                    }
                    case 8: {
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
                return (Descriptors.EnumDescriptor)Expr.getDescriptor().getEnumTypes().get(0);
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

    public static interface ExprOrBuilder
    extends MessageOrBuilder {
        public boolean hasType();

        public Expr.Type getType();

        public boolean hasIdentifier();

        public ColumnIdentifier getIdentifier();

        public ColumnIdentifierOrBuilder getIdentifierOrBuilder();

        public boolean hasVariable();

        public String getVariable();

        public ByteString getVariableBytes();

        public boolean hasLiteral();

        public MysqlxDatatypes.Scalar getLiteral();

        public MysqlxDatatypes.ScalarOrBuilder getLiteralOrBuilder();

        public boolean hasFunctionCall();

        public FunctionCall getFunctionCall();

        public FunctionCallOrBuilder getFunctionCallOrBuilder();

        public boolean hasOperator();

        public Operator getOperator();

        public OperatorOrBuilder getOperatorOrBuilder();

        public boolean hasPosition();

        public int getPosition();

        public boolean hasObject();

        public Object getObject();

        public ObjectOrBuilder getObjectOrBuilder();

        public boolean hasArray();

        public Array getArray();

        public ArrayOrBuilder getArrayOrBuilder();
    }
}

