package com.nublib.config.screen.page;

import com.nublib.config.screen.page.section.ConfigSection;
import com.nublib.config.screen.page.section.ConfigSectionBuilder;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.text.Text;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class ConfigPageBuilder {
	private final List<ConfigSection> configSections;
	private final Text title;
	private final TextRenderer textRenderer;

	public ConfigPageBuilder(TextRenderer textRenderer, Text message) {
		this.title = message;
		this.textRenderer = textRenderer;
		configSections = new ArrayList<>();
	}

	public ConfigPageBuilder addSection(Text title, Consumer<ConfigSectionBuilder> section) {
		ConfigSectionBuilder configSectionBuilder = new ConfigSectionBuilder(textRenderer, title);
		section.accept(configSectionBuilder);
		configSections.add(configSectionBuilder.build());
		return this;
	}

	public ConfigPage build() {
		return new ConfigPage(textRenderer, title, configSections);
	}
}
