package com.pl_vip.heartfactory;

import com.pl_vip.heartfactory.items.GoldenHeartItem;
import com.pl_vip.heartfactory.items.IncompleteHeartItem;
import com.pl_vip.heartfactory.items.PermanentHeartItem;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(HeartFactory.MOD_ID);

    // 1. Złote Serce (Start)
    public static final DeferredItem<Item> GOLDEN_HEART = ITEMS.register("golden_heart", () ->
            new GoldenHeartItem(new Item.Properties()));

    // 2. Enchanted Golden Heart (Błyszczące)
    public static final DeferredItem<Item> ENCHANTED_GOLDEN_HEART = ITEMS.register("enchanted_golden_heart", () ->
            new Item(new Item.Properties()
                    .rarity(Rarity.RARE)
                    .fireResistant()
                    .component(DataComponents.ENCHANTMENT_GLINT_OVERRIDE, true)));

    // 3. Painted Golden Heart (Wizualny etap malowania)
    public static final DeferredItem<Item> PAINTED_GOLDEN_HEART = ITEMS.register("painted_golden_heart", () ->
            new Item(new Item.Properties()));

    // 4. More Painted Golden Heart (Wynik malowania / Start dla Heart)
    public static final DeferredItem<Item> MORE_PAINTED_GOLDEN_HEART = ITEMS.register("more_painted_golden_heart", () ->
            new Item(new Item.Properties()));

    // 5. Incomplete Heart (Wizualny etap dla Netherite Heart)
    public static final DeferredItem<Item> INCOMPLETE_HEART = ITEMS.register("incomplete_heart", () ->
            new IncompleteHeartItem(new Item.Properties()));

    // 6. Broken Heart (Porażka)
    public static final DeferredItem<Item> BROKEN_HEART = ITEMS.register("broken_heart", () ->
            new Item(new Item.Properties().rarity(Rarity.COMMON)));

    // 7. Heart (Gotowe, zwykłe)
    public static final DeferredItem<Item> HEART = ITEMS.register("heart", () ->
            new PermanentHeartItem(new Item.Properties().rarity(Rarity.UNCOMMON), false));

    // 8. Netherite Heart (Gotowe, epickie)
    public static final DeferredItem<Item> NETHERITE_HEART = ITEMS.register("netherite_heart", () ->
            new PermanentHeartItem(new Item.Properties().rarity(Rarity.EPIC).fireResistant(), true));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}