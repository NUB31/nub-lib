package com.nublib.config.provider;

import java.util.Optional;

public interface IStorageProvider {
    Optional<String> get(String key);

    void set(String key, String value);

    void save();
}
