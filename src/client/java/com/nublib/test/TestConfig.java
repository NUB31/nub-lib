package com.nublib.test;

import com.nublib.config.Config;
import com.nublib.config.entry.ClientRangeConfigEntry;
import com.nublib.config.entry.ClientStringConfigEntry;
import com.nublib.config.entry.ClientToggleConfigEntry;
import com.nublib.config.entry.IClientConfigEntry;
import com.nublib.config.provider.IStorageProvider;
import net.minecraft.text.Text;

public class TestConfig extends Config {
	public IClientConfigEntry<String> testString = new ClientStringConfigEntry(storageProvider, "test.string", "UwU", Text.literal("Test string"), Text.literal("A string with text"));
	public IClientConfigEntry<Integer> testRange = new ClientRangeConfigEntry(storageProvider, "test.range", 4, 1, 10, Text.literal("Test range"), Text.literal("A number in a range"));
	public IClientConfigEntry<Boolean> testToggle = new ClientToggleConfigEntry(storageProvider, "test.toggle", false, Text.literal("Test toggle"), Text.literal("A Toggle button"));

	public TestConfig(IStorageProvider storageProvider) {
		super(storageProvider);
	}
}
