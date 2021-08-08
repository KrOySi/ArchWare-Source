/*
 * Decompiled with CFR 0.150.
 */
package com.mysql.cj.protocol.x;

import java.nio.channels.CompletionHandler;
import java.util.concurrent.CompletableFuture;

public class ErrorToFutureCompletionHandler<T>
implements CompletionHandler<T, Void> {
    private CompletableFuture<?> future;
    private Runnable successCallback;

    public ErrorToFutureCompletionHandler(CompletableFuture<?> future, Runnable successCallback) {
        this.future = future;
        this.successCallback = successCallback;
    }

    @Override
    public void completed(T result, Void attachment) {
        this.successCallback.run();
    }

    @Override
    public void failed(Throwable ex, Void attachment) {
        this.future.completeExceptionally(ex);
    }
}

