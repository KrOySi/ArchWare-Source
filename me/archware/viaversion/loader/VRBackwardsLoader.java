/*
 * Decompiled with CFR 0.150.
 */
package me.archware.viaversion.loader;

import com.viaversion.viabackwards.api.ViaBackwardsPlatform;
import java.io.File;
import java.util.logging.Logger;
import me.archware.viaversion.ViaMCP;

public class VRBackwardsLoader
implements ViaBackwardsPlatform {
    private final File file;

    public VRBackwardsLoader(File file) {
        this.file = new File(file, "ViaBackwards");
        this.init(this.file);
    }

    @Override
    public Logger getLogger() {
        return ViaMCP.getInstance().getjLogger();
    }

    @Override
    public void disable() {
    }

    @Override
    public boolean isOutdated() {
        return false;
    }

    @Override
    public File getDataFolder() {
        return new File(this.file, "config.yml");
    }
}

