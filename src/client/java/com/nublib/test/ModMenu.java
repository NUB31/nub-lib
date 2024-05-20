package com.nublib.test;

import com.nublib.NubLibClient;
import com.nublib.gui.ConfigScreenBuilder;
import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;
import net.minecraft.text.Text;

public class ModMenu implements ModMenuApi {
	@Override
	public ConfigScreenFactory<?> getModConfigScreenFactory() {
		return screen -> new ConfigScreenBuilder(screen)
				.fromConfig(Text.literal("NubLib config"), NubLibClient.CONFIG)
				.addPage(Text.literal("UwU"), page -> page
						.addToggle("test", true, entry -> entry
								.setTitle(Text.literal("TITLE"))
						))
				.onSave(NubLibClient.CONFIG::save)
				.build();
	}
}
