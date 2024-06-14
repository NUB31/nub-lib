package com.nublib.gui.widget.custom;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.util.Colors;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

public class MultiSelectWidget<T extends Enum<T>> extends AbstractCustomWidget {
    private final Consumer<T> onChange;
    private final List<T> values;
    private T value;

    public MultiSelectWidget(int x, int y, int width, T defaultValue, Consumer<T> onChange, Class<T> enumClass) {
        super(x, y, width, 20);
        this.value = defaultValue;
        this.onChange = onChange;
        values = Arrays.asList(enumClass.getEnumConstants());
    }

    @Override
    public void onClick(double mouseX, double mouseY) {
        int index = values.indexOf(value);
        if (index == -1 || index >= values.size() - 1) {
            value = values.getFirst();
        } else {
            value = values.get(index + 1);
        }

        onChange.accept(value);
    }

    @Override
    protected void render(DrawContext context, int x, int y, int width, int height, int mouseX, int mouseY, boolean hovered, float delta) {
        TextRenderer textRenderer = MinecraftClient.getInstance().textRenderer;
        context.drawCenteredTextWithShadow(textRenderer, value.toString(), x + (width / 2), y + (height / 2) - (textRenderer.fontHeight / 2), Colors.WHITE);
    }
}
