package com.nublib.gui;

import com.nublib.gui.widget.EntryListWidget;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.gui.widget.DirectionalLayoutWidget;
import net.minecraft.client.gui.widget.ThreePartsLayoutWidget;
import net.minecraft.screen.ScreenTexts;
import net.minecraft.text.Text;
import org.jetbrains.annotations.Nullable;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ConfigScreen extends Screen {
    public final ThreePartsLayoutWidget layout = new ThreePartsLayoutWidget(this);
    private final Runnable onSave;
    @Nullable
    private final Runnable onCancel;
    private final List<ConfigPage> configPages;
    private final Map<ConfigPage, ButtonWidget> tabButtons;
    @Nullable
    private final Screen parent;
    @Nullable
    private ConfigPage selectedConfigPage;
    private EntryListWidget entryListWidget;

    public ConfigScreen(Runnable onSave, @Nullable Runnable onCancel, List<ConfigPage> configPages, @Nullable Screen parent) {
        super(Text.literal("Options"));
        this.parent = parent;
        this.onSave = onSave;
        this.onCancel = onCancel;
        this.configPages = configPages;
        this.tabButtons = new LinkedHashMap<>();
    }

    public static ConfigScreenBuilder builder() {
        return new ConfigScreenBuilder();
    }

    private void refreshPage(ConfigPage page) {
        selectedConfigPage = page;

        if (selectedConfigPage != null && entryListWidget != null) {
            entryListWidget.setConfigEntries(selectedConfigPage.configEntries());
        }
    }

    private void initHeader() {
        for (ConfigPage configPage : configPages) {
            tabButtons.put(configPage, ButtonWidget.builder(configPage.title(), button -> {
                refreshPage(configPage);
            }).width(100).build());
        }

        DirectionalLayoutWidget buttonPositioningWidget = layout.addHeader(DirectionalLayoutWidget.horizontal().spacing(4));
        if (buttonPositioningWidget != null) {
            tabButtons.forEach((k, v) -> buttonPositioningWidget.add(v));
        }
    }

    private void initFooter() {
        DirectionalLayoutWidget directionalLayoutWidget = layout.addFooter(DirectionalLayoutWidget.horizontal().spacing(8));
        if (onCancel != null) {
            directionalLayoutWidget.add(ButtonWidget.builder(ScreenTexts.CANCEL, (button) -> close(false)).build());
        }
        directionalLayoutWidget.add(ButtonWidget.builder(ScreenTexts.DONE, (button) -> close(true)).build());
    }

    private void initBody() {
        entryListWidget = new EntryListWidget(0, layout.getHeaderHeight(), layout.getWidth(), layout.getHeight() - layout.getHeaderHeight() - layout.getFooterHeight());
        layout.addBody(entryListWidget);

        if (!configPages.isEmpty()) {
            refreshPage(configPages.getFirst());
        }
    }

    protected void initTabNavigation() {
        layout.refreshPositions();
        entryListWidget.setPosition(0, layout.getHeaderHeight());
        entryListWidget.setDimensions(layout.getWidth(), layout.getHeight() - layout.getHeaderHeight() - layout.getFooterHeight());
    }

    @Override
    protected void init() {
        initFooter();
        initHeader();
        initBody();

        this.layout.forEachChild(this::addDrawableChild);
        this.initTabNavigation();
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        tabButtons.forEach((k, v) -> v.active = selectedConfigPage != k);
        entryListWidget.render(context, mouseX, mouseY, delta);
        super.render(context, mouseX, mouseY, delta);
    }

    private void close(boolean save) {
        if (save) {
            onSave.run();
        } else {
            if (onCancel != null) {
                onCancel.run();
            }
        }

        if (client != null) {
            client.setScreen(parent);
        }
    }

    @Override
    public void close() {
        close(true);
    }
}
