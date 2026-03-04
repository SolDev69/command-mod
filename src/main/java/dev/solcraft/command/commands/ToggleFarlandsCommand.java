package dev.solcraft.command.commands;

import dev.solcraft.command.CommandMod;
import dev.solcraft.command.lib.Command;
import net.minecraft.client.Minecraft;

import static chatgptgen.GPTGeneratedMixinPlugin.*;

public class ToggleFarlandsCommand extends Command {
    public ToggleFarlandsCommand(Minecraft mc) {
        super(mc);
    }

	@Override
	public String commandName() {
		return "/farlands";
	}

    @Override
    public void runCommand(String input) {
		boolean farlands = CommandMod.genFarlands = !CommandMod.genFarlands;
		if (HAS_REGION_INT_XYZ) {
			if (farlands) {
				this.mc.player.addMessage("Farlands enabled!");
			} else {
				this.mc.player.addMessage("Farlands disabled!");
			}
		} else {
			this.mc.player.addMessage("This command does nothing on b1.7.3 and older!");
		}
    }
}
