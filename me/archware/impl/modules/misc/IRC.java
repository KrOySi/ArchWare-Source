/*
 * Decompiled with CFR 0.150.
 */
package me.archware.impl.modules.misc;

import me.archware.base.module.Category;
import me.archware.base.module.Module;
import me.archware.irc.Server;

public class IRC
extends Module {
    public IRC() {
        super("IRC", Category.MISC);
    }

    @Override
    public void onEnable() {
        Server server = new Server();
        server.start();
        super.onEnable();
    }
}

