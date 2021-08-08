/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  com.google.common.base.MoreObjects
 *  com.google.common.collect.Maps
 *  com.google.common.collect.Sets
 */
package net.minecraft.client.renderer.block.statemap;

import com.google.common.base.MoreObjects;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import java.util.Collections;
import java.util.HashSet;
import java.util.IdentityHashMap;
import java.util.Map;
import java.util.Set;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.block.statemap.DefaultStateMapper;
import net.minecraft.client.renderer.block.statemap.IStateMapper;
import net.minecraft.util.ResourceLocation;

public class BlockStateMapper {
    private final Map<Block, IStateMapper> blockStateMap = Maps.newIdentityHashMap();
    private final Set<Block> setBuiltInBlocks = Sets.newIdentityHashSet();

    public void registerBlockStateMapper(Block blockIn, IStateMapper stateMapper) {
        this.blockStateMap.put(blockIn, stateMapper);
    }

    public void registerBuiltInBlocks(Block ... blockIn) {
        Collections.addAll(this.setBuiltInBlocks, blockIn);
    }

    public Map<IBlockState, ModelResourceLocation> putAllStateModelLocations() {
        IdentityHashMap map = Maps.newIdentityHashMap();
        for (Block block : Block.REGISTRY) {
            map.putAll(this.getVariants(block));
        }
        return map;
    }

    public Set<ResourceLocation> getBlockstateLocations(Block blockIn) {
        if (this.setBuiltInBlocks.contains(blockIn)) {
            return Collections.emptySet();
        }
        IStateMapper istatemapper = this.blockStateMap.get(blockIn);
        if (istatemapper == null) {
            return Collections.singleton(Block.REGISTRY.getNameForObject(blockIn));
        }
        HashSet set = Sets.newHashSet();
        for (ModelResourceLocation modelresourcelocation : istatemapper.putStateModelLocations(blockIn).values()) {
            set.add(new ResourceLocation(modelresourcelocation.getResourceDomain(), modelresourcelocation.getResourcePath()));
        }
        return set;
    }

    public Map<IBlockState, ModelResourceLocation> getVariants(Block blockIn) {
        return this.setBuiltInBlocks.contains(blockIn) ? Collections.emptyMap() : ((IStateMapper)MoreObjects.firstNonNull((Object)this.blockStateMap.get(blockIn), (Object)new DefaultStateMapper())).putStateModelLocations(blockIn);
    }
}

