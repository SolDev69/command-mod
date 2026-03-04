package dev.solcraft.command.lib;

import dev.solcraft.command.UnknownCommand;
import dev.solcraft.command.commands.*;
import net.minecraft.client.Minecraft;

import java.util.HashMap;
import java.util.Map;

public class CommandRegistry {
    private final Map<String, Command> commands = new HashMap<>();
    private final Minecraft mc;
    public CommandRegistry(Minecraft mc) {
        this.mc = mc;
		this.registerCommands();
    }

	private void registerCommands() {
		HelpCommand helpCommand = new HelpCommand(mc);
		TeleportCommand teleportCommand = new TeleportCommand(mc);
		ToggleFarlandsCommand toggleFarlandsCommand = new ToggleFarlandsCommand(mc);

		commands.put(helpCommand.commandName(), helpCommand);
		commands.put(teleportCommand.commandName(), teleportCommand);
		commands.put(toggleFarlandsCommand.commandName(), toggleFarlandsCommand);
	}

	public Map<String, Command> getCommandsObject() {
        return commands;
    }

	public void parseCommand(String content)
	{
		try (Command command = commands.get(content.split(" ")[0])) {
			command.runCommand(content);
		} catch (NullPointerException npe) {
			new UnknownCommand(mc) {
				@Override
				public String commandName() {
					return content;
				}
			}.runCommand(content);
		}
	}
}
