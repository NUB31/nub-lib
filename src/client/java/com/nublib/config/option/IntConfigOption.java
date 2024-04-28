package com.nublib.config.option;

import com.nublib.config.provider.IStorageProvider;
import com.nublib.config.serialization.IntegerSerializer;

public class IntConfigOption extends ConfigOption<Integer> {
	public IntConfigOption(String key, Integer defaultValue, IStorageProvider storageProvider) {
		super(key, defaultValue, storageProvider, new IntegerSerializer());
	}
}
