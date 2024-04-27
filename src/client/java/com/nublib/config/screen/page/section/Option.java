package com.nublib.config.screen.page.section;

import com.nublib.config.screen.page.section.control.IControl;
import net.minecraft.text.Text;

public class Option {
	private final IControl control;
	private final Text label;
	private final Text description;

	public Option(IControl control, Text label, Text description) {
		this.control = control;
		this.label = label;
		this.description = description;
	}

	public IControl getControl() {
		return control;
	}

	public Text getLabel() {
		return label;
	}

	public Text getDescription() {
		return description;
	}
}
