package com.nublib.config;

import com.nublib.config.provider.IStorageProvider;

public abstract class Config {
    @Deprecated(forRemoval = true)
    protected IStorageProvider storageProvider;
    protected IStorageProvider sp;

    public Config(IStorageProvider storageProvider) {
        this.sp = storageProvider;
        this.storageProvider = sp;
    }

    public void save() {
        sp.save();
    }
}