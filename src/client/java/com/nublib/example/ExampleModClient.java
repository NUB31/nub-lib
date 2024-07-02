package com.nublib.example;

import com.nublib.config.provider.FileStorageProvider;
import com.nublib.config.provider.IStorageProvider;
import com.nublib.util.BindingUtil;
import net.fabricmc.api.ClientModInitializer;
import org.lwjgl.glfw.GLFW;

public class ExampleModClient implements ClientModInitializer {
    public static IStorageProvider STORAGE_PROVIDER = FileStorageProvider.create("example-config");
    public static ExampleConfig CONFIG = new ExampleConfig(STORAGE_PROVIDER);

    @Override
    public void onInitializeClient() {
        BindingUtil.bindScreenToKey(GLFW.GLFW_KEY_B, () -> CONFIG.createScreenFromConfig(null), "nub-lib.config.ui.key.show_example_config_screen_1");
        BindingUtil.bindScreenToKey(GLFW.GLFW_KEY_N, () -> CONFIG.createCustomScreenFromConfig(null), "nub-lib.config.ui.key.show_example_config_screen_2");
        BindingUtil.bindScreenToKey(GLFW.GLFW_KEY_M, () -> CONFIG.createCustomScreen(null), "nub-lib.config.ui.key.show_example_config_screen_3");
    }
}
