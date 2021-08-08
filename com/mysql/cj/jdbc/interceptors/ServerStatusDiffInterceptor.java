/*
 * Decompiled with CFR 0.150.
 */
package com.mysql.cj.jdbc.interceptors;

import com.mysql.cj.MysqlConnection;
import com.mysql.cj.Query;
import com.mysql.cj.exceptions.ExceptionFactory;
import com.mysql.cj.interceptors.QueryInterceptor;
import com.mysql.cj.jdbc.JdbcConnection;
import com.mysql.cj.log.Log;
import com.mysql.cj.protocol.Resultset;
import com.mysql.cj.protocol.ServerSession;
import com.mysql.cj.util.Util;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.function.Supplier;

public class ServerStatusDiffInterceptor
implements QueryInterceptor {
    private Map<String, String> preExecuteValues = new HashMap<String, String>();
    private Map<String, String> postExecuteValues = new HashMap<String, String>();
    private JdbcConnection connection;
    private Log log;

    @Override
    public QueryInterceptor init(MysqlConnection conn, Properties props, Log l) {
        this.connection = (JdbcConnection)conn;
        this.log = l;
        return this;
    }

    @Override
    public <T extends Resultset> T postProcess(Supplier<String> sql, Query interceptedQuery, T originalResultSet, ServerSession serverSession) {
        this.populateMapWithSessionStatusValues(this.postExecuteValues);
        this.log.logInfo("Server status change for query:\n" + Util.calculateDifferences(this.preExecuteValues, this.postExecuteValues));
        return null;
    }

    private void populateMapWithSessionStatusValues(Map<String, String> toPopulate) {
        try (Statement stmt = this.connection.createStatement();){
            toPopulate.clear();
            try (ResultSet rs = stmt.executeQuery("SHOW SESSION STATUS");){
                while (rs.next()) {
                    toPopulate.put(rs.getString(1), rs.getString(2));
                }
            }
        }
        catch (SQLException ex) {
            throw ExceptionFactory.createException(ex.getMessage(), ex);
        }
    }

    @Override
    public <T extends Resultset> T preProcess(Supplier<String> sql, Query interceptedQuery) {
        this.populateMapWithSessionStatusValues(this.preExecuteValues);
        return null;
    }

    @Override
    public boolean executeTopLevelOnly() {
        return true;
    }

    @Override
    public void destroy() {
        this.connection = null;
        this.log = null;
    }
}

