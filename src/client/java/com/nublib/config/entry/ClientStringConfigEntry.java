package com.nublib.config.entry;

import com.nublib.config.provider.IStorageProvider;
import com.nublib.gui.widget.entry.GuiConfigEntry;
import com.nublib.gui.widget.entry.StringGuiConfigEntryBuilder;
import net.minecraft.text.Text;

public class ClientStringConfigEntry extends StringConfigEntry implements IClientConfigEntry<String> {
	private final Text description;
	private final Text title;

	public ClientStringConfigEntry(IStorageProvider storageProvider, String key, String defaultValue, Text title, Text description) {
		super(storageProvider, key, defaultValue);
		this.description = description;
		this.title = title;
	}

	@Override
	public GuiConfigEntry guiConfigEntry() {
		return new StringGuiConfigEntryBuilder(key, get())
				.onChange(this::set)
				.setTitle(title)
				.setDescription(description)
				.build();
	}
}
