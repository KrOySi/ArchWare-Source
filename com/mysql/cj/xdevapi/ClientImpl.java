/*
 * Decompiled with CFR 0.150.
 */
package com.mysql.cj.xdevapi;

import com.mysql.cj.Messages;
import com.mysql.cj.conf.BooleanPropertyDefinition;
import com.mysql.cj.conf.ConnectionUrl;
import com.mysql.cj.conf.DefaultPropertySet;
import com.mysql.cj.conf.HostInfo;
import com.mysql.cj.conf.IntegerPropertyDefinition;
import com.mysql.cj.conf.PropertySet;
import com.mysql.cj.exceptions.CJCommunicationsException;
import com.mysql.cj.exceptions.CJException;
import com.mysql.cj.exceptions.ExceptionFactory;
import com.mysql.cj.exceptions.WrongArgumentException;
import com.mysql.cj.protocol.Protocol;
import com.mysql.cj.protocol.x.XProtocol;
import com.mysql.cj.protocol.x.XProtocolError;
import com.mysql.cj.util.StringUtils;
import com.mysql.cj.xdevapi.Client;
import com.mysql.cj.xdevapi.DbDoc;
import com.mysql.cj.xdevapi.JsonLiteral;
import com.mysql.cj.xdevapi.JsonNumber;
import com.mysql.cj.xdevapi.JsonParser;
import com.mysql.cj.xdevapi.JsonString;
import com.mysql.cj.xdevapi.JsonValue;
import com.mysql.cj.xdevapi.Session;
import com.mysql.cj.xdevapi.SessionFactory;
import com.mysql.cj.xdevapi.SessionImpl;
import com.mysql.cj.xdevapi.XDevAPIError;
import java.io.IOException;
import java.lang.invoke.LambdaMetafactory;
import java.lang.ref.Reference;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ClientImpl
implements Client,
Protocol.ProtocolEventListener {
    boolean isClosed = false;
    private ConnectionUrl connUrl = null;
    private boolean poolingEnabled = true;
    private int maxSize = 25;
    int maxIdleTime = 0;
    private int queueTimeout = 0;
    private int demotedTimeout = 120000;
    Map<HostInfo, Long> demotedHosts = null;
    BlockingQueue<PooledXProtocol> idleProtocols = null;
    Set<WeakReference<PooledXProtocol>> activeProtocols = null;
    Set<WeakReference<Session>> nonPooledSessions = null;
    SessionFactory sessionFactory = new SessionFactory();

    public ClientImpl(String url, String clientPropsJson) {
        Properties clientProps = StringUtils.isNullOrEmpty(clientPropsJson) ? new Properties() : this.clientPropsFromJson(clientPropsJson);
        this.init(url, clientProps);
    }

    public ClientImpl(String url, Properties clientProps) {
        this.init(url, clientProps != null ? clientProps : new Properties());
    }

    private Properties clientPropsFromJson(String clientPropsJson) {
        Properties props = new Properties();
        DbDoc clientPropsDoc = JsonParser.parseDoc(clientPropsJson);
        JsonValue pooling = (JsonValue)clientPropsDoc.remove("pooling");
        if (pooling != null) {
            if (!DbDoc.class.isAssignableFrom(pooling.getClass())) {
                throw new XDevAPIError(String.format("Client option 'pooling' does not support value '%s'.", pooling.toFormattedString()));
            }
            DbDoc poolingDoc = (DbDoc)pooling;
            JsonValue jsonVal = (JsonValue)poolingDoc.remove("enabled");
            if (jsonVal != null) {
                if (JsonLiteral.class.isAssignableFrom(jsonVal.getClass())) {
                    JsonLiteral pe = (JsonLiteral)jsonVal;
                    if (pe != JsonLiteral.FALSE && pe != JsonLiteral.TRUE) {
                        throw new XDevAPIError(String.format("Client option '%s' does not support value '%s'.", Client.ClientProperty.POOLING_ENABLED.getKeyName(), jsonVal.toFormattedString()));
                    }
                    props.setProperty(Client.ClientProperty.POOLING_ENABLED.getKeyName(), pe.value);
                } else {
                    if (JsonString.class.isAssignableFrom(jsonVal.getClass())) {
                        throw new XDevAPIError(String.format("Client option '%s' does not support value '%s'.", Client.ClientProperty.POOLING_ENABLED.getKeyName(), ((JsonString)jsonVal).getString()));
                    }
                    throw new XDevAPIError(String.format("Client option '%s' does not support value '%s'.", Client.ClientProperty.POOLING_ENABLED.getKeyName(), jsonVal.toFormattedString()));
                }
            }
            if ((jsonVal = (JsonValue)poolingDoc.remove("maxSize")) != null) {
                if (JsonNumber.class.isAssignableFrom(jsonVal.getClass())) {
                    props.setProperty(Client.ClientProperty.POOLING_MAX_SIZE.getKeyName(), ((JsonNumber)jsonVal).toString());
                } else {
                    if (JsonString.class.isAssignableFrom(jsonVal.getClass())) {
                        throw new XDevAPIError(String.format("Client option '%s' does not support value '%s'.", Client.ClientProperty.POOLING_MAX_SIZE.getKeyName(), ((JsonString)jsonVal).getString()));
                    }
                    throw new XDevAPIError(String.format("Client option '%s' does not support value '%s'.", Client.ClientProperty.POOLING_MAX_SIZE.getKeyName(), jsonVal.toFormattedString()));
                }
            }
            if ((jsonVal = (JsonValue)poolingDoc.remove("maxIdleTime")) != null) {
                if (JsonNumber.class.isAssignableFrom(jsonVal.getClass())) {
                    props.setProperty(Client.ClientProperty.POOLING_MAX_IDLE_TIME.getKeyName(), ((JsonNumber)jsonVal).toString());
                } else {
                    if (JsonString.class.isAssignableFrom(jsonVal.getClass())) {
                        throw new XDevAPIError(String.format("Client option '%s' does not support value '%s'.", Client.ClientProperty.POOLING_MAX_IDLE_TIME.getKeyName(), ((JsonString)jsonVal).getString()));
                    }
                    throw new XDevAPIError(String.format("Client option '%s' does not support value '%s'.", Client.ClientProperty.POOLING_MAX_IDLE_TIME.getKeyName(), jsonVal.toFormattedString()));
                }
            }
            if ((jsonVal = (JsonValue)poolingDoc.remove("queueTimeout")) != null) {
                if (JsonNumber.class.isAssignableFrom(jsonVal.getClass())) {
                    props.setProperty(Client.ClientProperty.POOLING_QUEUE_TIMEOUT.getKeyName(), ((JsonNumber)jsonVal).toString());
                } else {
                    if (JsonString.class.isAssignableFrom(jsonVal.getClass())) {
                        throw new XDevAPIError(String.format("Client option '%s' does not support value '%s'.", Client.ClientProperty.POOLING_QUEUE_TIMEOUT.getKeyName(), ((JsonString)jsonVal).getString()));
                    }
                    throw new XDevAPIError(String.format("Client option '%s' does not support value '%s'.", Client.ClientProperty.POOLING_QUEUE_TIMEOUT.getKeyName(), jsonVal.toFormattedString()));
                }
            }
            if (poolingDoc.size() > 0) {
                String key = (String)poolingDoc.keySet().stream().findFirst().get();
                throw new XDevAPIError(String.format("Client option 'pooling.%s' is not recognized as valid.", key));
            }
        }
        if (!clientPropsDoc.isEmpty()) {
            String key = (String)clientPropsDoc.keySet().stream().findFirst().get();
            throw new XDevAPIError(String.format("Client option '%s' is not recognized as valid.", key));
        }
        return props;
    }

    private void validateAndInitializeClientProps(Properties clientProps) {
        String propKey = "";
        String propValue = "";
        propKey = Client.ClientProperty.POOLING_ENABLED.getKeyName();
        if (clientProps.containsKey(propKey)) {
            propValue = clientProps.getProperty(propKey);
            try {
                this.poolingEnabled = BooleanPropertyDefinition.booleanFrom(propKey, propValue, null);
            }
            catch (CJException e) {
                throw new XDevAPIError(String.format("Client option '%s' does not support value '%s'.", propKey, propValue), e);
            }
        }
        if (clientProps.containsKey(propKey = Client.ClientProperty.POOLING_MAX_SIZE.getKeyName())) {
            propValue = clientProps.getProperty(propKey);
            try {
                this.maxSize = IntegerPropertyDefinition.integerFrom(propKey, propValue, 1, null);
            }
            catch (WrongArgumentException e) {
                throw new XDevAPIError(String.format("Client option '%s' does not support value '%s'.", propKey, propValue), e);
            }
            if (this.maxSize <= 0) {
                throw new XDevAPIError(String.format("Client option '%s' does not support value '%s'.", propKey, propValue));
            }
        }
        if (clientProps.containsKey(propKey = Client.ClientProperty.POOLING_MAX_IDLE_TIME.getKeyName())) {
            propValue = clientProps.getProperty(propKey);
            try {
                this.maxIdleTime = IntegerPropertyDefinition.integerFrom(propKey, propValue, 1, null);
            }
            catch (WrongArgumentException e) {
                throw new XDevAPIError(String.format("Client option '%s' does not support value '%s'.", propKey, propValue), e);
            }
            if (this.maxIdleTime < 0) {
                throw new XDevAPIError(String.format("Client option '%s' does not support value '%s'.", propKey, propValue));
            }
        }
        if (clientProps.containsKey(propKey = Client.ClientProperty.POOLING_QUEUE_TIMEOUT.getKeyName())) {
            propValue = clientProps.getProperty(propKey);
            try {
                this.queueTimeout = IntegerPropertyDefinition.integerFrom(propKey, propValue, 1, null);
            }
            catch (WrongArgumentException e) {
                throw new XDevAPIError(String.format("Client option '%s' does not support value '%s'.", propKey, propValue), e);
            }
            if (this.queueTimeout < 0) {
                throw new XDevAPIError(String.format("Client option '%s' does not support value '%s'.", propKey, propValue));
            }
        }
        List clientPropsAsString = Stream.of(Client.ClientProperty.values()).map(Client.ClientProperty::getKeyName).collect(Collectors.toList());
        propKey = clientProps.keySet().stream().filter(k -> !clientPropsAsString.contains(k)).findFirst().orElse(null);
        if (propKey != null) {
            throw new XDevAPIError(String.format("Client option '%s' is not recognized as valid.", propKey));
        }
    }

    private void init(String url, Properties clientProps) {
        this.connUrl = this.sessionFactory.parseUrl(url);
        this.validateAndInitializeClientProps(clientProps);
        if (this.poolingEnabled) {
            this.demotedHosts = new HashMap<HostInfo, Long>();
            this.idleProtocols = new LinkedBlockingQueue<PooledXProtocol>(this.maxSize);
            this.activeProtocols = new HashSet<WeakReference<PooledXProtocol>>(this.maxSize);
        } else {
            this.nonPooledSessions = new HashSet<WeakReference<Session>>();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     * Converted monitor instructions to comments
     * Lifted jumps to return sites
     */
    @Override
    public Session getSession() {
        if (this.isClosed) {
            throw new XDevAPIError("Client is closed.");
        }
        if (!this.poolingEnabled) {
            var1_1 = this;
            // MONITORENTER : var1_1
            obsoletedSessions = new ArrayList<WeakReference<Session>>();
            for (WeakReference<Session> ws : this.nonPooledSessions) {
                if (ws == null || (s = (Session)ws.get()) != null && s.isOpen()) continue;
                obsoletedSessions.add(ws);
            }
            var3_5 = obsoletedSessions.iterator();
            while (true) {
                if (!var3_5.hasNext()) {
                    sess = this.sessionFactory.getSession(this.connUrl);
                    this.nonPooledSessions.add(new WeakReference<Session>(sess));
                    // MONITOREXIT : var1_1
                    return sess;
                }
                ws = var3_5.next();
                this.nonPooledSessions.remove(ws);
            }
        }
        prot = null;
        hostsList = this.connUrl.getHostsList();
        sess = this;
        // MONITORENTER : sess
        toCloseAndRemove = this.idleProtocols.stream().filter((Predicate<PooledXProtocol>)LambdaMetafactory.metafactory(null, null, null, (Ljava/lang/Object;)Z, lambda$getSession$1(java.util.List com.mysql.cj.xdevapi.ClientImpl$PooledXProtocol ), (Lcom/mysql/cj/xdevapi/ClientImpl$PooledXProtocol;)Z)(hostsList)).collect(Collectors.toList());
        ((Stream)toCloseAndRemove.stream().peek((Consumer<PooledXProtocol>)LambdaMetafactory.metafactory(null, null, null, (Ljava/lang/Object;)V, realClose(), (Lcom/mysql/cj/xdevapi/ClientImpl$PooledXProtocol;)V)()).peek((Consumer<PooledXProtocol>)LambdaMetafactory.metafactory(null, null, null, (Ljava/lang/Object;)V, remove(java.lang.Object ), (Lcom/mysql/cj/xdevapi/ClientImpl$PooledXProtocol;)V)(this.idleProtocols)).map((Function<PooledXProtocol, HostInfo>)LambdaMetafactory.metafactory(null, null, null, (Ljava/lang/Object;)Ljava/lang/Object;, getHostInfo(), (Lcom/mysql/cj/xdevapi/ClientImpl$PooledXProtocol;)Lcom/mysql/cj/conf/HostInfo;)()).sequential()).forEach((Consumer<HostInfo>)LambdaMetafactory.metafactory(null, null, null, (Ljava/lang/Object;)V, remove(java.lang.Object ), (Lcom/mysql/cj/conf/HostInfo;)V)(this.demotedHosts));
        // MONITOREXIT : sess
        start = System.currentTimeMillis();
        while (true) {
            block39: {
                block41: {
                    block37: {
                        block40: {
                            block38: {
                                if (prot != null || this.queueTimeout != 0 && System.currentTimeMillis() >= start + (long)this.queueTimeout) break block37;
                                s = this.idleProtocols;
                                // MONITORENTER : s
                                if (this.idleProtocols.peek() == null) break block38;
                                tryProt = (PooledXProtocol)this.idleProtocols.poll();
                                if (tryProt.isOpen()) {
                                    if (tryProt.isIdleTimeoutReached()) {
                                        tryProt.realClose();
                                    } else {
                                        try {
                                            tryProt.reset();
                                            prot = tryProt;
                                        }
                                        catch (CJCommunicationsException | XProtocolError var7_15) {}
                                    }
                                }
                                break block39;
                            }
                            if (this.idleProtocols.size() + this.activeProtocols.size() >= this.maxSize) break block40;
                            latestException = null;
                            hostsToRevisit = new ArrayList<HostInfo>();
                            var8_16 = hostsList.iterator();
                            break block41;
                        }
                        if (this.queueTimeout > 0) {
                            currentTimeout = (long)this.queueTimeout - (System.currentTimeMillis() - start);
                            try {
                                if (currentTimeout <= 0L) ** GOTO lbl117
                                prot = this.idleProtocols.poll(currentTimeout, TimeUnit.MILLISECONDS);
                            }
                            catch (InterruptedException e) {
                                throw new XDevAPIError("Session can not be obtained within " + this.queueTimeout + " milliseconds.", e);
                            }
                        } else {
                            prot = (PooledXProtocol)this.idleProtocols.poll();
                        }
                        break block39;
                    }
                    if (prot == null) {
                        throw new XDevAPIError("Session can not be obtained within " + this.queueTimeout + " milliseconds.");
                    }
                    s = this;
                    // MONITORENTER : s
                    this.activeProtocols.add(new WeakReference<Object>(prot));
                    // MONITOREXIT : s
                    return new SessionImpl(prot);
                }
                while (var8_16.hasNext()) {
                    hi = var8_16.next();
                    if (this.demotedHosts.containsKey(hi)) {
                        if (start - this.demotedHosts.get(hi) > (long)this.demotedTimeout) {
                            this.demotedHosts.remove(hi);
                        } else {
                            hostsToRevisit.add(hi);
                            continue;
                        }
                    }
                    try {
                        prot = this.newPooledXProtocol(hi);
                        break;
                    }
                    catch (CJCommunicationsException e) {
                        if (e.getCause() == null) {
                            throw e;
                        }
                        latestException = e;
                        this.demotedHosts.put(hi, System.currentTimeMillis());
                    }
                }
                if (prot == null) {
                    for (HostInfo hi : hostsToRevisit) {
                        try {
                            prot = this.newPooledXProtocol(hi);
                            this.demotedHosts.remove(hi);
                            break;
                        }
                        catch (CJCommunicationsException e) {
                            if (e.getCause() == null) {
                                throw e;
                            }
                            latestException = e;
                            this.demotedHosts.put(hi, System.currentTimeMillis());
                        }
                    }
                }
                if (prot == null && latestException != null) {
                    throw ExceptionFactory.createException(CJCommunicationsException.class, Messages.getString("Session.Create.Failover.0"), latestException);
                }
            }
            // MONITOREXIT : s
        }
    }

    private PooledXProtocol newPooledXProtocol(HostInfo hi) {
        DefaultPropertySet pset = new DefaultPropertySet();
        pset.initializeProperties(hi.exposeAsProperties());
        PooledXProtocol tryProt = new PooledXProtocol(hi, pset);
        tryProt.addListener(this);
        tryProt.connect(hi.getUser(), hi.getPassword(), hi.getDatabase());
        return tryProt;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void close() {
        ClientImpl clientImpl = this;
        synchronized (clientImpl) {
            if (this.poolingEnabled) {
                if (!this.isClosed) {
                    this.isClosed = true;
                    this.idleProtocols.forEach(s -> s.realClose());
                    this.idleProtocols.clear();
                    this.activeProtocols.stream().map(Reference::get).filter(Objects::nonNull).forEach(s -> s.realClose());
                    this.activeProtocols.clear();
                }
            } else {
                this.nonPooledSessions.stream().map(Reference::get).filter(Objects::nonNull).filter(Session::isOpen).forEach(s -> s.close());
            }
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    void idleProtocol(PooledXProtocol prot) {
        ClientImpl clientImpl = this;
        synchronized (clientImpl) {
            if (!this.isClosed) {
                ArrayList<WeakReference<PooledXProtocol>> removeThem = new ArrayList<WeakReference<PooledXProtocol>>();
                for (WeakReference<PooledXProtocol> wps : this.activeProtocols) {
                    if (wps == null) continue;
                    PooledXProtocol as = (PooledXProtocol)wps.get();
                    if (as == null) {
                        removeThem.add(wps);
                        continue;
                    }
                    if (as != prot) continue;
                    removeThem.add(wps);
                    this.idleProtocols.add(as);
                }
                for (WeakReference<PooledXProtocol> wr : removeThem) {
                    this.activeProtocols.remove(wr);
                }
            }
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void handleEvent(Protocol.ProtocolEventListener.EventType type, Object info, Throwable reason) {
        switch (type) {
            case SERVER_SHUTDOWN: {
                HostInfo hi = ((PooledXProtocol)info).getHostInfo();
                ClientImpl clientImpl = this;
                synchronized (clientImpl) {
                    List toCloseAndRemove = this.idleProtocols.stream().filter(p -> p.getHostInfo().equalHostPortPair(hi)).collect(Collectors.toList());
                    ((Stream)toCloseAndRemove.stream().peek(PooledXProtocol::realClose).peek(this.idleProtocols::remove).map(PooledXProtocol::getHostInfo).sequential()).forEach(this.demotedHosts::remove);
                    this.removeActivePooledXProtocol((PooledXProtocol)info);
                    break;
                }
            }
            case SERVER_CLOSED_SESSION: {
                ClientImpl clientImpl = this;
                synchronized (clientImpl) {
                    this.removeActivePooledXProtocol((PooledXProtocol)info);
                    break;
                }
            }
        }
    }

    private void removeActivePooledXProtocol(PooledXProtocol prot) {
        WeakReference<PooledXProtocol> wprot = null;
        for (WeakReference<PooledXProtocol> wps : this.activeProtocols) {
            PooledXProtocol as;
            if (wps == null || (as = (PooledXProtocol)wps.get()) != prot) continue;
            wprot = wps;
            break;
        }
        this.activeProtocols.remove(wprot);
        prot.realClose();
    }

    private static /* synthetic */ boolean lambda$getSession$1(List hostsList, PooledXProtocol p) {
        return !p.isHostInfoValid(hostsList);
    }

    public class PooledXProtocol
    extends XProtocol {
        long idleSince;
        HostInfo hostInfo;

        public PooledXProtocol(HostInfo hostInfo, PropertySet propertySet) {
            super(hostInfo, propertySet);
            this.idleSince = -1L;
            this.hostInfo = null;
            this.hostInfo = hostInfo;
        }

        @Override
        public void close() {
            this.reset();
            this.idleSince = System.currentTimeMillis();
            ClientImpl.this.idleProtocol(this);
        }

        public HostInfo getHostInfo() {
            return this.hostInfo;
        }

        boolean isIdleTimeoutReached() {
            return ClientImpl.this.maxIdleTime > 0 && this.idleSince > 0L && System.currentTimeMillis() > this.idleSince + (long)ClientImpl.this.maxIdleTime;
        }

        boolean isHostInfoValid(List<HostInfo> hostsList) {
            return hostsList.stream().filter(h -> h.equalHostPortPair(this.hostInfo)).findFirst().isPresent();
        }

        void realClose() {
            try {
                super.close();
            }
            catch (IOException iOException) {
                // empty catch block
            }
        }
    }
}

