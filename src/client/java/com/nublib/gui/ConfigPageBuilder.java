package com.nublib.gui;

import com.nublib.config.entry.IClientConfigEntry;
import com.nublib.gui.widget.entry.*;
import net.minecraft.text.Text;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class ConfigPageBuilder {
    private final List<GuiConfigEntry> configEntries;
    private final Text title;

    public ConfigPageBuilder(Text title) {
        this.title = title;
        this.configEntries = new ArrayList<>();
    }

    public ConfigPageBuilder addConfigEntry(GuiConfigEntry configEntry) {
        configEntries.add(configEntry);
        return this;
    }

    public ConfigPageBuilder addRange(Integer defaultValue, Integer minValue, Integer maxValue, Consumer<RangeGuiConfigEntryBuilder> builderConsumer) {
        RangeGuiConfigEntryBuilder builder = new RangeGuiConfigEntryBuilder(defaultValue, minValue, maxValue);
        builderConsumer.accept(builder);
        addConfigEntry(builder.build());
        return this;
    }

    public ConfigPageBuilder addString(String defaultValue, Consumer<StringGuiConfigEntryBuilder> builderConsumer) {
        StringGuiConfigEntryBuilder builder = new StringGuiConfigEntryBuilder(defaultValue);
        builderConsumer.accept(builder);
        addConfigEntry(builder.build());
        return this;
    }

    public ConfigPageBuilder addToggle(Boolean defaultValue, Consumer<ToggleGuiConfigEntryBuilder> builderConsumer) {
        ToggleGuiConfigEntryBuilder builder = new ToggleGuiConfigEntryBuilder(defaultValue);
        builderConsumer.accept(builder);
        addConfigEntry(builder.build());
        return this;
    }

    public <T extends Enum<T>> ConfigPageBuilder addEnum(T defaultValue, Class<T> enumClass, Consumer<EnumGuiConfigEntryBuilder<T>> builderConsumer) {
        EnumGuiConfigEntryBuilder<T> builder = new EnumGuiConfigEntryBuilder<>(defaultValue, enumClass);
        builderConsumer.accept(builder);
        addConfigEntry(builder.build());
        return this;
    }

    public ConfigPageBuilder fromConfigEntry(IClientConfigEntry<?> entry) {
        addConfigEntry(entry.guiConfigEntry());
        return this;
    }

    public ConfigPage build() {
        return new ConfigPage(title, configEntries);
    }
}
