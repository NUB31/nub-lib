package com.nublib.config.provider;

import java.util.Optional;

public interface IConfigProvider {
	Optional<String> get(String key);

	void set(String key, String value);
}
