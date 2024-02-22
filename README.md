# Library for common functionality between mods
## Configuration classes/interfaces
### ConfigProviders
The abstract `ConfigProvider` class is responsible for your configuation storage.

This library includes two ConfigProviders by default, `FileConfigProvider` and `MemoryConfigProvider`. 

### Configuration class
To create a configuration inherit from the abstract `Config` or `SyncableConfig` class and create any number of `Option` properties on the class.
The `SyncableConfig` class only works for the client, and will send a synchronization packet when a coniguration has changed in the `ConfigProvider`

The `Option` property is responsible for "translating" the data from your `ConfigProvider`.
The `Option` class takes a `ConfigProvider`, key, default value and a `ISerializer`

The `ConfigProvider` argument tells the `Option` where it should retrieve the configuration key.

The default value will be used to create a key-value pair on the `ConfigProvider` if the key is not already present. It will also be used as a backup-value if parsing the value from the `ConfigProvider` fails.

The key defines which key of the key-value pairs on the `ConfigProvider` should be retrieved and parsed.

The `ISerializer` is used to parse the string value on the `ConfigProvider` to the generic type parameter on the `Option` class. A `BooleanSerializer`, `StringSerializer` and `IntSerializer` is currently included.
If you want to create your own serializer, inherit from the `ISerializer` class.

#### Examples:
##### Create the config
```java
public class ModConfig extends Config {
  public final Option<Boolean> featureEnabled = new Option<>(provider, "featureEnabled", true, new BooleanSerializer());
  public final Option<String> featureName = new Option<>(provider, "featureName", "SuperDuperName", new StringSerializer());
  
  public ModConfig(ConfigProvider provider) {
    super(provider);
  }
}
```

##### Initialize the config
```java
// ModInitializer.java
public class YourModClient implements ClientModInitializer {
  public static final ConfigProvider CONFIG_PROVIDER = FileConfigProvider.create("nubs-qol");
  public static final Config CONFIG = new ModConfig(CONFIG_PROVIDER);
  
  @Override
  public void onInitializeClient() {}
}
```

##### Use your config
```java
// SomeFunctionality.java
private void doSomething() {
  if (!YourModClient.CONFIG.featureEnabled.value()) return;
  
  // Rest of your code...
}
```

### Client/Server config synchronization

To synchronize your config between the server and client, do the following steps

In your config class, swap you `Config` for `SyncableConfig` (note that the `SyncableConfig` takes in an extra "id" argument)
```java
public class ModConfig extends SyncableConfig {
  public final Option<Boolean> featureEnabled = new Option<>(provider, "featureEnabled", true, new BooleanSerializer());
  public final Option<String> featureName = new Option<>(provider, "featureName", "SuperDuperName", new StringSerializer());
  
  public ModConfig(ConfigProvider provider, String id) {
    super(provider, id);
  }
}
```

Update the CONFIG property of the client initializer class to pass in an id
```java
public static final ModConfig CONFIG = new ModConfig(CONFIG_PROVIDER, YourMod.MOD_ID);
```

In your main mod initializer, add a `ConfigSync` property with the generic value set to the class of your configuration. The id passed inn to the `ConfigSync` must be identical to the id passed in to the `SyncableConfig` class as shown above
```java
public class YourMod implements ModInitializer {
  public static final ConfigSync<ModConfig> CONFIG_SYNC = new ConfigSync<>(YourMod.MOD_ID);
  
  @Override
  public void onInitialize() { }
}
```

Your config should be synced with the server and client, and can be used for server-side logic. Keep in mind that players without the mod installed will not have any config to sync, so null values should be trated as such
```java
private void doSomethingOnTheServer() {
  ModConfig config = YourMod.CONFIG_SYNC.configs.get(player.getUuid());
  if (config == null || !config.featureEnabled.value()) return;
 
  // Rest of your code...
}
```

### Mod menu integration

To get integration with modmenu, create a new class that implements the ModMenuIntegration interface and override the configProvider method.
In the configProvider method, return the `ConfigProvider` class that modmenu should use for its configuration screen

```java
public class ModMenu implements ModMenuIntegration {
  @Override
  public ConfigProvider configProvider() {
    return YourModClient.CONFIG_PROVIDER;
  }
}
```
