package com.nublib.config.screen.page.section.option;

import net.minecraft.client.gui.widget.ClickableWidget;
import net.minecraft.text.Text;

public interface IOption {
	ClickableWidget getWidget(int x, int y, int width, int height);

	Text getLabel();

	Text getDescription();
}
