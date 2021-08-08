/*
 * Decompiled with CFR 0.150.
 */
package me.archware.impl.modules.misc;

import me.archware.ArchWare;
import me.archware.base.event.EventTarget;
import me.archware.base.module.Category;
import me.archware.base.module.Module;
import me.archware.impl.events.EventMiddleClick;
import net.minecraft.entity.player.EntityPlayer;

public class MCF
extends Module {
    public MCF() {
        super("MCF", Category.MISC);
    }

    @EventTarget
    public void onMiddleClick(EventMiddleClick event) {
        if (this.mc.objectMouseOver.entityHit != null && this.mc.objectMouseOver.entityHit instanceof EntityPlayer) {
            if (ArchWare.friendManager.isFriend(this.mc.objectMouseOver.entityHit)) {
                ArchWare.friendManager.removeFriend(this.mc.objectMouseOver.entityHit);
                ArchWare.sendChatMessage(this.mc.objectMouseOver.entityHit.getName() + " successfully removed from your friend list");
            } else {
                ArchWare.friendManager.addFriend(this.mc.objectMouseOver.entityHit);
                ArchWare.sendChatMessage(this.mc.objectMouseOver.entityHit.getName() + " successfully added to your friend list");
            }
        }
    }
}

