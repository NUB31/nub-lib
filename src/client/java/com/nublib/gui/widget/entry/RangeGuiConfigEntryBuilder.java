package com.nublib.gui.widget.entry;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.SimpleOption;
import net.minecraft.text.Text;

public class RangeGuiConfigEntryBuilder extends AbstractGuiConfigEntryBuilder<Integer> {
	private final Integer minValue;
	private final Integer maxValue;

	public RangeGuiConfigEntryBuilder(String key, Integer defaultValue, Integer minValue, Integer maxValue) {
		super(key, defaultValue);
		this.minValue = minValue;
		this.maxValue = maxValue;
	}

	@Override
	public GuiConfigEntry build() {
		SimpleOption<Integer> option = new SimpleOption<>(
				key,
				SimpleOption.emptyTooltip(),
				((optionText, value) -> Text.translatable("options.generic_value", optionText, value)),
				new SimpleOption.ValidatingIntSliderCallbacks(minValue, maxValue, true),
				defaultValue,
				onChange
		);

		return new GuiConfigEntry(title, description, option.createWidget(MinecraftClient.getInstance().options));
	}
}
