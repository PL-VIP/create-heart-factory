package com.pl_vip.heartfactory;

import net.minecraft.core.registries.Registries;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.level.material.FlowingFluid;
import net.minecraft.world.level.material.Fluid;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.fluids.BaseFlowingFluid;
import net.neoforged.neoforge.fluids.FluidType;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

public class ModFluids {
    public static final DeferredRegister<FluidType> FLUID_TYPES = DeferredRegister.create(NeoForgeRegistries.FLUID_TYPES, HeartFactory.MOD_ID);
    public static final DeferredRegister<Fluid> FLUIDS = DeferredRegister.create(Registries.FLUID, HeartFactory.MOD_ID);

    // --- MOLTEN NETHERITE ---
    public static final DeferredHolder<FluidType, FluidType> MOLTEN_NETHERITE_TYPE = FLUID_TYPES.register("molten_netherite_type", () ->
            new FluidType(FluidType.Properties.create()
                    .lightLevel(15).density(3000).viscosity(6000).temperature(1300)
                    .sound(net.neoforged.neoforge.common.SoundActions.BUCKET_FILL, SoundEvents.BUCKET_FILL_LAVA)
                    .sound(net.neoforged.neoforge.common.SoundActions.BUCKET_EMPTY, SoundEvents.BUCKET_EMPTY_LAVA)));

    private static final BaseFlowingFluid.Properties NETHERITE_PROPERTIES = new BaseFlowingFluid.Properties(
            MOLTEN_NETHERITE_TYPE,
            () -> ModFluids.MOLTEN_NETHERITE.get(),
            () -> ModFluids.MOLTEN_NETHERITE_FLOWING.get()
    );

    public static final DeferredHolder<Fluid, FlowingFluid> MOLTEN_NETHERITE = FLUIDS.register("molten_netherite", () -> new BaseFlowingFluid.Source(NETHERITE_PROPERTIES));
    public static final DeferredHolder<Fluid, FlowingFluid> MOLTEN_NETHERITE_FLOWING = FLUIDS.register("molten_netherite_flowing", () -> new BaseFlowingFluid.Flowing(NETHERITE_PROPERTIES));

    public static void register(IEventBus eventBus) {
        FLUID_TYPES.register(eventBus);
        FLUIDS.register(eventBus);
    }
}