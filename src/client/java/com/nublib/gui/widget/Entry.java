package com.nublib.gui.widget;

import com.nublib.gui.widget.entry.GuiConfigEntry;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.gui.widget.ClickableWidget;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public final class Entry {
    public final GuiConfigEntry entry;
    public final ClickableWidget widget;
    public Optional<ButtonWidget> buttonWidget;
    public Optional<Boolean> collapsed;
    public List<Entry> children;

    public Entry(GuiConfigEntry entry, ClickableWidget widget) {
        this.entry = entry;
        this.widget = widget;
        this.buttonWidget = Optional.empty();
        this.collapsed = Optional.empty();
        this.children = new ArrayList<>();
    }
}
