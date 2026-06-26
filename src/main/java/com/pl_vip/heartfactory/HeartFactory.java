package com.pl_vip.heartfactory;

import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;

@Mod(HeartFactory.MOD_ID)
public class HeartFactory {
    public static final String MOD_ID = "heartfactory";

    public HeartFactory() {
        IEventBus modBus = net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext.get().getModEventBus();

        ModCapabilities.register(modBus);
        ModItems.register(modBus);
        ModCreativeModeTabs.register(modBus);
        ModFluids.register(modBus);
        ModPotions.register(modBus);
    }
}
