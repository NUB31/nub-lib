package com.nublib.util;

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.option.KeyBinding;

import java.util.function.Supplier;

public class BindingUtil {
    public static void bindScreenToKey(int defaultKey, Supplier<Screen> screenSupplier, String translationKey, String category) {
        KeyBinding keyBinding = KeyBindingHelper.registerKeyBinding(new KeyBinding(translationKey, defaultKey, category));
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (keyBinding.wasPressed()) {
                client.setScreen(screenSupplier.get());
            }
        });
    }

    public static void bindScreenToKey(int defaultKey, Supplier<Screen> screenSupplier, String translationKey) {
        bindScreenToKey(defaultKey, screenSupplier, translationKey, "nub-lib.name");
    }
}
