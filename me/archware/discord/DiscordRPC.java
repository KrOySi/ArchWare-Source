/*
 * Decompiled with CFR 0.150.
 */
package me.archware.discord;

import club.minnced.discord.rpc.DiscordEventHandlers;
import club.minnced.discord.rpc.DiscordRichPresence;
import java.io.IOException;
import me.archware.antiautistprotection.AAP;
import net.minecraft.client.Minecraft;

public class DiscordRPC {
    private static Minecraft mc = Minecraft.getMinecraft();

    public static void init() {
        DiscordEventHandlers handlers = new DiscordEventHandlers();
        handlers.ready = user -> {
            try {
                AAP.log(user.username + "#" + user.discriminator);
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        };
        club.minnced.discord.rpc.DiscordRPC discordRPC = club.minnced.discord.rpc.DiscordRPC.INSTANCE;
        discordRPC.Discord_Initialize("849671345654464533", handlers, false, null);
        DiscordRichPresence discordRichPresence = new DiscordRichPresence();
        discordRichPresence.largeImageKey = "logo";
        discordRichPresence.smallImageKey = "logo";
        discordRichPresence.largeImageText = "Using Development build: 130721 version";
        new Thread(() -> {
            while (true) {
                discordRPC.Discord_UpdatePresence(discordRichPresence);
                discordRichPresence.smallImageText = DiscordRPC.mc.world != null ? (mc.isSingleplayer() ? "Playing singleplayer" : "Playing on " + DiscordRPC.mc.getCurrentServerData().serverIP) : "AFK";
                discordRPC.Discord_RunCallbacks();
                try {
                    Thread.sleep(5000L);
                    continue;
                }
                catch (InterruptedException e) {
                    e.printStackTrace();
                    continue;
                }
                break;
            }
        }).start();
        System.out.println("Discord RPC initialized");
    }
}

