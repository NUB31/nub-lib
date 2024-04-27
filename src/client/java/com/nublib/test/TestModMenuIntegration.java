package com.nublib.test;

import com.nublib.NubLibClient;
import com.nublib.config.Config;
import com.nublib.config.modmenu.ModMenuIntegration;
import com.nublib.config.screen.ConfigScreen;
import com.nublib.config.screen.page.section.option.TextOption;
import com.nublib.config.screen.page.section.option.ToggleOption;
import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import net.minecraft.text.Text;

public class TestModMenuIntegration implements ModMenuIntegration {
	@Override
	public Config getConfig() {
		return NubLibClient.CONFIG;
	}

	@Override
	public ConfigScreenFactory<ConfigScreen> getModConfigScreenFactory() {
		return parent -> new ConfigScreen(parent)
				.addPage(Text.literal("Page 1"), page -> page
						.addSection(Text.literal("Section 1"), section -> section
								.addOption(new TextOption("", Text.literal("Test field"), Text.literal("Very description")))
								.addOption(new ToggleOption(false, Text.literal("Test field 2"), Text.literal("Very description 2")))
						)
				);
	}
}