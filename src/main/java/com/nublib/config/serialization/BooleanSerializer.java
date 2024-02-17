package com.nublib.config.serialization;

import java.util.Optional;

public class BooleanSerializer implements ISerializer<Boolean> {
	@Override
	public Optional<Boolean> parse(String value) {
		if (value.equalsIgnoreCase("true")) return Optional.of(true);
		if (value.equalsIgnoreCase("false")) return Optional.of(false);
		return Optional.empty();
	}

	@Override
	public String serialize(Boolean value) {
		return value ? "true" : "false";
	}
}
