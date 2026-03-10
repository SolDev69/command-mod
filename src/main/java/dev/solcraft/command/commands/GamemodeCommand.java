package dev.solcraft.command.commands;

import dev.solcraft.command.lib.Command;
import dev.solcraft.command.lib.CommandRegistry;
import net.minecraft.client.ClientPlayerInteractionManager;
import net.minecraft.client.CreativeInteractionManager;
import net.minecraft.client.Minecraft;
import net.minecraft.client.MultiplayerInteractionManager;
import net.minecraft.client.entity.mob.player.ClientPlayerEntity;
import net.minecraft.network.packet.GameEventPacket;
import net.minecraft.server.PlayerManager;
import net.minecraft.server.entity.mob.player.ServerPlayerEntity;
import net.minecraft.world.WorldSettings;
import net.minecraft.world.storage.WorldSaveInfo;
import net.ornithemc.feather.constants.GameModes;

public class GamemodeCommand extends Command {
	public GamemodeCommand(Minecraft mc) {
		super(mc);
	}

	@Override
	public String commandName() {
		return "/gamemode";
	}

	@Override
	public void runCommand(String input) {
		String g = input.split(" ")[1];
		if (!g.equals("1") & !g.equals("0")) {
			sendMessage("Gamemode must be 0 or 1");
			return;
		}
		boolean game_bool = g.equals("1");
		this.mc.interactionManager = new MultiplayerInteractionManager(this.mc, null);
		((MultiplayerInteractionManager)this.mc.interactionManager).setGameMode(game_bool);
		if (game_bool)
			sendMessage("Gamemode set to creative");
		else
			sendMessage("Gamemode set to survival");
	}
}
