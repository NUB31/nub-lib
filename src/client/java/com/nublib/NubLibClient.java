package com.nublib;

import com.nublib.config.TestConfig;
import com.nublib.config.provider.FileConfigProvider;
import com.nublib.config.provider.IConfigProvider;
import com.nublib.modmenu.DynamicScreenFactory;
import net.fabricmc.api.ClientModInitializer;

public class NubLibClient implements ClientModInitializer {
	public static final IConfigProvider CONFIG_PROVIDER = FileConfigProvider.create(NubLib.MOD_ID);
	public static final TestConfig CONFIG = new TestConfig(CONFIG_PROVIDER);
	public static final DynamicScreenFactory CONFIG_SCREEN_FACTORY = new DynamicScreenFactory(CONFIG_PROVIDER);

	@Override
	public void onInitializeClient() {

	}
}