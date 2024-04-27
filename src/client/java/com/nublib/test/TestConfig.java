package com.nublib.test;

import com.nublib.config.Config;
import com.nublib.config.option.ConfigOption;
import com.nublib.config.provider.StorageProvider;
import com.nublib.config.screen.page.section.option.TextOption;
import com.nublib.config.screen.page.section.option.ToggleOption;
import com.nublib.config.serialization.BooleanSerializer;
import com.nublib.config.serialization.StringSerializer;
import net.minecraft.text.Text;

public class TestConfig extends Config {
	public final ConfigOption<Boolean> featureEnabled = new ConfigOption<>(storageProvider, "featureEnabled", true, new BooleanSerializer(), new ToggleOption(true, Text.literal("Feature enabled"), Text.literal("Determines if the feature should be enabled")));
	public final ConfigOption<String> featureName = new ConfigOption<>(storageProvider, "featureName", "SuperDuperName", new StringSerializer(), new TextOption("SuperDuperName", Text.literal("Feature name"), Text.empty()));

	public TestConfig(StorageProvider storageProvider) {
		super(storageProvider);
	}

	class Category1 {
		public final ConfigOption<Boolean> featureEnabled = new ConfigOption<>(storageProvider, "anotherFeatureEnabled", true, new BooleanSerializer(), new ToggleOption(true, Text.literal("Another feature enabled"), Text.literal("Determines if the other feature should be enabled")));
	}
}
