package com.nublib.config.entry;

import com.nublib.config.provider.IStorageProvider;

public class IntegerConfigEntry extends ConfigEntry<Integer> {
    public IntegerConfigEntry(IStorageProvider storageProvider, String key, Integer defaultValue) {
        super(storageProvider, key, defaultValue);
    }

    @Override
    protected String serialize(Integer value) {
        return value.toString();
    }

    @Override
    protected Integer parse(String value) {
        return Integer.parseInt(value);
    }
}
