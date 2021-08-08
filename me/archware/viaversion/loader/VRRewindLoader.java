/*
 * Decompiled with CFR 0.150.
 */
package me.archware.viaversion.loader;

import com.viaversion.viaversion.api.Via;
import de.gerrygames.viarewind.api.ViaRewindConfigImpl;
import de.gerrygames.viarewind.api.ViaRewindPlatform;
import java.io.File;
import java.util.logging.Logger;

public class VRRewindLoader
implements ViaRewindPlatform {
    public VRRewindLoader(File file) {
        ViaRewindConfigImpl conf = new ViaRewindConfigImpl(file.toPath().resolve("ViaRewind").resolve("config.yml").toFile());
        conf.reloadConfig();
        this.init(conf);
    }

    @Override
    public Logger getLogger() {
        return Via.getPlatform().getLogger();
    }
}

