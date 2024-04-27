package com.nublib.config.screen.page.section;

import com.nublib.config.screen.page.section.option.IOption;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.narration.NarrationMessageBuilder;
import net.minecraft.client.gui.widget.ClickableWidget;
import net.minecraft.text.Text;

import java.util.List;

public class ConfigSection extends ClickableWidget {
	private final List<IOption> options;
	private final TextRenderer textRenderer;

	public ConfigSection(TextRenderer textRenderer, Text message, List<IOption> options) {
		super(0, 0, 0, 0, message);
		this.options = options;
		this.textRenderer = textRenderer;
	}

	@Override
	protected void renderWidget(DrawContext context, int mouseX, int mouseY, float delta) {
		context.drawTextWithShadow(textRenderer, getMessage(), 0, 0, 0);
	}

	@Override
	protected void appendClickableNarrations(NarrationMessageBuilder builder) {
	}
}
