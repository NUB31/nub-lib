# Configuration

## Create a class that inherits from the Config base class

```java
public class ExampleConfig extends Config {
    public ExampleConfig(IStorageProvider storageProvider) {
        super(storageProvider);
    }
}
```

## Add some configuration options

To add configuration entries, add create a public field that inherits from `IConfigEntry`

Here are the entries included in nub-lib:

- `BooleanConfigEntry`
- `EnumConfigEntry`
- `IntegerConfigEntry`
- `StringConfigEntry`

```java
public class ExampleConfig extends Config {
    public IConfigEntry<Boolean> featureEnabled = new BooleanConfigEntry(storageProvider, "feature.enabled", false);
    public IConfigEntry<String> featureName = new StringConfigEntry(storageProvider, "feature.name", "UwU");
    public IConfigEntry<Integer> featureValue = new IntegerConfigEntry(storageProvider, "feature.value", 69);
    public IConfigEntry<Option> featureOption = new EnumConfigEntry<>(storageProvider, "feature.option", Option.Option1);

    public ExampleConfig(IStorageProvider storageProvider) {
        super(storageProvider);
    }

    public enum Option {
        Option1,
        Option2,
        Option3
    }
}
```

## Using the configuration

### Creating the configuration instance

Create a static instance of an `IStorageProvider` and the configuration class
Storage providers are responsible for saving your configuration somewhere.
You can use one of the included ones below or create your own implementation of `IStorageProvider`:

- `FileStorageProvider`
    - Creates a {filename}.properties file in the default fabric config directory
- `MemoryStorageProvider`
    - Stores all values in memory, will not persist between restarts of the game
    - Intended for debugging purposes

```java
public class NubLib implements ModInitializer {
    public static final IStorageProvider STORAGE_PROVIDER = FileStorageProvider.create("exampleconfig");
    public static final ExampleConfig CONFIG = new ExampleConfig(STORAGE_PROVIDER);

    @Override
    public void onInitialize() {
        // Initialization code...
    }
}
```

### Getting the configuration value

```java
public void doSomething() {
    // Do something if the feature is enabled
    if (NubLibClient.CONFIG.featureEnabled.get()) {
        // Get the feature name
        String name = NubLibClient.CONFIG.featureName.get();
        // Get the feature value
        int value = NubLibClient.CONFIG.featureValue.get();

        String combined = String.format("The feature name is '%s' and the value is {%d}", name, value);
    }
}
```

## Config screen generation

If you are creating a client mod and want to automatically generate a configuration screen based on your configuration
class, a couple changes is required

### Change the configuration entry implementation

Change your configuration class to use an implementation of `IClientConfigEntry` instead of `IConfigEntry`
All included config entries has a version that implements the `IClientConfigEntry` interface. All of these takes title
and description arguments in addition to the standard ones.

```java
public class ExampleConfig extends Config {
    public final IClientConfigEntry<Boolean> featureEnabled = new ClientToggleConfigEntry(storageProvider, "feature.enabled", false, Text.translatable("..."), Text.translatable("..."));
    public final IClientConfigEntry<String> featureName = new ClientStringConfigEntry(storageProvider, "feature.name", "UwU", Text.translatable("..."), Text.translatable("..."));
    public final IClientConfigEntry<Integer> featureValue = new ClientRangeConfigEntry(storageProvider, "feature.value", 69, 0, 100, Text.translatable("..."), Text.translatable("..."));
    public final IClientConfigEntry<Option> featureOption = new ClientEnumConfigEntry<>(storageProvider, "feature.option", Option.Option1, Text.translatable("..."), Text.translatable("..."), Option.class);

    public ExampleConfig(IStorageProvider storageProvider) {
        super(storageProvider);
    }

    public enum Option {
        Option1,
        Option2,
        Option3
    }
}
```

### Move your configuration to yor client class

```java
public class NubLibClient implements ClientModInitializer {
    public static final IStorageProvider STORAGE_PROVIDER = FileStorageProvider.create(NubLib.MOD_ID);
    public static final ExampleConfig CONFIG = new ExampleConfig(STORAGE_PROVIDER);

    @Override
    public void onInitializeClient() {
        // Client initialization code...
    }
}
```

### Create a config screen based on your configuration

```java

@Override
public void onInitializeClient() {
    // Client initialization code...

    Screen configScreen = ConfigScreen
            .builder()
            .fromConfig(Text.translatable("nub-lib.config.ui.title"), NubLibClient.CONFIG)
            .onSave(CONFIG::save)
            .build();
}
```

### Add a keybinding to open your configuration screen

```java

@Override
public void onInitializeClient() {
    // Client initialization code...

    Screen configScreen = ConfigScreen
            .builder()
            .fromConfig(Text.translatable("nub-lib.config.ui.title"), NubLibClient.CONFIG)
            .onSave(CONFIG::save)
            .build();

    Utils.bindScreenToKey(GLFW.GLFW_KEY_N, configScreen, "nub-lib.ui.open_config_page");
}
```

### Integrate with mod menu

#### Create integration according to mod menu docs

Read the documentation [here](https://github.com/TerraformersMC/ModMenu/wiki/API#java-api)

When you have the entrypoint and mod menu class set up, add the screen to the `getModConfigScreenFactory` function

```java
public class ModMenu implements ModMenuApi {
    @Override
    public ConfigScreenFactory<?> getModConfigScreenFactory() {
        return screen -> ConfigScreen()
                .builder()
                .setParent(screen)
                .fromConfig(Text.literal("nub-lib Options"), NubQolClient.CONFIG)
                .onSave(NubLibClient.CONFIG::save)
                .build();
    }
}
```