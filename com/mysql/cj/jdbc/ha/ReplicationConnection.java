/*
 * Decompiled with CFR 0.150.
 */
package com.mysql.cj.jdbc.ha;

import com.mysql.cj.jdbc.JdbcConnection;
import java.sql.SQLException;

public interface ReplicationConnection
extends JdbcConnection {
    public long getConnectionGroupId();

    public JdbcConnection getCurrentConnection();

    public JdbcConnection getSourceConnection();

    @Deprecated
    default public JdbcConnection getMasterConnection() {
        return this.getSourceConnection();
    }

    public void promoteReplicaToSource(String var1) throws SQLException;

    @Deprecated
    default public void promoteSlaveToMaster(String host) throws SQLException {
        this.promoteReplicaToSource(host);
    }

    public void removeSourceHost(String var1) throws SQLException;

    @Deprecated
    default public void removeMasterHost(String host) throws SQLException {
        this.removeSourceHost(host);
    }

    public void removeSourceHost(String var1, boolean var2) throws SQLException;

    @Deprecated
    default public void removeMasterHost(String host, boolean waitUntilNotInUse) throws SQLException {
        this.removeSourceHost(host, waitUntilNotInUse);
    }

    public boolean isHostSource(String var1);

    @Deprecated
    default public boolean isHostMaster(String host) {
        return this.isHostSource(host);
    }

    public JdbcConnection getReplicaConnection();

    @Deprecated
    default public JdbcConnection getSlavesConnection() {
        return this.getReplicaConnection();
    }

    public void addReplicaHost(String var1) throws SQLException;

    @Deprecated
    default public void addSlaveHost(String host) throws SQLException {
        this.addReplicaHost(host);
    }

    public void removeReplica(String var1) throws SQLException;

    @Deprecated
    default public void removeSlave(String host) throws SQLException {
        this.removeReplica(host);
    }

    public void removeReplica(String var1, boolean var2) throws SQLException;

    @Deprecated
    default public void removeSlave(String host, boolean closeGently) throws SQLException {
        this.removeReplica(host, closeGently);
    }

    public boolean isHostReplica(String var1);

    @Deprecated
    default public boolean isHostSlave(String host) {
        return this.isHostReplica(host);
    }
}

