package com.nublib.config.entry;

public interface IConfigEntry<T> {
	void set(T value);

	void setAndSave(T value);

	T get();

	void reset();
}
