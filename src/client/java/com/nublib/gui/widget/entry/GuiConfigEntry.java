package com.nublib.gui.widget.entry;

import net.minecraft.client.gui.widget.ClickableWidget;
import net.minecraft.text.Text;
import org.jetbrains.annotations.Nullable;

public record GuiConfigEntry(
		Text title,
		Text description,
		ClickableWidget widget,
		@Nullable Runnable resetDelegate
) {
}
