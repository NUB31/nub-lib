package com.nublib.example;

import com.nublib.NubLib;
import net.minecraft.client.MinecraftClient;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MinecraftClient.class)
public class ExampleMixin {
    @Inject(at = @At("HEAD"), method = "run")
    private void init(CallbackInfo info) {
        if (ExampleModClient.CONFIG.feature1Enabled.get()) {
            NubLib.LOGGER.info(ExampleModClient.CONFIG.feature1Name.get());
        }
    }
}