/*
 * Decompiled with CFR 0.150.
 */
package com.mysql.cj.jdbc.jmx;

import java.sql.SQLException;

public interface ReplicationGroupManagerMBean {
    public void addReplicaHost(String var1, String var2) throws SQLException;

    @Deprecated
    default public void addSlaveHost(String groupFilter, String host) throws SQLException {
        this.addReplicaHost(groupFilter, host);
    }

    public void removeReplicaHost(String var1, String var2) throws SQLException;

    @Deprecated
    default public void removeSlaveHost(String groupFilter, String host) throws SQLException {
        this.removeReplicaHost(groupFilter, host);
    }

    public void promoteReplicaToSource(String var1, String var2) throws SQLException;

    @Deprecated
    default public void promoteSlaveToMaster(String groupFilter, String host) throws SQLException {
        this.promoteReplicaToSource(groupFilter, host);
    }

    public void removeSourceHost(String var1, String var2) throws SQLException;

    @Deprecated
    default public void removeMasterHost(String groupFilter, String host) throws SQLException {
        this.removeSourceHost(groupFilter, host);
    }

    public String getSourceHostsList(String var1);

    @Deprecated
    default public String getMasterHostsList(String group) {
        return this.getSourceHostsList(group);
    }

    public String getReplicaHostsList(String var1);

    @Deprecated
    default public String getSlaveHostsList(String group) {
        return this.getReplicaHostsList(group);
    }

    public String getRegisteredConnectionGroups();

    public int getActiveSourceHostCount(String var1);

    @Deprecated
    default public int getActiveMasterHostCount(String group) {
        return this.getActiveSourceHostCount(group);
    }

    public int getActiveReplicaHostCount(String var1);

    @Deprecated
    default public int getActiveSlaveHostCount(String group) {
        return this.getActiveReplicaHostCount(group);
    }

    public int getReplicaPromotionCount(String var1);

    @Deprecated
    default public int getSlavePromotionCount(String group) {
        return this.getReplicaPromotionCount(group);
    }

    public long getTotalLogicalConnectionCount(String var1);

    public long getActiveLogicalConnectionCount(String var1);
}

