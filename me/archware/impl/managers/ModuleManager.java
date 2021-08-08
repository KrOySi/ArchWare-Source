/*
 * Decompiled with CFR 0.150.
 */
package me.archware.impl.managers;

import java.util.ArrayList;
import me.archware.base.module.Category;
import me.archware.base.module.Module;
import me.archware.impl.modules.combat.AntiBot;
import me.archware.impl.modules.combat.AutoGapple;
import me.archware.impl.modules.combat.AutoTotem;
import me.archware.impl.modules.combat.CrystalAura;
import me.archware.impl.modules.combat.Hitbox;
import me.archware.impl.modules.combat.KeyPearl;
import me.archware.impl.modules.combat.KillAura;
import me.archware.impl.modules.combat.ShieldBreaker;
import me.archware.impl.modules.combat.TriggerBot;
import me.archware.impl.modules.misc.AntiAFK;
import me.archware.impl.modules.misc.AntiAim;
import me.archware.impl.modules.misc.AutoRespawn;
import me.archware.impl.modules.misc.CustomTime;
import me.archware.impl.modules.misc.FlagDetector;
import me.archware.impl.modules.misc.Freecam;
import me.archware.impl.modules.misc.GroundSpoof;
import me.archware.impl.modules.misc.GuiWalk;
import me.archware.impl.modules.misc.IRC;
import me.archware.impl.modules.misc.KillSult;
import me.archware.impl.modules.misc.MCF;
import me.archware.impl.modules.misc.Macros;
import me.archware.impl.modules.misc.NameProtect;
import me.archware.impl.modules.misc.SelfDamage;
import me.archware.impl.modules.misc.SlotClicker;
import me.archware.impl.modules.misc.SmartWClip;
import me.archware.impl.modules.misc.Spammer;
import me.archware.impl.modules.misc.Timer;
import me.archware.impl.modules.movement.AirJump;
import me.archware.impl.modules.movement.Flight;
import me.archware.impl.modules.movement.Jesus;
import me.archware.impl.modules.movement.NoClip;
import me.archware.impl.modules.movement.NoPush;
import me.archware.impl.modules.movement.Scaffold;
import me.archware.impl.modules.movement.Speed;
import me.archware.impl.modules.movement.Sprint;
import me.archware.impl.modules.movement.Step;
import me.archware.impl.modules.movement.TargetStrafe;
import me.archware.impl.modules.player.ChestStealer;
import me.archware.impl.modules.player.FastPlace;
import me.archware.impl.modules.player.NoInteract;
import me.archware.impl.modules.player.NoSlow;
import me.archware.impl.modules.player.Velocity;
import me.archware.impl.modules.render.ArmorHUD;
import me.archware.impl.modules.render.BlockOverlay;
import me.archware.impl.modules.render.Chams;
import me.archware.impl.modules.render.ChestESP;
import me.archware.impl.modules.render.ClickGUI;
import me.archware.impl.modules.render.CoolFeatureBtw;
import me.archware.impl.modules.render.CrossHair;
import me.archware.impl.modules.render.DeathCoordinates;
import me.archware.impl.modules.render.ESP;
import me.archware.impl.modules.render.FullBright;
import me.archware.impl.modules.render.HUD;
import me.archware.impl.modules.render.ItemESP;
import me.archware.impl.modules.render.ItemPhysics;
import me.archware.impl.modules.render.MiniItems;
import me.archware.impl.modules.render.NameTags;
import me.archware.impl.modules.render.NoHurt;
import me.archware.impl.modules.render.NoRender;
import me.archware.impl.modules.render.NoScoreboard;
import me.archware.impl.modules.render.Skeleton;
import me.archware.impl.modules.render.SmoothCamera;
import me.archware.impl.modules.render.TargetHUD;
import me.archware.impl.modules.render.Tracers;
import me.archware.impl.modules.render.ViewClip;
import me.archware.impl.modules.render.Xray;

public class ModuleManager {
    public final ArrayList<Module> modules = new ArrayList();

    public ModuleManager() {
        this.modules.add(new HUD());
        this.modules.add(new ClickGUI());
        this.modules.add(new Speed());
        this.modules.add(new KillAura());
        this.modules.add(new FullBright());
        this.modules.add(new TargetHUD());
        this.modules.add(new Velocity());
        this.modules.add(new Sprint());
        this.modules.add(new GuiWalk());
        this.modules.add(new NoSlow());
        this.modules.add(new AutoRespawn());
        this.modules.add(new Spammer());
        this.modules.add(new CrossHair());
        this.modules.add(new AntiAim());
        this.modules.add(new ESP());
        this.modules.add(new AntiBot());
        this.modules.add(new Flight());
        this.modules.add(new Jesus());
        this.modules.add(new SlotClicker());
        this.modules.add(new Xray());
        this.modules.add(new MCF());
        this.modules.add(new Freecam());
        this.modules.add(new AutoTotem());
        this.modules.add(new NoHurt());
        this.modules.add(new NoScoreboard());
        this.modules.add(new NameTags());
        this.modules.add(new ChestESP());
        this.modules.add(new ItemESP());
        this.modules.add(new Timer());
        this.modules.add(new TriggerBot());
        this.modules.add(new CrystalAura());
        this.modules.add(new Chams());
        this.modules.add(new AntiAFK());
        this.modules.add(new DeathCoordinates());
        this.modules.add(new KeyPearl());
        this.modules.add(new NoInteract());
        this.modules.add(new NoRender());
        this.modules.add(new MiniItems());
        this.modules.add(new NoClip());
        this.modules.add(new AirJump());
        this.modules.add(new NoPush());
        this.modules.add(new FastPlace());
        this.modules.add(new AutoGapple());
        this.modules.add(new KillSult());
        this.modules.add(new TargetStrafe());
        this.modules.add(new Macros());
        this.modules.add(new ViewClip());
        this.modules.add(new ChestStealer());
        this.modules.add(new Step());
        this.modules.add(new Hitbox());
        this.modules.add(new FlagDetector());
        this.modules.add(new ShieldBreaker());
        this.modules.add(new Skeleton());
        this.modules.add(new Scaffold());
        this.modules.add(new Tracers());
        this.modules.add(new GroundSpoof());
        this.modules.add(new ArmorHUD());
        this.modules.add(new CoolFeatureBtw());
        this.modules.add(new ItemPhysics());
        this.modules.add(new SmoothCamera());
        this.modules.add(new CustomTime());
        this.modules.add(new SelfDamage());
        this.modules.add(new SmartWClip());
        this.modules.add(new IRC());
        this.modules.add(new NameProtect());
        this.modules.add(new BlockOverlay());
    }

    public Module getModuleByClass(Class clazz) {
        return this.modules.stream().filter(module -> module.getClass() == clazz).findFirst().orElse(null);
    }

    public Module getModuleByName(String name) {
        return this.modules.stream().filter(module -> module.getName().equalsIgnoreCase(name)).findFirst().orElse(null);
    }

    public ArrayList<Module> getModulesByCategory(Category category) {
        ArrayList<Module> out = new ArrayList<Module>();
        for (Module m : this.modules) {
            if (m.getCategory() != category) continue;
            out.add(m);
        }
        return out;
    }
}

