package com.pl_vip.heartfactory.items;

import com.pl_vip.heartfactory.HealthHandler;
import com.pl_vip.heartfactory.ModAttachments;
import com.simibubi.create.content.kinetics.deployer.DeployerFakePlayer; // WAŻNY IMPORT
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

import java.util.List;

public class PermanentHeartItem extends Item {
    private final boolean isAnarchic;
    private final int MAX_PER_TYPE = 10;

    public PermanentHeartItem(Properties properties, boolean isAnarchic) {
        super(properties);
        this.isAnarchic = isAnarchic;
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);

        // --- BLOKADA DEPLOYERA ---
        // Jeśli ten "gracz" to Deployer, nie pozwalaj mu zjeść serca.
        if (player instanceof DeployerFakePlayer) {
            return InteractionResultHolder.pass(stack);
        }
        // -------------------------

        if (!level.isClientSide) {
            int currentNormal = player.getData(ModAttachments.HEART_CONTAINERS);
            int currentAnarchic = player.getData(ModAttachments.ANARCHIC_HEARTS);

            if (!isAnarchic) {
                if (currentNormal < MAX_PER_TYPE) {
                    player.setData(ModAttachments.HEART_CONTAINERS, currentNormal + 1);
                    HealthHandler.updateMaxHealth(player);
                    stack.shrink(1);
                    level.playSound(null, player.blockPosition(), SoundEvents.PLAYER_LEVELUP, SoundSource.PLAYERS, 1.0f, 1.0f);
                    return InteractionResultHolder.consume(stack);
                } else {
                    player.sendSystemMessage(Component.translatable("message.heartfactory.max_hearts"));
                }
            } else {
                if (currentAnarchic < MAX_PER_TYPE) {
                    player.setData(ModAttachments.ANARCHIC_HEARTS, currentAnarchic + 1);
                    HealthHandler.updateMaxHealth(player);
                    stack.shrink(1);
                    level.playSound(null, player.blockPosition(), SoundEvents.WITHER_SPAWN, SoundSource.PLAYERS, 0.5f, 2.0f);
                    return InteractionResultHolder.consume(stack);
                } else {
                    player.sendSystemMessage(Component.translatable("message.heartfactory.max_netherite"));
                }
            }
        }
        return InteractionResultHolder.fail(stack);
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltip, TooltipFlag flag) {
        if (!isAnarchic) {
            tooltip.add(Component.translatable("tooltip.heartfactory.heart_desc").withStyle(ChatFormatting.GRAY));
        } else {
            tooltip.add(Component.translatable("tooltip.heartfactory.netherite_desc").withStyle(ChatFormatting.DARK_PURPLE));
        }
        super.appendHoverText(stack, context, tooltip, flag);
    }
}