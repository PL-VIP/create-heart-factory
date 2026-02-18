package com.pl_vip.heartfactory.items;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;

import java.util.List;

public class GoldenHeartItem extends Item {
    public GoldenHeartItem(Properties properties) {
        super(properties);
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltip, TooltipFlag flag) {
        // Tooltip informujący o procesie
        tooltip.add(Component.translatable("item.heartfactory.golden_heart.desc_1").withStyle(ChatFormatting.GOLD));
        tooltip.add(Component.translatable("item.heartfactory.golden_heart.desc_2").withStyle(ChatFormatting.GRAY));
        super.appendHoverText(stack, context, tooltip, flag);
    }
}