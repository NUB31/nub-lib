package com.nublib.config.screen;

import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.Element;
import net.minecraft.client.gui.screen.narration.NarrationMessageBuilder;
import net.minecraft.client.gui.widget.ContainerWidget;
import net.minecraft.client.gui.widget.MultilineTextWidget;
import net.minecraft.client.gui.widget.TextWidget;
import net.minecraft.text.Text;
import net.minecraft.util.math.ColorHelper;

import java.awt.*;
import java.util.LinkedList;
import java.util.List;

public class ConfigDetailsWidget extends ContainerWidget {
	private final LinkedList<Element> children = new LinkedList<>();
	private final TextRenderer textRenderer;
	private String key = "";
	private String description = "";


	public ConfigDetailsWidget(int x, int y, int width, int height, TextRenderer textRenderer) {
		super(x, y, width, height, Text.empty());
		this.textRenderer = textRenderer;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public List<? extends Element> children() {
		return children;
	}

	@Override
	protected void renderWidget(DrawContext context, int mouseX, int mouseY, float delta) {
		int x = getX();
		int y = getY();
		int width = getWidth();
		int height = getHeight();

		context.fill(x, y, x + width, y + height, ColorHelper.Abgr.withAlpha(100, Color.black.getRGB()));

		x += 10;
		y += 10;
		width -= 20;
		height -= 20;

		new TextWidget(x, y, width, 20, Text.of(key), textRenderer)
				.alignLeft()
				.setTextColor(Color.white.getRGB()).render(context, mouseX, mouseY, delta);

		y += 20;

		new MultilineTextWidget(x, y, Text.of(description), textRenderer)
				.setMaxWidth(width - 20)
				.setTextColor(Color.white.getRGB())
				.render(context, mouseX, mouseY, delta);
	}

	@Override
	protected void appendClickableNarrations(NarrationMessageBuilder builder) {
	}
}
