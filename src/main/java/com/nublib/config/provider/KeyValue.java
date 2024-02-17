package com.nublib.config.provider;

public class KeyValue<TKey, TValue> {
	public TKey key;
	public TValue value;

	public KeyValue(TKey key, TValue value) {
		this.key = key;
		this.value = value;
	}
}