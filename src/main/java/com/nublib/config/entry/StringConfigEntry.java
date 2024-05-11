package com.nublib.config.entry;

import com.nublib.config.provider.IStorageProvider;

public class StringConfigEntry extends AbstractConfigEntry<String> {
	public StringConfigEntry(IStorageProvider storageProvider, String key, String defaultValue) {
		super(storageProvider, key, defaultValue);
	}

	@Override
	protected String serialize(String value) {
		return value;
	}

	@Override
	protected String parse(String value) {
		return value;
	}
}
