package com.nublib.test;

import com.nublib.NubLib;
import com.nublib.NubLibClient;
import com.nublib.gui.ConfigScreenBuilder;
import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;
import net.minecraft.client.gui.screen.Screen;

public class TestModMenuIntegration implements ModMenuApi {
	@Override
	public ConfigScreenFactory<Screen> getModConfigScreenFactory() {
		return parent -> new ConfigScreenBuilder(parent, NubLib.MOD_ID)
				.fromConfig(NubLibClient.CONFIG)
				.onSave(NubLibClient.CONFIG::save)
				.build();
	}
}
