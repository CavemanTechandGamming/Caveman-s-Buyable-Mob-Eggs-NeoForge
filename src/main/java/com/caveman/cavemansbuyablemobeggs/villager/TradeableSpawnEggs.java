package com.caveman.cavemansbuyablemobeggs.villager;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.OptionalInt;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SpawnEggItem;

import com.caveman.cavemansbuyablemobeggs.api.RegisterMobWranglerEggsEvent;

import net.neoforged.neoforge.common.NeoForge;

/**
 * Holds the list of spawn eggs the Mob Wrangler can trade and any level overrides from the API.
 * Populated during init from vanilla/mod spawn eggs (item registry) and {@link RegisterMobWranglerEggsEvent}.
 */
public final class TradeableSpawnEggs {

    private static List<Item> tradeableEggs = List.of();
    private static Map<Item, Integer> levelOverrides = Map.of();

    /**
     * Builds the tradeable eggs list and fires {@link RegisterMobWranglerEggsEvent}.
     * Call from FMLCommonSetupEvent (enqueueWork so on main thread).
     */
    public static void gatherAndFireEvent() {
        List<Item> initial = new ArrayList<>();
        BuiltInRegistries.ITEM.stream()
                .filter(SpawnEggItem.class::isInstance)
                .map(Item.class::cast)
                .forEach(initial::add);

        RegisterMobWranglerEggsEvent evt = new RegisterMobWranglerEggsEvent(initial);
        NeoForge.EVENT_BUS.post(evt);
        tradeableEggs = Collections.unmodifiableList(new ArrayList<>(evt.getSpawnEggs()));
        levelOverrides = Collections.unmodifiableMap(new HashMap<>(evt.getLevelOverrides()));
    }

    /** Unmodifiable list of spawn eggs the Mob Wrangler can trade (for use when registering trades). */
    public static List<Item> getTradeableSpawnEggs() {
        return tradeableEggs;
    }

    /** Level override (1–5) for an egg, or empty if level is auto-assigned. */
    public static OptionalInt getLevelOverride(Item eggItem) {
        return levelOverrides.containsKey(eggItem)
                ? OptionalInt.of(levelOverrides.get(eggItem))
                : OptionalInt.empty();
    }
}
