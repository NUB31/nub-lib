package com.nublib.config.screen.page.section.option;

import net.minecraft.client.font.TextRenderer;
import net.minecraft.text.Text;

public abstract class Option<T> implements IOption {
	protected final TextRenderer textRenderer;
	protected T value;
	protected Text label;
	protected Text description;

	protected Option(TextRenderer textRenderer, T defaultValue, Text label, Text description) {
		this.textRenderer = textRenderer;
		this.value = defaultValue;
		this.label = label;
		this.description = description;
	}

	@Override
	public Text getLabel() {
		return label;
	}

	@Override
	public Text getDescription() {
		return description;
	}

	public abstract T getValue();

	public abstract void setValue(T value);
}
