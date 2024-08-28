package com.nublib.config.provider;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public abstract class AbstractStorageProvider implements IStorageProvider {
	protected final HashMap<String, String> config = new HashMap<>();

	@Override
	public void set(String key, String value) {
		config.put(key, value);
	}

	@Override
	public Optional<String> get(String key) {
		return Optional.ofNullable(config.get(key));
	}

	@Override
	public void save() {
		save(config);
	}

	protected abstract void save(Map<String, String> options);
}