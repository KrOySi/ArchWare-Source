/*
 * Decompiled with CFR 0.150.
 */
package com.mysql.cj.jdbc;

import com.mysql.cj.conf.DefaultPropertySet;
import com.mysql.cj.conf.PropertyDefinition;
import com.mysql.cj.conf.PropertyDefinitions;
import com.mysql.cj.conf.PropertyKey;
import com.mysql.cj.conf.RuntimeProperty;
import com.mysql.cj.jdbc.JdbcPropertySet;
import com.mysql.cj.util.StringUtils;
import java.sql.DriverPropertyInfo;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class JdbcPropertySetImpl
extends DefaultPropertySet
implements JdbcPropertySet {
    private static final long serialVersionUID = -8223499903182568260L;

    @Override
    public void postInitialization() {
        String testEncoding;
        if (this.getIntegerProperty(PropertyKey.maxRows).getValue() == 0) {
            super.getProperty(PropertyKey.maxRows).setValue(-1, null);
        }
        if ((testEncoding = this.getStringProperty(PropertyKey.characterEncoding).getValue()) != null) {
            String testString = "abc";
            StringUtils.getBytes(testString, testEncoding);
        }
        if (this.getBooleanProperty(PropertyKey.useCursorFetch).getValue().booleanValue()) {
            super.getProperty(PropertyKey.useServerPrepStmts).setValue(true);
        }
    }

    @Override
    public List<DriverPropertyInfo> exposeAsDriverPropertyInfo() throws SQLException {
        return PropertyDefinitions.PROPERTY_KEY_TO_PROPERTY_DEFINITION.entrySet().stream().filter(e -> !((PropertyDefinition)e.getValue()).getCategory().equals(PropertyDefinitions.CATEGORY_XDEVAPI)).map(Map.Entry::getKey).map(this::getProperty).map(this::getAsDriverPropertyInfo).collect(Collectors.toList());
    }

    private DriverPropertyInfo getAsDriverPropertyInfo(RuntimeProperty<?> pr) {
        PropertyDefinition<?> pdef = pr.getPropertyDefinition();
        DriverPropertyInfo dpi = new DriverPropertyInfo(pdef.getName(), null);
        dpi.choices = pdef.getAllowableValues();
        dpi.value = pr.getStringValue() != null ? pr.getStringValue() : null;
        dpi.required = false;
        dpi.description = pdef.getDescription();
        return dpi;
    }
}

