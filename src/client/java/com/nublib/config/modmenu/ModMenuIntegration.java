package com.nublib.config.modmenu;

import com.nublib.config.provider.IConfigProvider;
import com.nublib.config.screen.ConfigScreen;
import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;

public interface ModMenuIntegration extends ModMenuApi {
	IConfigProvider configProvider();

	@Override
	default ConfigScreenFactory<ConfigScreen> getModConfigScreenFactory() {
		return screen -> configProvider().createScreen(screen);
	}
}
