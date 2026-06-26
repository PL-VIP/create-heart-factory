package com.pl_vip.heartfactory;

import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityToken;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Mod.EventBusSubscriber(modid = HeartFactory.MOD_ID)
public class ModCapabilities {
    public static final Capability<IHeartPlayerData> HEART_DATA = CapabilityManager.get(new CapabilityToken<>() {});

    public static void register(IEventBus modBus) {
        modBus.addListener(ModCapabilities::registerCapabilities);
    }

    private static void registerCapabilities(net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent event) {
        event.register(IHeartPlayerData.class);
    }

    public static IHeartPlayerData get(Player player) {
        return player.getCapability(HEART_DATA).orElseGet(HeartPlayerData::new);
    }

    @SubscribeEvent
    public static void onAttachCapabilities(AttachCapabilitiesEvent<net.minecraft.world.entity.Entity> event) {
        if (event.getObject() instanceof Player) {
            event.addCapability(
                    new ResourceLocation(HeartFactory.MOD_ID, "heart_data"),
                    new HeartPlayerDataProvider()
            );
        }
    }

    @SubscribeEvent
    public static void onPlayerClone(PlayerEvent.Clone event) {
        event.getOriginal().getCapability(HEART_DATA).ifPresent(oldData ->
                event.getEntity().getCapability(HEART_DATA).ifPresent(newData -> {
                    newData.setHeartContainers(oldData.getHeartContainers());
                    newData.setAnarchicHearts(oldData.getAnarchicHearts());
                })
        );
    }

    private static class HeartPlayerDataProvider implements ICapabilityProvider, INBTSerializable<CompoundTag> {
        private final HeartPlayerData data = new HeartPlayerData();
        private final LazyOptional<IHeartPlayerData> optional = LazyOptional.of(() -> data);

        @Override
        public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
            return HEART_DATA.orEmpty(cap, optional);
        }

        @Override
        public CompoundTag serializeNBT() {
            return data.serializeNBT();
        }

        @Override
        public void deserializeNBT(CompoundTag nbt) {
            data.deserializeNBT(nbt);
        }
    }
}
