package com.example.example_mod.dev.solcraft.command;

import net.minecraft.client.Minecraft;

@CommandName("/tp")
public class TeleportCommand extends Command {
    public TeleportCommand(Minecraft mc) {
        super(mc);
    }

    @Override
    public void runCommand(String input) {
        String[] arr = input.split(" ");
        double x = this.mc.player.x;
        double y = this.mc.player.y;
        double z = this.mc.player.z;
        try {
            if (arr[1].startsWith("~") && arr[1].split("~").length >= 1) {
                x += Double.parseDouble(arr[1].split("~")[1]);
            } else if (!arr[1].equals("~")){
                x = Double.parseDouble(arr[1]);
            }
        } catch (NumberFormatException | NullPointerException e) {
            this.mc.player.addMessage(e.getMessage());
        }
        try {
            if (arr[2].startsWith("~") && arr[2].split("~").length >= 1) {
                y += Double.parseDouble(arr[2].split("~")[2]);
            } else if (!arr[2].equals("~")){
                y = Double.parseDouble(arr[2]);
            }
        } catch (NumberFormatException | NullPointerException e) {
            this.mc.player.addMessage(e.getMessage());
        }
        try {
            if (arr[3].startsWith("~") && arr[3].split("~").length >= 1) {
                z += Double.parseDouble(arr[3].split("~")[3]);
            } else if (!arr[3].equals("~")) {
                z = Double.parseDouble(arr[3]);
            }
        } catch (NumberFormatException | NullPointerException e) {
            this.mc.player.addMessage(e.getMessage());
        }
        this.mc.player.setPosition(x, y, z);
        this.mc.player.addMessage("Teleported player to (" + (int)x + ", " + (int)y + ", " + (int)z + ") successfully");
    }
}
