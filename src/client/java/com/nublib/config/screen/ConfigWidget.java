package com.nublib.config.screen;

import com.nublib.config.provider.ConfigProvider;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.Element;
import net.minecraft.client.gui.Selectable;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.gui.widget.ElementListWidget;
import net.minecraft.client.gui.widget.TextFieldWidget;
import net.minecraft.client.gui.widget.TextWidget;
import net.minecraft.text.Text;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class ConfigWidget extends ElementListWidget.Entry<ConfigWidget> {
	private final LinkedList<Element> children = new LinkedList<>();
	private final TextFieldWidget textField;
	private final ButtonWidget saveButton;
	private final TextWidget title;
	private final String key;
	private final String description = "Not implemented yet";
	private String originalValue;

	public ConfigWidget(TextRenderer textRenderer, String key, String value, ConfigProvider configProvider) {
		this.originalValue = value;
		this.key = key;

		textField = new TextFieldWidget(textRenderer, 0, 20, Text.literal(key));
		textField.setText(value);
		saveButton = ButtonWidget
				.builder(Text.literal("Set"), action -> {
					configProvider.set(key, textField.getText());
					originalValue = textField.getText();
				})
				.size(60, 20)
				.build();

		title = new TextWidget(0, 20, Text.literal(key), textRenderer);
		title.alignLeft();

		children.add(textField);
		children.add(title);
		children.add(saveButton);
	}

	public String getKey() {
		return key;
	}

	public String getValue() {
		return originalValue;
	}

	public String getDescription() {
		return description;
	}

	@Override
	public List<? extends Element> children() {
		return children;
	}

	@Override
	public void render(DrawContext context, int index, int y, int x, int entryWidth, int entryHeight, int mouseX, int mouseY, boolean hovered, float tickDelta) {
		final int padding = 5;
		final int actualWidth = entryWidth - padding * 2;
		final int actualHeight = entryHeight - padding * 2;
		x += padding;
		y += padding;

		title.setX(x);
		title.setY(y);
		title.setWidth(actualWidth);

		textField.setX(x);
		textField.setY(y + actualHeight - 20);
		textField.setWidth(actualWidth - 60 - 10);
		textField.setText(textField.getText());

		saveButton.setX(x + actualWidth - 60 - 5);
		saveButton.setY(y + actualHeight - 20);
		saveButton.active = !Objects.equals(textField.getText(), originalValue);

		title.render(context, mouseX, mouseY, tickDelta);
		textField.render(context, mouseX, mouseY, tickDelta);
		saveButton.render(context, mouseX, mouseY, tickDelta);
	}

	@Override
	public List<? extends Selectable> selectableChildren() {
		return new ArrayList<>(0);
	}
}
