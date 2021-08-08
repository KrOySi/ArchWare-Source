/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  org.lwjgl.opengl.Display
 */
package me.archware;

import java.awt.Font;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import me.archware.base.altmanager.Alt;
import me.archware.base.altmanager.AltManagerGUI;
import me.archware.base.clickgui.util.FontUtil;
import me.archware.base.event.EventManager;
import me.archware.base.newgui.ClickGUI;
import me.archware.discord.DiscordRPC;
import me.archware.impl.managers.CommandManager;
import me.archware.impl.managers.FileManager;
import me.archware.impl.managers.FriendManager;
import me.archware.impl.managers.MacroManager;
import me.archware.impl.managers.ModuleManager;
import me.archware.impl.managers.SettingsManager;
import me.archware.impl.modules.render.tabgui.TabManager;
import me.archware.impl.utils.ChatUtils;
import me.archware.impl.utils.SpammerUtils;
import me.archware.impl.utils.font.CustomFontRenderer;
import me.archware.viaversion.ViaMCP;
import net.minecraft.util.text.TextFormatting;
import org.lwjgl.opengl.Display;

public class ArchWare {
    public static final String CLIENT_NAME = "ArchWare";
    public static final String CLIENT_VER = "Development build: 130721";
    public static String login;
    private static String[] changelog;
    public static ModuleManager moduleManager;
    public static CommandManager commandManager;
    public static EventManager eventManager;
    public static SettingsManager settingManager;
    public static final List<Alt> alts;
    public static FriendManager friendManager;
    public static MacroManager macroManager;
    private static final CustomFontRenderer font;
    public static ClickGUI clickGui;
    private static AltManagerGUI altManagerGui;

    public static void load() throws IOException {
        Display.setTitle((String)CLIENT_NAME);
        commandManager = new CommandManager();
        moduleManager = new ModuleManager();
        clickGui = new ClickGUI();
        FileManager.init();
        FileManager.initAlts();
        altManagerGui.initGui();
        friendManager = new FriendManager();
        new SpammerUtils().init();
        TabManager.init();
        FontUtil.setupFontUtils();
        eventManager.register(clickGui);
        macroManager = new MacroManager();
        ViaMCP.getInstance().start();
        System.out.println("Running DiscordRPC...");
        DiscordRPC.init();
    }

    public static void unload() throws IOException {
        System.out.println("ArchWare bDevelopment build: 130721 Closing...");
        FileManager.update();
    }

    public static void sendChatMessage(String message) {
        ChatUtils.printChatMessage((Object)((Object)TextFormatting.YELLOW) + "[" + CLIENT_NAME + "]" + (Object)((Object)TextFormatting.GRAY) + ": " + message);
    }

    public static void drawChangelog() {
        int posY = 12;
        font.drawStringWithShadow("Changelog (Development build: 130721)", 1.0f, 3.0f, -1);
        for (String string : changelog) {
            font.drawStringWithShadow("- " + string, 6.0f, posY, -1);
            posY += 9;
        }
    }

    static {
        changelog = new String[]{(Object)((Object)TextFormatting.YELLOW) + "Removed all useless elements", (Object)((Object)TextFormatting.GREEN) + "Improved KillAura", (Object)((Object)TextFormatting.GREEN) + "Improved Target Strafe (Fixed kicks for AKB)", (Object)((Object)TextFormatting.GREEN) + "Fixed Freecam", (Object)((Object)TextFormatting.GREEN) + "Added ViewClip", (Object)((Object)TextFormatting.GREEN) + "Added ViaMCP", (Object)((Object)TextFormatting.GREEN) + "NEW SELFDAMAGE FOR MATRIX!!!"};
        eventManager = new EventManager();
        settingManager = new SettingsManager();
        alts = new ArrayList<Alt>();
        font = new CustomFontRenderer(new Font("Arial", 0, 14), true, true);
        altManagerGui = new AltManagerGUI();
    }
}

