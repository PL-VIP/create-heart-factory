package com.pl_vip.heartfactory;

import net.minecraft.advancements.Advancement;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.UUID;

@Mod.EventBusSubscriber(modid = HeartFactory.MOD_ID)
public class HealthHandler {

    private static final UUID HEALTH_MODIFIER_UUID = UUID.fromString("8f4e2c1a-3b5d-4e6f-9a0b-1c2d3e4f5a6b");

    public static void updateMaxHealth(Player player) {
        IHeartPlayerData data = ModCapabilities.get(player);
        int containers = data.getHeartContainers();
        int anarchic = data.getAnarchicHearts();

        double bonusHealth = (containers * 2.0) + (anarchic * 2.0);

        AttributeInstance attribute = player.getAttribute(Attributes.MAX_HEALTH);
        if (attribute != null) {
            attribute.removeModifier(HEALTH_MODIFIER_UUID);

            if (bonusHealth > 0) {
                AttributeModifier modifier = new AttributeModifier(
                        HEALTH_MODIFIER_UUID,
                        "heartfactory.extra_health",
                        bonusHealth,
                        AttributeModifier.Operation.ADDITION
                );
                attribute.addPermanentModifier(modifier);
            }
        }

        if (player instanceof ServerPlayer serverPlayer && player.getMaxHealth() >= 60.0f) {
            ResourceLocation id = new ResourceLocation(HeartFactory.MOD_ID, "heartfactory/immortal");
            Advancement advancement = serverPlayer.server.getAdvancements().getAdvancement(id);

            if (advancement != null) {
                serverPlayer.getAdvancements().award(advancement, "get_immortal");
            }
        }
    }

    @SubscribeEvent
    public static void onPlayerLoggedIn(PlayerEvent.PlayerLoggedInEvent event) {
        updateMaxHealth(event.getEntity());
    }

    @SubscribeEvent
    public static void onPlayerRespawn(PlayerEvent.PlayerRespawnEvent event) {
        updateMaxHealth(event.getEntity());
    }
}
