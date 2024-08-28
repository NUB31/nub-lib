package com.nublib.gui.widget.entry;

import com.nublib.gui.widget.custom.ToggleWidget;
import net.minecraft.client.gui.widget.ClickableWidget;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;
import java.util.function.Consumer;

public class ToggleGuiConfigEntryBuilder extends AbstractGuiConfigEntryBuilder<Boolean> {
    @Nullable
    private Consumer<Integer> onKeyChanged = null;
    private Optional<Integer> keyCode = Optional.empty();

    public ToggleGuiConfigEntryBuilder(Boolean defaultValue) {
        super(defaultValue);
    }

    public ToggleGuiConfigEntryBuilder addKeyBinding(Optional<Integer> keyCode, Consumer<Integer> onKeyChanged) {
        this.onKeyChanged = onKeyChanged;
        this.keyCode = keyCode;
        return this;
    }

    @Override
    public ClickableWidget createWidget() {
        return new ToggleWidget(defaultValue, onChange, onKeyChanged, keyCode);
    }
}