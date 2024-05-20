package com.nublib.gui.widget.custom;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.screen.ScreenTexts;

import java.awt.*;
import java.util.function.Consumer;

public class ToggleWidget extends AbstractCustomWidget {
	private final Consumer<Boolean> onChange;
	private final TextRenderer textRenderer;
	private Boolean value;

	public ToggleWidget(int x, int y, int width, Boolean defaultValue, Consumer<Boolean> onChange) {
		super(x, y, width, 20);
		this.value = defaultValue;
		this.onChange = onChange;
		this.textRenderer = MinecraftClient.getInstance().textRenderer;
	}

	@Override
	public void onClick(double mouseX, double mouseY) {
		value = !value;
		this.onChange.accept(value);
	}

	@Override
	protected void render(DrawContext context, int x, int y, int width, int height, int mouseX, int mouseY, boolean hovered, float delta) {
		int textColor = value ? Color.decode("#59c279").getRGB() : Color.decode("#d94848").getRGB();
		context.drawCenteredTextWithShadow(textRenderer, ScreenTexts.onOrOff(value), x + (width / 2), y + (height / 2) - (textRenderer.fontHeight / 2), textColor);
	}
}
