package com.nublib.config.screen.page.section.control;

import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.widget.CheckboxWidget;
import net.minecraft.client.gui.widget.ClickableWidget;
import net.minecraft.text.Text;

public class ToggleControl extends Control<Boolean> {
	private final Text checkBoxLabel;

	public ToggleControl(Text checkBoxLabel, Boolean defaultValue) {
		super(defaultValue);
		this.checkBoxLabel = checkBoxLabel;
	}

	public ToggleControl(Boolean defaultValue) {
		super(defaultValue);
		this.checkBoxLabel = Text.literal("Enabled");
	}

	@Override
	public ClickableWidget createWidget(TextRenderer textRenderer, int x, int y, int width, int height) {
		return CheckboxWidget
				.builder(checkBoxLabel, textRenderer)
				.pos(x, y)
				.checked(value)
				.build();
	}
}
