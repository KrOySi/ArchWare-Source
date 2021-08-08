/*
 * Decompiled with CFR 0.150.
 */
package me.archware.impl.commands;

import java.io.File;
import me.archware.ArchWare;
import me.archware.base.command.Command;
import me.archware.impl.managers.FileManager;
import net.minecraft.util.text.TextFormatting;

public class ConfigList
extends Command {
    public ConfigList() {
        super("ConfigList", new String[]{"configlist", "configs"});
    }

    @Override
    public void onCommand(String[] args) {
        StringBuilder configs = new StringBuilder();
        for (File file : new File(FileManager.HOME.getAbsolutePath() + "\\configs").listFiles()) {
            if (!file.isFile()) continue;
            configs.append((Object)((Object)TextFormatting.YELLOW) + "> " + (Object)((Object)TextFormatting.GREEN) + file.getName() + "\n");
        }
        if (configs.toString().isEmpty()) {
            ArchWare.sendChatMessage("No configs found");
        } else {
            ArchWare.sendChatMessage("List of config files:\n" + configs.toString());
        }
    }
}

