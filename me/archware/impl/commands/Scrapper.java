/*
 * Decompiled with CFR 0.150.
 */
package me.archware.impl.commands;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import me.archware.base.command.Command;
import me.archware.impl.utils.ChatUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;

public class Scrapper
extends Command {
    private static final Minecraft mc = Minecraft.getMinecraft();

    public Scrapper() {
        super("Scrapper", new String[]{"scrap"});
    }

    @Override
    public void onCommand(String[] args) {
        if (args.length == 4 && args[1].equalsIgnoreCase("contains")) {
            try {
                File fil = new File("ArchWare//scrapper//" + args[2] + ".txt");
                if (!fil.exists()) {
                    fil.createNewFile();
                }
                if (!new File("ArchWare//scrapper//").exists()) {
                    new File("ArchWare//scrapper//").mkdir();
                }
                BufferedWriter writer = new BufferedWriter(new FileWriter(fil));
                Scrapper.mc.world.getLoadedEntityList().stream().filter(entityPlayer -> entityPlayer.getDisplayName().getFormattedText().contains(args[2]) && entityPlayer instanceof EntityPlayer).forEach(entityPlayer -> {
                    try {
                        if (Boolean.parseBoolean(args[3])) {
                            writer.write(entityPlayer.getDisplayName().getFormattedText() + "\n");
                        } else {
                            writer.write(entityPlayer.getName() + "\n");
                        }
                    }
                    catch (IOException e) {
                        e.printStackTrace();
                    }
                });
                writer.close();
            }
            catch (Exception formatException) {
                ChatUtils.printChatMessage("Wrong usage!!!");
                formatException.printStackTrace();
            }
        } else {
            ChatUtils.printChatMessage("Wrong usage!!!");
        }
    }
}

