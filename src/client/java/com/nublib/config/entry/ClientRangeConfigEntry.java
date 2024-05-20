package com.nublib.config.entry;

import com.nublib.config.provider.IStorageProvider;
import com.nublib.gui.widget.entry.GuiConfigEntry;
import com.nublib.gui.widget.entry.RangeGuiConfigEntryBuilder;
import net.minecraft.text.Text;

public class ClientRangeConfigEntry extends IntegerConfigEntry implements IClientConfigEntry<Integer> {
	private final Text title;
	private final Text description;
	private final Integer minValue;
	private final Integer maxValue;

	public ClientRangeConfigEntry(IStorageProvider storageProvider, String key, Integer defaultValue, Integer minValue, Integer maxValue, Text title, Text description) {
		super(storageProvider, key, defaultValue);
		this.title = title;
		this.description = description;
		this.minValue = minValue;
		this.maxValue = maxValue;
	}

	@Override
	public GuiConfigEntry guiConfigEntry() {
		return new RangeGuiConfigEntryBuilder(key, get(), minValue, maxValue)
				.onChange(this::set)
				.setTitle(title)
				.setDescription(description)
				.build();
	}
}
