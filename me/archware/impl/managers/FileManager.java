/*
 * Decompiled with CFR 0.150.
 */
package me.archware.impl.managers;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import me.archware.ArchWare;
import me.archware.base.altmanager.Alt;
import me.archware.base.module.Module;
import me.archware.base.setting.Setting;
import net.minecraft.client.Minecraft;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

public class FileManager {
    private static final Minecraft mc = Minecraft.getMinecraft();
    public static final File HOME = new File(FileManager.mc.mcDataDir.getAbsolutePath() + "\\" + "ArchWare");

    public static void init() throws IOException {
        if (!HOME.exists()) {
            HOME.mkdirs();
            new File(HOME.getAbsolutePath() + "\\configs").mkdir();
            FileManager.update();
        } else {
            FileManager.initConfig("config.arch");
        }
    }

    public static boolean initConfig(String CONFIG_NAME) throws IOException {
        if (!new File(HOME.getAbsolutePath() + "\\configs\\" + CONFIG_NAME).exists()) {
            return false;
        }
        JSONObject config = new JSONObject(new JSONTokener(new FileInputStream(HOME.getAbsolutePath() + "\\configs\\" + CONFIG_NAME)));
        for (Module module : ArchWare.moduleManager.modules) {
            try {
                JSONObject jsonObject = config.getJSONObject(module.getName());
                if (jsonObject.has("isToggled") && jsonObject.getBoolean("isToggled") && !module.isToggled()) {
                    module.toggle();
                }
                if (jsonObject.has("key")) {
                    module.setKey(jsonObject.getInt("key"));
                }
                for (Setting setting : ArchWare.settingManager.getSettingsByMod(module)) {
                    if (!jsonObject.has(setting.getName())) continue;
                    switch (setting.getSettingType()) {
                        case Boolean: {
                            setting.setValueBoolean(jsonObject.getBoolean(setting.getName()));
                            break;
                        }
                        case String: {
                            setting.setValueString(jsonObject.getString(setting.getName()));
                            break;
                        }
                        case Numeric: {
                            setting.setValueNumeric(jsonObject.getFloat(setting.getName()));
                        }
                    }
                }
            }
            catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return true;
    }

    public static void update() throws IOException {
        JSONObject config = new JSONObject();
        for (Module module : ArchWare.moduleManager.modules) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("key", module.getKey());
            jsonObject.put("isToggled", module.isToggled());
            for (Setting setting : ArchWare.settingManager.getSettingsByMod(module)) {
                switch (setting.getSettingType()) {
                    case Boolean: {
                        jsonObject.put(setting.getName(), setting.getValueBoolean());
                        break;
                    }
                    case String: {
                        jsonObject.put(setting.getName(), setting.getValueString());
                        break;
                    }
                    case Numeric: {
                        jsonObject.put(setting.getName(), setting.getValueNumeric());
                    }
                }
            }
            config.put(module.getName(), jsonObject);
        }
        if (!new File(HOME.getAbsolutePath() + "\\configs\\config.arch").exists()) {
            new File(HOME.getAbsolutePath() + "\\configs\\config.arch").createNewFile();
        }
        Files.write(Paths.get(new File(HOME.getAbsolutePath() + "\\configs\\config.arch").toURI()), config.toString().getBytes(), StandardOpenOption.WRITE);
    }

    public static void initAlts() {
        if (!new File(HOME.getAbsolutePath() + "\\alts.arch").exists()) {
            try {
                new File(HOME.getAbsolutePath() + "\\alts.arch").createNewFile();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
            return;
        }
        try {
            Files.readAllLines(Paths.get(new File(HOME.getAbsolutePath() + "\\alts.arch").toURI())).stream().forEach(alt -> ArchWare.alts.add(new Alt(alt.split(":")[0], alt.split(":").length == 1 ? "" : alt.split(":")[1])));
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void saveAlts() {
        StringBuilder alts = new StringBuilder();
        ArchWare.alts.stream().forEach(alt -> alts.append(alt.getName()).append(":").append(alt.getPassword()));
        try {
            Files.write(Paths.get(new File(HOME.getAbsolutePath() + "\\alts.arch").toURI()), alts.toString().getBytes(), StandardOpenOption.WRITE);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}

