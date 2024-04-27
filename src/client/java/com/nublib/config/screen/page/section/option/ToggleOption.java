package com.nublib.config.screen.page.section.option;

import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.widget.CheckboxWidget;
import net.minecraft.client.gui.widget.ClickableWidget;
import net.minecraft.text.Text;

public class ToggleOption extends Option<Boolean> {
	public ToggleOption(TextRenderer textRenderer, Boolean defaultValue, Text label, Text description) {
		super(textRenderer, defaultValue, label, description);
	}

	@Override
	public Boolean getValue() {
		return value;
	}

	@Override
	public void setValue(Boolean value) {
		this.value = value;
	}

	@Override
	public ClickableWidget getWidget(int x, int y, int width, int height) {
		return CheckboxWidget
				.builder(Text.literal("dfklgdkfjlg"), textRenderer)
				.pos(x, y)
				.checked(value)
				.build();
	}
}
