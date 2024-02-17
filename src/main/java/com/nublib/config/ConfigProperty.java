package com.nublib.config;

import com.nublib.config.provider.IConfigProvider;
import com.nublib.config.serialization.ISerializer;

public class ConfigProperty<T> {
	private final IConfigProvider provider;
	private final String key;
	private final T defaultValue;
	private final ISerializer<T> serializer;

	public ConfigProperty(IConfigProvider provider, String key, T defaultValue, ISerializer<T> serializer) {
		this.provider = provider;
		this.key = key;
		this.defaultValue = defaultValue;
		this.serializer = serializer;

		if (provider.get(key).isEmpty()) {
			provider.set(key, serializer.serialize(defaultValue));
		}
	}

	public T get() {
		return provider.get(key)
				.flatMap(serializer::parse)
				.orElse(defaultValue);
	}

	public void set(T value) {
		provider.set(key, serializer.serialize(value));
	}
}