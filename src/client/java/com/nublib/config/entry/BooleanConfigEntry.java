package com.nublib.config.entry;

import com.nublib.config.provider.IStorageProvider;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.text.Text;

public class BooleanConfigEntry extends ConfigEntry<Boolean> {
    public BooleanConfigEntry(IStorageProvider storageProvider, String key, Boolean defaultValue) {
        super(storageProvider, key, defaultValue);
    }

    public BooleanConfigEntry(IStorageProvider storageProvider, String key, Boolean defaultValue, KeyBinding keyBinding) {
        this(storageProvider, key, defaultValue);
        KeyBindingHelper.registerKeyBinding(keyBinding);
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (keyBinding.wasPressed()) {
                if (client.player != null) {
                    Boolean value = !get();
                    setAndSave(value);
                    client.player.sendMessage(Text.literal(String.format("Toggled %s to %s", key, value)));
                }
            }
        });
    }

    @Override
    protected String serialize(Boolean value) {
        return value.toString();
    }

    @Override
    protected Boolean parse(String value) {
        return Boolean.parseBoolean(value);
    }
}
