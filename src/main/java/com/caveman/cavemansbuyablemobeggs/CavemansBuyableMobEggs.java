package com.caveman.cavemansbuyablemobeggs;

import org.slf4j.Logger;

import com.mojang.logging.LogUtils;

import net.neoforged.fml.common.Mod;
import net.neoforged.fml.ModContainer;
import net.neoforged.neoforge.common.NeoForge;

import com.caveman.cavemansbuyablemobeggs.villager.ModVillagers;
import com.caveman.cavemansbuyablemobeggs.villager.MobWranglerLoot;
import com.caveman.cavemansbuyablemobeggs.villager.MobWranglerTrades;

@Mod(CavemansBuyableMobEggs.MOD_ID)
public class CavemansBuyableMobEggs {
    public static final String MOD_ID = "cavemans_buyable_mob_eggs";
    public static final Logger LOGGER = LogUtils.getLogger();

    public CavemansBuyableMobEggs(ModContainer container) {
        var bus = container.getEventBus();
        ModVillagers.POI_TYPES.register(bus);
        ModVillagers.PROFESSIONS.register(bus);
        NeoForge.EVENT_BUS.addListener(MobWranglerTrades::registerTrades);
        NeoForge.EVENT_BUS.addListener(MobWranglerLoot::onLivingDrops);
    }
}
