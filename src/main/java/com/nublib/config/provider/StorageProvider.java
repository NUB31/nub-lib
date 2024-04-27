package com.nublib.config.provider;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Optional;

public abstract class StorageProvider {
	protected final LinkedList<IChangeHandler> changeHandlers = new LinkedList<>();
	protected final HashMap<String, String> config = new HashMap<>();

	public void addChangeListener(IChangeHandler changeHandler) {
		changeHandlers.add(changeHandler);
	}

	public Optional<String> get(String key) {
		return getImpl(key);
	}

	public void set(String key, String value) {
		setImpl(key, value);
		changeHandlers.forEach(x -> x.onChange(key, value));
	}

	protected abstract void setImpl(String key, String value);

	protected abstract Optional<String> getImpl(String key);
}