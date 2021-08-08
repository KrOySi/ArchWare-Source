/*
 * Decompiled with CFR 0.150.
 */
package me.archware.antiautistprotection.authentication;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Base64;
import me.archware.ArchWare;
import me.archware.antiautistprotection.authentication.GuiAuthenticate;
import me.archware.notifications.Notification;
import me.archware.notifications.NotificationManager;
import me.archware.notifications.NotificationType;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiMainMenu;

public class SQLConnector {
    private static Connection sql;

    protected boolean authenticate(String login, String password) throws SQLException {
        try {
            sql = DriverManager.getConnection("jdbc:mysql://remotemysql.com:3306/N2uxrOr9Q8", "N2uxrOr9Q8", "Fjc0TP0y9H");
            Statement statement = sql.createStatement();
            if (!statement.executeQuery("SELECT login FROM archware WHERE `login`='" + login + "'").next()) {
                GuiAuthenticate.status = "Invalid login or password";
                return false;
            }
            ResultSet res = statement.executeQuery("SELECT password FROM archware WHERE `login`='" + login + "'");
            while (res.next()) {
                if (res.getString(1).equals(password)) continue;
                GuiAuthenticate.status = "Invalid login or password";
                return false;
            }
            res = statement.executeQuery("SELECT ban FROM archware WHERE `login`='" + login + "'");
            while (res.next()) {
                if (res.getString(1).isEmpty()) continue;
                GuiAuthenticate.status = "You are banned: " + res.getString(1);
                return false;
            }
            res = statement.executeQuery("SELECT isExpired FROM archware WHERE `login`='" + login + "'");
            while (res.next()) {
                if (!res.getBoolean(1)) continue;
                GuiAuthenticate.status = "Your account is expired.";
            }
            res = statement.executeQuery("SELECT hwid FROM archware WHERE `login`='" + login + "'");
            while (res.next()) {
                if (res.getString(1).equals(Base64.getEncoder().encodeToString((System.getProperty("user.name") + Runtime.getRuntime().availableProcessors() + System.getProperty("os.name") + System.getProperty("os.arch") + System.getenv("PROCESSOR_IDENTIFIER") + System.getenv("COMPUTERNAME")).getBytes()))) continue;
                GuiAuthenticate.status = "Wrong HWID. Contact with administration to reset.";
                return false;
            }
            statement.execute("USE N2uxrOr9Q8");
            statement.execute("UPDATE archware SET isAuthenticated = '1' WHERE `login` = '" + login + "'");
        }
        catch (Exception e) {
            GuiAuthenticate.status = "Authentication process failed.";
            e.printStackTrace();
            return false;
        }
        GuiAuthenticate.status = "Welcome back, " + login;
        NotificationManager.addNotification(new Notification("Welcome, " + ArchWare.login, NotificationType.OK));
        Minecraft.getMinecraft().displayGuiScreen(new GuiMainMenu(), sql, login, new GuiAuthenticate());
        return true;
    }
}

