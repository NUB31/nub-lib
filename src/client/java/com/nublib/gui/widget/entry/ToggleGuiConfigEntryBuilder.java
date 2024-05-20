package com.nublib.gui.widget.entry;

Config pagesRemoveConfig pagesRemoveConfig pagesRemoveimport net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.screen.ScreenTexts;

public class ToggleGuiConfigEntryBuilder extends AbstractGuiConfigEntryBuilder<Boolean> {
	private Boolean currentValue;

	public ToggleGuiConfigEntryBuilder(String key, Boolean defaultValue) {
		super(key, defaultValue);
		currentValue = defaultValue;
	}

	@Override
	public GuiConfigEntry build() {
		ButtonWidget buttonWidget = ButtonWidget.builder(ScreenTexts.onOrOff(currentValue), button -> {
			currentValue = !currentValue;
			onChange.accept(currentValue);
			button.setMessage(ScreenTexts.onOrOff(currentValue));
		}).build();

		return new GuiConfigEntry(title, description, buttonWidget);
	}
}
