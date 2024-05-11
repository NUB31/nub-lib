package com.nublib.gui.widget.entry;

import net.minecraft.text.Text;

import java.util.function.Consumer;

public abstract class AbstractGuiConfigEntryBuilder<T> {
	protected Text title;
	protected Text description;
	protected Consumer<T> onChange;
	protected T defaultValue;
	protected String key;

	public AbstractGuiConfigEntryBuilder(String key, T defaultValue) {
		this.title = Text.empty();
		this.description = Text.empty();
		this.defaultValue = defaultValue;
		this.key = key;
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
		onChange = delegate;
		return this;
	}

	public abstract GuiConfigEntry build();
}
