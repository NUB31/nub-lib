package com.nublib.config.screen.page.section.control;

import com.nublib.config.provider.IStorageProvider;
import com.nublib.config.serialization.ISerializer;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.widget.ClickableWidget;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public abstract class Control<T> {
	protected String key;
	@Nullable
	protected ClickableWidget widget;
	protected T defaultValue;
	protected IStorageProvider storageProvider;
	protected ISerializer<T> serializer;

	protected Control(String key, T defaultValue, IStorageProvider storageProvider, ISerializer<T> serializer) {
		this.key = key;
		this.storageProvider = storageProvider;
		this.serializer = serializer;
		this.defaultValue = defaultValue;
	}

	protected abstract ClickableWidget createWidget(TextRenderer textRenderer, int x, int y, int width, int height);

	public ClickableWidget getOrCreateWidget(TextRenderer textRenderer, int x, int y, int width, int height) {
		if (widget == null) {
			widget = createWidget(textRenderer, x, y, width, height);
		}

		return getWidget(x, y, width, height).orElse(widget);
	}

	public Optional<ClickableWidget> getWidget() {
		return Optional.ofNullable(widget);
	}

	public Optional<ClickableWidget> getWidget(int x, int y, int width, int height) {
		if (widget == null) return Optional.empty();
		widget.setX(x);
		widget.setY(y);
		widget.setWidth(width);
		widget.setHeight(height);
		return Optional.of(widget);
	}

	public void apply() {
		Optional<T> value = getValue();
		value.ifPresent(v -> storageProvider.set(key, serializer.serialize(v)));
	}

	public IStorageProvider getStorageProvider() {
		return storageProvider;
	}

	protected T getProviderValue() {
		return storageProvider.get(key)
				.flatMap(serializer::parse)
				.orElse(defaultValue);
	}

	protected abstract Optional<T> getValue();
}
