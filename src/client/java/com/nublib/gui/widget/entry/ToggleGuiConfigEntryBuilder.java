package com.nublib.gui.widget.entry;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.SimpleOption;

public class ToggleGuiConfigEntryBuilder extends AbstractGuiConfigEntryBuilder<Boolean> {
	public ToggleGuiConfigEntryBuilder(String key, Boolean defaultValue) {
		super(key, defaultValue);
	}

	@Override
	public GuiConfigEntry build() {
		SimpleOption<Boolean> option = SimpleOption.ofBoolean(key, defaultValue, onChange);
		return new GuiConfigEntry(title, description, option.createWidget(MinecraftClient.getInstance().options));
	}
}
