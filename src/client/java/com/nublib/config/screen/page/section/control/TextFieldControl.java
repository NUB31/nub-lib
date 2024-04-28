package com.nublib.config.screen.page.section.control;

import com.nublib.config.provider.IStorageProvider;
import com.nublib.config.serialization.StringSerializer;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.widget.ClickableWidget;
import net.minecraft.client.gui.widget.TextFieldWidget;
import net.minecraft.text.Text;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public class TextFieldControl extends Control<String> {
	@Nullable TextFieldWidget widget;

	public TextFieldControl(String key, String defaultValue, IStorageProvider storageProvider) {
		super(key, defaultValue, storageProvider, new StringSerializer());
	}

	@Override
	public ClickableWidget createWidget(TextRenderer textRenderer, int x, int y, int width, int height) {
		widget = new TextFieldWidget(textRenderer, x, y, width, height, Text.literal(getProviderValue()));
		widget.setText(getProviderValue());
		return widget;
	}

	@Override
	protected Optional<String> getValue() {
		if (widget == null) return Optional.empty();
		return Optional.of(widget.getText());
	}
}
