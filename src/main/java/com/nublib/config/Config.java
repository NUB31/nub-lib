package com.nublib.config;

import com.nublib.config.provider.IStorageProvider;

public abstract class Config {
	protected IStorageProvider storageProvider;

	public Config(IStorageProvider storageProvider) {
		this.storageProvider = storageProvider;
	}

	public void save() {
		storageProvider.save();
	}
}