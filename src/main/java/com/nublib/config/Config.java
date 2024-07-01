package com.nublib.config;

import com.nublib.config.provider.IStorageProvider;

public abstract class Config {
    protected IStorageProvider sp;

    public Config(IStorageProvider storageProvider) {
        this.sp = storageProvider;
    }

    public void save() {
        sp.save();
    }
}