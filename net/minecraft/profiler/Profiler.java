/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Lists
 *  com.google.common.collect.Maps
 *  org.apache.logging.log4j.LogManager
 *  org.apache.logging.log4j.Logger
 */
package net.minecraft.profiler;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;
import net.minecraft.client.renderer.GlStateManager;
import optifine.Config;
import optifine.Lagometer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Profiler {
    private static final Logger LOGGER = LogManager.getLogger();
    private final List<String> sectionList = Lists.newArrayList();
    private final List<Long> timestampList = Lists.newArrayList();
    public boolean profilingEnabled;
    private String profilingSection = "";
    private final Map<String, Long> profilingMap = Maps.newHashMap();
    public boolean profilerGlobalEnabled;
    private boolean profilerLocalEnabled = this.profilerGlobalEnabled = true;
    private static final String SCHEDULED_EXECUTABLES = "scheduledExecutables";
    private static final String TICK = "tick";
    private static final String PRE_RENDER_ERRORS = "preRenderErrors";
    private static final String RENDER = "render";
    private static final String DISPLAY = "display";
    private static final int HASH_SCHEDULED_EXECUTABLES = "scheduledExecutables".hashCode();
    private static final int HASH_TICK = "tick".hashCode();
    private static final int HASH_PRE_RENDER_ERRORS = "preRenderErrors".hashCode();
    private static final int HASH_RENDER = "render".hashCode();
    private static final int HASH_DISPLAY = "display".hashCode();

    public void clearProfiling() {
        this.profilingMap.clear();
        this.profilingSection = "";
        this.sectionList.clear();
        this.profilerLocalEnabled = this.profilerGlobalEnabled;
    }

    public void startSection(String name) {
        if (Lagometer.isActive()) {
            int i = name.hashCode();
            if (i == HASH_SCHEDULED_EXECUTABLES && name.equals(SCHEDULED_EXECUTABLES)) {
                Lagometer.timerScheduledExecutables.start();
            } else if (i == HASH_TICK && name.equals(TICK) && Config.isMinecraftThread()) {
                Lagometer.timerScheduledExecutables.end();
                Lagometer.timerTick.start();
            } else if (i == HASH_PRE_RENDER_ERRORS && name.equals(PRE_RENDER_ERRORS)) {
                Lagometer.timerTick.end();
            }
        }
        if (Config.isFastRender()) {
            int j = name.hashCode();
            if (j == HASH_RENDER && name.equals(RENDER)) {
                GlStateManager.clearEnabled = false;
            } else if (j == HASH_DISPLAY && name.equals(DISPLAY)) {
                GlStateManager.clearEnabled = true;
            }
        }
        if (this.profilerLocalEnabled && this.profilingEnabled) {
            if (!this.profilingSection.isEmpty()) {
                this.profilingSection = this.profilingSection + ".";
            }
            this.profilingSection = this.profilingSection + name;
            this.sectionList.add(this.profilingSection);
            this.timestampList.add(System.nanoTime());
        }
    }

    public void func_194340_a(Supplier<String> p_194340_1_) {
        if (this.profilerLocalEnabled && this.profilingEnabled) {
            this.startSection(p_194340_1_.get());
        }
    }

    public void endSection() {
        if (this.profilerLocalEnabled && this.profilingEnabled) {
            long i = System.nanoTime();
            long j = this.timestampList.remove(this.timestampList.size() - 1);
            this.sectionList.remove(this.sectionList.size() - 1);
            long k = i - j;
            if (this.profilingMap.containsKey(this.profilingSection)) {
                this.profilingMap.put(this.profilingSection, this.profilingMap.get(this.profilingSection) + k);
            } else {
                this.profilingMap.put(this.profilingSection, k);
            }
            if (k > 100000000L) {
                LOGGER.warn("Something's taking too long! '{}' took aprox {} ms", (Object)this.profilingSection, (Object)((double)k / 1000000.0));
            }
            this.profilingSection = this.sectionList.isEmpty() ? "" : this.sectionList.get(this.sectionList.size() - 1);
        }
    }

    public List<Result> getProfilingData(String profilerName) {
        if (!this.profilingEnabled) {
            return Collections.emptyList();
        }
        long i = this.profilingMap.containsKey("root") ? this.profilingMap.get("root") : 0L;
        long j = this.profilingMap.containsKey(profilerName) ? this.profilingMap.get(profilerName) : -1L;
        ArrayList list = Lists.newArrayList();
        if (!profilerName.isEmpty()) {
            profilerName = profilerName + ".";
        }
        long k = 0L;
        for (String s : this.profilingMap.keySet()) {
            if (s.length() <= profilerName.length() || !s.startsWith(profilerName) || s.indexOf(".", profilerName.length() + 1) >= 0) continue;
            k += this.profilingMap.get(s).longValue();
        }
        float f = k;
        if (k < j) {
            k = j;
        }
        if (i < k) {
            i = k;
        }
        for (String s1 : this.profilingMap.keySet()) {
            if (s1.length() <= profilerName.length() || !s1.startsWith(profilerName) || s1.indexOf(".", profilerName.length() + 1) >= 0) continue;
            long l = this.profilingMap.get(s1);
            double d0 = (double)l * 100.0 / (double)k;
            double d1 = (double)l * 100.0 / (double)i;
            String s2 = s1.substring(profilerName.length());
            list.add(new Result(s2, d0, d1));
        }
        for (String s3 : this.profilingMap.keySet()) {
            this.profilingMap.put(s3, this.profilingMap.get(s3) * 950L / 1000L);
        }
        if ((float)k > f) {
            list.add(new Result("unspecified", (double)((float)k - f) * 100.0 / (double)k, (double)((float)k - f) * 100.0 / (double)i));
        }
        Collections.sort(list);
        list.add(0, new Result(profilerName, 100.0, (double)k * 100.0 / (double)i));
        return list;
    }

    public void endStartSection(String name) {
        if (this.profilerLocalEnabled) {
            this.endSection();
            this.startSection(name);
        }
    }

    public void func_194339_b(Supplier<String> p_194339_1_) {
        if (this.profilerLocalEnabled) {
            this.endSection();
            this.func_194340_a(p_194339_1_);
        }
    }

    public String getNameOfLastSection() {
        return this.sectionList.isEmpty() ? "[UNKNOWN]" : this.sectionList.get(this.sectionList.size() - 1);
    }

    public void startSection(Class<?> p_startSection_1_) {
        if (this.profilingEnabled) {
            this.startSection(p_startSection_1_.getSimpleName());
        }
    }

    public static final class Result
    implements Comparable<Result> {
        public double usePercentage;
        public double totalUsePercentage;
        public String profilerName;

        public Result(String profilerName, double usePercentage, double totalUsePercentage) {
            this.profilerName = profilerName;
            this.usePercentage = usePercentage;
            this.totalUsePercentage = totalUsePercentage;
        }

        @Override
        public int compareTo(Result p_compareTo_1_) {
            if (p_compareTo_1_.usePercentage < this.usePercentage) {
                return -1;
            }
            return p_compareTo_1_.usePercentage > this.usePercentage ? 1 : p_compareTo_1_.profilerName.compareTo(this.profilerName);
        }

        public int getColor() {
            return (this.profilerName.hashCode() & 0xAAAAAA) + 0x444444;
        }
    }
}

