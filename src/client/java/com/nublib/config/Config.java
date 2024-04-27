package com.nublib.config;

import com.nublib.config.annotation.ConfigOptionMetadata;
import com.nublib.config.option.ConfigOption;
import com.nublib.config.provider.StorageProvider;
import org.jetbrains.annotations.Nullable;

import java.lang.reflect.Field;
import java.util.Objects;

public abstract class Config {
	protected StorageProvider storageProvider;

	public Config(StorageProvider storageProvider) {
		this.storageProvider = storageProvider;
	}

	public @Nullable ConfigOptionMetadata getAnnotation(ConfigOption<?> option) {
		for (Field field : this.getClass().getDeclaredFields()) {
			try {
				if (Objects.equals(field.get(this), option)) {
					if (field.isAnnotationPresent(ConfigOptionMetadata.class)) {
						return field.getAnnotation(ConfigOptionMetadata.class);
					}
				}
			} catch (Exception ignored) {
			}
		}

		return null;
	}
}