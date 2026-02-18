package com.pl_vip.heartfactory;

import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.Potions;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.brewing.RegisterBrewingRecipesEvent;

@EventBusSubscriber(modid = HeartFactory.MOD_ID)
public class ModBrewingRecipes {

    @SubscribeEvent
    public static void onRegisterBrewingRecipes(RegisterBrewingRecipesEvent event) {
        var builder = event.getBuilder();

        // 1. Baza: Awkward + Heart -> Health Boost
        // To pozwala zrobić potkę w Brewing Standzie
        builder.addMix(
                Potions.AWKWARD,
                ModItems.HEART.get(),
                ModPotions.HEALTH_BOOST
        );

        // 2. Ulepszenia (Redstone / Glowstone)
        // Dzięki temu Create w Basinie będzie wiedział, co robić!
        builder.addMix(ModPotions.HEALTH_BOOST, Items.REDSTONE, ModPotions.LONG_HEALTH_BOOST);
        builder.addMix(ModPotions.HEALTH_BOOST, Items.GLOWSTONE_DUST, ModPotions.STRONG_HEALTH_BOOST);
    }
}