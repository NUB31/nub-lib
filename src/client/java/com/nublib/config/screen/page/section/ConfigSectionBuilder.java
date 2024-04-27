package com.nublib.config.screen.page.section;

import com.nublib.config.screen.page.section.option.IOption;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.text.Text;

import java.util.ArrayList;
import java.util.List;

public class ConfigSectionBuilder {
	private final TextRenderer textRenderer;
	private final Text message;
	private final List<IOption> options;

	public ConfigSectionBuilder(TextRenderer textRenderer, Text message) {
		this.textRenderer = textRenderer;
		this.message = message;
		options = new ArrayList<>();
	}

	public ConfigSectionBuilder addOption(IOption option) {
		options.add(option);
		return this;
	}

	public ConfigSection build() {
		return new ConfigSection(textRenderer, message, options);
	}
}
