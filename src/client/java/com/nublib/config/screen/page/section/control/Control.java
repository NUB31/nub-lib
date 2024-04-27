package com.nublib.config.screen.page.section.control;

import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.widget.ClickableWidget;

public abstract class Control<T> implements IControl {
	protected T value;
	protected ClickableWidget widget;

	protected Control(T defaultValue) {
		this.value = defaultValue;
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
}
