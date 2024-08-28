package com.nublib.config.entry;

import com.nublib.gui.widget.entry.GuiConfigEntry;

public interface IClientConfigEntry<T> extends IConfigEntry<T> {
	GuiConfigEntry guiConfigEntry();
}
