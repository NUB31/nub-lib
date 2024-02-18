package com.nublib.config.screen;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.widget.ElementListWidget;

public class ConfigListWidget extends ElementListWidget<ConfigPropertyWidget> {
	public ConfigListWidget(int i, int j) {
		super(MinecraftClient.getInstance(), i, j, 0, 50);
	}
}
