package com.nublib.test;

import com.nublib.NubLibClient;
import com.nublib.config.Config;
import com.nublib.config.modmenu.ModMenuIntegration;
import com.nublib.config.screen.ConfigScreen;
import com.nublib.config.screen.model.section.Option;
import com.nublib.config.screen.model.section.control.TextFieldControl;
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
								.addOption(new Option<>(new TextFieldControl("test1", "test", NubLibClient.STORAGE_PROVIDER), Text.literal("test title 1"), Text.empty()))
								.addOption(new Option<>(new TextFieldControl("test2", "test", NubLibClient.STORAGE_PROVIDER), Text.literal("test title 2"), Text.empty()))
								.addOption(new Option<>(new TextFieldControl("test3", "test", NubLibClient.STORAGE_PROVIDER), Text.literal("test title 3"), Text.empty()))
								.addOption(new Option<>(new TextFieldControl("test4", "test", NubLibClient.STORAGE_PROVIDER), Text.literal("test title 4"), Text.empty()))
						))
				.addPage(Text.literal("Page 2"), page -> page
						.addSection(Text.literal("Section 1"), section -> section
								.addOption(new Option<>(new TextFieldControl("test5", "test", NubLibClient.STORAGE_PROVIDER), Text.literal("test title 5"), Text.empty()))
								.addOption(new Option<>(new TextFieldControl("test6", "test", NubLibClient.STORAGE_PROVIDER), Text.literal("test title 6"), Text.empty()))
								.addOption(new Option<>(new TextFieldControl("test7", "test", NubLibClient.STORAGE_PROVIDER), Text.literal("test title 7"), Text.empty()))
								.addOption(new Option<>(new TextFieldControl("test8", "test", NubLibClient.STORAGE_PROVIDER), Text.literal("test title 8"), Text.empty()))
						));
	}
}