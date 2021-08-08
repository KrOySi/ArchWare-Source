/*
 * Decompiled with CFR 0.150.
 */
package com.mysql.cj.callback;

import com.mysql.cj.callback.MysqlCallback;

public class UsernameCallback
implements MysqlCallback {
    private String username;

    public UsernameCallback(String username) {
        this.username = username;
    }

    public String getUsername() {
        return this.username;
    }
}

