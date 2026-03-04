package dev.solcraft.command;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.ornithemc.osl.entrypoints.api.ModInitializer;

import java.util.ArrayList;
import java.util.List;

public class CommandMod implements ModInitializer {

	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod name as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final Logger LOGGER = LogManager.getLogger("Example Mod");
	public static boolean genFarlands = false;
	public static List<String> pastChats = new ArrayList<>();
	public static CommandRegistry theCommandRegistry;
	@Override
	public void init() {
		LOGGER.info("initializing example mod!");
	}
}
