/*
 * Decompiled with CFR 0.150.
 */
package com.mysql.cj.jdbc;

import com.mysql.cj.jdbc.NonRegisteringDriver;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Driver
extends NonRegisteringDriver
implements java.sql.Driver {
    static {
        try {
            DriverManager.registerDriver(new Driver());
        }
        catch (SQLException E) {
            throw new RuntimeException("Can't register driver!");
        }
    }
}

