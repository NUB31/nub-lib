package com.nublib.example;

import com.nublib.config.Config;
import com.nublib.config.entry.BooleanConfigEntry;
import com.nublib.config.entry.IConfigEntry;
import com.nublib.config.entry.IntegerConfigEntry;
import com.nublib.config.entry.StringConfigEntry;
import com.nublib.config.provider.IStorageProvider;
import com.nublib.gui.ConfigScreen;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;
import org.jetbrains.annotations.Nullable;

public class ExampleConfig extends Config {
    public final IConfigEntry<String> feature1Name = new StringConfigEntry(sp, "feature1.name", "Feature 1");
    public final IConfigEntry<Integer> feature1Value = new IntegerConfigEntry(sp, "feature1.value", 25);
    public final IConfigEntry<Boolean> feature2Enabled = new BooleanConfigEntry(sp, "feature2.enabled", true);
    public final IConfigEntry<String> feature2Name = new StringConfigEntry(sp, "feature2.name", "Feature 2");
    public final IConfigEntry<Integer> feature2Value = new IntegerConfigEntry(sp, "feature2.value", 25);

    public ExampleConfig(IStorageProvider storageProvider) {
        super(storageProvider);
    }

    public Screen createConfigScreen(@Nullable Screen parent) {
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
                                                        .setTitle(Text.translatable("nub-lib.config.feature1.name.title"))
                                                        .setDescription(Text.translatable("nub-lib.config.feature1.name.description"))
                                                        .onChange(feature1Name::set))
                                                .addRange(feature1Value.get(), 0, 100, toggleGuiConfigEntryBuilder1 -> toggleGuiConfigEntryBuilder1
                                                        .setTitle(Text.translatable("nub-lib.config.feature1.value.title"))
                                                        .setDescription(Text.translatable("nub-lib.config.feature1.value.description"))
                                                        .onChange(feature1Value::set))
                                        ))
                                .addToggle(feature2Enabled.get(), toggleBuilder -> toggleBuilder
                                        .addKeyBinding(sp.getKeybinding(feature2Enabled.getKey()), (key) -> sp.setKeybinding(feature2Enabled.getKey(), key))
                                        .setTitle(Text.translatable("nub-lib.config.feature2.enabled.title"))
                                        .setDescription(Text.translatable("nub-lib.config.feature2.enabled.description"))
                                        .onChange(feature2Enabled::set)
                                        .addChildEntries(toggleEntryBuilder -> toggleEntryBuilder
                                                .addString(feature2Name.get(), stringGuiConfigEntryBuilder -> stringGuiConfigEntryBuilder
                                                        .setTitle(Text.translatable("nub-lib.config.feature2.name.title"))
                                                        .setDescription(Text.translatable("nub-lib.config.feature2.name.description"))
                                                        .onChange(feature2Name::set))
                                                .addRange(feature2Value.get(), 0, 100, toggleGuiConfigEntryBuilder1 -> toggleGuiConfigEntryBuilder1
                                                        .setTitle(Text.translatable("nub-lib.config.feature2.value.title"))
                                                        .setDescription(Text.translatable("nub-lib.config.feature2.value.description"))
                                                        .onChange(feature2Value::set))
                                        ))
                        )
                ).build();
    }
}
