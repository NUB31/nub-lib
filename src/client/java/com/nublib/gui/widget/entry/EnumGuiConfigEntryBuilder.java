package com.nublib.gui.widget.entry;

import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.text.Text;

import java.util.Arrays;
import java.util.List;

public class EnumGuiConfigEntryBuilder<T extends Enum<T>> extends AbstractGuiConfigEntryBuilder<T> {
	private final Class<T> enumClass;
	private T currentValue;

	public EnumGuiConfigEntryBuilder(String key, T defaultValue, Class<T> enumClass) {
		super(key, defaultValue);
		this.enumClass = enumClass;
		this.currentValue = defaultValue;
	}

	@Override
	public GuiConfigEntry build() {
		List<T> values = Arrays.asList(enumClass.getEnumConstants());

		ButtonWidget widget = ButtonWidget
				.builder(Text.literal(currentValue.name()), button -> {
					int index = values.indexOf(currentValue);

					if (index == -1 || index >= values.size() - 1) {
						currentValue = values.getFirst();
					} else {
						currentValue = values.get(index + 1);
					}

					button.setMessage(Text.translatable("options.generic_value", key, currentValue.name()));

					onChange.accept(currentValue);
				})
				.build();

		return new GuiConfigEntry(title, description, widget);
	}
}
