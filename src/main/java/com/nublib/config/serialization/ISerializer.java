package com.nublib.config.serialization;

import java.util.Optional;

public interface ISerializer<T> {
	String serialize(T value);

	Optional<T> parse(String value);
}
