package dev.solcraft.command;

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
