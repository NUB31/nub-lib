package com.nublib.config.screen;

import com.nublib.config.provider.IConfigProvider;
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

public class ConfigPropertyWidget extends ElementListWidget.Entry<ConfigPropertyWidget> {
	private final LinkedList<Element> children = new LinkedList<>();
	private final TextFieldWidget textField;
	private final ButtonWidget saveButton;
	private final TextWidget title;
	private String originalValue;

	public ConfigPropertyWidget(TextRenderer textRenderer, String key, String value, IConfigProvider configProvider) {
		this.originalValue = value;

		textField = new TextFieldWidget(textRenderer, 0, 20, Text.literal(key));
		textField.setText(value);
		saveButton = ButtonWidget
				.builder(Text.literal("Set"), action -> {
					configProvider.set(key, textField.getText());
					originalValue = textField.getText();
				})
				.size(60, 20)
				.build();

		title = new TextWidget(0, 25, Text.literal(key), textRenderer);
		title.alignLeft();

		children.add(textField);
		children.add(title);
		children.add(saveButton);
	}

	@Override
	public List<? extends Element> children() {
		return children;
	}

	@Override
	public void render(DrawContext context, int index, int y, int x, int entryWidth, int entryHeight, int mouseX, int mouseY, boolean hovered, float tickDelta) {
		title.setX(x);
		title.setY(y);
		title.setWidth(entryWidth);

		textField.setX(x);
		textField.setY(y + entryHeight - 20);
		textField.setWidth(entryWidth - 60 - 10);
		textField.setText(textField.getText());

		saveButton.setX(x + entryWidth - 60);
		saveButton.setY(y + entryHeight - 20);
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
