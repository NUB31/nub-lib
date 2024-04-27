package com.nublib;

import com.nublib.config.provider.impl.MemoryStorageProvider;
import com.nublib.event.ClientPlayConnectionEventHandlers;
import com.nublib.test.TestConfig;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayConnectionEvents;

public class NubLibClient implements ClientModInitializer {
	public static boolean inGame = false;
	public static final MemoryStorageProvider STORAGE_PROVIDER = new MemoryStorageProvider();
	public static final TestConfig CONFIG = new TestConfig(STORAGE_PROVIDER);

	@Override
	public void onInitializeClient() {
		ClientPlayConnectionEvents.DISCONNECT.register(new ClientPlayConnectionEventHandlers());
		ClientPlayConnectionEvents.JOIN.register(new ClientPlayConnectionEventHandlers());
	}
}