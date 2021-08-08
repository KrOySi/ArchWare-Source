/*
 * Decompiled with CFR 0.150.
 */
package me.archware.impl.commands;

import java.io.File;
import java.io.IOException;
import me.archware.ArchWare;
import me.archware.base.command.Command;
import me.archware.impl.managers.FileManager;
import net.minecraft.util.text.TextFormatting;

public class ConfigLoad
extends Command {
    public ConfigLoad() {
        super("ConfigLoad", new String[]{"cfg", "load"});
    }

    @Override
    public void onCommand(String[] args) {
        if (new File(FileManager.HOME + "\\configs\\" + args[1]).exists()) {
            ArchWare.sendChatMessage("Loading config");
            try {
                FileManager.initConfig(args[1]);
            }
            catch (IOException e) {
                ArchWare.sendChatMessage((Object)((Object)TextFormatting.RED) + "Something get wrong while loading config\n" + e.getMessage());
            }
        }
    }
}

