package com.nublib.config.provider;

import java.util.Map;
import java.util.Optional;

public interface IStorageProvider {
    Optional<String> get(String key);

    void set(String key, String value);

    void setKeybinding(String key, Integer value);

    Optional<Integer> getKeybinding(String key);

    Map<String, Integer> getKeybindings();

    void save();
}
