package com.nublib.config.option;

import com.nublib.config.provider.IStorageProvider;
import com.nublib.config.serialization.ISerializer;

public abstract class ConfigOption<T> implements IConfigOption<T> {
	private final ISerializer<T> serializer;
	protected T defaultValue;
	protected String key;
	protected IStorageProvider storageProvider;

	public ConfigOption(String key, T defaultValue, IStorageProvider storageProvider, ISerializer<T> serializer) {
		this.key = key;
		this.defaultValue = defaultValue;
		this.storageProvider = storageProvider;
		this.serializer = serializer;
	}

	@Override
	public T get() {
		return storageProvider.get(key)
				.flatMap(serializer::parse)
				.orElse(defaultValue);
	}

	@Override
	public void set(T value) {
		this.defaultValue = value;
		storageProvider.set(key, serializer.serialize(value));
	}

	@Override
	public String key() {
		return key;
	}
}
