/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  org.lwjgl.input.Keyboard
 */
package me.archware.impl.modules.misc;

import java.util.Arrays;
import me.archware.base.event.EventTarget;
import me.archware.base.module.Category;
import me.archware.base.module.Module;
import me.archware.impl.events.EventUpdate;
import net.minecraft.client.gui.GuiChat;
import net.minecraft.client.settings.KeyBinding;
import org.lwjgl.input.Keyboard;

public class GuiWalk
extends Module {
    private final KeyBinding[] binds;

    public GuiWalk() {
        super("GuiWalk", Category.MISC);
        this.binds = new KeyBinding[]{this.mc.gameSettings.keyBindForward, this.mc.gameSettings.keyBindBack, this.mc.gameSettings.keyBindRight, this.mc.gameSettings.keyBindLeft, this.mc.gameSettings.keyBindJump};
    }

    @EventTarget
    public void onUpdate(EventUpdate event) {
        if (this.mc.currentScreen != null && !(this.mc.currentScreen instanceof GuiChat)) {
            Arrays.asList(this.binds).forEach(key -> KeyBinding.setKeyBindState(key.getKeyCode(), Keyboard.isKeyDown((int)key.getKeyCode())));
        }
    }
}

