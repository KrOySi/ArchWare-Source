/*
 * Decompiled with CFR 0.150.
 */
package me.archware.impl.managers;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import me.archware.impl.managers.FileManager;
import net.minecraft.entity.Entity;

public class FriendManager {
    private List<String> friends = new ArrayList<String>();

    public FriendManager() throws IOException {
        if (!new File(FileManager.HOME.getAbsolutePath() + "\\friends.arch").exists()) {
            new File(FileManager.HOME.getAbsolutePath() + "\\friends.arch").createNewFile();
            return;
        }
        this.friends.addAll(Files.readAllLines(Paths.get(new File(FileManager.HOME.getAbsolutePath() + "\\friends.arch").toURI())));
    }

    public void addFriend(Entity entity) {
        this.friends.add(entity.getName());
        try {
            this.flush();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void removeFriend(Entity entity) {
        this.friends.remove(entity.getName());
        try {
            this.flush();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean isFriend(Entity entity) {
        return this.friends.contains(entity.getName());
    }

    private void flush() throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        this.friends.forEach(friend -> stringBuilder.append((String)friend).append("\n"));
        Files.write(Paths.get(new File(FileManager.HOME.getAbsolutePath() + "\\friends.arch").toURI()), stringBuilder.toString().getBytes(), StandardOpenOption.WRITE);
    }
}

