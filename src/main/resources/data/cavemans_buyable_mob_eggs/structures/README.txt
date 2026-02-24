Mob Wrangler house structure (NBT)
==================================

Drop your structure NBT file here.

1. Export your house from in-game using a Structure Block (Save mode).
2. Name it (e.g. mob_wrangler_house.nbt).
3. Put the .nbt file in this folder.

The structure will be available as:
  cavemans_buyable_mob_eggs:structures/<filename_without_.nbt>

Example: mob_wrangler_house.nbt
  -> cavemans_buyable_mob_eggs:structures/mob_wrangler_house

To have this structure spawn naturally in the world, you also need:
  - data/cavemans_buyable_mob_eggs/worldgen/structure/<name>.json
  - data/cavemans_buyable_mob_eggs/worldgen/structure_set/<name>.json
  (or use a mod/datagen to register the structure and add it to a structure set.)
