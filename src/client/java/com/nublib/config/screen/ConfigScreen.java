package com.nublib.config.screen;

import com.nublib.config.provider.IConfigProvider;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.option.GameOptionsScreen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.text.Text;

@Environment(EnvType.CLIENT)
public class ConfigScreen extends GameOptionsScreen {
	private final IConfigProvider configProvider;

	public ConfigScreen(Screen parent, IConfigProvider configProvider) {
		super(parent, MinecraftClient.getInstance().options, Text.of("Mod configuration"));
		this.configProvider = configProvider;
	}

	@Override
	protected void init() {
		ConfigListWidget container = new ConfigListWidget(300, height);
		ButtonWidget closeButton = ButtonWidget
				.builder(Text.literal("Close"), action -> close())
				.size(60, 20)
				.position(width - 70, height - 30)
				.build();

		configProvider.all().forEach((k, v) -> container.children().add(new ConfigPropertyWidget(textRenderer, k, v, configProvider)));

		addDrawableChild(container);
		addDrawableChild(closeButton);
	}
}