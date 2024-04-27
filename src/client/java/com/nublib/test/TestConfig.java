package com.nublib.test;

import com.nublib.config.Config;
import com.nublib.config.option.ConfigOption;
import com.nublib.config.provider.StorageProvider;
import com.nublib.config.serialization.BooleanSerializer;
import com.nublib.config.serialization.StringSerializer;

public class TestConfig extends Config {
	public final ConfigOption<Boolean> featureEnabled = new ConfigOption<>(storageProvider, "featureEnabled", true, new BooleanSerializer());
	public final ConfigOption<String> featureName = new ConfigOption<>(storageProvider, "featureName", "SuperDuperName", new StringSerializer());

	public TestConfig(StorageProvider storageProvider) {
		super(storageProvider);
	}
}
