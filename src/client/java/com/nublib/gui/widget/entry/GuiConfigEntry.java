package com.nublib.gui.widget.entry;

import net.minecraft.client.gui.widget.ClickableWidget;
import net.minecraft.text.Text;

public record GuiConfigEntry(
		Text title,
		Text description,
		ClickableWidget widget
) {
//	public static GuiConfigEntry toggleGuiConfigOption(Text title, Text description, String key, Boolean defaultValue, Consumer<Boolean> onChange) {
//		SimpleOption<Boolean> option = SimpleOption.ofBoolean(key, defaultValue, onChange);
//		return new GuiConfigEntry(title, description, option.createWidget(MinecraftClient.getInstance().options));
//	}
//
//	public static GuiConfigEntry stringGuiConfigOption(Text title, Text description, String key, String defaultValue, Consumer<String> onChange) {
//		TextFieldWidget widget = new TextFieldWidget(MinecraftClient.getInstance().textRenderer, 200, 20, Text.literal(key));
//		widget.setText(defaultValue);
//		widget.setChangedListener(onChange);
//		return new GuiConfigEntry(title, description, widget);
//	}
//
//	public static GuiConfigEntry rangeGuiConfigOption(Text title, Text description, String key, Integer defaultValue, Integer minValue, Integer maxValue, Consumer<Integer> onChange) {
//		SimpleOption<Integer> option = new SimpleOption<>(
//				key,
//				SimpleOption.emptyTooltip(),
//				((optionText, value) -> Text.translatable("options.generic_value", optionText, value)),
//				new SimpleOption.ValidatingIntSliderCallbacks(minValue, maxValue, true),
//				defaultValue,
//				onChange
//		);
//
//		return new GuiConfigEntry(title, description, option.createWidget(MinecraftClient.getInstance().options));
//	}
}
