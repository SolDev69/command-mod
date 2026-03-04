package dev.solcraft.command;

import net.minecraft.client.Minecraft;

public class UnknownCommand extends Command {
    public UnknownCommand(Minecraft mc) {
        super(mc);
    }

    @Override
    public void runCommand(String input) {
        this.mc.player.addMessage("Unknown Command: " + input);
    }
}
