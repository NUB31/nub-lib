package com.nublib.config;

import com.nublib.config.provider.ConfigProvider;
import com.nublib.config.provider.IChangeHandler;

import java.io.Serializable;

public abstract class Config implements Serializable {
	protected ConfigProvider provider;

	public Config(ConfigProvider provider) {
		this.provider = provider;
		this.provider.addChangeListener(new ChangeHandler());
	}

	private class ChangeHandler implements IChangeHandler {
		@Override
		public void onChange(String key, String value) {

		}
	}
}