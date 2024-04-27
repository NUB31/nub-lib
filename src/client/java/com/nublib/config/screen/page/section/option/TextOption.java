package com.nublib.config.screen.page.section.option;

import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.widget.ClickableWidget;
import net.minecraft.client.gui.widget.TextFieldWidget;
import net.minecraft.text.Text;

public class TextOption extends Option<String> {
	public TextOption(TextRenderer textRenderer, String defaultValue, Text label, Text description) {
		super(textRenderer, defaultValue, label, description);
	}

	@Override
	public String getValue() {
		return value;
	}

	@Override
	public void setValue(String value) {
		this.value = value;
	}

	@Override
	public ClickableWidget getWidget(int x, int y, int width, int height) {
		return new TextFieldWidget(textRenderer, x, y, width, height, Text.literal(value));
	}
}
