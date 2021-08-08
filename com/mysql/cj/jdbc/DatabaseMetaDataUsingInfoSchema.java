/*
 * Decompiled with CFR 0.150.
 */
package com.mysql.cj.jdbc;

import com.mysql.cj.Messages;
import com.mysql.cj.MysqlType;
import com.mysql.cj.ServerVersion;
import com.mysql.cj.conf.PropertyDefinitions;
import com.mysql.cj.conf.PropertyKey;
import com.mysql.cj.exceptions.CJException;
import com.mysql.cj.jdbc.DatabaseMetaData;
import com.mysql.cj.jdbc.JdbcConnection;
import com.mysql.cj.jdbc.exceptions.SQLError;
import com.mysql.cj.jdbc.exceptions.SQLExceptionsMapping;
import com.mysql.cj.jdbc.result.ResultSetFactory;
import com.mysql.cj.jdbc.result.ResultSetInternalMethods;
import com.mysql.cj.util.LRUCache;
import com.mysql.cj.util.StringUtils;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class DatabaseMetaDataUsingInfoSchema
extends DatabaseMetaData {
    private static Map<ServerVersion, String> keywordsCache = Collections.synchronizedMap(new LRUCache(10));

    protected DatabaseMetaDataUsingInfoSchema(JdbcConnection connToSet, String databaseToSet, ResultSetFactory resultSetFactory) throws SQLException {
        super(connToSet, databaseToSet, resultSetFactory);
    }

    protected ResultSet executeMetadataQuery(PreparedStatement pStmt) throws SQLException {
        ResultSet rs = pStmt.executeQuery();
        ((ResultSetInternalMethods)rs).setOwningStatement(null);
        return rs;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public ResultSet getColumnPrivileges(String catalog, String schema, String table, String columnNamePattern) throws SQLException {
        try {
            String db = this.getDatabase(catalog, schema);
            db = this.pedantic ? db : StringUtils.unQuoteIdentifier(db, this.quotedId);
            StringBuilder sqlBuf = new StringBuilder(this.databaseTerm.getValue() == PropertyDefinitions.DatabaseTerm.SCHEMA ? "SELECT TABLE_CATALOG, TABLE_SCHEMA," : "SELECT TABLE_SCHEMA, NULL,");
            sqlBuf.append(" TABLE_NAME, COLUMN_NAME, NULL AS GRANTOR, GRANTEE, PRIVILEGE_TYPE AS PRIVILEGE, IS_GRANTABLE FROM INFORMATION_SCHEMA.COLUMN_PRIVILEGES WHERE");
            if (db != null) {
                sqlBuf.append(" TABLE_SCHEMA=? AND");
            }
            sqlBuf.append(" TABLE_NAME =?");
            if (columnNamePattern != null) {
                sqlBuf.append(" AND COLUMN_NAME LIKE ?");
            }
            sqlBuf.append(" ORDER BY COLUMN_NAME, PRIVILEGE_TYPE");
            try (PreparedStatement pStmt = null;){
                pStmt = this.prepareMetaDataSafeStatement(sqlBuf.toString());
                int nextId = 1;
                pStmt.setString(nextId++, db);
                pStmt.setString(nextId++, table);
                if (columnNamePattern != null) {
                    pStmt.setString(nextId, columnNamePattern);
                }
                ResultSet rs = this.executeMetadataQuery(pStmt);
                ((ResultSetInternalMethods)rs).getColumnDefinition().setFields(this.getColumnPrivilegesFields());
                ResultSet resultSet = rs;
                return resultSet;
            }
        }
        catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public ResultSet getColumns(String catalog, String schemaPattern, String tableName, String columnNamePattern) throws SQLException {
        try {
            String db = this.getDatabase(catalog, schemaPattern);
            db = this.pedantic ? db : StringUtils.unQuoteIdentifier(db, this.quotedId);
            StringBuilder sqlBuf = new StringBuilder(this.databaseTerm.getValue() == PropertyDefinitions.DatabaseTerm.SCHEMA ? "SELECT TABLE_CATALOG, TABLE_SCHEMA," : "SELECT TABLE_SCHEMA, NULL,");
            sqlBuf.append(" TABLE_NAME, COLUMN_NAME,");
            this.appendJdbcTypeMappingQuery(sqlBuf, "DATA_TYPE", "COLUMN_TYPE");
            sqlBuf.append(" AS DATA_TYPE, ");
            sqlBuf.append("UPPER(CASE");
            if (this.tinyInt1isBit) {
                sqlBuf.append(" WHEN UPPER(DATA_TYPE)='TINYINT' THEN CASE");
                sqlBuf.append(" WHEN LOCATE('ZEROFILL', UPPER(COLUMN_TYPE)) = 0 AND LOCATE('UNSIGNED', UPPER(COLUMN_TYPE)) = 0 AND LOCATE('(1)', COLUMN_TYPE) != 0 THEN ");
                sqlBuf.append(this.transformedBitIsBoolean ? "'BOOLEAN'" : "'BIT'");
                sqlBuf.append(" WHEN LOCATE('UNSIGNED', UPPER(COLUMN_TYPE)) != 0 AND LOCATE('UNSIGNED', UPPER(DATA_TYPE)) = 0 THEN 'TINYINT UNSIGNED'");
                sqlBuf.append(" ELSE DATA_TYPE END ");
            }
            sqlBuf.append(" WHEN LOCATE('UNSIGNED', UPPER(COLUMN_TYPE)) != 0 AND LOCATE('UNSIGNED', UPPER(DATA_TYPE)) = 0 AND LOCATE('SET', UPPER(DATA_TYPE)) <> 1 AND LOCATE('ENUM', UPPER(DATA_TYPE)) <> 1 THEN CONCAT(DATA_TYPE, ' UNSIGNED')");
            sqlBuf.append(" WHEN UPPER(DATA_TYPE)='POINT' THEN 'GEOMETRY'");
            sqlBuf.append(" WHEN UPPER(DATA_TYPE)='LINESTRING' THEN 'GEOMETRY'");
            sqlBuf.append(" WHEN UPPER(DATA_TYPE)='POLYGON' THEN 'GEOMETRY'");
            sqlBuf.append(" WHEN UPPER(DATA_TYPE)='MULTIPOINT' THEN 'GEOMETRY'");
            sqlBuf.append(" WHEN UPPER(DATA_TYPE)='MULTILINESTRING' THEN 'GEOMETRY'");
            sqlBuf.append(" WHEN UPPER(DATA_TYPE)='MULTIPOLYGON' THEN 'GEOMETRY'");
            sqlBuf.append(" WHEN UPPER(DATA_TYPE)='GEOMETRYCOLLECTION' THEN 'GEOMETRY'");
            sqlBuf.append(" WHEN UPPER(DATA_TYPE)='GEOMCOLLECTION' THEN 'GEOMETRY'");
            sqlBuf.append(" ELSE UPPER(DATA_TYPE) END) AS TYPE_NAME,");
            sqlBuf.append("UPPER(CASE");
            sqlBuf.append(" WHEN UPPER(DATA_TYPE)='DATE' THEN 10");
            if (this.conn.getServerVersion().meetsMinimum(ServerVersion.parseVersion("5.6.4"))) {
                sqlBuf.append(" WHEN UPPER(DATA_TYPE)='TIME'");
                sqlBuf.append("  THEN 8+(CASE WHEN DATETIME_PRECISION>0 THEN DATETIME_PRECISION+1 ELSE DATETIME_PRECISION END)");
                sqlBuf.append(" WHEN UPPER(DATA_TYPE)='DATETIME' OR");
                sqlBuf.append("  UPPER(DATA_TYPE)='TIMESTAMP'");
                sqlBuf.append("  THEN 19+(CASE WHEN DATETIME_PRECISION>0 THEN DATETIME_PRECISION+1 ELSE DATETIME_PRECISION END)");
            } else {
                sqlBuf.append(" WHEN UPPER(DATA_TYPE)='TIME' THEN 8");
                sqlBuf.append(" WHEN UPPER(DATA_TYPE)='DATETIME' OR");
                sqlBuf.append("  UPPER(DATA_TYPE)='TIMESTAMP'");
                sqlBuf.append("  THEN 19");
            }
            sqlBuf.append(" WHEN UPPER(DATA_TYPE)='YEAR' THEN 4");
            if (this.tinyInt1isBit && !this.transformedBitIsBoolean) {
                sqlBuf.append(" WHEN UPPER(DATA_TYPE)='TINYINT' AND LOCATE('ZEROFILL', UPPER(COLUMN_TYPE)) = 0 AND LOCATE('UNSIGNED', UPPER(COLUMN_TYPE)) = 0 AND LOCATE('(1)', COLUMN_TYPE) != 0 THEN 1");
            }
            sqlBuf.append(" WHEN UPPER(DATA_TYPE)='MEDIUMINT' AND LOCATE('UNSIGNED', UPPER(COLUMN_TYPE)) != 0 THEN 8");
            sqlBuf.append(" WHEN UPPER(DATA_TYPE)='JSON' THEN 1073741824");
            sqlBuf.append(" WHEN UPPER(DATA_TYPE)='GEOMETRY' THEN 65535");
            sqlBuf.append(" WHEN UPPER(DATA_TYPE)='POINT' THEN 65535");
            sqlBuf.append(" WHEN UPPER(DATA_TYPE)='LINESTRING' THEN 65535");
            sqlBuf.append(" WHEN UPPER(DATA_TYPE)='POLYGON' THEN 65535");
            sqlBuf.append(" WHEN UPPER(DATA_TYPE)='MULTIPOINT' THEN 65535");
            sqlBuf.append(" WHEN UPPER(DATA_TYPE)='MULTILINESTRING' THEN 65535");
            sqlBuf.append(" WHEN UPPER(DATA_TYPE)='MULTIPOLYGON' THEN 65535");
            sqlBuf.append(" WHEN UPPER(DATA_TYPE)='GEOMETRYCOLLECTION' THEN 65535");
            sqlBuf.append(" WHEN UPPER(DATA_TYPE)='GEOMCOLLECTION' THEN 65535");
            sqlBuf.append(" WHEN CHARACTER_MAXIMUM_LENGTH IS NULL THEN NUMERIC_PRECISION");
            sqlBuf.append(" WHEN CHARACTER_MAXIMUM_LENGTH > ");
            sqlBuf.append(Integer.MAX_VALUE);
            sqlBuf.append(" THEN ");
            sqlBuf.append(Integer.MAX_VALUE);
            sqlBuf.append(" ELSE CHARACTER_MAXIMUM_LENGTH");
            sqlBuf.append(" END) AS COLUMN_SIZE,");
            sqlBuf.append(maxBufferSize);
            sqlBuf.append(" AS BUFFER_LENGTH,");
            sqlBuf.append("UPPER(CASE");
            sqlBuf.append(" WHEN UPPER(DATA_TYPE)='DECIMAL' THEN NUMERIC_SCALE");
            sqlBuf.append(" WHEN UPPER(DATA_TYPE)='FLOAT' OR UPPER(DATA_TYPE)='DOUBLE' THEN");
            sqlBuf.append(" CASE WHEN NUMERIC_SCALE IS NULL THEN 0");
            sqlBuf.append(" ELSE NUMERIC_SCALE END");
            sqlBuf.append(" ELSE NULL END) AS DECIMAL_DIGITS,");
            sqlBuf.append("10 AS NUM_PREC_RADIX,");
            sqlBuf.append("CASE");
            sqlBuf.append(" WHEN IS_NULLABLE='NO' THEN ");
            sqlBuf.append(0);
            sqlBuf.append(" ELSE CASE WHEN IS_NULLABLE='YES' THEN ");
            sqlBuf.append(1);
            sqlBuf.append(" ELSE ");
            sqlBuf.append(2);
            sqlBuf.append(" END END AS NULLABLE,");
            sqlBuf.append("COLUMN_COMMENT AS REMARKS,");
            sqlBuf.append("COLUMN_DEFAULT AS COLUMN_DEF,");
            sqlBuf.append("0 AS SQL_DATA_TYPE,");
            sqlBuf.append("0 AS SQL_DATETIME_SUB,");
            sqlBuf.append("CASE WHEN CHARACTER_OCTET_LENGTH > ");
            sqlBuf.append(Integer.MAX_VALUE);
            sqlBuf.append(" THEN ");
            sqlBuf.append(Integer.MAX_VALUE);
            sqlBuf.append(" ELSE CHARACTER_OCTET_LENGTH END AS CHAR_OCTET_LENGTH,");
            sqlBuf.append("ORDINAL_POSITION, IS_NULLABLE, NULL AS SCOPE_CATALOG, NULL AS SCOPE_SCHEMA, NULL AS SCOPE_TABLE, NULL AS SOURCE_DATA_TYPE,");
            sqlBuf.append("IF (EXTRA LIKE '%auto_increment%','YES','NO') AS IS_AUTOINCREMENT, ");
            sqlBuf.append("IF (EXTRA LIKE '%GENERATED%','YES','NO') AS IS_GENERATEDCOLUMN ");
            sqlBuf.append("FROM INFORMATION_SCHEMA.COLUMNS");
            StringBuilder conditionBuf = new StringBuilder();
            if (db != null) {
                conditionBuf.append("information_schema".equalsIgnoreCase(db) || "performance_schema".equalsIgnoreCase(db) || !StringUtils.hasWildcards(db) || this.databaseTerm.getValue() == PropertyDefinitions.DatabaseTerm.CATALOG ? " TABLE_SCHEMA = ?" : " TABLE_SCHEMA LIKE ?");
            }
            if (tableName != null) {
                if (conditionBuf.length() > 0) {
                    conditionBuf.append(" AND");
                }
                conditionBuf.append(StringUtils.hasWildcards(tableName) ? " TABLE_NAME LIKE ?" : " TABLE_NAME = ?");
            }
            if (columnNamePattern != null) {
                if (conditionBuf.length() > 0) {
                    conditionBuf.append(" AND");
                }
                conditionBuf.append(StringUtils.hasWildcards(columnNamePattern) ? " COLUMN_NAME LIKE ?" : " COLUMN_NAME = ?");
            }
            if (conditionBuf.length() > 0) {
                sqlBuf.append(" WHERE");
            }
            sqlBuf.append((CharSequence)conditionBuf);
            sqlBuf.append(" ORDER BY TABLE_SCHEMA, TABLE_NAME, ORDINAL_POSITION");
            try (PreparedStatement pStmt = null;){
                pStmt = this.prepareMetaDataSafeStatement(sqlBuf.toString());
                int nextId = 1;
                if (db != null) {
                    pStmt.setString(nextId++, db);
                }
                if (tableName != null) {
                    pStmt.setString(nextId++, tableName);
                }
                if (columnNamePattern != null) {
                    pStmt.setString(nextId, columnNamePattern);
                }
                ResultSet rs = this.executeMetadataQuery(pStmt);
                ((ResultSetInternalMethods)rs).getColumnDefinition().setFields(this.createColumnsFields());
                ResultSet resultSet = rs;
                return resultSet;
            }
        }
        catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public ResultSet getCrossReference(String primaryCatalog, String primarySchema, String primaryTable, String foreignCatalog, String foreignSchema, String foreignTable) throws SQLException {
        try {
            if (primaryTable == null) {
                throw SQLError.createSQLException(Messages.getString("DatabaseMetaData.2"), "S1009", this.getExceptionInterceptor());
            }
            String primaryDb = this.getDatabase(primaryCatalog, primarySchema);
            String foreignDb = this.getDatabase(foreignCatalog, foreignSchema);
            primaryDb = this.pedantic ? primaryDb : StringUtils.unQuoteIdentifier(primaryDb, this.quotedId);
            foreignDb = this.pedantic ? foreignDb : StringUtils.unQuoteIdentifier(foreignDb, this.quotedId);
            StringBuilder sqlBuf = new StringBuilder(this.databaseTerm.getValue() == PropertyDefinitions.DatabaseTerm.SCHEMA ? "SELECT A.CONSTRAINT_CATALOG AS PKTABLE_CAT, A.REFERENCED_TABLE_SCHEMA AS PKTABLE_SCHEM," : "SELECT A.REFERENCED_TABLE_SCHEMA AS PKTABLE_CAT,NULL AS PKTABLE_SCHEM,");
            sqlBuf.append(" A.REFERENCED_TABLE_NAME AS PKTABLE_NAME, A.REFERENCED_COLUMN_NAME AS PKCOLUMN_NAME,");
            sqlBuf.append(this.databaseTerm.getValue() == PropertyDefinitions.DatabaseTerm.SCHEMA ? " A.TABLE_CATALOG AS FKTABLE_CAT, A.TABLE_SCHEMA AS FKTABLE_SCHEM," : " A.TABLE_SCHEMA AS FKTABLE_CAT, NULL AS FKTABLE_SCHEM,");
            sqlBuf.append(" A.TABLE_NAME AS FKTABLE_NAME, A.COLUMN_NAME AS FKCOLUMN_NAME, A.ORDINAL_POSITION AS KEY_SEQ,");
            sqlBuf.append(this.generateUpdateRuleClause());
            sqlBuf.append(" AS UPDATE_RULE,");
            sqlBuf.append(this.generateDeleteRuleClause());
            sqlBuf.append(" AS DELETE_RULE, A.CONSTRAINT_NAME AS FK_NAME,");
            sqlBuf.append(" (SELECT CONSTRAINT_NAME FROM INFORMATION_SCHEMA.TABLE_CONSTRAINTS WHERE TABLE_SCHEMA = A.REFERENCED_TABLE_SCHEMA");
            sqlBuf.append(" AND TABLE_NAME = A.REFERENCED_TABLE_NAME AND CONSTRAINT_TYPE IN ('UNIQUE','PRIMARY KEY') LIMIT 1) AS PK_NAME, ");
            sqlBuf.append(7);
            sqlBuf.append(" AS DEFERRABILITY FROM INFORMATION_SCHEMA.KEY_COLUMN_USAGE A");
            sqlBuf.append(" JOIN INFORMATION_SCHEMA.TABLE_CONSTRAINTS B USING (TABLE_SCHEMA, TABLE_NAME, CONSTRAINT_NAME) ");
            sqlBuf.append(this.generateOptionalRefContraintsJoin());
            sqlBuf.append("WHERE B.CONSTRAINT_TYPE = 'FOREIGN KEY'");
            if (primaryDb != null) {
                sqlBuf.append(" AND A.REFERENCED_TABLE_SCHEMA=?");
            }
            sqlBuf.append(" AND A.REFERENCED_TABLE_NAME=?");
            if (foreignDb != null) {
                sqlBuf.append(" AND A.TABLE_SCHEMA = ?");
            }
            sqlBuf.append(" AND A.TABLE_NAME=?");
            sqlBuf.append(" ORDER BY A.TABLE_SCHEMA, A.TABLE_NAME, A.ORDINAL_POSITION");
            try (PreparedStatement pStmt = null;){
                pStmt = this.prepareMetaDataSafeStatement(sqlBuf.toString());
                int nextId = 1;
                if (primaryDb != null) {
                    pStmt.setString(nextId++, primaryDb);
                }
                pStmt.setString(nextId++, primaryTable);
                if (foreignDb != null) {
                    pStmt.setString(nextId++, foreignDb);
                }
                pStmt.setString(nextId, foreignTable);
                ResultSet rs = this.executeMetadataQuery(pStmt);
                ((ResultSetInternalMethods)rs).getColumnDefinition().setFields(this.createFkMetadataFields());
                ResultSet resultSet = rs;
                return resultSet;
            }
        }
        catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public ResultSet getExportedKeys(String catalog, String schema, String table) throws SQLException {
        try {
            if (table == null) {
                throw SQLError.createSQLException(Messages.getString("DatabaseMetaData.2"), "S1009", this.getExceptionInterceptor());
            }
            String db = this.getDatabase(catalog, schema);
            db = this.pedantic ? db : StringUtils.unQuoteIdentifier(db, this.quotedId);
            StringBuilder sqlBuf = new StringBuilder(this.databaseTerm.getValue() == PropertyDefinitions.DatabaseTerm.SCHEMA ? "SELECT A.CONSTRAINT_CATALOG AS PKTABLE_CAT, A.REFERENCED_TABLE_SCHEMA AS PKTABLE_SCHEM," : "SELECT A.REFERENCED_TABLE_SCHEMA AS PKTABLE_CAT,NULL AS PKTABLE_SCHEM,");
            sqlBuf.append(" A.REFERENCED_TABLE_NAME AS PKTABLE_NAME, A.REFERENCED_COLUMN_NAME AS PKCOLUMN_NAME,");
            sqlBuf.append(this.databaseTerm.getValue() == PropertyDefinitions.DatabaseTerm.SCHEMA ? " A.TABLE_CATALOG AS FKTABLE_CAT, A.TABLE_SCHEMA AS FKTABLE_SCHEM," : " A.TABLE_SCHEMA AS FKTABLE_CAT, NULL AS FKTABLE_SCHEM,");
            sqlBuf.append(" A.TABLE_NAME AS FKTABLE_NAME, A.COLUMN_NAME AS FKCOLUMN_NAME, A.ORDINAL_POSITION AS KEY_SEQ,");
            sqlBuf.append(this.generateUpdateRuleClause());
            sqlBuf.append(" AS UPDATE_RULE,");
            sqlBuf.append(this.generateDeleteRuleClause());
            sqlBuf.append(" AS DELETE_RULE, A.CONSTRAINT_NAME AS FK_NAME, (SELECT CONSTRAINT_NAME FROM");
            sqlBuf.append(" INFORMATION_SCHEMA.TABLE_CONSTRAINTS WHERE TABLE_SCHEMA = A.REFERENCED_TABLE_SCHEMA AND");
            sqlBuf.append(" TABLE_NAME = A.REFERENCED_TABLE_NAME AND CONSTRAINT_TYPE IN ('UNIQUE','PRIMARY KEY') LIMIT 1) AS PK_NAME,");
            sqlBuf.append(7);
            sqlBuf.append(" AS DEFERRABILITY FROM INFORMATION_SCHEMA.KEY_COLUMN_USAGE A");
            sqlBuf.append(" JOIN INFORMATION_SCHEMA.TABLE_CONSTRAINTS B USING (TABLE_SCHEMA, TABLE_NAME, CONSTRAINT_NAME) ");
            sqlBuf.append(this.generateOptionalRefContraintsJoin());
            sqlBuf.append(" WHERE B.CONSTRAINT_TYPE = 'FOREIGN KEY'");
            if (db != null) {
                sqlBuf.append(" AND A.REFERENCED_TABLE_SCHEMA = ?");
            }
            sqlBuf.append(" AND A.REFERENCED_TABLE_NAME=?");
            sqlBuf.append(" ORDER BY A.TABLE_SCHEMA, A.TABLE_NAME, A.ORDINAL_POSITION");
            try (PreparedStatement pStmt = null;){
                pStmt = this.prepareMetaDataSafeStatement(sqlBuf.toString());
                int nextId = 1;
                if (db != null) {
                    pStmt.setString(nextId++, db);
                }
                pStmt.setString(nextId, table);
                ResultSet rs = this.executeMetadataQuery(pStmt);
                ((ResultSetInternalMethods)rs).getColumnDefinition().setFields(this.createFkMetadataFields());
                ResultSet resultSet = rs;
                return resultSet;
            }
        }
        catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    private String generateOptionalRefContraintsJoin() {
        return "JOIN INFORMATION_SCHEMA.REFERENTIAL_CONSTRAINTS R ON (R.CONSTRAINT_NAME = B.CONSTRAINT_NAME AND R.TABLE_NAME = B.TABLE_NAME AND R.CONSTRAINT_SCHEMA = B.TABLE_SCHEMA) ";
    }

    private String generateDeleteRuleClause() {
        return "CASE WHEN R.DELETE_RULE='CASCADE' THEN " + String.valueOf(0) + " WHEN R.DELETE_RULE='SET NULL' THEN " + String.valueOf(2) + " WHEN R.DELETE_RULE='SET DEFAULT' THEN " + String.valueOf(4) + " WHEN R.DELETE_RULE='RESTRICT' THEN " + String.valueOf(1) + " WHEN R.DELETE_RULE='NO ACTION' THEN " + String.valueOf(1) + " ELSE " + String.valueOf(1) + " END ";
    }

    private String generateUpdateRuleClause() {
        return "CASE WHEN R.UPDATE_RULE='CASCADE' THEN " + String.valueOf(0) + " WHEN R.UPDATE_RULE='SET NULL' THEN " + String.valueOf(2) + " WHEN R.UPDATE_RULE='SET DEFAULT' THEN " + String.valueOf(4) + " WHEN R.UPDATE_RULE='RESTRICT' THEN " + String.valueOf(1) + " WHEN R.UPDATE_RULE='NO ACTION' THEN " + String.valueOf(1) + " ELSE " + String.valueOf(1) + " END ";
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public ResultSet getImportedKeys(String catalog, String schema, String table) throws SQLException {
        try {
            if (table == null) {
                throw SQLError.createSQLException(Messages.getString("DatabaseMetaData.2"), "S1009", this.getExceptionInterceptor());
            }
            String db = this.getDatabase(catalog, schema);
            db = this.pedantic ? db : StringUtils.unQuoteIdentifier(db, this.quotedId);
            StringBuilder sqlBuf = new StringBuilder(this.databaseTerm.getValue() == PropertyDefinitions.DatabaseTerm.SCHEMA ? "SELECT A.CONSTRAINT_CATALOG AS PKTABLE_CAT, A.REFERENCED_TABLE_SCHEMA AS PKTABLE_SCHEM," : "SELECT A.REFERENCED_TABLE_SCHEMA AS PKTABLE_CAT,NULL AS PKTABLE_SCHEM,");
            sqlBuf.append(" A.REFERENCED_TABLE_NAME AS PKTABLE_NAME, A.REFERENCED_COLUMN_NAME AS PKCOLUMN_NAME,");
            sqlBuf.append(this.databaseTerm.getValue() == PropertyDefinitions.DatabaseTerm.SCHEMA ? " A.TABLE_CATALOG AS FKTABLE_CAT, A.TABLE_SCHEMA AS FKTABLE_SCHEM," : " A.TABLE_SCHEMA AS FKTABLE_CAT, NULL AS FKTABLE_SCHEM,");
            sqlBuf.append(" A.TABLE_NAME AS FKTABLE_NAME, A.COLUMN_NAME AS FKCOLUMN_NAME, A.ORDINAL_POSITION AS KEY_SEQ,");
            sqlBuf.append(this.generateUpdateRuleClause());
            sqlBuf.append(" AS UPDATE_RULE,");
            sqlBuf.append(this.generateDeleteRuleClause());
            sqlBuf.append(" AS DELETE_RULE, A.CONSTRAINT_NAME AS FK_NAME, (SELECT CONSTRAINT_NAME FROM");
            sqlBuf.append(" INFORMATION_SCHEMA.TABLE_CONSTRAINTS WHERE TABLE_SCHEMA = A.REFERENCED_TABLE_SCHEMA AND");
            sqlBuf.append(" TABLE_NAME = A.REFERENCED_TABLE_NAME AND CONSTRAINT_TYPE IN ('UNIQUE','PRIMARY KEY') LIMIT 1) AS PK_NAME,");
            sqlBuf.append(7);
            sqlBuf.append(" AS DEFERRABILITY FROM INFORMATION_SCHEMA.KEY_COLUMN_USAGE A");
            sqlBuf.append(" JOIN INFORMATION_SCHEMA.TABLE_CONSTRAINTS B USING (CONSTRAINT_NAME, TABLE_NAME) ");
            sqlBuf.append(this.generateOptionalRefContraintsJoin());
            sqlBuf.append("WHERE B.CONSTRAINT_TYPE = 'FOREIGN KEY'");
            if (db != null) {
                sqlBuf.append(" AND A.TABLE_SCHEMA = ?");
            }
            sqlBuf.append(" AND A.TABLE_NAME=?");
            sqlBuf.append(" AND A.REFERENCED_TABLE_SCHEMA IS NOT NULL");
            sqlBuf.append(" ORDER BY A.REFERENCED_TABLE_SCHEMA, A.REFERENCED_TABLE_NAME, A.ORDINAL_POSITION");
            try (PreparedStatement pStmt = null;){
                pStmt = this.prepareMetaDataSafeStatement(sqlBuf.toString());
                int nextId = 1;
                if (db != null) {
                    pStmt.setString(nextId++, db);
                }
                pStmt.setString(nextId, table);
                ResultSet rs = this.executeMetadataQuery(pStmt);
                ((ResultSetInternalMethods)rs).getColumnDefinition().setFields(this.createFkMetadataFields());
                ResultSet resultSet = rs;
                return resultSet;
            }
        }
        catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public ResultSet getIndexInfo(String catalog, String schema, String table, boolean unique, boolean approximate) throws SQLException {
        try {
            String db = this.getDatabase(catalog, schema);
            db = this.pedantic ? db : StringUtils.unQuoteIdentifier(db, this.quotedId);
            StringBuilder sqlBuf = new StringBuilder(this.databaseTerm.getValue() == PropertyDefinitions.DatabaseTerm.SCHEMA ? "SELECT TABLE_CATALOG AS TABLE_CAT, TABLE_SCHEMA AS TABLE_SCHEM," : "SELECT TABLE_SCHEMA AS TABLE_CAT, NULL AS TABLE_SCHEM,");
            sqlBuf.append(" TABLE_NAME, NON_UNIQUE, NULL AS INDEX_QUALIFIER, INDEX_NAME,");
            sqlBuf.append(3);
            sqlBuf.append(" AS TYPE, SEQ_IN_INDEX AS ORDINAL_POSITION, COLUMN_NAME,");
            sqlBuf.append("COLLATION AS ASC_OR_DESC, CARDINALITY, 0 AS PAGES, NULL AS FILTER_CONDITION FROM INFORMATION_SCHEMA.STATISTICS WHERE");
            if (db != null) {
                sqlBuf.append(" TABLE_SCHEMA = ? AND");
            }
            sqlBuf.append(" TABLE_NAME = ?");
            if (unique) {
                sqlBuf.append(" AND NON_UNIQUE=0 ");
            }
            sqlBuf.append("ORDER BY NON_UNIQUE, INDEX_NAME, SEQ_IN_INDEX");
            try (PreparedStatement pStmt = null;){
                pStmt = this.prepareMetaDataSafeStatement(sqlBuf.toString());
                int nextId = 1;
                if (db != null) {
                    pStmt.setString(nextId++, db);
                }
                pStmt.setString(nextId, table);
                ResultSet rs = this.executeMetadataQuery(pStmt);
                ((ResultSetInternalMethods)rs).getColumnDefinition().setFields(this.createIndexInfoFields());
                ResultSet resultSet = rs;
                return resultSet;
            }
        }
        catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public ResultSet getPrimaryKeys(String catalog, String schema, String table) throws SQLException {
        try {
            if (table == null) {
                throw SQLError.createSQLException(Messages.getString("DatabaseMetaData.2"), "S1009", this.getExceptionInterceptor());
            }
            String db = this.getDatabase(catalog, schema);
            db = this.pedantic ? db : StringUtils.unQuoteIdentifier(db, this.quotedId);
            StringBuilder sqlBuf = new StringBuilder(this.databaseTerm.getValue() == PropertyDefinitions.DatabaseTerm.SCHEMA ? "SELECT TABLE_CATALOG AS TABLE_CAT, TABLE_SCHEMA AS TABLE_SCHEM," : "SELECT TABLE_SCHEMA AS TABLE_CAT, NULL AS TABLE_SCHEM,");
            sqlBuf.append(" TABLE_NAME, COLUMN_NAME, SEQ_IN_INDEX AS KEY_SEQ, 'PRIMARY' AS PK_NAME FROM INFORMATION_SCHEMA.STATISTICS WHERE");
            if (db != null) {
                sqlBuf.append(" TABLE_SCHEMA = ? AND");
            }
            sqlBuf.append(" TABLE_NAME = ?");
            sqlBuf.append(" AND INDEX_NAME='PRIMARY' ORDER BY TABLE_SCHEMA, TABLE_NAME, INDEX_NAME, SEQ_IN_INDEX");
            try (PreparedStatement pStmt = null;){
                pStmt = this.prepareMetaDataSafeStatement(sqlBuf.toString());
                int nextId = 1;
                if (db != null) {
                    pStmt.setString(nextId++, db);
                }
                pStmt.setString(nextId, table);
                ResultSet rs = this.executeMetadataQuery(pStmt);
                ((ResultSetInternalMethods)rs).getColumnDefinition().setFields(this.getPrimaryKeysFields());
                ResultSet resultSet = rs;
                return resultSet;
            }
        }
        catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public ResultSet getProcedures(String catalog, String schemaPattern, String procedureNamePattern) throws SQLException {
        try {
            String db = this.getDatabase(catalog, schemaPattern);
            db = this.pedantic ? db : StringUtils.unQuoteIdentifier(db, this.quotedId);
            StringBuilder sqlBuf = new StringBuilder(this.databaseTerm.getValue() == PropertyDefinitions.DatabaseTerm.SCHEMA ? "SELECT ROUTINE_CATALOG AS PROCEDURE_CAT, ROUTINE_SCHEMA AS PROCEDURE_SCHEM," : "SELECT ROUTINE_SCHEMA AS PROCEDURE_CAT, NULL AS PROCEDURE_SCHEM,");
            sqlBuf.append(" ROUTINE_NAME AS PROCEDURE_NAME, NULL AS RESERVED_1, NULL AS RESERVED_2, NULL AS RESERVED_3, ROUTINE_COMMENT AS REMARKS, CASE WHEN ROUTINE_TYPE = 'PROCEDURE' THEN ");
            sqlBuf.append(1);
            sqlBuf.append(" WHEN ROUTINE_TYPE='FUNCTION' THEN ");
            sqlBuf.append(2);
            sqlBuf.append(" ELSE ");
            sqlBuf.append(0);
            sqlBuf.append(" END AS PROCEDURE_TYPE, ROUTINE_NAME AS SPECIFIC_NAME FROM INFORMATION_SCHEMA.ROUTINES");
            StringBuilder conditionBuf = new StringBuilder();
            if (!this.conn.getPropertySet().getBooleanProperty(PropertyKey.getProceduresReturnsFunctions).getValue().booleanValue()) {
                conditionBuf.append(" ROUTINE_TYPE = 'PROCEDURE'");
            }
            if (db != null) {
                if (conditionBuf.length() > 0) {
                    conditionBuf.append(" AND");
                }
                conditionBuf.append(this.databaseTerm.getValue() == PropertyDefinitions.DatabaseTerm.SCHEMA ? " ROUTINE_SCHEMA LIKE ?" : " ROUTINE_SCHEMA = ?");
            }
            if (procedureNamePattern != null) {
                if (conditionBuf.length() > 0) {
                    conditionBuf.append(" AND");
                }
                conditionBuf.append(" ROUTINE_NAME LIKE ?");
            }
            if (conditionBuf.length() > 0) {
                sqlBuf.append(" WHERE");
                sqlBuf.append((CharSequence)conditionBuf);
            }
            sqlBuf.append(" ORDER BY ROUTINE_SCHEMA, ROUTINE_NAME, ROUTINE_TYPE");
            try (PreparedStatement pStmt = null;){
                pStmt = this.prepareMetaDataSafeStatement(sqlBuf.toString());
                int nextId = 1;
                if (db != null) {
                    pStmt.setString(nextId++, db);
                }
                if (procedureNamePattern != null) {
                    pStmt.setString(nextId, procedureNamePattern);
                }
                ResultSet rs = this.executeMetadataQuery(pStmt);
                ((ResultSetInternalMethods)rs).getColumnDefinition().setFields(this.createFieldMetadataForGetProcedures());
                ResultSet resultSet = rs;
                return resultSet;
            }
        }
        catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public ResultSet getProcedureColumns(String catalog, String schemaPattern, String procedureNamePattern, String columnNamePattern) throws SQLException {
        try {
            String db = this.getDatabase(catalog, schemaPattern);
            db = this.pedantic ? db : StringUtils.unQuoteIdentifier(db, this.quotedId);
            boolean supportsFractSeconds = this.conn.getServerVersion().meetsMinimum(ServerVersion.parseVersion("5.6.4"));
            StringBuilder sqlBuf = new StringBuilder(this.databaseTerm.getValue() == PropertyDefinitions.DatabaseTerm.SCHEMA ? "SELECT SPECIFIC_CATALOG AS PROCEDURE_CAT, SPECIFIC_SCHEMA AS `PROCEDURE_SCHEM`," : "SELECT SPECIFIC_SCHEMA AS PROCEDURE_CAT, NULL AS `PROCEDURE_SCHEM`,");
            sqlBuf.append(" SPECIFIC_NAME AS `PROCEDURE_NAME`, IFNULL(PARAMETER_NAME, '') AS `COLUMN_NAME`,");
            sqlBuf.append(" CASE WHEN PARAMETER_MODE = 'IN' THEN ");
            sqlBuf.append(1);
            sqlBuf.append(" WHEN PARAMETER_MODE = 'OUT' THEN ");
            sqlBuf.append(4);
            sqlBuf.append(" WHEN PARAMETER_MODE = 'INOUT' THEN ");
            sqlBuf.append(2);
            sqlBuf.append(" WHEN ORDINAL_POSITION = 0 THEN ");
            sqlBuf.append(5);
            sqlBuf.append(" ELSE ");
            sqlBuf.append(0);
            sqlBuf.append(" END AS `COLUMN_TYPE`, ");
            this.appendJdbcTypeMappingQuery(sqlBuf, "DATA_TYPE", "DTD_IDENTIFIER");
            sqlBuf.append(" AS `DATA_TYPE`, ");
            sqlBuf.append("UPPER(CASE");
            if (this.tinyInt1isBit) {
                sqlBuf.append(" WHEN UPPER(DATA_TYPE)='TINYINT' THEN CASE");
                sqlBuf.append(" WHEN LOCATE('ZEROFILL', UPPER(DTD_IDENTIFIER)) = 0 AND LOCATE('UNSIGNED', UPPER(DTD_IDENTIFIER)) = 0 AND LOCATE('(1)', DTD_IDENTIFIER) != 0 THEN ");
                sqlBuf.append(this.transformedBitIsBoolean ? "'BOOLEAN'" : "'BIT'");
                sqlBuf.append(" WHEN LOCATE('UNSIGNED', UPPER(DTD_IDENTIFIER)) != 0 AND LOCATE('UNSIGNED', UPPER(DATA_TYPE)) = 0 THEN 'TINYINT UNSIGNED'");
                sqlBuf.append(" ELSE DATA_TYPE END ");
            }
            sqlBuf.append(" WHEN LOCATE('UNSIGNED', UPPER(DTD_IDENTIFIER)) != 0 AND LOCATE('UNSIGNED', UPPER(DATA_TYPE)) = 0 AND LOCATE('SET', UPPER(DATA_TYPE)) <> 1 AND LOCATE('ENUM', UPPER(DATA_TYPE)) <> 1 THEN CONCAT(DATA_TYPE, ' UNSIGNED')");
            sqlBuf.append(" WHEN UPPER(DATA_TYPE)='POINT' THEN 'GEOMETRY'");
            sqlBuf.append(" WHEN UPPER(DATA_TYPE)='LINESTRING' THEN 'GEOMETRY'");
            sqlBuf.append(" WHEN UPPER(DATA_TYPE)='POLYGON' THEN 'GEOMETRY'");
            sqlBuf.append(" WHEN UPPER(DATA_TYPE)='MULTIPOINT' THEN 'GEOMETRY'");
            sqlBuf.append(" WHEN UPPER(DATA_TYPE)='MULTILINESTRING' THEN 'GEOMETRY'");
            sqlBuf.append(" WHEN UPPER(DATA_TYPE)='MULTIPOLYGON' THEN 'GEOMETRY'");
            sqlBuf.append(" WHEN UPPER(DATA_TYPE)='GEOMETRYCOLLECTION' THEN 'GEOMETRY'");
            sqlBuf.append(" WHEN UPPER(DATA_TYPE)='GEOMCOLLECTION' THEN 'GEOMETRY'");
            sqlBuf.append(" ELSE UPPER(DATA_TYPE) END) AS TYPE_NAME,");
            sqlBuf.append(" CASE WHEN LCASE(DATA_TYPE)='date' THEN 0");
            if (supportsFractSeconds) {
                sqlBuf.append(" WHEN LCASE(DATA_TYPE)='time' OR LCASE(DATA_TYPE)='datetime' OR LCASE(DATA_TYPE)='timestamp' THEN DATETIME_PRECISION");
            } else {
                sqlBuf.append(" WHEN LCASE(DATA_TYPE)='time' OR LCASE(DATA_TYPE)='datetime' OR LCASE(DATA_TYPE)='timestamp' THEN 0");
            }
            if (this.tinyInt1isBit && !this.transformedBitIsBoolean) {
                sqlBuf.append(" WHEN (UPPER(DATA_TYPE)='TINYINT' AND LOCATE('ZEROFILL', UPPER(DTD_IDENTIFIER)) = 0) AND LOCATE('UNSIGNED', UPPER(DTD_IDENTIFIER)) = 0 AND LOCATE('(1)', DTD_IDENTIFIER) != 0 THEN 1");
            }
            sqlBuf.append(" WHEN UPPER(DATA_TYPE)='MEDIUMINT' AND LOCATE('UNSIGNED', UPPER(DTD_IDENTIFIER)) != 0 THEN 8");
            sqlBuf.append(" WHEN UPPER(DATA_TYPE)='JSON' THEN 1073741824");
            sqlBuf.append(" ELSE NUMERIC_PRECISION END AS `PRECISION`,");
            sqlBuf.append(" CASE WHEN LCASE(DATA_TYPE)='date' THEN 10");
            if (supportsFractSeconds) {
                sqlBuf.append(" WHEN LCASE(DATA_TYPE)='time' THEN 8+(CASE WHEN DATETIME_PRECISION>0 THEN DATETIME_PRECISION+1 ELSE DATETIME_PRECISION END)");
                sqlBuf.append(" WHEN LCASE(DATA_TYPE)='datetime' OR LCASE(DATA_TYPE)='timestamp'");
                sqlBuf.append("  THEN 19+(CASE WHEN DATETIME_PRECISION>0 THEN DATETIME_PRECISION+1 ELSE DATETIME_PRECISION END)");
            } else {
                sqlBuf.append(" WHEN LCASE(DATA_TYPE)='time' THEN 8");
                sqlBuf.append(" WHEN LCASE(DATA_TYPE)='datetime' OR LCASE(DATA_TYPE)='timestamp' THEN 19");
            }
            if (this.tinyInt1isBit && !this.transformedBitIsBoolean) {
                sqlBuf.append(" WHEN (UPPER(DATA_TYPE)='TINYINT' OR UPPER(DATA_TYPE)='TINYINT UNSIGNED') AND LOCATE('ZEROFILL', UPPER(DTD_IDENTIFIER)) = 0 AND LOCATE('UNSIGNED', UPPER(DTD_IDENTIFIER)) = 0 AND LOCATE('(1)', DTD_IDENTIFIER) != 0 THEN 1");
            }
            sqlBuf.append(" WHEN UPPER(DATA_TYPE)='MEDIUMINT' AND LOCATE('UNSIGNED', UPPER(DTD_IDENTIFIER)) != 0 THEN 8");
            sqlBuf.append(" WHEN UPPER(DATA_TYPE)='JSON' THEN 1073741824");
            sqlBuf.append(" WHEN CHARACTER_MAXIMUM_LENGTH IS NULL THEN NUMERIC_PRECISION");
            sqlBuf.append(" WHEN CHARACTER_MAXIMUM_LENGTH > ");
            sqlBuf.append(Integer.MAX_VALUE);
            sqlBuf.append(" THEN ");
            sqlBuf.append(Integer.MAX_VALUE);
            sqlBuf.append(" ELSE CHARACTER_MAXIMUM_LENGTH END AS LENGTH,");
            sqlBuf.append("NUMERIC_SCALE AS `SCALE`, ");
            sqlBuf.append("10 AS RADIX,");
            sqlBuf.append(1);
            sqlBuf.append(" AS `NULLABLE`, NULL AS `REMARKS`, NULL AS `COLUMN_DEF`, NULL AS `SQL_DATA_TYPE`, NULL AS `SQL_DATETIME_SUB`,");
            sqlBuf.append(" CASE WHEN CHARACTER_OCTET_LENGTH > ");
            sqlBuf.append(Integer.MAX_VALUE);
            sqlBuf.append(" THEN ");
            sqlBuf.append(Integer.MAX_VALUE);
            sqlBuf.append(" ELSE CHARACTER_OCTET_LENGTH END AS `CHAR_OCTET_LENGTH`,");
            sqlBuf.append(" ORDINAL_POSITION, 'YES' AS `IS_NULLABLE`, SPECIFIC_NAME");
            sqlBuf.append(" FROM INFORMATION_SCHEMA.PARAMETERS");
            StringBuilder conditionBuf = new StringBuilder();
            if (!this.conn.getPropertySet().getBooleanProperty(PropertyKey.getProceduresReturnsFunctions).getValue().booleanValue()) {
                conditionBuf.append(" ROUTINE_TYPE = 'PROCEDURE'");
            }
            if (db != null) {
                if (conditionBuf.length() > 0) {
                    conditionBuf.append(" AND");
                }
                conditionBuf.append(this.databaseTerm.getValue() == PropertyDefinitions.DatabaseTerm.SCHEMA ? " SPECIFIC_SCHEMA LIKE ?" : " SPECIFIC_SCHEMA = ?");
            }
            if (procedureNamePattern != null) {
                if (conditionBuf.length() > 0) {
                    conditionBuf.append(" AND");
                }
                conditionBuf.append(" SPECIFIC_NAME LIKE ?");
            }
            if (columnNamePattern != null) {
                if (conditionBuf.length() > 0) {
                    conditionBuf.append(" AND");
                }
                conditionBuf.append(" (PARAMETER_NAME LIKE ? OR PARAMETER_NAME IS NULL)");
            }
            if (conditionBuf.length() > 0) {
                sqlBuf.append(" WHERE");
                sqlBuf.append((CharSequence)conditionBuf);
            }
            sqlBuf.append(" ORDER BY SPECIFIC_SCHEMA, SPECIFIC_NAME, ROUTINE_TYPE, ORDINAL_POSITION");
            try (PreparedStatement pStmt = null;){
                pStmt = this.prepareMetaDataSafeStatement(sqlBuf.toString());
                int nextId = 1;
                if (db != null) {
                    pStmt.setString(nextId++, db);
                }
                if (procedureNamePattern != null) {
                    pStmt.setString(nextId++, procedureNamePattern);
                }
                if (columnNamePattern != null) {
                    pStmt.setString(nextId, columnNamePattern);
                }
                ResultSet rs = this.executeMetadataQuery(pStmt);
                ((ResultSetInternalMethods)rs).getColumnDefinition().setFields(this.createProcedureColumnsFields());
                ResultSet resultSet = rs;
                return resultSet;
            }
        }
        catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public ResultSet getTables(String catalog, String schemaPattern, String tableNamePattern, String[] types) throws SQLException {
        try {
            List<String> parseList;
            String db = this.getDatabase(catalog, schemaPattern);
            String string = db = this.pedantic ? db : StringUtils.unQuoteIdentifier(db, this.quotedId);
            if (tableNamePattern != null && (parseList = StringUtils.splitDBdotName(tableNamePattern, db, this.quotedId, this.session.getServerSession().isNoBackslashEscapesSet())).size() == 2) {
                tableNamePattern = parseList.get(1);
            }
            PreparedStatement pStmt = null;
            StringBuilder sqlBuf = new StringBuilder(this.databaseTerm.getValue() == PropertyDefinitions.DatabaseTerm.SCHEMA ? "SELECT TABLE_CATALOG AS TABLE_CAT, TABLE_SCHEMA AS TABLE_SCHEM," : "SELECT TABLE_SCHEMA AS TABLE_CAT, NULL AS TABLE_SCHEM,");
            sqlBuf.append(" TABLE_NAME, CASE WHEN TABLE_TYPE='BASE TABLE' THEN CASE WHEN TABLE_SCHEMA = 'mysql' OR TABLE_SCHEMA = 'performance_schema' THEN 'SYSTEM TABLE' ");
            sqlBuf.append("ELSE 'TABLE' END WHEN TABLE_TYPE='TEMPORARY' THEN 'LOCAL_TEMPORARY' ELSE TABLE_TYPE END AS TABLE_TYPE, ");
            sqlBuf.append("TABLE_COMMENT AS REMARKS, NULL AS TYPE_CAT, NULL AS TYPE_SCHEM, NULL AS TYPE_NAME, NULL AS SELF_REFERENCING_COL_NAME, ");
            sqlBuf.append("NULL AS REF_GENERATION FROM INFORMATION_SCHEMA.TABLES");
            if (db != null || tableNamePattern != null) {
                sqlBuf.append(" WHERE");
            }
            if (db != null) {
                sqlBuf.append("information_schema".equalsIgnoreCase(db) || "performance_schema".equalsIgnoreCase(db) || !StringUtils.hasWildcards(db) || this.databaseTerm.getValue() == PropertyDefinitions.DatabaseTerm.CATALOG ? " TABLE_SCHEMA = ?" : " TABLE_SCHEMA LIKE ?");
            }
            if (tableNamePattern != null) {
                if (db != null) {
                    sqlBuf.append(" AND");
                }
                sqlBuf.append(StringUtils.hasWildcards(tableNamePattern) ? " TABLE_NAME LIKE ?" : " TABLE_NAME = ?");
            }
            if (types != null && types.length > 0) {
                sqlBuf.append(" HAVING TABLE_TYPE IN (?,?,?,?,?)");
            }
            sqlBuf.append(" ORDER BY TABLE_TYPE, TABLE_SCHEMA, TABLE_NAME");
            try {
                pStmt = this.prepareMetaDataSafeStatement(sqlBuf.toString());
                int nextId = 1;
                if (db != null) {
                    pStmt.setString(nextId++, db != null ? db : "%");
                }
                if (tableNamePattern != null) {
                    pStmt.setString(nextId++, tableNamePattern);
                }
                if (types != null && types.length > 0) {
                    int i;
                    for (i = 0; i < 5; ++i) {
                        pStmt.setNull(nextId + i, MysqlType.VARCHAR.getJdbcType());
                    }
                    for (i = 0; i < types.length; ++i) {
                        DatabaseMetaData.TableType tableType = DatabaseMetaData.TableType.getTableTypeEqualTo(types[i]);
                        if (tableType == DatabaseMetaData.TableType.UNKNOWN) continue;
                        pStmt.setString(nextId++, tableType.getName());
                    }
                }
                ResultSet rs = this.executeMetadataQuery(pStmt);
                ((ResultSetInternalMethods)rs).setColumnDefinition(this.createTablesFields());
                ResultSet resultSet = rs;
                return resultSet;
            }
            finally {
                if (pStmt != null) {
                    pStmt.close();
                }
            }
        }
        catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public ResultSet getVersionColumns(String catalog, String schema, String table) throws SQLException {
        try {
            if (table == null) {
                throw SQLError.createSQLException(Messages.getString("DatabaseMetaData.2"), "S1009", this.getExceptionInterceptor());
            }
            String db = this.getDatabase(catalog, schema);
            db = this.pedantic ? db : StringUtils.unQuoteIdentifier(db, this.quotedId);
            StringBuilder sqlBuf = new StringBuilder("SELECT NULL AS SCOPE, COLUMN_NAME, ");
            this.appendJdbcTypeMappingQuery(sqlBuf, "DATA_TYPE", "COLUMN_TYPE");
            sqlBuf.append(" AS DATA_TYPE, UPPER(COLUMN_TYPE) AS TYPE_NAME,");
            sqlBuf.append(" CASE WHEN LCASE(DATA_TYPE)='date' THEN 10");
            if (this.conn.getServerVersion().meetsMinimum(ServerVersion.parseVersion("5.6.4"))) {
                sqlBuf.append(" WHEN LCASE(DATA_TYPE)='time'");
                sqlBuf.append("  THEN 8+(CASE WHEN DATETIME_PRECISION>0 THEN DATETIME_PRECISION+1 ELSE DATETIME_PRECISION END)");
                sqlBuf.append(" WHEN LCASE(DATA_TYPE)='datetime' OR LCASE(DATA_TYPE)='timestamp'");
                sqlBuf.append("  THEN 19+(CASE WHEN DATETIME_PRECISION>0 THEN DATETIME_PRECISION+1 ELSE DATETIME_PRECISION END)");
            } else {
                sqlBuf.append(" WHEN LCASE(DATA_TYPE)='time' THEN 8");
                sqlBuf.append(" WHEN LCASE(DATA_TYPE)='datetime' OR LCASE(DATA_TYPE)='timestamp' THEN 19");
            }
            sqlBuf.append(" WHEN CHARACTER_MAXIMUM_LENGTH IS NULL THEN NUMERIC_PRECISION WHEN CHARACTER_MAXIMUM_LENGTH > ");
            sqlBuf.append(Integer.MAX_VALUE);
            sqlBuf.append(" THEN ");
            sqlBuf.append(Integer.MAX_VALUE);
            sqlBuf.append(" ELSE CHARACTER_MAXIMUM_LENGTH END AS COLUMN_SIZE, ");
            sqlBuf.append(maxBufferSize);
            sqlBuf.append(" AS BUFFER_LENGTH,NUMERIC_SCALE AS DECIMAL_DIGITS, ");
            sqlBuf.append(Integer.toString(1));
            sqlBuf.append(" AS PSEUDO_COLUMN FROM INFORMATION_SCHEMA.COLUMNS WHERE");
            if (db != null) {
                sqlBuf.append(" TABLE_SCHEMA = ? AND");
            }
            sqlBuf.append(" TABLE_NAME = ?");
            sqlBuf.append(" AND EXTRA LIKE '%on update CURRENT_TIMESTAMP%'");
            try (PreparedStatement pStmt = null;){
                pStmt = this.prepareMetaDataSafeStatement(sqlBuf.toString());
                int nextId = 1;
                if (db != null) {
                    pStmt.setString(nextId++, db);
                }
                pStmt.setString(nextId, table);
                ResultSet rs = this.executeMetadataQuery(pStmt);
                ((ResultSetInternalMethods)rs).getColumnDefinition().setFields(this.getVersionColumnsFields());
                ResultSet resultSet = rs;
                return resultSet;
            }
        }
        catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public ResultSet getFunctionColumns(String catalog, String schemaPattern, String functionNamePattern, String columnNamePattern) throws SQLException {
        try {
            String db = this.getDatabase(catalog, schemaPattern);
            db = this.pedantic ? db : StringUtils.unQuoteIdentifier(db, this.quotedId);
            boolean supportsFractSeconds = this.conn.getServerVersion().meetsMinimum(ServerVersion.parseVersion("5.6.4"));
            StringBuilder sqlBuf = new StringBuilder(this.databaseTerm.getValue() == PropertyDefinitions.DatabaseTerm.SCHEMA ? "SELECT SPECIFIC_CATALOG AS FUNCTION_CAT, SPECIFIC_SCHEMA AS `FUNCTION_SCHEM`," : "SELECT SPECIFIC_SCHEMA AS FUNCTION_CAT, NULL AS `FUNCTION_SCHEM`,");
            sqlBuf.append(" SPECIFIC_NAME AS `FUNCTION_NAME`, IFNULL(PARAMETER_NAME, '') AS `COLUMN_NAME`, CASE WHEN PARAMETER_MODE = 'IN' THEN ");
            sqlBuf.append(this.getFunctionConstant(FunctionConstant.FUNCTION_COLUMN_IN));
            sqlBuf.append(" WHEN PARAMETER_MODE = 'OUT' THEN ");
            sqlBuf.append(this.getFunctionConstant(FunctionConstant.FUNCTION_COLUMN_OUT));
            sqlBuf.append(" WHEN PARAMETER_MODE = 'INOUT' THEN ");
            sqlBuf.append(this.getFunctionConstant(FunctionConstant.FUNCTION_COLUMN_INOUT));
            sqlBuf.append(" WHEN ORDINAL_POSITION = 0 THEN ");
            sqlBuf.append(this.getFunctionConstant(FunctionConstant.FUNCTION_COLUMN_RETURN));
            sqlBuf.append(" ELSE ");
            sqlBuf.append(this.getFunctionConstant(FunctionConstant.FUNCTION_COLUMN_UNKNOWN));
            sqlBuf.append(" END AS `COLUMN_TYPE`, ");
            this.appendJdbcTypeMappingQuery(sqlBuf, "DATA_TYPE", "DTD_IDENTIFIER");
            sqlBuf.append(" AS `DATA_TYPE`, ");
            sqlBuf.append("UPPER(CASE");
            if (this.tinyInt1isBit) {
                sqlBuf.append(" WHEN UPPER(DATA_TYPE)='TINYINT' THEN CASE");
                sqlBuf.append(" WHEN LOCATE('ZEROFILL', UPPER(DTD_IDENTIFIER)) = 0 AND LOCATE('UNSIGNED', UPPER(DTD_IDENTIFIER)) = 0 AND LOCATE('(1)', DTD_IDENTIFIER) != 0 THEN ");
                sqlBuf.append(this.transformedBitIsBoolean ? "'BOOLEAN'" : "'BIT'");
                sqlBuf.append(" WHEN LOCATE('UNSIGNED', UPPER(DTD_IDENTIFIER)) != 0 AND LOCATE('UNSIGNED', UPPER(DATA_TYPE)) = 0 THEN 'TINYINT UNSIGNED'");
                sqlBuf.append(" ELSE DATA_TYPE END ");
            }
            sqlBuf.append(" WHEN LOCATE('UNSIGNED', UPPER(DTD_IDENTIFIER)) != 0 AND LOCATE('UNSIGNED', UPPER(DATA_TYPE)) = 0 AND LOCATE('SET', UPPER(DATA_TYPE)) <> 1 AND LOCATE('ENUM', UPPER(DATA_TYPE)) <> 1 THEN CONCAT(DATA_TYPE, ' UNSIGNED')");
            sqlBuf.append(" WHEN UPPER(DATA_TYPE)='POINT' THEN 'GEOMETRY'");
            sqlBuf.append(" WHEN UPPER(DATA_TYPE)='LINESTRING' THEN 'GEOMETRY'");
            sqlBuf.append(" WHEN UPPER(DATA_TYPE)='POLYGON' THEN 'GEOMETRY'");
            sqlBuf.append(" WHEN UPPER(DATA_TYPE)='MULTIPOINT' THEN 'GEOMETRY'");
            sqlBuf.append(" WHEN UPPER(DATA_TYPE)='MULTILINESTRING' THEN 'GEOMETRY'");
            sqlBuf.append(" WHEN UPPER(DATA_TYPE)='MULTIPOLYGON' THEN 'GEOMETRY'");
            sqlBuf.append(" WHEN UPPER(DATA_TYPE)='GEOMETRYCOLLECTION' THEN 'GEOMETRY'");
            sqlBuf.append(" WHEN UPPER(DATA_TYPE)='GEOMCOLLECTION' THEN 'GEOMETRY'");
            sqlBuf.append(" ELSE UPPER(DATA_TYPE) END) AS TYPE_NAME,");
            sqlBuf.append(" CASE WHEN LCASE(DATA_TYPE)='date' THEN 0");
            if (supportsFractSeconds) {
                sqlBuf.append(" WHEN LCASE(DATA_TYPE)='time' OR LCASE(DATA_TYPE)='datetime' OR LCASE(DATA_TYPE)='timestamp' THEN DATETIME_PRECISION");
            } else {
                sqlBuf.append(" WHEN LCASE(DATA_TYPE)='time' OR LCASE(DATA_TYPE)='datetime' OR LCASE(DATA_TYPE)='timestamp' THEN 0");
            }
            if (this.tinyInt1isBit && !this.transformedBitIsBoolean) {
                sqlBuf.append(" WHEN UPPER(DATA_TYPE)='TINYINT' AND LOCATE('ZEROFILL', UPPER(DTD_IDENTIFIER)) = 0 AND LOCATE('UNSIGNED', UPPER(DTD_IDENTIFIER)) = 0 AND LOCATE('(1)', DTD_IDENTIFIER) != 0 THEN 1");
            }
            sqlBuf.append(" WHEN UPPER(DATA_TYPE)='MEDIUMINT' AND LOCATE('UNSIGNED', UPPER(DTD_IDENTIFIER)) != 0 THEN 8");
            sqlBuf.append(" WHEN UPPER(DATA_TYPE)='JSON' THEN 1073741824");
            sqlBuf.append(" ELSE NUMERIC_PRECISION END AS `PRECISION`,");
            sqlBuf.append(" CASE WHEN LCASE(DATA_TYPE)='date' THEN 10");
            if (supportsFractSeconds) {
                sqlBuf.append(" WHEN LCASE(DATA_TYPE)='time' THEN 8+(CASE WHEN DATETIME_PRECISION>0 THEN DATETIME_PRECISION+1 ELSE DATETIME_PRECISION END)");
                sqlBuf.append(" WHEN LCASE(DATA_TYPE)='datetime' OR LCASE(DATA_TYPE)='timestamp'");
                sqlBuf.append("  THEN 19+(CASE WHEN DATETIME_PRECISION>0 THEN DATETIME_PRECISION+1 ELSE DATETIME_PRECISION END)");
            } else {
                sqlBuf.append(" WHEN LCASE(DATA_TYPE)='time' THEN 8");
                sqlBuf.append(" WHEN LCASE(DATA_TYPE)='datetime' OR LCASE(DATA_TYPE)='timestamp' THEN 19");
            }
            if (this.tinyInt1isBit && !this.transformedBitIsBoolean) {
                sqlBuf.append(" WHEN (UPPER(DATA_TYPE)='TINYINT' OR UPPER(DATA_TYPE)='TINYINT UNSIGNED') AND LOCATE('ZEROFILL', UPPER(DTD_IDENTIFIER)) = 0 AND LOCATE('UNSIGNED', UPPER(DTD_IDENTIFIER)) = 0 AND LOCATE('(1)', DTD_IDENTIFIER) != 0 THEN 1");
            }
            sqlBuf.append(" WHEN UPPER(DATA_TYPE)='MEDIUMINT' AND LOCATE('UNSIGNED', UPPER(DTD_IDENTIFIER)) != 0 THEN 8");
            sqlBuf.append(" WHEN UPPER(DATA_TYPE)='JSON' THEN 1073741824");
            sqlBuf.append(" WHEN CHARACTER_MAXIMUM_LENGTH IS NULL THEN NUMERIC_PRECISION");
            sqlBuf.append(" WHEN CHARACTER_MAXIMUM_LENGTH > 2147483647 THEN 2147483647");
            sqlBuf.append(" ELSE CHARACTER_MAXIMUM_LENGTH END AS LENGTH, ");
            sqlBuf.append("NUMERIC_SCALE AS `SCALE`, 10 AS RADIX, ");
            sqlBuf.append(this.getFunctionConstant(FunctionConstant.FUNCTION_NULLABLE));
            sqlBuf.append(" AS `NULLABLE`,  NULL AS `REMARKS`,");
            sqlBuf.append(" CASE WHEN CHARACTER_OCTET_LENGTH > ");
            sqlBuf.append(Integer.MAX_VALUE);
            sqlBuf.append(" THEN ");
            sqlBuf.append(Integer.MAX_VALUE);
            sqlBuf.append(" ELSE CHARACTER_OCTET_LENGTH END AS `CHAR_OCTET_LENGTH`,");
            sqlBuf.append(" ORDINAL_POSITION, 'YES' AS `IS_NULLABLE`,");
            sqlBuf.append(" SPECIFIC_NAME FROM INFORMATION_SCHEMA.PARAMETERS WHERE");
            StringBuilder conditionBuf = new StringBuilder();
            if (db != null) {
                conditionBuf.append(this.databaseTerm.getValue() == PropertyDefinitions.DatabaseTerm.SCHEMA ? " SPECIFIC_SCHEMA LIKE ?" : " SPECIFIC_SCHEMA = ?");
            }
            if (functionNamePattern != null) {
                if (conditionBuf.length() > 0) {
                    conditionBuf.append(" AND");
                }
                conditionBuf.append(" SPECIFIC_NAME LIKE ?");
            }
            if (columnNamePattern != null) {
                if (conditionBuf.length() > 0) {
                    conditionBuf.append(" AND");
                }
                conditionBuf.append(" (PARAMETER_NAME LIKE ? OR PARAMETER_NAME IS NULL)");
            }
            if (conditionBuf.length() > 0) {
                conditionBuf.append(" AND");
            }
            conditionBuf.append(" ROUTINE_TYPE='FUNCTION'");
            sqlBuf.append((CharSequence)conditionBuf);
            sqlBuf.append(" ORDER BY SPECIFIC_SCHEMA, SPECIFIC_NAME, ORDINAL_POSITION");
            try (PreparedStatement pStmt = null;){
                pStmt = this.prepareMetaDataSafeStatement(sqlBuf.toString());
                int nextId = 1;
                if (db != null) {
                    pStmt.setString(nextId++, db);
                }
                if (functionNamePattern != null) {
                    pStmt.setString(nextId++, functionNamePattern);
                }
                if (columnNamePattern != null) {
                    pStmt.setString(nextId, columnNamePattern);
                }
                ResultSet rs = this.executeMetadataQuery(pStmt);
                ((ResultSetInternalMethods)rs).getColumnDefinition().setFields(this.createFunctionColumnsFields());
                ResultSet resultSet = rs;
                return resultSet;
            }
        }
        catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    protected int getFunctionConstant(FunctionConstant constant) {
        switch (constant) {
            case FUNCTION_COLUMN_IN: {
                return 1;
            }
            case FUNCTION_COLUMN_INOUT: {
                return 2;
            }
            case FUNCTION_COLUMN_OUT: {
                return 3;
            }
            case FUNCTION_COLUMN_RETURN: {
                return 4;
            }
            case FUNCTION_COLUMN_RESULT: {
                return 5;
            }
            case FUNCTION_COLUMN_UNKNOWN: {
                return 0;
            }
            case FUNCTION_NO_NULLS: {
                return 0;
            }
            case FUNCTION_NULLABLE: {
                return 1;
            }
            case FUNCTION_NULLABLE_UNKNOWN: {
                return 2;
            }
        }
        return -1;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public ResultSet getFunctions(String catalog, String schemaPattern, String functionNamePattern) throws SQLException {
        try {
            String db = this.getDatabase(catalog, schemaPattern);
            db = this.pedantic ? db : StringUtils.unQuoteIdentifier(db, this.quotedId);
            StringBuilder sqlBuf = new StringBuilder(this.databaseTerm.getValue() == PropertyDefinitions.DatabaseTerm.SCHEMA ? "SELECT ROUTINE_CATALOG AS FUNCTION_CAT, ROUTINE_SCHEMA AS FUNCTION_SCHEM," : "SELECT ROUTINE_SCHEMA AS FUNCTION_CAT, NULL AS FUNCTION_SCHEM,");
            sqlBuf.append(" ROUTINE_NAME AS FUNCTION_NAME, ROUTINE_COMMENT AS REMARKS, ");
            sqlBuf.append(1);
            sqlBuf.append(" AS FUNCTION_TYPE, ROUTINE_NAME AS SPECIFIC_NAME FROM INFORMATION_SCHEMA.ROUTINES");
            sqlBuf.append(" WHERE ROUTINE_TYPE LIKE 'FUNCTION'");
            if (db != null) {
                sqlBuf.append(this.databaseTerm.getValue() == PropertyDefinitions.DatabaseTerm.SCHEMA ? " AND ROUTINE_SCHEMA LIKE ?" : " AND ROUTINE_SCHEMA = ?");
            }
            if (functionNamePattern != null) {
                sqlBuf.append(" AND ROUTINE_NAME LIKE ?");
            }
            sqlBuf.append(" ORDER BY FUNCTION_CAT, FUNCTION_SCHEM, FUNCTION_NAME, SPECIFIC_NAME");
            try (PreparedStatement pStmt = null;){
                pStmt = this.prepareMetaDataSafeStatement(sqlBuf.toString());
                int nextId = 1;
                if (db != null) {
                    pStmt.setString(nextId++, db);
                }
                if (functionNamePattern != null) {
                    pStmt.setString(nextId, functionNamePattern);
                }
                ResultSet rs = this.executeMetadataQuery(pStmt);
                ((ResultSetInternalMethods)rs).getColumnDefinition().setFields(this.getFunctionsFields());
                ResultSet resultSet = rs;
                return resultSet;
            }
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
            if (!this.conn.getServerVersion().meetsMinimum(ServerVersion.parseVersion("8.0.11"))) {
                return super.getSQLKeywords();
            }
            String keywords = keywordsCache.get(this.conn.getServerVersion());
            if (keywords != null) {
                return keywords;
            }
            Map<ServerVersion, String> map = keywordsCache;
            synchronized (map) {
                keywords = keywordsCache.get(this.conn.getServerVersion());
                if (keywords != null) {
                    return keywords;
                }
                ArrayList<String> keywordsFromServer = new ArrayList<String>();
                Statement stmt = this.conn.getMetadataSafeStatement();
                ResultSet rs = stmt.executeQuery("SELECT WORD FROM INFORMATION_SCHEMA.KEYWORDS WHERE RESERVED=1 ORDER BY WORD");
                while (rs.next()) {
                    keywordsFromServer.add(rs.getString(1));
                }
                stmt.close();
                keywordsFromServer.removeAll(SQL2003_KEYWORDS);
                keywords = String.join((CharSequence)",", keywordsFromServer);
                keywordsCache.put(this.conn.getServerVersion(), keywords);
                return keywords;
            }
        }
        catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    private final void appendJdbcTypeMappingQuery(StringBuilder buf, String mysqlTypeColumnName, String fullMysqlTypeColumnName) {
        buf.append("CASE ");
        block3: for (MysqlType mysqlType : MysqlType.values()) {
            buf.append(" WHEN UPPER(");
            buf.append(mysqlTypeColumnName);
            buf.append(")='");
            buf.append(mysqlType.getName());
            buf.append("' THEN ");
            switch (mysqlType) {
                case TINYINT: 
                case TINYINT_UNSIGNED: {
                    if (this.tinyInt1isBit) {
                        buf.append("CASE");
                        buf.append(" WHEN LOCATE('ZEROFILL', UPPER(");
                        buf.append(fullMysqlTypeColumnName);
                        buf.append(")) = 0 AND LOCATE('UNSIGNED', UPPER(");
                        buf.append(fullMysqlTypeColumnName);
                        buf.append(")) = 0 AND LOCATE('(1)', ");
                        buf.append(fullMysqlTypeColumnName);
                        buf.append(") != 0 THEN ");
                        buf.append(this.transformedBitIsBoolean ? "16" : "-7");
                        buf.append(" ELSE -6 END ");
                        continue block3;
                    }
                    buf.append(mysqlType.getJdbcType());
                    continue block3;
                }
                default: {
                    buf.append(mysqlType.getJdbcType());
                }
            }
        }
        buf.append(" WHEN UPPER(DATA_TYPE)='POINT' THEN -2");
        buf.append(" WHEN UPPER(DATA_TYPE)='LINESTRING' THEN -2");
        buf.append(" WHEN UPPER(DATA_TYPE)='POLYGON' THEN -2");
        buf.append(" WHEN UPPER(DATA_TYPE)='MULTIPOINT' THEN -2");
        buf.append(" WHEN UPPER(DATA_TYPE)='MULTILINESTRING' THEN -2");
        buf.append(" WHEN UPPER(DATA_TYPE)='MULTIPOLYGON' THEN -2");
        buf.append(" WHEN UPPER(DATA_TYPE)='GEOMETRYCOLLECTION' THEN -2");
        buf.append(" WHEN UPPER(DATA_TYPE)='GEOMCOLLECTION' THEN -2");
        buf.append(" ELSE 1111");
        buf.append(" END ");
    }

    @Override
    public ResultSet getSchemas() throws SQLException {
        try {
            return super.getSchemas();
        }
        catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    @Override
    public ResultSet getSchemas(String catalog, String schemaPattern) throws SQLException {
        try {
            return super.getSchemas(catalog, schemaPattern);
        }
        catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    @Override
    public ResultSet getCatalogs() throws SQLException {
        try {
            return super.getCatalogs();
        }
        catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    @Override
    public ResultSet getTablePrivileges(String catalog, String schemaPattern, String tableNamePattern) throws SQLException {
        try {
            return super.getTablePrivileges(catalog, schemaPattern, tableNamePattern);
        }
        catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    @Override
    public ResultSet getBestRowIdentifier(String catalog, String schema, String table, int scope, boolean nullable) throws SQLException {
        try {
            return super.getBestRowIdentifier(catalog, schema, table, scope, nullable);
        }
        catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    protected static enum FunctionConstant {
        FUNCTION_COLUMN_UNKNOWN,
        FUNCTION_COLUMN_IN,
        FUNCTION_COLUMN_INOUT,
        FUNCTION_COLUMN_OUT,
        FUNCTION_COLUMN_RETURN,
        FUNCTION_COLUMN_RESULT,
        FUNCTION_NO_NULLS,
        FUNCTION_NULLABLE,
        FUNCTION_NULLABLE_UNKNOWN;

    }
}

