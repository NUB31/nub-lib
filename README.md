# Library for common functionality between mods
## Configuration classes/interfaces
### Put your configuration somewhere
Create some ConfigProperty<T> members where you want to keep your configuration.
In this case i create a Config class.

The ConfigProperty class taks inn an `IConfigProvider`, key name (name that the `ConfigProvider` uses for lookups), default value and a serializer.

`BooleanSerializer`, `StringSerializer` and `IntSerializer` is currently included.
If you want to create your own serializer, inherit from the `ISerializer` class
```java
// Config.java
public class Config {
  public final ConfigProperty<Boolean> featureEnabled;
  public final ConfigProperty<String> itemName;

  public Config(IConfigProvider configProvider) {
      this.featureEnabled = new ConfigProperty<>(configProvider, "featureEnabled", true, new BooleanSerializer());
      this.itemName = new ConfigProperty<>(configProvider, "itemName", "UwU", new StringSerializer());
  }
} 
```
### Initialize configuration
Initialize configuration with an IConfigProvider

This library includes two ConfigProviders by default, FileConfigProvider and MemoryConfigProvider. If you want to create your own ConfigProvider, inherit from the IConfigProvider class
```java
// ModInitializer.java
public class YourMod implements ModInitializer {
  public static final Config CONFIG = new Config(FileConfigProvider.create("nubs-qol"));

  @Override
  public void onInitialize() {}
}
```

### Use your config
```java
// SomeFunctionality.java
private void doSomething() {
  if (!YourMod.CONFIG.featureEnabled.get()) return;
  
  // Rest of your code...
}
```

### Mod menu integration
#### WIP
