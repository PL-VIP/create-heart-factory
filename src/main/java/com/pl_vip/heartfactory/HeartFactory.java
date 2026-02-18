package com.pl_vip.heartfactory;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.loading.FMLEnvironment;
import net.neoforged.api.distmarker.Dist;

@Mod(HeartFactory.MOD_ID)
public class HeartFactory {
    public static final String MOD_ID = "heartfactory";

    public HeartFactory(IEventBus modBus) {
        ModItems.register(modBus);
        ModAttachments.register(modBus);
        ModCreativeModeTabs.register(modBus);
        ModFluids.register(modBus);
        ModPotions.register(modBus);

        if (FMLEnvironment.dist == Dist.CLIENT) {
            modBus.addListener(HeartFactoryClient::onRegisterClientExtensions);
        }
    }
}