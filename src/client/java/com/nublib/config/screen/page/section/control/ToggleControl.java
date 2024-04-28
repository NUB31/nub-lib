package com.nublib.config.screen.page.section.control;

import com.nublib.config.provider.IStorageProvider;
import com.nublib.config.serialization.BooleanSerializer;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.widget.CheckboxWidget;
import net.minecraft.client.gui.widget.ClickableWidget;
import net.minecraft.text.Text;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public class ToggleControl extends Control<Boolean> {
	private final Text checkBoxLabel;
	@Nullable
	private CheckboxWidget widget;

	public ToggleControl(Text checkBoxLabel, String key, Boolean defaultValue, IStorageProvider storageProvider) {
		super(key, defaultValue, storageProvider, new BooleanSerializer());
		this.checkBoxLabel = checkBoxLabel;
	}

	public ToggleControl(String key, Boolean defaultValue, IStorageProvider storageProvider) {
		super(key, defaultValue, storageProvider, new BooleanSerializer());
		this.checkBoxLabel = Text.literal("Enabled");
	}

	@Override
	public ClickableWidget createWidget(TextRenderer textRenderer, int x, int y, int width, int height) {
		widget = CheckboxWidget
				.builder(checkBoxLabel, textRenderer)
				.pos(x, y)
				.checked(getProviderValue())
				.build();

		return widget;
	}

	@Override
	protected Optional<Boolean> getValue() {
		if (widget == null) return Optional.empty();
		return Optional.of(widget.isChecked());
	}
}
