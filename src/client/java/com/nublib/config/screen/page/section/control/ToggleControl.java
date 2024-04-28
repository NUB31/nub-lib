package com.nublib.config.screen.page.section.control;

import com.nublib.config.provider.IStorageProvider;
import com.nublib.config.serialization.BooleanSerializer;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.widget.CheckboxWidget;
import net.minecraft.client.gui.widget.ClickableWidget;
import net.minecraft.text.Text;

public class ToggleControl extends Control<Boolean> {
	private final Text checkBoxLabel;

	public ToggleControl(Text checkBoxLabel, String key, Boolean defaultValue, IStorageProvider storageProvider) {
		super(key, defaultValue, storageProvider, new BooleanSerializer());
		this.checkBoxLabel = checkBoxLabel;
	}

	public ToggleControl(String key, Boolean defaultValue, IStorageProvider storageProvider) {
		super(key, defaultValue, storageProvider, new BooleanSerializer());
		this.checkBoxLabel = Text.literal("Enabled");
	}

	@Override
	public ClickableWidget getWidget(TextRenderer textRenderer, int x, int y, int width, int height) {
		return CheckboxWidget
				.builder(checkBoxLabel, textRenderer)
				.pos(x, y)
				.checked(getProviderValue())
				.callback((c, v) -> value = v)
				.build();
	}
}
