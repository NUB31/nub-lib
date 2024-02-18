package com.nublib.config.screen;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.narration.NarrationMessageBuilder;
import net.minecraft.client.gui.widget.EntryListWidget;
import net.minecraft.util.math.ColorHelper;
import org.jetbrains.annotations.Nullable;

import java.awt.*;
import java.util.function.Consumer;

public class ConfigListWidget extends EntryListWidget<ConfigWidget> {
	public Consumer<ConfigWidget> onSelected;

	public ConfigListWidget(int w, int h, int y, Consumer<ConfigWidget> onConfigSelected) {
		super(MinecraftClient.getInstance(), w, h, y, 55);
		setRenderBackground(false);
		onSelected = onConfigSelected;
	}

	@Override
	public void setSelected(@Nullable ConfigWidget entry) {
		onSelected.accept(entry);
		super.setSelected(entry);
	}

	@Override
	protected void drawSelectionHighlight(DrawContext context, int y, int entryWidth, int entryHeight, int borderColor, int fillColor) {
		int i = this.getX() + (this.width - entryWidth) / 2;
		int j = this.getX() + (this.width + entryWidth) / 2;
		context.fill(i, y - 2, j, y + entryHeight + 2, borderColor);
		context.fill(i + 1, y - 1, j - 1, y + entryHeight + 1, ColorHelper.Abgr.withAlpha(100, Color.black.getRGB()));
	}

	@Override
	protected void appendClickableNarrations(NarrationMessageBuilder builder) {
	}
}
