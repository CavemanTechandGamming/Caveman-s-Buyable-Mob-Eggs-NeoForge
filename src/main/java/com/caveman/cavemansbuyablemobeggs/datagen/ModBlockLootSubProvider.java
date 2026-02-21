package com.caveman.cavemansbuyablemobeggs.datagen;

import java.util.Set;
import java.util.stream.Stream;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;

public class ModBlockLootSubProvider extends BlockLootSubProvider {

    public ModBlockLootSubProvider(HolderLookup.Provider lookupProvider) {
        super(Set.of(), FeatureFlags.DEFAULT_FLAGS, lookupProvider);
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return Stream.of(Blocks.SPAWNER).toList();
    }

    @Override
    protected void generate() {
        // Spawner drops itself when broken (vanilla does not)
        dropSelf(Blocks.SPAWNER);
    }
}
