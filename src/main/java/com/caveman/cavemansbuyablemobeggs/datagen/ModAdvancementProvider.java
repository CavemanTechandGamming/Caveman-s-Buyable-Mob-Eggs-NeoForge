package com.caveman.cavemansbuyablemobeggs.datagen;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementHolder;
import net.minecraft.advancements.AdvancementRewards;
import net.minecraft.advancements.AdvancementType;
import net.minecraft.advancements.critereon.ItemUsedOnLocationTrigger;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Blocks;

import com.caveman.cavemansbuyablemobeggs.CavemansBuyableMobEggs;

import net.neoforged.neoforge.common.data.AdvancementProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

public class ModAdvancementProvider extends AdvancementProvider {

    public ModAdvancementProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries, ExistingFileHelper existingFileHelper) {
        super(output, registries, existingFileHelper, List.of(new Generator()));
    }

    private static class Generator implements AdvancementProvider.AdvancementGenerator {

        private static final String MOB_WRANGLER = "mob_wrangler";
        private static final String TITLE_KEY = "advancements." + CavemansBuyableMobEggs.MOD_ID + "." + MOB_WRANGLER + ".title";
        private static final String DESCR_KEY = "advancements." + CavemansBuyableMobEggs.MOD_ID + "." + MOB_WRANGLER + ".descr";

        @Override
        public void generate(HolderLookup.Provider registries, Consumer<AdvancementHolder> saver, ExistingFileHelper existingFileHelper) {
            ResourceLocation id = ResourceLocation.fromNamespaceAndPath(CavemansBuyableMobEggs.MOD_ID, MOB_WRANGLER);
            ResourceLocation background = ResourceLocation.parse("minecraft:textures/block/stone.png");

            Advancement.Builder.advancement()
                    .display(
                            Blocks.SPAWNER,
                            Component.translatable(TITLE_KEY),
                            Component.translatable(DESCR_KEY),
                            background,
                            AdvancementType.TASK,
                            true,
                            true,
                            true)
                    .addCriterion("placed_spawner", ItemUsedOnLocationTrigger.TriggerInstance.placedBlock(Blocks.SPAWNER))
                    .rewards(new AdvancementRewards(10, List.of(), List.of(), Optional.empty()))
                    .save(saver, id, existingFileHelper);
        }
    }
}
