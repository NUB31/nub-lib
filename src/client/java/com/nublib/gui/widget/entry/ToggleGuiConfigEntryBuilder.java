package com.nublib.gui.widget.entry;

import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.gui.widget.ClickableWidget;
import net.minecraft.screen.ScreenTexts;

public class ToggleGuiConfigEntryBuilder extends AbstractGuiConfigEntryBuilder<Boolean> {
    private Boolean value;

    public ToggleGuiConfigEntryBuilder(Boolean defaultValue) {
        super(defaultValue);
        value = defaultValue;
    }

    @Override
    public ClickableWidget createWidget() {
        return ButtonWidget.builder(ScreenTexts.onOrOff(value), (b) -> {
            value = !value;
            onChange.accept(value);
            b.setMessage(ScreenTexts.onOrOff(value));
        }).build();
    }
}
