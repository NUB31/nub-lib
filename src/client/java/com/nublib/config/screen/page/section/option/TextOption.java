package com.nublib.config.screen.page.section.option;

import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.widget.ClickableWidget;
import net.minecraft.client.gui.widget.TextFieldWidget;
import net.minecraft.text.Text;

public class TextOption extends Option<String> {
	public TextOption(String defaultValue, Text label, Text description) {
		super(defaultValue, label, description);
	}

	public TextOption(String defaultValue, Text label) {
		super(defaultValue, label);
	}

	@Override
	public ClickableWidget createWidget(TextRenderer textRenderer, int x, int y, int width, int height) {
		TextFieldWidget widget = new TextFieldWidget(textRenderer, x, y, width, height, Text.literal(value));
		widget.setText(value);
		return widget;
	}
}
