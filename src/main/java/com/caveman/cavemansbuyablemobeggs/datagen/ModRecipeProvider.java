package com.caveman.cavemansbuyablemobeggs.datagen;

import java.util.concurrent.CompletableFuture;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Blocks;

import com.caveman.cavemansbuyablemobeggs.CavemansBuyableMobEggs;

/**
 * Generates recipe JSONs. Add recipes in {@link #buildRecipes(RecipeOutput)}.
 */
public class ModRecipeProvider extends RecipeProvider {

    public ModRecipeProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider) {
        super(output, lookupProvider);
    }

    @Override
    protected void buildRecipes(RecipeOutput output) {
        // Spawner: 2 redstone torch, 4 iron bars, 1 dispenser, 1 diamond
        //   aba
        //   bcb
        //   dbd
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, Blocks.SPAWNER)
                .pattern("aba")
                .pattern("bcb")
                .pattern("dbd")
                .define('a', Items.REDSTONE_TORCH)
                .define('b', Items.IRON_BARS)
                .define('c', Items.DISPENSER)
                .define('d', Items.DIAMOND)
                .unlockedBy("has_dispenser", has(Items.DISPENSER))
                .save(output, CavemansBuyableMobEggs.MOD_ID + ":spawner");
    }
}
