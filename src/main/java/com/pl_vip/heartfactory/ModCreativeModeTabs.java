package com.pl_vip.heartfactory;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModCreativeModeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, HeartFactory.MOD_ID);

    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> HEART_TAB = CREATIVE_MODE_TABS.register("heart_tab", () -> CreativeModeTab.builder()
            .title(Component.translatable("itemGroup.heartfactory"))
            .icon(() -> new ItemStack(ModItems.GOLDEN_HEART.get()))
            .displayItems((parameters, output) -> {
                // Dodajemy WSZYSTKIE itemy, żeby było widać co mamy
                output.accept(ModItems.GOLDEN_HEART.get());
                output.accept(ModItems.PAINTED_GOLDEN_HEART.get());
                output.accept(ModItems.MORE_PAINTED_GOLDEN_HEART.get());
                output.accept(ModItems.BROKEN_HEART.get());
                output.accept(ModItems.HEART.get());

                output.accept(ModItems.ENCHANTED_GOLDEN_HEART.get());
                output.accept(ModItems.INCOMPLETE_HEART.get());
                output.accept(ModItems.NETHERITE_HEART.get());
            }).build());

    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }
}