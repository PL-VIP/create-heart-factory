package com.pl_vip.heartfactory;

import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.client.extensions.common.IClientFluidTypeExtensions;
import net.neoforged.neoforge.client.extensions.common.RegisterClientExtensionsEvent;

public class HeartFactoryClient {

    public static void onRegisterClientExtensions(RegisterClientExtensionsEvent event) {
        // Molten Netherite
        event.registerFluidType(new IClientFluidTypeExtensions() {
            private static final ResourceLocation STILL = ResourceLocation.withDefaultNamespace("block/lava_still");
            private static final ResourceLocation FLOW = ResourceLocation.withDefaultNamespace("block/lava_flow");
            @Override public ResourceLocation getStillTexture() { return STILL; }
            @Override public ResourceLocation getFlowingTexture() { return FLOW; }
            @Override public int getTintColor() { return 0xFF3B3B3B; }
        }, ModFluids.MOLTEN_NETHERITE_TYPE.get());
    }
}