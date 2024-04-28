package com.nublib.config.option;

import com.nublib.config.provider.IStorageProvider;
import com.nublib.config.screen.page.section.control.Control;
import com.nublib.config.screen.page.section.control.TextFieldControl;
import com.nublib.config.serialization.StringSerializer;

public class StringConfigOption extends ConfigOption<String> implements IHasControl<String> {
	TextFieldControl control = new TextFieldControl(key, defaultValue, storageProvider);

	public StringConfigOption(String key, String defaultValue, IStorageProvider storageProvider) {
		super(key, defaultValue, storageProvider, new StringSerializer());
	}

	public Control<String> getControl() {
		return control;
	}
}
