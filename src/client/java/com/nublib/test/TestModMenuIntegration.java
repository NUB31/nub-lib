package com.nublib.test;

import com.nublib.NubLibClient;
import com.nublib.config.Config;
import com.nublib.config.modmenu.ModMenuIntegration;

public class TestModMenuIntegration implements ModMenuIntegration {
	@Override
	public Config getConfig() {
		return NubLibClient.CONFIG;
	}

//	@Override
//	public ConfigScreenFactory<ConfigScreen> getModConfigScreenFactory() {
//		return parent -> new ConfigScreen(parent)
//				.addPage(Text.literal("Page 1"), page -> page
//						.addSection(Text.literal("Section 1"), section -> section
//								.addOption(new TextOption("", Text.literal("Test field"), Text.literal("Very description")))
//								.addOption(new ToggleOption(false, Text.literal("Test field 2"), Text.literal("Very description 2")))
//						)
//				)
//				.addPage(Text.literal("Page 2"), page -> page
//						.addSection(Text.literal("test"), section -> section
//								.addOption(new ToggleOption(false, Text.literal("1")))
//								.addOption(new ToggleOption(true, Text.literal("2"), Text.literal("Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.")))
//								.addOption(new ToggleOption(false, Text.literal("3")))
//								.addOption(new ToggleOption(true, Text.literal("4")))
//						)
//				);
//	}
}