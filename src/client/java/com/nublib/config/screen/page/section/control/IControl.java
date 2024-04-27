package com.nublib.config.screen.page.section.control;

import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.widget.ClickableWidget;

public interface IControl {
	ClickableWidget getWidget(TextRenderer textRenderer, int x, int y, int width, int height);
}
