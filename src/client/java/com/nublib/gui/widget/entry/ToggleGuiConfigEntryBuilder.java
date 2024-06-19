package com.nublib.gui.widget.entry;

import com.nublib.gui.widget.custom.ToggleWidget;
import net.minecraft.client.gui.widget.ClickableWidget;

public class ToggleGuiConfigEntryBuilder extends AbstractGuiConfigEntryBuilder<Boolean> {
    public ToggleGuiConfigEntryBuilder(Boolean defaultValue) {
        super(defaultValue);
    }

    @Override
    public ClickableWidget createWidget() {
        return new ToggleWidget(0, 0, 0, defaultValue, onChange);
    }
}
