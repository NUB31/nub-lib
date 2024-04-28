package com.nublib.config.screen;

import com.nublib.NubLib;
import com.nublib.config.Config;
import com.nublib.config.option.IHasControl;
import com.nublib.config.provider.IStorageProvider;
import com.nublib.config.screen.elements.ConfigEntry;
import com.nublib.config.screen.elements.ConfigList;
import com.nublib.config.screen.model.ConfigPage;
import com.nublib.config.screen.model.section.ConfigOption;
import com.nublib.config.screen.model.section.ConfigSection;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.option.GameOptionsScreen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.gui.widget.TextFieldWidget;
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

		screen.generatePageFromConfig(config, Text.literal("Uncategorized"));

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
				screen.generatePageFromConfig(nestedConfig, Text.literal(nestedConfig.getClass().getSimpleName()));
			} catch (Exception e) {
				NubLib.LOGGER.warn(e.getMessage());
			}
		});

		return screen;
	}

	public ConfigScreen reset() {
		configPages
				.forEach(configPage -> configPage
						.getConfigSections()
						.forEach(configSection -> configSection
								.getOptions()
								.forEach(configOption -> configOption
										.getControl()
										.reset())));

		return this;
	}

	private void generatePageFromConfig(Config config, Text name) {
		Field[] fields = config.getClass().getDeclaredFields();

		if (fields.length != 0) {
			addPage(name, page -> page
					.addSection(Text.empty(), section -> {
						for (Field field : fields) {
							try {
								IHasControl<?> hasControl = (IHasControl<?>) field.get(config);
								var metadata = config.getMetadataForField(hasControl);
								ConfigOption<?> option;
								if (metadata != null) {
									option = new ConfigOption<>(hasControl.getControl(), Text.literal(metadata.title()), Text.literal(metadata.description()));
								} else {
									option = new ConfigOption<>(hasControl.getControl(), Text.empty(), Text.empty());
								}

								section.addOption(option);
							} catch (ClassCastException ignored) {
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

		ButtonWidget saveButton = ButtonWidget
				.builder(saveButtonText, v -> {
					ArrayList<IStorageProvider> storageProviders = new ArrayList<>();

					configPages
							.forEach(page -> page
									.getConfigSections()
									.forEach(section -> section.
											getOptions()
											.forEach(configOption -> {
												configOption.getControl().apply();
												if (!storageProviders.contains(configOption.getControl().getStorageProvider())) {
													storageProviders.add(configOption.getControl().getStorageProvider());
												}
											})));
					
					storageProviders.forEach(IStorageProvider::save);
					close();
				})
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

		// Prep left side
		int x = paddingX;
		int y = paddingY;
		int width = Math.min(leftSidebarWidth, this.width - (2 * paddingX));
		int height = this.height - (2 * paddingY);

		// if the left sidebar overlaps with the buttons, reduce the height of the left sidebar
		if (this.width - (4 * paddingX) - closeButtonWidth - saveButtonWidth < width) {
			height -= (paddingX + ButtonWidget.DEFAULT_HEIGHT);
		}

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

			currentButtonGroupWidth += buttonWidget.getWidth() + paddingX;
			if (currentButtonGroupWidth - (2 * paddingX) > width && configPage != configPages.get(0)) {
				y += ButtonWidget.DEFAULT_HEIGHT + paddingY;
				x = paddingX;
				buttonWidget.setPosition(x, y);
				currentButtonGroupWidth = buttonWidget.getWidth() + paddingX;
			}

			x += buttonWidget.getWidth() + 5;
		}

		y += ButtonWidget.DEFAULT_HEIGHT + paddingY;
		x = paddingX;
		height -= (paddingY + ButtonWidget.DEFAULT_HEIGHT);

		if (selectedConfigPage != null) {
			for (ConfigSection section : selectedConfigPage.getConfigSections()) {
				if (!Objects.equals(section.getLabel().getLiteralString(), "")) {
					TextWidget sectionLabel = new TextWidget(x, y, width, 20, section.getLabel(), textRenderer);
					addDrawableChild(sectionLabel);
					y += sectionLabel.getHeight() + paddingY;
				}

				var configList = new ConfigList(width, height, x, y, 90);

				for (ConfigOption<?> configOption : section.getOptions()) {
					configList.children().add(new ConfigEntry(configOption, textRenderer));
				}

				addDrawableChild(configList);
			}
		}

		// Right sidebar
		width = this.width - leftSidebarWidth - (3 * paddingX);
		height = this.height - (3 * paddingY) - ButtonWidget.DEFAULT_HEIGHT;
		x = leftSidebarWidth + (2 * paddingX);
		y = paddingY;

		if (width > 0) {
			TextFieldWidget widget = new TextFieldWidget(textRenderer, x, y, width, height, Text.empty());
			addDrawableChild(widget);
		}
	}
}