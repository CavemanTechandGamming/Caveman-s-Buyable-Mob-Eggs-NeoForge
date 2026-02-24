package com.caveman.cavemansbuyablemobeggs.datagen;

import java.util.concurrent.CompletableFuture;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.PoiTypeTags;

import com.caveman.cavemansbuyablemobeggs.CavemansBuyableMobEggs;
import com.caveman.cavemansbuyablemobeggs.villager.ModVillagers;

import net.minecraft.data.tags.PoiTypeTagsProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

public class ModPoiTypeTagsProvider extends PoiTypeTagsProvider {

    public ModPoiTypeTagsProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, String modId, ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, modId, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider lookupProvider) {
        // Mob Wrangler workstation (spawner) is an acquirable job site (append to vanilla tag)
        tag(PoiTypeTags.ACQUIRABLE_JOB_SITE)
                .replace(false)
                .addOptional(ResourceLocation.fromNamespaceAndPath(CavemansBuyableMobEggs.MOD_ID, ModVillagers.MOB_WRANGLER_POI_NAME));
    }
}
