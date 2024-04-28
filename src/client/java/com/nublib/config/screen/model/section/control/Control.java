package com.nublib.config.screen.model.section.control;

import com.nublib.config.provider.IStorageProvider;
import com.nublib.config.serialization.ISerializer;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.widget.ClickableWidget;
import org.jetbrains.annotations.Nullable;

public abstract class Control<T> {
	protected String key;
	@Nullable
	protected ClickableWidget widget;
	protected T defaultValue;
	protected T value;
	protected IStorageProvider storageProvider;
	protected ISerializer<T> serializer;

	protected Control(String key, T defaultValue, IStorageProvider storageProvider, ISerializer<T> serializer) {
		this.key = key;
		this.storageProvider = storageProvider;
		this.serializer = serializer;
		this.defaultValue = defaultValue;
		this.value = getProviderValue();
	}

	protected abstract ClickableWidget getWidget(TextRenderer textRenderer, int x, int y, int width, int height);

	public ClickableWidget getOrCreateWidget(TextRenderer textRenderer, int x, int y, int width, int height) {
		if (widget == null) {
			widget = getWidget(textRenderer, x, y, width, height);
		}

		widget.setX(x);
		widget.setY(y);
		widget.setWidth(width);
		widget.setHeight(height);
		return widget;
	}

	public void apply() {
		storageProvider.set(key, serializer.serialize(value));
	}

	public IStorageProvider getStorageProvider() {
		return storageProvider;
	}

	protected T getProviderValue() {
		return storageProvider.get(key)
				.flatMap(serializer::parse)
				.orElse(defaultValue);
	}

	public void reset() {
		setWidgetValue(getProviderValue());
	}

	protected abstract void setWidgetValue(T value);
}
