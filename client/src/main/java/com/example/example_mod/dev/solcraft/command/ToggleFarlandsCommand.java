package com.example.example_mod.dev.solcraft.command;

import net.minecraft.client.Minecraft;

@CommandName("/farlands")
public class ToggleFarlandsCommand extends Command {
    public ToggleFarlandsCommand(Minecraft mc) {
        super(mc);
    }

    @Override
    public void runCommand(String input) {
        boolean farlands = CommandMod.genFarlands = !CommandMod.genFarlands;
        if (farlands) {
            this.mc.player.addMessage("Farlands enabled!");
        } else {
            this.mc.player.addMessage("Farlands disabled!");
        }
    }
}
