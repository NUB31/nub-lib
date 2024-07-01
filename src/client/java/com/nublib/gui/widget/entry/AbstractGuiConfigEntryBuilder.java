package com.nublib.gui.widget.entry;

import com.nublib.gui.EntryListBuilder;
import net.minecraft.client.gui.widget.ClickableWidget;
import net.minecraft.text.Text;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public abstract class AbstractGuiConfigEntryBuilder<T> {
    protected Text title;
    protected Text description;
    protected Consumer<T> onChange;
    protected T defaultValue;
    protected List<GuiConfigEntry> configEntries;
    private Boolean hasChanged = false;

    public AbstractGuiConfigEntryBuilder(T defaultValue) {
        this.title = Text.empty();
        this.description = Text.empty();
        this.defaultValue = defaultValue;
        this.configEntries = new ArrayList<>();
        this.onChange = (v) -> this.hasChanged = !v.equals(defaultValue);
    }

    public AbstractGuiConfigEntryBuilder<T> setTitle(Text title) {
        this.title = title;
        return this;
    }

    public AbstractGuiConfigEntryBuilder<T> setDescription(Text description) {
        this.description = description;
        return this;
    }

    public AbstractGuiConfigEntryBuilder<T> onChange(Consumer<T> delegate) {
        onChange = (v) -> {
            this.hasChanged = !v.equals(defaultValue);
            delegate.accept(v);
        };
        return this;
    }

    public AbstractGuiConfigEntryBuilder<T> addChildEntries(Consumer<EntryListBuilder> delegate) {
        EntryListBuilder entryListBuilder = new EntryListBuilder();
        delegate.accept(entryListBuilder);
        configEntries.addAll(entryListBuilder.getConfigEntries());
        return this;
    }

    public abstract ClickableWidget createWidget();

    public GuiConfigEntry build() {
        return new GuiConfigEntry(title, description, this::createWidget, configEntries, () -> hasChanged);
    }
}