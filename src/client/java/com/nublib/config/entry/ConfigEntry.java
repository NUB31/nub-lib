package com.nublib.config.entry;

import com.nublib.NubLib;
import com.nublib.config.provider.IStorageProvider;

import java.util.Optional;

public abstract class ConfigEntry<T> implements IConfigEntry<T> {
    protected IStorageProvider storageProvider;
    protected T defaultValue;
    protected String key;

    public ConfigEntry(IStorageProvider storageProvider, String key, T defaultValue) {
        this.storageProvider = storageProvider;
        this.defaultValue = defaultValue;
        this.key = key;
    }

    @Override
    public T get() {
        Optional<String> serialized = storageProvider.get(key);
        if (serialized.isPresent()) {
            try {
                return parse(serialized.get());
            } catch (Exception e) {
                NubLib.LOGGER.warn(String.format("Unable to parse key `%s`, using default value instead", key));
                return defaultValue;
            }
        } else {
            return defaultValue;
        }
    }

    @Override
    public String getKey() {
        return key;
    }

    @Override
    public void set(T value) {
        storageProvider.set(key, serialize(value));
    }

    @Override
    public void setAndSave(T value) {
        set(value);
        storageProvider.save();
    }

    @Override
    public void reset() {
        set(defaultValue);
    }

    protected abstract String serialize(T value);

    protected abstract T parse(String value);
}
