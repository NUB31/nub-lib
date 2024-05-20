package com.nublib.gui;

import com.nublib.gui.widget.ConfigEntryList;
import com.nublib.gui.widget.ConfigEntryWidget;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.option.GameOptionsScreen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.gui.widget.DirectionalLayoutWidget;
import net.minecraft.screen.ScreenTexts;
import net.minecraft.text.Text;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ConfigScreen extends GameOptionsScreen {
	private final Runnable onSave;
	private final Runnable onCancel;
	private final List<ConfigPage> configPages;
	private final Map<ConfigPage, ButtonWidget> tabButtons;
	private final Boolean cancelButton;
	@Nullable
	private ConfigPage selectedConfigPage;
	@Nullable
	private ConfigEntryList configEntryListWidget;


	public ConfigScreen(Screen parent, Runnable onSave, Runnable onCancel, List<ConfigPage> configPages, Boolean cancelButton) {
		super(parent, MinecraftClient.getInstance().options, Text.literal("Options"));
		this.onSave = onSave;
		this.onCancel = onCancel;
		this.configPages = configPages;
		this.tabButtons = new HashMap<>();
		this.cancelButton = cancelButton;
	}

	private void refreshPage(ConfigPage page) {
		selectedConfigPage = page;

		if (selectedConfigPage != null && configEntryListWidget != null) {
			configEntryListWidget.children().clear();
			selectedConfigPage.configEntries().forEach(entry -> configEntryListWidget.children().add(new ConfigEntryWidget(entry)));
		}
	}

	@Override
	protected void initHeader() {
		for (ConfigPage configPage : configPages) {
			tabButtons.put(configPage, ButtonWidget.builder(configPage.title(), button -> {
				refreshPage(configPage);
			}).width(100).build());
		}

		DirectionalLayoutWidget buttonPositioningWidget = layout.addHeader(DirectionalLayoutWidget.horizontal().spacing(4));
		if (buttonPositioningWidget != null) {
			tabButtons.forEach((k, v) -> buttonPositioningWidget.add(v));
		}
	}

	@Override
	protected void initFooter() {
		DirectionalLayoutWidget directionalLayoutWidget = layout.addFooter(DirectionalLayoutWidget.horizontal().spacing(8));
		if (cancelButton) {
			directionalLayoutWidget.add(ButtonWidget.builder(ScreenTexts.CANCEL, (button) -> close(false)).build());
		}
		directionalLayoutWidget.add(ButtonWidget.builder(ScreenTexts.DONE, (button) -> close(true)).build());
	}

	@Override
	protected void init() {
		configEntryListWidget = new ConfigEntryList(MinecraftClient.getInstance(), layout.getWidth(), layout.getContentHeight(), 0, 60);
		layout.addBody(configEntryListWidget);

		if (!configPages.isEmpty()) {
			refreshPage(configPages.getFirst());
		}

		super.init();
	}

	@Override
	public void render(DrawContext context, int mouseX, int mouseY, float delta) {
		tabButtons.forEach((k, v) -> v.active = selectedConfigPage != k);

		if (configEntryListWidget != null) {
			configEntryListWidget.setWidth(layout.getWidth());
			configEntryListWidget.setHeight(layout.getContentHeight());
		}

		super.render(context, mouseX, mouseY, delta);
	}

	private void close(boolean save) {
		if (save) {
			onSave.run();
			super.close();
		} else {
			onCancel.run();
			super.close();
		}
	}

	@Override
	public void close() {
		close(true);
	}
}
