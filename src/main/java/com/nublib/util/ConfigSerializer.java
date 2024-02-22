package com.nublib.util;

import com.nublib.NubLib;
import com.nublib.config.Config;

import java.io.*;

public class ConfigSerializer {
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