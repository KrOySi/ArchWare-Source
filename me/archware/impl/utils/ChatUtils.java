/*
 * Decompiled with CFR 0.150.
 */
package me.archware.impl.utils;

import net.minecraft.client.Minecraft;
import net.minecraft.util.text.TextComponentString;

public class ChatUtils {
    private static final Minecraft mc = Minecraft.getMinecraft();

    public static TextComponentString textComponentStringFromText(String text) {
        return new TextComponentString(text);
    }

    public static void printChatMessage(TextComponentString message) {
        ChatUtils.mc.ingameGUI.getChatGUI().printChatMessage(message);
    }

    public static void printChatMessage(String message) {
        ChatUtils.mc.ingameGUI.getChatGUI().printChatMessage(new TextComponentString(message));
    }
}

