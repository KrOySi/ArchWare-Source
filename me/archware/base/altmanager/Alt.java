/*
 * Decompiled with CFR 0.150.
 */
package me.archware.base.altmanager;

public class Alt {
    String name;
    String password;

    public Alt(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

