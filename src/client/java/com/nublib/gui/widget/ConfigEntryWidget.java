package com.nublib.gui.widget;

import com.nublib.gui.widget.entry.GuiConfigEntry;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.Element;
import net.minecraft.client.gui.Selectable;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.gui.widget.ClickableWidget;
import net.minecraft.client.gui.widget.ElementListWidget;
import net.minecraft.client.gui.widget.TextWidget;
import net.minecraft.text.Text;
import net.minecraft.util.Colors;
import net.minecraft.util.math.ColorHelper;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class ConfigEntryWidget extends ElementListWidget.Entry<ConfigEntryWidget> {
	private final List<? extends Selectable> selectableChildren = new ArrayList<>();
	private final List<ClickableWidget> children = new ArrayList<>();

	private final TextWidget label;
	private final ClickableWidget control;
	private final @Nullable ButtonWidget resetButton;
	private final Text description;

	public ConfigEntryWidget(GuiConfigEntry configEntry) {
		Text title;
		if (configEntry.title().getString().isEmpty()) {
			title = configEntry.widget().getMessage();
		} else {
			title = configEntry.title();
		}

		this.description = configEntry.description();
		this.control = configEntry.widget();
		this.label = new TextWidget(title, MinecraftClient.getInstance().textRenderer);

		Runnable resetDelegate = configEntry.resetDelegate();
		if (resetDelegate != null) {
			Text buttonText = Text.literal("Reset");
			this.resetButton = ButtonWidget
					.builder(buttonText, button -> {
						resetDelegate.run();
					})
					.width(MinecraftClient.getInstance().textRenderer.getWidth(buttonText) + 10)
					.build();

			children.add(this.resetButton);
		} else {
			resetButton = null;
		}

		children.add(this.control);
		children.add(this.label);
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
		if (hovered) {
			context.fill(x, y, x + entryWidth, y + entryHeight, ColorHelper.Argb.withAlpha(63, Colors.LIGHT_GRAY));
			context.drawBorder(x, y, entryWidth, entryHeight, Colors.LIGHT_GRAY);
		}

		final int padding = 10;

		label.setWidth(entryWidth - (padding * 2));
		label.setY(y + padding);
		label.setX(x + padding);
		label.render(context, mouseX, mouseY, tickDelta);

		if (resetButton != null) {
			resetButton.setX(x + entryWidth - padding - resetButton.getWidth());
			resetButton.setY(y + entryHeight - resetButton.getHeight() - padding);
			resetButton.setHeight(control.getHeight());
			resetButton.render(context, mouseX, mouseY, tickDelta);
		}

		int controlWidth = resetButton == null ? entryWidth - (padding * 2) : entryWidth - (padding * 3) - resetButton.getWidth();
		control.setWidth(controlWidth);
		control.setY(y + entryHeight - control.getHeight() - padding);
		control.setX(x + padding);
		control.render(context, mouseX, mouseY, tickDelta);

		if (hovered && !description.getString().isEmpty()) {
			context.drawTooltip(MinecraftClient.getInstance().textRenderer, description, mouseX, mouseY);
		}
	}
}
