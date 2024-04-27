package com.nublib.config.option;

import com.nublib.config.provider.StorageProvider;
import com.nublib.config.screen.page.section.option.IOption;
import com.nublib.config.serialization.ISerializer;

public class ConfigOption<T> extends ConfigOptionBase {
	private final T defaultValue;
	private final ISerializer<T> serializer;
	private final String key;

	public ConfigOption(StorageProvider storageProvider, String key, T defaultValue, ISerializer<T> serializer, IOption option) {
		super(storageProvider, option);
		this.defaultValue = defaultValue;
		this.serializer = serializer;
		this.key = key;

		if (storageProvider.get(key).isEmpty()) {
			set(defaultValue);
		}
	}

	public T get() {
		return storageProvider.get(key)
				.flatMap(serializer::parse)
				.orElse(defaultValue);
	}

	public void set(T value) {
		storageProvider.set(key, serializer.serialize(value));
	}
}