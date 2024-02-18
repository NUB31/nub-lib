package com.nublib.modmenu;

import com.nublib.config.provider.IConfigProvider;
import com.nublib.config.screen.ConfigScreen;
import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;

public class DynamicScreenFactory implements ConfigScreenFactory {
	private final IConfigProvider configProvider;

	public DynamicScreenFactory(IConfigProvider configProvider) {
		this.configProvider = configProvider;
	}

	@Override
	public Screen create(Screen parent) {
		return new ConfigScreen(Text.literal("Config screen"), configProvider, parent);
	}
}
