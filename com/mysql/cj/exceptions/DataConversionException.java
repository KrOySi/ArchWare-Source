/*
 * Decompiled with CFR 0.150.
 */
package com.mysql.cj.exceptions;

import com.mysql.cj.exceptions.DataReadException;

public class DataConversionException
extends DataReadException {
    private static final long serialVersionUID = -863576663404236982L;

    public DataConversionException(String msg) {
        super(msg);
        this.setSQLState("22018");
    }
}

