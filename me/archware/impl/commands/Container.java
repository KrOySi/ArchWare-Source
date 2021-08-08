/*
 * Decompiled with CFR 0.150.
 */
package me.archware.impl.commands;

import me.archware.ArchWare;
import me.archware.base.command.Command;
import me.archware.impl.utils.ContainerViewer;
import net.minecraft.client.Minecraft;

public class Container
extends Command {
    public Container() {
        super("container", "Shows inventory container of player", new String[]{"inventory", "c", "i"});
    }

    @Override
    public void onCommand(String[] args) {
        if (Minecraft.getMinecraft().world.getPlayerEntityByName(args[1]) != null) {
            Minecraft.getMinecraft().displayGuiScreen(new ContainerViewer(Minecraft.getMinecraft().world.getPlayerEntityByName(args[1])));
            ArchWare.sendChatMessage("Unable to find asdasd" + args[1]);
        } else {
            ArchWare.sendChatMessage("Unable to find " + args[1]);
        }
    }
}

