package com.nublib.config.screen.model.section.control;

import com.nublib.config.provider.IStorageProvider;
import com.nublib.config.serialization.StringSerializer;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.widget.ClickableWidget;
import net.minecraft.client.gui.widget.TextFieldWidget;
import net.minecraft.text.Text;
import org.jetbrains.annotations.Nullable;

public class TextFieldControl extends Control<String> {
	@Nullable TextFieldWidget textField;

	public TextFieldControl(String key, String defaultValue, IStorageProvider storageProvider) {
		super(key, defaultValue, storageProvider, new StringSerializer());
	}

	@Override
	public ClickableWidget getWidget(TextRenderer textRenderer, int x, int y, int width, int height) {
		textField = new TextFieldWidget(textRenderer, x, y, width, height, Text.literal(value));
		textField.setChangedListener(v -> value = v);
		textField.setText(value);
		return textField;
	}

	@Override
	protected void setWidgetValue(String value) {
		if (textField == null) return;
		textField.setText(value);
	}
}
