package com.nublib.config;

import com.nublib.config.provider.StorageProvider;

public abstract class Config {
	protected StorageProvider storageProvider;

	public Config(StorageProvider storageProvider) {
		this.storageProvider = storageProvider;
	}
}