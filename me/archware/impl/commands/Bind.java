/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  org.lwjgl.input.Keyboard
 */
package me.archware.impl.commands;

import me.archware.ArchWare;
import me.archware.base.command.Command;
import me.archware.base.module.Module;
import net.minecraft.util.text.TextFormatting;
import org.lwjgl.input.Keyboard;

public class Bind
extends Command {
    public Bind() {
        super("Bind", new String[]{"bind", "b"});
    }

    @Override
    public void onCommand(String[] args) {
        for (Module m : ArchWare.moduleManager.modules) {
            if (args.length >= 2) {
                if (!m.getName().equalsIgnoreCase(args[1])) continue;
                if (args[2].equalsIgnoreCase("0")) {
                    m.setKey(0);
                    ArchWare.sendChatMessage((Object)((Object)TextFormatting.GREEN) + m.getName() + " successfully unbinded");
                } else {
                    m.setKey(Keyboard.getKeyIndex((String)args[2].toUpperCase()));
                    ArchWare.sendChatMessage((Object)((Object)TextFormatting.GREEN) + m.getName() + " successfully binded to " + args[2].toUpperCase());
                }
                return;
            }
            ArchWare.sendChatMessage((Object)((Object)TextFormatting.RED) + "Invalid syntax (.bind MODULE_NAME KEY)");
        }
        ArchWare.sendChatMessage((Object)((Object)TextFormatting.RED) + "Module not found");
    }
}

