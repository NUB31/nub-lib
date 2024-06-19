package com.nublib.gui;

import com.nublib.config.entry.IClientConfigEntry;
import com.nublib.gui.widget.entry.*;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class EntryListBuilder {
    private final List<GuiConfigEntry> configEntries;

    public EntryListBuilder() {
        configEntries = new ArrayList<>();
    }

    public EntryListBuilder addConfigEntry(GuiConfigEntry configEntry) {
        configEntries.add(configEntry);
        return this;
    }

    public EntryListBuilder addRange(Integer defaultValue, Integer minValue, Integer maxValue, Consumer<RangeGuiConfigEntryBuilder> builderConsumer) {
        RangeGuiConfigEntryBuilder builder = new RangeGuiConfigEntryBuilder(defaultValue, minValue, maxValue);
        builderConsumer.accept(builder);
        addConfigEntry(builder.build());
        return this;
    }

    public EntryListBuilder addString(String defaultValue, Consumer<StringGuiConfigEntryBuilder> builderConsumer) {
        StringGuiConfigEntryBuilder builder = new StringGuiConfigEntryBuilder(defaultValue);
        builderConsumer.accept(builder);
        addConfigEntry(builder.build());
        return this;
    }

    public EntryListBuilder addToggle(Boolean defaultValue, Consumer<ToggleGuiConfigEntryBuilder> builderConsumer) {
        ToggleGuiConfigEntryBuilder builder = new ToggleGuiConfigEntryBuilder(defaultValue);
        builderConsumer.accept(builder);
        addConfigEntry(builder.build());
        return this;
    }

    public <T extends Enum<T>> EntryListBuilder addEnum(T defaultValue, Class<T> enumClass, Consumer<EnumGuiConfigEntryBuilder<T>> builderConsumer) {
        EnumGuiConfigEntryBuilder<T> builder = new EnumGuiConfigEntryBuilder<>(defaultValue, enumClass);
        builderConsumer.accept(builder);
        addConfigEntry(builder.build());
        return this;
    }

    public EntryListBuilder fromConfigEntry(IClientConfigEntry<?> entry) {
        addConfigEntry(entry.guiConfigEntry());
        return this;
    }

    public List<GuiConfigEntry> getConfigEntries() {
        return configEntries;
    }
}
