package com.nublib.gui.widget.entry;

import com.nublib.gui.widget.custom.MultiSelectWidget;
import net.minecraft.client.gui.widget.ClickableWidget;

public class EnumGuiConfigEntryBuilder<T extends Enum<T>> extends AbstractGuiConfigEntryBuilder<T> {
	private final Class<T> enumClass;

	public EnumGuiConfigEntryBuilder(String key, T defaultValue, Class<T> enumClass) {
		super(key, defaultValue);
		this.enumClass = enumClass;
	}

	@Override
	public ClickableWidget createWidget() {
		return new MultiSelectWidget<>(0, 0, 0, defaultValue, onChange, enumClass);
	}
}
