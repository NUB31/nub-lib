package com.nublib.config.option;

public interface IConfigOption<T> {
	T get();

	void set(T value);

	String key();
}
