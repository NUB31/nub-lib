package com.nublib;

import com.nublib.config.provider.IStorageProvider;
import com.nublib.config.provider.impl.FileStorageProvider;
import com.nublib.event.ClientPlayConnectionEventHandlers;
import com.nublib.test.TestConfig;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayConnectionEvents;

public class NubLibClient implements ClientModInitializer {
	public static final IStorageProvider STORAGE_PROVIDER = FileStorageProvider.create(NubLib.MOD_ID);
	public static final TestConfig CONFIG = new TestConfig(STORAGE_PROVIDER);
	public static boolean inGame = false;

	@Override
	public void onInitializeClient() {
		ClientPlayConnectionEvents.DISCONNECT.register(new ClientPlayConnectionEventHandlers());
		ClientPlayConnectionEvents.JOIN.register(new ClientPlayConnectionEventHandlers());
	}
}