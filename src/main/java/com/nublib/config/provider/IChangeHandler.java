package com.nublib.config.provider;

import java.io.Serializable;

public interface IChangeHandler extends Serializable {
	void onChange(String key, String value);
}
