package com.nublib;

import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NubLib implements ModInitializer {
	public static final String MOD_ID = "nub-lib";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		LOGGER.info("Initializing NUB Lib");
	}
}