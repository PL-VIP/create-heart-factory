package com.pl_vip.heartfactory.items;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;

import java.util.List;

public class BrokenHeartItem extends Item {
    public BrokenHeartItem(Properties properties) {
        super(properties);
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltip, TooltipFlag flag) {
        tooltip.add(Component.translatable("item.heartfactory.broken_heart.desc").withStyle(ChatFormatting.DARK_RED, ChatFormatting.ITALIC));
        super.appendHoverText(stack, context, tooltip, flag);
    }
}