package com.nublib.config.entry;

import com.nublib.config.provider.IStorageProvider;

public class EnumConfigEntry<T extends Enum<T>> extends ConfigEntry<T> {
    public EnumConfigEntry(IStorageProvider storageProvider, String key, T defaultValue) {
        super(storageProvider, key, defaultValue);
    }

    @Override
    protected String serialize(T value) {
        return value.name();
    }

    @Override
    protected T parse(String value) {
        return Enum.valueOf(defaultValue.getDeclaringClass(), value);
    }
}
