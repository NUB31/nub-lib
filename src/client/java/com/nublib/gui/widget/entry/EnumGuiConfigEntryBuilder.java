package com.nublib.gui.widget.entry;

import com.nublib.gui.widget.custom.MultiSelectWidget;

public class EnumGuiConfigEntryBuilder<T extends Enum<T>> extends AbstractGuiConfigEntryBuilder<T> {
	private final Class<T> enumClass;

	public EnumGuiConfigEntryBuilder(String key, T defaultValue, Class<T> enumClass) {
		super(key, defaultValue);
		this.enumClass = enumClass;
	}

	@Override
	public GuiConfigEntry build() {
		MultiSelectWidget<T> widget = new MultiSelectWidget<>(0, 0, 0, defaultValue, onChange, enumClass);
		return new GuiConfigEntry(title, description, widget);
	}
}
