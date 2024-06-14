package com.nublib.util;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.text.Text;

import java.util.ArrayList;
import java.util.List;

public class TooltipUtil {
    public static List<Text> wrapText(Text text, int maxWidth) {
        TextRenderer textRenderer = MinecraftClient.getInstance().textRenderer;
        List<Text> wrappedText = new ArrayList<>();

        String[] words = text.getString().split(" ");
        StringBuilder line = new StringBuilder();

        for (String word : words) {
            if (textRenderer.getWidth(line + word) <= maxWidth) {
                if (!line.isEmpty()) {
                    line.append(" ");
                }
                line.append(word);
            } else {
                wrappedText.add(Text.literal(line.toString()));
                line = new StringBuilder(word);
            }
        }

        if (!line.isEmpty()) {
            wrappedText.add(Text.literal(line.toString()));
        }

        return wrappedText;
    }
}
