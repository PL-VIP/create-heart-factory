package com.pl_vip.heartfactory;

import net.minecraft.advancements.AdvancementHolder; // WAŻNY IMPORT
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;

@EventBusSubscriber(modid = HeartFactory.MOD_ID)
public class HealthHandler {

    private static final ResourceLocation HEALTH_MODIFIER_ID = ResourceLocation.fromNamespaceAndPath(HeartFactory.MOD_ID, "extra_health");

    public static void updateMaxHealth(Player player) {
        int containers = player.getData(ModAttachments.HEART_CONTAINERS);
        int anarchic = player.getData(ModAttachments.ANARCHIC_HEARTS);

        double bonusHealth = (containers * 2.0) + (anarchic * 2.0);

        AttributeInstance attribute = player.getAttribute(Attributes.MAX_HEALTH);
        if (attribute != null) {
            attribute.removeModifier(HEALTH_MODIFIER_ID);

            if (bonusHealth > 0) {
                AttributeModifier modifier = new AttributeModifier(
                        HEALTH_MODIFIER_ID,
                        bonusHealth,
                        AttributeModifier.Operation.ADD_VALUE
                );
                attribute.addPermanentModifier(modifier);
            }
        }

        // --- ACHIEVEMENT (POPRAWIONE DLA 1.21) ---
        if (player instanceof ServerPlayer serverPlayer && player.getMaxHealth() >= 60.0f) {
            // Ścieżka: heartfactory:heartfactory/immortal
            ResourceLocation id = ResourceLocation.fromNamespaceAndPath(HeartFactory.MOD_ID, "heartfactory/immortal");
            AdvancementHolder advancement = serverPlayer.server.getAdvancements().get(id);

            if (advancement != null) {
                // "get_immortal" to nazwa kryterium z pliku JSON
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