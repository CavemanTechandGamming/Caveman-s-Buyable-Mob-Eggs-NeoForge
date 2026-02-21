package com.caveman.cavemansbuyablemobeggs.api;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.OptionalInt;

import net.minecraft.world.item.Item;

import net.neoforged.bus.api.Event;

/**
 * Fired on {@link net.neoforged.neoforge.common.NeoForge#EVENT_BUS} to let other mods add spawn eggs
 * to the list of eggs the Mob Wrangler villager can trade, optionally at a specific trade level (1–5).
 * <p>
 * Our mod adds all known spawn eggs (vanilla + modded already in the item registry) before firing.
 * Other mods should subscribe to this event and call {@link #addSpawnEgg(Item)} or
 * {@link #addSpawnEgg(Item, int)} for their own eggs.
 * <p>
 * Trade levels: 1 = CREATURE, 2 = AMBIENT + WATER_*, 3 = MONSTER, 4 = nether mobs, 5 = boss mobs.
 * If no level is specified, the egg is placed by category/tags as above.
 * <p>
 * Example (in another mod):
 * <pre>{@code
 * NeoForge.EVENT_BUS.addListener(RegisterMobWranglerEggsEvent::onRegister);
 * static void onRegister(RegisterMobWranglerEggsEvent event) {
 *     event.addSpawnEgg(MyModItems.MY_MOB_SPAWN_EGG.get());
 *     event.addSpawnEgg(MyModItems.BOSS_EGG.get(), 5);  // force level 5
 * }
 * }</pre>
 * <p>
 * Optional: mods can also send spawn eggs via InterModComms during {@code InterModEnqueueEvent}:
 * send to mod id {@code cavemans_buyable_mob_eggs}, key {@value #IMC_KEY_REGISTER_SPAWN_EGG},
 * value a {@code Supplier<Item>} that returns the spawn egg item (level will be auto-assigned).
 */
public class RegisterMobWranglerEggsEvent extends Event {

    /** InterModComms message key for registering a spawn egg. Value must be a Supplier&lt;Item&gt;. */
    public static final String IMC_KEY_REGISTER_SPAWN_EGG = "register_spawn_egg";

    /** Valid trade levels (1–5). */
    public static final int MIN_LEVEL = 1;
    public static final int MAX_LEVEL = 5;

    private final List<Item> spawnEggs;
    private final Map<Item, Integer> levelOverrides = new HashMap<>();

    public RegisterMobWranglerEggsEvent(List<Item> initialEggs) {
        this.spawnEggs = new ArrayList<>(initialEggs);
    }

    /** Add a spawn egg so the Mob Wrangler can trade it. Level is assigned automatically by category/tags. */
    public void addSpawnEgg(Item item) {
        if (item != null && !spawnEggs.contains(item)) {
            spawnEggs.add(item);
        }
    }

    /**
     * Add a spawn egg at a specific trade level (1–5). Overrides automatic placement.
     * @param item the spawn egg item
     * @param level trade level 1–5 (clamped if out of range)
     */
    public void addSpawnEgg(Item item, int level) {
        if (item == null) return;
        int clamped = Math.clamp(level, MIN_LEVEL, MAX_LEVEL);
        if (!spawnEggs.contains(item)) {
            spawnEggs.add(item);
        }
        levelOverrides.put(item, clamped);
    }

    /** Read-only view of the current list of tradeable spawn eggs. */
    public List<Item> getSpawnEggs() {
        return Collections.unmodifiableList(spawnEggs);
    }

    /** Level override for an item (1–5), or empty if level is auto-assigned. */
    public OptionalInt getLevelOverride(Item item) {
        return levelOverrides.containsKey(item)
                ? OptionalInt.of(levelOverrides.get(item))
                : OptionalInt.empty();
    }

    /** Read-only copy of level overrides (item → level 1–5). */
    public Map<Item, Integer> getLevelOverrides() {
        return Collections.unmodifiableMap(new HashMap<>(levelOverrides));
    }
}
