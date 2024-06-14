package com.nublib.config.provider;

import java.util.Map;

public class MemoryStorageProvider extends AbstractStorageProvider {
    public static IStorageProvider create() {
        return new MemoryStorageProvider();
    }

    @Override
    protected void save(Map<String, String> options) {
    }
}
