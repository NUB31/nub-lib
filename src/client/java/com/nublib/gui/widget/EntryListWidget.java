package com.nublib.gui.widget;

import com.nublib.gui.widget.entry.GuiConfigEntry;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.narration.NarrationMessageBuilder;
import net.minecraft.client.gui.widget.ClickableWidget;
import net.minecraft.client.gui.widget.ContainerWidget;
import net.minecraft.text.Text;
import net.minecraft.util.Colors;
import net.minecraft.util.math.ColorHelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EntryListWidget extends ContainerWidget {
    private final List<ClickableWidget> children;
    private final Map<GuiConfigEntry, ClickableWidget> childEntryMap = new HashMap<>();
    private final List<GuiConfigEntry> topLevelEntries;

    public EntryListWidget(int x, int y, int width, int height) {
        super(x, y, width, height, Text.literal("Entry list"));
        children = new ArrayList<>();
        topLevelEntries = new ArrayList<>();
    }

    public void setConfigEntries(List<GuiConfigEntry> entries) {
        children.clear();
        childEntryMap.clear();
        for (GuiConfigEntry entry : entries) {
            ClickableWidget widget = entry.widget().get();
            topLevelEntries.add(entry);
            addChildrenRecursively(entry, widget);
        }
    }

    private void addChildrenRecursively(GuiConfigEntry entry, ClickableWidget widget) {
        childEntryMap.put(entry, widget);
        children.add(widget);
        entry.children().forEach(e -> addChildrenRecursively(e, e.widget().get()));
    }

    @Override
    public List<ClickableWidget> children() {
        return children;
    }

    @Override
    protected void renderWidget(DrawContext context, int mouseX, int mouseY, float delta) {
        int offsetY = getY();
        int paddedX = getX() + 10;
        int paddedWidth = getWidth() - 20;

        for (GuiConfigEntry entry : topLevelEntries) {
            offsetY += renderEntry(context, entry, paddedX, offsetY, paddedWidth, mouseX, mouseY, delta) + 10;
        }
    }

    private int renderEntry(DrawContext context, GuiConfigEntry entry, int x, int y, int width, int mouseX, int mouseY, float delta) {
        int height = 0;

        context.drawCenteredTextWithShadow(MinecraftClient.getInstance().textRenderer, entry.title(), x + (width / 2), y, Colors.WHITE);
        height += MinecraftClient.getInstance().textRenderer.fontHeight + 5;

        ClickableWidget widget = childEntryMap.get(entry);
        widget.setDimensionsAndPosition(width - 10, 20, x + 13, y + height);
        widget.render(context, mouseX, mouseY, delta);
        height += widget.getHeight() + 10;

        context.fill(x, y, x + 3, y + height, ColorHelper.Argb.withAlpha(61, Colors.ALTERNATE_WHITE));
        context.fill(getX(), y, x, y + height, ColorHelper.Argb.withAlpha(30, Colors.ALTERNATE_WHITE));

        for (GuiConfigEntry child : entry.children()) {
            height += renderEntry(context, child, x + 20, y + height, width - 20, mouseX, mouseY, delta);
        }

        return height;
    }

    @Override
    protected void appendClickableNarrations(NarrationMessageBuilder builder) {
    }
}
