/*
 * Decompiled with CFR 0.150.
 */
package com.mysql.cj;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

public class Messages {
    private static final String BUNDLE_NAME = "com.mysql.cj.LocalizedErrorMessages";
    private static final ResourceBundle RESOURCE_BUNDLE;
    private static final Object[] emptyObjectArray;

    public static String getString(String key) {
        return Messages.getString(key, emptyObjectArray);
    }

    public static String getString(String key, Object[] args) {
        if (RESOURCE_BUNDLE == null) {
            throw new RuntimeException("Localized messages from resource bundle 'com.mysql.cj.LocalizedErrorMessages' not loaded during initialization of driver.");
        }
        try {
            if (key == null) {
                throw new IllegalArgumentException("Message key can not be null");
            }
            String message = RESOURCE_BUNDLE.getString(key);
            if (message == null) {
                message = "Missing error message for key '" + key + "'";
            }
            return MessageFormat.format(message, args);
        }
        catch (MissingResourceException e) {
            return '!' + key + '!';
        }
    }

    private Messages() {
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    static {
        emptyObjectArray = new Object[0];
        ResourceBundle temp = null;
        try {
            temp = ResourceBundle.getBundle(BUNDLE_NAME, Locale.getDefault(), Messages.class.getClassLoader());
        }
        catch (Throwable t) {
            try {
                temp = ResourceBundle.getBundle(BUNDLE_NAME);
            }
            catch (Throwable t2) {
                RuntimeException rt = new RuntimeException("Can't load resource bundle due to underlying exception " + t.toString());
                rt.initCause(t2);
                throw rt;
            }
        }
        finally {
            RESOURCE_BUNDLE = temp;
        }
    }
}

