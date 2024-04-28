package com.nublib.config.provider.impl;

import com.nublib.NubLib;
import com.nublib.config.provider.StorageProvider;
import net.fabricmc.loader.api.FabricLoader;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.LinkedList;
import java.util.Optional;
import java.util.Scanner;

public class FileStorageProvider extends StorageProvider {
	private final File file;

	private FileStorageProvider(File file) {
		this.file = file;

		try {
			boolean isNewFile = file.createNewFile();
			if (isNewFile) save();
			else loadFile();
		} catch (IOException e) {
			NubLib.LOGGER.error("Failed to load configuration file", e);
		}
	}

	public static FileStorageProvider create(String fileName) {
		File file = FabricLoader.getInstance().getConfigDir().resolve(String.format("%s.properties", fileName)).toFile();
		return new FileStorageProvider(file);
	}

	private void loadFile() throws IOException {
		Scanner reader = new Scanner(file);
		reader.forEachRemaining(line -> parseLine(line).ifPresent(kvp -> config.put(kvp.key, kvp.value)));
	}

	private Optional<ConfigKeyValue> parseLine(String line) {
		var cleansed = line.split("#")[0];
		String[] kvp = cleansed.split("=");
		if (kvp.length == 2) {
			return Optional.of(new ConfigKeyValue(kvp[0].trim(), kvp[1].trim()));
		}
		return Optional.empty();
	}

	public void save() {
		final LinkedList<String> content = new LinkedList<>();
		config.forEach((k, v) -> content.add(String.format("%s=%s", k, v)));

		try {
			Files.writeString(file.toPath(), String.join("\n", content));
		} catch (IOException e) {
			NubLib.LOGGER.error(String.format("Failed to save configuration `%s`", file.toPath()));
		}
	}
}
