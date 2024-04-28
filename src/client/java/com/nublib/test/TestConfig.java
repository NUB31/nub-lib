package com.nublib.test;

import com.nublib.config.Config;
import com.nublib.config.annotation.ConfigOptionMetadata;
import com.nublib.config.option.BooleanConfigOption;
import com.nublib.config.option.StringConfigOption;
import com.nublib.config.provider.IStorageProvider;

public class TestConfig extends Config {
	@ConfigOptionMetadata(title = "Is stupid", description = "Determines if i am stupid or not")
	public final BooleanConfigOption isStupid = new BooleanConfigOption("isStupid", true, storageProvider);
	@ConfigOptionMetadata(title = "Text field", description = "Determines text field of something")
	public final StringConfigOption textField = new StringConfigOption("textField", "test", storageProvider);

	public TestConfig(IStorageProvider storageProvider) {
		super(storageProvider);
	}
}