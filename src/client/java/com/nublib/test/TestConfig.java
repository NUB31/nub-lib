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
	@ConfigOptionMetadata(title = "Is stupid", description = "Determines if i am stupid or not")
	public final BooleanConfigOption isStupid2 = new BooleanConfigOption("isStupid", true, storageProvider);
	@ConfigOptionMetadata(title = "Text field", description = "Determines text field of something")
	public final StringConfigOption textField2 = new StringConfigOption("textField", "test", storageProvider);
	@ConfigOptionMetadata(title = "Is stupid", description = "Determines if i am stupid or not")
	public final BooleanConfigOption isStupid3 = new BooleanConfigOption("isStupid", true, storageProvider);
	@ConfigOptionMetadata(title = "Text field", description = "Determines text field of something")
	public final StringConfigOption textField3 = new StringConfigOption("textField", "test", storageProvider);
	@ConfigOptionMetadata(title = "Is stupid", description = "Determines if i am stupid or not")
	public final BooleanConfigOption isStupid4 = new BooleanConfigOption("isStupid", true, storageProvider);
	@ConfigOptionMetadata(title = "Text field", description = "Determines text field of something")
	public final StringConfigOption textField4 = new StringConfigOption("textField", "test", storageProvider);
	@ConfigOptionMetadata(title = "Is stupid", description = "Determines if i am stupid or not")
	public final BooleanConfigOption isStupid5 = new BooleanConfigOption("isStupid", true, storageProvider);
	@ConfigOptionMetadata(title = "Text field", description = "Determines text field of something")
	public final StringConfigOption textField5 = new StringConfigOption("textField", "test", storageProvider);
	@ConfigOptionMetadata(title = "Is stupid", description = "Determines if i am stupid or not")
	public final BooleanConfigOption isStupid6 = new BooleanConfigOption("isStupid", true, storageProvider);
	@ConfigOptionMetadata(title = "Text field", description = "Determines text field of something")
	public final StringConfigOption textField6 = new StringConfigOption("textField", "test", storageProvider);
	@ConfigOptionMetadata(title = "Is stupid", description = "Determines if i am stupid or not")
	public final BooleanConfigOption isStupid7 = new BooleanConfigOption("isStupid", true, storageProvider);
	@ConfigOptionMetadata(title = "Text field", description = "Determines text field of something")
	public final StringConfigOption textField7 = new StringConfigOption("textField", "test", storageProvider);

	public TestConfig(IStorageProvider storageProvider) {
		super(storageProvider);
	}
}