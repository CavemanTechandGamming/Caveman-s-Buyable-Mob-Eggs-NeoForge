package com.caveman.cavemansbuyablemobeggs.villager;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.SpawnEggItem;
import net.minecraft.world.item.trading.ItemCost;
import net.minecraft.world.item.trading.MerchantOffer;
import net.minecraft.world.entity.npc.VillagerTrades;

import com.caveman.cavemansbuyablemobeggs.Config;

import com.caveman.cavemansbuyablemobeggs.CavemansBuyableMobEggs;

import net.neoforged.neoforge.event.village.VillagerTradesEvent;

/**
 * Registers Mob Wrangler villager trades by level:
 * 1 = CREATURE, 2 = AMBIENT + WATER_*, 3 = MONSTER, 4 = nether mobs (see tag), 5 = boss mobs (config).
 */
public final class MobWranglerTrades {

    private static final int EMERALD_COST = 3;
    private static final int CHICKEN_EGG_COST = 1;
    private static final int MAX_TRADES = 12;
    private static final int VILLAGER_XP = 2;
    private static final float PRICE_MULT = 0.05f;

    /** Entity type ids from our nether_mobs entity tag (no registry lookup in event). */
    private static final Set<ResourceLocation> NETHER_MOB_IDS = Set.of(
            ResourceLocation.withDefaultNamespace("blaze"),
            ResourceLocation.withDefaultNamespace("ghast"),
            ResourceLocation.withDefaultNamespace("hoglin"),
            ResourceLocation.withDefaultNamespace("magma_cube"),
            ResourceLocation.withDefaultNamespace("piglin"),
            ResourceLocation.withDefaultNamespace("piglin_brute"),
            ResourceLocation.withDefaultNamespace("strider"),
            ResourceLocation.withDefaultNamespace("wither_skeleton"),
            ResourceLocation.withDefaultNamespace("zoglin")
    );

    /** Entity type ids from our boss_mobs entity tag. */
    private static final Set<ResourceLocation> BOSS_MOB_IDS = Set.of(
            ResourceLocation.withDefaultNamespace("ender_dragon"),
            ResourceLocation.withDefaultNamespace("wither")
    );

    public static void registerTrades(VillagerTradesEvent event) {
        if (event.getType() != ModVillagers.MOB_WRANGLER.get()) {
            return;
        }

        List<List<ItemStack>> eggsByLevel = partitionEggsByLevel();

        for (int level = 1; level <= 5; level++) {
            int index = level - 1;
            if (index >= eggsByLevel.size()) continue;
            for (ItemStack egg : eggsByLevel.get(index)) {
                event.getTrades().get(level).add(createTrade(egg));
            }
        }
    }

    private static List<List<ItemStack>> partitionEggsByLevel() {
        List<ItemStack> level1 = new ArrayList<>(); // CREATURE
        List<ItemStack> level2 = new ArrayList<>(); // AMBIENT, WATER_*
        List<ItemStack> level3 = new ArrayList<>(); // MONSTER
        List<ItemStack> level4 = new ArrayList<>(); // nether mobs
        List<ItemStack> level5 = new ArrayList<>(); // boss mobs

        for (Item eggItem : TradeableSpawnEggs.getTradeableSpawnEggs()) {
            if (!(eggItem instanceof SpawnEggItem spawnEgg)) continue;
            ItemStack eggStack = new ItemStack(eggItem);

            // API level override: addon requested a specific trade level (1–5)
            var overrideLevel = TradeableSpawnEggs.getLevelOverride(eggItem);
            if (overrideLevel.isPresent()) {
                int level = overrideLevel.getAsInt();
                switch (level) {
                    case 1 -> level1.add(eggStack.copy());
                    case 2 -> level2.add(eggStack.copy());
                    case 3 -> level3.add(eggStack.copy());
                    case 4 -> level4.add(eggStack.copy());
                    case 5 -> level5.add(eggStack.copy());
                    default -> { }
                }
                continue;
            }

            EntityType<?> type = spawnEgg.getType(eggStack);
            if (type == null) continue;

            ResourceLocation typeId = BuiltInRegistries.ENTITY_TYPE.getKey(type);

            // Level 5: boss mobs (config)
            if (Config.ENABLE_BOSS_MOB_TRADES.get() && BOSS_MOB_IDS.contains(typeId)) {
                level5.add(eggStack.copy());
                logSpawnEggLevel(typeId, type.getCategory(), 5);
                continue;
            }

            // Level 4: nether mobs
            if (NETHER_MOB_IDS.contains(typeId)) {
                level4.add(eggStack.copy());
                logSpawnEggLevel(typeId, type.getCategory(), 4);
                continue;
            }

            // Levels 1–3 by category
            MobCategory category = type.getCategory();
            int assignedLevel;
            switch (category) {
                case CREATURE -> { level1.add(eggStack.copy()); assignedLevel = 1; }
                case AMBIENT, WATER_CREATURE, WATER_AMBIENT, UNDERGROUND_WATER_CREATURE, AXOLOTLS -> { level2.add(eggStack.copy()); assignedLevel = 2; }
                case MONSTER -> { level3.add(eggStack.copy()); assignedLevel = 3; }
                default -> { level2.add(eggStack.copy()); assignedLevel = 2; } // MISC etc.
            }
            logSpawnEggLevel(typeId, category, assignedLevel);
        }

        return List.of(level1, level2, level3, level4, level5);
    }

    /** When system property cavemans_buyable_mob_eggs.dumpEggLevels is true, log every spawn egg and its level for README verification. */
    private static void logSpawnEggLevel(ResourceLocation typeId, MobCategory category, int level) {
        if (!Boolean.getBoolean("cavemans_buyable_mob_eggs.dumpEggLevels")) return;
        org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(CavemansBuyableMobEggs.MOD_ID);
        logger.info("[Mob Wrangler] Level {} | {} | category={}", level, typeId, category);
    }

    private static VillagerTrades.ItemListing createTrade(ItemStack spawnEgg) {
        return (trader, random) -> new MerchantOffer(
                new ItemCost(Items.EMERALD, EMERALD_COST),
                Optional.of(new ItemCost(Items.EGG, CHICKEN_EGG_COST)),
                spawnEgg.copy(),
                MAX_TRADES,
                VILLAGER_XP,
                PRICE_MULT
        );
    }
}
