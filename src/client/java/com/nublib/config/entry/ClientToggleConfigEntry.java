package com.nublib.config.entry;

import com.nublib.config.provider.IStorageProvider;
import com.nublib.gui.widget.entry.GuiConfigEntry;
import com.nublib.gui.widget.entry.ToggleGuiConfigEntryBuilder;
import net.minecraft.text.Text;

public class ClientToggleConfigEntry extends BooleanConfigEntry implements IClientConfigEntry<Boolean> {
	private final Text description;
	private final Text title;

	public ClientToggleConfigEntry(IStorageProvider storageProvider, String key, Boolean defaultValue, Text title, Text description) {
		super(storageProvider, key, defaultValue);
		this.title = title;
		this.description = description;
	}

	@Override
	public GuiConfigEntry guiConfigEntry() {
		return new ToggleGuiConfigEntryBuilder(key, get()).onChange(this::set).setTitle(title).setDescription(description).build();
	}
}
