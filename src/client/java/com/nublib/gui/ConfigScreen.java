package com.nublib.gui;

import com.nublib.gui.widget.ConfigEntryList;
import com.nublib.gui.widget.ConfigEntryWidget;
import com.nublib.gui.widget.entry.GuiConfigEntry;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.option.GameOptionsScreen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.gui.widget.DirectionalLayoutWidget;
import net.minecraft.screen.ScreenTexts;
import net.minecraft.text.Text;

import java.util.List;

public class ConfigScreen extends GameOptionsScreen {
	private final Runnable onSave;
	private final Runnable onCancel;
	private final List<GuiConfigEntry> configEntries;
	private ConfigEntryList entryWidget;

	public ConfigScreen(Screen parent, Text title, Runnable onSave, Runnable onCancel, List<GuiConfigEntry> configEntries, String modId) {
		super(parent, MinecraftClient.getInstance().options, title);
		this.onSave = onSave;
		this.onCancel = onCancel;
		this.configEntries = configEntries;
	}

	public static ConfigScreenBuilder builder(Screen parent, String modId) {
		return new ConfigScreenBuilder(parent, modId);
	}

	protected void refreshSize() {
		entryWidget.setWidth(layout.getWidth());
		entryWidget.setHeight(layout.getContentHeight());
	}

	@Override
	protected void initFooter() {
		DirectionalLayoutWidget directionalLayoutWidget = layout.addFooter(DirectionalLayoutWidget.horizontal().spacing(8));
		directionalLayoutWidget.add(ButtonWidget.builder(ScreenTexts.CANCEL, (button) -> close(false)).build());
		directionalLayoutWidget.add(ButtonWidget.builder(ScreenTexts.DONE, (button) -> close(true)).build());
	}

	@Override
	protected void init() {
		entryWidget = new ConfigEntryList(MinecraftClient.getInstance(), layout.getWidth(), layout.getContentHeight(), 0, 100);
		configEntries.forEach(configEntry -> entryWidget.children().add(new ConfigEntryWidget(configEntry)));
		layout.addBody(entryWidget);
		super.init();
	}

	@Override
	public void render(DrawContext context, int mouseX, int mouseY, float delta) {
		refreshSize();
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
