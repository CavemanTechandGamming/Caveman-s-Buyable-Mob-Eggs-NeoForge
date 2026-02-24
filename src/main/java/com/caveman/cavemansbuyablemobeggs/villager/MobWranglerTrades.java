package com.caveman.cavemansbuyablemobeggs.villager;

import java.util.List;

import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Blocks;

import net.neoforged.neoforge.common.BasicItemListing;
import net.neoforged.neoforge.event.village.VillagerTradesEvent;

public class MobWranglerTrades {

    public static void registerTrades(VillagerTradesEvent event) {
        if (event.getType() != ModVillagers.MOB_WRANGLER.get()) {
            return;
        }

        var trades = event.getTrades();

        // Level 1 – creature/passive spawn eggs (5 emeralds + 1 egg → spawn egg)
        addSpawnEggTrade(trades.get(1), Items.ALLAY_SPAWN_EGG);
        addSpawnEggTrade(trades.get(1), Items.AXOLOTL_SPAWN_EGG);
        addSpawnEggTrade(trades.get(1), Items.BAT_SPAWN_EGG);
        addSpawnEggTrade(trades.get(1), Items.CAMEL_SPAWN_EGG);
        addSpawnEggTrade(trades.get(1), Items.CAT_SPAWN_EGG);
        addSpawnEggTrade(trades.get(1), Items.CHICKEN_SPAWN_EGG);
        addSpawnEggTrade(trades.get(1), Items.COD_SPAWN_EGG);
        addSpawnEggTrade(trades.get(1), Items.COW_SPAWN_EGG);
        addSpawnEggTrade(trades.get(1), Items.DONKEY_SPAWN_EGG);
        addSpawnEggTrade(trades.get(1), Items.FOX_SPAWN_EGG);
        addSpawnEggTrade(trades.get(1), Items.FROG_SPAWN_EGG);
        addSpawnEggTrade(trades.get(1), Items.GLOW_SQUID_SPAWN_EGG);
        addSpawnEggTrade(trades.get(1), Items.HORSE_SPAWN_EGG);
        addSpawnEggTrade(trades.get(1), Items.MOOSHROOM_SPAWN_EGG);
        addSpawnEggTrade(trades.get(1), Items.MULE_SPAWN_EGG);
        addSpawnEggTrade(trades.get(1), Items.OCELOT_SPAWN_EGG);
        addSpawnEggTrade(trades.get(1), Items.PANDA_SPAWN_EGG);
        addSpawnEggTrade(trades.get(1), Items.PARROT_SPAWN_EGG);
        addSpawnEggTrade(trades.get(1), Items.PIG_SPAWN_EGG);
        addSpawnEggTrade(trades.get(1), Items.PUFFERFISH_SPAWN_EGG);
        addSpawnEggTrade(trades.get(1), Items.RABBIT_SPAWN_EGG);
        addSpawnEggTrade(trades.get(1), Items.SALMON_SPAWN_EGG);
        addSpawnEggTrade(trades.get(1), Items.SHEEP_SPAWN_EGG);
        addSpawnEggTrade(trades.get(1), Items.SKELETON_HORSE_SPAWN_EGG);
        addSpawnEggTrade(trades.get(1), Items.SNIFFER_SPAWN_EGG);
        addSpawnEggTrade(trades.get(1), Items.SNOW_GOLEM_SPAWN_EGG);
        addSpawnEggTrade(trades.get(1), Items.SQUID_SPAWN_EGG);
        addSpawnEggTrade(trades.get(1), Items.STRIDER_SPAWN_EGG);
        addSpawnEggTrade(trades.get(1), Items.TADPOLE_SPAWN_EGG);
        addSpawnEggTrade(trades.get(1), Items.TROPICAL_FISH_SPAWN_EGG);
        addSpawnEggTrade(trades.get(1), Items.TURTLE_SPAWN_EGG);
        addSpawnEggTrade(trades.get(1), Items.VILLAGER_SPAWN_EGG);
        addSpawnEggTrade(trades.get(1), Items.ZOMBIE_HORSE_SPAWN_EGG);

        // Level 1 – monster/hostile spawn eggs (same price, lower max uses)
        addSpawnEggTradeLow(trades.get(1), Items.BLAZE_SPAWN_EGG);
        addSpawnEggTradeLow(trades.get(1), Items.CAVE_SPIDER_SPAWN_EGG);
        addSpawnEggTradeLow(trades.get(1), Items.CREEPER_SPAWN_EGG);
        addSpawnEggTradeLow(trades.get(1), Items.DROWNED_SPAWN_EGG);
        addSpawnEggTradeLow(trades.get(1), Items.ENDERMAN_SPAWN_EGG);
        addSpawnEggTradeLow(trades.get(1), Items.ENDERMITE_SPAWN_EGG);
        addSpawnEggTradeLow(trades.get(1), Items.EVOKER_SPAWN_EGG);
        addSpawnEggTradeLow(trades.get(1), Items.GHAST_SPAWN_EGG);
        addSpawnEggTradeLow(trades.get(1), Items.GUARDIAN_SPAWN_EGG);
        addSpawnEggTradeLow(trades.get(1), Items.HOGLIN_SPAWN_EGG);
        addSpawnEggTradeLow(trades.get(1), Items.HUSK_SPAWN_EGG);
        addSpawnEggTradeLow(trades.get(1), Items.MAGMA_CUBE_SPAWN_EGG);
        addSpawnEggTradeLow(trades.get(1), Items.PHANTOM_SPAWN_EGG);
        addSpawnEggTradeLow(trades.get(1), Items.PIGLIN_SPAWN_EGG);
        addSpawnEggTradeLow(trades.get(1), Items.PIGLIN_BRUTE_SPAWN_EGG);
        addSpawnEggTradeLow(trades.get(1), Items.PILLAGER_SPAWN_EGG);
        addSpawnEggTradeLow(trades.get(1), Items.RAVAGER_SPAWN_EGG);
        addSpawnEggTradeLow(trades.get(1), Items.SHULKER_SPAWN_EGG);
        addSpawnEggTradeLow(trades.get(1), Items.SILVERFISH_SPAWN_EGG);
        addSpawnEggTradeLow(trades.get(1), Items.SKELETON_SPAWN_EGG);
        addSpawnEggTradeLow(trades.get(1), Items.SLIME_SPAWN_EGG);
        addSpawnEggTradeLow(trades.get(1), Items.SPIDER_SPAWN_EGG);
        addSpawnEggTradeLow(trades.get(1), Items.STRAY_SPAWN_EGG);
        addSpawnEggTradeLow(trades.get(1), Items.VEX_SPAWN_EGG);
        addSpawnEggTradeLow(trades.get(1), Items.VINDICATOR_SPAWN_EGG);
        addSpawnEggTradeLow(trades.get(1), Items.WITCH_SPAWN_EGG);
        addSpawnEggTradeLow(trades.get(1), Items.WITHER_SKELETON_SPAWN_EGG);
        addSpawnEggTradeLow(trades.get(1), Items.ZOGLIN_SPAWN_EGG);
        addSpawnEggTradeLow(trades.get(1), Items.ZOMBIE_SPAWN_EGG);
        addSpawnEggTradeLow(trades.get(1), Items.ZOMBIE_VILLAGER_SPAWN_EGG);
        addSpawnEggTradeLow(trades.get(1), Items.ZOMBIFIED_PIGLIN_SPAWN_EGG);

        // Level 2 – egg bulk buy, spawner sell
        trades.get(2).add(new BasicItemListing(new ItemStack(Items.EGG, 4), new ItemStack(Items.EMERALD), 50, 5, 0.05f));
        trades.get(2).add(new BasicItemListing(new ItemStack(Blocks.SPAWNER), new ItemStack(Blocks.EMERALD_BLOCK, 3), 10, 5, 0.05f));

        // Level 3 – emerald/egg exchange, spawner for emerald blocks
        trades.get(3).add(new BasicItemListing(new ItemStack(Items.EMERALD, 3), new ItemStack(Items.EGG, 8), 10, 5, 0.05f));
        trades.get(3).add(new BasicItemListing(new ItemStack(Blocks.EMERALD_BLOCK, 10), new ItemStack(Blocks.SPAWNER), 10, 5, 0.05f));

        // Level 4 – elder guardian, wither
        addSpawnEggTradeLow(trades.get(4), Items.ELDER_GUARDIAN_SPAWN_EGG);
        addSpawnEggTradeLow(trades.get(4), Items.WITHER_SPAWN_EGG);

        // Level 5 – ender dragon
        addSpawnEggTradeLow(trades.get(5), Items.ENDER_DRAGON_SPAWN_EGG);
    }

    private static void addSpawnEggTrade(List<VillagerTrades.ItemListing> list, Item egg) {
        list.add(new BasicItemListing(new ItemStack(Items.EMERALD, 5), new ItemStack(Items.EGG), new ItemStack(egg), 20, 5, 0.05f));
    }

    private static void addSpawnEggTradeLow(List<VillagerTrades.ItemListing> list, Item egg) {
        list.add(new BasicItemListing(new ItemStack(Items.EMERALD, 5), new ItemStack(Items.EGG), new ItemStack(egg), 10, 5, 0.05f));
    }
}
