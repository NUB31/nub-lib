package com.nublib.gui.widget.custom;

import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.narration.NarrationMessageBuilder;
import net.minecraft.client.gui.widget.ClickableWidget;
import net.minecraft.text.Text;
import net.minecraft.util.Colors;
import net.minecraft.util.math.ColorHelper;

public abstract class BaseCustomWidget extends ClickableWidget {
	public BaseCustomWidget(int x, int y, int width, int height, Text message) {
		super(x, y, width, height, message);
	}

	@Override
	protected void appendClickableNarrations(NarrationMessageBuilder builder) {
	}

	@Override
	protected void renderWidget(DrawContext context, int mouseX, int mouseY, float delta) {
		int x = getX();
		int y = getY();
		int width = getWidth();
		int height = getHeight();

		context.fill(x, y, x + width, y + height, ColorHelper.Argb.withAlpha(63, Colors.WHITE));
		int borderColor = isHovered() ? Colors.ALTERNATE_WHITE : Colors.LIGHT_GRAY;
		context.drawBorder(x, y, width, height, borderColor);

		render(context, x, y, width, height, mouseX, mouseY, isHovered(), delta);
	}

	protected abstract void render(DrawContext context, int x, int y, int width, int height, int mouseX, int mouseY, boolean hovered, float delta);
}
