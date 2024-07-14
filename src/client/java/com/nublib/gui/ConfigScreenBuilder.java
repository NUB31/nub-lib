package com.nublib.gui;

import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class ConfigScreenBuilder {
    private final List<ConfigPage> configPages;
    @Nullable
    Screen parent;
    private Runnable onSave;
    @Nullable
    private Runnable onCancel;

    public ConfigScreenBuilder() {
        this.onSave = () -> {
        };
        this.onCancel = null;
        this.configPages = new ArrayList<>();
    }

    public ConfigScreenBuilder onSave(Runnable delegate) {
        onSave = delegate;
        return this;
    }

    public ConfigScreenBuilder onCancel(Runnable delegate) {
        onCancel = delegate;
        return this;
    }

    public ConfigScreenBuilder setParent(@Nullable Screen parent) {
        this.parent = parent;
        return this;
    }

    public ConfigScreenBuilder addPage(Text title, Consumer<ConfigPageBuilder> builderConsumer) {
        ConfigPageBuilder builder = new ConfigPageBuilder(title);
        builderConsumer.accept(builder);
        configPages.add(builder.build());
        return this;
    }

    public ConfigScreen build() {
        return new ConfigScreen(onSave, onCancel, configPages, parent);
    }
}
