package com.nublib.config.option;

import com.nublib.config.provider.StorageProvider;
import com.nublib.config.screen.page.section.option.IOption;

public abstract class ConfigOptionBase {
	protected final StorageProvider storageProvider;
	protected final IOption option;

	protected ConfigOptionBase(StorageProvider storageProvider, IOption option) {
		this.storageProvider = storageProvider;
		this.option = option;
	}

	public IOption getOption() {
		return option;
	}

	public StorageProvider getStorageProvider() {
		return storageProvider;
	}
}
