/*
 * Decompiled with CFR 0.150.
 */
package me.archware.impl.commands;

import me.archware.base.command.Command;
import me.archware.impl.utils.ChatUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.network.play.client.CPacketPlayer;
import net.minecraft.util.math.MathHelper;

public class VClip
extends Command {
    private static final Minecraft mc = Minecraft.getMinecraft();

    public VClip() {
        super("Vclip", new String[]{"vclip", "hclip"});
    }

    @Override
    public void onCommand(String[] args) {
        if (args.length == 2 && args[0].equalsIgnoreCase("vclip")) {
            try {
                ChatUtils.printChatMessage("Clipped " + Double.valueOf(args[1]) + " blocks!");
                VClip.mc.player.setPosition(VClip.mc.player.posX, VClip.mc.player.posY + Double.valueOf(args[1]), VClip.mc.player.posZ);
                mc.getConnection().sendPacket(new CPacketPlayer(true));
            }
            catch (Exception formatException) {
                ChatUtils.printChatMessage("Wrong usage!!!");
                formatException.printStackTrace();
            }
        } else if (args.length == 2 && args[0].equalsIgnoreCase("hclip")) {
            try {
                ChatUtils.printChatMessage("Clipped " + Double.valueOf(args[1]) + " blocks!");
                float f = VClip.mc.player.rotationYaw * ((float)Math.PI / 180);
                double speed = Double.valueOf(args[1]);
                double x = -((double)MathHelper.sin(f) * speed);
                double z = (double)MathHelper.cos(f) * speed;
                VClip.mc.player.setPosition(VClip.mc.player.posX + x, VClip.mc.player.posY, VClip.mc.player.posZ + z);
            }
            catch (Exception formatException) {
                ChatUtils.printChatMessage("Wrong usage!!!");
                formatException.printStackTrace();
            }
        }
    }
}

