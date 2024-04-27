package com.nublib.config.screen.page.section;

import com.nublib.config.screen.page.section.option.IOption;
import net.minecraft.text.Text;

import java.util.ArrayList;
import java.util.List;

public class ConfigSection {
	private final Text label;
	private final List<IOption> options;

	public ConfigSection(Text label) {
		this.label = label;
		options = new ArrayList<>();
	}

	public ConfigSection addOption(IOption option) {
		options.add(option);
		return this;
	}

	public Text getLabel() {
		return label;
	}

	public List<IOption> getOptions() {
		return options;
	}
}
