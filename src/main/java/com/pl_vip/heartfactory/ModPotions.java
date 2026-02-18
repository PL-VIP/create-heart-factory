package com.pl_vip.heartfactory;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.alchemy.Potion;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModPotions {
    public static final DeferredRegister<Potion> POTIONS = DeferredRegister.create(Registries.POTION, HeartFactory.MOD_ID);

    // 1. Zwykła (3:00 min, +2 Serca)
    public static final DeferredHolder<Potion, Potion> HEALTH_BOOST = POTIONS.register("health_boost", () ->
            new Potion("health_boost",
                    new MobEffectInstance(MobEffects.HEALTH_BOOST, 3600, 0), // 3 min, Poziom I
                    new MobEffectInstance(MobEffects.HEAL, 1, 1)             // Instant Health II
            ));

    // 2. Długa (8:00 min, +2 Serca)
    public static final DeferredHolder<Potion, Potion> LONG_HEALTH_BOOST = POTIONS.register("long_health_boost", () ->
            new Potion("health_boost",
                    new MobEffectInstance(MobEffects.HEALTH_BOOST, 9600, 0), // 8 min
                    new MobEffectInstance(MobEffects.HEAL, 1, 1)
            ));

    // 3. Silna (1:30 min, +4 Serca)
    public static final DeferredHolder<Potion, Potion> STRONG_HEALTH_BOOST = POTIONS.register("strong_health_boost", () ->
            new Potion("health_boost",
                    new MobEffectInstance(MobEffects.HEALTH_BOOST, 1800, 1), // 1.5 min, Poziom II
                    new MobEffectInstance(MobEffects.HEAL, 1, 1)
            ));

    public static void register(IEventBus eventBus) {
        POTIONS.register(eventBus);
    }
}