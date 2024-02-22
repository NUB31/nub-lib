package com.nublib.config.modmenu;

import com.nublib.config.provider.ConfigProvider;
import com.nublib.config.screen.ConfigScreen;
import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;

public interface ModMenuIntegration extends ModMenuApi {
	ConfigProvider configProvider();

	@Override
	default ConfigScreenFactory<ConfigScreen> getModConfigScreenFactory() {
		return screen -> new ConfigScreen(screen, configProvider());
	}
}
