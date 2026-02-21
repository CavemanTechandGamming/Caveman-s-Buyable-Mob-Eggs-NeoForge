package com.caveman.cavemansbuyablemobeggs;

import org.slf4j.Logger;

import com.mojang.logging.LogUtils;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.common.NeoForge;

import com.caveman.cavemansbuyablemobeggs.villager.ModVillagers;
import com.caveman.cavemansbuyablemobeggs.villager.MobWranglerTrades;
import com.caveman.cavemansbuyablemobeggs.villager.TradeableSpawnEggs;

@Mod(CavemansBuyableMobEggs.MOD_ID)
public class CavemansBuyableMobEggs {
    public static final String MOD_ID = "cavemans_buyable_mob_eggs";
    public static final Logger LOGGER = LogUtils.getLogger();

    public CavemansBuyableMobEggs(IEventBus modEventBus, ModContainer modContainer) {
        modEventBus.addListener(this::commonSetup);

        ModVillagers.register(modEventBus);

        NeoForge.EVENT_BUS.addListener(MobWranglerTrades::registerTrades);

        modContainer.registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }

    private void commonSetup(FMLCommonSetupEvent event) {
        event.enqueueWork(TradeableSpawnEggs::gatherAndFireEvent);
    }
}
