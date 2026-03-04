package dev.solcraft.command.mixin;

import dev.solcraft.command.*;
import dev.solcraft.command.commands.HelpCommand;
import dev.solcraft.command.commands.TeleportCommand;
import dev.solcraft.command.commands.ToggleFarlandsCommand;
import dev.solcraft.command.lib.Command;
import dev.solcraft.command.lib.CommandRegistry;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.mob.player.ClientPlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ClientPlayerEntity.class)
public class PlayerInjectMixin {
	@Shadow
	protected Minecraft minecraft;

	@Inject(method = "<init>", at=@At("TAIL"))
	private void inject(CallbackInfo callbackInfo) {
		CommandMod.theCommandRegistry = new CommandRegistry(minecraft);
	}

	/**
	 * @author
	 * @reason
	 */
	@Overwrite
	public void sendChat(String content) {
		if (content.startsWith("/")) {
			Command command = new UnknownCommand(minecraft);
			switch (content.split(" ")[0].split("/")[1]) {
				case "tp":
					command = new TeleportCommand(minecraft);
					break;
				case "help":
					command = new HelpCommand(minecraft);
					break;
				case "farlands":
					command = new ToggleFarlandsCommand(minecraft);
					break;
			}
			command.runCommand(content);
		}
		else {
			this.minecraft.player.addMessage("<" + this.minecraft.player.name + "> " + content);
		}
		CommandMod.pastChats.add(content);
	}

}
