package com.nublib.config.screen.page.section;

import net.minecraft.text.Text;

import java.util.ArrayList;
import java.util.List;

public class ConfigSection {
	private final Text label;
	private final List<ConfigOption<?>> configOptions;

	public ConfigSection(Text label) {
		this.label = label;
		configOptions = new ArrayList<>();
	}

	public ConfigSection addOption(ConfigOption<?> configOption) {
		configOptions.add(configOption);
		return this;
	}

	public Text getLabel() {
		return label;
	}

	public List<ConfigOption<?>> getOptions() {
		return configOptions;
	}
}
