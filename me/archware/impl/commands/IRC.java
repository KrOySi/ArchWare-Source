/*
 * Decompiled with CFR 0.150.
 */
package me.archware.impl.commands;

import me.archware.base.command.Command;
import me.archware.irc.Server;

public class IRC
extends Command {
    public IRC() {
        super("IRC", "Send a message to IRC server", new String[]{"irc"});
    }

    @Override
    public void onCommand(String[] args) {
        Server.sendMessage(args[1]);
    }
}

