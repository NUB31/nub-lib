package com.nublib.test;

import com.nublib.NubLibClient;
import com.nublib.config.Config;
import com.nublib.config.modmenu.ModMenuIntegration;
import com.nublib.config.screen.ConfigScreen;
import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import net.minecraft.client.MinecraftClient;

public class TestModMenuIntegration implements ModMenuIntegration {
	@Override
	public Config getConfig() {
		return NubLibClient.CONFIG;
	}

	@Override
	public ConfigScreenFactory<ConfigScreen> getModConfigScreenFactory() {
		return (screen) -> TestScreen.createScreen(screen, MinecraftClient.getInstance().textRenderer);
	}
}