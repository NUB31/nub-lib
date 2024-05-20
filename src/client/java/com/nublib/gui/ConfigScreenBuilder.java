package com.nublib.gui;

import com.nublib.NubLib;
import com.nublib.config.Config;
import com.nublib.config.entry.IClientConfigEntry;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

public class ConfigScreenBuilder {
	private final Screen parent;
	private final List<ConfigPage> configPages;
	private Runnable onSave;
	private Runnable onCancel;
	private Boolean hasCancelButton;

	public ConfigScreenBuilder(Screen parent) {
		this.parent = parent;
		this.onSave = () -> {
		};
		this.onCancel = () -> {
		};
		this.configPages = new ArrayList<>();
		this.hasCancelButton = false;
	}

	public ConfigScreenBuilder onSave(Runnable delegate) {
		onSave = delegate;
		return this;
	}

	public ConfigScreenBuilder onCancel(Runnable delegate) {
		hasCancelButton = true;
		onCancel = delegate;
		return this;
	}

	public ConfigScreenBuilder addPage(Text title, Consumer<ConfigPageBuilder> builderConsumer) {
		ConfigPageBuilder builder = new ConfigPageBuilder(title);
		builderConsumer.accept(builder);
		configPages.add(builder.build());
		return this;
	}

	public ConfigScreenBuilder fromConfig(Text pageTitle, Config config) {
		addPage(pageTitle, builder -> {
			Arrays.stream(config.getClass().getDeclaredFields()).toList().forEach(field -> {
				try {
					IClientConfigEntry<?> instance = (IClientConfigEntry<?>) field.get(config);
					builder.fromConfigEntry(instance);
				} catch (Exception e) {
					NubLib.LOGGER.info(e.getMessage());
				}
			});
		});
		return this;
	}

	public ConfigScreen build() {
		return new ConfigScreen(parent, onSave, onCancel, configPages, hasCancelButton);
	}
}
