package com.nublib.example;

import com.nublib.config.Config;
import com.nublib.config.entry.ClientRangeConfigEntry;
import com.nublib.config.entry.ClientStringConfigEntry;
import com.nublib.config.entry.ClientToggleConfigEntry;
import com.nublib.config.entry.IClientConfigEntry;
import com.nublib.config.provider.IStorageProvider;
import com.nublib.gui.ConfigScreen;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;
import org.jetbrains.annotations.Nullable;

public class ExampleConfig extends Config {

    public final IClientConfigEntry<String> feature1Name = new ClientStringConfigEntry(sp, "feature1.name", "Feature 1", Text.translatable("nub-lib.config.feature1.name.title"), Text.translatable("nub-lib.config.feature1.name.description"));
    public final IClientConfigEntry<Integer> feature1Value = new ClientRangeConfigEntry(sp, "feature1.value", 25, 0, 100, Text.translatable("nub-lib.config.feature1.value.title"), Text.translatable("nub-lib.config.feature1.value.description"));

    public final IClientConfigEntry<Boolean> feature2Enabled = new ClientToggleConfigEntry(sp, "feature2.enabled", true, Text.translatable("nub-lib.config.feature2.enabled.title"), Text.translatable("nub-lib.config.feature2.enabled.description"));
    public final IClientConfigEntry<String> feature2Name = new ClientStringConfigEntry(sp, "feature2.name", "Feature 2", Text.translatable("nub-lib.config.feature2.name.title"), Text.translatable("nub-lib.config.feature2.name.description"));
    public final IClientConfigEntry<Integer> feature2Value = new ClientRangeConfigEntry(sp, "feature2.value", 25, 0, 200, Text.translatable("nub-lib.config.feature2.value.title"), Text.translatable("nub-lib.config.feature2.value.description"));


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

//    /**
//     * Creates a screen based on a set of config entries that inherit from `IClientConfigEntry`.
//     * This gives you more configurability than `fromConfig` at the cost of more manual work.
//     * In this example, I put each config entry on a different page (tab)
//     */
//    public Screen createCustomScreenFromConfig(@Nullable Screen parent) {
//        return ConfigScreen
//                .builder()
//                .setParent(parent)
//                .onSave(this::save)
//                .addPage(Text.literal("Tab 1"), page -> page
//                        .addEntries(containerEntryBuilder -> {
//                            containerEntryBuilder.fromConfigEntry(feature1Enabled);
//                        }))
//                .addPage(Text.literal("Tab 2"), page -> page
//                        .addEntries(containerEntryBuilder -> {
//                            containerEntryBuilder.fromConfigEntry(feature1Name);
//                        }))
//                .build();
//    }

    /**
     * Creates a screen that works with any config.
     * Per config entry, you need at least a title, but a onChange handler is needed for real world usage.
     * The code below will create the equivalent config screen as the `createScreenFromConfig`
     * except for nesting feature1 name under feature1 enabled
     */
    public Screen createCustomScreen(@Nullable Screen parent) {
        return ConfigScreen
                .builder()
                .setParent(parent)
                .onSave(this::save)
                .addPage(Text.translatable("nub-lib.config.ui.title"), page -> page
                        .addEntries(pageEntries -> pageEntries
                                .addContainer(containerBuilder -> containerBuilder
                                        .setTitle(Text.translatable("nub-lib.config.feature1.container.title"))
                                        .setDescription(Text.translatable("nub-lib.config.feature1.container.description"))
                                        .addChildEntries(containerEntryBuilder -> containerEntryBuilder
                                                .addString(feature1Name.get(), stringGuiConfigEntryBuilder -> stringGuiConfigEntryBuilder
                                                        .setTitle(feature1Name.guiConfigEntry().title())
                                                        .setDescription(feature1Name.guiConfigEntry().description())
                                                        .onChange(feature1Name::set))
                                                .addRange(feature1Value.get(), 0, 100, toggleGuiConfigEntryBuilder1 -> toggleGuiConfigEntryBuilder1
                                                        .setTitle(feature1Value.guiConfigEntry().title())
                                                        .setDescription(feature1Value.guiConfigEntry().description())
                                                        .onChange(feature1Value::set))
                                        ))
                                .addToggle(feature2Enabled.get(), toggleBuilder -> toggleBuilder
                                        .setTitle(feature2Enabled.guiConfigEntry().title())
                                        .setDescription(feature2Enabled.guiConfigEntry().description())
                                        .onChange(feature2Enabled::set)
                                        .addChildEntries(toggleEntryBuilder -> toggleEntryBuilder
                                                .addString(feature2Name.get(), stringGuiConfigEntryBuilder -> stringGuiConfigEntryBuilder
                                                        .setTitle(feature2Name.guiConfigEntry().title())
                                                        .setDescription(feature2Name.guiConfigEntry().description())
                                                        .onChange(feature2Name::set))
                                                .addRange(feature2Value.get(), 0, 100, toggleGuiConfigEntryBuilder1 -> toggleGuiConfigEntryBuilder1
                                                        .setTitle(feature2Value.guiConfigEntry().title())
                                                        .setDescription(feature2Value.guiConfigEntry().description())
                                                        .onChange(feature2Value::set))
                                        ))
                        )
                )
                .build();
    }
}
