/*
 * Decompiled with CFR 0.150.
 */
package com.mysql.cj.exceptions;

import com.mysql.cj.log.Log;
import java.util.Properties;

public interface ExceptionInterceptor {
    public ExceptionInterceptor init(Properties var1, Log var2);

    public void destroy();

    public Exception interceptException(Exception var1);
}

