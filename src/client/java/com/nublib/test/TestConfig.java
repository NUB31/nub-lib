package com.nublib.test;

import com.nublib.config.Config;
import com.nublib.config.entry.*;
import com.nublib.config.provider.IStorageProvider;
import net.minecraft.text.Text;

public class TestConfig extends Config {
	public IClientConfigEntry<String> testString = new ClientStringConfigEntry(storageProvider, "test.string", "UwU", Text.literal("Test string"), Text.literal("A string with text"));
	public IClientConfigEntry<Integer> testRange = new ClientRangeConfigEntry(storageProvider, "test.range", 4, 1, 10, Text.literal("Test range"), Text.literal("A number in a range"));
	public IClientConfigEntry<Boolean> testToggle = new ClientToggleConfigEntry(storageProvider, "test.toggle", false, Text.literal("Test toggle"), Text.literal("A Toggle button"));
	public IClientConfigEntry<Test> testEnum = new ClientEnumConfigEntry<>(storageProvider, "test.enum", Test.OWO, Text.literal("Test enum"), Text.empty(), Test.class);

	public TestConfig(IStorageProvider storageProvider) {
		super(storageProvider);
	}

	public enum Test {
		UWU,
		OWO
	}
}
