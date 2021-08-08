/*
 * Decompiled with CFR 0.150.
 */
package me.archware.impl.modules.combat;

import java.util.ArrayList;
import java.util.Comparator;
import me.archware.ArchWare;
import me.archware.base.event.EventTarget;
import me.archware.base.module.Category;
import me.archware.base.module.Module;
import me.archware.base.setting.BooleanValue;
import me.archware.base.setting.NumericValue;
import me.archware.base.setting.StringValue;
import me.archware.impl.events.EventPreUpdate;
import me.archware.impl.events.EventUpdate;
import me.archware.impl.utils.RotationUtils;
import me.archware.impl.utils.TimeUtil;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemAxe;
import net.minecraft.network.play.client.CPacketAnimation;
import net.minecraft.network.play.client.CPacketHeldItemChange;
import net.minecraft.util.EnumHand;

public class KillAura
extends Module {
    private BooleanValue players = new BooleanValue("Players", "AuraPlayers", this, true);
    private BooleanValue animals = new BooleanValue("Animals", "AuraAnimals", this, false);
    private BooleanValue mobs = new BooleanValue("Mobs", "AuraMobs", this, true);
    private BooleanValue invisibles = new BooleanValue("Invisibles", "AuraInvisibles", this, true);
    private BooleanValue teams = new BooleanValue("Teams", "AuraTeams", this, false);
    private BooleanValue friends = new BooleanValue("Friends", "AuraFriends", this, false);
    private NumericValue range = new NumericValue("Range", "AuraRange", (Module)this, 3.0f, 0.0f, 6.1f);
    private BooleanValue swing = new BooleanValue("Swing", "AuraSwing", this, true);
    private BooleanValue nosprint = new BooleanValue("Stop sprinting", "AuraStopSprinting", this, false);
    private StringValue sort = new StringValue("Sort", "AuraSort", this, "Health", new String[]{"Health", "Range"});
    private BooleanValue criticals = new BooleanValue("Criticals", "Criticals", this, false);
    private TimeUtil timer;
    private NumericValue falldistance = new NumericValue("Fall Distance", "AuraFallDistance", (Module)this, 0.02f, 0.01f, 0.1f);
    public static Entity target;

    public KillAura() {
        super("KillAura", Category.COMBAT);
        this.timer = new TimeUtil();
    }

    @EventTarget
    public void onUpdate(EventUpdate e) {
        if (target != null) {
            if (this.criticals.getValueBoolean()) {
                if (this.mc.player.fallDistance >= this.falldistance.getValueNumeric() && (double)this.mc.player.getCooledAttackStrength(0.0f) >= 0.8 && !this.mc.player.onGround) {
                    this.attack();
                }
            } else if (this.mc.player.getCooledAttackStrength(0.0f) == 1.0f) {
                this.attack();
            }
        }
        if (target != null && (KillAura.target.isDead || this.mc.player.getDistanceToEntity(target) > this.range.getValueNumeric())) {
            target = null;
        }
    }

    @EventTarget
    public void onPreUpdate(EventPreUpdate event) {
        String valueString;
        this.setSuffix(String.format("%.2f", Float.valueOf(this.range.getValueNumeric())));
        ArrayList<Entity> entities = new ArrayList<Entity>();
        for (Entity e2 : this.mc.world.loadedEntityList) {
            if (e2 instanceof EntityPlayer && !this.players.getValueBoolean() || e2 instanceof EntityMob && !this.mobs.getValueBoolean() || (e2 instanceof EntityAnimal || e2 instanceof EntityVillager) && !this.animals.getValueBoolean() || e2.isInvisible() && !this.invisibles.getValueBoolean() || e2.isOnSameTeam(this.mc.player) && !this.teams.getValueBoolean() || ArchWare.friendManager.isFriend(e2) && !this.friends.getValueBoolean() || e2.getDistanceToEntity(this.mc.player) > this.range.getValueNumeric() || !(e2 instanceof EntityLivingBase) || e2.isDead || e2 == this.mc.player) continue;
            entities.add(e2);
        }
        switch (valueString = this.sort.getValueString()) {
            case "Health": {
                entities.sort(Comparator.comparingInt(e -> (int)((EntityPlayer)e).getHealth()));
                break;
            }
            case "Range": {
                entities.sort(Comparator.comparingInt(e -> (int)this.mc.player.getDistanceToEntity((Entity)e)));
            }
        }
        if (!entities.isEmpty() && entities.get(0) != null) {
            target = (Entity)entities.get(0);
            event.setYaw(RotationUtils.getRotation(target)[0]);
            event.setPitch(RotationUtils.getRotation(target)[1]);
            this.mc.player.rotationYawHead = event.getYaw();
            this.mc.player.renderYawOffset = event.getYaw();
            this.mc.player.rotationPitchHead = event.getPitch();
        }
    }

    @Override
    public void onDisable() {
        target = null;
        super.onDisable();
    }

    private void attack() {
        if (this.nosprint.getValueBoolean()) {
            this.mc.gameSettings.keyBindSprint.pressed = false;
            this.mc.player.setSprinting(false);
        }
        if (ArchWare.moduleManager.getModuleByName("ShieldBreaker").isToggled() && target instanceof EntityPlayer && ((EntityPlayer)target).getActiveHand() == EnumHand.OFF_HAND && Item.getIdFromItem(((EntityPlayer)target).getHeldItem(EnumHand.OFF_HAND).getItem()) == 442) {
            for (int i = 0; i < 9; ++i) {
                if (!(this.mc.player.inventory.getStackInSlot(i).getItem() instanceof ItemAxe)) continue;
                this.mc.getConnection().sendPacket(new CPacketHeldItemChange(i));
                break;
            }
        }
        this.mc.playerController.attackEntity(this.mc.player, target);
        if (this.swing.getValueBoolean()) {
            this.mc.player.swingArm(EnumHand.MAIN_HAND);
            this.mc.gameSettings.keyBindSprint.pressed = true;
            this.mc.player.setSprinting(true);
        } else {
            this.mc.getConnection().sendPacket(new CPacketAnimation(EnumHand.MAIN_HAND));
        }
    }
}

