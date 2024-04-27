package com.nublib.test;

import com.nublib.config.Config;
import com.nublib.config.annotation.ConfigOptionMetadata;
import com.nublib.config.option.ConfigOption;
import com.nublib.config.provider.StorageProvider;
import com.nublib.config.screen.page.section.control.TextFieldControl;
import com.nublib.config.serialization.IntegerSerializer;

public class TestFeatureConfig extends Config {
	@ConfigOptionMetadata(title = "Feature enabled", description = "Determines if the feature is enabled or not")
	public final ConfigOption<Boolean> featureEnabled = ConfigOption.Boolean(storageProvider, "featureEnabled", true);

	@ConfigOptionMetadata(title = "Feature name", description = "Determines the name of the feature")
	public final ConfigOption<String> featureName = ConfigOption.String(storageProvider, "featureEnabled", "SuperDuperName");

	@ConfigOptionMetadata(title = "Feature count", description = "The amount of features")
	public final ConfigOption<Integer> featureCount = new ConfigOption<>(storageProvider, "featureCount", 1, new IntegerSerializer(), new TextFieldControl("1"));

	public TestFeatureConfig(StorageProvider storageProvider) {
		super(storageProvider);
	}
}
