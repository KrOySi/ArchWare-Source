/*
 * Decompiled with CFR 0.150.
 */
package com.mysql.cj.jdbc.jmx;

import com.mysql.cj.Messages;
import com.mysql.cj.jdbc.exceptions.SQLError;
import com.mysql.cj.jdbc.ha.ReplicationConnectionGroup;
import com.mysql.cj.jdbc.ha.ReplicationConnectionGroupManager;
import com.mysql.cj.jdbc.jmx.ReplicationGroupManagerMBean;
import java.lang.management.ManagementFactory;
import java.sql.SQLException;
import javax.management.MBeanServer;
import javax.management.ObjectName;

public class ReplicationGroupManager
implements ReplicationGroupManagerMBean {
    private boolean isJmxRegistered = false;

    public synchronized void registerJmx() throws SQLException {
        if (this.isJmxRegistered) {
            return;
        }
        MBeanServer mbs = ManagementFactory.getPlatformMBeanServer();
        try {
            ObjectName name = new ObjectName("com.mysql.cj.jdbc.jmx:type=ReplicationGroupManager");
            mbs.registerMBean(this, name);
            this.isJmxRegistered = true;
        }
        catch (Exception e) {
            throw SQLError.createSQLException(Messages.getString("ReplicationGroupManager.0"), null, e, null);
        }
    }

    @Override
    public void addReplicaHost(String groupFilter, String host) throws SQLException {
        ReplicationConnectionGroupManager.addReplicaHost(groupFilter, host);
    }

    @Override
    public void removeReplicaHost(String groupFilter, String host) throws SQLException {
        ReplicationConnectionGroupManager.removeReplicaHost(groupFilter, host);
    }

    @Override
    public void promoteReplicaToSource(String groupFilter, String host) throws SQLException {
        ReplicationConnectionGroupManager.promoteReplicaToSource(groupFilter, host);
    }

    @Override
    public void removeSourceHost(String groupFilter, String host) throws SQLException {
        ReplicationConnectionGroupManager.removeSourceHost(groupFilter, host);
    }

    @Override
    public String getSourceHostsList(String group) {
        StringBuilder sb = new StringBuilder("");
        boolean found = false;
        for (String host : ReplicationConnectionGroupManager.getSourceHosts(group)) {
            if (found) {
                sb.append(",");
            }
            found = true;
            sb.append(host);
        }
        return sb.toString();
    }

    @Override
    public String getReplicaHostsList(String group) {
        StringBuilder sb = new StringBuilder("");
        boolean found = false;
        for (String host : ReplicationConnectionGroupManager.getReplicaHosts(group)) {
            if (found) {
                sb.append(",");
            }
            found = true;
            sb.append(host);
        }
        return sb.toString();
    }

    @Override
    public String getRegisteredConnectionGroups() {
        StringBuilder sb = new StringBuilder("");
        boolean found = false;
        for (ReplicationConnectionGroup group : ReplicationConnectionGroupManager.getGroupsMatching(null)) {
            if (found) {
                sb.append(",");
            }
            found = true;
            sb.append(group.getGroupName());
        }
        return sb.toString();
    }

    @Override
    public int getActiveSourceHostCount(String group) {
        return ReplicationConnectionGroupManager.getSourceHosts(group).size();
    }

    @Override
    public int getActiveReplicaHostCount(String group) {
        return ReplicationConnectionGroupManager.getReplicaHosts(group).size();
    }

    @Override
    public int getReplicaPromotionCount(String group) {
        return ReplicationConnectionGroupManager.getNumberOfSourcePromotion(group);
    }

    @Override
    public long getTotalLogicalConnectionCount(String group) {
        return ReplicationConnectionGroupManager.getTotalConnectionCount(group);
    }

    @Override
    public long getActiveLogicalConnectionCount(String group) {
        return ReplicationConnectionGroupManager.getActiveConnectionCount(group);
    }
}

