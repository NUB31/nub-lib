package com.nublib.modmenu;

import com.nublib.NubLibClient;
import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;

public class ModMenu implements ModMenuApi {
	@Override
	public ConfigScreenFactory<?> getModConfigScreenFactory() {
		return NubLibClient.CONFIG_SCREEN_FACTORY;
	}
}

