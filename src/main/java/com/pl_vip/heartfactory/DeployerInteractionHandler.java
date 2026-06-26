package com.pl_vip.heartfactory;

import com.simibubi.create.content.kinetics.deployer.DeployerFakePlayer;
import net.minecraft.advancements.Advancement;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = HeartFactory.MOD_ID)
public class DeployerInteractionHandler {

    @SubscribeEvent
    public static void onEntityInteract(PlayerInteractEvent.EntityInteract event) {
        if (event.getLevel().isClientSide) return;

        if (!(event.getEntity() instanceof DeployerFakePlayer deployer)) return;
        if (!(event.getTarget() instanceof Player targetPlayer)) return;
        if (!event.getItemStack().isEmpty()) return;

        IHeartPlayerData data = ModCapabilities.get(targetPlayer);
        float currentAbsorption = targetPlayer.getAbsorptionAmount();
        int source = data.getHeartSource();

        if (currentAbsorption < 2.0f) {
            data.setHeartSource(0);
            return;
        }

        if (source == 2) {
            extractHeart(event, deployer, targetPlayer, currentAbsorption, ModItems.ENCHANTED_GOLDEN_HEART.get().getDefaultInstance(), data);
        } else if (source == 1) {
            extractHeart(event, deployer, targetPlayer, currentAbsorption, ModItems.GOLDEN_HEART.get().getDefaultInstance(), data);
        }
    }

    private static void extractHeart(PlayerInteractEvent.EntityInteract event, DeployerFakePlayer deployer, Player target, float currentAbs, ItemStack resultItem, IHeartPlayerData data) {
        float newAbs = currentAbs - 2.0f;
        target.setAbsorptionAmount(newAbs);

        if (newAbs <= 0.0f) {
            data.setHeartSource(0);
        }

        deployer.setItemInHand(event.getHand(), resultItem);
        event.getLevel().playSound(null, target.blockPosition(), SoundEvents.TRIDENT_THUNDER, SoundSource.PLAYERS, 0.5f, 2.0f);

        if (target instanceof ServerPlayer serverPlayer) {
            ResourceLocation id = new ResourceLocation(HeartFactory.MOD_ID, "heartfactory/stolen_heart");
            Advancement advancement = serverPlayer.server.getAdvancements().getAdvancement(id);

            if (advancement != null) {
                serverPlayer.getAdvancements().award(advancement, "get_stolen");
            }
        }

        event.setCancellationResult(InteractionResult.SUCCESS);
        event.setCanceled(true);
    }
}
