package com.nublib.config.screen.page;

import com.nublib.config.screen.page.section.ConfigSection;
import net.minecraft.text.Text;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class ConfigPage {
	private final List<ConfigSection> configSections;
	private final Text label;

	public ConfigPage(Text label) {
		this.label = label;
		configSections = new ArrayList<>();
	}

	public ConfigPage addSection(Text title, Consumer<ConfigSection> section) {
		ConfigSection configSection = new ConfigSection(title);
		section.accept(configSection);
		configSections.add(configSection);
		return this;
	}

	public Text getLabel() {
		return label;
	}

	public List<ConfigSection> getConfigSections() {
		return configSections;
	}
}
