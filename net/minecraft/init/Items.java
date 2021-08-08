/*
 * Decompiled with CFR 0.150.
 */
package net.minecraft.init;

import net.minecraft.init.Bootstrap;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemArmorStand;
import net.minecraft.item.ItemBow;
import net.minecraft.item.ItemEmptyMap;
import net.minecraft.item.ItemFishingRod;
import net.minecraft.item.ItemMap;
import net.minecraft.item.ItemPotion;
import net.minecraft.item.ItemShears;
import net.minecraft.util.ResourceLocation;

public class Items {
    public static final Item field_190931_a;
    public static final Item IRON_SHOVEL;
    public static final Item IRON_PICKAXE;
    public static final Item IRON_AXE;
    public static final Item FLINT_AND_STEEL;
    public static final Item APPLE;
    public static final ItemBow BOW;
    public static final Item ARROW;
    public static final Item SPECTRAL_ARROW;
    public static final Item TIPPED_ARROW;
    public static final Item COAL;
    public static final Item DIAMOND;
    public static final Item IRON_INGOT;
    public static final Item GOLD_INGOT;
    public static final Item IRON_SWORD;
    public static final Item WOODEN_SWORD;
    public static final Item WOODEN_SHOVEL;
    public static final Item WOODEN_PICKAXE;
    public static final Item WOODEN_AXE;
    public static final Item STONE_SWORD;
    public static final Item STONE_SHOVEL;
    public static final Item STONE_PICKAXE;
    public static final Item STONE_AXE;
    public static final Item DIAMOND_SWORD;
    public static final Item DIAMOND_SHOVEL;
    public static final Item DIAMOND_PICKAXE;
    public static final Item DIAMOND_AXE;
    public static final Item STICK;
    public static final Item BOWL;
    public static final Item MUSHROOM_STEW;
    public static final Item GOLDEN_SWORD;
    public static final Item GOLDEN_SHOVEL;
    public static final Item GOLDEN_PICKAXE;
    public static final Item GOLDEN_AXE;
    public static final Item STRING;
    public static final Item FEATHER;
    public static final Item GUNPOWDER;
    public static final Item WOODEN_HOE;
    public static final Item STONE_HOE;
    public static final Item IRON_HOE;
    public static final Item DIAMOND_HOE;
    public static final Item GOLDEN_HOE;
    public static final Item WHEAT_SEEDS;
    public static final Item WHEAT;
    public static final Item BREAD;
    public static final ItemArmor LEATHER_HELMET;
    public static final ItemArmor LEATHER_CHESTPLATE;
    public static final ItemArmor LEATHER_LEGGINGS;
    public static final ItemArmor LEATHER_BOOTS;
    public static final ItemArmor CHAINMAIL_HELMET;
    public static final ItemArmor CHAINMAIL_CHESTPLATE;
    public static final ItemArmor CHAINMAIL_LEGGINGS;
    public static final ItemArmor CHAINMAIL_BOOTS;
    public static final ItemArmor IRON_HELMET;
    public static final ItemArmor IRON_CHESTPLATE;
    public static final ItemArmor IRON_LEGGINGS;
    public static final ItemArmor IRON_BOOTS;
    public static final ItemArmor DIAMOND_HELMET;
    public static final ItemArmor DIAMOND_CHESTPLATE;
    public static final ItemArmor DIAMOND_LEGGINGS;
    public static final ItemArmor DIAMOND_BOOTS;
    public static final ItemArmor GOLDEN_HELMET;
    public static final ItemArmor GOLDEN_CHESTPLATE;
    public static final ItemArmor GOLDEN_LEGGINGS;
    public static final ItemArmor GOLDEN_BOOTS;
    public static final Item FLINT;
    public static final Item PORKCHOP;
    public static final Item COOKED_PORKCHOP;
    public static final Item PAINTING;
    public static final Item GOLDEN_APPLE;
    public static final Item SIGN;
    public static final Item OAK_DOOR;
    public static final Item SPRUCE_DOOR;
    public static final Item BIRCH_DOOR;
    public static final Item JUNGLE_DOOR;
    public static final Item ACACIA_DOOR;
    public static final Item DARK_OAK_DOOR;
    public static final Item BUCKET;
    public static final Item WATER_BUCKET;
    public static final Item LAVA_BUCKET;
    public static final Item MINECART;
    public static final Item SADDLE;
    public static final Item IRON_DOOR;
    public static final Item REDSTONE;
    public static final Item SNOWBALL;
    public static final Item BOAT;
    public static final Item SPRUCE_BOAT;
    public static final Item BIRCH_BOAT;
    public static final Item JUNGLE_BOAT;
    public static final Item ACACIA_BOAT;
    public static final Item DARK_OAK_BOAT;
    public static final Item LEATHER;
    public static final Item MILK_BUCKET;
    public static final Item BRICK;
    public static final Item CLAY_BALL;
    public static final Item REEDS;
    public static final Item PAPER;
    public static final Item BOOK;
    public static final Item SLIME_BALL;
    public static final Item CHEST_MINECART;
    public static final Item FURNACE_MINECART;
    public static final Item EGG;
    public static final Item COMPASS;
    public static final ItemFishingRod FISHING_ROD;
    public static final Item CLOCK;
    public static final Item GLOWSTONE_DUST;
    public static final Item FISH;
    public static final Item COOKED_FISH;
    public static final Item DYE;
    public static final Item BONE;
    public static final Item SUGAR;
    public static final Item CAKE;
    public static final Item BED;
    public static final Item REPEATER;
    public static final Item COOKIE;
    public static final ItemMap FILLED_MAP;
    public static final ItemShears SHEARS;
    public static final Item MELON;
    public static final Item PUMPKIN_SEEDS;
    public static final Item MELON_SEEDS;
    public static final Item BEEF;
    public static final Item COOKED_BEEF;
    public static final Item CHICKEN;
    public static final Item COOKED_CHICKEN;
    public static final Item MUTTON;
    public static final Item COOKED_MUTTON;
    public static final Item RABBIT;
    public static final Item COOKED_RABBIT;
    public static final Item RABBIT_STEW;
    public static final Item RABBIT_FOOT;
    public static final Item RABBIT_HIDE;
    public static final Item ROTTEN_FLESH;
    public static final Item ENDER_PEARL;
    public static final Item BLAZE_ROD;
    public static final Item GHAST_TEAR;
    public static final Item GOLD_NUGGET;
    public static final Item NETHER_WART;
    public static final ItemPotion POTIONITEM;
    public static final ItemPotion SPLASH_POTION;
    public static final ItemPotion LINGERING_POTION;
    public static final Item GLASS_BOTTLE;
    public static final Item DRAGON_BREATH;
    public static final Item SPIDER_EYE;
    public static final Item FERMENTED_SPIDER_EYE;
    public static final Item BLAZE_POWDER;
    public static final Item MAGMA_CREAM;
    public static final Item BREWING_STAND;
    public static final Item CAULDRON;
    public static final Item ENDER_EYE;
    public static final Item SPECKLED_MELON;
    public static final Item SPAWN_EGG;
    public static final Item EXPERIENCE_BOTTLE;
    public static final Item FIRE_CHARGE;
    public static final Item WRITABLE_BOOK;
    public static final Item WRITTEN_BOOK;
    public static final Item EMERALD;
    public static final Item ITEM_FRAME;
    public static final Item FLOWER_POT;
    public static final Item CARROT;
    public static final Item POTATO;
    public static final Item BAKED_POTATO;
    public static final Item POISONOUS_POTATO;
    public static final ItemEmptyMap MAP;
    public static final Item GOLDEN_CARROT;
    public static final Item SKULL;
    public static final Item CARROT_ON_A_STICK;
    public static final Item NETHER_STAR;
    public static final Item PUMPKIN_PIE;
    public static final Item FIREWORKS;
    public static final Item FIREWORK_CHARGE;
    public static final Item ENCHANTED_BOOK;
    public static final Item COMPARATOR;
    public static final Item NETHERBRICK;
    public static final Item QUARTZ;
    public static final Item TNT_MINECART;
    public static final Item HOPPER_MINECART;
    public static final ItemArmorStand ARMOR_STAND;
    public static final Item IRON_HORSE_ARMOR;
    public static final Item GOLDEN_HORSE_ARMOR;
    public static final Item DIAMOND_HORSE_ARMOR;
    public static final Item LEAD;
    public static final Item NAME_TAG;
    public static final Item COMMAND_BLOCK_MINECART;
    public static final Item RECORD_13;
    public static final Item RECORD_CAT;
    public static final Item RECORD_BLOCKS;
    public static final Item RECORD_CHIRP;
    public static final Item RECORD_FAR;
    public static final Item RECORD_MALL;
    public static final Item RECORD_MELLOHI;
    public static final Item RECORD_STAL;
    public static final Item RECORD_STRAD;
    public static final Item RECORD_WARD;
    public static final Item RECORD_11;
    public static final Item RECORD_WAIT;
    public static final Item PRISMARINE_SHARD;
    public static final Item PRISMARINE_CRYSTALS;
    public static final Item BANNER;
    public static final Item END_CRYSTAL;
    public static final Item SHIELD;
    public static final Item ELYTRA;
    public static final Item CHORUS_FRUIT;
    public static final Item CHORUS_FRUIT_POPPED;
    public static final Item BEETROOT_SEEDS;
    public static final Item BEETROOT;
    public static final Item BEETROOT_SOUP;
    public static final Item field_190929_cY;
    public static final Item field_190930_cZ;
    public static final Item field_191525_da;
    public static final Item field_192397_db;

    private static Item getRegisteredItem(String name) {
        Item item = Item.REGISTRY.getObject(new ResourceLocation(name));
        if (item == null) {
            throw new IllegalStateException("Invalid Item requested: " + name);
        }
        return item;
    }

    static {
        if (!Bootstrap.isRegistered()) {
            throw new RuntimeException("Accessed Items before Bootstrap!");
        }
        field_190931_a = Items.getRegisteredItem("air");
        IRON_SHOVEL = Items.getRegisteredItem("iron_shovel");
        IRON_PICKAXE = Items.getRegisteredItem("iron_pickaxe");
        IRON_AXE = Items.getRegisteredItem("iron_axe");
        FLINT_AND_STEEL = Items.getRegisteredItem("flint_and_steel");
        APPLE = Items.getRegisteredItem("apple");
        BOW = (ItemBow)Items.getRegisteredItem("bow");
        ARROW = Items.getRegisteredItem("arrow");
        SPECTRAL_ARROW = Items.getRegisteredItem("spectral_arrow");
        TIPPED_ARROW = Items.getRegisteredItem("tipped_arrow");
        COAL = Items.getRegisteredItem("coal");
        DIAMOND = Items.getRegisteredItem("diamond");
        IRON_INGOT = Items.getRegisteredItem("iron_ingot");
        GOLD_INGOT = Items.getRegisteredItem("gold_ingot");
        IRON_SWORD = Items.getRegisteredItem("iron_sword");
        WOODEN_SWORD = Items.getRegisteredItem("wooden_sword");
        WOODEN_SHOVEL = Items.getRegisteredItem("wooden_shovel");
        WOODEN_PICKAXE = Items.getRegisteredItem("wooden_pickaxe");
        WOODEN_AXE = Items.getRegisteredItem("wooden_axe");
        STONE_SWORD = Items.getRegisteredItem("stone_sword");
        STONE_SHOVEL = Items.getRegisteredItem("stone_shovel");
        STONE_PICKAXE = Items.getRegisteredItem("stone_pickaxe");
        STONE_AXE = Items.getRegisteredItem("stone_axe");
        DIAMOND_SWORD = Items.getRegisteredItem("diamond_sword");
        DIAMOND_SHOVEL = Items.getRegisteredItem("diamond_shovel");
        DIAMOND_PICKAXE = Items.getRegisteredItem("diamond_pickaxe");
        DIAMOND_AXE = Items.getRegisteredItem("diamond_axe");
        STICK = Items.getRegisteredItem("stick");
        BOWL = Items.getRegisteredItem("bowl");
        MUSHROOM_STEW = Items.getRegisteredItem("mushroom_stew");
        GOLDEN_SWORD = Items.getRegisteredItem("golden_sword");
        GOLDEN_SHOVEL = Items.getRegisteredItem("golden_shovel");
        GOLDEN_PICKAXE = Items.getRegisteredItem("golden_pickaxe");
        GOLDEN_AXE = Items.getRegisteredItem("golden_axe");
        STRING = Items.getRegisteredItem("string");
        FEATHER = Items.getRegisteredItem("feather");
        GUNPOWDER = Items.getRegisteredItem("gunpowder");
        WOODEN_HOE = Items.getRegisteredItem("wooden_hoe");
        STONE_HOE = Items.getRegisteredItem("stone_hoe");
        IRON_HOE = Items.getRegisteredItem("iron_hoe");
        DIAMOND_HOE = Items.getRegisteredItem("diamond_hoe");
        GOLDEN_HOE = Items.getRegisteredItem("golden_hoe");
        WHEAT_SEEDS = Items.getRegisteredItem("wheat_seeds");
        WHEAT = Items.getRegisteredItem("wheat");
        BREAD = Items.getRegisteredItem("bread");
        LEATHER_HELMET = (ItemArmor)Items.getRegisteredItem("leather_helmet");
        LEATHER_CHESTPLATE = (ItemArmor)Items.getRegisteredItem("leather_chestplate");
        LEATHER_LEGGINGS = (ItemArmor)Items.getRegisteredItem("leather_leggings");
        LEATHER_BOOTS = (ItemArmor)Items.getRegisteredItem("leather_boots");
        CHAINMAIL_HELMET = (ItemArmor)Items.getRegisteredItem("chainmail_helmet");
        CHAINMAIL_CHESTPLATE = (ItemArmor)Items.getRegisteredItem("chainmail_chestplate");
        CHAINMAIL_LEGGINGS = (ItemArmor)Items.getRegisteredItem("chainmail_leggings");
        CHAINMAIL_BOOTS = (ItemArmor)Items.getRegisteredItem("chainmail_boots");
        IRON_HELMET = (ItemArmor)Items.getRegisteredItem("iron_helmet");
        IRON_CHESTPLATE = (ItemArmor)Items.getRegisteredItem("iron_chestplate");
        IRON_LEGGINGS = (ItemArmor)Items.getRegisteredItem("iron_leggings");
        IRON_BOOTS = (ItemArmor)Items.getRegisteredItem("iron_boots");
        DIAMOND_HELMET = (ItemArmor)Items.getRegisteredItem("diamond_helmet");
        DIAMOND_CHESTPLATE = (ItemArmor)Items.getRegisteredItem("diamond_chestplate");
        DIAMOND_LEGGINGS = (ItemArmor)Items.getRegisteredItem("diamond_leggings");
        DIAMOND_BOOTS = (ItemArmor)Items.getRegisteredItem("diamond_boots");
        GOLDEN_HELMET = (ItemArmor)Items.getRegisteredItem("golden_helmet");
        GOLDEN_CHESTPLATE = (ItemArmor)Items.getRegisteredItem("golden_chestplate");
        GOLDEN_LEGGINGS = (ItemArmor)Items.getRegisteredItem("golden_leggings");
        GOLDEN_BOOTS = (ItemArmor)Items.getRegisteredItem("golden_boots");
        FLINT = Items.getRegisteredItem("flint");
        PORKCHOP = Items.getRegisteredItem("porkchop");
        COOKED_PORKCHOP = Items.getRegisteredItem("cooked_porkchop");
        PAINTING = Items.getRegisteredItem("painting");
        GOLDEN_APPLE = Items.getRegisteredItem("golden_apple");
        SIGN = Items.getRegisteredItem("sign");
        OAK_DOOR = Items.getRegisteredItem("wooden_door");
        SPRUCE_DOOR = Items.getRegisteredItem("spruce_door");
        BIRCH_DOOR = Items.getRegisteredItem("birch_door");
        JUNGLE_DOOR = Items.getRegisteredItem("jungle_door");
        ACACIA_DOOR = Items.getRegisteredItem("acacia_door");
        DARK_OAK_DOOR = Items.getRegisteredItem("dark_oak_door");
        BUCKET = Items.getRegisteredItem("bucket");
        WATER_BUCKET = Items.getRegisteredItem("water_bucket");
        LAVA_BUCKET = Items.getRegisteredItem("lava_bucket");
        MINECART = Items.getRegisteredItem("minecart");
        SADDLE = Items.getRegisteredItem("saddle");
        IRON_DOOR = Items.getRegisteredItem("iron_door");
        REDSTONE = Items.getRegisteredItem("redstone");
        SNOWBALL = Items.getRegisteredItem("snowball");
        BOAT = Items.getRegisteredItem("boat");
        SPRUCE_BOAT = Items.getRegisteredItem("spruce_boat");
        BIRCH_BOAT = Items.getRegisteredItem("birch_boat");
        JUNGLE_BOAT = Items.getRegisteredItem("jungle_boat");
        ACACIA_BOAT = Items.getRegisteredItem("acacia_boat");
        DARK_OAK_BOAT = Items.getRegisteredItem("dark_oak_boat");
        LEATHER = Items.getRegisteredItem("leather");
        MILK_BUCKET = Items.getRegisteredItem("milk_bucket");
        BRICK = Items.getRegisteredItem("brick");
        CLAY_BALL = Items.getRegisteredItem("clay_ball");
        REEDS = Items.getRegisteredItem("reeds");
        PAPER = Items.getRegisteredItem("paper");
        BOOK = Items.getRegisteredItem("book");
        SLIME_BALL = Items.getRegisteredItem("slime_ball");
        CHEST_MINECART = Items.getRegisteredItem("chest_minecart");
        FURNACE_MINECART = Items.getRegisteredItem("furnace_minecart");
        EGG = Items.getRegisteredItem("egg");
        COMPASS = Items.getRegisteredItem("compass");
        FISHING_ROD = (ItemFishingRod)Items.getRegisteredItem("fishing_rod");
        CLOCK = Items.getRegisteredItem("clock");
        GLOWSTONE_DUST = Items.getRegisteredItem("glowstone_dust");
        FISH = Items.getRegisteredItem("fish");
        COOKED_FISH = Items.getRegisteredItem("cooked_fish");
        DYE = Items.getRegisteredItem("dye");
        BONE = Items.getRegisteredItem("bone");
        SUGAR = Items.getRegisteredItem("sugar");
        CAKE = Items.getRegisteredItem("cake");
        BED = Items.getRegisteredItem("bed");
        REPEATER = Items.getRegisteredItem("repeater");
        COOKIE = Items.getRegisteredItem("cookie");
        FILLED_MAP = (ItemMap)Items.getRegisteredItem("filled_map");
        SHEARS = (ItemShears)Items.getRegisteredItem("shears");
        MELON = Items.getRegisteredItem("melon");
        PUMPKIN_SEEDS = Items.getRegisteredItem("pumpkin_seeds");
        MELON_SEEDS = Items.getRegisteredItem("melon_seeds");
        BEEF = Items.getRegisteredItem("beef");
        COOKED_BEEF = Items.getRegisteredItem("cooked_beef");
        CHICKEN = Items.getRegisteredItem("chicken");
        COOKED_CHICKEN = Items.getRegisteredItem("cooked_chicken");
        MUTTON = Items.getRegisteredItem("mutton");
        COOKED_MUTTON = Items.getRegisteredItem("cooked_mutton");
        RABBIT = Items.getRegisteredItem("rabbit");
        COOKED_RABBIT = Items.getRegisteredItem("cooked_rabbit");
        RABBIT_STEW = Items.getRegisteredItem("rabbit_stew");
        RABBIT_FOOT = Items.getRegisteredItem("rabbit_foot");
        RABBIT_HIDE = Items.getRegisteredItem("rabbit_hide");
        ROTTEN_FLESH = Items.getRegisteredItem("rotten_flesh");
        ENDER_PEARL = Items.getRegisteredItem("ender_pearl");
        BLAZE_ROD = Items.getRegisteredItem("blaze_rod");
        GHAST_TEAR = Items.getRegisteredItem("ghast_tear");
        GOLD_NUGGET = Items.getRegisteredItem("gold_nugget");
        NETHER_WART = Items.getRegisteredItem("nether_wart");
        POTIONITEM = (ItemPotion)Items.getRegisteredItem("potion");
        SPLASH_POTION = (ItemPotion)Items.getRegisteredItem("splash_potion");
        LINGERING_POTION = (ItemPotion)Items.getRegisteredItem("lingering_potion");
        GLASS_BOTTLE = Items.getRegisteredItem("glass_bottle");
        DRAGON_BREATH = Items.getRegisteredItem("dragon_breath");
        SPIDER_EYE = Items.getRegisteredItem("spider_eye");
        FERMENTED_SPIDER_EYE = Items.getRegisteredItem("fermented_spider_eye");
        BLAZE_POWDER = Items.getRegisteredItem("blaze_powder");
        MAGMA_CREAM = Items.getRegisteredItem("magma_cream");
        BREWING_STAND = Items.getRegisteredItem("brewing_stand");
        CAULDRON = Items.getRegisteredItem("cauldron");
        ENDER_EYE = Items.getRegisteredItem("ender_eye");
        SPECKLED_MELON = Items.getRegisteredItem("speckled_melon");
        SPAWN_EGG = Items.getRegisteredItem("spawn_egg");
        EXPERIENCE_BOTTLE = Items.getRegisteredItem("experience_bottle");
        FIRE_CHARGE = Items.getRegisteredItem("fire_charge");
        WRITABLE_BOOK = Items.getRegisteredItem("writable_book");
        WRITTEN_BOOK = Items.getRegisteredItem("written_book");
        EMERALD = Items.getRegisteredItem("emerald");
        ITEM_FRAME = Items.getRegisteredItem("item_frame");
        FLOWER_POT = Items.getRegisteredItem("flower_pot");
        CARROT = Items.getRegisteredItem("carrot");
        POTATO = Items.getRegisteredItem("potato");
        BAKED_POTATO = Items.getRegisteredItem("baked_potato");
        POISONOUS_POTATO = Items.getRegisteredItem("poisonous_potato");
        MAP = (ItemEmptyMap)Items.getRegisteredItem("map");
        GOLDEN_CARROT = Items.getRegisteredItem("golden_carrot");
        SKULL = Items.getRegisteredItem("skull");
        CARROT_ON_A_STICK = Items.getRegisteredItem("carrot_on_a_stick");
        NETHER_STAR = Items.getRegisteredItem("nether_star");
        PUMPKIN_PIE = Items.getRegisteredItem("pumpkin_pie");
        FIREWORKS = Items.getRegisteredItem("fireworks");
        FIREWORK_CHARGE = Items.getRegisteredItem("firework_charge");
        ENCHANTED_BOOK = Items.getRegisteredItem("enchanted_book");
        COMPARATOR = Items.getRegisteredItem("comparator");
        NETHERBRICK = Items.getRegisteredItem("netherbrick");
        QUARTZ = Items.getRegisteredItem("quartz");
        TNT_MINECART = Items.getRegisteredItem("tnt_minecart");
        HOPPER_MINECART = Items.getRegisteredItem("hopper_minecart");
        ARMOR_STAND = (ItemArmorStand)Items.getRegisteredItem("armor_stand");
        IRON_HORSE_ARMOR = Items.getRegisteredItem("iron_horse_armor");
        GOLDEN_HORSE_ARMOR = Items.getRegisteredItem("golden_horse_armor");
        DIAMOND_HORSE_ARMOR = Items.getRegisteredItem("diamond_horse_armor");
        LEAD = Items.getRegisteredItem("lead");
        NAME_TAG = Items.getRegisteredItem("name_tag");
        COMMAND_BLOCK_MINECART = Items.getRegisteredItem("command_block_minecart");
        RECORD_13 = Items.getRegisteredItem("record_13");
        RECORD_CAT = Items.getRegisteredItem("record_cat");
        RECORD_BLOCKS = Items.getRegisteredItem("record_blocks");
        RECORD_CHIRP = Items.getRegisteredItem("record_chirp");
        RECORD_FAR = Items.getRegisteredItem("record_far");
        RECORD_MALL = Items.getRegisteredItem("record_mall");
        RECORD_MELLOHI = Items.getRegisteredItem("record_mellohi");
        RECORD_STAL = Items.getRegisteredItem("record_stal");
        RECORD_STRAD = Items.getRegisteredItem("record_strad");
        RECORD_WARD = Items.getRegisteredItem("record_ward");
        RECORD_11 = Items.getRegisteredItem("record_11");
        RECORD_WAIT = Items.getRegisteredItem("record_wait");
        PRISMARINE_SHARD = Items.getRegisteredItem("prismarine_shard");
        PRISMARINE_CRYSTALS = Items.getRegisteredItem("prismarine_crystals");
        BANNER = Items.getRegisteredItem("banner");
        END_CRYSTAL = Items.getRegisteredItem("end_crystal");
        SHIELD = Items.getRegisteredItem("shield");
        ELYTRA = Items.getRegisteredItem("elytra");
        CHORUS_FRUIT = Items.getRegisteredItem("chorus_fruit");
        CHORUS_FRUIT_POPPED = Items.getRegisteredItem("chorus_fruit_popped");
        BEETROOT_SEEDS = Items.getRegisteredItem("beetroot_seeds");
        BEETROOT = Items.getRegisteredItem("beetroot");
        BEETROOT_SOUP = Items.getRegisteredItem("beetroot_soup");
        field_190929_cY = Items.getRegisteredItem("totem_of_undying");
        field_190930_cZ = Items.getRegisteredItem("shulker_shell");
        field_191525_da = Items.getRegisteredItem("iron_nugget");
        field_192397_db = Items.getRegisteredItem("knowledge_book");
    }
}

