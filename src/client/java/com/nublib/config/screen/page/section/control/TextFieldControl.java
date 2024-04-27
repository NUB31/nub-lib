package com.nublib.config.screen.page.section.control;

import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.widget.ClickableWidget;
import net.minecraft.client.gui.widget.TextFieldWidget;
import net.minecraft.text.Text;

public class TextFieldControl extends Control<String> {
	public TextFieldControl(String defaultValue) {
		super(defaultValue);
	}

	@Override
	public ClickableWidget createWidget(TextRenderer textRenderer, int x, int y, int width, int height) {
		TextFieldWidget widget = new TextFieldWidget(textRenderer, x, y, width, height, Text.literal(value));
		widget.setText(value);
		return widget;
	}
}
