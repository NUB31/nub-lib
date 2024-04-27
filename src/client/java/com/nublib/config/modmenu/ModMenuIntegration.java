package com.nublib.config.modmenu;

import com.nublib.config.Config;
import com.nublib.config.screen.ConfigScreen;
import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;

public interface ModMenuIntegration extends ModMenuApi {
	Config getConfig();

	@Override
	default ConfigScreenFactory<ConfigScreen> getModConfigScreenFactory() {
		return screen -> ConfigScreen.builder(screen).generate(getConfig()).build();
	}
}
