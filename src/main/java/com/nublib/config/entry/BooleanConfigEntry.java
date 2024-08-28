package com.nublib.config.entry;

import com.nublib.config.provider.IStorageProvider;

public class BooleanConfigEntry extends AbstractConfigEntry<Boolean> {
	public BooleanConfigEntry(IStorageProvider storageProvider, String key, Boolean defaultValue) {
		super(storageProvider, key, defaultValue);
	}

	@Override
	protected String serialize(Boolean value) {
		return value.toString();
	}

	@Override
	protected Boolean parse(String value) {
		return Boolean.parseBoolean(value);
	}
}
