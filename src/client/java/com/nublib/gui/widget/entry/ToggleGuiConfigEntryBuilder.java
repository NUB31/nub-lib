package com.nublib.gui.widget.entry;

import com.nublib.gui.widget.custom.ToggleWidget;
import net.minecraft.screen.ScreenTexts;

public class ToggleGuiConfigEntryBuilder extends AbstractGuiConfigEntryBuilder<Boolean> {
	private final Boolean currentValue;

	public ToggleGuiConfigEntryBuilder(String key, Boolean defaultValue) {
		super(key, defaultValue);
		currentValue = defaultValue;
	}

	@Override
	public GuiConfigEntry build() {
//		ButtonWidget buttonWidget = ButtonWidget.builder(ScreenTexts.onOrOff(currentValue), button -> {
//			currentValue = !currentValue;
//			onChange.accept(currentValue);
//			button.setMessage(ScreenTexts.onOrOff(currentValue));
//		}).build();

		ToggleWidget toggleWidget1 = new ToggleWidget(0, 0, 0, ScreenTexts.onOrOff(currentValue), defaultValue, onChange);

		return new GuiConfigEntry(title, description, toggleWidget1);
	}
}
