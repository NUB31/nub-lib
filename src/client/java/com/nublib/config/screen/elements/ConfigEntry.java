package com.nublib.config.screen.elements;

import com.nublib.config.screen.model.section.Option;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.Element;
import net.minecraft.client.gui.Selectable;
import net.minecraft.client.gui.widget.ClickableWidget;
import net.minecraft.client.gui.widget.ElementListWidget;
import net.minecraft.client.gui.widget.MultilineTextWidget;
import net.minecraft.client.gui.widget.TextWidget;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ConfigEntry extends ElementListWidget.Entry<ConfigEntry> {
	private final List<ClickableWidget> children = new ArrayList<>();
	private final Option<?> configOption;
	private final TextRenderer textRenderer;

	public ConfigEntry(Option<?> configOption, TextRenderer textRenderer) {
		this.configOption = configOption;
		this.textRenderer = textRenderer;
	}

	@Override
	public List<? extends Selectable> selectableChildren() {
		return new ArrayList<>();
	}

	@Override
	public List<? extends Element> children() {
		return children;
	}

	@Override
	public void render(DrawContext context, int index, int y, int x, int entryWidth, int entryHeight, int mouseX, int mouseY, boolean hovered, float tickDelta) {
		children.clear();

		int dynamicY = y + 10;
		int dynamicX = x + 10;
		int dynamicWidth = entryWidth - 20 - 8; // -8 to compensate for scrollbar width
		int dynamicHeight = entryHeight - 20;

		ClickableWidget configWidget = configOption
				.getControl()
				.getOrCreateWidget(textRenderer, dynamicX, dynamicY + dynamicHeight - 20, dynamicWidth, 20);

		configWidget.render(context, mouseX, mouseY, tickDelta);
		children.add(configWidget);

		dynamicHeight -= 25;

		if (!Objects.equals(configOption.getLabel().getLiteralString(), "")) {
			TextWidget title = new TextWidget(dynamicX, dynamicY, dynamicWidth, textRenderer.fontHeight, configOption.getLabel(), textRenderer).alignLeft();
			title.render(context, mouseX, mouseY, tickDelta);
			children.add(title);

			dynamicY += title.getHeight() + 5;
		}

		if (!Objects.equals(configOption.getDescription().getLiteralString(), "")) {
			MultilineTextWidget description = new MultilineTextWidget(dynamicX, dynamicY, configOption.getDescription(), textRenderer)
					.setTextColor(Color.decode("#bababa").getRGB())
					.setMaxWidth(dynamicWidth)
					.setMaxRows(dynamicHeight / textRenderer.fontHeight - 1);
			description.render(context, mouseX, mouseY, tickDelta);
			children.add(description);
			dynamicY += description.getHeight() + 5;
		}
	}
}
