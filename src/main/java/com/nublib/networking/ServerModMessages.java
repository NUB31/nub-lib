package com.nublib.networking;

import net.minecraft.util.Identifier;

public class ServerModMessages {
	public static Identifier createSynConfigIdentifier(String modId) {
		return new Identifier(modId, "syn-config");
	}
}
