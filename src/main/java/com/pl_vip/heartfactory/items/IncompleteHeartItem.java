package com.pl_vip.heartfactory.items;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;

import java.util.List;

public class IncompleteHeartItem extends Item {
    public IncompleteHeartItem(Properties properties) {
        super(properties);
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltip, TooltipFlag flag) {
        // Opis procesu
        tooltip.add(Component.translatable("item.heartfactory.incomplete_heart.desc_1").withStyle(ChatFormatting.GRAY));
        tooltip.add(Component.translatable("item.heartfactory.incomplete_heart.desc_2").withStyle(ChatFormatting.DARK_PURPLE));
        super.appendHoverText(stack, context, tooltip, flag);
    }
}