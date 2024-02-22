package com.nublib.config.serialization;

import java.io.Serializable;
import java.util.Optional;

public interface ISerializer<T> extends Serializable {
	String serialize(T value);

	Optional<T> parse(String value);
}
