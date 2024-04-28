package com.nublib;

import com.nublib.event.ClientPlayConnectionEventHandlers;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayConnectionEvents;

public class NubLibClient implements ClientModInitializer {
	public static boolean inGame = false;

	@Override
	public void onInitializeClient() {
		ClientPlayConnectionEvents.DISCONNECT.register(new ClientPlayConnectionEventHandlers());
		ClientPlayConnectionEvents.JOIN.register(new ClientPlayConnectionEventHandlers());
	}
}