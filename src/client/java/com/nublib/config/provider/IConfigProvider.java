package com.nublib.config.provider;

import com.nublib.config.screen.ConfigScreen;
import net.minecraft.client.gui.screen.Screen;

import java.util.HashMap;
import java.util.Optional;

public interface IConfigProvider {
	HashMap<String, String> all();

	Optional<String> get(String key);

	void set(String key, String value);

	default ConfigScreen createScreen(Screen parent) {
		return new ConfigScreen(parent, this);
	}
}
