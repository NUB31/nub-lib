package com.nublib.gui.widget.entry;

import com.nublib.gui.widget.custom.ToggleWidget;

public class ToggleGuiConfigEntryBuilder extends AbstractGuiConfigEntryBuilder<Boolean> {
	public ToggleGuiConfigEntryBuilder(String key, Boolean defaultValue) {
		super(key, defaultValue);
	}

	@Override
	public GuiConfigEntry build() {
		ToggleWidget toggleWidget = new ToggleWidget(0, 0, 0, defaultValue, onChange);
		return new GuiConfigEntry(title, description, toggleWidget);
	}
}
