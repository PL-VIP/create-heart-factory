package com.pl_vip.heartfactory;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModPotions {
    public static final DeferredRegister<Potion> POTIONS = DeferredRegister.create(ForgeRegistries.POTIONS, HeartFactory.MOD_ID);

    public static final RegistryObject<Potion> HEALTH_BOOST = POTIONS.register("health_boost", () ->
            new Potion("health_boost",
                    new MobEffectInstance(MobEffects.HEALTH_BOOST, 3600, 0),
                    new MobEffectInstance(MobEffects.HEAL, 1, 1)
            ));

    public static final RegistryObject<Potion> LONG_HEALTH_BOOST = POTIONS.register("long_health_boost", () ->
            new Potion("health_boost",
                    new MobEffectInstance(MobEffects.HEALTH_BOOST, 9600, 0),
                    new MobEffectInstance(MobEffects.HEAL, 1, 1)
            ));

    public static final RegistryObject<Potion> STRONG_HEALTH_BOOST = POTIONS.register("strong_health_boost", () ->
            new Potion("health_boost",
                    new MobEffectInstance(MobEffects.HEALTH_BOOST, 1800, 1),
                    new MobEffectInstance(MobEffects.HEAL, 1, 1)
            ));

    public static void register(IEventBus eventBus) {
        POTIONS.register(eventBus);
    }
}
