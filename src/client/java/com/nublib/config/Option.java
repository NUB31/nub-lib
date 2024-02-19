package com.nublib.config;

import com.nublib.config.provider.IConfigProvider;
import com.nublib.config.serialization.ISerializer;

import java.util.function.Consumer;

public class Option<T> {
	private final IConfigProvider provider;
	private final String key;
	private final T defaultValue;
	private final ISerializer<T> serializer;
	private Consumer<T> onSet = (v) -> {
	};

	public Option(IConfigProvider provider, String key, T defaultValue, ISerializer<T> serializer, Consumer<T> onSet) {
		this.provider = provider;
		this.key = key;
		this.defaultValue = defaultValue;
		this.serializer = serializer;

		if (provider.get(key).isEmpty()) {
			provider.set(key, serializer.serialize(defaultValue));
		}

		this.onSet = onSet;
	}

	public Option(IConfigProvider provider, String key, T defaultValue, ISerializer<T> serializer) {
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
		onSet.accept(value);
	}
}