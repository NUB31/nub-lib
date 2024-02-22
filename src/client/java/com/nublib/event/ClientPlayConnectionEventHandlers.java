package com.nublib.event;

import com.nublib.NubLibClient;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayConnectionEvents;
import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayNetworkHandler;

public class ClientPlayConnectionEventHandlers implements ClientPlayConnectionEvents.Disconnect, ClientPlayConnectionEvents.Join {
	@Override
	public void onPlayDisconnect(ClientPlayNetworkHandler handler, MinecraftClient client) {
		NubLibClient.inGame = false;
	}

	@Override
	public void onPlayReady(ClientPlayNetworkHandler handler, PacketSender sender, MinecraftClient client) {
		NubLibClient.inGame = true;
	}
}
