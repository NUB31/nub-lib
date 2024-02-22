package com.nublib.config;

import com.nublib.config.provider.ConfigProvider;
import com.nublib.config.serialization.ISerializer;

import java.io.Serializable;

public class Option<T> implements Serializable {
	private final ConfigProvider provider;
	private final String key;
	private final T defaultValue;
	private final ISerializer<T> serializer;

	public Option(ConfigProvider provider, String key, T defaultValue, ISerializer<T> serializer) {
		this.provider = provider;
		this.key = key;
		this.defaultValue = defaultValue;
		this.serializer = serializer;

		if (provider.get(key).isEmpty()) {
			setValue(defaultValue);
		}
	}

	public String key() {
		return key;
	}

	public T value() {
		return provider.get(key)
				.flatMap(serializer::parse)
				.orElse(defaultValue);
	}

	public void setValue(T value) {
		provider.set(key, serializer.serialize(value));
	}

	public void setRawValue(String value) {
		provider.set(key, value);
	}
}