package com.nublib.gui.widget.entry;

import net.minecraft.client.gui.widget.ClickableWidget;
import net.minecraft.text.Text;

import java.util.List;
import java.util.function.Supplier;

public record GuiConfigEntry(
        Text title,
        Text description,
        Supplier<ClickableWidget> widget,
        List<GuiConfigEntry> children,
        Supplier<Boolean> hasChanged
) {
}
