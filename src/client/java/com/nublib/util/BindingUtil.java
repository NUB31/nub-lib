package com.nublib.util;

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.option.KeyBinding;

public class BindingUtil {
    public static void bindScreenToKey(int defaultKey, Screen screen, String translationKey, String category) {
        KeyBinding keyBinding = KeyBindingHelper.registerKeyBinding(new KeyBinding(translationKey, defaultKey, category));
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while (keyBinding.wasPressed()) {
                client.setScreen(screen);
            }
        });
    }

    public static void bindScreenToKey(int defaultKey, Screen screen, String translationKey) {
        bindScreenToKey(defaultKey, screen, translationKey, "nub-lib.name");
    }
}
