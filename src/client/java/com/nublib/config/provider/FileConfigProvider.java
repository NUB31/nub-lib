package com.nublib.config.provider;

import com.nublib.NubLib;
import net.fabricmc.loader.api.FabricLoader;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.LinkedList;
import java.util.Optional;
import java.util.Scanner;

public class FileConfigProvider extends ConfigProvider {
	private final File file;

	private FileConfigProvider(File file) {
		this.file = file;

		try {
			boolean isNewFile = file.createNewFile();
			if (isNewFile) save();
			else loadFile();
		} catch (IOException e) {
			NubLib.LOGGER.error("Failed to load configuration file", e);
		}
	}

	public static FileConfigProvider create(String fileName) {
		File file = FabricLoader.getInstance().getConfigDir().resolve(String.format("%s.properties", fileName)).toFile();
		return new FileConfigProvider(file);
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

	@Override
	public Optional<String> getImpl(String key) {
		var value = config.get(key);
		return Optional.ofNullable(value);
	}

	@Override
	public void setImpl(String key, String value) {
		config.put(key, value);

		try {
			save();
		} catch (IOException e) {
			NubLib.LOGGER.error("Failed to save configuration", e);
		}
	}

	private void save() throws IOException {
		final LinkedList<String> content = new LinkedList<>();
		config.forEach((k, v) -> content.add(String.format("%s=%s", k, v)));

		Files.writeString(file.toPath(), String.join("\n", content));
	}
}
