package com.nublib.config.screen.elements;

import com.nublib.config.screen.page.section.Option;
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
	private final Option option;
	private final TextRenderer textRenderer;

	public ConfigEntry(Option option, TextRenderer textRenderer) {
		this.option = option;
		this.textRenderer = textRenderer;
		children.add(option.getControl().getWidget(textRenderer, 0, 0, 0, 0));
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
		int color = Color.decode("#33339A").getRGB();
		context.fill(x, y, x + entryWidth, y + entryHeight, color);

		int dynamicY = y + 10;
		int dynamicX = x + 10;
		int dynamicWidth = entryWidth - 20 - 8; // -8 to compensate for scrollbar width
		int dynamicHeight = entryHeight - 20;

		option.getControl().getWidget(textRenderer, dynamicX, dynamicY + dynamicHeight - 20, dynamicWidth, 20).render(context, mouseX, mouseY, tickDelta);

		dynamicHeight -= 25;

		if (!Objects.equals(option.getLabel().getLiteralString(), "")) {
			TextWidget title = new TextWidget(dynamicX, dynamicY, dynamicWidth, textRenderer.fontHeight, option.getLabel(), textRenderer).alignLeft();
			title.render(context, mouseX, mouseY, tickDelta);

			dynamicY += title.getHeight() + 5;
		}

		if (!Objects.equals(option.getDescription().getLiteralString(), "")) {
			MultilineTextWidget description = new MultilineTextWidget(dynamicX, dynamicY, option.getDescription(), textRenderer)
					.setTextColor(Color.decode("#bababa").getRGB())
					.setMaxWidth(dynamicWidth)
					.setMaxRows(dynamicHeight / textRenderer.fontHeight - 1);

			description.render(context, mouseX, mouseY, tickDelta);
			dynamicY += description.getHeight() + 5;
		}
	}
}
