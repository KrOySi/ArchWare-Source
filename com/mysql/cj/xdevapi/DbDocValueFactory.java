/*
 * Decompiled with CFR 0.150.
 */
package com.mysql.cj.xdevapi;

import com.mysql.cj.conf.PropertySet;
import com.mysql.cj.exceptions.AssertionFailedException;
import com.mysql.cj.result.DefaultValueFactory;
import com.mysql.cj.result.Field;
import com.mysql.cj.util.StringUtils;
import com.mysql.cj.xdevapi.DbDoc;
import com.mysql.cj.xdevapi.JsonParser;
import java.io.IOException;
import java.io.StringReader;

public class DbDocValueFactory
extends DefaultValueFactory<DbDoc> {
    public DbDocValueFactory(PropertySet pset) {
        super(pset);
    }

    @Override
    public DbDoc createFromBytes(byte[] bytes, int offset, int length, Field f) {
        try {
            return JsonParser.parseDoc(new StringReader(StringUtils.toString(bytes, offset, length, f.getEncoding())));
        }
        catch (IOException ex) {
            throw AssertionFailedException.shouldNotHappen(ex);
        }
    }

    @Override
    public DbDoc createFromNull() {
        return null;
    }

    @Override
    public String getTargetTypeName() {
        return DbDoc.class.getName();
    }
}

