# Adding Your Mod's Spawn Eggs to the Mob Wrangler

This document explains how other mods can make their spawn eggs available from the **Mob Wrangler** villager in Caveman's Buyable Mob Eggs.

## Overview

The Mob Wrangler is a custom villager profession whose workstation is a **Spawner**. It sells spawn eggs in exchange for **3 emeralds** and **1 chicken egg** per trade. Trades are grouped into five levels; addons can register eggs and optionally choose which level they appear at.

## Trade Levels

| Level | Description |
|-------|-------------|
| **1** | Creature mobs (`MobCategory.CREATURE`) |
| **2** | Ambient and water mobs (e.g. bat, fish, axolotl) |
| **3** | Monster mobs (`MobCategory.MONSTER`) |
| **4** | Nether mobs (via the mod’s `nether_mobs` entity tag) |
| **5** | Boss mobs (via the mod’s `boss_mobs` entity tag; can be disabled in config) |

If you do **not** specify a level, the mod assigns one automatically using the entity’s `MobCategory` and the nether/boss entity tags.

---

## Integration Steps

### 1. Subscribe to the event

Subscribe to `RegisterMobWranglerEggsEvent` on the **NeoForge event bus** (not the mod bus). The event is fired during common setup, after the mod has collected all spawn eggs already in the item registry.

### 2. Add your spawn eggs

In your handler you can:

- **Auto level:** `event.addSpawnEgg(yourSpawnEggItem)`  
  The egg is placed in a level based on entity category and tags (see above).

- **Fixed level:** `event.addSpawnEgg(yourSpawnEggItem, level)`  
  The egg is forced into the given level (1–5). Values outside 1–5 are clamped.

You can mix both: some eggs with a level, some without.

---

## Example (NeoForge / Java)

```java
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.BusSubscription;

// In your mod constructor or init:
NeoForge.EVENT_BUS.addListener(YourModClass::onMobWranglerRegister);

public static void onMobWranglerRegister(RegisterMobWranglerEggsEvent event) {
    // Add a normal mob egg – level chosen automatically
    event.addSpawnEgg(MyModItems.MY_MOB_SPAWN_EGG.get());

    // Add a boss egg – force it to level 5
    event.addSpawnEgg(MyModItems.BOSS_SPAWN_EGG.get(), 5);

    // Add an egg you want early in the villager – force level 1
    event.addSpawnEgg(MyModItems.EARLY_GAME_EGG.get(), 1);
}
```

---

## API Reference

- **Event:** `com.caveman.cavemansbuyablemobeggs.api.RegisterMobWranglerEggsEvent`
- **Methods:**
  - `addSpawnEgg(Item item)`  
    Adds the spawn egg; level is auto-assigned.
  - `addSpawnEgg(Item item, int level)`  
    Adds the spawn egg at the given trade level (1–5, clamped).
- **Constants:** `RegisterMobWranglerEggsEvent.MIN_LEVEL` (1), `MAX_LEVEL` (5)

Your `Item` must be a spawn egg item (e.g. extend or wrap `SpawnEggItem`). The mod only adds items that are valid spawn eggs when building trades.

---

## Dependencies

Add Caveman's Buyable Mob Eggs as a dependency in your mod so the event and API are available. In your `build.gradle` (or equivalent):

```gradle
dependencies {
    implementation "curse.maven:cavemans-buyable-mob-eggs-XXXXXX:YYYYYY"
    // or your preferred way to depend on the mod
}
```

For **compile-only** use (e.g. to avoid bundling the mod in your jar), you can depend on the mod’s API or use a `compileOnly` / optional dependency and document that the integration only runs when the mod is present.

---

## Summary

1. Subscribe to `RegisterMobWranglerEggsEvent` on **NeoForge.EVENT_BUS**.
2. Call `addSpawnEgg(Item)` for auto level, or `addSpawnEgg(Item, level)` for a fixed level (1–5).
3. Ensure your mod depends on (or optionally loads with) Caveman's Buyable Mob Eggs so the event is fired when both mods are installed.

After that, your spawn eggs will be available from the Mob Wrangler villager at the levels you chose (or the auto-assigned level).
