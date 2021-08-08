/*
 * Decompiled with CFR 0.150.
 */
package me.archware.irc;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.nio.charset.Charset;
import me.archware.ArchWare;
import net.minecraft.util.text.TextFormatting;
import org.json.JSONObject;

public class Server
extends Thread {
    private static Socket socket;
    private static DataOutputStream dataOutputStream;
    private static DataInputStream dataInputStream;

    public static void sendMessage(String content) {
        if (ArchWare.moduleManager.getModuleByName("IRC").isToggled()) {
            try {
                dataOutputStream.writeUTF(new JSONObject().put("owner", ArchWare.login).put("content", content).toString());
                dataOutputStream.flush();
            }
            catch (IOException e) {
                ArchWare.sendChatMessage((Object)((Object)TextFormatting.RED) + "Unable to send message to IRC server :(");
            }
        } else {
            ArchWare.sendChatMessage((Object)((Object)TextFormatting.RED) + "Enable IRC module, then write any message.");
        }
    }

    @Override
    public void run() {
        System.out.println("IRC is active!");
        super.run();
        while (true) {
            if (!ArchWare.moduleManager.getModuleByName("IRC").isToggled()) {
                System.out.println("Shutting down IRC thread.");
                return;
            }
            try {
                String content = dataInputStream.readUTF();
                if (content.isEmpty()) continue;
                JSONObject json = new JSONObject(new String(content.getBytes(), Charset.forName("UTF-8")));
                ArchWare.sendChatMessage("IRC: [" + json.getString("owner") + "]: " + (Object)((Object)TextFormatting.WHITE) + json.getString("content"));
                continue;
            }
            catch (IOException e) {
                e.printStackTrace();
                continue;
            }
            break;
        }
    }

    static {
        try {
            socket = new Socket("localhost", 1337);
            dataOutputStream = new DataOutputStream(socket.getOutputStream());
            dataInputStream = new DataInputStream(socket.getInputStream());
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}

