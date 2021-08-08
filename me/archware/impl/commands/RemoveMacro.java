/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  org.lwjgl.input.Keyboard
 */
package me.archware.impl.commands;

import me.archware.ArchWare;
import me.archware.base.command.Command;
import net.minecraft.util.text.TextFormatting;
import org.lwjgl.input.Keyboard;

public class RemoveMacro
extends Command {
    public RemoveMacro() {
        super("RemoveMacro", new String[]{"removemacro", "-macro"});
    }

    @Override
    public void onCommand(String[] args) {
        if (args.length < 2) {
            ArchWare.sendChatMessage((Object)((Object)TextFormatting.RED) + "Invalid syntax. (Example of usage: .-macro key)");
            return;
        }
        ArchWare.macroManager.removeMacro(Keyboard.getKeyIndex((String)args[1].toUpperCase()));
        ArchWare.macroManager.update();
        ArchWare.sendChatMessage((Object)((Object)TextFormatting.GREEN) + "Macros successfully removed!");
    }
}

