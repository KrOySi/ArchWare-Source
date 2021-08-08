/*
 * Decompiled with CFR 0.150.
 */
package com.mysql.cj.jdbc;

import com.mysql.cj.jdbc.ConnectionGroup;
import com.mysql.cj.jdbc.jmx.LoadBalanceConnectionGroupManager;
import java.sql.SQLException;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;

public class ConnectionGroupManager {
    private static HashMap<String, ConnectionGroup> GROUP_MAP = new HashMap();
    private static LoadBalanceConnectionGroupManager mbean = new LoadBalanceConnectionGroupManager();
    private static boolean hasRegisteredJmx = false;

    public static synchronized ConnectionGroup getConnectionGroupInstance(String groupName) {
        if (GROUP_MAP.containsKey(groupName)) {
            return GROUP_MAP.get(groupName);
        }
        ConnectionGroup group = new ConnectionGroup(groupName);
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

    public static ConnectionGroup getConnectionGroup(String groupName) {
        return GROUP_MAP.get(groupName);
    }

    private static Collection<ConnectionGroup> getGroupsMatching(String group) {
        if (group == null || group.equals("")) {
            HashSet<ConnectionGroup> s = new HashSet<ConnectionGroup>();
            s.addAll(GROUP_MAP.values());
            return s;
        }
        HashSet<ConnectionGroup> s = new HashSet<ConnectionGroup>();
        ConnectionGroup o = GROUP_MAP.get(group);
        if (o != null) {
            s.add(o);
        }
        return s;
    }

    public static void addHost(String group, String hostPortPair, boolean forExisting) {
        Collection<ConnectionGroup> s = ConnectionGroupManager.getGroupsMatching(group);
        for (ConnectionGroup cg : s) {
            cg.addHost(hostPortPair, forExisting);
        }
    }

    public static int getActiveHostCount(String group) {
        HashSet<String> active = new HashSet<String>();
        Collection<ConnectionGroup> s = ConnectionGroupManager.getGroupsMatching(group);
        for (ConnectionGroup cg : s) {
            active.addAll(cg.getInitialHosts());
        }
        return active.size();
    }

    public static long getActiveLogicalConnectionCount(String group) {
        int count = 0;
        Collection<ConnectionGroup> s = ConnectionGroupManager.getGroupsMatching(group);
        for (ConnectionGroup cg : s) {
            count = (int)((long)count + cg.getActiveLogicalConnectionCount());
        }
        return count;
    }

    public static long getActivePhysicalConnectionCount(String group) {
        int count = 0;
        Collection<ConnectionGroup> s = ConnectionGroupManager.getGroupsMatching(group);
        for (ConnectionGroup cg : s) {
            count = (int)((long)count + cg.getActivePhysicalConnectionCount());
        }
        return count;
    }

    public static int getTotalHostCount(String group) {
        Collection<ConnectionGroup> s = ConnectionGroupManager.getGroupsMatching(group);
        HashSet<String> hosts = new HashSet<String>();
        for (ConnectionGroup cg : s) {
            hosts.addAll(cg.getInitialHosts());
            hosts.addAll(cg.getClosedHosts());
        }
        return hosts.size();
    }

    public static long getTotalLogicalConnectionCount(String group) {
        long count = 0L;
        Collection<ConnectionGroup> s = ConnectionGroupManager.getGroupsMatching(group);
        for (ConnectionGroup cg : s) {
            count += cg.getTotalLogicalConnectionCount();
        }
        return count;
    }

    public static long getTotalPhysicalConnectionCount(String group) {
        long count = 0L;
        Collection<ConnectionGroup> s = ConnectionGroupManager.getGroupsMatching(group);
        for (ConnectionGroup cg : s) {
            count += cg.getTotalPhysicalConnectionCount();
        }
        return count;
    }

    public static long getTotalTransactionCount(String group) {
        long count = 0L;
        Collection<ConnectionGroup> s = ConnectionGroupManager.getGroupsMatching(group);
        for (ConnectionGroup cg : s) {
            count += cg.getTotalTransactionCount();
        }
        return count;
    }

    public static void removeHost(String group, String hostPortPair) throws SQLException {
        ConnectionGroupManager.removeHost(group, hostPortPair, false);
    }

    public static void removeHost(String group, String host, boolean removeExisting) throws SQLException {
        Collection<ConnectionGroup> s = ConnectionGroupManager.getGroupsMatching(group);
        for (ConnectionGroup cg : s) {
            cg.removeHost(host, removeExisting);
        }
    }

    public static String getActiveHostLists(String group) {
        Collection<ConnectionGroup> s = ConnectionGroupManager.getGroupsMatching(group);
        HashMap<String, Integer> hosts = new HashMap<String, Integer>();
        for (ConnectionGroup cg : s) {
            Collection<String> l = cg.getInitialHosts();
            for (String host : l) {
                Integer o = (Integer)hosts.get(host);
                o = o == null ? Integer.valueOf(1) : Integer.valueOf(o + 1);
                hosts.put(host, o);
            }
        }
        StringBuilder sb = new StringBuilder();
        String sep = "";
        for (String host : hosts.keySet()) {
            sb.append(sep);
            sb.append(host);
            sb.append('(');
            sb.append(hosts.get(host));
            sb.append(')');
            sep = ",";
        }
        return sb.toString();
    }

    public static String getRegisteredConnectionGroups() {
        Collection<ConnectionGroup> s = ConnectionGroupManager.getGroupsMatching(null);
        StringBuilder sb = new StringBuilder();
        String sep = "";
        for (ConnectionGroup cg : s) {
            String group = cg.getGroupName();
            sb.append(sep);
            sb.append(group);
            sep = ",";
        }
        return sb.toString();
    }
}

