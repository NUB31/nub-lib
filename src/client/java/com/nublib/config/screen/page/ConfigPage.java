package com.nublib.config.screen.page;

import com.nublib.config.screen.page.section.ConfigSection;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.narration.NarrationMessageBuilder;
import net.minecraft.client.gui.widget.ClickableWidget;
import net.minecraft.text.Text;

import java.util.List;

public class ConfigPage extends ClickableWidget {
	private final List<ConfigSection> configSections;
	private final TextRenderer textRenderer;

	public ConfigPage(TextRenderer textRenderer, Text message, List<ConfigSection> configSections) {
		super(0, 0, 0, 0, message);
		this.configSections = configSections;
		this.textRenderer = textRenderer;
	}

	public List<ConfigSection> getConfigSections() {
		return configSections;
	}

	@Override
	protected void renderWidget(DrawContext context, int mouseX, int mouseY, float delta) {
		context.drawTextWithShadow(textRenderer, getMessage(), 0, 0, 0);
	}

	@Override
	protected void appendClickableNarrations(NarrationMessageBuilder builder) {

	}
}
