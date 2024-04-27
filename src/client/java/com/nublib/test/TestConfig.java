package com.nublib.test;

import com.nublib.config.Config;
import com.nublib.config.annotation.ConfigMetadata;
import com.nublib.config.option.ConfigOption;
import com.nublib.config.provider.StorageProvider;

public class TestConfig extends Config {
	@ConfigMetadata(title = "Feature enabled", description = "Determines if the feature is enabled or not")
	public final ConfigOption<Boolean> featureEnabled = ConfigOption.Boolean(storageProvider, "featureEnabled", true);

	@ConfigMetadata(title = "Feature name", description = "Determines the name of the feature")
	public final ConfigOption<String> featureName = ConfigOption.String(storageProvider, "featureEnabled", "SuperDuperName");

	@ConfigMetadata(title = "Feature count", description = "The amount of features")
	public final ConfigOption<Integer> featureCount = ConfigOption.Integer(storageProvider, "featureCount", 1);

	public TestConfig(StorageProvider storageProvider) {
		super(storageProvider);
	}
}