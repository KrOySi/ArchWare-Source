/*
 * Decompiled with CFR 0.150.
 */
package com.mysql.cj.jdbc.ha;

import java.util.Properties;

public interface LoadBalanceExceptionChecker {
    public void init(Properties var1);

    public void destroy();

    public boolean shouldExceptionTriggerFailover(Throwable var1);
}

