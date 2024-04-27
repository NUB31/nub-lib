package com.nublib.test;

import com.nublib.config.screen.ConfigScreen;
import com.nublib.config.screen.page.section.option.TextOption;
import com.nublib.config.screen.page.section.option.ToggleOption;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;

public class TestScreen {
	public static ConfigScreen createScreen(Screen parent, TextRenderer textRenderer) {
		return ConfigScreen
				.builder(parent)
				.addPage(Text.literal("Page 1"), page -> page
						.addSection(Text.literal("Section 1"), section -> section
								.addOption(new TextOption(textRenderer, "", Text.literal("Test field"), Text.literal("Very description")))
								.addOption(new ToggleOption(textRenderer, false, Text.literal("Test field 2"), Text.literal("Very description 2 Very description 2 Very description 2 Very description 2 Very description 2 Very description 2 Very description 2 Very description 2 Very description 2 Very description 2Very description 2Very description 2Very description 2Very description 2Very description 2Very description 2 Very description 2 Very description 2 Very description 2")))
						)
				)
				.build();
	}
}
