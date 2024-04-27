package com.nublib.config.screen.page.section;

import net.minecraft.text.Text;

import java.util.ArrayList;
import java.util.List;

public class ConfigSection {
	private final Text label;
	private final List<Option> options;

	public ConfigSection(Text label) {
		this.label = label;
		options = new ArrayList<>();
	}

	public ConfigSection addOption(Option option) {
		options.add(option);
		return this;
	}

	public Text getLabel() {
		return label;
	}

	public List<Option> getOptions() {
		return options;
	}
}
