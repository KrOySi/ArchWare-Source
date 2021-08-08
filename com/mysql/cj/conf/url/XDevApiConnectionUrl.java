/*
 * Decompiled with CFR 0.150.
 */
package com.mysql.cj.conf.url;

import com.mysql.cj.Messages;
import com.mysql.cj.conf.ConnectionUrl;
import com.mysql.cj.conf.ConnectionUrlParser;
import com.mysql.cj.conf.HostInfo;
import com.mysql.cj.conf.HostsListView;
import com.mysql.cj.conf.PropertyKey;
import com.mysql.cj.exceptions.ExceptionFactory;
import com.mysql.cj.exceptions.WrongArgumentException;
import com.mysql.cj.util.StringUtils;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.stream.Collectors;

public class XDevApiConnectionUrl
extends ConnectionUrl {
    private static final int DEFAULT_PORT = 33060;
    private boolean prioritySorted = false;
    private boolean hasDuplicatedPriorities = false;

    public XDevApiConnectionUrl(ConnectionUrlParser connStrParser, Properties info) {
        super(connStrParser, info);
        this.type = ConnectionUrl.Type.XDEVAPI_SESSION;
        boolean first = true;
        String user = null;
        String password = null;
        boolean hasPriority = false;
        HashSet<Integer> priorities = new HashSet<Integer>();
        for (HostInfo hi2 : this.hosts) {
            if (first) {
                first = false;
                user = hi2.getUser();
                password = hi2.getPassword();
                hasPriority = hi2.getHostProperties().containsKey(PropertyKey.PRIORITY.getKeyName());
            } else {
                if (!StringUtils.nullSafeEqual(user, hi2.getUser()) || !StringUtils.nullSafeEqual(password, hi2.getPassword())) {
                    throw ExceptionFactory.createException(WrongArgumentException.class, Messages.getString("ConnectionString.14", new Object[]{ConnectionUrl.Type.XDEVAPI_SESSION.getScheme()}));
                }
                if (hasPriority ^ hi2.getHostProperties().containsKey(PropertyKey.PRIORITY.getKeyName())) {
                    throw ExceptionFactory.createException(WrongArgumentException.class, Messages.getString("ConnectionString.15", new Object[]{ConnectionUrl.Type.XDEVAPI_SESSION.getScheme()}));
                }
            }
            if (!hasPriority) continue;
            try {
                int priority = Integer.parseInt(hi2.getProperty(PropertyKey.PRIORITY.getKeyName()));
                if (priority < 0 || priority > 100) {
                    throw ExceptionFactory.createException(WrongArgumentException.class, Messages.getString("ConnectionString.16", new Object[]{ConnectionUrl.Type.XDEVAPI_SESSION.getScheme()}));
                }
                if (priorities.contains(priority)) {
                    this.hasDuplicatedPriorities = true;
                    continue;
                }
                priorities.add(priority);
            }
            catch (NumberFormatException e) {
                throw ExceptionFactory.createException(WrongArgumentException.class, Messages.getString("ConnectionString.16", new Object[]{ConnectionUrl.Type.XDEVAPI_SESSION.getScheme()}));
            }
        }
        if (hasPriority) {
            this.prioritySorted = true;
            this.hosts.sort(Comparator.comparing(hi -> Integer.parseInt(hi.getHostProperties().get(PropertyKey.PRIORITY.getKeyName()))).reversed());
        }
    }

    @Override
    protected void preprocessPerTypeHostProperties(Map<String, String> hostProps) {
        if (hostProps.containsKey(PropertyKey.ADDRESS.getKeyName())) {
            String address = hostProps.get(PropertyKey.ADDRESS.getKeyName());
            ConnectionUrlParser.Pair<String, Integer> hostPortPair = ConnectionUrlParser.parseHostPortPair(address);
            String host = StringUtils.safeTrim((String)hostPortPair.left);
            Integer port = (Integer)hostPortPair.right;
            if (!StringUtils.isNullOrEmpty(host) && !hostProps.containsKey(PropertyKey.HOST.getKeyName())) {
                hostProps.put(PropertyKey.HOST.getKeyName(), host);
            }
            if (port != -1 && !hostProps.containsKey(PropertyKey.PORT.getKeyName())) {
                hostProps.put(PropertyKey.PORT.getKeyName(), port.toString());
            }
        }
    }

    @Override
    public int getDefaultPort() {
        return 33060;
    }

    @Override
    protected void fixProtocolDependencies(Map<String, String> hostProps) {
    }

    @Override
    public List<HostInfo> getHostsList(HostsListView view) {
        if (this.prioritySorted) {
            if (this.hasDuplicatedPriorities) {
                Map<Integer, List<HostInfo>> hostsByPriority = this.hosts.stream().collect(Collectors.groupingBy(hi -> Integer.valueOf(hi.getHostProperties().get(PropertyKey.PRIORITY.getKeyName()))));
                this.hosts = hostsByPriority.entrySet().stream().sorted(Comparator.comparing(Map.Entry::getKey).reversed()).map(Map.Entry::getValue).peek(Collections::shuffle).flatMap(Collection::stream).collect(Collectors.toList());
            }
        } else {
            Collections.shuffle(this.hosts);
        }
        return super.getHostsList(view);
    }
}

