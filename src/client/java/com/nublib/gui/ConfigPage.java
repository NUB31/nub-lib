package com.nublib.gui;

import com.nublib.gui.widget.entry.GuiConfigEntry;
import net.minecraft.text.Text;

import java.util.List;

public record ConfigPage(Text title, List<GuiConfigEntry> configEntries) {
}
