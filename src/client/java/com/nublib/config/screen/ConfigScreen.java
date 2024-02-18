package com.nublib.config.screen;

import com.nublib.config.provider.IConfigProvider;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.gui.widget.TextFieldWidget;
import net.minecraft.client.gui.widget.TextWidget;
import net.minecraft.text.Text;

import java.util.Map;

@Environment(EnvType.CLIENT)
public class ConfigScreen extends Screen {
	private final IConfigProvider configProvider;
	private final Screen parent;
	private String longestConfigKey = "";

	public ConfigScreen(Text title, IConfigProvider configProvider, Screen parent) {
		super(title);
		this.configProvider = configProvider;
		this.parent = parent;

		configProvider.all().keySet().forEach(k -> {
			if (k.length() > this.longestConfigKey.length()) {
				this.longestConfigKey = k;
			}
		});
	}

	@Override
	protected void init() {
		int index = 0;

		for (Map.Entry<String, String> entry : configProvider.all().entrySet()) {
			index++;
			addConfig(entry.getKey(), entry.getValue(), index);
		}
	}

	private void addConfig(String key, String value, int index) {
		final int widgetHeight = 20;
		final int gap = 10;
		final int y = (widgetHeight + gap) * index;

		final int textWidth = textRenderer.getWidth(key);
		final int buttonWidth = 50;
		final int inputWidth = 150;

		int x = gap;
		TextWidget textWidget = new TextWidget(x, y, textWidth, widgetHeight, Text.literal(key), textRenderer);

		x += textRenderer.getWidth(longestConfigKey) + gap;
		TextFieldWidget textFieldWidget = new TextFieldWidget(textRenderer, x, y, inputWidth, widgetHeight, Text.empty());
		textFieldWidget.setText(value);

		x += inputWidth + gap;
		ButtonWidget buttonWidget = ButtonWidget
				.builder(Text.literal("Save"), action -> configProvider.set(key, textFieldWidget.getText()))
				.position(x, y)
				.size(buttonWidth, widgetHeight)
				.build();

		addDrawableChild(textWidget);
		addDrawableChild(textFieldWidget);
		addDrawableChild(buttonWidget);
	}

	@Override
	public void close() {
		client.setScreen(parent);
	}
}
