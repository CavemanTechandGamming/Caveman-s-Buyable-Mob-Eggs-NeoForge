package com.caveman.cavemansbuyablemobeggs.datagen;

import java.util.List;
import java.util.Set;
import java.util.concurrent.CompletableFuture;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;

import com.caveman.cavemansbuyablemobeggs.CavemansBuyableMobEggs;

import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.data.event.GatherDataEvent;

@EventBusSubscriber(modid = CavemansBuyableMobEggs.MOD_ID, bus = EventBusSubscriber.Bus.MOD)
public class DataGenerators {

    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        var generator = event.getGenerator();
        PackOutput packOutput = generator.getPackOutput();
        CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();

        event.createProvider((output, lookup) -> new ModRecipeProvider(output, lookup));

        event.createProvider((output, lookup) -> new LootTableProvider(
                output,
                Set.of(),
                List.of(new LootTableProvider.SubProviderEntry(ModBlockLootSubProvider::new, LootContextParamSets.BLOCK)),
                lookup
        ));

        event.createProvider(output -> new ModLanguageProvider(output));

        event.createBlockAndItemTags(
                (output, lookup) -> new ModBlockTagsProvider(output, lookup, CavemansBuyableMobEggs.MOD_ID, event.getExistingFileHelper()),
                (output, lookup, blockTags) -> new ModItemTagsProvider(output, lookup, blockTags));
    }
}
