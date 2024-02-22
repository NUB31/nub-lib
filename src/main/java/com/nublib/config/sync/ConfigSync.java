package com.nublib.config.sync;

import com.nublib.NubLib;
import com.nublib.config.Config;
import com.nublib.networking.ServerModMessages;
import net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;

import java.io.*;
import java.util.HashMap;
import java.util.UUID;

public class ConfigSync<T extends Config> {
	public HashMap<UUID, T> configs = new HashMap<>();

	public ConfigSync(String id) {
		ServerPlayNetworking.registerGlobalReceiver(ServerModMessages.createSynConfigIdentifier(id), (server, player, handler, buf, responseSender) -> {
			configs.put(player.getUuid(), deserialize(buf.readByteArray()));
			NubLib.LOGGER.info(String.format("SYNCED \"%s\"", player.getUuid()));
		});
		ServerPlayConnectionEvents.DISCONNECT.register((networkHandler, minecraftServer) -> {
			configs.remove(networkHandler.player.getUuid());
			NubLib.LOGGER.info(String.format("REMOVED \"%s\" from config sync", networkHandler.player.getUuid()));
		});
	}

	public static byte[] serialize(Config config) {
		try (ByteArrayOutputStream bos = new ByteArrayOutputStream(); ObjectOutput out = new ObjectOutputStream(bos)) {
			out.writeObject(config);
			return bos.toByteArray();
		} catch (IOException e) {
			NubLib.LOGGER.error("Config serialization failed", e);
			return null;
		}
	}

	public static <T extends Config> T deserialize(byte[] data) {
		try (ByteArrayInputStream bis = new ByteArrayInputStream(data);
			 ObjectInput in = new ObjectInputStream(bis)) {
			return (T) in.readObject();
		} catch (IOException | ClassNotFoundException e) {
			NubLib.LOGGER.error("Config deserialization failed", e);
			return null;
		}
	}
}
