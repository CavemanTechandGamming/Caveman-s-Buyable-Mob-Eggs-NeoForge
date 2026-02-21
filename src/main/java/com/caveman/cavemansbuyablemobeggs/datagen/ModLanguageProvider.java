package com.caveman.cavemansbuyablemobeggs.datagen;

import net.minecraft.data.PackOutput;

import com.caveman.cavemansbuyablemobeggs.CavemansBuyableMobEggs;

import net.neoforged.neoforge.common.data.LanguageProvider;

public class ModLanguageProvider extends LanguageProvider {

    public ModLanguageProvider(PackOutput output) {
        super(output, CavemansBuyableMobEggs.MOD_ID, "en_us");
    }

    @Override
    protected void addTranslations() {

        // =====================
        // ENTITY
        // =====================

        add("entity.minecraft.villager." + CavemansBuyableMobEggs.MOD_ID + ".mob_wrangler", "Mob Wrangler");

        // =====================
        // CONFIG
        // =====================

        add("config." + CavemansBuyableMobEggs.MOD_ID + ".title", "Caveman's Buyable Mob Eggs Config");
        add("config." + CavemansBuyableMobEggs.MOD_ID + ".common.enableBossMobTrades", "Enable boss mob trades (level 5)");
    }
}
