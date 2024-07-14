package com.nublib.config.provider;

import com.nublib.NubLib;
import net.fabricmc.loader.api.FabricLoader;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.*;

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

    public static IStorageProvider create(String fileName) {
        File file = FabricLoader
                .getInstance()
                .getConfigDir()
                .resolve(String.format("%s.properties", fileName))
                .toFile();

        return new FileStorageProvider(file);
    }

    private void loadFile() throws IOException {
        Scanner reader = new Scanner(file);
        reader.forEachRemaining(line -> parseLine(line).ifPresent(kvp -> config.put(kvp.left, kvp.right)));
    }

    private Optional<Pair<String, String>> parseLine(String line) {
        try {
            if (line.trim().startsWith("#")) {
                return Optional.empty();
            }

            var cleansed = line.split("#")[0];
            String[] kvp = cleansed.split("=");
            if (kvp.length == 2) {
                return Optional.of(new Pair<>(kvp[0].trim(), kvp[1].trim()));
            }
            return Optional.empty();
        } catch (Exception e) {
            NubLib.LOGGER.warn(String.format("failed to parse line '%s'. Error: %s", line, e.getMessage()));
            return Optional.empty();
        }
    }

    @Override
    public void save(Map<String, String> options) {
        NubLib.LOGGER.info(String.format("Saving configuration file '%s'", file.getName()));
        final List<String> content = new ArrayList<>();
        content.add("# Values");
        config.forEach((k, v) -> content.add(String.format("%s=%s", k, v)));

        try {
            Files.writeString(file.toPath(), String.join("\n", content));
        } catch (IOException e) {
            NubLib.LOGGER.error(String.format("Failed to save configuration file `%s`", file.toPath()));
        }
    }

    private record Pair<T1, T2>(T1 left, T2 right) {
    }
}
