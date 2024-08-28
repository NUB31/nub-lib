package com.nublib.gui.widget.entry;

import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.narration.NarrationMessageBuilder;
import net.minecraft.client.gui.widget.ClickableWidget;
import net.minecraft.text.Text;

public class ContainerGuiConfigEntryBuilder extends AbstractGuiConfigEntryBuilder<Object> {
    public ContainerGuiConfigEntryBuilder() {
        super(null);
    }

    @Override
    public ClickableWidget createWidget() {
        return new ClickableWidget(0, 0, 0, 0, Text.empty()) {
            @Override
            protected void renderWidget(DrawContext context, int mouseX, int mouseY, float delta) {
            }

            @Override
            protected void appendClickableNarrations(NarrationMessageBuilder builder) {
            }
        };
    }
}
