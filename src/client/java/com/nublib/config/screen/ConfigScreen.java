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
	private final int configListWidth = 320;
	private ConfigDetailsWidget configDetails;

	public ConfigScreen(Screen parent, IConfigProvider configProvider) {
		super(parent, MinecraftClient.getInstance().options, Text.of("Mod configuration"));
		this.configProvider = configProvider;
	}

	@Override
	protected void init() {
		ConfigListWidget configList = new ConfigListWidget(configListWidth, height - 20, 10, this::setDetailsPane);
		configList.setX(10);
		ButtonWidget closeButton = ButtonWidget
				.builder(Text.literal("Close"), action -> close())
				.size(60, 20)
				.position(width - 70, height - 30)
				.build();

		configProvider.all().forEach((k, v) -> configList.children().add(new ConfigWidget(textRenderer, k, v, configProvider)));

		configDetails = new ConfigDetailsWidget(configListWidth + 20, 10, width - configListWidth - 30, height - 50, textRenderer);

		addDrawableChild(configList);
		addDrawableChild(closeButton);
		addDrawableChild(configDetails);
	}

	private void setDetailsPane(ConfigWidget configWidget) {
		configDetails.setKey(configWidget.getKey());
		configDetails.setDescription(configWidget.getDescription());
	}
}