package com.nublib.config.screen.page.section.option;

import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.widget.ClickableWidget;
import net.minecraft.text.Text;

public abstract class Option<T> implements IOption {
	protected T value;
	protected Text label;
	protected Text description;
	protected ClickableWidget widget;

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

	protected abstract ClickableWidget createWidget(TextRenderer textRenderer, int x, int y, int width, int height);

	@Override
	public ClickableWidget getWidget(TextRenderer textRenderer, int x, int y, int width, int height) {
		if (widget == null) {
			widget = createWidget(textRenderer, x, y, width, height);
		}

		widget.setX(x);
		widget.setY(y);
		widget.setWidth(width);
		widget.setHeight(height);
		return widget;
	}

	@Override
	public Text getLabel() {
		return label;
	}

	@Override
	public Text getDescription() {
		return description;
	}
}
