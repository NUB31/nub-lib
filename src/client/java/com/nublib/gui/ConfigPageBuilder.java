package com.nublib.gui;

import com.nublib.gui.widget.entry.GuiConfigEntry;
import net.minecraft.text.Text;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class ConfigPageBuilder {
    private final Text title;
    protected List<GuiConfigEntry> configEntries;

    public ConfigPageBuilder(Text title) {
        this.title = title;
        this.configEntries = new ArrayList<>();
    }

    public ConfigPageBuilder addEntries(Consumer<EntryListBuilder> delegate) {
        EntryListBuilder entryListBuilder = new EntryListBuilder();
        delegate.accept(entryListBuilder);
        configEntries.addAll(entryListBuilder.getConfigEntries());
        return this;
    }

    public ConfigPage build() {
        return new ConfigPage(title, configEntries);
    }
}
