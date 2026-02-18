package com.pl_vip.heartfactory;

import com.simibubi.create.content.kinetics.deployer.DeployerFakePlayer;
import net.minecraft.advancements.AdvancementHolder; // WAŻNY IMPORT
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent;

@EventBusSubscriber(modid = HeartFactory.MOD_ID)
public class DeployerInteractionHandler {

    @SubscribeEvent
    public static void onEntityInteract(PlayerInteractEvent.EntityInteract event) {
        if (event.getLevel().isClientSide) return;

        if (!(event.getEntity() instanceof DeployerFakePlayer deployer)) return;
        if (!(event.getTarget() instanceof Player targetPlayer)) return;
        if (!event.getItemStack().isEmpty()) return;

        float currentAbsorption = targetPlayer.getAbsorptionAmount();
        int source = targetPlayer.getData(ModAttachments.HEART_SOURCE);

        if (currentAbsorption < 2.0f) {
            targetPlayer.setData(ModAttachments.HEART_SOURCE, 0);
            return;
        }

        if (source == 2) {
            extractHeart(event, deployer, targetPlayer, currentAbsorption, ModItems.ENCHANTED_GOLDEN_HEART.get().getDefaultInstance());
        } else if (source == 1) {
            extractHeart(event, deployer, targetPlayer, currentAbsorption, ModItems.GOLDEN_HEART.get().getDefaultInstance());
        }
    }

    private static void extractHeart(PlayerInteractEvent.EntityInteract event, DeployerFakePlayer deployer, Player target, float currentAbs, ItemStack resultItem) {
        float newAbs = currentAbs - 2.0f;
        target.setAbsorptionAmount(newAbs);

        if (newAbs <= 0.0f) {
            target.setData(ModAttachments.HEART_SOURCE, 0);
        }

        deployer.setItemInHand(event.getHand(), resultItem);
        event.getLevel().playSound(null, target.blockPosition(), SoundEvents.TRIDENT_THUNDER.value(), SoundSource.PLAYERS, 0.5f, 2.0f);

        // --- ACHIEVEMENT (POPRAWIONE DLA 1.21) ---
        if (target instanceof ServerPlayer serverPlayer) {
            // Ścieżka: heartfactory:heartfactory/stolen_heart
            ResourceLocation id = ResourceLocation.fromNamespaceAndPath(HeartFactory.MOD_ID, "heartfactory/stolen_heart");
            AdvancementHolder advancement = serverPlayer.server.getAdvancements().get(id);

            if (advancement != null) {
                // "get_stolen" to nazwa kryterium z pliku JSON
                serverPlayer.getAdvancements().award(advancement, "get_stolen");
            }
        }

        event.setCancellationResult(InteractionResult.SUCCESS);
        event.setCanceled(true);
    }
}