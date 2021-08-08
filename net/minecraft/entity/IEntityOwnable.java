/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 */
package net.minecraft.entity;

import java.util.UUID;
import javax.annotation.Nullable;
import net.minecraft.entity.Entity;

public interface IEntityOwnable {
    @Nullable
    public UUID getOwnerId();

    @Nullable
    public Entity getOwner();
}

