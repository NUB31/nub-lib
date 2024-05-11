package com.nublib.gui;

import com.nublib.NubLib;
import com.nublib.config.Config;
import com.nublib.config.entry.IClientConfigEntry;
import com.nublib.gui.widget.entry.GuiConfigEntry;
import com.nublib.gui.widget.entry.RangeGuiConfigEntryBuilder;
import com.nublib.gui.widget.entry.StringGuiConfigEntryBuilder;
import com.nublib.gui.widget.entry.ToggleGuiConfigEntryBuilder;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

public class ConfigScreenBuilder {
	private final Screen parent;
	private final List<GuiConfigEntry> configEntries;
	private final String modId;
	private Runnable onClose;
	private Text title;

	public ConfigScreenBuilder(Screen parent, String modId) {
		this.parent = parent;
		this.onClose = () -> {
		};
		this.configEntries = new ArrayList<>();
		this.title = Text.literal(String.format("Mod options for %s", modId));
		this.modId = modId;
	}

	public ConfigScreenBuilder onSave(Runnable delegate) {
		onClose = delegate;
		return this;
	}

	public ConfigScreenBuilder setTitle(Text title) {
		this.title = title;
		return this;
	}

	public ConfigScreenBuilder addConfigEntry(GuiConfigEntry configEntry) {
		configEntries.add(configEntry);
		return this;
	}

	public ConfigScreenBuilder addRange(String key, Integer defaultValue, Integer minValue, Integer maxValue, Consumer<RangeGuiConfigEntryBuilder> builderConsumer) {
		RangeGuiConfigEntryBuilder builder = new RangeGuiConfigEntryBuilder(key, defaultValue, minValue, maxValue);
		builderConsumer.accept(builder);
		addConfigEntry(builder.build());
		return this;
	}

	public ConfigScreenBuilder addString(String key, String defaultValue, Consumer<StringGuiConfigEntryBuilder> builderConsumer) {
		StringGuiConfigEntryBuilder builder = new StringGuiConfigEntryBuilder(key, defaultValue);
		builderConsumer.accept(builder);
		addConfigEntry(builder.build());
		return this;
	}

	public ConfigScreenBuilder addToggle(String key, Boolean defaultValue, Consumer<ToggleGuiConfigEntryBuilder> builderConsumer) {
		ToggleGuiConfigEntryBuilder builder = new ToggleGuiConfigEntryBuilder(key, defaultValue);
		builderConsumer.accept(builder);
		addConfigEntry(builder.build());
		return this;
	}

	public ConfigScreenBuilder fromConfig(Config config) {
		Arrays.stream(config.getClass().getDeclaredFields()).toList().forEach(field -> {
			try {
				IClientConfigEntry<?> instance = (IClientConfigEntry<?>) field.get(config);
				fromConfigEntry(instance);
			} catch (Exception e) {
				NubLib.LOGGER.info(e.getMessage());
			}
		});
		return this;
	}

	public ConfigScreenBuilder fromConfigEntry(IClientConfigEntry<?> entry) {
		addConfigEntry(entry.guiConfigEntry());
		return this;
	}

	public ConfigScreen build() {
		return new ConfigScreen(parent, title, onClose, configEntries, modId);
	}
}
