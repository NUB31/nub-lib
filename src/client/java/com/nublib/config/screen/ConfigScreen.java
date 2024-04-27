package com.nublib.config.screen;

import com.nublib.NubLib;
import com.nublib.config.screen.page.ConfigPage;
import com.nublib.config.screen.page.section.ConfigSection;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.option.GameOptionsScreen;
import net.minecraft.text.Text;

import java.util.List;

public class ConfigScreen extends GameOptionsScreen {
	private final List<ConfigPage> configPages;

	public ConfigScreen(Screen parent, Text title, List<ConfigPage> configPages) {
		super(parent, MinecraftClient.getInstance().options, title);
		this.configPages = configPages;
	}

	public static ConfigScreenBuilder builder(Screen parent) {
		return new ConfigScreenBuilder(MinecraftClient.getInstance().textRenderer, parent);
	}

	@Override
	protected void init() {
		int offsetY = 10;

		for (ConfigPage page : configPages) {
			page.setX(20);
			page.setY(offsetY);
			page.setWidth(width);
			page.setHeight(20);
			addDrawableChild(page);
			offsetY += 20;

			for (ConfigSection section : page.getConfigSections()) {
				NubLib.LOGGER.info("" + offsetY);
				section.setX(20);
				section.setY(offsetY);
				section.setWidth(width);
				section.setHeight(20);
				addDrawableChild(section);
				offsetY += 20;
			}
		}
	}
}