package com.pl_vip.heartfactory;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Items;
import net.minecraftforge.event.entity.living.LivingEntityUseItemEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = HeartFactory.MOD_ID)
public class HeartSourceHandler {

    @SubscribeEvent
    public static void onItemConsumed(LivingEntityUseItemEvent.Finish event) {
        if (event.getEntity().level().isClientSide) return;
        if (!(event.getEntity() instanceof Player player)) return;

        IHeartPlayerData data = ModCapabilities.get(player);

        if (event.getItem().is(Items.ENCHANTED_GOLDEN_APPLE)) {
            data.setHeartSource(2);
        } else if (event.getItem().is(Items.GOLDEN_APPLE)) {
            data.setHeartSource(1);
        }
    }

    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
        if (event.phase != TickEvent.Phase.END) return;

        Player player = event.player;
        if (player.level().isClientSide) return;

        if (player.getAbsorptionAmount() <= 0.0f) {
            IHeartPlayerData data = ModCapabilities.get(player);
            if (data.getHeartSource() != 0) {
                data.setHeartSource(0);
            }
        }
    }
}
