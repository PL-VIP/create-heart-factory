package com.pl_vip.heartfactory;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.level.material.FlowingFluid;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.client.extensions.common.IClientFluidTypeExtensions;
import net.minecraftforge.common.SoundActions;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fluids.FluidType;
import net.minecraftforge.fluids.ForgeFlowingFluid;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Consumer;

public class ModFluids {
    public static final DeferredRegister<FluidType> FLUID_TYPES = DeferredRegister.create(ForgeRegistries.Keys.FLUID_TYPES, HeartFactory.MOD_ID);
    public static final DeferredRegister<Fluid> FLUIDS = DeferredRegister.create(ForgeRegistries.FLUIDS, HeartFactory.MOD_ID);

    public static final RegistryObject<FluidType> MOLTEN_NETHERITE_TYPE = FLUID_TYPES.register("molten_netherite_type", () ->
            new FluidType(FluidType.Properties.create()
                    .lightLevel(15).density(3000).viscosity(6000).temperature(1300)
                    .sound(SoundActions.BUCKET_FILL, SoundEvents.BUCKET_FILL_LAVA)
                    .sound(SoundActions.BUCKET_EMPTY, SoundEvents.BUCKET_EMPTY_LAVA)) {
                @Override
                public void initializeClient(Consumer<IClientFluidTypeExtensions> consumer) {
                    consumer.accept(new IClientFluidTypeExtensions() {
                        private static final ResourceLocation STILL = new ResourceLocation("minecraft", "block/lava_still");
                        private static final ResourceLocation FLOW = new ResourceLocation("minecraft", "block/lava_flow");

                        @Override
                        public ResourceLocation getStillTexture() {
                            return STILL;
                        }

                        @Override
                        public ResourceLocation getFlowingTexture() {
                            return FLOW;
                        }

                        @Override
                        public int getTintColor() {
                            return 0xFF3B3B3B;
                        }
                    });
                }
            });

    public static final RegistryObject<FlowingFluid> MOLTEN_NETHERITE = FLUIDS.register("molten_netherite",
            () -> new ForgeFlowingFluid.Source(fluidProperties()));
    public static final RegistryObject<FlowingFluid> MOLTEN_NETHERITE_FLOWING = FLUIDS.register("molten_netherite_flowing",
            () -> new ForgeFlowingFluid.Flowing(fluidProperties()));

    private static ForgeFlowingFluid.Properties fluidProperties() {
        return new ForgeFlowingFluid.Properties(
                MOLTEN_NETHERITE_TYPE,
                MOLTEN_NETHERITE,
                MOLTEN_NETHERITE_FLOWING
        );
    }

    public static void register(IEventBus eventBus) {
        FLUID_TYPES.register(eventBus);
        FLUIDS.register(eventBus);
    }
}
