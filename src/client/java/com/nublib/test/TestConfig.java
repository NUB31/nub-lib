package com.nublib.test;

import com.nublib.config.Config;
import com.nublib.config.entry.ClientToggleConfigEntry;
import com.nublib.config.entry.IClientConfigEntry;
import com.nublib.config.provider.IStorageProvider;
import net.minecraft.text.Text;

public class TestConfig extends Config {

	public IClientConfigEntry<Boolean> FEATURE_ENABLED = new ClientToggleConfigEntry(storageProvider, "featureEnabled", false, Text.literal("Feature 1 enabled"), Text.literal("Determines if feature 1 is enabled"));
	public IClientConfigEntry<Boolean> FEATURE2_ENABLED = new ClientToggleConfigEntry(storageProvider, "feature2Enabled", false, Text.literal("Feature 2 enabled"), Text.literal("Determines if feature 2 is enabled"));

	public TestConfig(IStorageProvider storageProvider) {
		super(storageProvider);
	}
}
