package com.nublib.gui.widget;

import com.nublib.gui.widget.entry.GuiConfigEntry;
import com.nublib.util.TooltipUtil;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.narration.NarrationMessageBuilder;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.gui.widget.ClickableWidget;
import net.minecraft.client.gui.widget.ContainerWidget;
import net.minecraft.client.gui.widget.TextWidget;
import net.minecraft.text.Text;
import net.minecraft.util.Colors;
import net.minecraft.util.math.ColorHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class EntryListWidget extends ContainerWidget {
    private final List<Entry> entries = new ArrayList<>();
    private double scrollOffset = 0;
    private int contentHeight = 0;
    private boolean isScrolling = false;
    private double lastMouseY = 0;

    public EntryListWidget(int x, int y, int width, int height) {
        super(x, y, width, height, Text.literal("Entry list"));
    }

    @Override
    public boolean mouseScrolled(double mouseX, double mouseY, double horizontalAmount, double verticalAmount) {
        scrollOffset = Math.clamp(scrollOffset + (verticalAmount * 10), Math.min(-(contentHeight - getHeight()), 0), 0);
        return true;
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        if (isMouseOverScrollbar(mouseX, mouseY)) {
            isScrolling = true;
            lastMouseY = mouseY;
            return true;
        }
        return super.mouseClicked(mouseX, mouseY, button);
    }

    @Override
    public boolean mouseReleased(double mouseX, double mouseY, int button) {
        if (isScrolling) {
            isScrolling = false;
            return true;
        }
        return super.mouseReleased(mouseX, mouseY, button);
    }

    @Override
    public boolean mouseDragged(double mouseX, double mouseY, int button, double deltaX, double deltaY) {
        if (isScrolling) {
            double delta = mouseY - lastMouseY;
            scrollOffset = Math.clamp(scrollOffset - delta * (contentHeight / (double) getHeight()), Math.min(-(contentHeight - getHeight()), 0), 0);
            lastMouseY = mouseY;
            return true;
        }
        return super.mouseDragged(mouseX, mouseY, button, deltaX, deltaY);
    }

    private boolean isMouseOverScrollbar(double mouseX, double mouseY) {
        int scrollbarX = getX() + getWidth() - 6;
        return mouseX >= scrollbarX && mouseX < scrollbarX + 6 && mouseY >= getY() && mouseY < getY() + getHeight();
    }

    public void setConfigEntries(List<GuiConfigEntry> entries) {
        this.entries.clear();

        for (GuiConfigEntry entry : entries) {
            this.entries.add(constructEntry(entry));
        }

        scrollOffset = 0;
    }

    private Entry constructEntry(GuiConfigEntry entry) {
        TextWidget textWidget = new TextWidget(entry.title(), MinecraftClient.getInstance().textRenderer);
        textWidget.alignLeft();

        Entry converted = new Entry(entry, entry.widget().get(), textWidget);
        if (!entry.children().isEmpty()) {
            converted.buttonWidget = Optional.of(ButtonWidget.builder(Text.literal("↑"), b -> {
                converted.collapsed = Optional.of(!converted.collapsed.orElse(true));
                if (converted.collapsed.get()) {
                    b.setMessage(Text.literal("↑"));
                } else {
                    b.setMessage(Text.literal("↓"));
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
        int offsetY = getY() + (int) scrollOffset;
        int startPos = offsetY;

        int clampedWidth;
        if (getWidth() >= 1000) {
            clampedWidth = 950;
        } else {
            clampedWidth = getWidth() - 50;
        }

        int x = (getWidth() - clampedWidth) / 2;

        context.enableScissor(getX(), getY(), getX() + getWidth(), getY() + getHeight());

        for (Entry entry : entries) {
            offsetY += renderEntry(context, entry, x, offsetY, clampedWidth, mouseX, mouseY, delta) + 10;
        }

        contentHeight = offsetY - startPos;
        adjustScrollOffset();
        context.disableScissor();

        drawScrollbar(context, mouseX, mouseY);
    }

    private int renderEntry(DrawContext context, Entry entry, int x, int y, int width, int mouseX, int mouseY, float delta) {
        int localY = y + renderRow(context, entry, x, y, width, mouseX, mouseY, delta);

        boolean hovered = mouseX >= x && mouseY >= y && mouseX < x + width && mouseY < localY;
        if (hovered && !entry.entry.description().getString().isEmpty()) {
            context.drawTooltip(MinecraftClient.getInstance().textRenderer, TooltipUtil.wrapText(entry.entry.description(), 300), mouseX, mouseY);
        }

        if (!entry.collapsed.orElse(true)) {
            for (Entry child : entry.children) {
                localY += renderEntry(context, child, x + 20, localY, width - 20, mouseX, mouseY, delta);
            }
        }

        return localY - y;
    }

    private int renderRow(DrawContext context, Entry entry, int x, int y, int width, int mouseX, int mouseY, float delta) {
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

        entry.textWidget.setDimensionsAndPosition(Math.max(0, width - (buttonWidth == 0 ? 0 : buttonWidth + 10) - 200 - 20), actualHeight, x + 10, actualY);
        entry.textWidget.render(context, mouseX, mouseY, delta);

        return height;
    }

    @Override
    protected void appendClickableNarrations(NarrationMessageBuilder builder) {
    }

    private void adjustScrollOffset() {
        scrollOffset = Math.max(Math.min(scrollOffset, 0), Math.min(-(contentHeight - getHeight()), 0));
    }

    private void drawScrollbar(DrawContext context, int mouseX, int mouseY) {
        if (contentHeight <= getHeight()) {
            return;
        }

        int scrollbarHeight = (int) ((float) getHeight() / contentHeight * getHeight());
        int scrollbarY = getY() - (int) (scrollOffset / contentHeight * getHeight());

        int color = ColorHelper.Argb.getArgb(191, 128, 128, 128);
        if (isMouseOverScrollbar(mouseX, mouseY)) {
            color = ColorHelper.Argb.getArgb(255, 128, 128, 128);
        }

        int scrollbarX = getX() + getWidth() - 6;
        context.fill(scrollbarX, scrollbarY, scrollbarX + 6, scrollbarY + scrollbarHeight, color);
    }
}
