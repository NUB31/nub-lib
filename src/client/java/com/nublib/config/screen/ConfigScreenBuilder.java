package com.nublib.config.screen;

import com.nublib.config.Config;
import com.nublib.config.screen.page.ConfigPage;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class ConfigScreenBuilder {
	private final List<ConfigPage> configPages;
	private final TextRenderer textRenderer;
	private final ConfigScreen configScreen;

	public ConfigScreenBuilder(TextRenderer textRenderer, Screen parent) {
		this.textRenderer = textRenderer;
		configPages = new ArrayList<>();
		configScreen = new ConfigScreen(parent, Text.literal("Options"), configPages);
	}

	public ConfigScreenBuilder addPage(Text title, Consumer<ConfigPage> page) {
		var configPage = new ConfigPage(textRenderer, title);
		page.accept(configPage);
		configPages.add(configPage);
		return this;
	}

	public ConfigScreenBuilder generate(Config config) {
		// TODO: implementation
		return this;
	}

	public ConfigScreen build() {
		return configScreen;
	}
}
