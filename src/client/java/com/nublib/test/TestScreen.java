package com.nublib.test;

import com.nublib.config.screen.ConfigScreen;
import com.nublib.config.screen.page.section.option.TextOption;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;

public class TestScreen {
	public static ConfigScreen createScreen(Screen parent, TextRenderer textRenderer) {
		return ConfigScreen
				.builder(parent)
				.addPage(Text.literal("Page 1"), page -> page
						.addSection(Text.literal("Section 1"), section -> section
								.addOption(new TextOption(textRenderer))
						)
				)
				.build();
	}
}
