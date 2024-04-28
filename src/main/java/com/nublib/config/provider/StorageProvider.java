package com.nublib.config.provider;

import java.util.HashMap;
import java.util.Optional;

public abstract class StorageProvider implements IStorageProvider {
	protected final HashMap<String, String> config = new HashMap<>();

	@Override
	public Optional<String> get(String key) {
		var value = config.get(key);
		return Optional.ofNullable(value);
	}

	@Override
	public void set(String key, String value) {
		config.put(key, value);
	}
}