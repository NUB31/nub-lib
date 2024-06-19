package com.nublib.gui.widget.entry;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.widget.ClickableWidget;
import net.minecraft.client.option.SimpleOption;
import net.minecraft.text.Text;

public class RangeGuiConfigEntryBuilder extends AbstractGuiConfigEntryBuilder<Integer> {
    private final Integer minValue;
    private final Integer maxValue;

    public RangeGuiConfigEntryBuilder(Integer defaultValue, Integer minValue, Integer maxValue) {
        super(defaultValue);
        this.minValue = minValue;
        this.maxValue = maxValue;
    }

    @Override
    public ClickableWidget createWidget() {
        return new SimpleOption<>(
                "",
                SimpleOption.emptyTooltip(),
                ((optionText, value) -> Text.translatable("options.generic_value", optionText, value)),
                new SimpleOption.ValidatingIntSliderCallbacks(minValue, maxValue, true),
                defaultValue,
                onChange
        ).createWidget(MinecraftClient.getInstance().options);
    }
}
