package com.nublib.config.screen.page.section.option;

import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.widget.ClickableWidget;
import net.minecraft.text.Text;

public class RangeOption extends Option<Integer> {
	protected RangeOption(Integer defaultValue, Text label, Text description) {
		super(defaultValue, label, description);
	}

	protected RangeOption(Integer defaultValue, Text label) {
		super(defaultValue, label);
	}

	@Override
	public ClickableWidget getWidget(TextRenderer textRenderer, int x, int y, int width, int height) {
		return null;
	}
}
