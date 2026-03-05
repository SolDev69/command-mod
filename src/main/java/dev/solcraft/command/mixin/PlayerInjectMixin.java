package dev.solcraft.command.mixin;

import dev.solcraft.command.*;
import dev.solcraft.command.lib.CommandRegistry;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.mob.player.ClientPlayerEntity;
import net.minecraft.client.entity.mob.player.Input;
import net.minecraft.entity.mob.player.PlayerAbilities;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ClientPlayerEntity.class)
public class PlayerInjectMixin {
	@Shadow
	protected Minecraft minecraft;


	@Inject(method = "<init>", at=@At("TAIL"))
	private void inject(CallbackInfo callbackInfo) {
		CommandMod.theCommandRegistry = new CommandRegistry(minecraft);
	}


	@Inject(method = "sendChat", at=@At("TAIL"))
	public void parseChat(String content, CallbackInfo ci) {
		if (content.startsWith("/"))
			CommandMod.theCommandRegistry.parseCommand(content);
		else
			this.minecraft.player.addMessage("<" + this.minecraft.player.name + "> " + content);

		CommandMod.pastChats.add(content);
	}

//	@Redirect(method = "mobTick", at= @At(value = "FIELD", target = "Lnet/minecraft/entity/mob/player/PlayerAbilities;canFly:Z", opcode = Opcodes.GETFIELD))
//	private boolean modifyCanFly(PlayerAbilities instance) {
//		return true;
//	}
//	@Inject(method = "mobTick", at=@At("TAIL"))
//	public void flightHandler(CallbackInfo ci) {
//		PlayerAccessor playerAccessor = (PlayerAccessor) this;
//		if (playerAccessor.getAbilities().canFly && this.input.jumping) {
//			if (playerAccessor.getPressedJumpTwiceTimer() == 0) {
//				playerAccessor.setPressedJumpTwiceTimer(7);
//			} else {
//				playerAccessor.getAbilities().flying = !playerAccessor.getAbilities().flying;
//				playerAccessor.setPressedJumpTwiceTimer(0);
//			}
//		}
//	}
}
