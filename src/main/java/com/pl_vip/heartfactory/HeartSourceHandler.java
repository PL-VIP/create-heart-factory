package com.pl_vip.heartfactory;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Items;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.living.LivingEntityUseItemEvent;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;

@EventBusSubscriber(modid = HeartFactory.MOD_ID)
public class HeartSourceHandler {

    @SubscribeEvent
    public static void onItemConsumed(LivingEntityUseItemEvent.Finish event) {
        if (event.getEntity().level().isClientSide) return;
        if (!(event.getEntity() instanceof Player player)) return;

        if (event.getItem().is(Items.ENCHANTED_GOLDEN_APPLE)) {
            player.setData(ModAttachments.HEART_SOURCE, 2); // Kox
        } else if (event.getItem().is(Items.GOLDEN_APPLE)) {
            player.setData(ModAttachments.HEART_SOURCE, 1); // Zwykłe
        }
    }

    @SubscribeEvent
    public static void onPlayerTick(PlayerTickEvent.Post event) {
        Player player = event.getEntity();
        if (player.level().isClientSide) return;

        // Jeśli absorpcja spadła do 0, resetujemy źródło
        if (player.getAbsorptionAmount() <= 0.0f) {
            if (player.getData(ModAttachments.HEART_SOURCE) != 0) {
                player.setData(ModAttachments.HEART_SOURCE, 0);
            }
        }
    }
}