package dev.solcraft.command;

import net.minecraft.client.Minecraft;

import java.util.HashMap;
import java.util.Map;

public class CommandRegistry {
    private final Map<String, Command> commands = new HashMap<>();
    private final Minecraft mc;
    public CommandRegistry(Minecraft mc) {
        this.mc = mc;
        register(TeleportCommand.class);
        register(HelpCommand.class);
        register(ToggleFarlandsCommand.class);
    }


    private void register(Class<? extends Command> clazz) {
        try {
            CommandName annotation = clazz.getAnnotation(CommandName.class);

            if (annotation != null) {
                Command cmd = clazz.getConstructor(Minecraft.class).newInstance(mc);
                commands.put(annotation.value(), cmd);
            }
        } catch (Exception e) {
            this.mc.player.addMessage(e.getMessage());
        }
    }

    public Map<String, Command> getCommandsObject() {
        return commands;
    }
}
