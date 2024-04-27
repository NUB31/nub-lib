package com.nublib.config.screen.page.section.option;

import net.minecraft.text.Text;

public abstract class Option<T> implements IOption {
	protected T value;
	protected Text label;
	protected Text description;

	protected Option(T defaultValue, Text label, Text description) {
		this.value = defaultValue;
		this.label = label;
		this.description = description;
	}

	protected Option(T defaultValue, Text label) {
		this.value = defaultValue;
		this.label = label;
		this.description = Text.empty();
	}

	@Override
	public Text getLabel() {
		return label;
	}

	@Override
	public Text getDescription() {
		return description;
	}

	public T getValue() {
		return value;
	}

	public void setValue(T value) {
		this.value = value;
	}
}
