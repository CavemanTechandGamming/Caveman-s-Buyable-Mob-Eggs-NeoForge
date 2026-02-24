package com.caveman.cavemansbuyablemobeggs.villager;

import java.util.List;
import java.util.random.RandomGenerator;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;

import net.neoforged.neoforge.event.entity.living.LivingDropsEvent;

public class MobWranglerLoot {

    /** 1 in 10 million chance for the rare sword. */
    private static final long RARE_SWORD_CHANCE = 10_000_000L;

    /** Spawn eggs the Mob Wrangler sells (subset for drop variety; can expand). */
    private static final List<Item> SPAWN_EGG_DROPS = List.of(
            Items.CHICKEN_SPAWN_EGG, Items.COW_SPAWN_EGG, Items.PIG_SPAWN_EGG, Items.SHEEP_SPAWN_EGG,
            Items.CAT_SPAWN_EGG, Items.WOLF_SPAWN_EGG, Items.VILLAGER_SPAWN_EGG,
            Items.ZOMBIE_SPAWN_EGG, Items.SKELETON_SPAWN_EGG, Items.CREEPER_SPAWN_EGG
    );

    public static void onLivingDrops(LivingDropsEvent event) {
        if (!(event.getEntity() instanceof Villager villager)) {
            return;
        }
        if (villager.getVillagerData().getProfession() != ModVillagers.MOB_WRANGLER.get()) {
            return;
        }

        RandomGenerator random = villager.level().getRandom();
        int looting = 0;
        if (event.getSource().getEntity() instanceof LivingEntity killer) {
            looting = EnchantmentHelper.getEnchantmentLevel(Enchantments.LOOTING, killer);
        }
        Level level = villager.level();
        double x = villager.getX();
        double y = villager.getY();
        double z = villager.getZ();

        // 2–4 rolls of “trade” drops (things he sells or takes as payment)
        int rolls = 2 + random.nextInt(3) + looting;
        for (int i = 0; i < rolls; i++) {
            addTradeDrop(event, random, looting, level, x, y, z);
        }

        // 1 in 10 million chance for a netherite sword (very rare)
        if (random.nextInt((int) RARE_SWORD_CHANCE) == 0) {
            addDrop(event, new ItemStack(Items.NETHERITE_SWORD), level, x, y, z);
        }
    }

    private static void addDrop(LivingDropsEvent event, ItemStack stack, Level level, double x, double y, double z) {
        event.getDrops().add(new ItemEntity(level, x, y, z, stack));
    }

    private static void addTradeDrop(LivingDropsEvent event, RandomGenerator random, int looting,
            Level level, double x, double y, double z) {
        int roll = random.nextInt(100);
        ItemStack stack;

        if (roll < 40) {
            // Emerald (common) – 1–4 + looting
            stack = new ItemStack(Items.EMERALD, 1 + random.nextInt(4) + random.nextInt(looting + 1));
        } else if (roll < 70) {
            // Egg (common) – 1–6 + looting
            stack = new ItemStack(Items.EGG, 1 + random.nextInt(6) + random.nextInt(looting + 1));
        } else if (roll < 88) {
            // Random spawn egg (uncommon)
            Item egg = SPAWN_EGG_DROPS.get(random.nextInt(SPAWN_EGG_DROPS.size()));
            stack = new ItemStack(egg);
        } else if (roll < 97) {
            // Emerald block (rare)
            stack = new ItemStack(Blocks.EMERALD_BLOCK, 1 + (random.nextInt(looting + 1) > 0 ? 1 : 0));
        } else {
            // Spawner (very rare)
            stack = new ItemStack(Blocks.SPAWNER);
        }

        addDrop(event, stack, level, x, y, z);
    }
}
