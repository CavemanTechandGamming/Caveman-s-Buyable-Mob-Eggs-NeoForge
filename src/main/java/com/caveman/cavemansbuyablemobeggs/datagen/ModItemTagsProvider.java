package com.caveman.cavemansbuyablemobeggs.datagen;

import java.util.concurrent.CompletableFuture;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.TagsProvider;
import net.minecraft.world.level.block.Block;

import net.minecraft.data.tags.ItemTagsProvider;

public class ModItemTagsProvider extends ItemTagsProvider {

    public ModItemTagsProvider(
            PackOutput output,
            CompletableFuture<HolderLookup.Provider> lookupProvider,
            CompletableFuture<TagsProvider.TagLookup<Block>> blockTags) {
        super(output, lookupProvider, blockTags);
    }

    @Override
    protected void addTags(HolderLookup.Provider lookupProvider) {
        // Add item tags when needed
    }
}
