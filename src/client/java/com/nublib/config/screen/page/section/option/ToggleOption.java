package com.nublib.config.screen.page.section.option;

import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.widget.CheckboxWidget;
import net.minecraft.client.gui.widget.ClickableWidget;
import net.minecraft.text.Text;

public class ToggleOption extends Option<Boolean> {
	private final Text checkBoxLabel;

	public ToggleOption(Text checkBoxLabel, Boolean defaultValue, Text label, Text description) {
		super(defaultValue, label, description);
		this.checkBoxLabel = checkBoxLabel;
	}

	public ToggleOption(Text checkBoxLabel, Boolean defaultValue, Text label) {
		super(defaultValue, label);
		this.checkBoxLabel = checkBoxLabel;
	}

	public ToggleOption(Boolean defaultValue, Text label, Text description) {
		super(defaultValue, label, description);
		this.checkBoxLabel = Text.literal("Enabled");
	}

	public ToggleOption(Boolean defaultValue, Text label) {
		super(defaultValue, label);
		this.checkBoxLabel = Text.literal("Enabled");
	}

	@Override
	public ClickableWidget getWidget(TextRenderer textRenderer, int x, int y, int width, int height) {
		return CheckboxWidget
				.builder(checkBoxLabel, textRenderer)
				.pos(x, y)
				.checked(value)
				.build();
	}
}
