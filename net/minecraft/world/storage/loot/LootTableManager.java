/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  com.google.common.cache.CacheBuilder
 *  com.google.common.cache.CacheLoader
 *  com.google.common.cache.LoadingCache
 *  com.google.common.io.Files
 *  com.google.common.io.Resources
 *  com.google.gson.Gson
 *  com.google.gson.GsonBuilder
 *  com.google.gson.JsonParseException
 *  javax.annotation.Nullable
 *  org.apache.logging.log4j.LogManager
 *  org.apache.logging.log4j.Logger
 */
package net.minecraft.world.storage.loot;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.io.Files;
import com.google.common.io.Resources;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParseException;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import javax.annotation.Nullable;
import net.minecraft.util.JsonUtils;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.storage.loot.LootContext;
import net.minecraft.world.storage.loot.LootEntry;
import net.minecraft.world.storage.loot.LootPool;
import net.minecraft.world.storage.loot.LootTable;
import net.minecraft.world.storage.loot.LootTableList;
import net.minecraft.world.storage.loot.RandomValueRange;
import net.minecraft.world.storage.loot.conditions.LootCondition;
import net.minecraft.world.storage.loot.conditions.LootConditionManager;
import net.minecraft.world.storage.loot.functions.LootFunction;
import net.minecraft.world.storage.loot.functions.LootFunctionManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LootTableManager {
    private static final Logger LOGGER = LogManager.getLogger();
    private static final Gson GSON_INSTANCE = new GsonBuilder().registerTypeAdapter(RandomValueRange.class, (Object)new RandomValueRange.Serializer()).registerTypeAdapter(LootPool.class, (Object)new LootPool.Serializer()).registerTypeAdapter(LootTable.class, (Object)new LootTable.Serializer()).registerTypeHierarchyAdapter(LootEntry.class, (Object)new LootEntry.Serializer()).registerTypeHierarchyAdapter(LootFunction.class, (Object)new LootFunctionManager.Serializer()).registerTypeHierarchyAdapter(LootCondition.class, (Object)new LootConditionManager.Serializer()).registerTypeHierarchyAdapter(LootContext.EntityTarget.class, (Object)new LootContext.EntityTarget.Serializer()).create();
    private final LoadingCache<ResourceLocation, LootTable> registeredLootTables = CacheBuilder.newBuilder().build((CacheLoader)new Loader());
    private final File baseFolder;

    public LootTableManager(@Nullable File folder) {
        this.baseFolder = folder;
        this.reloadLootTables();
    }

    public LootTable getLootTableFromLocation(ResourceLocation ressources) {
        return (LootTable)this.registeredLootTables.getUnchecked((Object)ressources);
    }

    public void reloadLootTables() {
        this.registeredLootTables.invalidateAll();
        for (ResourceLocation resourcelocation : LootTableList.getAll()) {
            this.getLootTableFromLocation(resourcelocation);
        }
    }

    class Loader
    extends CacheLoader<ResourceLocation, LootTable> {
        private Loader() {
        }

        public LootTable load(ResourceLocation p_load_1_) throws Exception {
            if (p_load_1_.getResourcePath().contains(".")) {
                LOGGER.debug("Invalid loot table name '{}' (can't contain periods)", (Object)p_load_1_);
                return LootTable.EMPTY_LOOT_TABLE;
            }
            LootTable loottable = this.loadLootTable(p_load_1_);
            if (loottable == null) {
                loottable = this.loadBuiltinLootTable(p_load_1_);
            }
            if (loottable == null) {
                loottable = LootTable.EMPTY_LOOT_TABLE;
                LOGGER.warn("Couldn't find resource table {}", (Object)p_load_1_);
            }
            return loottable;
        }

        @Nullable
        private LootTable loadLootTable(ResourceLocation resource) {
            if (LootTableManager.this.baseFolder == null) {
                return null;
            }
            File file1 = new File(new File(LootTableManager.this.baseFolder, resource.getResourceDomain()), resource.getResourcePath() + ".json");
            if (file1.exists()) {
                if (file1.isFile()) {
                    String s;
                    try {
                        s = Files.toString((File)file1, (Charset)StandardCharsets.UTF_8);
                    }
                    catch (IOException ioexception) {
                        LOGGER.warn("Couldn't load loot table {} from {}", (Object)resource, (Object)file1, (Object)ioexception);
                        return LootTable.EMPTY_LOOT_TABLE;
                    }
                    try {
                        return JsonUtils.gsonDeserialize(GSON_INSTANCE, s, LootTable.class);
                    }
                    catch (JsonParseException | IllegalArgumentException jsonparseexception) {
                        LOGGER.error("Couldn't load loot table {} from {}", (Object)resource, (Object)file1, (Object)jsonparseexception);
                        return LootTable.EMPTY_LOOT_TABLE;
                    }
                }
                LOGGER.warn("Expected to find loot table {} at {} but it was a folder.", (Object)resource, (Object)file1);
                return LootTable.EMPTY_LOOT_TABLE;
            }
            return null;
        }

        @Nullable
        private LootTable loadBuiltinLootTable(ResourceLocation resource) {
            URL url = LootTableManager.class.getResource("/assets/" + resource.getResourceDomain() + "/loot_tables/" + resource.getResourcePath() + ".json");
            if (url != null) {
                String s;
                try {
                    s = Resources.toString((URL)url, (Charset)StandardCharsets.UTF_8);
                }
                catch (IOException ioexception) {
                    LOGGER.warn("Couldn't load loot table {} from {}", (Object)resource, (Object)url, (Object)ioexception);
                    return LootTable.EMPTY_LOOT_TABLE;
                }
                try {
                    return JsonUtils.gsonDeserialize(GSON_INSTANCE, s, LootTable.class);
                }
                catch (JsonParseException jsonparseexception) {
                    LOGGER.error("Couldn't load loot table {} from {}", (Object)resource, (Object)url, (Object)jsonparseexception);
                    return LootTable.EMPTY_LOOT_TABLE;
                }
            }
            return null;
        }
    }
}

