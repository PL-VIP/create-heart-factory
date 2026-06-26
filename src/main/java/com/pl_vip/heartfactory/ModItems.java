package com.pl_vip.heartfactory;

import com.pl_vip.heartfactory.items.GoldenHeartItem;
import com.pl_vip.heartfactory.items.IncompleteHeartItem;
import com.pl_vip.heartfactory.items.PermanentHeartItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, HeartFactory.MOD_ID);

    public static final RegistryObject<Item> GOLDEN_HEART = ITEMS.register("golden_heart", () ->
            new GoldenHeartItem(new Item.Properties()));

    public static final RegistryObject<Item> ENCHANTED_GOLDEN_HEART = ITEMS.register("enchanted_golden_heart", () ->
            new Item(new Item.Properties().rarity(Rarity.RARE).fireResistant()) {
                @Override
                public boolean isFoil(ItemStack stack) {
                    return true;
                }
            });

    public static final RegistryObject<Item> PAINTED_GOLDEN_HEART = ITEMS.register("painted_golden_heart", () ->
            new Item(new Item.Properties()));

    public static final RegistryObject<Item> MORE_PAINTED_GOLDEN_HEART = ITEMS.register("more_painted_golden_heart", () ->
            new Item(new Item.Properties()));

    public static final RegistryObject<Item> INCOMPLETE_HEART = ITEMS.register("incomplete_heart", () ->
            new IncompleteHeartItem(new Item.Properties()));

    public static final RegistryObject<Item> BROKEN_HEART = ITEMS.register("broken_heart", () ->
            new Item(new Item.Properties().rarity(Rarity.COMMON)));

    public static final RegistryObject<Item> HEART = ITEMS.register("heart", () ->
            new PermanentHeartItem(new Item.Properties().rarity(Rarity.UNCOMMON), false));

    public static final RegistryObject<Item> NETHERITE_HEART = ITEMS.register("netherite_heart", () ->
            new PermanentHeartItem(new Item.Properties().rarity(Rarity.EPIC).fireResistant(), true));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
