package com.caveman.cavemansbuyablemobeggs.villager;

import java.util.Set;

import com.google.common.collect.ImmutableSet;

import net.minecraft.core.registries.Registries;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.ai.village.poi.PoiType;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;

import com.caveman.cavemansbuyablemobeggs.CavemansBuyableMobEggs;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

/**
 * Villager-related registrations: POI types (job sites) and professions.
 * Mob Wrangler uses the spawner block as their job site.
 * POI and profession must have different registry names to work correctly in-game.
 */
public class ModVillagers {

    /** Registry path for the POI type (spawner job site). */
    public static final String MOB_WRANGLER_POI_ID = "mob_wrangler_poi";
    /** Registry path for the Mob Wrangler profession (translation/texture use this). */
    public static final String MOB_WRANGLER_PROFESSION_ID = "mob_wrangler";

    public static final DeferredRegister<PoiType> POI_TYPES =
            DeferredRegister.create(Registries.POINT_OF_INTEREST_TYPE, CavemansBuyableMobEggs.MOD_ID);

    public static final DeferredRegister<VillagerProfession> PROFESSIONS =
            DeferredRegister.create(Registries.VILLAGER_PROFESSION, CavemansBuyableMobEggs.MOD_ID);

    /** POI for the mob spawner block. Mob Wrangler uses this as their job site. */
    public static final DeferredHolder<PoiType, PoiType> MOB_WRANGLER_POI = POI_TYPES.register(MOB_WRANGLER_POI_ID,
            () -> new PoiType(getSpawnerBlockStates(), 1, 1));

    /** Mob Wrangler: buys/sells mob eggs. Workstation = spawner. */
    public static final DeferredHolder<VillagerProfession, VillagerProfession> MOB_WRANGLER = PROFESSIONS.register(
            MOB_WRANGLER_PROFESSION_ID,
            () -> new VillagerProfession(
                    MOB_WRANGLER_PROFESSION_ID,
                    holder -> holder.is(MOB_WRANGLER_POI.getKey()),
                    holder -> holder.is(MOB_WRANGLER_POI.getKey()),
                    ImmutableSet.of(),
                    ImmutableSet.of(),
                    SoundEvents.VILLAGER_WORK_WEAPONSMITH
            ));

    private static Set<BlockState> getSpawnerBlockStates() {
        return Set.of(Blocks.SPAWNER.defaultBlockState());
    }

    public static void register(IEventBus eventBus) {
        POI_TYPES.register(eventBus);
        PROFESSIONS.register(eventBus);
    }
}
