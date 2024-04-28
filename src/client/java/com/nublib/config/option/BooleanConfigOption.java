package com.nublib.config.option;

import com.nublib.config.provider.IStorageProvider;
import com.nublib.config.screen.page.section.control.Control;
import com.nublib.config.screen.page.section.control.ToggleControl;
import com.nublib.config.serialization.BooleanSerializer;

public class BooleanConfigOption extends ConfigOption<Boolean> implements IHasControl<Boolean> {
	ToggleControl control = new ToggleControl(key, defaultValue, storageProvider);

	public BooleanConfigOption(String key, Boolean defaultValue, IStorageProvider storageProvider) {
		super(key, defaultValue, storageProvider, new BooleanSerializer());
	}

	@Override
	public Control<Boolean> getControl() {
		return control;
	}
}
