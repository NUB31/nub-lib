package com.nublib.config.provider.impl;

import com.nublib.config.provider.StorageProvider;

import java.util.Optional;

public class MemoryStorageProvider extends StorageProvider {
	@Override
	public Optional<String> getImpl(String key) {
		var value = config.get(key);
		return Optional.ofNullable(value);
	}

	@Override
	public void setImpl(String key, String value) {
		config.put(key, value);
	}
}
