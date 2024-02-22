package com.nublib.config;

import com.nublib.NubLibClient;
import com.nublib.config.provider.ConfigProvider;
import com.nublib.config.provider.IChangeHandler;
import com.nublib.config.sync.ConfigSync;
import com.nublib.networking.ServerModMessages;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;

public class ClientConfig extends Config {
	private final ClientConfig me = this;
	private final String id;

	public ClientConfig(ConfigProvider provider, String id) {
		super(provider);
		this.id = id;
		this.provider.addChangeListener(new ChangeHandler());
	}

	private class ChangeHandler implements IChangeHandler {
		@Override
		public void onChange(String key, String value) {
			if (!NubLibClient.inGame) return;
			byte[] serialized = ConfigSync.serialize(me);
			if (serialized == null) return;

			ClientPlayNetworking.send(ServerModMessages.createSynConfigIdentifier(id), PacketByteBufs.create().writeVarInt(serialized.length).writeBytes(serialized));
		}
	}
}
