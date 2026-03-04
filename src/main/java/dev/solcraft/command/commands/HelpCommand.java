package dev.solcraft.command.commands;

import dev.solcraft.command.CommandMod;
import dev.solcraft.command.lib.Command;
import net.minecraft.client.Minecraft;

public class HelpCommand extends Command {
    public HelpCommand(Minecraft mc) {
        super(mc);
    }

	@Override
	public String commandName() {
		return "/help";
	}

    @Override
    public void runCommand(String input) {
        sendMessage("Available commands: " + CommandMod.theCommandRegistry.getCommandsObject().keySet());
    }
}
