/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  com.mojang.authlib.Agent
 *  com.mojang.authlib.exceptions.AuthenticationException
 *  com.mojang.authlib.yggdrasil.YggdrasilAuthenticationService
 *  com.mojang.authlib.yggdrasil.YggdrasilUserAuthentication
 */
package me.archware.base.altmanager;

import com.mojang.authlib.Agent;
import com.mojang.authlib.exceptions.AuthenticationException;
import com.mojang.authlib.yggdrasil.YggdrasilAuthenticationService;
import com.mojang.authlib.yggdrasil.YggdrasilUserAuthentication;
import java.net.Proxy;
import me.archware.base.altmanager.AltManagerGUI;
import net.minecraft.client.Minecraft;
import net.minecraft.util.Session;
import net.minecraft.util.text.TextFormatting;

public class AuthenticatorThread
extends Thread {
    private String username;
    private String password;

    public AuthenticatorThread(String username, String password) {
        this.username = username;
        this.password = password;
    }

    private Session loadSession() {
        YggdrasilAuthenticationService yggdrasilAuthenticationService = new YggdrasilAuthenticationService(Proxy.NO_PROXY, "");
        YggdrasilUserAuthentication yggdrasilUserAuthentication = (YggdrasilUserAuthentication)yggdrasilAuthenticationService.createUserAuthentication(Agent.MINECRAFT);
        yggdrasilUserAuthentication.setUsername(this.username);
        yggdrasilUserAuthentication.setPassword(this.password);
        System.out.println(this.username + ":" + this.password);
        try {
            yggdrasilUserAuthentication.logIn();
            return new Session(yggdrasilUserAuthentication.getSelectedProfile().getName(), yggdrasilUserAuthentication.getSelectedProfile().getId().toString(), yggdrasilUserAuthentication.getAuthenticatedToken(), "mojang");
        }
        catch (AuthenticationException e) {
            return null;
        }
    }

    @Override
    public void run() {
        AltManagerGUI.status = (Object)((Object)TextFormatting.YELLOW) + "Authenticating...";
        if (this.password.isEmpty()) {
            Minecraft.getMinecraft().setSession(new Session(this.username, "", "", "mojang"));
            AltManagerGUI.status = (Object)((Object)TextFormatting.GREEN) + "Logged in ~ Cracked";
        } else {
            Session session = this.loadSession();
            if (session == null) {
                AltManagerGUI.status = (Object)((Object)TextFormatting.RED) + "Invalid login";
                return;
            }
            Minecraft.getMinecraft().setSession(session);
            AltManagerGUI.status = (Object)((Object)TextFormatting.GREEN) + "Logged in ~ " + session.getUsername();
        }
    }
}

