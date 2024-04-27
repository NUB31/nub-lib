package com.nublib.config.screen;

import com.nublib.NubLib;
import com.nublib.config.Config;
import com.nublib.config.option.ConfigOption;
import com.nublib.config.screen.elements.ConfigEntry;
import com.nublib.config.screen.elements.ConfigList;
import com.nublib.config.screen.page.ConfigPage;
import com.nublib.config.screen.page.section.ConfigSection;
import com.nublib.config.screen.page.section.Option;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.option.GameOptionsScreen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.gui.widget.TextWidget;
import net.minecraft.text.Text;
import org.jetbrains.annotations.Nullable;

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
		ConfigScreen screen = new ConfigScreen(parent);

		screen.addFromConfig(config, Text.literal("Uncategorized"));

		List<Field> pages = Arrays
				.stream(config.getClass().getDeclaredFields())
				.filter(field -> {
					try {
						return field.get(config) instanceof Config;
					} catch (Exception e) {
						NubLib.LOGGER.warn(e.getMessage());
						return false;
					}
				})
				.toList();

		pages.forEach(page -> {
			try {
				Config nestedConfig = (Config) page.get(config);
				screen.addFromConfig(nestedConfig, Text.literal(nestedConfig.getClass().getSimpleName()));
			} catch (Exception e) {
				NubLib.LOGGER.warn(e.getMessage());
			}
		});

		return screen;
	}

	private void addFromConfig(Config config, Text name) {
		List<Field> fields = Arrays
				.stream(config.getClass().getDeclaredFields())
				.filter(field -> field.getType().isAssignableFrom(ConfigOption.class))
				.toList();

		if (!fields.isEmpty()) {
			addPage(name, page -> page
					.addSection(Text.empty(), section -> {
						for (Field field : fields) {
							try {
								ConfigOption<?> val = (ConfigOption<?>) field.get(config);
								var metadata = config.getAnnotation(val);
								Option option;
								if (metadata != null) {
									option = new Option(val.getControl(), Text.literal(metadata.title()), Text.literal(metadata.description()));
								} else {
									option = new Option(val.getControl(), Text.empty(), Text.empty());
								}

								section.addOption(option);
							} catch (Exception e) {
								NubLib.LOGGER.info(e.getMessage());
							}
						}
					})
			);
		}
	}

	public ConfigScreen setLeftSidebarWidth(int leftSidebarWidth) {
		this.leftSidebarWidth = leftSidebarWidth;
		return this;
	}

	public ConfigScreen addPage(Text title, Consumer<ConfigPage> page) {
		var configPage = new ConfigPage(title);
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

		Text saveButtonText = Text.literal("Save");
		int saveButtonWidth = textRenderer.getWidth(saveButtonText) + (paddingX * 2);

		ButtonWidget saveButton = ButtonWidget.builder(saveButtonText, v -> close())
				.width(saveButtonWidth)
				.position(width - paddingX - saveButtonWidth, height - ButtonWidget.DEFAULT_HEIGHT - paddingY)
				.build();

		addDrawableChild(saveButton);

		Text closeButtonText = Text.literal("Close");
		int closeButtonWidth = textRenderer.getWidth(closeButtonText) + (paddingX * 2);

		ButtonWidget closeButton = ButtonWidget.builder(closeButtonText, v -> close())
				.width(closeButtonWidth)
				.position(width - (2 * paddingX) - closeButtonWidth - saveButton.getWidth(), height - ButtonWidget.DEFAULT_HEIGHT - paddingY)
				.build();

		addDrawableChild(closeButton);

		int x = paddingX;
		int y = paddingY;
		int width = Math.min(leftSidebarWidth, this.width - (2 * paddingX));
		int height = this.height - (3 * paddingY) - Math.max(closeButton.getHeight(), saveButton.getHeight());

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

			if (configPage == selectedConfigPage) {
				buttonWidget.active = false;
			}

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
		height -= (paddingY + ButtonWidget.DEFAULT_HEIGHT);

		// Config list

		if (selectedConfigPage != null) {
			for (ConfigSection section : selectedConfigPage.getConfigSections()) {
				if (!Objects.equals(section.getLabel().getLiteralString(), "")) {
					TextWidget sectionLabel = new TextWidget(x, y, width, 20, section.getLabel(), textRenderer);
					addDrawableChild(sectionLabel);
					y += sectionLabel.getHeight() + paddingY;
				}

				var configList = new ConfigList(width, height, x, y, 100);

				for (Option option : section.getOptions()) {
					configList.children().add(new ConfigEntry(option, textRenderer));
				}

				addDrawableChild(configList);
			}
		}

		// There is space left on the right, render a right pane
		if (width >= leftSidebarWidth) {
			x = (2 * paddingX) + width;
			y = paddingY;

			// Remaining screen space minus padding;
			width = -(3 * paddingX);

			// TODO: Add customizable sidebar
		}
	}
}