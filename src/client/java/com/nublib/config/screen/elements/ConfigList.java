package com.nublib.config.screen.elements;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.widget.ElementListWidget;

public class ConfigList extends ElementListWidget<ConfigEntry> {
	public ConfigList(int width, int height, int x, int y, int itemHeight) {
		super(MinecraftClient.getInstance(), width, height, y, itemHeight);
		setX(x);
	}

	@Override
	public int getRowWidth() {
		return width;
	}
}
