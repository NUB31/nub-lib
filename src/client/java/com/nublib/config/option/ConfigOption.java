package com.nublib.config.option;

import com.nublib.config.provider.StorageProvider;
import com.nublib.config.serialization.ISerializer;

public class ConfigOption<T> {
	private final T defaultValue;
	private final ISerializer<T> serializer;
	private final StorageProvider storageProvider;
	private final String key;

	public ConfigOption(StorageProvider storageProvider, String key, T defaultValue, ISerializer<T> serializer) {
		this.defaultValue = defaultValue;
		this.serializer = serializer;
		this.storageProvider = storageProvider;
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