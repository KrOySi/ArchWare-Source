/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Lists
 *  com.google.common.util.concurrent.Runnables
 *  org.apache.commons.io.IOUtils
 *  org.apache.logging.log4j.LogManager
 *  org.apache.logging.log4j.Logger
 *  org.lwjgl.input.Mouse
 *  org.lwjgl.opengl.GL11
 *  org.lwjgl.opengl.GL20
 *  org.lwjgl.opengl.GLContext
 */
package net.minecraft.client.gui;

import com.google.common.collect.Lists;
import com.google.common.util.concurrent.Runnables;
import java.io.BufferedReader;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;
import me.archware.ArchWare;
import me.archware.antiautistprotection.authentication.GuiAuthenticate;
import me.archware.base.altmanager.AltManagerGUI;
import me.archware.base.clickgui.util.FontUtil;
import me.archware.impl.utils.GLSLSandboxShader;
import me.archware.impl.utils.Particle;
import me.archware.impl.utils.RenderUtil;
import me.archware.impl.utils.TimeUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiConfirmOpenLink;
import net.minecraft.client.gui.GuiLanguage;
import net.minecraft.client.gui.GuiMultiplayer;
import net.minecraft.client.gui.GuiOptions;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiWinGame;
import net.minecraft.client.gui.GuiWorldSelection;
import net.minecraft.client.gui.GuiYesNo;
import net.minecraft.client.gui.GuiYesNoCallback;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.texture.DynamicTexture;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.resources.IResource;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.realms.RealmsBridge;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StringUtils;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.WorldServerDemo;
import net.minecraft.world.storage.ISaveFormat;
import net.minecraft.world.storage.WorldInfo;
import optifine.Reflector;
import org.apache.commons.io.IOUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GLContext;

public class GuiMainMenu
extends GuiScreen {
    private static final Logger LOGGER = LogManager.getLogger();
    private static final Random RANDOM = new Random();
    private TimeUtil timeUtil;
    private int opacity;
    private List<Particle> particles;
    private GLSLSandboxShader shader;
    private long starTime;
    private final float updateCounter;
    private String splashText;
    private GuiButton buttonResetDemo;
    private float panoramaTimer;
    private DynamicTexture viewportTexture;
    private GLSLSandboxShader sandboxShader;
    private final Object threadLock;
    public static final String MORE_INFO_TEXT = "Please click " + (Object)((Object)TextFormatting.UNDERLINE) + "here" + (Object)((Object)TextFormatting.RESET) + " for more information.";
    private int openGLWarning2Width;
    private int openGLWarning1Width;
    private int openGLWarningX1;
    private int openGLWarningY1;
    private int openGLWarningX2;
    private int openGLWarningY2;
    private String openGLWarning1;
    private String openGLWarning2;
    private String openGLWarningLink;
    private static final ResourceLocation SPLASH_TEXTS = new ResourceLocation("texts/splashes.txt");
    private static final ResourceLocation MINECRAFT_TITLE_TEXTURES = new ResourceLocation("textures/gui/title/minecraft.png");
    private static final ResourceLocation field_194400_H = new ResourceLocation("textures/gui/title/edition.png");
    private static final ResourceLocation[] TITLE_PANORAMA_PATHS = new ResourceLocation[]{new ResourceLocation("textures/gui/title/background/panorama_0.png"), new ResourceLocation("textures/gui/title/background/panorama_1.png"), new ResourceLocation("textures/gui/title/background/panorama_2.png"), new ResourceLocation("textures/gui/title/background/panorama_3.png"), new ResourceLocation("textures/gui/title/background/panorama_4.png"), new ResourceLocation("textures/gui/title/background/panorama_5.png")};
    private ResourceLocation backgroundTexture;
    private GuiButton realmsButton;
    private boolean hasCheckedForRealmsNotification;
    private GuiScreen realmsNotification;
    private int field_193978_M;
    private int field_193979_N;
    private GuiButton modButton;
    private GuiScreen modUpdateNotification;
    private me.archware.antiautistprotection.authentication.components.GuiButton singleplayer;
    private me.archware.antiautistprotection.authentication.components.GuiButton multiplayer;
    private me.archware.antiautistprotection.authentication.components.GuiButton options;
    private me.archware.antiautistprotection.authentication.components.GuiButton logout;
    private me.archware.antiautistprotection.authentication.components.GuiButton altmanager;

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public GuiMainMenu() {
        IResource iresource;
        block8: {
            this.timeUtil = new TimeUtil();
            this.particles = new ArrayList<Particle>();
            this.threadLock = new Object();
            try {
                this.shader = new GLSLSandboxShader("/shader.fsh");
            }
            catch (IOException e) {
                e.printStackTrace();
            }
            this.starTime = System.currentTimeMillis();
            this.openGLWarning2 = MORE_INFO_TEXT;
            this.splashText = "missingno";
            iresource = null;
            try {
                String s;
                ArrayList list = Lists.newArrayList();
                iresource = Minecraft.getMinecraft().getResourceManager().getResource(SPLASH_TEXTS);
                BufferedReader bufferedreader = new BufferedReader(new InputStreamReader(iresource.getInputStream(), StandardCharsets.UTF_8));
                while ((s = bufferedreader.readLine()) != null) {
                    if ((s = s.trim()).isEmpty()) continue;
                    list.add(s);
                }
                if (list.isEmpty()) break block8;
                do {
                    this.splashText = (String)list.get(RANDOM.nextInt(list.size()));
                } while (this.splashText.hashCode() == 125780783);
            }
            catch (IOException iOException) {
                IOUtils.closeQuietly(iresource);
            }
            catch (Throwable throwable) {
                IOUtils.closeQuietly(iresource);
                throw throwable;
            }
        }
        IOUtils.closeQuietly((Closeable)iresource);
        this.updateCounter = RANDOM.nextFloat();
        this.openGLWarning1 = "";
        if (!GLContext.getCapabilities().OpenGL20 && !OpenGlHelper.areShadersSupported()) {
            this.openGLWarning1 = I18n.format("title.oldgl1", new Object[0]);
            this.openGLWarning2 = I18n.format("title.oldgl2", new Object[0]);
            this.openGLWarningLink = "https://help.mojang.com/customer/portal/articles/325948?ref=game";
        }
    }

    private boolean areRealmsNotificationsEnabled() {
        return Minecraft.getMinecraft().gameSettings.getOptionOrdinalValue(GameSettings.Options.REALMS_NOTIFICATIONS) && this.realmsNotification != null;
    }

    @Override
    public void updateScreen() {
        if (this.areRealmsNotificationsEnabled()) {
            this.realmsNotification.updateScreen();
        }
    }

    @Override
    public boolean doesGuiPauseGame() {
        return false;
    }

    @Override
    protected void keyTyped(char typedChar, int keyCode) throws IOException {
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void initGui() {
        this.timeUtil.reset();
        try {
            this.sandboxShader = new GLSLSandboxShader("/shader.fsh");
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        this.viewportTexture = new DynamicTexture(256, 256);
        this.backgroundTexture = this.mc.getTextureManager().getDynamicTextureLocation("background", this.viewportTexture);
        this.field_193978_M = this.fontRendererObj.getStringWidth("Copyright Mojang AB. Do not distribute!");
        this.field_193979_N = this.width - this.field_193978_M - 2;
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        if (calendar.get(2) + 1 == 12 && calendar.get(5) == 24) {
            this.splashText = "Merry x-mas!";
        } else if (calendar.get(2) + 1 == 1 && calendar.get(5) == 1) {
            this.splashText = "Happy new year!";
        } else if (calendar.get(2) + 1 == 10 && calendar.get(5) == 31) {
            this.splashText = "OOoooOOOoooo! Spooky!";
        }
        int i = 24;
        int j = this.height / 4 + 48;
        if (this.mc.isDemo()) {
            this.addDemoButtons(j, 24);
        } else {
            this.addSingleplayerMultiplayerButtons(j, 24);
        }
        Object object = this.threadLock;
        synchronized (object) {
            this.openGLWarning1Width = this.fontRendererObj.getStringWidth(this.openGLWarning1);
            this.openGLWarning2Width = this.fontRendererObj.getStringWidth(this.openGLWarning2);
            int k = Math.max(this.openGLWarning1Width, this.openGLWarning2Width);
            this.openGLWarningX1 = (this.width - k) / 2;
            this.openGLWarningX2 = this.openGLWarningX1 + k;
            this.openGLWarningY2 = this.openGLWarningY1 + 24;
        }
        this.mc.setConnectedToRealms(false);
        if (Minecraft.getMinecraft().gameSettings.getOptionOrdinalValue(GameSettings.Options.REALMS_NOTIFICATIONS) && !this.hasCheckedForRealmsNotification) {
            RealmsBridge realmsbridge = new RealmsBridge();
            this.realmsNotification = realmsbridge.getNotificationScreen(this);
            this.hasCheckedForRealmsNotification = true;
        }
        if (this.areRealmsNotificationsEnabled()) {
            this.realmsNotification.setGuiSize(this.width, this.height);
            this.realmsNotification.initGui();
        }
        if (Reflector.NotificationModUpdateScreen_init.exists()) {
            this.modUpdateNotification = (GuiScreen)Reflector.call(Reflector.NotificationModUpdateScreen_init, this, this.modButton);
        }
    }

    private void addSingleplayerMultiplayerButtons(int p_73969_1_, int p_73969_2_) {
        ScaledResolution sr = new ScaledResolution(this.mc);
        this.buttonList.add(new GuiButton(1, sr.getScaledWidth() / 2 - 37, sr.getScaledHeight() / 2 - 45, 80, 20, I18n.format("menu.singleplayer", new Object[0])));
        this.buttonList.add(new GuiButton(2, sr.getScaledWidth() / 2 - 37, sr.getScaledHeight() / 2 - 20, 80, 20, I18n.format("menu.multiplayer", new Object[0])));
        if (Reflector.GuiModList_Constructor.exists()) {
            this.realmsButton = this.addButton(new GuiButton(14, sr.getScaledWidth() / 2 - 37, sr.getScaledHeight() / 2 - 5, 80, 20, I18n.format("menu.online", new Object[0]).replace("Minecraft", "").trim()));
            this.modButton = new GuiButton(6, sr.getScaledWidth() / 2 - 37, sr.getScaledHeight() / 2 + 30, 80, 20, I18n.format("fml.menu.mods", new Object[0]));
            this.buttonList.add(this.modButton);
        } else {
            this.realmsButton = this.addButton(new GuiButton(14, sr.getScaledWidth() / 2 - 37, sr.getScaledHeight() / 2 + 5, 80, 20, I18n.format("menu.online", new Object[0])));
            this.buttonList.add(new GuiButton(0, sr.getScaledWidth() / 2 - 37, sr.getScaledHeight() / 2 + 30, 80, 20, "Options"));
            this.buttonList.add(new GuiButton(4, sr.getScaledWidth() / 2 - 37, sr.getScaledHeight() / 2 + 55, 80, 20, "Logout"));
            this.buttonList.add(new GuiButton(1337, sr.getScaledWidth() / 2 - 37, sr.getScaledHeight() / 2 + 80, 80, 20, "Account Manager"));
        }
    }

    private void addDemoButtons(int p_73972_1_, int p_73972_2_) {
        this.buttonList.add(new GuiButton(11, this.width / 2 - 100, p_73972_1_, I18n.format("menu.playdemo", new Object[0])));
        this.buttonResetDemo = this.addButton(new GuiButton(12, this.width / 2 - 100, p_73972_1_ + p_73972_2_ * 1, I18n.format("menu.resetdemo", new Object[0])));
        ISaveFormat isaveformat = this.mc.getSaveLoader();
        WorldInfo worldinfo = isaveformat.getWorldInfo("Demo_World");
        if (worldinfo == null) {
            this.buttonResetDemo.enabled = false;
        }
    }

    @Override
    protected void actionPerformed(GuiButton button) throws IOException {
        ISaveFormat isaveformat;
        WorldInfo worldinfo;
        if (button.id == 0) {
            this.mc.displayGuiScreen(new GuiOptions(this, this.mc.gameSettings));
        }
        if (button.id == 5) {
            this.mc.displayGuiScreen(new GuiLanguage(this, this.mc.gameSettings, this.mc.getLanguageManager()));
        }
        if (button.id == 1) {
            this.mc.displayGuiScreen(new GuiWorldSelection(this));
        }
        if (button.id == 2) {
            this.mc.displayGuiScreen(new GuiMultiplayer(this));
        }
        if (button.id == 1337) {
            this.mc.displayGuiScreen(new AltManagerGUI());
        }
        if (button.id == 14 && this.realmsButton.visible) {
            this.switchToRealms();
        }
        if (button.id == 4) {
            this.mc.shutdown();
        }
        if (button.id == 6 && Reflector.GuiModList_Constructor.exists()) {
            this.mc.displayGuiScreen((GuiScreen)Reflector.newInstance(Reflector.GuiModList_Constructor, this));
        }
        if (button.id == 11) {
            this.mc.launchIntegratedServer("Demo_World", "Demo_World", WorldServerDemo.DEMO_WORLD_SETTINGS);
        }
        if (button.id == 12 && (worldinfo = (isaveformat = this.mc.getSaveLoader()).getWorldInfo("Demo_World")) != null) {
            this.mc.displayGuiScreen(new GuiYesNo(this, I18n.format("selectWorld.deleteQuestion", new Object[0]), "'" + worldinfo.getWorldName() + "' " + I18n.format("selectWorld.deleteWarning", new Object[0]), I18n.format("selectWorld.deleteButton", new Object[0]), I18n.format("gui.cancel", new Object[0]), 12));
        }
    }

    private void switchToRealms() {
        RealmsBridge realmsbridge = new RealmsBridge();
        realmsbridge.switchToRealms(this);
    }

    @Override
    public void confirmClicked(boolean result, int id) {
        if (result && id == 12) {
            ISaveFormat isaveformat = this.mc.getSaveLoader();
            isaveformat.flushCache();
            isaveformat.deleteWorldDirectory("Demo_World");
            this.mc.displayGuiScreen(this);
        } else if (id == 12) {
            this.mc.displayGuiScreen(this);
        } else if (id == 13) {
            if (result) {
                try {
                    Class<?> oclass = Class.forName("java.awt.Desktop");
                    Object object = oclass.getMethod("getDesktop", new Class[0]).invoke(null, new Object[0]);
                    oclass.getMethod("browse", URI.class).invoke(object, new URI(this.openGLWarningLink));
                }
                catch (Throwable throwable1) {
                    LOGGER.error("Couldn't open link", throwable1);
                }
            }
            this.mc.displayGuiScreen(this);
        }
    }

    @Override
    public void onResize(Minecraft mcIn, int w, int h) {
        this.particles.clear();
        super.onResize(mcIn, w, h);
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        GlStateManager.enableAlpha();
        GlStateManager.disableCull();
        this.shader.useShader(this.width, this.height * 2, mouseX, mouseY, (float)(this.starTime - System.currentTimeMillis()) / 1000.0f);
        GL11.glBegin((int)7);
        GL11.glVertex2f((float)-1.0f, (float)-1.0f);
        GL11.glVertex2f((float)-1.0f, (float)1.0f);
        GL11.glVertex2f((float)1.0f, (float)1.0f);
        GL11.glVertex2f((float)1.0f, (float)-1.0f);
        GL11.glEnd();
        GL20.glUseProgram((int)0);
        if (ArchWare.login == null) {
            this.mc.displayGuiScreen(new GuiAuthenticate());
        }
        this.panoramaTimer += partialTicks;
        ScaledResolution sr = new ScaledResolution(this.mc);
        ArchWare.drawChangelog();
        RenderUtil.drawRect("Main Menu", sr.getScaledWidth() / 2 - 42, sr.getScaledHeight() / 2 - 50, 90.0f, 155.0f);
        GL11.glPushMatrix();
        GL11.glTranslatef((float)(sr.getScaledWidth() / 2 - 41), (float)45.0f, (float)0.0f);
        GL11.glScalef((float)2.6f, (float)2.6f, (float)2.6f);
        this.drawString(this.mc.fontRendererObj, (Object)((Object)TextFormatting.DARK_PURPLE) + String.valueOf("Arch".charAt(0)) + (Object)((Object)TextFormatting.RESET) + "Arch".substring(1) + (Object)((Object)TextFormatting.DARK_PURPLE) + "Ware".charAt(0) + (Object)((Object)TextFormatting.RESET) + "Ware".substring(1), -7, 0, -1);
        GL11.glPopMatrix();
        FontUtil.drawStringWithShadow("Build: " + (Object)((Object)TextFormatting.GREEN) + "Development build: 130721", sr.getScaledWidth() - FontUtil.getStringWidth("Build: Development build: 130721") - 2, sr.getScaledHeight() - 20, -1);
        FontUtil.drawStringWithShadow("Welcome, " + (Object)((Object)TextFormatting.GRAY) + ArchWare.login, sr.getScaledWidth() - FontUtil.getStringWidth("Welcome, " + ArchWare.login) - 2, sr.getScaledHeight() - 10, -1);
        if (mouseX > this.field_193979_N && mouseX < this.field_193979_N + this.field_193978_M && mouseY > this.height - 10 && mouseY < this.height && Mouse.isInsideWindow()) {
            GuiMainMenu.drawRect(this.field_193979_N, this.height - 1, this.field_193979_N + this.field_193978_M, this.height, -1);
        }
        super.drawScreen(mouseX, mouseY, partialTicks);
        if (this.areRealmsNotificationsEnabled()) {
            this.realmsNotification.drawScreen(mouseX, mouseY, partialTicks);
        }
        if (this.modUpdateNotification != null) {
            this.modUpdateNotification.drawScreen(mouseX, mouseY, partialTicks);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
        super.mouseClicked(mouseX, mouseY, mouseButton);
        Object object = this.threadLock;
        synchronized (object) {
            if (!this.openGLWarning1.isEmpty() && !StringUtils.isNullOrEmpty(this.openGLWarningLink) && mouseX >= this.openGLWarningX1 && mouseX <= this.openGLWarningX2 && mouseY >= this.openGLWarningY1 && mouseY <= this.openGLWarningY2) {
                GuiConfirmOpenLink guiconfirmopenlink = new GuiConfirmOpenLink((GuiYesNoCallback)this, this.openGLWarningLink, 13, true);
                guiconfirmopenlink.disableSecurityWarning();
                this.mc.displayGuiScreen(guiconfirmopenlink);
            }
        }
        if (this.areRealmsNotificationsEnabled()) {
            this.realmsNotification.mouseClicked(mouseX, mouseY, mouseButton);
        }
        if (mouseX > this.field_193979_N && mouseX < this.field_193979_N + this.field_193978_M && mouseY > this.height - 10 && mouseY < this.height) {
            this.mc.displayGuiScreen(new GuiWinGame(false, Runnables.doNothing()));
        }
    }

    @Override
    public void onGuiClosed() {
        this.opacity = 0;
        this.particles.clear();
        this.timeUtil.reset();
        if (this.realmsNotification != null) {
            this.realmsNotification.onGuiClosed();
        }
    }
}

