package com.nublib.config.entry;

import com.nublib.config.provider.IStorageProvider;
import com.nublib.gui.widget.entry.EnumGuiConfigEntryBuilder;
import com.nublib.gui.widget.entry.GuiConfigEntry;
import net.minecraft.text.Text;

public class ClientEnumConfigEntry<T extends Enum<T>> extends EnumConfigEntry<T> implements IClientConfigEntry<T> {
	private final Text title;
	private final Text description;
	private final Class<T> clazz;

	public ClientEnumConfigEntry(IStorageProvider storageProvider, String key, T defaultValue, Text title, Text description, Class<T> clazz) {
		super(storageProvider, key, defaultValue);
		this.title = title;
		this.description = description;
		this.clazz = clazz;
	}

	@Override
	public GuiConfigEntry guiConfigEntry() {
		return new EnumGuiConfigEntryBuilder<>(key, get(), clazz)
				.onChange(this::set)
				.setTitle(title)
				.setDescription(description)
				.build();
	}
}
