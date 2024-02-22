package com.nublib.config.provider;

import java.util.Optional;

public class MemoryConfigProvider extends ConfigProvider {
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
