/*
 * Decompiled with CFR 0.150.
 */
package me.archware.impl.utils;

import net.minecraft.client.Minecraft;

public class MathUtils {
    private static final Minecraft mc = Minecraft.getMinecraft();

    public static double randomNumber(double min, double max) {
        return Math.random() * (max - min) + min;
    }
}

