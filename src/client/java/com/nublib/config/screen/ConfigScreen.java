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
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.gui.widget.ClickableWidget;
import net.minecraft.client.gui.widget.MultilineTextWidget;
import net.minecraft.client.gui.widget.TextWidget;
import net.minecraft.text.Text;
import org.jetbrains.annotations.Nullable;

import java.awt.*;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;

public class ConfigScreen extends GameOptionsScreen {
	private final List<ConfigPage> configPages;
	@Nullable
	private ConfigPage selectedConfigPage;
	private int leftSidebarWidth = 512;

	public ConfigScreen(Screen parent) {
		super(parent, MinecraftClient.getInstance().options, Text.literal("Options"));
		this.configPages = new ArrayList<>();
	}

	public static ConfigScreen fromConfig(Screen parent, Config config) {
		ConfigScreen screen = new ConfigScreen(parent)
				.addPage(Text.literal("Uncategorized"), page -> page
						.addSection(Text.empty(), section -> {
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

		List<Class<?>> classes = Arrays.stream(config.getClass().getDeclaredClasses()).toList();
		for (Class<?> clazz : classes) {
			List<Field> fields = Arrays.stream(clazz.getDeclaredFields()).filter(field -> field.getType().equals(ConfigOption.class)).toList();
			if (fields.isEmpty()) continue;

			screen.addPage(Text.literal(clazz.getSimpleName()), page -> {
				page.addSection(Text.empty(), section -> {
					for (Field field : fields) {
						try {
							ConfigOptionBase val = (ConfigOptionBase) field.get(config);
							section.addOption(val.getOption());
						} catch (IllegalAccessException ignored) {
						}
					}
				});
			});
		}

		return screen;
	}

	public ConfigScreen setLeftSidebarWidth(int leftSidebarWidth) {
		this.leftSidebarWidth = leftSidebarWidth;
		return this;
	}

	public ConfigScreen addPage(Text title, Consumer<ConfigPage> page) {
		var configPage = new ConfigPage(textRenderer, title);
		page.accept(configPage);
		configPages.add(configPage);

		if (configPages.size() == 1) {
			selectedConfigPage = configPage;
		}

		return this;
	}

	@Override
	protected void init() {
		final int paddingX = 10;
		final int paddingY = 10;

		int width = Math.min(leftSidebarWidth, this.width - (2 * paddingX));

		// Init position: top left
		int x = paddingX;
		int y = paddingY;

		// Tabs
		int currentButtonGroupWidth = 0;

		for (ConfigPage configPage : configPages) {
			ButtonWidget buttonWidget = ButtonWidget
					.builder(configPage.getLabel(), v -> {
						selectedConfigPage = configPage;
						clearAndInit();
					})
					.width(textRenderer.getWidth(configPage.getLabel()) + (paddingX * 2))
					.position(x, y)
					.build();

			addDrawableChild(buttonWidget);

			currentButtonGroupWidth += buttonWidget.getWidth();
			if (currentButtonGroupWidth > width && configPage != configPages.get(0)) {
				y += ButtonWidget.DEFAULT_HEIGHT + paddingY;
				x = paddingX;
				buttonWidget.setPosition(x, y);
			}

			x += buttonWidget.getWidth() + 5;
		}

		y += ButtonWidget.DEFAULT_HEIGHT + paddingY;
		x = paddingX;

		// Options list
		if (selectedConfigPage != null) {
			for (ConfigSection section : selectedConfigPage.getConfigSections()) {
				if (!Objects.equals(section.getLabel().getLiteralString(), "")) {
					TextWidget sectionLabel = new TextWidget(x, y, width, 20, section.getLabel(), textRenderer);
					addDrawableChild(sectionLabel);
					y += sectionLabel.getHeight() + paddingY;
				}

				for (IOption option : section.getOptions()) {
					if (!Objects.equals(option.getLabel().getLiteralString(), "")) {
						TextWidget titleLabel = new TextWidget(x, y, width, 20, option.getLabel(), textRenderer).alignLeft();
						addDrawableChild(titleLabel);
						y += titleLabel.getHeight() + (paddingY / 2);
					}

					if (!Objects.equals(option.getDescription().getLiteralString(), "")) {
						MultilineTextWidget descriptionLabel = new MultilineTextWidget(x, y, option.getDescription(), textRenderer)
								.setTextColor(Color.decode("#bababa").getRGB())
								.setMaxWidth(width);

						addDrawableChild(descriptionLabel);
						y += descriptionLabel.getHeight() + (paddingY / 2);
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

			// TODO: Add customizable sidebar
		}
	}
}