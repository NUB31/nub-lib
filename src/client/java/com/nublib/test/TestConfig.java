package com.nublib.test;

import com.nublib.config.Config;
import com.nublib.config.annotation.ConfigOptionMetadata;
import com.nublib.config.option.ConfigOption;
import com.nublib.config.provider.StorageProvider;

public class TestConfig extends Config {
	@ConfigOptionMetadata(title = "Is stupid", description = "Determines if i am stupid or not")
	public final ConfigOption<Boolean> isStupid = ConfigOption.Boolean(storageProvider, "isStupid", true);

	public TestFeatureConfig config = new TestFeatureConfig(storageProvider);

	public TestConfig(StorageProvider storageProvider) {
		super(storageProvider);
	}
}