package com.nublib.gui;

import com.nublib.config.entry.IClientConfigEntry;
import com.nublib.gui.widget.entry.*;
import net.minecraft.text.Text;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class ConfigPageBuilder {
	private final List<GuiConfigEntry> configEntries;
	private final Text title;

	public ConfigPageBuilder(Text title) {
		this.title = title;
		this.configEntries = new ArrayList<>();
	}

	public ConfigPageBuilder addConfigEntry(GuiConfigEntry configEntry) {
		configEntries.add(configEntry);
		return this;
	}

	public ConfigPageBuilder addRange(String key, Integer defaultValue, Integer minValue, Integer maxValue, Consumer<RangeGuiConfigEntryBuilder> builderConsumer) {
		RangeGuiConfigEntryBuilder builder = new RangeGuiConfigEntryBuilder(key, defaultValue, minValue, maxValue);
		builderConsumer.accept(builder);
		addConfigEntry(builder.build());
		return this;
	}

	public ConfigPageBuilder addString(String key, String defaultValue, Consumer<StringGuiConfigEntryBuilder> builderConsumer) {
		StringGuiConfigEntryBuilder builder = new StringGuiConfigEntryBuilder(key, defaultValue);
		builderConsumer.accept(builder);
		addConfigEntry(builder.build());
		return this;
	}

	public ConfigPageBuilder addToggle(String key, Boolean defaultValue, Consumer<ToggleGuiConfigEntryBuilder> builderConsumer) {
		ToggleGuiConfigEntryBuilder builder = new ToggleGuiConfigEntryBuilder(key, defaultValue);
		builderConsumer.accept(builder);
		addConfigEntry(builder.build());
		return this;
	}

	public <T extends Enum<T>> ConfigPageBuilder addEnum(String key, T defaultValue, Class<T> enumClass, Consumer<EnumGuiConfigEntryBuilder<T>> builderConsumer) {
		EnumGuiConfigEntryBuilder<T> builder = new EnumGuiConfigEntryBuilder<>(key, defaultValue, enumClass);
		builderConsumer.accept(builder);
		addConfigEntry(builder.build());
		return this;
	}

	public ConfigPageBuilder fromConfigEntry(IClientConfigEntry<?> entry) {
		addConfigEntry(entry.guiConfigEntry());
		return this;
	}

	public ConfigPage build() {
		return new ConfigPage(title, configEntries);
	}
}
