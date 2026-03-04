package dev.solcraft.command.commands;

import dev.solcraft.command.CommandMod;
import dev.solcraft.command.lib.Command;
import dev.solcraft.command.lib.CommandName;
import net.minecraft.client.Minecraft;

@CommandName("/help")
public class HelpCommand extends Command {
    public HelpCommand(Minecraft mc) {
        super(mc);
    }

    @Override
    public void runCommand(String input) {
        sendMessage("Available commands: " + CommandMod.theCommandRegistry.getCommandsObject().keySet());
    }
}
