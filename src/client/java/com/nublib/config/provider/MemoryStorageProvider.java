package com.nublib.config.provider;

import java.util.Map;

public class MemoryStorageProvider extends StorageProvider {
    public static IStorageProvider create() {
        return new MemoryStorageProvider();
    }

    @Override
    protected void save(Map<String, String> options) {
    }
}
