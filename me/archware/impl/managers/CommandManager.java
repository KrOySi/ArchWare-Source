/*
 * Decompiled with CFR 0.150.
 */
package me.archware.impl.managers;

import java.util.ArrayList;
import me.archware.base.command.Command;
import me.archware.impl.commands.AddMacro;
import me.archware.impl.commands.Bind;
import me.archware.impl.commands.CommandSetting;
import me.archware.impl.commands.ConfigList;
import me.archware.impl.commands.ConfigLoad;
import me.archware.impl.commands.Container;
import me.archware.impl.commands.IRC;
import me.archware.impl.commands.ModuleToggle;
import me.archware.impl.commands.RemoveMacro;
import me.archware.impl.commands.Scrapper;
import me.archware.impl.commands.VClip;

public class CommandManager {
    public final ArrayList<Command> commands = new ArrayList();

    public CommandManager() {
        this.commands.add(new VClip());
        this.commands.add(new Scrapper());
        this.commands.add(new ModuleToggle());
        this.commands.add(new CommandSetting());
        this.commands.add(new Container());
        this.commands.add(new ConfigList());
        this.commands.add(new ConfigLoad());
        this.commands.add(new Bind());
        this.commands.add(new AddMacro());
        this.commands.add(new RemoveMacro());
        this.commands.add(new IRC());
    }

    public ArrayList<Command> getCommands() {
        return this.commands;
    }
}

