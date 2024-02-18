package com.nublib.config.provider;

import java.util.HashMap;
import java.util.Optional;

public interface IConfigProvider {
	HashMap<String, String> all();

	Optional<String> get(String key);

	void set(String key, String value);
}
