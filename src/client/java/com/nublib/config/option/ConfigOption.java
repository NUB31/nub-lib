package com.nublib.config.option;

import com.nublib.NubLib;
import com.nublib.config.annotation.ConfigMetadata;
import com.nublib.config.provider.StorageProvider;
import com.nublib.config.screen.page.section.Option;
import com.nublib.config.screen.page.section.control.IControl;
import com.nublib.config.screen.page.section.control.TextFieldControl;
import com.nublib.config.screen.page.section.control.ToggleControl;
import com.nublib.config.serialization.BooleanSerializer;
import com.nublib.config.serialization.ISerializer;
import com.nublib.config.serialization.IntegerSerializer;
import com.nublib.config.serialization.StringSerializer;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.narration.NarrationMessageBuilder;
import net.minecraft.client.gui.widget.ClickableWidget;
import net.minecraft.text.Text;

import java.util.Arrays;

public class ConfigOption<T> {
	private final StorageProvider storageProvider;
	private final T defaultValue;
	private final ISerializer<T> serializer;
	private final String key;
	private final IControl control;

	public ConfigOption(StorageProvider storageProvider, String key, T defaultValue, ISerializer<T> serializer, IControl control) {
		this.storageProvider = storageProvider;
		this.defaultValue = defaultValue;
		this.serializer = serializer;
		this.key = key;
		this.control = control;

		if (storageProvider.get(key).isEmpty()) {
			set(defaultValue);
		}
	}

	public ConfigOption(StorageProvider storageProvider, String key, T defaultValue, ISerializer<T> serializer) {
		this.storageProvider = storageProvider;
		this.defaultValue = defaultValue;
		this.serializer = serializer;
		this.key = key;
		this.control = (textRenderer, x, y, width, height) -> new ClickableWidget(x, y, width, height, Text.empty()) {
			@Override
			protected void renderWidget(DrawContext context, int mouseX, int mouseY, float delta) {
			}

			@Override
			protected void appendClickableNarrations(NarrationMessageBuilder builder) {
			}
		};

		if (storageProvider.get(key).isEmpty()) {
			set(defaultValue);
		}
	}

	public static ConfigOption<String> String(StorageProvider storageProvider, String key, String defaultValue) {
		return new ConfigOption<>(storageProvider, key, defaultValue, new StringSerializer(), new TextFieldControl(defaultValue));
	}

	public static ConfigOption<Boolean> Boolean(StorageProvider storageProvider, String key, Boolean defaultValue) {
		return new ConfigOption<>(storageProvider, key, defaultValue, new BooleanSerializer(), new ToggleControl(defaultValue));
	}

	public static ConfigOption<Integer> Integer(StorageProvider storageProvider, String key, Integer defaultValue) {
		return new ConfigOption<>(storageProvider, key, defaultValue, new IntegerSerializer(), new TextFieldControl(defaultValue.toString()));
	}

	public T get() {
		return storageProvider.get(key)
				.flatMap(serializer::parse)
				.orElse(defaultValue);
	}

	public Option getOption() {
		Arrays.stream(this.getClass().getAnnotations()).toList().forEach(x -> NubLib.LOGGER.info(x.toString()));

		ConfigMetadata annotation = this.getClass().getAnnotation(ConfigMetadata.class);
		Text description = annotation != null ? Text.literal(annotation.description()) : Text.empty();
		Text title = annotation != null ? Text.literal(annotation.title()) : Text.empty();
		return new Option(control, title, description);
	}

	public void set(T value) {
		storageProvider.set(key, serializer.serialize(value));
	}
}