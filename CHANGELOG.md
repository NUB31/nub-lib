* Change GuiConfigEntry to take a Supplier<ClickableWidget> instead of a ClickableWidget directly
    * Should not affect you unless you have created your own extensions of GuiConfigEntry
* Added examples
* Removed the static inGame boolean since you can just check if MinecraftInstance.player is null