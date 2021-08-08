/*
 * Decompiled with CFR 0.150.
 */
package com.mysql.cj.jdbc.ha;

import com.mysql.cj.jdbc.JdbcConnection;
import java.sql.SQLException;

public interface LoadBalancedConnection
extends JdbcConnection {
    public boolean addHost(String var1) throws SQLException;

    public void removeHost(String var1) throws SQLException;

    public void removeHostWhenNotInUse(String var1) throws SQLException;

    public void ping(boolean var1) throws SQLException;
}

