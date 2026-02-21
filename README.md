# Caveman's Buyable Mob Eggs (NeoForge)

A [NeoForge](https://neoforged.net/) mod for **Minecraft 1.21.1** that makes every mob spawn egg buyable from a custom villager—the **Mob Wrangler**—and adds a craftable spawner.

---

## Mob Wrangler Villager

- **Workstation:** Mob Spawner  
- **Trade:** 3 Emeralds + 1 Chicken Egg → 1 Spawn Egg (same at every level)  
- Trades refresh like a normal villager; cycle through to find the egg you want.

### Trade levels

| Level | Name       | Description                          |
| ----- | ---------- | ------------------------------------ |
| 1     | Novice     | Creature mobs                        |
| 2     | Apprentice | Ambient, water, and misc mobs        |
| 3     | Journeyman | Monster mobs                         |
| 4     | Expert     | Nether mobs                          |
| 5     | Master     | Boss mobs (toggle in config)         |

**Level 1 — Novice (creatures)**  
Allay, Armadillo, Bee, Camel, Cat, Chicken, Cow, Donkey, Fox, Frog, Goat, Horse, Llama, Mooshroom, Mule, Ocelot, Panda, Parrot, Pig, Polar Bear, Rabbit, Sheep, Sniffer, Snow Golem, Trader Llama, Turtle, Villager, Wandering Trader, Wolf

**Level 2 — Apprentice (ambient / water / misc)**  
Bat, Axolotl, Cod, Dolphin, Elder Guardian, Glow Squid, Guardian, Pufferfish, Salmon, Squid, Tadpole, Tropical Fish, Iron Golem

**Level 3 — Journeyman (monsters)**  
Bogged, Breeze, Cave Spider, Creeper, Drowned, Enderman, Endermite, Evoker, Husk, Phantom, Pillager, Ravager, Shulker, Silverfish, Skeleton, Skeleton Horse, Slime, Spider, Stray, Vex, Vindicator, Warden, Witch, Zombie, Zombie Horse, Zombie Villager, Zombified Piglin

**Level 4 — Expert (Nether)**  
Blaze, Ghast, Hoglin, Magma Cube, Piglin, Piglin Brute, Strider, Wither Skeleton, Zoglin

**Level 5 — Master (bosses)**  
Ender Dragon, Wither

---

## Craftable Mob Spawners

- Mob spawners are **craftable** in survival.  
- Use a **spawn egg on a placed spawner** to set its mob type.  
- Break with an **iron pickaxe or better** to drop the spawner.

**Recipe** (shaped, 3×3)

![Mob Spawner recipe layout](docs/Mob%20Spawner%20Recipe.png)

- 2 Redstone Torches  
- 4 Iron Bars  
- 1 Dispenser  
- 1 Diamond  

---

## Requirements & installation

- **Minecraft** 1.21.1 · **NeoForge** 21.1.x (or compatible)

1. Install [NeoForge](https://neoforged.net/) for 1.21.1.  
2. Download the latest JAR from [releases](https://github.com/CavemanTechandGamming/Caveman-s-Buyable-Mob-Eggs-NeoForge/releases).  
3. Put the JAR in your `mods` folder.

---

## Addon integration

Other mods can register spawn eggs with the Mob Wrangler. See the **[Addon integration guide](docs/ADDON_INTEGRATION.md)** for the API, `RegisterMobWranglerEggsEvent`, and examples.

---

## Links & license

- [GitHub](https://github.com/CavemanTechandGamming/Caveman-s-Buyable-Mob-Eggs-NeoForge) · [NeoForge docs](https://docs.neoforged.net/)  
- This project uses official Mojang mapping names; see the [NeoForm mapping license](https://github.com/NeoForged/NeoForm/blob/main/Mojang.md).  
- [LICENSE](LICENSE)
