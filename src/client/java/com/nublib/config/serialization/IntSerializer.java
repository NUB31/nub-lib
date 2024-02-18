package com.nublib.config.serialization;

import java.util.Optional;

public class IntSerializer implements ISerializer<Integer> {
	@Override
	public Optional<Integer> parse(String value) {
		try {
			return Optional.of(Integer.parseInt(value));
		} catch (NumberFormatException ex) {
			return Optional.empty();
		}
	}

	@Override
	public String serialize(Integer value) {
		return value.toString();
	}
}
