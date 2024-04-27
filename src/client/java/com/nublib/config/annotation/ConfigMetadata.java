package com.nublib.config.annotation;

import org.jetbrains.annotations.Nullable;

public @interface ConfigMetadata {
	@Nullable
	String title();

	@Nullable
	String description();
}
