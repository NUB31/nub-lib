package com.nublib.gui.widget.entry;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.widget.ClickableWidget;
import net.minecraft.client.gui.widget.TextFieldWidget;

public class StringGuiConfigEntryBuilder extends AbstractGuiConfigEntryBuilder<String> {
	public StringGuiConfigEntryBuilder(String key, String defaultValue) {
		super(key, defaultValue);
	}

	@Override
	public ClickableWidget createWidget() {
		TextFieldWidget widget = new TextFieldWidget(MinecraftClient.getInstance().textRenderer, 200, 20, title);
		widget.setText(defaultValue);
		widget.setChangedListener(onChange);
		return widget;
	}
}
