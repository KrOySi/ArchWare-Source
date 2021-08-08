/*
 * Decompiled with CFR 0.150.
 */
package com.mysql.cj.jdbc;

import com.mysql.cj.Messages;
import com.mysql.cj.MysqlType;
import com.mysql.cj.NativeSession;
import com.mysql.cj.conf.PropertyDefinitions;
import com.mysql.cj.conf.PropertyKey;
import com.mysql.cj.conf.RuntimeProperty;
import com.mysql.cj.exceptions.AssertionFailedException;
import com.mysql.cj.exceptions.CJException;
import com.mysql.cj.exceptions.ExceptionInterceptor;
import com.mysql.cj.jdbc.DatabaseMetaDataUsingInfoSchema;
import com.mysql.cj.jdbc.IterateBlock;
import com.mysql.cj.jdbc.JdbcConnection;
import com.mysql.cj.jdbc.JdbcStatement;
import com.mysql.cj.jdbc.NonRegisteringDriver;
import com.mysql.cj.jdbc.exceptions.SQLError;
import com.mysql.cj.jdbc.exceptions.SQLExceptionsMapping;
import com.mysql.cj.jdbc.result.ResultSetFactory;
import com.mysql.cj.jdbc.result.ResultSetImpl;
import com.mysql.cj.protocol.ColumnDefinition;
import com.mysql.cj.protocol.a.result.ByteArrayRow;
import com.mysql.cj.protocol.a.result.ResultsetRowsStatic;
import com.mysql.cj.result.DefaultColumnDefinition;
import com.mysql.cj.result.Field;
import com.mysql.cj.result.Row;
import com.mysql.cj.util.StringUtils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.RowIdLifetime;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.StringTokenizer;
import java.util.TreeMap;
import java.util.TreeSet;

public class DatabaseMetaData
implements java.sql.DatabaseMetaData {
    protected static int maxBufferSize = 65535;
    protected static final int MAX_IDENTIFIER_LENGTH = 64;
    private static final String SUPPORTS_FK = "SUPPORTS_FK";
    protected static final byte[] TABLE_AS_BYTES = "TABLE".getBytes();
    protected static final byte[] SYSTEM_TABLE_AS_BYTES = "SYSTEM TABLE".getBytes();
    protected static final byte[] VIEW_AS_BYTES = "VIEW".getBytes();
    private static final String[] MYSQL_KEYWORDS = new String[]{"ACCESSIBLE", "ADD", "ALL", "ALTER", "ANALYZE", "AND", "AS", "ASC", "ASENSITIVE", "BEFORE", "BETWEEN", "BIGINT", "BINARY", "BLOB", "BOTH", "BY", "CALL", "CASCADE", "CASE", "CHANGE", "CHAR", "CHARACTER", "CHECK", "COLLATE", "COLUMN", "CONDITION", "CONSTRAINT", "CONTINUE", "CONVERT", "CREATE", "CROSS", "CUBE", "CUME_DIST", "CURRENT_DATE", "CURRENT_TIME", "CURRENT_TIMESTAMP", "CURRENT_USER", "CURSOR", "DATABASE", "DATABASES", "DAY_HOUR", "DAY_MICROSECOND", "DAY_MINUTE", "DAY_SECOND", "DEC", "DECIMAL", "DECLARE", "DEFAULT", "DELAYED", "DELETE", "DENSE_RANK", "DESC", "DESCRIBE", "DETERMINISTIC", "DISTINCT", "DISTINCTROW", "DIV", "DOUBLE", "DROP", "DUAL", "EACH", "ELSE", "ELSEIF", "EMPTY", "ENCLOSED", "ESCAPED", "EXCEPT", "EXISTS", "EXIT", "EXPLAIN", "FALSE", "FETCH", "FIRST_VALUE", "FLOAT", "FLOAT4", "FLOAT8", "FOR", "FORCE", "FOREIGN", "FROM", "FULLTEXT", "FUNCTION", "GENERATED", "GET", "GRANT", "GROUP", "GROUPING", "GROUPS", "HAVING", "HIGH_PRIORITY", "HOUR_MICROSECOND", "HOUR_MINUTE", "HOUR_SECOND", "IF", "IGNORE", "IN", "INDEX", "INFILE", "INNER", "INOUT", "INSENSITIVE", "INSERT", "INT", "INT1", "INT2", "INT3", "INT4", "INT8", "INTEGER", "INTERVAL", "INTO", "IO_AFTER_GTIDS", "IO_BEFORE_GTIDS", "IS", "ITERATE", "JOIN", "JSON_TABLE", "KEY", "KEYS", "KILL", "LAG", "LAST_VALUE", "LEAD", "LEADING", "LEAVE", "LEFT", "LIKE", "LIMIT", "LINEAR", "LINES", "LOAD", "LOCALTIME", "LOCALTIMESTAMP", "LOCK", "LONG", "LONGBLOB", "LONGTEXT", "LOOP", "LOW_PRIORITY", "MASTER_BIND", "MASTER_SSL_VERIFY_SERVER_CERT", "MATCH", "MAXVALUE", "MEDIUMBLOB", "MEDIUMINT", "MEDIUMTEXT", "MIDDLEINT", "MINUTE_MICROSECOND", "MINUTE_SECOND", "MOD", "MODIFIES", "NATURAL", "NOT", "NO_WRITE_TO_BINLOG", "NTH_VALUE", "NTILE", "NULL", "NUMERIC", "OF", "ON", "OPTIMIZE", "OPTIMIZER_COSTS", "OPTION", "OPTIONALLY", "OR", "ORDER", "OUT", "OUTER", "OUTFILE", "OVER", "PARTITION", "PERCENT_RANK", "PERSIST", "PERSIST_ONLY", "PRECISION", "PRIMARY", "PROCEDURE", "PURGE", "RANGE", "RANK", "READ", "READS", "READ_WRITE", "REAL", "RECURSIVE", "REFERENCES", "REGEXP", "RELEASE", "RENAME", "REPEAT", "REPLACE", "REQUIRE", "RESIGNAL", "RESTRICT", "RETURN", "REVOKE", "RIGHT", "RLIKE", "ROW", "ROWS", "ROW_NUMBER", "SCHEMA", "SCHEMAS", "SECOND_MICROSECOND", "SELECT", "SENSITIVE", "SEPARATOR", "SET", "SHOW", "SIGNAL", "SMALLINT", "SPATIAL", "SPECIFIC", "SQL", "SQLEXCEPTION", "SQLSTATE", "SQLWARNING", "SQL_BIG_RESULT", "SQL_CALC_FOUND_ROWS", "SQL_SMALL_RESULT", "SSL", "STARTING", "STORED", "STRAIGHT_JOIN", "SYSTEM", "TABLE", "TERMINATED", "THEN", "TINYBLOB", "TINYINT", "TINYTEXT", "TO", "TRAILING", "TRIGGER", "TRUE", "UNDO", "UNION", "UNIQUE", "UNLOCK", "UNSIGNED", "UPDATE", "USAGE", "USE", "USING", "UTC_DATE", "UTC_TIME", "UTC_TIMESTAMP", "VALUES", "VARBINARY", "VARCHAR", "VARCHARACTER", "VARYING", "VIRTUAL", "WHEN", "WHERE", "WHILE", "WINDOW", "WITH", "WRITE", "XOR", "YEAR_MONTH", "ZEROFILL"};
    static final List<String> SQL2003_KEYWORDS = Arrays.asList("ABS", "ALL", "ALLOCATE", "ALTER", "AND", "ANY", "ARE", "ARRAY", "AS", "ASENSITIVE", "ASYMMETRIC", "AT", "ATOMIC", "AUTHORIZATION", "AVG", "BEGIN", "BETWEEN", "BIGINT", "BINARY", "BLOB", "BOOLEAN", "BOTH", "BY", "CALL", "CALLED", "CARDINALITY", "CASCADED", "CASE", "CAST", "CEIL", "CEILING", "CHAR", "CHARACTER", "CHARACTER_LENGTH", "CHAR_LENGTH", "CHECK", "CLOB", "CLOSE", "COALESCE", "COLLATE", "COLLECT", "COLUMN", "COMMIT", "CONDITION", "CONNECT", "CONSTRAINT", "CONVERT", "CORR", "CORRESPONDING", "COUNT", "COVAR_POP", "COVAR_SAMP", "CREATE", "CROSS", "CUBE", "CUME_DIST", "CURRENT", "CURRENT_DATE", "CURRENT_DEFAULT_TRANSFORM_GROUP", "CURRENT_PATH", "CURRENT_ROLE", "CURRENT_TIME", "CURRENT_TIMESTAMP", "CURRENT_TRANSFORM_GROUP_FOR_TYPE", "CURRENT_USER", "CURSOR", "CYCLE", "DATE", "DAY", "DEALLOCATE", "DEC", "DECIMAL", "DECLARE", "DEFAULT", "DELETE", "DENSE_RANK", "DEREF", "DESCRIBE", "DETERMINISTIC", "DISCONNECT", "DISTINCT", "DOUBLE", "DROP", "DYNAMIC", "EACH", "ELEMENT", "ELSE", "END", "END-EXEC", "ESCAPE", "EVERY", "EXCEPT", "EXEC", "EXECUTE", "EXISTS", "EXP", "EXTERNAL", "EXTRACT", "FALSE", "FETCH", "FILTER", "FLOAT", "FLOOR", "FOR", "FOREIGN", "FREE", "FROM", "FULL", "FUNCTION", "FUSION", "GET", "GLOBAL", "GRANT", "GROUP", "GROUPING", "HAVING", "HOLD", "HOUR", "IDENTITY", "IN", "INDICATOR", "INNER", "INOUT", "INSENSITIVE", "INSERT", "INT", "INTEGER", "INTERSECT", "INTERSECTION", "INTERVAL", "INTO", "IS", "JOIN", "LANGUAGE", "LARGE", "LATERAL", "LEADING", "LEFT", "LIKE", "LN", "LOCAL", "LOCALTIME", "LOCALTIMESTAMP", "LOWER", "MATCH", "MAX", "MEMBER", "MERGE", "METHOD", "MIN", "MINUTE", "MOD", "MODIFIES", "MODULE", "MONTH", "MULTISET", "NATIONAL", "NATURAL", "NCHAR", "NCLOB", "NEW", "NO", "NONE", "NORMALIZE", "NOT", "NULL", "NULLIF", "NUMERIC", "OCTET_LENGTH", "OF", "OLD", "ON", "ONLY", "OPEN", "OR", "ORDER", "OUT", "OUTER", "OVER", "OVERLAPS", "OVERLAY", "PARAMETER", "PARTITION", "PERCENTILE_CONT", "PERCENTILE_DISC", "PERCENT_RANK", "POSITION", "POWER", "PRECISION", "PREPARE", "PRIMARY", "PROCEDURE", "RANGE", "RANK", "READS", "REAL", "RECURSIVE", "REF", "REFERENCES", "REFERENCING", "REGR_AVGX", "REGR_AVGY", "REGR_COUNT", "REGR_INTERCEPT", "REGR_R2", "REGR_SLOPE", "REGR_SXX", "REGR_SXY", "REGR_SYY", "RELEASE", "RESULT", "RETURN", "RETURNS", "REVOKE", "RIGHT", "ROLLBACK", "ROLLUP", "ROW", "ROWS", "ROW_NUMBER", "SAVEPOINT", "SCOPE", "SCROLL", "SEARCH", "SECOND", "SELECT", "SENSITIVE", "SESSION_USER", "SET", "SIMILAR", "SMALLINT", "SOME", "SPECIFIC", "SPECIFICTYPE", "SQL", "SQLEXCEPTION", "SQLSTATE", "SQLWARNING", "SQRT", "START", "STATIC", "STDDEV_POP", "STDDEV_SAMP", "SUBMULTISET", "SUBSTRING", "SUM", "SYMMETRIC", "SYSTEM", "SYSTEM_USER", "TABLE", "TABLESAMPLE", "THEN", "TIME", "TIMESTAMP", "TIMEZONE_HOUR", "TIMEZONE_MINUTE", "TO", "TRAILING", "TRANSLATE", "TRANSLATION", "TREAT", "TRIGGER", "TRIM", "TRUE", "UESCAPE", "UNION", "UNIQUE", "UNKNOWN", "UNNEST", "UPDATE", "UPPER", "USER", "USING", "VALUE", "VALUES", "VARCHAR", "VARYING", "VAR_POP", "VAR_SAMP", "WHEN", "WHENEVER", "WHERE", "WIDTH_BUCKET", "WINDOW", "WITH", "WITHIN", "WITHOUT", "YEAR");
    private static volatile String mysqlKeywords = null;
    protected JdbcConnection conn;
    protected NativeSession session;
    protected String database = null;
    protected final String quotedId;
    protected boolean pedantic;
    protected boolean tinyInt1isBit;
    protected boolean transformedBitIsBoolean;
    protected boolean useHostsInPrivileges;
    protected RuntimeProperty<PropertyDefinitions.DatabaseTerm> databaseTerm;
    protected RuntimeProperty<Boolean> nullDatabaseMeansCurrent;
    protected ResultSetFactory resultSetFactory;
    private String metadataEncoding;
    private int metadataCollationIndex;
    private ExceptionInterceptor exceptionInterceptor;

    protected static DatabaseMetaData getInstance(JdbcConnection connToSet, String databaseToSet, boolean checkForInfoSchema, ResultSetFactory resultSetFactory) throws SQLException {
        if (checkForInfoSchema && connToSet.getPropertySet().getBooleanProperty(PropertyKey.useInformationSchema).getValue().booleanValue()) {
            return new DatabaseMetaDataUsingInfoSchema(connToSet, databaseToSet, resultSetFactory);
        }
        return new DatabaseMetaData(connToSet, databaseToSet, resultSetFactory);
    }

    protected DatabaseMetaData(JdbcConnection connToSet, String databaseToSet, ResultSetFactory resultSetFactory) {
        this.conn = connToSet;
        this.session = (NativeSession)connToSet.getSession();
        this.database = databaseToSet;
        this.resultSetFactory = resultSetFactory;
        this.exceptionInterceptor = this.conn.getExceptionInterceptor();
        this.databaseTerm = this.conn.getPropertySet().getEnumProperty(PropertyKey.databaseTerm);
        this.nullDatabaseMeansCurrent = this.conn.getPropertySet().getBooleanProperty(PropertyKey.nullDatabaseMeansCurrent);
        this.pedantic = this.conn.getPropertySet().getBooleanProperty(PropertyKey.pedantic).getValue();
        this.tinyInt1isBit = this.conn.getPropertySet().getBooleanProperty(PropertyKey.tinyInt1isBit).getValue();
        this.transformedBitIsBoolean = this.conn.getPropertySet().getBooleanProperty(PropertyKey.transformedBitIsBoolean).getValue();
        this.useHostsInPrivileges = this.conn.getPropertySet().getBooleanProperty(PropertyKey.useHostsInPrivileges).getValue();
        this.quotedId = this.session.getIdentifierQuoteString();
    }

    @Override
    public boolean allProceduresAreCallable() throws SQLException {
        try {
            return false;
        }
        catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    @Override
    public boolean allTablesAreSelectable() throws SQLException {
        try {
            return false;
        }
        catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    protected void convertToJdbcFunctionList(ResultSet proceduresRs, List<ComparableWrapper<String, Row>> procedureRows, Field[] fields) throws SQLException {
        while (proceduresRs.next()) {
            String procDb = proceduresRs.getString("db");
            String functionName = proceduresRs.getString("name");
            Object rowData = null;
            if (fields != null && fields.length == 9) {
                rowData = new byte[9][];
                rowData[0] = this.databaseTerm.getValue() == PropertyDefinitions.DatabaseTerm.SCHEMA ? this.s2b("def") : this.s2b(procDb);
                rowData[1] = this.databaseTerm.getValue() == PropertyDefinitions.DatabaseTerm.SCHEMA ? this.s2b(procDb) : null;
                rowData[2] = this.s2b(functionName);
                rowData[3] = null;
                rowData[4] = null;
                rowData[5] = null;
                rowData[6] = this.s2b(proceduresRs.getString("comment"));
                rowData[7] = this.s2b(Integer.toString(2));
                rowData[8] = this.s2b(functionName);
            } else {
                rowData = new byte[6][];
                rowData[0] = this.databaseTerm.getValue() == PropertyDefinitions.DatabaseTerm.SCHEMA ? this.s2b("def") : this.s2b(procDb);
                rowData[1] = this.databaseTerm.getValue() == PropertyDefinitions.DatabaseTerm.SCHEMA ? this.s2b(procDb) : null;
                rowData[2] = this.s2b(functionName);
                rowData[3] = this.s2b(proceduresRs.getString("comment"));
                rowData[4] = this.s2b(Integer.toString(1));
                rowData[5] = this.s2b(functionName);
            }
            procedureRows.add(new ComparableWrapper<String, ByteArrayRow>(StringUtils.getFullyQualifiedName(procDb, functionName, this.quotedId, this.pedantic), new ByteArrayRow((byte[][])rowData, this.getExceptionInterceptor())));
        }
    }

    protected void convertToJdbcProcedureList(boolean fromSelect, ResultSet proceduresRs, List<ComparableWrapper<String, Row>> procedureRows) throws SQLException {
        while (proceduresRs.next()) {
            String procDb = proceduresRs.getString("db");
            String procedureName = proceduresRs.getString("name");
            byte[][] rowData = new byte[9][];
            rowData[0] = this.databaseTerm.getValue() == PropertyDefinitions.DatabaseTerm.SCHEMA ? this.s2b("def") : this.s2b(procDb);
            rowData[1] = this.databaseTerm.getValue() == PropertyDefinitions.DatabaseTerm.SCHEMA ? this.s2b(procDb) : null;
            rowData[2] = this.s2b(procedureName);
            rowData[3] = null;
            rowData[4] = null;
            rowData[5] = null;
            rowData[6] = this.s2b(proceduresRs.getString("comment"));
            boolean isFunction = fromSelect ? "FUNCTION".equalsIgnoreCase(proceduresRs.getString("type")) : false;
            rowData[7] = this.s2b(isFunction ? Integer.toString(2) : Integer.toString(1));
            rowData[8] = this.s2b(procedureName);
            procedureRows.add(new ComparableWrapper<String, ByteArrayRow>(StringUtils.getFullyQualifiedName(procDb, procedureName, this.quotedId, this.pedantic), new ByteArrayRow(rowData, this.getExceptionInterceptor())));
        }
    }

    private Row convertTypeDescriptorToProcedureRow(byte[] procNameAsBytes, byte[] procCatAsBytes, String paramName, boolean isOutParam, boolean isInParam, boolean isReturnParam, TypeDescriptor typeDesc, boolean forGetFunctionColumns, int ordinal) throws SQLException {
        byte[][] row = forGetFunctionColumns ? new byte[17][] : new byte[20][];
        row[0] = this.databaseTerm.getValue() == PropertyDefinitions.DatabaseTerm.SCHEMA ? this.s2b("def") : procCatAsBytes;
        row[1] = (byte[])(this.databaseTerm.getValue() == PropertyDefinitions.DatabaseTerm.SCHEMA ? procCatAsBytes : null);
        row[2] = procNameAsBytes;
        row[3] = this.s2b(paramName);
        row[4] = this.s2b(String.valueOf(this.getColumnType(isOutParam, isInParam, isReturnParam, forGetFunctionColumns)));
        row[5] = this.s2b(Short.toString((short)typeDesc.mysqlType.getJdbcType()));
        row[6] = this.s2b(typeDesc.mysqlType.getName());
        row[7] = typeDesc.datetimePrecision == null ? this.s2b(typeDesc.columnSize.toString()) : this.s2b(typeDesc.datetimePrecision.toString());
        row[8] = typeDesc.columnSize == null ? null : this.s2b(typeDesc.columnSize.toString());
        row[9] = typeDesc.decimalDigits == null ? null : this.s2b(typeDesc.decimalDigits.toString());
        row[10] = this.s2b(Integer.toString(typeDesc.numPrecRadix));
        switch (typeDesc.nullability) {
            case 0: {
                row[11] = this.s2b(String.valueOf(0));
                break;
            }
            case 1: {
                row[11] = this.s2b(String.valueOf(1));
                break;
            }
            case 2: {
                row[11] = this.s2b(String.valueOf(2));
                break;
            }
            default: {
                throw SQLError.createSQLException(Messages.getString("DatabaseMetaData.1"), "S1000", this.getExceptionInterceptor());
            }
        }
        row[12] = null;
        if (forGetFunctionColumns) {
            row[13] = typeDesc.charOctetLength == null ? null : this.s2b(typeDesc.charOctetLength.toString());
            row[14] = this.s2b(String.valueOf(ordinal));
            row[15] = this.s2b(typeDesc.isNullable);
            row[16] = procNameAsBytes;
        } else {
            row[13] = null;
            row[14] = null;
            row[15] = null;
            row[16] = typeDesc.charOctetLength == null ? null : this.s2b(typeDesc.charOctetLength.toString());
            row[17] = this.s2b(String.valueOf(ordinal));
            row[18] = this.s2b(typeDesc.isNullable);
            row[19] = procNameAsBytes;
        }
        return new ByteArrayRow(row, this.getExceptionInterceptor());
    }

    protected int getColumnType(boolean isOutParam, boolean isInParam, boolean isReturnParam, boolean forGetFunctionColumns) {
        return DatabaseMetaData.getProcedureOrFunctionColumnType(isOutParam, isInParam, isReturnParam, forGetFunctionColumns);
    }

    protected static int getProcedureOrFunctionColumnType(boolean isOutParam, boolean isInParam, boolean isReturnParam, boolean forGetFunctionColumns) {
        if (isInParam && isOutParam) {
            return forGetFunctionColumns ? 2 : 2;
        }
        if (isInParam) {
            return forGetFunctionColumns ? 1 : 1;
        }
        if (isOutParam) {
            return forGetFunctionColumns ? 3 : 4;
        }
        if (isReturnParam) {
            return forGetFunctionColumns ? 4 : 5;
        }
        return forGetFunctionColumns ? 0 : 0;
    }

    protected ExceptionInterceptor getExceptionInterceptor() {
        return this.exceptionInterceptor;
    }

    @Override
    public boolean dataDefinitionCausesTransactionCommit() throws SQLException {
        try {
            return true;
        }
        catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    @Override
    public boolean dataDefinitionIgnoredInTransactions() throws SQLException {
        try {
            return false;
        }
        catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    @Override
    public boolean deletesAreDetected(int type) throws SQLException {
        try {
            return false;
        }
        catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    @Override
    public boolean doesMaxRowSizeIncludeBlobs() throws SQLException {
        try {
            return true;
        }
        catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    public List<Row> extractForeignKeyForTable(ArrayList<Row> rows, ResultSet rs, String dbName) throws SQLException {
        byte[][] row = new byte[3][];
        row[0] = rs.getBytes(1);
        row[1] = this.s2b(SUPPORTS_FK);
        String createTableString = rs.getString(2);
        StringTokenizer lineTokenizer = new StringTokenizer(createTableString, "\n");
        StringBuilder commentBuf = new StringBuilder("comment; ");
        boolean firstTime = true;
        while (lineTokenizer.hasMoreTokens()) {
            int afterFk;
            int indexOfRef;
            String line = lineTokenizer.nextToken().trim();
            String constraintName = null;
            if (StringUtils.startsWithIgnoreCase(line, "CONSTRAINT")) {
                boolean usingBackTicks = true;
                int beginPos = StringUtils.indexOfQuoteDoubleAware(line, this.quotedId, 0);
                if (beginPos == -1) {
                    beginPos = line.indexOf("\"");
                    usingBackTicks = false;
                }
                if (beginPos != -1) {
                    int endPos = -1;
                    endPos = usingBackTicks ? StringUtils.indexOfQuoteDoubleAware(line, this.quotedId, beginPos + 1) : StringUtils.indexOfQuoteDoubleAware(line, "\"", beginPos + 1);
                    if (endPos != -1) {
                        constraintName = line.substring(beginPos + 1, endPos);
                        line = line.substring(endPos + 1, line.length()).trim();
                    }
                }
            }
            if (!line.startsWith("FOREIGN KEY")) continue;
            if (line.endsWith(",")) {
                line = line.substring(0, line.length() - 1);
            }
            int indexOfFK = line.indexOf("FOREIGN KEY");
            String localColumnName = null;
            String referencedDbName = StringUtils.quoteIdentifier(dbName, this.quotedId, this.pedantic);
            String referencedTableName = null;
            String referencedColumnName = null;
            if (indexOfFK != -1 && (indexOfRef = StringUtils.indexOfIgnoreCase(afterFk = indexOfFK + "FOREIGN KEY".length(), line, "REFERENCES", this.quotedId, this.quotedId, StringUtils.SEARCH_MODE__ALL)) != -1) {
                int indexOfParenOpen = line.indexOf(40, afterFk);
                int indexOfParenClose = StringUtils.indexOfIgnoreCase(indexOfParenOpen, line, ")", this.quotedId, this.quotedId, StringUtils.SEARCH_MODE__ALL);
                if (indexOfParenOpen == -1 || indexOfParenClose == -1) {
                    // empty if block
                }
                localColumnName = line.substring(indexOfParenOpen + 1, indexOfParenClose);
                int afterRef = indexOfRef + "REFERENCES".length();
                int referencedColumnBegin = StringUtils.indexOfIgnoreCase(afterRef, line, "(", this.quotedId, this.quotedId, StringUtils.SEARCH_MODE__ALL);
                if (referencedColumnBegin != -1) {
                    int indexOfDbSep;
                    referencedTableName = line.substring(afterRef, referencedColumnBegin);
                    int referencedColumnEnd = StringUtils.indexOfIgnoreCase(referencedColumnBegin + 1, line, ")", this.quotedId, this.quotedId, StringUtils.SEARCH_MODE__ALL);
                    if (referencedColumnEnd != -1) {
                        referencedColumnName = line.substring(referencedColumnBegin + 1, referencedColumnEnd);
                    }
                    if ((indexOfDbSep = StringUtils.indexOfIgnoreCase(0, referencedTableName, ".", this.quotedId, this.quotedId, StringUtils.SEARCH_MODE__ALL)) != -1) {
                        referencedDbName = referencedTableName.substring(0, indexOfDbSep);
                        referencedTableName = referencedTableName.substring(indexOfDbSep + 1);
                    }
                }
            }
            if (!firstTime) {
                commentBuf.append("; ");
            } else {
                firstTime = false;
            }
            if (constraintName != null) {
                commentBuf.append(constraintName);
            } else {
                commentBuf.append("not_available");
            }
            commentBuf.append("(");
            commentBuf.append(localColumnName);
            commentBuf.append(") REFER ");
            commentBuf.append(referencedDbName);
            commentBuf.append("/");
            commentBuf.append(referencedTableName);
            commentBuf.append("(");
            commentBuf.append(referencedColumnName);
            commentBuf.append(")");
            int lastParenIndex = line.lastIndexOf(")");
            if (lastParenIndex == line.length() - 1) continue;
            String cascadeOptions = line.substring(lastParenIndex + 1);
            commentBuf.append(" ");
            commentBuf.append(cascadeOptions);
        }
        row[2] = this.s2b(commentBuf.toString());
        rows.add(new ByteArrayRow(row, this.getExceptionInterceptor()));
        return rows;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public ResultSet extractForeignKeyFromCreateTable(String dbName, String tableName) throws SQLException {
        ArrayList<String> tableList = new ArrayList<String>();
        ResultSet rs = null;
        Statement stmt = null;
        if (tableName != null) {
            tableList.add(tableName);
        } else {
            try {
                ResultSet resultSet = this.databaseTerm.getValue() == PropertyDefinitions.DatabaseTerm.SCHEMA ? this.getTables(null, dbName, null, new String[]{"TABLE"}) : (rs = this.getTables(dbName, null, null, new String[]{"TABLE"}));
                while (rs.next()) {
                    tableList.add(rs.getString("TABLE_NAME"));
                }
            }
            finally {
                if (rs != null) {
                    rs.close();
                }
                rs = null;
            }
        }
        ArrayList<Row> rows = new ArrayList<Row>();
        Field[] fields = new Field[]{new Field("", "Name", this.metadataCollationIndex, this.metadataEncoding, MysqlType.CHAR, Integer.MAX_VALUE), new Field("", "Type", this.metadataCollationIndex, this.metadataEncoding, MysqlType.CHAR, 255), new Field("", "Comment", this.metadataCollationIndex, this.metadataEncoding, MysqlType.CHAR, Integer.MAX_VALUE)};
        int numTables = tableList.size();
        stmt = this.conn.getMetadataSafeStatement();
        try {
            for (int i = 0; i < numTables; ++i) {
                String tableToExtract = (String)tableList.get(i);
                String query = "SHOW CREATE TABLE " + StringUtils.getFullyQualifiedName(dbName, tableToExtract, this.quotedId, this.pedantic);
                try {
                    rs = stmt.executeQuery(query);
                }
                catch (SQLException sqlEx) {
                    String sqlState = sqlEx.getSQLState();
                    if ("42S02".equals(sqlState) || sqlEx.getErrorCode() == 1146 || sqlEx.getErrorCode() == 1049) continue;
                    throw sqlEx;
                }
                while (rs != null && rs.next()) {
                    this.extractForeignKeyForTable(rows, rs, dbName);
                }
            }
        }
        finally {
            if (rs != null) {
                rs.close();
            }
            rs = null;
            if (stmt != null) {
                stmt.close();
            }
            stmt = null;
        }
        return this.resultSetFactory.createFromResultsetRows(1007, 1004, new ResultsetRowsStatic(rows, new DefaultColumnDefinition(fields)));
    }

    @Override
    public ResultSet getAttributes(String arg0, String arg1, String arg2, String arg3) throws SQLException {
        try {
            Field[] fields = new Field[]{new Field("", "TYPE_CAT", this.metadataCollationIndex, this.metadataEncoding, MysqlType.CHAR, 32), new Field("", "TYPE_SCHEM", this.metadataCollationIndex, this.metadataEncoding, MysqlType.CHAR, 32), new Field("", "TYPE_NAME", this.metadataCollationIndex, this.metadataEncoding, MysqlType.CHAR, 32), new Field("", "ATTR_NAME", this.metadataCollationIndex, this.metadataEncoding, MysqlType.CHAR, 32), new Field("", "DATA_TYPE", this.metadataCollationIndex, this.metadataEncoding, MysqlType.SMALLINT, 32), new Field("", "ATTR_TYPE_NAME", this.metadataCollationIndex, this.metadataEncoding, MysqlType.CHAR, 32), new Field("", "ATTR_SIZE", this.metadataCollationIndex, this.metadataEncoding, MysqlType.INT, 32), new Field("", "DECIMAL_DIGITS", this.metadataCollationIndex, this.metadataEncoding, MysqlType.INT, 32), new Field("", "NUM_PREC_RADIX", this.metadataCollationIndex, this.metadataEncoding, MysqlType.INT, 32), new Field("", "NULLABLE ", this.metadataCollationIndex, this.metadataEncoding, MysqlType.INT, 32), new Field("", "REMARKS", this.metadataCollationIndex, this.metadataEncoding, MysqlType.CHAR, 32), new Field("", "ATTR_DEF", this.metadataCollationIndex, this.metadataEncoding, MysqlType.CHAR, 32), new Field("", "SQL_DATA_TYPE", this.metadataCollationIndex, this.metadataEncoding, MysqlType.INT, 32), new Field("", "SQL_DATETIME_SUB", this.metadataCollationIndex, this.metadataEncoding, MysqlType.INT, 32), new Field("", "CHAR_OCTET_LENGTH", this.metadataCollationIndex, this.metadataEncoding, MysqlType.INT, 32), new Field("", "ORDINAL_POSITION", this.metadataCollationIndex, this.metadataEncoding, MysqlType.INT, 32), new Field("", "IS_NULLABLE", this.metadataCollationIndex, this.metadataEncoding, MysqlType.CHAR, 32), new Field("", "SCOPE_CATALOG", this.metadataCollationIndex, this.metadataEncoding, MysqlType.CHAR, 32), new Field("", "SCOPE_SCHEMA", this.metadataCollationIndex, this.metadataEncoding, MysqlType.CHAR, 32), new Field("", "SCOPE_TABLE", this.metadataCollationIndex, this.metadataEncoding, MysqlType.CHAR, 32), new Field("", "SOURCE_DATA_TYPE", this.metadataCollationIndex, this.metadataEncoding, MysqlType.SMALLINT, 32)};
            return this.resultSetFactory.createFromResultsetRows(1007, 1004, new ResultsetRowsStatic(new ArrayList(), new DefaultColumnDefinition(fields)));
        }
        catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public ResultSet getBestRowIdentifier(String catalog, String schema, final String table, int scope, boolean nullable) throws SQLException {
        try {
            if (table == null) {
                throw SQLError.createSQLException(Messages.getString("DatabaseMetaData.2"), "S1009", this.getExceptionInterceptor());
            }
            Field[] fields = new Field[]{new Field("", "SCOPE", this.metadataCollationIndex, this.metadataEncoding, MysqlType.SMALLINT, 5), new Field("", "COLUMN_NAME", this.metadataCollationIndex, this.metadataEncoding, MysqlType.CHAR, 32), new Field("", "DATA_TYPE", this.metadataCollationIndex, this.metadataEncoding, MysqlType.INT, 32), new Field("", "TYPE_NAME", this.metadataCollationIndex, this.metadataEncoding, MysqlType.CHAR, 32), new Field("", "COLUMN_SIZE", this.metadataCollationIndex, this.metadataEncoding, MysqlType.INT, 10), new Field("", "BUFFER_LENGTH", this.metadataCollationIndex, this.metadataEncoding, MysqlType.INT, 10), new Field("", "DECIMAL_DIGITS", this.metadataCollationIndex, this.metadataEncoding, MysqlType.SMALLINT, 10), new Field("", "PSEUDO_COLUMN", this.metadataCollationIndex, this.metadataEncoding, MysqlType.SMALLINT, 5)};
            final ArrayList rows = new ArrayList();
            String db = this.getDatabase(catalog, schema);
            try (final Statement stmt = this.conn.getMetadataSafeStatement();){
                new IterateBlock<String>(this.getDatabaseIterator(db)){

                    /*
                     * WARNING - Removed try catching itself - possible behaviour change.
                     */
                    @Override
                    void forEach(String dbStr) throws SQLException {
                        ResultSet results = null;
                        try {
                            block22: {
                                StringBuilder queryBuf = new StringBuilder("SHOW COLUMNS FROM ");
                                queryBuf.append(StringUtils.quoteIdentifier(table, DatabaseMetaData.this.quotedId, DatabaseMetaData.this.pedantic));
                                queryBuf.append(" FROM ");
                                queryBuf.append(StringUtils.quoteIdentifier(dbStr, DatabaseMetaData.this.quotedId, DatabaseMetaData.this.pedantic));
                                try {
                                    results = stmt.executeQuery(queryBuf.toString());
                                }
                                catch (SQLException sqlEx) {
                                    String sqlState = sqlEx.getSQLState();
                                    int errorCode = sqlEx.getErrorCode();
                                    if ("42S02".equals(sqlState) || errorCode == 1146 || errorCode == 1049) break block22;
                                    throw sqlEx;
                                }
                            }
                            while (results != null && results.next()) {
                                String keyType = results.getString("Key");
                                if (keyType == null || !StringUtils.startsWithIgnoreCase(keyType, "PRI")) continue;
                                byte[][] rowVal = new byte[8][];
                                rowVal[0] = Integer.toString(2).getBytes();
                                rowVal[1] = results.getBytes("Field");
                                String type = results.getString("Type");
                                int size = stmt.getMaxFieldSize();
                                int decimals = 0;
                                boolean hasLength = false;
                                if (type.indexOf("enum") != -1) {
                                    String temp = type.substring(type.indexOf("("), type.indexOf(")"));
                                    StringTokenizer tokenizer = new StringTokenizer(temp, ",");
                                    int maxLength = 0;
                                    while (tokenizer.hasMoreTokens()) {
                                        maxLength = Math.max(maxLength, tokenizer.nextToken().length() - 2);
                                    }
                                    size = maxLength;
                                    decimals = 0;
                                    type = "enum";
                                } else if (type.indexOf("(") != -1) {
                                    hasLength = true;
                                    if (type.indexOf(",") != -1) {
                                        size = Integer.parseInt(type.substring(type.indexOf("(") + 1, type.indexOf(",")));
                                        decimals = Integer.parseInt(type.substring(type.indexOf(",") + 1, type.indexOf(")")));
                                    } else {
                                        size = Integer.parseInt(type.substring(type.indexOf("(") + 1, type.indexOf(")")));
                                    }
                                    type = type.substring(0, type.indexOf("("));
                                }
                                MysqlType ft = MysqlType.getByName(type.toUpperCase());
                                rowVal[2] = DatabaseMetaData.this.s2b(String.valueOf(ft.getJdbcType()));
                                rowVal[3] = DatabaseMetaData.this.s2b(type);
                                rowVal[4] = hasLength ? Integer.toString(size + decimals).getBytes() : Long.toString(ft.getPrecision()).getBytes();
                                rowVal[5] = Integer.toString(maxBufferSize).getBytes();
                                rowVal[6] = Integer.toString(decimals).getBytes();
                                rowVal[7] = Integer.toString(1).getBytes();
                                rows.add(new ByteArrayRow(rowVal, DatabaseMetaData.this.getExceptionInterceptor()));
                            }
                        }
                        catch (SQLException sqlEx) {
                            if (!"42S02".equals(sqlEx.getSQLState())) {
                                throw sqlEx;
                            }
                        }
                        finally {
                            if (results != null) {
                                try {
                                    results.close();
                                }
                                catch (Exception exception) {}
                                results = null;
                            }
                        }
                    }
                }.doForAll();
            }
            ResultSetImpl results = this.resultSetFactory.createFromResultsetRows(1007, 1004, new ResultsetRowsStatic(rows, new DefaultColumnDefinition(fields)));
            return results;
        }
        catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    private void getCallStmtParameterTypes(String db, String quotedProcName, ProcedureType procType, String parameterNamePattern, List<Row> resultRows, boolean forGetFunctionColumns) throws SQLException {
        String declaration;
        String storageDefnClosures;
        String storageDefnDelims;
        boolean isProcedureInAnsiMode;
        byte[] procCatAsBytes;
        byte[] procNameAsBytes;
        String parameterDef;
        block42: {
            Statement paramRetrievalStmt = null;
            ResultSet paramRetrievalRs = null;
            parameterDef = null;
            procNameAsBytes = null;
            procCatAsBytes = null;
            isProcedureInAnsiMode = false;
            storageDefnDelims = null;
            storageDefnClosures = null;
            try {
                paramRetrievalStmt = this.conn.getMetadataSafeStatement();
                String oldDb = this.conn.getDatabase();
                if (this.conn.lowerCaseTableNames() && db != null && db.length() != 0 && oldDb != null && oldDb.length() != 0) {
                    ResultSet rs = null;
                    try {
                        this.conn.setDatabase(StringUtils.unQuoteIdentifier(db, this.quotedId));
                        rs = paramRetrievalStmt.executeQuery("SELECT DATABASE()");
                        rs.next();
                        db = rs.getString(1);
                    }
                    finally {
                        this.conn.setDatabase(oldDb);
                        if (rs != null) {
                            rs.close();
                        }
                    }
                }
                if (paramRetrievalStmt.getMaxRows() != 0) {
                    paramRetrievalStmt.setMaxRows(0);
                }
                int dotIndex = " ".equals(this.quotedId) ? quotedProcName.indexOf(".") : StringUtils.indexOfIgnoreCase(0, quotedProcName, ".", this.quotedId, this.quotedId, this.session.getServerSession().isNoBackslashEscapesSet() ? StringUtils.SEARCH_MODE__MRK_COM_WS : StringUtils.SEARCH_MODE__ALL);
                String dbName = null;
                if (dotIndex != -1 && dotIndex + 1 < quotedProcName.length()) {
                    dbName = quotedProcName.substring(0, dotIndex);
                    quotedProcName = quotedProcName.substring(dotIndex + 1);
                } else {
                    dbName = StringUtils.quoteIdentifier(db, this.quotedId, this.pedantic);
                }
                String tmpProcName = StringUtils.unQuoteIdentifier(quotedProcName, this.quotedId);
                procNameAsBytes = StringUtils.getBytes(tmpProcName, "UTF-8");
                tmpProcName = StringUtils.unQuoteIdentifier(dbName, this.quotedId);
                procCatAsBytes = StringUtils.getBytes(tmpProcName, "UTF-8");
                StringBuilder procNameBuf = new StringBuilder();
                procNameBuf.append(dbName);
                procNameBuf.append('.');
                procNameBuf.append(quotedProcName);
                String fieldName = null;
                if (procType == ProcedureType.PROCEDURE) {
                    paramRetrievalRs = paramRetrievalStmt.executeQuery("SHOW CREATE PROCEDURE " + procNameBuf.toString());
                    fieldName = "Create Procedure";
                } else {
                    paramRetrievalRs = paramRetrievalStmt.executeQuery("SHOW CREATE FUNCTION " + procNameBuf.toString());
                    fieldName = "Create Function";
                }
                if (!paramRetrievalRs.next()) break block42;
                String procedureDef = paramRetrievalRs.getString(fieldName);
                if (!(this.conn.getPropertySet().getBooleanProperty(PropertyKey.noAccessToProcedureBodies).getValue().booleanValue() || procedureDef != null && procedureDef.length() != 0)) {
                    throw SQLError.createSQLException(Messages.getString("DatabaseMetaData.4"), "S1000", this.getExceptionInterceptor());
                }
                try {
                    String sqlMode = paramRetrievalRs.getString("sql_mode");
                    if (StringUtils.indexOfIgnoreCase(sqlMode, "ANSI") != -1) {
                        isProcedureInAnsiMode = true;
                    }
                }
                catch (SQLException sqlMode) {
                    // empty catch block
                }
                String identifierMarkers = isProcedureInAnsiMode ? "`\"" : "`";
                String identifierAndStringMarkers = "'" + identifierMarkers;
                storageDefnDelims = "(" + identifierMarkers;
                storageDefnClosures = ")" + identifierMarkers;
                if (procedureDef != null && procedureDef.length() != 0) {
                    procedureDef = StringUtils.stripComments(procedureDef, identifierAndStringMarkers, identifierAndStringMarkers, true, false, true, true);
                    int openParenIndex = StringUtils.indexOfIgnoreCase(0, procedureDef, "(", this.quotedId, this.quotedId, this.session.getServerSession().isNoBackslashEscapesSet() ? StringUtils.SEARCH_MODE__MRK_COM_WS : StringUtils.SEARCH_MODE__ALL);
                    int endOfParamDeclarationIndex = 0;
                    endOfParamDeclarationIndex = this.endPositionOfParameterDeclaration(openParenIndex, procedureDef, this.quotedId);
                    if (procType == ProcedureType.FUNCTION) {
                        int declarationStart;
                        int returnsIndex = StringUtils.indexOfIgnoreCase(0, procedureDef, " RETURNS ", this.quotedId, this.quotedId, this.session.getServerSession().isNoBackslashEscapesSet() ? StringUtils.SEARCH_MODE__MRK_COM_WS : StringUtils.SEARCH_MODE__ALL);
                        int endReturnsDef = this.findEndOfReturnsClause(procedureDef, returnsIndex);
                        for (declarationStart = returnsIndex + "RETURNS ".length(); declarationStart < procedureDef.length() && Character.isWhitespace(procedureDef.charAt(declarationStart)); ++declarationStart) {
                        }
                        String returnsDefn = procedureDef.substring(declarationStart, endReturnsDef).trim();
                        TypeDescriptor returnDescriptor = new TypeDescriptor(returnsDefn, "YES");
                        resultRows.add(this.convertTypeDescriptorToProcedureRow(procNameAsBytes, procCatAsBytes, "", false, false, true, returnDescriptor, forGetFunctionColumns, 0));
                    }
                    if (openParenIndex == -1 || endOfParamDeclarationIndex == -1) {
                        throw SQLError.createSQLException(Messages.getString("DatabaseMetaData.5"), "S1000", this.getExceptionInterceptor());
                    }
                    parameterDef = procedureDef.substring(openParenIndex + 1, endOfParamDeclarationIndex);
                }
            }
            finally {
                SQLException sqlExRethrow = null;
                if (paramRetrievalRs != null) {
                    try {
                        paramRetrievalRs.close();
                    }
                    catch (SQLException sqlEx) {
                        sqlExRethrow = sqlEx;
                    }
                    paramRetrievalRs = null;
                }
                if (paramRetrievalStmt != null) {
                    try {
                        paramRetrievalStmt.close();
                    }
                    catch (SQLException sqlEx) {
                        sqlExRethrow = sqlEx;
                    }
                    paramRetrievalStmt = null;
                }
                if (sqlExRethrow != null) {
                    throw sqlExRethrow;
                }
            }
        }
        if (parameterDef == null) return;
        int ordinal = 1;
        List<String> parseList = StringUtils.split(parameterDef, ",", storageDefnDelims, storageDefnClosures, true);
        int parseListLen = parseList.size();
        for (int i = 0; i < parseListLen && (declaration = parseList.get(i)).trim().length() != 0; ++i) {
            declaration = declaration.replaceAll("[\\t\\n\\x0B\\f\\r]", " ");
            StringTokenizer declarationTok = new StringTokenizer(declaration, " \t");
            String paramName = null;
            boolean isOutParam = false;
            boolean isInParam = false;
            if (!declarationTok.hasMoreTokens()) throw SQLError.createSQLException(Messages.getString("DatabaseMetaData.8"), "S1000", this.getExceptionInterceptor());
            String possibleParamName = declarationTok.nextToken();
            if (possibleParamName.equalsIgnoreCase("OUT")) {
                isOutParam = true;
                if (!declarationTok.hasMoreTokens()) throw SQLError.createSQLException(Messages.getString("DatabaseMetaData.6"), "S1000", this.getExceptionInterceptor());
                paramName = declarationTok.nextToken();
            } else if (possibleParamName.equalsIgnoreCase("INOUT")) {
                isOutParam = true;
                isInParam = true;
                if (!declarationTok.hasMoreTokens()) throw SQLError.createSQLException(Messages.getString("DatabaseMetaData.6"), "S1000", this.getExceptionInterceptor());
                paramName = declarationTok.nextToken();
            } else if (possibleParamName.equalsIgnoreCase("IN")) {
                isOutParam = false;
                isInParam = true;
                if (!declarationTok.hasMoreTokens()) throw SQLError.createSQLException(Messages.getString("DatabaseMetaData.6"), "S1000", this.getExceptionInterceptor());
                paramName = declarationTok.nextToken();
            } else {
                isOutParam = false;
                isInParam = true;
                paramName = possibleParamName;
            }
            TypeDescriptor typeDesc = null;
            if (!declarationTok.hasMoreTokens()) throw SQLError.createSQLException(Messages.getString("DatabaseMetaData.7"), "S1000", this.getExceptionInterceptor());
            StringBuilder typeInfoBuf = new StringBuilder(declarationTok.nextToken());
            while (declarationTok.hasMoreTokens()) {
                typeInfoBuf.append(" ");
                typeInfoBuf.append(declarationTok.nextToken());
            }
            String typeInfo = typeInfoBuf.toString();
            typeDesc = new TypeDescriptor(typeInfo, "YES");
            if (paramName.startsWith("`") && paramName.endsWith("`") || isProcedureInAnsiMode && paramName.startsWith("\"") && paramName.endsWith("\"")) {
                paramName = paramName.substring(1, paramName.length() - 1);
            }
            if (parameterNamePattern != null && !StringUtils.wildCompareIgnoreCase(paramName, parameterNamePattern)) continue;
            Row row = this.convertTypeDescriptorToProcedureRow(procNameAsBytes, procCatAsBytes, paramName, isOutParam, isInParam, false, typeDesc, forGetFunctionColumns, ordinal++);
            resultRows.add(row);
        }
    }

    private int endPositionOfParameterDeclaration(int beginIndex, String procedureDef, String quoteChar) throws SQLException {
        int currentPos = beginIndex + 1;
        int parenDepth = 1;
        while (parenDepth > 0 && currentPos < procedureDef.length()) {
            int closedParenIndex = StringUtils.indexOfIgnoreCase(currentPos, procedureDef, ")", quoteChar, quoteChar, this.session.getServerSession().isNoBackslashEscapesSet() ? StringUtils.SEARCH_MODE__MRK_COM_WS : StringUtils.SEARCH_MODE__ALL);
            if (closedParenIndex != -1) {
                int nextOpenParenIndex = StringUtils.indexOfIgnoreCase(currentPos, procedureDef, "(", quoteChar, quoteChar, this.session.getServerSession().isNoBackslashEscapesSet() ? StringUtils.SEARCH_MODE__MRK_COM_WS : StringUtils.SEARCH_MODE__ALL);
                if (nextOpenParenIndex != -1 && nextOpenParenIndex < closedParenIndex) {
                    ++parenDepth;
                    currentPos = closedParenIndex + 1;
                    continue;
                }
                --parenDepth;
                currentPos = closedParenIndex;
                continue;
            }
            throw SQLError.createSQLException(Messages.getString("DatabaseMetaData.5"), "S1000", this.getExceptionInterceptor());
        }
        return currentPos;
    }

    private int findEndOfReturnsClause(String procedureDefn, int positionOfReturnKeyword) throws SQLException {
        int i;
        String openingMarkers = this.quotedId + "(";
        String closingMarkers = this.quotedId + ")";
        String[] tokens = new String[]{"LANGUAGE", "NOT", "DETERMINISTIC", "CONTAINS", "NO", "READ", "MODIFIES", "SQL", "COMMENT", "BEGIN", "RETURN"};
        int startLookingAt = positionOfReturnKeyword + "RETURNS".length() + 1;
        int endOfReturn = -1;
        for (i = 0; i < tokens.length; ++i) {
            int nextEndOfReturn = StringUtils.indexOfIgnoreCase(startLookingAt, procedureDefn, tokens[i], openingMarkers, closingMarkers, this.session.getServerSession().isNoBackslashEscapesSet() ? StringUtils.SEARCH_MODE__MRK_COM_WS : StringUtils.SEARCH_MODE__ALL);
            if (nextEndOfReturn == -1 || endOfReturn != -1 && nextEndOfReturn >= endOfReturn) continue;
            endOfReturn = nextEndOfReturn;
        }
        if (endOfReturn != -1) {
            return endOfReturn;
        }
        endOfReturn = StringUtils.indexOfIgnoreCase(startLookingAt, procedureDefn, ":", openingMarkers, closingMarkers, this.session.getServerSession().isNoBackslashEscapesSet() ? StringUtils.SEARCH_MODE__MRK_COM_WS : StringUtils.SEARCH_MODE__ALL);
        if (endOfReturn != -1) {
            for (i = endOfReturn; i > 0; --i) {
                if (!Character.isWhitespace(procedureDefn.charAt(i))) continue;
                return i;
            }
        }
        throw SQLError.createSQLException(Messages.getString("DatabaseMetaData.5"), "S1000", this.getExceptionInterceptor());
    }

    private int getCascadeDeleteOption(String cascadeOptions) {
        int onDeletePos = cascadeOptions.indexOf("ON DELETE");
        if (onDeletePos != -1) {
            String deleteOptions = cascadeOptions.substring(onDeletePos, cascadeOptions.length());
            if (deleteOptions.startsWith("ON DELETE CASCADE")) {
                return 0;
            }
            if (deleteOptions.startsWith("ON DELETE SET NULL")) {
                return 2;
            }
        }
        return 1;
    }

    private int getCascadeUpdateOption(String cascadeOptions) {
        int onUpdatePos = cascadeOptions.indexOf("ON UPDATE");
        if (onUpdatePos != -1) {
            String updateOptions = cascadeOptions.substring(onUpdatePos, cascadeOptions.length());
            if (updateOptions.startsWith("ON UPDATE CASCADE")) {
                return 0;
            }
            if (updateOptions.startsWith("ON UPDATE SET NULL")) {
                return 2;
            }
        }
        return 1;
    }

    protected IteratorWithCleanup<String> getDatabaseIterator(String dbSpec) throws SQLException {
        if (dbSpec == null) {
            return this.nullDatabaseMeansCurrent.getValue() != false ? new SingleStringIterator(this.database) : new StringListIterator(this.getDatabases());
        }
        return new SingleStringIterator(this.pedantic ? dbSpec : StringUtils.unQuoteIdentifier(dbSpec, this.quotedId));
    }

    protected IteratorWithCleanup<String> getSchemaPatternIterator(String schemaPattern) throws SQLException {
        if (schemaPattern == null) {
            return this.nullDatabaseMeansCurrent.getValue() != false ? new SingleStringIterator(this.database) : new StringListIterator(this.getDatabases());
        }
        return new StringListIterator(this.getDatabases(schemaPattern));
    }

    protected List<String> getDatabases() throws SQLException {
        return this.getDatabases(null);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    protected List<String> getDatabases(String dbPattern) throws SQLException {
        Statement pStmt = null;
        ResultSet results = null;
        Statement stmt = null;
        try {
            stmt = this.conn.getMetadataSafeStatement();
            StringBuilder queryBuf = new StringBuilder("SHOW DATABASES");
            if (dbPattern != null) {
                queryBuf.append(" LIKE ?");
            }
            pStmt = this.prepareMetaDataSafeStatement(queryBuf.toString());
            if (dbPattern != null) {
                pStmt.setString(1, dbPattern);
            }
            results = pStmt.executeQuery();
            int dbCount = 0;
            if (results.last()) {
                dbCount = results.getRow();
                results.beforeFirst();
            }
            ArrayList<String> resultsAsList = new ArrayList<String>(dbCount);
            while (results.next()) {
                resultsAsList.add(results.getString(1));
            }
            Collections.sort(resultsAsList);
            ArrayList<String> arrayList = resultsAsList;
            return arrayList;
        }
        finally {
            if (results != null) {
                try {
                    results.close();
                }
                catch (SQLException sqlEx) {
                    AssertionFailedException.shouldNotHappen(sqlEx);
                }
                results = null;
            }
            if (pStmt != null) {
                try {
                    pStmt.close();
                }
                catch (Exception sqlEx) {}
                pStmt = null;
            }
            if (stmt != null) {
                try {
                    stmt.close();
                }
                catch (SQLException sqlEx) {
                    AssertionFailedException.shouldNotHappen(sqlEx);
                }
                stmt = null;
            }
        }
    }

    @Override
    public ResultSet getCatalogs() throws SQLException {
        try {
            List<Object> resultsAsList = this.databaseTerm.getValue() == PropertyDefinitions.DatabaseTerm.SCHEMA ? new ArrayList() : this.getDatabases();
            Field[] fields = new Field[]{new Field("", "TABLE_CAT", this.metadataCollationIndex, this.metadataEncoding, MysqlType.VARCHAR, 0)};
            ArrayList<ByteArrayRow> tuples = new ArrayList<ByteArrayRow>(resultsAsList.size());
            for (String string : resultsAsList) {
                byte[][] rowVal = new byte[][]{this.s2b(string)};
                tuples.add(new ByteArrayRow(rowVal, this.getExceptionInterceptor()));
            }
            return this.resultSetFactory.createFromResultsetRows(1007, 1004, new ResultsetRowsStatic(tuples, new DefaultColumnDefinition(fields)));
        }
        catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    @Override
    public String getCatalogSeparator() throws SQLException {
        try {
            return ".";
        }
        catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    @Override
    public String getCatalogTerm() throws SQLException {
        try {
            return this.databaseTerm.getValue() == PropertyDefinitions.DatabaseTerm.SCHEMA ? "CATALOG" : "database";
        }
        catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    protected String getDatabase(String catalog, String schema) {
        if (this.databaseTerm.getValue() == PropertyDefinitions.DatabaseTerm.SCHEMA) {
            return schema == null && this.nullDatabaseMeansCurrent.getValue() != false ? this.database : schema;
        }
        return catalog == null && this.nullDatabaseMeansCurrent.getValue() != false ? this.database : catalog;
    }

    protected Field[] getColumnPrivilegesFields() {
        Field[] fields = new Field[]{new Field("", "TABLE_CAT", this.metadataCollationIndex, this.metadataEncoding, MysqlType.CHAR, 64), new Field("", "TABLE_SCHEM", this.metadataCollationIndex, this.metadataEncoding, MysqlType.CHAR, 1), new Field("", "TABLE_NAME", this.metadataCollationIndex, this.metadataEncoding, MysqlType.CHAR, 64), new Field("", "COLUMN_NAME", this.metadataCollationIndex, this.metadataEncoding, MysqlType.CHAR, 64), new Field("", "GRANTOR", this.metadataCollationIndex, this.metadataEncoding, MysqlType.CHAR, 77), new Field("", "GRANTEE", this.metadataCollationIndex, this.metadataEncoding, MysqlType.CHAR, 77), new Field("", "PRIVILEGE", this.metadataCollationIndex, this.metadataEncoding, MysqlType.CHAR, 64), new Field("", "IS_GRANTABLE", this.metadataCollationIndex, this.metadataEncoding, MysqlType.CHAR, 3)};
        return fields;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public ResultSet getColumnPrivileges(String catalog, String schema, String table, String columnNamePattern) throws SQLException {
        try {
            String db = this.getDatabase(catalog, schema);
            StringBuilder grantQueryBuf = new StringBuilder("SELECT c.host, c.db, t.grantor, c.user, c.table_name, c.column_name, c.column_priv");
            grantQueryBuf.append(" FROM mysql.columns_priv c, mysql.tables_priv t");
            grantQueryBuf.append(" WHERE c.host = t.host AND c.db = t.db AND c.table_name = t.table_name");
            if (db != null) {
                grantQueryBuf.append(" AND c.db = ?");
            }
            grantQueryBuf.append(" AND c.table_name = ?");
            if (columnNamePattern != null) {
                grantQueryBuf.append(" AND c.column_name LIKE ?");
            }
            PreparedStatement pStmt = null;
            ResultSet results = null;
            ArrayList<ByteArrayRow> grantRows = new ArrayList<ByteArrayRow>();
            try {
                pStmt = this.prepareMetaDataSafeStatement(grantQueryBuf.toString());
                int nextId = 1;
                if (db != null) {
                    pStmt.setString(nextId++, db);
                }
                pStmt.setString(nextId++, table);
                if (columnNamePattern != null) {
                    pStmt.setString(nextId, columnNamePattern);
                }
                results = pStmt.executeQuery();
                while (results.next()) {
                    String host = results.getString(1);
                    db = results.getString(2);
                    String grantor = results.getString(3);
                    String user = results.getString(4);
                    if (user == null || user.length() == 0) {
                        user = "%";
                    }
                    StringBuilder fullUser = new StringBuilder(user);
                    if (host != null && this.useHostsInPrivileges) {
                        fullUser.append("@");
                        fullUser.append(host);
                    }
                    String columnName = results.getString(6);
                    String allPrivileges = results.getString(7);
                    if (allPrivileges == null) continue;
                    allPrivileges = allPrivileges.toUpperCase(Locale.ENGLISH);
                    StringTokenizer st = new StringTokenizer(allPrivileges, ",");
                    while (st.hasMoreTokens()) {
                        String privilege = st.nextToken().trim();
                        byte[][] tuple = new byte[][]{this.databaseTerm.getValue() == PropertyDefinitions.DatabaseTerm.SCHEMA ? this.s2b("def") : this.s2b(db), this.databaseTerm.getValue() == PropertyDefinitions.DatabaseTerm.SCHEMA ? this.s2b(db) : null, this.s2b(table), this.s2b(columnName), grantor != null ? this.s2b(grantor) : null, this.s2b(fullUser.toString()), this.s2b(privilege), null};
                        grantRows.add(new ByteArrayRow(tuple, this.getExceptionInterceptor()));
                    }
                }
            }
            finally {
                if (results != null) {
                    try {
                        results.close();
                    }
                    catch (Exception exception) {}
                    results = null;
                }
                if (pStmt != null) {
                    try {
                        pStmt.close();
                    }
                    catch (Exception exception) {}
                    pStmt = null;
                }
            }
            return this.resultSetFactory.createFromResultsetRows(1007, 1004, new ResultsetRowsStatic(grantRows, new DefaultColumnDefinition(this.getColumnPrivilegesFields())));
        }
        catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public ResultSet getColumns(String catalog, final String schemaPattern, final String tableNamePattern, String columnNamePattern) throws SQLException {
        try {
            String db = this.getDatabase(catalog, schemaPattern);
            final String colPattern = columnNamePattern;
            Field[] fields = this.createColumnsFields();
            final ArrayList rows = new ArrayList();
            final boolean dbMapsToSchema = this.databaseTerm.getValue() == PropertyDefinitions.DatabaseTerm.SCHEMA;
            try (final Statement stmt = this.conn.getMetadataSafeStatement();){
                new IterateBlock<String>(dbMapsToSchema ? this.getSchemaPatternIterator(db) : this.getDatabaseIterator(db)){

                    /*
                     * WARNING - Removed try catching itself - possible behaviour change.
                     */
                    @Override
                    void forEach(String dbStr) throws SQLException {
                        ArrayList<String> tableNameList = new ArrayList<String>();
                        ResultSet tables = null;
                        try {
                            ResultSet resultSet = tables = dbMapsToSchema ? DatabaseMetaData.this.getTables(null, dbStr, tableNamePattern, new String[0]) : DatabaseMetaData.this.getTables(dbStr, schemaPattern, tableNamePattern, new String[0]);
                            while (tables.next()) {
                                String tableNameFromList = tables.getString("TABLE_NAME");
                                tableNameList.add(tableNameFromList);
                            }
                        }
                        finally {
                            if (tables != null) {
                                try {
                                    tables.close();
                                }
                                catch (Exception sqlEx) {
                                    AssertionFailedException.shouldNotHappen(sqlEx);
                                }
                                tables = null;
                            }
                        }
                        for (String tableName : tableNameList) {
                            ResultSet results = null;
                            try {
                                StringBuilder queryBuf = new StringBuilder("SHOW FULL COLUMNS FROM ");
                                queryBuf.append(StringUtils.quoteIdentifier(tableName, DatabaseMetaData.this.quotedId, DatabaseMetaData.this.pedantic));
                                queryBuf.append(" FROM ");
                                queryBuf.append(StringUtils.quoteIdentifier(dbStr, DatabaseMetaData.this.quotedId, DatabaseMetaData.this.pedantic));
                                if (colPattern != null) {
                                    queryBuf.append(" LIKE ");
                                    queryBuf.append(StringUtils.quoteIdentifier(colPattern, "'", true));
                                }
                                boolean fixUpOrdinalsRequired = false;
                                HashMap<String, Integer> ordinalFixUpMap = null;
                                if (colPattern != null && !colPattern.equals("%")) {
                                    fixUpOrdinalsRequired = true;
                                    StringBuilder fullColumnQueryBuf = new StringBuilder("SHOW FULL COLUMNS FROM ");
                                    fullColumnQueryBuf.append(StringUtils.quoteIdentifier(tableName, DatabaseMetaData.this.quotedId, DatabaseMetaData.this.pedantic));
                                    fullColumnQueryBuf.append(" FROM ");
                                    fullColumnQueryBuf.append(StringUtils.quoteIdentifier(dbStr, DatabaseMetaData.this.quotedId, DatabaseMetaData.this.pedantic));
                                    results = stmt.executeQuery(fullColumnQueryBuf.toString());
                                    ordinalFixUpMap = new HashMap<String, Integer>();
                                    int fullOrdinalPos = 1;
                                    while (results.next()) {
                                        String fullOrdColName = results.getString("Field");
                                        ordinalFixUpMap.put(fullOrdColName, fullOrdinalPos++);
                                    }
                                    results.close();
                                }
                                results = stmt.executeQuery(queryBuf.toString());
                                int ordPos = 1;
                                while (results.next()) {
                                    TypeDescriptor typeDesc = new TypeDescriptor(results.getString("Type"), results.getString("Null"));
                                    byte[][] rowVal = new byte[24][];
                                    rowVal[0] = DatabaseMetaData.this.databaseTerm.getValue() == PropertyDefinitions.DatabaseTerm.SCHEMA ? DatabaseMetaData.this.s2b("def") : DatabaseMetaData.this.s2b(dbStr);
                                    rowVal[1] = DatabaseMetaData.this.databaseTerm.getValue() == PropertyDefinitions.DatabaseTerm.SCHEMA ? DatabaseMetaData.this.s2b(dbStr) : null;
                                    rowVal[2] = DatabaseMetaData.this.s2b(tableName);
                                    rowVal[3] = results.getBytes("Field");
                                    rowVal[4] = Short.toString((short)typeDesc.mysqlType.getJdbcType()).getBytes();
                                    rowVal[5] = DatabaseMetaData.this.s2b(typeDesc.mysqlType.getName());
                                    if (typeDesc.columnSize == null) {
                                        rowVal[6] = null;
                                    } else {
                                        String collation = results.getString("Collation");
                                        int mbminlen = 1;
                                        if (collation != null) {
                                            if (collation.indexOf("ucs2") > -1 || collation.indexOf("utf16") > -1) {
                                                mbminlen = 2;
                                            } else if (collation.indexOf("utf32") > -1) {
                                                mbminlen = 4;
                                            }
                                        }
                                        rowVal[6] = mbminlen == 1 ? DatabaseMetaData.this.s2b(typeDesc.columnSize.toString()) : DatabaseMetaData.this.s2b(Integer.valueOf(typeDesc.columnSize / mbminlen).toString());
                                    }
                                    rowVal[7] = DatabaseMetaData.this.s2b(Integer.toString(typeDesc.bufferLength));
                                    rowVal[8] = typeDesc.decimalDigits == null ? null : DatabaseMetaData.this.s2b(typeDesc.decimalDigits.toString());
                                    rowVal[9] = DatabaseMetaData.this.s2b(Integer.toString(typeDesc.numPrecRadix));
                                    rowVal[10] = DatabaseMetaData.this.s2b(Integer.toString(typeDesc.nullability));
                                    try {
                                        rowVal[11] = results.getBytes("Comment");
                                    }
                                    catch (Exception E) {
                                        rowVal[11] = new byte[0];
                                    }
                                    rowVal[12] = results.getBytes("Default");
                                    rowVal[13] = new byte[]{48};
                                    rowVal[14] = new byte[]{48};
                                    rowVal[15] = (byte[])(StringUtils.indexOfIgnoreCase(typeDesc.mysqlType.getName(), "CHAR") != -1 || StringUtils.indexOfIgnoreCase(typeDesc.mysqlType.getName(), "BLOB") != -1 || StringUtils.indexOfIgnoreCase(typeDesc.mysqlType.getName(), "TEXT") != -1 || StringUtils.indexOfIgnoreCase(typeDesc.mysqlType.getName(), "ENUM") != -1 || StringUtils.indexOfIgnoreCase(typeDesc.mysqlType.getName(), "SET") != -1 || StringUtils.indexOfIgnoreCase(typeDesc.mysqlType.getName(), "BINARY") != -1 ? rowVal[6] : null);
                                    if (!fixUpOrdinalsRequired) {
                                        rowVal[16] = Integer.toString(ordPos++).getBytes();
                                    } else {
                                        String origColName = results.getString("Field");
                                        Integer realOrdinal = (Integer)ordinalFixUpMap.get(origColName);
                                        if (realOrdinal != null) {
                                            rowVal[16] = realOrdinal.toString().getBytes();
                                        } else {
                                            throw SQLError.createSQLException(Messages.getString("DatabaseMetaData.10"), "S1000", DatabaseMetaData.this.getExceptionInterceptor());
                                        }
                                    }
                                    rowVal[17] = DatabaseMetaData.this.s2b(typeDesc.isNullable);
                                    rowVal[18] = null;
                                    rowVal[19] = null;
                                    rowVal[20] = null;
                                    rowVal[21] = null;
                                    rowVal[22] = DatabaseMetaData.this.s2b("");
                                    String extra = results.getString("Extra");
                                    if (extra != null) {
                                        rowVal[22] = DatabaseMetaData.this.s2b(StringUtils.indexOfIgnoreCase(extra, "auto_increment") != -1 ? "YES" : "NO");
                                        rowVal[23] = DatabaseMetaData.this.s2b(StringUtils.indexOfIgnoreCase(extra, "generated") != -1 ? "YES" : "NO");
                                    }
                                    rows.add(new ByteArrayRow(rowVal, DatabaseMetaData.this.getExceptionInterceptor()));
                                }
                            }
                            finally {
                                if (results == null) continue;
                                try {
                                    results.close();
                                }
                                catch (Exception exception) {}
                                results = null;
                            }
                        }
                    }
                }.doForAll();
            }
            ResultSetImpl results = this.resultSetFactory.createFromResultsetRows(1007, 1004, new ResultsetRowsStatic(rows, new DefaultColumnDefinition(fields)));
            return results;
        }
        catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    protected Field[] createColumnsFields() {
        Field[] fields = new Field[]{new Field("", "TABLE_CAT", this.metadataCollationIndex, this.metadataEncoding, MysqlType.CHAR, 255), new Field("", "TABLE_SCHEM", this.metadataCollationIndex, this.metadataEncoding, MysqlType.CHAR, 0), new Field("", "TABLE_NAME", this.metadataCollationIndex, this.metadataEncoding, MysqlType.CHAR, 255), new Field("", "COLUMN_NAME", this.metadataCollationIndex, this.metadataEncoding, MysqlType.CHAR, 32), new Field("", "DATA_TYPE", this.metadataCollationIndex, this.metadataEncoding, MysqlType.INT, 5), new Field("", "TYPE_NAME", this.metadataCollationIndex, this.metadataEncoding, MysqlType.CHAR, 16), new Field("", "COLUMN_SIZE", this.metadataCollationIndex, this.metadataEncoding, MysqlType.INT, Integer.toString(Integer.MAX_VALUE).length()), new Field("", "BUFFER_LENGTH", this.metadataCollationIndex, this.metadataEncoding, MysqlType.INT, 10), new Field("", "DECIMAL_DIGITS", this.metadataCollationIndex, this.metadataEncoding, MysqlType.INT, 10), new Field("", "NUM_PREC_RADIX", this.metadataCollationIndex, this.metadataEncoding, MysqlType.INT, 10), new Field("", "NULLABLE", this.metadataCollationIndex, this.metadataEncoding, MysqlType.INT, 10), new Field("", "REMARKS", this.metadataCollationIndex, this.metadataEncoding, MysqlType.CHAR, 0), new Field("", "COLUMN_DEF", this.metadataCollationIndex, this.metadataEncoding, MysqlType.CHAR, 0), new Field("", "SQL_DATA_TYPE", this.metadataCollationIndex, this.metadataEncoding, MysqlType.INT, 10), new Field("", "SQL_DATETIME_SUB", this.metadataCollationIndex, this.metadataEncoding, MysqlType.INT, 10), new Field("", "CHAR_OCTET_LENGTH", this.metadataCollationIndex, this.metadataEncoding, MysqlType.INT, Integer.toString(Integer.MAX_VALUE).length()), new Field("", "ORDINAL_POSITION", this.metadataCollationIndex, this.metadataEncoding, MysqlType.INT, 10), new Field("", "IS_NULLABLE", this.metadataCollationIndex, this.metadataEncoding, MysqlType.CHAR, 3), new Field("", "SCOPE_CATALOG", this.metadataCollationIndex, this.metadataEncoding, MysqlType.CHAR, 255), new Field("", "SCOPE_SCHEMA", this.metadataCollationIndex, this.metadataEncoding, MysqlType.CHAR, 255), new Field("", "SCOPE_TABLE", this.metadataCollationIndex, this.metadataEncoding, MysqlType.CHAR, 255), new Field("", "SOURCE_DATA_TYPE", this.metadataCollationIndex, this.metadataEncoding, MysqlType.SMALLINT, 10), new Field("", "IS_AUTOINCREMENT", this.metadataCollationIndex, this.metadataEncoding, MysqlType.CHAR, 3), new Field("", "IS_GENERATEDCOLUMN", this.metadataCollationIndex, this.metadataEncoding, MysqlType.CHAR, 3)};
        return fields;
    }

    @Override
    public Connection getConnection() throws SQLException {
        try {
            return this.conn;
        }
        catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public ResultSet getCrossReference(String primaryCatalog, String primarySchema, final String primaryTable, String foreignCatalog, String foreignSchema, final String foreignTable) throws SQLException {
        try {
            if (primaryTable == null) {
                throw SQLError.createSQLException(Messages.getString("DatabaseMetaData.2"), "S1009", this.getExceptionInterceptor());
            }
            String foreignDb = this.getDatabase(foreignCatalog, foreignSchema);
            Field[] fields = this.createFkMetadataFields();
            final ArrayList tuples = new ArrayList();
            final boolean dbMapsToSchema = this.databaseTerm.getValue() == PropertyDefinitions.DatabaseTerm.SCHEMA;
            try (Statement stmt = this.conn.getMetadataSafeStatement();){
                new IterateBlock<String>(this.getDatabaseIterator(foreignDb)){

                    /*
                     * WARNING - Removed try catching itself - possible behaviour change.
                     */
                    @Override
                    void forEach(String dbStr) throws SQLException {
                        ResultSet fkresults = null;
                        try {
                            fkresults = DatabaseMetaData.this.extractForeignKeyFromCreateTable(dbStr, null);
                            String foreignTableWithCase = DatabaseMetaData.this.getTableNameWithCase(foreignTable);
                            String primaryTableWithCase = DatabaseMetaData.this.getTableNameWithCase(primaryTable);
                            while (fkresults.next()) {
                                String comment;
                                String tableType = fkresults.getString("Type");
                                if (tableType == null || !tableType.equalsIgnoreCase("innodb") && !tableType.equalsIgnoreCase(DatabaseMetaData.SUPPORTS_FK) || (comment = fkresults.getString("Comment").trim()) == null) continue;
                                StringTokenizer commentTokens = new StringTokenizer(comment, ";", false);
                                if (commentTokens.hasMoreTokens()) {
                                    String string = commentTokens.nextToken();
                                }
                                while (commentTokens.hasMoreTokens()) {
                                    String keys = commentTokens.nextToken();
                                    LocalAndReferencedColumns parsedInfo = DatabaseMetaData.this.parseTableStatusIntoLocalAndReferencedColumns(keys);
                                    int keySeq = 1;
                                    Iterator<String> referencingColumns = parsedInfo.localColumnsList.iterator();
                                    Iterator<String> referencedColumns = parsedInfo.referencedColumnsList.iterator();
                                    while (referencingColumns.hasNext()) {
                                        String referencingColumn = StringUtils.unQuoteIdentifier(referencingColumns.next(), DatabaseMetaData.this.quotedId);
                                        String dummy = fkresults.getString("Name");
                                        if (dummy.compareTo(foreignTableWithCase) != 0 || parsedInfo.referencedTable.compareTo(primaryTableWithCase) != 0) continue;
                                        byte[][] tuple = new byte[14][];
                                        tuple[0] = dbMapsToSchema ? DatabaseMetaData.this.s2b("def") : DatabaseMetaData.this.s2b(parsedInfo.referencedDatabase);
                                        tuple[1] = dbMapsToSchema ? DatabaseMetaData.this.s2b(parsedInfo.referencedDatabase) : null;
                                        tuple[2] = DatabaseMetaData.this.s2b(parsedInfo.referencedTable);
                                        tuple[3] = DatabaseMetaData.this.s2b(StringUtils.unQuoteIdentifier(referencedColumns.next(), DatabaseMetaData.this.quotedId));
                                        tuple[4] = dbMapsToSchema ? DatabaseMetaData.this.s2b("def") : DatabaseMetaData.this.s2b(dbStr);
                                        tuple[5] = dbMapsToSchema ? DatabaseMetaData.this.s2b(dbStr) : null;
                                        tuple[6] = DatabaseMetaData.this.s2b(dummy);
                                        tuple[7] = DatabaseMetaData.this.s2b(referencingColumn);
                                        tuple[8] = Integer.toString(keySeq).getBytes();
                                        int[] actions = DatabaseMetaData.this.getForeignKeyActions(keys);
                                        tuple[9] = Integer.toString(actions[1]).getBytes();
                                        tuple[10] = Integer.toString(actions[0]).getBytes();
                                        tuple[11] = DatabaseMetaData.this.s2b(parsedInfo.constraintName);
                                        tuple[12] = null;
                                        tuple[13] = Integer.toString(7).getBytes();
                                        tuples.add(new ByteArrayRow(tuple, DatabaseMetaData.this.getExceptionInterceptor()));
                                        ++keySeq;
                                    }
                                }
                            }
                        }
                        finally {
                            if (fkresults != null) {
                                try {
                                    fkresults.close();
                                }
                                catch (Exception sqlEx) {
                                    AssertionFailedException.shouldNotHappen(sqlEx);
                                }
                                fkresults = null;
                            }
                        }
                    }
                }.doForAll();
            }
            ResultSetImpl results = this.resultSetFactory.createFromResultsetRows(1007, 1004, new ResultsetRowsStatic(tuples, new DefaultColumnDefinition(fields)));
            return results;
        }
        catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    protected Field[] createFkMetadataFields() {
        Field[] fields = new Field[]{new Field("", "PKTABLE_CAT", this.metadataCollationIndex, this.metadataEncoding, MysqlType.CHAR, 255), new Field("", "PKTABLE_SCHEM", this.metadataCollationIndex, this.metadataEncoding, MysqlType.CHAR, 0), new Field("", "PKTABLE_NAME", this.metadataCollationIndex, this.metadataEncoding, MysqlType.CHAR, 255), new Field("", "PKCOLUMN_NAME", this.metadataCollationIndex, this.metadataEncoding, MysqlType.CHAR, 32), new Field("", "FKTABLE_CAT", this.metadataCollationIndex, this.metadataEncoding, MysqlType.CHAR, 255), new Field("", "FKTABLE_SCHEM", this.metadataCollationIndex, this.metadataEncoding, MysqlType.CHAR, 0), new Field("", "FKTABLE_NAME", this.metadataCollationIndex, this.metadataEncoding, MysqlType.CHAR, 255), new Field("", "FKCOLUMN_NAME", this.metadataCollationIndex, this.metadataEncoding, MysqlType.CHAR, 32), new Field("", "KEY_SEQ", this.metadataCollationIndex, this.metadataEncoding, MysqlType.SMALLINT, 2), new Field("", "UPDATE_RULE", this.metadataCollationIndex, this.metadataEncoding, MysqlType.SMALLINT, 2), new Field("", "DELETE_RULE", this.metadataCollationIndex, this.metadataEncoding, MysqlType.SMALLINT, 2), new Field("", "FK_NAME", this.metadataCollationIndex, this.metadataEncoding, MysqlType.CHAR, 0), new Field("", "PK_NAME", this.metadataCollationIndex, this.metadataEncoding, MysqlType.CHAR, 0), new Field("", "DEFERRABILITY", this.metadataCollationIndex, this.metadataEncoding, MysqlType.SMALLINT, 2)};
        return fields;
    }

    @Override
    public int getDatabaseMajorVersion() throws SQLException {
        try {
            return this.conn.getServerVersion().getMajor();
        }
        catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    @Override
    public int getDatabaseMinorVersion() throws SQLException {
        try {
            return this.conn.getServerVersion().getMinor();
        }
        catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    @Override
    public String getDatabaseProductName() throws SQLException {
        try {
            return "MySQL";
        }
        catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    @Override
    public String getDatabaseProductVersion() throws SQLException {
        try {
            return this.conn.getServerVersion().toString();
        }
        catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    @Override
    public int getDefaultTransactionIsolation() throws SQLException {
        try {
            return 2;
        }
        catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    @Override
    public int getDriverMajorVersion() {
        return NonRegisteringDriver.getMajorVersionInternal();
    }

    @Override
    public int getDriverMinorVersion() {
        return NonRegisteringDriver.getMinorVersionInternal();
    }

    @Override
    public String getDriverName() throws SQLException {
        try {
            return "MySQL Connector/J";
        }
        catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    @Override
    public String getDriverVersion() throws SQLException {
        try {
            return "mysql-connector-java-8.0.25 (Revision: 08be9e9b4cba6aa115f9b27b215887af40b159e0)";
        }
        catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public ResultSet getExportedKeys(String catalog, String schema, final String table) throws SQLException {
        try {
            if (table == null) {
                throw SQLError.createSQLException(Messages.getString("DatabaseMetaData.2"), "S1009", this.getExceptionInterceptor());
            }
            Field[] fields = this.createFkMetadataFields();
            final ArrayList rows = new ArrayList();
            String db = this.getDatabase(catalog, schema);
            try (Statement stmt = this.conn.getMetadataSafeStatement();){
                new IterateBlock<String>(this.getDatabaseIterator(db)){

                    /*
                     * WARNING - Removed try catching itself - possible behaviour change.
                     */
                    @Override
                    void forEach(String dbStr) throws SQLException {
                        ResultSet fkresults = null;
                        try {
                            fkresults = DatabaseMetaData.this.extractForeignKeyFromCreateTable(dbStr, null);
                            String tableNameWithCase = DatabaseMetaData.this.getTableNameWithCase(table);
                            while (fkresults.next()) {
                                StringTokenizer commentTokens;
                                String comment;
                                String tableType = fkresults.getString("Type");
                                if (tableType == null || !tableType.equalsIgnoreCase("innodb") && !tableType.equalsIgnoreCase(DatabaseMetaData.SUPPORTS_FK) || (comment = fkresults.getString("Comment").trim()) == null || !(commentTokens = new StringTokenizer(comment, ";", false)).hasMoreTokens()) continue;
                                commentTokens.nextToken();
                                while (commentTokens.hasMoreTokens()) {
                                    String keysComment = commentTokens.nextToken();
                                    DatabaseMetaData.this.populateKeyResults(dbStr, tableNameWithCase, keysComment, rows, fkresults.getString("Name"), true);
                                }
                            }
                        }
                        finally {
                            if (fkresults != null) {
                                try {
                                    fkresults.close();
                                }
                                catch (SQLException sqlEx) {
                                    AssertionFailedException.shouldNotHappen(sqlEx);
                                }
                                fkresults = null;
                            }
                        }
                    }
                }.doForAll();
            }
            ResultSetImpl results = this.resultSetFactory.createFromResultsetRows(1007, 1004, new ResultsetRowsStatic(rows, new DefaultColumnDefinition(fields)));
            return results;
        }
        catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    @Override
    public String getExtraNameCharacters() throws SQLException {
        try {
            return "#@";
        }
        catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    protected int[] getForeignKeyActions(String commentString) {
        int[] actions = new int[]{1, 1};
        int lastParenIndex = commentString.lastIndexOf(")");
        if (lastParenIndex != commentString.length() - 1) {
            String cascadeOptions = commentString.substring(lastParenIndex + 1).trim().toUpperCase(Locale.ENGLISH);
            actions[0] = this.getCascadeDeleteOption(cascadeOptions);
            actions[1] = this.getCascadeUpdateOption(cascadeOptions);
        }
        return actions;
    }

    @Override
    public String getIdentifierQuoteString() throws SQLException {
        try {
            return this.session.getIdentifierQuoteString();
        }
        catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public ResultSet getImportedKeys(String catalog, String schema, final String table) throws SQLException {
        try {
            if (table == null) {
                throw SQLError.createSQLException(Messages.getString("DatabaseMetaData.2"), "S1009", this.getExceptionInterceptor());
            }
            Field[] fields = this.createFkMetadataFields();
            final ArrayList rows = new ArrayList();
            String db = this.getDatabase(catalog, schema);
            try (Statement stmt = this.conn.getMetadataSafeStatement();){
                new IterateBlock<String>(this.getDatabaseIterator(db)){

                    /*
                     * WARNING - Removed try catching itself - possible behaviour change.
                     */
                    @Override
                    void forEach(String dbStr) throws SQLException {
                        ResultSet fkresults = null;
                        try {
                            fkresults = DatabaseMetaData.this.extractForeignKeyFromCreateTable(dbStr, table);
                            while (fkresults.next()) {
                                StringTokenizer commentTokens;
                                String comment;
                                String tableType = fkresults.getString("Type");
                                if (tableType == null || !tableType.equalsIgnoreCase("innodb") && !tableType.equalsIgnoreCase(DatabaseMetaData.SUPPORTS_FK) || (comment = fkresults.getString("Comment").trim()) == null || !(commentTokens = new StringTokenizer(comment, ";", false)).hasMoreTokens()) continue;
                                commentTokens.nextToken();
                                while (commentTokens.hasMoreTokens()) {
                                    String keysComment = commentTokens.nextToken();
                                    DatabaseMetaData.this.populateKeyResults(dbStr, table, keysComment, rows, null, false);
                                }
                            }
                        }
                        finally {
                            if (fkresults != null) {
                                try {
                                    fkresults.close();
                                }
                                catch (SQLException sqlEx) {
                                    AssertionFailedException.shouldNotHappen(sqlEx);
                                }
                                fkresults = null;
                            }
                        }
                    }
                }.doForAll();
            }
            ResultSetImpl results = this.resultSetFactory.createFromResultsetRows(1007, 1004, new ResultsetRowsStatic(rows, new DefaultColumnDefinition(fields)));
            return results;
        }
        catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public ResultSet getIndexInfo(String catalog, String schema, final String table, final boolean unique, boolean approximate) throws SQLException {
        try {
            Field[] fields = this.createIndexInfoFields();
            final TreeMap sortedRows = new TreeMap();
            ArrayList rows = new ArrayList();
            final Statement stmt = this.conn.getMetadataSafeStatement();
            String db = this.getDatabase(catalog, schema);
            final boolean dbMapsToSchema = this.databaseTerm.getValue() == PropertyDefinitions.DatabaseTerm.SCHEMA;
            try {
                ResultSetImpl indexInfo;
                new IterateBlock<String>(this.getDatabaseIterator(db)){

                    /*
                     * WARNING - Removed try catching itself - possible behaviour change.
                     */
                    @Override
                    void forEach(String dbStr) throws SQLException {
                        ResultSet results = null;
                        try {
                            block12: {
                                StringBuilder queryBuf = new StringBuilder("SHOW INDEX FROM ");
                                queryBuf.append(StringUtils.quoteIdentifier(table, DatabaseMetaData.this.quotedId, DatabaseMetaData.this.pedantic));
                                queryBuf.append(" FROM ");
                                queryBuf.append(StringUtils.quoteIdentifier(dbStr, DatabaseMetaData.this.quotedId, DatabaseMetaData.this.pedantic));
                                try {
                                    results = stmt.executeQuery(queryBuf.toString());
                                }
                                catch (SQLException sqlEx) {
                                    String sqlState = sqlEx.getSQLState();
                                    int errorCode = sqlEx.getErrorCode();
                                    if ("42S02".equals(sqlState) || errorCode == 1146 || errorCode == 1049) break block12;
                                    throw sqlEx;
                                }
                            }
                            while (results != null && results.next()) {
                                byte[][] row = new byte[14][];
                                row[0] = dbMapsToSchema ? DatabaseMetaData.this.s2b("def") : DatabaseMetaData.this.s2b(dbStr);
                                row[1] = dbMapsToSchema ? DatabaseMetaData.this.s2b(dbStr) : null;
                                row[2] = results.getBytes("Table");
                                boolean indexIsUnique = results.getInt("Non_unique") == 0;
                                row[3] = !indexIsUnique ? DatabaseMetaData.this.s2b("true") : DatabaseMetaData.this.s2b("false");
                                row[4] = null;
                                row[5] = results.getBytes("Key_name");
                                short indexType = 3;
                                row[6] = Integer.toString(indexType).getBytes();
                                row[7] = results.getBytes("Seq_in_index");
                                row[8] = results.getBytes("Column_name");
                                row[9] = results.getBytes("Collation");
                                long cardinality = results.getLong("Cardinality");
                                row[10] = DatabaseMetaData.this.s2b(String.valueOf(cardinality));
                                row[11] = DatabaseMetaData.this.s2b("0");
                                row[12] = null;
                                IndexMetaDataKey indexInfoKey = new IndexMetaDataKey(!indexIsUnique, indexType, results.getString("Key_name").toLowerCase(), results.getShort("Seq_in_index"));
                                if (unique) {
                                    if (!indexIsUnique) continue;
                                    sortedRows.put(indexInfoKey, new ByteArrayRow(row, DatabaseMetaData.this.getExceptionInterceptor()));
                                    continue;
                                }
                                sortedRows.put(indexInfoKey, new ByteArrayRow(row, DatabaseMetaData.this.getExceptionInterceptor()));
                            }
                        }
                        finally {
                            if (results != null) {
                                try {
                                    results.close();
                                }
                                catch (Exception exception) {}
                                results = null;
                            }
                        }
                    }
                }.doForAll();
                Iterator sortedRowsIterator = sortedRows.values().iterator();
                while (sortedRowsIterator.hasNext()) {
                    rows.add(sortedRowsIterator.next());
                }
                ResultSetImpl resultSetImpl = indexInfo = this.resultSetFactory.createFromResultsetRows(1007, 1004, new ResultsetRowsStatic(rows, new DefaultColumnDefinition(fields)));
                return resultSetImpl;
            }
            finally {
                if (stmt != null) {
                    stmt.close();
                }
            }
        }
        catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    protected Field[] createIndexInfoFields() {
        Field[] fields = new Field[]{new Field("", "TABLE_CAT", this.metadataCollationIndex, this.metadataEncoding, MysqlType.CHAR, 255), new Field("", "TABLE_SCHEM", this.metadataCollationIndex, this.metadataEncoding, MysqlType.CHAR, 0), new Field("", "TABLE_NAME", this.metadataCollationIndex, this.metadataEncoding, MysqlType.CHAR, 255), new Field("", "NON_UNIQUE", this.metadataCollationIndex, this.metadataEncoding, MysqlType.BOOLEAN, 4), new Field("", "INDEX_QUALIFIER", this.metadataCollationIndex, this.metadataEncoding, MysqlType.CHAR, 1), new Field("", "INDEX_NAME", this.metadataCollationIndex, this.metadataEncoding, MysqlType.CHAR, 32), new Field("", "TYPE", this.metadataCollationIndex, this.metadataEncoding, MysqlType.SMALLINT, 32), new Field("", "ORDINAL_POSITION", this.metadataCollationIndex, this.metadataEncoding, MysqlType.SMALLINT, 5), new Field("", "COLUMN_NAME", this.metadataCollationIndex, this.metadataEncoding, MysqlType.CHAR, 32), new Field("", "ASC_OR_DESC", this.metadataCollationIndex, this.metadataEncoding, MysqlType.CHAR, 1), new Field("", "CARDINALITY", this.metadataCollationIndex, this.metadataEncoding, MysqlType.BIGINT, 20), new Field("", "PAGES", this.metadataCollationIndex, this.metadataEncoding, MysqlType.BIGINT, 20), new Field("", "FILTER_CONDITION", this.metadataCollationIndex, this.metadataEncoding, MysqlType.CHAR, 32)};
        return fields;
    }

    @Override
    public int getJDBCMajorVersion() throws SQLException {
        try {
            return 4;
        }
        catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    @Override
    public int getJDBCMinorVersion() throws SQLException {
        try {
            return 2;
        }
        catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    @Override
    public int getMaxBinaryLiteralLength() throws SQLException {
        try {
            return 0xFFFFF8;
        }
        catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    @Override
    public int getMaxCatalogNameLength() throws SQLException {
        try {
            return 32;
        }
        catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    @Override
    public int getMaxCharLiteralLength() throws SQLException {
        try {
            return 0xFFFFF8;
        }
        catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    @Override
    public int getMaxColumnNameLength() throws SQLException {
        try {
            return 64;
        }
        catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    @Override
    public int getMaxColumnsInGroupBy() throws SQLException {
        try {
            return 64;
        }
        catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    @Override
    public int getMaxColumnsInIndex() throws SQLException {
        try {
            return 16;
        }
        catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    @Override
    public int getMaxColumnsInOrderBy() throws SQLException {
        try {
            return 64;
        }
        catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    @Override
    public int getMaxColumnsInSelect() throws SQLException {
        try {
            return 256;
        }
        catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    @Override
    public int getMaxColumnsInTable() throws SQLException {
        try {
            return 512;
        }
        catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    @Override
    public int getMaxConnections() throws SQLException {
        try {
            return 0;
        }
        catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    @Override
    public int getMaxCursorNameLength() throws SQLException {
        try {
            return 64;
        }
        catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    @Override
    public int getMaxIndexLength() throws SQLException {
        try {
            return 256;
        }
        catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    @Override
    public int getMaxProcedureNameLength() throws SQLException {
        try {
            return 0;
        }
        catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    @Override
    public int getMaxRowSize() throws SQLException {
        try {
            return 0x7FFFFFF7;
        }
        catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    @Override
    public int getMaxSchemaNameLength() throws SQLException {
        try {
            return 0;
        }
        catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    @Override
    public int getMaxStatementLength() throws SQLException {
        try {
            return maxBufferSize - 4;
        }
        catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    @Override
    public int getMaxStatements() throws SQLException {
        try {
            return 0;
        }
        catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    @Override
    public int getMaxTableNameLength() throws SQLException {
        try {
            return 64;
        }
        catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    @Override
    public int getMaxTablesInSelect() throws SQLException {
        try {
            return 256;
        }
        catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    @Override
    public int getMaxUserNameLength() throws SQLException {
        try {
            return 16;
        }
        catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    @Override
    public String getNumericFunctions() throws SQLException {
        try {
            return "ABS,ACOS,ASIN,ATAN,ATAN2,BIT_COUNT,CEILING,COS,COT,DEGREES,EXP,FLOOR,LOG,LOG10,MAX,MIN,MOD,PI,POW,POWER,RADIANS,RAND,ROUND,SIN,SQRT,TAN,TRUNCATE";
        }
        catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    protected Field[] getPrimaryKeysFields() {
        Field[] fields = new Field[]{new Field("", "TABLE_CAT", this.metadataCollationIndex, this.metadataEncoding, MysqlType.CHAR, 255), new Field("", "TABLE_SCHEM", this.metadataCollationIndex, this.metadataEncoding, MysqlType.CHAR, 0), new Field("", "TABLE_NAME", this.metadataCollationIndex, this.metadataEncoding, MysqlType.CHAR, 255), new Field("", "COLUMN_NAME", this.metadataCollationIndex, this.metadataEncoding, MysqlType.CHAR, 32), new Field("", "KEY_SEQ", this.metadataCollationIndex, this.metadataEncoding, MysqlType.SMALLINT, 5), new Field("", "PK_NAME", this.metadataCollationIndex, this.metadataEncoding, MysqlType.CHAR, 32)};
        return fields;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public ResultSet getPrimaryKeys(String catalog, String schema, final String table) throws SQLException {
        try {
            if (table == null) {
                throw SQLError.createSQLException(Messages.getString("DatabaseMetaData.2"), "S1009", this.getExceptionInterceptor());
            }
            String db = this.getDatabase(catalog, schema);
            final boolean dbMapsToSchema = this.databaseTerm.getValue() == PropertyDefinitions.DatabaseTerm.SCHEMA;
            final ArrayList rows = new ArrayList();
            try (final Statement stmt = this.conn.getMetadataSafeStatement();){
                new IterateBlock<String>(this.getDatabaseIterator(db)){

                    /*
                     * WARNING - Removed try catching itself - possible behaviour change.
                     */
                    @Override
                    void forEach(String dbStr) throws SQLException {
                        ResultSet rs = null;
                        try {
                            block12: {
                                StringBuilder queryBuf = new StringBuilder("SHOW KEYS FROM ");
                                queryBuf.append(StringUtils.quoteIdentifier(table, DatabaseMetaData.this.quotedId, DatabaseMetaData.this.pedantic));
                                queryBuf.append(" FROM ");
                                queryBuf.append(StringUtils.quoteIdentifier(dbStr, DatabaseMetaData.this.quotedId, DatabaseMetaData.this.pedantic));
                                try {
                                    rs = stmt.executeQuery(queryBuf.toString());
                                }
                                catch (SQLException sqlEx) {
                                    String sqlState = sqlEx.getSQLState();
                                    int errorCode = sqlEx.getErrorCode();
                                    if ("42S02".equals(sqlState) || errorCode == 1146 || errorCode == 1049) break block12;
                                    throw sqlEx;
                                }
                            }
                            TreeMap<String, byte[][]> sortMap = new TreeMap<String, byte[][]>();
                            while (rs != null && rs.next()) {
                                String keyType = rs.getString("Key_name");
                                if (keyType == null || !keyType.equalsIgnoreCase("PRIMARY") && !keyType.equalsIgnoreCase("PRI")) continue;
                                byte[][] tuple = new byte[6][];
                                tuple[0] = dbMapsToSchema ? DatabaseMetaData.this.s2b("def") : DatabaseMetaData.this.s2b(dbStr);
                                tuple[1] = dbMapsToSchema ? DatabaseMetaData.this.s2b(dbStr) : null;
                                tuple[2] = DatabaseMetaData.this.s2b(table);
                                String columnName = rs.getString("Column_name");
                                tuple[3] = DatabaseMetaData.this.s2b(columnName);
                                tuple[4] = DatabaseMetaData.this.s2b(rs.getString("Seq_in_index"));
                                tuple[5] = DatabaseMetaData.this.s2b(keyType);
                                sortMap.put(columnName, tuple);
                            }
                            Iterator sortedIterator = sortMap.values().iterator();
                            while (sortedIterator.hasNext()) {
                                rows.add(new ByteArrayRow((byte[][])sortedIterator.next(), DatabaseMetaData.this.getExceptionInterceptor()));
                            }
                        }
                        finally {
                            if (rs != null) {
                                try {
                                    rs.close();
                                }
                                catch (Exception exception) {}
                                rs = null;
                            }
                        }
                    }
                }.doForAll();
            }
            ResultSetImpl results = this.resultSetFactory.createFromResultsetRows(1007, 1004, new ResultsetRowsStatic(rows, new DefaultColumnDefinition(this.getPrimaryKeysFields())));
            return results;
        }
        catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    @Override
    public ResultSet getProcedureColumns(String catalog, String schemaPattern, String procedureNamePattern, String columnNamePattern) throws SQLException {
        try {
            return this.getProcedureOrFunctionColumns(this.createProcedureColumnsFields(), catalog, schemaPattern, procedureNamePattern, columnNamePattern, true, this.conn.getPropertySet().getBooleanProperty(PropertyKey.getProceduresReturnsFunctions).getValue());
        }
        catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    protected Field[] createProcedureColumnsFields() {
        Field[] fields = new Field[]{new Field("", "PROCEDURE_CAT", this.metadataCollationIndex, this.metadataEncoding, MysqlType.CHAR, 512), new Field("", "PROCEDURE_SCHEM", this.metadataCollationIndex, this.metadataEncoding, MysqlType.CHAR, 512), new Field("", "PROCEDURE_NAME", this.metadataCollationIndex, this.metadataEncoding, MysqlType.CHAR, 512), new Field("", "COLUMN_NAME", this.metadataCollationIndex, this.metadataEncoding, MysqlType.CHAR, 512), new Field("", "COLUMN_TYPE", this.metadataCollationIndex, this.metadataEncoding, MysqlType.CHAR, 64), new Field("", "DATA_TYPE", this.metadataCollationIndex, this.metadataEncoding, MysqlType.SMALLINT, 6), new Field("", "TYPE_NAME", this.metadataCollationIndex, this.metadataEncoding, MysqlType.CHAR, 64), new Field("", "PRECISION", this.metadataCollationIndex, this.metadataEncoding, MysqlType.INT, 12), new Field("", "LENGTH", this.metadataCollationIndex, this.metadataEncoding, MysqlType.INT, 12), new Field("", "SCALE", this.metadataCollationIndex, this.metadataEncoding, MysqlType.SMALLINT, 12), new Field("", "RADIX", this.metadataCollationIndex, this.metadataEncoding, MysqlType.SMALLINT, 6), new Field("", "NULLABLE", this.metadataCollationIndex, this.metadataEncoding, MysqlType.SMALLINT, 6), new Field("", "REMARKS", this.metadataCollationIndex, this.metadataEncoding, MysqlType.CHAR, 512), new Field("", "COLUMN_DEF", this.metadataCollationIndex, this.metadataEncoding, MysqlType.CHAR, 512), new Field("", "SQL_DATA_TYPE", this.metadataCollationIndex, this.metadataEncoding, MysqlType.INT, 12), new Field("", "SQL_DATETIME_SUB", this.metadataCollationIndex, this.metadataEncoding, MysqlType.INT, 12), new Field("", "CHAR_OCTET_LENGTH", this.metadataCollationIndex, this.metadataEncoding, MysqlType.INT, 12), new Field("", "ORDINAL_POSITION", this.metadataCollationIndex, this.metadataEncoding, MysqlType.INT, 12), new Field("", "IS_NULLABLE", this.metadataCollationIndex, this.metadataEncoding, MysqlType.CHAR, 512), new Field("", "SPECIFIC_NAME", this.metadataCollationIndex, this.metadataEncoding, MysqlType.CHAR, 512)};
        return fields;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    protected ResultSet getProcedureOrFunctionColumns(Field[] fields, String catalog, String schemaPattern, String procedureOrFunctionNamePattern, String columnNamePattern, boolean returnProcedures, boolean returnFunctions) throws SQLException {
        String db = this.getDatabase(catalog, schemaPattern);
        boolean dbMapsToSchema = this.databaseTerm.getValue() == PropertyDefinitions.DatabaseTerm.SCHEMA;
        ArrayList<ComparableWrapper<String, ProcedureType>> procsOrFuncsToExtractList = new ArrayList<ComparableWrapper<String, ProcedureType>>();
        ResultSet procsAndOrFuncsRs = null;
        try {
            String tmpProcedureOrFunctionNamePattern = null;
            if (procedureOrFunctionNamePattern != null && !procedureOrFunctionNamePattern.equals("%")) {
                tmpProcedureOrFunctionNamePattern = StringUtils.sanitizeProcOrFuncName(procedureOrFunctionNamePattern);
            }
            if (tmpProcedureOrFunctionNamePattern == null) {
                tmpProcedureOrFunctionNamePattern = procedureOrFunctionNamePattern;
            } else {
                String tmpDb = db;
                List<String> parseList = StringUtils.splitDBdotName(tmpProcedureOrFunctionNamePattern, tmpDb, this.quotedId, this.session.getServerSession().isNoBackslashEscapesSet());
                if (parseList.size() == 2) {
                    tmpDb = parseList.get(0);
                    tmpProcedureOrFunctionNamePattern = parseList.get(1);
                }
            }
            procsAndOrFuncsRs = this.getProceduresAndOrFunctions(this.createFieldMetadataForGetProcedures(), catalog, schemaPattern, tmpProcedureOrFunctionNamePattern, returnProcedures, returnFunctions);
            boolean hasResults = false;
            while (procsAndOrFuncsRs.next()) {
                procsOrFuncsToExtractList.add(new ComparableWrapper<String, ProcedureType>(StringUtils.getFullyQualifiedName(dbMapsToSchema ? procsAndOrFuncsRs.getString(2) : procsAndOrFuncsRs.getString(1), procsAndOrFuncsRs.getString(3), this.quotedId, this.pedantic), procsAndOrFuncsRs.getShort(8) == 1 ? ProcedureType.PROCEDURE : ProcedureType.FUNCTION));
                hasResults = true;
            }
            if (!hasResults) {
            } else {
                Collections.sort(procsOrFuncsToExtractList);
            }
        }
        finally {
            SQLException rethrowSqlEx = null;
            if (procsAndOrFuncsRs != null) {
                try {
                    procsAndOrFuncsRs.close();
                }
                catch (SQLException sqlEx) {
                    rethrowSqlEx = sqlEx;
                }
            }
            if (rethrowSqlEx != null) {
                throw rethrowSqlEx;
            }
        }
        ArrayList<Row> resultRows = new ArrayList<Row>();
        int idx = 0;
        String procNameToCall = "";
        for (ComparableWrapper comparableWrapper : procsOrFuncsToExtractList) {
            String procName = (String)comparableWrapper.getKey();
            ProcedureType procType = (ProcedureType)((Object)comparableWrapper.getValue());
            idx = !" ".equals(this.quotedId) ? StringUtils.indexOfIgnoreCase(0, procName, ".", this.quotedId, this.quotedId, this.session.getServerSession().isNoBackslashEscapesSet() ? StringUtils.SEARCH_MODE__MRK_COM_WS : StringUtils.SEARCH_MODE__ALL) : procName.indexOf(".");
            if (idx > 0) {
                db = StringUtils.unQuoteIdentifier(procName.substring(0, idx), this.quotedId);
                procNameToCall = procName;
            } else {
                procNameToCall = procName;
            }
            this.getCallStmtParameterTypes(db, procNameToCall, procType, columnNamePattern, resultRows, fields.length == 17);
        }
        return this.resultSetFactory.createFromResultsetRows(1007, 1004, new ResultsetRowsStatic(resultRows, new DefaultColumnDefinition(fields)));
    }

    @Override
    public ResultSet getProcedures(String catalog, String schemaPattern, String procedureNamePattern) throws SQLException {
        try {
            return this.getProceduresAndOrFunctions(this.createFieldMetadataForGetProcedures(), catalog, schemaPattern, procedureNamePattern, true, this.conn.getPropertySet().getBooleanProperty(PropertyKey.getProceduresReturnsFunctions).getValue());
        }
        catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    protected Field[] createFieldMetadataForGetProcedures() {
        Field[] fields = new Field[]{new Field("", "PROCEDURE_CAT", this.metadataCollationIndex, this.metadataEncoding, MysqlType.CHAR, 255), new Field("", "PROCEDURE_SCHEM", this.metadataCollationIndex, this.metadataEncoding, MysqlType.CHAR, 255), new Field("", "PROCEDURE_NAME", this.metadataCollationIndex, this.metadataEncoding, MysqlType.CHAR, 255), new Field("", "reserved1", this.metadataCollationIndex, this.metadataEncoding, MysqlType.CHAR, 0), new Field("", "reserved2", this.metadataCollationIndex, this.metadataEncoding, MysqlType.CHAR, 0), new Field("", "reserved3", this.metadataCollationIndex, this.metadataEncoding, MysqlType.CHAR, 0), new Field("", "REMARKS", this.metadataCollationIndex, this.metadataEncoding, MysqlType.CHAR, 255), new Field("", "PROCEDURE_TYPE", this.metadataCollationIndex, this.metadataEncoding, MysqlType.SMALLINT, 6), new Field("", "SPECIFIC_NAME", this.metadataCollationIndex, this.metadataEncoding, MysqlType.CHAR, 255)};
        return fields;
    }

    protected ResultSet getProceduresAndOrFunctions(final Field[] fields, String catalog, String schemaPattern, final String procedureNamePattern, final boolean returnProcedures, final boolean returnFunctions) throws SQLException {
        ArrayList procedureRows = new ArrayList();
        String db = this.getDatabase(catalog, schemaPattern);
        final boolean dbMapsToSchema = this.databaseTerm.getValue() == PropertyDefinitions.DatabaseTerm.SCHEMA;
        final ArrayList procedureRowsToSort = new ArrayList();
        new IterateBlock<String>(dbMapsToSchema ? this.getSchemaPatternIterator(db) : this.getDatabaseIterator(db)){

            /*
             * WARNING - Removed try catching itself - possible behaviour change.
             */
            @Override
            void forEach(String dbPattern) throws SQLException {
                ResultSet proceduresRs = null;
                StringBuilder selectFromMySQLProcSQL = new StringBuilder();
                selectFromMySQLProcSQL.append("SELECT db, name, type, comment FROM mysql.proc WHERE");
                if (returnProcedures && !returnFunctions) {
                    selectFromMySQLProcSQL.append(" type = 'PROCEDURE' AND ");
                } else if (!returnProcedures && returnFunctions) {
                    selectFromMySQLProcSQL.append(" type = 'FUNCTION' AND ");
                }
                selectFromMySQLProcSQL.append(dbMapsToSchema ? " db LIKE ?" : " db = ?");
                if (procedureNamePattern != null && procedureNamePattern.length() > 0) {
                    selectFromMySQLProcSQL.append(" AND name LIKE ?");
                }
                selectFromMySQLProcSQL.append(" ORDER BY name, type");
                PreparedStatement proceduresStmt = DatabaseMetaData.this.prepareMetaDataSafeStatement(selectFromMySQLProcSQL.toString());
                try {
                    if (DatabaseMetaData.this.conn.lowerCaseTableNames()) {
                        dbPattern = dbPattern.toLowerCase();
                    }
                    proceduresStmt.setString(1, dbPattern);
                    if (procedureNamePattern != null && procedureNamePattern.length() > 0) {
                        proceduresStmt.setString(2, procedureNamePattern);
                    }
                    try {
                        proceduresRs = proceduresStmt.executeQuery();
                        if (returnProcedures) {
                            DatabaseMetaData.this.convertToJdbcProcedureList(true, proceduresRs, procedureRowsToSort);
                        }
                        if (returnFunctions) {
                            DatabaseMetaData.this.convertToJdbcFunctionList(proceduresRs, procedureRowsToSort, fields);
                        }
                    }
                    catch (SQLException sqlEx) {
                        String sql;
                        if (returnFunctions) {
                            proceduresStmt.close();
                            sql = "SHOW FUNCTION STATUS WHERE " + (dbMapsToSchema ? "Db LIKE ?" : "Db = ?");
                            if (procedureNamePattern != null && procedureNamePattern.length() > 0) {
                                sql = sql + " AND Name LIKE ?";
                            }
                            proceduresStmt = DatabaseMetaData.this.prepareMetaDataSafeStatement(sql);
                            proceduresStmt.setString(1, dbPattern);
                            if (procedureNamePattern != null && procedureNamePattern.length() > 0) {
                                proceduresStmt.setString(2, procedureNamePattern);
                            }
                            proceduresRs = proceduresStmt.executeQuery();
                            DatabaseMetaData.this.convertToJdbcFunctionList(proceduresRs, procedureRowsToSort, fields);
                        }
                        if (returnProcedures) {
                            proceduresStmt.close();
                            sql = "SHOW PROCEDURE STATUS WHERE " + (dbMapsToSchema ? "Db LIKE ?" : "Db = ?");
                            if (procedureNamePattern != null && procedureNamePattern.length() > 0) {
                                sql = sql + " AND Name LIKE ?";
                            }
                            proceduresStmt = DatabaseMetaData.this.prepareMetaDataSafeStatement(sql);
                            proceduresStmt.setString(1, dbPattern);
                            if (procedureNamePattern != null && procedureNamePattern.length() > 0) {
                                proceduresStmt.setString(2, procedureNamePattern);
                            }
                            proceduresRs = proceduresStmt.executeQuery();
                            DatabaseMetaData.this.convertToJdbcProcedureList(false, proceduresRs, procedureRowsToSort);
                        }
                    }
                }
                finally {
                    SQLException rethrowSqlEx = null;
                    if (proceduresRs != null) {
                        try {
                            proceduresRs.close();
                        }
                        catch (SQLException sqlEx) {
                            rethrowSqlEx = sqlEx;
                        }
                    }
                    if (proceduresStmt != null) {
                        try {
                            proceduresStmt.close();
                        }
                        catch (SQLException sqlEx) {
                            rethrowSqlEx = sqlEx;
                        }
                    }
                    if (rethrowSqlEx != null) {
                        throw rethrowSqlEx;
                    }
                }
            }
        }.doForAll();
        Collections.sort(procedureRowsToSort);
        for (ComparableWrapper procRow : procedureRowsToSort) {
            procedureRows.add(procRow.getValue());
        }
        return this.resultSetFactory.createFromResultsetRows(1007, 1004, new ResultsetRowsStatic(procedureRows, new DefaultColumnDefinition(fields)));
    }

    @Override
    public String getProcedureTerm() throws SQLException {
        try {
            return "PROCEDURE";
        }
        catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    @Override
    public int getResultSetHoldability() throws SQLException {
        try {
            return 1;
        }
        catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    void populateKeyResults(String db, String table, String keysComment, List<Row> resultRows, String fkTableName, boolean isExport) throws SQLException {
        boolean dbMapsToSchema;
        LocalAndReferencedColumns parsedInfo = this.parseTableStatusIntoLocalAndReferencedColumns(keysComment);
        if (isExport && !parsedInfo.referencedTable.equals(table)) {
            return;
        }
        if (parsedInfo.localColumnsList.size() != parsedInfo.referencedColumnsList.size()) {
            throw SQLError.createSQLException(Messages.getString("DatabaseMetaData.12"), "S1000", this.getExceptionInterceptor());
        }
        Iterator<String> localColumnNames = parsedInfo.localColumnsList.iterator();
        Iterator<String> referColumnNames = parsedInfo.referencedColumnsList.iterator();
        int keySeqIndex = 1;
        boolean bl = dbMapsToSchema = this.databaseTerm.getValue() == PropertyDefinitions.DatabaseTerm.SCHEMA;
        while (localColumnNames.hasNext()) {
            byte[][] tuple = new byte[14][];
            String lColumnName = StringUtils.unQuoteIdentifier(localColumnNames.next(), this.quotedId);
            String rColumnName = StringUtils.unQuoteIdentifier(referColumnNames.next(), this.quotedId);
            tuple[0] = dbMapsToSchema ? this.s2b("def") : this.s2b(parsedInfo.referencedDatabase);
            tuple[1] = dbMapsToSchema ? this.s2b(parsedInfo.referencedDatabase) : null;
            tuple[2] = this.s2b(isExport ? table : parsedInfo.referencedTable);
            tuple[3] = this.s2b(rColumnName);
            tuple[4] = dbMapsToSchema ? this.s2b("def") : this.s2b(db);
            tuple[5] = dbMapsToSchema ? this.s2b(db) : null;
            tuple[6] = this.s2b(isExport ? fkTableName : table);
            tuple[7] = this.s2b(lColumnName);
            tuple[8] = this.s2b(Integer.toString(keySeqIndex++));
            int[] actions = this.getForeignKeyActions(keysComment);
            tuple[9] = this.s2b(Integer.toString(actions[1]));
            tuple[10] = this.s2b(Integer.toString(actions[0]));
            tuple[11] = this.s2b(parsedInfo.constraintName);
            tuple[12] = null;
            tuple[13] = this.s2b(Integer.toString(7));
            resultRows.add(new ByteArrayRow(tuple, this.getExceptionInterceptor()));
        }
    }

    @Override
    public ResultSet getSchemas() throws SQLException {
        try {
            return this.getSchemas(null, null);
        }
        catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    @Override
    public ResultSet getSchemas(String catalog, String schemaPattern) throws SQLException {
        try {
            List<Object> dbList = this.databaseTerm.getValue() == PropertyDefinitions.DatabaseTerm.SCHEMA ? this.getDatabases(schemaPattern) : new ArrayList();
            Field[] fields = new Field[]{new Field("", "TABLE_SCHEM", this.metadataCollationIndex, this.metadataEncoding, MysqlType.CHAR, 0), new Field("", "TABLE_CATALOG", this.metadataCollationIndex, this.metadataEncoding, MysqlType.CHAR, 0)};
            ArrayList<ByteArrayRow> tuples = new ArrayList<ByteArrayRow>(dbList.size());
            for (String string : dbList) {
                byte[][] rowVal = new byte[][]{this.s2b(string), this.s2b("def")};
                tuples.add(new ByteArrayRow(rowVal, this.getExceptionInterceptor()));
            }
            return this.resultSetFactory.createFromResultsetRows(1007, 1004, new ResultsetRowsStatic(tuples, new DefaultColumnDefinition(fields)));
        }
        catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    @Override
    public String getSchemaTerm() throws SQLException {
        try {
            return this.databaseTerm.getValue() == PropertyDefinitions.DatabaseTerm.SCHEMA ? "SCHEMA" : "";
        }
        catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    @Override
    public String getSearchStringEscape() throws SQLException {
        try {
            return "\\";
        }
        catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public String getSQLKeywords() throws SQLException {
        try {
            if (mysqlKeywords != null) {
                return mysqlKeywords;
            }
            Class<DatabaseMetaData> class_ = DatabaseMetaData.class;
            synchronized (DatabaseMetaData.class) {
                if (mysqlKeywords != null) {
                    // ** MonitorExit[var1_1] (shouldn't be in output)
                    return mysqlKeywords;
                }
                TreeSet mysqlKeywordSet = new TreeSet();
                StringBuilder mysqlKeywordsBuffer = new StringBuilder();
                Collections.addAll(mysqlKeywordSet, MYSQL_KEYWORDS);
                mysqlKeywordSet.removeAll(SQL2003_KEYWORDS);
                for (String keyword : mysqlKeywordSet) {
                    mysqlKeywordsBuffer.append(",").append(keyword);
                }
                mysqlKeywords = mysqlKeywordsBuffer.substring(1);
                // ** MonitorExit[var1_1] (shouldn't be in output)
                return mysqlKeywords;
            }
        }
        catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    @Override
    public int getSQLStateType() throws SQLException {
        try {
            return 2;
        }
        catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    @Override
    public String getStringFunctions() throws SQLException {
        try {
            return "ASCII,BIN,BIT_LENGTH,CHAR,CHARACTER_LENGTH,CHAR_LENGTH,CONCAT,CONCAT_WS,CONV,ELT,EXPORT_SET,FIELD,FIND_IN_SET,HEX,INSERT,INSTR,LCASE,LEFT,LENGTH,LOAD_FILE,LOCATE,LOCATE,LOWER,LPAD,LTRIM,MAKE_SET,MATCH,MID,OCT,OCTET_LENGTH,ORD,POSITION,QUOTE,REPEAT,REPLACE,REVERSE,RIGHT,RPAD,RTRIM,SOUNDEX,SPACE,STRCMP,SUBSTRING,SUBSTRING,SUBSTRING,SUBSTRING,SUBSTRING_INDEX,TRIM,UCASE,UPPER";
        }
        catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    @Override
    public ResultSet getSuperTables(String arg0, String arg1, String arg2) throws SQLException {
        try {
            Field[] fields = new Field[]{new Field("", "TABLE_CAT", this.metadataCollationIndex, this.metadataEncoding, MysqlType.CHAR, 32), new Field("", "TABLE_SCHEM", this.metadataCollationIndex, this.metadataEncoding, MysqlType.CHAR, 32), new Field("", "TABLE_NAME", this.metadataCollationIndex, this.metadataEncoding, MysqlType.CHAR, 32), new Field("", "SUPERTABLE_NAME", this.metadataCollationIndex, this.metadataEncoding, MysqlType.CHAR, 32)};
            return this.resultSetFactory.createFromResultsetRows(1007, 1004, new ResultsetRowsStatic(new ArrayList(), new DefaultColumnDefinition(fields)));
        }
        catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    @Override
    public ResultSet getSuperTypes(String arg0, String arg1, String arg2) throws SQLException {
        try {
            Field[] fields = new Field[]{new Field("", "TYPE_CAT", this.metadataCollationIndex, this.metadataEncoding, MysqlType.CHAR, 32), new Field("", "TYPE_SCHEM", this.metadataCollationIndex, this.metadataEncoding, MysqlType.CHAR, 32), new Field("", "TYPE_NAME", this.metadataCollationIndex, this.metadataEncoding, MysqlType.CHAR, 32), new Field("", "SUPERTYPE_CAT", this.metadataCollationIndex, this.metadataEncoding, MysqlType.CHAR, 32), new Field("", "SUPERTYPE_SCHEM", this.metadataCollationIndex, this.metadataEncoding, MysqlType.CHAR, 32), new Field("", "SUPERTYPE_NAME", this.metadataCollationIndex, this.metadataEncoding, MysqlType.CHAR, 32)};
            return this.resultSetFactory.createFromResultsetRows(1007, 1004, new ResultsetRowsStatic(new ArrayList(), new DefaultColumnDefinition(fields)));
        }
        catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    @Override
    public String getSystemFunctions() throws SQLException {
        try {
            return "DATABASE,USER,SYSTEM_USER,SESSION_USER,PASSWORD,ENCRYPT,LAST_INSERT_ID,VERSION";
        }
        catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    protected String getTableNameWithCase(String table) {
        String tableNameWithCase = this.conn.lowerCaseTableNames() ? table.toLowerCase() : table;
        return tableNameWithCase;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public ResultSet getTablePrivileges(String catalog, String schemaPattern, String tableNamePattern) throws SQLException {
        try {
            Field[] fields = new Field[]{new Field("", "TABLE_CAT", this.metadataCollationIndex, this.metadataEncoding, MysqlType.CHAR, 64), new Field("", "TABLE_SCHEM", this.metadataCollationIndex, this.metadataEncoding, MysqlType.CHAR, 1), new Field("", "TABLE_NAME", this.metadataCollationIndex, this.metadataEncoding, MysqlType.CHAR, 64), new Field("", "GRANTOR", this.metadataCollationIndex, this.metadataEncoding, MysqlType.CHAR, 77), new Field("", "GRANTEE", this.metadataCollationIndex, this.metadataEncoding, MysqlType.CHAR, 77), new Field("", "PRIVILEGE", this.metadataCollationIndex, this.metadataEncoding, MysqlType.CHAR, 64), new Field("", "IS_GRANTABLE", this.metadataCollationIndex, this.metadataEncoding, MysqlType.CHAR, 3)};
            String dbPattern = this.getDatabase(catalog, schemaPattern);
            StringBuilder grantQueryBuf = new StringBuilder("SELECT host,db,table_name,grantor,user,table_priv FROM mysql.tables_priv");
            StringBuilder conditionBuf = new StringBuilder();
            if (dbPattern != null) {
                conditionBuf.append(this.databaseTerm.getValue() == PropertyDefinitions.DatabaseTerm.SCHEMA ? " db LIKE ?" : " db = ?");
            }
            if (tableNamePattern != null) {
                if (conditionBuf.length() > 0) {
                    conditionBuf.append(" AND");
                }
                conditionBuf.append(" table_name LIKE ?");
            }
            if (conditionBuf.length() > 0) {
                grantQueryBuf.append(" WHERE");
                grantQueryBuf.append((CharSequence)conditionBuf);
            }
            ResultSet results = null;
            ArrayList<ByteArrayRow> grantRows = new ArrayList<ByteArrayRow>();
            PreparedStatement pStmt = null;
            try {
                pStmt = this.prepareMetaDataSafeStatement(grantQueryBuf.toString());
                int nextId = 1;
                if (dbPattern != null) {
                    pStmt.setString(nextId++, dbPattern);
                }
                if (tableNamePattern != null) {
                    pStmt.setString(nextId, tableNamePattern);
                }
                results = pStmt.executeQuery();
                while (results.next()) {
                    String allPrivileges;
                    String host = results.getString(1);
                    String db = results.getString(2);
                    String table = results.getString(3);
                    String grantor = results.getString(4);
                    String user = results.getString(5);
                    if (user == null || user.length() == 0) {
                        user = "%";
                    }
                    StringBuilder fullUser = new StringBuilder(user);
                    if (host != null && this.useHostsInPrivileges) {
                        fullUser.append("@");
                        fullUser.append(host);
                    }
                    if ((allPrivileges = results.getString(6)) == null) continue;
                    allPrivileges = allPrivileges.toUpperCase(Locale.ENGLISH);
                    StringTokenizer st = new StringTokenizer(allPrivileges, ",");
                    while (st.hasMoreTokens()) {
                        String privilege = st.nextToken().trim();
                        ResultSet columnResults = null;
                        try {
                            columnResults = this.getColumns(catalog, schemaPattern, table, null);
                            while (columnResults.next()) {
                                byte[][] tuple = new byte[8][];
                                tuple[0] = this.databaseTerm.getValue() == PropertyDefinitions.DatabaseTerm.SCHEMA ? this.s2b("def") : this.s2b(db);
                                tuple[1] = this.databaseTerm.getValue() == PropertyDefinitions.DatabaseTerm.SCHEMA ? this.s2b(db) : null;
                                tuple[2] = this.s2b(table);
                                tuple[3] = grantor != null ? this.s2b(grantor) : null;
                                tuple[4] = this.s2b(fullUser.toString());
                                tuple[5] = this.s2b(privilege);
                                tuple[6] = null;
                                grantRows.add(new ByteArrayRow(tuple, this.getExceptionInterceptor()));
                            }
                        }
                        finally {
                            if (columnResults == null) continue;
                            try {
                                columnResults.close();
                            }
                            catch (Exception exception) {}
                        }
                    }
                }
            }
            finally {
                if (results != null) {
                    try {
                        results.close();
                    }
                    catch (Exception exception) {}
                    results = null;
                }
                if (pStmt != null) {
                    try {
                        pStmt.close();
                    }
                    catch (Exception exception) {}
                    pStmt = null;
                }
            }
            return this.resultSetFactory.createFromResultsetRows(1007, 1004, new ResultsetRowsStatic(grantRows, new DefaultColumnDefinition(fields)));
        }
        catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public ResultSet getTables(String catalog, String schemaPattern, String tableNamePattern, final String[] types) throws SQLException {
        try {
            List<String> parseList;
            boolean dbMapsToSchema;
            final TreeMap sortedRows = new TreeMap();
            ArrayList tuples = new ArrayList();
            final Statement stmt = this.conn.getMetadataSafeStatement();
            String db = this.getDatabase(catalog, schemaPattern);
            boolean bl = dbMapsToSchema = this.databaseTerm.getValue() == PropertyDefinitions.DatabaseTerm.SCHEMA;
            if (tableNamePattern != null && (parseList = StringUtils.splitDBdotName(tableNamePattern, db, this.quotedId, this.session.getServerSession().isNoBackslashEscapesSet())).size() == 2) {
                tableNamePattern = parseList.get(1);
            }
            final String tableNamePat = tableNamePattern;
            try {
                new IterateBlock<String>(dbMapsToSchema ? this.getSchemaPatternIterator(db) : this.getDatabaseIterator(db)){

                    /*
                     * WARNING - Removed try catching itself - possible behaviour change.
                     */
                    @Override
                    void forEach(String dbPattern) throws SQLException {
                        boolean operatingOnSystemDB = "information_schema".equalsIgnoreCase(dbPattern) || "mysql".equalsIgnoreCase(dbPattern) || "performance_schema".equalsIgnoreCase(dbPattern);
                        ResultSet results = null;
                        try {
                            try {
                                StringBuilder sqlBuf = new StringBuilder("SHOW FULL TABLES FROM ");
                                sqlBuf.append(StringUtils.quoteIdentifier(dbPattern, DatabaseMetaData.this.quotedId, DatabaseMetaData.this.pedantic));
                                if (tableNamePat != null) {
                                    sqlBuf.append(" LIKE ");
                                    sqlBuf.append(StringUtils.quoteIdentifier(tableNamePat, "'", true));
                                }
                                results = stmt.executeQuery(sqlBuf.toString());
                            }
                            catch (SQLException sqlEx) {
                                if ("08S01".equals(sqlEx.getSQLState())) {
                                    throw sqlEx;
                                }
                                if (results != null) {
                                    try {
                                        results.close();
                                    }
                                    catch (Exception exception) {
                                        // empty catch block
                                    }
                                    results = null;
                                }
                                return;
                            }
                            boolean shouldReportTables = false;
                            boolean shouldReportViews = false;
                            boolean shouldReportSystemTables = false;
                            boolean shouldReportSystemViews = false;
                            boolean shouldReportLocalTemporaries = false;
                            if (types == null || types.length == 0) {
                                shouldReportTables = true;
                                shouldReportViews = true;
                                shouldReportSystemTables = true;
                                shouldReportSystemViews = true;
                                shouldReportLocalTemporaries = true;
                            } else {
                                for (int i = 0; i < types.length; ++i) {
                                    if (TableType.TABLE.equalsTo(types[i])) {
                                        shouldReportTables = true;
                                        continue;
                                    }
                                    if (TableType.VIEW.equalsTo(types[i])) {
                                        shouldReportViews = true;
                                        continue;
                                    }
                                    if (TableType.SYSTEM_TABLE.equalsTo(types[i])) {
                                        shouldReportSystemTables = true;
                                        continue;
                                    }
                                    if (TableType.SYSTEM_VIEW.equalsTo(types[i])) {
                                        shouldReportSystemViews = true;
                                        continue;
                                    }
                                    if (!TableType.LOCAL_TEMPORARY.equalsTo(types[i])) continue;
                                    shouldReportLocalTemporaries = true;
                                }
                            }
                            int typeColumnIndex = 0;
                            boolean hasTableTypes = false;
                            try {
                                typeColumnIndex = results.findColumn("table_type");
                                hasTableTypes = true;
                            }
                            catch (SQLException sqlEx) {
                                try {
                                    typeColumnIndex = results.findColumn("Type");
                                    hasTableTypes = true;
                                }
                                catch (SQLException sqlEx2) {
                                    hasTableTypes = false;
                                }
                            }
                            block24: while (results.next()) {
                                byte[][] row = new byte[10][];
                                row[0] = dbMapsToSchema ? DatabaseMetaData.this.s2b("def") : DatabaseMetaData.this.s2b(dbPattern);
                                row[1] = dbMapsToSchema ? DatabaseMetaData.this.s2b(dbPattern) : null;
                                row[2] = results.getBytes(1);
                                row[4] = new byte[0];
                                row[5] = null;
                                row[6] = null;
                                row[7] = null;
                                row[8] = null;
                                row[9] = null;
                                if (hasTableTypes) {
                                    String tableType = results.getString(typeColumnIndex);
                                    switch (TableType.getTableTypeCompliantWith(tableType)) {
                                        case TABLE: {
                                            boolean reportTable = false;
                                            TableMetaDataKey tablesKey = null;
                                            if (operatingOnSystemDB && shouldReportSystemTables) {
                                                row[3] = TableType.SYSTEM_TABLE.asBytes();
                                                tablesKey = new TableMetaDataKey(TableType.SYSTEM_TABLE.getName(), dbPattern, null, results.getString(1));
                                                reportTable = true;
                                            } else if (!operatingOnSystemDB && shouldReportTables) {
                                                row[3] = TableType.TABLE.asBytes();
                                                tablesKey = new TableMetaDataKey(TableType.TABLE.getName(), dbPattern, null, results.getString(1));
                                                reportTable = true;
                                            }
                                            if (!reportTable) continue block24;
                                            sortedRows.put(tablesKey, new ByteArrayRow(row, DatabaseMetaData.this.getExceptionInterceptor()));
                                            break;
                                        }
                                        case VIEW: {
                                            if (!shouldReportViews) continue block24;
                                            row[3] = TableType.VIEW.asBytes();
                                            sortedRows.put(new TableMetaDataKey(TableType.VIEW.getName(), dbPattern, null, results.getString(1)), new ByteArrayRow(row, DatabaseMetaData.this.getExceptionInterceptor()));
                                            break;
                                        }
                                        case SYSTEM_TABLE: {
                                            if (!shouldReportSystemTables) continue block24;
                                            row[3] = TableType.SYSTEM_TABLE.asBytes();
                                            sortedRows.put(new TableMetaDataKey(TableType.SYSTEM_TABLE.getName(), dbPattern, null, results.getString(1)), new ByteArrayRow(row, DatabaseMetaData.this.getExceptionInterceptor()));
                                            break;
                                        }
                                        case SYSTEM_VIEW: {
                                            if (!shouldReportSystemViews) continue block24;
                                            row[3] = TableType.SYSTEM_VIEW.asBytes();
                                            sortedRows.put(new TableMetaDataKey(TableType.SYSTEM_VIEW.getName(), dbPattern, null, results.getString(1)), new ByteArrayRow(row, DatabaseMetaData.this.getExceptionInterceptor()));
                                            break;
                                        }
                                        case LOCAL_TEMPORARY: {
                                            if (!shouldReportLocalTemporaries) continue block24;
                                            row[3] = TableType.LOCAL_TEMPORARY.asBytes();
                                            sortedRows.put(new TableMetaDataKey(TableType.LOCAL_TEMPORARY.getName(), dbPattern, null, results.getString(1)), new ByteArrayRow(row, DatabaseMetaData.this.getExceptionInterceptor()));
                                            break;
                                        }
                                        default: {
                                            row[3] = TableType.TABLE.asBytes();
                                            sortedRows.put(new TableMetaDataKey(TableType.TABLE.getName(), dbPattern, null, results.getString(1)), new ByteArrayRow(row, DatabaseMetaData.this.getExceptionInterceptor()));
                                            break;
                                        }
                                    }
                                    continue;
                                }
                                if (!shouldReportTables) continue;
                                row[3] = TableType.TABLE.asBytes();
                                sortedRows.put(new TableMetaDataKey(TableType.TABLE.getName(), dbPattern, null, results.getString(1)), new ByteArrayRow(row, DatabaseMetaData.this.getExceptionInterceptor()));
                            }
                        }
                        finally {
                            if (results != null) {
                                try {
                                    results.close();
                                }
                                catch (Exception exception) {}
                                results = null;
                            }
                        }
                    }
                }.doForAll();
            }
            finally {
                if (stmt != null) {
                    stmt.close();
                }
            }
            tuples.addAll(sortedRows.values());
            ResultSetImpl tables = this.resultSetFactory.createFromResultsetRows(1007, 1004, new ResultsetRowsStatic(tuples, this.createTablesFields()));
            return tables;
        }
        catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    protected ColumnDefinition createTablesFields() {
        Field[] fields = new Field[]{new Field("", "TABLE_CAT", this.metadataCollationIndex, this.metadataEncoding, MysqlType.VARCHAR, 255), new Field("", "TABLE_SCHEM", this.metadataCollationIndex, this.metadataEncoding, MysqlType.VARCHAR, 0), new Field("", "TABLE_NAME", this.metadataCollationIndex, this.metadataEncoding, MysqlType.VARCHAR, 255), new Field("", "TABLE_TYPE", this.metadataCollationIndex, this.metadataEncoding, MysqlType.VARCHAR, 5), new Field("", "REMARKS", this.metadataCollationIndex, this.metadataEncoding, MysqlType.VARCHAR, 0), new Field("", "TYPE_CAT", this.metadataCollationIndex, this.metadataEncoding, MysqlType.VARCHAR, 0), new Field("", "TYPE_SCHEM", this.metadataCollationIndex, this.metadataEncoding, MysqlType.VARCHAR, 0), new Field("", "TYPE_NAME", this.metadataCollationIndex, this.metadataEncoding, MysqlType.VARCHAR, 0), new Field("", "SELF_REFERENCING_COL_NAME", this.metadataCollationIndex, this.metadataEncoding, MysqlType.VARCHAR, 0), new Field("", "REF_GENERATION", this.metadataCollationIndex, this.metadataEncoding, MysqlType.VARCHAR, 0)};
        return new DefaultColumnDefinition(fields);
    }

    @Override
    public ResultSet getTableTypes() throws SQLException {
        try {
            ArrayList<ByteArrayRow> tuples = new ArrayList<ByteArrayRow>();
            Field[] fields = new Field[]{new Field("", "TABLE_TYPE", this.metadataCollationIndex, this.metadataEncoding, MysqlType.VARCHAR, 256)};
            tuples.add(new ByteArrayRow(new byte[][]{TableType.LOCAL_TEMPORARY.asBytes()}, this.getExceptionInterceptor()));
            tuples.add(new ByteArrayRow(new byte[][]{TableType.SYSTEM_TABLE.asBytes()}, this.getExceptionInterceptor()));
            tuples.add(new ByteArrayRow(new byte[][]{TableType.SYSTEM_VIEW.asBytes()}, this.getExceptionInterceptor()));
            tuples.add(new ByteArrayRow(new byte[][]{TableType.TABLE.asBytes()}, this.getExceptionInterceptor()));
            tuples.add(new ByteArrayRow(new byte[][]{TableType.VIEW.asBytes()}, this.getExceptionInterceptor()));
            return this.resultSetFactory.createFromResultsetRows(1007, 1004, new ResultsetRowsStatic(tuples, new DefaultColumnDefinition(fields)));
        }
        catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    @Override
    public String getTimeDateFunctions() throws SQLException {
        try {
            return "DAYOFWEEK,WEEKDAY,DAYOFMONTH,DAYOFYEAR,MONTH,DAYNAME,MONTHNAME,QUARTER,WEEK,YEAR,HOUR,MINUTE,SECOND,PERIOD_ADD,PERIOD_DIFF,TO_DAYS,FROM_DAYS,DATE_FORMAT,TIME_FORMAT,CURDATE,CURRENT_DATE,CURTIME,CURRENT_TIME,NOW,SYSDATE,CURRENT_TIMESTAMP,UNIX_TIMESTAMP,FROM_UNIXTIME,SEC_TO_TIME,TIME_TO_SEC";
        }
        catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    private byte[][] getTypeInfo(String mysqlTypeName) throws SQLException {
        MysqlType mt = MysqlType.getByName(mysqlTypeName);
        byte[][] rowVal = new byte[18][];
        rowVal[0] = this.s2b(mysqlTypeName);
        rowVal[1] = Integer.toString(mt.getJdbcType()).getBytes();
        rowVal[2] = Integer.toString(mt.getPrecision() > Integer.MAX_VALUE ? Integer.MAX_VALUE : mt.getPrecision().intValue()).getBytes();
        switch (mt) {
            case ENUM: 
            case SET: 
            case CHAR: 
            case VARCHAR: 
            case TINYTEXT: 
            case MEDIUMTEXT: 
            case LONGTEXT: 
            case JSON: 
            case TEXT: 
            case TINYBLOB: 
            case MEDIUMBLOB: 
            case LONGBLOB: 
            case BLOB: 
            case BINARY: 
            case VARBINARY: 
            case DATE: 
            case TIME: 
            case DATETIME: 
            case TIMESTAMP: 
            case GEOMETRY: 
            case UNKNOWN: {
                rowVal[3] = this.s2b("'");
                rowVal[4] = this.s2b("'");
                break;
            }
            default: {
                rowVal[3] = this.s2b("");
                rowVal[4] = this.s2b("");
            }
        }
        rowVal[5] = this.s2b(mt.getCreateParams());
        rowVal[6] = Integer.toString(1).getBytes();
        rowVal[7] = this.s2b("true");
        rowVal[8] = Integer.toString(3).getBytes();
        rowVal[9] = this.s2b(mt.isAllowed(32) ? "true" : "false");
        rowVal[10] = this.s2b("false");
        rowVal[11] = this.s2b("false");
        rowVal[12] = this.s2b(mt.getName());
        switch (mt) {
            case DECIMAL: 
            case DECIMAL_UNSIGNED: 
            case DOUBLE: 
            case DOUBLE_UNSIGNED: {
                rowVal[13] = this.s2b("-308");
                rowVal[14] = this.s2b("308");
                break;
            }
            case FLOAT: 
            case FLOAT_UNSIGNED: {
                rowVal[13] = this.s2b("-38");
                rowVal[14] = this.s2b("38");
                break;
            }
            default: {
                rowVal[13] = this.s2b("0");
                rowVal[14] = this.s2b("0");
            }
        }
        rowVal[15] = this.s2b("0");
        rowVal[16] = this.s2b("0");
        rowVal[17] = this.s2b("10");
        return rowVal;
    }

    @Override
    public ResultSet getTypeInfo() throws SQLException {
        try {
            Field[] fields = new Field[]{new Field("", "TYPE_NAME", this.metadataCollationIndex, this.metadataEncoding, MysqlType.CHAR, 32), new Field("", "DATA_TYPE", this.metadataCollationIndex, this.metadataEncoding, MysqlType.INT, 5), new Field("", "PRECISION", this.metadataCollationIndex, this.metadataEncoding, MysqlType.INT, 10), new Field("", "LITERAL_PREFIX", this.metadataCollationIndex, this.metadataEncoding, MysqlType.CHAR, 4), new Field("", "LITERAL_SUFFIX", this.metadataCollationIndex, this.metadataEncoding, MysqlType.CHAR, 4), new Field("", "CREATE_PARAMS", this.metadataCollationIndex, this.metadataEncoding, MysqlType.CHAR, 32), new Field("", "NULLABLE", this.metadataCollationIndex, this.metadataEncoding, MysqlType.SMALLINT, 5), new Field("", "CASE_SENSITIVE", this.metadataCollationIndex, this.metadataEncoding, MysqlType.BOOLEAN, 3), new Field("", "SEARCHABLE", this.metadataCollationIndex, this.metadataEncoding, MysqlType.SMALLINT, 3), new Field("", "UNSIGNED_ATTRIBUTE", this.metadataCollationIndex, this.metadataEncoding, MysqlType.BOOLEAN, 3), new Field("", "FIXED_PREC_SCALE", this.metadataCollationIndex, this.metadataEncoding, MysqlType.BOOLEAN, 3), new Field("", "AUTO_INCREMENT", this.metadataCollationIndex, this.metadataEncoding, MysqlType.BOOLEAN, 3), new Field("", "LOCAL_TYPE_NAME", this.metadataCollationIndex, this.metadataEncoding, MysqlType.CHAR, 32), new Field("", "MINIMUM_SCALE", this.metadataCollationIndex, this.metadataEncoding, MysqlType.SMALLINT, 5), new Field("", "MAXIMUM_SCALE", this.metadataCollationIndex, this.metadataEncoding, MysqlType.SMALLINT, 5), new Field("", "SQL_DATA_TYPE", this.metadataCollationIndex, this.metadataEncoding, MysqlType.INT, 10), new Field("", "SQL_DATETIME_SUB", this.metadataCollationIndex, this.metadataEncoding, MysqlType.INT, 10), new Field("", "NUM_PREC_RADIX", this.metadataCollationIndex, this.metadataEncoding, MysqlType.INT, 10)};
            ArrayList<ByteArrayRow> tuples = new ArrayList<ByteArrayRow>();
            tuples.add(new ByteArrayRow(this.getTypeInfo("BIT"), this.getExceptionInterceptor()));
            tuples.add(new ByteArrayRow(this.getTypeInfo("BOOL"), this.getExceptionInterceptor()));
            tuples.add(new ByteArrayRow(this.getTypeInfo("TINYINT"), this.getExceptionInterceptor()));
            tuples.add(new ByteArrayRow(this.getTypeInfo("TINYINT UNSIGNED"), this.getExceptionInterceptor()));
            tuples.add(new ByteArrayRow(this.getTypeInfo("BIGINT"), this.getExceptionInterceptor()));
            tuples.add(new ByteArrayRow(this.getTypeInfo("BIGINT UNSIGNED"), this.getExceptionInterceptor()));
            tuples.add(new ByteArrayRow(this.getTypeInfo("LONG VARBINARY"), this.getExceptionInterceptor()));
            tuples.add(new ByteArrayRow(this.getTypeInfo("MEDIUMBLOB"), this.getExceptionInterceptor()));
            tuples.add(new ByteArrayRow(this.getTypeInfo("LONGBLOB"), this.getExceptionInterceptor()));
            tuples.add(new ByteArrayRow(this.getTypeInfo("BLOB"), this.getExceptionInterceptor()));
            tuples.add(new ByteArrayRow(this.getTypeInfo("VARBINARY"), this.getExceptionInterceptor()));
            tuples.add(new ByteArrayRow(this.getTypeInfo("TINYBLOB"), this.getExceptionInterceptor()));
            tuples.add(new ByteArrayRow(this.getTypeInfo("BINARY"), this.getExceptionInterceptor()));
            tuples.add(new ByteArrayRow(this.getTypeInfo("LONG VARCHAR"), this.getExceptionInterceptor()));
            tuples.add(new ByteArrayRow(this.getTypeInfo("MEDIUMTEXT"), this.getExceptionInterceptor()));
            tuples.add(new ByteArrayRow(this.getTypeInfo("LONGTEXT"), this.getExceptionInterceptor()));
            tuples.add(new ByteArrayRow(this.getTypeInfo("TEXT"), this.getExceptionInterceptor()));
            tuples.add(new ByteArrayRow(this.getTypeInfo("CHAR"), this.getExceptionInterceptor()));
            tuples.add(new ByteArrayRow(this.getTypeInfo("ENUM"), this.getExceptionInterceptor()));
            tuples.add(new ByteArrayRow(this.getTypeInfo("SET"), this.getExceptionInterceptor()));
            tuples.add(new ByteArrayRow(this.getTypeInfo("DECIMAL"), this.getExceptionInterceptor()));
            tuples.add(new ByteArrayRow(this.getTypeInfo("NUMERIC"), this.getExceptionInterceptor()));
            tuples.add(new ByteArrayRow(this.getTypeInfo("INTEGER"), this.getExceptionInterceptor()));
            tuples.add(new ByteArrayRow(this.getTypeInfo("INTEGER UNSIGNED"), this.getExceptionInterceptor()));
            tuples.add(new ByteArrayRow(this.getTypeInfo("INT"), this.getExceptionInterceptor()));
            tuples.add(new ByteArrayRow(this.getTypeInfo("INT UNSIGNED"), this.getExceptionInterceptor()));
            tuples.add(new ByteArrayRow(this.getTypeInfo("MEDIUMINT"), this.getExceptionInterceptor()));
            tuples.add(new ByteArrayRow(this.getTypeInfo("MEDIUMINT UNSIGNED"), this.getExceptionInterceptor()));
            tuples.add(new ByteArrayRow(this.getTypeInfo("SMALLINT"), this.getExceptionInterceptor()));
            tuples.add(new ByteArrayRow(this.getTypeInfo("SMALLINT UNSIGNED"), this.getExceptionInterceptor()));
            tuples.add(new ByteArrayRow(this.getTypeInfo("FLOAT"), this.getExceptionInterceptor()));
            tuples.add(new ByteArrayRow(this.getTypeInfo("DOUBLE"), this.getExceptionInterceptor()));
            tuples.add(new ByteArrayRow(this.getTypeInfo("DOUBLE PRECISION"), this.getExceptionInterceptor()));
            tuples.add(new ByteArrayRow(this.getTypeInfo("REAL"), this.getExceptionInterceptor()));
            tuples.add(new ByteArrayRow(this.getTypeInfo("VARCHAR"), this.getExceptionInterceptor()));
            tuples.add(new ByteArrayRow(this.getTypeInfo("TINYTEXT"), this.getExceptionInterceptor()));
            tuples.add(new ByteArrayRow(this.getTypeInfo("DATE"), this.getExceptionInterceptor()));
            tuples.add(new ByteArrayRow(this.getTypeInfo("YEAR"), this.getExceptionInterceptor()));
            tuples.add(new ByteArrayRow(this.getTypeInfo("TIME"), this.getExceptionInterceptor()));
            tuples.add(new ByteArrayRow(this.getTypeInfo("DATETIME"), this.getExceptionInterceptor()));
            tuples.add(new ByteArrayRow(this.getTypeInfo("TIMESTAMP"), this.getExceptionInterceptor()));
            return this.resultSetFactory.createFromResultsetRows(1007, 1004, new ResultsetRowsStatic(tuples, new DefaultColumnDefinition(fields)));
        }
        catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    @Override
    public ResultSet getUDTs(String catalog, String schemaPattern, String typeNamePattern, int[] types) throws SQLException {
        try {
            Field[] fields = new Field[]{new Field("", "TYPE_CAT", this.metadataCollationIndex, this.metadataEncoding, MysqlType.VARCHAR, 32), new Field("", "TYPE_SCHEM", this.metadataCollationIndex, this.metadataEncoding, MysqlType.VARCHAR, 32), new Field("", "TYPE_NAME", this.metadataCollationIndex, this.metadataEncoding, MysqlType.VARCHAR, 32), new Field("", "CLASS_NAME", this.metadataCollationIndex, this.metadataEncoding, MysqlType.VARCHAR, 32), new Field("", "DATA_TYPE", this.metadataCollationIndex, this.metadataEncoding, MysqlType.INT, 10), new Field("", "REMARKS", this.metadataCollationIndex, this.metadataEncoding, MysqlType.VARCHAR, 32), new Field("", "BASE_TYPE", this.metadataCollationIndex, this.metadataEncoding, MysqlType.SMALLINT, 10)};
            ArrayList tuples = new ArrayList();
            return this.resultSetFactory.createFromResultsetRows(1007, 1004, new ResultsetRowsStatic(tuples, new DefaultColumnDefinition(fields)));
        }
        catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    @Override
    public String getURL() throws SQLException {
        try {
            return this.conn.getURL();
        }
        catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public String getUserName() throws SQLException {
        try {
            if (this.useHostsInPrivileges) {
                Statement stmt = null;
                ResultSet rs = null;
                try {
                    stmt = this.conn.getMetadataSafeStatement();
                    rs = stmt.executeQuery("SELECT USER()");
                    rs.next();
                    String string = rs.getString(1);
                    return string;
                }
                finally {
                    if (rs != null) {
                        try {
                            rs.close();
                        }
                        catch (Exception ex) {
                            AssertionFailedException.shouldNotHappen(ex);
                        }
                        rs = null;
                    }
                    if (stmt != null) {
                        try {
                            stmt.close();
                        }
                        catch (Exception ex) {
                            AssertionFailedException.shouldNotHappen(ex);
                        }
                        stmt = null;
                    }
                }
            }
            return this.conn.getUser();
        }
        catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    protected Field[] getVersionColumnsFields() {
        Field[] fields = new Field[]{new Field("", "SCOPE", this.metadataCollationIndex, this.metadataEncoding, MysqlType.SMALLINT, 5), new Field("", "COLUMN_NAME", this.metadataCollationIndex, this.metadataEncoding, MysqlType.CHAR, 32), new Field("", "DATA_TYPE", this.metadataCollationIndex, this.metadataEncoding, MysqlType.INT, 5), new Field("", "TYPE_NAME", this.metadataCollationIndex, this.metadataEncoding, MysqlType.CHAR, 16), new Field("", "COLUMN_SIZE", this.metadataCollationIndex, this.metadataEncoding, MysqlType.INT, 16), new Field("", "BUFFER_LENGTH", this.metadataCollationIndex, this.metadataEncoding, MysqlType.INT, 16), new Field("", "DECIMAL_DIGITS", this.metadataCollationIndex, this.metadataEncoding, MysqlType.SMALLINT, 16), new Field("", "PSEUDO_COLUMN", this.metadataCollationIndex, this.metadataEncoding, MysqlType.SMALLINT, 5)};
        return fields;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public ResultSet getVersionColumns(String catalog, String schema, final String table) throws SQLException {
        try {
            if (table == null) {
                throw SQLError.createSQLException(Messages.getString("DatabaseMetaData.2"), "S1009", this.getExceptionInterceptor());
            }
            final ArrayList rows = new ArrayList();
            String db = this.getDatabase(catalog, schema);
            try (final Statement stmt = this.conn.getMetadataSafeStatement();){
                new IterateBlock<String>(this.getDatabaseIterator(db)){

                    /*
                     * WARNING - Removed try catching itself - possible behaviour change.
                     */
                    @Override
                    void forEach(String dbStr) throws SQLException {
                        block16: {
                            ResultSet results = null;
                            try {
                                block17: {
                                    StringBuilder whereBuf = new StringBuilder(" Extra LIKE '%on update CURRENT_TIMESTAMP%'");
                                    ArrayList rsFields = new ArrayList();
                                    if (whereBuf.length() <= 0 && rsFields.size() <= 0) break block16;
                                    StringBuilder queryBuf = new StringBuilder("SHOW COLUMNS FROM ");
                                    queryBuf.append(StringUtils.quoteIdentifier(table, DatabaseMetaData.this.quotedId, DatabaseMetaData.this.pedantic));
                                    queryBuf.append(" FROM ");
                                    queryBuf.append(StringUtils.quoteIdentifier(dbStr, DatabaseMetaData.this.quotedId, DatabaseMetaData.this.pedantic));
                                    queryBuf.append(" WHERE");
                                    queryBuf.append(whereBuf.toString());
                                    try {
                                        results = stmt.executeQuery(queryBuf.toString());
                                    }
                                    catch (SQLException sqlEx) {
                                        String sqlState = sqlEx.getSQLState();
                                        int errorCode = sqlEx.getErrorCode();
                                        if ("42S02".equals(sqlState) || errorCode == 1146 || errorCode == 1049) break block17;
                                        throw sqlEx;
                                    }
                                }
                                while (results != null && results.next()) {
                                    TypeDescriptor typeDesc = new TypeDescriptor(results.getString("Type"), results.getString("Null"));
                                    byte[][] rowVal = new byte[][]{null, results.getBytes("Field"), Short.toString((short)typeDesc.mysqlType.getJdbcType()).getBytes(), DatabaseMetaData.this.s2b(typeDesc.mysqlType.getName()), typeDesc.columnSize == null ? null : DatabaseMetaData.this.s2b(typeDesc.columnSize.toString()), DatabaseMetaData.this.s2b(Integer.toString(typeDesc.bufferLength)), typeDesc.decimalDigits == null ? null : DatabaseMetaData.this.s2b(typeDesc.decimalDigits.toString()), Integer.toString(1).getBytes()};
                                    rows.add(new ByteArrayRow(rowVal, DatabaseMetaData.this.getExceptionInterceptor()));
                                }
                            }
                            catch (SQLException sqlEx) {
                                if (!"42S02".equals(sqlEx.getSQLState())) {
                                    throw sqlEx;
                                }
                            }
                            finally {
                                if (results != null) {
                                    try {
                                        results.close();
                                    }
                                    catch (Exception exception) {}
                                    results = null;
                                }
                            }
                        }
                    }
                }.doForAll();
            }
            return this.resultSetFactory.createFromResultsetRows(1007, 1004, new ResultsetRowsStatic(rows, new DefaultColumnDefinition(this.getVersionColumnsFields())));
        }
        catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    @Override
    public boolean insertsAreDetected(int type) throws SQLException {
        try {
            return false;
        }
        catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    @Override
    public boolean isCatalogAtStart() throws SQLException {
        try {
            return true;
        }
        catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    @Override
    public boolean isReadOnly() throws SQLException {
        try {
            return false;
        }
        catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    @Override
    public boolean locatorsUpdateCopy() throws SQLException {
        try {
            return this.conn.getPropertySet().getBooleanProperty(PropertyKey.emulateLocators).getValue() == false;
        }
        catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    @Override
    public boolean nullPlusNonNullIsNull() throws SQLException {
        try {
            return true;
        }
        catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    @Override
    public boolean nullsAreSortedAtEnd() throws SQLException {
        try {
            return false;
        }
        catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    @Override
    public boolean nullsAreSortedAtStart() throws SQLException {
        try {
            return false;
        }
        catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    @Override
    public boolean nullsAreSortedHigh() throws SQLException {
        try {
            return false;
        }
        catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    @Override
    public boolean nullsAreSortedLow() throws SQLException {
        try {
            return !this.nullsAreSortedHigh();
        }
        catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    @Override
    public boolean othersDeletesAreVisible(int type) throws SQLException {
        try {
            return false;
        }
        catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    @Override
    public boolean othersInsertsAreVisible(int type) throws SQLException {
        try {
            return false;
        }
        catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    @Override
    public boolean othersUpdatesAreVisible(int type) throws SQLException {
        try {
            return false;
        }
        catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    @Override
    public boolean ownDeletesAreVisible(int type) throws SQLException {
        try {
            return false;
        }
        catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    @Override
    public boolean ownInsertsAreVisible(int type) throws SQLException {
        try {
            return false;
        }
        catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    @Override
    public boolean ownUpdatesAreVisible(int type) throws SQLException {
        try {
            return false;
        }
        catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    protected LocalAndReferencedColumns parseTableStatusIntoLocalAndReferencedColumns(String keysComment) throws SQLException {
        String columnsDelimitter = ",";
        int indexOfOpenParenLocalColumns = StringUtils.indexOfIgnoreCase(0, keysComment, "(", this.quotedId, this.quotedId, StringUtils.SEARCH_MODE__ALL);
        if (indexOfOpenParenLocalColumns == -1) {
            throw SQLError.createSQLException(Messages.getString("DatabaseMetaData.14"), "S1000", this.getExceptionInterceptor());
        }
        String constraintName = StringUtils.unQuoteIdentifier(keysComment.substring(0, indexOfOpenParenLocalColumns).trim(), this.quotedId);
        String keysCommentTrimmed = (keysComment = keysComment.substring(indexOfOpenParenLocalColumns, keysComment.length())).trim();
        int indexOfCloseParenLocalColumns = StringUtils.indexOfIgnoreCase(0, keysCommentTrimmed, ")", this.quotedId, this.quotedId, StringUtils.SEARCH_MODE__ALL);
        if (indexOfCloseParenLocalColumns == -1) {
            throw SQLError.createSQLException(Messages.getString("DatabaseMetaData.15"), "S1000", this.getExceptionInterceptor());
        }
        String localColumnNamesString = keysCommentTrimmed.substring(1, indexOfCloseParenLocalColumns);
        int indexOfRefer = StringUtils.indexOfIgnoreCase(0, keysCommentTrimmed, "REFER ", this.quotedId, this.quotedId, StringUtils.SEARCH_MODE__ALL);
        if (indexOfRefer == -1) {
            throw SQLError.createSQLException(Messages.getString("DatabaseMetaData.16"), "S1000", this.getExceptionInterceptor());
        }
        int indexOfOpenParenReferCol = StringUtils.indexOfIgnoreCase(indexOfRefer, keysCommentTrimmed, "(", this.quotedId, this.quotedId, StringUtils.SEARCH_MODE__MRK_COM_WS);
        if (indexOfOpenParenReferCol == -1) {
            throw SQLError.createSQLException(Messages.getString("DatabaseMetaData.17"), "S1000", this.getExceptionInterceptor());
        }
        String referDbTableString = keysCommentTrimmed.substring(indexOfRefer + "REFER ".length(), indexOfOpenParenReferCol);
        int indexOfSlash = StringUtils.indexOfIgnoreCase(0, referDbTableString, "/", this.quotedId, this.quotedId, StringUtils.SEARCH_MODE__MRK_COM_WS);
        if (indexOfSlash == -1) {
            throw SQLError.createSQLException(Messages.getString("DatabaseMetaData.18"), "S1000", this.getExceptionInterceptor());
        }
        String referDb = StringUtils.unQuoteIdentifier(referDbTableString.substring(0, indexOfSlash), this.quotedId);
        String referTable = StringUtils.unQuoteIdentifier(referDbTableString.substring(indexOfSlash + 1).trim(), this.quotedId);
        int indexOfCloseParenRefer = StringUtils.indexOfIgnoreCase(indexOfOpenParenReferCol, keysCommentTrimmed, ")", this.quotedId, this.quotedId, StringUtils.SEARCH_MODE__ALL);
        if (indexOfCloseParenRefer == -1) {
            throw SQLError.createSQLException(Messages.getString("DatabaseMetaData.19"), "S1000", this.getExceptionInterceptor());
        }
        String referColumnNamesString = keysCommentTrimmed.substring(indexOfOpenParenReferCol + 1, indexOfCloseParenRefer);
        List<String> referColumnsList = StringUtils.split(referColumnNamesString, columnsDelimitter, this.quotedId, this.quotedId, false);
        List<String> localColumnsList = StringUtils.split(localColumnNamesString, columnsDelimitter, this.quotedId, this.quotedId, false);
        return new LocalAndReferencedColumns(localColumnsList, referColumnsList, constraintName, referDb, referTable);
    }

    protected byte[] s2b(String s) throws SQLException {
        if (s == null) {
            return null;
        }
        try {
            return StringUtils.getBytes(s, this.conn.getCharacterSetMetadata());
        }
        catch (CJException e) {
            throw SQLExceptionsMapping.translateException(e, this.getExceptionInterceptor());
        }
    }

    @Override
    public boolean storesLowerCaseIdentifiers() throws SQLException {
        try {
            return this.conn.storesLowerCaseTableName();
        }
        catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    @Override
    public boolean storesLowerCaseQuotedIdentifiers() throws SQLException {
        try {
            return this.conn.storesLowerCaseTableName();
        }
        catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    @Override
    public boolean storesMixedCaseIdentifiers() throws SQLException {
        try {
            return !this.conn.storesLowerCaseTableName();
        }
        catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    @Override
    public boolean storesMixedCaseQuotedIdentifiers() throws SQLException {
        try {
            return !this.conn.storesLowerCaseTableName();
        }
        catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    @Override
    public boolean storesUpperCaseIdentifiers() throws SQLException {
        try {
            return false;
        }
        catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    @Override
    public boolean storesUpperCaseQuotedIdentifiers() throws SQLException {
        try {
            return true;
        }
        catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    @Override
    public boolean supportsAlterTableWithAddColumn() throws SQLException {
        try {
            return true;
        }
        catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    @Override
    public boolean supportsAlterTableWithDropColumn() throws SQLException {
        try {
            return true;
        }
        catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    @Override
    public boolean supportsANSI92EntryLevelSQL() throws SQLException {
        try {
            return true;
        }
        catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    @Override
    public boolean supportsANSI92FullSQL() throws SQLException {
        try {
            return false;
        }
        catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    @Override
    public boolean supportsANSI92IntermediateSQL() throws SQLException {
        try {
            return false;
        }
        catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    @Override
    public boolean supportsBatchUpdates() throws SQLException {
        try {
            return true;
        }
        catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    @Override
    public boolean supportsCatalogsInDataManipulation() throws SQLException {
        try {
            return this.databaseTerm.getValue() == PropertyDefinitions.DatabaseTerm.CATALOG;
        }
        catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    @Override
    public boolean supportsCatalogsInIndexDefinitions() throws SQLException {
        try {
            return this.databaseTerm.getValue() == PropertyDefinitions.DatabaseTerm.CATALOG;
        }
        catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    @Override
    public boolean supportsCatalogsInPrivilegeDefinitions() throws SQLException {
        try {
            return this.databaseTerm.getValue() == PropertyDefinitions.DatabaseTerm.CATALOG;
        }
        catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    @Override
    public boolean supportsCatalogsInProcedureCalls() throws SQLException {
        try {
            return this.databaseTerm.getValue() == PropertyDefinitions.DatabaseTerm.CATALOG;
        }
        catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    @Override
    public boolean supportsCatalogsInTableDefinitions() throws SQLException {
        try {
            return this.databaseTerm.getValue() == PropertyDefinitions.DatabaseTerm.CATALOG;
        }
        catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    @Override
    public boolean supportsColumnAliasing() throws SQLException {
        try {
            return true;
        }
        catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    @Override
    public boolean supportsConvert() throws SQLException {
        try {
            return false;
        }
        catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    @Override
    public boolean supportsConvert(int fromType, int toType) throws SQLException {
        try {
            return MysqlType.supportsConvert(fromType, toType);
        }
        catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    @Override
    public boolean supportsCoreSQLGrammar() throws SQLException {
        try {
            return true;
        }
        catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    @Override
    public boolean supportsCorrelatedSubqueries() throws SQLException {
        try {
            return true;
        }
        catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    @Override
    public boolean supportsDataDefinitionAndDataManipulationTransactions() throws SQLException {
        try {
            return false;
        }
        catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    @Override
    public boolean supportsDataManipulationTransactionsOnly() throws SQLException {
        try {
            return false;
        }
        catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    @Override
    public boolean supportsDifferentTableCorrelationNames() throws SQLException {
        try {
            return true;
        }
        catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    @Override
    public boolean supportsExpressionsInOrderBy() throws SQLException {
        try {
            return true;
        }
        catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    @Override
    public boolean supportsExtendedSQLGrammar() throws SQLException {
        try {
            return false;
        }
        catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    @Override
    public boolean supportsFullOuterJoins() throws SQLException {
        try {
            return false;
        }
        catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    @Override
    public boolean supportsGetGeneratedKeys() {
        try {
            return true;
        }
        catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    @Override
    public boolean supportsGroupBy() throws SQLException {
        try {
            return true;
        }
        catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    @Override
    public boolean supportsGroupByBeyondSelect() throws SQLException {
        try {
            return true;
        }
        catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    @Override
    public boolean supportsGroupByUnrelated() throws SQLException {
        try {
            return true;
        }
        catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    @Override
    public boolean supportsIntegrityEnhancementFacility() throws SQLException {
        try {
            return this.conn.getPropertySet().getBooleanProperty(PropertyKey.overrideSupportsIntegrityEnhancementFacility).getValue() != false;
        }
        catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    @Override
    public boolean supportsLikeEscapeClause() throws SQLException {
        try {
            return true;
        }
        catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    @Override
    public boolean supportsLimitedOuterJoins() throws SQLException {
        try {
            return true;
        }
        catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    @Override
    public boolean supportsMinimumSQLGrammar() throws SQLException {
        try {
            return true;
        }
        catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    @Override
    public boolean supportsMixedCaseIdentifiers() throws SQLException {
        try {
            return !this.conn.lowerCaseTableNames();
        }
        catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    @Override
    public boolean supportsMixedCaseQuotedIdentifiers() throws SQLException {
        try {
            return !this.conn.lowerCaseTableNames();
        }
        catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    @Override
    public boolean supportsMultipleOpenResults() throws SQLException {
        try {
            return true;
        }
        catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    @Override
    public boolean supportsMultipleResultSets() throws SQLException {
        try {
            return true;
        }
        catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    @Override
    public boolean supportsMultipleTransactions() throws SQLException {
        try {
            return true;
        }
        catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    @Override
    public boolean supportsNamedParameters() throws SQLException {
        try {
            return false;
        }
        catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    @Override
    public boolean supportsNonNullableColumns() throws SQLException {
        try {
            return true;
        }
        catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    @Override
    public boolean supportsOpenCursorsAcrossCommit() throws SQLException {
        try {
            return false;
        }
        catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    @Override
    public boolean supportsOpenCursorsAcrossRollback() throws SQLException {
        try {
            return false;
        }
        catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    @Override
    public boolean supportsOpenStatementsAcrossCommit() throws SQLException {
        try {
            return false;
        }
        catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    @Override
    public boolean supportsOpenStatementsAcrossRollback() throws SQLException {
        try {
            return false;
        }
        catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    @Override
    public boolean supportsOrderByUnrelated() throws SQLException {
        try {
            return false;
        }
        catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    @Override
    public boolean supportsOuterJoins() throws SQLException {
        try {
            return true;
        }
        catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    @Override
    public boolean supportsPositionedDelete() throws SQLException {
        try {
            return false;
        }
        catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    @Override
    public boolean supportsPositionedUpdate() throws SQLException {
        try {
            return false;
        }
        catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    @Override
    public boolean supportsResultSetConcurrency(int type, int concurrency) throws SQLException {
        try {
            if (!(type != 1003 && type != 1004 || concurrency != 1007 && concurrency != 1008)) {
                return true;
            }
            if (type == 1005) {
                return false;
            }
            throw SQLError.createSQLException(Messages.getString("DatabaseMetaData.20"), "S1009", this.getExceptionInterceptor());
        }
        catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    @Override
    public boolean supportsResultSetHoldability(int holdability) throws SQLException {
        try {
            return holdability == 1;
        }
        catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    @Override
    public boolean supportsResultSetType(int type) throws SQLException {
        try {
            return type == 1003 || type == 1004;
        }
        catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    @Override
    public boolean supportsSavepoints() throws SQLException {
        try {
            return true;
        }
        catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    @Override
    public boolean supportsSchemasInDataManipulation() throws SQLException {
        try {
            return this.databaseTerm.getValue() == PropertyDefinitions.DatabaseTerm.SCHEMA;
        }
        catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    @Override
    public boolean supportsSchemasInIndexDefinitions() throws SQLException {
        try {
            return this.databaseTerm.getValue() == PropertyDefinitions.DatabaseTerm.SCHEMA;
        }
        catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    @Override
    public boolean supportsSchemasInPrivilegeDefinitions() throws SQLException {
        try {
            return this.databaseTerm.getValue() == PropertyDefinitions.DatabaseTerm.SCHEMA;
        }
        catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    @Override
    public boolean supportsSchemasInProcedureCalls() throws SQLException {
        try {
            return this.databaseTerm.getValue() == PropertyDefinitions.DatabaseTerm.SCHEMA;
        }
        catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    @Override
    public boolean supportsSchemasInTableDefinitions() throws SQLException {
        try {
            return this.databaseTerm.getValue() == PropertyDefinitions.DatabaseTerm.SCHEMA;
        }
        catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    @Override
    public boolean supportsSelectForUpdate() throws SQLException {
        try {
            return true;
        }
        catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    @Override
    public boolean supportsStatementPooling() throws SQLException {
        try {
            return false;
        }
        catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    @Override
    public boolean supportsStoredProcedures() throws SQLException {
        try {
            return true;
        }
        catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    @Override
    public boolean supportsSubqueriesInComparisons() throws SQLException {
        try {
            return true;
        }
        catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    @Override
    public boolean supportsSubqueriesInExists() throws SQLException {
        try {
            return true;
        }
        catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    @Override
    public boolean supportsSubqueriesInIns() throws SQLException {
        try {
            return true;
        }
        catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    @Override
    public boolean supportsSubqueriesInQuantifieds() throws SQLException {
        try {
            return true;
        }
        catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    @Override
    public boolean supportsTableCorrelationNames() throws SQLException {
        try {
            return true;
        }
        catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    @Override
    public boolean supportsTransactionIsolationLevel(int level) throws SQLException {
        try {
            switch (level) {
                case 1: 
                case 2: 
                case 4: 
                case 8: {
                    return true;
                }
            }
            return false;
        }
        catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    @Override
    public boolean supportsTransactions() throws SQLException {
        try {
            return true;
        }
        catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    @Override
    public boolean supportsUnion() throws SQLException {
        try {
            return true;
        }
        catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    @Override
    public boolean supportsUnionAll() throws SQLException {
        try {
            return true;
        }
        catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    @Override
    public boolean updatesAreDetected(int type) throws SQLException {
        try {
            return false;
        }
        catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    @Override
    public boolean usesLocalFilePerTable() throws SQLException {
        try {
            return false;
        }
        catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    @Override
    public boolean usesLocalFiles() throws SQLException {
        try {
            return false;
        }
        catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    @Override
    public ResultSet getClientInfoProperties() throws SQLException {
        try {
            Field[] fields = new Field[]{new Field("", "NAME", this.metadataCollationIndex, this.metadataEncoding, MysqlType.VARCHAR, 255), new Field("", "MAX_LEN", this.metadataCollationIndex, this.metadataEncoding, MysqlType.INT, 10), new Field("", "DEFAULT_VALUE", this.metadataCollationIndex, this.metadataEncoding, MysqlType.VARCHAR, 255), new Field("", "DESCRIPTION", this.metadataCollationIndex, this.metadataEncoding, MysqlType.VARCHAR, 255)};
            return this.resultSetFactory.createFromResultsetRows(1007, 1004, new ResultsetRowsStatic(new ArrayList(), new DefaultColumnDefinition(fields)));
        }
        catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    @Override
    public ResultSet getFunctionColumns(String catalog, String schemaPattern, String functionNamePattern, String columnNamePattern) throws SQLException {
        try {
            return this.getProcedureOrFunctionColumns(this.createFunctionColumnsFields(), catalog, schemaPattern, functionNamePattern, columnNamePattern, false, true);
        }
        catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    protected Field[] createFunctionColumnsFields() {
        Field[] fields = new Field[]{new Field("", "FUNCTION_CAT", this.metadataCollationIndex, this.metadataEncoding, MysqlType.VARCHAR, 512), new Field("", "FUNCTION_SCHEM", this.metadataCollationIndex, this.metadataEncoding, MysqlType.VARCHAR, 512), new Field("", "FUNCTION_NAME", this.metadataCollationIndex, this.metadataEncoding, MysqlType.VARCHAR, 512), new Field("", "COLUMN_NAME", this.metadataCollationIndex, this.metadataEncoding, MysqlType.VARCHAR, 512), new Field("", "COLUMN_TYPE", this.metadataCollationIndex, this.metadataEncoding, MysqlType.VARCHAR, 64), new Field("", "DATA_TYPE", this.metadataCollationIndex, this.metadataEncoding, MysqlType.SMALLINT, 6), new Field("", "TYPE_NAME", this.metadataCollationIndex, this.metadataEncoding, MysqlType.VARCHAR, 64), new Field("", "PRECISION", this.metadataCollationIndex, this.metadataEncoding, MysqlType.INT, 12), new Field("", "LENGTH", this.metadataCollationIndex, this.metadataEncoding, MysqlType.INT, 12), new Field("", "SCALE", this.metadataCollationIndex, this.metadataEncoding, MysqlType.SMALLINT, 12), new Field("", "RADIX", this.metadataCollationIndex, this.metadataEncoding, MysqlType.SMALLINT, 6), new Field("", "NULLABLE", this.metadataCollationIndex, this.metadataEncoding, MysqlType.SMALLINT, 6), new Field("", "REMARKS", this.metadataCollationIndex, this.metadataEncoding, MysqlType.VARCHAR, 512), new Field("", "CHAR_OCTET_LENGTH", this.metadataCollationIndex, this.metadataEncoding, MysqlType.INT, 32), new Field("", "ORDINAL_POSITION", this.metadataCollationIndex, this.metadataEncoding, MysqlType.INT, 32), new Field("", "IS_NULLABLE", this.metadataCollationIndex, this.metadataEncoding, MysqlType.VARCHAR, 12), new Field("", "SPECIFIC_NAME", this.metadataCollationIndex, this.metadataEncoding, MysqlType.VARCHAR, 64)};
        return fields;
    }

    protected Field[] getFunctionsFields() {
        Field[] fields = new Field[]{new Field("", "FUNCTION_CAT", this.metadataCollationIndex, this.metadataEncoding, MysqlType.CHAR, 255), new Field("", "FUNCTION_SCHEM", this.metadataCollationIndex, this.metadataEncoding, MysqlType.CHAR, 255), new Field("", "FUNCTION_NAME", this.metadataCollationIndex, this.metadataEncoding, MysqlType.CHAR, 255), new Field("", "REMARKS", this.metadataCollationIndex, this.metadataEncoding, MysqlType.CHAR, 255), new Field("", "FUNCTION_TYPE", this.metadataCollationIndex, this.metadataEncoding, MysqlType.SMALLINT, 6), new Field("", "SPECIFIC_NAME", this.metadataCollationIndex, this.metadataEncoding, MysqlType.CHAR, 255)};
        return fields;
    }

    @Override
    public ResultSet getFunctions(String catalog, String schemaPattern, String functionNamePattern) throws SQLException {
        try {
            return this.getProceduresAndOrFunctions(this.getFunctionsFields(), catalog, schemaPattern, functionNamePattern, false, true);
        }
        catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    public boolean providesQueryObjectGenerator() throws SQLException {
        return false;
    }

    @Override
    public boolean supportsStoredFunctionsUsingCallSyntax() throws SQLException {
        try {
            return true;
        }
        catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    protected PreparedStatement prepareMetaDataSafeStatement(String sql) throws SQLException {
        PreparedStatement pStmt = this.conn.clientPrepareStatement(sql, 1004, 1007);
        if (pStmt.getMaxRows() != 0) {
            pStmt.setMaxRows(0);
        }
        ((JdbcStatement)((Object)pStmt)).setHoldResultsOpenOverClose(true);
        return pStmt;
    }

    @Override
    public ResultSet getPseudoColumns(String catalog, String schemaPattern, String tableNamePattern, String columnNamePattern) throws SQLException {
        try {
            Field[] fields = new Field[]{new Field("", "TABLE_CAT", this.metadataCollationIndex, this.metadataEncoding, MysqlType.VARCHAR, 512), new Field("", "TABLE_SCHEM", this.metadataCollationIndex, this.metadataEncoding, MysqlType.VARCHAR, 512), new Field("", "TABLE_NAME", this.metadataCollationIndex, this.metadataEncoding, MysqlType.VARCHAR, 512), new Field("", "COLUMN_NAME", this.metadataCollationIndex, this.metadataEncoding, MysqlType.VARCHAR, 512), new Field("", "DATA_TYPE", this.metadataCollationIndex, this.metadataEncoding, MysqlType.INT, 12), new Field("", "COLUMN_SIZE", this.metadataCollationIndex, this.metadataEncoding, MysqlType.INT, 12), new Field("", "DECIMAL_DIGITS", this.metadataCollationIndex, this.metadataEncoding, MysqlType.INT, 12), new Field("", "NUM_PREC_RADIX", this.metadataCollationIndex, this.metadataEncoding, MysqlType.INT, 12), new Field("", "COLUMN_USAGE", this.metadataCollationIndex, this.metadataEncoding, MysqlType.VARCHAR, 512), new Field("", "REMARKS", this.metadataCollationIndex, this.metadataEncoding, MysqlType.VARCHAR, 512), new Field("", "CHAR_OCTET_LENGTH", this.metadataCollationIndex, this.metadataEncoding, MysqlType.INT, 12), new Field("", "IS_NULLABLE", this.metadataCollationIndex, this.metadataEncoding, MysqlType.VARCHAR, 512)};
            return this.resultSetFactory.createFromResultsetRows(1007, 1004, new ResultsetRowsStatic(new ArrayList(), new DefaultColumnDefinition(fields)));
        }
        catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    @Override
    public boolean generatedKeyAlwaysReturned() throws SQLException {
        try {
            return true;
        }
        catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    @Override
    public <T> T unwrap(Class<T> iface) throws SQLException {
        try {
            try {
                return iface.cast(this);
            }
            catch (ClassCastException cce) {
                throw SQLError.createSQLException(Messages.getString("Common.UnableToUnwrap", new Object[]{iface.toString()}), "S1009", this.conn.getExceptionInterceptor());
            }
        }
        catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    @Override
    public boolean isWrapperFor(Class<?> iface) throws SQLException {
        try {
            return iface.isInstance(this);
        }
        catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    @Override
    public RowIdLifetime getRowIdLifetime() throws SQLException {
        try {
            return RowIdLifetime.ROWID_UNSUPPORTED;
        }
        catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    @Override
    public boolean autoCommitFailureClosesAllResultSets() throws SQLException {
        try {
            return false;
        }
        catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    public String getMetadataEncoding() {
        return this.metadataEncoding;
    }

    public void setMetadataEncoding(String metadataEncoding) {
        this.metadataEncoding = metadataEncoding;
    }

    public int getMetadataCollationIndex() {
        return this.metadataCollationIndex;
    }

    public void setMetadataCollationIndex(int metadataCollationIndex) {
        this.metadataCollationIndex = metadataCollationIndex;
    }

    protected static enum ProcedureType {
        PROCEDURE,
        FUNCTION;

    }

    protected static enum TableType {
        LOCAL_TEMPORARY("LOCAL TEMPORARY"),
        SYSTEM_TABLE("SYSTEM TABLE"),
        SYSTEM_VIEW("SYSTEM VIEW"),
        TABLE("TABLE", new String[]{"BASE TABLE"}),
        VIEW("VIEW"),
        UNKNOWN("UNKNOWN");

        private String name;
        private byte[] nameAsBytes;
        private String[] synonyms;

        private TableType(String tableTypeName) {
            this(tableTypeName, null);
        }

        private TableType(String tableTypeName, String[] tableTypeSynonyms) {
            this.name = tableTypeName;
            this.nameAsBytes = tableTypeName.getBytes();
            this.synonyms = tableTypeSynonyms;
        }

        String getName() {
            return this.name;
        }

        byte[] asBytes() {
            return this.nameAsBytes;
        }

        boolean equalsTo(String tableTypeName) {
            return this.name.equalsIgnoreCase(tableTypeName);
        }

        static TableType getTableTypeEqualTo(String tableTypeName) {
            for (TableType tableType : TableType.values()) {
                if (!tableType.equalsTo(tableTypeName)) continue;
                return tableType;
            }
            return UNKNOWN;
        }

        boolean compliesWith(String tableTypeName) {
            if (this.equalsTo(tableTypeName)) {
                return true;
            }
            if (this.synonyms != null) {
                for (String synonym : this.synonyms) {
                    if (!synonym.equalsIgnoreCase(tableTypeName)) continue;
                    return true;
                }
            }
            return false;
        }

        static TableType getTableTypeCompliantWith(String tableTypeName) {
            for (TableType tableType : TableType.values()) {
                if (!tableType.compliesWith(tableTypeName)) continue;
                return tableType;
            }
            return UNKNOWN;
        }
    }

    protected class ComparableWrapper<K, V>
    implements Comparable<ComparableWrapper<K, V>> {
        K key;
        V value;

        public ComparableWrapper(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public K getKey() {
            return this.key;
        }

        public V getValue() {
            return this.value;
        }

        @Override
        public int compareTo(ComparableWrapper<K, V> other) {
            return ((Comparable)this.getKey()).compareTo(other.getKey());
        }

        public boolean equals(Object obj) {
            if (obj == null) {
                return false;
            }
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof ComparableWrapper)) {
                return false;
            }
            K otherKey = ((ComparableWrapper)obj).getKey();
            return this.key.equals(otherKey);
        }

        public int hashCode() {
            assert (false) : "hashCode not designed";
            return 0;
        }

        public String toString() {
            return "{KEY:" + this.key + "; VALUE:" + this.value + "}";
        }
    }

    protected class TableMetaDataKey
    implements Comparable<TableMetaDataKey> {
        String tableType;
        String tableCat;
        String tableSchem;
        String tableName;

        TableMetaDataKey(String tableType, String tableCat, String tableSchem, String tableName) {
            this.tableType = tableType == null ? "" : tableType;
            this.tableCat = tableCat == null ? "" : tableCat;
            this.tableSchem = tableSchem == null ? "" : tableSchem;
            this.tableName = tableName == null ? "" : tableName;
        }

        @Override
        public int compareTo(TableMetaDataKey tablesKey) {
            int compareResult = this.tableType.compareTo(tablesKey.tableType);
            if (compareResult != 0) {
                return compareResult;
            }
            compareResult = this.tableCat.compareTo(tablesKey.tableCat);
            if (compareResult != 0) {
                return compareResult;
            }
            compareResult = this.tableSchem.compareTo(tablesKey.tableSchem);
            if (compareResult != 0) {
                return compareResult;
            }
            return this.tableName.compareTo(tablesKey.tableName);
        }

        public boolean equals(Object obj) {
            if (obj == null) {
                return false;
            }
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof TableMetaDataKey)) {
                return false;
            }
            return this.compareTo((TableMetaDataKey)obj) == 0;
        }

        public int hashCode() {
            assert (false) : "hashCode not designed";
            return 0;
        }
    }

    protected class IndexMetaDataKey
    implements Comparable<IndexMetaDataKey> {
        Boolean columnNonUnique;
        Short columnType;
        String columnIndexName;
        Short columnOrdinalPosition;

        IndexMetaDataKey(boolean columnNonUnique, short columnType, String columnIndexName, short columnOrdinalPosition) {
            this.columnNonUnique = columnNonUnique;
            this.columnType = columnType;
            this.columnIndexName = columnIndexName;
            this.columnOrdinalPosition = columnOrdinalPosition;
        }

        @Override
        public int compareTo(IndexMetaDataKey indexInfoKey) {
            int compareResult = this.columnNonUnique.compareTo(indexInfoKey.columnNonUnique);
            if (compareResult != 0) {
                return compareResult;
            }
            compareResult = this.columnType.compareTo(indexInfoKey.columnType);
            if (compareResult != 0) {
                return compareResult;
            }
            compareResult = this.columnIndexName.compareTo(indexInfoKey.columnIndexName);
            if (compareResult != 0) {
                return compareResult;
            }
            return this.columnOrdinalPosition.compareTo(indexInfoKey.columnOrdinalPosition);
        }

        public boolean equals(Object obj) {
            if (obj == null) {
                return false;
            }
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof IndexMetaDataKey)) {
                return false;
            }
            return this.compareTo((IndexMetaDataKey)obj) == 0;
        }

        public int hashCode() {
            assert (false) : "hashCode not designed";
            return 0;
        }
    }

    class TypeDescriptor {
        int bufferLength;
        Integer datetimePrecision = null;
        Integer columnSize = null;
        Integer charOctetLength = null;
        Integer decimalDigits = null;
        String isNullable;
        int nullability;
        int numPrecRadix = 10;
        String mysqlTypeName;
        MysqlType mysqlType;

        TypeDescriptor(String typeInfo, String nullabilityInfo) throws SQLException {
            if (typeInfo == null) {
                throw SQLError.createSQLException(Messages.getString("DatabaseMetaData.0"), "S1009", DatabaseMetaData.this.getExceptionInterceptor());
            }
            this.mysqlType = MysqlType.getByName(typeInfo);
            int maxLength = 0;
            switch (this.mysqlType) {
                case ENUM: {
                    String temp = typeInfo.substring(typeInfo.indexOf("(") + 1, typeInfo.lastIndexOf(")"));
                    StringTokenizer tokenizer = new StringTokenizer(temp, ",");
                    while (tokenizer.hasMoreTokens()) {
                        String nextToken = tokenizer.nextToken();
                        maxLength = Math.max(maxLength, nextToken.length() - 2);
                    }
                    this.columnSize = maxLength;
                    break;
                }
                case SET: {
                    String temp = typeInfo.substring(typeInfo.indexOf("(") + 1, typeInfo.lastIndexOf(")"));
                    StringTokenizer tokenizer = new StringTokenizer(temp, ",");
                    int numElements = tokenizer.countTokens();
                    if (numElements > 0) {
                        maxLength += numElements - 1;
                    }
                    while (tokenizer.hasMoreTokens()) {
                        String setMember = tokenizer.nextToken().trim();
                        if (setMember.startsWith("'") && setMember.endsWith("'")) {
                            maxLength += setMember.length() - 2;
                            continue;
                        }
                        maxLength += setMember.length();
                    }
                    this.columnSize = maxLength;
                    break;
                }
                case FLOAT: 
                case FLOAT_UNSIGNED: {
                    if (typeInfo.indexOf(",") != -1) {
                        this.columnSize = Integer.valueOf(typeInfo.substring(typeInfo.indexOf("(") + 1, typeInfo.indexOf(",")).trim());
                        this.decimalDigits = Integer.valueOf(typeInfo.substring(typeInfo.indexOf(",") + 1, typeInfo.indexOf(")")).trim());
                        break;
                    }
                    if (typeInfo.indexOf("(") != -1) {
                        int size = Integer.valueOf(typeInfo.substring(typeInfo.indexOf("(") + 1, typeInfo.indexOf(")")).trim());
                        if (size <= 23) break;
                        this.mysqlType = this.mysqlType == MysqlType.FLOAT ? MysqlType.DOUBLE : MysqlType.DOUBLE_UNSIGNED;
                        this.columnSize = 22;
                        this.decimalDigits = 0;
                        break;
                    }
                    this.columnSize = 12;
                    this.decimalDigits = 0;
                    break;
                }
                case DECIMAL: 
                case DECIMAL_UNSIGNED: 
                case DOUBLE: 
                case DOUBLE_UNSIGNED: {
                    if (typeInfo.indexOf(",") != -1) {
                        this.columnSize = Integer.valueOf(typeInfo.substring(typeInfo.indexOf("(") + 1, typeInfo.indexOf(",")).trim());
                        this.decimalDigits = Integer.valueOf(typeInfo.substring(typeInfo.indexOf(",") + 1, typeInfo.indexOf(")")).trim());
                        break;
                    }
                    switch (this.mysqlType) {
                        case DECIMAL: 
                        case DECIMAL_UNSIGNED: {
                            this.columnSize = 65;
                            break;
                        }
                        case DOUBLE: 
                        case DOUBLE_UNSIGNED: {
                            this.columnSize = 22;
                            break;
                        }
                    }
                    this.decimalDigits = 0;
                    break;
                }
                case CHAR: 
                case VARCHAR: 
                case TINYTEXT: 
                case MEDIUMTEXT: 
                case LONGTEXT: 
                case JSON: 
                case TEXT: 
                case TINYBLOB: 
                case MEDIUMBLOB: 
                case LONGBLOB: 
                case BLOB: 
                case BINARY: 
                case VARBINARY: 
                case BIT: {
                    if (this.mysqlType == MysqlType.CHAR) {
                        this.columnSize = 1;
                    }
                    if (typeInfo.indexOf("(") == -1) break;
                    int endParenIndex = typeInfo.indexOf(")");
                    if (endParenIndex == -1) {
                        endParenIndex = typeInfo.length();
                    }
                    this.columnSize = Integer.valueOf(typeInfo.substring(typeInfo.indexOf("(") + 1, endParenIndex).trim());
                    if (!DatabaseMetaData.this.tinyInt1isBit || this.columnSize != 1 || !StringUtils.startsWithIgnoreCase(typeInfo, "tinyint")) break;
                    if (DatabaseMetaData.this.transformedBitIsBoolean) {
                        this.mysqlType = MysqlType.BOOLEAN;
                        break;
                    }
                    this.mysqlType = MysqlType.BIT;
                    break;
                }
                case TINYINT: {
                    if (DatabaseMetaData.this.tinyInt1isBit && typeInfo.indexOf("(1)") != -1) {
                        if (DatabaseMetaData.this.transformedBitIsBoolean) {
                            this.mysqlType = MysqlType.BOOLEAN;
                            break;
                        }
                        this.mysqlType = MysqlType.BIT;
                        break;
                    }
                    this.columnSize = 3;
                    break;
                }
                case TINYINT_UNSIGNED: {
                    this.columnSize = 3;
                    break;
                }
                case DATE: {
                    this.datetimePrecision = 0;
                    this.columnSize = 10;
                    break;
                }
                case TIME: {
                    int fract;
                    this.datetimePrecision = 0;
                    this.columnSize = 8;
                    if (typeInfo.indexOf("(") == -1 || (fract = Integer.valueOf(typeInfo.substring(typeInfo.indexOf("(") + 1, typeInfo.indexOf(")")).trim()).intValue()) <= 0) break;
                    this.datetimePrecision = fract;
                    TypeDescriptor typeDescriptor = this;
                    typeDescriptor.columnSize = typeDescriptor.columnSize + (fract + 1);
                    break;
                }
                case DATETIME: 
                case TIMESTAMP: {
                    int fract;
                    this.datetimePrecision = 0;
                    this.columnSize = 19;
                    if (typeInfo.indexOf("(") == -1 || (fract = Integer.valueOf(typeInfo.substring(typeInfo.indexOf("(") + 1, typeInfo.indexOf(")")).trim()).intValue()) <= 0) break;
                    this.datetimePrecision = fract;
                    TypeDescriptor typeDescriptor = this;
                    typeDescriptor.columnSize = typeDescriptor.columnSize + (fract + 1);
                    break;
                }
            }
            if (this.columnSize == null) {
                this.columnSize = this.mysqlType.getPrecision() > Integer.MAX_VALUE ? Integer.MAX_VALUE : this.mysqlType.getPrecision().intValue();
            }
            switch (this.mysqlType) {
                case CHAR: 
                case VARCHAR: 
                case TINYTEXT: 
                case MEDIUMTEXT: 
                case LONGTEXT: 
                case JSON: 
                case TEXT: 
                case TINYBLOB: 
                case MEDIUMBLOB: 
                case LONGBLOB: 
                case BLOB: 
                case BINARY: 
                case VARBINARY: 
                case BIT: {
                    this.charOctetLength = this.columnSize;
                    break;
                }
            }
            this.bufferLength = maxBufferSize;
            this.numPrecRadix = 10;
            if (nullabilityInfo != null) {
                if (nullabilityInfo.equals("YES")) {
                    this.nullability = 1;
                    this.isNullable = "YES";
                } else if (nullabilityInfo.equals("UNKNOWN")) {
                    this.nullability = 2;
                    this.isNullable = "";
                } else {
                    this.nullability = 0;
                    this.isNullable = "NO";
                }
            } else {
                this.nullability = 0;
                this.isNullable = "NO";
            }
        }
    }

    protected class SingleStringIterator
    extends IteratorWithCleanup<String> {
        boolean onFirst;
        String value;

        SingleStringIterator(String s) {
            this.onFirst = true;
            this.value = s;
        }

        @Override
        void close() throws SQLException {
        }

        @Override
        boolean hasNext() throws SQLException {
            return this.onFirst;
        }

        @Override
        String next() throws SQLException {
            this.onFirst = false;
            return this.value;
        }
    }

    protected class StringListIterator
    extends IteratorWithCleanup<String> {
        int idx;
        List<String> list;

        StringListIterator(List<String> list) {
            this.idx = -1;
            this.list = list;
        }

        @Override
        void close() throws SQLException {
            this.list = null;
        }

        @Override
        boolean hasNext() throws SQLException {
            return this.idx < this.list.size() - 1;
        }

        @Override
        String next() throws SQLException {
            ++this.idx;
            return this.list.get(this.idx);
        }
    }

    class LocalAndReferencedColumns {
        String constraintName;
        List<String> localColumnsList;
        String referencedDatabase;
        List<String> referencedColumnsList;
        String referencedTable;

        LocalAndReferencedColumns(List<String> localColumns, List<String> refColumns, String constName, String refDatabase, String refTable) {
            this.localColumnsList = localColumns;
            this.referencedColumnsList = refColumns;
            this.constraintName = constName;
            this.referencedTable = refTable;
            this.referencedDatabase = refDatabase;
        }
    }

    protected abstract class IteratorWithCleanup<T> {
        protected IteratorWithCleanup() {
        }

        abstract void close() throws SQLException;

        abstract boolean hasNext() throws SQLException;

        abstract T next() throws SQLException;
    }
}

