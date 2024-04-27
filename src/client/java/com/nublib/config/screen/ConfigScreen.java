package com.nublib.config.screen;

import com.nublib.config.screen.page.ConfigPage;
import com.nublib.config.screen.page.section.ConfigSection;
import com.nublib.config.screen.page.section.option.IOption;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.option.GameOptionsScreen;
import net.minecraft.client.gui.widget.ClickableWidget;
import net.minecraft.client.gui.widget.TextWidget;
import net.minecraft.text.Text;

import java.awt.*;
import java.util.List;
import java.util.Objects;

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
		int y = 10;
		int x = 10;
		for (ConfigPage page : configPages) {
			for (ConfigSection section : page.getConfigSections()) {
				for (IOption option : section.getOptions()) {
					if (!Objects.equals(option.getLabel().getLiteralString(), "")) {
						TextWidget widget = new TextWidget(x, y, width - (2 * x), 20, option.getLabel(), textRenderer);
						addDrawableChild(widget);
						y += widget.getHeight() + 5;
					}

					if (!Objects.equals(option.getDescription().getLiteralString(), "")) {
						TextWidget widget = new TextWidget(x, y, width - (2 * x), 20, option.getDescription(), textRenderer)
								.alignLeft()
								.setTextColor(Color.decode("#bababa").getRGB());

						addDrawableChild(widget);
						y += widget.getHeight() + 5;
					}

					ClickableWidget widget = option.getWidget(10, y, width - (2 * x), 20);
					addDrawableChild(widget);
					y += widget.getHeight() + 5;
				}
			}
		}
	}
}