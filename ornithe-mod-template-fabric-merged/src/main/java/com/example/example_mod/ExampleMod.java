package com.example.example_mod;

import com.example.example_mod.command.InstantTileTickCommand;
import net.minecraft.server.command.handler.CommandRegistry;
import net.ornithemc.osl.entrypoints.api.ModInitializer;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ExampleMod implements ModInitializer {

	public static final Logger LOGGER = LogManager.getLogger("Example Mod");

	@Override
	public void init() {
		// The init method is too early for command registration.
		// The actual registration will be handled by a mixin.
		LOGGER.info("initializing example mod!");
	}

	/**
	 * This static method is called by a mixin to register commands
	 * when the server is ready.
	 *
	 * @param commandRegistry The CommandRegistry instance from the server.
	 */
	public static void registerCommands(CommandRegistry commandRegistry) {
		commandRegistry.register(new InstantTileTickCommand());
		LOGGER.info("Registered InstantTileTickCommand!");
	}
}
