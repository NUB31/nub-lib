package com.nublib.test;

import com.nublib.NubLib;
import com.nublib.NubLibClient;
import com.nublib.gui.ConfigScreenBuilder;
import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;

public class TestModMenuIntegration implements ModMenuApi {
	@Override
	public ConfigScreenFactory<Screen> getModConfigScreenFactory() {
		return parent -> new ConfigScreenBuilder(parent, NubLib.MOD_ID)
				.addPage(Text.literal("Test"), page -> page
						.addRange("test.range", NubLibClient.CONFIG.testRange.get(), 1, 10, builder -> builder
								.onChange(NubLibClient.CONFIG.testRange::set)
								.setTitle(Text.literal("Test range"))
								.setDescription(Text.literal("Test description that is somewhat short")))
						.addString("test.string", NubLibClient.CONFIG.testString.get(), builder -> builder
								.onChange(NubLibClient.CONFIG.testString::set)
								.setTitle(Text.literal("Test string"))
								.setDescription(Text.literal("Test description that is slightly longer than the range description")))
						.addToggle("test.string", NubLibClient.CONFIG.testToggle.get(), builder -> builder
								.onChange(NubLibClient.CONFIG.testToggle::set)
								.setTitle(Text.literal("Test toggle"))
								.setDescription(Text.literal("Test description that is quite a bit longer than the range description and slightly longer than the string description")))
				)
				.fromConfig(Text.literal("Config"), NubLibClient.CONFIG)
				.onSave(NubLibClient.CONFIG::save)
				.build();
	}
}
