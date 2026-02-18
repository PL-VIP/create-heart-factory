package com.pl_vip.heartfactory;

import com.mojang.serialization.Codec;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.attachment.AttachmentType;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

import java.util.function.Supplier;

public class ModAttachments {
    public static final DeferredRegister<AttachmentType<?>> ATTACHMENT_TYPES = DeferredRegister.create(NeoForgeRegistries.ATTACHMENT_TYPES, HeartFactory.MOD_ID);

    // Ilość zjedzonych serc zwykłych
    public static final Supplier<AttachmentType<Integer>> HEART_CONTAINERS = ATTACHMENT_TYPES.register("heart_containers", () ->
            AttachmentType.builder(() -> 0).serialize(Codec.INT).copyOnDeath().build());

    // Ilość zjedzonych serc anarchicznych
    public static final Supplier<AttachmentType<Integer>> ANARCHIC_HEARTS = ATTACHMENT_TYPES.register("anarchic_hearts", () ->
            AttachmentType.builder(() -> 0).serialize(Codec.INT).copyOnDeath().build());

    // Źródło absorpcji (0=Nic, 1=Jabłko, 2=Kox)
    public static final Supplier<AttachmentType<Integer>> HEART_SOURCE = ATTACHMENT_TYPES.register("heart_source", () ->
            AttachmentType.builder(() -> 0).serialize(Codec.INT).build());

    public static void register(IEventBus eventBus) {
        ATTACHMENT_TYPES.register(eventBus);
    }
}