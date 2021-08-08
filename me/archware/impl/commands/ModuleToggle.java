/*
 * Decompiled with CFR 0.150.
 */
package me.archware.impl.commands;

import me.archware.ArchWare;
import me.archware.base.command.Command;
import me.archware.impl.utils.ChatUtils;

public class ModuleToggle
extends Command {
    public ModuleToggle() {
        super("ModuleToggle", new String[]{"t", "toggle"});
    }

    @Override
    public void onCommand(String[] args) {
        if (args.length == 2 && args[0].equalsIgnoreCase("t") || args.length == 2 && args[0].equalsIgnoreCase("toggle")) {
            try {
                ArchWare.moduleManager.getModuleByName(args[1]).toggle();
            }
            catch (Exception e) {
                ChatUtils.printChatMessage("Wrong usage!!!");
            }
        } else {
            ChatUtils.printChatMessage("Wrong usage!!!");
        }
    }
}

