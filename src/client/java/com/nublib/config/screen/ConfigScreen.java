package com.nublib.config.screen;

import com.nublib.config.Config;
import com.nublib.config.option.ConfigOption;
import com.nublib.config.option.ConfigOptionBase;
import com.nublib.config.screen.page.ConfigPage;
import com.nublib.config.screen.page.section.ConfigSection;
import com.nublib.config.screen.page.section.option.IOption;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.option.GameOptionsScreen;
import net.minecraft.client.gui.widget.ClickableWidget;
import net.minecraft.client.gui.widget.MultilineTextWidget;
import net.minecraft.client.gui.widget.TextFieldWidget;
import net.minecraft.client.gui.widget.TextWidget;
import net.minecraft.text.Text;

import java.awt.*;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;

public class ConfigScreen extends GameOptionsScreen {
	private final List<ConfigPage> configPages;
	private int leftSidebarWidth = 400;

	public ConfigScreen(Screen parent) {
		super(parent, MinecraftClient.getInstance().options, Text.literal("Options"));
		this.configPages = new ArrayList<>();
	}

	public static ConfigScreen fromConfig(Screen parent, Config config) {
		return new ConfigScreen(parent)
				.addPage(Text.literal("Options"), page -> page
						.addSection(Text.literal("Section 1"), section -> {
							List<Field> fields = Arrays.stream(config.getClass().getDeclaredFields()).filter(field -> field.getType().equals(ConfigOption.class)).toList();
							for (Field field : fields) {
								try {
									ConfigOptionBase val = (ConfigOptionBase) field.get(config);
									section.addOption(val.getOption());
								} catch (IllegalAccessException ignored) {
								}
							}
						})
				);
	}

	public ConfigScreen setLeftSidebarWidth(int leftSidebarWidth) {
		this.leftSidebarWidth = leftSidebarWidth;
		return this;
	}

	public ConfigScreen addPage(Text title, Consumer<ConfigPage> page) {
		var configPage = new ConfigPage(textRenderer, title);
		page.accept(configPage);
		configPages.add(configPage);
		return this;
	}

	@Override
	protected void init() {
		final int paddingX = 10;
		final int paddingY = 5;

		// Left sidebar
		int width = Math.min(leftSidebarWidth, this.width - (2 * paddingX));
		int x = paddingX;
		int y = paddingY;

		for (ConfigPage page : configPages) {
			TextWidget pageLabel = new TextWidget(x, y, width, 20, page.getLabel(), textRenderer).alignLeft();
			addDrawableChild(pageLabel);
			y += pageLabel.getHeight() + paddingY;

			for (ConfigSection section : page.getConfigSections()) {
				TextWidget sectionLabel = new TextWidget(x, y, width, 20, section.getLabel(), textRenderer);
				addDrawableChild(sectionLabel);
				y += sectionLabel.getHeight() + paddingY;

				for (IOption option : section.getOptions()) {
					if (!Objects.equals(option.getLabel().getLiteralString(), "")) {
						TextWidget titleLabel = new TextWidget(x, y, width, 20, option.getLabel(), textRenderer).alignLeft();
						addDrawableChild(titleLabel);
						y += titleLabel.getHeight() + paddingY;
					}

					if (!Objects.equals(option.getDescription().getLiteralString(), "")) {
						MultilineTextWidget descriptionLabel = new MultilineTextWidget(x, y, option.getDescription(), textRenderer).setTextColor(Color.decode("#bababa").getRGB());

						addDrawableChild(descriptionLabel);
						y += descriptionLabel.getHeight() + paddingY;
					}

					ClickableWidget widget = option.getWidget(textRenderer, 10, y, width, 20);
					addDrawableChild(widget);
					y += widget.getHeight() + paddingY;
				}
			}
		}

		// There is space left on the right, render a right pane
		if (width >= leftSidebarWidth) {
			x = (2 * paddingX) + width;
			y = paddingY;

			// Remaining screen space minus padding;
			width = this.width - (3 * paddingX) - width;

			// No point in rendering if the side pane is too small
			TextFieldWidget detailsPane = new TextFieldWidget(textRenderer, x, y, width, height - (2 * paddingY), Text.literal("lkdfjgl"));
			addDrawableChild(detailsPane);
		}
	}
}