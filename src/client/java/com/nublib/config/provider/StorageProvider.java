package com.nublib.config.provider;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public abstract class StorageProvider implements IStorageProvider {
    protected final HashMap<String, String> config = new HashMap<>();
    protected final HashMap<String, Integer> keyBindings = new HashMap<>();

    @Override
    public Optional<String> get(String key) {
        return Optional.ofNullable(config.get(key));
    }

    @Override
    public void set(String key, String value) {
        config.put(key, value);
    }

    @Override
    public Optional<Integer> getKeybinding(String key) {
        return Optional.ofNullable(keyBindings.get(key));
    }

    @Override
    public Map<String, Integer> getKeybindings() {
        return keyBindings;
    }

    @Override
    public void setKeybinding(String key, Integer keyCode) {
        keyBindings.put(key, keyCode);
    }

    @Override
    public void save() {
        save(config);
    }

    protected abstract void save(Map<String, String> options);
}