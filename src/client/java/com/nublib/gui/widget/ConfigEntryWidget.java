package com.nublib.gui.widget;

import com.nublib.gui.widget.entry.GuiConfigEntry;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.Element;
import net.minecraft.client.gui.Selectable;
import net.minecraft.client.gui.widget.ClickableWidget;
import net.minecraft.client.gui.widget.ElementListWidget;
import net.minecraft.client.gui.widget.MultilineTextWidget;
import net.minecraft.client.gui.widget.TextWidget;
import net.minecraft.text.Text;

import java.util.ArrayList;
import java.util.List;

public class ConfigEntryWidget extends ElementListWidget.Entry<ConfigEntryWidget> {
	private final List<? extends Selectable> selectableChildren = new ArrayList<>();
	private final List<ClickableWidget> children = new ArrayList<>();

	private final TextWidget label;
	private final MultilineTextWidget description;
	private final ClickableWidget control;

	public ConfigEntryWidget(GuiConfigEntry configEntry) {
		Text title;
		if (configEntry.title().getString().isEmpty()) {
			title = configEntry.widget().getMessage();
		} else {
			title = configEntry.title();
		}

		this.control = configEntry.widget();
		this.label = new TextWidget(title, MinecraftClient.getInstance().textRenderer);
		this.description = new MultilineTextWidget(configEntry.description(), MinecraftClient.getInstance().textRenderer);

		children.add(this.control);
		children.add(this.label);
		children.add(this.description);
	}

	@Override
	public List<? extends Selectable> selectableChildren() {
		return selectableChildren;
	}

	@Override
	public List<? extends Element> children() {
		return children;
	}

	@Override
	public void render(DrawContext context, int index, int y, int x, int entryWidth, int entryHeight, int mouseX, int mouseY, boolean hovered, float tickDelta) {
		label.setWidth(entryWidth);
		label.setY(y);
		label.setX(x);
		label.render(context, mouseX, mouseY, tickDelta);

		control.setWidth(entryWidth);
		control.setY(y + entryHeight - control.getHeight() - 10);
		control.setX(x);
		control.render(context, mouseX, mouseY, tickDelta);

		int descriptionHeight = entryHeight - label.getHeight() - control.getHeight() - 25;

		description.setMaxWidth(entryWidth);
		description.setMaxRows(descriptionHeight / MinecraftClient.getInstance().textRenderer.fontHeight);
		description.setY(y + label.getHeight() + 10);
		description.setX(x);
		description.render(context, mouseX, mouseY, tickDelta);
	}
}
