package com.caveman.cavemansbuyablemobeggs.villager;

import java.util.function.Predicate;

import com.google.common.collect.ImmutableSet;

import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.ai.village.poi.PoiType;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.level.block.Blocks;

import com.caveman.cavemansbuyablemobeggs.CavemansBuyableMobEggs;

import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModVillagers {

    public static final String MOB_WRANGLER_POI_NAME = "mob_wrangler_poi";
    public static final String MOB_WRANGLER_PROFESSION_NAME = "mob_wrangler";

    public static final DeferredRegister<PoiType> POI_TYPES =
            DeferredRegister.create(Registries.POINT_OF_INTEREST_TYPE, CavemansBuyableMobEggs.MOD_ID);

    public static final DeferredHolder<PoiType, PoiType> MOB_WRANGLER_POI =
            POI_TYPES.register(MOB_WRANGLER_POI_NAME, () -> new PoiType(
                    ImmutableSet.copyOf(Blocks.SPAWNER.getStateDefinition().getPossibleStates()), 1, 1));

    public static final DeferredRegister<VillagerProfession> PROFESSIONS =
            DeferredRegister.create(Registries.VILLAGER_PROFESSION, CavemansBuyableMobEggs.MOD_ID);

    public static final DeferredHolder<VillagerProfession, VillagerProfession> MOB_WRANGLER =
            PROFESSIONS.register(MOB_WRANGLER_PROFESSION_NAME, () -> {
                Holder<PoiType> poiHolder = MOB_WRANGLER_POI;
                Predicate<Holder<PoiType>> poiPredicate = holder -> holder.value() == poiHolder.value();
                SoundEvent workSound = BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("entity.zombie.hurt"));
                return new VillagerProfession(
                        CavemansBuyableMobEggs.MOD_ID + ":" + MOB_WRANGLER_PROFESSION_NAME,
                        poiPredicate,
                        poiPredicate,
                        ImmutableSet.of(),
                        ImmutableSet.of(),
                        workSound);
            });
}
