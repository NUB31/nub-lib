package com.nublib.gui.widget.custom;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.Element;
import net.minecraft.client.gui.screen.narration.NarrationMessageBuilder;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.gui.widget.ClickableWidget;
import net.minecraft.client.gui.widget.ContainerWidget;
import net.minecraft.client.util.InputUtil;
import net.minecraft.screen.ScreenTexts;
import net.minecraft.text.Text;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

public class ToggleWidget extends ContainerWidget {
    private final List<ClickableWidget> children = new ArrayList<>();
    private final ButtonWidget button;
    @Nullable
    private final Consumer<Integer> onKeyChanged;
    @Nullable
    private ButtonWidget bindKeyButton = null;
    private boolean waitingForKeypress = false;
    private boolean value;

    public ToggleWidget(Boolean defaultValue, Consumer<Boolean> onClick, @Nullable Consumer<Integer> onKeyChanged, Optional<Integer> keyCode) {
        super(0, 0, 0, 0, Text.empty());
        this.onKeyChanged = onKeyChanged;
        value = defaultValue;

        this.button = ButtonWidget.builder(ScreenTexts.onOrOff(value), (b) -> {
            value = !value;
            onClick.accept(value);
            b.setMessage(ScreenTexts.onOrOff(value));
        }).build();

        if (onKeyChanged != null) {
            InputUtil.Key key = InputUtil.UNKNOWN_KEY;
            if (keyCode.isPresent() && keyCode.get() != 256) {
                key = InputUtil.fromKeyCode(keyCode.get(), -1);
            }

            bindKeyButton = ButtonWidget.builder(key.getLocalizedText(), (b) -> {
                waitingForKeypress = true;
            }).build();

            children.add(bindKeyButton);
        }

        children.add(button);
    }

    @Override
    public List<? extends Element> children() {
        return children;
    }

    @Override
    protected void renderWidget(DrawContext context, int mouseX, int mouseY, float delta) {
        int buttonWidth = getWidth();

        if (bindKeyButton != null) {
            if (waitingForKeypress) {
                bindKeyButton.setMessage(Text.literal("[Waiting]"));
            }

            int bindKeyButtonWidth = MinecraftClient.getInstance().textRenderer.getWidth(bindKeyButton.getMessage()) + 10;
            bindKeyButton.setDimensionsAndPosition(bindKeyButtonWidth, getHeight(), getX() + getWidth() - bindKeyButtonWidth, getY());
            bindKeyButton.render(context, mouseX, mouseY, delta);
            buttonWidth = getWidth() - bindKeyButton.getWidth() - 5;
        }

        button.setDimensionsAndPosition(buttonWidth, getHeight(), getX(), getY());
        button.render(context, mouseX, mouseY, delta);
    }

    @Override
    public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
        if (waitingForKeypress && bindKeyButton != null && onKeyChanged != null) {
            onKeyChanged.accept(keyCode);
            waitingForKeypress = false;
            bindKeyButton.setMessage(InputUtil.fromKeyCode(keyCode, scanCode).getLocalizedText());
            return true;
        } else {
            return super.keyPressed(keyCode, scanCode, modifiers);
        }
    }

    @Override
    protected void appendClickableNarrations(NarrationMessageBuilder builder) {
    }
}
