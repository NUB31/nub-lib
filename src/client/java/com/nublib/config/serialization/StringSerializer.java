package com.nublib.config.serialization;

import java.util.Optional;

public class StringSerializer implements ISerializer<String> {
	@Override
	public Optional<String> parse(String value) {
		return Optional.of(value);
	}

	@Override
	public String serialize(String value) {
		return value;
	}
}
