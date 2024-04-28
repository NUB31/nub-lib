package com.nublib.config.screen.model.section;

import com.nublib.config.screen.model.section.control.Control;
import net.minecraft.text.Text;

public class Option<T> {
	private final Control<T> control;
	private final Text label;
	private final Text description;

	public Option(Control<T> control, Text label, Text description) {
		this.control = control;
		this.label = label;
		this.description = description;
	}

	public Control<T> getControl() {
		return control;
	}

	public Text getLabel() {
		return label;
	}

	public Text getDescription() {
		return description;
	}
}
