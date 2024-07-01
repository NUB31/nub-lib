package com.nublib.example;

import com.nublib.NubLib;
import com.nublib.config.Config;
import com.nublib.config.entry.ClientStringConfigEntry;
import com.nublib.config.entry.ClientToggleConfigEntry;
import com.nublib.config.entry.IClientConfigEntry;
import com.nublib.config.provider.IStorageProvider;
import com.nublib.gui.ConfigScreen;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;
import org.jetbrains.annotations.Nullable;

public class ExampleConfig extends Config {

    public final IClientConfigEntry<Boolean> feature1Enabled = new ClientToggleConfigEntry(sp, "feature1.enabled", true, Text.translatable("nub-lib.config.feature1.enabled.title"), Text.translatable("nub-lib.config.feature1.enabled.description"));
    public final IClientConfigEntry<String> feature1Name = new ClientStringConfigEntry(sp, "feature1.name", "Feature 1", Text.translatable("nub-lib.config.feature1.name.title"), Text.translatable("nub-lib.config.feature1.name.description"));

    public ExampleConfig(IStorageProvider storageProvider) {
        super(storageProvider);
    }

    /**
     * Creates a screen based on a config class extending from `Config` using reflection.
     * Will take all entries that implement `IClientConfigEntry` and put them on a single page (tab).
     */
    public Screen createScreenFromConfig(@Nullable Screen parent) {
        return ConfigScreen
                .builder()
                .setParent(parent)
                .onSave(this::save)
                .fromConfig(Text.translatable("nub-lib.config.ui.title"), this)
                .build();
    }

    /**
     * Creates a screen based on a set of config entries that inherit from `IClientConfigEntry`.
     * This gives you more configurability than `fromConfig` at the cost of more manual work.
     * In this example, I put each config entry on a different page (tab)
     */
    public Screen createCustomScreenFromConfig(@Nullable Screen parent) {
        return ConfigScreen
                .builder()
                .setParent(parent)
                .onSave(this::save)
                .addPage(Text.literal("Tab 1"), page -> page
                        .addEntries(entryListBuilder -> {
                            entryListBuilder.fromConfigEntry(feature1Enabled);
                        }))
                .addPage(Text.literal("Tab 2"), page -> page
                        .addEntries(entryListBuilder -> {
                            entryListBuilder.fromConfigEntry(feature1Name);
                        }))
                .build();
    }

    /**
     * Creates a screen that works with any config.
     * Per config entry, you need at least a title, but a onChange handler is needed for real world usage.
     * The code below will create the equivalent config screen as the `createScreenFromConfig`
     */
    public Screen createCustomScreen(@Nullable Screen parent) {
        NubLib.LOGGER.info("UWU");
        return ConfigScreen
                .builder()
                .setParent(parent)
                .onSave(this::save)
                .addPage(Text.translatable("nub-lib.config.ui.title"), page -> {
                    page.addEntries(entryListBuilder -> {
                        entryListBuilder.addToggle(feature1Enabled.get(), toggleGuiConfigEntryBuilder -> {
                            toggleGuiConfigEntryBuilder
                                    .setTitle(feature1Enabled.guiConfigEntry().title())
                                    .setDescription(feature1Enabled.guiConfigEntry().description())
                                    .onChange(feature1Enabled::set)
                                    .addChildEntries(entryListBuilder1 -> {
                                        entryListBuilder1.addString(feature1Name.get(), stringGuiConfigEntryBuilder -> {
                                            stringGuiConfigEntryBuilder
                                                    .setTitle(feature1Name.guiConfigEntry().title())
                                                    .setDescription(feature1Name.guiConfigEntry().description())
                                                    .onChange(feature1Name::set);
                                        });
                                    });
                        });
                    });
                })
                .build();
    }
}
