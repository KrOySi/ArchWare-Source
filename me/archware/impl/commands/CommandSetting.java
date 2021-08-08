/*
 * Decompiled with CFR 0.150.
 */
package me.archware.impl.commands;

import me.archware.ArchWare;
import me.archware.base.command.Command;
import me.archware.base.module.Module;
import me.archware.base.setting.Setting;
import me.archware.impl.utils.ChatUtils;

public class CommandSetting
extends Command {
    public CommandSetting() {
        super("CommandSetting", new String[]{"module"});
    }

    @Override
    public void onCommand(String[] args) {
        if (args.length == 4 && ArchWare.moduleManager.getModuleByName(args[1]) != null && args[0].equalsIgnoreCase("module")) {
            try {
                Module mod = ArchWare.moduleManager.getModuleByName(args[1]);
                Setting set = ArchWare.settingManager.getSettingByName(args[2]);
                if (set.isTypeNumeric()) {
                    ArchWare.settingManager.getSetting(mod, args[2]).setValueNumeric(Float.parseFloat(args[3]));
                } else if (set.isTypeBoolean()) {
                    ArchWare.settingManager.getSetting(mod, args[2]).setValueBoolean(Boolean.parseBoolean(args[3]));
                } else if (set.isTypeString()) {
                    ArchWare.settingManager.getSetting(mod, args[2]).setValueString(args[3]);
                }
            }
            catch (Exception e) {
                ChatUtils.printChatMessage("Wrong usage!!!");
            }
        } else {
            ChatUtils.printChatMessage("Wrong usage!!!");
        }
    }
}

