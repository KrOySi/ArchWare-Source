/*
 * Decompiled with CFR 0.150.
 */
package com.mysql.cj.jdbc;

import com.mysql.cj.MysqlConnection;
import com.mysql.cj.protocol.NetworkResources;
import java.lang.ref.PhantomReference;
import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class AbandonedConnectionCleanupThread
implements Runnable {
    private static final Set<ConnectionFinalizerPhantomReference> connectionFinalizerPhantomRefs = ConcurrentHashMap.newKeySet();
    private static final ReferenceQueue<MysqlConnection> referenceQueue = new ReferenceQueue();
    private static final ExecutorService cleanupThreadExecutorService;
    private static Thread threadRef;
    private static Lock threadRefLock;
    private static boolean abandonedConnectionCleanupDisabled;

    private AbandonedConnectionCleanupThread() {
    }

    @Override
    public void run() {
        while (true) {
            try {
                while (true) {
                    this.checkThreadContextClassLoader();
                    Reference<MysqlConnection> reference = referenceQueue.remove(5000L);
                    if (reference == null) continue;
                    AbandonedConnectionCleanupThread.finalizeResource((ConnectionFinalizerPhantomReference)reference);
                }
            }
            catch (InterruptedException e) {
                threadRefLock.lock();
                try {
                    Reference<MysqlConnection> reference;
                    threadRef = null;
                    while ((reference = referenceQueue.poll()) != null) {
                        AbandonedConnectionCleanupThread.finalizeResource((ConnectionFinalizerPhantomReference)reference);
                    }
                    connectionFinalizerPhantomRefs.clear();
                }
                finally {
                    threadRefLock.unlock();
                }
                return;
            }
            catch (Exception exception) {
                continue;
            }
            break;
        }
    }

    private void checkThreadContextClassLoader() {
        try {
            threadRef.getContextClassLoader().getResource("");
        }
        catch (Throwable e) {
            AbandonedConnectionCleanupThread.uncheckedShutdown();
        }
    }

    private static boolean consistentClassLoaders() {
        threadRefLock.lock();
        try {
            if (threadRef == null) {
                boolean bl = false;
                return bl;
            }
            ClassLoader callerCtxClassLoader = Thread.currentThread().getContextClassLoader();
            ClassLoader threadCtxClassLoader = threadRef.getContextClassLoader();
            boolean bl = callerCtxClassLoader != null && threadCtxClassLoader != null && callerCtxClassLoader == threadCtxClassLoader;
            return bl;
        }
        finally {
            threadRefLock.unlock();
        }
    }

    private static void shutdown(boolean checked) {
        if (checked && !AbandonedConnectionCleanupThread.consistentClassLoaders()) {
            return;
        }
        if (cleanupThreadExecutorService != null) {
            cleanupThreadExecutorService.shutdownNow();
        }
    }

    public static void checkedShutdown() {
        AbandonedConnectionCleanupThread.shutdown(true);
    }

    public static void uncheckedShutdown() {
        AbandonedConnectionCleanupThread.shutdown(false);
    }

    public static boolean isAlive() {
        threadRefLock.lock();
        try {
            boolean bl = threadRef != null && threadRef.isAlive();
            return bl;
        }
        finally {
            threadRefLock.unlock();
        }
    }

    protected static void trackConnection(MysqlConnection conn, NetworkResources io) {
        if (abandonedConnectionCleanupDisabled) {
            return;
        }
        threadRefLock.lock();
        try {
            if (AbandonedConnectionCleanupThread.isAlive()) {
                ConnectionFinalizerPhantomReference reference = new ConnectionFinalizerPhantomReference(conn, io, referenceQueue);
                connectionFinalizerPhantomRefs.add(reference);
            }
        }
        finally {
            threadRefLock.unlock();
        }
    }

    private static void finalizeResource(ConnectionFinalizerPhantomReference reference) {
        try {
            reference.finalizeResources();
            reference.clear();
        }
        finally {
            connectionFinalizerPhantomRefs.remove(reference);
        }
    }

    static {
        threadRef = null;
        threadRefLock = new ReentrantLock();
        abandonedConnectionCleanupDisabled = Boolean.getBoolean("com.mysql.cj.disableAbandonedConnectionCleanup");
        if (abandonedConnectionCleanupDisabled) {
            cleanupThreadExecutorService = null;
        } else {
            cleanupThreadExecutorService = Executors.newSingleThreadExecutor(r -> {
                Thread t = new Thread(r, "mysql-cj-abandoned-connection-cleanup");
                t.setDaemon(true);
                ClassLoader classLoader = AbandonedConnectionCleanupThread.class.getClassLoader();
                if (classLoader == null) {
                    classLoader = ClassLoader.getSystemClassLoader();
                }
                t.setContextClassLoader(classLoader);
                threadRef = t;
                return threadRef;
            });
            cleanupThreadExecutorService.execute(new AbandonedConnectionCleanupThread());
        }
    }

    private static class ConnectionFinalizerPhantomReference
    extends PhantomReference<MysqlConnection> {
        private NetworkResources networkResources;

        ConnectionFinalizerPhantomReference(MysqlConnection conn, NetworkResources networkResources, ReferenceQueue<? super MysqlConnection> refQueue) {
            super(conn, refQueue);
            this.networkResources = networkResources;
        }

        void finalizeResources() {
            if (this.networkResources != null) {
                try {
                    this.networkResources.forceClose();
                }
                finally {
                    this.networkResources = null;
                }
            }
        }
    }
}

