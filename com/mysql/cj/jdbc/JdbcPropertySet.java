/*
 * Decompiled with CFR 0.150.
 */
package com.mysql.cj.jdbc;

import com.mysql.cj.conf.PropertySet;
import java.sql.DriverPropertyInfo;
import java.sql.SQLException;
import java.util.List;

public interface JdbcPropertySet
extends PropertySet {
    public List<DriverPropertyInfo> exposeAsDriverPropertyInfo() throws SQLException;
}

