package com.nublib.config.screen.page.section.option;

import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.widget.TextFieldWidget;
import net.minecraft.client.gui.widget.Widget;
import net.minecraft.text.Text;

public class TextOption extends Option<String> {
	private String value;

	public TextOption(TextRenderer textRenderer) {
		super(textRenderer);
		value = "";
	}

	public TextOption(TextRenderer textRenderer, String defaultValue) {
		super(textRenderer);
		value = defaultValue;
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
	public Widget getWidget() {
		return new TextFieldWidget(textRenderer, 100, 20, Text.literal(value));
	}
}
