/*
 * Decompiled with CFR 0.150.
 */
package com.mysql.cj.conf.url;

import com.mysql.cj.Messages;
import com.mysql.cj.conf.BooleanPropertyDefinition;
import com.mysql.cj.conf.ConnectionUrl;
import com.mysql.cj.conf.ConnectionUrlParser;
import com.mysql.cj.conf.HostInfo;
import com.mysql.cj.conf.HostsListView;
import com.mysql.cj.conf.PropertyKey;
import com.mysql.cj.exceptions.ExceptionFactory;
import com.mysql.cj.exceptions.InvalidConnectionAttributeException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

public class ReplicationDnsSrvConnectionUrl
extends ConnectionUrl {
    private static final String DEFAULT_HOST = "";
    private static final int DEFAULT_PORT = -1;
    private static final String TYPE_SOURCE = "SOURCE";
    private static final String TYPE_REPLICA = "REPLICA";
    @Deprecated
    private static final String TYPE_SOURCE_DEPRECATED = "MASTER";
    @Deprecated
    private static final String TYPE_REPLICA_DEPRECATED = "SLAVE";
    private List<HostInfo> sourceHosts = new ArrayList<HostInfo>();
    private List<HostInfo> replicaHosts = new ArrayList<HostInfo>();

    public ReplicationDnsSrvConnectionUrl(ConnectionUrlParser connStrParser, Properties info) {
        super(connStrParser, info);
        Map<Object, Object> hostPropsReplica;
        this.type = ConnectionUrl.Type.REPLICATION_DNS_SRV_CONNECTION;
        LinkedList<HostInfo> undefinedHosts = new LinkedList<HostInfo>();
        for (HostInfo hi : this.hosts) {
            Map<String, String> hostProperties = hi.getHostProperties();
            if (hostProperties.containsKey(PropertyKey.TYPE.getKeyName())) {
                if (TYPE_SOURCE.equalsIgnoreCase(hostProperties.get(PropertyKey.TYPE.getKeyName())) || TYPE_SOURCE_DEPRECATED.equalsIgnoreCase(hostProperties.get(PropertyKey.TYPE.getKeyName()))) {
                    this.sourceHosts.add(hi);
                    continue;
                }
                if (TYPE_REPLICA.equalsIgnoreCase(hostProperties.get(PropertyKey.TYPE.getKeyName())) || TYPE_REPLICA_DEPRECATED.equalsIgnoreCase(hostProperties.get(PropertyKey.TYPE.getKeyName()))) {
                    this.replicaHosts.add(hi);
                    continue;
                }
                undefinedHosts.add(hi);
                continue;
            }
            undefinedHosts.add(hi);
        }
        if (!undefinedHosts.isEmpty()) {
            if (this.sourceHosts.isEmpty()) {
                this.sourceHosts.add((HostInfo)undefinedHosts.removeFirst());
            }
            this.replicaHosts.addAll(undefinedHosts);
        }
        HostInfo srvHostSource = this.sourceHosts.isEmpty() ? null : this.sourceHosts.get(0);
        Map<Object, Object> hostPropsSource = srvHostSource == null ? Collections.emptyMap() : srvHostSource.getHostProperties();
        HostInfo srvHostReplica = this.replicaHosts.isEmpty() ? null : this.replicaHosts.get(0);
        Map<Object, Object> map = hostPropsReplica = srvHostReplica == null ? Collections.emptyMap() : srvHostReplica.getHostProperties();
        if (srvHostSource == null || srvHostReplica == null || DEFAULT_HOST.equals(srvHostSource.getHost()) || DEFAULT_HOST.equals(srvHostReplica.getHost())) {
            throw ExceptionFactory.createException(InvalidConnectionAttributeException.class, Messages.getString("ConnectionString.20"));
        }
        if (this.sourceHosts.size() != 1 || this.replicaHosts.size() != 1) {
            throw ExceptionFactory.createException(InvalidConnectionAttributeException.class, Messages.getString("ConnectionString.21"));
        }
        if (srvHostSource.getPort() != -1 || srvHostReplica.getPort() != -1) {
            throw ExceptionFactory.createException(InvalidConnectionAttributeException.class, Messages.getString("ConnectionString.22"));
        }
        if (!(!hostPropsSource.containsKey(PropertyKey.dnsSrv.getKeyName()) && !hostPropsReplica.containsKey(PropertyKey.dnsSrv.getKeyName()) || BooleanPropertyDefinition.booleanFrom(PropertyKey.dnsSrv.getKeyName(), (String)hostPropsSource.get(PropertyKey.dnsSrv.getKeyName()), null).booleanValue() && BooleanPropertyDefinition.booleanFrom(PropertyKey.dnsSrv.getKeyName(), (String)hostPropsReplica.get(PropertyKey.dnsSrv.getKeyName()), null).booleanValue())) {
            throw ExceptionFactory.createException(InvalidConnectionAttributeException.class, Messages.getString("ConnectionString.23", new Object[]{PropertyKey.dnsSrv.getKeyName()}));
        }
        if (hostPropsSource.containsKey(PropertyKey.PROTOCOL.getKeyName()) && ((String)hostPropsSource.get(PropertyKey.PROTOCOL.getKeyName())).equalsIgnoreCase("PIPE") || hostPropsReplica.containsKey(PropertyKey.PROTOCOL.getKeyName()) && ((String)hostPropsReplica.get(PropertyKey.PROTOCOL.getKeyName())).equalsIgnoreCase("PIPE")) {
            throw ExceptionFactory.createException(InvalidConnectionAttributeException.class, Messages.getString("ConnectionString.24"));
        }
        if (hostPropsSource.containsKey(PropertyKey.replicationConnectionGroup.getKeyName()) || hostPropsReplica.containsKey(PropertyKey.replicationConnectionGroup.getKeyName())) {
            throw ExceptionFactory.createException(InvalidConnectionAttributeException.class, Messages.getString("ConnectionString.25", new Object[]{PropertyKey.replicationConnectionGroup.getKeyName()}));
        }
    }

    @Override
    public String getDefaultHost() {
        return DEFAULT_HOST;
    }

    @Override
    public int getDefaultPort() {
        return -1;
    }

    @Override
    public List<HostInfo> getHostsList(HostsListView view) {
        switch (view) {
            case SOURCES: {
                return this.getHostsListFromDnsSrv(this.sourceHosts.get(0));
            }
            case REPLICAS: {
                return this.getHostsListFromDnsSrv(this.replicaHosts.get(0));
            }
        }
        return super.getHostsList(HostsListView.ALL);
    }
}

