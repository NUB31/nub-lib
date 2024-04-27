package com.nublib.config.screen.page.section.option;

import net.minecraft.client.font.TextRenderer;

public abstract class Option<T> implements IOption {
	protected final TextRenderer textRenderer;

	protected Option(TextRenderer textRenderer) {
		this.textRenderer = textRenderer;
	}

	public abstract T getValue();

	public abstract void setValue(T value);
}
