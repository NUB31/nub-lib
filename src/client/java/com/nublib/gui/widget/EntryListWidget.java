package com.nublib.gui.widget;

import com.nublib.gui.widget.entry.GuiConfigEntry;
import com.nublib.util.TooltipUtil;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.narration.NarrationMessageBuilder;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.gui.widget.ClickableWidget;
import net.minecraft.client.gui.widget.ContainerWidget;
import net.minecraft.text.Text;
import net.minecraft.util.Colors;
import net.minecraft.util.math.ColorHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class EntryListWidget extends ContainerWidget {
    private final List<Entry> entries = new ArrayList<>();

    public EntryListWidget(int x, int y, int width, int height) {
        super(x, y, width, height, Text.literal("Entry list"));
    }

    public void setConfigEntries(List<GuiConfigEntry> entries) {
        this.entries.clear();

        for (GuiConfigEntry entry : entries) {
            this.entries.add(constructEntry(entry));
        }
    }

    private Entry constructEntry(GuiConfigEntry entry) {
        Entry converted = new Entry(entry, entry.widget().get());
        if (!entry.children().isEmpty()) {
            converted.buttonWidget = Optional.of(ButtonWidget.builder(Text.literal("↓"), b -> {
                converted.collapsed = Optional.of(!converted.collapsed.orElse(true));
                if (converted.collapsed.get()) {
                    b.setMessage(Text.literal("↓"));
                } else {
                    b.setMessage(Text.literal("↑"));
                }
            }).build());
            converted.collapsed = Optional.of(true);
            for (GuiConfigEntry child : entry.children()) {
                converted.children.add(constructEntry(child));
            }
        }

        return converted;
    }

    @Override
    public List<ClickableWidget> children() {
        List<ClickableWidget> children = new ArrayList<>();
        entries.forEach(e -> getChildren(e, children));
        return children;
    }

    private void getChildren(Entry entry, List<ClickableWidget> list) {
        list.add(entry.widget);
        entry.buttonWidget.ifPresent(list::add);
        entry.children.forEach(e -> {
            if (!entry.collapsed.orElse(true))
                getChildren(e, list);
        });
    }

    @Override
    protected void renderWidget(DrawContext context, int mouseX, int mouseY, float delta) {
        int offsetY = getY();
        int paddedX = getX() + 100;
        int paddedWidth = getWidth() - 200;

        for (Entry entry : entries) {
            offsetY += renderEntry(MinecraftClient.getInstance().textRenderer, context, entry, paddedX, offsetY, paddedWidth, mouseX, mouseY, delta) + 10;
        }
    }

    private int renderEntry(TextRenderer textRenderer, DrawContext context, Entry entry, int x, int y, int width, int mouseX, int mouseY, float delta) {
        int localY = y + renderRow(textRenderer, context, entry, x, y, width, mouseX, mouseY, delta);

        boolean hovered = mouseX >= x && mouseY >= y && mouseX < x + width && mouseY < localY;
        if (hovered && !entry.entry.description().getString().isEmpty()) {
            context.drawTooltip(MinecraftClient.getInstance().textRenderer, TooltipUtil.wrapText(entry.entry.description(), 300), mouseX, mouseY);
        }

        if (!entry.collapsed.orElse(true)) {
            for (Entry child : entry.children) {
                localY += renderEntry(textRenderer, context, child, x + 20, localY, width - 20, mouseX, mouseY, delta);
            }
        }

        return localY - y;
    }

    private int renderRow(TextRenderer textRenderer, DrawContext context, Entry entry, int x, int y, int width, int mouseX, int mouseY, float delta) {
        final int height = 26;
        final int actualY = y + 3;
        final int actualHeight = height - 6;

        int buttonWidth = 0;
        if (entry.buttonWidget.isPresent()) {
            entry.buttonWidget.get().setDimensionsAndPosition(30, actualHeight, x + width - 30, actualY);
            entry.buttonWidget.get().render(context, mouseX, mouseY, delta);
            buttonWidth = entry.buttonWidget.get().getWidth();
        }

        entry.widget.setDimensionsAndPosition(200, actualHeight, x + width - 200 - (buttonWidth == 0 ? 0 : buttonWidth + 10), actualY);
        entry.widget.render(context, mouseX, mouseY, delta);

        int color = entry.entry.hasChanged().get() ? Colors.GREEN : Colors.ALTERNATE_WHITE;
        context.fill(x, y, x + 3, y + height, ColorHelper.Argb.withAlpha(127, color));
        context.drawTextWithShadow(textRenderer, entry.entry.title(), x + 10, actualY + (actualHeight - textRenderer.fontHeight) / 2, Colors.WHITE);

        return height;
    }

    @Override
    protected void appendClickableNarrations(NarrationMessageBuilder builder) {
    }
}
