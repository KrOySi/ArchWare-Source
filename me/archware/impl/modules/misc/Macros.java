/*
 * Decompiled with CFR 0.150.
 */
package me.archware.impl.modules.misc;

import me.archware.ArchWare;
import me.archware.base.module.Category;
import me.archware.base.module.Module;

public class Macros
extends Module {
    public Macros() {
        super("Macros", Category.MISC);
    }

    @Override
    public void onKey(int key) {
        ArchWare.macroManager.getMacroByKey(key).forEach(macro -> this.mc.player.sendChatMessage((String)macro));
        super.onKey(key);
    }
}

