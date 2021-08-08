/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  com.google.protobuf.ByteString
 *  com.google.protobuf.GeneratedMessageV3
 *  com.google.protobuf.InvalidProtocolBufferException
 *  com.google.protobuf.Parser
 */
package com.mysql.cj.protocol.x;

import com.google.protobuf.ByteString;
import com.google.protobuf.GeneratedMessageV3;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Parser;
import com.mysql.cj.exceptions.CJCommunicationsException;
import com.mysql.cj.protocol.ProtocolEntity;
import com.mysql.cj.protocol.Warning;
import com.mysql.cj.protocol.x.MessageConstants;
import com.mysql.cj.protocol.x.XMessage;
import com.mysql.cj.x.protobuf.MysqlxDatatypes;
import com.mysql.cj.x.protobuf.MysqlxNotice;
import java.util.List;

public class Notice
implements ProtocolEntity {
    public static final int NoticeScope_Global = 1;
    public static final int NoticeScope_Local = 2;
    public static final int NoticeType_WARNING = 1;
    public static final int NoticeType_SESSION_VARIABLE_CHANGED = 2;
    public static final int NoticeType_SESSION_STATE_CHANGED = 3;
    public static final int NoticeType_GROUP_REPLICATION_STATE_CHANGED = 4;
    public static final int SessionStateChanged_CURRENT_SCHEMA = 1;
    public static final int SessionStateChanged_ACCOUNT_EXPIRED = 2;
    public static final int SessionStateChanged_GENERATED_INSERT_ID = 3;
    public static final int SessionStateChanged_ROWS_AFFECTED = 4;
    public static final int SessionStateChanged_ROWS_FOUND = 5;
    public static final int SessionStateChanged_ROWS_MATCHED = 6;
    public static final int SessionStateChanged_TRX_COMMITTED = 7;
    public static final int SessionStateChanged_TRX_ROLLEDBACK = 9;
    public static final int SessionStateChanged_PRODUCED_MESSAGE = 10;
    public static final int SessionStateChanged_CLIENT_ID_ASSIGNED = 11;
    public static final int SessionStateChanged_GENERATED_DOCUMENT_IDS = 12;
    protected int scope = 0;
    protected int type = 0;

    public static Notice getInstance(XMessage message) {
        MysqlxNotice.Frame notice = (MysqlxNotice.Frame)message.getMessage();
        if (notice.getScope() != MysqlxNotice.Frame.Scope.GLOBAL) {
            switch (notice.getType()) {
                case 1: {
                    return new XWarning(notice);
                }
                case 2: {
                    return new XSessionVariableChanged(notice);
                }
                case 3: {
                    return new XSessionStateChanged(notice);
                }
                case 4: {
                    break;
                }
            }
        }
        return new Notice(notice);
    }

    public Notice(MysqlxNotice.Frame frm) {
        this.scope = frm.getScope().getNumber();
        this.type = frm.getType();
    }

    public int getType() {
        return this.type;
    }

    public int getScope() {
        return this.scope;
    }

    static <T extends GeneratedMessageV3> T parseNotice(ByteString payload, Class<T> noticeClass) {
        try {
            Parser<? extends GeneratedMessageV3> parser = MessageConstants.MESSAGE_CLASS_TO_PARSER.get(noticeClass);
            return (T)((GeneratedMessageV3)parser.parseFrom(payload));
        }
        catch (InvalidProtocolBufferException ex) {
            throw new CJCommunicationsException(ex);
        }
    }

    public static class XSessionStateChanged
    extends Notice {
        private Integer paramType = null;
        private List<MysqlxDatatypes.Scalar> valueList = null;

        public XSessionStateChanged(MysqlxNotice.Frame frm) {
            super(frm);
            MysqlxNotice.SessionStateChanged ssmsg = XSessionStateChanged.parseNotice(frm.getPayload(), MysqlxNotice.SessionStateChanged.class);
            this.paramType = ssmsg.getParam().getNumber();
            this.valueList = ssmsg.getValueList();
        }

        public Integer getParamType() {
            return this.paramType;
        }

        public List<MysqlxDatatypes.Scalar> getValueList() {
            return this.valueList;
        }

        public MysqlxDatatypes.Scalar getValue() {
            if (this.valueList != null && !this.valueList.isEmpty()) {
                return this.valueList.get(0);
            }
            return null;
        }
    }

    public static class XSessionVariableChanged
    extends Notice {
        private String paramName = null;
        private MysqlxDatatypes.Scalar value = null;

        public XSessionVariableChanged(MysqlxNotice.Frame frm) {
            super(frm);
            MysqlxNotice.SessionVariableChanged svmsg = XSessionVariableChanged.parseNotice(frm.getPayload(), MysqlxNotice.SessionVariableChanged.class);
            this.paramName = svmsg.getParam();
            this.value = svmsg.getValue();
        }

        public String getParamName() {
            return this.paramName;
        }

        public MysqlxDatatypes.Scalar getValue() {
            return this.value;
        }
    }

    public static class XWarning
    extends Notice
    implements Warning {
        private int level;
        private long code;
        private String message;

        public XWarning(MysqlxNotice.Frame frm) {
            super(frm);
            MysqlxNotice.Warning warn = XWarning.parseNotice(frm.getPayload(), MysqlxNotice.Warning.class);
            this.level = warn.getLevel().getNumber();
            this.code = Integer.toUnsignedLong(warn.getCode());
            this.message = warn.getMsg();
        }

        @Override
        public int getLevel() {
            return this.level;
        }

        @Override
        public long getCode() {
            return this.code;
        }

        @Override
        public String getMessage() {
            return this.message;
        }
    }
}

