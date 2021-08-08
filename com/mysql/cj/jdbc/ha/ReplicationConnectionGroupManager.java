/*
 * Decompiled with CFR 0.150.
 */
package com.mysql.cj.jdbc.ha;

import com.mysql.cj.jdbc.ha.ReplicationConnectionGroup;
import com.mysql.cj.jdbc.jmx.ReplicationGroupManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;

public class ReplicationConnectionGroupManager {
    private static HashMap<String, ReplicationConnectionGroup> GROUP_MAP = new HashMap();
    private static ReplicationGroupManager mbean = new ReplicationGroupManager();
    private static boolean hasRegisteredJmx = false;

    public static synchronized ReplicationConnectionGroup getConnectionGroupInstance(String groupName) {
        if (GROUP_MAP.containsKey(groupName)) {
            return GROUP_MAP.get(groupName);
        }
        ReplicationConnectionGroup group = new ReplicationConnectionGroup(groupName);
        GROUP_MAP.put(groupName, group);
        return group;
    }

    public static void registerJmx() throws SQLException {
        if (hasRegisteredJmx) {
            return;
        }
        mbean.registerJmx();
        hasRegisteredJmx = true;
    }

    public static ReplicationConnectionGroup getConnectionGroup(String groupName) {
        return GROUP_MAP.get(groupName);
    }

    public static Collection<ReplicationConnectionGroup> getGroupsMatching(String group) {
        if (group == null || group.equals("")) {
            HashSet<ReplicationConnectionGroup> s = new HashSet<ReplicationConnectionGroup>();
            s.addAll(GROUP_MAP.values());
            return s;
        }
        HashSet<ReplicationConnectionGroup> s = new HashSet<ReplicationConnectionGroup>();
        ReplicationConnectionGroup o = GROUP_MAP.get(group);
        if (o != null) {
            s.add(o);
        }
        return s;
    }

    public static void addReplicaHost(String group, String hostPortPair) throws SQLException {
        Collection<ReplicationConnectionGroup> s = ReplicationConnectionGroupManager.getGroupsMatching(group);
        for (ReplicationConnectionGroup cg : s) {
            cg.addReplicaHost(hostPortPair);
        }
    }

    @Deprecated
    public static void addSlaveHost(String group, String hostPortPair) throws SQLException {
        ReplicationConnectionGroupManager.addReplicaHost(group, hostPortPair);
    }

    public static void removeReplicaHost(String group, String hostPortPair) throws SQLException {
        ReplicationConnectionGroupManager.removeReplicaHost(group, hostPortPair, true);
    }

    @Deprecated
    public static void removeSlaveHost(String group, String hostPortPair) throws SQLException {
        ReplicationConnectionGroupManager.removeReplicaHost(group, hostPortPair);
    }

    public static void removeReplicaHost(String group, String hostPortPair, boolean closeGently) throws SQLException {
        Collection<ReplicationConnectionGroup> s = ReplicationConnectionGroupManager.getGroupsMatching(group);
        for (ReplicationConnectionGroup cg : s) {
            cg.removeReplicaHost(hostPortPair, closeGently);
        }
    }

    @Deprecated
    public static void removeSlaveHost(String group, String hostPortPair, boolean closeGently) throws SQLException {
        ReplicationConnectionGroupManager.removeReplicaHost(group, hostPortPair, closeGently);
    }

    public static void promoteReplicaToSource(String group, String hostPortPair) throws SQLException {
        Collection<ReplicationConnectionGroup> s = ReplicationConnectionGroupManager.getGroupsMatching(group);
        for (ReplicationConnectionGroup cg : s) {
            cg.promoteReplicaToSource(hostPortPair);
        }
    }

    @Deprecated
    public static void promoteSlaveToMaster(String group, String hostPortPair) throws SQLException {
        ReplicationConnectionGroupManager.promoteReplicaToSource(group, hostPortPair);
    }

    public static long getReplicaPromotionCount(String group) throws SQLException {
        Collection<ReplicationConnectionGroup> s = ReplicationConnectionGroupManager.getGroupsMatching(group);
        long promoted = 0L;
        for (ReplicationConnectionGroup cg : s) {
            long tmp = cg.getNumberOfReplicaPromotions();
            if (tmp <= promoted) continue;
            promoted = tmp;
        }
        return promoted;
    }

    @Deprecated
    public static long getSlavePromotionCount(String group) throws SQLException {
        return ReplicationConnectionGroupManager.getReplicaPromotionCount(group);
    }

    public static void removeSourceHost(String group, String hostPortPair) throws SQLException {
        ReplicationConnectionGroupManager.removeSourceHost(group, hostPortPair, true);
    }

    @Deprecated
    public static void removeMasterHost(String group, String hostPortPair) throws SQLException {
        ReplicationConnectionGroupManager.removeSourceHost(group, hostPortPair);
    }

    public static void removeSourceHost(String group, String hostPortPair, boolean closeGently) throws SQLException {
        Collection<ReplicationConnectionGroup> s = ReplicationConnectionGroupManager.getGroupsMatching(group);
        for (ReplicationConnectionGroup cg : s) {
            cg.removeSourceHost(hostPortPair, closeGently);
        }
    }

    @Deprecated
    public static void removeMasterHost(String group, String hostPortPair, boolean closeGently) throws SQLException {
        ReplicationConnectionGroupManager.removeSourceHost(group, hostPortPair, closeGently);
    }

    public static String getRegisteredReplicationConnectionGroups() {
        Collection<ReplicationConnectionGroup> s = ReplicationConnectionGroupManager.getGroupsMatching(null);
        StringBuilder sb = new StringBuilder();
        String sep = "";
        for (ReplicationConnectionGroup cg : s) {
            String group = cg.getGroupName();
            sb.append(sep);
            sb.append(group);
            sep = ",";
        }
        return sb.toString();
    }

    public static int getNumberOfSourcePromotion(String groupFilter) {
        int total = 0;
        Collection<ReplicationConnectionGroup> s = ReplicationConnectionGroupManager.getGroupsMatching(groupFilter);
        for (ReplicationConnectionGroup cg : s) {
            total = (int)((long)total + cg.getNumberOfReplicaPromotions());
        }
        return total;
    }

    @Deprecated
    public static int getNumberOfMasterPromotion(String groupFilter) {
        return ReplicationConnectionGroupManager.getNumberOfSourcePromotion(groupFilter);
    }

    public static int getConnectionCountWithHostAsReplica(String groupFilter, String hostPortPair) {
        int total = 0;
        Collection<ReplicationConnectionGroup> s = ReplicationConnectionGroupManager.getGroupsMatching(groupFilter);
        for (ReplicationConnectionGroup cg : s) {
            total += cg.getConnectionCountWithHostAsReplica(hostPortPair);
        }
        return total;
    }

    @Deprecated
    public static int getConnectionCountWithHostAsSlave(String groupFilter, String hostPortPair) {
        return ReplicationConnectionGroupManager.getConnectionCountWithHostAsReplica(groupFilter, hostPortPair);
    }

    public static int getConnectionCountWithHostAsSource(String groupFilter, String hostPortPair) {
        int total = 0;
        Collection<ReplicationConnectionGroup> s = ReplicationConnectionGroupManager.getGroupsMatching(groupFilter);
        for (ReplicationConnectionGroup cg : s) {
            total += cg.getConnectionCountWithHostAsSource(hostPortPair);
        }
        return total;
    }

    @Deprecated
    public static int getConnectionCountWithHostAsMaster(String groupFilter, String hostPortPair) {
        return ReplicationConnectionGroupManager.getConnectionCountWithHostAsSource(groupFilter, hostPortPair);
    }

    public static Collection<String> getReplicaHosts(String groupFilter) {
        Collection<ReplicationConnectionGroup> s = ReplicationConnectionGroupManager.getGroupsMatching(groupFilter);
        ArrayList<String> hosts = new ArrayList<String>();
        for (ReplicationConnectionGroup cg : s) {
            hosts.addAll(cg.getReplicaHosts());
        }
        return hosts;
    }

    @Deprecated
    public static Collection<String> getSlaveHosts(String groupFilter) {
        return ReplicationConnectionGroupManager.getReplicaHosts(groupFilter);
    }

    public static Collection<String> getSourceHosts(String groupFilter) {
        Collection<ReplicationConnectionGroup> s = ReplicationConnectionGroupManager.getGroupsMatching(groupFilter);
        ArrayList<String> hosts = new ArrayList<String>();
        for (ReplicationConnectionGroup cg : s) {
            hosts.addAll(cg.getSourceHosts());
        }
        return hosts;
    }

    @Deprecated
    public static Collection<String> getMasterHosts(String groupFilter) {
        return ReplicationConnectionGroupManager.getSourceHosts(groupFilter);
    }

    public static long getTotalConnectionCount(String group) {
        long connections = 0L;
        Collection<ReplicationConnectionGroup> s = ReplicationConnectionGroupManager.getGroupsMatching(group);
        for (ReplicationConnectionGroup cg : s) {
            connections += cg.getTotalConnectionCount();
        }
        return connections;
    }

    public static long getActiveConnectionCount(String group) {
        long connections = 0L;
        Collection<ReplicationConnectionGroup> s = ReplicationConnectionGroupManager.getGroupsMatching(group);
        for (ReplicationConnectionGroup cg : s) {
            connections += cg.getActiveConnectionCount();
        }
        return connections;
    }
}

