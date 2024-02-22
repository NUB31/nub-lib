package com.nublib.config;

import com.nublib.config.provider.ConfigProvider;

import java.io.Serializable;

public abstract class Config implements Serializable {
	protected ConfigProvider provider;

	public Config(ConfigProvider provider) {
		this.provider = provider;
	}
}