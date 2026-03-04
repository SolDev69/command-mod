package dev.solcraft.command;

import net.minecraft.client.Minecraft;

public abstract class Command {
    protected Minecraft mc;

    protected Command(Minecraft mc) {
        this.mc = mc;
    }


    public abstract void runCommand(String input);

    protected void sendMessage(String o) {
        this.mc.player.addMessage(o);
    }

}
