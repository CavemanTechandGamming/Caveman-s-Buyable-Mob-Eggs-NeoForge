package com.caveman.cavemansbuyablemobeggs;

import net.neoforged.neoforge.common.ModConfigSpec;

public class Config {
    private static final ModConfigSpec.Builder BUILDER = new ModConfigSpec.Builder();

    public static final ModConfigSpec.BooleanValue ENABLE_BOSS_MOB_TRADES = BUILDER
            .comment("If true, Mob Wrangler level 5 offers boss mob spawn eggs (e.g. wither, ender_dragon).")
            .define("enableBossMobTrades", true);

    static final ModConfigSpec SPEC = BUILDER.build();
}
