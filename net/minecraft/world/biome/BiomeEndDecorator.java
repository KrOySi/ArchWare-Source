/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  com.google.common.cache.CacheBuilder
 *  com.google.common.cache.CacheLoader
 *  com.google.common.cache.LoadingCache
 *  com.google.common.collect.ContiguousSet
 *  com.google.common.collect.DiscreteDomain
 *  com.google.common.collect.Lists
 *  com.google.common.collect.Range
 */
package net.minecraft.world.biome;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.collect.ContiguousSet;
import com.google.common.collect.DiscreteDomain;
import com.google.common.collect.Lists;
import com.google.common.collect.Range;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeDecorator;
import net.minecraft.world.gen.feature.WorldGenSpikes;

public class BiomeEndDecorator
extends BiomeDecorator {
    private static final LoadingCache<Long, WorldGenSpikes.EndSpike[]> SPIKE_CACHE = CacheBuilder.newBuilder().expireAfterWrite(5L, TimeUnit.MINUTES).build((CacheLoader)new SpikeCacheLoader());
    private final WorldGenSpikes spikeGen = new WorldGenSpikes();

    @Override
    protected void genDecorations(Biome biomeIn, World worldIn, Random random) {
        WorldGenSpikes.EndSpike[] aworldgenspikes$endspike;
        this.generateOres(worldIn, random);
        for (WorldGenSpikes.EndSpike worldgenspikes$endspike : aworldgenspikes$endspike = BiomeEndDecorator.getSpikesForWorld(worldIn)) {
            if (!worldgenspikes$endspike.doesStartInChunk(this.chunkPos)) continue;
            this.spikeGen.setSpike(worldgenspikes$endspike);
            this.spikeGen.generate(worldIn, random, new BlockPos(worldgenspikes$endspike.getCenterX(), 45, worldgenspikes$endspike.getCenterZ()));
        }
    }

    public static WorldGenSpikes.EndSpike[] getSpikesForWorld(World p_185426_0_) {
        Random random = new Random(p_185426_0_.getSeed());
        long i = random.nextLong() & 0xFFFFL;
        return (WorldGenSpikes.EndSpike[])SPIKE_CACHE.getUnchecked((Object)i);
    }

    static class SpikeCacheLoader
    extends CacheLoader<Long, WorldGenSpikes.EndSpike[]> {
        private SpikeCacheLoader() {
        }

        public WorldGenSpikes.EndSpike[] load(Long p_load_1_) throws Exception {
            ArrayList list = Lists.newArrayList((Iterable)ContiguousSet.create((Range)Range.closedOpen((Comparable)Integer.valueOf(0), (Comparable)Integer.valueOf(10)), (DiscreteDomain)DiscreteDomain.integers()));
            Collections.shuffle(list, new Random(p_load_1_));
            WorldGenSpikes.EndSpike[] aworldgenspikes$endspike = new WorldGenSpikes.EndSpike[10];
            for (int i = 0; i < 10; ++i) {
                int j = (int)(42.0 * Math.cos(2.0 * (-Math.PI + 0.3141592653589793 * (double)i)));
                int k = (int)(42.0 * Math.sin(2.0 * (-Math.PI + 0.3141592653589793 * (double)i)));
                int l = (Integer)list.get(i);
                int i1 = 2 + l / 3;
                int j1 = 76 + l * 3;
                boolean flag = l == 1 || l == 2;
                aworldgenspikes$endspike[i] = new WorldGenSpikes.EndSpike(j, k, i1, j1, flag);
            }
            return aworldgenspikes$endspike;
        }
    }
}

